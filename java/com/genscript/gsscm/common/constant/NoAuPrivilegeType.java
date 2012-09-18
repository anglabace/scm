package com.genscript.gsscm.common.constant;

public enum NoAuPrivilegeType {

    privilege("privilege!parentPath.action"), 
	logout("logout.action"), left("privilege!menuListForUser.action"), 
	main("privilege!main.action"), top("privilege!top.action"),
	search("my_search!mySearchList.action"),
	get_territory_by_country("get_territory_by_country.action"),
	base_data_getTermList("base_data!getTermList.action"),
	base_data_getSourceList("base_data!getSourceList.action"),
	org_picker("org_picker.action"),
	org_picker_list("org_picker!list.action"),
	org_picker_showDivList("org_picker!showDivList.action"),
	org_picker_showDeptList("org_picker!showDeptList.action"),
	get_all_country_state("get_all_country_state.action"),
	get_loc_json_getOrgLocJson("get_loc_json!getOrgLocJson.action"),
	get_loc_json_getDivLocJson("get_loc_json!getDivLocJson.action"),
	get_loc_json_getDeptLocJson("get_loc_json!getDeptLocJson.action"),
	Qu_Order_test_product_product_relation_form("qu_order!testProductProductRelationForm.action"),
	Download("download.action"),
	space(""); 
	private final String value;

	NoAuPrivilegeType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static NoAuPrivilegeType fromValue(String v) {
		for (NoAuPrivilegeType c : NoAuPrivilegeType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
	
	public static Boolean booleanValue(String v) {
		if (v.contains("/")) {
			v = v.substring(v.lastIndexOf("/")+1,v.length());
		}
		for (NoAuPrivilegeType c : NoAuPrivilegeType.values()) {
			if (c.value.equals(v)) {
				return true;
			}
		}
		return false;
	}
}
