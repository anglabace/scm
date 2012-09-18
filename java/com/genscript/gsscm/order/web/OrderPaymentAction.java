package com.genscript.gsscm.order.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dto.PaymentTermDTO;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.olddb.entity.Charger;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.PaymentVoucherDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.shipment.dto.BankCardDTO;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShippingChargeLog;
import com.genscript.gsscm.shipment.service.ShippingService;

/**
 * 处理 order payment 相关信息
 * 
 * @author Administrator
 * 
 */
@Results( { @Result(name = "payment_list", location = "order/order_payment_list.jsp") })
public class OrderPaymentAction extends BaseAction<PaymentVoucherDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4964903848197948886L;
	private final static String SUCCESS = "SUCCESS";
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private FileService fileService;
	@Autowired
	private ShippingService shippingService;
	@Autowired
	private CustomerDao customerDao;

	private String sessOrderNo;
	private Map<String, PaymentVoucherDTO> paymentMap;
	private String quorderStatus;
	private List<PaymentTermDTO> termList;
	private List<CreditCard> cardList;
	private List<PbDropdownListOptions> cardTypeList;
	private PaymentVoucherDTO payment;
	private String sessVoucherId;
	private Integer saveCardFlag;
	private Integer custNo;
	private List<Integer> tenYearList;
	private Integer orderNoShow;
	private int ccPayAllowFlag;
	// 文件处理
	/**
	 * 实际上传文件
	 */
	private List<File> upload;
	/**
	 * 文件的内容类型
	 */
	private List<String> uploadContentType;
	/**
	 * 上传文件名
	 */
	private List<String> uploadFileName;

	/**
	 * 删除payment
	 */
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		SessionUtil.deleteOneRow(SessionConstant.OrderPayment.value(),
				sessOrderNo, sessVoucherId);
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/**
	 * 显示编辑
	 */
	@Override
	public String input() throws Exception {
		return null;
	}

	/**
	 * 显示列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		this.tenYearList = this.getTenYearList();
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		orderNoShow = order.getOrderNo();
		quorderStatus = order.getStatus();
		custNo = order.getCustNo();
		Customer customer = customerDao.findByCustNo(custNo);
		if(customer != null){
			String payFlag = customer.getCcpayFlag();
			if("N".equalsIgnoreCase(payFlag)){
				ccPayAllowFlag = 1;
			}
		}
		// 下拉列表
		termList = customerService.getPaymentTermList();
		cardList = orderService.getCustActiveCardList(custNo);
		cardTypeList = publicService.getDropdownList("CREDIT_CARD_TYPE");
		Map<String, PaymentVoucherDTO> dbPaymentMap = null;
		if (StringUtils.isNumeric(sessOrderNo)) {
			List<PaymentVoucherDTO> dbPaymentList = orderService
					.getPayInfoList(Integer.parseInt(sessOrderNo));
			dbPaymentMap = SessionUtil.convertList2Map(dbPaymentList,
					"voucherId");
		}
		Map<String, PaymentVoucherDTO> sessMap = (Map<String, PaymentVoucherDTO>) SessionUtil
				.getRow(SessionConstant.OrderPayment.value(), sessOrderNo);
		paymentMap = SessionUtil.mergeList(sessMap, dbPaymentMap, 1);
		return "payment_list";
	}

	/**
	 * 生成十年列表
	 * 
	 * @return
	 */
	public List<Integer> getTenYearList() {
		List<Integer> yearList = new ArrayList<Integer>();
		Date now = new Date();
		String nowDate = DateUtils.formatDate2Str(now, "yyyy");
		Integer thisYear = Integer.parseInt(nowDate);
		for (int i = 0; i < 10; i++) {
			yearList.add(thisYear.intValue() + i);
		}
		return yearList;
	}

	/**
	 * 初始化模型的数据
	 */
	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(sessVoucherId)) {
			payment = (PaymentVoucherDTO) SessionUtil.getOneRow(
					SessionConstant.OrderPayment.value(), sessOrderNo,
					sessVoucherId);
			if (StringUtils.isNumeric(sessVoucherId) && payment == null) {
				payment = orderService.getPayInfo(Integer
						.parseInt(sessVoucherId));
			}
		} else {
			payment = new PaymentVoucherDTO();
		}
	}

	/**
	 * 保存payment
	 */
	@Override
	public String save() throws Exception {
		//Map<String, Object> rt = new HashMap<String, Object>();
		String message = this.checkDataBeforeSave();
		if (!message.equalsIgnoreCase(SUCCESS)) {
			Struts2Util.renderText("error");
			return null;
		}
		boolean cc_verify = true;
		String busEmail = customerService.getBusEmailByCustomerNo(custNo);
		if(StringUtils.isNotBlank(busEmail)){
			if(busEmail.indexOf("@scripps.edu") > -1 || busEmail.indexOf("@yu.edu") > -1 || busEmail.indexOf("@mit.edu") > -1 || busEmail.indexOf("@u.washington.edu") > -1 || busEmail.indexOf("@mail.med.upenn.edu") > -1 || busEmail.indexOf("@slu.edu") > -1){
				cc_verify = false;
			}
		}
		if (payment.getPaymentType().equalsIgnoreCase("CC") && cc_verify) {
			if (payment.getCcType().equalsIgnoreCase("VI")
					|| payment.getCcType().equalsIgnoreCase("MC")) {
				if (payment.getCcNo().length() != 16) {
					Struts2Util
							.renderText("The credit card number did not match the card type that you selected. Please check the credit card number and select the proper credit card type.");
					return null;
				}
			}
			if (payment.getCcType().equalsIgnoreCase("AE")
					&& payment.getCcNo().length() != 15) {
				Struts2Util
						.renderText("The credit card number did not match the card type that you selected. Please check the credit card number and select the proper credit card type.");
				return null;
			}
			Date tranDate = orderService.getChargeLastDate(custNo, "CC",
					payment.getCcNo());
			String authNo = null;
			if (tranDate != null) {
				authNo = publicService.getAuthNo(payment.getCcNo(),
						payment.getCcHolder(), tranDate);
			}
			if (StringUtils.isEmpty(authNo)) {
				BankCardDTO bank = shippingService.selectForBand(sessOrderNo);
				// System.out.println("=======================信用卡银行接口的环节=======================");
				String action = "ns_quicksale_cc";
				String acctid = "FC14L";
				if (bank.getCountry() != null
						&& !bank.getCountry().equals("US")
						&& !bank.getCountry().equals("CA")) {
					acctid = "FCX2U";
				}
				String exprDate = DateUtils.formatDate2Str(
						payment.getCcExprDate(), "yyyy-MM-dd");

				String ccname = payment.getCcHolder();
				String ccnum = payment.getCcNo();
				String cvv2 = payment.getCcCvc();
				String amount = "1.00";// "0.2";
				String expmon = exprDate.substring(5, 7);
				String expyear = exprDate.substring(0, 4);// "2012";
				String ci_companyname = "Genscript Corp";

				String Authonly = "1";
				String usepost = "1";
				String ci_billadd1 = bank.getBillTo();
				String ci_billcity = bank.getCity();
				String ci_billstate = bank.getState();
				String ci_billcountry = bank.getCountry();
				String ci_billzip = bank.getZipCode();
				String Accepturl = "http://genscript.com/cgi-bin/ccreceipt.cgi";
				String Declineurl = "http://genscript.com/cgi-bin/ccreceipt.cgi";
				StringBuffer url = new StringBuffer(
						"https://trans.merchantpartners.com/cgi-bin/process.cgi");
				StringBuffer param = new StringBuffer();
				String returnStr = "";
				try {
					System.out
							.println("带参数的url=============================================="
									+ url);
					// user="+user+"&order_id="+order_id+"&merchantordernumber="+merchantordernumber+"&ci_memo="+ci_memo+"&
					param.append("action=" + action + "&acctid=" + acctid
							+ "&Accepturl=" + Accepturl + "&Declineurl="
							+ Declineurl + "&ccname=" + ccname + "&ccnum="
							+ ccnum + "&cvv2=" + cvv2 + "&amount=" + amount
							+ "&expmon=" + expmon + "&expyear=" + expyear
							+ "&ci_companyname=" + ci_companyname
							+ "&Authonly=" + Authonly + "&usepost=" + usepost
							+ "&ci_billadd1=" + ci_billadd1 + "&ci_billcity="
							+ ci_billcity + "&ci_billstate=" + ci_billstate
							+ "&ci_billcountry=" + ci_billcountry
							+ "&ci_billzip=" + ci_billzip);
					System.out.println(param.toString());
					returnStr = postReq(url.toString(), param.toString());
					System.out.println("returnStr=" + returnStr);
					if (returnStr != null && !returnStr.equals("")) {
						System.out.println("returnStr=" + returnStr);
						String[] retruns = returnStr.split("Accepted");
						String[] dretruns = returnStr.split("Declined");
						if (retruns.length > 1) {
							String ret1 = retruns[1];
							System.out.println(ret1);
							if (ret1 != null && ret1.indexOf("AuthNo:") > -1) {
								int i = ret1.indexOf("<br>Reason:");
								String authNO = ret1.substring(4, i);
								Charger charger = new Charger();
								charger.setCcNumber(ccnum);
								charger.setAuthno(authNO);
								charger.setCcHolder(ccname);
								charger.setCharger("1.00");
								charger.setOrderId(1001);
								charger.setStatus("Accepted");
								charger.setAmount(1.0f);
								charger.setTranDate(new Date());
								orderService.saveCharger(charger);
							}

						} else if (dretruns.length > 1) {
							String dret1 = dretruns[1];
							System.out.println(dret1);
							if (dret1 != null && dret1.indexOf("DECLINED:") > -1) {
								int i = dret1.indexOf("Reason:");
								String reason = dret1.substring(i+6, dret1.length()-4).replaceAll("<br>", " ");
								Struts2Util
								.renderText("Your Credit Card Information has the following error "+ reason +" Please check and  make any necessary changes.");
							}
							return null;
						}
					}
				} catch (Exception e) {
					returnStr = "Error";
					e.printStackTrace();
				}
			}

		}
		// 对时间格式进行转换
		if (payment != null) {
			payment.setCcExprDateStr(DateUtils.formatDate2Str(
					payment.getCcExprDate(), "yyyy-MM-dd"));
			payment.setPoDueDateStr(DateUtils.formatDate2Str(
					payment.getPoDueDate(), "yyyy-MM-dd"));
		}
		if (upload != null && upload.size() == 1) {
			for (int i = 0; i < upload.size(); i++) {
				Document doc = new Document();
				String srcFileName = uploadFileName.get(i);
				uploadFileName.set(i, SessionUtil.generateTempId() + "_"
						+ "order_" + srcFileName);
				doc.setFilePath("po/" + uploadFileName.get(i));
				doc.setDocName(srcFileName);
				payment.setDocument(doc);
			}
		}
		if (StringUtils.isEmpty(sessVoucherId)) {
			sessVoucherId = SessionUtil.generateTempId();
		}
		SessionUtil.updateOneRow(SessionConstant.OrderPayment.value(),
				sessOrderNo, sessVoucherId, payment);
		fileService.uploadFile(upload, uploadContentType, uploadFileName, "po");
		// 保存customer account
		if (saveCardFlag != null && saveCardFlag.intValue() == 1) {
			CreditCard card = new CreditCard();
			card.setCardHolder(payment.getCcHolder());
			card.setCardNo(payment.getCcNo());
			card.setCardType(payment.getCcType());
			card.setCustNo(custNo);
			card.setCvc(payment.getCcCvc());
			card.setExprDate(payment.getCcExprDate());
			customerService.saveCreditCard(card, SessionUtil.getUserId());
		}
		Struts2Util.renderHtml(SUCCESS);
		return null;
	}
	
	private String postReq(String url,String param) throws Exception{
		String resultStr = null;
		StringBuffer readOneLineBuff = new StringBuffer(); 
		URL postUrl = new URL(url);   
        // 打开连接   
		if(url.startsWith("https://")) {
			HttpsURLConnection connection = (HttpsURLConnection)postUrl.openConnection(); 
			connection.setDoOutput(true);     
		    connection.setDoInput(true);   
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty("Content-Type","text/html;charset=utf-8"); 
		    connection.connect();  
		    DataOutputStream out = new DataOutputStream(connection   
	                .getOutputStream()); 
		    out.write(param.getBytes());   
	        out.flush();   
	        out.close();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream()));   
	        String line=null;    
	        while ((line = reader.readLine()) != null) {   
	            readOneLineBuff.append(line);   
	        } 
	        connection.disconnect();
		} else {
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);     
		    connection.setDoInput(true);   
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty("Content-Type","text/html"); 
		    connection.connect();  
		    DataOutputStream out = new DataOutputStream(connection   
	                .getOutputStream()); 
		    out.writeBytes(param);   
	        out.flush();   
	        out.close();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream()));   
	        String line=null;   
	        while ((line = reader.readLine()) != null) {   
	            readOneLineBuff.append(line);   
	        } 
	        connection.disconnect();
		}    
        resultStr = readOneLineBuff.toString();
		return resultStr;
	}

	/**
	 * 删除附件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deletePoDoc() throws Exception {
		this.prepareModel();
		payment.setDocument(null);
		SessionUtil.updateOneRow(SessionConstant.OrderPayment.value(),
				sessOrderNo, sessVoucherId, payment);
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/**
	 * 验证数据
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
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		if (order.getOrderAddrList() == null
				|| order.getOrderAddrList().getShipToAddr() == null) {
			return "The address of 'Ship To' is required";
		}
		return SUCCESS;
	}

	public PaymentVoucherDTO getModel() {
		return payment;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public Map<String, PaymentVoucherDTO> getPaymentMap() {
		return paymentMap;
	}

	public void setPaymentMap(Map<String, PaymentVoucherDTO> paymentMap) {
		this.paymentMap = paymentMap;
	}

	public String getQuorderStatus() {
		return quorderStatus;
	}

	public void setQuorderStatus(String quorderStatus) {
		this.quorderStatus = quorderStatus;
	}

	public List<PaymentTermDTO> getTermList() {
		return termList;
	}

	public void setTermList(List<PaymentTermDTO> termList) {
		this.termList = termList;
	}

	public List<CreditCard> getCardList() {
		return cardList;
	}

	public void setCardList(List<CreditCard> cardList) {
		this.cardList = cardList;
	}

	public List<PbDropdownListOptions> getCardTypeList() {
		return cardTypeList;
	}

	public void setCardTypeList(List<PbDropdownListOptions> cardTypeList) {
		this.cardTypeList = cardTypeList;
	}

	public String getSessVoucherId() {
		return sessVoucherId;
	}

	public void setSessVoucherId(String sessVoucherId) {
		this.sessVoucherId = sessVoucherId;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public PaymentVoucherDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentVoucherDTO payment) {
		this.payment = payment;
	}

	public Integer getSaveCardFlag() {
		return saveCardFlag;
	}

	public void setSaveCardFlag(Integer saveCardFlag) {
		this.saveCardFlag = saveCardFlag;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public void setTenYearList(List<Integer> tenYearList) {
		this.tenYearList = tenYearList;
	}

	public Integer getOrderNoShow() {
		return orderNoShow;
	}

	public void setOrderNoShow(Integer orderNoShow) {
		this.orderNoShow = orderNoShow;
	}

	public int getCcPayAllowFlag() {
		return ccPayAllowFlag;
	}

	public void setCcPayAllowFlag(int ccPayAllowFlag) {
		this.ccPayAllowFlag = ccPayAllowFlag;
	}

}
