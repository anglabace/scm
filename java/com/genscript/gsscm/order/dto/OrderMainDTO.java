package com.genscript.gsscm.order.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.order.entity.OrderProcessLog;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;


@XmlType(name = "OrderMainDTO", namespace = WsConstants.NS)
public class OrderMainDTO implements Serializable {
	private static final long serialVersionUID = -3996329168409784178L;
	private Integer orderNo;
	private String refOrderNo;
	private Integer custNo;
	private String orderType;
	private Date orderDate;
	private Date exprDate;
	private String priority;
	private Integer salesContact;
	private Integer techSupport;
	private Integer altContact;
	private String orderCurrency;
	private Double exchRate;
	private Date exchRateDate;
	private Double subTotal;
	private Double discount = 0.0;
	private Double tax = 0.0;
	private Double shipAmt;
	private Double amount;
	private Integer paymentTerm;
	private String orderMemo;
	private String srcQuoteNo;
	private Integer orderSrc;
	private Integer billtoAddrId;
	private Integer shiptoAddrId;
	private Integer soldtoAddrId;
	private String status;
	private String statusReason;
	private Integer gsCoId;
	private Integer altTechSupport;
	private Integer shiptoAddrFlag;
	private String baseCurrency;
    private Integer projectSupport;
    private String projectSupportUser;
    private Integer altProjectSupport;
	private List<OrderItemDTO> itemList;
	private List<Integer> delItemIdList;
	private List<InstructionDTO> instructionList;
	private OrderAddressDTO orderAddrList;
	private String custAltNo;
	private OrderPromotionDTO orderPromotion;
	private String salesContactUser;
	private String techSupportUser;
	private String statusText;
	private Integer promotionId;
	private List<Integer> delPaymentPlanIdList;
	private List<PaymentVoucherDTO> paymentPlanList;
	private List<Integer> delDocumentsList;
    //add by zhanghuibin
    private OrderProcessLog orderProcessLog;

	//非数据库属性之file
	private List<Document> documentList;
	
	
	

	private List<Integer> sendMailIdList;
	private List<CreditCard> cardList;
	private List<Integer> delCardIdList;
    //for shipping package.
    private List<OrderPackageDTO> orderPackageList;
    private List<Integer> delOrderPackageIdList;
	//业务用， status_history.note.
	private String statusNote;
	private Integer custPrefShipMethod;
	private String shipAccount;
	private String altOrderNo;
	private Integer srcPoNo;
	private Integer warehouseId;
	private String shippingType;
	private String shippingRule;
	private String shippingAccount;

