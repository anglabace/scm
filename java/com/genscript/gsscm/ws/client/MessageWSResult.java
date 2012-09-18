package com.genscript.gsscm.ws.client;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.privilege.dto.UserDTO;

@XmlType(name = "MessageWSResult", namespace = WsConstants.NS)
public class MessageWSResult extends WSResult {

	
	private UserDTO user;

	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
}
