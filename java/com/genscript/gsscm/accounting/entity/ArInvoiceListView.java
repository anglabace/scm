package com.genscript.gsscm.accounting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "v_ar_invoice_list", catalog="accounting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArInvoiceListView implements Serializable {
    
	@Id
	@Column(name = "invoice_id")
	public int invoiceId;
	@Column(name = "cust_no")
	public int custNo;
	@Column(name = "order_no")
	public int orderNo;
	@Column(name = "total_amount")
	public float totalAmount;
	@Column(name = "balance")
	public float balance;
	@Column(name = "currency")
	public String currency;
	@Column(name = "paid_amt")
	public float paidAmount;
	@Column(name = "discount")
	public float discount;
	@Column(name = "bad_debt")
	public float badDebt;
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getCustNo() {
		return custNo;
	}
	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
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
	public float getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getBadDebt() {
		return badDebt;
	}
	public void setBadDebt(float badDebt) {
		this.badDebt = badDebt;
	}
	
}
