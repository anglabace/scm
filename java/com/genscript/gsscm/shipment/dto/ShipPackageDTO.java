package com.genscript.gsscm.shipment.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.Shipment;

@XmlType(name = "ShipPackageDTO", namespace = WsConstants.NS)
public class ShipPackageDTO {
	private Integer packageId;
	private Integer orderNo;
//	private Integer shipAddrId;
	private Integer warehouseId;
	private String status;
	private Integer invoiceNo;
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
	private String note;
	private String shippingAccount;
	private String shiptoAddress;
	private Integer companyId;
	private String ciItemDesc;
	private String ciItemDescFromorder;
	private String ciInsuranceFromorder;
	private String ciItemOtherDesc;
	// 其它非model的属性:
	private Integer totalQty;
	private Double size;
	private String shipVia;
//	private OrderAddress shipToAddr;
	private String warehouseName;
	private List<ShipPackageItemDTO> packageItemList;
	// add by Jarvinia
	private String shippingClerk;
	private String error;
	private String wname;
	private String desc;
	private String orderType;
	private String priority;
	private String shipTo;
	private String wareHouseType;
	private Date creationDate;
	private Date modifyDate;
	private String modifiedBy;
	private Integer packageNo;
	
	private Integer quty;
	private Double unitvalue; 
	private String shipmentDateStr; 
	
	private Shipment shipments;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ship_method")
	private ShipMethod sm;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")
	private Warehouse wareHouse;
    private String rcpCountry;
    private String carrier;
    private Double totalInvoiceValue;
    private String sendBy;
    private String isPrintShippingLabel;

    public String getRcpCountry() {
        return rcpCountry;
    }

    public void setRcpCountry(String rcpCountry) {
        this.rcpCountry = rcpCountry;
    }

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    public List<ShipPackageItemDTO> getPackageItemList() {
		return packageItemList;
	}

	public void setPackageItemList(List<ShipPackageItemDTO> packageItemList) {
		this.packageItemList = packageItemList;
	}

	/**
	 * @return the packageId
	 */
	public Integer getPackageId() {
		return packageId;
	}

	/**
	 * @param packageId
	 *            the packageId to set
	 */
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo
	 *            the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * @return the warehouseId
	 */
	public Integer getWarehouseId() {
		return warehouseId;
	}

	/**
	 * @param warehouseId
	 *            the warehouseId to set
	 */
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
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
	 * @param invoiceNo
	 *            the invoiceNo to set
	 */
	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the deliveryType
	 */
	public String getDeliveryType() {
		return deliveryType;
	}

	/**
	 * @param deliveryType
	 *            the deliveryType to set
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
	 * @param shipmentDate
	 *            the shipmentDate to set
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
	 * @param trackingNo
	 *            the trackingNo to set
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
	 * @param actualWeight
	 *            the actualWeight to set
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
	 * @param billableWeight
	 *            the billableWeight to set
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
	 * @param zone
	 *            the zone to set
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
	 * @param pkgBatchSeq
	 *            the pkgBatchSeq to set
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
	 * @param pkgBatchCount
	 *            the pkgBatchCount to set
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
	 * @param packer
	 *            the packer to set
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
	 * @param insuranceValue
	 *            the insuranceValue to set
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
	 * @param shipMethod
	 *            the shipMethod to set
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
	 * @param additionalHandle
	 *            the additionalHandle to set
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
	 * @param deliveryConfirm
	 *            the deliveryConfirm to set
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
	 * @param hazardousMtl
	 *            the hazardousMtl to set
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
	 * @param saturdayPickup
	 *            the saturdayPickup to set
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
	 * @param noteOnShip
	 *            the noteOnShip to set
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
	 * @param noteOnDelivery
	 *            the noteOnDelivery to set
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
	 * @param noteOnExcp
	 *            the noteOnExcp to set
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
	 * @param packageType
	 *            the packageType to set
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
	 * @param length
	 *            the length to set
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
	 * @param width
	 *            the width to set
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
	 * @param height
	 *            the height to set
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
	 * @param dimUom
	 *            the dimUom to set
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
	 * @param baseCharge
	 *            the baseCharge to set
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
	 * @param deliveryConfirmFee
	 *            the deliveryConfirmFee to set
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
	 * @param packagingFee
	 *            the packagingFee to set
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
	 * @param actlCarrCharge
	 *            the actlCarrCharge to set
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
	 * @param insuranceCharge
	 *            the insuranceCharge to set
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
	 * @param adtlCustomerCharge
	 *            the adtlCustomerCharge to set
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
	 * @param carrierCharge
	 *            the carrierCharge to set
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
	 * @param customerCharge
	 *            the customerCharge to set
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
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the shippingAccount
	 */
	public String getShippingAccount() {
		return shippingAccount;
	}

