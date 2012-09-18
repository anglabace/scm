package com.genscript.gsscm.customer.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "DefaultAddressDTO", namespace = WsConstants.NS)
public class DefaultAddressDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8530086308625712799L;
	private CustAddrOperDTO defBillToAddr;
	private CustAddrOperDTO defShipToAddr;
	public CustAddrOperDTO getDefBillToAddr() {
		return defBillToAddr;
	}
	public void setDefBillToAddr(CustAddrOperDTO defBillToAddr) {
		this.defBillToAddr = defBillToAddr;
	}
	public CustAddrOperDTO getDefShipToAddr() {
		return defShipToAddr;
	}
	public void setDefShipToAddr(CustAddrOperDTO defShipToAddr) {
		this.defShipToAddr = defShipToAddr;
	}
	
}
