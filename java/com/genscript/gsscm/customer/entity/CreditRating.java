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
 * CREDIT_RATING.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "credit_rating")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CreditRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7173907824697709646L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cr_rating_id")
	private Integer crRatingId;
	private String crRatingCode;
	private String description;
	private Integer crRating;
	private Date crRatingDate;
	private Double crLimit;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	
	
	public Integer getCrRatingId() {
		return crRatingId;
	}
	public void setCrRatingId(Integer crRatingId) {
		this.crRatingId = crRatingId;
	}
	public String getCrRatingCode() {
		return crRatingCode;
	}
	public void setCrRatingCode(String crRatingCode) {
		this.crRatingCode = crRatingCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCrRating() {
		return crRating;
	}
	public void setCrRating(Integer crRating) {
		this.crRating = crRating;
	}
	public Date getCrRatingDate() {
		return crRatingDate;
	}
	public void setCrRatingDate(Date crRatingDate) {
		this.crRatingDate = crRatingDate;
	}
	public Double getCrLimit() {
		return crLimit;
	}
	public void setCrLimit(Double crLimit) {
		this.crLimit = crLimit;
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
	public CreditRating(){}
	public CreditRating(String crRatingCode, String description,
			Integer crRating, Date crRatingDate, Double crLimit,
			Integer createdBy, Integer modifiedBy) {
		super();
		this.crRatingCode = crRatingCode;
		this.description = description;
		this.crRating = crRating;
		this.crRatingDate = crRatingDate;
		this.crLimit = crLimit;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
