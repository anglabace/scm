package com.genscript.gsscm.common.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.genscript.gsscm.common.events.NewPartEvent;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.product.dto.ProductDTO;

@Component
public class NewPartJob implements ApplicationListener<NewPartEvent>{
	private static Logger logger = LoggerFactory.getLogger(NewPartJob.class);
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
	
	@Override
	public void onApplicationEvent(NewPartEvent event) {
		logger.debug("Received a NewCustomerEvent");
		this.execute(event);
	}
	
	public void execute(NewPartEvent event){
		ProductDTO productDTO = event.getProductDTO();
		erpSalesOrderService.createPart(productDTO);
	}
}
