package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.entity.ServiceReference;

@XmlType(name="ServiceCategoryDTO", namespace=WsConstants.NS)
public class ServiceCategoryDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7056601020877364002L;
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
	private Integer clsId;
	private Integer priceRuleGroup;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	//ADDED BUSINESS PROPERTIES
    private List<ServiceCategoryDTO> subCategoryList;
    private List<ServiceCategory> subCategoryLists;
    private List<Integer> delSubCategoryIdList;
	private List<ServicePriceDTO> ServPriceList;
    private List<Integer> delServPriceIdList;
    private String createdByText;
    private String modifyByText;
	private String createUser;
	private String modifyUser;
    private String parentCatName;
	private String previousCatName;
	private String parentCatNo;
	private String previousCatNo;
	
	private Integer requestId;
	private String nameApprove;
	private String nameReason;
	private String statusApprove;
	private String statusReason;
	
	private String nameNewVal;
	private String nameOldVal;
	private String statusNewVal;
	private String statusOldVal;
	/*
	 * 
	 */
	private List<ServiceReference> serviceReferenceList;
	private List<Integer> delReferenceList;
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
	public String getCreateUser() {
		return createUser;
	}


	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public String getModifyUser() {
		return modifyUser;
	}


	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
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

	public String getParentCatName() {
		return parentCatName;
	}

	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}

	public String getPreviousCatName() {
		return previousCatName;
	}

	public void setPreviousCatName(String previousCatName) {
		this.previousCatName = previousCatName;
	}

	public String getModifyByText() {
		return modifyByText;
	}

	public void setModifyByText(String modifyByText) {
		this.modifyByText = modifyByText;
	}

	public String getCreatedByText() {
		return createdByText;
	}

	public void setCreatedByText(String createdByText) {
		this.createdByText = createdByText;
	}

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
	public Integer getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(Integer parentCatId) {
		this.parentCatId = parentCatId;
	}
	public Integer getPreviousCatId() {
		return previousCatId;
	}
	public void setPreviousCatId(Integer previousCatId) {
		this.previousCatId = previousCatId;
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
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
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
	public List<ServiceCategoryDTO> getSubCategoryList() {
		return subCategoryList;
	}
	public void setSubCategoryList(List<ServiceCategoryDTO> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	public List<Integer> getDelSubCategoryIdList() {
		return delSubCategoryIdList;
	}
	public void setDelSubCategoryIdList(List<Integer> delSubCategoryIdList) {
		this.delSubCategoryIdList = delSubCategoryIdList;
	}

	public List<ServicePriceDTO> getServPriceList() {
		return ServPriceList;
	}

	public void setServPriceList(List<ServicePriceDTO> servPriceList) {
		ServPriceList = servPriceList;
	}

	public List<Integer> getDelServPriceIdList() {
		return delServPriceIdList;
	}

	public void setDelServPriceIdList(List<Integer> delServPriceIdList) {
		this.delServPriceIdList = delServPriceIdList;
	}


	public List<ServiceCategory> getSubCategoryLists() {
		return subCategoryLists;
	}


	public void setSubCategoryLists(List<ServiceCategory> subCategoryLists) {
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


	public Integer getClsId() {
		return clsId;
	}


	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}


	public Integer getPriceRuleGroup() {
		return priceRuleGroup;
	}


	public void setPriceRuleGroup(Integer priceRuleGroup) {
		this.priceRuleGroup = priceRuleGroup;
	}


	public List<ServiceReference> getServiceReferenceList() {
		return serviceReferenceList;
	}


	public void setServiceReferenceList(List<ServiceReference> serviceReferenceList) {
		this.serviceReferenceList = serviceReferenceList;
	}


	public List<Integer> getDelReferenceList() {
		return delReferenceList;
	}


	public void setDelReferenceList(List<Integer> delReferenceList) {
		this.delReferenceList = delReferenceList;
	}


	public Integer getCategoryLevel() {
		return categoryLevel;
	}


	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	
}
