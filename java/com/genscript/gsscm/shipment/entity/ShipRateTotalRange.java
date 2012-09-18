package com.genscript.gsscm.shipment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "ship_rate_total_range", catalog = "shipping")
public class ShipRateTotalRange extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer shipMethodId;
	private Double totalFrom;
	private Double totalTo;
	private Double charge;
	private Double chargePct;
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
	public Double getTotalFrom() {
		return totalFrom;
	}
	public void setTotalFrom(Double totalFrom) {
		this.totalFrom = totalFrom;
	}
	public Double getTotalTo() {
		return totalTo;
	}
	public void setTotalTo(Double totalTo) {
		this.totalTo = totalTo;
	}
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
	}
	public Double getChargePct() {
		return chargePct;
	}
	public void setChargePct(Double chargePct) {
		this.chargePct = chargePct;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
