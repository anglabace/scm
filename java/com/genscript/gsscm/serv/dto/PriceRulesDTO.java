package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.serv.entity.PriceRules;

@XmlType(name = "PriceRulesDTO", namespace = WsConstants.NS)
public class PriceRulesDTO   implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5947386625103559378L;
	private PriceRules priceRules;
	private List<PriceFormulaDTO> priceFormulasDTOList;
	private List<PriceRuleAttrValueMappingDTO> priceAttrValMapDTOList;
	private List<Integer> delPriceForms;
	private List<Integer> delPriceAttr;
	
	public PriceRulesDTO(){
		this.priceAttrValMapDTOList = new ArrayList<PriceRuleAttrValueMappingDTO>();
		this.priceFormulasDTOList =  new ArrayList<PriceFormulaDTO>();
		this.delPriceAttr = new ArrayList<Integer>();
		this.delPriceForms = new ArrayList<Integer>();
	}
	
	public PriceRules getPriceRules() {
		return priceRules;
	}
	public void setPriceRules(PriceRules priceRules) {
		this.priceRules = priceRules;
	}
	public List<PriceFormulaDTO> getPriceFormulasDTOList() {
		return priceFormulasDTOList;
	}
	public void setPriceFormulasDTOList(List<PriceFormulaDTO> priceFormulasDTOList) {
		this.priceFormulasDTOList = priceFormulasDTOList;
	}
	public List<Integer> getDelPriceForms() {
		return delPriceForms;
	}
	public void setDelPriceForms(List<Integer> delPriceForms) {
		this.delPriceForms = delPriceForms;
	}
	public List<Integer> getDelPriceAttr() {
		return delPriceAttr;
	}
	public void setDelPriceAttr(List<Integer> delPriceAttr) {
		this.delPriceAttr = delPriceAttr;
	}
	public List<PriceRuleAttrValueMappingDTO> getPriceAttrValMapDTOList() {
		return priceAttrValMapDTOList;
	}
	public void setPriceAttrValMapDTOList(
			List<PriceRuleAttrValueMappingDTO> priceAttrValMapDTOList) {
		this.priceAttrValMapDTOList = priceAttrValMapDTOList;
	}
	
}
