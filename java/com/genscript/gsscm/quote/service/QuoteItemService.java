package com.genscript.gsscm.quote.service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PriceService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.ItemSearchType;
import com.genscript.gsscm.common.constant.OrderQuoteStautsType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.ServicePriceType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.ExcelUtil;
import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.common.util.MoreDetailUtil;
import com.genscript.gsscm.common.util.QuoteSessionUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.customer.dao.CustomerSpecialPriceDao;
import com.genscript.gsscm.customer.dao.SalesProjectManagerAssignmentDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.dao.StatusListDao;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.order.service.OrderQuoteUtil;
import com.genscript.gsscm.quote.dao.QuoteAntibodyDao;
import com.genscript.gsscm.quote.dao.QuoteCustCloningDao;
import com.genscript.gsscm.quote.dao.QuoteDao;
import com.genscript.gsscm.quote.dao.QuoteDsPlatesDao;
import com.genscript.gsscm.quote.dao.QuoteGeneSynthesisDao;
import com.genscript.gsscm.quote.dao.QuoteItemDao;
import com.genscript.gsscm.quote.dao.QuoteMutagenesisDao;
import com.genscript.gsscm.quote.dao.QuoteOligoDao;
import com.genscript.gsscm.quote.dao.QuoteOrfCloneDao;
import com.genscript.gsscm.quote.dao.QuotePeptideDao;
import com.genscript.gsscm.quote.dao.QuotePkgServiceDao;
import com.genscript.gsscm.quote.dao.QuotePlasmidPreparationDao;
import com.genscript.gsscm.quote.dao.QuoteRnaDao;
import com.genscript.gsscm.quote.dao.ServiceQuoteTemplateBeanDao;
import com.genscript.gsscm.quote.dto.QuoteDsPlatesDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.entity.QuoteAddress;
import com.genscript.gsscm.quote.entity.QuoteAntibody;
import com.genscript.gsscm.quote.entity.QuoteCustCloning;
import com.genscript.gsscm.quote.entity.QuoteDsPlates;
import com.genscript.gsscm.quote.entity.QuoteGeneSynthesis;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.quote.entity.QuoteMutagenesis;
import com.genscript.gsscm.quote.entity.QuoteOligo;
import com.genscript.gsscm.quote.entity.QuoteOrfClone;
import com.genscript.gsscm.quote.entity.QuotePeptide;
import com.genscript.gsscm.quote.entity.QuotePkgService;
import com.genscript.gsscm.quote.entity.QuotePlasmidPreparation;
import com.genscript.gsscm.quote.entity.QuoteRna;
import com.genscript.gsscm.quote.entity.ServiceQuoteTemplateBean;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.DnaSequencingDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOligoDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOrfCloneDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.OrderServiceDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.PkgServiceDTO;
import com.genscript.gsscm.quoteorder.dto.RnaDTO;
import com.genscript.gsscm.quoteorder.dto.TargetDateDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.quoteorder.service.QuoteOrderUtil;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;
import com.genscript.gsscm.serv.entity.ServiceClass;

/**
 * 
 * @author zhangyong
 *
 */
@Service
@Transactional
public class QuoteItemService {
	@Autowired
	private QuoteItemDao quoteItemDao;
	@Autowired
	private SalesProjectManagerAssignmentDao salesProjectManagerAssignmentDao;
	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;
	@Autowired
	private QuoteDao quoteDao;
	@Autowired
	private ServiceQuoteTemplateBeanDao serviceQuoteTemplateBeanDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private SpecDropDownListDao specDropDownListDao;
    @Autowired
    private ErpSalesOrderService erpSalesOrderService;
	@Autowired
	private StatusListDao statusListDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private QuoteGeneSynthesisDao quoteGeneSynthesisDao;
	@Autowired
	private QuoteCustCloningDao quoteCustCloningDao;
	@Autowired
	private QuoteMutagenesisDao quoteMutagenesisDao;
	@Autowired
	private QuotePlasmidPreparationDao quotePlasmidPreparationDao;
	@Autowired
	private QuotePkgServiceDao quotePkgServiceDao;
	@Autowired
	private QuoteAntibodyDao quoteAntibodyDao;
	@Autowired
	private QuoteRnaDao quoteRnaDao;
	@Autowired
	private QuoteOrfCloneDao quoteOrfCloneDao;
	@Autowired
	private QuoteOligoDao quoteOligoDao;
	@Autowired
	private QuotePeptideDao quotePeptideDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
    private OrderQuoteUtil orderQuoteUtil; 
	@Autowired
	private QuoteDsPlatesDao quoteDsPlatesDao;
	@Autowired
	private ExcelUtil excelUtil;
	@Autowired
	private PublicService publicService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private QuoteOrderUtil quoteOrderUtil;
	@Autowired
	private PriceService priceService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	private final static String tbd_0 = "0";
	private final static String Y = "Y";
	private final static String N = "N";
	
	
	/**
	 * 查询QuoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
	 * @param quoteItemDTO
	 * @return
	 */
	public boolean checkPriceChage (QuoteItemDTO quoteItemDTO) {
		boolean status = false;
		try {
			if (quoteItemDTO != null && quoteItemDTO.getQuoteItemId() != null) {
				QuoteItem quoteItem = this.quoteItemDao.searchQuoteItemByItemId(quoteItemDTO.getQuoteItemId());
				if ((QuoteItemStatusType.Y.value()).equals(quoteItem.getPriceChanged())) {
					status = true;
				}
			}
		} catch (Exception ex) {
		}
		return status;
	}
	
	/**
	 * 由service 类型的item的clsId获得所有关联的SalesRep
	 */
	public List<SalesRep> getSalesRepByClsId(Integer clsId) {
		return this.salesProjectManagerAssignmentDao.findSalesRepByServiceType(clsId);
	}
	
	/**
	 * 获得所有的SalesRep
	 */
	public List<SalesRep> getAllSalesRep() {
		return this.salesProjectManagerAssignmentDao.findSalesRep();
	}
	
	/**
	 * 将有优惠的QuoteItem直接显示优惠的UnitPrice
	 * @param quote
	 * @param quoteItem
	 * @return
	 */
	public QuoteItemDTO getOfferQuoteItem (QuoteMainDTO quote, QuoteItemDTO quoteItem) {
		if (StringUtils.isEmpty(quoteItem.getCatalogNo()) || quote.getCustNo() == null) {
			return quoteItem;
		}
		CustomerSpecialPrice  customerSpecialPrice  = customerSpecialPriceDao.getSpecialPrice(quote.getCustNo(), quoteItem.getCatalogNo());
		if (customerSpecialPrice == null) {
			return quoteItem;
		}
		quoteItem.setUnitPrice(customerSpecialPrice.getStandardPrice());
		quoteItem.setAmount(customerSpecialPrice.getStandardPrice().multiply(new BigDecimal(quoteItem.getQuantity())));
		if ((customerSpecialPrice.getMinQty() != null && customerSpecialPrice.getMinQty()>quoteItem.getQuantity())) {
			return quoteItem;
		}
		if (customerSpecialPrice.getOrderTotal() != null && customerSpecialPrice.getOrderTotal()
				.doubleValue() > quoteItem.getAmount().doubleValue()) {
			return quoteItem;
		}
		if (customerSpecialPrice.getEffFrom() != null && customerSpecialPrice.getEffFrom().getTime() > new Date().getTime()) {
			return quoteItem;
		}
		if (customerSpecialPrice.getEffTo() != null && customerSpecialPrice.getEffTo().getTime() < new Date().getTime()) {
			return quoteItem;
		}
		quoteItem.setUnitPrice(customerSpecialPrice.getUnitPrice());
		BigDecimal amount = customerSpecialPrice.getUnitPrice().multiply(
				new BigDecimal(quoteItem.getQuantity()));
		quoteItem.setAmount(amount);
		return quoteItem;
	}
	
	//------------------保存Template Item add by Zhang Yong start---------------------------//	
	public Map<String, QuoteItemDTO> saveTempItemsItem (Map<String, QuoteItemDTO> itemMap, 
			QuoteMainDTO quote, QuoteItemDTO quoteItem, String srcQuOrderNo,
			CustomerDTO customer, Integer defalutShipMethod, QuoteAddress defaultAddress,
			String sessQuoteNo) throws Exception {
		if (!QuoteItemType.SERVICE.value().equals(quoteItem.getType())) {
			return itemMap;
		}
		String srcQuOrderItemNo = quoteItem.getSrcItemNo();
		if (srcQuOrderItemNo == null || StringUtils.isEmpty(srcQuOrderItemNo.trim()) 
				|| srcQuOrderNo == null || StringUtils.isEmpty(srcQuOrderNo.trim())) {
			return itemMap;
		}
		if (ItemSearchType.SERVICE_TEMPLATE_ITEM.value().equals(quoteItem.getTemplateType())) {
			String srcItemNo = quoteItem.getSrcItemNo();
			QuoteItem srcQuoteItem = quoteItemDao.getQuoteItem(Integer.parseInt(srcQuOrderNo), 
					Integer.parseInt(srcItemNo));
			if (srcQuoteItem == null || srcQuoteItem.getParentId() != null) {
				return itemMap;
			}
			QuoteMain srcQuote = quoteDao.getById(srcQuoteItem.getQuoteNo());
			List<QuoteItem> srcItemList = quoteItemDao.getQuoteAllItemList(srcQuoteItem.getQuoteNo());
			List<QuoteItem> subItemList = new ArrayList<QuoteItem>();
			subItemList.add(srcQuoteItem);
			subItemList = getSubItemList(srcQuoteItem, srcItemList, subItemList);
			List<ServiceQuoteTemplateBean> sotList = serviceQuoteTemplateBeanDao
				.searchServiceQuoteTemplateItem(srcQuote.getCustNo(), srcQuoteItem.getQuoteNo());
			for (QuoteItem item : subItemList) {
				itemMap = saveServiceSessionItem(item, sotList, itemMap, quote, customer, 
						defalutShipMethod, defaultAddress, sessQuoteNo);
			}
		}
		return itemMap;
	}
	
