package com.genscript.gsscm.accounting.web;

import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.service.OverDueService;

/**
 * 定时执行的程序
 * @author Administrator
 *
 */
@Component("initialServlet")
public class InitialServlet  extends HttpServlet{

	private Timer timer = null;
	

//	@Override
//	public void contextDestroyed(ServletContextEvent event) {
//		timer = new Timer(true);
//		timer.schedule(new OverDueService(), 0,
//				 60 * 60 * 1000);// 每小时执行一次
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent event) {
//		timer.cancel();
//		event.getServletContext().log("定时器销毁");
//		System.out.println("销毁定时器...");
//	}

	@Override
	public void destroy() {
		timer.cancel();
		System.out.println("销毁定时器...");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		timer = new Timer(true);
		ServletContext context = config.getServletContext();
		timer.schedule(new OverDueService(context),1*60*1000,
				 24 * 60 * 60 * 1000);// 每24小时执行一次,5分钟后执行
	}
	
	

}
