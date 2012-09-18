package com.genscript.gsscm.product.dto;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ProductRelationItemDTO", namespace = WsConstants.NS)
public class ProductRelationItemDTO {

	private Integer productId;
	private String relationType;
	private String relateInfo;
	private String catalogNo;
	private String itemName;
	private Double size;
	private String uom;
	private String qtyUom;
	private Double unitPrice;
//	private BigDecimal catalogPrice;
	private Double specialPrice;
	private String upCatalogId;
	private String cpCatalogId;
//	private String fullDesc;
	private String type;
//	private String taxStatus;
//	private String customerInfo;
//	private Integer scheduleShip;
//	private Integer clsId;
//	private String clsName;
	private String upCurrency;
	private Integer upPrecision;
	private String upSymbol;
	private String upCatalogName;
	private String cpCurrency;
	private Integer cpPrecision;
	private String cpSymbol;
	private String cpCatalogName;
	private String spCurrency;
	private Integer spPrecision;
	private String spSymbol;
	private String spCatalogId;
	private String spCatalogName;
//	private String sellingNote;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getRelationType() {
		return relationType;
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
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getRelateInfo() {
		return relateInfo;
	}
	public void setRelateInfo(String relateInfo) {
		this.relateInfo = relateInfo;
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
	public String getUpCatalogId() {
		return upCatalogId;
	}
	public void setUpCatalogId(String upCatalogId) {
		this.upCatalogId = upCatalogId;
	}
	public String getCpCatalogId() {
		return cpCatalogId;
	}
	public void setCpCatalogId(String cpCatalogId) {
		this.cpCatalogId = cpCatalogId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getCpCurrency() {
		return cpCurrency;
	}
	public void setCpCurrency(String cpCurrency) {
		this.cpCurrency = cpCurrency;
	}
	public Integer getCpPrecision() {
		return cpPrecision;
	}
	public void setCpPrecision(Integer cpPrecision) {
		this.cpPrecision = cpPrecision;
	}
	public String getCpSymbol() {
		return cpSymbol;
	}
	public void setCpSymbol(String cpSymbol) {
		this.cpSymbol = cpSymbol;
	}
	public String getCpCatalogName() {
		return cpCatalogName;
	}
	public void setCpCatalogName(String cpCatalogName) {
		this.cpCatalogName = cpCatalogName;
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
	
}
