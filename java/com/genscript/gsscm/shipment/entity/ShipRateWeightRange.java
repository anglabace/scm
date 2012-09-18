package com.genscript.gsscm.shipment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ORDER SHIP RATE WEIGHT RANGE.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "ship_rate_weight_range", catalog="shipping")
public class ShipRateWeightRange extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer shipMethodId;
	private Double weightFrom;
	private Double weightTo;
	private Double charge;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShipMethodId() {
		return shipMethodId;
	}
	public void setShipMethodId(Integer shipMethodId) {
		this.shipMethodId = shipMethodId;
	}
	public Double getWeightFrom() {
		return weightFrom;
	}
	public void setWeightFrom(Double weightFrom) {
		this.weightFrom = weightFrom;
	}
	public Double getWeightTo() {
		return weightTo;
	}
	public void setWeightTo(Double weightTo) {
		this.weightTo = weightTo;
	}
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
