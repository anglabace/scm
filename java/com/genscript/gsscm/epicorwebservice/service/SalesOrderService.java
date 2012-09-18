package com.genscript.gsscm.epicorwebservice.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.genscript.gsscm.order.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderErpMappingDao;
import com.genscript.gsscm.order.dao.OrderLineErpMappingDao;
import com.genscript.gsscm.order.dao.OrderNoteDao;
import com.genscript.gsscm.order.dao.OrderPackageDao;
import com.genscript.gsscm.order.dao.OrderProcessLogDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderErpMapping;
import com.genscript.gsscm.order.entity.OrderLineErpMapping;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderNote;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryDao;

@Service
@Transactional
public class SalesOrderService {

	@Autowired
	private OrderNoteDao orderNoteDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private OrderErpMappingDao orderErpMappingDao;
	@Autowired
	private OrderPackageDao orderPackageDao;
	@Autowired
	private PbCountryDao pbCountryDao;
	@Autowired
	private OrderLineErpMappingDao orderLineErpMappingDao;
	@Autowired
	private ShipMethodDao shipMethodDao;
	@Autowired
	private BillTerritoryDao billTerritoryDao;
	@Autowired
	private OrderProcessLogDao orderProcessLogDao;
	@Autowired
    private DozerBeanMapper dozer;
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
    @Autowired
    private OrderService orderService;
	
	public static void main(String[] args) {
		//createXMLFile("D:\\teacher.xml");
	 } 
	
