package com.genscript.gsscm.serv.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * service OF CATEGORY VIEW.
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "v_services_in_category", catalog="product")
public class ServiceOfServcategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4905199057537476338L;
	
	@Id
	private Integer serviceId;
	private String catalogNo;
	@Column(name="name")
	private String itemName;
	private Double size;
	private String uom;
	private String description;
	private String type;
	private String symbol;
	private Integer categoryId;
	private String status;
	private Date creationDate;
	private Date modifyDate;
	private Double unitPrice;
	private String currencyCode;
	
	private String catalogId;
	private String qtyUom;
	private String categoryNo;
	private String categoryName;
	//private String catalogName;
	private String catalogStatus;
	private String baseCatalog;
	//private Double limitPrice;
	//private Integer pricePrecision;
	//private String createdBy;
	//private String modifiedBy;
	//private Integer leadTime;
	//private String categoryStatus;

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
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCatalogStatus() {
		return catalogStatus;
	}
	public void setCatalogStatus(String catalogStatus) {
		this.catalogStatus = catalogStatus;
	}
	public String getBaseCatalog() {
		return baseCatalog;
	}
	public void setBaseCatalog(String baseCatalog) {
		this.baseCatalog = baseCatalog;
	}
}

