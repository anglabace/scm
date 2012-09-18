package com.genscript.gsscm.customer.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "sales_territory_assign_process")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesTerritoryAssignProcess implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7095965892677652072L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sequence;
	private Integer ruleId;
	private String processName;
	private String status;
	private String description;
	private Integer modifiedBy;
	private Date modifyDate;
	
	@Transient
	private String modifiedName;
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
