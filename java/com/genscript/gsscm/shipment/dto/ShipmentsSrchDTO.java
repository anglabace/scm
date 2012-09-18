package com.genscript.gsscm.shipment.dto;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.shipment.entity.ShipPackage;


@SuppressWarnings("serial")
@XmlType(name = "ShipmentsSrchDTO", namespace = WsConstants.NS)
public class ShipmentsSrchDTO implements java.io.Serializable {

	// Fields
	@Id
//	@Column(name = "shipment_id", unique = true, nullable = false)
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_id")
	private ShipPackage shipPackageses;
//	private Integer shipmentId;
	private String shipmentNo;
	private String shippingClerk;
	private String shipDate;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")
	private Warehouse wareHouse;
	private Integer warehouseId;
	private String status;
	// 2010-10-26增加的字段
	private Integer custNo;
	private String priority;
	private String type;
	private String orderStatus;
	private String shippingRule;
	private String poNo;
	private String sendBy;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	//add property
	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

//	public Integer getShipmentId() {
//		return shipmentId;
//	}
//
//	public void setShipmentId(Integer shipmentId) {
//		this.shipmentId = shipmentId;
//	}

	public String getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	public String getShippingClerk() {
		return shippingClerk;
	}

	public void setShippingClerk(String shippingClerk) {
		this.shippingClerk = shippingClerk;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	
	public Warehouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(Warehouse wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ShipPackage getShipPackageses() {
		return shipPackageses;
	}

	public void setShipPackageses(ShipPackage shipPackageses) {
		this.shipPackageses = shipPackageses;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getShippingRule() {
		return shippingRule;
	}

	public void setShippingRule(String shippingRule) {
		this.shippingRule = shippingRule;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getSendBy() {
		return sendBy;
	}

	public void setSendBy(String sendBy) {
		this.sendBy = sendBy;
	}

//	public ShipPackage getShipPackageses() {
//		return shipPackageses;
//	}
//
//	public void setShipPackageses(ShipPackage shipPackageses) {
//		this.shipPackageses = shipPackageses;
//	}

}