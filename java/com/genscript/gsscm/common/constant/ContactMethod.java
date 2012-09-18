package com.genscript.gsscm.common.constant;

public enum ContactMethod {

	Email("Email"), Phone("Phone"), Mail("Mail"), Fax("Fax"), Meeting("Meeting");

	private final String value;

	ContactMethod(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ContactMethod fromValue(String v) {
		for (ContactMethod c : ContactMethod.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
}