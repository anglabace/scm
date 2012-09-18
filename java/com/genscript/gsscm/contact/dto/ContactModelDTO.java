package com.genscript.gsscm.contact.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.entity.ContactGrants;
import com.genscript.gsscm.contact.entity.ContactInterest;
import com.genscript.gsscm.contact.entity.ContactPersonalInfo;
import com.genscript.gsscm.contact.entity.ContactPublications;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.Organization;

@XmlType(name = "ContactModelDTO", namespace = WsConstants.NS)
public class ContactModelDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1510059634346219560L;
	private Integer contactNo;
	private String altNo;
	private Integer jobRoleId;
	private String namePfx;
	private String firstName;
	private String midName;
	private String lastName;
	private String nameSfx;
	private String busPhone;
	private String busPhoneExt;
	private String altPhone;
	private String altPhoneExt;
	private String mobile;
	private String altMobile;
	private String homePhone;
	private String fax;
	private String faxExt;
	private String city;
	private String state;
	private String country;
	private String addrLine1;
	private String addrLine2;
	private String addrLine3;
	private String zipCode;
	private String title;
	private String supervisor;
	private Integer orgId;
    private Organization organization;
	private Integer divisionId;
	private Divisions division;
	private Integer deptId;
	private Departments department;
	private String busEmail;
	private String altBusEmail;
	private String personalEmail;
	private String altPersonalEmail;
	private String bstCallTimeFrom;
	private String bstCallTimeTo;
	private String bstCallTmzn;
	private String web;
	private String donotCode;
	private Integer salesTerritory;
	private Integer salesGroup;
	private Integer salesContact;
	private Integer techSupport;
	private Integer gsCoId;
	private String note;
	private String comment;
	private Integer source;
	private Integer interestRating;
	private String status;
	private String statusReason;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private List<Integer> delPubIdList;
	private List<Integer> delGrantIdList;
	private List<Integer> delIntIdList;
	private List<Integer> delAddrIdList;
	private List<Integer> delContactIdList;
	private List<ContactPublications> pubsList;
	private List<ContactGrants> grantsList;
	private List<ContactInterest> interestList;
	private List<ContactDTO> contactDTOList;
	private List<ContactAddressDTO> addressList;
	private ContactPersonalInfo personal;
	private Date lastActivity;
	private Long contactCount; 
	
 

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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
	 * @return the altNo
	 */
	public String getAltNo() {
		return altNo;
	}

	/**
	 * @param altNo
	 *            the altNo to set
	 */
	public void setAltNo(String altNo) {
		this.altNo = altNo;
	}

	/**
	 * @return the jobRoleId
	 */
	public Integer getJobRoleId() {
		return jobRoleId;
	}

	/**
	 * @param jobRoleId
	 *            the jobRoleId to set
	 */
	public void setJobRoleId(Integer jobRoleId) {
		this.jobRoleId = jobRoleId;
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
	 * @return the busPhone
	 */
	public String getBusPhone() {
		return busPhone;
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
	 * @return the divisionId
	 */
	public Integer getDivisionId() {
		return divisionId;
	}

	/**
	 * @param divisionId
	 *            the divisionId to set
	 */
	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	/**
	 * @return the deptId
	 */
	public Integer getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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
	 * @return the bstCallTimeFrom
	 */
	public String getBstCallTimeFrom() {
		return bstCallTimeFrom;
	}

	/**
	 * @param bstCallTimeFrom
	 *            the bstCallTimeFrom to set
	 */
	public void setBstCallTimeFrom(String bstCallTimeFrom) {
		this.bstCallTimeFrom = bstCallTimeFrom;
	}

	/**
	 * @return the bstCallTimeTo
	 */
	public String getBstCallTimeTo() {
		return bstCallTimeTo;
	}

	/**
	 * @param bstCallTimeTo
	 *            the bstCallTimeTo to set
	 */
	public void setBstCallTimeTo(String bstCallTimeTo) {
		this.bstCallTimeTo = bstCallTimeTo;
	}

	/**
	 * @return the bstCallTmzn
	 */
	public String getBstCallTmzn() {
		return bstCallTmzn;
	}

	/**
	 * @param bstCallTmzn
	 *            the bstCallTmzn to set
	 */
	public void setBstCallTmzn(String bstCallTmzn) {
		this.bstCallTmzn = bstCallTmzn;
	}

	/**
	 * @return the web
	 */
	public String getWeb() {
		return web;
	}

	/**
	 * @param web
	 *            the web to set
	 */
	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * @return the donotCode
	 */
	public String getDonotCode() {
		return donotCode;
	}

	/**
	 * @param donotCode
	 *            the donotCode to set
	 */
	public void setDonotCode(String donotCode) {
		this.donotCode = donotCode;
	}

	/**
	 * @return the salesTerritory
	 */
	public Integer getSalesTerritory() {
		return salesTerritory;
	}

	/**
	 * @param salesTerritory
	 *            the salesTerritory to set
	 */
	public void setSalesTerritory(Integer salesTerritory) {
		this.salesTerritory = salesTerritory;
	}

	/**
	 * @return the salesGroup
	 */
	public Integer getSalesGroup() {
		return salesGroup;
	}

	/**
	 * @param salesGroup
	 *            the salesGroup to set
	 */
	public void setSalesGroup(Integer salesGroup) {
		this.salesGroup = salesGroup;
	}

	/**
	 * @return the salesContact
	 */
	public Integer getSalesContact() {
		return salesContact;
	}

	/**
	 * @param salesContact
	 *            the salesContact to set
	 */
	public void setSalesContact(Integer salesContact) {
		this.salesContact = salesContact;
	}

	/**
	 * @return the techSupport
	 */
	public Integer getTechSupport() {
		return techSupport;
	}

	/**
	 * @param techSupport
	 *            the techSupport to set
	 */
	public void setTechSupport(Integer techSupport) {
		this.techSupport = techSupport;
	}
	/**
	 * @return the gsCoId
	 */
	public Integer getGsCoId() {
		return gsCoId;
	}

	/**
	 * @param gsCoId
	 *            the gsCoId to set
	 */
	public void setGsCoId(Integer gsCoId) {
		this.gsCoId = gsCoId;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the source
	 */
	public Integer getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Integer source) {
		this.source = source;
	}

	/**
	 * @return the interestRating
	 */
	public Integer getInterestRating() {
		return interestRating;
	}

	/**
	 * @param interestRating
	 *            the interestRating to set
	 */
	public void setInterestRating(Integer interestRating) {
		this.interestRating = interestRating;
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

	/**
	 * @return the statusReason
	 */
	public String getStatusReason() {
		return statusReason;
	}

	/**
	 * @param statusReason
	 *            the statusReason to set
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	/**
	 * @return the creationDate
	 */
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
	 * @return the delPubIdList
	 */
	public List<Integer> getDelPubIdList() {
		return delPubIdList;
	}

	/**
	 * @param delPubIdList
	 *            the delPubIdList to set
	 */
	public void setDelPubIdList(List<Integer> delPubIdList) {
		this.delPubIdList = delPubIdList;
	}

	/**
	 * @return the delIntIdList
	 */
	public List<Integer> getDelIntIdList() {
		return delIntIdList;
	}

	/**
	 * @param delIntIdList
	 *            the delIntIdList to set
	 */
	public void setDelIntIdList(List<Integer> delIntIdList) {
		this.delIntIdList = delIntIdList;
	}

	/**
	 * @return the pubsList
	 */
	public List<ContactPublications> getPubsList() {
		return pubsList;
	}

	/**
	 * @param pubsList
	 *            the pubsList to set
	 */
	public void setPubsList(List<ContactPublications> pubsList) {
		this.pubsList = pubsList;
	}

	/**
	 * @return the grantsList
	 */
	public List<ContactGrants> getGrantsList() {
		return grantsList;
	}

	/**
	 * @param grantsList
	 *            the grantsList to set
	 */
	public void setGrantsList(List<ContactGrants> grantsList) {
		this.grantsList = grantsList;
	}

	/**
	 * @return the interestList
	 */
	public List<ContactInterest> getInterestList() {
		return interestList;
	}

	/**
	 * @param interestList
	 *            the interestList to set
	 */
	public void setInterestList(List<ContactInterest> interestList) {
		this.interestList = interestList;
	}

 	public Organization getOrganization() {
		return organization;
	}
 
	public void setOrganization(Organization organization) {
		this.organization = organization;
	} 

	/**
	 * @return the division
	 */
	public Divisions getDivision() {
		return division;
	}

	/**
	 * @param division
	 *            the division to set
	 */
	public void setDivision(Divisions division) {
		this.division = division;
	}

	/**
	 * @return the department
	 */
	public Departments getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Departments department) {
		this.department = department;
	}

	/**
	 * @return the delAddrIdList
	 */
	public List<Integer> getDelAddrIdList() {
		return delAddrIdList;
	}

	/**
	 * @param delAddrIdList
	 *            the delAddrIdList to set
	 */
	public void setDelAddrIdList(List<Integer> delAddrIdList) {
		this.delAddrIdList = delAddrIdList;
	}

	/**
	 * @return the addressList
	 */
	public List<ContactAddressDTO> getAddressList() {
		return addressList;
	}

	/**
	 * @param addressList
	 *            the addressList to set
	 */
	public void setAddressList(List<ContactAddressDTO> addressList) {
		this.addressList = addressList;
	}

	/**
	 * @return the contactDTOList
	 */
	public List<ContactDTO> getContactDTOList() {
		return contactDTOList;
	}

	/**
	 * @param contactDTOList
	 *            the contactDTOList to set
	 */
	public void setContactDTOList(List<ContactDTO> contactDTOList) {
		this.contactDTOList = contactDTOList;
	}

	/**
	 * @return the delGrantIdList
	 */
	public List<Integer> getDelGrantIdList() {
		return delGrantIdList;
	}

	/**
	 * @param delGrantIdList
	 *            the delGrantIdList to set
	 */
	public void setDelGrantIdList(List<Integer> delGrantIdList) {
		this.delGrantIdList = delGrantIdList;
	}

	/**
	 * @return the delContactIdList
	 */
	public List<Integer> getDelContactIdList() {
		return delContactIdList;
	}

	/**
	 * @param delContactIdList
	 *            the delContactIdList to set
	 */
	public void setDelContactIdList(List<Integer> delContactIdList) {
		this.delContactIdList = delContactIdList;
	}

	/**
	 * @return the personal
	 */
	public ContactPersonalInfo getPersonal() {
		return personal;
	}

	/**
	 * @param personal the personal to set
	 */
	public void setPersonal(ContactPersonalInfo personal) {
		this.personal = personal;
	}

	/**
	 * @return the lastActivity
	 */
	public Date getLastActivity() {
		return lastActivity;
	}

	/**
	 * @param lastActivity the lastActivity to set
	 */
	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}

	/**
	 * @return the contactCount
	 */
	public Long getContactCount() {
		return contactCount;
	}

	/**
	 * @param contactCount the contactCount to set
	 */
	public void setContactCount(Long contactCount) {
		this.contactCount = contactCount;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

}
