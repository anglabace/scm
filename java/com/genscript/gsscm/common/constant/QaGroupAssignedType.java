package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "QaGroupAssignedType")
@XmlEnum
public enum QaGroupAssignedType {
	@XmlEnumValue("SERVICE")
	SERVICE("SERVICE"),
	@XmlEnumValue("PRODUCT")
	PRODUCT("PRODUCT");
	
	private final String value;

	QaGroupAssignedType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static QaGroupAssignedType fromValue(String v) {
		for (QaGroupAssignedType c : QaGroupAssignedType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}
