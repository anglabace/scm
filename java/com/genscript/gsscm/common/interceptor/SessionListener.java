package com.genscript.gsscm.common.interceptor;

import java.util.HashMap;   
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener; 
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@SuppressWarnings("unchecked")
public class SessionListener implements ServletContextListener, HttpSessionListener, 
	HttpSessionAttributeListener { 
 
	/**    
	 * 定义监听的session属性名     
	 */
	public final static String LISTENER_NAME = "username";  
	
 	/**    
	 * 定义存储客户登录session的集合  
	 */
	private static Map sessions = new HashMap();   
	             
	// 声明ServletContext对象
	private static ServletContext application = null;   
	     
	
	// 容器初始化
	@SuppressWarnings("static-access")      
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		this.application = sce.getServletContext();   
	}
	
	/**
	 * 加入session时的监听方法.
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		if (LISTENER_NAME.equals(sbe.getName())) {  
			String userName = "login_" + sbe.getValue();
			if (null != sessions.get(userName) && !("").equals(sessions.get(userName))) {
				String modelCode = sessions.get(userName).toString();
				Object obj = application != null? application.getAttribute(modelCode): null;
				if (obj != null && userName.equals(obj.toString())) {
					//用户如果重复登陆则删除application中前一次登陆操作的模块记录
					application.removeAttribute(modelCode);
				}
			}
			sessions.put(userName, "");
		}   
	}

	/**
	 * session失效时的监听方法.
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		if (LISTENER_NAME.equals(sbe.getName())) {
			Object obj = sessions.get("login_" + sbe.getValue());
			if (null != obj && !("").equals(obj.toString())) {
				Object objapp = application != null? application.getAttribute(obj.toString()): null;
				if (objapp != null) {
					application.removeAttribute(obj.toString());
				}
				sessions.remove("login_" + sbe.getValue());  
			}
		} 
	}

	/**
	 * session覆盖时的监听方法.
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		// TODO Auto-generated method stub

	}

	/**
	 * 返回客户登录session的集合
	 * @return
	 */
	public static Map getSessions() {   
        return sessions;   
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sce) { 
		// TODO Auto-generated method stub
	}

	public static ServletContext getApplication() {
		return application;
	}
}
