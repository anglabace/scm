package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SalesOrderedDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1257803188216064043L;
	private String name;
	private Double sumQuantity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSumQuantity() {
		return sumQuantity;
	}
	public void setSumQuantity(Double sumQuantity) {
		this.sumQuantity = sumQuantity;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
