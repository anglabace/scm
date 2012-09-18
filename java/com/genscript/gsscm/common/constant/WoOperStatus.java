package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "WorkOrderStatus")
@XmlEnum
public enum WoOperStatus {
	@XmlEnumValue("New")
	New("New"),
	@XmlEnumValue("In Production")
	Inprogress("In Production"),
	@XmlEnumValue("Completed")
	Completed("Completed"),
	@XmlEnumValue("Failed")
	Failed("Failed");
	
	private final String value;

	WoOperStatus(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static WoOperStatus fromValue(String v) {
		for (WoOperStatus c : WoOperStatus.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
	
	public static boolean isExist(String v) {
		for (WoOperStatus c : WoOperStatus.values()) {
			if (c.value.equals(v)) {
				return true;
			}
		}
		return false;
	}

}
