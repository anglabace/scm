package com.genscript.gsscm.shipment.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ORDER SHIP CARRIERS.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "ship_carriers", catalog = "shipping")
public class ShipCarriers extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carrier_id")
	private Integer carrierId;
	private String name; 
	private String description; 
	private String trackingUrl;
	private Date rateDate;
	private String carrierCode;
	private BigDecimal insurance;
	private BigDecimal additionalHandling;
	private BigDecimal hazardousMaterial;
	private BigDecimal packageSurcharge;
	private BigDecimal residentialSurcharge;
	private BigDecimal commercialSurcharge;
	private BigDecimal intlInsurance;
	private BigDecimal intlAdditionalHandling;
	private BigDecimal intlHazardousMaterial;
	private BigDecimal intlPackageSurcharge;
	private BigDecimal intlResidentialSurcharge;
	private BigDecimal intlCommercialSurcharge;
	private String intlFlag;//intl_flag字段存储的是international tab 中三个 
//	复选框是否选中，格式可以为“1,1,1” 或“1;1;1”
	private String status;

	
	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public BigDecimal getInsurance() {
		return insurance;
	}

	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}

	public BigDecimal getAdditionalHandling() {
		return additionalHandling;
	}

	public void setAdditionalHandling(BigDecimal additionalHandling) {
		this.additionalHandling = additionalHandling;
	}

	public BigDecimal getHazardousMaterial() {
		return hazardousMaterial;
	}

	public void setHazardousMaterial(BigDecimal hazardousMaterial) {
		this.hazardousMaterial = hazardousMaterial;
	}

	public BigDecimal getPackageSurcharge() {
		return packageSurcharge;
	}

	public void setPackageSurcharge(BigDecimal packageSurcharge) {
		this.packageSurcharge = packageSurcharge;
	}

	public BigDecimal getResidentialSurcharge() {
		return residentialSurcharge;
	}

	public void setResidentialSurcharge(BigDecimal residentialSurcharge) {
		this.residentialSurcharge = residentialSurcharge;
	}

	public BigDecimal getCommercialSurcharge() {
		return commercialSurcharge;
	}

	public void setCommercialSurcharge(BigDecimal commercialSurcharge) {
		this.commercialSurcharge = commercialSurcharge;
	}

	public BigDecimal getIntlInsurance() {
		return intlInsurance;
	}

	public void setIntlInsurance(BigDecimal intlInsurance) {
		this.intlInsurance = intlInsurance;
	}

	public BigDecimal getIntlAdditionalHandling() {
		return intlAdditionalHandling;
	}

	public void setIntlAdditionalHandling(BigDecimal intlAdditionalHandling) {
		this.intlAdditionalHandling = intlAdditionalHandling;
	}

	public BigDecimal getIntlHazardousMaterial() {
		return intlHazardousMaterial;
	}

	public void setIntlHazardousMaterial(BigDecimal intlHazardousMaterial) {
		this.intlHazardousMaterial = intlHazardousMaterial;
	}

	public BigDecimal getIntlPackageSurcharge() {
		return intlPackageSurcharge;
	}

	public void setIntlPackageSurcharge(BigDecimal intlPackageSurcharge) {
		this.intlPackageSurcharge = intlPackageSurcharge;
	}

	public BigDecimal getIntlResidentialSurcharge() {
		return intlResidentialSurcharge;
	}

	public void setIntlResidentialSurcharge(BigDecimal intlResidentialSurcharge) {
		this.intlResidentialSurcharge = intlResidentialSurcharge;
	}

	public BigDecimal getIntlCommercialSurcharge() {
		return intlCommercialSurcharge;
	}

	public void setIntlCommercialSurcharge(BigDecimal intlCommercialSurcharge) {
		this.intlCommercialSurcharge = intlCommercialSurcharge;
	}

	public String getIntlFlag() {
		return intlFlag;
	}

	public void setIntlFlag(String intlFlag) {
		this.intlFlag = intlFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrackingUrl() {
		return trackingUrl;
	}

	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
