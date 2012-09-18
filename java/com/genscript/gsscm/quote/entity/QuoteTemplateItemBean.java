package com.genscript.gsscm.quote.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * QUOTE TEMPLATE LIST VIEW.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "v_cust_product_price_of_quote_tmpl", catalog="order")
public class QuoteTemplateItemBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9067955635437731965L;
	@Column(name="tmpl_name")
	private String name;
	private Integer owner;
	private Integer srcQuoteNo;
	private Integer itemNo;
	private Integer custNo;
	private Integer productId;
	@Id
	private String catalogNo;
	@Column(name="name")
	private String productName;
	private String shortDesc;
	private String qtyUom;
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
	private Integer unitInStock;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public Integer getSrcQuoteNo() {
		return srcQuoteNo;
	}
	public void setSrcQuoteNo(Integer srcQuoteNo) {
		this.srcQuoteNo = srcQuoteNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	
	
}
