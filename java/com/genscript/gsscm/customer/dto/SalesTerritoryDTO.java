package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.customer.entity.SalesTerritoryAssignment;

public class SalesTerritoryDTO implements Serializable{


	private static final long serialVersionUID = -8086578868437314414L;
	private Integer territoryId;
	private String territoryCode;
	private String territoryName;
	private String description;
	private String territoryType;
	private Integer gsCoId;
	private String territoryClassification;
	private Date creationDate = new Date();
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	private String modifiedName;
	private String status;
	private List<SalesTerritoryAssignment> zoneList;
	private List<SalesTerritoryAssignment> delZoneList;
	public Integer getTerritoryId() {
		return territoryId;
	}
	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}
	public String getTerritoryCode() {
		return territoryCode;
	}
	public void setTerritoryCode(String territoryCode) {
		this.territoryCode = territoryCode;
	}
	public String getTerritoryName() {
		return territoryName;
	}
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTerritoryType() {
		return territoryType;
	}
	public void setTerritoryType(String territoryType) {
		this.territoryType = territoryType;
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
	public List<SalesTerritoryAssignment> getZoneList() {
		return zoneList;
	}
	public void setZoneList(List<SalesTerritoryAssignment> zoneList) {
		this.zoneList = zoneList;
	}
	public List<SalesTerritoryAssignment> getDelZoneList() {
		return delZoneList;
	}
	public void setDelZoneList(List<SalesTerritoryAssignment> delZoneList) {
		this.delZoneList = delZoneList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTerritoryClassification() {
		return territoryClassification;
	}
	public void setTerritoryClassification(String territoryClassification) {
		this.territoryClassification = territoryClassification;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
