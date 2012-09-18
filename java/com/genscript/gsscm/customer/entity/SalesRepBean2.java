package com.genscript.gsscm.customer.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "v_unassigned_resource")
public class SalesRepBean2 implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 194475817717557228L;
	@Id
	private Integer salesId;
	private Integer deptId;
	private String name;
	private String resourceName;
	private String function;
	private String status;
	private Date modifyDate;
	private Integer modifiedBy;
	

	@Transient
	private String orgAssigned;
	@Transient
	private String terrAssigned;
	@Transient
	private String modifiedName;
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
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
	
	public String getOrgAssigned() {
		return orgAssigned;
	}
	public void setOrgAssigned(String orgAssigned) {
		this.orgAssigned = orgAssigned;
	}
	public String getTerrAssigned() {
		return terrAssigned;
	}
	public void setTerrAssigned(String terrAssigned) {
		this.terrAssigned = terrAssigned;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
