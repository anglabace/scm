package com.genscript.gsscm.shipment.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ShipPackageItemDTO", namespace = WsConstants.NS)
public class ShipPackageItemDTO {
	private Integer pkgItemId;
	private Integer packageId;
	private Integer orderNo;
	private Integer itemNo;
	private Double size;
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
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	private String uom;
	private Integer quantity;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
