package com.genscript.gsscm.quote.entity;

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
 * QUOTATION SHIP INFO.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "quote_packages", catalog="order")
public class QuotePackage extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "package_id")
	private Integer packageId;
	private Integer quoteNo;
	private Integer shipAddrId;
	private String status;
	private Integer invoiceNo;
	private Integer warehouseId;
	private String deliveryType;
	private Date shipmentDate;
	private String trackingNo;
	private Double actualWeight;
	private Double billableWeight;
	private String zone;
	private Integer pkgBatchSeq;
	private Integer pkgBatchCount;
	private String packer;
	private Double insuranceValue;
	private Integer shipMethod;
	private String additionalHandle;
	private String deliveryConfirm;
	private String hazardousMtl;
	private String saturdayPickup;
	private String noteOnShip;
	private String noteOnDelivery;
	private String noteOnExcp;
	private String packageType;
	private Double length;
	private Double width;
	private Double height;
	private String dimUom;
	private Double baseCharge;
	private Double deliveryConfirmFee;
	private Double packagingFee;
	private Double actlCarrCharge;
	private Double insuranceCharge;
	private Double adtlCustomerCharge;
	private Double carrierCharge;
	private Double customerCharge;
	private Double handlingFee;
	private String note;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the packageId
	 */
	public Integer getPackageId() {
		return packageId;
	}
	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	/**
	 * @return the quoteNo
	 */
	public Integer getQuoteNo() {
		return quoteNo;
	}
	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	/**
	 * @return the shipAddrId
	 */
	public Integer getShipAddrId() {
		return shipAddrId;
	}
	/**
	 * @param shipAddrId the shipAddrId to set
	 */
	public void setShipAddrId(Integer shipAddrId) {
		this.shipAddrId = shipAddrId;
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
	 * @return the invoiceNo
	 */
	public Integer getInvoiceNo() {
		return invoiceNo;
	}
	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
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
	 * @return the deliveryType
	 */
	public String getDeliveryType() {
		return deliveryType;
	}
	/**
	 * @param deliveryType the deliveryType to set
	 */
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	/**
	 * @return the shipmentDate
	 */
	public Date getShipmentDate() {
		return shipmentDate;
	}
	/**
	 * @param shipmentDate the shipmentDate to set
	 */
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	/**
	 * @return the trackingNo
	 */
	public String getTrackingNo() {
		return trackingNo;
	}
	/**
	 * @param trackingNo the trackingNo to set
	 */
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	/**
	 * @return the actualWeight
	 */
	public Double getActualWeight() {
		return actualWeight;
	}
	/**
	 * @param actualWeight the actualWeight to set
	 */
	public void setActualWeight(Double actualWeight) {
		this.actualWeight = actualWeight;
	}
	/**
	 * @return the billableWeight
	 */
	public Double getBillableWeight() {
		return billableWeight;
	}
	/**
	 * @param billableWeight the billableWeight to set
	 */
	public void setBillableWeight(Double billableWeight) {
		this.billableWeight = billableWeight;
	}
	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}
	/**
	 * @return the pkgBatchSeq
	 */
	public Integer getPkgBatchSeq() {
		return pkgBatchSeq;
	}
	/**
	 * @param pkgBatchSeq the pkgBatchSeq to set
	 */
	public void setPkgBatchSeq(Integer pkgBatchSeq) {
		this.pkgBatchSeq = pkgBatchSeq;
	}
	/**
	 * @return the pkgBatchCount
	 */
	public Integer getPkgBatchCount() {
		return pkgBatchCount;
	}
	/**
	 * @param pkgBatchCount the pkgBatchCount to set
	 */
	public void setPkgBatchCount(Integer pkgBatchCount) {
		this.pkgBatchCount = pkgBatchCount;
	}
	/**
	 * @return the packer
	 */
	public String getPacker() {
		return packer;
	}
	/**
	 * @param packer the packer to set
	 */
	public void setPacker(String packer) {
		this.packer = packer;
	}
	/**
	 * @return the insuranceValue
	 */
	public Double getInsuranceValue() {
		return insuranceValue;
	}
	/**
	 * @param insuranceValue the insuranceValue to set
	 */
	public void setInsuranceValue(Double insuranceValue) {
		this.insuranceValue = insuranceValue;
	}
	/**
	 * @return the shipMethod
	 */
	public Integer getShipMethod() {
		return shipMethod;
	}
	/**
	 * @param shipMethod the shipMethod to set
	 */
	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}
	/**
	 * @return the additionalHandle
	 */
	public String getAdditionalHandle() {
		return additionalHandle;
	}
	/**
	 * @param additionalHandle the additionalHandle to set
	 */
	public void setAdditionalHandle(String additionalHandle) {
		this.additionalHandle = additionalHandle;
	}
	/**
	 * @return the deliveryConfirm
	 */
	public String getDeliveryConfirm() {
		return deliveryConfirm;
	}
	/**
	 * @param deliveryConfirm the deliveryConfirm to set
	 */
	public void setDeliveryConfirm(String deliveryConfirm) {
		this.deliveryConfirm = deliveryConfirm;
	}
	/**
	 * @return the hazardousMtl
	 */
	public String getHazardousMtl() {
		return hazardousMtl;
	}
	/**
	 * @param hazardousMtl the hazardousMtl to set
	 */
	public void setHazardousMtl(String hazardousMtl) {
		this.hazardousMtl = hazardousMtl;
	}
	/**
	 * @return the saturdayPickup
	 */
	public String getSaturdayPickup() {
		return saturdayPickup;
	}
	/**
	 * @param saturdayPickup the saturdayPickup to set
	 */
	public void setSaturdayPickup(String saturdayPickup) {
		this.saturdayPickup = saturdayPickup;
	}
	/**
	 * @return the noteOnShip
	 */
	public String getNoteOnShip() {
		return noteOnShip;
	}
	/**
	 * @param noteOnShip the noteOnShip to set
	 */
	public void setNoteOnShip(String noteOnShip) {
		this.noteOnShip = noteOnShip;
	}
	/**
	 * @return the noteOnDelivery
	 */
	public String getNoteOnDelivery() {
		return noteOnDelivery;
	}
	/**
	 * @param noteOnDelivery the noteOnDelivery to set
	 */
	public void setNoteOnDelivery(String noteOnDelivery) {
		this.noteOnDelivery = noteOnDelivery;
	}
	/**
	 * @return the noteOnExcp
	 */
	public String getNoteOnExcp() {
		return noteOnExcp;
	}
	/**
	 * @param noteOnExcp the noteOnExcp to set
	 */
	public void setNoteOnExcp(String noteOnExcp) {
		this.noteOnExcp = noteOnExcp;
	}
	/**
	 * @return the packageType
	 */
	public String getPackageType() {
		return packageType;
	}
	/**
	 * @param packageType the packageType to set
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	/**
	 * @return the length
	 */
	public Double getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Double length) {
		this.length = length;
	}
	/**
	 * @return the width
	 */
	public Double getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(Double width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * @return the dimUom
	 */
	public String getDimUom() {
		return dimUom;
	}
	/**
	 * @param dimUom the dimUom to set
	 */
	public void setDimUom(String dimUom) {
		this.dimUom = dimUom;
	}
	/**
	 * @return the baseCharge
	 */
	public Double getBaseCharge() {
		return baseCharge;
	}
	/**
	 * @param baseCharge the baseCharge to set
	 */
	public void setBaseCharge(Double baseCharge) {
		this.baseCharge = baseCharge;
	}
	/**
	 * @return the deliveryConfirmFee
	 */
	public Double getDeliveryConfirmFee() {
		return deliveryConfirmFee;
	}
	/**
	 * @param deliveryConfirmFee the deliveryConfirmFee to set
	 */
	public void setDeliveryConfirmFee(Double deliveryConfirmFee) {
		this.deliveryConfirmFee = deliveryConfirmFee;
	}
	/**
	 * @return the packagingFee
	 */
	public Double getPackagingFee() {
		return packagingFee;
	}
	/**
	 * @param packagingFee the packagingFee to set
	 */
	public void setPackagingFee(Double packagingFee) {
		this.packagingFee = packagingFee;
	}
	/**
	 * @return the actlCarrCharge
	 */
	public Double getActlCarrCharge() {
		return actlCarrCharge;
	}
	/**
	 * @param actlCarrCharge the actlCarrCharge to set
	 */
	public void setActlCarrCharge(Double actlCarrCharge) {
		this.actlCarrCharge = actlCarrCharge;
	}
	/**
	 * @return the insuranceCharge
	 */
	public Double getInsuranceCharge() {
		return insuranceCharge;
	}
	/**
	 * @param insuranceCharge the insuranceCharge to set
	 */
	public void setInsuranceCharge(Double insuranceCharge) {
		this.insuranceCharge = insuranceCharge;
	}
	/**
	 * @return the adtlCustomerCharge
	 */
	public Double getAdtlCustomerCharge() {
		return adtlCustomerCharge;
	}
	/**
	 * @param adtlCustomerCharge the adtlCustomerCharge to set
	 */
	public void setAdtlCustomerCharge(Double adtlCustomerCharge) {
		this.adtlCustomerCharge = adtlCustomerCharge;
	}
	/**
	 * @return the carrierCharge
	 */
	public Double getCarrierCharge() {
		return carrierCharge;
	}
	/**
	 * @param carrierCharge the carrierCharge to set
	 */
	public void setCarrierCharge(Double carrierCharge) {
		this.carrierCharge = carrierCharge;
	}
	/**
	 * @return the customerCharge
	 */
	public Double getCustomerCharge() {
		return customerCharge;
	}
	/**
	 * @param customerCharge the customerCharge to set
	 */
	public void setCustomerCharge(Double customerCharge) {
		this.customerCharge = customerCharge;
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
	public Double getHandlingFee() {
		return handlingFee;
	}
	public void setHandlingFee(Double handlingFee) {
		this.handlingFee = handlingFee;
	}
}
