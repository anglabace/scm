package com.genscript.gsscm.customer.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "GiftCardDTO", namespace = WsConstants.NS)
public class GiftCardDTO {

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

	private String userName;
	private String email;

	private String sender;
	private String purchasePro;

	private String purchaseRef;

	public String getPurchaseRef() {
		return purchaseRef;
	}

	public void setPurchaseRef(String purchaseRef) {
		this.purchaseRef = purchaseRef;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getPurchasePro() {
		return purchasePro;
	}

	 

	public void setPurchasePro(String purchasePro) {
		this.purchasePro = purchasePro;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
