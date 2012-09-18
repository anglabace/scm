package com.genscript.gsscm.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * The persistent class for the order_protein database table.
 * 
 * @author Golf
 */
@Entity
@Table(name="order_pkg_service", catalog="order")
public class OrderProtein extends BaseEntity {
	@Id
	private Integer orderItemId;
    @Lob()
	private String description;
	private Integer itemNo;
	private String name;
	private Integer orderNo;
//	private Integer quantity;

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
