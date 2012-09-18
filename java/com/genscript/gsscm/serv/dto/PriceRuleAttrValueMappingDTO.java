package com.genscript.gsscm.serv.dto;

import com.genscript.gsscm.serv.entity.PriceRuleAttrValueMapping;

public class PriceRuleAttrValueMappingDTO {
	private PriceRuleAttrValueMapping priceRuleValue;
	private String attributerName;
	private String sessionId;
	public PriceRuleAttrValueMapping getPriceRuleValue() {
		return priceRuleValue;
	}
	public void setPriceRuleValue(PriceRuleAttrValueMapping priceRuleValue) {
		this.priceRuleValue = priceRuleValue;
	}
	public String getAttributerName() {
		return attributerName;
	}
	public void setAttributerName(String attributerName) {
		this.attributerName = attributerName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
