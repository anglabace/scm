package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ORFCloneSearchType")
@XmlEnum
public enum ORFCloneSearchType {
	@XmlEnumValue("REFSEQ")
	REFSEQ("REFSEQ"),
	@XmlEnumValue("GENE")
	GENE("GENE"),
	@XmlEnumValue("KEYWORDS")
	KEYWORDS("KEYWORDS"),
	@XmlEnumValue("Refseq Accession Number(e.g.,NM_005098)")
	REFSEQACCESSIONNUMBER("Refseq Accession Number(e.g.,NM_005098)"),
	@XmlEnumValue("GenScript Gene ID(e.g.,GN106946)")
	GENSCRIPTGENEID("GenScript Gene ID(e.g.,GN106946)"),
	@XmlEnumValue("Keyword")
	KEYWORD("Keyword")
	;
	
	private final String value;

	ORFCloneSearchType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}
	
	public static ORFCloneSearchType fromValue(String v) {
		for (ORFCloneSearchType c : ORFCloneSearchType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
