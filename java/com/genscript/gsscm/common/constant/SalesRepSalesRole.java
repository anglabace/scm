package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlType(name = "SaleRepSalesRole")
@XmlEnum
public enum SalesRepSalesRole {
	@XmlEnumValue("PROJECT_SUPPORT")
	PROJECT_SUPPORT("PROJECT_SUPPORT"),
	@XmlEnumValue("SALES_CONTACT")
	SALES_CONTACT("SALES_CONTACT"),
	@XmlEnumValue("TECH_SUPPORT")
	TECH_SUPPORT("TECH_SUPPORT");
	
	private final String value;
	
	SalesRepSalesRole (String v) {
		value = v;
	}
	
	public String value () {
		return value;
	}
	
	public static SalesRepSalesRole fromValue(String v) {
		for (SalesRepSalesRole c : SalesRepSalesRole.values()) {
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
