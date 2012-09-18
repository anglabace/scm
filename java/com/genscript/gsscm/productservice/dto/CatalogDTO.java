package com.genscript.gsscm.productservice.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.serv.entity.ServiceCategory;

@XmlType(name="CatalogDTO", namespace=WsConstants.NS)
public class CatalogDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5157507142592851817L;
	private Integer id;
	private String catalogId;
	private String catalogName;
	private String type;
	private String description;
	private String defaultFlag;
	private Integer publisher;
	private Date publishDate;
	private String publishZone;
	private String currencyCode;
	private String priceLimit;
	private String status;
	private Date creationDate ;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;	
	private String createUser;
	private String modifyUser;
	private List<ProductCategory> pdtCatList;
	private List<ServiceCategory> serviceCatList;
	private List<Integer> delPdtCatIdList;
	private List<Integer> delServiceCatIdList;
	
	//ADDED BUSINESS PROPERTIES
	private String nameApprove;
	private String nameReason;
	private String statusApprove;
	private String statusReason;
	
	private String nameNewVal;
	private String nameOldVal;
	private String statusNewVal;
	private String statusOldVal;
	private Integer requestId;
	
	private String publishUser;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the catalogName
	 */
	public String getCatalogName() {
		return catalogName;
	}
	/**
	 * @param catalogName the catalogName to set
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	public Integer getPublisher() {
		return publisher;
	}
	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getStatus() {
		return status;
	}
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
	 * @return the publishZone
	 */
	public String getPublishZone() {
		return publishZone;
	}
	/**
	 * @param publishZone the publishZone to set
	 */
	public void setPublishZone(String publishZone) {
		this.publishZone = publishZone;
	}
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	/**
	 * @return the priceLimit
	 */
	public String getPriceLimit() {
		return priceLimit;
	}
	/**
	 * @param priceLimit the priceLimit to set
	 */
	public void setPriceLimit(String priceLimit) {
		this.priceLimit = priceLimit;
	}
	/**
	 * @return the pdtCatList
	 */
	public List<ProductCategory> getPdtCatList() {
		return pdtCatList;
	}
	/**
	 * @param pdtCatList the pdtCatList to set
	 */
	public void setPdtCatList(List<ProductCategory> pdtCatList) {
		this.pdtCatList = pdtCatList;
	}
	/**
	 * @return the serviceCatList
	 */
	public List<ServiceCategory> getServiceCatList() {
		return serviceCatList;
	}
	/**
	 * @param serviceCatList the serviceCatList to set
	 */
	public void setServiceCatList(List<ServiceCategory> serviceCatList) {
		this.serviceCatList = serviceCatList;
	}
	/**
	 * @return the delPdtCatIdList
	 */
	public List<Integer> getDelPdtCatIdList() {
		return delPdtCatIdList;
	}
	/**
	 * @param delPdtCatIdList the delPdtCatIdList to set
	 */
	public void setDelPdtCatIdList(List<Integer> delPdtCatIdList) {
		this.delPdtCatIdList = delPdtCatIdList;
	}
	/**
	 * @return the delServiceCatIdList
	 */
	public List<Integer> getDelServiceCatIdList() {
		return delServiceCatIdList;
	}
	/**
	 * @param delServiceCatIdList the delServiceCatIdList to set
	 */
	public void setDelServiceCatIdList(List<Integer> delServiceCatIdList) {
		this.delServiceCatIdList = delServiceCatIdList;
	}
	public String getNameApprove() {
		return nameApprove;
	}
	public void setNameApprove(String nameApprove) {
		this.nameApprove = nameApprove;
	}
	public String getStatusApprove() {
		return statusApprove;
	}
	public void setStatusApprove(String statusApprove) {
		this.statusApprove = statusApprove;
	}
	public String getNameReason() {
		return nameReason;
	}
	public void setNameReason(String nameReason) {
		this.nameReason = nameReason;
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
	 * @return the publishUser
	 */
	public String getPublishUser() {
		return publishUser;
	}
	/**
	 * @param publishUser the publishUser to set
	 */
	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

}
