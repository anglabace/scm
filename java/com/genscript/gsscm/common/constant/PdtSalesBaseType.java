package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PdtSalesBaseType")
@XmlEnum
public enum PdtSalesBaseType {

	@XmlEnumValue("NetSales")
	NetSales("NetSales"),
	@XmlEnumValue("GrossSales")
	GrossSales("GrossSales"),
	@XmlEnumValue("LossOnReturn")
	LossOnReturn("LossOnReturn"),
	@XmlEnumValue("NetUnitsSold")
	NetUnitsSold("NetUnitsSold"),
	@XmlEnumValue("GrossUnitsSold")
	GrossUnitsSold("GrossUnitsSold"),
	@XmlEnumValue("UnitsReturned")
	UnitsReturned("UnitsReturned");
	private final String value;

	PdtSalesBaseType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static PdtSalesBaseType fromValue(String v) {
		for (PdtSalesBaseType c : PdtSalesBaseType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