	private List<QuoteItem> getSubItemList (QuoteItem parentItem, List<QuoteItem> srcSubItemList, 
			List<QuoteItem> subItemList) {
		if (srcSubItemList == null) {
			return subItemList;
		}
		for (QuoteItem srcSubItem : srcSubItemList) {
			if (parentItem.getQuoteItemId().equals(srcSubItem.getParentId())) {
				subItemList.add(srcSubItem);
				getSubItemList(srcSubItem, srcSubItemList, subItemList);
			}
		}
		return subItemList;
	}
	
	private Map<String, QuoteItemDTO> saveServiceSessionItem (QuoteItem item, List<ServiceQuoteTemplateBean> sotList, 
			Map<String, QuoteItemDTO> itemMap, QuoteMainDTO quote, CustomerDTO customer,
			Integer defalutShipMethod, QuoteAddress defaultAddress, String sessQuoteNo)
	 		throws Exception {
		for (ServiceQuoteTemplateBean sot : sotList) {
			if (item.getItemNo().equals(sot.getItemNo())) {
				String itemId = SessionUtil.generateTempId();
				QuoteItemDTO quoteItem = new QuoteItemDTO ();
				quoteItem.setCatalogNo(sot.getCatalogNo());
				quoteItem.setName(sot.getServiceName());
				quoteItem.setQuantity(1);
				quoteItem.setSize(sot.getSize() == null?0d:sot.getSize().doubleValue());
				quoteItem.setUnitPrice(new BigDecimal(0.0));
				quoteItem.setUpSymbol(sot.getUpSymbol());
				quoteItem.setSizeUom(sot.getUom());
				quoteItem.setQtyUom(sot.getQtyUom());
				quoteItem.setDiscount(new BigDecimal(0.0));
				quoteItem.setTax(new BigDecimal(0.0));
				quoteItem.setItemNo(itemMap.size()+1);
				quoteItem.setAmount(new BigDecimal(0.0));
				quoteItem.setType(QuoteItemType.SERVICE.value());
				quoteItem.setCatalogId(sot.getCatalogId());
				quoteItem.setCatalogText(sot.getCatalogId()+" : ");
				if (sot.getParentItemId() != null) {
					String parentItemId = (String)SessionUtil.getRow(SessionConstant.TemplateQuoteItem.value(), 
							sot.getParentItemId().toString());
					if (parentItemId != null) {
						quoteItem.setParentId(parentItemId);
					}
				}
				quoteItem.setSrcItemNo(sot.getItemNo()==null?null:sot.getItemNo().toString());
				quoteItem.setTemplateType(ItemSearchType.SERVICE_TEMPLATE_ITEM.value());
//				quoteItem = getOfferQuoteItem(quote, quoteItem);
				com.genscript.gsscm.serv.entity.Service serv = serviceDao.findUniqueBy("catalogNo", sot.getCatalogNo());
				if (serv != null) {
					if (serv.getServiceClsId() != null) {
						Integer clsId = serv.getServiceClsId();
						quoteItem.setClsId(clsId);
						ServiceClass serviceClass = serviceClassDao.get(serv.getServiceClsId());
						if (serviceClass != null) {
							quoteItem.setTypeText(quoteItem.getType() + " - "
									+ serviceClass.getName());
						}
					}
					quoteItem.setShipSchedule(serv.getLeadTime());
					quoteItem.setShortDesc(serv.getShortDesc());
					quoteItem.setLongDesc(serv.getLongDesc());
					quoteItem.setSellingNote(serv.getSellingNote());
				}
				quoteItem.setCost(new BigDecimal(0.0));
				quoteItem.setBasePrice(quoteItem.getUnitPrice().setScale(2,
						BigDecimal.ROUND_HALF_UP));
				if (Y.equalsIgnoreCase(customer.getTaxExemptFlag())
						&& !StringUtils.isEmpty(customer.getTaxId())) {
					quoteItem.setTaxStatus(N);
				} else {
					if (serv != null) {
						quoteItem.setTaxStatus(serv.getTaxable());
					}
				}
				if (quote.getWarehouseId() != null) {
					List<DropDownDTO> pickLocationList = specDropDownListDao.getPickLocationList(quote.getWarehouseId());
					if (pickLocationList != null && !pickLocationList.isEmpty()) {
						String idStr = pickLocationList.get(0).getId();
						quoteItem.setStorageId(Integer.parseInt(idStr));
					}
				}
				String otherInfo = getItemOtherInfo(quoteItem
						.getCatalogNo(), quoteItem.getStorageId(), QuoteItemType
						.valueOf(quoteItem.getType()), QuoteItemStatusType
						.valueOf("CM"), quoteItem.getStatusReason(), null);
				quoteItem.setOtherInfo(otherInfo);
				quoteItem.setStatus("CM");
				quoteItem.setStatusText("Committed");
				this.setServiceForItem(quoteItem, MoreDetailUtil
						.getServiceNameByClsId(quoteItem.getClsId()), sot, itemId);
				quoteItem.setShipMethod(defalutShipMethod);
				quoteItem.setShipToAddress(defaultAddress);
				quoteItem.setNameShow(quoteItem.getName());
				if (!StringUtils.isEmpty(quote.getQuoteCurrency())) {
					quoteItem.setCurrencyCode(quote.getQuoteCurrency());
					quoteItem = dozer.map(quoteItem, QuoteItemDTO.class);
					Date exchRateDate = quote.getExchRateDate();
					if (exchRateDate == null) {
						exchRateDate = new Date();
					}
					Double rate = exchRateByDateDao.getCurrencyRate(quote.getQuoteCurrency(), CurrencyType.USD.value(), exchRateDate);
					if (rate != null && quoteItem.getUnitPrice() != null) {
						int small = CurrencyType.JPY.value().equalsIgnoreCase(quote.getQuoteCurrency())?0:2;
						quoteItem.setBasePrice(quoteItem.getUnitPrice().multiply(new BigDecimal(rate)).setScale(small, BigDecimal.ROUND_HALF_UP));
					}
				}
				itemMap = this.insertNewChild(itemMap, quoteItem.getParentId(), itemId,
						quoteItem, sessQuoteNo);
				break;
			}
		}
		return itemMap;
	}
	
	/**
	 * 获得某个Product在某个Storage中的Other info.
	 */
	@Transactional(readOnly = true)
	public String getItemOtherInfo(String catalogNo, Integer storageId, QuoteItemType itemType, 
			QuoteItemStatusType status, String reason, Integer count) {
		String ret = "";
		if (itemType.equals(QuoteItemType.PRODUCT)) {
			if (status.equals(QuoteItemStatusType.CN)) {
				ret = reason;
			} else {
				try {
					count = this .getItemStockQty(storageId, null, catalogNo);
				} catch (Exception ex) {
					count = 0;
				}
				if (count == null) {
				} else if (count.intValue() >0) {
					ret = "In Stock,"+ count + " Available";// 库存信息.
				} else {
					ret = "Not in stock";
				}
			}
		}
		return ret;
	}
    
    /**
     * Update list
     * 1. 2011-01-10 wangsf
     * 计算产品或服务的库存改为从StockDetail中取
     *
     */
    /**
     * 获得某个产品在storage中的库存信息.
     *
     * @param storageId
     * @param warehouseId
     * @param catalogNo
     * @return
     */
    @Transactional(readOnly = true)
    public Integer getItemStockQty(Integer storageId, Integer warehouseId,
                                   String catalogNo) {
    	BigDecimal unitInStock = erpSalesOrderService.getPartStorageNumber(catalogNo);
        return unitInStock != null?unitInStock.intValue():null;
    }
    
    /**
     * 根据OrderItem Status的缩写获得该OrderItem status的全称.
     *
     * @param strStatus
     * @return
     */
    @Transactional(readOnly = true)
    public String getOrderItemStatusText(String strStatus) {
        String text = null;
        if (strStatus == null || strStatus.trim().length() < 1) {
            return null;
        }
        List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
        PropertyFilter filterType = new PropertyFilter("EQS_type",
                OrderQuoteStautsType.ORDERITEM.value());
        filterList.add(filterType);
        PropertyFilter filterCode = new PropertyFilter("EQS_code", strStatus);
        filterList.add(filterCode);
        List<StatusList> statusList = this.statusListDao.find(filterList);
        if (statusList != null && !statusList.isEmpty()) {
            text = statusList.get(0).getName();
        }
        return text;
    }
	
