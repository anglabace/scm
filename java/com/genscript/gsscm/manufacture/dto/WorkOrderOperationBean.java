package com.genscript.gsscm.manufacture.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;

public class WorkOrderOperationBean implements Serializable{
	private static final long serialVersionUID = -7534499528161973562L;
	
	private Integer id;
	private String operationName;
	private String altOrderNo;
	private Integer soNo;
	private Integer soItemNo;
	private Integer srcSoNo;
	private String workGroupNames;
	private String exptdStartDate;
	private String exptdEndDate;
	private String customStartDate;
	private String customEndDate;
	private String status;
	private String comment;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getAltOrderNo() {
		return altOrderNo;
	}
	public void setAltOrderNo(String altOrderNo) {
		this.altOrderNo = altOrderNo;
	}
	
	public Integer getSoNo() {
		return soNo;
	}
	public void setSoNo(Integer soNo) {
		this.soNo = soNo;
	}
	public Integer getSoItemNo() {
		return soItemNo;
	}
	public void setSoItemNo(Integer soItemNo) {
		this.soItemNo = soItemNo;
	}
	public String getWorkGroupNames() {
		return workGroupNames;
	}
	public void setWorkGroupNames(String workGroupNames) {
		this.workGroupNames = workGroupNames;
	}
	public String getExptdStartDate() {
		return exptdStartDate;
	}
	public void setExptdStartDate(String exptdStartDate) {
		this.exptdStartDate = exptdStartDate;
	}
	public String getExptdEndDate() {
		return exptdEndDate;
	}
	public void setExptdEndDate(String exptdEndDate) {
		this.exptdEndDate = exptdEndDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCustomStartDate() {
		return customStartDate;
	}
	public void setCustomStartDate(String customStartDate) {
		this.customStartDate = customStartDate;
	}
	public String getCustomEndDate() {
		return customEndDate;
	}
	public void setCustomEndDate(String customEndDate) {
		this.customEndDate = customEndDate;
	}
	public Integer getSrcSoNo() {
		return srcSoNo;
	}
	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	

}
