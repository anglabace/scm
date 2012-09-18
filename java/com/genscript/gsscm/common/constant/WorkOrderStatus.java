package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "WorkOrderStatus")
@XmlEnum
/**
 * 
 */
public enum WorkOrderStatus {
	@XmlEnumValue("New")
	New("New"),
	@XmlEnumValue("In Production")
	Inprogress("In Production"),
	@XmlEnumValue("Production Complete")
	Completed("Production Complete"),
	@XmlEnumValue("QC Failed")
	QCFailed("QC Failed"),
	@XmlEnumValue("Product QC Failed")
	ProductQCFailed("Product QC Failed"),
	@XmlEnumValue("Document QC Failed")
	DocumentQCFailed("Document QC Failed"),
	@XmlEnumValue("Product QC Passed")
	ProductQCPassed("Product QC Passed"),
	@XmlEnumValue("Document QC Passed")
	DocumentQCPassed("Document QC Passed"),
	@XmlEnumValue("Closed")
	Closed("Closed"),
	@XmlEnumValue("QC Partial")
	QCPartial("QC Partial"),
	@XmlEnumValue("Product QC Partial")
	ProductQCPartial("Product QC Partial"),
	@XmlEnumValue("Document QC Partial")
	DocumentQCPartial("Document QC Partial"),
	@XmlEnumValue("Canceled")
	Canceled("Canceled");
	
	
	private final String value;

	WorkOrderStatus(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static WorkOrderStatus fromValue(String v) {
		for (WorkOrderStatus c : WorkOrderStatus.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
