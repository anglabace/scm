package com.genscript.gsscm.purchase.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "v_all_purchase_orders", catalog="purchase")
public class PurchaseOrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1327382333427126459L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
	private Integer orderNo;
	private String altOrderNo;
	private String priority;
	private String orderType;
	private Integer vendorNo;
	private String vendorName;
	private Integer srcSoNo;
	private Date orderDate;
	private Date exprDate;
	private Double subTotal;
	private String currency;
	private String symbol;
	private Integer purchaseContactId;
	private String purchaseContact;
	private Integer warehouseId;
	private String warehouseName;
	private String status;
	
	@Transient
	private String erpUsPo;
	
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getAltOrderNo() {
		return altOrderNo;
	}
	public void setAltOrderNo(String altOrderNo) {
		this.altOrderNo = altOrderNo;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Integer getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(Integer vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Integer getSrcSoNo() {
		return srcSoNo;
	}
	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	public Integer getPurchaseContactId() {
		return purchaseContactId;
	}
	public void setPurchaseContactId(Integer purchaseContactId) {
		this.purchaseContactId = purchaseContactId;
	}
	public String getPurchaseContact() {
		return purchaseContact;
	}
	public void setPurchaseContact(String purchaseContact) {
		this.purchaseContact = purchaseContact;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getErpUsPo() {
		return erpUsPo;
	}
	public void setErpUsPo(String erpUsPo) {
		this.erpUsPo = erpUsPo;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
