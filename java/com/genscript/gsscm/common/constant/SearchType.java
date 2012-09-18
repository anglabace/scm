package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "SearchType")
@XmlEnum
public enum SearchType {
    @XmlEnumValue("CustomerSearch")
    CUSTOMER("CustomerSearch"),
    @XmlEnumValue("ContactSearch")
    CONTACT("ContactSearch"),
    @XmlEnumValue("QuoteSearch")
    QUOTE("QuoteSearch"),
    @XmlEnumValue("OrderSearch")
    ORDER("OrderSearch"),
    @XmlEnumValue("ProductSearch")
    PRODUCT("ProductSearch"),
    @XmlEnumValue("PurchaseOrderSearch")
    CustPurchaseOrder("PurchaseOrderSearch"),
    @XmlEnumValue("ServiceSearch")
    SERVICE("ServiceSearch");

    private final String value;

    SearchType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchType fromValue(String v) {
        for (SearchType c : SearchType.values()) {
            if (c.value.equals(v)) {
                return c;

            }
        }
        throw new IllegalArgumentException(v);
    }
}