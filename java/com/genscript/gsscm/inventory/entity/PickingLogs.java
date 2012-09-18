package com.genscript.gsscm.inventory.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * picking logs.
 * 
 * 
 * @author Mingrs
 */

@Entity
@Table(name = "picking_logs", catalog="inventory")
public class PickingLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String catalogNo;
	private Integer pickingQty;
	private Double pickingSize;
	private Integer warehouseId; 
	private String lotNo;
	private Integer storageId; 
	private String locationCode;
	private Date pickingDate; 
	private Integer pickedBy;
	private Integer packageId;
	//@Column(name = "pkg_line_Id")
	private Integer pkgLineId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public Integer getPickingQty() {
		return pickingQty;
	}
	public void setPickingQty(Integer pickingQty) {
		this.pickingQty = pickingQty;
	}
	public Double getPickingSize() {
		return pickingSize;
	}
	public void setPickingSize(Double pickingSize) {
		this.pickingSize = pickingSize;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getStorageId() {
		return storageId;
	}
	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public Date getPickingDate() {
		return pickingDate;
	}
	public void setPickingDate(Date pickingDate) {
		this.pickingDate = pickingDate;
	}
	public Integer getPickedBy() {
		return pickedBy;
	}
	public void setPickedBy(Integer pickedBy) {
		this.pickedBy = pickedBy;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getPkgLineId() {
		return pkgLineId;
	}
	public void setPkgLineId(Integer pkgLineId) {
		this.pkgLineId = pkgLineId;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	
}
