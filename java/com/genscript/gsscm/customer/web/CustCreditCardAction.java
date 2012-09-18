package com.genscript.gsscm.customer.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.accounting.entity.ApInvoicePayment;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.accounting.service.ApInvoicePaymentService;
import com.genscript.gsscm.accounting.service.ArInvoicePaymentService;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dao.CreditCardDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Address;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.service.AddressService;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShippingChargeLog;

@SuppressWarnings("serial")
@Results({
		@Result(name = "ChargerRefunding", location = "customer/cardChangeRefund.jsp"),
		@Result(name = "cardChangeForm", location = "customer/cardChangeForm.jsp"),
		@Result(name = "transactionListByOrdernNo", location = "customer/transactionByOrdernNoList.jsp"),
		@Result(location = "customer/cust_credit_search_result.jsp") })
public class CustCreditCardAction extends BaseAction<OrderMainBean> {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private CreditCardDao creditCardDao;
	@Autowired
	private ArInvoicePaymentService arInvoicePaymentService;
	@Autowired
	private AddressService addservice;
	@Autowired
	private PublicService publicService;

	private List<OrderMainBean> orderList;
	private Map<Integer, String> addressMap;
	private ArInvoicePayment arInvoicePayment;
	private String orderNo;
	private BigDecimal totalPayment;
	private BigDecimal totalRefund;
	private String billingAddress;
	private BigDecimal orderTotal;
	private BigDecimal totalPayments;
	private String cardtype;
	private String customerCardNo;
	private String cardholderName;
	private String empimeryear;
	private String empimermonth;
	private String cvcs;
	private BigDecimal balance;
	private Map<String, List<PbDropdownListOptions>> dropDownList;
	private Page<ArInvoicePayment> arInvoicePaymentpage;
	private OrderMainBean orderMainBean;
	private Page<OrderMainBean> page = new Page<OrderMainBean>(20);
	private Integer custNo;
	private String country;
	private String cardStatus;

	private String currency;

	public String saveTotransaction() throws Exception {
		String returnStr = "";
		String orderNo = Struts2Util.getRequest().getParameter("orderNO");
		String currency = Struts2Util.getRequest().getParameter("currency");
		Integer custno = 0;
		Map<String, Object> rt = new HashMap<String, Object>();
		if (orderNo != null && !"".equals(orderNo)) {
			OrderMain om = this.orderService
					.getOrder(Integer.parseInt(orderNo));
			Address addr = null;
			if (om != null) {
				Integer billaddress = om.getBilltoAddrId();
				addr = this.addservice.getAddress(billaddress);
				country = addr.getCountry();
				custno = om.getCustNo();
				System.out.println(country);
			}
			System.out.println("======== =======信用卡银行接口的环节============= ===");
			String action = "ns_quicksale_cc";
			String acctid = "FC14L";
			if (country != null && !country.equals("US")
					&& !country.equals("CA")) {
				acctid = "FCX2U";
			}
			String Accepturl = "http://genscript.com/cgi-bin/ccreceipt.cgi";
			String Declineurl = "http://genscript.com/cgi-bin/ccreceipt.cgi";
			String ccname = arInvoicePayment.getCcCardHolder();// "Frank Zhang";
			String ccnum = arInvoicePayment.getAccountNo(); // "5454545454545454";
			String cvv2 = arInvoicePayment.getCcCvc();// "689";
			String amount = arInvoicePayment.getAmount().toString();// "0.2";
			String expmon = ServletActionContext.getRequest().getParameter(
					"month"); // "07";
			String expyear = ServletActionContext.getRequest().getParameter(
					"year");// "2012";
			String ci_companyname = "Genscript Corp";
			String Authonly = "1";
			String usepost = "1";
			String ci_billadd1 = addr.getAddrLine1();
			String ci_billcity = addr.getCity();
			String ci_billstate = addr.getState();
			String ci_billcountry = addr.getCountry();
			String ci_billzip = addr.getZipCode();
			StringBuffer url = new StringBuffer(
					"https://trans.merchantpartners.com/cgi-bin/process.cgi");
			StringBuffer param = new StringBuffer();
			try {

				param.append("action=" + action + "&acctid=" + acctid
						+ "&Accepturl=" + Accepturl + "&Declineurl="
						+ Declineurl + "&ccname=" + ccname + "&ccnum=" + ccnum
						+ "&cvv2=" + cvv2 + "&amount=" + amount + "&expmon="
						+ expmon + "&expyear=" + expyear + "&ci_companyname="
						+ ci_companyname + "&Authonly=" + Authonly
						+ "&usepost=" + usepost + "&ci_billadd1=" + ci_billadd1
						+ "&ci_billcity=" + ci_billcity + "&ci_billstate="
						+ ci_billstate + "&currency=" + currency
						+ "&ci_billcountry=" + ci_billcountry + "&ci_billzip="
						+ ci_billzip);
				System.out.println(param.toString());
				returnStr = this.postReq(url.toString(), param.toString());
				System.out
						.println("带参数的url=============================================="
								+ url);
				System.out.println("returnStr=" + returnStr);
				if (returnStr != null && !returnStr.equals("")) {
					System.out.println("returnStr=" + returnStr);
					String[] retruns = returnStr.split("Accepted");
					if (retruns.length > 1) {
						if (arInvoicePayment != null
								&& !"".equals(arInvoicePayment)) {
							System.out.println(arInvoicePayment.getAccountNo());
							Integer createdBy = SessionUtil.getUserId();
							Date creationDate = new Date();
							Date modifyDate = new Date();
							Integer modifiedBy = createdBy;
							Integer approvedBy = createdBy;
							Date transactionDate = new Date();
							Date ApproveDate = new Date();
							arInvoicePayment.setCreatedBy(createdBy);
							arInvoicePayment.setCreationDate(creationDate);
							arInvoicePayment.setApprovedBy(approvedBy);
							arInvoicePayment.setModifiedBy(modifiedBy);
							arInvoicePayment.setModifyDate(modifyDate);
							arInvoicePayment.setOrderNo(orderNo);
							System.out.println("  =================" + custno);
							arInvoicePayment.setCustNo(custno);
							arInvoicePayment
									.setTransactionDate(transactionDate);
							arInvoicePayment.setApproveDate(ApproveDate);
							arInvoicePayment.setCurrency(currency);
							this.arInvoicePaymentService
									.saveArInvoicePayment(arInvoicePayment);
							rt.put("message", "Save success!");
							Struts2Util.renderJson(rt);
							return null;
						}
					} else {
						rt.put("message", "Save error!");
						Struts2Util.renderJson(rt);
						return null;
					}

				}
				rt.put("message", "Save error!");
				Struts2Util.renderJson(rt);
				return null;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		rt.put("message", "Save error!");
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 5454545454545454 get请求
	 */

	/**
	 * post请求
	 */
	private String postReq(String url, String param) throws Exception {
		String resultStr = null;
		StringBuffer readOneLineBuff = new StringBuffer();
		URL postUrl = new URL(url);
		// 打开连接
		if (url.startsWith("https://")) {
			HttpsURLConnection connection = (HttpsURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"text/html;charset=utf-8");
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.write(param.getBytes());
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				readOneLineBuff.append(line);
			}
			connection.disconnect();
		} else {
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "text/html");
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(param);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				readOneLineBuff.append(line);
			}
			connection.disconnect();
		}
		resultStr = readOneLineBuff.toString();
		return resultStr;
	}

