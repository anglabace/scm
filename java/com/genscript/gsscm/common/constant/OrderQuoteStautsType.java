package com.genscript.gsscm.common.constant;

public enum OrderQuoteStautsType {

	ORDER("O"), ORDERITEM("OI"), QUOTE("Q"), QUOTEITEM("QI");

	private final String value;

	OrderQuoteStautsType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static OrderQuoteStautsType fromValue(String v) {
		for (OrderQuoteStautsType c : OrderQuoteStautsType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
