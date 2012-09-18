package com.genscript.gsscm.quote.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.constant.CountryCode;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.PackageHelper;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.service.OrderQuoteUtil;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.dao.ShipConditionDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ShipCondition;
import com.genscript.gsscm.quote.dao.QuoteAddressDao;
import com.genscript.gsscm.quote.dao.QuoteDao;
import com.genscript.gsscm.quote.dao.QuoteMainBeanDao;
import com.genscript.gsscm.quote.dao.QuotePackageDao;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quote.dto.QuotePackageItemDTO;
import com.genscript.gsscm.quote.entity.QuoteAddress;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.quote.entity.QuotePackage;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceShipConditionDao;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;
import com.genscript.gsscm.shipment.dao.ShipZoneDao;
import com.genscript.gsscm.shipment.entity.ShipZone;

/**
 * @author wangsf
 * 
 */
@Service
@Transactional
public class QuoteShippingService {
	@Autowired
	protected QuoteMainBeanDao quoteMainDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
	private QuotePackageDao quotePackageDao;
	@Autowired
	private ShipZoneDao shipZoneDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ShipConditionDao shipConditionDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceShipConditionDao serviceShipConditionDao;
	@Autowired
	private OrderQuoteUtil orderQuoteUtil;
	@Autowired
	private QuoteDao quoteDao;
	@Autowired
	private QuoteAddressDao quoteAddressDao;
	@Autowired
	private WarehouseDao warehouseDao;
	
	/**
	 * 根据多个Item进行按照业务规则生成ShippingPackage. 如果orderNo为null, 则生成临时的， 否则要保存进数据库.
	 * 
	 * @param itemDTOList
	 */
	@Transactional(propagation = Propagation.NESTED)
	public List<QuotePackageDTO> calShippingPackage(
			final List<QuoteItemDTO> itemDTOList, Integer quoteNo,
			Integer userId, Integer companyId, Integer warehouseId) {
		if (itemDTOList == null || itemDTOList.isEmpty()) {
			return null;
		}
		// 判断是否能自动计算生成package.
		this.checkAutoCalPackage(itemDTOList, warehouseId, companyId);
		QuoteMain quote = this.quoteDao.getById(quoteNo);
		this.quoteDao.getSession().evict(quote);
		QuoteAddress quoteShipAddr = this.quoteAddressDao.getById(quote.getShiptoAddrId());
		// 对QuoteItem list进行分组.
		Map<String, List<QuoteItemDTO>> packageMap = new HashMap<String, List<QuoteItemDTO>>();
		int size = itemDTOList.size();
		for (int i = 0; i < size; i++) {
			QuoteItemDTO itemDTO = itemDTOList.get(i);
			String key = itemDTO.getType()+itemDTO.getClsId();
			if (itemDTO.getShipToAddress() == null) {
				if (itemDTO.getShiptoAddrId() != null) {
					QuoteAddress itemShipAddr = this.quoteAddressDao.getById(itemDTO.getShiptoAddrId());	
					itemDTO.setShipToAddress(itemShipAddr);
				} else {
					itemDTO.setShipToAddress(quoteShipAddr);
				}
			}
			if (QuoteItemType.PRODUCT.value().equals(itemDTO.getType())) {
				if (itemDTO.getShipSchedule() == null) {
					itemDTO.setShipSchedule(productDao.getProductByCatalogNo(itemDTO.getCatalogNo()).getLeadTime());
				}
			} else {
				if (itemDTO.getShipSchedule() == null) {
					itemDTO.setShipSchedule(serviceDao.getServiceByCatalogNo(itemDTO.getCatalogNo()).getLeadTime());
				}
				if (StringUtils.isNotBlank(itemDTO.getParentId())) {
                	String parentKey = getParentKey(quoteNo, itemDTO.getParentId());
                	if (StringUtils.isNotBlank(parentKey)) {
                		key = parentKey;
                	} else {
                		break;
                	}
                }
			}
			if (packageMap.containsKey(key)) {// 更改已存在Package中的OrderItem list.
				List<QuoteItemDTO> packageItemList = packageMap.get(key);
				packageItemList.add(itemDTO);
				packageMap.put(key, packageItemList);
			} else {// 新增一个Package.
				List<QuoteItemDTO> packageItemList = new ArrayList<QuoteItemDTO>();
				packageItemList.add(itemDTO);
				packageMap.put(key, packageItemList);
			}
		}
		// 先产生内存中的各单个Package及PackageItem list.
		List<PackageHelper> packageTempList = new ArrayList<PackageHelper>();
		Iterator<Map.Entry<String, List<QuoteItemDTO>>> it = packageMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<QuoteItemDTO>> entry = it.next();
			List<QuoteItemDTO> packageItemList = entry.getValue();
			PackageHelper packageHelper = this.genPackage(packageItemList,
					warehouseId);
			packageTempList.add(packageHelper);
		}

