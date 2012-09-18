package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ServicePriceType")
@XmlEnum
public enum ServicePriceType {
	@XmlEnumValue("geneSynthesis")
	geneSynthesis("geneSynthesis"),
	@XmlEnumValue("custCloning")
	custCloning("custCloning"),
	@XmlEnumValue("mutagenesis")
	mutagenesis("mutagenesis"),
	@XmlEnumValue("plasmidPreparation")
	plasmidPreparation("plasmidPreparation"),
	@XmlEnumValue("pkgService")
	pkgService("pkgService"),
	@XmlEnumValue("antibody")
	antibody("antibody"),
	@XmlEnumValue("rna")
	rna("rna"),
	@XmlEnumValue("peptide")
	peptide("peptide"),
	@XmlEnumValue("orfClone")
	orfClone("orfClone"),
	@XmlEnumValue("oligo")
	oligo("oligo")
	;
	
	private final String value;

	ServicePriceType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ServicePriceType fromValue(String v) {
		for (ServicePriceType c : ServicePriceType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
