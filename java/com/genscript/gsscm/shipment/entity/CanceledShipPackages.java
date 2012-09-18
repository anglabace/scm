package com.genscript.gsscm.shipment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "canceled_ship_packages", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CanceledShipPackages {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private Integer id;
	 private Integer packageId;
	 private Integer canceledBy;
	 private String canceledReason;
	 private String trackingNo;
	 private Date canceledTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getCanceledBy() {
		return canceledBy;
	}
	public void setCanceledBy(Integer canceledBy) {
		this.canceledBy = canceledBy;
	}
	public String getCanceledReason() {
		return canceledReason;
	}
	public void setCanceledReason(String canceledReason) {
		this.canceledReason = canceledReason;
	}
	public Date getCanceledTime() {
		return canceledTime;
	}
	public void setCanceledTime(Date canceledTime) {
		this.canceledTime = canceledTime;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}


	 
	 
}
