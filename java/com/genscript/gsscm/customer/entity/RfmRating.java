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
 * RFM_RATING.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "rfm_rating")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RfmRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 391021909487525506L;
	private Integer rfmRatingId;
	private String rfmRatingCd;
	private String description;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rfm_rating_id")
	public Integer getRfmRatingId() {
		return rfmRatingId;
	}
	public void setRfmRatingId(Integer rfmRatingId) {
		this.rfmRatingId = rfmRatingId;
	}
	public String getRfmRatingCd() {
		return rfmRatingCd;
	}
	public void setRfmRatingCd(String rfmRatingCd) {
		this.rfmRatingCd = rfmRatingCd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public RfmRating(){}
	public RfmRating(String rfmRatingCd, String description, Integer createdBy,
			Integer modifiedBy) {
		super();
		this.rfmRatingCd = rfmRatingCd;
		this.description = description;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
