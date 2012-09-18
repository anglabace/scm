package com.genscript.gsscm.common.constant;

/**
 * 上下午符号
 * @author zouyulu
 *
 */
public enum AmPm {
	AM("AM"),
	PM("PM");
	
	private final String value;

	AmPm(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static AmPm fromValue(String v) {
		for (AmPm c : AmPm.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
	
	public String getKey() {
		return this.name();
	}

	public String getValue() {
		return this.value;
	}
}