	/**
	 * @param shippingAccount
	 *            the shippingAccount to set
	 */
	public void setShippingAccount(String shippingAccount) {
		this.shippingAccount = shippingAccount;
	}

	/**
	 * @return the totalQty
	 */
	public Integer getTotalQty() {
		return totalQty;
	}

	/**
	 * @param totalQty
	 *            the totalQty to set
	 */
	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	/**
	 * @return the size
	 */
	public Double getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Double size) {
		this.size = size;
	}

	/**
	 * @return the shipVia
	 */
	public String getShipVia() {
		return shipVia;
	}

	/**
	 * @param shipVia
	 *            the shipVia to set
	 */
	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}

	/**
	 * @return the warehouseName
	 */
	public String getWarehouseName() {
		return warehouseName;
	}

	/**
	 * @param warehouseName
	 *            the warehouseName to set
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getShiptoAddress() {
		return shiptoAddress;
	}

	public void setShiptoAddress(String shiptoAddress) {
		this.shiptoAddress = shiptoAddress;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getShippingClerk() {
		return shippingClerk;
	}

	public void setShippingClerk(String shippingClerk) {
		this.shippingClerk = shippingClerk;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public String getWareHouseType() {
		return wareHouseType;
	}

	public void setWareHouseType(String wareHouseType) {
		this.wareHouseType = wareHouseType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

	public Integer getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(Integer packageNo) {
		this.packageNo = packageNo;
	}

	public ShipMethod getSm() {
		return sm;
	}

	public void setSm(ShipMethod sm) {
		this.sm = sm;
	}

	public Warehouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(Warehouse wareHouse) {
		this.wareHouse = wareHouse;
	}

	public Integer getQuty() {
		return quty;
	}

	public void setQuty(Integer quty) {
		this.quty = quty;
	}



	public Double getUnitvalue() {
		return unitvalue;
	}

	public void setUnitvalue(Double unitvalue) {
		this.unitvalue = unitvalue;
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

	public Shipment getShipments() {
		return shipments;
	}

	public void setShipments(Shipment shipments) {
		this.shipments = shipments;
	}

	public String getShipmentDateStr() {
		return shipmentDateStr;
	}

	public void setShipmentDateStr(String shipmentDateStr) {
		this.shipmentDateStr = shipmentDateStr;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCiItemOtherDesc() {
		return ciItemOtherDesc;
	}

	public void setCiItemOtherDesc(String ciItemOtherDesc) {
		this.ciItemOtherDesc = ciItemOtherDesc;
	}

	public Double getTotalInvoiceValue() {
		return totalInvoiceValue;
	}

	public void setTotalInvoiceValue(Double totalInvoiceValue) {
		this.totalInvoiceValue = totalInvoiceValue;
	}

	public String getSendBy() {
		return sendBy;
	}

	public void setSendBy(String sendBy) {
		this.sendBy = sendBy;
	}

	public String getIsPrintShippingLabel() {
		return isPrintShippingLabel;
	}

	public void setIsPrintShippingLabel(String isPrintShippingLabel) {
		this.isPrintShippingLabel = isPrintShippingLabel;
	}

	
	
}
