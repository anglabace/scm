package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.PublicRequest;
import com.genscript.gsscm.ws.response.PublicResponse;

@WebService(name = "PublicService", targetNamespace = WsConstants.NS)
public interface PublicWebService {

	public PublicResponse getDropdownListOptions(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getSrchAttrList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse saveMySrch(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse delMySrch(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getMySrchList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getSpecDropDownList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getCountryList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getStateList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getTerritoryByCountry(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getSourceDetail(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getContactInfo(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getOrganizationList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getDivisionList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getDepartmentList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getContentTmplList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getAllCountryState(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getOrgLocation(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getDivLocation(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getDeptLocation(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getMessageTmplList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getTemplateNameList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getZipCodeList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getStatusDropDownList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getPrmtCdBySource(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse isPromotionExist(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getOrderMailContentTemplateList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse getCustMailContentTemplateList(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse changeCurrency(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse searchORFCloneGene(@WebParam(name="publicRequest")PublicRequest publicRequest);
	public PublicResponse calculateServicePrice(@WebParam(name="publicRequest")PublicRequest publicRequest);
}
