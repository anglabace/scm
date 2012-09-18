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
 * SALES_TERRITORY_ASSIGNMENT.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "sales_territory_assignment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesTerritoryAssignment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3187852596355915393L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assign_id")
	private Integer assignId;
//	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
//	private SalesTerritory salesTerritory;
	private Integer territoryId;
	private String countryCode;
	private String stateCode;
	private String fromZip;
	private String toZip;
//	private String regionLevel;
//	private String regionCode;
	//private Integer gsCoId;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	@Transient
	private String modifiedName;
	@Transient
	private String continent;
	
	public Integer getAssignId() {
		return assignId;
	}


	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}


//	public SalesTerritory getSalesTerritory() {
//		return salesTerritory;
//	}
//
//
//	public void setSalesTerritory(SalesTerritory salesTerritory) {
//		this.salesTerritory = salesTerritory;
//	}




	public Integer getTerritoryId() {
		return territoryId;
	}


	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}

	



/*	public Integer getGsCoId() {
		return gsCoId;
	}


	public void setGsCoId(Integer gsCoId) {
		this.gsCoId = gsCoId;
	}*/


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public String getStateCode() {
		return stateCode;
	}


	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}


	public String getFromZip() {
		return fromZip;
	}


	public void setFromZip(String fromZip) {
		this.fromZip = fromZip;
	}


	public String getToZip() {
		return toZip;
	}


	public void setToZip(String toZip) {
		this.toZip = toZip;
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


	public String getContinent() {
		System.out.println(continent);
		return continent;
	}


	public void setContinent(String continent) {
		this.continent = continent;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
