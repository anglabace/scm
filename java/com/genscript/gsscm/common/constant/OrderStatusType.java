package com.genscript.gsscm.common.constant;

public enum OrderStatusType {
	NW("NW"), RV("RV"), CC("CC"), BO("BO"), VC("VC"), IP("IP"), PT("PT"), SH(
			"SH"), VS("VS"), HS("HS"), OH("OH"), CN("CN"),OP("OP");
	private final String value;

	OrderStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getName() {
		return this.name();
	}

	public static OrderStatusType fromValue(String v) {
		for (OrderStatusType c : OrderStatusType.values()) {
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