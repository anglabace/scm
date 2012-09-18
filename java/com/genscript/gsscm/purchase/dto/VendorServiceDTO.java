package com.genscript.gsscm.purchase.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "VendorServiceDTO", namespace = WsConstants.NS)
public class VendorServiceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2467509406309298285L;

	private Integer id;
	private Integer vendorNo;
	private String vendorCatalogNo;
	private String catalogNo;
	private String name;
	private BigDecimal size;
	private String uom;
	private Integer buyQuantity;
	private Integer sellQuantity;
	private Integer leadTime;
	private BigDecimal standardPrice;
	private String discount;
	private BigDecimal batchPrice;
	private String comment;
	private Date effFrom;
	private Date effTo;
	private BigDecimal altSize;
	private String altUom;
	//非本表而是业务需要的属性
	private String vendorName;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(Integer vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getVendorCatalogNo() {
		return vendorCatalogNo;
	}
	public void setVendorCatalogNo(String vendorCatalogNo) {
		this.vendorCatalogNo = vendorCatalogNo;
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
	public BigDecimal getSize() {
		return size;
	}
	public void setSize(BigDecimal size) {
		this.size = size;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public Integer getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(Integer buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public Integer getSellQuantity() {
		return sellQuantity;
	}
	public void setSellQuantity(Integer sellQuantity) {
		this.sellQuantity = sellQuantity;
	}
	public Integer getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	public BigDecimal getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public BigDecimal getBatchPrice() {
		return batchPrice;
	}
	public void setBatchPrice(BigDecimal batchPrice) {
		this.batchPrice = batchPrice;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getEffFrom() {
		return effFrom;
	}
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}
	public Date getEffTo() {
		return effTo;
	}
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}
	public BigDecimal getAltSize() {
		return altSize;
	}
	public void setAltSize(BigDecimal altSize) {
		this.altSize = altSize;
	}
	public String getAltUom() {
		return altUom;
	}
	public void setAltUom(String altUom) {
		this.altUom = altUom;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
}
