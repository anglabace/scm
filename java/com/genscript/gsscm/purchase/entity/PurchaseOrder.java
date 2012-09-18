package com.genscript.gsscm.purchase.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * PurchaseOrder.
 * 
 * 
 * @author Wangsf.
 */

@Entity
@Table(name = "purchase_orders", catalog="purchase")
public class PurchaseOrder extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
	private Integer orderNo;
	private String altOrderNo;
	private Integer vendorNo;
	private Date orderDate;
	private Date expectedDate;
	private String status;
	private String currency;
	private Integer companyId;
	private String priority;
	private Integer purchaseContact;
	private String orderType;
	private Integer warehouseId;
	private String receivingFlag;
	private Integer srcSoNo;
	private Date exprDate;
	private Double subTotal;
	private String comment;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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

	public String getReceivingFlag() {
		return receivingFlag;
	}

	public void setReceivingFlag(String receivingFlag) {
		this.receivingFlag = receivingFlag;
	}
	
}
