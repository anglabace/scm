package com.genscript.gsscm.shipment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ShipZone.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "ship_zone", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShipZone extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer shipMethodId;
	private Integer warehouseId;
	private String country;
	private String zipFrom;
	private String zipTo;
	private String zoneCode;
	private String note;
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
	 * @return the shipMethodId
	 */
	public Integer getShipMethodId() {
		return shipMethodId;
	}
	/**
	 * @param shipMethodId the shipMethodId to set
	 */
	public void setShipMethodId(Integer shipMethodId) {
		this.shipMethodId = shipMethodId;
	}
	/**
	 * @return the warehouseId
	 */
	public Integer getWarehouseId() {
		return warehouseId;
	}
	/**
	 * @param warehouseId the warehouseId to set
	 */
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the zipFrom
	 */
	public String getZipFrom() {
		return zipFrom;
	}
	/**
	 * @param zipFrom the zipFrom to set
	 */
	public void setZipFrom(String zipFrom) {
		this.zipFrom = zipFrom;
	}
	/**
	 * @return the zipTo
	 */
	public String getZipTo() {
		return zipTo;
	}
	/**
	 * @param zipTo the zipTo to set
	 */
	public void setZipTo(String zipTo) {
		this.zipTo = zipTo;
	}
	/**
	 * @return the zoneCode
	 */
	public String getZoneCode() {
		return zoneCode;
	}
	/**
	 * @param zoneCode the zoneCode to set
	 */
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
