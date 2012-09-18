package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.serv.entity.PriceFormulaItems;
import com.genscript.gsscm.serv.entity.PriceFormulas;

@XmlType(name = "PriceFormlaDTO", namespace = WsConstants.NS)
public class PriceFormulaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4395411218358052886L;
	private PriceFormulas priceFormulas;
	private String createdByText;
	private String modifiedByText;
	private String sessionId;
	private List<PriceFormulaCriteriasDTO> priceFormlaCriteriasDTO;
	private List<PriceFormulaItems> priceFormulaItems;
	private List<Integer> delPriceFormulaItems;
	private List<Integer> delPriceFormlaCriterias;
	
	public PriceFormulaDTO(){
		priceFormlaCriteriasDTO = new ArrayList<PriceFormulaCriteriasDTO>();
		priceFormulaItems = new ArrayList<PriceFormulaItems>();
		delPriceFormulaItems = new ArrayList<Integer>();
		delPriceFormlaCriterias = new ArrayList<Integer>();
	}
	
	public PriceFormulas getPriceFormulas() {
		return priceFormulas;
	}
	public void setPriceFormulas(PriceFormulas priceFormulas) {
		this.priceFormulas = priceFormulas;
	}
	public List<PriceFormulaItems> getPriceFormulaItems() {
		return priceFormulaItems;
	}
	public void setPriceFormulaItems(List<PriceFormulaItems> priceFormulaItems) {
		this.priceFormulaItems = priceFormulaItems;
	}
	public String getCreatedByText() {
		return createdByText;
	}
	public void setCreatedByText(String createdByText) {
		this.createdByText = createdByText;
	}
	public String getModifiedByText() {
		return modifiedByText;
	}
	public void setModifiedByText(String modifiedByText) {
		this.modifiedByText = modifiedByText;
	}
	public List<PriceFormulaCriteriasDTO> getPriceFormlaCriteriasDTO() {
		return priceFormlaCriteriasDTO;
	}
	public void setPriceFormlaCriteriasDTO(
			List<PriceFormulaCriteriasDTO> priceFormlaCriteriasDTO) {
		this.priceFormlaCriteriasDTO = priceFormlaCriteriasDTO;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<Integer> getDelPriceFormulaItems() {
		return delPriceFormulaItems;
	}

	public void setDelPriceFormulaItems(List<Integer> delPriceFormulaItems) {
		this.delPriceFormulaItems = delPriceFormulaItems;
	}

	public List<Integer> getDelPriceFormlaCriterias() {
		return delPriceFormlaCriterias;
	}

	public void setDelPriceFormlaCriterias(List<Integer> delPriceFormlaCriterias) {
		this.delPriceFormlaCriterias = delPriceFormlaCriterias;
	}
	
}
