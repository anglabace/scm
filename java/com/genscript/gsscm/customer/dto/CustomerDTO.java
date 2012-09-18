package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.customer.entity.CustInfoStat;
import com.genscript.gsscm.customer.entity.CustomerCase;
import com.genscript.gsscm.customer.entity.CustomerGrants;
import com.genscript.gsscm.customer.entity.CustomerInterest;
import com.genscript.gsscm.customer.entity.CustomerPersonalInfo;
import com.genscript.gsscm.customer.entity.CustomerPoints;
import com.genscript.gsscm.customer.entity.CustomerPublications;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.Organization;

@XmlType(name = "CustomerMain", namespace = WsConstants.NS)
public class CustomerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8172163198753213689L;
	private Integer custNo;
	private String altNo;
	private Integer custRoleId;
	// private CustomerRole customerRole;
	private String namePfx;
	private String sessCustNo;
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
	private String addrLine1;
	private String addrLine2;
	private String addrLine3;
	// private SalesTax salesTax;
	private String taxId;
	private String altTaxId;
	private String taxExemptFlag;
	private Integer salesTerritory;
	// private SalesTerritory salesTerritory;
	private Integer salesGroup;
	// private SalesGroup salesGroup;
	private Integer salesContact;
	// private SalesRep salesContact;
	private Integer techSupport;
	// private SalesRep techSupport;
	private String prefContactType;
	private String prefEmailFmt;
	private Integer prefPaymentTerm;
	private String prefPaymentMthd;
	private Integer prefShipMthd;
	private String prefShipFromLoc;
	private String overShipFlag;
	private String partialShipFlag;
	private String substitutionFlag;
	private String discountFlag;
	private String acknlgReqFlag;
	private String pointsFlag;
	private Integer crRatingId;
	// private CreditRating creditRating;//cr_rating_id
	private Integer rfmRatingId;
	private String rfmRatingCd;
	private Integer purchasingCpbl;
	// private RfmRating rfmRating;
	private Integer rateGroup;
	// private RateGroup rateGroup;
	private String transCurrency;
	private String paymentCurrency;
	private Integer companyId;
	private String note;
	private String comment;
	private Integer source;
	private String status;
	private String statusReason;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private String orgLocState;
	private List<CustAddrOperDTO> custAddrList;
	private List<ContactDTO> contactList;
	private List<CustSpecialPriceDTO> custSpecialPriceList;
	private Integer pointsHistory;
	private Integer pointsAvailable;
	private Integer pointsUsed;
	private List<Integer> delCustPubIdList;
	private List<CustomerPublications> custPubList;
	private List<CustomerGrants> custGrantsList;
	private List<Integer> delCustGrantsIdList;
	private List<Integer> delCustIntIdList;
	private List<Integer> delAddrIdList;
	private List<Integer> delContactsIdList;
	private List<Integer> delSpecialPriceIdList;
	private List<CustomerCase> custCaseList;
	private List<CustomerInterest> custInterestList;
	private CustomerPersonalInfo personal;
	private CustInfoStat custInfoStat;
	private CustomerPoints customerPoints;
	private String specialPriceFlag;
	private String priceCatalogId;
	private BigDecimal specialDiscount;
	private Date discEffFrom;
	private Date discEffTo;
	private String priorityLvl;
	private String greenAccFlag;
	private Integer projectSupport;
	private List<CustNoteDTO> custNoteList;
	private Date lastActivity;
	private String ccpayFlag;
	private String custType;
	private String passwd;
	private String orgName;
	
	private String billAccountCode;
	private int crRatingIds;
	
	
	public int getCrRatingIds() {
		return crRatingIds;
	}

	public void setCrRatingIds(int crRatingIds) {
		this.crRatingIds = crRatingIds;
	}

	private BigDecimal 	creditLimit;
	
	
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getBillAccountCode() {
		return billAccountCode;
	}

	public void setBillAccountCode(String billAccountCode) {
		this.billAccountCode = billAccountCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSessCustNo() {
		return sessCustNo;
	}

	public void setSessCustNo(String sessCustNo) {
		this.sessCustNo = sessCustNo;
	}

	public String getOrgLocState() {
		return orgLocState;
	}

	public void setOrgLocState(String orgLocState) {
		this.orgLocState = orgLocState;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the custNo
	 */
	public Integer getCustNo() {
		return custNo;
	}

	/**
	 * @param custNo
	 *            the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
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
	 * @return the custRoleId
	 */
	public Integer getCustRoleId() {
		return custRoleId;
	}

	/**
	 * @param custRoleId
	 *            the custRoleId to set
	 */
	public void setCustRoleId(Integer custRoleId) {
		this.custRoleId = custRoleId;
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
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
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
	 * @return the taxId
	 */
	public String getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId
	 *            the taxId to set
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	/**
	 * @return the altTaxId
	 */
	public String getAltTaxId() {
		return altTaxId;
	}

	/**
	 * @param altTaxId
	 *            the altTaxId to set
	 */
	public void setAltTaxId(String altTaxId) {
		this.altTaxId = altTaxId;
	}

	/**
	 * @return the taxExemptFlag
	 */
	public String getTaxExemptFlag() {
		return taxExemptFlag;
	}

	/**
	 * @param taxExemptFlag
	 *            the taxExemptFlag to set
	 */
	public void setTaxExemptFlag(String taxExemptFlag) {
		this.taxExemptFlag = taxExemptFlag;
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
	 * @return the prefContactType
	 */
	public String getPrefContactType() {
		return prefContactType;
	}

	/**
	 * @param prefContactType
	 *            the prefContactType to set
	 */
	public void setPrefContactType(String prefContactType) {
		this.prefContactType = prefContactType;
	}

	/**
	 * @return the prefEmailFmt
	 */
	public String getPrefEmailFmt() {
		return prefEmailFmt;
	}

	/**
	 * @param prefEmailFmt
	 *            the prefEmailFmt to set
	 */
	public void setPrefEmailFmt(String prefEmailFmt) {
		this.prefEmailFmt = prefEmailFmt;
	}

	/**
	 * @return the prefPaymentTerm
	 */
	public Integer getPrefPaymentTerm() {
		return prefPaymentTerm;
	}

	/**
	 * @param prefPaymentTerm
	 *            the prefPaymentTerm to set
	 */
	public void setPrefPaymentTerm(Integer prefPaymentTerm) {
		this.prefPaymentTerm = prefPaymentTerm;
	}

	public String getPrefPaymentMthd() {
		return prefPaymentMthd;
	}

	public void setPrefPaymentMthd(String prefPaymentMthd) {
		this.prefPaymentMthd = prefPaymentMthd;
	}

	public Integer getPrefShipMthd() {
		return prefShipMthd;
	}

	public void setPrefShipMthd(Integer prefShipMthd) {
		this.prefShipMthd = prefShipMthd;
	}

	/**
	 * @return the prefShipFromLoc
	 */
	public String getPrefShipFromLoc() {
		return prefShipFromLoc;
	}

	/**
	 * @param prefShipFromLoc
	 *            the prefShipFromLoc to set
	 */
	public void setPrefShipFromLoc(String prefShipFromLoc) {
		this.prefShipFromLoc = prefShipFromLoc;
	}

	/**
	 * @return the overShipFlag
	 */
	public String getOverShipFlag() {
		return overShipFlag;
	}

	/**
	 * @param overShipFlag
	 *            the overShipFlag to set
	 */
	public void setOverShipFlag(String overShipFlag) {
		this.overShipFlag = overShipFlag;
	}

	/**
	 * @return the partialShipFlag
	 */
	public String getPartialShipFlag() {
		return partialShipFlag;
	}

	/**
	 * @param partialShipFlag
	 *            the partialShipFlag to set
	 */
	public void setPartialShipFlag(String partialShipFlag) {
		this.partialShipFlag = partialShipFlag;
	}

	/**
	 * @return the substitutionFlag
	 */
	public String getSubstitutionFlag() {
		return substitutionFlag;
	}

	/**
	 * @param substitutionFlag
	 *            the substitutionFlag to set
	 */
	public void setSubstitutionFlag(String substitutionFlag) {
		this.substitutionFlag = substitutionFlag;
	}

	/**
	 * @return the discountFlag
	 */
	public String getDiscountFlag() {
		return discountFlag;
	}

	/**
	 * @param discountFlag
	 *            the discountFlag to set
	 */
	public void setDiscountFlag(String discountFlag) {
		this.discountFlag = discountFlag;
	}

	/**
	 * @return the crRatingId
	 */
	public Integer getCrRatingId() {
		return crRatingId;
	}

	/**
	 * @param crRatingId
	 *            the crRatingId to set
	 */
	public void setCrRatingId(Integer crRatingId) {
		this.crRatingId = crRatingId;
	}

	/**
	 * @return the rfmRatingId
	 */
	public Integer getRfmRatingId() {
		return rfmRatingId;
	}

	/**
	 * @param rfmRatingId
	 *            the rfmRatingId to set
	 */
	public void setRfmRatingId(Integer rfmRatingId) {
		this.rfmRatingId = rfmRatingId;
	}

	/**
	 * @return the rateGroup
	 */
	public Integer getRateGroup() {
		return rateGroup;
	}

	/**
	 * @param rateGroup
	 *            the rateGroup to set
	 */
	public void setRateGroup(Integer rateGroup) {
		this.rateGroup = rateGroup;
	}

	/**
	 * @return the transCurrency
	 */
	public String getTransCurrency() {
		return transCurrency;
	}

	/**
	 * @param transCurrency
	 *            the transCurrency to set
	 */
	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}

	/**
	 * @return the paymentCurrency
	 */
	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	/**
	 * @param paymentCurrency
	 *            the paymentCurrency to set
	 */
	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getPriorityLvl() {
		return priorityLvl;
	}

	public void setPriorityLvl(String priorityLvl) {
		this.priorityLvl = priorityLvl;
	}

	public String getGreenAccFlag() {
		return greenAccFlag;
	}

	public void setGreenAccFlag(String greenAccFlag) {
		this.greenAccFlag = greenAccFlag;
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
	 * @return the custAddrList
	 */
	public List<CustAddrOperDTO> getCustAddrList() {
		return custAddrList;
	}

	/**
	 * @param custAddrList
	 *            the custAddrList to set
	 */
	public void setCustAddrList(List<CustAddrOperDTO> custAddrList) {
		this.custAddrList = custAddrList;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the contactList
	 */
	public List<ContactDTO> getContactList() {
		return contactList;
	}

	/**
	 * @param contactList
	 *            the contactList to set
	 */
	public void setContactList(List<ContactDTO> contactList) {
		this.contactList = contactList;
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
	 * @return the custSpecialPriceList
	 */
	public List<CustSpecialPriceDTO> getCustSpecialPriceList() {
		return custSpecialPriceList;
	}

	/**
	 * @param custSpecialPriceList
	 *            the custSpecialPriceList to set
	 */
	public void setCustSpecialPriceList(
			List<CustSpecialPriceDTO> custSpecialPriceList) {
		this.custSpecialPriceList = custSpecialPriceList;
	}

	/**
	 * @return the acknlgReqFlag
	 */
	public String getAcknlgReqFlag() {
		return acknlgReqFlag;
	}

	/**
	 * @param acknlgReqFlag
	 *            the acknlgReqFlag to set
	 */
	public void setAcknlgReqFlag(String acknlgReqFlag) {
		this.acknlgReqFlag = acknlgReqFlag;
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
	 * @return the pointsFlag
	 */
	public String getPointsFlag() {
		return pointsFlag;
	}

	/**
	 * @param pointsFlag
	 *            the pointsFlag to set
	 */
	public void setPointsFlag(String pointsFlag) {
		this.pointsFlag = pointsFlag;
	}

	/**
	 * @return the priceCatalogId
	 */
	public String getPriceCatalogId() {
		return priceCatalogId;
	}

	/**
	 * @param priceCatalogId
	 *            the priceCatalogId to set
	 */
	public void setPriceCatalogId(String priceCatalogId) {
		this.priceCatalogId = priceCatalogId;
	}

	/**
	 * @return the pointsHistory
	 */
	public Integer getPointsHistory() {
		return pointsHistory;
	}

	/**
	 * @param pointsHistory
	 *            the pointsHistory to set
	 */
	public void setPointsHistory(Integer pointsHistory) {
		this.pointsHistory = pointsHistory;
	}

	/**
	 * @return the pointsAvailable
	 */
	public Integer getPointsAvailable() {
		return pointsAvailable;
	}

	/**
	 * @param pointsAvailable
	 *            the pointsAvailable to set
	 */
	public void setPointsAvailable(Integer pointsAvailable) {
		this.pointsAvailable = pointsAvailable;
	}

	/**
	 * @return the pointsUsed
	 */
	public Integer getPointsUsed() {
		return pointsUsed;
	}

	/**
	 * @param pointsUsed
	 *            the pointsUsed to set
	 */
	public void setPointsUsed(Integer pointsUsed) {
		this.pointsUsed = pointsUsed;
	}

	/**
	 * @return the specialDiscount
	 */
	public BigDecimal getSpecialDiscount() {
		return specialDiscount;
	}

	/**
	 * @param specialDiscount
	 *            the specialDiscount to set
	 */
	public void setSpecialDiscount(BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * @return the delCustPubIdList
	 */
	public List<Integer> getDelCustPubIdList() {
		return delCustPubIdList;
	}

	/**
	 * @param delCustPubIdList
	 *            the delCustPubIdList to set
	 */
	public void setDelCustPubIdList(List<Integer> delCustPubIdList) {
		this.delCustPubIdList = delCustPubIdList;
	}

	/**
	 * @return the custPubList
	 */
	public List<CustomerPublications> getCustPubList() {
		return custPubList;
	}

	/**
	 * @param custPubList
	 *            the custPubList to set
	 */
	public void setCustPubList(List<CustomerPublications> custPubList) {
		this.custPubList = custPubList;
	}

	/**
	 * @return the custGrantsList
	 */
	public List<CustomerGrants> getCustGrantsList() {
		return custGrantsList;
	}

	/**
	 * @param custGrantsList
	 *            the custGrantsList to set
	 */
	public void setCustGrantsList(List<CustomerGrants> custGrantsList) {
		this.custGrantsList = custGrantsList;
	}

	/**
	 * @return the delCustGrantsIdList
	 */
	public List<Integer> getDelCustGrantsIdList() {
		return delCustGrantsIdList;
	}

	/**
	 * @param delCustGrantsIdList
	 *            the delCustGrantsIdList to set
	 */
	public void setDelCustGrantsIdList(List<Integer> delCustGrantsIdList) {
		this.delCustGrantsIdList = delCustGrantsIdList;
	}

	/**
	 * @return the custCaseList
	 */
	public List<CustomerCase> getCustCaseList() {
		return custCaseList;
	}

	/**
	 * @param custCaseList
	 *            the custCaseList to set
	 */
	public void setCustCaseList(List<CustomerCase> custCaseList) {
		this.custCaseList = custCaseList;
	}

	/**
	 * @return the personal
	 */
	public CustomerPersonalInfo getPersonal() {
		return personal;
	}

	/**
	 * @param personal
	 *            the personal to set
	 */
	public void setPersonal(CustomerPersonalInfo personal) {
		this.personal = personal;
	}

	/**
	 * @return the custInfoStat
	 */
	public CustInfoStat getCustInfoStat() {
		return custInfoStat;
	}

	/**
	 * @param custInfoStat
	 *            the custInfoStat to set
	 */
	public void setCustInfoStat(CustInfoStat custInfoStat) {
		this.custInfoStat = custInfoStat;
	}

	/**
	 * @return the customerPoints
	 */
	public CustomerPoints getCustomerPoints() {
		return customerPoints;
	}

	/**
	 * @param customerPoints
	 *            the customerPoints to set
	 */
	public void setCustomerPoints(CustomerPoints customerPoints) {
		this.customerPoints = customerPoints;
	}

	/**
	 * @return the custInterestList
	 */
	public List<CustomerInterest> getCustInterestList() {
		return custInterestList;
	}

	/**
	 * @param custInterestList
	 *            the custInterestList to set
	 */
	public void setCustInterestList(List<CustomerInterest> custInterestList) {
		this.custInterestList = custInterestList;
	}

	/**
	 * @return the delCustIntIdList
	 */
	public List<Integer> getDelCustIntIdList() {
		return delCustIntIdList;
	}

	/**
	 * @param delCustIntIdList
	 *            the delCustIntIdList to set
	 */
	public void setDelCustIntIdList(List<Integer> delCustIntIdList) {
		this.delCustIntIdList = delCustIntIdList;
	}

	/**
	 * @return the specialPriceFlag
	 */
	public String getSpecialPriceFlag() {
		return specialPriceFlag;
	}

	/**
	 * @param specialPriceFlag
	 *            the specialPriceFlag to set
	 */
	public void setSpecialPriceFlag(String specialPriceFlag) {
		this.specialPriceFlag = specialPriceFlag;
	}

	/**
	 * @return the discEffFrom
	 */
	public Date getDiscEffFrom() {
		return discEffFrom;
	}

	/**
	 * @param discEffFrom
	 *            the discEffFrom to set
	 */
	public void setDiscEffFrom(Date discEffFrom) {
		this.discEffFrom = discEffFrom;
	}

	/**
	 * @return the discEffTo
	 */
	public Date getDiscEffTo() {
		return discEffTo;
	}

	/**
	 * @param discEffTo
	 *            the discEffTo to set
	 */
	public void setDiscEffTo(Date discEffTo) {
		this.discEffTo = discEffTo;
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
	 * @return the delContactsIdList
	 */
	public List<Integer> getDelContactsIdList() {
		return delContactsIdList;
	}

	/**
	 * @param delContactsIdList
	 *            the delContactsIdList to set
	 */
	public void setDelContactsIdList(List<Integer> delContactsIdList) {
		this.delContactsIdList = delContactsIdList;
	}

	/**
	 * @return the delSpecialPriceIdList
	 */
	public List<Integer> getDelSpecialPriceIdList() {
		return delSpecialPriceIdList;
	}

	/**
	 * @param delSpecialPriceIdList
	 *            the delSpecialPriceIdList to set
	 */
	public void setDelSpecialPriceIdList(List<Integer> delSpecialPriceIdList) {
		this.delSpecialPriceIdList = delSpecialPriceIdList;
	}

	/**
	 * @return the projectSupport
	 */
	public Integer getProjectSupport() {
		return projectSupport;
	}

	/**
	 * @param projectSupport
	 *            the projectSupport to set
	 */
	public void setProjectSupport(Integer projectSupport) {
		this.projectSupport = projectSupport;
	}

	/**
	 * @return the custNoteList
	 */
	public List<CustNoteDTO> getCustNoteList() {
		return custNoteList;
	}

	/**
	 * @param custNoteList
	 *            the custNoteList to set
	 */
	public void setCustNoteList(List<CustNoteDTO> custNoteList) {
		this.custNoteList = custNoteList;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public Integer getPurchasingCpbl() {
		return purchasingCpbl;
	}

	public void setPurchasingCpbl(Integer purchasingCpbl) {
		this.purchasingCpbl = purchasingCpbl;
	}

	public Date getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}

	public String getCcpayFlag() {
		return ccpayFlag;
	}

	public void setCcpayFlag(String ccpayFlag) {
		this.ccpayFlag = ccpayFlag;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRfmRatingCd() {
		return rfmRatingCd;
	}

	public void setRfmRatingCd(String rfmRatingCd) {
		this.rfmRatingCd = rfmRatingCd;
	}

}
