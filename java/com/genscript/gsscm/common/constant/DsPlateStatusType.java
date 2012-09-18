package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DsPlateStatusType")
@XmlEnum
public enum DsPlateStatusType {
	@XmlEnumValue("Receiving")
	Receiving("Receiving"),
	@XmlEnumValue("Concentration")
	Concentration("Concentration"),
	@XmlEnumValue("Add Primer")
	AddPrimer("Add Primer"),
	@XmlEnumValue("PCR")
	PCR("PCR"),
	@XmlEnumValue("Purification")
	Purification("Purification"),
	@XmlEnumValue("Sequencing")
	Sequencing("Sequencing"),
	@XmlEnumValue("Finished")
	Finished("Finished");
	private final String value;

	DsPlateStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static DsPlateStatusType fromValue(String v) {
		for (DsPlateStatusType c : DsPlateStatusType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
