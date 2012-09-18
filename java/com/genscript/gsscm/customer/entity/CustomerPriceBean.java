package com.genscript.gsscm.customer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CUSTOMER PRICE VIEW.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "V_cust_product_price")
public class CustomerPriceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -804603475402516545L;
	private Integer custNo;
	@Id
	private Integer productId;
	private String catalogNo;
	private String name;
	private String shortDesc;
	private String qtyUom;
	private Double size;
	private String uom;
	private String type;
	private String status;
//	private Integer clsId;
//	private String clsName;
//	private String taxStatus;
//	private String customerInfo;
//	private Integer scheduleShip;
	private Double unitPrice;
	private String upCurrency;
	private Integer upPrecision;
	private String upSymbol;
	private String upCatalogName;
//	private String cpCurrency;
//	private Integer cpPrecision;
//	private String cpSymbol;
//	private String cpCatalogName;
	private String spCurrency;
	private Integer spPrecision;
	private String spSymbol;
//	private BigDecimal catalogPrice;
	private Double specialPrice;
	private String upCatalogId;
//	private String cpCatalogId;
	private String spCatalogId;
	private String spCatalogName;
//	private String sellingNote;
	private Integer unitInStock;
	private String categoryName;
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
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(Double specialPrice) {
		this.specialPrice = specialPrice;
	}
	public String getUpCatalogId() {
		return upCatalogId;
	}
	public void setUpCatalogId(String upCatalogId) {
		this.upCatalogId = upCatalogId;
	}
	public String getSpCatalogId() {
		return spCatalogId;
	}
	public void setSpCatalogId(String spCatalogId) {
		this.spCatalogId = spCatalogId;
	}
	public String getSpCatalogName() {
		return spCatalogName;
	}
	public void setSpCatalogName(String spCatalogName) {
		this.spCatalogName = spCatalogName;
	}
	public String getUpCurrency() {
		return upCurrency;
	}
	public void setUpCurrency(String upCurrency) {
		this.upCurrency = upCurrency;
	}
	public Integer getUpPrecision() {
		return upPrecision;
	}
	public void setUpPrecision(Integer upPrecision) {
		this.upPrecision = upPrecision;
	}
	public String getUpSymbol() {
		return upSymbol;
	}
	public void setUpSymbol(String upSymbol) {
		this.upSymbol = upSymbol;
	}
	public String getUpCatalogName() {
		return upCatalogName;
	}
	public void setUpCatalogName(String upCatalogName) {
		this.upCatalogName = upCatalogName;
	}
	public String getSpCurrency() {
		return spCurrency;
	}
	public void setSpCurrency(String spCurrency) {
		this.spCurrency = spCurrency;
	}
	public Integer getSpPrecision() {
		return spPrecision;
	}
	public void setSpPrecision(Integer spPrecision) {
		this.spPrecision = spPrecision;
	}
	public String getSpSymbol() {
		return spSymbol;
	}
	public void setSpSymbol(String spSymbol) {
		this.spSymbol = spSymbol;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public Integer getUnitInStock() {
		return unitInStock;
	}
	public void setUnitInStock(Integer unitInStock) {
		this.unitInStock = unitInStock;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
