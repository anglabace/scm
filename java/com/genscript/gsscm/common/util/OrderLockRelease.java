package com.genscript.gsscm.common.util;

import java.util.Map;
import javax.servlet.ServletContext;

public class OrderLockRelease {
	/**
	 * 释放订单锁
	 */
	@SuppressWarnings("unchecked")
	public void releaseOrderLock () {
		String userName = SessionUtil.getUserName();
		String loginUserName = "login_"+userName;
		ServletContext application = com.genscript.gsscm.common.interceptor.SessionListener.getApplication();
		Map sessions = com.genscript.gsscm.common.interceptor.SessionListener.getSessions();
		//application中含有该单号
		if (application != null && sessions != null) {
			if (null != sessions.get(loginUserName) && !("").equals(sessions.get(loginUserName).toString())) {
				application.removeAttribute(sessions.get(loginUserName).toString());
				sessions.put(loginUserName, "");
			}
		}
		
	}
	/**
	 * 检验订单是否正被别人编辑
	 * @param url
	 * @return
	 */
	public String checkOrderStatus (String url) {
		String byUser = null;
		String userName = SessionUtil.getUserName();
		if (url.contains("&")) {
			url = url.substring(0, url.indexOf("&"));
		}
		String loginUserName = "login_"+userName;
		ServletContext application = com.genscript.gsscm.common.interceptor.SessionListener.getApplication();
		if (application != null) {
			//application中含有该单号
			if (application.getAttribute(url) != null) {
				//单号正被其他人操作
				String appByUser = application.getAttribute(url).toString(); 
				if (!(loginUserName).equals(appByUser)) {
					byUser = application.getAttribute(url).toString();
					byUser = byUser.substring(("login_").length());
				}
			} 
		}
		return byUser;
	}
}
