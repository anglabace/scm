package com.genscript.gsscm.ws.request;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "OperationNameRequest", namespace = WsConstants.NS)
public class OperationNameRequest extends WsRequest {
	
	/**
	 * fangquan
	 * 2011-11-29
	 */
	
	private Integer order_No;//生产单号
	
	private Integer item_No;//产品单号
		
	public Integer getOrder_No() {
		return order_No;
	}
	public void setOrder_No(Integer order_No) {
		this.order_No = order_No;
	}
	public Integer getItem_No() {
		return item_No;
	}
	public void setItem_No(Integer item_No) {
		this.item_No = item_No;
	}
	
	
	
}
