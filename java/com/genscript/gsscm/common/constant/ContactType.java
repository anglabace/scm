package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ContactType")
@XmlEnum
public enum ContactType {

	@XmlEnumValue("SCHEDULE")
	SCHEDULE("SCHEDULE"),
	@XmlEnumValue("HISTORY")
	HISTORY("HISTORY");
	
	private final String value;

	ContactType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}
	
	public static ContactType fromValue(String v) {
		for (ContactType c : ContactType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
