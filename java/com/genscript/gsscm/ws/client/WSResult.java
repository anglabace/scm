package com.genscript.gsscm.ws.client;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.WSException;

/**
 * WebService返回结果基类,包含返回码定义.
 * 
 * @author golf
 */
@XmlType(name = "WSResult", namespace = WsConstants.NS)
public class WSResult {


	// WSResult基本属性
	protected boolean hasException;
	protected WSException wsException;

	public WSException getWsException() {
		return wsException;
	}

	public void setWsException(WSException wsException) {
		this.wsException = wsException;
	}

	public boolean isHasException() {
		return hasException;
	}

	public void setHasException(boolean hasException) {
		this.hasException = hasException;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
