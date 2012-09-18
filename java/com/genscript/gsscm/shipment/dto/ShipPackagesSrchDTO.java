package com.genscript.gsscm.shipment.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ShipPackages entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ship_packages", catalog = "shipping")
public class ShipPackagesSrchDTO implements java.io.Serializable {

	// Fields

	private Integer warehouseId;
	private Integer shippingClerk;
	private String status;
	private Date shipmentDate;
	private String trackingNo;
	
	
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getShippingClerk() {
		return shippingClerk;
	}
	public void setShippingClerk(Integer shippingClerk) {
		this.shippingClerk = shippingClerk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
}