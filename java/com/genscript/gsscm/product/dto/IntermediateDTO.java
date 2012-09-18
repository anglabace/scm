package com.genscript.gsscm.product.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "IntermediateDTO", namespace = WsConstants.NS)
public class IntermediateDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679752891076150202L;
	private Integer id;
	private Integer productId;
	private Integer quantity;
	private String intmdCatalogNo;
	private Integer listSeq;
    private String requiredFlag;
	private String item;
	private String symbol;
	private Integer leadTime;
    private Double price;
    private String intmdKeyword;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    /**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the intmdCatalogNo
	 */
	public String getIntmdCatalogNo() {
		return intmdCatalogNo;
	}
	/**
	 * @param intmdCatalogNo the intmdCatalogNo to set
	 */
	public void setIntmdCatalogNo(String intmdCatalogNo) {
		this.intmdCatalogNo = intmdCatalogNo;
	}
	/**
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}
	/**
	 * @param listSeq the listSeq to set
	 */
	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * @return the leadTime
	 */
	public Integer getLeadTime() {
		return leadTime;
	}
	/**
	 * @param leadTime the leadTime to set
	 */
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
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
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the requiredFlag
	 */
	public String getRequiredFlag() {
		return requiredFlag;
	}
	/**
	 * @param requiredFlag the requiredFlag to set
	 */
	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}
	
	public String getIntmdKeyword() {
		return intmdKeyword;
	}
	public void setIntmdKeyword(String intmdKeyword) {
		this.intmdKeyword = intmdKeyword;
	}
	
}
