package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "SequenceType")
@XmlEnum
public enum SequenceType {
	@XmlEnumValue("DNA")
    DNA("DNA"),
    @XmlEnumValue("Protein")
    Protein("Protein"),
    @XmlEnumValue("Length")
    Length("Length");
	
	private final String value;

	SequenceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SequenceType fromValue(String v) {
        for (SequenceType c : SequenceType.values()) {
            if (c.value.equals(v)) {
                return c;

            }
        }
        throw new IllegalArgumentException(v);
    }
}
