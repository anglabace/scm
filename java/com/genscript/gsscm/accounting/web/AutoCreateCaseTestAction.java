package com.genscript.gsscm.accounting.web;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.genscript.gsscm.accounting.service.AutoCreateService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.impl.SessionFactoryImpl;

public class AutoCreateCaseTestAction extends ActionSupport  {

	
	
	
	
	
	
	AutoCreateService autoCreateService;
	
	
	
	public String TestCase()
	{ 
		//ActionContext context = ActionContext.getContext();
		ServletContext context=ServletActionContext.getServletContext();
 
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
//		org.apache.commons.dbcp.BasicDataSource dataSource = (org.apache.commons.dbcp.BasicDataSource)wac.getBean("dataSource");
		autoCreateService=(AutoCreateService)wac.getBean("autoCreateService");
		if(null==autoCreateService)
		System.out.println("@@@@@@@@@1111111111@@@@@@@@@@@@@@@@");
		else
		System.out.println("$$$$$$$$$2222222222$$$$$$$$$$$$$$$$");	
		org.apache.commons.dbcp.BasicDataSource dataSource = (org.apache.commons.dbcp.BasicDataSource)wac.getBean("dataSource");
		autoCreateService.init((SessionFactoryImpl)wac.getBean("sessionFactory"));
		try{
		autoCreateService.CreateService(dataSource.getConnection());
		}catch( Exception e)
		{
			e.printStackTrace();
		}
		//autoCreateService.testCase1();
		return null;
	}
	
	
	
}
