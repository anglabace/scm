package com.genscript.gsscm.order.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PriceService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.ItemSearchType;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderQuoteStautsType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.ServicePriceType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.ExcelUtil;
import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.common.util.MoreDetailUtil;
import com.genscript.gsscm.common.util.MyX509TrustManager;
import com.genscript.gsscm.common.util.OrderSessionUtil;
import com.genscript.gsscm.common.util.PdfUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerSpecialPriceDao;
import com.genscript.gsscm.customer.dao.SalesProjectManagerAssignmentDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.dao.MfgOrderItemDao;
import com.genscript.gsscm.order.dao.OrderAntibodyDao;
import com.genscript.gsscm.order.dao.OrderCustCloningDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderGeneSynthesisDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderMutagenesisDao;
import com.genscript.gsscm.order.dao.OrderMutationLibrariesDao;
import com.genscript.gsscm.order.dao.OrderOligoDao;
import com.genscript.gsscm.order.dao.OrderOrfCloneDao;
import com.genscript.gsscm.order.dao.OrderPeptideDao;
import com.genscript.gsscm.order.dao.OrderPkgServiceDao;
import com.genscript.gsscm.order.dao.OrderPlasmidPreparationDao;
import com.genscript.gsscm.order.dao.OrderProcessLogDao;
import com.genscript.gsscm.order.dao.OrderRnaDao;
import com.genscript.gsscm.order.dao.ServiceOrderTemplateBeanDao;
import com.genscript.gsscm.order.dao.StatusListDao;
import com.genscript.gsscm.order.dto.ItemOtherInfoForNewDTO;
import com.genscript.gsscm.order.dto.OrderDsPlatesDTO;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.MfgOrderItem;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderAntibody;
import com.genscript.gsscm.order.entity.OrderCustCloning;
import com.genscript.gsscm.order.entity.OrderGeneSynthesis;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderMutagenesis;
import com.genscript.gsscm.order.entity.OrderMutationLibraries;
import com.genscript.gsscm.order.entity.OrderOligo;
import com.genscript.gsscm.order.entity.OrderOrfClone;
import com.genscript.gsscm.order.entity.OrderPeptide;
import com.genscript.gsscm.order.entity.OrderPkgService;
import com.genscript.gsscm.order.entity.OrderPlasmidPreparation;
import com.genscript.gsscm.order.entity.OrderRna;
import com.genscript.gsscm.order.entity.ServiceOrderTemplateBean;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.DnaSequencingDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutationLibrariesDTO;
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
@Service
@Transactional
public class OrderItemService {
	
