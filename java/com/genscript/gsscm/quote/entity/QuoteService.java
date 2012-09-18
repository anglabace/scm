package com.genscript.gsscm.quote.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * 
 * quote_service entity
 * 
 * @author golf
 * 
 */
@Entity
@Table(name = "quote_service", catalog = "order")
public class QuoteService extends BaseEntity {
	@Id
	private Integer quoteItemId;
	private Integer quoteNo;
	private String customDesc;
	private String comments;
	public Integer getQuoteItemId() {
		return quoteItemId;
	}
	public void setQuoteItemId(Integer quoteItemId) {
		this.quoteItemId = quoteItemId;
	}
	public Integer getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getCustomDesc() {
		return customDesc;
	}
	public void setCustomDesc(String customDesc) {
		this.customDesc = customDesc;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
