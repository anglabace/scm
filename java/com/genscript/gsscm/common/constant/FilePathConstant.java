package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnumValue;

public enum FilePathConstant {
	@XmlEnumValue("orderService")
	orderService("orderService"),
	@XmlEnumValue("quoteService")
	quoteService("quoteService"),
	@XmlEnumValue("document_template")
	document_template("document_template"),
	@XmlEnumValue("customer_case")
	customer_case("customer_case"),
	Email_Customer("email_customer"),
	Manu_WorkOrder("manu_workorder"),
	@XmlEnumValue("Order_licenceDocument")
	Order_licenceDocument("Order_licenceDocument");

	private final String value;

	FilePathConstant(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static FilePathConstant fromValue(String v) {
		for (FilePathConstant c : FilePathConstant.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
