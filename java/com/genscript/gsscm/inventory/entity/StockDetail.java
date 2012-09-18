package com.genscript.gsscm.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * StockDetail.
 * 
 * 
 * @author Wangsf
 */

@Entity
@Table(name = "stock_detail", catalog="inventory")
public class StockDetail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_detail_id")
	private Integer id;
    private Integer warehouseId;
    private Integer storageId;
    private String itemType;
    private String catalogNo;
    private Integer onhandQty;
    private Integer reservedQty;
    private String locationCode;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the storageId
	 */
	public Integer getStorageId() {
		return storageId;
	}
	/**
	 * @param storageId the storageId to set
	 */
	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}
	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}
	/**
	 * @param catalogNo the catalogNo to set
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	/**
	 * @return the onhandQty
	 */
	public Integer getOnhandQty() {
		return onhandQty;
	}
	/**
	 * @param onhandQty the onhandQty to set
	 */
	public void setOnhandQty(Integer onhandQty) {
		this.onhandQty = onhandQty;
	}
	/**
	 * @return the reservedQty
	 */
	public Integer getReservedQty() {
		return reservedQty;
	}
	/**
	 * @param reservedQty the reservedQty to set
	 */
	public void setReservedQty(Integer reservedQty) {
		this.reservedQty = reservedQty;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
}
