package com.genscript.gsscm.quote.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.quote.entity.QuotePaymentPlan;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;


@XmlType(name = "QuoteMainDTO", namespace = WsConstants.NS)
public class QuoteMainDTO {
	private Integer quoteNo;
	private Integer custNo;
	private String altQuoteNo;
	private Date quoteDate;
	private Date exprDate;
	private Integer warehouseId;
	private String quoteType;
	private String priority;
	private Integer salesContact;
	private Integer techSupport;
	private Integer altContact;
	private String quoteCurrency; 
	private Double exchRate;
	private Date exchRateDate;
	private List<Integer> delShipPackageIdList;
	private Integer custPrefShipMethod;
	private BigDecimal subTotal;
	private BigDecimal discount = new BigDecimal(0.00) ;
	private BigDecimal tax = new BigDecimal(0.00);
	private BigDecimal shipAmt;
	private String shipAmtChanged;
	private BigDecimal amount;
	private Integer paymentTerm;
	private String quoteMemo;
	private String refQuoteNo;
	private Integer quoteSrc;
	private String orderNo;
	private Integer billtoAddrId;
	private Integer shiptoAddrId;
	private Integer soldtoAddrId;
	private String status;
	private String statusReason;
	private String note;
	private Integer gsCoId;
	private Integer altTechSupport;
	private Integer shiptoAddrFlag;
	private String baseCurrency;
    private Integer projectSupport;
    private String projectSupportUser;
    private Integer altProjectSupport;
	private List<QuoteItemDTO> itemList;
	private List<Integer> delItemIdList;
	private List<InstructionDTO> instructionList;
	private QuoteAddressDTO quoteAddrList;
	private String custAltNo;
	private QuotePromotionDTO quotePromotion;
	private String salesContactUser;
	private String techSupportUser;
	private String statusText;
	private Integer promotionId;
	private List<Integer> delPaymentPlanIdList;
	private List<QuotePaymentPlan> paymentPlanList;
	private List<CreditCard> cardList;
	private List<Integer> delCardIdList;
	private List<Integer> sendMailIdList;
    //for shipping package.
    private List<QuotePackageDTO> quotePackageList;
    private List<Integer> delQuotePackageIdList;
	//业务用， status_history.note.
	private String statusNote;
	private String shippingAccount;
	private String salesContactEmail;
	//coupon
	private String couponCode;
	private String couponId;
	private BigDecimal couponValue;
	private Integer projectManager;
	private Integer altProjectManager;

