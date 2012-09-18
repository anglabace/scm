package com.genscript.gsscm.customer.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.SessionBaseDTO;

public class CustGrantsDTO extends SessionBaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3527764406426699022L;
	private Integer grantId;
	private Integer custNo;
	private String category;
	private String fundingIc;
	private String projectNo;
	private String subProjectNo;
	private String projectName;
	private String abst;
	private String pi;
	private String email;
	private String organization;
	private String state;
	private String country;
	private Double amount;
	private Date issueDate;
	private Date exprDate;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	private String grantIdStr;
	public Integer getGrantId() {
		return grantId;
	}
	public void setGrantId(Integer grantId) {
		this.grantId = grantId;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
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
	public String getGrantIdStr() {
		return grantIdStr;
	}
	public void setGrantIdStr(String grantIdStr) {
		this.grantIdStr = grantIdStr;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
