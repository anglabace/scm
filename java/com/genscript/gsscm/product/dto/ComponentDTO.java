package com.genscript.gsscm.product.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ComponentDTO", namespace = WsConstants.NS)
public class ComponentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679752891076150202L;
	private Integer id;
	private Integer productId;
	private String cpntCatalogNo;
	private Double quantity;
	private Integer listSeq;
	private String item;
	private String symbol;
	private Integer leadTime;
    private Double price;
    private String specification;
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
	 * @return the cpntCatalogNo
	 */
	public String getCpntCatalogNo() {
		return cpntCatalogNo;
	}
	/**
	 * @param cpntCatalogNo the cpntCatalogNo to set
	 */
	public void setCpntCatalogNo(String cpntCatalogNo) {
		this.cpntCatalogNo = cpntCatalogNo;
	}
	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}	
	
	
	
	
}
