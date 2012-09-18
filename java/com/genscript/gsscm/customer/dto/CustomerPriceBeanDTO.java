package com.genscript.gsscm.customer.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CustomerPriceBeanDTO implements Serializable {
	private static final long serialVersionUID = -2092862085606315166L;
	private Integer custNo;
	private Integer productId;
	private String catalogNo;
	private String name;
	private String accessionNo;
	private String shortDesc;
	private String code;
	private String upSymbol;
	private Double unitPrice;
	private String qtyUom;
	private Double size;
	private String uom;
	private String upCatalogId;
	private String upCatalogName;
	private String vectorName;
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getUpSymbol() {
		return upSymbol;
	}
	public void setUpSymbol(String upSymbol) {
		this.upSymbol = upSymbol;
	}
	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getUpCatalogId() {
		return upCatalogId;
	}

	public void setUpCatalogId(String upCatalogId) {
		this.upCatalogId = upCatalogId;
	}

	public String getUpCatalogName() {
		return upCatalogName;
	}

	public void setUpCatalogName(String upCatalogName) {
		this.upCatalogName = upCatalogName;
	}

	public String getVectorName() {
		return vectorName;
	}

	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}
}
