package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.PrivilegeRequest;
import com.genscript.gsscm.ws.response.PrivilegeResponse;

@WebService(name = "PrivilegeService", targetNamespace = WsConstants.NS)
public interface PrivilegeWebService {
	public PrivilegeResponse getUser(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getUserListByRole(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getPvlgListByRole(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getPvlgListByUser(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getRoleListByUser(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getRole(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse saveUser(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse saveRole(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse savePrivilege(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getTree(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse searchUser(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse searchRole(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getMenuListForUser(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getUIListForUser(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse login(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse logout(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getLoginList(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
	public PrivilegeResponse getParentPathList(@WebParam(name="privilegeRequest")PrivilegeRequest privilegeRequest);
}
