package com.genscript.gsscm.manufacture.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WoBatches entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wo_batches", catalog = "manufacturing")
public class WoBatche implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2818631833557769170L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wo_batch_id", unique = true, nullable = false)
	private Integer woBatchId;
	private String batchNo;
	private String batchType;
	private String status;
	private Date receiveDate;
	private Integer receivedBy;
	private String batchFunction;
	@Column(updatable = false)
	protected Date creationDate = new Date();
	@Column(updatable = false)
	protected Integer createdBy;
	// Constructors

	/** default constructor */
	public WoBatche() {
	}

	public Integer getWoBatchId() {
		return this.woBatchId;
	}

	public void setWoBatchId(Integer woBatchId) {
		this.woBatchId = woBatchId;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getBatchType() {
		return this.batchType;
	}

	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Integer getReceivedBy() {
		return this.receivedBy;
	}

	public void setReceivedBy(Integer receivedBy) {
		this.receivedBy = receivedBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getBatchFunction() {
		return batchFunction;
	}

	public void setBatchFunction(String batchFunction) {
		this.batchFunction = batchFunction;
	}

}