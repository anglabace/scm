package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

/**
 * @author zhangyong
 *
 */
@XmlType(name = "RedeemHistoryDTO", namespace = WsConstants.NS)
public class RedeemHistoryDTO implements Serializable {
	private static final long serialVersionUID = -5834314185484109687L;
	private Date redeemDate;
	private Integer redeemedPoints;
	private String couponCode;
	private Integer couponValue;
	private Date expirationDate;
	private String comments;
	private String status;
	
	public Date getRedeemDate() {
		return redeemDate;
	}
	public void setRedeemDate(Date redeemDate) {
		this.redeemDate = redeemDate;
	}
	public Integer getRedeemedPoints() {
		return redeemedPoints;
	}
	public void setRedeemedPoints(Integer redeemedPoints) {
		this.redeemedPoints = redeemedPoints;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Integer getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(Integer couponValue) {
		this.couponValue = couponValue;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
