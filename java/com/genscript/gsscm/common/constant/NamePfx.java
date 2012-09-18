package com.genscript.gsscm.common.constant;

public enum NamePfx {

	Dr("Dr."), Mr("Mr."), Mrs("Mrs"), Ms("Ms.");

	private final String value;

	NamePfx(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static NamePfx fromValue(String v) {
		for (NamePfx c : NamePfx.values()) {
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
