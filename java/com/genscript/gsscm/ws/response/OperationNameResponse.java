package com.genscript.gsscm.ws.response;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "OperationNameResponse", namespace = WsConstants.NS)
public class OperationNameResponse extends WsResponse{
	/**
	 * fangquan
	 * 2011-11-29
	 */
	
	private String status;//工序名称

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
