package com.genscript.gsscm.systemsetting.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ShipRateDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3799149941410368881L;
	private Integer id;
	private Integer shipMethodId;
	private Integer warehouseId;
	private String zoneCode;
	private Double weightFrom;
	private Double weightTo;
	private BigDecimal charge;
	private String note;
	private String idStr;
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
	public Integer getShipMethodId() {
		return shipMethodId;
	}
	public void setShipMethodId(Integer shipMethodId) {
		this.shipMethodId = shipMethodId;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
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
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
}
