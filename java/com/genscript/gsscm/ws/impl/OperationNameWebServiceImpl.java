package com.genscript.gsscm.ws.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.manufacture.service.OperationNameService;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.OperationNameWebService;
import com.genscript.gsscm.ws.request.OperationNameRequest;
import com.genscript.gsscm.ws.response.OperationNameResponse;
@WebService(serviceName = "OperationNameWebService", portName = "OperationNameWebServicePort", endpointInterface = "com.genscript.gsscm.ws.api.OperationNameWebService", targetNamespace = WsConstants.NS)
public class OperationNameWebServiceImpl implements OperationNameWebService {
	@Autowired
	private OperationNameService operationNameService;
	@Autowired
	private ExceptionService exceptionUtil;
	
	/**
	 * fangquan
	 * 2011-11-29
	 * 查询工序名称
	 */
	@Override
	public OperationNameResponse getOperationName(OperationNameRequest operationNameRequest) {
		OperationNameResponse operationNameResponse = new OperationNameResponse();
		Integer order_No = operationNameRequest.getOrder_No();
		Integer item_No = operationNameRequest.getItem_No();
		Integer loginUserId = operationNameRequest.getUserId();
		try {
			String status= operationNameService.getOperationName(order_No, item_No);
			
			operationNameResponse.setHasException(false);
			operationNameResponse.setStatus(status);
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			operationNameResponse.setHasException(true);
			operationNameResponse.setWsException(exDTO);
		}
		return operationNameResponse;
	}



}
