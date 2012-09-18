package com.genscript.gsscm.accounting.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.genscript.gsscm.accounting.entity.SelectBean;

public class ArInvoicePaymentDTO {
	int transactionId;
	String transactionNo;
	int custNo;
	String transactionType;
	Date transactionDate;
	String paymentType;
	String currency;
	String amount;
	String transactionFee;
	String balance;
	String tenderType;
	String accountName;
	String accountNo;
	String routingNo;
	String chkNo;
	String ccType;
	String ccCardHolder;
	String ccCvc;
	String ccExpiration;
	String description;
	String status;
	Date creationDate;
	int createdBy; 
	String createdByName;
	Date modifyDate;
	int modifiedBy;
	int invoiceId;
	String invoiceNo;
	String orderNo;
	String applyAmount;
	String statusUpdReason;
	String transactionIds; //批量删除
	String transactionTitle; //记录页面标题
	String firstName;  //客户名称
	String lastName; //客户名称
	String symbol;//汇率符号
	
	List<SelectBean> statusList;
	List<SelectBean> transactionTypeList;
	List<SelectBean> tenderTypeList;
	List<SelectBean> paymentTypeList;
	List<SelectBean> cardList;
	List<SelectBean> monthList;
	List<SelectBean> yearList;
	List<SelectBean> currencyList;
	List<Map> invoiceList;
	int userId;
	String userName;
	String sysDate;
	String month;
	String year;
	String readonly;


	public void setYear(String year) {
		this.year = year;
	}

	public String getTransactionTitle() {
		return transactionTitle;
	}

	public void setTransactionTitle(String transactionTitle) {
		this.transactionTitle = transactionTitle;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(String transactionFee) {
		this.transactionFee = transactionFee;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public String getChkNo() {
		return chkNo;
	}

	public void setChkNo(String chkNo) {
		this.chkNo = chkNo;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCcCardHolder() {
		return ccCardHolder;
	}

	public void setCcCardHolder(String ccCardHolder) {
		this.ccCardHolder = ccCardHolder;
	}

	public String getCcCvc() {
		return ccCvc;
	}

	public void setCcCvc(String ccCvc) {
		this.ccCvc = ccCvc;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}
	
	public String getStatusUpdReason() {
		return statusUpdReason;
	}

	public void setStatusUpdReason(String statusUpdReason) {
		this.statusUpdReason = statusUpdReason;
	}
	
	public String getTransactionIds() {
		return transactionIds;
	}

	public void setTransactionIds(String transactionIds) {
		this.transactionIds = transactionIds;
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
	
	public List<SelectBean> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<SelectBean> statusList) {
		this.statusList = statusList;
	}

	public List<SelectBean> getTransactionTypeList() {
		return transactionTypeList;
	}

	public void setTransactionTypeList(List<SelectBean> transactionTypeList) {
		this.transactionTypeList = transactionTypeList;
	}

	public List<SelectBean> getTenderTypeList() {
		return tenderTypeList;
	}

	public void setTenderTypeList(List<SelectBean> tenderTypeList) {
		this.tenderTypeList = tenderTypeList;
	}

	public List<SelectBean> getPaymentTypeList() {
		return paymentTypeList;
	}

	public void setPaymentTypeList(List<SelectBean> paymentTypeList) {
		this.paymentTypeList = paymentTypeList;
	}

	public List<SelectBean> getCardList() {
		return cardList;
	}

	public void setCardList(List<SelectBean> cardList) {
		this.cardList = cardList;
	}

	public List<SelectBean> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<SelectBean> monthList) {
		this.monthList = monthList;
	}

	public List<SelectBean> getYearList() {
		return yearList;
	}

	public void setYearList(List<SelectBean> yearList) {
		this.yearList = yearList;
	}

	public List<SelectBean> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<SelectBean> currencyList) {
		this.currencyList = currencyList;
	}

	public List<Map> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Map> invoiceList) {
		this.invoiceList = invoiceList;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}
	
	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
