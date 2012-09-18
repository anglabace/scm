package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "QuoteItemType")
@XmlEnum
/*
 * 使用ProductService and ServService 中获取order item有用到。
 * 2010-10-13 mingrs 增加注释
 */
public enum QuoteItemType {
	@XmlEnumValue("SERVICE")
	SERVICE("SERVICE"),
	@XmlEnumValue("PRODUCT")
	PRODUCT("PRODUCT");
	
	private final String value;

	QuoteItemType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static QuoteItemType fromValue(String v) {
		for (QuoteItemType c : QuoteItemType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}