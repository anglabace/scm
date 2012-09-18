package com.genscript.gsscm.accounting.entity;

/* * Created on 2010-11-09 11:16:19
 * by zhouyong
 * for what
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ar_transaction_allocation", catalog="accounting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class ArTransactionAllocation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2155664328289947768L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	Integer invoice_id;

	public void setInvoice_id(Integer invoice_id) {
		this.invoice_id = invoice_id;
	}

	@Column(name = "invoice_id")
	public Integer getInvoice_id() {
		return this.invoice_id;
	}

	Integer transaction_id;

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}

	@Column(name = "transaction_id")
	public Integer getTransaction_id() {
		return this.transaction_id;
	}

	String apply_amount;

	public void setApply_amount(String apply_amount) {
		this.apply_amount = apply_amount;
	}

	@Column(name = "apply_amount")
	public String getApply_amount() {
		return this.apply_amount;
	}

	Date creation_date;

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	@Column(name = "creation_date")
	public Date getCreation_date() {
		return this.creation_date;
	}

	Integer created_by;

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	@Column(name = "created_by")
	public Integer getCreated_by() {
		return this.created_by;
	}

	Date modify_date;

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	@Column(name = "modify_date")
	public Date getModify_date() {
		return this.modify_date;
	}

	Integer modified_by;

	public void setModified_by(Integer modified_by) {
		this.modified_by = modified_by;
	}

	@Column(name = "modified_by")
	public Integer getModified_by() {
		return this.modified_by;
	}
	
	String status ;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="status_upd_reason")
	String statusUpdReason;

	public String getStatusUpdReason() {
		return statusUpdReason;
	}

	public void setStatusUpdReason(String statusUpdReason) {
		this.statusUpdReason = statusUpdReason;
	}

	
	
	
}
