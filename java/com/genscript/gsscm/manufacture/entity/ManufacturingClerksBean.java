package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_manclerk_user",catalog = "manufacturing")
public class ManufacturingClerksBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer clerkId;
	private String loginName;
	private Integer workGroupId;
	private String workGroupName;
    private Integer workCenterId;
    private String workCenterName;
	private String comment;
	private String status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClerkId() {
		return clerkId;
	}
	public void setClerkId(Integer clerkId) {
		this.clerkId = clerkId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(Integer workGroupId) {
		this.workGroupId = workGroupId;
	}
	public String getWorkGroupName() {
		return workGroupName;
	}
	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}
	public Integer getWorkCenterId() {
		return workCenterId;
	}
	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
	}
	public String getWorkCenterName() {
		return workCenterName;
	}
	public void setWorkCenterName(String workCenterName) {
		this.workCenterName = workCenterName;
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

}
