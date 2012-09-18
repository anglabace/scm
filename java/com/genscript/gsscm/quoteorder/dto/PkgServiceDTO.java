package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;


public class PkgServiceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6853285154343799333L;
	private Integer orderItemId;
	private Integer quoteItemId;
	private Integer orderNo;
	private Integer quoteNo;
	private String name;
	private String description;
	private String seqType;
	private String sequence;
	private Double seqBpPrice;
	private Double seqBpCost;
	private String seqFlag;
	//add by Zhang Yong  新增以下字段
	//protein sequence
	private String proteinSeq;
	//当子服务是molucular biology时用
	//Protein Name
	private String additionInfo1;
	//Accession Number
	private String additionInfo2;
	//非数据库属性
	private String catalogNo;
	private String itemName;
    private BigDecimal cost;
	private BigDecimal unitPrice;
	private String upSymbol;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUpSymbol() {
		return upSymbol;
	}

	public void setUpSymbol(String upSymbol) {
		this.upSymbol = upSymbol;
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

	public String getSeqFlag() {
		return seqFlag;
	}

	public void setSeqFlag(String seqFlag) {
		this.seqFlag = seqFlag;
	}

	public String getProteinSeq() {
		return proteinSeq;
	}

	public void setProteinSeq(String proteinSeq) {
		this.proteinSeq = proteinSeq;
	}

	public String getAdditionInfo1() {
		return additionInfo1;
	}

	public void setAdditionInfo1(String additionInfo1) {
		this.additionInfo1 = additionInfo1;
	}

	public String getAdditionInfo2() {
		return additionInfo2;
	}

	public void setAdditionInfo2(String additionInfo2) {
		this.additionInfo2 = additionInfo2;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
