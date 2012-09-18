package com.genscript.gsscm.product.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ProductPriceListBeanDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3049284890962581146L;
	
	private String catalogNo;
	private String name;
	private String catalogId;
	private String categoryName;
	private String currencyCode;
	private Double limitPrice;
	private String symbol;
	private String productId;
	private Integer requestId;
	private Date creationDate;
	private String createdBy;
	private Date modifyDate;
	private String modifiedBy;
	private Date requestDate;
	private String requestedBy;
	
	//ADDED BUSINESS PROPERTIES
	private String priceReason;
	private String priceNewVal;
	private String priceOldVal;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Double getLimitPrice() {
		return limitPrice;
	}
	public void setLimitPrice(Double limitPrice) {
		this.limitPrice = limitPrice;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
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
}
