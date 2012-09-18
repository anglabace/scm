package com.genscript.gsscm.common.constant;

import java.util.Date;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlType(name = "SearchPropertyType")
@XmlEnum
public enum SearchPropertyType {

	@XmlEnumValue("S")
	S(String.class),
	@XmlEnumValue("I")
	I(Integer.class),
	@XmlEnumValue("L")
	L(Long.class),
	@XmlEnumValue("N")
	N(Double.class),
	@XmlEnumValue("D")
	D(Date.class),
	@XmlEnumValue("B")
	B(Boolean.class);
	
	private Class<?> clazz;

	SearchPropertyType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getValue() {
		return clazz;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
