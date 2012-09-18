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
 * ORDER SHIP ZONE DETAIL.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "standard_ship_zone_detail", catalog="shipping")
public class StandardShipZoneDetail  extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer zoneType;
	private Integer warehouseId;
	private String country;
	private String zipFrom;
	private String zipTo;
	private String zoneCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getZoneType() {
		return zoneType;
	}
	public void setZoneType(Integer zoneType) {
		this.zoneType = zoneType;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
