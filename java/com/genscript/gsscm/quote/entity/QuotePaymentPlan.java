package com.genscript.gsscm.quote.entity;

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
 * QUOTATION PAYMENT PLAN.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "quote_payment_plan", catalog="order")
public class QuotePaymentPlan extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_plan_id")
	private Integer planId;
	private Integer quoteNo;
	private String paymentType;
	private Double amount;
	private String ccType;
	private String ccNo;
	private Date ccExprDate;
	private String ccCvc;
	private Integer chkNo;
	private Date chkClearDate;
	private String chkPostFlag;
	private String echkpRoutingNo;
	private String echkBank;
	private String echkAccount;
	private String echkType;
	private String accountHolder;
	private String eftTransactionNo;
	private Date eftReceiveDate;	
	private Date poDueDate;
	private Integer poPaymentTerm;
	private String poNumber;
	private String authStatus;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the planId
	 */
	public Integer getPlanId() {
		return planId;
	}
	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	/**
	 * @return the quoteNo
	 */
	public Integer getQuoteNo() {
		return quoteNo;
	}
	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
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
	 * @return the chkNo
	 */
	public Integer getChkNo() {
		return chkNo;
	}
	/**
	 * @param chkNo the chkNo to set
	 */
	public void setChkNo(Integer chkNo) {
		this.chkNo = chkNo;
	}
	/**
	 * @return the chkClearDate
	 */
	public Date getChkClearDate() {
		return chkClearDate;
	}
	/**
	 * @param chkClearDate the chkClearDate to set
	 */
	public void setChkClearDate(Date chkClearDate) {
		this.chkClearDate = chkClearDate;
	}
	/**
	 * @return the chkPostFlag
	 */
	public String getChkPostFlag() {
		return chkPostFlag;
	}
	/**
	 * @param chkPostFlag the chkPostFlag to set
	 */
	public void setChkPostFlag(String chkPostFlag) {
		this.chkPostFlag = chkPostFlag;
	}
	/**
	 * @return the echkpRoutingNo
	 */
	public String getEchkpRoutingNo() {
		return echkpRoutingNo;
	}
	/**
	 * @param echkpRoutingNo the echkpRoutingNo to set
	 */
	public void setEchkpRoutingNo(String echkpRoutingNo) {
		this.echkpRoutingNo = echkpRoutingNo;
	}
	/**
	 * @return the echkBank
	 */
	public String getEchkBank() {
		return echkBank;
	}
	/**
	 * @param echkBank the echkBank to set
	 */
	public void setEchkBank(String echkBank) {
		this.echkBank = echkBank;
	}
	/**
	 * @return the echkAccount
	 */
	public String getEchkAccount() {
		return echkAccount;
	}
	/**
	 * @param echkAccount the echkAccount to set
	 */
	public void setEchkAccount(String echkAccount) {
		this.echkAccount = echkAccount;
	}
	/**
	 * @return the echkType
	 */
	public String getEchkType() {
		return echkType;
	}
	/**
	 * @param echkType the echkType to set
	 */
	public void setEchkType(String echkType) {
		this.echkType = echkType;
	}
	/**
	 * @return the accountHolder
	 */
	public String getAccountHolder() {
		return accountHolder;
	}
	/**
	 * @param accountHolder the accountHolder to set
	 */
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	/**
	 * @return the eftTransactionNo
	 */
	public String getEftTransactionNo() {
		return eftTransactionNo;
	}
	/**
	 * @param eftTransactionNo the eftTransactionNo to set
	 */
	public void setEftTransactionNo(String eftTransactionNo) {
		this.eftTransactionNo = eftTransactionNo;
	}
	/**
	 * @return the eftReceiveDate
	 */
	public Date getEftReceiveDate() {
		return eftReceiveDate;
	}
	/**
	 * @param eftReceiveDate the eftReceiveDate to set
	 */
	public void setEftReceiveDate(Date eftReceiveDate) {
		this.eftReceiveDate = eftReceiveDate;
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
	 * @return the authStatus
	 */
	public String getAuthStatus() {
		return authStatus;
	}
	/**
	 * @param authStatus the authStatus to set
	 */
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
}
