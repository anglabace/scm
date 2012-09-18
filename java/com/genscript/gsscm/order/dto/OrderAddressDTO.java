package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.OrderAddress;

@XmlType(name = "OrderAddressDTO", namespace = WsConstants.NS)
public class OrderAddressDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4249255581235405052L;
	private OrderAddress billToAddr;
	private OrderAddress shipToAddr;
	private OrderAddress soldToAddr;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the billToAddr
	 */
	public OrderAddress getBillToAddr() {
		return billToAddr;
	}

	/**
	 * @param billToAddr
	 *            the billToAddr to set
	 */
	public void setBillToAddr(OrderAddress billToAddr) {
		this.billToAddr = billToAddr;
	}

	/**
	 * @return the shipToAddr
	 */
	public OrderAddress getShipToAddr() {
		return shipToAddr;
	}

	/**
	 * @param shipToAddr
	 *            the shipToAddr to set
	 */
	public void setShipToAddr(OrderAddress shipToAddr) {
		this.shipToAddr = shipToAddr;
	}

	/**
	 * @return the soldToAddr
	 */
	public OrderAddress getSoldToAddr() {
		return soldToAddr;
	}

	/**
	 * @param soldToAddr
	 *            the soldToAddr to set
	 */
	public void setSoldToAddr(OrderAddress soldToAddr) {
		this.soldToAddr = soldToAddr;
	}

}
