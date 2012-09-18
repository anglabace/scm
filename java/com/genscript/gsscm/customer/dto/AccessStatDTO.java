package com.genscript.gsscm.customer.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "AccessStatDTO", namespace = WsConstants.NS)
public class AccessStatDTO {
	private Long visitTotal;
	private String firstVisit;
	private String lastVisit;
	private Long visitPagesTotal;
	private Long avgStaySecd;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the visitTotal
	 */
	public Long getVisitTotal() {
		return visitTotal;
	}
	/**
	 * @param visitTotal the visitTotal to set
	 */
	public void setVisitTotal(Long visitTotal) {
		this.visitTotal = visitTotal;
	}
	/**
	 * @return the firstVisit
	 */
	public String getFirstVisit() {
		return firstVisit;
	}
	/**
	 * @param firstVisit the firstVisit to set
	 */
	public void setFirstVisit(String firstVisit) {
		this.firstVisit = firstVisit;
	}
	/**
	 * @return the lastVisit
	 */
	public String getLastVisit() {
		return lastVisit;
	}
	/**
	 * @param lastVisit the lastVisit to set
	 */
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	/**
	 * @return the avgStaySecd
	 */
	public Long getAvgStaySecd() {
		return avgStaySecd;
	}
	/**
	 * @param avgStaySecd the avgStaySecd to set
	 */
	public void setAvgStaySecd(Long avgStaySecd) {
		this.avgStaySecd = avgStaySecd;
	}
	/**
	 * @return the visitPagesTotal
	 */
	public Long getVisitPagesTotal() {
		return visitPagesTotal;
	}
	/**
	 * @param visitPagesTotal the visitPagesTotal to set
	 */
	public void setVisitPagesTotal(Long visitPagesTotal) {
		this.visitPagesTotal = visitPagesTotal;
	}



}
