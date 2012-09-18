package com.genscript.gsscm.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * PAYMENT VOUCHER.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "payment_voucher", catalog="order")
public class PaymentVoucher extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "voucher_id")
	private Integer voucherId;
	private Integer orderNo;
	private String paymentType;
	private String ccType;
	private String ccNo;
	private Date ccExprDate;
	private String ccCvc;
	private String ccHolder;
	private Date poDueDate;
	private Integer poPaymentTerm;
	private String poNumber;
	private String poDocFlag;
	private BigDecimal poAmount;
	public Integer getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getCcType() {
		return ccType;
	}
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	public String getCcNo() {
		return ccNo;
	}
	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}
	public Date getCcExprDate() {
		return ccExprDate;
	}
	public void setCcExprDate(Date ccExprDate) {
		this.ccExprDate = ccExprDate;
	}
	public String getCcCvc() {
		return ccCvc;
	}
	public void setCcCvc(String ccCvc) {
		this.ccCvc = ccCvc;
	}
	public String getCcHolder() {
		return ccHolder;
	}
	public void setCcHolder(String ccHolder) {
		this.ccHolder = ccHolder;
	}
	public Date getPoDueDate() {
		return poDueDate;
	}
	public void setPoDueDate(Date poDueDate) {
		this.poDueDate = poDueDate;
	}
	public Integer getPoPaymentTerm() {
		return poPaymentTerm;
	}
	public void setPoPaymentTerm(Integer poPaymentTerm) {
		this.poPaymentTerm = poPaymentTerm;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the poDocFlag
	 */
	public String getPoDocFlag() {
		return poDocFlag;
	}
	/**
	 * @param poDocFlag the poDocFlag to set
	 */
	public void setPoDocFlag(String poDocFlag) {
		this.poDocFlag = poDocFlag;
	}
	public BigDecimal getPoAmount() {
		return poAmount;
	}
	public void setPoAmount(BigDecimal poAmount) {
		this.poAmount = poAmount;
	}
	
}
