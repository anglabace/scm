package com.genscript.gsscm.customer.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CustSalesStaticsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -657121487605334735L;
	private String picName;
	private String showContent;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getShowContent() {
		return showContent;
	}
	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}
}
