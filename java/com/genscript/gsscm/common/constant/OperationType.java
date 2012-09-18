package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OperationType")
@XmlEnum
public enum OperationType {

	@XmlEnumValue("ADD")
	ADD("ADD"),
	@XmlEnumValue("EDIT")
	EDIT("EDIT"),
	@XmlEnumValue("DEL")
	DEL("DEL");
	
	private final String value;

	OperationType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}
	
	public static OperationType fromValue(String v) {
		for (OperationType c : OperationType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
