package com.genscript.gsscm.accounting.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ArPaymentAmountDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5174025402155054523L;
	private String currency;
	private BigDecimal amount;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
