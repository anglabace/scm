package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SALES_REP.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "sales_rep")
public class SalesRep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1424634454531834104L;
	@Id
	@Column(name = "sales_id")
	private Integer salesId;
	private Integer deptId;
	private String resourceName;
	private String supervisor;
	private String function;
	private String description;
	private String status;
//	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
//	private SalesGroup salesGroup;
	private Integer salesGroupId;
	@Column(name = "company_id")
	private Integer gsCoId;
	@Column(insertable=true,updatable=false)
	private Date creationDate;
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	@Transient
	private String modifiedName;
	@Transient
	private String deptName;
	@Transient
	private String functionText;
	@Transient
	private String userName;
	
	
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//	public SalesGroup getSalesGroup() {
//		return salesGroup;
//	}
//	public void setSalesGroup(SalesGroup salesGroup) {
//		this.salesGroup = salesGroup;
//	}
	public Integer getGsCoId() {
		return gsCoId;
	}
	public Integer getSalesGroupId() {
		return salesGroupId;
	}
	public void setSalesGroupId(Integer salesGroupId) {
		this.salesGroupId = salesGroupId;
	}
	public void setGsCoId(Integer gsCoId) {
		this.gsCoId = gsCoId;
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
	
	public String getModifiedName() {
		return modifiedName;
	}
	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFunctionText() {
		return functionText;
	}
	public void setFunctionText(String functionText) {
		this.functionText = functionText;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
