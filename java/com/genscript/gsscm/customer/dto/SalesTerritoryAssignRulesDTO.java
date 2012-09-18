package com.genscript.gsscm.customer.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.customer.entity.SalesTerritoryAssignProcess;

public class SalesTerritoryAssignRulesDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4305030666801709344L;
	private Integer ruleId;
	private String ruleName;
	private String status;
	private String description;
	private Integer modifiedBy;
	private Date modifyDate;
	private String modifiedName;
	
	private List<SalesTerritoryAssignProcess> assignProcessList;
	private List<SalesTerritoryAssignProcess> delAssignProcessList;
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getModifiedName() {
		return modifiedName;
	}
	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}
	public List<SalesTerritoryAssignProcess> getAssignProcessList() {
		return assignProcessList;
	}
	public void setAssignProcessList(
			List<SalesTerritoryAssignProcess> assignProcessList) {
		this.assignProcessList = assignProcessList;
	}
	public List<SalesTerritoryAssignProcess> getDelAssignProcessList() {
		return delAssignProcessList;
	}
	public void setDelAssignProcessList(
			List<SalesTerritoryAssignProcess> delAssignProcessList) {
		this.delAssignProcessList = delAssignProcessList;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
