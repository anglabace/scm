package com.genscript.gsscm.basedata.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * PUBLIC STATE.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "state", catalog="common")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PbState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 284107693129397050L;

	private Integer stateId;
	private Integer countryId;
	private String stateCode;
	private String name;
	private String description;
//	private Boolean activeFlag;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
//	private Set<PbCity> pbCitys = new LinkedHashSet<PbCity>();

	private Double taxRate;

	private String status;
	
	private String modifiedName;
	
	private String note;
//	@OneToMany(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "state_id")
//	@Fetch(FetchMode.SUBSELECT)
//	@OrderBy(value="stateId")
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	public Set<PbCity> getPbCitys() {
//		return pbCitys;
//	}
//	public void setPbCitys(Set<PbCity> pbCitys) {
//		this.pbCitys = pbCitys;
//	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id")
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	@Column(name = "country_id")
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@Transient
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	@Transient
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Transient
	public String getModifiedName() {
		return modifiedName;
	}
	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}
	@Transient
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
