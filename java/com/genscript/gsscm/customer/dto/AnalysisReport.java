package com.genscript.gsscm.customer.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "AnalysisReport", namespace = WsConstants.NS)
public class AnalysisReport {
	private Long visit;
	private Long refered;
	private Long searching;
	private Long pageView;
	private Double avgViewTime;	
	private String fromDate;
	private String toDate;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the visit
	 */
	public Long getVisit() {
		return visit;
	}
	/**
	 * @param visit the visit to set
	 */
	public void setVisit(Long visit) {
		this.visit = visit;
	}
	/**
	 * @return the refered
	 */
	public Long getRefered() {
		return refered;
	}
	/**
	 * @param refered the refered to set
	 */
	public void setRefered(Long refered) {
		this.refered = refered;
	}
	/**
	 * @return the searching
	 */
	public Long getSearching() {
		return searching;
	}
	/**
	 * @param searching the searching to set
	 */
	public void setSearching(Long searching) {
		this.searching = searching;
	}
	/**
	 * @return the pageView
	 */
	public Long getPageView() {
		return pageView;
	}
	/**
	 * @param pageView the pageView to set
	 */
	public void setPageView(Long pageView) {
		this.pageView = pageView;
	}
	/**
	 * @return the avgViewTime
	 */
	public Double getAvgViewTime() {
		return avgViewTime;
	}
	/**
	 * @param avgViewTime the avgViewTime to set
	 */
	public void setAvgViewTime(Double avgViewTime) {
		this.avgViewTime = avgViewTime;
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

}
