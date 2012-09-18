package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "RequestApproveType")
@XmlEnum
public enum RequestApproveType {
	@XmlEnumValue("Catalog")
	Catalog("Catalog"),
	@XmlEnumValue("ProductCategory")
	ProductCategory("ProductCategory"),
	@XmlEnumValue("ServiceCategory")
	ServiceCategory("ServiceCategory"),
	@XmlEnumValue("ProductPrice")
	ProductPrice("ProductPrice"),
	@XmlEnumValue("ServicePrice")
	ServicePrice("ServicePrice"),
	@XmlEnumValue("ServicePriceGroup")
	ServicePriceGroup("ServicePriceGroup"),
	@XmlEnumValue("SubServicePrice")
	SubServicePrice("SubServicePrice"),
	@XmlEnumValue("Product")
	Product("Product"),
	@XmlEnumValue("Service")
	Service("Service"),
	@XmlEnumValue("catalogName")
	catalogName("catalogName"),
	@XmlEnumValue("name")
	name("name"),
	@XmlEnumValue("standardPrice")
	standardPrice("standardPrice"),
	@XmlEnumValue("status")
	status("status"),
	@XmlEnumValue("unitCost")
	unitCost("unitCost");
	
	private final String value;

	RequestApproveType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static RequestApproveType fromValue(String v) {
		for (RequestApproveType c : RequestApproveType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
