package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * SALES_GROUP.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "sales_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3416406511076513629L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Integer groupId;
	private Integer deptId;
	private String groupCode;
	private Integer supervisor;
	private String status;
	private String description;
	@Column(name = "company_id")
	private Integer gsCoId;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	@Transient
	private String deptName;
	@Transient
	private String supervisorName;
	
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getGsCoId() {
		return gsCoId;
	}
	public void setGsCoId(Integer gsCoId) {
		this.gsCoId = gsCoId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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
	//	public SalesTerritory getSalesTerritory() {
//		return salesTerritory;
//	}
//	public void setSalesTerritory(SalesTerritory salesTerritory) {
//		this.salesTerritory = salesTerritory;
//	}
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
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public SalesGroup(){}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
