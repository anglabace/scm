package com.genscript.gsscm.systemsetting.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ShipZoneDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6522882557063966501L;
	private Integer id;
	private Integer shipMethodId;
	private Integer warehouseId;
	private String country;
	private String zipFrom;
	private String zipTo;
	private String zoneCode;
	private String note;
	private String idStr;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipFrom() {
		return zipFrom;
	}
	public void setZipFrom(String zipFrom) {
		this.zipFrom = zipFrom;
	}
	public String getZipTo() {
		return zipTo;
	}
	public void setZipTo(String zipTo) {
		this.zipTo = zipTo;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
