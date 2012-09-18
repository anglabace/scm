package com.genscript.gsscm.shipment.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ShipCarriersDTO {
	private Integer carrierId;
	private String name;
	private String description;
	private String trackingUrl;
	private Date rateDate;
	private String CarrierCode;
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
	private Integer billid;
	private String billType;
	private String accountNo;
	private String accountPwd;
	private String phone;
	private String phoneExt;
	private String addrLine1;
	private String addrLine2;
	private String addrLine3;
	private String city;
	private String zipCode;
	private String state;
	private String country;
	private String billStatus;
	private String intlFlag;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy; 
	private String status;

	
	public Integer getBillid() {
		return billid;
	}
	public void setBillid(Integer billid) {
		this.billid = billid;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Integer getCarrierId() {
		return carrierId;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public String getIntlFlag() {
		return intlFlag;
	}
	public void setIntlFlag(String intlFlag) {
		this.intlFlag = intlFlag;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
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

	public String getCarrierCode() {
		return CarrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		CarrierCode = carrierCode;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneExt() {
		return phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getAddrLine3() {
		return addrLine3;
	}

	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
