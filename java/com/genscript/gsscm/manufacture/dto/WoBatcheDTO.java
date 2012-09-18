package com.genscript.gsscm.manufacture.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "WorkOrderDTO", namespace = WsConstants.NS)
public class WoBatcheDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer woBatchId;
	private String batchNo;
	private String batchType;
	private String status;
	private Date receiveDate;
	private Integer receivedBy;
	private String batchFunction;
	protected Date creationDate = new Date();
	protected Integer createdBy;
	private String orderNoStr;//包中的订单号
	
	
	public Integer getWoBatchId() {
		return woBatchId;
	}

	public void setWoBatchId(Integer woBatchId) {
		this.woBatchId = woBatchId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getBatchType() {
		return batchType;
	}

	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Integer getReceivedBy() {
		return receivedBy;
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

	public String getOrderNoStr() {
		return orderNoStr;
	}

	public void setOrderNoStr(String orderNoStr) {
		this.orderNoStr = orderNoStr;
	}

	public String getBatchFunction() {
		return batchFunction;
	}

	public void setBatchFunction(String batchFunction) {
		this.batchFunction = batchFunction;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
