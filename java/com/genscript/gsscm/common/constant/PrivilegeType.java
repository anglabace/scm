package com.genscript.gsscm.common.constant;

public enum PrivilegeType {

	MENU("MENU"), UI("UI"), ACTION("ACTION"),EMAILCAMPAIGN("EC");

	private final String value;

	PrivilegeType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static PrivilegeType fromValue(String v) {
		for (PrivilegeType c : PrivilegeType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}