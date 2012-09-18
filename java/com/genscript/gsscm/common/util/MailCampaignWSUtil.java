package com.genscript.gsscm.common.util;

import java.util.HashMap;
import java.util.Map;

public class MailCampaignWSUtil {
	private static Map<String,String> userIdMap=new HashMap<String, String>();
	
	public static String getSessionIdByUserId(String userId){
		return userIdMap.get(userId);
	}
	public static void setUserIdMap(String userId,String sessionUserId){
		userIdMap.put(userId, sessionUserId);
	}
	public static void rmoveUserIdMap(String userId){
		userIdMap.remove(userId);
	}
}
