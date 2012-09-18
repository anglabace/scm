package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
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

/**
 * CUSTOMER_ROLE.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "job_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1405185022017329520L;
	private Integer custRoleId;
	private String name;
	private String description;
	@Column(insertable=true,updatable=false)
	private Date creationDate = new Date();
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate = new Date();
	private Integer modifiedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	public Integer getCustRoleId() {
		return custRoleId;
	}
	public void setCustRoleId(Integer custRoleId) {
		this.custRoleId = custRoleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public CustomerRole(){}
	public CustomerRole(String name, Integer createdBy, Integer modifiedBy) {
		this.name = name;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
