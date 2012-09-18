package com.genscript.gsscm.basedata.entity;

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

/**
 * PUBLIC SEARCH.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "search", catalog="common")
public class PbSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566350782669036358L;
	private Integer searchId;
	private PbSearchType pbSearchType;
//	private Integer searchType;
	private String name;
	private String description;
	private Integer owner;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "search_id")
	public Integer getSearchId() {
		return searchId;
	}
	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="search_type")
	public PbSearchType getPbSearchType() {
		return pbSearchType;
	}
	public void setPbSearchType(PbSearchType pbSearchType) {
		this.pbSearchType = pbSearchType;
	}
	
	public String getName() {
		return name;
	}
//	public Integer getSearchType() {
//		return searchType;
//	}
//	public void setSearchType(Integer searchType) {
//		this.searchType = searchType;
//	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
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
	
}
