package com.genscript.gsscm.quote.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.quote.entity.QuoteAddress;

@XmlType(name = "QuoteMultiAddrDTO", namespace = WsConstants.NS)
public class QuoteMultiAddrDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4482095455696599881L;
	private String addrDisplay;
	private String addrStr;
	private String itemIdStr;
	private QuoteAddress QuoteAddress;

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

	public QuoteAddress getQuoteAddress() {
		return QuoteAddress;
	}

	public void setQuoteAddress(QuoteAddress quoteAddress) {
		QuoteAddress = quoteAddress;
	}


}
