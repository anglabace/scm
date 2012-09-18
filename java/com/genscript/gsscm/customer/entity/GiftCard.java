package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * .
 * 
 * 
 * @author Mingrs
 */

@Entity
@Table(name = "gift_card")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GiftCard   implements Serializable{
	
	/**
	 * 
	 */
	private static long serialVersionUID = -1441531029106339545L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cardType;
	private String cardNo;
	private Integer cardValue;
	private Date purchaseDate;
	private Integer purchasedBy;
	private Date sendDate;
	private Integer sentBy;
	private Integer custNo; 
	private Integer orderNo;
	private String purchaseRef;
	@Transient
	private String purchasedName;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public String getPurchaseRef() {
		return purchaseRef;
	}

	public void setPurchaseRef(String purchaseRef) {
		this.purchaseRef = purchaseRef;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getCardValue() {
		return cardValue;
	}
	public void setCardValue(Integer cardValue) {
		this.cardValue = cardValue;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Integer getPurchasedBy() {
		return purchasedBy;
	}
	public void setPurchasedBy(Integer purchasedBy) {
		this.purchasedBy = purchasedBy;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getSentBy() {
		return sentBy;
	}
	public void setSentBy(Integer sentBy) {
		this.sentBy = sentBy;
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

	public String getPurchasedName() {
		return purchasedName;
	}

	public void setPurchasedName(String purchasedName) {
		this.purchasedName = purchasedName;
	}
	
	
}
