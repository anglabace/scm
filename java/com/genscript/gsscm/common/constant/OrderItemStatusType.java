package com.genscript.gsscm.common.constant;

public enum OrderItemStatusType {
	CM("CM"), CC("CC"), BO("BO"), PB("PB"), VC("VC"), IP("IP"), PT("PT"), SH(
			"SH"), PS("PS"), VS("VS"), RT("RT"), OH("OH"), CN("CN"), Y("Y");
	private final String value;

	OrderItemStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static OrderItemStatusType fromValue(String v) {
		for (OrderItemStatusType c : OrderItemStatusType.values()) {
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