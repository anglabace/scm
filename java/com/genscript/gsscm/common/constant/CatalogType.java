package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CatalogType")
@XmlEnum
public enum CatalogType {

	@XmlEnumValue("PRODUCT")
	PRODUCT("PRODUCT"),
	@XmlEnumValue("SERVICE")
	SERVICE("SERVICE");
	
	private final String value;

	CatalogType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}
	
	public static CatalogType fromValue(String v) {
		for (CatalogType c : CatalogType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
