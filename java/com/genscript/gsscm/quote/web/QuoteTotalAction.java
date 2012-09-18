package com.genscript.gsscm.quote.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.CountryCode;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.QuoteSessionUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dto.OrderReturnItemDTO;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quote.dto.QuotePromotionDTO;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quote.service.QuoteShippingService;
import com.genscript.gsscm.quoteorder.dto.CurrencyChangeDTO;
import com.genscript.gsscm.quoteorder.dto.ShippingTotalDTO;
import com.genscript.gsscm.quoteorder.dto.TotalDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;
import com.genscript.gsscm.ws.WSException;

/**
 * Quote Total tab相关处理
 * @author zhangyang
 *
 */
@Results({
	@Result(name="total", location="quote/quote_total.jsp")
})
public class QuoteTotalAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5812035383140034035L;
	public static final String SUCCESS = "SUCCESS";
	@Autowired
	private PublicService publicService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private QuoteShippingService quoteShippingService;
	@Autowired
	private ExceptionService exceptionUtil;
    @Autowired
	private OrderService orderService;
	private String sessQuoteNo;
	private String mainStatus;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownMap;
	private String shipAccount;
	private Integer shipMethod;
	private List<OrderReturnItemDTO> returnItemList;
	private TotalDTO billTotal;
	private String prmtCode;
	private String symbol;
	private String fromCurrency;
	private String toCurrency;
	private String shippingAccount;
	private String customizedCost;
	private String shipAmtChanged;
	private Double shipAmt;
	//coupon
	private String couponValue;
	private String couponCode;
	private String couponId;
	private String shipFlag;

	
	/**
	 * 计算bill total
	 * @return
	 * @throws Exception
	 */
	public String calBill() throws Exception{
		QuoteMainDTO quote= (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		Double amount = quote.getAmount().doubleValue();
		Double subTotal = quote.getSubTotal().doubleValue();
		Double discount = quote.getDiscount().doubleValue();
		Double tax = quote.getTax().doubleValue();
		Double couponValue = quote.getCouponValue() != null ?quote.getCouponValue().doubleValue():0.0;
		String toCurrency = quote.getQuoteCurrency();
		shippingAccount = quote.getShippingAccount();
		ShippingTotalDTO shippingTotalDTO = (ShippingTotalDTO) SessionUtil.getRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo);
		Double customerCharge = null;
		if(!StringUtils.isEmpty(shippingAccount)){
			customerCharge = 0.0;
		}else if(!"Y".equalsIgnoreCase(quote.getShipAmtChanged())){
			customerCharge = shippingTotalDTO==null?0:shippingTotalDTO.getCostTotal();
		}else{
			customerCharge = quote.getShipAmt().doubleValue();
		}
		boolean isJPCountry = false;
		if (quote.getQuoteAddrList() != null && quote.getQuoteAddrList().getShipToAddr() != null
				&& CountryCode.JP.value().equalsIgnoreCase(quote.getQuoteAddrList().getShipToAddr().getCountry())) {
			isJPCountry = true;
		}
		String handlingFee = shippingTotalDTO==null?"0":shippingTotalDTO.getHandlingFee();
		TotalDTO quoteTotalDTO = quoteOrderService.getMyTotals(amount, subTotal, discount, tax, customerCharge, handlingFee, toCurrency, new BigDecimal(0.0), couponValue, isJPCountry);
		//计算prepayment
		BigDecimal prePayment = quoteService.getPrePayment(quote.getCustNo(), Integer.parseInt(sessQuoteNo), quoteTotalDTO.getQuorderTotal().doubleValue(), toCurrency);
		quoteTotalDTO.setPrePayment(prePayment);
		SessionUtil.insertRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo, quoteTotalDTO);
		Struts2Util.renderJson(quoteTotalDTO);
		return null;
	}
	
	
	/**
	 * 显示total页面，初始化相关数据
	 * @return
	 * @throws Exception
	 */
	public String showTotal() throws Exception{
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		mainStatus = quote.getStatus();
		//下拉数据
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.CURRENCY);
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		specDropDownMap = publicService.getSpecDropDownMap(speclListName);
		//初始化shipping total
		toCurrency = quote.getQuoteCurrency();
		Date exchRateDate = quote.getExchRateDate();
		Double exchRate = quote.getExchRate();
		shipAmtChanged = quote.getShipAmtChanged();
		if(quote.getShipAmt() != null){
			shipAmt = quote.getShipAmt().doubleValue();
		}
		shippingAccount = quote.getShippingAccount();
		symbol = publicService.getCurrencySymbol(toCurrency);
		ShippingTotalDTO shippingTotalDTO = null;
		try {
			shippingTotalDTO = this.initShippingTotal(toCurrency, exchRate, exchRateDate);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
		}
		//初始化 billTotal
		if(billTotal == null && shippingTotalDTO != null){
			Double customerCharge = null;
			if(!StringUtils.isEmpty(shippingAccount)){
				customerCharge = 0.0;
			}else if(!"Y".equalsIgnoreCase(quote.getShipAmtChanged())){
				customerCharge = shippingTotalDTO.getCostTotal();
			}else{
				customerCharge = quote.getShipAmt().doubleValue();
			}
			Double amount = quote.getAmount().doubleValue();
			Double subTotal = quote.getSubTotal().doubleValue();
			Double tax = quote.getTax().doubleValue();
			Double discount = quote.getDiscount().doubleValue();
			Double couponValue = quote.getCouponValue() != null ?quote.getCouponValue().doubleValue():0.0;
			String handlingFee = shippingTotalDTO.getHandlingFee();
			boolean isJPCountry = false;
//			if (quote.getQuoteAddrList() != null && quote.getQuoteAddrList().getShipToAddr() != null
//					&& CountryCode.JP.value().equalsIgnoreCase(quote.getQuoteAddrList().getShipToAddr().getCountry())) {
//				isJPCountry = true;
//			}
			billTotal = quoteOrderService.getMyTotals(amount, subTotal, discount, tax, customerCharge, handlingFee, toCurrency, new BigDecimal(0.0), couponValue, isJPCountry);
			BigDecimal prePayment = quoteService.getPrePayment(quote.getCustNo(), Integer.parseInt(sessQuoteNo), billTotal.getQuorderTotal().doubleValue(), toCurrency);
			billTotal.setPrePayment(prePayment);
			SessionUtil.insertRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo, billTotal);
		}else if(billTotal != null && shippingTotalDTO == null){
			billTotal = null;
			SessionUtil.deleteRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo);
		}
		return "total";
	}
	
	/**
	 * 计算shipping相关统计数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String calShipping() throws Exception{
		String checkStr = checkDataBeforeSave(); 
		if(!checkStr.equalsIgnoreCase(SUCCESS)){
			Struts2Util.renderText(checkStr);
			return null;
		}
		List<QuoteItemDTO> itemDTOList = new ArrayList<QuoteItemDTO>();
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO itemDTO = entry.getValue();
			if(itemDTO.getItemNo() == null)
				continue;
			if(!itemDTO.getStatus().equalsIgnoreCase(OrderItemStatusType.CN.value())){
				itemDTOList.add(itemDTO);
			}
		}
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		Integer companyId = quote.getGsCoId();
		Integer warehouseId = quote.getWarehouseId();
		Map<String, Object> rt = new HashMap<String, Object>();
		try{
			Map<String, QuotePackageDTO> packageMap = (Map<String, QuotePackageDTO>) SessionUtil.getRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
			if (("N").equals(shipFlag) && packageMap != null && !packageMap.isEmpty()) {
			} else {
				//清除Package 的session
				SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
				SessionUtil.deleteRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo);
				SessionUtil.deleteRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo);
				List<QuotePackageDTO> packageList = quoteShippingService.calShippingPackage(itemDTOList, 
						Integer.parseInt(sessQuoteNo), SessionUtil.getUserId(), companyId, warehouseId);
				Double shipAmt = quoteOrderService.getShipAmtFromPackage(packageList);
				if(!"Y".equalsIgnoreCase(quote.getShipAmtChanged())){
					if (shipAmt == null) {
						quote.setShipAmt(new BigDecimal(0));
					} else {
						quote.setShipAmt(new BigDecimal(shipAmt));
					}
				}
			}
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			Struts2Util.renderJson(rt);
		}
		rt.put("SUCCESS", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 更改shipping account
	 * @return
	 * @throws Exception
	 */
	public String changeShipAccount() throws Exception{
		//更新quote中的信息。
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		quote.setShippingAccount(shipAccount);
		if(!StringUtils.isEmpty(shipAccount)){
			if(!"Y".equalsIgnoreCase(quote.getShipAmtChanged())){
					quote.setShipAmt(new BigDecimal(0.0));
			}
		}
		ShippingTotalDTO shippingTotalDTO = (ShippingTotalDTO) SessionUtil.getRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo);
		if(shippingTotalDTO != null){
			shippingTotalDTO.setShipAccount(shipAccount);
			shippingTotalDTO.setShipAccountFlag(true);
			//如果shipAccount有值，则要清空costTotal
			if(!StringUtils.isEmpty(shipAccount)){
				shippingTotalDTO.setCostTotal(0.0);
			}
			this.updateShippingAccount(shipAccount);//更新package信息。
			SessionUtil.updateRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo, shippingTotalDTO);
			TotalDTO totalDTO = new TotalDTO();
			SessionUtil.updateRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo, totalDTO);
			Struts2Util.renderText(SUCCESS);
			return null;
		}
		Struts2Util.renderText(SUCCESS);
		return null;
	}
	
	/**
	 * 手动修改shipping fee
	 * @return
	 */
	public String changeCost(){
		String shipAmtChanged = "Y";
		if(StringUtils.isEmpty(this.customizedCost)){
			shipAmtChanged = "N";
		}
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		if(shipAmtChanged.equalsIgnoreCase("Y")){
			quote.setShipAmt(new BigDecimal(Double.parseDouble(this.customizedCost)));
		}
		quote.setShipAmtChanged(shipAmtChanged);
		return null;
	}
	
	/**
	 * 更改ship method
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String changeShipMethod() throws Exception{
		Map<String, QuoteItemDTO> itemMapNew = new LinkedHashMap<String, QuoteItemDTO>();
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if(itemMap == null){
			return null;
		}
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO itemDTO = entry.getValue();
			String tmpId = entry.getKey();
			itemDTO.setShipMethod(shipMethod);
			itemMapNew.put(tmpId, itemDTO);
		}
		SessionUtil.updateRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, itemMapNew);
		//清除package
		QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
		//清除total统计信息
		SessionUtil.deleteRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo);
		SessionUtil.deleteRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo);
		
		Struts2Util.renderText(SUCCESS);
		return null;
	}
	
	/**
	 * 计算package 前的验证
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String checkDataBeforeSave(){
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if(itemMap == null || itemMap.size() == 0){
			return "No item added, please added one item at least.";
		}
		return SUCCESS;
	}
	
	/**
	 * 获得shipping total 相关信息
	 * @return
	 * @throws Exception
	 */
	public String getShippingTotal()  {
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		String currency = quote.getQuoteCurrency();
		Double exchRate = quote.getExchRate();
		Date exchRateDate = quote.getExchRateDate();
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			ShippingTotalDTO shippingTotalDTO = this.initShippingTotal(currency, exchRate, exchRateDate);
			if(shippingTotalDTO != null){
				String str=shippingTotalDTO.getZone();
				if(str==null||str.length()<1){
					shippingTotalDTO.setZone("");
				}
			}
			Struts2Util.renderJson(shippingTotalDTO);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			Struts2Util.renderJson(rt);
		}
		return null;
	}
	
	/**
	 * 初始化shipping total
	 * @param currency
	 * @param exchRate
	 * @return
	 */
	private ShippingTotalDTO initShippingTotal(String currency, Double exchRate,
			Date exchRateDate){
		try{
		shipMethod = this.getUnitedShipMethod();
		ShippingTotalDTO shippingTotalDTO = (ShippingTotalDTO) SessionUtil.getRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo);
		List<QuotePackageDTO> packageList =  this.getPackageListForCalTotal();
		if(packageList == null || packageList.size() == 0){
			if(shippingTotalDTO != null){
				SessionUtil.deleteRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo);
			}
			return null;
		}
		if(shippingTotalDTO != null){
			shippingTotalDTO.setShipVia(shipMethod);
			return shippingTotalDTO;
		}
		shippingTotalDTO = quoteService.getShippingTotalByPage(currency, packageList, exchRate, exchRateDate);
		shippingTotalDTO.setShipVia(shipMethod);
		SessionUtil.insertRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo, shippingTotalDTO);
		return shippingTotalDTO;
		
		}catch(Exception e){
			e.printStackTrace();
			return new ShippingTotalDTO();
		}
	
	}
	
	/**
	 * 获得统一的order item 中的shipMethod，如果不一致返回0.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Integer getUnitedShipMethod(){
		try{
		Integer tmpShipMethod = 0;
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if(itemMap != null){
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, QuoteItemDTO> entry = it.next();
				if (tmpShipMethod != null && tmpShipMethod.intValue() != 0 && tmpShipMethod.intValue() != entry.getValue().getShipMethod().intValue()){
					tmpShipMethod = 0;
					break;
				}else{
					tmpShipMethod = entry.getValue().getShipMethod();
				}
			}
		}
		return tmpShipMethod;
		}catch(java.lang.NullPointerException e){
			e.printStackTrace();
			//当某一个itemDTO的属性shipMethod字段为空时会抛出NullPointerException,返回0此说明此字段为null
			return 0;
		}
	}

	/**
	 * 获得当前的Package List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<QuotePackageDTO> getPackageListForCalTotal(){
		Map<String, QuotePackageDTO> packageMap = (Map<String, QuotePackageDTO>) SessionUtil
			.getRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		List<QuotePackageDTO> packageList = new ArrayList<QuotePackageDTO>();
		if(StringUtils.isNumeric(sessQuoteNo) && (packageMap == null || packageMap.isEmpty())){
			packageList = quoteService.getBaseQuoteShipPackageList(Integer.parseInt(sessQuoteNo));
			if (packageList != null && !packageList.isEmpty()) {
				packageMap = SessionUtil.convertList2Map(packageList, "packageId");
				Iterator<Entry<String, QuotePackageDTO>> it = packageMap.entrySet().iterator();
				while(it.hasNext()){
					Entry<String, QuotePackageDTO> entry = it.next();
					QuotePackageDTO quotePackageDTO = entry.getValue();
					quotePackageDTO.setPackageId(null);
				}
			}
		}
		List<QuotePackageDTO> rtPackageList = new ArrayList<QuotePackageDTO>();
		if(packageMap != null){
			SessionUtil.updateRow(SessionConstant.QuotePackage.value(), sessQuoteNo, packageMap);
			Iterator<Entry<String, QuotePackageDTO>> it = packageMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, QuotePackageDTO> entry = it.next();
				QuotePackageDTO quotePackageDTO = entry.getValue();
				rtPackageList.add(quotePackageDTO);
			}
		}
		return rtPackageList;
	}
	
	/**
	 * 更新所有的package 的 shippingAccount
	 * @param shippingAccount
	 */
	@SuppressWarnings("unchecked")
	private void updateShippingAccount(String shippingAccount){
		Map<String, QuotePackageDTO> sessPackageMap = (Map<String, QuotePackageDTO>) SessionUtil
		.getRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		List<QuotePackageDTO> packageList = new ArrayList<QuotePackageDTO>();
		if(StringUtils.isNumeric(sessQuoteNo)){
			packageList = quoteService
			.getPackageList(Integer.parseInt(sessQuoteNo));
		}
		Map<String, QuotePackageDTO> dbPackageMap = SessionUtil.convertList2Map(
				packageList, "packageId");
		Map<String, QuotePackageDTO> packageMap = SessionUtil.mergeList(sessPackageMap, dbPackageMap, 1);
		if(packageMap != null){
			Iterator<Entry<String, QuotePackageDTO>> it = packageMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, QuotePackageDTO> entry = it.next();
				QuotePackageDTO quotePackageDTO = entry.getValue();
				quotePackageDTO.setCustomerCharge(0.0);
			}
			SessionUtil.updateRow(SessionConstant.QuotePackage.value(), sessQuoteNo, packageMap);
		}
	}
	
	/**
	 * 修改currency
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String changeCurrency(){
		try{
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		if (sessQuoteNo == null || !StringUtil.isNumeric(sessQuoteNo)) {
			quote.setQuoteCurrency(toCurrency);
			Struts2Util.renderText(SUCCESS);
			return null;
		}
		Integer custNo = quote.getCustNo();
		CustomerDTO customer = this.customerService.getCustomerBase(custNo);
		String country = customer.getCountry();
		String state = customer.getState();
		
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if (itemMap == null || itemMap.size() == 0) {
			quote.setQuoteCurrency(toCurrency);
			Struts2Util.renderText(SUCCESS);
			return null;
		}
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		List<CurrencyChangeDTO> currencyDTOList = new ArrayList<CurrencyChangeDTO>();
		while(it.hasNext()){
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			CurrencyChangeDTO currencyChangeDTO = new CurrencyChangeDTO();
			currencyChangeDTO.setItemId(tmpId);
			currencyChangeDTO.setCatalogNo(quoteItemDTO.getCatalogNo());
			currencyChangeDTO.setQuantity(quoteItemDTO.getQuantity());
			if (quoteItemDTO.getBasePrice() == null || quoteItemDTO.getBasePrice().doubleValue() <=0) {
                BigDecimal basePrice = orderService.getOrderChangeCurrency(quoteItemDTO.getUnitPrice(), null, quote.getQuoteCurrency(), CurrencyType.USD.value());
				quoteItemDTO.setBasePrice(basePrice);
			}
			currencyChangeDTO.setBasePrice(quoteItemDTO.getBasePrice().doubleValue());
			currencyChangeDTO.setUnitPrice(quoteItemDTO.getBasePrice().doubleValue());
			currencyChangeDTO.setDiscount(quoteItemDTO.getDiscount().doubleValue());
			currencyChangeDTO.setState(state);
			currencyChangeDTO.setCountry(country);
			currencyDTOList.add(currencyChangeDTO);
		}
		//fromCurrency = Constants.ORDER_CURRENCY_USD;
		fromCurrency = quote.getBaseCurrency();
		List<CurrencyChangeDTO> retList = new ArrayList<CurrencyChangeDTO>();
		if (StringUtils.isNumeric(sessQuoteNo)) {
			retList = quoteService.changeCurrency(Integer.parseInt(sessQuoteNo), 
					currencyDTOList, fromCurrency, toCurrency);
		} else {
			retList = quoteService.changeCurrency(null, currencyDTOList, fromCurrency, toCurrency);
		}
		String toSymbol = publicService.getCurrencySymbol(toCurrency);
		for(int i=0; i<retList.size(); i++){
			CurrencyChangeDTO currencyChangeDTO = retList.get(i);
			String itemId = currencyChangeDTO.getItemId();
			if(itemMap.containsKey(itemId)){
				QuoteItemDTO tmpItemDTO = itemMap.get(itemId);
				tmpItemDTO.setCurrencyCode(toCurrency);
				int small = CurrencyType.JPY.value().equals(toCurrency)?0:2;
				tmpItemDTO.setAmount(new BigDecimal(currencyChangeDTO.getAmount()).setScale(small, BigDecimal.ROUND_HALF_UP));
				tmpItemDTO.setUnitPrice(new BigDecimal(currencyChangeDTO.getUnitPrice()).setScale(small, BigDecimal.ROUND_HALF_UP));
				tmpItemDTO.setDiscount(new BigDecimal(currencyChangeDTO.getDiscount()).setScale(small, BigDecimal.ROUND_HALF_UP));
				tmpItemDTO.setTax(new BigDecimal(currencyChangeDTO.getTax()).setScale(small, BigDecimal.ROUND_HALF_UP));
				tmpItemDTO.setUpSymbol(toSymbol);
				itemMap.put(itemId, tmpItemDTO);
			}
		}
		SessionUtil.updateRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, itemMap);
		quote.setQuoteCurrency(toCurrency);
		SessionUtil.updateRow(SessionConstant.Quote.value(), sessQuoteNo, quote);
//		Double exchRate = order.getExchRate();
		//重新生成shippint total
		QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
		SessionUtil.deleteRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo);
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessQuoteNo);
//		this.initShippingTotal(toCurrency, exchRate);
		TotalDTO totalDTO = new TotalDTO();
		SessionUtil.insertRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo, totalDTO);
		Struts2Util.renderText(SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 计算并更新item的价格
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String calAllItemPrice()throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		if(prmtCode == null){
			QuotePromotionDTO promotionDTO = quote.getQuotePromotion();
			if(promotionDTO != null){
				prmtCode = promotionDTO.getPrmtCode();
			}
		}
		if(StringUtils.isEmpty(prmtCode)){
			prmtCode = null;//quoteservice 中会以null进行判断
		}
		Integer custNo = quote.getCustNo();
		if (quote.getQuoteAddrList() == null || quote.getQuoteAddrList().getShipToAddr() == null) {
			rt.put("message","Ship To Address can not be null.");
			Struts2Util.renderJson(rt);
			return null;
		}
		String country = quote.getQuoteAddrList().getShipToAddr().getCountry();
		String state = quote.getQuoteAddrList().getShipToAddr().getState();
		String baseCurrency = quote.getBaseCurrency();
		String quoteCurrency = quote.getQuoteCurrency();
		
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if(itemMap != null && itemMap.size() > 0){
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				if(quoteItemDTO != null && "SERVICE".equalsIgnoreCase(quoteItemDTO.getType())){
					if(quoteItemDTO.getClsId() == 1 || quoteItemDTO.getClsId() == 31){
						quoteService.setItemAllMoreDetail(itemMap, quoteItemDTO);
					}
				}
			}
		}
		if(itemMap == null){
			rt.put("message","Item list none");
			Struts2Util.renderJson(rt);
			return null;
		}
		List<QuoteItemDTO> itemList = SessionUtil.convertMap2List(itemMap);
		try{
			boolean flg = false;
			if(quote.getQuotePromotion()!=null) {
				clearQuoteDiscount(sessQuoteNo);
				clearQuoteGift(sessQuoteNo);
			}
			if(!systemSettingService.isPrmtOk(custNo,prmtCode,sessQuoteNo,"quote")) {
				if(quote.getQuotePromotion()!=null&&prmtCode!=null&&prmtCode.equals(quote.getQuotePromotion().getPrmtCode())) {
					quote.setQuotePromotion(null);
				}
				rt.put("message","Failed to apply the promotion for it does not meet the conditions.");
				Struts2Util.renderJson(rt);
				return null;
			}
			String result = quoteOrderService.calProductItemPrice(itemList, custNo, prmtCode, baseCurrency, quoteCurrency, country, state,quote.getQuoteDate());
			if(result!=null) {
				flg = true;
			}
			if(StringUtils.isNotBlank(prmtCode)){
				result = quoteService.calGiftItemDiscount(itemList, sessQuoteNo, prmtCode, custNo, quote.getWarehouseId());
			}
			if(flg&&result!=null) {
				if(quote.getQuotePromotion()!=null&&prmtCode!=null&&prmtCode.equals(quote.getQuotePromotion().getPrmtCode())) {
					quote.setQuotePromotion(null);
				}
				rt.put("message",result);
				Struts2Util.renderJson(rt);
				return null;
			} else if(StringUtils.isNotEmpty(prmtCode)&&(quote.getQuotePromotion()==null||!StringUtils.isNotEmpty(quote.getQuotePromotion().getPrmtCode()))){
				QuotePromotionDTO quotePromotion = new QuotePromotionDTO();
				quotePromotion.setPrmtCode(prmtCode);
				quote.setQuotePromotion(quotePromotion);
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			Struts2Util.renderJson(rt);
			return null;
		}
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		Double totalAmount = 0.0;
		Double totalDiscount = 0.0;
		Double totalTax = 0.0;
		while(it.hasNext()){
			Entry<String, QuoteItemDTO> entry2 = it.next();
			QuoteItemDTO ItemDTO2 = entry2.getValue();
			if("CN".equalsIgnoreCase(ItemDTO2.getStatus())){
				continue;
			}
			if(quoteOrderService.isGiftItem(ItemDTO2.getCatalogNo(), ItemDTO2.getType())){
				continue;
			}
			totalAmount = ArithUtil.add(totalAmount, ItemDTO2.getAmount().doubleValue());
			totalDiscount = ArithUtil.add(totalDiscount, ItemDTO2.getDiscount().doubleValue());
			totalTax = ArithUtil.add(totalTax, ItemDTO2.getTax().doubleValue());
		}
		if (totalAmount == null || totalAmount.doubleValue() < 0) {
			quote.setSubTotal(new BigDecimal(0));
		} else {
			quote.setSubTotal(new BigDecimal(totalAmount));
		}
		quote.setDiscount(new BigDecimal(totalDiscount));
		if(country.equalsIgnoreCase("JP")){
			if(quote.getShipAmt()!=null){
				totalTax = ArithUtil.add(ArithUtil.mul(quote.getShipAmt().doubleValue(), 0.05), totalTax);
			}
		}
		quote.setTax(new BigDecimal(totalTax));
		if (quote.getCouponValue() == null) {
			quote.setCouponValue(new BigDecimal(0));
		}
		if (quote.getShipAmt() == null) {
			quote.setShipAmt(new BigDecimal(0));
		}
		Double quoteTotalBD = totalAmount-totalDiscount-quote.getCouponValue().doubleValue()+quote.getShipAmt().doubleValue()+totalTax;
		quote.setAmount(new BigDecimal(quoteTotalBD));
		rt.put("message","Success");
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * applyCouponCode
	 */
	public String applyCouponCode() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.quoteService.applyCoupon(couponId,sessQuoteNo);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			Struts2Util.renderJson(rt);
			return null;
		}
		rt.put("message", "Success");
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * @param sessNoVal
	 */
	private void clearQuoteGift(String sessNoVal) {
		this.quoteService.deleteGifItem(this.prmtCode, sessNoVal);
	}
	
	/**
	 * @param sessNoVal
	 */
	@SuppressWarnings("unchecked")
	private void clearQuoteDiscount(String sessNoVal) {
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), sessNoVal);
		if (itemMap != null && !itemMap.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				QuoteItemDTO val = (QuoteItemDTO) itemMap.get(key);
				val.setDiscount(new BigDecimal(0.0));
				SessionUtil.updateOneRow(SessionConstant.QuoteItemList.value(),
						sessNoVal, key, val);
				sb.append(val.getCatalogNo()).append("@@0||");
			}
		}
		SessionUtil.insertRow(SessionConstant.QuoteBillingTotalFlag.value(),
				sessNoVal, 0);
	}
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownMap() {
		return specDropDownMap;
	}

	public void setSpecDropDownMap(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownMap) {
		this.specDropDownMap = specDropDownMap;
	}

	public String getMainStatus() {
		return mainStatus;
	}

	public void setMainStatus(String mainStatus) {
		this.mainStatus = mainStatus;
	}

	public String getShipAccount() {
		return shipAccount;
	}

	public void setShipAccount(String shipAccount) {
		this.shipAccount = shipAccount;
	}

	public Integer getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}

	public List<OrderReturnItemDTO> getReturnItemList() {
		return returnItemList;
	}

	public void setReturnItemList(List<OrderReturnItemDTO> returnItemList) {
		this.returnItemList = returnItemList;
	}

	public TotalDTO getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(TotalDTO billTotal) {
		this.billTotal = billTotal;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}


	public String getPrmtCode() {
		return prmtCode;
	}


	public void setPrmtCode(String prmtCode) {
		this.prmtCode = prmtCode;
	}


	public String getShippingAccount() {
		return shippingAccount;
	}


	public String getCouponValue() {
		return couponValue;
	}


	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}


	public String getCouponCode() {
		return couponCode;
	}


	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}


	public String getCouponId() {
		return couponId;
	}


	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}


	public String getCustomizedCost() {
		return customizedCost;
	}


	public void setCustomizedCost(String customizedCost) {
		this.customizedCost = customizedCost;
	}


	public String getShipAmtChanged() {
		return shipAmtChanged;
	}


	public void setShipAmtChanged(String shipAmtChanged) {
		this.shipAmtChanged = shipAmtChanged;
	}


	public Double getShipAmt() {
		return shipAmt;
	}


	public void setShipAmt(Double shipAmt) {
		this.shipAmt = shipAmt;
	}


	public String getShipFlag() {
		return shipFlag;
	}


	public void setShipFlag(String shipFlag) {
		this.shipFlag = shipFlag;
	}

}
