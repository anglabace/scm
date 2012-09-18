package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ItemSearchType")
@XmlEnum
public enum ItemSearchType {
	@XmlEnumValue("PRODUCT_ITEM")
	PRODUCT_ITEM("PRODUCT_ITEM"),
	@XmlEnumValue("QUOTE_ITEM")
	QUOTE_ITEM("QUOTE_ITEM"),
	@XmlEnumValue("SERVICE_ITEM")
	SERVICE_ITEM("SERVICE_ITEM"),
	@XmlEnumValue("TEMPLATE_ITEM")
	TEMPLATE_ITEM("TEMPLATE_ITEM"),
	@XmlEnumValue("SERVICE_TEMPLATE_ITEM")
	SERVICE_TEMPLATE_ITEM("SERVICE_TEMPLATE_ITEM"),
	@XmlEnumValue("SERVICE_ASSOCIATED_ITEM")
	SERVICE_ASSOCIATED_ITEM("SERVICE_ASSOCIATED_ITEM"),
	@XmlEnumValue("ORDER_ITEM")
	ORDER_ITEM("ORDER_ITEM");
	
	private final String value;

	ItemSearchType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ItemSearchType fromValue(String v) {
		for (ItemSearchType c : ItemSearchType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
