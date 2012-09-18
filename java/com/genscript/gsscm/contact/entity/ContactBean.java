package com.genscript.gsscm.contact.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "V_all_contacts")
public class ContactBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8660610193957487943L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_no")
	private Integer contactNo;
	private String altNo;
	private String status;
	private String firstName;
	private String midName;
	private String lastName;
	private String country;
	private String zipCode;
	private String organizationName;
	private String divisionName;
	private String departmentName;
	private String addrLine1;
	private String addrLine2;
	private String addrLine3;
	private String state;
	private String territoryCode;
	private String groupCode;
	private String salesContact;
	private String techSupport;
	private String busEmail;
	private String busPhone;
	private Integer salesContactId;
	private Integer techSupportId;
	private Date creationDate;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getContactNo() {
		return contactNo;
	}

	public void setContactNo(Integer contactNo) {
		this.contactNo = contactNo;
	}

	public String getAltNo() {
		return altNo;
	}

	public void setAltNo(String altNo) {
		this.altNo = altNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getAddrLine3() {
		return addrLine3;
	}

	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTerritoryCode() {
		return territoryCode;
	}

	public void setTerritoryCode(String territoryCode) {
		this.territoryCode = territoryCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getSalesContact() {
		return salesContact;
	}

	public void setSalesContact(String salesContact) {
		this.salesContact = salesContact;
	}

	public String getTechSupport() {
		return techSupport;
	}

	public void setTechSupport(String techSupport) {
		this.techSupport = techSupport;
	}

	public Integer getSalesContactId() {
		return salesContactId;
	}

	public void setSalesContactId(Integer salesContactId) {
		this.salesContactId = salesContactId;
	}

	public Integer getTechSupportId() {
		return techSupportId;
	}

	public void setTechSupportId(Integer techSupportId) {
		this.techSupportId = techSupportId;
	}

	public String getBusEmail() {
		return busEmail;
	}

	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
