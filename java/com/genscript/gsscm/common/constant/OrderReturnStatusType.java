package com.genscript.gsscm.common.constant;

public enum OrderReturnStatusType {
	UNPROCESSED("UNPROCESSED"), PROCESSED("PROCESSED"), EXCHANGED("EXCHANGED"), REFUNDED("REFUNDED");
	private final String value;

	OrderReturnStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static OrderReturnStatusType fromValue(String v) {
		for (OrderReturnStatusType c : OrderReturnStatusType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}

}