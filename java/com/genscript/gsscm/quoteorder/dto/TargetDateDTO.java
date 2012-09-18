package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;
import java.util.Date;

public class TargetDateDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9076257024673164395L;
	private Date targetDate;
	private int scheduleDays;
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public int getScheduleDays() {
		return scheduleDays;
	}
	public void setScheduleDays(int scheduleDays) {
		this.scheduleDays = scheduleDays;
	}
	
}
