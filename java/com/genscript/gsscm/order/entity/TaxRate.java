package com.genscript.gsscm.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ORDER RATE.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "tax_rate", catalog = "order")
public class TaxRate extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rate_id")
	private Integer rateId;
	private String country;
	private String state;
	private Integer taxClass;
	private Double taxRate;
	private String note;
	private String status;
	
	//显示用， 非数据库属性
	@Transient
	private String modifyName;
	@Transient
	private String countryName;
	@Transient
	private String stateName;
	@Transient
	private String stateCode;
	@Transient
	private String languageCode;
	@Transient
	private String language;
	@Transient
	private String currencyCode;
	@Transient
	private String currency;
	@Transient
	private String continent;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public Integer getRateId() {
		return rateId;
	}


	public void setRateId(Integer rateId) {
		this.rateId = rateId;
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


	public Integer getTaxClass() {
		return taxClass;
	}


	public void setTaxClass(Integer taxClass) {
		this.taxClass = taxClass;
	}


	public Double getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

		
	
}
