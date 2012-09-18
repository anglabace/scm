package com.genscript.gsscm.common.constant;

public enum PageViewType {

	PRODUCT("PRODUCT"), SERVICE("SERVICE");

	private final String value;

	PageViewType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static PageViewType fromValue(String v) {
		for (PageViewType c : PageViewType.values()) {
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