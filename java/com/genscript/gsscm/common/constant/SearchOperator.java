package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlType(name = "SearchOperator")
@XmlEnum
public enum SearchOperator {

	@XmlEnumValue("LIKE")
	LIKE("LIKE"),
	@XmlEnumValue("PLIKE")
	PLIKE("PLIKE"),
	@XmlEnumValue("SLIKE")
	SLIKE("SLIKE"),
	@XmlEnumValue("EQ")
	EQ("EQ"),
	@XmlEnumValue("LT")
	LT("LT"),
	@XmlEnumValue("GT")
	GT("GT"),
	@XmlEnumValue("LE")
	LE("LE"),
	@XmlEnumValue("BETWEEN")
	BETWEEN("BETWEEN"),
	@XmlEnumValue("NE")
	NE("NE"),
	@XmlEnumValue("NULL")
	NULL("NULL"),
	@XmlEnumValue("NOTNULL")
	NOTNULL("NOTNULL"),
	@XmlEnumValue("GE")
	GE("LE");
	
	private final String value;

	SearchOperator(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static SearchOperator fromValue(String v) {
		for (SearchOperator c : SearchOperator.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}