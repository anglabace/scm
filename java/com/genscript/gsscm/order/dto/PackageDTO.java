package com.genscript.gsscm.order.dto;

import java.util.List;

public class PackageDTO {
	private Integer shipMethod;
	private Integer warehouseId;
	private String country;
	private List<ItemWeight> item;
	private String zipCode;
	private Integer clsId;
	private String itemType;
	private Integer totalQty;
	private Double orderAmount;
	
	public Integer getClsId() {
		return clsId;
	}
	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Integer getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Integer getShipMethod() {
		return shipMethod;
	}
	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public List<ItemWeight> getItem() {
		return item;
	}
	public void setItem(List<ItemWeight> item) {
		this.item = item;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
