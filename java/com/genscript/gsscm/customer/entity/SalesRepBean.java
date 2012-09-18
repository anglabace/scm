package com.genscript.gsscm.customer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "v_assigned_resource")
public class SalesRepBean implements java.io.Serializable{
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

	@Transient
	private String orgAssigned;
	@Transient
	private String terrAssigned;
	@Transient
	private String functionText;
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
	public String getFunctionText() {
		return functionText;
	}
	public void setFunctionText(String functionText) {
		this.functionText = functionText;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
