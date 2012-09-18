package com.genscript.gsscm.common.jobs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.shipment.service.ShipmentsService;
import com.genscript.gsscm.ws.WSException;



public class FedexGetStatusJob {
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ShipmentsService shipmentsService;
	@Autowired
	private FileService fileService;
	
	public void execute(){
		System.out.println("fedex status>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
			//HttpServletRequest request = Struts2Util.getRequest();
			//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			this.shipmentsService.getStatusOfFedex(fileService.getUploadPath()+"shipped", "shipped/","");
		} catch (IOException ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		
	}
}
