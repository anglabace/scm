package com.genscript.gsscm.tools.dto;

import java.util.Date;

public class FollowUpDTO {
	private Integer Id;
	private Integer orderNo;
	private String status;
	private Date followupDate;
	private Integer followupBy;
	private String message;
	private String nextFollowupDate;
	private String nextFollowupTask;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	public Integer getFollowupBy() {
		return followupBy;
	}

	public void setFollowupBy(Integer followupBy) {
		this.followupBy = followupBy;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNextFollowupDate() {
		return nextFollowupDate;
	}

	public void setNextFollowupDate(String nextFollowupDate) {
		this.nextFollowupDate = nextFollowupDate;
	}

	public String getNextFollowupTask() {
		return nextFollowupTask;
	}

	public void setNextFollowupTask(String nextFollowupTask) {
		this.nextFollowupTask = nextFollowupTask;
	}

}
