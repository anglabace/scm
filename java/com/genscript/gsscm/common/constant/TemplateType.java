package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TemplateType")
@XmlEnum
public enum TemplateType {
	@XmlEnumValue("QUOTE")
	QUOTE("QUOTE"),
	@XmlEnumValue("ORDER")
	ORDER("ORDER");
	
	private final String value;

	TemplateType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static TemplateType fromValue(String v) {
		for (TemplateType c : TemplateType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
