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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * PUBLIC SEARCH ATTRIBUTE.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "search_attributes", catalog="common")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PbSearchAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -497218351087999919L;
	private Integer attributeId;
	private PbSearchType pbSearchType;
	private String name;
	private String type;
	private String description;
	private String dataSrcCode;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attribute_id")
	public Integer getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="search_type_id")
	public PbSearchType getPbSearchType() {
		return pbSearchType;
	}
	public void setPbSearchType(PbSearchType pbSearchType) {
		this.pbSearchType = pbSearchType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDataSrcCode() {
		return dataSrcCode;
	}
	public void setDataSrcCode(String dataSrcCode) {
		this.dataSrcCode = dataSrcCode;
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
