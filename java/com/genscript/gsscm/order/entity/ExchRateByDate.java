package com.genscript.gsscm.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * EXCH RATE BY DATE.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "exch_rate_by_date", catalog="order")
public class ExchRateByDate extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rate_id")
	private Integer rateId;
	private Integer rateGroupId;
	private String fromCurrency;
	private String toCurrency;
	private Double rate;
	private Date effFrom;
	private Date effTo;
	private String comment;
	public Integer getRateId() {
		return rateId;
	}
	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}
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
	public Date getEffFrom() {
		return effFrom;
	}
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}
	public Date getEffTo() {
		return effTo;
	}
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
