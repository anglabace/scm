package com.genscript.gsscm.workstation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "shipping_clerk_assigned", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShippingClerkAssigned {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assign_id")
	private Integer assignId;
	private Integer warehouseId;
	private String itemType;
	private Integer clsId;
	private Integer shippingClerk;
	private Date assignDate;
	private Integer assignedBy;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public Integer getAssignId() {
		return assignId;
	}

	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	public Integer getShippingClerk() {
		return shippingClerk;
	}

	public void setShippingClerk(Integer shippingClerk) {
		this.shippingClerk = shippingClerk;
	}

	public Date getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	public Integer getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(Integer assignedBy) {
		this.assignedBy = assignedBy;
	}

}
