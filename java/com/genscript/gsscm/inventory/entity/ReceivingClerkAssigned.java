package com.genscript.gsscm.inventory.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * receiving clerk assigned.
 * 
 * 
 * @author Mingrs
 */

@Entity
@Table(name = "receiving_clerk_assigned", catalog="inventory")
public class ReceivingClerkAssigned {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer warehouseId;
	private String itemType;
	private Integer clsId;
	private Integer receivingClerk;
	private Integer assignedBy;
	private Date assignDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getReceivingClerk() {
		return receivingClerk;
	}
	public void setReceivingClerk(Integer receivingClerk) {
		this.receivingClerk = receivingClerk;
	}
	public Integer getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(Integer assignedBy) {
		this.assignedBy = assignedBy;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	
}
