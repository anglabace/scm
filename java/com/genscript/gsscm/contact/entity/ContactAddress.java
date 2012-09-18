package com.genscript.gsscm.contact.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.genscript.gsscm.customer.entity.Organization;

/**
 * ContactAddress.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "contact_addresses")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContactAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7490941815995823265L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addr_id")
	private Integer addrId;
	private String addrType;
	private Integer contactNo;
	// private Customer customer;
	private String description;
	private String namePfx;
	private String firstName;
	private String midName;
	private String lastName;
	private String nameSfx;
	private String title;
	/*
	 * @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch =
	 * FetchType.LAZY)
	 * 
	 * @NotFound(action=NotFoundAction.IGNORE)
	 * 
	 * @JoinColumn(name = "org_id") private Organization organization;
	 */
	private Integer orgId;
	private String busPhone;
	private String busPhoneExt;
	private String altPhone;
	private String altPhoneExt;
	private String mobile;
	private String altMobile;
	private String homePhone;
	private String fax;
	private String faxExt;
	private String busEmail;
	private String altBusEmail;
	private String personalEmail;
	private String altPersonalEmail;
	private String addrLine1;
	private String addrLine2;
	private String addrLine3;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String addrClass;
	private String note;
	private String defaultFlag;
	private String status;
	private Date effFrom;
	private Date effTo;
	@Column(updatable = false, insertable = true)
	private Date creationDate;
	@Column(updatable = false, insertable = true)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	// 新增
	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getAddrId() {
		return addrId;
	}

	/**
	 * @return the addrType
	 */
	public String getAddrType() {
		return addrType;
	}

	/**
	 * @param addrType
	 *            the addrType to set
	 */
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the namePfx
	 */
	public String getNamePfx() {
		return namePfx;
	}

	/**
	 * @param namePfx
	 *            the namePfx to set
	 */
	public void setNamePfx(String namePfx) {
		this.namePfx = namePfx;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the midName
	 */
	public String getMidName() {
		return midName;
	}

	/**
	 * @param midName
	 *            the midName to set
	 */
	public void setMidName(String midName) {
		this.midName = midName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the nameSfx
	 */
	public String getNameSfx() {
		return nameSfx;
	}

	/**
	 * @param nameSfx
	 *            the nameSfx to set
	 */
	public void setNameSfx(String nameSfx) {
		this.nameSfx = nameSfx;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the organization
	 */
	/*
	 * public Organization getOrganization() { return organization; }
	 *//**
	 * @param organization
	 *            the organization to set
	 */
	/*
	 * public void setOrganization(Organization organization) {
	 * this.organization = organization; }
	 */

	/**
	 * @return the busPhone
	 */
	public String getBusPhone() {
		return busPhone;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @param busPhone
	 *            the busPhone to set
	 */
	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	/**
	 * @return the busPhoneExt
	 */
	public String getBusPhoneExt() {
		return busPhoneExt;
	}

	/**
	 * @param busPhoneExt
	 *            the busPhoneExt to set
	 */
	public void setBusPhoneExt(String busPhoneExt) {
		this.busPhoneExt = busPhoneExt;
	}

	/**
	 * @return the altPhone
	 */
	public String getAltPhone() {
		return altPhone;
	}

	/**
	 * @param altPhone
	 *            the altPhone to set
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
	 * @param altPhoneExt
	 *            the altPhoneExt to set
	 */
	public void setAltPhoneExt(String altPhoneExt) {
		this.altPhoneExt = altPhoneExt;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the altMobile
	 */
	public String getAltMobile() {
		return altMobile;
	}

	/**
	 * @param altMobile
	 *            the altMobile to set
	 */
	public void setAltMobile(String altMobile) {
		this.altMobile = altMobile;
	}

	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * @param homePhone
	 *            the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
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
	 * @param faxExt
	 *            the faxExt to set
	 */
	public void setFaxExt(String faxExt) {
		this.faxExt = faxExt;
	}

	/**
	 * @return the busEmail
	 */
	public String getBusEmail() {
		return busEmail;
	}

	/**
	 * @param busEmail
	 *            the busEmail to set
	 */
	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	/**
	 * @return the altBusEmail
	 */
	public String getAltBusEmail() {
		return altBusEmail;
	}

	/**
	 * @param altBusEmail
	 *            the altBusEmail to set
	 */
	public void setAltBusEmail(String altBusEmail) {
		this.altBusEmail = altBusEmail;
	}

	/**
	 * @return the personalEmail
	 */
	public String getPersonalEmail() {
		return personalEmail;
	}

	/**
	 * @param personalEmail
	 *            the personalEmail to set
	 */
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	/**
	 * @return the altPersonalEmail
	 */
	public String getAltPersonalEmail() {
		return altPersonalEmail;
	}

	/**
	 * @param altPersonalEmail
	 *            the altPersonalEmail to set
	 */
	public void setAltPersonalEmail(String altPersonalEmail) {
		this.altPersonalEmail = altPersonalEmail;
	}

	/**
	 * @return the addrLine1
	 */
	public String getAddrLine1() {
		return addrLine1;
	}

	/**
	 * @param addrLine1
	 *            the addrLine1 to set
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
	 * @param addrLine2
	 *            the addrLine2 to set
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
	 * @param addrLine3
	 *            the addrLine3 to set
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
	 * @param city
	 *            the city to set
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
	 * @param state
	 *            the state to set
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
	 * @param zipCode
	 *            the zipCode to set
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
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the addrClass
	 */
	public String getAddrClass() {
		return addrClass;
	}

	/**
	 * @param addrClass
	 *            the addrClass to set
	 */
	public void setAddrClass(String addrClass) {
		this.addrClass = addrClass;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the defaultFlag
	 */
	public String getDefaultFlag() {
		return defaultFlag;
	}

	/**
	 * @param defaultFlag
	 *            the defaultFlag to set
	 */
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	/**
	 * @return the effFrom
	 */
	public Date getEffFrom() {
		return effFrom;
	}

	/**
	 * @param effFrom
	 *            the effFrom to set
	 */
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}

	/**
	 * @return the effTo
	 */
	public Date getEffTo() {
		return effTo;
	}

	/**
	 * @param effTo
	 *            the effTo to set
	 */
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}

	/**
	 * @return the creationDate
	 */
	@Column(updatable = false, insertable = true)
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the createdBy
	 */
	@Column(updatable = false, insertable = true)
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @param addrId
	 *            the addrId to set
	 */
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	/**
	 * @return the contactNo
	 */
	public Integer getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo
	 *            the contactNo to set
	 */
	public void setContactNo(Integer contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
