package com.genscript.gsscm.quote.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name="quote_orf_clone", catalog = "order")
public class QuoteOrfClone extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 8753884863773397741L;
	@Id
	private Integer 	quoteItemId;
	private Integer 	quoteNo;
	private String 		accessionNo;
	private String 		seqType;
	private String 		geneUsage;
	private String 		readingFrame;
	private String 		otherDescription;
	@Lob
	private String 		otherRequirement;
	private String 		serviceDocFlag;
	private String		comments;

	public QuoteOrfClone() {
		super();
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

	public String getAccessionNo() {
		return accessionNo;
	}

	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}

	public String getSeqType() {
		return seqType;
	}

	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}

	public String getGeneUsage() {
		return geneUsage;
	}

	public void setGeneUsage(String geneUsage) {
		this.geneUsage = geneUsage;
	}

	public String getReadingFrame() {
		return readingFrame;
	}

	public void setReadingFrame(String readingFrame) {
		this.readingFrame = readingFrame;
	}

	public String getOtherDescription() {
		return otherDescription;
	}

	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}

	public String getOtherRequirement() {
		return otherRequirement;
	}

	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}

	public String getServiceDocFlag() {
		return serviceDocFlag;
	}

	public void setServiceDocFlag(String serviceDocFlag) {
		this.serviceDocFlag = serviceDocFlag;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
