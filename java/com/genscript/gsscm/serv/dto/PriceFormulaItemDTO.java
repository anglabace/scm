package com.genscript.gsscm.serv.dto;

import com.genscript.gsscm.serv.entity.PriceFormulaItems;

public class PriceFormulaItemDTO {
	private PriceFormulaItems priceFormulaItem;
	private String valueName;
	public PriceFormulaItems getPriceFormulaItem() {
		return priceFormulaItem;
	}
	public void setPriceFormulaItem(PriceFormulaItems priceFormulaItem) {
		this.priceFormulaItem = priceFormulaItem;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	
}
