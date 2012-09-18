package com.genscript.gsscm.common.constant;

public enum ProductDetailType {
	AMERCANPEPTIDE("amercanpeptide"), ANASPEC("anaspec"), 
	ANTIBODY("antibody"),
	BACHEM("bachem"),
	CHEMICALS("chemicals"),
	ENZYME("enzyme"),
	GENE("gene"),
	KIT("kit"),
	MOLECULE("molecule"),
	OLIGO("oligo"),
	PEPTIDE("peptide"),
	PHOENIXPEPTIDE("phoenixpeptide"),
	PROTEIN("protein");

	private final String value;

	ProductDetailType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ProductDetailType fromValue(String v) {
		for (ProductDetailType c : ProductDetailType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