    //新增
    private String orgName;
	private String couponId;
	private Double couponValue;
	private String couponCode;
	private String shipAmtChanged;
	private Integer projectManager;
	private Integer altProjectManager;
    private Integer keyInfoChanged;
    //add by Zhang Yong
    private String dbStatus;
    private String custType;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the itemList
	 */
	public List<OrderItemDTO> getItemList() {
		return itemList;
	}
	
	
	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	} 
	public List<Integer> getDelDocumentsList() {
		return delDocumentsList;
	}
	public void setDelDocumentsList(List<Integer> delDocumentsList) {
		this.delDocumentsList = delDocumentsList;
	}
	
	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<OrderItemDTO> itemList) {
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
	 * @return the orderAddrList
	 */
	public OrderAddressDTO getOrderAddrList() {
		return orderAddrList;
	}
	/**
	 * @param orderAddrList the orderAddrList to set
	 */
	public void setOrderAddrList(OrderAddressDTO orderAddrList) {
		this.orderAddrList = orderAddrList;
	}
	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	/**
	 * @param exchRateDate the exchRateDate to set
	 */
	public void setExchRateDate(Date exchRateDate) {
		this.exchRateDate = exchRateDate;
	}
	/**
	 * @return the subTotal
	 */
	public Double getSubTotal() {
		return subTotal;
	}
	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	/**
	 * @return the tax
	 */
	public Double getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}
	/**
	 * @return the shipAmt
	 */
	public Double getShipAmt() {
		return shipAmt;
	}
	/**
	 * @param shipAmt the shipAmt to set
	 */
	public void setShipAmt(Double shipAmt) {
		this.shipAmt = shipAmt;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
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
	 * @return the orderMemo
	 */
	public String getOrderMemo() {
		return orderMemo;
	}
	/**
	 * @param orderMemo the orderMemo to set
	 */
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	
	/**
	 * @return the orderSrc
	 */
	public Integer getOrderSrc() {
		return orderSrc;
	}
	/**
	 * @param orderSrc the orderSrc to set
	 */
	public void setOrderSrc(Integer orderSrc) {
		this.orderSrc = orderSrc;
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
	
	public String getRefOrderNo() {
		return refOrderNo;
	}
	public void setRefOrderNo(String refOrderNo) {
		this.refOrderNo = refOrderNo;
	}
	public String getSrcQuoteNo() {
		return srcQuoteNo;
	}
	public void setSrcQuoteNo(String srcQuoteNo) {
		this.srcQuoteNo = srcQuoteNo;
	}
	/**
	 * @return the orderPromotion
	 */
	public OrderPromotionDTO getOrderPromotion() {
		return orderPromotion;
	}
	/**
	 * @param orderPromotion the orderPromotion to set
	 */
	public void setOrderPromotion(OrderPromotionDTO orderPromotion) {
		this.orderPromotion = orderPromotion;
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
	 * @return the paymentPlanList
	 */
	public List<PaymentVoucherDTO> getPaymentPlanList() {
		return paymentPlanList;
	}
	/**
	 * @param paymentPlanList the paymentPlanList to set
	 */
	public void setPaymentPlanList(List<PaymentVoucherDTO> paymentPlanList) {
		this.paymentPlanList = paymentPlanList;
	}
	/**
	 * @return the sendMailIdList
	 */
	public List<Integer> getSendMailIdList() {
		return sendMailIdList;
	}
	/**
	 * @param sendMailIdList the sendMailIdList to set
	 */
	public void setSendMailIdList(List<Integer> sendMailIdList) {
		this.sendMailIdList = sendMailIdList;
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
	 * @return the cardList
	 */
	public List<CreditCard> getCardList() {
		return cardList;
	}
	/**
	 * @param cardList the cardList to set
	 */
	public void setCardList(List<CreditCard> cardList) {
		this.cardList = cardList;
	}
	/**
	 * @return the delCardIdList
	 */
	public List<Integer> getDelCardIdList() {
		return delCardIdList;
	}
	/**
	 * @param delCardIdList the delCardIdList to set
	 */
	public void setDelCardIdList(List<Integer> delCardIdList) {
		this.delCardIdList = delCardIdList;
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
	 * @return the orderCurrency
	 */
	public String getOrderCurrency() {
		return orderCurrency;
	}
	/**
	 * @param orderCurrency the orderCurrency to set
	 */
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	/**
	 * @return the orderPackageList
	 */
	public List<OrderPackageDTO> getOrderPackageList() {
		return orderPackageList;
	}
	/**
	 * @param orderPackageList the orderPackageList to set
	 */
	public void setOrderPackageList(List<OrderPackageDTO> orderPackageList) {
		this.orderPackageList = orderPackageList;
	}
	/**
	 * @return the delOrderPackageIdList
	 */
	public List<Integer> getDelOrderPackageIdList() {
		return delOrderPackageIdList;
	}
	/**
	 * @param delOrderPackageIdList the delOrderPackageIdList to set
	 */
	public void setDelOrderPackageIdList(List<Integer> delOrderPackageIdList) {
		this.delOrderPackageIdList = delOrderPackageIdList;
	}
	public Integer getCustPrefShipMethod() {
		return custPrefShipMethod;
	}
	public void setCustPrefShipMethod(Integer custPrefShipMethod) {
		this.custPrefShipMethod = custPrefShipMethod;
	}
	/**
	 * @return the shipAccount
	 */
	public String getShipAccount() {
		return shipAccount;
	}
	/**
	 * @param shipAccount the shipAccount to set
	 */
	public void setShipAccount(String shipAccount) {
		this.shipAccount = shipAccount;
	}
	public String getAltOrderNo() {
		return altOrderNo;
	}
	public void setAltOrderNo(String altOrderNo) {
		this.altOrderNo = altOrderNo;
	}
	public Integer getSrcPoNo() {
		return srcPoNo;
	}
	public void setSrcPoNo(Integer srcPoNo) {
		this.srcPoNo = srcPoNo;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public String getShippingRule() {
		return shippingRule;
	}
	public void setShippingRule(String shippingRule) {
		this.shippingRule = shippingRule;
	}
	public String getShippingAccount() {
		return shippingAccount;
	}
	public void setShippingAccount(String shippingAccount) {
		this.shippingAccount = shippingAccount;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public Double getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(Double couponValue) {
		this.couponValue = couponValue;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public void setShipAmtChanged(String shipAmtChanged) {
		this.shipAmtChanged = shipAmtChanged;
	}
	public String getShipAmtChanged() {
		return shipAmtChanged;
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

    public Integer getKeyInfoChanged() {
        return keyInfoChanged;
    }

    public void setKeyInfoChanged(Integer keyInfoChanged) {
        this.keyInfoChanged = keyInfoChanged;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
	/**
	 * @return the dbStatus
	 */
	public String getDbStatus() {
		return dbStatus;
	}
	/**
	 * @param dbStatus the dbStatus to set
	 */
	public void setDbStatus(String dbStatus) {
		this.dbStatus = dbStatus;
	}
	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

    public OrderProcessLog getOrderProcessLog() {
        return orderProcessLog;
    }

    public void setOrderProcessLog(OrderProcessLog orderProcessLog) {
        this.orderProcessLog = orderProcessLog;
    }
}
