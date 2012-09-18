package com.genscript.gsscm.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * 
 * order_service entity
 * 
 * @author golf
 * 
 */
@Entity
@Table(name = "order_service", catalog = "order")
public class OrderService extends BaseEntity {
	@Id
	private Integer orderItemId;
	private Integer orderNo;
	private String customDesc;
	private String comments;
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getCustomDesc() {
		return customDesc;
	}
	public void setCustomDesc(String customDesc) {
		this.customDesc = customDesc;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
