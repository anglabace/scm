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
@Entity
@Table(name = "ar_transactions", catalog = "accounting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArInvoicePayment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5915690674865992110L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	int transactionId;

	@Column(name = "transaction_no")
	String transactionNo;

	@Column(name = "cust_no")
	int custNo;

	@Column(name = "transaction_type")
	String transactionType;

	@Column(name = "transaction_date")
	Date transactionDate;
	
	@Column(name = "payment_type")
	String paymentType;
	
	String currency;
	
	String amount;
	
	@Column(name = "transaction_fee")
	String transactionFee;
	
	String balance;
	
	@Column(name = "tender_type")
	String tenderType;
	
	@Column(name = "account_name")
	String accountName;
	
	@Column(name = "account_no")
	String accountNo;
	
	@Column(name = "routing_no")
	String routingNo;
	
	@Column(name = "chk_no")
	String chkNo;
	
	@Column(name = "cc_type")
	String ccType;
	
	@Column(name = "cc_card_holder")
	String ccCardHolder;
	
	@Column(name = "cc_cvc")
	String ccCvc;
	
	@Column(name = "cc_expiration")
	String ccExpiration;
	
	String description;
	
	String status;
	
	@Column(name = "creation_date",updatable = false)
	Date creationDate;
	
	@Column(name = "created_by",updatable = false)
	int createdBy; 
	@Column(name = "modify_date")
	Date modifyDate;
	
	@Column(name = "modified_by")
	int modifiedBy;
	
	@Column(name = "status_upd_reason")
	String statusUpdReason;
	
	@Transient
	String invoiceNo;
	@Column(name = "order_no")
	String orderNo;
	@Transient
	String applyAmount;
	@Transient
	Date approveDate;
	@Transient
	int approvedBy;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(String transactionFee) {
		this.transactionFee = transactionFee;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public String getChkNo() {
		return chkNo;
	}

	public void setChkNo(String chkNo) {
		this.chkNo = chkNo;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCcCardHolder() {
		return ccCardHolder;
	}

	public void setCcCardHolder(String ccCardHolder) {
		this.ccCardHolder = ccCardHolder;
	}

	public String getCcCvc() {
		return ccCvc;
	}

	public void setCcCvc(String ccCvc) {
		this.ccCvc = ccCvc;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	public String getStatusUpdReason() {
		return statusUpdReason;
	}

	public void setStatusUpdReason(String statusUpdReason) {
		this.statusUpdReason = statusUpdReason;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}
	
}