		String quoteShipToAddrCountry = null;
		if (quote.getShiptoAddrId() != null) {
			QuoteAddress shipToAddress = quoteAddressDao.getById(quote.getShiptoAddrId());
			if (shipToAddress != null) {
				quoteShipToAddrCountry = shipToAddress.getCountry();
			}
		}
		// 产生内存中已排序的多个Package list.
		List<QuotePackageDTO> packageList = this.genPackageList(
				packageTempList, quoteNo, companyId, warehouseId, quoteShipToAddrCountry);
		return packageList;
	}
	
	 /**
     * 获取最高父级的Type和ClsId
     * @author Zhang Yong
     * @param quoteNo
     * @param parentId
     * @return
     */
    private String getParentKey (Integer quoteNo, String parentId) {
    	String key = null;
    	@SuppressWarnings("unchecked")
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), quoteNo.toString());
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO itemDTO = entry.getValue();
			String id = entry.getKey();
			if (parentId.equals(id) && !QuoteItemStatusType.CN.value().equalsIgnoreCase(itemDTO.getStatus())) {
				if (StringUtils.isNotBlank(itemDTO.getParentId())) {
					key = getParentKey(quoteNo, itemDTO.getParentId());
					return key;
				} else {
					key = itemDTO.getType() + itemDTO.getClsId();
			    	return key;
				}
			}
		}
    	return key;
    }
	
	/**
	 * 判断方法作了调整, by wangsf 2011-01-10.
	 */
	/**
	 * 判断是否能够自动calculate shipping package. warehouseId, shipMethod, packageType,
	 * quoteAddress.四个元素是否完全一致. 并且设置packageType
	 * 
	 * @param itemDTOList
	 */
	private void checkAutoCalPackage(final List<QuoteItemDTO> itemDTOList,
			Integer warehouseId, Integer companyId) {
		if (warehouseId == null) {
			throw new BussinessException(
					BussinessException.SHIP_PKG_ERROR_WAREHOUSEID);
		}
		
		// 通过set判断唯一集的数量
		Set<String> typeSet = new HashSet<String>();
		Set<String> packageTypeSet = new HashSet<String>();
		Set<String> addrSet = new HashSet<String>();
		Set<Integer> shipMethodSet = new HashSet<Integer>();
		for (int i = 0; i < itemDTOList.size(); i++) {
			QuoteItemDTO dto = itemDTOList.get(i);
			typeSet.add(dto.getType().toLowerCase());
			shipMethodSet.add(dto.getShipMethod());
			QuoteAddress quoteAddress = dto.getShipToAddress();
			if (quoteAddress == null) {
				addrSet.add(null);
			} else {
				addrSet.add(AddressUtil.getUniqueAddress(quoteAddress
						.getFirstName(), quoteAddress.getMidName(),
						quoteAddress.getLastName(), quoteAddress.getState(),
						quoteAddress.getCountry()));
			}
			// 获得packageType
			String dtoPackageType = null;
			try {
				if (dto.getType().equals(QuoteItemType.SERVICE.value())) {
					Integer serviceId = this.serviceDao.getServiceByCatalogNo(
							dto.getCatalogNo()).getServiceId();
					dtoPackageType = this.serviceShipConditionDao
							.getShipCondition(serviceId).getShipPkgType();
				} else {
					Integer productId = this.productDao.getProductByCatalogNo(
							dto.getCatalogNo()).getProductId();
					dtoPackageType = this.shipConditionDao.getShipCondition(
							productId).getShipPkgType();
				}
			} catch (Exception e) {
				throw new BussinessException(
						BussinessException.INF_CAL_PACKING_DIFF,
						"ProductShipCondition's shipPackageType");
			}
			dto.setPackageType(dtoPackageType);
			packageTypeSet.add(dtoPackageType);
		}		
		int typeCount = typeSet.size();
		if (shipMethodSet.size() > typeCount || typeCount == 0
				|| shipMethodSet.contains(null)) {// typeCount为0说明itemList为空，
			System.out.println("shipMethodSet: " + shipMethodSet);
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"QuoteItem's shipMethod");
		}
		if (addrSet.size() > typeCount || addrSet.contains(null)) {
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"QuoteItem's shipToAddress");
		}
		if (packageTypeSet.size() > typeCount || packageTypeSet.contains(null)) {
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"ProductShipCondition's orderPackageType");
		}
	}
	
	/**
	 * 在内存中产生临时的shipPackage, 根据totalActWeight排序后才能进行真正的保存.
	 * 
	 * @param itemDTOList
	 * @param idList
	 * @param groupFirst
	 * @param orderMainAddress
	 * @return
	 */
	private PackageHelper genPackage(List<QuoteItemDTO> packageItemList,
			Integer warehouseId) {
		QuoteItemDTO groupFirst = packageItemList.get(0);
		PackageHelper packageHelper = new PackageHelper();
		QuotePackageDTO quotePackage = new QuotePackageDTO();
		// shipPackage.setShipAddrId(groupFirst.getShiptoAddrId());
		if (groupFirst.getShipToAddress() != null) {
			List<String> tmpList = new ArrayList<String>();
        	QuoteAddress shipToAddress = groupFirst.getShipToAddress();
        	tmpList.add(shipToAddress.getFirstName() + " " + shipToAddress.getLastName());
        	tmpList.add(shipToAddress.getOrgName());
    		String addrStr = "";
    		if (!StringUtils.isEmpty(shipToAddress.getAddrLine1())) {
    			addrStr = shipToAddress.getAddrLine1();
    		}
    		if (!StringUtils.isEmpty(shipToAddress.getAddrLine2())) {
    			addrStr = addrStr + ", " + shipToAddress.getAddrLine2();
    		}
    		if (!StringUtils.isEmpty(shipToAddress.getAddrLine3())) {
    			addrStr = addrStr + ", " + shipToAddress.getAddrLine3();
    		}
    		tmpList.add(addrStr);
    		String cityStr = "";
    		if (!StringUtils.isEmpty(shipToAddress.getCity())) {
    			cityStr = shipToAddress.getCity();
    		}
    		if (!StringUtils.isEmpty(shipToAddress.getState())) {
    			if (cityStr.equalsIgnoreCase("")) {
    				cityStr = shipToAddress.getState() + " "
    						+ shipToAddress.getZipCode();
    			} else {
    				cityStr = cityStr + ", " + shipToAddress.getState() + " "
    						+ shipToAddress.getZipCode();
    			}
    		}
    		if (!StringUtils.isEmpty(shipToAddress.getCountry())) {
    			if (cityStr.equalsIgnoreCase("")) {
    				cityStr = shipToAddress.getCountry();
    			} else {
    				cityStr = cityStr + ", " + shipToAddress.getCountry();
    			}
    		}
    		tmpList.add(cityStr);
    		quotePackage.setShiptoAddress(StringUtils.join(tmpList.toArray(), "\n"));
		}
		quotePackage.setWarehouseId(warehouseId);
		quotePackage.setStatus("Ready");
		Double totalActWeight = null;
		Double lowestT = null;
		try {
			lowestT = this.getPackageLowestTemperature(packageItemList);
			totalActWeight = this.getPackageTotalWeight(packageItemList);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BussinessException(
					BussinessException.ERR_CAL_PACKING_PKGWEIGHT);
		}
		quotePackage.setActualWeight(totalActWeight);
		packageHelper.setLowestT(lowestT);
		quotePackage.setDeliveryType(groupFirst.getShipToAddress()
				.getAddrClass());
		packageHelper.setTotalActWeight(totalActWeight);
		packageHelper.setQuotePackageDTO(quotePackage);
		packageHelper.setQuotePackageItemList(packageItemList);
		return packageHelper;
	}
	
	/**
	 * 按照顺序保存.
	 * 
	 * @param itemDTOList
	 * @param packageTempList
	 * @param orderNo
	 * @param userId
	 */
	@SuppressWarnings("unchecked")
	private List<QuotePackageDTO> genPackageList(
			List<PackageHelper> packageTempList, Integer quoteNo,
			Integer companyId, Integer warehouseId, String country) {
		List<QuotePackageDTO> packageList = new ArrayList<QuotePackageDTO>();
		Collections.sort(packageTempList, new BeanComparator("totalActWeight"));
        Map<String, QuotePackageDTO> packageMap = new LinkedHashMap<String, QuotePackageDTO>();
        QuoteMainDTO sessQuote = (QuoteMainDTO)SessionUtil.getRow(SessionConstant.Quote.value(), quoteNo.toString());
        Double rate = null;
        Double handlingRate = null;
        if (!StringUtils.isEmpty(sessQuote.getQuoteCurrency()) 
        		&& !StringUtils.isEmpty(sessQuote.getBaseCurrency()) 
        		&& sessQuote.getExchRateDate() != null) {
        	rate = exchRateByDateDao.getCurrencyRate(sessQuote.getBaseCurrency(), 
        			sessQuote.getQuoteCurrency(), sessQuote.getExchRateDate());
        	handlingRate = exchRateByDateDao.getCurrencyRate(sessQuote.getQuoteCurrency(), 
        			sessQuote.getBaseCurrency(), sessQuote.getExchRateDate());
        }
        if (rate == null || handlingRate == null) {
        	throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
        }
        int packageSeq = 1;
		for (PackageHelper packageTemp : packageTempList) {
			String tempPackageId = null;
        	try {
				tempPackageId = SessionUtil.generateTempId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<QuoteItemDTO> pkgQuoteItemList = packageTemp.getQuotePackageItemList();
			int totalQty = 0;
		    Double quoteAmount = 0.0;
		    QuoteItemDTO groupFirst = null;
            for (QuoteItemDTO pkgItem : pkgQuoteItemList) {
            	if (QuoteItemStatusType.CN.value().equals(pkgItem.getStatus())) {
    				continue;
    			}
            	if (QuoteItemType.SERVICE.value().equalsIgnoreCase(pkgItem.getType())) {
	            	if (StringUtils.isBlank(pkgItem.getParentId())) {
	                	totalQty += pkgItem.getQuantity();
	            		if (groupFirst == null) {
	                        groupFirst = pkgItem;
	            		}
	            	}
				} else {
					if (groupFirst == null) {
						groupFirst = pkgItem;
					}
					totalQty += pkgItem.getQuantity();
				}
    			if (pkgItem.getAmount() == null || pkgItem.getAmount().doubleValue() <= 0) {
    				continue;
    			}
    			quoteAmount += pkgItem.getAmount().doubleValue();
            }
            quoteAmount = quoteAmount*handlingRate;
			QuotePackageDTO quotePackage = packageTemp.getQuotePackageDTO();
			quotePackage.setQuoteNo(quoteNo);
			quotePackage.setBillableWeight(quotePackage.getActualWeight());
			quotePackage.setShiptoAddress(getPkgShiptoAddress(groupFirst));
			quotePackage.setWarehouseName(warehouseDao.getById(warehouseId).getName());
			// 获得Package's zone.
			String zipCode = groupFirst.getShipToAddress().getZipCode();
			ShipZone shipZone = this.shipZoneDao.getShipZone(groupFirst
					.getShipMethod(), warehouseId, zipCode, country);
			if (shipZone != null) {
				quotePackage.setZone(shipZone.getZoneCode());
			}
			quotePackage.setPkgBatchSeq(1);
			quotePackage.setPkgBatchCount(1);
			quotePackage.setShipMethod(groupFirst.getShipMethod());
			quotePackage.setPackageType(groupFirst.getPackageType());
			quotePackage.setShipAddrId(groupFirst.getShiptoAddrId());
			 //获得package的Handding Fee
            BigDecimal handlingFee = new BigDecimal(orderQuoteUtil.getHandlingFee(groupFirst.getClsId(), groupFirst.getType(), 
            		quotePackage.getBillableWeight(), totalQty, quoteAmount));
            // 获得Package's baseCharge.
			BigDecimal charge = this.orderQuoteUtil.getShippingFee(groupFirst.getShipMethod(), 
					warehouseId, quotePackage.getBillableWeight(), quotePackage.getZone(), 
					groupFirst.getClsId(), groupFirst.getType(), totalQty, 
					quoteAmount, packageSeq);
			quotePackage.setBaseCharge(charge.doubleValue());
			quotePackage.setDeliveryConfirmFee(0.0);
			quotePackage.setPackagingFee(0.0);
			// 获得 actlCarrCharge.
			Double actlCarrCharge = BigDecimal.valueOf(
					quotePackage.getBaseCharge()).add(
					BigDecimal.valueOf(quotePackage.getDeliveryConfirmFee()))
					.add(BigDecimal.valueOf(quotePackage.getPackagingFee()))
					.doubleValue();
			quotePackage.setActlCarrCharge(actlCarrCharge);
			quotePackage.setInsuranceCharge(0.0);
			quotePackage.setAdtlCustomerCharge(0.0);
			// 获得 carrierCharge.
			BigDecimal carrCharge = null;
            handlingFee = handlingFee.multiply(new BigDecimal(rate));
            boolean isJp = CountryCode.JP.value().equals(country)?true:false;
            if (isJp) {
                Double lowestT = packageTemp.getLowestT();
                if (lowestT == null || lowestT.doubleValue() >= 20) {
                    carrCharge = new BigDecimal(1500);
                } else {
                    carrCharge = new BigDecimal(3000);
                }
            	Double jpRate = exchRateByDateDao.getCurrencyRate(CurrencyType.JPY.value(), 
            			CurrencyType.USD.value(), new Date());
                carrCharge = (CurrencyType.JPY.value().equals(sessQuote.getQuoteCurrency())?
                		carrCharge:carrCharge.multiply(new BigDecimal(jpRate))
                		.multiply(new BigDecimal(rate))).add(handlingFee);
            } else {
                carrCharge = BigDecimal.valueOf(
                        quotePackage.getActlCarrCharge()).add(
                        BigDecimal.valueOf(quotePackage.getInsuranceCharge())).add(
                        BigDecimal.valueOf(quotePackage.getAdtlCustomerCharge()));
                carrCharge = carrCharge.multiply(new BigDecimal(rate));
            }
            handlingFee = CurrencyType.JPY.value().equals(sessQuote.getQuoteCurrency())?
            		handlingFee.setScale(0, BigDecimal.ROUND_HALF_UP)
            		:handlingFee.setScale(2, BigDecimal.ROUND_HALF_UP);
            quotePackage.setHandlingFee(handlingFee.doubleValue());
            carrCharge = CurrencyType.JPY.value().equals(sessQuote.getQuoteCurrency())?
            		carrCharge.setScale(0, BigDecimal.ROUND_HALF_UP)
            		:carrCharge.setScale(2, BigDecimal.ROUND_HALF_UP);
    		quotePackage.setBaseCharge(carrCharge.doubleValue());
    		quotePackage.setActlCarrCharge(carrCharge.doubleValue());
			quotePackage.setCarrierCharge(carrCharge.doubleValue());
			quotePackage.setCustomerCharge(quotePackage.getCarrierCharge());
			quotePackage.setCompanyId(companyId);
			packageSeq++;
//			int totalPackageItem = 0;
			List<QuotePackageItemDTO> packageItemList = new ArrayList<QuotePackageItemDTO>();
			// 对 package item进行保存操作.
			for (int m = 0; m < pkgQuoteItemList.size(); m++) {
				QuoteItemDTO quoteItem = pkgQuoteItemList.get(m);// 获得原先传过来的某个OrderItem
				if (QuoteItemType.SERVICE.value().equalsIgnoreCase(quoteItem.getType()) 
	            		&& StringUtils.isNotBlank(quoteItem.getParentId())) {
	            	continue;
	            }
				QuotePackageItemDTO packageItem = new QuotePackageItemDTO();
				packageItem.setQuoteNo(quoteNo);
				packageItem.setItemNo(quoteItem.getItemNo());
				packageItem.setSize(quoteItem.getSize());
				packageItem.setUom(quoteItem.getSizeUom());
				packageItem.setQuantity(quoteItem.getQuantity());
				packageItemList.add(packageItem);
//	            totalPackageItem++;
			}
			quotePackage.setPackageItemList(packageItemList);
			quotePackage.setTotalQty(totalQty);
			packageList.add(quotePackage);
            packageMap.put(tempPackageId, quotePackage);
		}
        SessionUtil.insertRow(SessionConstant.QuotePackage.value(), quoteNo.toString(), packageMap);
		return packageList;
	}
	
	/**
     * 获得ShipToAddress
     * @author Zhang Yong
     * @param quoteItem
     * @return
     */
    private String getPkgShiptoAddress (QuoteItemDTO quoteItem) {
    	String shipToAddr = null;
    	if (quoteItem != null && quoteItem.getShipToAddress() != null) {
        	List<String> tmpList = new ArrayList<String>();
        	QuoteAddress shipToAddress = quoteItem.getShipToAddress();
        	tmpList.add(shipToAddress.getFirstName() + " " + shipToAddress.getLastName());
        	tmpList.add(shipToAddress.getOrgName());
    		String addrStr = "";
    		if (!StringUtils.isEmpty(shipToAddress.getAddrLine1())) {
    			addrStr = shipToAddress.getAddrLine1();
    		}
    		if (!StringUtils.isEmpty(shipToAddress.getAddrLine2())) {
    			addrStr = addrStr + ", " + shipToAddress.getAddrLine2();
    		}
    		if (!StringUtils.isEmpty(shipToAddress.getAddrLine3())) {
    			addrStr = addrStr + ", " + shipToAddress.getAddrLine3();
    		}
    		tmpList.add(addrStr);
    		String cityStr = "";
    		if (!StringUtils.isEmpty(shipToAddress.getCity())) {
    			cityStr = shipToAddress.getCity();
    		}
    		if (!StringUtils.isEmpty(shipToAddress.getState())) {
    			if (cityStr.equalsIgnoreCase("")) {
    				cityStr = shipToAddress.getState() + " "
    						+ shipToAddress.getZipCode();
    			} else {
    				cityStr = cityStr + ", " + shipToAddress.getState() + " "
    						+ shipToAddress.getZipCode();
    			}
    		}
    		if (!StringUtils.isEmpty(shipToAddress.getCountry())) {
    			if (cityStr.equalsIgnoreCase("")) {
    				cityStr = shipToAddress.getCountry();
    			} else {
    				cityStr = cityStr + ", " + shipToAddress.getCountry();
    			}
    		}
    		tmpList.add(cityStr);
    		shipToAddr = StringUtils.join(tmpList.toArray(), "\n");
        }
    	return shipToAddr;
    }
	
	/**
	 * 获得生成的某个package的totalWeight.
	 * 
	 * @param packageItemsList
	 *            该Package包含的OrderItem list.
	 * @return
	 */
	@Transactional(readOnly = true)
	private Double getPackageLowestTemperature(
			List<QuoteItemDTO> packageItemsList) {
		Double lowestT = null;
		for (int i = 0; i < packageItemsList.size(); i++) {
			QuoteItemDTO tmpDTO = packageItemsList.get(i);
			if ("PRODUCT".equalsIgnoreCase(tmpDTO.getType())) {
				Integer productId = this.productDao
						.getProductIdByCatalogNo(tmpDTO.getCatalogNo());
				if (productId != null) {
					ShipCondition shipCon = this.shipConditionDao
							.get(productId);
					if (shipCon != null && shipCon.getTemperature() != null) {
						if (lowestT == null
								|| shipCon.getTemperature().doubleValue() < lowestT) {
							lowestT = shipCon.getTemperature().doubleValue();
						}
					}
				}
			} else {
				Integer serviceId = this.serviceDao
						.getServiceIdByCatalogNo(tmpDTO.getCatalogNo());
				if (serviceId != null) {
					ServiceShipCondition shipCon = this.serviceShipConditionDao
							.get(serviceId);
					if (shipCon != null && shipCon.getTemperature() != null) {
						if (lowestT == null
								|| shipCon.getTemperature().doubleValue() < lowestT) {
							lowestT = shipCon.getTemperature().doubleValue();
						}
					}
				}
			}
		}
		return lowestT;
	}
	
	/**
	 * 获得生成的某个package的totalWeight.
	 * 
	 * @param packageItemsList
	 *            该Package包含的QuoteItem list.
	 * @return
	 */
	private Double getPackageTotalWeight(List<QuoteItemDTO> packageItemsList) {
		QuoteItemDTO groupFirst = packageItemsList.get(0);
		Double biggestActWeight = 0.0;
		String shipToCountry = groupFirst.getShipToAddress().getCountry();
		final String usaCountry = "US";
		if (shipToCountry.equals(usaCountry)) {// shipTo是美国.
			for (int m = 0; m < packageItemsList.size(); m++) {
				QuoteItemDTO itemTemp = packageItemsList.get(m);// 获得原先传过来的某个QuoteItem在itemDTOList中的序号.
				if (itemTemp.getType().equals(QuoteItemType.PRODUCT.value())) {
					Product product = this.productDao.findUniqueBy("catalogNo",
							itemTemp.getCatalogNo());
					ShipCondition scTemp = this.shipConditionDao
							.getById(product.getProductId());
					if (scTemp.getDomShipWeight().doubleValue() > biggestActWeight.doubleValue()) {
                    	biggestActWeight = scTemp.getDomShipWeight();
                    }
				} else {
					Integer serviceId = this.serviceDao.getServiceByCatalogNo(
							itemTemp.getCatalogNo()).getServiceId();
					ServiceShipCondition scTemp = this.serviceShipConditionDao
							.getShipCondition(serviceId);
					if (scTemp.getDomShipWeight().doubleValue() > biggestActWeight.doubleValue()) {
                    	biggestActWeight = scTemp.getDomShipWeight();
                    }
				}
			}
		} else {
			for (int m = 0; m < packageItemsList.size(); m++) {
				QuoteItemDTO itemTemp = packageItemsList.get(m);// 获得原先传过来的某个QuoteItem在itemDTOList中的序号.
				if (itemTemp.getType().equals(QuoteItemType.PRODUCT.value())) {
					Product product = this.productDao.findUniqueBy("catalogNo",
							itemTemp.getCatalogNo());
					ShipCondition scTemp = this.shipConditionDao
							.getById(product.getProductId());
					if (scTemp.getIntlShipWeight().doubleValue() > biggestActWeight.doubleValue()) {
                    	biggestActWeight = scTemp.getIntlShipWeight();
                    }
				} else {
					Integer serviceId = this.serviceDao.getServiceByCatalogNo(
							itemTemp.getCatalogNo()).getServiceId();
					ServiceShipCondition scTemp = this.serviceShipConditionDao
							.getShipCondition(serviceId);
					if (scTemp.getIntlShipWeight().doubleValue() > biggestActWeight.doubleValue()) {
                    	biggestActWeight = scTemp.getIntlShipWeight();
                    }
				}
			}
		}
		return biggestActWeight;
	}
	
	/**
	 * 通过QuoteNo查询Package
	 * @param quoteNo
	 * @return
	 */
	public List<QuotePackage> getAllPackage(Integer quoteNo) {
		return quotePackageDao.getQuotePackageList(quoteNo);
	}
}
