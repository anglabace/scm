package com.genscript.gsscm.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.product.entity.ProductCategory;

@XmlType(name="ProductCategoryDTO", namespace=WsConstants.NS)
public class ProductCategoryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5157507142592851817L;
	private Integer categoryId;
    private String categoryNo;
    private String catalogId;
    private Integer categoryLevel;
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
	private Date creationDate ;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;	
	private String createUser;
	private String modifyUser;
	//以下为业务数据.
    private List<ProductCategoryDTO> subCategoryList;
    private List<ProductCategory> subCategoryLists;
    private List<Integer> delSubCategoryIdList;
	private String parentCatName;
	private String previousCatName;
	private String parentCatNo;
	private String previousCatNo;
	private List<ProductPriceDTO> productPriceList;
    private List<Integer> delProductPriceIdList;
	//ADDED BUSINESS PROPERTIES
	private Integer requestId;
	private String nameApprove;
	private String nameReason;
	private String statusApprove;
	private String statusReason;
	
	private String nameNewVal;
	private String nameOldVal;
	private String statusNewVal;
	private String statusOldVal;
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getParentCatNo() {
		return parentCatNo;
	}

	public void setParentCatNo(String parentCatNo) {
		this.parentCatNo = parentCatNo;
	}

	public String getPreviousCatNo() {
		return previousCatNo;
	}

	public void setPreviousCatNo(String previousCatNo) {
		this.previousCatNo = previousCatNo;
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

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the modifyUser
	 */
	public String getModifyUser() {
		return modifyUser;
	}

	/**
	 * @param modifyUser the modifyUser to set
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	/**
	 * @return the subCategoryList
	 */
	public List<ProductCategoryDTO> getSubCategoryList() {
		return subCategoryList;
	}

	/**
	 * @param subCategoryList the subCategoryList to set
	 */
	public void setSubCategoryList(List<ProductCategoryDTO> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	/**
	 * @return the delSubCategoryIdList
	 */
	public List<Integer> getDelSubCategoryIdList() {
		return delSubCategoryIdList;
	}

	/**
	 * @param delSubCategoryIdList the delSubCategoryIdList to set
	 */
	public void setDelSubCategoryIdList(List<Integer> delSubCategoryIdList) {
		this.delSubCategoryIdList = delSubCategoryIdList;
	}

	/**
	 * @return the parentCatName
	 */
	public String getParentCatName() {
		return parentCatName;
	}

	/**
	 * @param parentCatName the parentCatName to set
	 */
	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}

	/**
	 * @return the previousCatName
	 */
	public String getPreviousCatName() {
		return previousCatName;
	}

	/**
	 * @param previousCatName the previousCatName to set
	 */
	public void setPreviousCatName(String previousCatName) {
		this.previousCatName = previousCatName;
	}

	public String getNameApprove() {
		return nameApprove;
	}

	public void setNameApprove(String nameApprove) {
		this.nameApprove = nameApprove;
	}

	public String getNameReason() {
		return nameReason;
	}

	public void setNameReason(String nameReason) {
		this.nameReason = nameReason;
	}

	public String getStatusApprove() {
		return statusApprove;
	}

	public void setStatusApprove(String statusApprove) {
		this.statusApprove = statusApprove;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getNameNewVal() {
		return nameNewVal;
	}

	public void setNameNewVal(String nameNewVal) {
		this.nameNewVal = nameNewVal;
	}

	public String getNameOldVal() {
		return nameOldVal;
	}

	public void setNameOldVal(String nameOldVal) {
		this.nameOldVal = nameOldVal;
	}

	public String getStatusNewVal() {
		return statusNewVal;
	}

	public void setStatusNewVal(String statusNewVal) {
		this.statusNewVal = statusNewVal;
	}

	public String getStatusOldVal() {
		return statusOldVal;
	}

	public void setStatusOldVal(String statusOldVal) {
		this.statusOldVal = statusOldVal;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the productPriceList
	 */
	public List<ProductPriceDTO> getProductPriceList() {
		return productPriceList;
	}

	/**
	 * @param productPriceList the productPriceList to set
	 */
	public void setProductPriceList(List<ProductPriceDTO> productPriceList) {
		this.productPriceList = productPriceList;
	}

	/**
	 * @return the delProductPriceIdList
	 */
	public List<Integer> getDelProductPriceIdList() {
		return delProductPriceIdList;
	}

	/**
	 * @param delProductPriceIdList the delProductPriceIdList to set
	 */
	public void setDelProductPriceIdList(List<Integer> delProductPriceIdList) {
		this.delProductPriceIdList = delProductPriceIdList;
	}

	public List<ProductCategory> getSubCategoryLists() {
		return subCategoryLists;
	}

	public void setSubCategoryLists(List<ProductCategory> subCategoryLists) {
		this.subCategoryLists = subCategoryLists;
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
