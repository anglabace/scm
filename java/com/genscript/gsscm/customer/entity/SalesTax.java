package com.genscript.gsscm.customer.entity;

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
 * SALES_TAX.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "sales_tax")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesTax implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8479856278942966838L;
	private Integer taxId;
	private String taxType;
	private Double taxRate;
	private Date effFrom;
	private Date effTo;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tax_id")
	public Integer getTaxId() {
		return taxId;
	}
	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
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
	public SalesTax(){}
	public SalesTax(String taxType, Double taxRate, Date effFrom, Date effTo,
			Integer createdBy, Integer modifiedBy) {
		super();
		this.taxType = taxType;
		this.taxRate = taxRate;
		this.effFrom = effFrom;
		this.effTo = effTo;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
