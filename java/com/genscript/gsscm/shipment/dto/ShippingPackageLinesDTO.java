package com.genscript.gsscm.shipment.dto;

import java.math.BigDecimal;
import java.util.List;


public class ShippingPackageLinesDTO {
	private Integer lineId;
	private String catalogNo;
	private String lotNo;
	private String storageLocation;
	private List<StorageLocationDTO> storageLocationList;
	private String lotNoValue;
	private BigDecimal inventory;
	private String status;
	private String type;
	private Integer qty;
	private String company;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public List<StorageLocationDTO> getStorageLocationList() {
		return storageLocationList;
	}
	public void setStorageLocationList(List<StorageLocationDTO> storageLocationList) {
		this.storageLocationList = storageLocationList;
	}
	public String getLotNoValue() {
		return lotNoValue;
	}
	public void setLotNoValue(String lotNoValue) {
		this.lotNoValue = lotNoValue;
	}
	public BigDecimal getInventory() {
		return inventory;
	}
	public void setInventory(BigDecimal inventory) {
		this.inventory = inventory;
	}
	
	
}
