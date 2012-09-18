package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.util.Date;


public class ServicePriceListBeanDTO implements Serializable {

	private static final long serialVersionUID = -7056601020877364002L;
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
    private String priceReason;
    private String priceNewVal;
    private String priceOldVal;
	private Integer requestId;
	private Date modifyDate;
	private String modifiedBy;
	private Date requestDate;
	private String requestedBy;

    public Integer getPriceId() {
        return priceId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
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

    public Integer getPriceRuleGroup() {
        return priceRuleGroup;
    }

    public void setPriceRuleGroup(Integer priceRuleGroup) {
        this.priceRuleGroup = priceRuleGroup;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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
}
