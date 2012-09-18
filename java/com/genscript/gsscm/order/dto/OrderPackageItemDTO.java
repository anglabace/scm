package com.genscript.gsscm.order.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "OrderPackageItemDTO", namespace = WsConstants.NS)
public class OrderPackageItemDTO {
	private Integer pkgItemId;
	private Integer packageId;
	private Integer orderNo;
	private Integer itemNo;
	private Double size;
	private Integer shipmentLineId;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