	@Override
	public String list() throws Exception {
		PagerUtil<OrderMainBean> pagerUtil = new PagerUtil<OrderMainBean>();
		Page<OrderMainBean> page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("orderNo");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(20);
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
//		List<PropertyFilter> poNofilters = WebUtil.buildPropertyFilters(
//				ServletActionContext.getRequest(), "searcher_");
//		if (poNofilters != null && poNofilters.size() > 0) {
//			Page<PaymentVoucher> pageOrderMain = new Page<PaymentVoucher>();
//			pageOrderMain.setPageSize(20);
//			Set<Integer> orderNoSet = orderService.getOrderNoSetByPoNo(
//					pageOrderMain, poNofilters);
//			if (!orderNoSet.isEmpty()) {
//				page = orderService.searchOrder(page, filters, orderNoSet);
//			} else {
//				page.setTotalCount(0L);//
//			}
//		} else {
			page = orderService.searchOrderByfilter(page, filters);
//		}
		orderList = page.getResult();
		addressMap = new HashMap<Integer, String>();
		if (orderList != null && !orderList.isEmpty()) {
			for (int i = 0; i < orderList.size(); i++) {
				OrderMainBean tmpOrderMainBean = orderList.get(i);
				int orderNo = tmpOrderMainBean.getOrderNo();
				String address = AddressUtil.getFullAddress(
						tmpOrderMainBean.getAddrLine1(),
						tmpOrderMainBean.getAddrLine2(),
						tmpOrderMainBean.getAddrLine3(),
						tmpOrderMainBean.getCity(),
						tmpOrderMainBean.getState(),
						tmpOrderMainBean.getZipCode(),
						tmpOrderMainBean.getCountry());
				addressMap.put(orderNo, address);
			}
		}
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("CREDIT_CARD_TYPE",
				publicService.getDropdownList("CREDIT_CARD_TYPE"));
		dropDownList.put("AR_TRANSACTION_TYPE",
				publicService.getDropdownList("AR_TRANSACTION_TYPE"));
		if (orderNo != null && !"".equals(orderNo)) {
			OrderAddress oad = null;
			oad = this.orderAddressDao.getAddrByOrderNoAndType(
					Integer.parseInt(orderNo), "BILL_TO");
			if (oad != null && !"".equals(oad)) {
				// 获取billingaddress
				if (oad.getAddrLine1() != null
						&& !"".equals(oad.getAddrLine1())) {
					billingAddress = oad.getAddrLine1();
				}
			}
			OrderMain om = this.orderService
					.getOrder(Integer.parseInt(orderNo));
			String empimer = "";
			if (om != null) {
				CreditCard ccss = null;
				Integer custNo = om.getCustNo();
				List<CreditCard> ccdlist = this.creditCardDao.findBy("custNo",
						custNo);
				if (ccdlist != null && ccdlist.size() > 0) {
					ccss = ccdlist.get(0);
				}
				if (ccss != null && !"".equals(ccss)) {
					cardtype = ccss.getCardType();
					customerCardNo = ccss.getCardNo();
					cardholderName = ccss.getCardHolder();
					cvcs = ccss.getCvc();
					if (ccss.getExprDate() != null
							&& !"".equals(ccss.getExprDate())) {
						empimer = sdf.format(ccss.getExprDate());
					}
					if (empimer != null && !"".equals(empimer)) {// 2011-05-02
						empimeryear = empimer.substring(0, 4);
						empimermonth = empimer.substring(5, 7);
					}
					cardStatus = ccss.getStatus();
				}
				currency = om.getOrderCurrency();
				// 一些信息。
				if (om.getAmount() != null && !"".equals(om.getAmount())) {
					orderTotal = om.getAmount(); // 获取orderTotal
					String orderChency = om.getOrderCurrency();
					Date orderDate = om.getOrderDate();
					if (orderChency != null && !"".equals(orderChency)
							&& orderDate != null && !"".equals(orderDate)) {
						totalPayments = this.arInvoicePaymentService
								.gettotalPaymentsByOrderNo(
										Integer.parseInt(orderNo), orderChency,
										orderDate);
					}
				}
			}

			if (totalPayments != null && !"".equals(totalPayments)
					&& orderTotal != null && !"".equals(orderTotal)) {
				balance = orderTotal.subtract(totalPayments);
			}
		}
		return "ChargerRefunding";
	}

