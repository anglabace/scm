package com.genscript.gsscm.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * SHIP PACKAGE ITEMS.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_package_items", catalog="order")
public class OrderPackageItem extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pkg_item_id")
	private Integer pkgItemId;
	private Integer shipmentLineId;
	private Integer packageId;
	private Integer orderNo;
	private Integer itemNo;
	private Double size;
	@Column(name = "size_uom")
	private String uom;
	private String qtyUom;
	private Integer quantity;
	public Integer getPkgItemId() {
		return pkgItemId;
	}
	public void setPkgItemId(Integer pkgItemId) {
		this.pkgItemId = pkgItemId;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the size
	 */
	public Double getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Double size) {
		this.size = size;
	}
	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}
	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}
	public Integer getShipmentLineId() {
		return shipmentLineId;
	}
	public void setShipmentLineId(Integer shipmentLineId) {
		this.shipmentLineId = shipmentLineId;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
}
