package com.genscript.gsscm.tools.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "order_followup", catalog = "order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FollowUp {
	private static long serialVersionUID = 3465386394404418781L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	@Column(name = "order_no")
	private Integer orderNo;
	@Column(name = "status")
	private String status;
	@Column(name = "followup_date")
	private Date followupDate;
	@Column(name = "followup_by")
	private Integer followupBy;
	@Column(name = "message")
	private String message;
	@Column(name = "next_followup_date")
	private String nextFollowupDate;
	@Column(name = "next_followup_task")
	private String nextFollowupTask;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
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
	 
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setSerialVersionUID(long serialVersionUID) {
		FollowUp.serialVersionUID = serialVersionUID;
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
