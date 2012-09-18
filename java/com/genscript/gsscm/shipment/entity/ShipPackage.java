package com.genscript.gsscm.shipment.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ORDER SHIP INFO.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "ship_packages", catalog = "shipping")
public class ShipPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "package_id")
	private Integer packageId;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_id")
	private Shipment shipments;
	private String shiptoAddress;
	private Integer warehouseId;
	private Integer shippingClerk;
	private String status;
	private Integer invoiceNo;
	private String deliveryType;
	private Date shipmentDate;
	private Date shippedTime;
	private String trackingNo;
	private Double actualWeight;
	private Double billableWeight;
	private String zone;
	private Integer pkgBatchSeq;
	private Integer pkgBatchCount;
	private String packer;
	private BigDecimal insuranceValue;
	private Integer shipMethod;
	private String additionalHandle;
	private String deliveryConfirm;
	private String hazardousMtl;
	private String saturdayPickup;
	private String noteOnShip;
	private String noteOnDelivery;
	private String noteOnExcp;
	private String packageType;
	private Double containerWeight;
	private Double length;
	private Double width;
	private Double height;
	private String dimUom;
	private BigDecimal baseCharge;
	private BigDecimal deliveryConfirmFee;
	private BigDecimal packagingFee;
	private BigDecimal actlCarrCharge;
	private BigDecimal insuranceCharge;
	private BigDecimal adtlCustomerCharge;
	private BigDecimal carrierCharge;
	private BigDecimal customerCharge;
	private BigDecimal handingFee;
	private String shippingAccount;
	private String note;
	private Integer companyId;
	private String invoicedFlag;
	private String rcpFirstName;
	private String rcpMidName;
	private String rcpLastName;
	private String rcpOrgName;
	private String rcpCity;
	private String rcpState;
	private String rcpCountry;
	private String rcpPhone;
	private String rcpMobile;
	private String rcpZipCode;
	private String rcpBusEmail;
	private String rcpFax;
	private String rcpTitle;
	private String rcpAddrClass;
	private String rcpAddrLine1;
	private String rcpAddrLine2;
	private String rcpAddrLine3;
	private String ciItemDesc;
	private String ciItemOtherDesc;
	private String ciItemDescFromorder;
	private String ciInsuranceFromorder;
	private Date creationDate;
	private String createdBy;
	private Date modifyDate;
	private String modifiedBy;
	private String packageNo;
	private Integer sendBy;
	private String shipinfoSentFlag;
	@Transient
	private String orderNos;
    @Transient
    private String flag;
    @Transient
	private String itemAmt;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Shipment getShipments() {
		return shipments;
	}

	public void setShipments(Shipment shipments) {
		this.shipments = shipments;
	}

	public String getShiptoAddress() {
		return shiptoAddress;
	}

	public void setShiptoAddress(String shiptoAddress) {
		this.shiptoAddress = shiptoAddress;
	}

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

	public Integer getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
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

	public Double getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(Double actualWeight) {
		this.actualWeight = actualWeight;
	}

	public Double getBillableWeight() {
		return billableWeight;
	}

	public void setBillableWeight(Double billableWeight) {
		this.billableWeight = billableWeight;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Integer getPkgBatchSeq() {
		return pkgBatchSeq;
	}

	public void setPkgBatchSeq(Integer pkgBatchSeq) {
		this.pkgBatchSeq = pkgBatchSeq;
	}

	public Integer getPkgBatchCount() {
		return pkgBatchCount;
	}

	public void setPkgBatchCount(Integer pkgBatchCount) {
		this.pkgBatchCount = pkgBatchCount;
	}

	public String getPacker() {
		return packer;
	}

	public void setPacker(String packer) {
		this.packer = packer;
	}


	public Integer getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}

	public String getAdditionalHandle() {
		return additionalHandle;
	}

	public void setAdditionalHandle(String additionalHandle) {
		this.additionalHandle = additionalHandle;
	}

	public String getDeliveryConfirm() {
		return deliveryConfirm;
	}

	public void setDeliveryConfirm(String deliveryConfirm) {
		this.deliveryConfirm = deliveryConfirm;
	}

	public String getHazardousMtl() {
		return hazardousMtl;
	}

	public void setHazardousMtl(String hazardousMtl) {
		this.hazardousMtl = hazardousMtl;
	}

	public String getSaturdayPickup() {
		return saturdayPickup;
	}

	public void setSaturdayPickup(String saturdayPickup) {
		this.saturdayPickup = saturdayPickup;
	}

	public String getNoteOnShip() {
		return noteOnShip;
	}

	public void setNoteOnShip(String noteOnShip) {
		this.noteOnShip = noteOnShip;
	}

	public String getNoteOnDelivery() {
		return noteOnDelivery;
	}

	public void setNoteOnDelivery(String noteOnDelivery) {
		this.noteOnDelivery = noteOnDelivery;
	}

	public String getNoteOnExcp() {
		return noteOnExcp;
	}

	public void setNoteOnExcp(String noteOnExcp) {
		this.noteOnExcp = noteOnExcp;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getDimUom() {
		return dimUom;
	}

	public void setDimUom(String dimUom) {
		this.dimUom = dimUom;
	}

	

	public BigDecimal getInsuranceValue() {
		return insuranceValue;
	}

	public void setInsuranceValue(BigDecimal insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

	public BigDecimal getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(BigDecimal baseCharge) {
		this.baseCharge = baseCharge;
	}

	public BigDecimal getDeliveryConfirmFee() {
		return deliveryConfirmFee;
	}

	public void setDeliveryConfirmFee(BigDecimal deliveryConfirmFee) {
		this.deliveryConfirmFee = deliveryConfirmFee;
	}

	public BigDecimal getPackagingFee() {
		return packagingFee;
	}

	public void setPackagingFee(BigDecimal packagingFee) {
		this.packagingFee = packagingFee;
	}

	public BigDecimal getActlCarrCharge() {
		return actlCarrCharge;
	}

	public void setActlCarrCharge(BigDecimal actlCarrCharge) {
		this.actlCarrCharge = actlCarrCharge;
	}

	public BigDecimal getInsuranceCharge() {
		return insuranceCharge;
	}

	public void setInsuranceCharge(BigDecimal insuranceCharge) {
		this.insuranceCharge = insuranceCharge;
	}

	public BigDecimal getAdtlCustomerCharge() {
		return adtlCustomerCharge;
	}

	public void setAdtlCustomerCharge(BigDecimal adtlCustomerCharge) {
		this.adtlCustomerCharge = adtlCustomerCharge;
	}

	public BigDecimal getCarrierCharge() {
		return carrierCharge;
	}

	public void setCarrierCharge(BigDecimal carrierCharge) {
		this.carrierCharge = carrierCharge;
	}

	public BigDecimal getCustomerCharge() {
		return customerCharge;
	}

	public void setCustomerCharge(BigDecimal customerCharge) {
		this.customerCharge = customerCharge;
	}

	public String getShippingAccount() {
		return shippingAccount;
	}

	public void setShippingAccount(String shippingAccount) {
		this.shippingAccount = shippingAccount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getInvoicedFlag() {
		return invoicedFlag;
	}

	public void setInvoicedFlag(String invoicedFlag) {
		this.invoicedFlag = invoicedFlag;
	}

	public String getRcpFirstName() {
		return rcpFirstName;
	}

	public void setRcpFirstName(String rcpFirstName) {
		this.rcpFirstName = rcpFirstName;
	}

	public String getRcpMidName() {
		return rcpMidName;
	}

	public void setRcpMidName(String rcpMidName) {
		this.rcpMidName = rcpMidName;
	}

	public String getRcpLastName() {
		return rcpLastName;
	}

	public void setRcpLastName(String rcpLastName) {
		this.rcpLastName = rcpLastName;
	}

	public String getRcpOrgName() {
		return rcpOrgName;
	}

	public void setRcpOrgName(String rcpOrgName) {
		this.rcpOrgName = rcpOrgName;
	}

	public String getRcpCity() {
		return rcpCity;
	}

	public void setRcpCity(String rcpCity) {
		this.rcpCity = rcpCity;
	}

	public String getRcpState() {
		return rcpState;
	}

	public void setRcpState(String rcpState) {
		this.rcpState = rcpState;
	}

	public String getRcpCountry() {
		return rcpCountry;
	}

	public void setRcpCountry(String rcpCountry) {
		this.rcpCountry = rcpCountry;
	}

	public String getRcpPhone() {
		return rcpPhone;
	}

	public void setRcpPhone(String rcpPhone) {
		this.rcpPhone = rcpPhone;
	}

	public String getRcpMobile() {
		return rcpMobile;
	}

	public void setRcpMobile(String rcpMobile) {
		this.rcpMobile = rcpMobile;
	}

	public String getRcpZipCode() {
		return rcpZipCode;
	}

	public void setRcpZipCode(String rcpZipCode) {
		this.rcpZipCode = rcpZipCode;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCiItemDesc() {
		return ciItemDesc;
	}

	public void setCiItemDesc(String ciItemDesc) {
		this.ciItemDesc = ciItemDesc;
	}


	public String getCiItemDescFromorder() {
		return ciItemDescFromorder;
	}

	public void setCiItemDescFromorder(String ciItemDescFromorder) {
		this.ciItemDescFromorder = ciItemDescFromorder;
	}

	public String getCiInsuranceFromorder() {
		return ciInsuranceFromorder;
	}

	public void setCiInsuranceFromorder(String ciInsuranceFromorder) {
		this.ciInsuranceFromorder = ciInsuranceFromorder;
	}

	public String getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
	}

	public BigDecimal getHandingFee() {
		return handingFee;
	}

	public void setHandingFee(BigDecimal handingFee) {
		this.handingFee = handingFee;
	}

	public Double getContainerWeight() {
		return containerWeight;
	}

	public void setContainerWeight(Double containerWeight) {
		this.containerWeight = containerWeight;
	}

	public String getShipinfoSentFlag() {
		return shipinfoSentFlag;
	}

	public void setShipinfoSentFlag(String shipinfoSentFlag) {
		this.shipinfoSentFlag = shipinfoSentFlag;
	}

	public String getCiItemOtherDesc() {
		return ciItemOtherDesc;
	}

	public void setCiItemOtherDesc(String ciItemOtherDesc) {
		this.ciItemOtherDesc = ciItemOtherDesc;
	}

	public String getItemAmt() {
		return itemAmt;
	}

	public void setItemAmt(String itemAmt) {
		this.itemAmt = itemAmt;
	}

	public Integer getSendBy() {
		return sendBy;
	}

	public void setSendBy(Integer sendBy) {
		this.sendBy = sendBy;
	}

	public Date getShippedTime() {
		return shippedTime;
	}

	public void setShippedTime(Date shippedTime) {
		this.shippedTime = shippedTime;
	}

	public String getRcpBusEmail() {
		return rcpBusEmail;
	}

	public void setRcpBusEmail(String rcpBusEmail) {
		this.rcpBusEmail = rcpBusEmail;
	}

	public String getRcpFax() {
		return rcpFax;
	}

	public void setRcpFax(String rcpFax) {
		this.rcpFax = rcpFax;
	}



	public String getRcpTitle() {
		return rcpTitle;
	}

	public void setRcpTitle(String rcpTitle) {
		this.rcpTitle = rcpTitle;
	}

	public String getRcpAddrClass() {
		return rcpAddrClass;
	}

	public void setRcpAddrClass(String rcpAddrClass) {
		this.rcpAddrClass = rcpAddrClass;
	}

	public String getRcpAddrLine1() {
		return rcpAddrLine1;
	}

	public void setRcpAddrLine1(String rcpAddrLine1) {
		this.rcpAddrLine1 = rcpAddrLine1;
	}

	public String getRcpAddrLine2() {
		return rcpAddrLine2;
	}

	public void setRcpAddrLine2(String rcpAddrLine2) {
		this.rcpAddrLine2 = rcpAddrLine2;
	}

	public String getRcpAddrLine3() {
		return rcpAddrLine3;
	}

	public void setRcpAddrLine3(String rcpAddrLine3) {
		this.rcpAddrLine3 = rcpAddrLine3;
	}
	
	
}
