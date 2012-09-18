package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ORGANIZATION.
 *
 * @author Golf
 */
@Entity
@Table(name = "organization")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Organization implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4990242775284853987L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orgId;
    private Integer parentOrgId;
    private Integer orgGroupId;
    @Column(name = "org_category_id")
    private Integer categoryId;
    @Column(name = "org_type_id")
    private Integer typeId;
    private String name;
    private String web;
    private String description;
    private String langCode;
    private String supervisor;
    @Column(name = "tax_ID")
    private String taxID;
    private String taxExemptFlag;
    @Column(name = "D_U_N_S")
    private String duns;
    private String referenceNo;
    private Double noOfEmployees;
    private String creditStatus;
    private Double creditLimit;
    private Double balance;
    private String rating;
    private Double annualRevenueExpected;
    private Double annualActualRevenue;
    private Double saleVolume;
    private Double percentageOfSales;
    private Date firstSalesDate;
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
    private Integer salesTerritory;
    private Integer salesGroup;


    @Column(insertable = true, updatable = false)
    private Date creationDate;
    @Column(insertable = true, updatable = false)
    private Integer createdBy;
    private Date modifyDate;
    private Integer modifiedBy;
    @Transient
    private String langName;
    @Transient
    private Integer projectSupport;
    @Transient
    private Integer techSupport;
    @Transient
    private Integer salesContact;
    @Transient
    private String modifiedName;

    @Transient
    private Organization parentOrganization;
    @Transient
    private OrganizationType organizationType;
    @Transient
 /* @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")*/
    private OrganizationGroup organizationGroup;
    @Transient
    private List<Integer> subOrgIdList;
    @Transient
    private List<Integer> delSubOrgIdList;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public Organization() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the typeId
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return the web
     */
    public String getWeb() {
        return web;
    }

    /**
     * @param web the web to set
     */
    public void setWeb(String web) {
        this.web = web;
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

    public Integer getSalesTerritory() {
        return salesTerritory;
    }

    public void setSalesTerritory(Integer salesTerritory) {
        this.salesTerritory = salesTerritory;
    }

    public Integer getSalesGroup() {
        return salesGroup;
    }

    public void setSalesGroup(Integer salesGroup) {
        this.salesGroup = salesGroup;
    }

    public Integer getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(Integer salesContact) {
        this.salesContact = salesContact;
    }

    public Integer getTechSupport() {
        return techSupport;
    }

    public void setTechSupport(Integer techSupport) {
        this.techSupport = techSupport;
    }

    public Integer getProjectSupport() {
        return projectSupport;
    }

    public void setProjectSupport(Integer projectSupport) {
        this.projectSupport = projectSupport;
    }

    public Integer getOrgGroupId() {
        return orgGroupId;
    }

    public void setOrgGroupId(Integer orgGroupId) {
        this.orgGroupId = orgGroupId;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getDuns() {
        return duns;
    }

    public void setDuns(String duns) {
        this.duns = duns;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Double getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(Double noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Double getAnnualRevenueExpected() {
        return annualRevenueExpected;
    }

    public void setAnnualRevenueExpected(Double annualRevenueExpected) {
        this.annualRevenueExpected = annualRevenueExpected;
    }

    public Double getAnnualActualRevenue() {
        return annualActualRevenue;
    }

    public void setAnnualActualRevenue(Double annualActualRevenue) {
        this.annualActualRevenue = annualActualRevenue;
    }

    public Double getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(Double saleVolume) {
        this.saleVolume = saleVolume;
    }

    public Double getPercentageOfSales() {
        return percentageOfSales;
    }

    public void setPercentageOfSales(Double percentageOfSales) {
        this.percentageOfSales = percentageOfSales;
    }

    public Date getFirstSalesDate() {
        return firstSalesDate;
    }

    public void setFirstSalesDate(Date firstSalesDate) {
        this.firstSalesDate = firstSalesDate;
    }

    public Organization getParentOrganization() {
        return parentOrganization;
    }

    public void setParentOrganization(Organization parentOrganization) {
        this.parentOrganization = parentOrganization;
    }

    public Integer getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Integer parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(OrganizationType organizationType) {
        this.organizationType = organizationType;
    }

    public OrganizationGroup getOrganizationGroup() {
        return organizationGroup;
    }

    public void setOrganizationGroup(OrganizationGroup organizationGroup) {
        this.organizationGroup = organizationGroup;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getTaxExemptFlag() {
        return taxExemptFlag;
    }

    public void setTaxExemptFlag(String taxExemptFlag) {
        this.taxExemptFlag = taxExemptFlag;
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

    public List<Integer> getSubOrgIdList() {
        return subOrgIdList;
    }

    public void setSubOrgIdList(List<Integer> subOrgIdList) {
        this.subOrgIdList = subOrgIdList;
    }

    public List<Integer> getDelSubOrgIdList() {
        return delSubOrgIdList;
    }

    public void setDelSubOrgIdList(List<Integer> delSubOrgIdList) {
        this.delSubOrgIdList = delSubOrgIdList;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }


}
