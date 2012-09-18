package com.genscript.gsscm.product.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name="PurchaseOrderDTO", namespace=WsConstants.NS)
public class PurchaseOrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4919468602322927720L;
	private Integer orderNo;
	private Integer vendorNo;
	private Date orderDate;
	private String altOrderNo;
	private Date expectedDate;
//	private String poNo;
//	private Date poDate;
	private String status;
	private Integer companyId;
	private String currency;
	private String priority;
	private Integer purchaseContact;
	private String orderType;
	private Integer warehouseId;
	private Integer srcSoNo;
	private Date exprDate;
	private Double subTotal;
	private String comment;
	//非本表而是业务需要的属性
	private String vendorName;
	private String catalogNo;
	private Integer catalogQty;
	private String orderedBy;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the vendorNo
	 */
	public Integer getVendorNo() {
		return vendorNo;
	}
	/**
	 * @param vendorNo the vendorNo to set
	 */
	public void setVendorNo(Integer vendorNo) {
		this.vendorNo = vendorNo;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the expectedDate
	 */
	public Date getExpectedDate() {
		return expectedDate;
	}
	/**
	 * @param expectedDate the expectedDate to set
	 */
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the catalogQty
	 */
	public Integer getCatalogQty() {
		return catalogQty;
	}
	/**
	 * @param catalogQty the catalogQty to set
	 */
	public void setCatalogQty(Integer catalogQty) {
		this.catalogQty = catalogQty;
	}
	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Integer getPurchaseContact() {
		return purchaseContact;
	}
	public void setPurchaseContact(Integer purchaseContact) {
		this.purchaseContact = purchaseContact;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getSrcSoNo() {
		return srcSoNo;
	}
	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public String getOrderedBy() {
		return orderedBy;
	}
	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getAltOrderNo() {
		return altOrderNo;
	}
	public void setAltOrderNo(String altOrderNo) {
		this.altOrderNo = altOrderNo;
	}
}
