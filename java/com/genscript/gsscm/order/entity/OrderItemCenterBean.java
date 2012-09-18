package com.genscript.gsscm.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "v_orderitem_center", catalog = "order")
public class OrderItemCenterBean  implements Serializable{

	private static final long serialVersionUID = 8444441783383315596L;
	@Id
	private Integer orderItemId;
	private Integer orderNo;
	private Integer srcSoNo;
	private Integer itemNo;
	private Integer workCenterId;
	private String catalogNo;
	private String status;
	private Date creationDate;
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
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
	public Integer getWorkCenterId() {
		return workCenterId;
	}
	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getSrcSoNo() {
		return srcSoNo;
	}
	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}
