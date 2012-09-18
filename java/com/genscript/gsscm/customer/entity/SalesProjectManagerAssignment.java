package com.genscript.gsscm.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;
import com.genscript.gsscm.serv.entity.ServiceClassification;
@Entity
@Table(name = "sales_project_manager_assignment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesProjectManagerAssignment extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer assignId;
	private Integer serviceType;
	private Integer salesId;
	private String comments;
	@Transient
	private String resourceName;
	@Transient
	private String modifiedName;
	@Transient
	private ServiceClassification serviceClassification;
	@Transient
	private SalesRep salesRep;
	public Integer getAssignId() {
		return assignId;
	}
	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}
	public ServiceClassification getServiceClassification() {
		return serviceClassification;
	}
	public void setServiceClassification(ServiceClassification serviceClassification) {
		this.serviceClassification = serviceClassification;
	}
	public SalesRep getSalesRep() {
		return salesRep;
	}
	public void setSalesRep(SalesRep salesRep) {
		this.salesRep = salesRep;
	}
	public String getModifiedName() {
		return modifiedName;
	}
	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	

}
