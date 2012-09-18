package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "WorkOrderType")
@XmlEnum
/**
 * 
 */
public enum WorkOrderType {
	@XmlEnumValue("SERVICE")
	SERVICE("SERVICE"),
	@XmlEnumValue("PRODUCT")
	PRODUCT("PRODUCT");
	
	private final String value;

	WorkOrderType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static WorkOrderType fromValue(String v) {
		for (WorkOrderType c : WorkOrderType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}
