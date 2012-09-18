package com.genscript.gsscm.customer.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.customer.entity.SalesResourceAssignOrg;
import com.genscript.gsscm.customer.entity.SalesResourceAssignTerritory;

public class SalesRepDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8763041495428748440L;
	private Integer salesId;
	private Integer deptId;
	private String resourceName;
	private String supervisor;
	private String function;
	private String description;
	private String status;
	private Integer salesGroupId;
	private Integer gsCoId;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private String modifiedName;
	private String deptName;
	private List<SalesResourceAssignOrg> orgAssignedList;
	private List<SalesResourceAssignTerritory> terrAssignedList;
	private List<SalesResourceAssignOrg> delOrgAssignedList;
	private List<SalesResourceAssignTerritory> delTerrAssignedList;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getSalesGroupId() {
		return salesGroupId;
	}
	public void setSalesGroupId(Integer salesGroupId) {
		this.salesGroupId = salesGroupId;
	}
	public Integer getGsCoId() {
		return gsCoId;
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
	
	public List<SalesResourceAssignOrg> getOrgAssignedList() {
		return orgAssignedList;
	}
	public void setOrgAssignedList(List<SalesResourceAssignOrg> orgAssignedList) {
		this.orgAssignedList = orgAssignedList;
	}
	public List<SalesResourceAssignTerritory> getTerrAssignedList() {
		return terrAssignedList;
	}
	public void setTerrAssignedList(
			List<SalesResourceAssignTerritory> terrAssignedList) {
		this.terrAssignedList = terrAssignedList;
	}
	public List<SalesResourceAssignOrg> getDelOrgAssignedList() {
		return delOrgAssignedList;
	}
	public void setDelOrgAssignedList(
			List<SalesResourceAssignOrg> delOrgAssignedList) {
		this.delOrgAssignedList = delOrgAssignedList;
	}
	public List<SalesResourceAssignTerritory> getDelTerrAssignedList() {
		return delTerrAssignedList;
	}
	public void setDelTerrAssignedList(
			List<SalesResourceAssignTerritory> delTerrAssignedList) {
		this.delTerrAssignedList = delTerrAssignedList;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
