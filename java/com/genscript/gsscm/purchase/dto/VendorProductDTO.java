package com.genscript.gsscm.purchase.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;



@XmlType(name = "VendorProductDTO", namespace = WsConstants.NS)
public class VendorProductDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -570915645565259491L;
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
	 * @return the vendorNo
	 */
	public Integer getVendorNo() {
		return vendorNo;
	}
	/**
	 * @param vendorNo the vendorNo to set
	 */
	public void setVendorNo(Integer vendorNo) {
		this.vendorNo = vendorNo;
	}
	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}
	/**
	 * @param catalogNo the catalogNo to set
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the size
	 */
	public BigDecimal getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(BigDecimal size) {
		this.size = size;
	}
	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}
	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}
	/**
	 * @return the buyQuantity
	 */
	public Integer getBuyQuantity() {
		return buyQuantity;
	}
	/**
	 * @param buyQuantity the buyQuantity to set
	 */
	public void setBuyQuantity(Integer buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	/**
	 * @return the sellQuantity
	 */
	public Integer getSellQuantity() {
		return sellQuantity;
	}
	/**
	 * @param sellQuantity the sellQuantity to set
	 */
	public void setSellQuantity(Integer sellQuantity) {
		this.sellQuantity = sellQuantity;
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
	 * @return the standardPrice
	 */
	public BigDecimal getStandardPrice() {
		return standardPrice;
	}
	/**
	 * @param standardPrice the standardPrice to set
	 */
	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}
	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	/**
	 * @return the batchPrice
	 */
	public BigDecimal getBatchPrice() {
		return batchPrice;
	}
	/**
	 * @param batchPrice the batchPrice to set
	 */
	public void setBatchPrice(BigDecimal batchPrice) {
		this.batchPrice = batchPrice;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the effFrom
	 */
	public Date getEffFrom() {
		return effFrom;
	}
	/**
	 * @param effFrom the effFrom to set
	 */
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}
	/**
	 * @return the effTo
	 */
	public Date getEffTo() {
		return effTo;
	}
	/**
	 * @param effTo the effTo to set
	 */
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}
	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	/**
	 * @return the vendorCatalogNo
	 */
	public String getVendorCatalogNo() {
		return vendorCatalogNo;
	}
	/**
	 * @param vendorCatalogNo the vendorCatalogNo to set
	 */
	public void setVendorCatalogNo(String vendorCatalogNo) {
		this.vendorCatalogNo = vendorCatalogNo;
	}
	/**
	 * @return the altSize
	 */
	public BigDecimal getAltSize() {
		return altSize;
	}
	/**
	 * @param altSize the altSize to set
	 */
	public void setAltSize(BigDecimal altSize) {
		this.altSize = altSize;
	}
	/**
	 * @return the altUom
	 */
	public String getAltUom() {
		return altUom;
	}
	/**
	 * @param altUom the altUom to set
	 */
	public void setAltUom(String altUom) {
		this.altUom = altUom;
	}
	
}
