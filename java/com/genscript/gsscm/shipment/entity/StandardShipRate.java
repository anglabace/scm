package com.genscript.gsscm.shipment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ORDER SHIP METHOD.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "standard_ship_rate", catalog="shipping")
public class StandardShipRate extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer shipMethodId;
	private Integer zoneType;
	private String zoneCode;
	private Integer warehouseId;
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
	public Integer getZoneType() {
		return zoneType;
	}
	public void setZoneType(Integer zoneType) {
		this.zoneType = zoneType;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
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
}
