package com.genscript.gsscm.common.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.genscript.gsscm.common.events.NewCustomerEvent;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;

@Component
public class NewCustomerJob implements ApplicationListener<NewCustomerEvent>{

	private static Logger logger = LoggerFactory.getLogger(NewCustomerJob.class);
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
	
	@Override
	public void onApplicationEvent(NewCustomerEvent event) {
		logger.debug("Received a NewCustomerEvent");
		this.execute(event);
	}

	public void execute(NewCustomerEvent event){
		CustomerDTO customerDTO = event.getCustomerDTO();
		String custNoStr = event.getCustNoStr();
		erpSalesOrderService.createCustomer(customerDTO, custNoStr);
	}
}
