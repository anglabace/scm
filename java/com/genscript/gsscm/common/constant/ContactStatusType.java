package com.genscript.gsscm.common.constant;

/**
 * Customer 的 contact 类型
 * @author zouyulu
 *
 */
public enum ContactStatusType {
	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE"),
	SUSPENDED("SUSPENDED");
	
	private final String value;

	ContactStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ContactStatusType fromValue(String v) {
		for (ContactStatusType c : ContactStatusType.values()) {
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
