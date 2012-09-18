package com.genscript.gsscm.common.constant;

public enum CallTime {
	T0000("00:00"),
	T0030("00:30"),
	T0100("01:00"),
	T0130("01:30"),
	T0200("02:00"),
	T0230("02:30"),
	T0300("03:00"),
	T0330("03:30"),
	T0400("04:00"),
	T0430("04:30"),
	T0500("05:00"),
	T0530("05:30"),
	T0600("06:00"),
	T0630("06:30"),
	T0700("07:00"),
	T0730("07:30"),
	T0800("08:00"),
	T0830("08:30"),
	T0900("09:00"),
	T0930("09:30"),
	T1000("10:00"),
	T1030("10:30"),
	T1100("11:00"),
	T1130("11:30"),
	T1200("12:00");

	private final String value;

	CallTime(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static CallTime fromValue(String v) {
		for (CallTime c : CallTime.values()) {
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
