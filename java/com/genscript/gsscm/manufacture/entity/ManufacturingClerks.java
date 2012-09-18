package com.genscript.gsscm.manufacture.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "manufacturing_clerks", catalog = "manufacturing")
public class ManufacturingClerks extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer clerkId;
	private Integer workGroupId;
    private Integer workCenterId;
	private String comment;
	private String status;
	
	@Transient
	private String modifyName;
	@Transient
	private String workCenterSupervisor;
	@Transient
	private String workGroupSupervisor;
	@Transient
	private String clerkName;
	
	
	public Integer getClerkId() {
		return clerkId;
	}
	public void setClerkId(Integer clerkId) {
		this.clerkId = clerkId;
	}
	public Integer getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(Integer workGroupId) {
		this.workGroupId = workGroupId;
	}
	public Integer getWorkCenterId() {
		return workCenterId;
	}
	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
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
	
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public String getWorkCenterSupervisor() {
		return workCenterSupervisor;
	}
	public void setWorkCenterSupervisor(String workCenterSupervisor) {
		this.workCenterSupervisor = workCenterSupervisor;
	}
	public String getWorkGroupSupervisor() {
		return workGroupSupervisor;
	}
	public void setWorkGroupSupervisor(String workGroupSupervisor) {
		this.workGroupSupervisor = workGroupSupervisor;
	}
	public String getClerkName() {
		return clerkName;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
