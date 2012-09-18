package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.Organization;

@XmlType(name = "CustAddrOperDTO", namespace = WsConstants.NS)
public class CustAddrOperDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2814622322491340727L;
	private Integer addrId;
	private String addrType;
	private Customer customer;
	private String description;
	private String namePfx;
	private String firstName;
	private String midName;
	private String lastName;
	private String nameSfx;
	private String title;
//	private Organization organization;
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
	private Date effFrom;
	private Date effTo;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	private String operateType;
	private String status;
	private String fullAddrLine;
	 //新增
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public String getAddrType() {
		return addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNamePfx() {
		return namePfx;
	}

	public void setNamePfx(String namePfx) {
		this.namePfx = namePfx;
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

	public String getNameSfx() {
		return nameSfx;
	}

	public void setNameSfx(String nameSfx) {
		this.nameSfx = nameSfx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

/*	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}*/

	
	
	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public String getBusPhoneExt() {
		return busPhoneExt;
	}

	public void setBusPhoneExt(String busPhoneExt) {
		this.busPhoneExt = busPhoneExt;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public String getAltPhoneExt() {
		return altPhoneExt;
	}

	public void setAltPhoneExt(String altPhoneExt) {
		this.altPhoneExt = altPhoneExt;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAltMobile() {
		return altMobile;
	}

	public void setAltMobile(String altMobile) {
		this.altMobile = altMobile;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFaxExt() {
		return faxExt;
	}

	public void setFaxExt(String faxExt) {
		this.faxExt = faxExt;
	}

	public String getBusEmail() {
		return busEmail;
	}

	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	public String getAltBusEmail() {
		return altBusEmail;
	}

	public void setAltBusEmail(String altBusEmail) {
		this.altBusEmail = altBusEmail;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getAltPersonalEmail() {
		return altPersonalEmail;
	}

	public void setAltPersonalEmail(String altPersonalEmail) {
		this.altPersonalEmail = altPersonalEmail;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddrClass() {
		return addrClass;
	}

	public void setAddrClass(String addrClass) {
		this.addrClass = addrClass;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public Date getEffFrom() {
		return effFrom;
	}

	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}

	public Date getEffTo() {
		return effTo;
	}

	public void setEffTo(Date effTo) {
		this.effTo = effTo;
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

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public CustAddrOperDTO(){}

	public CustAddrOperDTO(Integer addrId, String addrType,
			String description, String namePfx, String firstName,
			String midName, String lastName, String nameSfx, String title,
			Integer orgId, String busPhone, String busPhoneExt,
			String altPhone, String altPhoneExt, String mobile,
			String altMobile, String homePhone, String fax, String faxExt,
			String busEmail, String altBusEmail, String personalEmail,
			String altPersonalEmail, String addrLine1, String addrLine2,
			String addrLine3, String city, String state, String zipCode,
			String country, String addrClass, String note,
			String defaultFlag, Date effFrom, Date effTo, Integer createdBy,
			Integer modifiedBy, String operateType) {
		super();
		this.addrId = addrId;
		this.addrType = addrType;
		this.description = description;
		this.namePfx = namePfx;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.nameSfx = nameSfx;
		this.title = title;
		this.orgId=orgId;
		//this.organization = organization;
		this.busPhone = busPhone;
		this.busPhoneExt = busPhoneExt;
		this.altPhone = altPhone;
		this.altPhoneExt = altPhoneExt;
		this.mobile = mobile;
		this.altMobile = altMobile;
		this.homePhone = homePhone;
		this.fax = fax;
		this.faxExt = faxExt;
		this.busEmail = busEmail;
		this.altBusEmail = altBusEmail;
		this.personalEmail = personalEmail;
		this.altPersonalEmail = altPersonalEmail;
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.addrLine3 = addrLine3;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.addrClass = addrClass;
		this.note = note;
		this.defaultFlag = defaultFlag;
		this.effFrom = effFrom;
		this.effTo = effTo;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.operateType = operateType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullAddrLine() {
		return fullAddrLine;
	}

	public void setFullAddrLine(String fullAddrLine) {
		this.fullAddrLine = fullAddrLine;
	}
}
