package com.genscript.gsscm.product.dto;

import java.util.List;

public class CatalogNORulesDTO {
	private Integer ruleId;
	private String ruleName;
	private String itemType;
	private Integer clsId;
	private Integer categoryId;
	private String categoryNo;
	private String prefix;
	private String currentSeq;
	private String categryNo;
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Integer getClsId() {
		return clsId;
	}
	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getCurrentSeq() {
		return currentSeq;
	}
	public void setCurrentSeq(String currentSeq) {
		this.currentSeq = currentSeq;
	}
	public String getCategryNo() {
		return categryNo;
	}
	public void setCategryNo(String categryNo) {
		this.categryNo = categryNo;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}	
	
}
