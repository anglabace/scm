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
@Table(name = "stock", catalog="inventory")
public class Stock extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Integer id;
    private String itemType;
    private String catalogNo;	
    private Integer warehouseId;
    private Integer realAmount;
    private Integer virtualAmount;
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
	 * @return the realAmount
	 */
	public Integer getRealAmount() {
		return realAmount;
	}
	/**
	 * @param realAmount the realAmount to set
	 */
	public void setRealAmount(Integer realAmount) {
		this.realAmount = realAmount;
	}
	public Integer getVirtualAmount() {
		return virtualAmount;
	}
	public void setVirtualAmount(Integer virtualAmount) {
		this.virtualAmount = virtualAmount;
	}
	
}
