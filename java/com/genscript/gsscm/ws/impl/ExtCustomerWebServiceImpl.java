package com.genscript.gsscm.ws.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.GetInfoByRegionDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.common.util.PackageHelper;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dto.ItemWeight;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.dto.PackageDTO;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.TaxRate;
import com.genscript.gsscm.order.service.OrderQuoteUtil;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.dao.ShipConditionDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductShipCondition;
import com.genscript.gsscm.product.entity.ShipCondition;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quoteorder.dto.ShippingTotalDTO;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceShipConditionDao;
import com.genscript.gsscm.serv.entity.Service;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;
import com.genscript.gsscm.shipment.dao.ShipZoneDao;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipZone;
import com.genscript.gsscm.systemsetting.service.AccountSalesTaxService;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.ExtCustomerWebService;
import com.genscript.gsscm.ws.request.ExtCustomerRequest;
import com.genscript.gsscm.ws.response.ExtCustomerResponse;

@WebService(serviceName = "ExtCustomerService", portName = "ExtCustomerServicePort", endpointInterface = "com.genscript.gsscm.ws.api.ExtCustomerWebService", targetNamespace = WsConstants.NS)
public class ExtCustomerWebServiceImpl implements ExtCustomerWebService {

	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private PublicService publicService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AccountSalesTaxService accountSalesTaxService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private ShipZoneDao shipZoneDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ShipConditionDao shipConditionDao;
	@Autowired
	private ServiceShipConditionDao serviceShipConditionDao;
	@Autowired
	private OrderQuoteUtil orderQuoteUtil;
	@Autowired
	private ExceptionService exceptionUtil;
	
