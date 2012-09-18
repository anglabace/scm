package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OrderTotalType")
@XmlEnum
public enum OrderTotalType {

	@XmlEnumValue("TOTAL_ORDER")
	TOTAL_ORDER("TOTAL_ORDER"),
	@XmlEnumValue("TOTAL_BILLED")
	TOTAL_BILLED("TOTAL_BILLED"),
	@XmlEnumValue("TOTAL_REFUND")
	TOTAL_REFUND("TOTAL_REFUND"),
	@XmlEnumValue("TOTAL_UNBILLED")
	TOTAL_UNBILLED("TOTAL_UNBILLED");
	
	private final String value;

	OrderTotalType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static OrderTotalType fromValue(String v) {
		for (OrderTotalType c : OrderTotalType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
