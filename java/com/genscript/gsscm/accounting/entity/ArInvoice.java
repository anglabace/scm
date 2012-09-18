package com.genscript.gsscm.accounting.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "ar_invoices", catalog = "accounting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArInvoice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3034545521798855455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	Integer invoiceId;

	@Column(name = "invoice_no")
	String invoiceNo;

	@Column(name = "cust_no")
	Integer custNo;

	@Column(name = "invoice_type")
	String invoiceType;

	@Column(name = "invoice_date")
	Date invoiceDate;

	@Column(name = "expr_date")
	Date exprDate;             //过期时间

	@Column(name = "order_no")
	Integer orderNo;

	@Column(name = "shipment_id")
	Integer shipmentId;

	@Column(name = "sales_contact")
	Integer salesContact;

	String currency;

	@Column(name = "sub_total")
	Float subTotal;

	Float shipping;

	Float tax;

	Float discount;

	@Column(name = "total_amount")
	Float totalAmount;

	Float balance;

	@Column(name = "payment_term")
	Integer paymentTerm;

	@Column(name = "payment_method")
	String paymentMethod;

	String description;

	@Column(name = "printed_flag")
	String printedFlag;

	String comment;

	@Column(name = "customer_note")
	String customerNote;

	String status;

	@Column(name = "company_id")
	Integer companyId;


	@Column(name = "status_upd_reason")
	String statusUpdReason;
	
	@Column(name = "creation_date")
	Date creationDate;

	@Column(name = "created_by")
	int createdBy;

	@Column(name = "modify_date")
	Date modifyDate;

	@Column(name = "modified_by")
	int modifiedBy;
	
	@Transient
	String busEmail;//加注释
	@Transient
	Date orderDate;
	@Transient
	public String oldStatus;
	@Transient
	public String symbol; //币种符号
	
	@Transient 
	public String salesContactName;
	
	@Transient
	public Integer PaidAmount;
	
	
	
	//toString()方便调试
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public Date getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}


	public int getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Integer getInvoiceId() {
		return invoiceId;
	}


	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public Integer getCustNo() {
		return custNo;
	}


	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}


	public String getInvoiceType() {
		return invoiceType;
	}


	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}


	public Date getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public Date getExprDate() {
		return exprDate;
	}


	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}


	public Integer getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	public Integer getShipmentId() {
		return shipmentId;
	}


	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}


	public Integer getSalesContact() {
		return salesContact;
	}


	public void setSalesContact(Integer salesContact) {
		this.salesContact = salesContact;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public Float getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}


	public Float getShipping() {
		return shipping;
	}


	public void setShipping(Float shipping) {
		this.shipping = shipping;
	}


	public Float getTax() {
		return tax;
	}


	public void setTax(Float tax) {
		this.tax = tax;
	}


	public Float getDiscount() {
		return discount;
	}


	public void setDiscount(Float discount) {
		this.discount = discount;
	}


	public Float getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Float getBalance() {
		return balance;
	}


	public void setBalance(Float balance) {
		this.balance = balance;
	}


	public Integer getPaymentTerm() {
		return paymentTerm;
	}


	public void setPaymentTerm(Integer paymentTerm) {
		this.paymentTerm = paymentTerm;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPrintedFlag() {
		return printedFlag;
	}


	public void setPrintedFlag(String printedFlag) {
		this.printedFlag = printedFlag;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getCustomerNote() {
		return customerNote;
	}


	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getCompanyId() {
		return companyId;
	}


	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


	public String getStatusUpdReason() {
		return statusUpdReason;
	}


	public void setStatusUpdReason(String statusUpdReason) {
		this.statusUpdReason = statusUpdReason;
	}


	public String getBusEmail() {
		return busEmail;
	}


	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public String getOldStatus() {
		return oldStatus;
	}


	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public String getSalesContactName() {
		return salesContactName;
	}


	public void setSalesContactName(String salesContactName) {
		this.salesContactName = salesContactName;
	}


	public Integer getPaidAmount() {
		return PaidAmount;
	}


	public void setPaidAmount(Integer paidAmount) {
		PaidAmount = paidAmount;
	}
	
	
	
}
