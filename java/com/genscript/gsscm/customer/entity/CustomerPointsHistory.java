package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * .
 * 
 * 
 * @author Mingrs
 */

@Entity
@Table(name = "customer_points_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerPointsHistory  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1623745209715614111L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private String giftCardValue;
	private String giftCardId;
	
	
	
	public String getGiftCardValue() {
		return giftCardValue;
	}
	public void setGiftCardValue(String giftCardValue) {
		this.giftCardValue = giftCardValue;
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
	
	
}