     private Date followupDate;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the quoteNo
	 */
	public Integer getQuoteNo() {
		return quoteNo;
	}
	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	/**
	 * @return the custNo
	 */
	public Integer getCustNo() {
		return custNo;
	}
	/**
	 * @param custNo the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	/**
	 * @return the quoteDate
	 */
	public Date getQuoteDate() {
		return quoteDate;
	}
	/**
	 * @param quoteDate the quoteDate to set
	 */
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	/**
	 * @return the exprDate
	 */
	public Date getExprDate() {
		return exprDate;
	}
	/**
	 * @param exprDate the exprDate to set
	 */
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	/**
	 * @return the quoteType
	 */
	public String getQuoteType() {
		return quoteType;
	}
	/**
	 * @param quoteType the quoteType to set
	 */
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the salesContact
	 */
	public Integer getSalesContact() {
		return salesContact;
	}
	/**
	 * @param salesContact the salesContact to set
	 */
	public void setSalesContact(Integer salesContact) {
		this.salesContact = salesContact;
	}
	/**
	 * @return the techSupport
	 */
	public Integer getTechSupport() {
		return techSupport;
	}
	/**
	 * @param techSupport the techSupport to set
	 */
	public void setTechSupport(Integer techSupport) {
		this.techSupport = techSupport;
	}
	/**
	 * @return the altContact
	 */
	public Integer getAltContact() {
		return altContact;
	}
	/**
	 * @param altContact the altContact to set
	 */
	public void setAltContact(Integer altContact) {
		this.altContact = altContact;
	}
	/**
	 * @return the exchRate
	 */
	public Double getExchRate() {
		return exchRate;
	}
	/**
	 * @param exchRate the exchRate to set
	 */
	public void setExchRate(Double exchRate) {
		this.exchRate = exchRate;
	}
	/**
	 * @return the exchRateDate
	 */
	public Date getExchRateDate() {
		return exchRateDate;
	}
	
	
	public Date getFollowupDate() {
		return followupDate;
	}
	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}
	/**
	 * @param exchRateDate the exchRateDate to set
	 */
	public void setExchRateDate(Date exchRateDate) {
		this.exchRateDate = exchRateDate;
	}
	/**
	 * @return the subTotal
	 */
	public BigDecimal getSubTotal() {
		if(subTotal == null)
			return null;
		return subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(BigDecimal subTotal) {
		if(subTotal == null){
			this.subTotal = null;
		}else{
			this.subTotal = subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	/**
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		if(discount == null)
			return null;
		return discount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(BigDecimal discount) {
		if(discount == null){
			this.discount = null;
		}else{
			this.discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	/**
	 * @return the tax
	 */
	public BigDecimal getTax() {
		if(tax == null)
			return null;
		return tax.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(BigDecimal tax) {
		if(tax == null){
			this.tax = null;
		}else{
			this.tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		if(amount == null)
			return null;
		return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		if(amount == null){
			this.amount = null;
		}else{
			this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	/**
	 * @return the paymentTerm
	 */
	public Integer getPaymentTerm() {
		return paymentTerm;
	}
	/**
	 * @param paymentTerm the paymentTerm to set
	 */
	public void setPaymentTerm(Integer paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	/**
	 * @return the quoteMemo
	 */
	public String getQuoteMemo() {
		return quoteMemo;
	}
	/**
	 * @param quoteMemo the quoteMemo to set
	 */
	public void setQuoteMemo(String quoteMemo) {
		this.quoteMemo = quoteMemo;
	}
	
	/**
	 * @return the quoteSrc
	 */
	public Integer getQuoteSrc() {
		return quoteSrc;
	}
	/**
	 * @param quoteSrc the quoteSrc to set
	 */
	public void setQuoteSrc(Integer quoteSrc) {
		this.quoteSrc = quoteSrc;
	}
	public String getRefQuoteNo() {
		return refQuoteNo;
	}
	public void setRefQuoteNo(String refQuoteNo) {
		this.refQuoteNo = refQuoteNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the billtoAddrId
	 */
	public Integer getBilltoAddrId() {
		return billtoAddrId;
	}
	/**
	 * @param billtoAddrId the billtoAddrId to set
	 */
	public void setBilltoAddrId(Integer billtoAddrId) {
		this.billtoAddrId = billtoAddrId;
	}
	/**
	 * @return the shiptoAddrId
	 */
	public Integer getShiptoAddrId() {
		return shiptoAddrId;
	}
	/**
	 * @param shiptoAddrId the shiptoAddrId to set
	 */
	public void setShiptoAddrId(Integer shiptoAddrId) {
		this.shiptoAddrId = shiptoAddrId;
	}
	/**
	 * @return the soldtoAddrId
	 */
	public Integer getSoldtoAddrId() {
		return soldtoAddrId;
	}
	/**
	 * @param soldtoAddrId the soldtoAddrId to set
	 */
	public void setSoldtoAddrId(Integer soldtoAddrId) {
		this.soldtoAddrId = soldtoAddrId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the statusReason
	 */
	public String getStatusReason() {
		return statusReason;
	}
	/**
	 * @param statusReason the statusReason to set
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the gsCoId
	 */
	public Integer getGsCoId() {
		return gsCoId;
	}
	/**
	 * @param gsCoId the gsCoId to set
	 */
	public void setGsCoId(Integer gsCoId) {
		this.gsCoId = gsCoId;
	}
	/**
	 * @return the itemList
	 */
	public List<QuoteItemDTO> getItemList() {
		return itemList;
	}
	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<QuoteItemDTO> itemList) {
		this.itemList = itemList;
	}
	/**
	 * @return the delItemIdList
	 */
	public List<Integer> getDelItemIdList() {
		return delItemIdList;
	}
	/**
	 * @param delItemIdList the delItemIdList to set
	 */
	public void setDelItemIdList(List<Integer> delItemIdList) {
		this.delItemIdList = delItemIdList;
	}
	/**
	 * @return the instructionList
	 */
	public List<InstructionDTO> getInstructionList() {
		return instructionList;
	}
	/**
	 * @param instructionList the instructionList to set
	 */
	public void setInstructionList(List<InstructionDTO> instructionList) {
		this.instructionList = instructionList;
	}
	/**
	 * @return the quoteAddrList
	 */
	public QuoteAddressDTO getQuoteAddrList() {
		return quoteAddrList;
	}
	/**
	 * @param quoteAddrList the quoteAddrList to set
	 */
	public void setQuoteAddrList(QuoteAddressDTO quoteAddrList) {
		this.quoteAddrList = quoteAddrList;
	}
	/**
	 * @return the custAltNo
	 */
	public String getCustAltNo() {
		return custAltNo;
	}
	/**
	 * @param custAltNo the custAltNo to set
	 */
	public void setCustAltNo(String custAltNo) {
		this.custAltNo = custAltNo;
	}
	/**
	 * @return the quotePromotion
	 */
	public QuotePromotionDTO getQuotePromotion() {
		return quotePromotion;
	}
	/**
	 * @param quotePromotion the quotePromotion to set
	 */
	public void setQuotePromotion(QuotePromotionDTO quotePromotion) {
		this.quotePromotion = quotePromotion;
	}
	/**
	 * @return the salesContactUser
	 */
	public String getSalesContactUser() {
		return salesContactUser;
	}
	/**
	 * @param salesContactUser the salesContactUser to set
	 */
	public void setSalesContactUser(String salesContactUser) {
		this.salesContactUser = salesContactUser;
	}
	/**
	 * @return the techSupportUser
	 */
	public String getTechSupportUser() {
		return techSupportUser;
	}
	/**
	 * @param techSupportUser the techSupportUser to set
	 */
	public void setTechSupportUser(String techSupportUser) {
		this.techSupportUser = techSupportUser;
	}
	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}
	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	/**
	 * @return the shiptoAddrFlag
	 */
	public Integer getShiptoAddrFlag() {
		return shiptoAddrFlag;
	}
	/**
	 * @param shiptoAddrFlag the shiptoAddrFlag to set
	 */
	public void setShiptoAddrFlag(Integer shiptoAddrFlag) {
		this.shiptoAddrFlag = shiptoAddrFlag;
	}
	/**
	 * @return the altTechSupport
	 */
	public Integer getAltTechSupport() {
		return altTechSupport;
	}
	/**
	 * @param altTechSupport the altTechSupport to set
	 */
	public void setAltTechSupport(Integer altTechSupport) {
		this.altTechSupport = altTechSupport;
	}
	/**
	 * @return the promotionId
	 */
	public Integer getPromotionId() {
		return promotionId;
	}
	/**
	 * @param promotionId the promotionId to set
	 */
	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}
	/**
	 * @return the delPaymentPlanIdList
	 */
	public List<Integer> getDelPaymentPlanIdList() {
		return delPaymentPlanIdList;
	}
	/**
	 * @param delPaymentPlanIdList the delPaymentPlanIdList to set
	 */
	public void setDelPaymentPlanIdList(List<Integer> delPaymentPlanIdList) {
		this.delPaymentPlanIdList = delPaymentPlanIdList;
	}
	/**
	 * @return the baseCurrency
	 */
	public String getBaseCurrency() {
		return baseCurrency;
	}
	/**
	 * @param baseCurrency the baseCurrency to set
	 */
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	/**
	 * @return the paymentPlanList
	 */
	public List<QuotePaymentPlan> getPaymentPlanList() {
		return paymentPlanList;
	}
	/**
	 * @param paymentPlanList the paymentPlanList to set
	 */
	public void setPaymentPlanList(List<QuotePaymentPlan> paymentPlanList) {
		this.paymentPlanList = paymentPlanList;
	}
	/**
	 * @return the projectSupport
	 */
	public Integer getProjectSupport() {
		return projectSupport;
	}
	/**
	 * @param projectSupport the projectSupport to set
	 */
	public void setProjectSupport(Integer projectSupport) {
		this.projectSupport = projectSupport;
	}
	/**
	 * @return the altProjectSupport
	 */
	public Integer getAltProjectSupport() {
		return altProjectSupport;
	}
	/**
	 * @param altProjectSupport the altProjectSupport to set
	 */
	public void setAltProjectSupport(Integer altProjectSupport) {
		this.altProjectSupport = altProjectSupport;
	}
	/**
	 * @return the projectSupportUser
	 */
	public String getProjectSupportUser() {
		return projectSupportUser;
	}
	/**
	 * @param projectSupportUser the projectSupportUser to set
	 */
	public void setProjectSupportUser(String projectSupportUser) {
		this.projectSupportUser = projectSupportUser;
	}
	/**
	 * @return the statusNote
	 */
	public String getStatusNote() {
		return statusNote;
	}
	/**
	 * @param statusNote the statusNote to set
	 */
	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}
	/**
	 * @return the quoteCurrency
	 */
	public String getQuoteCurrency() {
		return quoteCurrency;
	}
	/**
	 * @param quoteCurrency the quoteCurrency to set
	 */
	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}
	/**
	 * @return the quotePackageList
	 */
	public List<QuotePackageDTO> getQuotePackageList() {
		return quotePackageList;
	}
	/**
	 * @param quotePackageList the quotePackageList to set
	 */
	public void setQuotePackageList(List<QuotePackageDTO> quotePackageList) {
		this.quotePackageList = quotePackageList;
	}
	/**
	 * @return the delQuotePackageIdList
	 */
	public List<Integer> getDelQuotePackageIdList() {
		return delQuotePackageIdList;
	}
	/**
	 * @param delQuotePackageIdList the delQuotePackageIdList to set
	 */
	public void setDelQuotePackageIdList(List<Integer> delQuotePackageIdList) {
		this.delQuotePackageIdList = delQuotePackageIdList;
	}
	/**
	 * @return the shipAmt
	 */
	public BigDecimal getShipAmt() {
		if(shipAmt == null)
			return null;
		return shipAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * @param shipAmt the shipAmt to set
	 */
	public void setShipAmt(BigDecimal shipAmt) {
		if(shipAmt == null){
			this.shipAmt = null;
		}else{
			this.shipAmt = shipAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public String getAltQuoteNo() {
		return altQuoteNo;
	}
	public void setAltQuoteNo(String altQuoteNo) {
		this.altQuoteNo = altQuoteNo;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getCustPrefShipMethod() {
		return custPrefShipMethod;
	}
	public void setCustPrefShipMethod(Integer custPrefShipMethod) {
		this.custPrefShipMethod = custPrefShipMethod;
	}
	public List<Integer> getDelShipPackageIdList() {
		return delShipPackageIdList;
	}
	public void setDelShipPackageIdList(List<Integer> delShipPackageIdList) {
		this.delShipPackageIdList = delShipPackageIdList;
	}
	
	public List<CreditCard> getCardList() {
		return cardList;
	}
	public void setCardList(List<CreditCard> cardList) {
		this.cardList = cardList;
	}
	public List<Integer> getDelCardIdList() {
		return delCardIdList;
	}
	public void setDelCardIdList(List<Integer> delCardIdList) {
		this.delCardIdList = delCardIdList;
	}
	public List<Integer> getSendMailIdList() {
		return sendMailIdList;
	}
	public void setSendMailIdList(List<Integer> sendMailIdList) {
		this.sendMailIdList = sendMailIdList;
	}
	public String getShippingAccount() {
		return shippingAccount;
	}
	public void setShippingAccount(String shippingAccount) {
		this.shippingAccount = shippingAccount;
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
	public BigDecimal getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(BigDecimal couponValue) {
		this.couponValue = couponValue;
	}
	public String getShipAmtChanged() {
		return shipAmtChanged;
	}
	public void setShipAmtChanged(String shipAmtChanged) {
		this.shipAmtChanged = shipAmtChanged;
	}
	public String getSalesContactEmail() {
		return salesContactEmail;
	}
	public void setSalesContactEmail(String salesContactEmail) {
		this.salesContactEmail = salesContactEmail;
	}
	public Integer getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(Integer projectManager) {
		this.projectManager = projectManager;
	}
	public Integer getAltProjectManager() {
		return altProjectManager;
	}
	public void setAltProjectManager(Integer altProjectManager) {
		this.altProjectManager = altProjectManager;
	}
}
