package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name="ChangeCurrencyDTO", namespace=WsConstants.NS)
public class ChangeCurrencyDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7137788872851540832L;
	private Double price;
	private String fromCurrency;
	private String toCurrency;
	private Date currencyDate;
	private String symbol;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	public String getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
	public Date getCurrencyDate() {
		return currencyDate;
	}
	public void setCurrencyDate(Date currencyDate) {
		this.currencyDate = currencyDate;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
