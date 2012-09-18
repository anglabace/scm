package com.genscript.gsscm.accounting.entity;

/* * Created on 2010-11-08 11:05:57
 * by zhouyong
 * for what
 */

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "ap_invoices", catalog = "accounting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApInvoice extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6584028483706994791L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	public Integer invoiceId;
	public String invoiceNo;
	public Integer vendorNo;
	public String invoiceType;
	public Date invoiceDate;
	public Date exprDate;
	public Integer orderNo;
	public Integer shipmentId;
	@Column(name="purchase_contact")
	public Integer salesContact;
	public String currency;
	public float subTotal;
	public float shipping;
	public float tax;
	public float discount;
	public float totalAmount;
	public float balance;
	public Integer paymentTerm;
	public String paymentMethod;
	public String description;
	public String printedFlag;
	public String comment;
	public String customerNote;
	public String status;
	@Column(name="status_upd_reason")
	public String statusUpdReason;
	public Integer companyId;
	@Transient
	public String Symbol; //币种符号
	@Transient
	String dateFrom; // invoice date 大于的日期
	@Transient
	String dateTo; // invoice date 小于的日期
	@Transient
	String oldStatus;

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void sdetInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Integer getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(Integer vendorNo) {
		this.vendorNo = vendorNo;
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


	public Integer getSalesContact() {
		return salesContact;
	}

	public void setSalesContact(Integer salesContact) {
		this.salesContact = salesContact;
	}

	public String getStatusUpdReason() {
		return statusUpdReason;
	}

	public void setStatusUpdReason(String statusUpdReason) {
		this.statusUpdReason = statusUpdReason;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public void setShipping(float shipping) {
		this.shipping = shipping;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

}
