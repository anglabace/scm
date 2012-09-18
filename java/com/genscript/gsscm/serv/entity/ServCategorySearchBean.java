package com.genscript.gsscm.serv.entity;

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
 * CATEGORY SEARCH VIEW.
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "v_service_categories", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServCategorySearchBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -550564878290578859L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer categoryId;
	private String categoryNo;
    private String catalogId;
    private String name;
    private String description;
    private String assetGroup;
    private String status;
    private Date creationDate;
    private Date modifyDate;
    private String previousCatName;
    private String parentCatName;
    private String createdName;
    private String modifiedName;
    private Integer previousCatId;
    private Integer parentCatId;
    
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
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
	public String getAssetGroup() {
		return assetGroup;
	}
	public void setAssetGroup(String assetGroup) {
		this.assetGroup = assetGroup;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getPreviousCatName() {
		return previousCatName;
	}
	public void setPreviousCatName(String previousCatName) {
		this.previousCatName = previousCatName;
	}
	public String getParentCatName() {
		return parentCatName;
	}
	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public String getModifiedName() {
		return modifiedName;
	}
	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}
	public Integer getPreviousCatId() {
		return previousCatId;
	}
	public void setPreviousCatId(Integer previousCatId) {
		this.previousCatId = previousCatId;
	}
	public Integer getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(Integer parentCatId) {
		this.parentCatId = parentCatId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
