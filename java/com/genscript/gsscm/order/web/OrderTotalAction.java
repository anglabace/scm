package com.genscript.gsscm.order.web;

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
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.CountryCode;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.OrderSessionUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.dto.OrderPromotionDTO;
import com.genscript.gsscm.order.dto.OrderReturnItemDTO;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.quoteorder.dto.CurrencyChangeDTO;
import com.genscript.gsscm.quoteorder.dto.ShippingTotalDTO;
import com.genscript.gsscm.quoteorder.dto.TotalDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;
import com.genscript.gsscm.ws.WSException;

/**
 * Order Total tab相关处理
 * 
 * @author zouyulu
 * @update by zhou gang 2011 11 18
 */
@Results({
		@Result(name = "total", location = "order/order_total.jsp"),
		@Result(name = "orderTotalPaymentsView", location = "order/order_TotalPaymentsList.jsp") })
public class OrderTotalAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5812035383140034035L;
	public static final String SUCCESS = "SUCCESS";
	@Autowired
	private PublicService publicService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private ExceptionService exceptionUtil;

	private String sessOrderNo;
	private String mainStatus;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownMap;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private String shipAccount;
	private Integer shipMethod;
	private List<OrderReturnItemDTO> returnItemList;
	private TotalDTO billTotal;
	private String symbol;
	private String fromCurrency;
	private String toCurrency;
	private String shippingType;
	private String shippingRule;
	private String shippingAccount;
	private String prmtCode;
	// coupon
	private String couponValue;
	private String couponCode;
	private String couponId;

	private String customizedCost;
	private String shipAmtChanged;
	private Double shipAmt;
	private String shipFlag;

	/**
	 * 显示total页面，初始化相关数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showTotal() throws Exception {
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		mainStatus = order.getStatus();
		shippingType = order.getShippingType();
		shippingRule = order.getShippingRule();
		shippingAccount = order.getShippingAccount();
		shipAmtChanged = order.getShipAmtChanged();
		shipAmt = order.getShipAmt();
		// 下拉数据
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.CURRENCY);
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		specDropDownMap = publicService.getSpecDropDownMap(speclListName);
		List<DropDownListName> dropNameList = new ArrayList<DropDownListName>();
		dropNameList.add(DropDownListName.SHIPPING_TYPE);
		dropNameList.add(DropDownListName.SHIPPING_RULE);
		dropDownMap = publicService.getDropDownMap(dropNameList);
		// return item list
		if (StringUtils.isNumeric(sessOrderNo)) {
			returnItemList = orderService.getProcessedReturnItemList(Integer
					.parseInt(sessOrderNo));
		}
		// 初始化shipping total
		toCurrency = order.getOrderCurrency();
		Double exchRate = order.getExchRate();
		Date exchRateDate = order.getExchRateDate();
		symbol = publicService.getCurrencySymbol(toCurrency);
		ShippingTotalDTO shippingTotalDTO = null;
		try {
			shippingTotalDTO = this.initShippingTotal(toCurrency, exchRate,
					exchRateDate);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
		}
		// 初始化 billTotal
		billTotal = (TotalDTO) SessionUtil.getRow(
				SessionConstant.OrderBillingTotal.value(), sessOrderNo);
		if (billTotal == null && shippingTotalDTO != null) {
			Double customerCharge = null;
			if (!StringUtils.isEmpty(shippingAccount)) {
				customerCharge = 0.0;
			} else if (!"Y".equalsIgnoreCase(order.getShipAmtChanged())) {
				customerCharge = shippingTotalDTO.getCostTotal();
			} else {
				customerCharge = order.getShipAmt();
			}
			Double amount = order.getAmount();
			Double subToal = order.getSubTotal();
			Double tax = order.getTax();
			Double discount = order.getDiscount();
			Double couponValue = order.getCouponValue();
			boolean isJPCountry = false;
			// if (order.getOrderAddrList() != null &&
			// order.getOrderAddrList().getShipToAddr() != null
			// &&
			// CountryCode.JP.value().equalsIgnoreCase(order.getOrderAddrList().getShipToAddr().getCountry()))
			// {
			// isJPCountry = true;
			// }
			BigDecimal totalPayment = orderService.getTotalPaymentsAmount_new(
					Integer.parseInt(sessOrderNo), toCurrency, new Date());
			String handlingFee = shippingTotalDTO.getHandlingFee();
			billTotal = quoteOrderService.getMyTotals(amount, subToal,
					discount, tax, customerCharge, handlingFee, toCurrency,
					totalPayment, couponValue, isJPCountry);
			BigDecimal prePayment = orderService.getPrePayment(order
					.getCustNo(), Integer.parseInt(sessOrderNo), billTotal
					.getQuorderTotal().doubleValue(), toCurrency);
			billTotal.setPrePayment(prePayment);
			SessionUtil.insertRow(SessionConstant.OrderBillingTotal.value(), sessOrderNo, billTotal);
		} else if (billTotal != null && shippingTotalDTO == null) {
			billTotal = null;
			SessionUtil.deleteRow(SessionConstant.OrderBillingTotal.value(), sessOrderNo);
		}
		return "total";
	}

	/**
	 * 生成order packages
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String calShipping() throws Exception {
		String checkStr = checkDataBeforeSave();
		if (!checkStr.equalsIgnoreCase(SUCCESS)) {
			Struts2Util.renderText(checkStr);
			return null;
		}
		List<OrderItemDTO> itemDTOList = new ArrayList<OrderItemDTO>();
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO itemDTO = entry.getValue();
			if (itemDTO.getItemNo() == null)
				continue;
			if (!itemDTO.getStatus().equalsIgnoreCase(
					OrderItemStatusType.CN.value())) {
				itemDTOList.add(itemDTO);
			}
		}
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		Integer companyId = order.getGsCoId();
		Integer warehouseId = order.getWarehouseId();
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String, OrderPackageDTO> packageMap = (Map<String, OrderPackageDTO>) SessionUtil
					.getRow(SessionConstant.OrderPackage.value(), sessOrderNo);
			if (("N").equals(shipFlag) && packageMap != null
					&& !packageMap.isEmpty()) {
			} else {
				// 清除Package 的session
				SessionUtil.deleteRow(SessionConstant.OrderPackage.value(),
						sessOrderNo);
				SessionUtil
						.deleteRow(SessionConstant.OrderShippingTotal.value(),
								sessOrderNo);
				SessionUtil.deleteRow(
						SessionConstant.OrderBillingTotal.value(), sessOrderNo);
				// 在内存中产生新的package
				List<OrderPackageDTO> packageList = orderService
						.calShippingPackage(itemDTOList,
								Integer.parseInt(sessOrderNo),
								SessionUtil.getUserId(), companyId, warehouseId);
				Double shipAmt = quoteOrderService
						.getShipAmtFromPackage(packageList);
				if (!"Y".equalsIgnoreCase(order.getShipAmtChanged())) {
					if (shipAmt == null) {
						order.setShipAmt(0d);
					} else {
						order.setShipAmt(shipAmt);
					}
				}
			}
		} catch (Exception e) {
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
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changeShipAccount() throws Exception {
		// 更新order中的信息。
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		order.setShippingAccount(shipAccount);
		if (!StringUtils.isEmpty(shipAccount)) {
			if (!"Y".equalsIgnoreCase(order.getShipAmtChanged())) {
				order.setShipAmt(0.0);
			}
		}

		ShippingTotalDTO shippingTotalDTO = (ShippingTotalDTO) SessionUtil
				.getRow(SessionConstant.OrderShippingTotal.value(), sessOrderNo);
		if (shippingTotalDTO != null) {
			shippingTotalDTO.setShipAccount(shipAccount);
			shippingTotalDTO.setShipAccountFlag(true);
			// 如果shipAccount有值，则要清空costTotal
			if (!StringUtils.isEmpty(shipAccount)) {
				shippingTotalDTO.setCostTotal(0.0);
			}
			this.updateShippingAccount(shipAccount);// 更新package信息。
			SessionUtil.updateRow(SessionConstant.OrderShippingTotal.value(),
					sessOrderNo, shippingTotalDTO);
			TotalDTO totalDTO = new TotalDTO();
			SessionUtil.updateRow(SessionConstant.OrderBillingTotal.value(),
					sessOrderNo, totalDTO);
			Struts2Util.renderText(SUCCESS);
			return null;
		}
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/**
	 * 更改ship method
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String changeShipMethod() throws Exception {
		Map<String, OrderItemDTO> itemMapNew = new LinkedHashMap<String, OrderItemDTO>();
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (itemMap == null) {
			return null;
		}
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO itemDTO = entry.getValue();
			String tmpId = entry.getKey();
			itemDTO.setShipMethod(shipMethod);
			itemMapNew.put(tmpId, itemDTO);
		}
		SessionUtil.updateRow(SessionConstant.OrderItemList.value(),
				sessOrderNo, itemMapNew);
		// 清除package
		OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
		// 清除total统计信息
		SessionUtil.deleteRow(SessionConstant.OrderShippingTotal.value(),
				sessOrderNo);
		SessionUtil.deleteRow(SessionConstant.OrderBillingTotal.value(),
				sessOrderNo);

		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/**
	 * 计算package 前的验证
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String checkDataBeforeSave() {
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (itemMap == null || itemMap.size() == 0) {
			return "No item added, please added one item at least.";
		}
		return SUCCESS;
	}

	/**
	 * 获得shipping total 相关信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getShippingTotal() {
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		String currency = order.getOrderCurrency();
		Double exchRate = order.getExchRate();
		Date exchRateDate = order.getExchRateDate();
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			ShippingTotalDTO shippingTotalDTO = this.initShippingTotal(
					currency, exchRate, exchRateDate);
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
	 * 
	 * @param currency
	 * @param exchRate
	 * @return
	 */
	private ShippingTotalDTO initShippingTotal(String currency,
			Double exchRate, Date exchRateDate) {
		shipMethod = this.getUnitedShipMethod();
		ShippingTotalDTO shippingTotalDTO = (ShippingTotalDTO) SessionUtil
				.getRow(SessionConstant.OrderShippingTotal.value(), sessOrderNo);
		List<OrderPackageDTO> packageList = this.getPackageListForCalTotal();
		if (packageList == null || packageList.size() == 0) {
			if (shippingTotalDTO != null) {
				SessionUtil.deleteRow(
						SessionConstant.OrderBillingTotal.value(), sessOrderNo);
			}
			return null;
		}
		if (shippingTotalDTO != null) {
			shippingTotalDTO.setShipVia(shipMethod);
			return shippingTotalDTO;
		}
		shippingTotalDTO = orderService.getShippingTotalByPage(currency,
				packageList, exchRate, exchRateDate);
		shippingTotalDTO.setShipVia(shipMethod);
		SessionUtil.insertRow(SessionConstant.OrderShippingTotal.value(),
				sessOrderNo, shippingTotalDTO);
		return shippingTotalDTO;
	}

	/**
	 * 获得统一的order item 中的shipMethod，如果不一致返回0.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Integer getUnitedShipMethod() {
		Integer tmpShipMethod = 0;
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (itemMap != null && itemMap.size() > 0) {
			for (String key : itemMap.keySet()) {
				OrderItemDTO orderItemDTO = itemMap.get(key);
				if (tmpShipMethod != null && tmpShipMethod.intValue() != 0
						&& orderItemDTO != null
						&& orderItemDTO.getShipMethod() != null
						&& !tmpShipMethod.equals(orderItemDTO.getShipMethod())) {
					tmpShipMethod = 0;
					break;
				} else {
					if (orderItemDTO != null) {
						tmpShipMethod = orderItemDTO.getShipMethod();
					}
				}
			}
		}
		return tmpShipMethod;
	}

	/**
	 * 获得当前的Package List
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderPackageDTO> getPackageListForCalTotal() {
		Map<String, OrderPackageDTO> packageMap = (Map<String, OrderPackageDTO>) SessionUtil
				.getRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		List<OrderPackageDTO> packageList = new ArrayList<OrderPackageDTO>();
		if (StringUtils.isNumeric(sessOrderNo)
				&& (packageMap == null || packageMap.isEmpty())) {
			packageList = orderService.getBaseOrderOrderPackageList(Integer
					.parseInt(sessOrderNo));
			if (packageList != null && !packageList.isEmpty()) {
				packageMap = SessionUtil.convertList2Map(packageList,
						"packageId");
				Iterator<Entry<String, OrderPackageDTO>> it = packageMap
						.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, OrderPackageDTO> entry = it.next();
					OrderPackageDTO orderPackageDTO = entry.getValue();
					orderPackageDTO.setPackageId(null);
				}
			}
		}
		List<OrderPackageDTO> rtPackageList = new ArrayList<OrderPackageDTO>();
		if (packageMap != null) {
			SessionUtil.updateRow(SessionConstant.OrderPackage.value(),
					sessOrderNo, packageMap);
			Iterator<Entry<String, OrderPackageDTO>> it = packageMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderPackageDTO> entry = it.next();
				OrderPackageDTO orderPackageDTO = entry.getValue();
				rtPackageList.add(orderPackageDTO);
			}
		}
		return rtPackageList;
	}

	/**
	 * 更新所有的package 的 shippingAccount
	 * 
	 * @param shippingAccount
	 */
	@SuppressWarnings("unchecked")
	private void updateShippingAccount(String shippingAccount) {
		Map<String, OrderPackageDTO> sessPackageMap = (Map<String, OrderPackageDTO>) SessionUtil
				.getRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		List<OrderPackageDTO> packageList = new ArrayList<OrderPackageDTO>();
		if (StringUtils.isNumeric(sessOrderNo)) {
			packageList = orderService.getPackageList(Integer
					.parseInt(sessOrderNo));
		}
		Map<String, OrderPackageDTO> dbPackageMap = SessionUtil
				.convertList2Map(packageList, "packageId");
		Map<String, OrderPackageDTO> packageMap = SessionUtil.mergeList(
				sessPackageMap, dbPackageMap, 1);
		if (packageMap != null) {
			Iterator<Entry<String, OrderPackageDTO>> it = packageMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderPackageDTO> entry = it.next();
				OrderPackageDTO orderPackageDTO = entry.getValue();
				orderPackageDTO.setCustomerCharge(0.0);
			}
			SessionUtil.updateRow(SessionConstant.OrderPackage.value(),
					sessOrderNo, packageMap);
		}
	}

	/**
	 * 修改currency
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String changeCurrency() throws Exception {
		OrderMainDTO order = new OrderMainDTO();
		if (sessOrderNo == null || !StringUtil.isNumeric(sessOrderNo)) {
			order.setOrderCurrency(toCurrency);
			Struts2Util.renderText(SUCCESS);
			return null;
		}
		order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (itemMap == null) {// 为空直接更改order currency,然后返回。
			order.setOrderCurrency(toCurrency);
			Struts2Util.renderText(SUCCESS);
			return null;
		}
		Integer custNo = order.getCustNo();
		CustomerDTO customer = this.customerService.getCustomerBase(custNo);
		String country = customer.getCountry();
		String state = customer.getState();
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		List<CurrencyChangeDTO> currencyDTOList = new ArrayList<CurrencyChangeDTO>();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			CurrencyChangeDTO currencyChangeDTO = new CurrencyChangeDTO();
			currencyChangeDTO.setItemId(tmpId);
			currencyChangeDTO.setCatalogNo(orderItemDTO.getCatalogNo());
			currencyChangeDTO.setQuantity(orderItemDTO.getQuantity());
			if (orderItemDTO.getBasePrice() == null
					|| orderItemDTO.getBasePrice().doubleValue() <= 0) {
				BigDecimal basePrice = orderService.getOrderChangeCurrency(
						orderItemDTO.getUnitPrice(), null,
						order.getOrderCurrency(), CurrencyType.USD.value());
				orderItemDTO.setBasePrice(basePrice);
			}
			currencyChangeDTO.setBasePrice(orderItemDTO.getBasePrice()
					.doubleValue());
			currencyChangeDTO.setDiscount(orderItemDTO.getDiscount()
					.doubleValue());
			currencyChangeDTO.setState(state);
			currencyChangeDTO.setCountry(country);
			currencyDTOList.add(currencyChangeDTO);
		}
		fromCurrency = order.getBaseCurrency();
		Date exchRateDate = order.getExchRateDate();
		if (exchRateDate == null) {
			exchRateDate = new Date();
		}
		List<CurrencyChangeDTO> retList = orderService.changeCurrency(
				exchRateDate, currencyDTOList, fromCurrency, toCurrency);
		String toSymbol = publicService.getCurrencySymbol(toCurrency);
		for (int i = 0; i < retList.size(); i++) {
			CurrencyChangeDTO currencyChangeDTO = retList.get(i);
			String itemId = currencyChangeDTO.getItemId();
			if (itemMap.containsKey(itemId)) {
				OrderItemDTO tmpItemDTO = itemMap.get(itemId);
				tmpItemDTO.setCurrencyCode(toCurrency);
				int small = CurrencyType.JPY.value().equals(toCurrency) ? 0 : 2;
				tmpItemDTO
						.setAmount(new BigDecimal(currencyChangeDTO.getAmount())
								.setScale(small, BigDecimal.ROUND_HALF_UP));
				tmpItemDTO.setUnitPrice(new BigDecimal(currencyChangeDTO
						.getUnitPrice()).setScale(small,
						BigDecimal.ROUND_HALF_UP));
				tmpItemDTO.setDiscount(new BigDecimal(currencyChangeDTO
						.getDiscount()).setScale(small,
						BigDecimal.ROUND_HALF_UP));
				tmpItemDTO.setTax(new BigDecimal(currencyChangeDTO.getTax())
						.setScale(small, BigDecimal.ROUND_HALF_UP));
				tmpItemDTO.setUpSymbol(toSymbol);
				itemMap.put(itemId, tmpItemDTO);
			}
		}
		order.setOrderCurrency(toCurrency);
		SessionUtil.deleteRow(SessionConstant.OrderShippingTotal.value(),
				sessOrderNo);
		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		TotalDTO totalDTO = new TotalDTO();
		SessionUtil.insertRow(SessionConstant.OrderBillingTotal.value(),
				sessOrderNo, totalDTO);
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/**
	 * 手动修改shipping fee
	 * 
	 * @return
	 */
	public String changeCost() {
		String shipAmtChanged = "Y";
		System.out.println("customizedCost:>>" + customizedCost);
		if (StringUtils.isEmpty(this.customizedCost)) {
			shipAmtChanged = "N";
		}
		System.out.println("shipAmtChanged:>>" + shipAmtChanged);
		System.out.println("sessOrderNo:>>" + sessOrderNo);
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		if (shipAmtChanged.equalsIgnoreCase("Y")) {
			order.setShipAmt(Double.parseDouble(this.customizedCost));
		}
		order.setShipAmtChanged(shipAmtChanged);
		return null;
	}

	/**
	 * 计算并更新item的价格
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String calAllItemPrice() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		if (prmtCode == null) {
			OrderPromotionDTO promotionDTO = order.getOrderPromotion();
			if (promotionDTO != null) {
				prmtCode = promotionDTO.getPrmtCode();
			}
		}
		if (StringUtils.isEmpty(prmtCode)) {
			prmtCode = null;// orderservice 中会以null进行判断
		}
		Integer custNo = order.getCustNo();
		if (order.getOrderAddrList() == null
				|| order.getOrderAddrList().getShipToAddr() == null) {
			rt.put("message", "Ship To Address can not be null.");
			Struts2Util.renderJson(rt);
			return null;
		}
		String country = order.getOrderAddrList().getShipToAddr().getCountry();
		String state = order.getOrderAddrList().getShipToAddr().getState();
		String baseCurrency = order.getBaseCurrency();
		String orderCurrency = order.getOrderCurrency();
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if(itemMap != null && itemMap.size() > 0){
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO orderItemDTO = entry.getValue();
				if(orderItemDTO != null && "SERVICE".equalsIgnoreCase(orderItemDTO.getType())){
					if(orderItemDTO.getClsId() == 1 || orderItemDTO.getClsId() == 31){
						orderService.setItemAllMoreDetail(itemMap, orderItemDTO);
					}
				}
			}
		}
		if (itemMap == null) {
			rt.put("message", "Item list none");
			Struts2Util.renderJson(rt);
			return null;
		}
		List<OrderItemDTO> itemList = SessionUtil.convertMap2List(itemMap);
		try {
			/*** mod by lizhang promotion apply **/
			boolean flg = false;
			if (order.getOrderPromotion() != null) {
				clearOrderDiscount(sessOrderNo);
				clearOrderGift(sessOrderNo);
			}
			if (!systemSettingService.isPrmtOk(custNo, prmtCode, sessOrderNo,
					"order")) {
				if (order.getOrderPromotion() != null
						&& prmtCode != null
						&& prmtCode.equals(order.getOrderPromotion()
								.getPrmtCode())) {
					order.setOrderPromotion(null);
				}
				rt.put("message",
						"Failed to apply the promotion for it does not meet the conditions.");
				Struts2Util.renderJson(rt);
				return null;
			}
			String result = quoteOrderService.calProductItemPrice(itemList,
					custNo, prmtCode, baseCurrency, orderCurrency, country,
					state, order.getOrderDate());
			if (result != null) {
				flg = true;
			}
			if (StringUtils.isNotBlank(prmtCode)) {
				result = orderService.calGiftItemDiscount(itemList,
						sessOrderNo, prmtCode, custNo, order.getWarehouseId());
			}
			if (flg && result != null) {
				if (order.getOrderPromotion() != null
						&& prmtCode != null
						&& prmtCode.equals(order.getOrderPromotion()
								.getPrmtCode())) {
					order.setOrderPromotion(null);
				}
				rt.put("message", result);
				Struts2Util.renderJson(rt);
				return null;
			} else if (StringUtils.isNotEmpty(prmtCode)
					&& (order.getOrderPromotion() == null || !StringUtils
							.isNotEmpty(order.getOrderPromotion().getPrmtCode()))) {
				OrderPromotionDTO orderPromotion = new OrderPromotionDTO();
				orderPromotion.setPrmtCode(prmtCode);
				order.setOrderPromotion(orderPromotion);
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
		Iterator<Entry<String, OrderItemDTO>> it2 = itemMap.entrySet()
				.iterator();
		Double totalAmount = 0.0;
		Double totalDiscount = 0.0;
		Double totalTax = 0.0;
		while (it2.hasNext()) {
			Entry<String, OrderItemDTO> entry2 = it2.next();
			OrderItemDTO orderItemDTO2 = entry2.getValue();
			if ("CN".equalsIgnoreCase(orderItemDTO2.getStatus())) {
				continue;
			}
			if (quoteOrderService.isGiftItem(orderItemDTO2.getCatalogNo(), orderItemDTO2.getType())) {
				continue;
			}
			totalAmount = orderItemDTO2.getAmount().add(new BigDecimal(totalAmount)).doubleValue();
			System.out.println("-------------------itemNo:"+orderItemDTO2.getItemNo()+"-totalAmount="+totalAmount);
			totalDiscount = orderItemDTO2.getDiscount().add(new BigDecimal(totalDiscount)).doubleValue();
			totalTax = orderItemDTO2.getTax().add(new BigDecimal(totalTax)).doubleValue();
		}
		if (totalAmount == null || totalAmount.doubleValue() < 0) {
			order.setSubTotal(0d);
		} else {
			order.setSubTotal(totalAmount);
		}
		System.out.println("-------------------------OrderTotalAmount="+totalAmount);
		order.setDiscount(totalDiscount);
		if (country.equalsIgnoreCase("JP")) {
			if (order.getShipAmt() != null) {
				totalTax = ArithUtil.add(ArithUtil.mul(order.getShipAmt(), 0.05), totalTax);
			}
		}
		order.setTax(totalTax);
		if (order.getCouponValue() == null) {
			order.setCouponValue(0d);
		}
		if (order.getShipAmt() == null) {
			order.setShipAmt(0d);
		}
		Double orderTotalBD = totalAmount - totalDiscount - order.getCouponValue() + order.getShipAmt() + totalTax;
		order.setAmount(orderTotalBD);
		rt.put("message", "Success");
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 计算bill total
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calBill() throws Exception {
		TotalDTO orderTotalDTO = null;
		ShippingTotalDTO shippingTotalDTO = (ShippingTotalDTO) SessionUtil
				.getRow(SessionConstant.OrderShippingTotal.value(), sessOrderNo);
		if (shippingTotalDTO == null) {
			Struts2Util.renderJson(orderTotalDTO);
			return null;
		}
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		Double amount = order.getAmount();
		Double subTotal = order.getSubTotal();
		Double discount = order.getDiscount();
		Double tax = order.getTax();
		Double couponValue = order.getCouponValue();
		String toCurrency = order.getOrderCurrency();
		Double customerCharge = null;
		if (!StringUtils.isEmpty(order.getShippingAccount())) {
			customerCharge = 0.0;
		} else if (!"Y".equalsIgnoreCase(order.getShipAmtChanged())) {
			customerCharge = shippingTotalDTO == null ? 0 : shippingTotalDTO
					.getCostTotal();
		} else {
			customerCharge = order.getShipAmt();
		}
		boolean isJPCountry = false;
		if (order.getOrderAddrList() != null
				&& order.getOrderAddrList().getShipToAddr() != null
				&& CountryCode.JP.value().equalsIgnoreCase(
						order.getOrderAddrList().getShipToAddr().getCountry())) {
			isJPCountry = true;
		}
		BigDecimal payments = orderService.getTotalPaymentsAmount_new(// 按照 最新的
																		// 表来修改。
				Integer.parseInt(sessOrderNo), toCurrency, new Date());
		String handlingFee = shippingTotalDTO == null ? "0" : shippingTotalDTO
				.getHandlingFee();
		orderTotalDTO = quoteOrderService.getMyTotals(amount, subTotal,
				discount, tax, customerCharge, handlingFee, toCurrency,
				payments, couponValue, isJPCountry);
		BigDecimal prePayment = orderService.getPrePayment(order.getCustNo(),
				Integer.parseInt(sessOrderNo), orderTotalDTO.getQuorderTotal().doubleValue(), toCurrency);
		orderTotalDTO.setPrePayment(prePayment);
		SessionUtil.insertRow(SessionConstant.OrderBillingTotal.value(), sessOrderNo, orderTotalDTO);
		Struts2Util.renderJson(orderTotalDTO);
		return null;
	}

	private List<ArInvoicePayment> Paymentlist;
	private BigDecimal totalpayments;
// add by  zhou gang  2011 11 18
	public String caltotalPaymentslist() {
		System.out.println(sessOrderNo);
		if (StringUtils.isNumeric(sessOrderNo)) {
			OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
					SessionConstant.Order.value(), sessOrderNo);

			String toCurrency = order.getOrderCurrency();
			totalpayments = orderService.getTotalPaymentsAmount_new(
					Integer.parseInt(sessOrderNo), toCurrency, new Date());

			Paymentlist = orderService.getTotalPaymentsList(Integer
					.parseInt(sessOrderNo));

			ServletActionContext.getRequest().setAttribute("Paymentlist", Paymentlist);
		}
		return "orderTotalPaymentsView";
	}

	public BigDecimal getTotalpayments() {
		return totalpayments;
	}

	public void setTotalpayments(BigDecimal totalpayments) {
		this.totalpayments = totalpayments;
	}

	public List<ArInvoicePayment> getPaymentlist() {
		return Paymentlist;
	}

	public void setPaymentlist(List<ArInvoicePayment> paymentlist) {
		Paymentlist = paymentlist;
	}

	/**
	 * replace order
	 */
	public String replace() throws Exception {
		if (StringUtils.isNumeric(sessOrderNo)) {
			try {
				orderService.replaceOrder(Integer.parseInt(sessOrderNo),
						SessionUtil.getUserId());
				Struts2Util.renderText(SUCCESS);
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				Struts2Util.renderText(exDTO.getMessageDetail() + "\n"
						+ exDTO.getAction());
			}
		}
		return null;
	}

	/**
	 * applyCouponCode
	 */
	public String applyCouponCode() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String result = this.orderService
					.applyCoupon(couponId, sessOrderNo);
			if (StringUtils.isEmpty(result)) {
				rt.put("message", "Success");
			} else {
				rt.put("message", result);
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
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * @param sessNoVal
	 */
	@SuppressWarnings("unchecked")
	private void clearOrderDiscount(String sessNoVal) {
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessNoVal);
		if (itemMap != null && !itemMap.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				OrderItemDTO val = (OrderItemDTO) itemMap.get(key);
				val.setDiscount(new BigDecimal(0.0));
				SessionUtil.updateOneRow(SessionConstant.OrderItemList.value(),
						sessNoVal, key, val);
				sb.append(val.getCatalogNo()).append("@@0||");
			}
		}
		SessionUtil.insertRow(SessionConstant.OrderBillingTotalFlag.value(),
				sessNoVal, 0);
	}

	/**
	 * @param sessNoVal
	 */
	private void clearOrderGift(String sessNoVal) {
		this.orderService.deleteGifItem(this.prmtCode, sessNoVal);
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

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
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

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getShippingType() {
		return shippingType;
	}

	public String getShippingRule() {
		return shippingRule;
	}

	/*
	 * public String getPrmtCode() { return prmtCode; }
	 */

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
