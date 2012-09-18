package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "QaGroupAssignedType")
@XmlEnum
public enum WorkCenterAssignedType {
	@XmlEnumValue("SERVICE")
	SERVICE("SERVICE"),
	@XmlEnumValue("PRODUCT")
	PRODUCT("PRODUCT");
	
	private final String value;

	WorkCenterAssignedType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static WorkCenterAssignedType fromValue(String v) {
		for (WorkCenterAssignedType c : WorkCenterAssignedType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}
