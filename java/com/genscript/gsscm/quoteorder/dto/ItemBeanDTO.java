package com.genscript.gsscm.quoteorder.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ItemBeanDTO", namespace = WsConstants.NS)
public class ItemBeanDTO {
	private Integer productId;
	private Integer serviceId;
	private String catalogNo;
	private String name;
	private String productName;
	private String serviceName;
	private String shortDesc;
	private String qtyUom;
	private Double size;
	private String uom;
	private Integer itemNo;
	private String type;
	private BigDecimal unitPrice;
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
	private BigDecimal specialPrice;
	private String upCatalogId;
	private String baseCatalogId;
	private String specialCatalogId;
    private String catalogId;
    private String catalogName;
    private Integer unitInStock;
    
    private Integer srcQuoteNo;
    private Integer srcOrderNo;
    
    private String templateType;
    
    private String currencyCode;
	
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
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		if(unitPrice == null){
			this.unitPrice = null;
		}else{
			this.unitPrice = unitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
//	public BigDecimal getCatalogPrice() {
//		return catalogPrice;
//	}
//	public void setCatalogPrice(BigDecimal catalogPrice) {
//		this.catalogPrice = catalogPrice;
//	}
	public BigDecimal getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
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
	public String getBaseCatalogId() {
		return baseCatalogId;
	}
	public void setBaseCatalogId(String baseCatalogId) {
		this.baseCatalogId = baseCatalogId;
	}
	public String getSpecialCatalogId() {
		return specialCatalogId;
	}
	public void setSpecialCatalogId(String specialCatalogId) {
		this.specialCatalogId = specialCatalogId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public String getUpCatalogId() {
		return upCatalogId;
	}
	public void setUpCatalogId(String upCatalogId) {
		this.upCatalogId = upCatalogId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public Integer getUnitInStock() {
		return unitInStock;
	}
	public void setUnitInStock(Integer unitInStock) {
		this.unitInStock = unitInStock;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Integer getSrcQuoteNo() {
		return srcQuoteNo;
	}
	public void setSrcQuoteNo(Integer srcQuoteNo) {
		this.srcQuoteNo = srcQuoteNo;
	}
	public Integer getSrcOrderNo() {
		return srcOrderNo;
	}
	public void setSrcOrderNo(Integer srcOrderNo) {
		this.srcOrderNo = srcOrderNo;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
