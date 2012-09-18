package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "QuoteInstructionType")
@XmlEnum
public enum QuoteInstructionType {
	@XmlEnumValue("SALES_NOTES")
	SALES_NOTES("SALES_NOTES"),
	@XmlEnumValue("CROSS_SELLING")
	CROSS_SELLING("CROSS_SELLING"),
	@XmlEnumValue("FOLLOWUP_EMAIL")
	FOLLOWUP_EMAIL("FOLLOWUP_EMAIL"),
	@XmlEnumValue("CUST_CONFIRM_EMAIL")
	CUST_CONFIRM_EMAIL("CUST_CONFIRM_EMAIL"),
	@XmlEnumValue("PRODUCTION")
	PRODUCTION("PRODUCTION"),
	@XmlEnumValue("ACCOUNTING")
	ACCOUNTING("ACCOUNTING"),
	@XmlEnumValue("VENDOR_CONFIRM_EMAIL")
	VENDOR_CONFIRM_EMAIL("VENDOR_CONFIRM_EMAIL"),
	@XmlEnumValue("FOLLOWUP_DATE")
	FOLLOWUP_DATE("FOLLOWUP_DATE"),
	@XmlEnumValue("SHIPMENT")
	SHIPMENT("SHIPMENT"),
	@XmlEnumValue("CUSTOMER")
	CUSTOMER("CUSTOMER"),
    @XmlEnumValue("SELLING")
	SELLING("SELLING")
	;
	
	private final String value;

	QuoteInstructionType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static QuoteInstructionType fromValue(String v) {
		for (QuoteInstructionType c : QuoteInstructionType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}