package com.genscript.gsscm.accounting.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
@Transactional
public class OverDueService extends TimerTask{
	ArInvoiceDao arInvoiceDao ;
	ServletContext context ;
	public OverDueService(ServletContext context){
		this.context = context;
	}
	
	@Override
	public void run() {
		Date d = new Date();
		//每晚23点-24点执行
		if(d.getHours() >= 23){
	
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
			org.apache.commons.dbcp.BasicDataSource dataSource = (org.apache.commons.dbcp.BasicDataSource)wac.getBean("dataSource");
			arInvoiceDao = (ArInvoiceDao) wac.getBean("arInvoiceDao");
			Connection conn = null;
			try {
				conn =  dataSource.getConnection();
				int r = this.arInvoiceDao.overDue(conn);
				System.out.println("更新了"+r+"条数据");
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			AutoCreateService autoCreateService=(AutoCreateService)wac.getBean("autoCreateService");
			try {
				 conn =  dataSource.getConnection();
				 autoCreateService.CreateService(conn);
				 conn=null;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("");
			}
			
			
			
		}else{
//			try {
//				Thread.sleep(60000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("dfsdfsdfsdfsdf+++++++++");
//			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
//			
////			SessionFactory sessionFactory = (SessionFactory) wac.getBean("sessionFactory");
////			System.out.println("sessionFactory="+sessionFactory);
//			ArInvoiceDao dao = (ArInvoiceDao) wac.getBean("arInvoiceDao");
//			boolean flag = dao.checkInvoiceNoExists("123");
//			System.out.println(flag);
		}
	}
	



}
