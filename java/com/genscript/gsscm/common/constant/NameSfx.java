package com.genscript.gsscm.common.constant;

public enum NameSfx {

	CPA("CPA"), EDD("Ed.D"), ESP("Esq."), II("II"), III("III"), IV("IV"), JR(
			"Jr."), MD("M.D."), PHD("Ph.D"), SR("Sr."), V("V");

	private final String value;

	NameSfx(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static NameSfx fromValue(String v) {
		for (NameSfx c : NameSfx.values()) {
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
