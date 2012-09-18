package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OrderInstructionType")
@XmlEnum
public enum OrderInstructionType {
	@XmlEnumValue("SALES_NOTES")
	SALES_NOTES("SALES_NOTES"),
	@XmlEnumValue("CROSS_SELLING")
	CROSS_SELLING("CROSS_SELLING"),
	@XmlEnumValue("CUST_CONFIRM_EMAIL") 
	CUST_CONFIRM_EMAIL("CUST_CONFIRM_EMAIL"),
    @XmlEnumValue("ORDER_CHANGE_NOTIFICATION")
	ORDER_CHANGE_NOTIFICATION("ORDER_CHANGE_NOTIFICATION"),
	@XmlEnumValue("VENDOR_CONFIRM_EMAIL")
	VENDOR_CONFIRM_EMAIL("VENDOR_CONFIRM_EMAIL"),
	@XmlEnumValue("Supply Confirm Email") 
	SUPPLY_CONFIRM_EMAIL("Supply Confirm Email"),
	@XmlEnumValue("PRODUCTION")
	PRODUCTION("PRODUCTION"),
	@XmlEnumValue("ACCOUNTING")
	ACCOUNTING("ACCOUNTING"),
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

	OrderInstructionType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static OrderInstructionType fromValue(String v) {
		for (OrderInstructionType c : OrderInstructionType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}