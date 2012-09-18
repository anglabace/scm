package com.genscript.gsscm.shipment.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.shipment.entity.Shipment;

/**
 * ShipmentLines entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "shipment_lines", catalog = "shipping")
public class ShipmentLinesDTO implements java.io.Serializable {

	// Fields

	@Id
	@Column(name = "line_id", unique = true, nullable = false)
	private Integer lineId;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_id")
	private Shipment shipments;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_no")
	private OrderMain order;
	
	@Column(name = "item_no")
	private Integer itemNo;
//	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
//	@JoinColumn(name = "item_no")
//	private OrderItems orderItems;
	
	@Column(name = "status", length = 20)
	private String status;
	
//	// add property
//	private String name;
//	private Integer quantity;
//	private Double size;
//	private Integer sumquantity;
//	private Double sumsize;
	private String itemName;
	private String orderQty;
	private String orderSize;
	private Integer shippedQty;
	private Double shippedSize;
	private String qtyUom;
	private String sizeUom;
	private String modifiedName;
	private Date modifyDate;
	private Date creationDate;
	private String description;
	
	private Integer orderQuantity;
	private Double shipQuantity;
	private String shipMethod;
	private Integer quantity;//ship qut;
	private Double size;
	private String shipTo;
	private String shipClerk;
	private String type;//itemType

	// Property accessors
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}

	public String getOrderSize() {
		return orderSize;
	}

	public void setOrderSize(String orderSize) {
		this.orderSize = orderSize;
	}

	public Integer getShippedQty() {
		return shippedQty;
	}

	public void setShippedQty(Integer shippedQty) {
		this.shippedQty = shippedQty;
	}

	public Double getShippedSize() {
		return shippedSize;
	}

	public void setShippedSize(Double shippedSize) {
		this.shippedSize = shippedSize;
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

	public String getModifiedName() {
		return modifiedName;
	}

	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}


	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLineId() {
		return this.lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderMain getOrder() {
		return order;
	}

	public void setOrder(OrderMain order) {
		this.order = order;
	}

//	public OrderItems getOrderItems() {
//		return orderItems;
//	}
//
//	public void setOrderItems(OrderItems orderItems) {
//		this.orderItems = orderItems;
//	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Shipment getShipments() {
		return shipments;
	}

	public void setShipments(Shipment shipments) {
		this.shipments = shipments;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(String shipMethod) {
		this.shipMethod = shipMethod;
	}

	public Double getShipQuantity() {
		return shipQuantity;
	}

	public void setShipQuantity(Double shipQuantity) {
		this.shipQuantity = shipQuantity;
	}

	public String getShipClerk() {
		return shipClerk;
	}

	public void setShipClerk(String shipClerk) {
		this.shipClerk = shipClerk;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}