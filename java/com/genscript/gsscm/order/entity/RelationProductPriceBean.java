package com.genscript.gsscm.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RELATION TEMPLATE ITEMS VIEW.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "v_related_products_price", catalog="order")
public class RelationProductPriceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4682743124791660737L;
	private Integer mainProductId;
	private String mainCatalogNo;
	private Integer custNo;
	@Id
	private Integer productId;
	private String catalogNo;
	private String name;
	private String shortDesc;
	private Double size;
	private String uom;
//	private String fullDesc;
	private String type;
//	private Integer clsId;
//	private String clsName;
//	private String taxStatus;
//	private String customerInfo;
//	private Integer scheduleShip;
	private BigDecimal unitPrice;
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
	private BigDecimal specialPrice;
	private String upCatalogId;
//	private String cpCatalogId;
	private String spCatalogId;
	private String spCatalogName;
//	private String sellingNote;
	
	public Integer getMainProductId() {
		return mainProductId;
	}
	public void setMainProductId(Integer mainProductId) {
		this.mainProductId = mainProductId;
	}
	public String getMainCatalogNo() {
		return mainCatalogNo;
	}
	public void setMainCatalogNo(String mainCatalogNo) {
		this.mainCatalogNo = mainCatalogNo;
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
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
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
	public BigDecimal getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(BigDecimal specialPrice) {
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
	
	
}
