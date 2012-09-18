package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * DIVISIONS.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "divisions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Divisions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985134634326832438L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "division_id")
	private Integer divisionId;
	private Integer orgId;
	private String name;
	private String description;
	private String supervisor;
	private String langCode;
	private String phone;
	private String phoneExt;
	private String altPhone;
	private String altPhoneExt;
	private String fax;
	private String faxExt;
	private String addrLine1;
	private String addrLine2;
	private String addrLine3;
	private String addrType;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String activeFlag;
    private String updateStatusReason;
	@Column(insertable = true, updatable = false)
	private Date creationDate = new Date();
	@Column(insertable = true, updatable = false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}


	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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

	public Divisions() {
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the phoneExt
	 */
	public String getPhoneExt() {
		return phoneExt;
	}

	/**
	 * @param phoneExt the phoneExt to set
	 */
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	/**
	 * @return the altPhone
	 */
	public String getAltPhone() {
		return altPhone;
	}

	/**
	 * @param altPhone the altPhone to set
	 */
	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	/**
	 * @return the altPhoneExt
	 */
	public String getAltPhoneExt() {
		return altPhoneExt;
	}

	/**
	 * @param altPhoneExt the altPhoneExt to set
	 */
	public void setAltPhoneExt(String altPhoneExt) {
		this.altPhoneExt = altPhoneExt;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the faxExt
	 */
	public String getFaxExt() {
		return faxExt;
	}

	/**
	 * @param faxExt the faxExt to set
	 */
	public void setFaxExt(String faxExt) {
		this.faxExt = faxExt;
	}

	/**
	 * @return the addrLine1
	 */
	public String getAddrLine1() {
		return addrLine1;
	}

	/**
	 * @param addrLine1 the addrLine1 to set
	 */
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	/**
	 * @return the addrLine2
	 */
	public String getAddrLine2() {
		return addrLine2;
	}

	/**
	 * @param addrLine2 the addrLine2 to set
	 */
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	/**
	 * @return the addrLine3
	 */
	public String getAddrLine3() {
		return addrLine3;
	}

	/**
	 * @param addrLine3 the addrLine3 to set
	 */
	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddrType() {
		return addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	public String getUpdateStatusReason() {
		return updateStatusReason;
	}

	public void setUpdateStatusReason(String updateStatusReason) {
		this.updateStatusReason = updateStatusReason;
	}
}
