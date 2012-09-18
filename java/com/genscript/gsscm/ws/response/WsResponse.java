package com.genscript.gsscm.ws.response;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.ws.WSException;

public class WsResponse {

	protected Boolean hasException;
	protected WSException wsException;
	
	public WSException getWsException() {
		return wsException;
	}

	public void setWsException(WSException wsException) {
		this.wsException = wsException;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the hasException
	 */
	public Boolean getHasException() {
		return hasException;
	}

	/**
	 * @param hasException the hasException to set
	 */
	public void setHasException(Boolean hasException) {
		this.hasException = hasException;
	}
}
