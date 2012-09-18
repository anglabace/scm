package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PeptideModificationType")
@XmlEnum
public enum PeptideModificationType {
	@XmlEnumValue("N-Terminal")
	NTerminal("N-Terminal"),
	@XmlEnumValue("C-Terminal")
	CTerminal("C-Terminal"),
	@XmlEnumValue("Normal")
	Normal("Normal");
	
	private final String value;

	PeptideModificationType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static PeptideModificationType fromValue(String v) {
		for (PeptideModificationType c : PeptideModificationType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
