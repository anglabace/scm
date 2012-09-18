package com.genscript.gsscm.accounting.entity;

/* * Created on 2010-11-18 11:11:56
 * by zhouyong
 * for what
 */

import java.io.Serializable;
import java.util.Date;


public class OrderItem implements Serializable{
	 public int orderItemId;
	 public int orderNo;
	 public int itemNo;
	 public String type;
	 public int clsId;
	 public String catalogNo;
	 public String catalogId;
	 public String name;
	 public String shortDesc;
	 public String longDesc;
	 public String taxStatus;
	 public int leadTime;
	 public int quantity;
	 public String qtyUom;
	 public float size;
	 public String sizeUom;
	 public float basePrice;
	 public float cost;
	 public float unitPrice;
	 public float amount;
	 public float discount;
	 public float tax;
	 public int billtoAddrId;
	 public int shiptoAddrId;
	 public int soldtoAddrId;
	 public int shipMethod;
	 public String trackingNo;
	 public int parentItemId;
	 public String relationType;
	 public String sellingNote;
	 public int storageId;
	 public String comment;
	 public String status;
	 public String statusUpdReason;
	 public Date creationDate;
	 public int createdBy;
	 public Date modifyDate;
	 public int modifiedBy;
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getClsId() {
		return clsId;
	}
	public void setClsId(int clsId) {
		this.clsId = clsId;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public int getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public String getSizeUom() {
		return sizeUom;
	}
	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}
	public float getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public int getBilltoAddrId() {
		return billtoAddrId;
	}
	public void setBilltoAddrId(int billtoAddrId) {
		this.billtoAddrId = billtoAddrId;
	}
	public int getShiptoAddrId() {
		return shiptoAddrId;
	}
	public void setShiptoAddrId(int shiptoAddrId) {
		this.shiptoAddrId = shiptoAddrId;
	}
	public int getSoldtoAddrId() {
		return soldtoAddrId;
	}
	public void setSoldtoAddrId(int soldtoAddrId) {
		this.soldtoAddrId = soldtoAddrId;
	}
	public int getShipMethod() {
		return shipMethod;
	}
	public void setShipMethod(int shipMethod) {
		this.shipMethod = shipMethod;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public int getParentItemId() {
		return parentItemId;
	}
	public void setParentItemId(int parentItemId) {
		this.parentItemId = parentItemId;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getSellingNote() {
		return sellingNote;
	}
	public void setSellingNote(String sellingNote) {
		this.sellingNote = sellingNote;
	}
	public int getStorageId() {
		return storageId;
	}
	public void setStorageId(int storageId) {
		this.storageId = storageId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusUpdReason() {
		return statusUpdReason;
	}
	public void setStatusUpdReason(String statusUpdReason) {
		this.statusUpdReason = statusUpdReason;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
}
