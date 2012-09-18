package com.genscript.gsscm.quote.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * 
 * quote_antibody entity
 * @author zhangyang
 *
 */
@Entity
@Table(name = "quote_antibody", catalog = "order")
public class QuoteAntibody extends BaseEntity {

	@Id
	private Integer quoteItemId;
	private Integer quoteNo;
	private String antibodyName;
	private String antigenType;
	@Lob
	private String customSequence;
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

	public String getAntibodyName() {
		return antibodyName;
	}

	public void setAntibodyName(String antibodyName) {
		this.antibodyName = antibodyName;
	}

	public String getAntigenType() {
		return antigenType;
	}

	public void setAntigenType(String antigenType) {
		this.antigenType = antigenType;
	}

	public String getCustomSequence() {
		return customSequence;
	}

	public void setCustomSequence(String customSequence) {
		this.customSequence = customSequence;
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
