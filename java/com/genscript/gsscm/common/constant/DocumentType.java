package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DocumentType")
@XmlEnum
public enum DocumentType {
	@XmlEnumValue("ORDER_INST_NOTE")
	ORDER_INST_NOTE("ORDER_INST_NOTE"), @XmlEnumValue("ORDER_INST_MAIL")
	ORDER_INST_MAIL("ORDER_INST_MAIL"),@XmlEnumValue("System_Mail_Log")
	System_Mail_Log("System_Mail_Log"), @XmlEnumValue("QUOTE_INST_NOTE")
	QUOTE_INST_NOTE("QUOTE_INST_NOTE"), @XmlEnumValue("CUSTOMER_NOTE_TYPE")
	CUSTOMER_NOTE_TYPE("CUSTOMER_NOTE_TYPE"), @XmlEnumValue("QUOTE_INST_MAIL")
	QUOTE_INST_MAIL("QUOTE_INST_MAIL"), @XmlEnumValue("ORDER_PO")
	ORDER_PO("ORDER_PO"), @XmlEnumValue("QUOTE_PO")
	QUOTE_PO("QUOTE_PO"), @XmlEnumValue("OIM_PEPTIED")
	OIM_PEPTIED("OIM_PEPTIED"), @XmlEnumValue("OIM_PROTEIN")
	OIM_PROTEIN("OIM_PROTEIN"), @XmlEnumValue("OIM_GENE")
	OIM_GENE("OIM_GENE"), @XmlEnumValue("OIM_RNA")
	OIM_RNA("OIM_RNA"), @XmlEnumValue("QIM_RNA")
	QIM_RNA("QIM_RNA"), @XmlEnumValue("OIM_MUTA")
	OIM_MUTA("OIM_MUTA"), @XmlEnumValue("OIM_MUTA_SELF")
	OIM_MUTA_SELF("OIM_MUTA_SELF"), @XmlEnumValue("OIM_MUTALIB")
	OIM_MUTALIB("OIM_MUTALIB"),@XmlEnumValue("OIM_MUTALIB_SELF")
	OIM_MUTALIB_SELF("OIM_MUTALIB_SELF"), @XmlEnumValue("OIM_ORFCLONE")
	OIM_ORFCLONE("OIM_ORFCLONE"), @XmlEnumValue("OIM_SIRNA")
	OIM_SIRNA("OIM_SIRNA"), @XmlEnumValue("OIM_CUSTCLONING")
	OIM_CUSTCLONING("OIM_CUSTCLONING"), @XmlEnumValue("OIM_CUSTCLONING_CS")
	OIM_CUSTCLONING_CS("OIM_CUSTCLONING_CS"), @XmlEnumValue("OIM_CUSTCLONING_SELF")
	OIM_CUSTCLONING_SELF("OIM_CUSTCLONING_SELF"),@XmlEnumValue("OIM_PLASMID")
	OIM_PLASMID("OIM_PLASMID"), @XmlEnumValue("OIM_PLASMID_SELF")
	OIM_PLASMID_SELF("OIM_PLASMID_SELF"), @XmlEnumValue("QIM_PEPTIED")
	QIM_PEPTIED("QIM_PEPTIED"), @XmlEnumValue("QIM_PROTEIN")
	QIM_PROTEIN("QIM_PROTEIN"), @XmlEnumValue("QIM_GENE")
	QIM_GENE("QIM_GENE"), @XmlEnumValue("QIM_MUTA")
	QIM_MUTA("QIM_MUTA"), @XmlEnumValue("QIM_MUTA_SELF")
	QIM_MUTA_SELF("QIM_MUTA_SELF"),@XmlEnumValue("QIM_MUTALIB")
	QIM_MUTALIB("QIM_MUTALIB"),@XmlEnumValue("QIM_MUTALIB_SELF")
	QIM_MUTALIB_SELF("QIM_MUTALIB_SELF"), @XmlEnumValue("QIM_ORFCLONE")
	QIM_ORFCLONE("QIM_ORFCLONE"), @XmlEnumValue("QIM_SIRNA")
	QIM_SIRNA("QIM_SIRNA"), @XmlEnumValue("QIM_CUSTCLONING")
	QIM_CUSTCLONING("QIM_CUSTCLONING"), @XmlEnumValue("QIM_CUSTCLONING_CS")
	QIM_CUSTCLONING_CS("QIM_CUSTCLONING_CS"), @XmlEnumValue("QIM_CUSTCLONING_SELF")
	QIM_CUSTCLONING_SELF("QIM_CUSTCLONING_SELF"), @XmlEnumValue("QIM_PLASMID")
	QIM_PLASMID("QIM_PLASMID"), @XmlEnumValue("QIM_PLASMID_SELF")
	QIM_PLASMID_SELF("QIM_PLASMID_SELF"),
	MANU_WORKORDER("MANU_WORKORDER"), 
	LICENCE_ORDER("LICENCE_ORDER"), 
	ORGANIZATION_NOTE("ORGANIZATION_NOTE"),
	@XmlEnumValue("CUST_CASE")
	CUST_CASE("CUST_CASE");

	private final String value;

	DocumentType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static DocumentType fromValue(String v) {
		for (DocumentType c : DocumentType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}