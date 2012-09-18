package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.CustomerRequest;
import com.genscript.gsscm.ws.response.CustomerResponse;

@WebService(name = "CustomerService", targetNamespace = WsConstants.NS)
public interface CustomerWebService {
	
	public CustomerResponse searchCustomerList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
	public CustomerResponse saveCustomer(@WebParam(name="customerRequest")CustomerRequest customerRequest);
	public CustomerResponse getCustmerDetail(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse delCustomer(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getCustAddrList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getTermsList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getCustGrantsList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getCustPublicationsList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getCustCaseList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getCustInfoStat(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getCustIntList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getCustContactList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getContactHistory(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getAccessStat(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse searchAccess(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getPageAccess(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getProductAccess(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getServiceAccess(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse searchAnalysis(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getSpecialPriceList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getSpecialPrice(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse saveCreditCard(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse delCreditCard(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getDefaultAddress(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse getNoteList(@WebParam(name="customerRequest")CustomerRequest customerRequest);
    public CustomerResponse combineCustomer(@WebParam(name="customerRequest")CustomerRequest customerRequest);
}