    /**
	 * 初始化OrderItem的Service
	 * 
	 * @param orderItem
	 * @param serviceName
	 */
	private void setServiceForItem(QuoteItemDTO quoteItem, String serviceName, 
			ServiceQuoteTemplateBean sot, String itemId) {
		Integer srcQuoteNo = sot.getSrcQuoteNo();
		Integer itemNo = sot.getItemNo();
		QuoteItem srcQuoteItem = null;
		if (srcQuoteNo != null && itemNo != null) {
			srcQuoteItem = quoteItemDao.getQuoteItem(srcQuoteNo, itemNo);
			SessionUtil.insertRow(SessionConstant.TemplateQuoteItem.value(), 
					srcQuoteItem.getQuoteItemId().toString(), itemId);
		}
		if (serviceName.equalsIgnoreCase("geneSynthesis")) {
			quoteItem.setGeneSynthesis(new OrderGeneSynthesisDTO());
			if (srcQuoteItem != null) {
				QuoteGeneSynthesis gene = quoteGeneSynthesisDao.getById(srcQuoteItem.getQuoteItemId());
				if (gene != null) {
					OrderGeneSynthesisDTO geneDTO = dozer.map(gene, OrderGeneSynthesisDTO.class);
					geneDTO.setQuoteNo(quoteItem.getQuoteNo());
					geneDTO.setQuoteItemId(null);
					DocumentType[] docTypeList = {DocumentType.QIM_GENE};
		            List<Document> docList = orderQuoteUtil.getItemDocList(srcQuoteItem.getQuoteItemId(), docTypeList);
		            geneDTO.setDocumentList(docList);
					quoteItem.setGeneSynthesis(geneDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("mutagenesis")) {
			quoteItem.setMutagenesis(new OrderMutagenesisDTO());
			if (srcQuoteItem != null) {
				QuoteMutagenesis muta = quoteMutagenesisDao.getById(srcQuoteItem.getQuoteItemId());
				if (muta != null) {
					OrderMutagenesisDTO mutaDTO = dozer.map(muta, OrderMutagenesisDTO.class);
					mutaDTO.setQuoteNo(quoteItem.getQuoteNo());
					mutaDTO.setQuoteItemId(null);
					DocumentType[] docTypeList = {DocumentType.QIM_MUTA, DocumentType.QIM_MUTA_SELF};
		            List<Document> docList = orderQuoteUtil.getItemDocList(srcQuoteItem.getQuoteItemId(), docTypeList);
		            mutaDTO.setDocumentList(docList);
		            quoteItem.setMutagenesis(mutaDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("custCloning")) {
			quoteItem.setCustCloning(new OrderCustCloningDTO());
			if (srcQuoteItem != null) {
				QuoteCustCloning clone = quoteCustCloningDao.getById(srcQuoteItem.getQuoteItemId());
				if (clone != null) {
					OrderCustCloningDTO cloneDTO = dozer.map(clone, OrderCustCloningDTO.class);
					cloneDTO.setQuoteNo(quoteItem.getQuoteNo());
					cloneDTO.setQuoteItemId(null);
					DocumentType[] docTypeList = {DocumentType.QIM_CUSTCLONING,
	                        DocumentType.QIM_CUSTCLONING_CS,
	                        DocumentType.QIM_CUSTCLONING_SELF};
	                List<Document> docList = orderQuoteUtil.getItemDocList(srcQuoteItem.getQuoteItemId(), docTypeList);
	                cloneDTO.setDocumentList(docList);
					quoteItem.setCustCloning(cloneDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("plasmidPreparation")) {
			quoteItem.setPlasmidPreparation(new OrderPlasmidPreparationDTO());
			if (srcQuoteItem != null) {
				QuotePlasmidPreparation plass = quotePlasmidPreparationDao.getById(srcQuoteItem.getQuoteItemId());
				if (plass != null) {
					OrderPlasmidPreparationDTO plassDTO = dozer.map(plass, OrderPlasmidPreparationDTO.class);
					plassDTO.setQuoteNo(quoteItem.getQuoteNo());
					plassDTO.setQuoteItemId(null);
					DocumentType[] docTypeList = {DocumentType.QIM_PLASMID,
	                        DocumentType.QIM_PLASMID_SELF};
	                List<Document> docList = orderQuoteUtil.getItemDocList(srcQuoteItem.getQuoteItemId(), docTypeList);
	                plassDTO.setDocumentList(docList);
	                quoteItem.setPlasmidPreparation(plassDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("pkgService")) {
			quoteItem.setQuotePkgService(new PkgServiceDTO());
			if (srcQuoteItem != null) {
				QuotePkgService pkg = quotePkgServiceDao.getById(srcQuoteItem.getQuoteItemId());
				if (pkg != null) {
					PkgServiceDTO pkgDTO = dozer.map(pkg, PkgServiceDTO.class);
					pkgDTO.setQuoteNo(quoteItem.getQuoteNo());
					pkgDTO.setQuoteItemId(null);
					quoteItem.setQuotePkgService(pkgDTO);
				}
			}
		} else if (serviceName.equals("antibody")) {
			quoteItem.setAntibody(new AntibodyDTO());
			if (srcQuoteItem != null) {
				QuoteAntibody anti = quoteAntibodyDao.getById(srcQuoteItem.getQuoteItemId());
				if (anti != null) {
					AntibodyDTO antiDTO = dozer.map(anti, AntibodyDTO.class);
					antiDTO.setQuoteNo(quoteItem.getQuoteNo());
					antiDTO.setQuoteItemId(null);
					quoteItem.setAntibody(antiDTO);
				}
			}
		} else if (serviceName.equals("rna")) {
			quoteItem.setRna(new RnaDTO());
			if (srcQuoteItem != null) {
				QuoteRna rna = quoteRnaDao.getById(srcQuoteItem.getQuoteItemId());
				if (rna != null) {
					RnaDTO rnaDTO = dozer.map(rna, RnaDTO.class);
					rnaDTO.setQuoteNo(quoteItem.getQuoteNo());
					rnaDTO.setQuoteItemId(null);
					DocumentType[] docTypeList = {DocumentType.QIM_RNA};
		            List<Document> docList = orderQuoteUtil.getItemDocList(srcQuoteItem.getQuoteItemId(), docTypeList);
		            rnaDTO.setDocumentList(docList);
					quoteItem.setRna(rnaDTO);
				}
			}
		} else if (serviceName.equals("orfClone")) {
			quoteItem.setOrfClone(new OrderOrfCloneDTO());
			if (srcQuoteItem != null) {
				QuoteOrfClone orfClone = quoteOrfCloneDao.getById(srcQuoteItem.getQuoteItemId());
				if (orfClone != null) {
					OrderOrfCloneDTO orfCloneDTO = dozer.map(orfClone, OrderOrfCloneDTO.class);
					orfCloneDTO.setQuoteNo(quoteItem.getQuoteNo());
					orfCloneDTO.setQuoteItemId(null);
					DocumentType[] docTypeList = {DocumentType.QIM_ORFCLONE};
	                List<Document> docList = orderQuoteUtil.getItemDocList(srcQuoteItem.getQuoteItemId(), docTypeList);
	                orfCloneDTO.setDocumentList(docList);
	                quoteItem.setOrfClone(orfCloneDTO);
				}
			}
		} else if (serviceName.equals("oligo")) {
			quoteItem.setOligo(new OrderOligoDTO());
			if (srcQuoteItem != null) {
				QuoteOligo oligo = quoteOligoDao.getById(srcQuoteItem.getQuoteItemId());
				if (oligo != null) {
					OrderOligoDTO oligoDTO = dozer.map(oligo, OrderOligoDTO.class);
					oligoDTO.setQuoteNo(quoteItem.getQuoteNo());
					oligoDTO.setQuoteItemId(null);
					quoteItem.setOligo(oligoDTO);
				}
			}
		} else if (serviceName.equals("peptide") || serviceName.equals("librayPeptide") 
				|| serviceName.equals("arrayPeptide")) {
			quoteItem.setPeptide(new PeptideDTO());
			if (srcQuoteItem != null) {
				QuotePeptide peptide = quotePeptideDao.getById(srcQuoteItem.getQuoteItemId());
				if (peptide != null) {
					PeptideDTO peptideDTO = dozer.map(peptide, PeptideDTO.class);
					peptideDTO.setQuoteNo(quoteItem.getQuoteNo());
					peptideDTO.setQuoteItemId(null);
					quoteItem.setPeptide(peptideDTO);
				}
			}
		}
	}
	
	/**
	 * get special Drop Down List except common Drop Down List
	 * 
	 * @author zouyulu
	 * @param names
	 * @return the Map of DropDownListDTO
	 */
	@Transactional(readOnly = true)
	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownMap(
			List<SpecDropDownListName> names) {
		Map<SpecDropDownListName, DropDownListDTO> dropDownList = new HashMap<SpecDropDownListName, DropDownListDTO>();
		if (names != null) {
			for (SpecDropDownListName specDropDownListName : names) {
				List<DropDownDTO> list = specDropDownListDao
						.getSpecDropDownList(specDropDownListName.value());
				DropDownListDTO dropDownListDTO = new DropDownListDTO();
				dropDownListDTO.setName(specDropDownListName.value());
				dropDownListDTO.setDropDownDTOs(list);
				dropDownList.put(specDropDownListName, dropDownListDTO);
			}
			return dropDownList;
		} else {
			return null;
		}
	}
	
	/**
	 * 在session中增加新的item
	 * 
	 * @param itemMap
	 * @param afterItemId
	 * @param newItemId
	 * @param newItem
	 */
	private Map<String, QuoteItemDTO> insertNewChild(Map<String, QuoteItemDTO> itemMap, String afterItemId, 
			String newItemId, QuoteItemDTO newItem, String sessQuoteNo) {
		if (StringUtils.isEmpty(afterItemId)) {
			Integer itemNo = itemMap.size() + 1;
			newItem.setItemNo(itemNo);
			itemMap.put(newItemId, newItem);
		} else {
			Map<String, QuoteItemDTO> newItemMap = new LinkedHashMap<String, QuoteItemDTO>();
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			QuoteItemDTO afterItem = itemMap.get(afterItemId);
			Integer itemNo = afterItem.getItemNo();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				String tmpKey = entry.getKey();
				QuoteItemDTO tmpItemDTO = entry.getValue();
				Integer tmpItemNo = tmpItemDTO.getItemNo();
				if (tmpItemNo.intValue() == itemNo.intValue()) {
					newItemMap.put(tmpKey, tmpItemDTO);
					newItem.setItemNo(tmpItemNo.intValue() + 1);
					newItemMap.put(newItemId, newItem);
				} else {
					if (tmpItemNo.intValue() > itemNo.intValue()) {
						tmpItemDTO.setItemNo(tmpItemNo.intValue() + 1);
					}
					newItemMap.put(tmpKey, tmpItemDTO);
				}
			}
			SessionUtil.updateRow(SessionConstant.QuoteItemList.value(),
					sessQuoteNo, newItemMap);
			itemMap = newItemMap;
		}
		return itemMap;
	}
	//------------------保存Template Item add by Zhang Yong End---------------------------//	
	/**
	 * 由id查找QuoteItemDTO对象
	 */
	public QuoteItemDTO findById(Integer id) {
		QuoteItem item = this.quoteItemDao.getById(id);
		QuoteItemDTO itemDTO = this.dozer.map(item,QuoteItemDTO.class);
		return itemDTO;
	}
	
	/**
	 * 获得service item的其他信息
	 */
	public QuoteItemDTO getServiceItemOtherInfo(String serviceName,QuoteItemDTO itemInfo,QuoteItemDTO parentItem,QuoteItemDTO preItem) {
		if("Antibody-Pep".equals(serviceName)) {
			if(preItem!=null) {
				itemInfo.setPreParentId(preItem.getSessionItemId());
			}
			//Comments
			String aq53 = "Antibody production. Aliquot: 5mg*3. Please send to GenScript Antibody Group.";
			String aq54 = "Antibody production. Aliquot: 5mg*4. Please send to GenScript Antibody Group.";
			String aq55 = "Antibody production. Aliquot: 5mg*5. Please send to GenScript Antibody Group.";
			String aq1452 = "Antibody production. Aliquot: 1mg + 4mg + 5mg*2. Please send to GenScript Antibody Group.";
			String aq1453 = "Antibody production. Aliquot: 1mg + 4mg + 5mg*3. Please send to GenScript Antibody Group.";
			//Quantity
			String qt10_14 = "10-14 mg";
			String qt15_19 = "15-19 mg";
			String qt20_24 = "20-24 mg";
			//Purity
			String p70 = ">70%";
			String p85 = ">85%";
			String p90 = ">90%";
			PeptideDTO peptide = new PeptideDTO();
			if(itemInfo.getCatalogNo() == null) {
				peptide.setPurity(p85);
				peptide.setQuantity(qt10_14);
				peptide.setComments(aq53);
			} else if(("SC1031").equals(parentItem.getCatalogNo()) || ("SC1179").equals(parentItem.getCatalogNo())
					|| ("SC1344").equals(parentItem.getCatalogNo()) || ("SC1345").equals(parentItem.getCatalogNo())) {
				peptide.setPurity(p85);
				peptide.setQuantity(qt15_19);
				peptide.setComments(aq54);
			} else if(("SC1044").equals(parentItem.getCatalogNo())) {
				peptide.setPurity(p90);
				peptide.setQuantity(qt15_19);
				peptide.setComments(aq1453);
				if (preItem != null && preItem.getCatalogNo().equals(itemInfo.getCatalogNo())) {
					peptide.setQuantity(qt10_14);
					peptide.setComments(aq1452);
				} 
			} else if(("SC1180").equals(parentItem.getCatalogNo())) {
				peptide.setPurity(p85);
				peptide.setQuantity(qt20_24);
				peptide.setComments(aq55);
			} else if(("SC1041").equals(parentItem.getCatalogNo()) || ("SC1392").equals(parentItem.getCatalogNo())) {
				peptide.setPurity(p90);
				peptide.setQuantity(qt15_19);
				peptide.setComments(aq54);
			} else if(("SC1301").equals(parentItem.getCatalogNo()) || ("SC1398").equals(parentItem.getCatalogNo())) {
				peptide.setPurity(p90);
				peptide.setQuantity(qt15_19);
				peptide.setComments(aq54);
				if (preItem != null && preItem.getCatalogNo().equals(itemInfo.getCatalogNo())) {
					peptide.setPurity(p70);
					peptide.setQuantity(qt10_14);
					peptide.setComments(aq53);
				}
			} else {
				peptide.setPurity(p85);
				peptide.setQuantity(qt10_14);
				peptide.setComments(aq53);
			}
			itemInfo.setPeptide(peptide);
		}
		return itemInfo;
	}
	
	/**
	 * 删除QuoteItem，只操作session不对数据库操作，此方法从Action中迁移过来
	 * @author Zhang Yong
	 * @param delItemIdStr
	 * @param sessQuoteNo
	 */
	@Transactional(readOnly = true)
	public void deleteItem (String delItemIdStr, String sessQuoteNo) {
		String[] delItemIds = delItemIdStr.split(",");
		List<String> delItemIdList = Arrays.asList(delItemIds);
		@SuppressWarnings("unchecked")
		Map<String, QuoteItemDTO> quoteItemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		Iterator<Entry<String, QuoteItemDTO>> it = quoteItemMap.entrySet().iterator();
		StatusList statusList = statusListDao.findByTypeAndCode(OrderQuoteStautsType.QUOTEITEM.value(), QuoteItemStatusType.CN.value());
		int i = 1;
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			quoteItemDTO.setItemNo(i);
			String tmpItemId = entry.getKey();
			if (delItemIdList.contains(tmpItemId)) {
				// 数据库中的变成CN状态
				if (StringUtils.isNumeric(tmpItemId)) {
					quoteItemDTO.setStatus(QuoteItemStatusType.CN.value());
					quoteItemDTO.setStatusText(statusList.getName());
				} else {
					//对于特殊的父项需要更新
					updateParentItem(quoteItemMap, quoteItemDTO);
					//删除父item时把子Item的parentId设为空
					updateChildrenItem(quoteItemMap, tmpItemId);
					// 新增的直接删除
					it.remove();
					quoteItemMap.remove(tmpItemId);
					continue;
				}
			}
			i++;
		}
	}
	
	/**
	 * 更新父项
	 * @author Zhang Yong
	 * @param orderItemMap
	 * @param item
	 */
	public void updateParentItem (Map<String, QuoteItemDTO> quoteItemMap, QuoteItemDTO item) {
		if (StringUtils.isBlank(item.getParentId()) || !quoteItemMap.containsKey(item.getParentId()) || item.getClsId() == null) {
			return;
		}
		QuoteItemDTO parentItem = quoteItemMap.get(item.getParentId());
		if (parentItem.getClsId() == 3 && parentItem.getGeneSynthesis() != null) {
			if (item.getClsId() == 9) {
				parentItem.getGeneSynthesis().setCloningFlag(N);
			} else if (item.getClsId() == 10) {
				parentItem.getGeneSynthesis().setPlasmidPrepFlag(N);
			} else if (item.getClsId() == 4) {
				parentItem.getGeneSynthesis().setMutagenesisFlag(N);
			}
		} else if (parentItem.getClsId() == 4 && parentItem.getMutagenesis() != null) {
			if (item.getClsId() == 9) {
				parentItem.getMutagenesis().setCloningFlag(N);
			} else if (item.getClsId() == 10) {
				parentItem.getMutagenesis().setPlasmidPrepFlag(N);
			}
		} else if (parentItem.getClsId() == 5 && parentItem.getMutationLibraries() != null) {
			if (item.getClsId() == 9) {
				parentItem.getMutationLibraries().setCloningFlag(N);
				parentItem.getMutationLibraries().setTgtVectorName("PCR products");
			} else if (item.getClsId() == 10) {
				parentItem.getMutationLibraries().setPlasmidPrepFlag(N);
			}
		} else if (parentItem.getClsId() == 9 && parentItem.getCustCloning() != null) {
			if (item.getClsId() == 9) {
				parentItem.getCustCloning().setCloningFlag(N);
			} else if (item.getClsId() == 10) {
				parentItem.getCustCloning().setPlasmidPrepFlag(N);
			}
		}
	}
	
	public void updateChildrenItem (Map<String, QuoteItemDTO> quoteItemMap, String tempItemId) {
		Iterator<Entry<String, QuoteItemDTO>> it = quoteItemMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			if(quoteItemDTO.getParentId() != null && quoteItemDTO.getParentId().equals(tempItemId)){
				quoteItemDTO.setParentId(null);
			}
		}
	}

	public List<QuoteItem> getAllItemsByquoteNo(int quoteNo) {
		
		return this.quoteItemDao.getAllItemsByquoteNo(quoteNo);
	}
	
	public QuoteItemDTO getItemMoreDetail(QuoteItemDTO itemDTO) {
		String itemName = itemDTO.getName();
		String nameShow = itemName;
		Integer clsId = itemDTO.getClsId();
		if (clsId == null || clsId == 0) {
			return itemDTO;
		}
		boolean noParentId = StringUtils.isEmpty(itemDTO.getParentId()) ;
		if  (clsId == 1 || clsId == 30 || clsId == 31) {
			PeptideDTO peptideTemp = itemDTO.getPeptide();
			if (peptideTemp != null) {
				nameShow = nameShow+":"+(peptideTemp.getName()==null?"":peptideTemp.getName());
				itemDTO.setNameShow(nameShow);
			}
			return itemDTO;
		} else if  (clsId == 3) {
			OrderGeneSynthesisDTO geneTemp = itemDTO.getGeneSynthesis();
			if (geneTemp != null && noParentId) {
				nameShow = nameShow+": "+(geneTemp.getGeneName()==null?"":geneTemp.getGeneName());
				itemDTO.setNameShow(nameShow);
			}
			return itemDTO;
		}
		return itemDTO;
	}
	
	/**
	 * 通过quoteNo查询
	 * @author Zhang Yong
	 * add date 2011-11-14
	 * @param quoteNo
	 * @return
	 */
	public List<QuoteDsPlates> getDsPlateByQuoteNo (String quoteNo) {
		if (StringUtil.isNumeric(quoteNo)) {
			return quoteDsPlatesDao.getDsPlateByQuoteNo(Integer.parseInt(quoteNo));
		}
		return null;
	}
	
	/**
	 * 获得DNA Sequencing Map
	 * @author Zhang Yong
	 * add date 2011-11-14
	 * @param itemMap
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Map<String, QuoteItemDTO>> getDNASeqItemByType (Map<String, QuoteItemDTO> itemMap) {
		Map<String, Map<String, QuoteItemDTO>> resultTemMap = new HashMap<String, Map<String, QuoteItemDTO>>();
		if (itemMap == null || itemMap.isEmpty()) {
			return resultTemMap;
		}
        Map<String, QuoteItemDTO> tubeSeqMap = new LinkedHashMap<String, QuoteItemDTO>();
        Map<String, QuoteItemDTO> plateSeqMap = new LinkedHashMap<String, QuoteItemDTO>();
		ArrayList<String[]> resultTubeTempList = new ArrayList<String[]>();
		ArrayList<String[]> resultPlateTempList = new ArrayList<String[]>();
        for (String key : itemMap.keySet()) {
        	QuoteItemDTO dto = itemMap.get(key);
            if (dto == null || 40 != dto.getClsId().intValue() || dto.getDnaSequencing() == null 
					|| StringUtils.isBlank(dto.getDnaSequencing().getSampleName())
					|| StringUtils.isBlank(dto.getDnaSequencing().getCode())
					|| StringUtils.isNotBlank(dto.getParentId())) {
				continue;
			}
            String[] keys = new String[2];
            keys[0] = key;
			if (dto.getDnaSequencing().getCode().startsWith("T")) {
				resultTubeTempList.add(keys);
			} else if (dto.getDnaSequencing().getCode().startsWith("P")) {
				resultPlateTempList.add(keys);
			}
        }
        for (String[] keys : resultTubeTempList) {
        	tubeSeqMap.put(keys[0], itemMap.get(keys[0]));
            String childKeys = keys[1];
            if(childKeys != null){
                childKeys = keys[1].substring(1, keys[1].length());
            }else {
                continue;
            }
            for(int i = 0; i < childKeys.split(",").length; i++){
            	tubeSeqMap.put(childKeys.split(",")[i], itemMap.get(childKeys.split(",")[i]));
            }
        }
        resultTemMap.put("tubeMap", tubeSeqMap);
        for (String[] keys : resultPlateTempList) {
        	plateSeqMap.put(keys[0], itemMap.get(keys[0]));
            String childKeys = keys[1];
            if(childKeys != null){
                childKeys = keys[1].substring(1, keys[1].length());
            }else {
                continue;
            }
            for(int i = 0; i < childKeys.split(",").length; i++){
            	plateSeqMap.put(childKeys.split(",")[i], itemMap.get(childKeys.split(",")[i]));
            }
        }
        resultTemMap.put("plateMap", plateSeqMap);
		return resultTemMap;
	}
	
	/**
	 * 通过quoteNo查询
	 * @author Zhang Yong
	 * add date 2011-11-14
	 * @param quoteNo
	 * @return
	 */
	public List<QuoteDsPlatesDTO> getDsPlateByQuoteNo (Map<String, QuoteItemDTO> itemMap, String quoteNo) {
		List<QuoteDsPlatesDTO> dsPlateDTOList = new ArrayList<QuoteDsPlatesDTO>();
		if (itemMap == null || itemMap.isEmpty()) {
			return dsPlateDTOList;
		}
		Map<String, String> dsPlateMap = new HashMap<String, String>();
		Iterator<Entry<String, QuoteItemDTO>> iterator = itemMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, QuoteItemDTO> entry = iterator.next();
			QuoteItemDTO dto = entry.getValue();
			if (dto == null || 40 != dto.getClsId().intValue() || dto.getDnaSequencing() == null 
					|| StringUtils.isBlank(dto.getDnaSequencing().getSampleName())
					|| StringUtils.isBlank(dto.getDnaSequencing().getCode())) {
				continue;
			}
			DnaSequencingDTO dnaSequencing = dto.getDnaSequencing();
			if (dnaSequencing.getCode().startsWith("P")) {
				if (dsPlateMap.containsKey(dnaSequencing.getSessPlateId())) {
					continue;
				}
				dsPlateMap.put(dnaSequencing.getSessPlateId(), null);
				QuoteDsPlatesDTO dsPlate = new QuoteDsPlatesDTO();
				dsPlate.setName(dnaSequencing.getpName());
				dsPlate.setNums(dnaSequencing.getPlateNums());
				dsPlate.setSessPlateId(dnaSequencing.getSessPlateId());
				dsPlate.setLayout(dnaSequencing.getPlateLayout());
				dsPlateDTOList.add(dsPlate);
			}
		}
		return dsPlateDTOList;
	}

	/**
	 * 将新增的item填充到session中service内容为空的item，不为空则在session中新增一条记录
	 * @author Zhang Yong
	 * add date 2011-11-24
	 * @param itemId
	 * @param quoteItemDTO
	 * @param sessquoteNo
	 * @param itemMap
	 * @return
	 */
	public String insertSpecialService (String itemId, QuoteItemDTO quoteItemDTO, String sessquoteNo, Map<String, QuoteItemDTO> itemMap) {
		String blankItemKey = null;
		QuoteItemDTO blankItemDTO = null;
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			QuoteItemDTO tmpItemDTO = entry.getValue();
			if (tmpItemDTO.getClsId().intValue() == 34) {
				if (tmpItemDTO.getOligo() == null || StringUtils.isBlank(tmpItemDTO.getOligo().getOligoName())) {
					blankItemKey = tmpKey;
					blankItemDTO = tmpItemDTO;
					break;
				}
			}
		}
		if (blankItemDTO != null) {
			itemId = blankItemKey;
			itemMap.put(blankItemKey, quoteItemDTO);
		} else {
			Integer itemNo = itemMap.size() + 1;
			quoteItemDTO.setItemNo(itemNo);
			itemMap.put(itemId, quoteItemDTO);
		}
		SessionUtil.updateRow(SessionConstant.QuoteItemList.value(), sessquoteNo, itemMap);
		return itemId;
	}
	
	/**
	 * 批量Oligo（包含Manufacturing模块直接批量Oligo）
	 * @author Zhang Yong
	 * add date 2011-11-24
	 * @param itemId
	 * @param sequence
	 * @param batchBackbone
	 * @param batchSynthesisScales
	 * @param batchPurification
	 * @param batchModification5
	 * @param batchModification3
	 * @param batchAliquotingInto
	 * @param batchAliquotingSize
	 * @param upload
	 * @param uploadFileName
	 * @param retMap
	 * @param sessQuoteNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> batchOligo (String itemId, String sequence, String batchBackbone, 
			String batchSynthesisScales, String batchPurification, String batchModification5,
			String batchModification3, String batchAliquotingInto, String batchAliquotingSize, 
			List<File> upload, List<String> uploadFileName, 
			Map<String, QuoteItemDTO> retMap, String sessQuoteNo) throws Exception {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		String rtnMessage = null;
		@SuppressWarnings("unchecked")
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if (itemMap == null) {
			rtnMessage = "Batch oligo failure, can not find source item, Please check the session";
			rtnMap.put("rtnMessage", rtnMessage);
			return rtnMap;
		}
		QuoteItemDTO quoteItemDTO = itemMap.get(itemId);
		String catalogNo = quoteItemDTO.getCatalogNo();
		if (StringUtils.isBlank(catalogNo)) {
			rtnMessage = "Batch oligo failure, source item catalogNo can not be null, Please check the session";
			rtnMap.put("rtnMessage", rtnMessage);
			return rtnMap;
		}
		//在order页面批量Oligo
		if (StringUtils.isEmpty(sequence) && (upload == null || upload.isEmpty())) {
			rtnMessage = "Batch oligo failure, Please check the session";
			rtnMap.put("rtnMessage", rtnMessage);
			return rtnMap;
		}
		// 上传附件不为空则以附件为主
		if (upload != null && !upload.isEmpty()) {
			for (int i = 0; i < upload.size(); i++) {
				String uploadFileNameStr = uploadFileName.get(i);
				String fileNameSuffix = uploadFileNameStr.substring(uploadFileNameStr.lastIndexOf(".") + 1);
				// The upload file type is Excel type
				if (fileNameSuffix.equalsIgnoreCase("xls") || fileNameSuffix.equalsIgnoreCase("xlsx")) {
					List<ArrayList<String>> excelList = excelUtil.readTool(upload.get(i), uploadFileName.get(i));
					if (excelList == null || excelList.isEmpty()) {
						rtnMessage = "Batch oligo failure, The upload file content is empty.";
						rtnMap.put("rtnMessage", rtnMessage);
						return rtnMap;
					}
					for (List<String> strList : excelList) {
						if (strList.size() < 2) {
							continue;
						}
						String oligoName = strList.get(0);
						String seq = strList.get(1);
						seq = seq.toUpperCase();
						if (!checkSequence(batchBackbone, seq)) {
							rtnMessage = "Batch oligo failure, Please upload The correct Oligo Sequence.";
							rtnMap.put("rtnMessage", rtnMessage);
							//更新Map中信息
							retMap = updateItemMapInfo(retMap, itemMap, sessQuoteNo);
							return rtnMap;
						}
						if (!StringUtils.isEmpty(batchModification5) && !batchModification5.equals("null")) {
							seq = batchModification5 + seq;
						}
						if (!StringUtils.isEmpty(batchModification3) && !batchModification3.equals("null")) {
							seq += batchModification3;
						}
						newMultipleOligoSessionSequence(retMap, sessQuoteNo, 
								oligoName, seq, batchBackbone, batchSynthesisScales, 
								batchPurification, batchAliquotingInto, batchAliquotingSize, 
								itemId, quoteItemDTO, itemMap);
					}
				} else {
					rtnMessage = "Batch oligo failure, The upload file type must be Excel type.";
					rtnMap.put("rtnMessage", rtnMessage);
					return rtnMap;
				}
			}
			if (retMap != null && !retMap.isEmpty()) {
				SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
				SessionUtil.insertRow(SessionConstant.OtherOligoList.value(), sessQuoteNo, retMap);
			} else {
				rtnMessage = "Batch oligo failure, Please upload The correct Oligo Name and Sequence.";
				rtnMap.put("rtnMessage", rtnMessage);
			}
			// 附件为空时，则取sequence输入框内容
		} else if (StringUtils.isNotEmpty(sequence)) {
			String[] seqArray = sequence.split("\r\n");
			StringBuffer sbf = new StringBuffer();
			String oligoName = null;
			for (int i = 0; i < seqArray.length; i++) {
				String str = seqArray[i];
				if (StringUtils.isEmpty(str)) {
					continue;
				}
				if (str.contains(">")) {
					if (!StringUtils.isEmpty(oligoName) && !StringUtils.isEmpty(sbf.toString())) {
						String seq = sbf.toString().replaceAll(" ", "").toUpperCase();
						if (!checkSequence(batchBackbone, seq)) {
							rtnMessage = "Batch oligo failure, Please enter The correct Oligo Sequence.";
							rtnMap.put("rtnMessage", rtnMessage);
							//更新Map中信息
							retMap = updateItemMapInfo(retMap, itemMap, sessQuoteNo);
							return rtnMap;
						}
						if (!StringUtils.isEmpty(batchModification5) && !batchModification5.equals("null")) {
							sbf.append(batchModification5);
						}
						if (!StringUtils.isEmpty(batchModification3) && !batchModification3.equals("null")) {
							sbf.append(batchModification3);
						}
						newMultipleOligoSessionSequence(retMap, sessQuoteNo, 
								oligoName.replaceAll(" ", ""), sbf.toString(), batchBackbone, 
								batchSynthesisScales, batchPurification, batchAliquotingInto, 
								batchAliquotingSize, itemId, quoteItemDTO, itemMap);
						oligoName = str.substring(str.lastIndexOf(">") + 1, str.length());
					} else {
						oligoName = str.substring(str.lastIndexOf(">") + 1, str.length());
					}
					sbf = new StringBuffer();
				} else {
					sbf.append(str);
					if (i == seqArray.length - 1 && !StringUtils.isEmpty(oligoName) && !StringUtils.isEmpty(sbf.toString())) {
						String seq = sbf.toString().replaceAll(" ", "").toUpperCase();
						if (!checkSequence(batchBackbone, seq)) {
							rtnMessage = "Batch oligo failure, Please enter The correct Oligo Sequence.";
							rtnMap.put("rtnMessage", rtnMessage);
							//更新Map中信息
							retMap = updateItemMapInfo(retMap, itemMap, sessQuoteNo);
							return rtnMap;
						}
						if (!StringUtils.isEmpty(batchModification5) && !batchModification5.equals("null")) {
							sbf = new StringBuffer().append(batchModification5).append(sbf.toString());
						}
						if (!StringUtils.isEmpty(batchModification3) && !batchModification3.equals("null")) {
							sbf.append(batchModification3);
						}
						newMultipleOligoSessionSequence(retMap, sessQuoteNo, 
								oligoName.replaceAll(" ", ""), sbf.toString(), batchBackbone, 
								batchSynthesisScales, batchPurification, batchAliquotingInto,
								batchAliquotingSize, itemId, quoteItemDTO, itemMap);
						oligoName = str.substring(str.lastIndexOf(">") + 1, str.length());
					}
				}
			}
			if (retMap != null && !retMap.isEmpty()) {
				SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
				SessionUtil.insertRow(SessionConstant.OtherOligoList.value(), sessQuoteNo, retMap);
			} else {
				rtnMessage = "Batch oligo failure, Please enter The correct Oligo Sequence.";
				rtnMap.put("rtnMessage", rtnMessage);
			}
		}
		return null;
	}
	
	/**
	 * 更新Map中信息
	 * @author Zhang Yong 
	 * add date 2011-11-24
	 * @param retMap
	 * @param itemMap
	 * @param sessQuoteNo
	 * @return
	 */
	@SuppressWarnings("unused")
	private Map<String, QuoteItemDTO> updateItemMapInfo (Map<String, QuoteItemDTO> retMap,
			Map<String, QuoteItemDTO> itemMap, String sessQuoteNo) {
		if (retMap != null && !retMap.isEmpty()) {
			for (String key : retMap.keySet()) {
				itemMap.remove(key);
				if (itemMap == null) {
					itemMap = new HashMap<String, QuoteItemDTO>();
				}
			}
			retMap = new HashMap<String, QuoteItemDTO>();
		} else if ((retMap == null || retMap.isEmpty()) && !itemMap.isEmpty() && itemMap.size() == 1) {
			for (String key : itemMap.keySet()) {
				QuoteItemDTO sourceItem = itemMap.get(key);
				if (sourceItem != null) {
					sourceItem.setOligo(new OrderOligoDTO());
				}
			}
		}
		SessionUtil.updateRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, itemMap);
		return retMap;
	}
	
	/**
	 * 验证Sequence
	 * @author zhang yong
	 * @param batchBackbone
	 * @param sequence
	 * @return
	 */
	private boolean checkSequence(String batchBackbone, String sequence) {
		String newSequence = sequence.replaceAll("\\/[^/]*\\/", "")
				.replaceAll("\\{.*\\}", "").replaceAll(" ", "");
		boolean isCorrectSequence = false;
		String pattern = "";
		if (batchBackbone.contains("RNA")) {
			pattern = "^(A|C|G|U)*$";
		} else if (batchBackbone.contains("DNA")) {
			pattern = "^(A|C|G|T)*$";
		}
		Pattern pat = Pattern.compile(pattern);
		Matcher mat = pat.matcher(newSequence);
		boolean cb = mat.matches();
		if (cb) {
			isCorrectSequence = true;
		}
		return isCorrectSequence;
	}
	
	/**
	 * 复制粘贴Sequence时所调用方法
	 * @author Zhang Yong
	 * add date 2011-11-24
	 * @param retMap
	 * @param sessQuoteNo
	 * @param oligoName
	 * @param seq
	 * @param batchBackbone
	 * @param batchSynthesisScales
	 * @param batchPurification
	 * @param batchAliquotingInto
	 * @param batchAliquotingSize
	 * @param srcItemId
	 * @param srcQuoteItemDTO
	 * @param itemMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	private void newMultipleOligoSessionSequence(
			Map<String, QuoteItemDTO> retMap, String sessQuoteNo, String oligoName, String seq,
			String batchBackbone,String batchSynthesisScales, 
			String batchPurification, String batchAliquotingInto, String batchAliquotingSize,
			String srcItemId, QuoteItemDTO srcQuoteItemDTO, Map<String, QuoteItemDTO> itemMap) 
		throws IllegalAccessException, InvocationTargetException, Exception {
		if (retMap == null) {
			retMap = new HashMap<String, QuoteItemDTO>();
		}
		OrderOligoDTO oligoDTO = new OrderOligoDTO();
		oligoDTO.setSequence(seq);
		oligoDTO.setSeqLength(seq.replaceAll("\\/[^/]*\\/", "").replaceAll("\\{.*\\}", "")
				.replaceAll("\\(.*?\\)", "X").replaceAll(" ", "").length());
		oligoDTO.setOligoName(oligoName);
		oligoDTO.setBackbone(batchBackbone);
		oligoDTO.setSynthesisScales(batchSynthesisScales);
		oligoDTO.setPurification(batchPurification);
		oligoDTO.setAliquotingInto(StringUtil.isNumeric(batchAliquotingInto) ? Integer
				.parseInt(batchAliquotingInto) : 1);
		oligoDTO.setAliquotingSize(StringUtil.isNumeric(batchAliquotingSize) ? new BigDecimal(
				batchAliquotingSize).setScale(3, BigDecimal.ROUND_HALF_UP).toString(): null);
		oligoDTO.setOthermodificationFlag3(N);
		oligoDTO.setOthermodificationFlag5(N);
		oligoDTO.setInterOtherModificationFlag(N);
		QuoteItemDTO quoteItemDTO = null;
		String tmpId = null;
		if (srcQuoteItemDTO.getOligo() == null || StringUtils.isBlank(srcQuoteItemDTO.getOligo().getOligoName())) {
			quoteItemDTO = srcQuoteItemDTO;
			tmpId = srcItemId;
		} else {
			tmpId = SessionUtil.generateTempId();
			quoteItemDTO = new QuoteItemDTO();
			ModelUtils.mergerModel(srcQuoteItemDTO, quoteItemDTO);
			quoteItemDTO.setItemNo(itemMap.size() + 1);
			quoteItemDTO.setQuoteItemId(null);
		}
		quoteItemDTO.setNameShow(quoteItemDTO.getName() + ": " + oligoName);
		quoteItemDTO.setOligo(oligoDTO);
		// 计算Oligo Item的价格,方法里有对quoteItem的价格设值
		quoteOrderService.getOligoPriceAndCost(sessQuoteNo, quoteItemDTO);
		// add by zhanghuibin
		quoteItemDTO.setUpdateFlag(Y);
		if (!tmpId.equals(srcItemId)) {
			retMap.put(tmpId, quoteItemDTO);
		}
		itemMap.put(tmpId, quoteItemDTO);
	}
	
	/**
	 * 计算service价格
	 * @param quoteItem
	 * @return
	 */
//	private ServiceItemPiceDTO getCostPrice(QuoteItemDTO quoteItem) {
//		ServiceItemPiceDTO orderServiceItemPiceDTO = new ServiceItemPiceDTO();
//		orderServiceItemPiceDTO.setServiceClsId(quoteItem.getClsId());
//		orderServiceItemPiceDTO.setCatalogId(quoteItem.getCatalogId());
//		orderServiceItemPiceDTO.setCatalogNo(quoteItem.getCatalogNo());
//		orderServiceItemPiceDTO.setQuantity(quoteItem.getQuantity());
//		return orderServiceItemPiceDTO;
//	}
	
	/**
	 * 从QuoteMoreAction中拷贝了此方法，有修改
	 * @author Zhang Yong
	 * add date 2011-11-23
	 * @param sessQuoteNo
	 * @param quoteItem
	 * @param quoteServiceItemPiceDTO
	 */
	public void returnQuoteServiceItemPrice(String sessQuoteNo, QuoteItemDTO quoteItem, ServiceItemPiceDTO quoteServiceItemPiceDTO) {
		quoteItem.setUpdateFlag(Y);
		BigDecimal zero = new BigDecimal(0);
		QuoteMainDTO quoteMain = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		String currency = "";
		if (quoteMain != null && StringUtils.isNotBlank(quoteMain.getQuoteCurrency())) {
			currency = quoteMain.getQuoteCurrency();
		} else {
			currency = CurrencyType.USD.value();
		}
		ServiceItemPiceDTO newQuoteServiceItemPiceDTO = null;
		if (ServicePriceType.peptide.name().equals(quoteServiceItemPiceDTO.getServicePriceType())) {
			boolean needGetDifcult = false;
			if (1 == quoteItem.getClsId().intValue()) {
				if (StringUtils.isBlank(quoteItem.getParentId())) {
					needGetDifcult = true;
				} else {
					@SuppressWarnings("unchecked")
					Map<String, QuoteItemDTO> sessItemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
					if (1 == sessItemMap.get(quoteItem.getParentId()).getClsId().intValue()) {
						needGetDifcult = true;
					}
				}
			}
			newQuoteServiceItemPiceDTO = priceService.getPeptidePrice(quoteServiceItemPiceDTO, needGetDifcult, currency);
		} else {
			newQuoteServiceItemPiceDTO = publicService.calculateServicePrice(quoteServiceItemPiceDTO);
		}
		OrderGeneSynthesisDTO geneSynthesisDTO = quoteServiceItemPiceDTO.getGeneSynthesisDTO();
		if (newQuoteServiceItemPiceDTO.getTBDFlag() != null && newQuoteServiceItemPiceDTO.getTBDFlag().equals("1")) {
			if (geneSynthesisDTO != null && quoteServiceItemPiceDTO.getServiceClsId() == 3 && geneSynthesisDTO.getBpPrice() != null) {
				quoteItem.setTbdFlag(tbd_0);
			} else {
				quoteItem.setTbdFlag(newQuoteServiceItemPiceDTO.getTBDFlag());
				quoteItem.setCost(zero);
				quoteItem.setUnitPrice(zero);
				quoteItem.setAmount(zero);
				quoteItem.setBasePrice(zero);
				return;
			}
		} else {
			quoteItem.setTbdFlag(tbd_0);
		}
		quoteItem.setCost(newQuoteServiceItemPiceDTO.getCost());
		Double rate = publicService.getRateByBaseCurrencyToCurrency(quoteMain.getQuoteCurrency(), new Date());
		if (rate == null) {
			quoteItem.setUnitPrice(zero);
			quoteItem.setAmount(zero);
			quoteItem.setBasePrice(zero);
			throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
		}
		quoteItem.setUnitPrice(newQuoteServiceItemPiceDTO.getPrice().multiply(new BigDecimal(rate)));
		quoteItem.setAmount(newQuoteServiceItemPiceDTO.getPrice().multiply(new BigDecimal(newQuoteServiceItemPiceDTO.getQuantity())).multiply(new BigDecimal(rate)));
		quoteItem.setBasePrice(newQuoteServiceItemPiceDTO.getPrice().multiply(new BigDecimal(rate)));
	}
	
	/**
	 * 更新QuoteItemDTO价格
	 * @author Zhang Yong
	 * add date 2011-11-28
	 * @param quoteItem
	 * @param quoteServiceItemPiceDTO
	 * @param sessQuoteNo
	 */
	public void updateQuoteItemPrice (QuoteItemDTO quoteItem, ServiceItemPiceDTO quoteServiceItemPiceDTO, String sessQuoteNo) {
		QuoteMainDTO quoteMain = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		BigDecimal zero = new BigDecimal(0);
		quoteItem.setCost(quoteServiceItemPiceDTO.getCost());
		Double rate = publicService.getRateByBaseCurrencyToCurrency(quoteMain.getQuoteCurrency(), new Date());
		if (rate == null) {
			quoteItem.setUnitPrice(zero);
			quoteItem.setAmount(zero);
			quoteItem.setBasePrice(zero);
			throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
		}
		quoteItem.setUnitPrice(quoteServiceItemPiceDTO.getPrice().multiply(new BigDecimal(rate)));
		quoteItem.setAmount(quoteServiceItemPiceDTO.getPrice().multiply(new BigDecimal(quoteItem.getQuantity())).multiply(new BigDecimal(rate)));
		quoteItem.setBasePrice(quoteServiceItemPiceDTO.getPrice());
		quoteItem.setUpdateFlag(Y);
	}
	
	/**
	 * 取得targetDate
	 * @param itemId
	 * @param itemMap
	 * @return
	 */
	public TargetDateDTO getTargetDate(String itemId, Map<String, QuoteItemDTO> itemMap){
		QuoteItemDTO itemInfo = itemMap.get(itemId);
		if(StringUtils.isNumeric(itemId)){
			quoteService.setItemAllMoreDetail(itemMap, itemInfo);
		}
		if(itemInfo==null)  {
			return null;
		}
		Integer clsId = itemInfo.getClsId();
		String catalogNo = itemInfo.getCatalogNo();
		com.genscript.gsscm.serv.entity.Service serv = serviceDao.getServiceByCatalogNo(catalogNo);
		Integer leadTime = serv.getLeadTime();
		boolean monoClonalFlag = serviceDao.getServiceClsByCatalogNo(catalogNo, "Monoclonal");
		monoClonalFlag = monoClonalFlag || "SC1041".equals(catalogNo) || "SC1110".equals(catalogNo) || "SC1043".equals(catalogNo) || "SC1117".equals(catalogNo) || "SC1227".equals(catalogNo);
		boolean polyClonalFlag = serviceDao.getServiceClsByCatalogNo(catalogNo, "Polyclonal");
		//Integer leadTime = itemInfo.getShipMethod();
		if(clsId == null){
			return null;
		}
		//基因
		if(clsId.equals(3)){
			OrderServiceDTO serviceDTO = new OrderServiceDTO();
			serviceDTO.setGeneSynthesis(itemInfo.getGeneSynthesis());
			Map<String, QuoteItemDTO> childItemMap = QuoteSessionUtil.getChildItemMap(itemId, itemMap);
			Iterator<Entry<String, QuoteItemDTO>> it = childItemMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO itemDTO = entry.getValue();
				if(itemDTO.getCustCloning() != null){
					serviceDTO.setCustCloning(itemDTO.getCustCloning());
				}
				if(itemDTO.getPlasmidPreparation() != null){
					serviceDTO.setPlasmidPreparation(itemDTO.getPlasmidPreparation());
				}
			}
			return QuoteOrderUtil.getGeneTargetDate(serviceDTO);
		}else if (clsId.equals(11) || clsId.equals(28)) {
			//多抗
			return QuoteOrderUtil.getPolyAntibodyTargetDate(catalogNo, leadTime);
		//}else if(clsId.equals(12)){
		}else if(monoClonalFlag ){
			//单抗
			Set<String> catalogNoSet = new HashSet<String>();
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO itemDTO = entry.getValue();
				String itemCatalogNo = itemDTO.getCatalogNo();
				if("SC1041".equals(itemCatalogNo) || "SC1110".equals(itemCatalogNo) || "SC1043".equals(itemCatalogNo) || "SC1117".equals(itemCatalogNo) || "SC1227".equals(itemCatalogNo)){
					catalogNoSet.add(itemCatalogNo);
				}
			}
			return QuoteOrderUtil.getMonAntibodyTargetDate(leadTime, catalogNoSet, catalogNo);
		}else if(clsId.equals(1)){
			//peptide
			OrderServiceDTO serviceDTO = new OrderServiceDTO();
			serviceDTO.setPeptide(itemInfo.getPeptide());
			return QuoteOrderUtil.getPeptideTargetDate(serviceDTO);
		}else if(MoreDetailUtil.isProteinGroupService(clsId)){
			//order_pkg_service
			return QuoteOrderUtil.getPkgServiceTargetDate(leadTime, catalogNo);
		}else if(clsId.equals(7) || clsId.equals(8)){
			return QuoteOrderUtil.getSirnaTargetDate();
		}else{
			return quoteOrderUtil.getOtherTargetDate(catalogNo);
		}
	}
	/**
	 * 获取前一个Item的ItemId
	 * @author Zhang Yong
	 * @param sessItemMap
	 * @param newItem
	 * @return
	 */
	public String getPreParentServiceId(Map<String, QuoteItemDTO> sessItemMap,
			QuoteItemDTO newItem) {
		String preParentId = newItem.getParentId();
		if (preParentId == null || preParentId == null) {
			return preParentId;
		}
		QuoteItemDTO parentDTO = sessItemMap.get(preParentId);
		if (parentDTO == null || parentDTO.getClsId() == null) {
			return preParentId;
		}
		Map<String, QuoteItemDTO> childrenMap = getAllFirstStepChildrenService(
				sessItemMap, preParentId);
		// 主服务下无子服务的新加的子服务直接放在主服务后面
		if (childrenMap.isEmpty()) {
			return preParentId;
		}
		Integer parentClsId = parentDTO.getClsId();
		// 主服务为clsId=4(Gene),clsId=9(Custom Clone)
		if (3 == parentClsId.intValue() || 9 == parentClsId.intValue()) {
			// 新添加的 子服务为Plasmid Preparation，需放在Custom Cloning后面，Mutagenesis前面。
			if (10 == newItem.getClsId()) {
				Iterator<Entry<String, QuoteItemDTO>> childIt = childrenMap
						.entrySet().iterator();
				while (childIt.hasNext()) {
					Entry<String, QuoteItemDTO> entry = childIt.next();
					QuoteItemDTO tmpItemDTO = entry.getValue();
					if (9 == tmpItemDTO.getClsId().intValue()) {
						preParentId = entry.getKey();
						return preParentId;
					}
				}
			} else if (4 == newItem.getClsId()) {
				Map<String, QuoteItemDTO> childrenServiceMap = new LinkedHashMap<String, QuoteItemDTO>();
				//取父级服务下的所有子服务
				getAllStepChildrenService(sessItemMap, childrenServiceMap, preParentId);
				String lastItemId = null;
				int lastItenNo = 0;
				Iterator<Entry<String, QuoteItemDTO>> childIt = childrenServiceMap.entrySet().iterator();
				while (childIt.hasNext()) {
					Entry<String, QuoteItemDTO> entry = childIt.next();
					QuoteItemDTO tmpItemDTO = entry.getValue();
					if (tmpItemDTO.getItemNo() > lastItenNo) {
						lastItemId = entry.getKey();
						lastItenNo = tmpItemDTO.getItemNo();
					}
				}
				if (lastItenNo == 0 || lastItemId == null) {
					return preParentId;
				}
				return lastItemId;
			}
			// 以下主服务在添加子服务时子服务在后累加，主服务为11/12/28:Antibody,2:Protein
		} else if (2 == parentClsId.intValue() || 11 == parentClsId.intValue()
				|| 12 == parentClsId.intValue() || 14 == parentClsId.intValue()
				|| 16 == parentClsId.intValue() || 18 == parentClsId.intValue()
				|| 28 == parentClsId.intValue() || 32 == parentClsId.intValue()) {
			String lastItemId = null;
			int lastItenNo = 0;
			Iterator<Entry<String, QuoteItemDTO>> childIt = childrenMap
					.entrySet().iterator();
			while (childIt.hasNext()) {
				Entry<String, QuoteItemDTO> entry = childIt.next();
				QuoteItemDTO tmpItemDTO = entry.getValue();
				if (tmpItemDTO.getItemNo() > lastItenNo) {
					lastItemId = entry.getKey();
					lastItenNo = tmpItemDTO.getItemNo();
				}
			}
			if (lastItenNo != 0 && lastItemId != null) {
				preParentId = lastItemId;
			}
			// 主服务为clsId=4(Mutagenesis)或clsId=5(Mutation Libraries)
		} else if (4 == parentClsId.intValue() || 5 == parentClsId.intValue()) {
			// 新添加的 子服务为Plasmid Preparation，需放在Custom Cloning后面。
			if (10 == newItem.getClsId()) {
				Iterator<Entry<String, QuoteItemDTO>> childIt = childrenMap
						.entrySet().iterator();
				while (childIt.hasNext()) {
					Entry<String, QuoteItemDTO> entry = childIt.next();
					QuoteItemDTO tmpItemDTO = entry.getValue();
					if (9 == tmpItemDTO.getClsId().intValue()) {
						preParentId = entry.getKey();
						return preParentId;
					}
				}
			}
		}
		return preParentId;
	}

	/**
	 * 通过ItemId获取该服务下第一级所有子服务
	 * @author Zhang Yong
	 * @param itemMap
	 * @param itemId
	 * @return childrenServiceMap
	 */
	private Map<String, QuoteItemDTO> getAllFirstStepChildrenService(
			Map<String, QuoteItemDTO> itemMap, String itemId) {
		Map<String, QuoteItemDTO> childrenServiceMap = new LinkedHashMap<String, QuoteItemDTO>();
		if (itemMap == null || itemMap.isEmpty()) {
			return childrenServiceMap;
		}
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		// 获取主服务的所有第一级子服务
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			QuoteItemDTO tmpItemDTO = entry.getValue();
			if (QuoteItemType.SERVICE.value().equalsIgnoreCase(
					tmpItemDTO.getType())
					&& itemId.equals(tmpItemDTO.getParentId())) {
				childrenServiceMap.put(tmpKey, tmpItemDTO);
			}
		}
		return childrenServiceMap;
	}
	
	/**
	 * 通过ItemId获取该服务顶级服务下所有子服务
	 * @author Zhang Yong
	 * @param itemMap
	 * @param childrenServiceMap
	 * @param itemId
	 */
	private void getAllStepChildrenService(Map<String, QuoteItemDTO> itemMap, 
			Map<String, QuoteItemDTO> childrenServiceMap, String itemId) {
		if (childrenServiceMap == null) {
			childrenServiceMap = new LinkedHashMap<String, QuoteItemDTO>();
		}
		if (StringUtils.isBlank(itemId) || itemMap == null || itemMap.isEmpty()) {
			return;
		}
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		// 获取主服务的所有第一级子服务
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			QuoteItemDTO tmpItemDTO = entry.getValue();
			if (tmpItemDTO == null || !QuoteItemType.SERVICE.value().equalsIgnoreCase(tmpItemDTO.getType()) 
					|| StringUtils.isBlank(tmpItemDTO.getParentId())) {
				continue;
			}
			if (itemId.equals(tmpItemDTO.getParentId())) {
				childrenServiceMap.put(tmpKey, tmpItemDTO);
				getAllStepChildrenService(itemMap, childrenServiceMap, tmpKey);
			}
		}
		return;
	}
}
