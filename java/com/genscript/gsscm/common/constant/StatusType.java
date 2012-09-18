package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "StatusType")
@XmlEnum
public enum StatusType {

	@XmlEnumValue("ACTIVE")
	ACTIVE("ACTIVE"),
	@XmlEnumValue("INACTIVE")
	INACTIVE("INACTIVE");
	
	private final String value;

	StatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static StatusType fromValue(String v) {
		for (StatusType c : StatusType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
