package com.genscript.gsscm.shipment.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.shipment.entity.ShipPackage;

/**
 * Applications entity
 * @author JLiu
 */
@SuppressWarnings("serial")
@XmlType(name = "ShipmentsDTO", namespace = WsConstants.NS)
public class ShipmentsDTO implements java.io.Serializable{
	
	//Fields 
	@Id
	@Column(name = "shipment_id", unique = true, nullable = false)
	private Integer shipmentId;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_id")
	private ShipPackage shipPackageses;
	
	private String shipmentNo;
	private String priority;
	private String shippingType;
	private String shippingRule;
	private String currency;
	private String description;
	private String shipDate;
	private ShipPackage packages;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")
	private Warehouse wareHouse;
	private Integer warehouseId;
	private Short companyId;
	private String status;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	// add property
	private String shippingClerk;
	private Integer shippingClerkId;
	private String shipTo;
	private String trackingNo;
	private String orderNo;
	private Double customerCharge;
	private String error;
	private String shipAmt;
	private String wname;
	// 2010-10-26增加的字段
	private Integer custNo;
	private String modifyName;
	private String orderType;
	private String itemNo;
	private String catalogNo;
	private String itemName;
	private String itemStatus;
	private String reservationId;
	private String orderItemQty;
	private String quantity;
	private String size;
	private Integer sort;
	private String packageId;
	private String packageNo;
	private String shipVia;
	private Integer shipMethodId;
	private String packageStatus;
	private Double billableWeight;
    private Double actualWeight;
	private String splQuantity;
	private String splSize;
	private String qtyUom;
	private String sizeUom;
	private String warehouseType;
	private String warehouseName;
	private String type;
	private List<ShipPackageLineDTO> listPackageLine;
	private Boolean flag;
	
	private String zone;
	private String deliveryType;
	
	private Integer pkgBatchSeq;
	private Integer pkgBatchCount;
	
	private String creationDateStr;
	private String modifyDateStr;
	/*
	 * isNew=1为新建；isNew=0否；
	 */
	private String isNew;

	private String shipToAddress;
	
	private String receiveTime;
    //add by zhanghuibin
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
    private String orderItemType;
    private String poNo;
    private String overDuo;
    private String orderStatus;
    private String greenAccFlag;
    private Double handingFee;
    private String usPoNo;
    
    private String serviceType;
    
    private String sendBys;
    
    private String receivingFlag;
	
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public List<ShipPackageLineDTO> getListPackageLine() {
		return listPackageLine;
	}

	public void setListPackageLine(List<ShipPackageLineDTO> listPackageLine) {
		this.listPackageLine = listPackageLine;
	}

	public String getPackageStatus() {
		return packageStatus;
	}

	public void setPackageStatus(String packageStatus) {
		this.packageStatus = packageStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getOrderItemQty() {
		return orderItemQty;
	}

	public void setOrderItemQty(String orderItemQty) {
		this.orderItemQty = orderItemQty;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Warehouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(Warehouse wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	//getter & setter
	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getShippingClerk() {
		return shippingClerk;
	}

	public void setShippingClerk(String shippingClerk) {
		this.shippingClerk = shippingClerk;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	// Constructors
	// Property accessors
	
	public String getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getShippingRule() {
		return shippingRule;
	}

	public void setShippingRule(String shippingRule) {
		this.shippingRule = shippingRule;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Short getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Short companyId) {
		this.companyId = companyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public Integer getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Warehouse getWarehouse() {
		return wareHouse;
	}

	public void setWarehouse(Warehouse wareHouse) {
		this.wareHouse = wareHouse;
	}

	public ShipPackage getShipPackageses() {
		return shipPackageses;
	}

	public void setShipPackageses(ShipPackage shipPackageses) {
		this.shipPackageses = shipPackageses;
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

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getCustomerCharge() {
		return customerCharge;
	}

	public void setCustomerCharge(Double customerCharge) {
		this.customerCharge = customerCharge;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public String getShipAmt() {
		return shipAmt;
	}

	public void setShipAmt(String shipAmt) {
		this.shipAmt = shipAmt;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	public String getShipVia() {
		return shipVia;
	}

	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}

	public Double getBillableWeight() {
		return billableWeight;
	}

	public void setBillableWeight(Double billableWeight) {
		this.billableWeight = billableWeight;
	}

	public String getSplQuantity() {
		return splQuantity;
	}

	public void setSplQuantity(String splQuantity) {
		this.splQuantity = splQuantity;
	}

	public String getSplSize() {
		return splSize;
	}

	public void setSplSize(String splSize) {
		this.splSize = splSize;
	}

	public String getQtyUom() {
		return qtyUom;
	}

	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}

	public String getSizeUom() {
		return sizeUom;
	}

	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public ShipPackage getPackages() {
		return packages;
	}

	public void setPackages(ShipPackage packages) {
		this.packages = packages;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Integer getShipMethodId() {
		return shipMethodId;
	}

	public void setShipMethodId(Integer shipMethodId) {
		this.shipMethodId = shipMethodId;
	}

	public Integer getShippingClerkId() {
		return shippingClerkId;
	}

	public void setShippingClerkId(Integer shippingClerkId) {
		this.shippingClerkId = shippingClerkId;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getCreationDateStr() {
		return creationDateStr;
	}

	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}

	public String getModifyDateStr() {
		return modifyDateStr;
	}

	public void setModifyDateStr(String modifyDateStr) {
		this.modifyDateStr = modifyDateStr;
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

    public String getRcpZipCode() {
        return rcpZipCode;
    }

    public void setRcpZipCode(String rcpZipCode) {
        this.rcpZipCode = rcpZipCode;
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

	public String getOrderItemType() {
		return orderItemType;
	}

	public void setOrderItemType(String orderItemType) {
		this.orderItemType = orderItemType;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getOverDuo() {
		return overDuo;
	}

	public void setOverDuo(String overDuo) {
		this.overDuo = overDuo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getGreenAccFlag() {
		return greenAccFlag;
	}

	public void setGreenAccFlag(String greenAccFlag) {
		this.greenAccFlag = greenAccFlag;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Double getHandingFee() {
		return handingFee;
	}

	public void setHandingFee(Double handingFee) {
		this.handingFee = handingFee;
	}

	public String getUsPoNo() {
		return usPoNo;
	}

	public void setUsPoNo(String usPoNo) {
		this.usPoNo = usPoNo;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getSendBys() {
		return sendBys;
	}

	public void setSendBys(String sendBys) {
		this.sendBys = sendBys;
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

	public String getReceivingFlag() {
		return receivingFlag;
	}

	public void setReceivingFlag(String receivingFlag) {
		this.receivingFlag = receivingFlag;
	}
    
    
}
