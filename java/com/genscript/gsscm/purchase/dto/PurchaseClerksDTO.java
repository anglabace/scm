package com.genscript.gsscm.purchase.dto;

import java.util.Date;


import org.apache.commons.lang.builder.ToStringBuilder;

public class PurchaseClerksDTO {
	private Integer clerkId;
	private String description;
	private Integer supervisor;
	private String status;
	protected Date creationDate = new Date();
	protected Integer createdBy;
	protected Date modifyDate = new Date();
	protected Integer modifiedBy;
	
	private String clerkName;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public Integer getClerkId() {
		return clerkId;
	}
	public void setClerkId(Integer clerkId) {
		this.clerkId = clerkId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Integer supervisor) {
		this.supervisor = supervisor;
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

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	
}
