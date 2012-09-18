package com.genscript.gsscm.common.jobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.genscript.gsscm.common.constant.QuoteStatusType;
import com.genscript.gsscm.common.events.SendMailAtOnceEvent;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.contact.service.ContactService;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.quote.dao.QuoteMainBeanDao;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.quote.entity.QuoteMainBean;
import com.genscript.gsscm.quote.service.QuoteService;
import com.opensymphony.xwork2.ActionSupport;

public class DateTimerJob {
	
		@Autowired
		private QuoteService quoteService;
		@Autowired(required = false)
		private DozerBeanMapper dozer;
		private Date tmpDate=null;
		private SimpleDateFormat sdf=null;
		private String quoteDate=null;
		private String tmpStrDate=null;
		public void execute(){
		
				//from后面是对象，不是表名
				List<QuoteMain> list=quoteService.getAllQuoteList();
				tmpDate=new Date();
				sdf=new SimpleDateFormat("yyyy-MM-dd");
				tmpStrDate=sdf.format(tmpDate);
				for(int i=0;i<list.size();i++){
					QuoteMain quoteMain=list.get(i);
					Date exprDate=quoteMain.getExprDate();
					if(null!=exprDate){
					quoteDate=sdf.format(exprDate);
					}
			        try {
			        	if(tmpStrDate.equals(quoteDate)){
							String status=quoteMain.getStatus();
							if(status!=QuoteStatusType.CO.value()&&status!="CW"&&status!="OD"){
								quoteMain.setStatus("OD");
								quoteService.saveQuote(quoteMain);
							
			        	}
			        }
			        } catch (Exception e) {
			          
			        }
						
				}
		}
				
}
