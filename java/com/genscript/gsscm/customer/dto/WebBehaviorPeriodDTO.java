package com.genscript.gsscm.customer.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class WebBehaviorPeriodDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3888335626107471833L;
	private String firstDate;
	private String lastYear;
	private String thisYear;
	private String lastWeek;
	private String thisWeek;
	private String lastMonth;
	private String thisMonth;
	private String thisQuarter;
	private String lastQuarter;
	private String lastSixMonths;
	public String getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}
	public String getLastYear() {
		return lastYear;
	}
	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}
	public String getThisYear() {
		return thisYear;
	}
	public void setThisYear(String thisYear) {
		this.thisYear = thisYear;
	}
	public String getLastWeek() {
		return lastWeek;
	}
	public void setLastWeek(String lastWeek) {
		this.lastWeek = lastWeek;
	}
	public String getThisWeek() {
		return thisWeek;
	}
	public void setThisWeek(String thisWeek) {
		this.thisWeek = thisWeek;
	}
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	public String getThisMonth() {
		return thisMonth;
	}
	public void setThisMonth(String thisMonth) {
		this.thisMonth = thisMonth;
	}
	public String getThisQuarter() {
		return thisQuarter;
	}
	public void setThisQuarter(String thisQuarter) {
		this.thisQuarter = thisQuarter;
	}
	public String getLastQuarter() {
		return lastQuarter;
	}
	public void setLastQuarter(String lastQuarter) {
		this.lastQuarter = lastQuarter;
	}
	public String getLastSixMonths() {
		return lastSixMonths;
	}
	public void setLastSixMonths(String lastSixMonths) {
		this.lastSixMonths = lastSixMonths;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
