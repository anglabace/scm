package com.genscript.gsscm.common.util;

import java.util.List;


import com.genscript.gsscm.privilege.entity.Privilege;

public class PrivilegeServletUtil {
	
	private static List<Privilege> privilegeList;

	public static List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public static void setPrivilegeList(List<Privilege> privilegeList) {
		PrivilegeServletUtil.privilegeList = privilegeList;
	}	
}
