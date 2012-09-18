package com.genscript.gsscm.quote.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * The persistent class for the quote_sirna_and_mirna database table.
 * 
 * @author Wangsf
 */
@Entity
@Table(name="quote_sirna_and_mirna", catalog="order")
public class QuoteSirnaAndMirna extends BaseEntity {
	@Id
	private Integer quoteItemId;
	@Lob()
	private String comments;
	private String geneName;
	private Integer quoteNo;
	private String quantity;
    @Lob()
	private String sequenceInsert;
	private String type;
	private String vectorName;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSequenceInsert() {
		return sequenceInsert;
	}

	public void setSequenceInsert(String sequenceInsert) {
		this.sequenceInsert = sequenceInsert;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVectorName() {
		return vectorName;
	}

	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

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
}
