package com.genscript.gsscm.manufacture.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;

public class ManufacturingClerksDTO {
	
	private Integer clerkId;
	private WorkGroup workGroup;
    private WorkCenter workCenter;
	private String comment;
	private String status;
	
	
	public Integer getClerkId() {
		return clerkId;
	}
	public void setClerkId(Integer clerkId) {
		this.clerkId = clerkId;
	}
	public WorkGroup getWorkGroup() {
		return workGroup;
	}
	public void setWorkGroup(WorkGroup workGroup) {
		this.workGroup = workGroup;
	}
	public WorkCenter getWorkCenter() {
		return workCenter;
	}
	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
