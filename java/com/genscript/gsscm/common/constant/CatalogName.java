package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CatalogType")
@XmlEnum
public enum CatalogName {
	@XmlEnumValue("DNA")
	DNA("DNA"),
	@XmlEnumValue("Phosphorothioated DNA")
	PHOSPHOROTHIOATED_DNA("Phosphorothioated DNA"),
	@XmlEnumValue("RNA")
	RNA("RNA"),
	@XmlEnumValue("Phosphorothioated RNA")
	PHOSPHOROTHIOATED_RNA("Phosphorothioated RNA"),
	@XmlEnumValue("2'-OMe-RNA")
	OME_RNA_2("2'-OMe-RNA"),
	@XmlEnumValue("Phosphorothioated 2'-OMe-RNA")
	PHOSPHOROTHIOATED_2_OME_RNA("Phosphorothioated 2'-OMe-RNA");
	
	private final String value;

	CatalogName(String v) {
		value = v;
	}

	public String value() {
		return value;
	}
	
	public static CatalogName fromValue(String v) {
		for (CatalogName c : CatalogName.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
