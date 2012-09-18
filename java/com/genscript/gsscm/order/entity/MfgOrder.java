package com.genscript.gsscm.order.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * MFG ORDER.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "mfg_order", catalog="order")
public class MfgOrder extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
	private Integer orderNo;
	private Integer custNo;
	private String priority;
	private Integer srcSoNo;
	private Integer srcPoNo;
	private Date orderDate;
	private Date exprDate;
	private String billAccCode;
	private String status;
	private Double subTotal;
	private Integer companyId;
	private String type;
	@Transient
	private List<MfgOrderItem> mfgOrderItemList;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Integer getSrcSoNo() {
		return srcSoNo;
	}
	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}
	public Integer getSrcPoNo() {
		return srcPoNo;
	}
	public void setSrcPoNo(Integer srcPoNo) {
		this.srcPoNo = srcPoNo;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getBillAccCode() {
		return billAccCode;
	}
	public void setBillAccCode(String billAccCode) {
		this.billAccCode = billAccCode;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<MfgOrderItem> getMfgOrderItemList() {
		return mfgOrderItemList;
	}
	public void setMfgOrderItemList(List<MfgOrderItem> mfgOrderItemList) {
		this.mfgOrderItemList = mfgOrderItemList;
	}
	
}
