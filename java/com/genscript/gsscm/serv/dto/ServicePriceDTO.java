package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name="ServicePriceDTO", namespace=WsConstants.NS)
public class ServicePriceDTO implements Serializable{

	private static final long serialVersionUID = -4844800074064343721L;
	
	private Integer priceId;
	private Integer serviceId;
	private Integer categoryId;
	private String catalogId;
	private String status;
	private Double standardPrice;
	private Double listPrice;
	private Double limitPrice;
	private Integer priceRuleGroup;
	private Date creationDate = new Date();
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;	
	//以下为业务数据.
	private List<Integer> delServPriceIdList;
	private String createdByText;
    private String modifyByText;
    
    private String catalogName;
    private String serviceName;
    private String catalogNo;
    private String categoryName;
    private String currency;
    
    
  //ADDED BUSINESS PROPERTIES
	private Integer requestId;
	private String priceReason;
	private String priceNewVal;
	private String priceOldVal;
	private Date requestDate;
	private String requestedBy;
	
    
    private Double serviceSubPriceApprove;
    private Integer serviceRuleGroupPriceApprove;
    private Double servicePriceApprove;
	private String servicePriceReason;
	private Double servicePriceNewVal;
	private Double servicePriceOldVal;
	
	public String getCreatedByText() {
		return createdByText;
	}
	public void setCreatedByText(String createdByText) {
		this.createdByText = createdByText;
	}
	public String getModifyByText() {
		return modifyByText;
	}
	public void setModifyByText(String modifyByText) {
		this.modifyByText = modifyByText;
	}
	public Integer getPriceId() {
		return priceId;
	}
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(Double standardPrice) {
		this.standardPrice = standardPrice;
	}
	public Double getListPrice() {
		return listPrice;
	}
	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}
	public Double getLimitPrice() {
		return limitPrice;
	}
	public void setLimitPrice(Double limitPrice) {
		this.limitPrice = limitPrice;
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
	public List<Integer> getDelServPriceIdList() {
		return delServPriceIdList;
	}
	public void setDelServPriceIdList(List<Integer> delServPriceIdList) {
		this.delServPriceIdList = delServPriceIdList;
	}
	public Integer getPriceRuleGroup() {
		return priceRuleGroup;
	}
	public void setPriceRuleGroup(Integer priceRuleGroup) {
		this.priceRuleGroup = priceRuleGroup;
	}
	
	public Integer getServiceRuleGroupPriceApprove() {
		return serviceRuleGroupPriceApprove;
	}
	public void setServiceRuleGroupPriceApprove(Integer serviceRuleGroupPriceApprove) {
		this.serviceRuleGroupPriceApprove = serviceRuleGroupPriceApprove;
	}
	public Double getServicePriceApprove() {
		return servicePriceApprove;
	}
	public void setServicePriceApprove(Double servicePriceApprove) {
		this.servicePriceApprove = servicePriceApprove;
	}
	public String getServicePriceReason() {
		return servicePriceReason;
	}
	public void setServicePriceReason(String servicePriceReason) {
		this.servicePriceReason = servicePriceReason;
	}
	public Double getServicePriceNewVal() {
		return servicePriceNewVal;
	}
	public void setServicePriceNewVal(Double servicePriceNewVal) {
		this.servicePriceNewVal = servicePriceNewVal;
	}
	public Double getServicePriceOldVal() {
		return servicePriceOldVal;
	}
	public void setServicePriceOldVal(Double servicePriceOldVal) {
		this.servicePriceOldVal = servicePriceOldVal;
	}
	public Double getServiceSubPriceApprove() {
		return serviceSubPriceApprove;
	}
	public void setServiceSubPriceApprove(Double serviceSubPriceApprove) {
		this.serviceSubPriceApprove = serviceSubPriceApprove;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getPriceReason() {
		return priceReason;
	}
	public void setPriceReason(String priceReason) {
		this.priceReason = priceReason;
	}
	public String getPriceNewVal() {
		return priceNewVal;
	}
	public void setPriceNewVal(String priceNewVal) {
		this.priceNewVal = priceNewVal;
	}
	public String getPriceOldVal() {
		return priceOldVal;
	}
	public void setPriceOldVal(String priceOldVal) {
		this.priceOldVal = priceOldVal;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	
}
