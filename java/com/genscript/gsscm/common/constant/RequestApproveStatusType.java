package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "RequestApproveStatusType")
@XmlEnum
public enum RequestApproveStatusType {
	@XmlEnumValue("UNPROCESSED")
	UNPROCESSED("UNPROCESSED"),
	@XmlEnumValue("APPROVED")
	APPROVED("APPROVED"),
	@XmlEnumValue("REJECTED")
	REJECTED("REJECTED");
	
	private final String value;

	RequestApproveStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static RequestApproveStatusType fromValue(String v) {
		for (RequestApproveStatusType c : RequestApproveStatusType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
