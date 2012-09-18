package com.genscript.gsscm.common.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.genscript.gsscm.common.events.SendMailAtOnceEvent;
import com.genscript.gsscm.contact.service.ContactService;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.quote.service.QuoteService;

public class MailSendingJob implements ApplicationListener<SendMailAtOnceEvent>{

	private static Logger logger = LoggerFactory.getLogger(MailSendingJob.class);
	
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ContactService contactService;
	
	public void execute(){
		quoteService.sendQuoteMailJob();
		orderService.sendOrderMailJob();
		customerService.sendCustMailJob();
		contactService.sendCntctMailJob();
		logger.debug("The cron job executed");
	}

	@Override
	public void onApplicationEvent(SendMailAtOnceEvent event) {
		System.out.println("Received a SendMailAtOnceEvent");
		this.execute();
	}
}
