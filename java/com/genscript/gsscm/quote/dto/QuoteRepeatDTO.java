package com.genscript.gsscm.quote.dto;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.quote.entity.QuoteItem;

public class QuoteRepeatDTO {
	private List<QuoteItem> newItemList;
	private Integer srcQuoteNo;
	private Integer newQuoteNo;
	private String quoteCurrency;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public List<QuoteItem> getNewItemList() {
		return newItemList;
	}
	public void setNewItemList(List<QuoteItem> newItemList) {
		this.newItemList = newItemList;
	}
	public Integer getSrcQuoteNo() {
		return srcQuoteNo;
	}
	public void setSrcQuoteNo(Integer srcQuoteNo) {
		this.srcQuoteNo = srcQuoteNo;
	}
	public Integer getNewQuoteNo() {
		return newQuoteNo;
	}
	public void setNewQuoteNo(Integer newQuoteNo) {
		this.newQuoteNo = newQuoteNo;
	}
	public String getQuoteCurrency() {
		return quoteCurrency;
	}
	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}
}
