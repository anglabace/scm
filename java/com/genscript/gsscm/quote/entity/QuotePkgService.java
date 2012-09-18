package com.genscript.gsscm.quote.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * for table quote_pkg_service
 * 
 * @author zhangyang
 * 
 */
@Entity
@Table(name = "quote_pkg_service", catalog = "order")
public class QuotePkgService extends BaseEntity {
	@Id
	private Integer quoteItemId;
	private Integer quoteNo;
	private String name;
	private String description;
	private String seqType;
	private String sequence;
	private Double seqBpPrice;
	private Double seqBpCost;
	//add by Zhang Yong  新增以下字段
	//protein sequence
	private String proteinSeq;
	//当子服务是molucular biology时用
	//Protein Name
	private String additionInfo1;
	//Accession Number
	private String additionInfo2;

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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getSeqType() {
		return seqType;
	}

	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Double getSeqBpPrice() {
		return seqBpPrice;
	}

	public void setSeqBpPrice(Double seqBpPrice) {
		this.seqBpPrice = seqBpPrice;
	}

	public Double getSeqBpCost() {
		return seqBpCost;
	}

	public void setSeqBpCost(Double seqBpCost) {
		this.seqBpCost = seqBpCost;
	}

	/**
	 * @return the additionInfo1
	 */
	public String getAdditionInfo1() {
		return additionInfo1;
	}

	/**
	 * @param additionInfo1 the additionInfo1 to set
	 */
	public void setAdditionInfo1(String additionInfo1) {
		this.additionInfo1 = additionInfo1;
	}

	/**
	 * @return the additionInfo2
	 */
	public String getAdditionInfo2() {
		return additionInfo2;
	}

	/**
	 * @param additionInfo2 the additionInfo2 to set
	 */
	public void setAdditionInfo2(String additionInfo2) {
		this.additionInfo2 = additionInfo2;
	}

	/**
	 * @return the proteinSeq
	 */
	public String getProteinSeq() {
		return proteinSeq;
	}

	/**
	 * @param proteinSeq the proteinSeq to set
	 */
	public void setProteinSeq(String proteinSeq) {
		this.proteinSeq = proteinSeq;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
