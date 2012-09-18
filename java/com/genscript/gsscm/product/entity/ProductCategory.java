package com.genscript.gsscm.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * PRODUCT SEARCH VIEW.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "product_category", catalog="product")
public class ProductCategory extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7909503879716501002L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer categoryId;
    private String categoryNo;
    private Integer categoryLevel;// 1表示Business Unit, 2表示Category， 3表示 Line
    private String catalogId;
    private String name;
    private String description;
    private String assetGroup;
    private Integer parentCatId;
    private Integer previousCatId;
    private String status;
    private String businessDivision;
    private Integer marketingGroup;
    private Integer marketingSplst;
    private Integer marketingSpvs;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the categoryNo
	 */
	public String getCategoryNo() {
		return categoryNo;
	}
	/**
	 * @param categoryNo the categoryNo to set
	 */
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	/**
	 * @return the catalogId
	 */
	public String getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId the catalogId to set
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the assetGroup
	 */
	public String getAssetGroup() {
		return assetGroup;
	}
	/**
	 * @param assetGroup the assetGroup to set
	 */
	public void setAssetGroup(String assetGroup) {
		this.assetGroup = assetGroup;
	}
	/**
	 * @return the parentCatId
	 */
	public Integer getParentCatId() {
		return parentCatId;
	}
	/**
	 * @param parentCatId the parentCatId to set
	 */
	public void setParentCatId(Integer parentCatId) {
		this.parentCatId = parentCatId;
	}
	/**
	 * @return the previousCatId
	 */
	public Integer getPreviousCatId() {
		return previousCatId;
	}
	/**
	 * @param previousCatId the previousCatId to set
	 */
	public void setPreviousCatId(Integer previousCatId) {
		this.previousCatId = previousCatId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBusinessDivision() {
		return businessDivision;
	}
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	public Integer getMarketingGroup() {
		return marketingGroup;
	}
	public void setMarketingGroup(Integer marketingGroup) {
		this.marketingGroup = marketingGroup;
	}
	public Integer getMarketingSplst() {
		return marketingSplst;
	}
	public void setMarketingSplst(Integer marketingSplst) {
		this.marketingSplst = marketingSplst;
	}
	public Integer getMarketingSpvs() {
		return marketingSpvs;
	}
	public void setMarketingSpvs(Integer marketingSpvs) {
		this.marketingSpvs = marketingSpvs;
	}
	public Integer getCategoryLevel() {
		return categoryLevel;
	}
	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	
	
}
