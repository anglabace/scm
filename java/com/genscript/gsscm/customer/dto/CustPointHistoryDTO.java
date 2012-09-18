package com.genscript.gsscm.customer.dto;

import com.genscript.gsscm.common.SessionBaseDTO;

import java.util.Date;

public class CustPointHistoryDTO extends SessionBaseDTO {

    /**
     *
     */
    private static long serialVersionUID = 3527764406426699022L;
	private Integer id;
	private Integer custNo;
	private Integer orderNo;
	private Integer arTransactionId;
	private Integer couponId;
	private Integer points;
	private String comments;
	private Date issueDate;
	private Date expirationDate;
	private String redeemType;
	private String giftCardId;

	private String giftCardValue;
	
	
	public String getGiftCardValue() {
		return giftCardValue;
	}
	public void setGiftCardValue(String giftCardValue) {
		this.giftCardValue = giftCardValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getArTransactionId() {
		return arTransactionId;
	}
	public void setArTransactionId(Integer arTransactionId) {
		this.arTransactionId = arTransactionId;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getRedeemType() {
		return redeemType;
	}
	public void setRedeemType(String redeemType) {
		this.redeemType = redeemType;
	}
	public String getGiftCardId() {
		return giftCardId;
	}
	public void setGiftCardId(String giftCardId) {
		this.giftCardId = giftCardId;
	} 
	
	
	
}
