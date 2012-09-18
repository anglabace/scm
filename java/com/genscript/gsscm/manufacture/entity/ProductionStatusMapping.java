package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "production_status_mapping", catalog = "manufacturing")
public class ProductionStatusMapping extends BaseEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1884517206241198456L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer workCenterId;
	private String productionStatus;
	private String customProductionStatus;
	private String description;
	private String status;
	@Transient
	private String workCenterName;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getWorkCenterName() {
		return workCenterName;
	}

	public void setWorkCenterName(String workCenterName) {
		this.workCenterName = workCenterName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkCenterId() {
		return workCenterId;
	}

	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
	}

	public String getProductionStatus() {
		return productionStatus;
	}

	public void setProductionStatus(String productionStatus) {
		this.productionStatus = productionStatus;
	}

	public String getCustomProductionStatus() {
		return customProductionStatus;
	}

	public void setCustomProductionStatus(String customProductionStatus) {
		this.customProductionStatus = customProductionStatus;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
}	
