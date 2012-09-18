package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * RATE_GROUP.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "exch_rate_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RateGroup implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 301296860310787130L;
	private Integer groupId;
	private String name;
	private String description;
	private RateSource rateSource;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="rate_src_id")
	public RateSource getRateSource() {
		return rateSource;
	}


	public void setRateSource(RateSource rateSource) {
		this.rateSource = rateSource;
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
	public RateGroup(){}

	public RateGroup(String name, String description, RateSource rateSource,
			Integer createdBy, Integer modifiedBy) {
		super();
		this.name = name;
		this.description = description;
		this.rateSource = rateSource;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