	public String transactionListByOrdernNo() {
		if (orderNo != null && !"".equals(orderNo)) {
			PagerUtil<ArInvoicePayment> pagerUtil = new PagerUtil<ArInvoicePayment>();
			arInvoicePaymentpage = pagerUtil.getRequestPage();
			if (!arInvoicePaymentpage.isOrderBySetted()) {
				arInvoicePaymentpage.setOrder(Page.DESC);
			}
			arInvoicePaymentpage.setPageSize(20);
			arInvoicePaymentpage = this.arInvoicePaymentService
					.GetAllPaymentpage(arInvoicePaymentpage, orderNo);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					arInvoicePaymentpage);
		}
		totalRefund = this.arInvoicePaymentService.getTotalRefund(orderNo);
		totalPayment = this.arInvoicePaymentService.getTotalPayment(orderNo);

		return "transactionListByOrdernNo";
	}

	public Page<ArInvoicePayment> getArInvoicePaymentpage() {
		return arInvoicePaymentpage;
	}

	public void setArInvoicePaymentpage(
			Page<ArInvoicePayment> arInvoicePaymentpage) {
		this.arInvoicePaymentpage = arInvoicePaymentpage;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(BigDecimal totalPayment) {
		this.totalPayment = totalPayment;
	}

	public BigDecimal getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(BigDecimal totalRefund) {
		this.totalRefund = totalRefund;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	public OrderMainBean getOrderMainBean() {
		return orderMainBean;
	}

	public Map<Integer, String> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<Integer, String> addressMap) {
		this.addressMap = addressMap;
	}

	public void setOrderMainBean(OrderMainBean orderMainBean) {
		this.orderMainBean = orderMainBean;
	}

	public List<OrderMainBean> getOrderList() {
		return orderList;
	}

	public ArInvoicePayment getArInvoicePayment() {
		return arInvoicePayment;
	}

	public void setArInvoicePayment(ArInvoicePayment arInvoicePayment) {
		this.arInvoicePayment = arInvoicePayment;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownList() {
		return dropDownList;
	}

	public void setDropDownList(
			Map<String, List<PbDropdownListOptions>> dropDownList) {
		this.dropDownList = dropDownList;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getTotalPayments() {
		return totalPayments;
	}

	public void setTotalPayments(BigDecimal totalPayments) {
		this.totalPayments = totalPayments;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String cardChanger() {
		return "cardChangeForm";
	}

	public void setOrderList(List<OrderMainBean> orderList) {
		this.orderList = orderList;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public void setPage(Page<OrderMainBean> page) {
		this.page = page;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Page<OrderMainBean> getPage() {
		return page;
	}

	@Override
	public OrderMainBean getModel() {
		return orderMainBean;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCvcs() {
		return cvcs;
	}

	public void setCvcs(String cvcs) {
		this.cvcs = cvcs;
	}

	public String getCustomerCardNo() {
		return customerCardNo;
	}

	public void setCustomerCardNo(String customerCardNo) {
		this.customerCardNo = customerCardNo;
	}

	public String getEmpimeryear() {
		return empimeryear;
	}

	public void setEmpimeryear(String empimeryear) {
		this.empimeryear = empimeryear;
	}

	public String getEmpimermonth() {
		return empimermonth;
	}

	public void setEmpimermonth(String empimermonth) {
		this.empimermonth = empimermonth;
	}
}
