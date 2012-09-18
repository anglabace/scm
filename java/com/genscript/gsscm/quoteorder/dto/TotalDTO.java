package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "TotalDTO", namespace = WsConstants.NS)
public class TotalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -911481500355845543L;
	private BigDecimal quorderSubtotal;
	private String quorderSubtotalShow;
	private BigDecimal quorderDiscount;
	private String quorderDiscountShow;
	private BigDecimal quorderShipAmt;
	private String quorderShipAmtShow;
	private BigDecimal quorderTax;
	private String quorderTaxShow;
	private BigDecimal quorderTotal;
	private String quorderTotalShow;
	private BigDecimal quorderTotalPayments;
	private String quorderTotalPaymentsShow;
	private BigDecimal quorderTotalCouponValue;
	private String quorderTotalCouponValueShow;
	private String handlingFee;
	/**
	 * added by zouyulu 货币符号
	 */
	private String symbol;
	private String currency;
	private BigDecimal prePayment;

	public BigDecimal getQuorderSubtotal() {
		return quorderSubtotal;
	}

	public void setQuorderSubtotal(BigDecimal quorderSubtotal) {
		this.quorderSubtotal = quorderSubtotal;
	}

	public BigDecimal getQuorderDiscount() {
		return quorderDiscount;
	}

	public void setQuorderDiscount(BigDecimal quorderDiscount) {
		this.quorderDiscount = quorderDiscount;
	}

	public BigDecimal getQuorderShipAmt() {
		return quorderShipAmt;
	}

	public void setQuorderShipAmt(BigDecimal quorderShipAmt) {
		this.quorderShipAmt = quorderShipAmt;
	}

	public BigDecimal getQuorderTax() {
		return quorderTax;
	}

	public void setQuorderTax(BigDecimal quorderTax) {
		this.quorderTax = quorderTax;
	}

	public BigDecimal getQuorderTotal() {
		return quorderTotal;
	}

	public void setQuorderTotal(BigDecimal quorderTotal) {
		this.quorderTotal = quorderTotal;
	}

	public BigDecimal getQuorderTotalPayments() {
		return quorderTotalPayments;
	}

	public void setQuorderTotalPayments(BigDecimal quorderTotalPayments) {
		this.quorderTotalPayments = quorderTotalPayments;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getPrePayment() {
		return prePayment;
	}

	public void setPrePayment(BigDecimal prePayment) {
		this.prePayment = prePayment;
	}

	public BigDecimal getQuorderTotalCouponValue() {
		return quorderTotalCouponValue;
	}

	public void setQuorderTotalCouponValue(BigDecimal quorderTotalCouponValue) {
		this.quorderTotalCouponValue = quorderTotalCouponValue;
	}

	public String getQuorderSubtotalShow() {
		return quorderSubtotalShow;
	}

	public void setQuorderSubtotalShow(String quorderSubtotalShow) {
		this.quorderSubtotalShow = quorderSubtotalShow;
	}

	public String getQuorderDiscountShow() {
		return quorderDiscountShow;
	}

	public void setQuorderDiscountShow(String quorderDiscountShow) {
		this.quorderDiscountShow = quorderDiscountShow;
	}

	public String getQuorderShipAmtShow() {
		return quorderShipAmtShow;
	}

	public void setQuorderShipAmtShow(String quorderShipAmtShow) {
		this.quorderShipAmtShow = quorderShipAmtShow;
	}

	public String getQuorderTaxShow() {
		return quorderTaxShow;
	}

	public void setQuorderTaxShow(String quorderTaxShow) {
		this.quorderTaxShow = quorderTaxShow;
	}

	public String getQuorderTotalShow() {
		return quorderTotalShow;
	}

	public void setQuorderTotalShow(String quorderTotalShow) {
		this.quorderTotalShow = quorderTotalShow;
	}

	public String getQuorderTotalPaymentsShow() {
		return quorderTotalPaymentsShow;
	}

	public void setQuorderTotalPaymentsShow(String quorderTotalPaymentsShow) {
		this.quorderTotalPaymentsShow = quorderTotalPaymentsShow;
	}

	public String getQuorderTotalCouponValueShow() {
		return quorderTotalCouponValueShow;
	}

	public void setQuorderTotalCouponValueShow(String quorderTotalCouponValueShow) {
		this.quorderTotalCouponValueShow = quorderTotalCouponValueShow;
	}

	public String getHandlingFee() {
		return handlingFee;
	}

	public void setHandlingFee(String handlingFee) {
		this.handlingFee = handlingFee;
	}
}
