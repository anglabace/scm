package com.genscript.gsscm.product.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name="ProductPriceDTO", namespace=WsConstants.NS)
public class ProductPriceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4919468602322927720L;
	private Integer priceId;
	private Integer productId;
	private Integer categoryId;
	private String catalogId;
	private String status;
	private Double standardPrice;
	private Double listPrice;
	private Double limitPrice;
	private String symbol;
	private String currencyCode;
	
	private Double productPriceApprove;
	private String productPriceReason;
	private Double productPriceNewVal;
	private Double productPriceOldVal;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the priceId
	 */
	public Integer getPriceId() {
		return priceId;
	}
	/**
	 * @param priceId the priceId to set
	 */
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the catalogId
	 */
	public String getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId the catalogId to set
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the standardPrice
	 */
	public Double getStandardPrice() {
		return standardPrice;
	}
	/**
	 * @param standardPrice the standardPrice to set
	 */
	public void setStandardPrice(Double standardPrice) {
		this.standardPrice = standardPrice;
	}
	/**
	 * @return the listPrice
	 */
	public Double getListPrice() {
		return listPrice;
	}
	/**
	 * @param listPrice the listPrice to set
	 */
	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}
	/**
	 * @return the limitPrice
	 */
	public Double getLimitPrice() {
		return limitPrice;
	}
	/**
	 * @param limitPrice the limitPrice to set
	 */
	public void setLimitPrice(Double limitPrice) {
		this.limitPrice = limitPrice;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Double getProductPriceApprove() {
		return productPriceApprove;
	}
	public void setProductPriceApprove(Double productPriceApprove) {
		this.productPriceApprove = productPriceApprove;
	}
	public String getProductPriceReason() {
		return productPriceReason;
	}
	public void setProductPriceReason(String productPriceReason) {
		this.productPriceReason = productPriceReason;
	}
	public Double getProductPriceNewVal() {
		return productPriceNewVal;
	}
	public void setProductPriceNewVal(Double productPriceNewVal) {
		this.productPriceNewVal = productPriceNewVal;
	}
	public Double getProductPriceOldVal() {
		return productPriceOldVal;
	}
	public void setProductPriceOldVal(Double productPriceOldVal) {
		this.productPriceOldVal = productPriceOldVal;
	}
}