	/**
	 * spring注入orderItemDao
	 */
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private SalesProjectManagerAssignmentDao salesProjectManagerAssignmentDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;
	@Autowired
	private ServiceOrderTemplateBeanDao serviceOrderTemplateBeanDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private SpecDropDownListDao specDropDownListDao;
	@Autowired
	private StatusListDao statusListDao;
	@Autowired
	private OrderGeneSynthesisDao orderGeneSynthesisDao;
	@Autowired
	private OrderCustCloningDao orderCustCloningDao;
	@Autowired
	private OrderMutagenesisDao orderMutagenesisDao;
	@Autowired
	private OrderPlasmidPreparationDao orderPlasmidPreparationDao;
	@Autowired
	private OrderPkgServiceDao orderPkgServiceDao;
	@Autowired
	private OrderAntibodyDao orderAntibodyDao;
	@Autowired
	private OrderRnaDao orderRnaDao;
	@Autowired
	private OrderOrfCloneDao orderOrfCloneDao;
	@Autowired
	private OrderOligoDao orderOligoDao;
	@Autowired
	private OrderPeptideDao orderPeptideDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
    private OrderQuoteUtil orderQuoteUtil; 
	@Autowired
	private QuoteOrderUtil quoteOrderUtil;
	@Autowired
	private MfgOrderItemDao mfgOrderItemDao;
    @Autowired
    private ErpSalesOrderService erpSalesOrderService;
    @Autowired
    private OrderMutationLibrariesDao orderMutationLibrariesDao;
    @Autowired
    private OrderProcessLogDao orderProcessLogDao;
	@Autowired
	private ExcelUtil excelUtil;
	@Autowired
	private PublicService publicService;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	private final static String ROOTNODE = "0000";
	private final static String tbd_0 = "0";
	private final static String Y = "Y";
	private final static String N = "N";
	/**
	 * 根据orderNo查询详细信息
	 * @param  trackingNo 
	 * @return ShipPackages
	 */
	@SuppressWarnings("unchecked")
	public Page<OrderItemDTO> getOrderPageDetail(Page<OrderItem> page,
			Integer packageId) {
		Page<OrderItemDTO> retPage = new Page<OrderItemDTO>();
		List<OrderItemDTO> dtoList = new ArrayList<OrderItemDTO>();
		page = orderItemDao.getOrderPageDetail(page, packageId);
		if (page.getResult() != null) {
			for (OrderItem user : page.getResult()) {
				OrderItemDTO dto = dozer.map(user, OrderItemDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	/**
	 * 取得targetDate
	 * @param itemId
	 * @param itemMap
	 * @return
	 */
	public TargetDateDTO getTargetDate(String itemId, Map<String, OrderItemDTO> itemMap){
		OrderItemDTO itemInfo = itemMap.get(itemId);
		if(StringUtils.isNumeric(itemId)){
			orderService.setItemAllMoreDetail(itemMap, itemInfo);
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
			Map<String, OrderItemDTO> childItemMap = OrderSessionUtil.getChildItemMap(itemId, itemMap);
			Iterator<Entry<String, OrderItemDTO>> it = childItemMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO itemDTO = entry.getValue();
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
		}else if(monoClonalFlag){
			//单抗
			Set<String> catalogNoSet = new HashSet<String>();
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO itemDTO = entry.getValue();
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
	 * 更新OrderItem(CC-VC)返回OrderItemDTO对象
	 * @param itemNo 
	 * @param order
	 * @author lizhang
	 * @return
	 */
	public OrderItem saveOrderItem(Integer itemNo,Integer orderNo) {
		OrderItem orderItem = null;
		orderItem = this.orderItemDao.getOrderItem(orderNo, itemNo);
		if(orderItem==null) {
			return null;
		}
		if("CC".equals(orderItem.getStatus())) {
			orderItem.setStatus("VC");
			orderItem.setModifiedBy(SessionUtil.getUserId());
			orderItem.setModifyDate(new Date());
		}
//			if("service".equalsIgnoreCase(orderItem.getType())){
//				Map<String, OrderItemDTO> itemMap = SessionUtil.convertList2Map(order.getItemList(),
//				"orderItemId");
//				Date targetDate = this.getTargetDate(String.valueOf(orderItem.getOrderItemId()), itemMap);
//				orderItem.setTargetDate(targetDate);
//			}
		this.orderItemDao.save(orderItem);
		return orderItem;
		
	}
	
	/**
	 * 更新MfgOrderItem(CC-VC)返回MfgOrderItem对象
	 * @param itemNo 
	 * @param order
	 * @author lizhang
	 * @return
	 */
	public OrderItem saveMfgOrderItem(Integer itemNo,MfgOrder mfgOrder) {
		MfgOrderItem mfgOrderItem = null;
		OrderItem orderItem = null;
		mfgOrderItem = this.mfgOrderItemDao.searchOrderItemByOrderNoAndItemNo(mfgOrder.getOrderNo(), itemNo);
		orderItem = this.orderItemDao.getOrderItem(mfgOrder.getSrcSoNo(), itemNo);
		if(mfgOrderItem!=null&&"CC".equals(mfgOrderItem.getStatus())) {
			mfgOrderItem.setStatus("VC");
			mfgOrderItem.setModifiedBy(SessionUtil.getUserId());
			mfgOrderItem.setModifyDate(new Date());
		}
//			if(order!=null&&orderItem!=null&&"service".equalsIgnoreCase(orderItem.getType())){
//				Map<String, OrderItemDTO> itemMap = SessionUtil.convertList2Map(order.getItemList(),
//				"orderItemId");
//				Date targetDate = this.getTargetDate(String.valueOf(orderItem.getOrderItemId()), itemMap);
//				orderItem.setTargetDate(targetDate);
//				this.orderItemDao.save(orderItem);
//			}
		if(mfgOrderItem!=null) {
			this.mfgOrderItemDao.save(mfgOrderItem);
		}
		return orderItem;
		
	}
	
	/**
	 * 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
	 * @param orderItemDTO
	 * @return
	 */
	public boolean checkPriceChage (OrderItemDTO orderItemDTO) {
		boolean status = false;
		try {
			if (orderItemDTO != null && orderItemDTO.getOrderItemId() != null) {
				OrderItem orderItem = this.orderItemDao.searchOrderItemByItemId(orderItemDTO.getOrderItemId());
				if ((OrderItemStatusType.Y.value()).equals(orderItem.getPriceChanged())) {
					status = true;
				}
			}
		} catch (Exception ex) {
		}
		return status;
	}
	
	public static String getROOTNODE() {
		return ROOTNODE;
	}
	
	/*
	 * 获取item 根据orderNo
	 */
	public List<OrderItem> getOrderItemByOrderNo(Integer orderNo){
		return this.orderItemDao.findBy("orderNo", orderNo);
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
	 * 将有优惠的OrderItem直接显示优惠的UnitPrice
	 * @author Zhang Yong
	 * @param order
	 * @param orderItem
	 * @return
	 */
	public OrderItemDTO getOfferOrderItem (OrderMainDTO order, OrderItemDTO orderItem) {
		if (StringUtils.isEmpty(orderItem.getCatalogNo()) || order.getCustNo() == null) {
			return orderItem;
		}
		CustomerSpecialPrice  customerSpecialPrice  = customerSpecialPriceDao.getSpecialPrice(order.getCustNo(), orderItem.getCatalogNo());
		if (customerSpecialPrice == null) {
			return orderItem;
		}
		orderItem.setUnitPrice(customerSpecialPrice.getStandardPrice());
		orderItem.setAmount(customerSpecialPrice.getStandardPrice().multiply(new BigDecimal(orderItem.getQuantity())));
		if ((customerSpecialPrice.getMinQty() != null && customerSpecialPrice.getMinQty()>orderItem.getQuantity())) {
			return orderItem;
		}
		if (customerSpecialPrice.getOrderTotal() != null && customerSpecialPrice.getOrderTotal()
				.doubleValue() > orderItem.getAmount().doubleValue()) {
			return orderItem;
		}
		if (customerSpecialPrice.getEffFrom() != null && customerSpecialPrice.getEffFrom().getTime() > new Date().getTime()) {
			return orderItem;
		}
		if (customerSpecialPrice.getEffTo() != null && customerSpecialPrice.getEffTo().getTime() < new Date().getTime()) {
			return orderItem;
		}
		orderItem.setUnitPrice(customerSpecialPrice.getUnitPrice());
		BigDecimal amount = customerSpecialPrice.getUnitPrice().multiply(
				new BigDecimal(orderItem.getQuantity()));
		orderItem.setAmount(amount);
		return orderItem;
	}
	
//------------------保存Template Item add by Zhang Yong Start---------------------------//	
	public Map<String, OrderItemDTO> saveTempItemsItem (Map<String, OrderItemDTO> itemMap, 
			OrderMainDTO order, OrderItemDTO orderItem, String srcQuOrderNo,
			CustomerDTO customer, Integer defalutShipMethod, OrderAddress defaultAddress,
			String sessOrderNo) throws Exception {
		if (!QuoteItemType.SERVICE.value().equals(orderItem.getType())) {
			return itemMap;
		}
		String srcQuOrderItemNo = orderItem.getSrcItemNo();
		if (srcQuOrderItemNo == null || StringUtils.isEmpty(srcQuOrderItemNo.trim()) 
				|| srcQuOrderNo == null || StringUtils.isEmpty(srcQuOrderNo.trim())) {
			return itemMap;
		}
		if (ItemSearchType.SERVICE_TEMPLATE_ITEM.value().equals(orderItem.getTemplateType())) {
			String srcItemNo = orderItem.getSrcItemNo();
			OrderItem srcOrderItem = orderItemDao.getOrderItem(Integer.parseInt(srcQuOrderNo), 
					Integer.parseInt(srcItemNo));
			if (srcOrderItem == null || srcOrderItem.getParentId() != null) {
				return itemMap;
			}
			OrderMain srcOrder = orderDao.getById(srcOrderItem.getOrderNo());
			List<OrderItem> srcItemList = orderItemDao.getOrderAllItemList(srcOrderItem.getOrderNo());
			List<OrderItem> subItemList = new ArrayList<OrderItem>();
			subItemList.add(srcOrderItem);
			subItemList = getSubItemList(srcOrderItem, srcItemList, subItemList);
			List<ServiceOrderTemplateBean> sotList = serviceOrderTemplateBeanDao
				.searchServiceOrderTemplateItem(srcOrder.getCustNo(), srcOrderItem.getOrderNo());
			for (OrderItem item : subItemList) {
				itemMap = saveServiceSessionItem(item, sotList, itemMap, order, customer, 
						defalutShipMethod, defaultAddress, sessOrderNo);
			}
		}
		return itemMap;
	}
	
	private List<OrderItem> getSubItemList (OrderItem parentItem, List<OrderItem> srcSubItemList, 
			List<OrderItem> subItemList) {
		if (srcSubItemList == null) {
			return subItemList;
		}
		for (OrderItem srcSubItem : srcSubItemList) {
			if (parentItem.getOrderItemId().equals(srcSubItem.getParentId())) {
				subItemList.add(srcSubItem);
				getSubItemList(srcSubItem, srcSubItemList, subItemList);
			}
		}
		return subItemList;
	}
	
	private Map<String, OrderItemDTO> saveServiceSessionItem (OrderItem item, List<ServiceOrderTemplateBean> sotList, 
			Map<String, OrderItemDTO> itemMap, OrderMainDTO order, CustomerDTO customer,
			Integer defalutShipMethod, OrderAddress defaultAddress, String sessOrderNo)
	 		throws Exception {
		for (ServiceOrderTemplateBean sot : sotList) {
			if (item.getItemNo().equals(sot.getItemNo())) {
				String itemId = SessionUtil.generateTempId();
				OrderItemDTO orderItem = new OrderItemDTO ();
				orderItem.setCatalogNo(sot.getCatalogNo());
				orderItem.setName(sot.getServiceName());
				orderItem.setQuantity(1);
				orderItem.setSize(sot.getSize() == null?0d:sot.getSize().doubleValue());
				orderItem.setUnitPrice(new BigDecimal(0.0));
				orderItem.setUpSymbol(sot.getUpSymbol());
				orderItem.setSizeUom(sot.getUom());
				orderItem.setQtyUom(sot.getQtyUom());
				orderItem.setDiscount(new BigDecimal(0.0));
				orderItem.setTax(new BigDecimal(0.0));
				orderItem.setItemNo(itemMap.size()+1);
				orderItem.setAmount(new BigDecimal(0.0));
				orderItem.setType(QuoteItemType.SERVICE.value());
				orderItem.setCatalogId(sot.getCatalogId());
				orderItem.setCatalogText(sot.getCatalogId()+" : ");
				if (sot.getParentItemId() != null) {
					String parentItemId = (String)SessionUtil.getRow(SessionConstant.TemplateOrderItem.value(), 
							sot.getParentItemId().toString());
					if (parentItemId != null) {
						orderItem.setParentId(parentItemId);
					}
				}
				orderItem.setSrcItemNo(sot.getItemNo()==null?null:sot.getItemNo().toString());
				orderItem.setTemplateType(ItemSearchType.SERVICE_TEMPLATE_ITEM.value());
//				orderItem = getOfferOrderItem(order, orderItem);
				com.genscript.gsscm.serv.entity.Service serv = serviceDao.findUniqueBy("catalogNo", sot.getCatalogNo());
				if (serv != null) {
					if (serv.getServiceClsId() != null) {
						Integer clsId = serv.getServiceClsId();
						orderItem.setClsId(clsId);
						ServiceClass serviceClass = serviceClassDao.get(serv.getServiceClsId());
						if (serviceClass != null) {
							orderItem.setTypeText(orderItem.getType() + " - "
									+ serviceClass.getName());
						}
					}
					orderItem.setShipSchedule(serv.getLeadTime());
					orderItem.setShortDesc(serv.getShortDesc());
					orderItem.setLongDesc(serv.getLongDesc());
					orderItem.setSellingNote(serv.getSellingNote());
				}
				orderItem.setCost(new BigDecimal(0.0));
				orderItem.setBasePrice(orderItem.getUnitPrice().setScale(2,
						BigDecimal.ROUND_HALF_UP));
				if (Y.equalsIgnoreCase(customer.getTaxExemptFlag())
						&& !StringUtils.isEmpty(customer.getTaxId())) {
					orderItem.setTaxStatus(N);
				} else {
					if (serv != null) {
						orderItem.setTaxStatus(serv.getTaxable());
					}
				}
				if (order.getWarehouseId() != null) {
					List<DropDownDTO> pickLocationList = specDropDownListDao.getPickLocationList(order.getWarehouseId());
					if (pickLocationList != null && !pickLocationList.isEmpty()) {
						String idStr = pickLocationList.get(0).getId();
						orderItem.setStorageId(Integer.parseInt(idStr));
					}
				}
				ItemOtherInfoForNewDTO itemOtherInfo = getItemOtherInfoForNew(orderItem.getStorageId(),
								orderItem.getQuantity(), orderItem.getCatalogNo(), 
								QuoteItemType.fromValue(orderItem.getType()));
				if (org.apache.commons.lang.StringUtils.isNumeric(itemOtherInfo.getOtherInfo())) {
					orderItem.setOtherInfo("In Stock,"
							+ itemOtherInfo.getOtherInfo() + " Available");
				} else {
					orderItem.setOtherInfo(itemOtherInfo.getOtherInfo());
				}
				orderItem.setStatus("CM");
				orderItem.setStatusText("Committed");
				this.setServiceForItem(orderItem, MoreDetailUtil
						.getServiceNameByClsId(orderItem.getClsId()), sot, itemId);
				orderItem.setShipMethod(defalutShipMethod);
				orderItem.setShipToAddress(defaultAddress);
				orderItem.setNameShow(orderItem.getName());
				if (!StringUtils.isEmpty(order.getOrderCurrency())) {
					orderItem.setCurrencyCode(order.getOrderCurrency());
					orderItem = dozer.map(orderItem, OrderItemDTO.class);
					Date exchRateDate = order.getExchRateDate();
					if (exchRateDate == null) {
						exchRateDate = new Date();
					}
					Double rate = exchRateByDateDao.getCurrencyRate(order.getOrderCurrency(), CurrencyType.USD.value(), exchRateDate);
					if (rate != null && orderItem.getUnitPrice() != null) {
						int small = CurrencyType.JPY.value().equalsIgnoreCase(order.getOrderCurrency())?0:2;
						orderItem.setBasePrice(orderItem.getUnitPrice().multiply(new BigDecimal(rate)).setScale(small, BigDecimal.ROUND_HALF_UP));
					}
				}
				itemMap = this.insertNewChild(itemMap, orderItem.getParentId(), itemId,
						orderItem, sessOrderNo);
				break;
			}
		}
		return itemMap;
	}
	
	/**
     * 在编辑Order时， 当新增一个OrderItem时， 获得其Status. in: storageId, needCount,
     * catalogNo; out: status(BO, PB, CM) processed: 库存数为X; 1. X==0或不存在返回BO; 2.
     * 0< X <needCount, 返回PB; 3. X >= needCount, 返回CM.
     *
     * @param storageId
     * @param needCount OrderItem该产品需要的数量
     * @param catalogNo Product's catalogNo
     * @return
     */
    @Transactional(readOnly = true)
    public ItemOtherInfoForNewDTO getItemOtherInfoForNew(Integer storageId,
                                                         Integer needCount, String catalogNo, QuoteItemType itemType) {
    	ItemOtherInfoForNewDTO dto = new ItemOtherInfoForNewDTO();
        OrderItemStatusType itemStatus = null;
        Integer count = null;
        try {
        	count = QuoteItemType.PRODUCT.value().equalsIgnoreCase(itemType.toString())?this.getItemStockQty(storageId, null, catalogNo):null;
	    } catch (Exception ex) {
			count = 0;
		}
        if (count == null || !QuoteItemType.PRODUCT.value().equalsIgnoreCase(itemType.toString())) {
            //如果不是product,则默认有库存。
            itemStatus = OrderItemStatusType.CM;
        } else if (count.intValue() <= 0) {
            itemStatus = OrderItemStatusType.BO;
        } else if (count.intValue() < needCount) {
            itemStatus = OrderItemStatusType.PB;
        } else {
            itemStatus = OrderItemStatusType.CM;
        }
        String otherInfo = this.getItemOtherInfo(catalogNo, storageId,
                itemType, itemStatus, null, count);
        String itemStatusText = this.getOrderItemStatusText(itemStatus.value());
        dto.setOtherInfo(otherInfo);
        dto.setItemStatus(itemStatus.value());
        dto.setItemStatusText(itemStatusText);
        return dto;
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
     * 获得某个Product在某个Storage中的Other info.
     */
    @Transactional(readOnly = true)
    public String getItemOtherInfo(String catalogNo, Integer storageId, QuoteItemType itemType, 
    		OrderItemStatusType status, String reason, Integer count) {
    	String ret = "";
        if (itemType.equals(QuoteItemType.PRODUCT)) {
            if (status.equals(OrderItemStatusType.CN) || status.equals(OrderItemStatusType.OH)) {
                ret = reason;
            } else {
            	if (count == null) {
                	try {
    					count = this.getItemStockQty(storageId, null, catalogNo);
    				} catch (Exception ex) {
    					count = 0;
    				}
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
	private void setServiceForItem(OrderItemDTO orderItem, String serviceName, 
			ServiceOrderTemplateBean sot, String itemId) {
		Integer srcOrderNo = sot.getSrcOrderNo();
		Integer itemNo = sot.getItemNo();
		OrderItem srcOrderItem = null;
		if (srcOrderNo != null && itemNo != null) {
			srcOrderItem = orderItemDao.getOrderItem(srcOrderNo, itemNo);
			SessionUtil.insertRow(SessionConstant.TemplateOrderItem.value(), 
					srcOrderItem.getOrderItemId().toString(), itemId);
		}
		if (serviceName.equalsIgnoreCase("geneSynthesis")) {
			orderItem.setGeneSynthesis(new OrderGeneSynthesisDTO());
			if (srcOrderItem != null) {
				OrderGeneSynthesis gene = orderGeneSynthesisDao.getById(srcOrderItem.getOrderItemId());
				if (gene != null) {
					OrderGeneSynthesisDTO geneDTO = dozer.map(gene, OrderGeneSynthesisDTO.class);
					geneDTO.setOrderNo(orderItem.getOrderNo());
					geneDTO.setOrderItemId(null);
					DocumentType[] docTypeList = {DocumentType.OIM_GENE};
		            List<Document> docList = orderQuoteUtil.getItemDocList(srcOrderItem.getOrderItemId(), docTypeList);
		            geneDTO.setDocumentList(docList);
					orderItem.setGeneSynthesis(geneDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("mutagenesis")) {
			orderItem.setMutagenesis(new OrderMutagenesisDTO());
			if (srcOrderItem != null) {
				OrderMutagenesis muta = orderMutagenesisDao.getById(srcOrderItem.getOrderItemId());
				if (muta != null) {
					OrderMutagenesisDTO mutaDTO = dozer.map(muta, OrderMutagenesisDTO.class);
					mutaDTO.setOrderNo(orderItem.getOrderNo());
					mutaDTO.setOrderItemId(null);
					DocumentType[] docTypeList = {DocumentType.OIM_MUTA, DocumentType.OIM_MUTA_SELF};
		            List<Document> docList = orderQuoteUtil.getItemDocList(srcOrderItem.getOrderItemId(), docTypeList);
		            mutaDTO.setDocumentList(docList);
					orderItem.setMutagenesis(mutaDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("mutaLib")) {
			orderItem.setMutationLibraries(new OrderMutationLibrariesDTO());
			if (srcOrderItem != null) {
				OrderMutationLibraries mutaLib = orderMutationLibrariesDao.getById(srcOrderItem.getOrderItemId());
				if (mutaLib != null) {
					OrderMutationLibrariesDTO mutaLibDTO = dozer.map(mutaLib, OrderMutationLibrariesDTO.class);
					mutaLibDTO.setOrderNo(orderItem.getOrderNo());
					mutaLibDTO.setOrderItemId(null);
					DocumentType[] docTypeList = {DocumentType.OIM_MUTALIB, DocumentType.OIM_MUTALIB_SELF};
		            List<Document> docList = orderQuoteUtil.getItemDocList(srcOrderItem.getOrderItemId(), docTypeList);
		            mutaLibDTO.setDocumentList(docList);
					orderItem.setMutationLibraries(mutaLibDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("custCloning")) {
			orderItem.setCustCloning(new OrderCustCloningDTO());
			if (srcOrderItem != null) {
				OrderCustCloning clone = orderCustCloningDao.getById(srcOrderItem.getOrderItemId());
				if (clone != null) {
					OrderCustCloningDTO cloneDTO = dozer.map(clone, OrderCustCloningDTO.class);
					cloneDTO.setOrderNo(orderItem.getOrderNo());
					cloneDTO.setOrderItemId(null);
					DocumentType[] docTypeList = {DocumentType.OIM_CUSTCLONING,
	                        DocumentType.OIM_CUSTCLONING_CS,
	                        DocumentType.OIM_CUSTCLONING_SELF};
	                List<Document> docList = orderQuoteUtil.getItemDocList(srcOrderItem.getOrderItemId(), docTypeList);
	                cloneDTO.setDocumentList(docList);
					orderItem.setCustCloning(cloneDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("plasmidPreparation")) {
			orderItem.setPlasmidPreparation(new OrderPlasmidPreparationDTO());
			if (srcOrderItem != null) {
				OrderPlasmidPreparation plass = orderPlasmidPreparationDao.getById(srcOrderItem.getOrderItemId());
				if (plass != null) {
					OrderPlasmidPreparationDTO plassDTO = dozer.map(plass, OrderPlasmidPreparationDTO.class);
					plassDTO.setOrderNo(orderItem.getOrderNo());
					plassDTO.setOrderItemId(null);
					DocumentType[] docTypeList = {DocumentType.OIM_PLASMID,
	                        DocumentType.OIM_PLASMID_SELF};
	                List<Document> docList = orderQuoteUtil.getItemDocList(srcOrderItem.getOrderItemId(), docTypeList);
	                plassDTO.setDocumentList(docList);
					orderItem.setPlasmidPreparation(plassDTO);
				}
			}
		} else if (serviceName.equalsIgnoreCase("pkgService")) {
			orderItem.setOrderPkgService(new PkgServiceDTO());
			if (srcOrderItem != null) {
				OrderPkgService pkg = orderPkgServiceDao.getById(srcOrderItem.getOrderItemId());
				if (pkg != null) {
					PkgServiceDTO pkgDTO = dozer.map(pkg, PkgServiceDTO.class);
					pkgDTO.setOrderNo(orderItem.getOrderNo());
					pkgDTO.setOrderItemId(null);
					orderItem.setOrderPkgService(pkgDTO);
				}
			}
		} else if (serviceName.equals("antibody")) {
			orderItem.setAntibody(new AntibodyDTO());
			if (srcOrderItem != null) {
				OrderAntibody anti = orderAntibodyDao.getById(srcOrderItem.getOrderItemId());
				if (anti != null) {
					AntibodyDTO antiDTO = dozer.map(anti, AntibodyDTO.class);
					antiDTO.setOrderNo(orderItem.getOrderNo());
					antiDTO.setOrderItemId(null);
					orderItem.setAntibody(antiDTO);
				}
			}
		} else if (serviceName.equals("rna")) {
			orderItem.setRna(new RnaDTO());
			if (srcOrderItem != null) {
				OrderRna rna = orderRnaDao.getById(srcOrderItem.getOrderItemId());
				if (rna != null) {
					RnaDTO rnaDTO = dozer.map(rna, RnaDTO.class);
					rnaDTO.setOrderNo(orderItem.getOrderNo());
					rnaDTO.setOrderItemId(null);
					DocumentType[] docTypeList = {DocumentType.OIM_RNA};
		            List<Document> docList = orderQuoteUtil.getItemDocList(srcOrderItem.getOrderItemId(), docTypeList);
		            rnaDTO.setDocumentList(docList);
					orderItem.setRna(rnaDTO);
				}
			}
		} else if (serviceName.equals("oligo")) {
			orderItem.setOligo(new OrderOligoDTO());
			if (srcOrderItem != null) {
				OrderOligo oligo = orderOligoDao.getById(srcOrderItem.getOrderItemId());
				if (oligo != null) {
					OrderOligoDTO oligoDTO = dozer.map(oligo, OrderOligoDTO.class);
					oligoDTO.setOrderNo(orderItem.getOrderNo());
					oligoDTO.setOrderItemId(null);
					orderItem.setOligo(oligoDTO);
				}
			}
		} else if (serviceName.equals("orfClone")) {
			orderItem.setOrfClone(new OrderOrfCloneDTO());
			if (srcOrderItem != null) {
				OrderOrfClone orfClone = orderOrfCloneDao.getById(srcOrderItem.getOrderItemId());
				if (orfClone != null) {
					OrderOrfCloneDTO orfCloneDTO = dozer.map(orfClone, OrderOrfCloneDTO.class);
					orfCloneDTO.setOrderNo(orderItem.getOrderNo());
					orfCloneDTO.setOrderItemId(null);
					DocumentType[] docTypeList = {DocumentType.OIM_ORFCLONE};
	                List<Document> docList = orderQuoteUtil.getItemDocList(srcOrderItem.getOrderItemId(), docTypeList);
	                orfCloneDTO.setDocumentList(docList);
					orderItem.setOrfClone(orfCloneDTO);
				}
			}
		} else if (serviceName.equals("oligo")) {
			orderItem.setOligo(new OrderOligoDTO());
			if (srcOrderItem != null) {
				OrderOligo oligo = orderOligoDao.getById(srcOrderItem.getOrderItemId());
				if (oligo != null) {
					OrderOligoDTO oligoDTO = dozer.map(oligo, OrderOligoDTO.class);
					oligoDTO.setOrderNo(orderItem.getOrderNo());
					oligoDTO.setOrderItemId(null);
					orderItem.setOligo(oligoDTO);
				}
			}
		} else if (serviceName.equals("peptide") || serviceName.equals("librayPeptide") 
				|| serviceName.equals("arrayPeptide")) {
			orderItem.setPeptide(new PeptideDTO());
			if (srcOrderItem != null) {
				OrderPeptide peptide = orderPeptideDao.getById(srcOrderItem.getOrderItemId());
				if (peptide != null) {
					PeptideDTO peptideDTO = dozer.map(peptide, PeptideDTO.class);
					peptideDTO.setOrderNo(orderItem.getOrderNo());
					peptideDTO.setOrderItemId(null);
					orderItem.setPeptide(peptideDTO);
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
	private Map<String, OrderItemDTO> insertNewChild(Map<String, OrderItemDTO> itemMap, String afterItemId, 
			String newItemId, OrderItemDTO newItem, String sessOrderNo) {
		if (StringUtils.isEmpty(afterItemId)) {
			Integer itemNo = itemMap.size() + 1;
			newItem.setItemNo(itemNo);
			itemMap.put(newItemId, newItem);
		} else {
			Map<String, OrderItemDTO> newItemMap = new LinkedHashMap<String, OrderItemDTO>();
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			OrderItemDTO afterItem = itemMap.get(afterItemId);
			Integer itemNo = afterItem.getItemNo();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				String tmpKey = entry.getKey();
				OrderItemDTO tmpItemDTO = entry.getValue();
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
			SessionUtil.updateRow(SessionConstant.OrderItemList.value(),
					sessOrderNo, newItemMap);
			itemMap = newItemMap;
		}
		return itemMap;
	}
	//------------------保存Template Item add by Zhang Yong End---------------------------//	
	/**
	 * 由id查找OrderItemDTO对象
	 */
	public OrderItemDTO findById(Integer id) {
		OrderItem item = this.orderItemDao.getById(id);
		OrderItemDTO itemDTO = this.dozer.map(item,OrderItemDTO.class);
		return itemDTO;
	}
	
	/**
	 * 获得service item的其他信息
	 */
	public OrderItemDTO getServiceItemOtherInfo(String serviceName,OrderItemDTO itemInfo,OrderItemDTO parentItem,OrderItemDTO preItem) {
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
	
	public OrderItem getOrderItem(Integer orderNo, Integer itemNo){
		return this.orderItemDao.getOrderItem(orderNo, itemNo);
	}
	
	/**
	 * 删除OrderItem，只操作session不对数据库操作，此方法从Action中迁移过来
	 * @author Zhang Yong
	 * @param delItemIdStr
	 * @param sessOrderNo
	 */
	@Transactional(readOnly = true)
	public void deleteItem (String delItemIdStr, String sessOrderNo) {
		String[] delItemIds = delItemIdStr.split(",");
		List<String> delItemIdList = Arrays.asList(delItemIds);
		@SuppressWarnings("unchecked")
		Map<String, OrderItemDTO> orderItemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		Iterator<Entry<String, OrderItemDTO>> it = orderItemMap.entrySet().iterator();
		StatusList statusList = statusListDao.findByTypeAndCode(OrderQuoteStautsType.ORDERITEM.value(), OrderItemStatusType.CN.value());
		int i = 1;
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			orderItemDTO.setItemNo(i);
			String tmpItemId = entry.getKey();
			if (delItemIdList.contains(tmpItemId)) {
				// 数据库中的变成CN状态
				if (StringUtils.isNumeric(tmpItemId)) {
					orderItemDTO.setStatus(OrderItemStatusType.CN.value());
					orderItemDTO.setStatusText(statusList.getName());
				} else {
					//对于特殊的父项需要更新
					updateParentItem(orderItemMap, orderItemDTO);
					//删除父item时把子Item的parentId设为空
					updateChildrenItem(orderItemMap, tmpItemId);
					// 新增的直接删除
					it.remove();
					orderItemMap.remove(tmpItemId);
					continue;
				}
			}
			i++;
		}
		this.autoChangeMainStatus(statusList.getCode(), statusList.getName(), sessOrderNo);
	}
	
	/**
	 * 根据item的状态更新order的状态，此方法从Action中迁移过来
	 * @param mainStatus
	 * @param mainStatusText
	 * @param sessOrderNo
	 */
	@SuppressWarnings("unchecked")
	public void autoChangeMainStatus(String mainStatus, String mainStatusText, String sessOrderNo) {
		Map<String, OrderItemDTO> orderItemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (orderItemMap == null || orderItemMap.size() == 0 || mainStatus == null) {
			return;
		}
		Iterator<Entry<String, OrderItemDTO>> it = orderItemMap.entrySet().iterator();
		boolean changeFlag = true;
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			if (!orderItemDTO.getStatus().equalsIgnoreCase(mainStatus)) {
				changeFlag = false;
				break;
			}
		}
		if (changeFlag == true) {
			OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			if(!OrderStatusType.OH.getValue().equals(mainStatus) && !("CM").equalsIgnoreCase(mainStatus)){
				order.setStatus(mainStatus);
				order.setStatusText(mainStatusText);
				SessionUtil.updateRow(SessionConstant.Order.value(), sessOrderNo, order);
			}
		}
	}
	
	/**
	 * 更新父项
	 * @author Zhang Yong
	 * @param orderItemMap
	 * @param item
	 */
	public void updateParentItem (Map<String, OrderItemDTO> orderItemMap, OrderItemDTO item) {
		if (StringUtils.isBlank(item.getParentId()) || !orderItemMap.containsKey(item.getParentId()) || item.getClsId() == null) {
			return;
		}
		OrderItemDTO parentItem = orderItemMap.get(item.getParentId());
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
	
	public void updateChildrenItem (Map<String, OrderItemDTO> orderItemMap, String tempItemId) {
		Iterator<Entry<String, OrderItemDTO>> it = orderItemMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			if(orderItemDTO.getParentId() != null && orderItemDTO.getParentId().equals(tempItemId)){
				orderItemDTO.setParentId(null);
			}
		}
	}
	
	@Transactional(readOnly = true)
	public OrderItemDTO getItemMoreDetail(OrderItemDTO itemDTO) {
		String itemName = itemDTO.getName();
		String nameShow = itemName;
		Integer clsId = itemDTO.getClsId();
		if (clsId == null || clsId == 0) {
			return itemDTO;
		}
		boolean noParentId = StringUtils.isBlank(itemDTO.getParentId());
		if (clsId == 1 || clsId == 30 || clsId == 31) {
			PeptideDTO peptideTemp = itemDTO.getPeptide();
			if (peptideTemp != null) {
				nameShow = nameShow+ ": "+ (peptideTemp.getName() == null ? "" : peptideTemp.getName());
				itemDTO.setNameShow(nameShow);
			}
			return itemDTO;
		} else if (clsId == 3) {
			OrderGeneSynthesisDTO geneTemp = itemDTO.getGeneSynthesis();
			if (geneTemp != null && noParentId) {
				nameShow = nameShow + ": " + (geneTemp.getGeneName() == null ? "" : geneTemp.getGeneName());
				itemDTO.setNameShow(nameShow);
			}
			return itemDTO;
		}
		return itemDTO;
	}
	
	/**
	 * 通过orderNo查询
	 * @author Zhang Yong
	 * add date 2011-11-01
	 * @param orderNo
	 * @return
	 */
	public List<OrderDsPlatesDTO> getDsPlateByOrderNo (Map<String, OrderItemDTO> itemMap, String orderNo) {
		List<OrderDsPlatesDTO> dsPlateDTOList = new ArrayList<OrderDsPlatesDTO>();
		if (itemMap == null || itemMap.isEmpty()) {
			return dsPlateDTOList;
		}
		Map<String, String> dsPlateMap = new HashMap<String, String>();
		Iterator<Entry<String, OrderItemDTO>> iterator = itemMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, OrderItemDTO> entry = iterator.next();
			OrderItemDTO dto = entry.getValue();
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
				OrderDsPlatesDTO dsPlate = new OrderDsPlatesDTO();
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
	 * 获得DNA Sequencing Map
	 * @author Zhang Yong
	 * add date 2011-11-07
	 * @param itemMap
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Map<String, OrderItemDTO>> getDNASeqItemByType (Map<String, OrderItemDTO> itemMap) {
		Map<String, Map<String, OrderItemDTO>> resultTemMap = new HashMap<String, Map<String, OrderItemDTO>>();
		if (itemMap == null || itemMap.isEmpty()) {
			return resultTemMap;
		}
        Map<String, OrderItemDTO> tubeSeqMap = new LinkedHashMap<String, OrderItemDTO>();
        Map<String, OrderItemDTO> plateSeqMap = new LinkedHashMap<String, OrderItemDTO>();
		ArrayList<String[]> resultTubeTempList = new ArrayList<String[]>();
		ArrayList<String[]> resultPlateTempList = new ArrayList<String[]>();
        for (String key : itemMap.keySet()) {
            OrderItemDTO dto = itemMap.get(key);
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
	 * 校验MutationLibraries数据是否正确
	 * @author Zhang Yong
	 * add date 2011-11-18
	 * @param mutaLib
	 * @return
	 */
	public Map<String, String> checkMutaLib (OrderMutationLibrariesDTO mutaLib) {
		Map<String, String> rtnMap = new HashMap<String, String>();
		String message = "message";
		if (mutaLib == null || StringUtils.isBlank(mutaLib.getConstructName())) {
			rtnMap.put(message, "Please enter the Construct name in 'More Detail','Mutation Libraries' tab");
			return rtnMap;
		}
		if (StringUtils.isBlank(mutaLib.getInterestSequence())) {
			rtnMap.put(message, "Please enter the Sequence in 'More Detail','Mutation Libraries' tab");
			return rtnMap;
		}
		if (Y.equals(mutaLib.getTmplFlag())&& StringUtils.isBlank(mutaLib.getTmplVector())) {
			rtnMap.put(message, "Please enter the Insert name in 'More Detail','Template Info' tab");
			return rtnMap;
		}
		return rtnMap;
	}
	
	/**
	 * 将新增的item填充到session中service内容为空的item，不为空则在session中新增一条记录
	 * @author Zhang Yong
	 * add date 2011-11-24
	 * @param itemId
	 * @param orderItemDTO
	 * @param sessOrderNo
	 * @param itemMap
	 */
	public String insertSpecialService (String itemId, OrderItemDTO orderItemDTO, String sessOrderNo, Map<String, OrderItemDTO> itemMap) {
		String blankItemKey = null;
		OrderItemDTO blankItemDTO = null;
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			OrderItemDTO tmpItemDTO = entry.getValue();
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
			itemMap.put(blankItemKey, orderItemDTO);
		} else {
			Integer itemNo = itemMap.size() + 1;
			orderItemDTO.setItemNo(itemNo);
			itemMap.put(itemId, orderItemDTO);
		}
		SessionUtil.updateRow(SessionConstant.OrderItemList.value(), sessOrderNo, itemMap);
		return itemId;
	}
	
	/**
	 * 批量Oligo（包含Manufacturing模块直接批量Oligo）
	 * @author Zhang Yong
	 * add date 2011-11-24
	 * @param itemId
	 * @param sequence
	 * @param batchCustNo
	 * @param batchBackbone
	 * @param batchSynthesisScales
	 * @param batchPurification
	 * @param batchModification5
	 * @param batchModification3
	 * @param batchAliquotingInto
	 * @param batchAliquotingSize
	 * @param excelUrl
	 * @param upload
	 * @param uploadFileName
	 * @param retMap
	 * @param sessOrderNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> batchOligo (String itemId, String sequence, String batchCustNo, 
			String batchBackbone, String batchSynthesisScales, String batchPurification, String batchModification5,
			String batchModification3, String batchAliquotingInto, String batchAliquotingSize, String excelUrl, 
			List<File> upload, List<String> uploadFileName, 
			Map<String, OrderItemDTO> retMap, String sessOrderNo) throws Exception {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		String rtnMessage = null;
		@SuppressWarnings("unchecked")
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (itemMap == null) {
			rtnMessage = "Batch oligo failure, can not find source item, Please check the session";
			rtnMap.put("rtnMessage", rtnMessage);
			return rtnMap;
		}
		OrderItemDTO orderItemDTO = itemMap.get(itemId);
		String catalogNo = orderItemDTO.getCatalogNo();
		if (StringUtils.isBlank(catalogNo)) {
			rtnMessage = "Batch oligo failure, source item catalogNo can not be null, Please check the session";
			rtnMap.put("rtnMessage", rtnMessage);
			return rtnMap;
		}
		//Manufacturing不跳转到order页面，直接批量Oligo
		//String excelUrl = "https://www.genscript.com//app_data/scratch/olides-14928/oligo_des.xls";
		if (StringUtil.isNumeric(batchCustNo) && StringUtils.isNotBlank(excelUrl)) {
			if (upload == null) {
				upload = new ArrayList<File>();
			}
			if (uploadFileName == null) {
				uploadFileName = new ArrayList<String>();
			}
			File oligoFile = createFile(excelUrl);
			upload.add(oligoFile);
			uploadFileName.add(oligoFile.getName());
		//在order页面批量Oligo	
		} else {
			if (StringUtils.isEmpty(sequence) && (upload == null || upload.isEmpty())) {
				rtnMessage = "Batch oligo failure, Please check the session";
				rtnMap.put("rtnMessage", rtnMessage);
				return rtnMap;
			}
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
						//内容格式为 name sequence...少于两列的数据直接跳过
						if (strList.size() < 2) {
							continue;
						}
						//如果使用工具生成的Excel文件，读取后第一行是标题，第一行跳过
						if (("Name").equalsIgnoreCase(strList.get(0)) || strList.get(1).startsWith("Sequence")) {
							continue;
						}
						String oligoName = strList.get(0);
						String seq = strList.get(1);
						seq = seq.toUpperCase();
						if (!checkSequence(batchBackbone, seq)) {
							rtnMessage = "Batch oligo failure, Please upload The correct Oligo Sequence.";
							rtnMap.put("rtnMessage", rtnMessage);
							//更新Map中信息
							retMap = updateItemMapInfo(retMap, itemMap, sessOrderNo);
							return rtnMap;
						}
						if (!StringUtils.isEmpty(batchModification5) && !batchModification5.equals("null")) {
							seq = batchModification5 + seq;
						}
						if (!StringUtils.isEmpty(batchModification3) && !batchModification3.equals("null")) {
							seq += batchModification3;
						}
						newMultipleOligoSessionSequence(retMap, sessOrderNo, 
								oligoName, seq, batchBackbone, batchSynthesisScales, 
								batchPurification, batchAliquotingInto, batchAliquotingSize, 
								itemId, orderItemDTO, itemMap);
					}
				} else {
					rtnMessage = "Batch oligo failure, The upload file type must be Excel type.";
					rtnMap.put("rtnMessage", rtnMessage);
					return rtnMap;
				}
			}
			if (retMap != null && !retMap.isEmpty()) {
				SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
				SessionUtil.insertRow(SessionConstant.OtherOligoList.value(), sessOrderNo, retMap);
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
							retMap = updateItemMapInfo(retMap, itemMap, sessOrderNo);
							return rtnMap;
						}
						if (!StringUtils.isEmpty(batchModification5) && !batchModification5.equals("null")) {
							sbf.append(batchModification5);
						}
						if (!StringUtils.isEmpty(batchModification3) && !batchModification3.equals("null")) {
							sbf.append(batchModification3);
						}
						newMultipleOligoSessionSequence(retMap, sessOrderNo, 
								oligoName.replaceAll(" ", ""), sbf.toString(), batchBackbone, 
								batchSynthesisScales, batchPurification, batchAliquotingInto, 
								batchAliquotingSize, itemId, orderItemDTO, itemMap);
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
							retMap = updateItemMapInfo(retMap, itemMap, sessOrderNo);
							return rtnMap;
						}
						if (!StringUtils.isEmpty(batchModification5) && !batchModification5.equals("null")) {
							sbf = new StringBuffer().append(batchModification5).append(sbf.toString());
						}
						if (!StringUtils.isEmpty(batchModification3) && !batchModification3.equals("null")) {
							sbf.append(batchModification3);
						}
						newMultipleOligoSessionSequence(retMap, sessOrderNo, 
								oligoName.replaceAll(" ", ""), sbf.toString(), batchBackbone, 
								batchSynthesisScales, batchPurification, batchAliquotingInto,
								batchAliquotingSize, itemId, orderItemDTO, itemMap);
						oligoName = str.substring(str.lastIndexOf(">") + 1, str.length());
					}
				}
			}
			if (retMap != null && !retMap.isEmpty()) {
				SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
				SessionUtil.insertRow(SessionConstant.OtherOligoList.value(), sessOrderNo, retMap);
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
	 * @param sessOrderNo
	 * @return
	 */
	@SuppressWarnings("unused")
	private Map<String, OrderItemDTO> updateItemMapInfo (Map<String, OrderItemDTO> retMap,
			Map<String, OrderItemDTO> itemMap, String sessOrderNo) {
		if (retMap != null && !retMap.isEmpty()) {
			for (String key : retMap.keySet()) {
				itemMap.remove(key);
				if (itemMap == null) {
					itemMap = new HashMap<String, OrderItemDTO>();
				}
			}
			retMap = new HashMap<String, OrderItemDTO>();
		} else if ((retMap == null || retMap.isEmpty()) && !itemMap.isEmpty() && itemMap.size() == 1) {
			for (String key : itemMap.keySet()) {
				OrderItemDTO sourceItem = itemMap.get(key);
				if (sourceItem != null) {
					sourceItem.setOligo(new OrderOligoDTO());
				}
			}
		}
		SessionUtil.updateRow(SessionConstant.OrderItemList.value(), sessOrderNo, itemMap);
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
	 * @param sessOrderNo
	 * @param oligoName
	 * @param seq
	 * @param batchBackbone
	 * @param batchSynthesisScales
	 * @param batchPurification
	 * @param batchAliquotingInto
	 * @param batchAliquotingSize
	 * @param srcItemId
	 * @param srcOrderItemDTO
	 * @param itemMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	private void newMultipleOligoSessionSequence(
			Map<String, OrderItemDTO> retMap, String sessOrderNo, String oligoName, String seq,
			String batchBackbone,String batchSynthesisScales, 
			String batchPurification, String batchAliquotingInto, String batchAliquotingSize,
			String srcItemId, OrderItemDTO srcOrderItemDTO, Map<String, OrderItemDTO> itemMap) 
		throws IllegalAccessException, InvocationTargetException, Exception {
		if (retMap == null) {
			retMap = new HashMap<String, OrderItemDTO>();
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
		OrderItemDTO orderItemDTO = null;
		String tmpId = null;
		if (srcOrderItemDTO.getOligo() == null || StringUtils.isBlank(srcOrderItemDTO.getOligo().getOligoName())) {
			orderItemDTO = srcOrderItemDTO;
			tmpId = srcItemId;
		} else {
			tmpId = SessionUtil.generateTempId();
			orderItemDTO = new OrderItemDTO();
			ModelUtils.mergerModel(srcOrderItemDTO, orderItemDTO);
			orderItemDTO.setItemNo(itemMap.size() + 1);
			orderItemDTO.setOrderItemId(null);
		}
		orderItemDTO.setNameShow(orderItemDTO.getName() + ": " + oligoName);
		orderItemDTO.setOligo(oligoDTO);
		// 计算Oligo Item的价格
		quoteOrderService.getOligoPriceAndCost(sessOrderNo, orderItemDTO);
		boolean isInternalOrder = this.isInternalOrder(sessOrderNo);
		if (isInternalOrder) {
			this.updatePriceByInternal(orderItemDTO, sessOrderNo);
		}
		// add by zhanghuibin
		orderItemDTO.setUpdateFlag(Y);
		if (!tmpId.equals(srcItemId)) {
			retMap.put(tmpId, orderItemDTO);
		}
		itemMap.put(tmpId, orderItemDTO);
	}
	
	/**
	 * 计算service价格
	 * @param itemDTO
	 * @return
	 */
//	private ServiceItemPiceDTO getCostPrice(OrderItemDTO orderItem) {
//		ServiceItemPiceDTO orderServiceItemPiceDTO = new ServiceItemPiceDTO();
//		orderServiceItemPiceDTO.setServiceClsId(orderItem.getClsId());
//		orderServiceItemPiceDTO.setCatalogId(orderItem.getCatalogId());
//		orderServiceItemPiceDTO.setCatalogNo(orderItem.getCatalogNo());
//		orderServiceItemPiceDTO.setQuantity(orderItem.getQuantity());
//		return orderServiceItemPiceDTO;
//	}
	
	/**
	 * 从OrderMoreAction中拷贝了此方法，有修改
	 * @author Zhang Yong
	 * add date 2011-11-23
	 * @param sessOrderNo
	 * @param orderItem
	 * @param orderServiceItemPiceDTO
	 */
	public void returnOrderServiceItemPrice(String sessOrderNo, OrderItemDTO orderItem, ServiceItemPiceDTO orderServiceItemPiceDTO) {
		orderItem.setUpdateFlag(Y);
		BigDecimal zero = new BigDecimal(0);
		OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		String currency = "";
		if (orderMain != null && StringUtils.isNotBlank(orderMain.getOrderCurrency())) {
			currency = orderMain.getOrderCurrency();
		} else {
			currency = CurrencyType.USD.value();
		}
		ServiceItemPiceDTO newOrderServiceItemPiceDTO = null;
		if (ServicePriceType.peptide.name().equals(orderServiceItemPiceDTO.getServicePriceType())) {
			boolean needGetDifcult = false;
			if (1 == orderItem.getClsId().intValue()) {
				if (StringUtils.isBlank(orderItem.getParentId())) {
					needGetDifcult = true;
				} else {
					@SuppressWarnings("unchecked")
					Map<String, OrderItemDTO> sessItemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
					if (1 == sessItemMap.get(orderItem.getParentId()).getClsId().intValue()) {
						needGetDifcult = true;
					}
				}
			}
			newOrderServiceItemPiceDTO = priceService.getPeptidePrice(orderServiceItemPiceDTO, needGetDifcult, currency);
		} else {
			newOrderServiceItemPiceDTO = publicService.calculateServicePrice(orderServiceItemPiceDTO);
		}
		OrderGeneSynthesisDTO geneSynthesisDTO = orderServiceItemPiceDTO.getGeneSynthesisDTO();
		if (newOrderServiceItemPiceDTO.getTBDFlag() != null && newOrderServiceItemPiceDTO.getTBDFlag().equals("1")) {
			if (geneSynthesisDTO != null && orderServiceItemPiceDTO.getServiceClsId() == 3 && geneSynthesisDTO.getBpPrice() != null) {
				orderItem.setTbdFlag(tbd_0);
			} else {
				orderItem.setTbdFlag(newOrderServiceItemPiceDTO.getTBDFlag());
				orderItem.setCost(zero);
				orderItem.setUnitPrice(zero);
				orderItem.setAmount(zero);
				orderItem.setBasePrice(zero);
				orderServiceItemPiceDTO.setPrice(zero);
				orderServiceItemPiceDTO.setCost(zero);
				return;
			}
		} else {
			orderItem.setTbdFlag(tbd_0);
		}
		orderItem.setCost(newOrderServiceItemPiceDTO.getCost());
		Double rate = publicService.getRateByBaseCurrencyToCurrency(orderMain.getOrderCurrency(), new Date());
		if (rate == null) {
			orderItem.setUnitPrice(zero);
			orderItem.setAmount(zero);
			orderItem.setBasePrice(zero);
			throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
		}
		if (!isInternalOrder(sessOrderNo)) {
			orderItem.setUnitPrice(newOrderServiceItemPiceDTO.getPrice().multiply(new BigDecimal(rate)));
			orderItem.setAmount(newOrderServiceItemPiceDTO.getPrice().multiply(new BigDecimal(newOrderServiceItemPiceDTO.getQuantity())).multiply(new BigDecimal(rate)));
			BigDecimal basePrice = newOrderServiceItemPiceDTO.getPrice();
			orderItem.setBasePrice(basePrice);
		} else {
			updatePriceByInternal(orderItem, sessOrderNo);
		}
		orderServiceItemPiceDTO.setPrice(newOrderServiceItemPiceDTO.getPrice());
		orderServiceItemPiceDTO.setCost(newOrderServiceItemPiceDTO.getCost());
	}
	
	/**
	 * 判断customer的Type是否为Internal,为True则计算后的价格除cost外都为0
	 * @author Zhang Yong
	 * add date 2011-11-23
	 * @param sessOrderNo
	 * @return
	 */
	public boolean isInternalOrder (String sessOrderNo) {
		boolean isInternal = false;
		if (StringUtils.isBlank(sessOrderNo)) {
			return isInternal;
		}
		OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		if (orderMain != null && orderMain.getCustNo() != null) {
			Customer customer = customerDao.getById(orderMain.getCustNo());
			if (Constants.INTERNAL_TYPE_CUSTOMER.equalsIgnoreCase(customer.getCustType())) {
				isInternal = true;
			}
		}
		return isInternal;
	}
	
	/**
	 * 更新价格为cost
	 * @author Zhang Yong
	 * add date 2011-11-23
	 * @param orderItemDTO
	 * @param sessOrderNo
	 */
	public void updatePriceByInternal (OrderItemDTO orderItem, String sessOrderNo) {
		OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		BigDecimal zero = new BigDecimal(0);
		Double rate = publicService.getRateByBaseCurrencyToCurrency(orderMain.getOrderCurrency(), new Date());
		if (rate == null) {
			orderItem.setUnitPrice(zero);
			orderItem.setAmount(zero);
			orderItem.setBasePrice(zero);
			throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
		}
		orderItem.setUnitPrice(orderItem.getCost().multiply(new BigDecimal(rate)));
		orderItem.setAmount(orderItem.getCost().multiply(new BigDecimal(orderItem.getQuantity())).multiply(new BigDecimal(rate)));
		orderItem.setBasePrice(orderItem.getCost());
		orderItem.setUpdateFlag(Y);
	}
	
	/**
	 * 更新OrderItemDTO价格
	 * @author Zhang Yong
	 * add date 2011-11-28
	 * @param orderItem
	 * @param orderServiceItemPiceDTO
	 * @param sessOrderNo
	 */
	public void updateOrderItemPrice (OrderItemDTO orderItem, ServiceItemPiceDTO orderServiceItemPiceDTO, String sessOrderNo) {
		OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		BigDecimal zero = new BigDecimal(0);
		orderItem.setCost(orderServiceItemPiceDTO.getCost());
		Double rate = publicService.getRateByBaseCurrencyToCurrency(orderMain.getOrderCurrency(), new Date());
		if (rate == null) {
			orderItem.setUnitPrice(zero);
			orderItem.setAmount(zero);
			orderItem.setBasePrice(zero);
			throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
		}
		orderItem.setUnitPrice(orderServiceItemPiceDTO.getPrice().multiply(new BigDecimal(rate)));
		orderItem.setAmount(orderServiceItemPiceDTO.getPrice().multiply(new BigDecimal(orderItem.getQuantity())).multiply(new BigDecimal(rate)));
		orderItem.setBasePrice(orderServiceItemPiceDTO.getPrice());
		orderItem.setUpdateFlag(Y);
	}
	
	private File createFile(String excelUrl) throws Exception{
		HttpURLConnection con = null;
		HttpsURLConnection connection = null;
		URL url = new URL(excelUrl);
		String sessionid = Struts2Util.getRequest().getSession().getId();
		if (excelUrl.startsWith("https://")) {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext
					.getInstance("SSL", "SunJSSE");

			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			HostnameVerifier hv = new HostnameVerifier()

			{

				public boolean verify(String urlHostName, SSLSession session)

				{

					System.out.println("Warning: URL Host: " + urlHostName
							+ " vs. " + session.getPeerHost());

					return true;

				}

			};

			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setSSLSocketFactory(ssf);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty(" Content-Type ",
					" application/x-www-form-urlencoded ");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Cookie", "JSESSIONID="
					+ sessionid);
			connection.connect();
		} else {
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setUseCaches(false);

			con.setInstanceFollowRedirects(true);
			con.setRequestProperty(" Content-Type ",
					" application/x-www-form-urlencoded ");
			con.setRequestProperty("charset", "utf-8");
			con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
			con.connect();
		}

		@SuppressWarnings("unused")
		int size = 0;
		byte[] buf = new byte[1024];
		BufferedInputStream bis = null;
		if (con != null) {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					con.getOutputStream(), "utf-8"));
			out.flush();
			out.close();
			bis = new BufferedInputStream(con.getInputStream());
		} else {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					connection.getOutputStream(), "utf-8"));
			out.flush();
			out.close();
			bis = new BufferedInputStream(connection.getInputStream());
		}
		FileOutputStream writer = null;
		String tempHtmlFileName = PdfUtils.getTempFileShortName() + ".xls";
		File file = new File(tempHtmlFileName);
		if (file.isFile() && !file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				System.out.println("create parent directory fail");
			}
		}
		writer = new FileOutputStream(file);
		while ((size = bis.read(buf)) != -1) {
			writer.write(buf);
		}
		writer.flush();
		writer.close();
		return file;
	}
	
	@Transactional(readOnly = true)
	public Date getOrderFirstConfirmDate(Integer orderNo) {
		return orderProcessLogDao.getOrderFirstConfirmDate(orderNo);
	}
	
	/**
	 * 获取前一个Item的ItemId
	 * @author Zhang Yong
	 * @param sessItemMap
	 * @param parentId
	 * @param newItem
	 * @return
	 */
	public String getPreParentServiceId(Map<String, OrderItemDTO> sessItemMap, OrderItemDTO newItem) {
		String preParentId = newItem.getParentId();
		if (preParentId == null || preParentId == null) {
			return preParentId;
		}
		OrderItemDTO parentDTO = sessItemMap.get(preParentId);
		if (parentDTO == null || parentDTO.getClsId() == null) {
			return preParentId;
		}
		Map<String, OrderItemDTO> childrenMap = getAllFirstStepChildrenService(sessItemMap, preParentId);
		// 主服务下无子服务的新加的子服务直接放在主服务后面
		if (childrenMap.isEmpty()) {
			return preParentId;
		}
		Integer parentClsId = parentDTO.getClsId();
		// 主服务为clsId=4(Gene),clsId=9(Custom Clone)
		if (3 == parentClsId.intValue() || 9 == parentClsId.intValue()) {
			// 新添加的 子服务为Plasmid Preparation，需放在Custom Cloning后面，Mutagenesis前面。
			if (10 == newItem.getClsId()) {
				Iterator<Entry<String, OrderItemDTO>> childIt = childrenMap.entrySet().iterator();
				while (childIt.hasNext()) {
					Entry<String, OrderItemDTO> entry = childIt.next();
					OrderItemDTO tmpItemDTO = entry.getValue();
					if (9 == tmpItemDTO.getClsId().intValue()) {
						preParentId = entry.getKey();
						return preParentId;
					}
				}
			} else if (4 == newItem.getClsId()) {
				Map<String, OrderItemDTO> childrenServiceMap = new LinkedHashMap<String, OrderItemDTO>();
				//取父级服务下的所有子服务
				getAllStepChildrenService(sessItemMap, childrenServiceMap, preParentId);
				String lastItemId = null;
				int lastItenNo = 0;
				Iterator<Entry<String, OrderItemDTO>> childIt = childrenServiceMap.entrySet().iterator();
				while (childIt.hasNext()) {
					Entry<String, OrderItemDTO> entry = childIt.next();
					OrderItemDTO tmpItemDTO = entry.getValue();
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
			Iterator<Entry<String, OrderItemDTO>> childIt = childrenMap
					.entrySet().iterator();
			while (childIt.hasNext()) {
				Entry<String, OrderItemDTO> entry = childIt.next();
				OrderItemDTO tmpItemDTO = entry.getValue();
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
				Iterator<Entry<String, OrderItemDTO>> childIt = childrenMap
						.entrySet().iterator();
				while (childIt.hasNext()) {
					Entry<String, OrderItemDTO> entry = childIt.next();
					OrderItemDTO tmpItemDTO = entry.getValue();
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
	private Map<String, OrderItemDTO> getAllFirstStepChildrenService(Map<String, OrderItemDTO> itemMap, 
			String itemId) {
		Map<String, OrderItemDTO> childrenServiceMap = new LinkedHashMap<String, OrderItemDTO>();
		if (itemMap == null || itemMap.isEmpty()) {
			return childrenServiceMap;
		}
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		// 获取主服务的所有第一级子服务
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			OrderItemDTO tmpItemDTO = entry.getValue();
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
	private void getAllStepChildrenService(Map<String, OrderItemDTO> itemMap, 
			Map<String, OrderItemDTO> childrenServiceMap, String itemId) {
		if (childrenServiceMap == null) {
			childrenServiceMap = new LinkedHashMap<String, OrderItemDTO>();
		}
		if (StringUtils.isBlank(itemId) || itemMap == null || itemMap.isEmpty()) {
			return;
		}
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
		// 获取主服务的所有第一级子服务
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			OrderItemDTO tmpItemDTO = entry.getValue();
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
	
	/**
	 * 通过order的orderNo查找所有的item的id
	 */
	public List<Integer> getItemIdsByOrderNo(Integer orderNo) {
		return this.orderItemDao.getItemIdsByOrderNo(orderNo);
	}
}
