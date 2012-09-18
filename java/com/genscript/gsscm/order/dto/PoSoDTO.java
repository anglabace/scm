package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ERPPoSoDTO", namespace = WsConstants.NS)
public class PoSoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3438272984283290809L;
	private String erpSoNo;

	public String getErpSoNo() {
		return erpSoNo;
	}

	public void setErpSoNo(String erpSoNo) {
		this.erpSoNo = erpSoNo;
	}
	
}
