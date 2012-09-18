package com.genscript.gsscm.accounting.entity;

/* * Created on 2010-11-08 16:55:13
 * by zhouyong
 * for what
 */

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "ap_transaction_allocation", catalog="accounting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApTransactionAllocation extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7467666306192571470L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) //生成逐渐策略
	 public Integer id;
	 public Integer invoiceId;
	 public Integer transactionId;
	 public Float applyAmount;
	// @Column(name="status_upd_reason")
	 @Transient
	 public String statusUpdReason;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Float getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(Float applyAmount) {
		this.applyAmount = applyAmount;
	}
	public String getStatusUpdReason() {
		return statusUpdReason;
	}
	public void setStatusUpdReason(String statusUpdReason) {
		this.statusUpdReason = statusUpdReason;
	}



}
