package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * CUSTOMER_INFO_STATISTICS.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "customer_info_statistics")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustInfoStat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -863428365624091395L;
	@Id
	private Integer custNo;
	private Double currentBalance;
	private Double deferedBalance;
	private Double interestCharge;
	private Double grossSpent;
	private Double grossProfit;
	private Integer avgPayDay;
	private String paymentPerf;
	private String badCreditRisk;
	private Integer lastQuote;
	private Integer quoteCount;
	private Integer lastOrder;
	private Integer orderCount;
	private Integer contactCount;
	private Double unappliedCredit;
	private Date lastActivity;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public Double getDeferedBalance() {
		return deferedBalance;
	}
	public void setDeferedBalance(Double deferedBalance) {
		this.deferedBalance = deferedBalance;
	}
	public Double getInterestCharge() {
		return interestCharge;
	}
	public void setInterestCharge(Double interestCharge) {
		this.interestCharge = interestCharge;
	}
	public Date getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}
	public Double getGrossSpent() {
		return grossSpent;
	}
	public void setGrossSpent(Double grossSpent) {
		this.grossSpent = grossSpent;
	}
	public Double getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}
	public Integer getAvgPayDay() {
		return avgPayDay;
	}
	public void setAvgPayDay(Integer avgPayDay) {
		this.avgPayDay = avgPayDay;
	}
	public String getPaymentPerf() {
		return paymentPerf;
	}
	public void setPaymentPerf(String paymentPerf) {
		this.paymentPerf = paymentPerf;
	}
	public Integer getLastQuote() {
		return lastQuote;
	}
	public void setLastQuote(Integer lastQuote) {
		this.lastQuote = lastQuote;
	}
	public Integer getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(Integer quoteCount) {
		this.quoteCount = quoteCount;
	}
	public Integer getLastOrder() {
		return lastOrder;
	}
	public void setLastOrder(Integer lastOrder) {
		this.lastOrder = lastOrder;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Integer getContactCount() {
		return contactCount;
	}
	public void setContactCount(Integer contactCount) {
		this.contactCount = contactCount;
	}
	public Double getUnappliedCredit() {
		return unappliedCredit;
	}
	public void setUnappliedCredit(Double unappliedCredit) {
		this.unappliedCredit = unappliedCredit;
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
	 * @return the badCreditRisk
	 */
	public String getBadCreditRisk() {
		return badCreditRisk;
	}
	/**
	 * @param badCreditRisk the badCreditRisk to set
	 */
	public void setBadCreditRisk(String badCreditRisk) {
		this.badCreditRisk = badCreditRisk;
	}
}
