package com.genscript.gsscm.shipment.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author mingrs
 */
@Entity
@Table(name = "shipping_clerk_shipment_adjustment", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShippingClerkShipmentAdjustment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer shipmentId;
	private Integer shippingClerk;
	private Date adjustDate;
	private Integer adjustedBy;
	private String reasion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}
	public Integer getShippingClerk() {
		return shippingClerk;
	}
	public void setShippingClerk(Integer shippingClerk) {
		this.shippingClerk = shippingClerk;
	}
	public Date getAdjustDate() {
		return adjustDate;
	}
	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}
	public Integer getAdjustedBy() {
		return adjustedBy;
	}
	public void setAdjustedBy(Integer adjustedBy) {
		this.adjustedBy = adjustedBy;
	}
	public String getReasion() {
		return reasion;
	}
	public void setReasion(String reasion) {
		this.reasion = reasion;
	} 
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}