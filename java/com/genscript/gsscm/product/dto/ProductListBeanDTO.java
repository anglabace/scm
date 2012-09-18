package com.genscript.gsscm.product.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ProductListBeanDTO", namespace = WsConstants.NS)
public class ProductListBeanDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4538773100324076105L;
	private Integer productId;
	private Integer requestId;
	private String catalogNo;
	private String name;
	private Double size;
	private String uom;
	private String symbol;
	private String description;
	private String type;
	private String status;
	private String unitCost;
	private Date creationDate;
	private Date modifyDate;
	private Double unitPrice;
	private String currencyCode;
	//ADDED BUSINESS PROPERTIES
	private String nameReason;
	private String statusReason;
	private String unitCostReason;
	
	private String nameNewVal;
	private String nameOldVal;
	private String statusNewVal;
	private String statusOldVal;
	private String unitCostNewVal;
	private String unitCostOldVal;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getNameNewVal() {
		return nameNewVal;
	}
	public void setNameNewVal(String nameNewVal) {
		this.nameNewVal = nameNewVal;
	}
	public String getNameOldVal() {
		return nameOldVal;
	}
	public void setNameOldVal(String nameOldVal) {
		this.nameOldVal = nameOldVal;
	}
	public String getStatusNewVal() {
		return statusNewVal;
	}
	public void setStatusNewVal(String statusNewVal) {
		this.statusNewVal = statusNewVal;
	}
	public String getStatusOldVal() {
		return statusOldVal;
	}
	public void setStatusOldVal(String statusOldVal) {
		this.statusOldVal = statusOldVal;
	}
	public String getNameReason() {
		return nameReason;
	}
	public void setNameReason(String nameReason) {
		this.nameReason = nameReason;
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}
	public String getUnitCostReason() {
		return unitCostReason;
	}
	public void setUnitCostReason(String unitCostReason) {
		this.unitCostReason = unitCostReason;
	}
	public String getUnitCostNewVal() {
		return unitCostNewVal;
	}
	public void setUnitCostNewVal(String unitCostNewVal) {
		this.unitCostNewVal = unitCostNewVal;
	}
	public String getUnitCostOldVal() {
		return unitCostOldVal;
	}
	public void setUnitCostOldVal(String unitCostOldVal) {
		this.unitCostOldVal = unitCostOldVal;
	}
	
}
