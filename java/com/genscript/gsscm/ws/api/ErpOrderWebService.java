package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.ErpOrderRequest;
import com.genscript.gsscm.ws.response.ErpOrderResponse;

@WebService(name = "ERPOrderService", targetNamespace = WsConstants.NS)
public interface ErpOrderWebService {
	public ErpOrderResponse updateOrderPoSo(@WebParam(name="ERPOrderRequest")ErpOrderRequest eRPOrderRequest);
	public ErpOrderResponse updateCustomer(@WebParam(name="ERPOrderRequest")ErpOrderRequest eRPOrderRequest);
	public ErpOrderResponse getPeptidePrice(@WebParam(name="ERPOrderRequest")ErpOrderRequest eRPOrderRequest);
	public ErpOrderResponse getOligoPrice(@WebParam(name="ERPOrderRequest")ErpOrderRequest eRPOrderRequest);
}
