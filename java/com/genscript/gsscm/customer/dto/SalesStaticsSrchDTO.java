package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.util.Date;

public class SalesStaticsSrchDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5685110518774322489L;
	private Integer custNo;
	private Date beginDate;
	private Date endDate;
	private Integer period;
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
}
