package com.genscript.gsscm.serv.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *ServiceSubSteps
 * 
 * 
 * @author Mingrs
 */
@Entity
@Table(name = "service_sub_steps", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceSubSteps{
	private static final long serialVersionUID = 4354495584562837377L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stepId;
	private Integer serviceId;
	private Integer stepNo;
	private String name;
	private String description; 
	private Double cost;
	private String seqFlag;
	private Integer leadTime;  
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getStepNo() {
		return stepNo;
	}
	public void setStepNo(Integer stepNo) {
		this.stepNo = stepNo;
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
	public Integer getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getSeqFlag() {
		return seqFlag;
	}
	public void setSeqFlag(String seqFlag) {
		this.seqFlag = seqFlag;
	}
}
