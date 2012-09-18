package com.genscript.gsscm.product.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "PdtSalesReport", namespace = WsConstants.NS)
public class PdtSalesReport {
	private String fromDate;
	private String toDate;
	private Double salesValue;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the salesValue
	 */
	public Double getSalesValue() {
		return salesValue;
	}
	/**
	 * @param salesValue the salesValue to set
	 */
	public void setSalesValue(Double salesValue) {
		this.salesValue = salesValue;
	}
}
