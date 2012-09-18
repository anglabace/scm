package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.ContactRequest;
import com.genscript.gsscm.ws.response.ContactResponse;

@WebService(name = "ContactService", targetNamespace = WsConstants.NS)
public interface ContactWebService {
	
	public ContactResponse searchContactList(@WebParam(name="contactRequest")ContactRequest contactRequest);
	public ContactResponse getContactDetail(@WebParam(name="contactRequest")ContactRequest contactRequest);
	public ContactResponse saveContact(@WebParam(name="contactRequest")ContactRequest contactRequest);
    public ContactResponse delContact(@WebParam(name="contactRequest")ContactRequest contactRequest);
    public ContactResponse getContactsList(@WebParam(name="contactRequest")ContactRequest contactRequest);
    public ContactResponse getAddrList(@WebParam(name="contactRequest")ContactRequest contactRequest);
    public ContactResponse getGrantsList(@WebParam(name="contactRequest")ContactRequest contactRequest);
    public ContactResponse getPublicationsList(@WebParam(name="contactRequest")ContactRequest contactRequest);
    public ContactResponse getContactHistory(@WebParam(name="contactRequest")ContactRequest contactRequest);
}
