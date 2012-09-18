package com.genscript.gsscm.tools.dto;

import java.util.List;

import com.genscript.core.orm.Page;

public class OrderFollowupDTO {

	private String confirmationDate;
	private String dataFrom;
	private String dataTo;
	private String orderNo;
	private String orderStatus;
	private String priority;
	private String typeName;
	private String custNo;
	private String email;
	private String orgName;
	private String country;
	private String salesManager;
	private String accountManager;
	private String projectManager;
	private String followupStatus;
	private String overdueNumOfitems;
	private String mail;
	private String custName;
	private String organization;
	private String location;
	private String tam;
	private String salesManage;
	private String numofItems;
	private String deliveryDate;
	private String guaranteedDeliveryDate;
	private String productionTargetDate;
	private String orderTotal;
	private String productServiceType;
	private String followupDate;
	private String followupMessage;
	private String po; 
	
	
	public String getOverdueNumOfitems() {
		return overdueNumOfitems;
	}

	public void setOverdueNumOfitems(String overdueNumOfitems) {
		this.overdueNumOfitems = overdueNumOfitems;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTam() {
		return tam;
	}

	public void setTam(String tam) {
		this.tam = tam;
	}

	public String getSalesManage() {
		return salesManage;
	}

	public void setSalesManage(String salesManage) {
		this.salesManage = salesManage;
	}

	public String getNumofItems() {
		return numofItems;
	}

	public void setNumofItems(String numofItems) {
		this.numofItems = numofItems;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getGuaranteedDeliveryDate() {
		return guaranteedDeliveryDate;
	}

	public void setGuaranteedDeliveryDate(String guaranteedDeliveryDate) {
		this.guaranteedDeliveryDate = guaranteedDeliveryDate;
	}

	public String getProductionTargetDate() {
		return productionTargetDate;
	}

	public void setProductionTargetDate(String productionTargetDate) {
		this.productionTargetDate = productionTargetDate;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getProductServiceType() {
		return productServiceType;
	}

	public void setProductServiceType(String productServiceType) {
		this.productServiceType = productServiceType;
	}

	public String getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(String followupDate) {
		this.followupDate = followupDate;
	}

	public String getFollowupMessage() {
		return followupMessage;
	}

	public void setFollowupMessage(String followupMessage) {
		this.followupMessage = followupMessage;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	private List reportData;

	public List getReportData() {
		return reportData;
	}

	public void setReportData(List reportData) {
		this.reportData = reportData;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	private List<String> column;
	private List<String> columnName;
	private String customer;
	private String sortBy;

	private String reportPicName;

	public String getReportPicName() {
		return reportPicName;
	}

	public void setReportPicName(String reportPicName) {
		this.reportPicName = reportPicName;
	}

	public String getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getDataTo() {
		return dataTo;
	}

	public void setDataTo(String dataTo) {
		this.dataTo = dataTo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSalesManager() {
		return salesManager;
	}

	public void setSalesManager(String salesManager) {
		this.salesManager = salesManager;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getFollowupStatus() {
		return followupStatus;
	}

	public void setFollowupStatus(String followupStatus) {
		this.followupStatus = followupStatus;
	}

	public List<String> getColumn() {
		return column;
	}

	public void setColumn(List<String> column) {
		this.column = column;
	}

	public List<String> getColumnName() {
		return columnName;
	}

	public void setColumnName(List<String> columnName) {
		this.columnName = columnName;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy1(String sortBy) {
		this.sortBy = sortBy;
	}

}
