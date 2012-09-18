package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "QuoteItemStatusType")
@XmlEnum
public enum QuoteItemStatusType {
	@XmlEnumValue("CM")
	CM("CM"),
	@XmlEnumValue("CN")
	CN("CN"),
	Y("Y");
	
	private final String value;

	QuoteItemStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static QuoteItemStatusType fromValue(String v) {
		for (QuoteItemStatusType c : QuoteItemStatusType.values()) {
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
		return this.value;
	}
}