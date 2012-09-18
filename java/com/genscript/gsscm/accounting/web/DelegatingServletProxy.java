package com.genscript.gsscm.accounting.web;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 自定义一个用于代理启动Servlet的类DelegatingServletProxy：
 * @author Administrator
 *
 */
public class DelegatingServletProxy extends GenericServlet {

	
	private Servlet proxy;
	private String targetBean;

	
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		proxy.service(req, res);
	}

	@Override
	public void init() throws ServletException {
		this.targetBean = getServletName(); 
		getServletBean();
		proxy.init(getServletConfig());
	}

	public String getTargetBean() {
		return targetBean;
	}

	public void setTargetBean(String targetBean) {
		this.targetBean = targetBean;
	}

	private void getServletBean() {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (Servlet)wac.getBean(targetBean);
	}

	
}
