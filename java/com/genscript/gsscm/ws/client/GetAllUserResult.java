package com.genscript.gsscm.ws.client;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.privilege.dto.UserDTO;

/**
 * GetAllUser方法的返回结果类型.
 * 
 * @author golf
 */

@XmlType(name = "GetAllUserResult", namespace = WsConstants.NS)
public class GetAllUserResult extends WSResult {

	private List<UserDTO> userList;

	public List<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}
}