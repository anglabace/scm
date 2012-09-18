package com.genscript.gsscm.quote.web;

import java.io.File;
import java.util.List;
import java.util.Map;

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
import com.genscript.gsscm.customer.dto.PaymentTermDTO;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dto.PaymentVoucherDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;

/**
 * 处理 order payment 相关信息
 * @author Administrator
 *
 */
@Results({
	@Result(name="payment_list", location="quote/quote_payment_list.jsp")
})
public class QuotePaymentAction extends BaseAction<PaymentVoucherDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4964903848197948886L;
	private final static String SUCCESS = "SUCCESS";
	@Autowired
	private OrderService quoteService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private FileService fileService;
	
	private String sessQuoteNo;
	private Map<String, PaymentVoucherDTO> paymentMap;  
	private String quorderStatus;
	private List<PaymentTermDTO> termList;
	private List<CreditCard> cardList;
	private List<PbDropdownListOptions> cardTypeList;
	private PaymentVoucherDTO payment;
	private String sessVoucherId;
	private Integer saveCardFlag;
	private Integer custNo;
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
		SessionUtil.deleteOneRow(SessionConstant.QuotePayment.value(), sessQuoteNo, sessVoucherId);
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/**
	 * 显示编辑
	 */
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 显示列表
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String list() throws Exception {		
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		quorderStatus = quote.getStatus();
		custNo = quote.getCustNo();
		//下拉列表
		termList = customerService.getPaymentTermList();
		cardList = quoteService.getCustActiveCardList(custNo);
		cardTypeList= publicService.getDropdownList("CREDIT_CARD_TYPE");
		Map<String, PaymentVoucherDTO> dbPaymentMap = null;
		if(StringUtils.isNumeric(sessQuoteNo)){
			List<PaymentVoucherDTO> dbPaymentList = quoteService.getPayInfoList(Integer.parseInt(sessQuoteNo));
			dbPaymentMap = SessionUtil.convertList2Map(dbPaymentList, "voucherId");
		}
		Map<String, PaymentVoucherDTO> sessMap = (Map<String, PaymentVoucherDTO>) SessionUtil.getRow(SessionConstant.QuotePayment.value(), sessQuoteNo); 
		paymentMap = SessionUtil.mergeList(sessMap, dbPaymentMap, 1);
		return "payment_list";
	}

	/**
	 * 初始化模型的数据
	 */
	@Override
	protected void prepareModel() throws Exception {
		if(!StringUtils.isEmpty(sessVoucherId)){
			payment = (PaymentVoucherDTO) SessionUtil.getOneRow(SessionConstant.QuotePayment.value(), sessQuoteNo, sessVoucherId);
			if(StringUtils.isNumeric(sessVoucherId) && payment == null){
				payment = quoteService.getPayInfo(Integer.parseInt(sessVoucherId));
			}
		}else{
			payment = new PaymentVoucherDTO();
		}
	}

	/**
	 * 保存payment
	 */
	@Override
	public String save() throws Exception {
		String message = this.checkDataBeforeSave();
		if(!message.equalsIgnoreCase(SUCCESS)){
			Struts2Util.renderText("error");
			return null;
		}
		//对时间格式进行转换
		if(payment != null){
			payment.setCcExprDateStr(DateUtils.formatDate2Str(payment.getCcExprDate(), "yyyy-MM-dd"));
			payment.setPoDueDateStr(DateUtils.formatDate2Str(payment.getPoDueDate(), "yyyy-MM-dd"));
		}
		if (upload != null && upload.size() == 1) {
			for (int i = 0; i < upload.size(); i++) {
				Document doc = new Document();
				String srcFileName = uploadFileName.get(i);
				uploadFileName.set(i, SessionUtil.generateTempId() + "_"+"order_"
						+ srcFileName);
				doc.setFilePath("po/" + uploadFileName.get(i));
				doc.setDocName(srcFileName);
				payment.setDocument(doc);
			}
		}
		if(StringUtils.isEmpty(sessVoucherId)){
			sessVoucherId = SessionUtil.generateTempId();
		}
		SessionUtil.updateOneRow(SessionConstant.OrderPayment.value(), sessQuoteNo, sessVoucherId, payment);
		fileService.uploadFile(upload, uploadContentType, uploadFileName, "po");
		//保存customer account
		if(saveCardFlag != null && saveCardFlag.intValue() == 1){
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
	
	/**
	 * 删除附件
	 * @return
	 * @throws Exception
	 */
	public String deletePoDoc() throws Exception{
		this.prepareModel();
		payment.setDocument(null);
		SessionUtil.updateOneRow(SessionConstant.OrderPayment.value(), sessQuoteNo, sessVoucherId, payment);
		Struts2Util.renderText(SUCCESS);
		return null;
	}
	
	/**
	 * 验证数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String checkDataBeforeSave(){
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if(itemMap == null || itemMap.size() == 0){
			return "No item added, please added one item at least.";
		}
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		if(quote.getQuoteAddrList() == null || quote.getQuoteAddrList().getShipToAddr() == null){
			return "The address of 'Ship To' is required";
		}
		return SUCCESS;
	}
	
	public PaymentVoucherDTO getModel(){
		return payment;
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

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public static String getSUCCESS() {
		return SUCCESS;
	}

}
