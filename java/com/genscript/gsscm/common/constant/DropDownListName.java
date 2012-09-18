package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DropDownListName")
@XmlEnum
public enum DropDownListName {

	@XmlEnumValue("RESOURCE_COST_BASIS")
	RESOURCE_COST_BASIS("RESOURCE_COST_BASIS"),	
	@XmlEnumValue("RESOURCE_UOM")
	RESOURCE_UOM("RESOURCE_UOM"),	
	@XmlEnumValue("RESOURCE_GROUP")
	RESOURCE_GROUP("RESOURCE_GROUP"),
	@XmlEnumValue("SHIPPING_TYPE")
	SHIPPING_TYPE("SHIPPING_TYPE"),
	@XmlEnumValue("SHIPPING_RULE")
	SHIPPING_RULE("SHIPPING_RULE"),
	@XmlEnumValue("SHIPPING_ROUTE")
	SHIPPING_ROUTE("SHIPPING_ROUTE"),
	WORK_ORDER_SOURCE("WORK_ORDER_SOURCE"),
	WORK_ORDER_PRIORITY("WORK_ORDER_PRIORITY"),
	WORK_ORDER_STATUS("WORK_ORDER_STATUS"),
	QC_STATUS("QC_STATUS"),
	WORK_OPERATION_UOM("WORK_OPERATION_UOM"),
	WORK_ORDER_TYPE("WORK_ORDER_TYPE"),
	WO_OPERATION_STATUS("WO_OPERATION_STATUS"),
	TEMPLATE_WORK_FUNCTION("TEMPLATE_WORK_FUNCTION"),
	PROCESS_NAME("PROCESS_NAME"),
	SALES_REP_FUNCTION("SALES_REP_FUNCTION"),
	CONTINENT("CONTINENT"),
	GROUP_FUNCTION("GROUP_FUNCTION"),
	CREDIT_STATUS("CREDIT_STATUS"),
	ORGANIZATION_RATING("ORGANIZATION_RATING"),
	ORGANIZATION_NOTE_TYPE("ORGANIZATION_NOTE_TYPE"),
	HOSTNAME("OPERATION_HOST_NAME"), 
	CREDIT_CARD_TYPE("CREDIT_CARD_TYPE"), 
	REMAINSGEL("OPERATION_REMAINS_GEL")
;
	
	private final String value;
	
	DropDownListName(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static DropDownListName fromValue(String v) {
		for (DropDownListName c : DropDownListName.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
	
}
