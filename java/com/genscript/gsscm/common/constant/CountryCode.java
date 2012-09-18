package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CountryCode")
@XmlEnum
public enum CountryCode {
	@XmlEnumValue("JP")
	JP("JP"),
	@XmlEnumValue("Customer")
	Customer("Customer"),
	
	@XmlEnumValue("US")
	US("US"),
	@XmlEnumValue("US-JP")
	USJP("US-JP"),
	@XmlEnumValue("CN")
	CN("CN");
	
	private final String value;

	CountryCode(String v) {
		value = v;
	}

	public String value() {
		return value;
	}
	
	public static CountryCode fromValue(String v) {
		for (CountryCode c : CountryCode.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
