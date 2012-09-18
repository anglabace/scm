package com.genscript.gsscm.ws.client;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

/**
 * GetUser方法的返回结果类型.
 * 
 * @author golf
 */

@XmlType(name = "GetUserResult", namespace = WsConstants.NS)
public class GetUserResult extends WSResult {

	private com.genscript.gsscm.ws.client.UserDTO user;

	public com.genscript.gsscm.ws.client.UserDTO getUser() {
		return user;
	}

	public void setUser(com.genscript.gsscm.ws.client.UserDTO user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}