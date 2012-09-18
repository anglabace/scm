package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AddressType")
@XmlEnum
public enum AddressType {
	@XmlEnumValue("SHIP_TO")
	SHIP_TO("SHIP_TO"),
	@XmlEnumValue("SOLD_TO")
	SOLD_TO("SOLD_TO"),
	@XmlEnumValue("CONTACT")
	CONTACT("CONTACT"),
	@XmlEnumValue("BILL_TO")
	BILL_TO("BILL_TO");
	
	private final String value;

	AddressType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static AddressType fromValue(String v) {
		for (AddressType c : AddressType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}