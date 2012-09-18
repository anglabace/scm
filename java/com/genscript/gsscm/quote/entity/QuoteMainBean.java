package com.genscript.gsscm.quote.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * QUOTATION MAIN LIST VIEW.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "v_all_quotes", catalog="order")
public class QuoteMainBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8904745188634714367L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quote_no")
	private Integer quoteNo;
	private Integer custNo;
	private String status;
	private String quoteType;
	private String priority;
	private String email;
	private String organization;
	private String firstName;
	private String midName;
	private String lastName;
	private String addrLine1;
	private String addrLine2;
	private String addrLine3;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private Date quoteDate;
	private Date exprDate;
	private Double amount;
	private String salesContact;
	private String techSupport;
	private Integer orderNo;
	private String symbol;
	
	private Integer salesContactId;
	private Integer altSalesContactId;
	private Integer techSupportId;
	private Integer altTechSupportId;
	private String createdBy;
    @Transient
    private String fulStatus;

	@Column(name = "followup_date")
    private Date followUpDate;

    public Date getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(Date followUpDate) {
		this.followUpDate = followUpDate;
	}
	public Integer getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
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
	public Date getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getSalesContact() {
		return salesContact;
	}
	public void setSalesContact(String salesContact) {
		this.salesContact = salesContact;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
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
	public String getTechSupport() {
		return techSupport;
	}
	public void setTechSupport(String techSupport) {
		this.techSupport = techSupport;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Integer getSalesContactId() {
		return salesContactId;
	}
	public void setSalesContactId(Integer salesContactId) {
		this.salesContactId = salesContactId;
	}
	public Integer getAltSalesContactId() {
		return altSalesContactId;
	}
	public void setAltSalesContactId(Integer altSalesContactId) {
		this.altSalesContactId = altSalesContactId;
	}
	public Integer getTechSupportId() {
		return techSupportId;
	}
	public void setTechSupportId(Integer techSupportId) {
		this.techSupportId = techSupportId;
	}
	public Integer getAltTechSupportId() {
		return altTechSupportId;
	}
	public void setAltTechSupportId(Integer altTechSupportId) {
		this.altTechSupportId = altTechSupportId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    public String getFulStatus() {
		return fulStatus;
	}
	public void setFulStatus(String fulStatus) {
		this.fulStatus = fulStatus;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
