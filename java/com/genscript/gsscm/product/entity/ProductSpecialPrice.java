package com.genscript.gsscm.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;
import com.genscript.gsscm.common.constant.WsConstants;

/**
 * ProductSpecialPrice.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "product_special_price", catalog = "product")
@XmlType(name = "ProductSpecialPrice", namespace = WsConstants.NS)
public class ProductSpecialPrice extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008369632411038932L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_id")
	private Integer id;
	private Integer productId;
	private String catalogId;
	private Double standardPrice;
	private Double unitPrice;
	private String discount;
	private Integer rfmRating;
	private Integer sourceId;
	private Double minQty;
	private Double orderTotal;
	private Date effFrom;
	private Date effTo;
	private String status;

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
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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
	 * @return the rfmRating
	 */
	public Integer getRfmRating() {
		return rfmRating;
	}

	/**
	 * @param rfmRating the rfmRating to set
	 */
	public void setRfmRating(Integer rfmRating) {
		this.rfmRating = rfmRating;
	}

	/**
	 * @return the sourceId
	 */
	public Integer getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the minQty
	 */
	public Double getMinQty() {
		return minQty;
	}

	/**
	 * @param minQty the minQty to set
	 */
	public void setMinQty(Double minQty) {
		this.minQty = minQty;
	}

	/**
	 * @return the orderTotal
	 */
	public Double getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(Double orderTotal) {
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
}