	/**
	  * 
	  *   @Description 创建xml文件
	  *   @param filename
	  *   @param rootElement
	  *   void
	  *   @throws  抛出异常说明
	  */
	 public void createXMLFile(String filename, OrderMain order,
				Map<String, OrderItemDTO> itemMap, String poNumber, boolean cancel) {

	  /** 建立document对象 */
	  Document document = DocumentHelper.createDocument();
	  Element rootElement = document.addElement("SalesOrderDataSet");
	  Element orderHedElement = rootElement.addElement("OrderHed");
//	  Integer companyId = order.getGsCoId();
	  String company = "GSUS";
//	  if(companyId == 1){
//		  company = "GSUS";
//	  }else if(companyId == 2){
//		  company = "GSJR";
//	  }else if(companyId == 3){
//		  company = "GSJK";
//	  }else if(companyId == 4){
//		  company = "GSHK";
//	  }
	  Integer shipAddrToId = order.getShiptoAddrId();
	  Integer billAddrToId = order.getBilltoAddrId();
	  OrderAddress shipToAddr = orderAddressDao.findByAddrId(shipAddrToId);
	  OrderAddress billToAddr = orderAddressDao.findByAddrId(billAddrToId);
	  Customer customer = customerDao.findByCustNo(order.getCustNo());
	  CustomerDTO customerDTO = dozer.map(customer, CustomerDTO.class);
	  String accountCode = billTerritoryDao.getAccountCode(billToAddr.getCountry(), billToAddr.getState(), billToAddr.getZipCode());
	  if("HK".equalsIgnoreCase(accountCode)){
		  company = "GSHK";
		}
	  Element companyElement = orderHedElement.addElement("Company");
	  companyElement.addText(company);
	  
	  String orderNum = "0";
	  Element orderNumElement = orderHedElement.addElement("OrderNum");
	  List<OrderErpMapping> orderErpMappingList = orderErpMappingDao.findBy("orderNo", order.getOrderNo());
	  String usPo,njSo; 
	  if(orderErpMappingList != null && orderErpMappingList.size() > 0){
		  String soStr = orderErpMappingList.get(0).getErpUsSo();
		  orderNum = soStr;
		  orderNumElement.addText(orderNum);
		  StringBuilder poSb = new StringBuilder();
		  StringBuilder soSb = new StringBuilder();
		  for(OrderErpMapping orderErpMapping : orderErpMappingList){
			  poSb.append(orderErpMapping.getErpUsPo());
			  soSb.append(orderErpMapping.getErpNjSo());
		  }
		  usPo = poSb.substring(0, poSb.length()-1);
		  njSo = soSb.substring(0, soSb.length()-1);
	  }else{
		  orderNum = "0";
		  orderNumElement.addText(orderNum);
		  usPo = null;
		  njSo = null;
	  }
	  
	  String temsCode = customer.getPrefPaymentTerm();
	  
	  Integer custNum = erpSalesOrderService.createCustomer(customerDTO, order.getCustNo()+"", company);
	  Element custNumElement = orderHedElement.addElement("CustNum");
	  custNumElement.addText(custNum.toString());
	  Element customerCustIDElement = orderHedElement.addElement("CustomerCustID");
	  customerCustIDElement.addText(order.getCustNo().toString());
	  Element pONumElement = orderHedElement.addElement("PONum");
	  if (poNumber != null) {
		  pONumElement.addText(poNumber);
	  }
	  Integer shipViaCodeId = null;
	  if(itemMap != null){
		  Iterator<Entry<String, OrderItemDTO>> sit = itemMap.entrySet()
			.iterator();
		  while (sit.hasNext()) {
			  Entry<String, OrderItemDTO> entry = sit.next();
			  OrderItemDTO orderItemDTO = entry.getValue();
			  if(orderItemDTO != null && orderItemDTO.getShipMethod() != null){
				  shipViaCodeId = orderItemDTO.getShipMethod();
				  break;
			  }
		  }
	  }
	  Element shipViaCodeElement = orderHedElement.addElement("ShipViaCode");
	  if(shipViaCodeId != null){
		  String shipViaCode = shipMethodDao.get(shipViaCodeId).getMethodCode();
		  shipViaCodeElement.addText(shipViaCode);
	  }
	  //shipViaCodeElement.addText("FPO");
	  Element termsCodeElement = orderHedElement.addElement("TermsCode");
	  if(StringUtils.isNotEmpty(temsCode)){
		  termsCodeElement.addText(temsCode);
	  }
	  //Element needByDateElement = orderHedElement.addElement("NeedByDate");
	  //needByDateElement.addText("0");
	  //Element shipToCustIdElement = orderHedElement.addElement("ShipToCustId");
	  //shipToCustIdElement.addText("0");
	  
	  
	  String oTSContact = AddressUtil.getFullName(shipToAddr.getFirstName(), shipToAddr.getMidName(), shipToAddr.getLastName());
	  String billStr = AddressUtil.getFullAddress(billToAddr.getAddrLine1(), billToAddr.getAddrLine2(), billToAddr.getAddrLine3(), billToAddr.getCity(), billToAddr.getState(), billToAddr.getZipCode(), billToAddr.getCountry(), billToAddr.getOrgName());
	  
	  Element useOTSElement = orderHedElement.addElement("UseOTS");
	  useOTSElement.addText("True");
	  Element oTSContactElement = orderHedElement.addElement("OTSContact");
	  oTSContactElement.addText(oTSContact);
	  Element oTSNameElement = orderHedElement.addElement("OTSName");
	  if(StringUtils.isBlank(shipToAddr.getOrgName())){
		  String custName =  AddressUtil.getFullName(customerDTO.getFirstName(), customerDTO.getMidName(), customerDTO.getLastName());
		  oTSNameElement.addText(custName);
	  }else{
		  oTSNameElement.addText(StringUtil.subStringByLength(shipToAddr.getOrgName(), 50)); 
	  }
	  Element oTSAddress1Element = orderHedElement.addElement("OTSAddress1");
	  oTSAddress1Element.addText(StringUtil.subStringByLength(shipToAddr.getAddrLine1(), 50));
	  Element oTSAddress2Element = orderHedElement.addElement("OTSAddress2");
	  if(StringUtils.isNotEmpty(shipToAddr.getAddrLine2())){
		  oTSAddress2Element.addText(StringUtil.subStringByLength(shipToAddr.getAddrLine2(), 50));
	  }
	  Element oTSAddress3Element = orderHedElement.addElement("OTSAddress3");
	  if(StringUtils.isNotEmpty(shipToAddr.getAddrLine3())){
		  oTSAddress3Element.addText(StringUtil.subStringByLength(shipToAddr.getAddrLine3(), 50));
	  }
	  Element oTSZipCodeElement = orderHedElement.addElement("OTSZIP");
	  if(StringUtils.isNotEmpty(shipToAddr.getZipCode())){
		  oTSZipCodeElement.addText(shipToAddr.getZipCode());
	  }
	  Element oTSCityElement = orderHedElement.addElement("OTSCity");
	  oTSCityElement.addText(shipToAddr.getCity());
	  Element OTSStateElement = orderHedElement.addElement("OTSState");
	  OTSStateElement.addText(shipToAddr.getState());
	  Element oTSCountryNumElement = orderHedElement.addElement("OTSCountryNum");
	  if(StringUtils.isNotEmpty(shipToAddr.getCountry())){
		  PbCountry country = pbCountryDao.findUniqueBy("countryCode", shipToAddr.getCountry());
		  if(country != null){
			  oTSCountryNumElement.addText(country.getCountryId().toString());
		  }
	  }
	  Element oTSPhoneNumElement = orderHedElement.addElement("OTSPhoneNum");
	  oTSPhoneNumElement.addText(shipToAddr.getBusPhone());
	  Element oTSFaxNumElement = orderHedElement.addElement("OTSFaxNum");
	  if(StringUtils.isNotEmpty(shipToAddr.getFax())){
		  oTSFaxNumElement.addText(shipToAddr.getFax());
	  }
	  
	  Element orderCommentElement = orderHedElement.addElement("OrderComment");
	  if(StringUtils.isNotEmpty(order.getOrderMemo())){
		  orderCommentElement.addText(order.getOrderMemo());
	  }
	  Element shipCommentElement = orderHedElement.addElement("ShipComment");
	  List<OrderNote> shpList = orderNoteDao.getOrderNoteList(order
				.getOrderNo(), OrderInstructionType.SHIPMENT);
		if (shpList != null && !shpList.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (OrderNote orderNote : shpList) {
				sb.append(orderNote.getDescription());
			}
			//updateOrderHed.setShipComment(sb.toString());
			shipCommentElement.addText(sb.toString());
		}
	  
	  Element invoiceCommentElement = orderHedElement.addElement("InvoiceComment");
	  List<OrderNote> accountList = orderNoteDao.getOrderNoteList(order
				.getOrderNo(), OrderInstructionType.ACCOUNTING);
		if (accountList != null && !accountList.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (OrderNote orderNote : accountList) {
				sb.append(orderNote.getDescription());
			}
			//updateOrderHed.setInvoiceComment(sb.toString());
			invoiceCommentElement.addText(sb.toString());
		}
	  
	  Element checkBox01Element = orderHedElement.addElement("CheckBox01");
	  checkBox01Element.addText("true");
	  Element checkBox02Element = orderHedElement.addElement("CheckBox02");
	  checkBox02Element.addText("true");
	  Element currencyCodeElement = orderHedElement.addElement("CurrencyCode");
	  currencyCodeElement.addText("USD");
	  Element shortChar01Element = orderHedElement.addElement("ShortChar01");
	  shortChar01Element.addText("US");
	  Element character01Element = orderHedElement.addElement("Character01");
	  character01Element.addText(billStr);
	  Element number01Element = orderHedElement.addElement("Number01");
	  number01Element.addText(order.getOrderNo().toString());
	  
	  
	  Element uSPONumElement = orderHedElement.addElement("USPONum");
	  if(StringUtils.isNotEmpty(usPo)){
		  uSPONumElement.addText(usPo);
	  }
	  Element nJSONumElement = orderHedElement.addElement("NJSONum");
	  if(StringUtils.isNotEmpty(njSo)){
		  nJSONumElement.addText(njSo);
	  }
	  Element statusElement = orderHedElement.addElement("Status");
	  if(cancel){
		  statusElement.addText("Cancel");
	  }
	  int seqNum = 1;
	  List<OrderPackage> packageList = orderPackageDao.getOrderPackageList(order.getOrderNo());
	  if(packageList != null && packageList.size() > 0){
		  //for(OrderPackage orderPackage : packageList){
			  Element oHShipOrderMscElement = rootElement.addElement("OHOrderMsc");
			  Element oHShipCompanyElement = oHShipOrderMscElement.addElement("Company");
			  if(StringUtils.isNotEmpty(company)){
				  oHShipCompanyElement.addText(company);
			  }
			  Element oHShipOrderNumElement = oHShipOrderMscElement.addElement("OrderNum");
			  oHShipOrderNumElement.addText("0");
			  Element oHShipOrderLineElement = oHShipOrderMscElement.addElement("OrderLine");
			  oHShipOrderLineElement.addText("0");
			  Element oHShipSeqNumElement = oHShipOrderMscElement.addElement("SeqNum");
			  oHShipSeqNumElement.addText(seqNum+"");
			  seqNum++;
			  Element oHShipMiscCodeElement = oHShipOrderMscElement.addElement("MiscCode");
			  oHShipMiscCodeElement.addText("ShFe");
			  Element oHShipDescriptionElement = oHShipOrderMscElement.addElement("Description");
			  oHShipDescriptionElement.addText("Shipping Fee");
			  Element oHShipMiscAmtElement = oHShipOrderMscElement.addElement("MiscAmt");
			  oHShipMiscAmtElement.addText(order.getShipAmt().toString());
			  Element oHShipDocMiscAmtElement = oHShipOrderMscElement.addElement("DocMiscAmt");
			  oHShipDocMiscAmtElement.addText(order.getShipAmt().toString());
			  Element oHShipFreqCodeElement = oHShipOrderMscElement.addElement("FreqCode");
			  oHShipFreqCodeElement.addText("F");
			  Element oHShipTypeElement = oHShipOrderMscElement.addElement("Type");
			  oHShipTypeElement.addText("A");
		  //}  
	  }
	  
//	  OrderMain orderMain = orderDao.getById(order.getOrderNo());
//	  if(orderMain.getCouponId() != null && orderMain.getCouponValue() != null){
//		  Element oHCouponOrderMscElement = rootElement.addElement("OHOrderMsc");
//		  Element oHShipCompanyElement = oHCouponOrderMscElement.addElement("Company");
//		  if(StringUtils.isNotEmpty(company)){
//			  oHShipCompanyElement.addText(company);
//		  }
//		  Element oHShipOrderNumElement = oHCouponOrderMscElement.addElement("OrderNum");
//		  oHShipOrderNumElement.addText("0");
//		  Element oHShipOrderLineElement = oHCouponOrderMscElement.addElement("OrderLine");
//		  oHShipOrderLineElement.addText("0");
//		  Element oHShipSeqNumElement = oHCouponOrderMscElement.addElement("SeqNum");
//		  oHShipSeqNumElement.addText(seqNum+"");
//		  Element oHShipMiscCodeElement = oHCouponOrderMscElement.addElement("MiscCode");
//		  oHShipMiscCodeElement.addText("VIP");
//		  Element oHShipDescriptionElement = oHCouponOrderMscElement.addElement("Description");
//		  oHShipDescriptionElement.addText("VIP Coupon");
//		  Element oHShipMiscAmtElement = oHCouponOrderMscElement.addElement("MiscAmt");
//		  oHShipMiscAmtElement.addText("-"+orderMain.getCouponValue());
//		  Element oHShipDocMiscAmtElement = oHCouponOrderMscElement.addElement("DocMiscAmt");
//		  oHShipDocMiscAmtElement.addText("-"+orderMain.getCouponValue());
//		  Element oHShipFreqCodeElement = oHCouponOrderMscElement.addElement("FreqCode");
//		  oHShipFreqCodeElement.addText("E");
//		  Element oHShipTypeElement = oHCouponOrderMscElement.addElement("Type");
//		  oHShipTypeElement.addText("A");
//	  }
	  
//	  Element orderTypeElement = orderHedElement.addElement("OrderType");
//	  orderTypeElement.addText("US");
	  if(itemMap != null){
	  int i = 0;
	  OrderProcessLog lastConfirmLog = orderProcessLogDao
		.getOrderLastConfirm(order.getOrderNo());
	  
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			i++;
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
            orderService.setItemMoreDetailByClsId(orderItemDTO);
			if(lastConfirmLog == null && "CN".equals(orderItemDTO.getStatus())){
				continue;
			}
			if(StringUtils.isEmpty(orderItemDTO.getChangeStatus()) && "CN".equals(orderItemDTO.getStatus())){
				continue;
			}
			  Element orderDtlElement = rootElement.addElement("OrderDtl");
			  Element dtlCompanyElement = orderDtlElement.addElement("Company");
			  dtlCompanyElement.addText(company);
			  Element dtlOrderNumElement = orderDtlElement.addElement("OrderNum");
			  dtlOrderNumElement.addText(orderNum);
			  OrderLineErpMapping orderLineErpMapping = orderLineErpMappingDao.getErpItem(order.getOrderNo(), orderItemDTO.getItemNo());
			  Element dtlOrderLineElement = orderDtlElement.addElement("OrderLine");
			  Element dtlPOElement = orderDtlElement.addElement("PO");
			  Element dtlPOLineElement = orderDtlElement.addElement("POLine");
			  Element dtlNJSOElement = orderDtlElement.addElement("NJSO");
			  Element dtlNJSOLineElement = orderDtlElement.addElement("NJSOLine");
			  if(orderLineErpMapping != null){
				  if(orderLineErpMapping.getErpUsSoLine()!=null){
					  dtlOrderLineElement.addText(orderLineErpMapping.getErpUsSoLine());
				  }
				  if(orderLineErpMapping.getErpUsPo()!=null){
					  dtlPOElement.addText(orderLineErpMapping.getErpUsPo());
				  }
				  if(orderLineErpMapping.getErpUsPoLine()!=null){
					  dtlPOLineElement.addText(orderLineErpMapping.getErpUsPoLine());
				  }
				  if(orderLineErpMapping.getErpNjSo()!=null){
					  dtlNJSOElement.addText(orderLineErpMapping.getErpNjSo());
				  }
				  if(orderLineErpMapping.getErpNjSoLine()!=null){
					  dtlNJSOLineElement.addText(orderLineErpMapping.getErpNjSoLine());
				  }
				  
			  }else{
				  dtlOrderLineElement.addText("0");
			  }
			  Element dtlPartNumElement = orderDtlElement.addElement("PartNum");
			  String partStr = generatePartStr(orderItemDTO);
			  dtlPartNumElement.addText(partStr);
			  Element dtlLineDescElement = orderDtlElement.addElement("LineDesc");
//			  if(orderItemDTO.getGeneSynthesis() != null){
//				  dtlLineDescElement.addText(orderItemDTO.getGeneSynthesis().getGeneName());
//			  }else if(orderItemDTO.getCustCloning() != null){
//				  dtlLineDescElement.addText(orderItemDTO.getCustCloning().get);
//			  }
			  dtlLineDescElement.addText(orderItemDTO.getNameShow());
			  //dtlLineDescElement.addText(orderItemDTO.getName());
			  Element dtlDocUnitPriceElement = orderDtlElement.addElement("DocUnitPrice");
			  Element dtlSellingQuantityElement = orderDtlElement.addElement("SellingQuantity");
			  if("SC1010".equalsIgnoreCase(orderItemDTO.getCatalogNo())){
				  BigDecimal unitPrice = orderItemDTO.getUnitPrice();
				  OrderGeneSynthesisDTO dto = orderItemDTO.getGeneSynthesis();
				  int seqLength = StringUtil.calculateSeqLength(dto.getSequence());
				  BigDecimal perUnitPrice = unitPrice.divide(new BigDecimal(seqLength),5,BigDecimal.ROUND_HALF_UP);
				  dtlDocUnitPriceElement.addText(perUnitPrice.toString());
				  dtlSellingQuantityElement.addText(seqLength+"");
			  }else{
				  dtlDocUnitPriceElement.addText(orderItemDTO.getUnitPrice().toString());			  
				  dtlSellingQuantityElement.addText(orderItemDTO.getQuantity().toString());
			  }
			  Element dtlDocDiscountElement = orderDtlElement.addElement("DocDiscount");
			  dtlDocDiscountElement.addText(orderItemDTO.getDiscount().toString());
			  Element dtlNeedByDateElement = orderDtlElement.addElement("NeedByDate");
			  if (orderItemDTO.getTargetDate() != null) {
//					changePriceOrderDtl.setNeedByDate(DateUtils
//							.convertToXMLGregorianCalendar(orderItemDTO
//									.getTargetDate()));
					dtlNeedByDateElement.addText(DateUtils
							.convertToXMLGregorianCalendar(orderItemDTO
									.getTargetDate()).toString());
				}
			  Element dtlNumber01Element = orderDtlElement.addElement("Number01");
			  dtlNumber01Element.addText(orderItemDTO.getOrderNo().toString());
			  Element dtlNumber02Element = orderDtlElement.addElement("Number02");
			  dtlNumber02Element.addText(orderItemDTO.getItemNo().toString());
			  Element dtlNumber03Element = orderDtlElement.addElement("Number03");
			  dtlNumber03Element.addText(orderItemDTO.getCost().toString());
			  
			  Element dtlNumber04Element = orderDtlElement.addElement("Number04");
			  Element dtlNumber05Element = orderDtlElement.addElement("Number05");
			  if("PRODUCT".equalsIgnoreCase(orderItemDTO.getType())){
				  Product product = productDao.getProductByCatalogNo(orderItemDTO.getCatalogNo());
				  if(product != null && product.getVtRatio() == null || product.getBtRatio() == null){
					  dtlNumber04Element.addText("1");
					  dtlNumber05Element.addText("0");
				  }else{
					  dtlNumber04Element.addText(product.getVtRatio());
					  dtlNumber05Element.addText(product.getBtRatio());
				  }
			  }else{
				  com.genscript.gsscm.serv.entity.Service serv = serviceDao.getServiceByCatalogNo(orderItemDTO.getCatalogNo());
				  if(serv != null && serv.getVtRatio() == null || serv.getBtRatio() == null){
					  dtlNumber04Element.addText("1");
					  dtlNumber05Element.addText("0");
				  }else{
					  dtlNumber04Element.addText(serv.getVtRatio());
					  dtlNumber05Element.addText(serv.getBtRatio());
				  }
			  }
			  Element dtlJobNumElement = orderDtlElement.addElement("JobNum");
			  
			  Element dtlCheckbox01Element = orderDtlElement.addElement("CheckBox01");
			  if("PRODUCT".equalsIgnoreCase(orderItemDTO.getType())){
				  dtlCheckbox01Element.addText("true");
			  }else{
				  dtlCheckbox01Element.addText("false");
			  }
			  Element dtlCheckbox02Element = orderDtlElement.addElement("CheckBox02");
			  if("PRODUCT".equalsIgnoreCase(orderItemDTO.getType())){
					//product多肽
					if(orderItemDTO.getClsId() == 2){
						//changePriceOrderDtl.setShortChar01("GSJK");
						dtlCheckbox02Element.addText("true");
					}else{
						//changePriceOrderDtl.setShortChar01("GSJR");
						dtlCheckbox02Element.addText("false");
					}
				}else{
					if(orderItemDTO.getClsId() == 1){
						//changePriceOrderDtl.setShortChar01("GSJK");
						dtlCheckbox02Element.addText("true");
					}else{
						//changePriceOrderDtl.setShortChar01("GSJR");
						dtlCheckbox02Element.addText("false");
					}
				}
			  Element dtlCheckbox03Element = orderDtlElement.addElement("CheckBox03");
			  dtlCheckbox03Element.addText("true");
			  Element dtlStatusElement = orderDtlElement.addElement("Status");
			  if(orderItemDTO.getChangeStatus() != null){
				  dtlStatusElement.addText(orderItemDTO.getChangeStatus());
			  }
		}
	  }
//	  try {
//
//	   /** 将document中的内容写入文件中 */
//
//	   XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)));
//
//	   writer.write(document);
//
//	   writer.close();
//
//	  } catch (Exception ex) {
//		  ex.printStackTrace();
//	  }
		saveDocument(document, filename);
	 }

	private String generatePartStr(OrderItemDTO orderItemDTO) {
		String partStr = orderItemDTO.getCatalogNo();
		  if("SC1010".equalsIgnoreCase(orderItemDTO.getCatalogNo())){
			  OrderGeneSynthesisDTO geneSynthesisDTO = orderItemDTO.getGeneSynthesis();
			  if(geneSynthesisDTO != null){
				  Integer seqLen = geneSynthesisDTO.getSeqLength();
				  String seqTypeVal = geneSynthesisDTO.getSequenceType();
				  String sequence = geneSynthesisDTO.getSequence();
				  if(seqLen == null){
					  if (seqTypeVal.equals("DNA")) {
                          String threeSequence = geneSynthesisDTO
                                  .getSequence3();
                          String fiveSequence = geneSynthesisDTO
                                  .getSequence5();
                          String sequenceStr = geneSynthesisDTO
                                  .getSequence();
                          StringBuilder sequenceSB = new StringBuilder();
                          sequenceSB.append(threeSequence).append(
                                  fiveSequence).append(sequenceStr);
                          seqLen = sequenceSB.toString()
                                  .length();
                      } else if (seqTypeVal.equals("Protein")) {
                    	  seqLen = sequence.length() * 3;
                      } else {
                    	  seqLen = 0;
                      }
				  }
				  if(seqLen < 3000){
					  partStr = "SC1010-2999";
				  }else if(seqLen >= 3000 && seqLen <= 4999){
					  partStr = "SC1010-4999";
				  }else if(seqLen >= 5000 && seqLen <= 7999){
					  partStr = "SC1010-7999";
				  }else if(seqLen >= 8000){
					  partStr = "SC1010-8000";
				  }
			  }
		  }
		  if("SC1017".equalsIgnoreCase(orderItemDTO.getCatalogNo())){
			  OrderCustCloningDTO orderCustCloningDTO = orderItemDTO.getCustCloning();
			  if(orderCustCloningDTO != null){
				  Integer seqLen = null;
				  if(orderCustCloningDTO.getTgtSequence() == null){
					  seqLen = 0;
				  }else{
					  seqLen = orderCustCloningDTO.getTgtSequence().length();
				  }
				  if(seqLen < 1000){
					  partStr = "SC1017-999";
				  }else if(seqLen >= 1000 && seqLen <= 1999){
					  partStr = "SC1017-1999";
				  }else if(seqLen >= 2000 && seqLen <= 2999){
					  partStr = "SC1017-2999";
				  }else if(seqLen >= 3000 && seqLen <= 4999){
					  partStr = "SC1017-4999";
				  }else if(seqLen >= 5000 && seqLen <= 7999){
					  partStr = "SC1017-7999";
				  }else if(seqLen >= 8000){
					  partStr = "SC1017-8000";
				  }
			  }
		  }
		  if("SC1023".equalsIgnoreCase(orderItemDTO.getCatalogNo())){
			  OrderMutagenesisDTO orderMutagenesisDTO = orderItemDTO.getMutagenesis();
			  Integer seqLen = null;
			  if(orderMutagenesisDTO != null){
				  if(orderMutagenesisDTO.getVariantSequence() == null){
					  seqLen = 0;
				  }else{
					  seqLen = orderMutagenesisDTO.getVariantSequence().length();
				  }
				  if(seqLen <= 1000){
					  partStr = "SC1023-1000";
				  }else if(seqLen >= 1001 && seqLen <= 2999){
					  partStr = "SC1023-2999";
				  }else if(seqLen >= 3000 && seqLen <= 4999){
					  partStr = "SC1023-4999";
				  }else if(seqLen >= 5000 && seqLen <= 7999){
					  partStr = "SC1023-7999";
				  }else if(seqLen >= 8000){
					  partStr = "SC1023-8000";
				  }
			  }
		  }
		  if("SC1208".equalsIgnoreCase(orderItemDTO.getCatalogNo())){
			  StringBuilder sb = new StringBuilder();
			  PeptideDTO orderPeptideDTO = orderItemDTO.getPeptide();
			  Integer seqLen = 0;
			  if(orderPeptideDTO.getSeqLength() == null){
				  seqLen = StringUtil.calculateSeqLength(orderPeptideDTO.getSequence());
			  }else{
				  seqLen = orderPeptideDTO.getSeqLength();
			  }
			  String quantity = orderPeptideDTO.getQuantity();
			  String quantityStr = "";
			  if(quantity.indexOf("mg") > -1){
				  if(quantity.indexOf("-") > -1){
					  quantityStr = quantity.substring(quantity.indexOf("-") + 1, quantity.indexOf("mg"));
				  }else{
					  quantityStr = quantity.substring(0, quantity.indexOf("mg"));
				  }
			  }
			  String purity = orderPeptideDTO.getPurity();
			  String purityStr = "";
			  if(purity.indexOf("Crude") > -1){
				  purityStr = "C";
			  }else if(purity.indexOf("Desalt") > -1){
				  purityStr = "D";
			  }else if(purity.indexOf(">") > -1){
				  purityStr = purity.substring(purity.indexOf(">") + 1, purity.indexOf("%"));
			  }
			  if(Integer.parseInt(quantityStr.trim()) > 1000){
				  sb.append("SC1208");
			  }else{
				  if(seqLen >= 1 && seqLen <= 12){
					  sb.append("SC1208_12AA_").append(quantityStr.trim()).append("_").append(purityStr.trim());
				  }else if(seqLen >= 13 && seqLen <= 20){
					  sb.append("SC1208_20AA_").append(quantityStr.trim()).append("_").append(purityStr.trim());
				  }else if(seqLen >= 21 && seqLen <= 30){
					  sb.append("SC1208_30AA_").append(quantityStr.trim()).append("_").append(purityStr.trim());
				  }else if(seqLen >= 31 && seqLen <= 40){
					  sb.append("SC1208_40AA_").append(quantityStr.trim()).append("_").append(purityStr.trim());
				  }else if(seqLen >= 41 && seqLen <= 50){
					  sb.append("SC1208_50AA_").append(quantityStr.trim()).append("_").append(purityStr.trim());
				  }else if(seqLen >= 51 && seqLen <= 60){
					  sb.append("SC1208_60AA_").append(quantityStr.trim()).append("_").append(purityStr.trim());
				  }else{
					  sb.append("SC1208");
				  }
				  partStr = sb.toString();
				  }
		  }
		  
		  if("SC1098".equalsIgnoreCase(orderItemDTO.getCatalogNo())){
			  StringBuilder sb = new StringBuilder();
			  OrderPlasmidPreparationDTO orderPlasmidPreparationDTO = orderItemDTO.getPlasmidPreparation();
			  String copyNumber = orderPlasmidPreparationDTO.getCopyNumber().substring(0, 1);
			  String qualityGrade = orderPlasmidPreparationDTO.getQualityGrade().substring(0, 1);
			  double prepWeight = orderPlasmidPreparationDTO.getPrepWeight().doubleValue();
			  String prepWtUom = orderPlasmidPreparationDTO.getPrepWtUom();
			  if("ug".equalsIgnoreCase(prepWtUom)){
				  prepWeight = prepWeight/1000;
			  }
			  String prepWeightStr = prepWeight+"";
			  if(prepWeightStr.indexOf(".0") > -1){
				  prepWeightStr = prepWeightStr.substring(0, prepWeightStr.indexOf(".0"));
			  }
			  if(prepWeight < 5){
				  sb.append("SC1098-").append(prepWeightStr).append("-").append(qualityGrade);
			  }else{
				  sb.append("SC1098-").append(prepWeightStr).append(copyNumber).append("-").append(qualityGrade);
			  }
			  partStr = sb.toString();
		  }
		return partStr;
	}
	 
	 /**
	  * 
	  *   @Description 保存编辑的XML文件  
	  *   @param document
	  *   @param filepath
	  *   @return
	  *   boolean
	  *   @throws  抛出异常说明
	  */
	 public boolean saveDocument(Document document,String filepath) {
	  boolean isOk = false;
	  Writer writer = null;
	  XMLWriter xmlWriter = null;
	  try {
	   writer = new OutputStreamWriter(new FileOutputStream(filepath),"utf-8");
	   OutputFormat format = OutputFormat.createPrettyPrint();
	   format.setEncoding("utf-8");
	   xmlWriter = new XMLWriter(writer,format);
	   xmlWriter.write(document);
	   writer.close();
	   isOk = true;
	  } catch (IOException e) {
		  e.printStackTrace();
	   return false;
	  }
	  return isOk;
	 }
}
