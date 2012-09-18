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
 * SALES_TERRITORY.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "sales_territory")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesTerritory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7011131083985170134L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "territory_id")
	private Integer territoryId;
	private String territoryCode;
	private String territoryName;
	private String description;
	private String territoryType;
	@Column(name = "company_id")
	private Integer gsCoId;
	private String status;
	private String territoryClassification;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	@Transient
	private String modifiedName;
	
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
	public String getTerritoryName() {
		return territoryName;
	}
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}
	public SalesTerritory(){}
	public SalesTerritory(String territoryCode, String description,
			Integer gsCoId, Integer createdBy, Integer modifiedBy) {
		super();
		this.territoryCode = territoryCode;
		this.description = description;
		this.gsCoId = gsCoId;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	public String getTerritoryType() {
		return territoryType;
	}
	public void setTerritoryType(String territoryType) {
		this.territoryType = territoryType;
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
