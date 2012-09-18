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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ContactGrants.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "contact_grants")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContactGrants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2404657685571784214L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grant_id")
	private Integer grantId;
	private Integer contactNo;
	private String category;
	private String fundingIc;
	private String projectNo;
	private String subProjectNo;
	private String projectName;
	@Column(name = "abstract")
	private String abst;
	private String pi;
	private String email;
	private String organization;
	private String state;
	private String country;
	private Double amount;
	private Date issueDate;
	private Date exprDate;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	public Integer getGrantId() {
		return grantId;
	}
	public void setGrantId(Integer grantId) {
		this.grantId = grantId;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFundingIc() {
		return fundingIc;
	}
	public void setFundingIc(String fundingIc) {
		this.fundingIc = fundingIc;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getSubProjectNo() {
		return subProjectNo;
	}
	public void setSubProjectNo(String subProjectNo) {
		this.subProjectNo = subProjectNo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getAbst() {
		return abst;
	}
	public void setAbst(String abst) {
		this.abst = abst;
	}
	public String getPi() {
		return pi;
	}
	public void setPi(String pi) {
		this.pi = pi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
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
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(Integer contactNo) {
		this.contactNo = contactNo;
	}
}
