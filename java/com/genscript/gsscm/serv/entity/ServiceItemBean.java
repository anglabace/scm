package com.genscript.gsscm.serv.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SERVICE ITEM BEAN.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "v_services_in_category", catalog = "product")
public class ServiceItemBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4046372540252346271L;
	@Id
	private Integer serviceId;
	private String catalogNo;
	private String type;
	private String name;
	private Double size;
	private String uom;
	private String qtyUom;
	private String description;
	private Integer categoryId;
	private String categoryNo;
	private String categoryName;
	private String catalogId;
	private String catalogStatus;
	private String baseCatalog;
	private String unitPrice;
	private String symbol;
	private String currencyCode;
	private String status;
	
	public String getBaseCatalog() {
		return baseCatalog;
	}
	public void setBaseCatalog(String baseCatalog) {
		this.baseCatalog = baseCatalog;
	}
	public String getCatalogStatus() {
		return catalogStatus;
	}
	public void setCatalogStatus(String catalogStatus) {
		this.catalogStatus = catalogStatus;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
