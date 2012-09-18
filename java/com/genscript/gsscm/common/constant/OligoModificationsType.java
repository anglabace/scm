package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OligoModificationsType")
@XmlEnum
public enum OligoModificationsType {

	@XmlEnumValue("RNA 5'modification")
	RNA5MODIFICATION("RNA 5'modification"),
	@XmlEnumValue("RNA internal modification")
	RNAINTERNALMODIFICATION("RNA internal modification"),
	@XmlEnumValue("RNA 3'modification")
	RNA3MODIFICATION("RNA 3'modification"),
	@XmlEnumValue("DNA 5'modification")
	DNA5MODIFICATION("DNA 5'modification"),
	@XmlEnumValue("DNA internal modification")
	DNAINTERNALMODIFICATION("DNA internal modification"),
	@XmlEnumValue("DNA 3'modification")
	DNA3MODIFICATION("DNA 3'modification");
	
	private final String value;

	public String getValue() {
		return value;
	}

	OligoModificationsType(String value) {
		this.value = value;
	}

	public static OligoModificationsType fromValue(String v) {
		for (OligoModificationsType c : OligoModificationsType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
