package com.genscript.gsscm.shipment.entity;

import java.math.BigDecimal;

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
@Table(name = "ship_rate", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShipRate extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3339227207901902549L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer shipMethodId;
	private Integer warehouseId;
	private String zoneCode;
	private Double weightFrom;
	private Double weightTo;
	private BigDecimal charge;
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
	 * @return the weightFrom
	 */
	public Double getWeightFrom() {
		return weightFrom;
	}
	/**
	 * @param weightFrom the weightFrom to set
	 */
	public void setWeightFrom(Double weightFrom) {
		this.weightFrom = weightFrom;
	}
	/**
	 * @return the weightTo
	 */
	public Double getWeightTo() {
		return weightTo;
	}
	/**
	 * @param weightTo the weightTo to set
	 */
	public void setWeightTo(Double weightTo) {
		this.weightTo = weightTo;
	}
	/**
	 * @return the charge
	 */
	public BigDecimal getCharge() {
		return charge;
	}
	/**
	 * @param charge the charge to set
	 */
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
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
