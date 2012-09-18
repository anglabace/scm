package com.genscript.gsscm.quote.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.quote.entity.QuoteAddress;


@XmlType(name = "QuoteAddressDTO", namespace = WsConstants.NS)
public class QuoteAddressDTO {
    private QuoteAddress billToAddr;
    private QuoteAddress shipToAddr;
    private QuoteAddress soldToAddr;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the billToAddr
	 */
	public QuoteAddress getBillToAddr() {
		return billToAddr;
	}
	/**
	 * @param billToAddr the billToAddr to set
	 */
	public void setBillToAddr(QuoteAddress billToAddr) {
		this.billToAddr = billToAddr;
	}
	/**
	 * @return the shipToAddr
	 */
	public QuoteAddress getShipToAddr() {
		return shipToAddr;
	}
	/**
	 * @param shipToAddr the shipToAddr to set
	 */
	public void setShipToAddr(QuoteAddress shipToAddr) {
		this.shipToAddr = shipToAddr;
	}
	/**
	 * @return the soldToAddr
	 */
	public QuoteAddress getSoldToAddr() {
		return soldToAddr;
	}
	/**
	 * @param soldToAddr the soldToAddr to set
	 */
	public void setSoldToAddr(QuoteAddress soldToAddr) {
		this.soldToAddr = soldToAddr;
	}

}
