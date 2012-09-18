package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * CUSTOMER_SPECIAL_PRICE.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "customer_special_price")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerSpecialPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4653514664883999619L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_id")
	private Integer priceId;
	private Integer custNo;
	@Column(name = "catalog_Id")
	private String catalogId;
	private String catalogNo;
	private String type;
	private String name;
	private BigDecimal unitPrice;
	private BigDecimal standardPrice;	
	private String discount;
	private Integer minQty;
	private Integer source;
	private BigDecimal orderTotal;
	private Date effFrom;
	private Date effTo;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
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
	 * @return the custNo
	 */
	public Integer getCustNo() {
		return custNo;
	}
	/**
	 * @param custNo the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the minQty
	 */
	public Integer getMinQty() {
		return minQty;
	}
	/**
	 * @param minQty the minQty to set
	 */
	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}
	/**
	 * @return the source
	 */
	public Integer getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	/**
	 * @return the orderTotal
	 */
	public BigDecimal getOrderTotal() {
		return orderTotal;
	}
	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
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
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
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

	

}
