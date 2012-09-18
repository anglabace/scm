package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.OperationNameRequest;
import com.genscript.gsscm.ws.response.OperationNameResponse;

@WebService(name = "OperationNameWebService", targetNamespace = WsConstants.NS)
public interface OperationNameWebService {
	
	public OperationNameResponse getOperationName(@WebParam(name="OperationNameRequest")OperationNameRequest operationNameRequest);

}
