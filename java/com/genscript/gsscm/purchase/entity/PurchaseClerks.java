package com.genscript.gsscm.purchase.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * PurchaseClerks.
 * 
 * 
 * @author mingrs.
 */

@Entity
@Table(name = "purchase_clerks", catalog="purchase")
public class PurchaseClerks  extends BaseEntity{
	@Id
	private Integer clerkId;
	private String description;
	private Integer supervisor;
	private String status;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public Integer getClerkId() {
		return clerkId;
	}
	public void setClerkId(Integer clerkId) {
		this.clerkId = clerkId;
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
