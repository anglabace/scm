package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.MailRequest;
import com.genscript.gsscm.ws.response.MailRequestResponse;

@WebService(name = "MailService", targetNamespace = WsConstants.NS)
public interface MailCampaignWebService {
	public MailRequestResponse getRoleByUserId(@WebParam(name="mailRequest")MailRequest mailRequest);
	public MailRequestResponse getCustomerDetailByCustNo(@WebParam(name="mailRequest")MailRequest mailRequest);
	public MailRequestResponse getContactDetailByContactNo(@WebParam(name="mailRequest")MailRequest mailRequest);
}
