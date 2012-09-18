package com.genscript.gsscm.serv.dto;

import java.util.ArrayList;
import java.util.List;

import com.genscript.gsscm.serv.entity.PriceRuleGroups;
import com.genscript.gsscm.serv.entity.PriceRules;

public class PriceRuleGroupsDTO {
	private PriceRuleGroups priceRuleGropus;
	private String createdByText;
	private String modifiedByText;
	private List<PriceRules> priceRuleList;
	private List<Integer> delPriceRuleList;
	private List<Integer> addPriceRuleList;
	
	public PriceRuleGroupsDTO(){
		this.priceRuleList = new ArrayList<PriceRules>();
		delPriceRuleList = new ArrayList<Integer>();
		addPriceRuleList = new ArrayList<Integer>();
	}
	public PriceRuleGroups getPriceRuleGropus() {
		return priceRuleGropus;
	}
	public void setPriceRuleGropus(PriceRuleGroups priceRuleGropus) {
		this.priceRuleGropus = priceRuleGropus;
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
	public List<PriceRules> getPriceRuleList() {
		return priceRuleList;
	}
	public void setPriceRuleList(List<PriceRules> priceRuleList) {
		this.priceRuleList = priceRuleList;
	}
	public List<Integer> getDelPriceRuleList() {
		return delPriceRuleList;
	}
	public void setDelPriceRuleList(List<Integer> delPriceRuleList) {
		this.delPriceRuleList = delPriceRuleList;
	}
	public List<Integer> getAddPriceRuleList() {
		return addPriceRuleList;
	}
	public void setAddPriceRuleList(List<Integer> addPriceRuleList) {
		this.addPriceRuleList = addPriceRuleList;
	}
	
}
