package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "QuoteStatusType")
@XmlEnum
public enum QuoteStatusType {
	@XmlEnumValue("NW")
	NW("NW"),
	@XmlEnumValue("OP")
	OP("OP"),	
	@XmlEnumValue("CW")
	CW("CW"),
	@XmlEnumValue("CO")
	CO("CO"),	
	@XmlEnumValue("OH")
	OH("OH"),
	@XmlEnumValue("VD")
	VD("VD"),
	@XmlEnumValue("CN")
	CN("CN");
	
	private final String value;

	QuoteStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static QuoteStatusType fromValue(String v) {
		for (QuoteStatusType c : QuoteStatusType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}