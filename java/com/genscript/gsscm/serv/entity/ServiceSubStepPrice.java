package com.genscript.gsscm.serv.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Service Sub Step Price
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "service_sub_step_price", catalog="product")
public class ServiceSubStepPrice  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4354495584562837377L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer priceId;
	private Integer stepId;
	private String catalogId;
	private Double retailPrice;
	private Double limitPrice;
	public Integer getPriceId() {
		return priceId;
	}
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	
	public Double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public Double getLimitPrice() {
		return limitPrice;
	}
	public void setLimitPrice(Double limitPrice) {
		this.limitPrice = limitPrice;
	}
	

	
}
