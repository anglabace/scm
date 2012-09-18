package com.genscript.gsscm.common.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.customer.dto.PaymentDTO;
import com.genscript.gsscm.customer.service.CustomerPointsHistoryService;

public class CalCustPointsJob {
	@Autowired
	private CustomerPointsHistoryService customerPointsHistoryService;
	
	public void execute() {
		try {
			String random = SessionUtil.generateTempId();
			String shortName = "/tmp/genXml/";
			String separator = System.getProperty("file.separator");
			if (separator.equals("\\")) {// windows
				shortName = "C:" + shortName;
			} else {// linux etc.

			}
			List<PaymentDTO> list = customerPointsHistoryService.getPaymentList(shortName, "custom_payment/");
			customerPointsHistoryService.paymentToPoint(list);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
