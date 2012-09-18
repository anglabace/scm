package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.SysSetRequest;
import com.genscript.gsscm.ws.response.SysSetResponse;

@WebService(name = "SysSetService", targetNamespace = WsConstants.NS)
public interface SysSetWebService {
	public SysSetResponse searchSourceList(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse searchPromotionList(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse saveSource(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse savePromotion(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse searchShipMethodList(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse getZoneAndRateList(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse getShipRateByZone(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse getShipMethodDetail(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse delShipMethod(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
	public SysSetResponse saveShipMethod(@WebParam(name="sysSetRequest")SysSetRequest sysSetRequest);
}
