package com.genscript.gsscm.systemsetting.dto;

import java.util.Date;

public class BillTerritoryZoneDTO {
	private Integer zoneId;
	private String sessZoneId;
	private Integer territoryId;
	private String continent;
	private String country;
	private String state;
	private String zipFrom;
	private String zipTo;
	protected Date creationDate;
	protected Integer createdBy;
	protected Date modifyDate;
	protected Integer modifiedBy;
	
	//
	private String modifiedByUser;
	
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public Integer getTerritoryId() {
		return territoryId;
	}
	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipFrom() {
		return zipFrom;
	}
	public void setZipFrom(String zipFrom) {
		this.zipFrom = zipFrom;
	}
	public String getZipTo() {
		return zipTo;
	}
	public void setZipTo(String zipTo) {
		this.zipTo = zipTo;
	}
	public String getSessZoneId() {
		return sessZoneId;
	}
	public void setSessZoneId(String sessZoneId) {
		this.sessZoneId = sessZoneId;
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
	public String getModifiedByUser() {
		return modifiedByUser;
	}
	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}
	
}
