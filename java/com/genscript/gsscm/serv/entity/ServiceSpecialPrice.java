package com.genscript.gsscm.serv.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * The persistent class for the service_special_price database table.
 * 
 */
@Entity
@Table(name="service_special_price", catalog="product")
public class ServiceSpecialPrice extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="price_id")
	private Integer priceId;

	private Integer serviceId;
	@Column(name="catalog_id")
	private String catalogId;

	private String discount;

    @Temporal( TemporalType.DATE)
	@Column(name="eff_from")
	private Date effFrom;

    @Temporal( TemporalType.DATE)
	@Column(name="eff_to")
	private Date effTo;

	@Column(name="min_qty")
	private BigDecimal minQty;

	@Column(name="order_total")
	private BigDecimal orderTotal;

	@Column(name="rfm_rating")
	private Integer rfmRating;

	@Column(name="source_id")
	private Integer sourceId;

	@Column(name="standard_price")
	private BigDecimal standardPrice;

	private String status;

	@Column(name="unit_price")
	private BigDecimal unitPrice;

	

    
    public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceSpecialPrice() {
    }

	public Integer getPriceId() {
		return this.priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public String getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Date getEffFrom() {
		return this.effFrom;
	}

	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}

	public Date getEffTo() {
		return this.effTo;
	}

	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}

	public BigDecimal getMinQty() {
		return this.minQty;
	}

	public void setMinQty(BigDecimal minQty) {
		this.minQty = minQty;
	}

	public BigDecimal getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Integer getRfmRating() {
		return this.rfmRating;
	}

	public void setRfmRating(Integer rfmRating) {
		this.rfmRating = rfmRating;
	}

	public Integer getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public BigDecimal getStandardPrice() {
		return this.standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
