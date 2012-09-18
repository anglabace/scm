package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnumValue;

public enum ShippingType {
		@XmlEnumValue("Package")
		Package("Package"),
		@XmlEnumValue("Order")
		Order("Order")
		;
		
		private final String value;

		ShippingType(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		public static ShippingType fromValue(String v) {
			for (ShippingType c : ShippingType.values()) {
				if (c.value.equals(v)) {
					return c;

				}
			}
			throw new IllegalArgumentException(v);
		}
}
