package com.genscript.gsscm.olddb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "charger", catalog = "olddb")
public class Charger implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6658929562550389557L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer orderId;
	private Float amount;
	private String ccNumber;
	private String ccHolder;
	private String status;
	private String authno;
	private String merchantOrderid;
	private String reason;
	private Date tranDate;
	private String charger;
	private String comments;
	private String acctname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getCcHolder() {
		return ccHolder;
	}
	public void setCcHolder(String ccHolder) {
		this.ccHolder = ccHolder;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthno() {
		return authno;
	}
	public void setAuthno(String authno) {
		this.authno = authno;
	}
	public String getMerchantOrderid() {
		return merchantOrderid;
	}
	public void setMerchantOrderid(String merchantOrderid) {
		this.merchantOrderid = merchantOrderid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getTranDate() {
		return tranDate;
	}
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getAcctname() {
		return acctname;
	}
	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
