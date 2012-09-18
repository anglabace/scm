package com.genscript.gsscm.customer.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_credit_cards", catalog = "customer")
public class CustomerCreditCards {
	private Integer cardId;
	private Integer custNo;
	private String cardType;
	private Date expr_date;
	private String cardNo;
	private String cardHolder;
	private String cvc;
	private String status;
	private Date creation_date;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	public CustomerCreditCards() {
		super();
	}
	public CustomerCreditCards(Integer cardId, Integer custNo, String cardType, Date expr_date, String cardNo, String cardHolder, String cvc, String status, Date creation_date, Integer createdBy, Date modifyDate, Integer modifiedBy) {
		super();
		this.cardId = cardId;
		this.custNo = custNo;
		this.cardType = cardType;
		this.expr_date = expr_date;
		this.cardNo = cardNo;
		this.cardHolder = cardHolder;
		this.cvc = cvc;
		this.status = status;
		this.creation_date = creation_date;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
	}
	@Column(name="card_holder",length=50)
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	@Id
	@Column(name="card_id",unique=true,nullable=false)
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	@Column(name="card_no",length=30)
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Column(name="card_type",length=30)
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@Column(name="created_by",nullable=false)
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="creation_date",nullable=false)
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	@Column(name="cust_no",nullable=false)
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	@Column(name="cvc",length=10)
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	@Column(name="expr_date")
	public Date getExpr_date() {
		return expr_date;
	}
	public void setExpr_date(Date expr_date) {
		this.expr_date = expr_date;
	}
	@Column(name="modified_by",nullable =false)
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Column(name="modify_date",nullable=false)
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Column(name="status",length=20)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
