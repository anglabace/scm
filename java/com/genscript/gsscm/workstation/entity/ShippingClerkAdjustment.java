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
@Table(name = "Shipping_clerk_adjustment", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShippingClerkAdjustment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer orderNo;
	private Integer itemNo;
	private Integer oldClerk;
	private Integer newClerk;
	private Date adjustDate;
	private Integer adjustedBy;
	private String reason;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public Integer getOldClerk() {
		return oldClerk;
	}

	public void setOldClerk(Integer oldClerk) {
		this.oldClerk = oldClerk;
	}

	public Integer getNewClerk() {
		return newClerk;
	}

	public void setNewClerk(Integer newClerk) {
		this.newClerk = newClerk;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public Integer getAdjustedBy() {
		return adjustedBy;
	}

	public void setAdjustedBy(Integer adjustedBy) {
		this.adjustedBy = adjustedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	
}
