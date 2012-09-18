package com.genscript.gsscm.accounting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "v_ap_invoice_list", catalog="accounting")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ApInvoiceListView implements Serializable {
    
	@Id
	@Column(name = "invoice_id")
	public int invoiceId;
	@Column(name = "invoice_no")
	public String invoiceNo;
	@Column(name = "vendor_no")
	public int vendorNo;
	@Column(name = "order_no")
	public int orderNo;
	@Column(name = "total_amount")
	public String amount; 
	@Column(name = "paid_amount")
	public String paidAmount;
	@Column(name = "discount")
	public float discount;
	@Column(name = "applied_amount")
	public String appliedAmount;
	@Column(name = "balance")
	public float balance;
	@Column(name = "currency")
	public String currency;
	
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public int getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(int vendorNo) {
		this.vendorNo = vendorNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getAppliedAmount() {
		return appliedAmount;
	}
	public void setAppliedAmount(String appliedAmount) {
		this.appliedAmount = appliedAmount;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
