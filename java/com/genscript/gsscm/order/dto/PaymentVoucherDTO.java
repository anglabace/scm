package com.genscript.gsscm.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;

@XmlType(name="PaymentVoucherDTO", namespace=WsConstants.NS)
public class PaymentVoucherDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5157507142592851817L;
	private Integer voucherId;
	private Integer orderNo;
	private String paymentType;
	private String ccType;
	private String ccNo;
	private Date ccExprDate;
	private String ccExprDateStr;
	private String ccCvc;
	private String ccHolder;
	private Date poDueDate;
	private String poDueDateStr;
	private Integer poPaymentTerm;
	private String poNumber;
	private BigDecimal poAmount;
	private String poDocFlag;
	private Document document;
	private Integer delDocId;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the voucherId
	 */
	public Integer getVoucherId() {
		return voucherId;
	}
	/**
	 * @param voucherId the voucherId to set
	 */
	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return the ccType
	 */
	public String getCcType() {
		return ccType;
	}
	/**
	 * @param ccType the ccType to set
	 */
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	/**
	 * @return the ccNo
	 */
	public String getCcNo() {
		return ccNo;
	}
	/**
	 * @param ccNo the ccNo to set
	 */
	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}
	/**
	 * @return the ccExprDate
	 */
	public Date getCcExprDate() {
		return ccExprDate;
	}
	/**
	 * @param ccExprDate the ccExprDate to set
	 */
	public void setCcExprDate(Date ccExprDate) {
		this.ccExprDate = ccExprDate;
	}
	/**
	 * @return the ccCvc
	 */
	public String getCcCvc() {
		return ccCvc;
	}
	/**
	 * @param ccCvc the ccCvc to set
	 */
	public void setCcCvc(String ccCvc) {
		this.ccCvc = ccCvc;
	}
	/**
	 * @return the ccHolder
	 */
	public String getCcHolder() {
		return ccHolder;
	}
	/**
	 * @param ccHolder the ccHolder to set
	 */
	public void setCcHolder(String ccHolder) {
		this.ccHolder = ccHolder;
	}
	/**
	 * @return the poDueDate
	 */
	public Date getPoDueDate() {
		return poDueDate;
	}
	/**
	 * @param poDueDate the poDueDate to set
	 */
	public void setPoDueDate(Date poDueDate) {
		this.poDueDate = poDueDate;
	}
	/**
	 * @return the poPaymentTerm
	 */
	public Integer getPoPaymentTerm() {
		return poPaymentTerm;
	}
	/**
	 * @param poPaymentTerm the poPaymentTerm to set
	 */
	public void setPoPaymentTerm(Integer poPaymentTerm) {
		this.poPaymentTerm = poPaymentTerm;
	}
	/**
	 * @return the poNumber
	 */
	public String getPoNumber() {
		return poNumber;
	}
	/**
	 * @param poNumber the poNumber to set
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
	/**
	 * @return the delDocId
	 */
	public Integer getDelDocId() {
		return delDocId;
	}
	/**
	 * @param delDocId the delDocId to set
	 */
	public void setDelDocId(Integer delDocId) {
		this.delDocId = delDocId;
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
	public String getCcExprDateStr() {
		return ccExprDateStr;
	}
	public void setCcExprDateStr(String ccExprDateStr) {
		this.ccExprDateStr = ccExprDateStr;
	}
	public String getPoDueDateStr() {
		return poDueDateStr;
	}
	public void setPoDueDateStr(String poDueDateStr) {
		this.poDueDateStr = poDueDateStr;
	}
	public BigDecimal getPoAmount() {
		return poAmount;
	}
	public void setPoAmount(BigDecimal poAmount) {
		this.poAmount = poAmount;
	}
	
}