	@Override
	public ExtCustomerResponse getTerritoryInfo(
			ExtCustomerRequest customerRequest) {
		ExtCustomerResponse customerResponse = new ExtCustomerResponse();
		Integer defTerrId = customerRequest.getDefTerrId();
		Integer orgId = customerRequest.getOrgId();
		String countryCode = customerRequest.getCountryCode();
		String stateCode = customerRequest.getStateCode();
		String zipCode = customerRequest.getZipCode();
		Integer loginUserId = customerRequest.getUserId();
		try {
			List<GetInfoByRegionDTO> territoryInfoList = publicService.getReginInfoList(defTerrId, orgId, countryCode, stateCode, zipCode);
			customerResponse.setHasException(false);
			customerResponse.setTerritoryInfoList(territoryInfoList);
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public ExtCustomerResponse searchSalesByTerritory(
			ExtCustomerRequest extCustomerRequest) {
		ExtCustomerResponse customerResponse = new ExtCustomerResponse();
		Integer loginUserId = extCustomerRequest.getUserId();
		String defSalesRepId = extCustomerRequest.getDefSalesRepId();
		String salesTerrId = extCustomerRequest.getSalesTerrId();
		String defaultSalesGroup = extCustomerRequest.getDefaultSalesGroup();
		try {
			Map<String, DropDownListDTO> salesInfo = publicService.searchSalesByTerritoryWS(salesTerrId, defSalesRepId, defaultSalesGroup);
			customerResponse.setHasException(false);
			customerResponse.setSalesInfo(salesInfo);
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public ExtCustomerResponse searchDisCount(
			ExtCustomerRequest extCustomerRequest) {
		Integer loginUserId = extCustomerRequest.getUserId();
		ExtCustomerResponse customerResponse = new ExtCustomerResponse();
		Integer costomerId = extCustomerRequest.getCustomerId();
		CustomerDTO customerDTO = new CustomerDTO();
		try {
			if(costomerId!=null){
				customerDTO = this.customerService.getCustomerDetail(costomerId);
			}
			customerResponse.setSpecialDiscount(customerDTO.getSpecialDiscount());
			customerResponse.setHasException(false);
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public ExtCustomerResponse searchNationalSalesTaxRate(
			ExtCustomerRequest extCustomerRequest) {
		
		Integer loginUserId = extCustomerRequest.getUserId();
		ExtCustomerResponse customerResponse = new ExtCustomerResponse();
		try {
			TaxRate taxRate = this.accountSalesTaxService.getTaxRate(extCustomerRequest.getCountry(), extCustomerRequest.getState());
			if(taxRate==null||taxRate.getTaxRate()==null){
				customerResponse.setTaxRate(0.0);
			}else{
				customerResponse.setTaxRate(taxRate.getTaxRate());
			}
			customerResponse.setHasException(false);
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public ExtCustomerResponse searchShippingCost(
			ExtCustomerRequest extCustomerRequest) {
		Integer loginUserId = extCustomerRequest.getUserId();
		ExtCustomerResponse customerResponse = new ExtCustomerResponse();
		try {
			ShippingTotalDTO dto = this.orderService.getShippingTotalByPage(extCustomerRequest.getOrderCurrency(), extCustomerRequest.getPackageList(), extCustomerRequest.getExchRate(), extCustomerRequest.getExchRateDate());
			customerResponse.setCost(dto.getCostTotal());
			customerResponse.setHasException(false);
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		
		// TODO Auto-generated method stub
		return customerResponse;
	}

	@Override
	public ExtCustomerResponse searchInitShippingCost(
			ExtCustomerRequest extCustomerRequest) {
		Integer loginUserId = extCustomerRequest.getUserId();
		ExtCustomerResponse customerResponse = new ExtCustomerResponse();
		System.out.println(extCustomerRequest.getBaseCurrency());
		System.out.println(extCustomerRequest.getExchRate());
		System.out.println(extCustomerRequest.getCountry());
		String country= "";
		System.out.println("           aaaaaaaaaaaaa");
		if(extCustomerRequest.getExchRateDate()==null){
			extCustomerRequest.setExchRateDate(new Date());
		}
		try {
			if(extCustomerRequest.getItemDTOList()!=null&&!extCustomerRequest.getItemDTOList().isEmpty()){
				for(OrderItemDTO dto : extCustomerRequest.getItemDTOList()){
					OrderAddress orderAddress = null;
					System.out.println();
					if(dto.getShiptoAddrId()!=null&&orderAddress==null){
						orderAddress = this.orderAddressDao.getById(dto.getShiptoAddrId());
						dto.setShipToAddress(orderAddress);
						country = orderAddress.getCountry();
					}
					if(dto.getShipSchedule()==null){
						if(dto.getType().equals("PRODUCT")){
							Product product = this.productDao.findUniqueBy("catalogNo", dto.getCatalogNo());
							dto.setShipSchedule(product.getLeadTime());
						}else if(dto.getType().equals("SERVICE")){
							com.genscript.gsscm.serv.entity.Service service = this.serviceDao.findUniqueBy("catalogNo", dto.getCatalogNo());
							dto.setShipSchedule(service.getLeadTime());
						}
					}
				}
				
			}
			this.orderService.checkAutoCalPackage( extCustomerRequest.getItemDTOList(), extCustomerRequest.getWarehouseId(), extCustomerRequest.getCompanyId());
			
			// 对OrderItem list进行分组.
			Map<String, List<OrderItemDTO>> packageMap = new HashMap<String, List<OrderItemDTO>>();
			int size = extCustomerRequest.getItemDTOList().size();
			for (int i = 0; i < size; i++) {
				OrderItemDTO itemDTO = extCustomerRequest.getItemDTOList().get(i);
				String key = itemDTO.getType()+itemDTO.getShipSchedule();
				if (packageMap.containsKey(key)) {// 更改已存在Package中的OrderItem list.
					List<OrderItemDTO> packageItemList = packageMap.get(key);
					packageItemList.add(itemDTO);
					packageMap.put(key, packageItemList);
				} else {// 新增一个Package.
					List<OrderItemDTO> packageItemList = new ArrayList<OrderItemDTO>();
					packageItemList.add(itemDTO);
					packageMap.put(key, packageItemList);
				}
			}
			// 先产生内存中的各单个Package及PackageItem list.
			List<PackageHelper> packageTempList = new ArrayList<PackageHelper>();
			Iterator<Map.Entry<String, List<OrderItemDTO>>> it = packageMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, List<OrderItemDTO>> entry = it.next();
				List<OrderItemDTO> packageItemList = entry.getValue();
				PackageHelper packageHelper = this.orderService.genPackagePublic(packageItemList,
						 extCustomerRequest.getWarehouseId());
				packageTempList.add(packageHelper);
			}
			// 产生内存中已排序的多个Package list.
			System.out.println("countty="+country);
			ShipPackage shipPackage = this.orderService.genPackageListPublic(
					packageTempList, extCustomerRequest.getCompanyId(), extCustomerRequest.getWarehouseId(),extCustomerRequest.getCountry(),extCustomerRequest.getCurrency(),extCustomerRequest.getExchRate(), extCustomerRequest.getExchRateDate(),extCustomerRequest.getOrderAmount(),extCustomerRequest.getBaseCurrency());
			Double cost = shipPackage.getCustomerCharge().doubleValue();
			customerResponse.setCost(cost);
			customerResponse.setHasException(false);
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		
		// TODO Auto-generated method stub
		return customerResponse;
	}

	@Override
	public ExtCustomerResponse searchInitShippingTotal(
			ExtCustomerRequest extCustomerRequest) {
		
		Integer loginUserId = extCustomerRequest.getUserId();
		ExtCustomerResponse customerResponse = new ExtCustomerResponse();
		try {
			List<QuotePackageDTO> packageList = new ArrayList<QuotePackageDTO>();
			packageList = quoteService.getBaseQuoteShipPackageList(extCustomerRequest.getQuoteNo());
			ShippingTotalDTO dto = quoteService.getShippingTotalByPage(extCustomerRequest.getQuoteCurrency(), packageList, extCustomerRequest.getExchRate(), extCustomerRequest.getExchRateDate());
			customerResponse.setCost(dto.getCostTotal());
			customerResponse.setHasException(false);
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return null;
	}

	@Override
	public ExtCustomerResponse searchInitOrderShippingCost(
			ExtCustomerRequest extCustomerRequest) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtCustomerResponse searchInitQuoteShippingCost(
			ExtCustomerRequest extCustomerRequest) {
		Integer loginUserId = extCustomerRequest.getUserId();
		ExtCustomerResponse customerResponse = new ExtCustomerResponse();
		BigDecimal costTotal = BigDecimal.valueOf(0.0);
	/*	if(extCustomerRequest.getPackageDTOList()!=null&&!extCustomerRequest.getPackageDTOList().isEmpty()){
			int packageSeq = 1;
			BigDecimal charge = BigDecimal.valueOf(0.0);
			BigDecimal charges = BigDecimal.valueOf(0.0);
			List<Integer> clsIds = new ArrayList<Integer>();
			List<String> itemTypes = new ArrayList<String>();
			List<Integer> totalQtys = new ArrayList<Integer>();
			List<Double> weights = new ArrayList<Double>();
			for(PackageDTO dto : extCustomerRequest.getPackageDTOList()){
				Double totalActWeight = 0.0;
				String shipToCountry = dto.getCountry();
				for(ItemWeight item : dto.getItem()){
					Double weight = 0.0;
					Integer clsId = null;
					String type = null;
					if (item.getType().equals("PRODUCT")) {
						Product product = this.productDao.findUniqueBy("catalogNo",
								item.getCatalogNo());
						clsId = product.getProductClsId();
						ShipCondition scTemp = this.shipConditionDao
								.getById(product.getProductId());
						if("US".equals(shipToCountry)){
							weight = scTemp.getDomShipWeight();
						}else{
							weight = scTemp.getIntlShipWeight();
						}
					} else if (item.getType().equals("SERVICE")) {
						com.genscript.gsscm.serv.entity.Service service = this.serviceDao.getServiceByCatalogNo(
								item.getCatalogNo());
						clsId = service.getServiceClsId();
						ServiceShipCondition scTemp = this.serviceShipConditionDao
								.getShipCondition(service.getServiceId());
						if("US".equals(shipToCountry)){
							weight = scTemp.getDomShipWeight();
						}else{
							weight = scTemp.getIntlShipWeight();
						}
					}
					if(clsIds.isEmpty()){
						clsIds.add(clsId);
						itemTypes.add(item.getType());
						totalQtys.add(item.getQuantity());
						weights.add(weight);
					}else{
						int is = 0;
						for(Integer f = 0 ; f<clsIds.size();f++){
							if(clsIds.get(f).equals(clsId)&&itemTypes.get(f).equals(item.getType())){
								totalQtys.set(f, totalQtys.get(f)+item.getQuantity());
								if(weights.get(f)<weight){
									weights.set(f, weight);
								}
								is = 1;
							}
						}
						if(is==0){
							clsIds.add(clsId);
							itemTypes.add(item.getType());
							totalQtys.add(item.getQuantity());
							weights.add(weight);
						}
					}
				}
				
				
				
				final String usaCountry = "US";
				if (shipToCountry.equals(usaCountry)) {// shipTo是美国.
					for (int m = 0; m < dto.getItem().size(); m++) {
						ItemWeight itemTemp = dto.getItem().get(m);// 获得原先传过来的某个OrderItem在itemDTOList中的序号.
						if (itemTemp.getType().equals(QuoteItemType.PRODUCT.value())) {
							Product product = this.productDao.findUniqueBy("catalogNo",
									itemTemp.getCatalogNo());
							ShipCondition scTemp = this.shipConditionDao
									.getById(product.getProductId());
							totalActWeight += scTemp.getDomShipWeight()
									* itemTemp.getQuantity();
						} else {
							Integer serviceId = this.serviceDao.getServiceByCatalogNo(
									itemTemp.getCatalogNo()).getServiceId();
							ServiceShipCondition scTemp = this.serviceShipConditionDao
									.getShipCondition(serviceId);
							totalActWeight += scTemp.getDomShipWeight()
									* itemTemp.getQuantity();
						}
					}
				} else {
					for (int m = 0; m < dto.getItem().size(); m++) {
						ItemWeight itemTemp = dto.getItem().get(m);// 获得原先传过来的某个OrderItem在itemDTOList中的序号.
						if (itemTemp.getType().equals(QuoteItemType.PRODUCT.value())) {
							Product product = this.productDao.findUniqueBy("catalogNo",
									itemTemp.getCatalogNo());
							ShipCondition scTemp = this.shipConditionDao
									.getById(product.getProductId());
							totalActWeight += scTemp.getIntlShipWeight()
									* itemTemp.getQuantity();
						} else {
							Integer serviceId = this.serviceDao.getServiceByCatalogNo(
									itemTemp.getCatalogNo()).getServiceId();
							ServiceShipCondition scTemp = this.serviceShipConditionDao
									.getShipCondition(serviceId);
							totalActWeight += scTemp.getIntlShipWeight()
									* itemTemp.getQuantity();
						}
					}
				}
				
				String zipCode = dto.getZipCode();
				ShipZone shipZone = this.shipZoneDao.getShipZone(dto.getShipMethod(), dto.getWarehouseId(), zipCode);
				String zone = shipZone.getZoneCode();
				System.out.println(zone+"=zone");
				for(Integer f = 0 ; f<clsIds.size();f++){
					charges.add(this.orderQuoteUtil.getShippingFee(dto.getShipMethod(), dto.getWarehouseId(), weights.get(f), zone
							, clsIds.get(f), itemTypes.get(f), totalQtys.get(f).intValue(), dto.getOrderAmount(), packageSeq));
				}
				//BigDecimal charge = this.orderQuoteUtil.getShippingFee(dto.getShipMethod(), dto.getWarehouseId(), totalActWeight, zone, dto.getClsId(), dto.getItemType(), dto.getTotalQty(), dto.getOrderAmount(), packageSeq);
				Double deliveryConfirmFee= 0.0;
				Double packagingFee=0.0;
				Double actlCarrCharge = BigDecimal.valueOf(charges.doubleValue()).add(
						BigDecimal.valueOf(deliveryConfirmFee))
						.add(BigDecimal.valueOf(packagingFee))
						.doubleValue();
				System.out.println("actlCarrCharge=" +actlCarrCharge);
				if (actlCarrCharge == null) {
					costTotal = costTotal.add(BigDecimal.valueOf(0));
				} else {
					costTotal = costTotal.add(BigDecimal.valueOf(actlCarrCharge));
				}
				System.out.println("costTotal=" +costTotal);
				
			}
			customerResponse.setCost(costTotal.doubleValue());
			customerResponse.setHasException(false);
		}*/
		// TODO Auto-generated method stub
		return customerResponse;
	}

}
