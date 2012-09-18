package com.genscript.gsscm.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class MfgOrderDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4761897717087420285L;
	@Id
	private Integer orderNo;
	private Integer custNo;
	private String priority;
	private Integer srcSoNo;
	private Integer srcPoNo;
	private Date orderDate;
	private Date exprDate;
	private String billAccCode;
	private String status;
	private Integer companyId;
	private String type;
	
	private String salesContact;
	private String symbol;
	private String companyName;
	private Double amount;
	private String erpNjSo;
	private Double subTotal;
	private Date customerConfirmDate;
	private Date vendorConfirmDate;
	private String orderType;
	private String email;
	private String firstName;
	private String lastName;
	private String organization;
	private String country;
	private String state;
	private String techSupport;
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Integer getSrcSoNo() {
		return srcSoNo;
	}
	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}
	public Integer getSrcPoNo() {
		return srcPoNo;
	}
	public void setSrcPoNo(Integer srcPoNo) {
		this.srcPoNo = srcPoNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	public String getBillAccCode() {
		return billAccCode;
	}
	public void setBillAccCode(String billAccCode) {
		this.billAccCode = billAccCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getSalesContact() {
		return salesContact;
	}
	public void setSalesContact(String salesContact) {
		this.salesContact = salesContact;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getErpNjSo() {
		return erpNjSo;
	}
	public void setErpNjSo(String erpNjSo) {
		this.erpNjSo = erpNjSo;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCustomerConfirmDate() {
		return customerConfirmDate;
	}
	public void setCustomerConfirmDate(Date customerConfirmDate) {
		this.customerConfirmDate = customerConfirmDate;
	}
	public Date getVendorConfirmDate() {
		return vendorConfirmDate;
	}
	public void setVendorConfirmDate(Date vendorConfirmDate) {
		this.vendorConfirmDate = vendorConfirmDate;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTechSupport() {
		return techSupport;
	}
	public void setTechSupport(String techSupport) {
		this.techSupport = techSupport;
	}
}
