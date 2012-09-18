package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.OrderAddress;

@XmlType(name = "OrderMultiAddrDTO", namespace = WsConstants.NS)
public class OrderMultiAddrDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4482095455696599881L;
	private String addrDisplay;
	private String addrStr;
	private String itemIdStr;
	private OrderAddress orderAddress;

	public String getAddrDisplay() {
		return addrDisplay;
	}

	public void setAddrDisplay(String addrDisplay) {
		this.addrDisplay = addrDisplay;
	}

	public String getAddrStr() {
		return addrStr;
	}

	public void setAddrStr(String addrStr) {
		this.addrStr = addrStr;
	}

	public String getItemIdStr() {
		return itemIdStr;
	}

	public void setItemIdStr(String itemIdStr) {
		this.itemIdStr = itemIdStr;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

}
