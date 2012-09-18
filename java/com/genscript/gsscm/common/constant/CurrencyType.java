package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CurrencyType")
@XmlEnum
public enum CurrencyType {
	@XmlEnumValue("JPY")
	JPY("JPY"),
	@XmlEnumValue("USD")
	USD("USD");
	
	private final String value;

	CurrencyType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}
	
	public static CurrencyType fromValue(String v) {
		for (CurrencyType c : CurrencyType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
