package com.genscript.gsscm.serv.dto;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.serv.entity.PriceFormulaCriterias;

@XmlType(name = "PriceFormulaCriteriasDTO", namespace = WsConstants.NS)
public class PriceFormulaCriteriasDTO {
	private PriceFormulaCriterias priceFormulasCriterias;
	private String attributerName;
	private String createdByText;
	public PriceFormulaCriterias getPriceFormulasCriterias() {
		return priceFormulasCriterias;
	}
	public void setPriceFormulasCriterias(
			PriceFormulaCriterias priceFormulasCriterias) {
		this.priceFormulasCriterias = priceFormulasCriterias;
	}
	public String getCreatedByText() {
		return createdByText;
	}
	public void setCreatedByText(String createdByText) {
		this.createdByText = createdByText;
	}
	public String getAttributerName() {
		return attributerName;
	}
	public void setAttributerName(String attributerName) {
		this.attributerName = attributerName;
	}
}
