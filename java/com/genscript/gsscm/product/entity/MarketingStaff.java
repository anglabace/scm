package com.genscript.gsscm.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * CATALOG.
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "marketing_clerks", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MarketingStaff  extends BaseEntity {
	@Id
	@Column(name = "clerk_id")
	private Integer staffId;
	private Integer marketingGroup;
	private String description;
	private Integer supervisor;
	private String status;
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	public Integer getMarketingGroup() {
		return marketingGroup;
	}
	public void setMarketingGroup(Integer marketingGroup) {
		this.marketingGroup = marketingGroup;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Integer supervisor) {
		this.supervisor = supervisor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
