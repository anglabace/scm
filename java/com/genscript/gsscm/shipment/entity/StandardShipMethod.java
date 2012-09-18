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
 * ORDER SHIP METHOD.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "standard_ship_method", catalog="shipping")
public class StandardShipMethod extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "method_id")
	private Integer methodId;
	private String methodCode;
	private String methodName;
	private Integer zoneType;
	private String carrier;
	private String trackUrl;
	public Integer getMethodId() {
		return methodId;
	}
	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Integer getZoneType() {
		return zoneType;
	}
	public void setZoneType(Integer zoneType) {
		this.zoneType = zoneType;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getTrackUrl() {
		return trackUrl;
	}
	public void setTrackUrl(String trackUrl) {
		this.trackUrl = trackUrl;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
