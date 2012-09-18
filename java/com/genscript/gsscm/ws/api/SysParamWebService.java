package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.SysParamRequest;
import com.genscript.gsscm.ws.response.SysParamResponse;

@WebService(name = "SysParamService", targetNamespace = WsConstants.NS)
public interface SysParamWebService {

	public SysParamResponse getMessage(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse saveMessage(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse deleteMessage(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse listMessage(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse getMsgDet(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse saveMsgDet(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse delMsgDet(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse listMsgDet(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse getApplication(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse saveApplication(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse delApplication(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse listApplication(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse getAppIrf(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse saveAppIrf(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse delAppIrf(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse listAppIrf(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse getAppMod(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse saveAppMod(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse delAppMod(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse listAppMod(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse logException(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
	
	public SysParamResponse searchException(@WebParam(name="sysParamRequest")SysParamRequest sysParamRequest);
}
