package com.genscript.gsscm.order.entity;

import java.util.Date;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * EXCH RATE BY PERIOD.
 * 
 * 
 * @author Golf
 */

//@Entity
//@Table(name = "exch_rate_by_period", catalog="order")
public class ExchRateByPeriod extends BaseEntity {

	private Integer rateGroupId;
	private String fromCurrency;
	private String toCurrency;
	private Double rate;
	private Date fiscalYear;
	private Date periodNo;
	private String comment;
	public Integer getRateGroupId() {
		return rateGroupId;
	}
	public void setRateGroupId(Integer rateGroupId) {
		this.rateGroupId = rateGroupId;
	}
	public String getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	public String getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Date getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(Date fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public Date getPeriodNo() {
		return periodNo;
	}
	public void setPeriodNo(Date periodNo) {
		this.periodNo = periodNo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
