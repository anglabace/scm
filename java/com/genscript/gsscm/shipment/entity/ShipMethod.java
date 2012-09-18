package com.genscript.gsscm.shipment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * SHIP_METHOD.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "ship_method", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShipMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3339227207901902549L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "method_id")
	private Integer methodId;
	private String methodCode;
	private String name;
	private String carrier;
	private Integer standardZone;
	private Integer standardRate;
	private String description;
	private String shippingAcount;
	private String insuranceBase;
	private String printFlag;
	private String externalFlag;
	private String trackUrl;
	private Integer cmprMethod1;
	private Integer cmprMethod2;
	private String status;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the methodId
	 */
	public Integer getMethodId() {
		return methodId;
	}
	/**
	 * @param methodId the methodId to set
	 */
	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}
	/**
	 * @return the methodCode
	 */
	public String getMethodCode() {
		return methodCode;
	}
	/**
	 * @param methodCode the methodCode to set
	 */
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the carrier
	 */
	public String getCarrier() {
		return carrier;
	}
	/**
	 * @param carrier the carrier to set
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	/**
	 * @return the standardZone
	 */
	public Integer getStandardZone() {
		return standardZone;
	}
	/**
	 * @param standardZone the standardZone to set
	 */
	public void setStandardZone(Integer standardZone) {
		this.standardZone = standardZone;
	}
	/**
	 * @return the standardRate
	 */
	public Integer getStandardRate() {
		return standardRate;
	}
	/**
	 * @param standardRate the standardRate to set
	 */
	public void setStandardRate(Integer standardRate) {
		this.standardRate = standardRate;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the shippingAcount
	 */
	public String getShippingAcount() {
		return shippingAcount;
	}
	/**
	 * @param shippingAcount the shippingAcount to set
	 */
	public void setShippingAcount(String shippingAcount) {
		this.shippingAcount = shippingAcount;
	}
	/**
	 * @return the insuranceBase
	 */
	public String getInsuranceBase() {
		return insuranceBase;
	}
	/**
	 * @param insuranceBase the insuranceBase to set
	 */
	public void setInsuranceBase(String insuranceBase) {
		this.insuranceBase = insuranceBase;
	}
	/**
	 * @return the printFlag
	 */
	public String getPrintFlag() {
		return printFlag;
	}
	/**
	 * @param printFlag the printFlag to set
	 */
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	/**
	 * @return the externalFlag
	 */
	public String getExternalFlag() {
		return externalFlag;
	}
	/**
	 * @param externalFlag the externalFlag to set
	 */
	public void setExternalFlag(String externalFlag) {
		this.externalFlag = externalFlag;
	}
	/**
	 * @return the trackUrl
	 */
	public String getTrackUrl() {
		return trackUrl;
	}
	/**
	 * @param trackUrl the trackUrl to set
	 */
	public void setTrackUrl(String trackUrl) {
		this.trackUrl = trackUrl;
	}
	/**
	 * @return the cmprMethod1
	 */
	public Integer getCmprMethod1() {
		return cmprMethod1;
	}
	/**
	 * @param cmprMethod1 the cmprMethod1 to set
	 */
	public void setCmprMethod1(Integer cmprMethod1) {
		this.cmprMethod1 = cmprMethod1;
	}
	/**
	 * @return the cmprMethod2
	 */
	public Integer getCmprMethod2() {
		return cmprMethod2;
	}
	/**
	 * @param cmprMethod2 the cmprMethod2 to set
	 */
	public void setCmprMethod2(Integer cmprMethod2) {
		this.cmprMethod2 = cmprMethod2;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
