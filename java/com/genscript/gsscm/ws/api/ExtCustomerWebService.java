package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.ExtCustomerRequest;
import com.genscript.gsscm.ws.response.ExtCustomerResponse;

@WebService(name = "ExtCustomerService", targetNamespace = WsConstants.NS)
public interface ExtCustomerWebService {
	public ExtCustomerResponse getTerritoryInfo(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
	public ExtCustomerResponse searchSalesByTerritory(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
	public ExtCustomerResponse searchDisCount(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
	public ExtCustomerResponse searchShippingCost(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
	public ExtCustomerResponse searchNationalSalesTaxRate(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
	public ExtCustomerResponse searchInitShippingCost(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
	public ExtCustomerResponse searchInitShippingTotal(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
	public ExtCustomerResponse searchInitOrderShippingCost(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
	public ExtCustomerResponse searchInitQuoteShippingCost(@WebParam(name="extCustomerRequest")ExtCustomerRequest extCustomerRequest);
}
