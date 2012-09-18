package com.genscript.gsscm.quoteorder.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "OrderMutagenesisDTO", namespace = WsConstants.NS)
public class OrderMutagenesisDTO {
	private Integer quoteItemId;
	private Integer quoteNo;
	private Integer orderItemId;
	private Integer orderNo;
	private String tmplInsertName;
	private String tmplCloningMethod;
	private String tmplCloningSite;
	private String tmplSequence;
	private String tmplVector;
	private String tmplVectorOther;
	private String tmplVectorSize;
	private String tmplResistance;
	private String tmplResistanceOther;
	private String tmplCopyNo;
	private String tmplVectorSeq;
	private String tmplMapDocFlag;
	private String variantName;
	private String variantSequence;
	private Integer variantSeqLength;
	private String cloningFlag;
	private Integer stdPlasmidWt;
	private String stdPlasmidWtUom;
	private String plasmidPrepFlag;
	private String geneUsage;
	private String readingFrame;
	private String geneUsageText;
	private String otherDescription;
	private String otherRequirement;
	private String serviceDocFlag;
	//以下为业务属性
	private List<Document> documentList;
	private List<Integer> delDocIdList;
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
	public String getTmplInsertName() {
		return tmplInsertName;
	}
	public void setTmplInsertName(String tmplInsertName) {
		this.tmplInsertName = tmplInsertName;
	}
	public String getTmplCloningMethod() {
		return tmplCloningMethod;
	}
	public void setTmplCloningMethod(String tmplCloningMethod) {
		this.tmplCloningMethod = tmplCloningMethod;
	}
	public String getTmplCloningSite() {
		return tmplCloningSite;
	}
	public void setTmplCloningSite(String tmplCloningSite) {
		this.tmplCloningSite = tmplCloningSite;
	}
	public String getTmplSequence() {
		return tmplSequence;
	}
	public void setTmplSequence(String tmplSequence) {
		this.tmplSequence = tmplSequence;
	}
	public String getTmplVector() {
		return tmplVector;
	}
	public void setTmplVector(String tmplVector) {
		this.tmplVector = tmplVector;
	}
	public String getTmplVectorSize() {
		return tmplVectorSize;
	}
	public void setTmplVectorSize(String tmplVectorSize) {
		this.tmplVectorSize = tmplVectorSize;
	}
	public String getTmplResistance() {
		return tmplResistance;
	}
	public void setTmplResistance(String tmplResistance) {
		this.tmplResistance = tmplResistance;
	}
	public String getTmplCopyNo() {
		return tmplCopyNo;
	}
	public void setTmplCopyNo(String tmplCopyNo) {
		this.tmplCopyNo = tmplCopyNo;
	}
	public String getTmplVectorSeq() {
		return tmplVectorSeq;
	}
	public void setTmplVectorSeq(String tmplVectorSeq) {
		this.tmplVectorSeq = tmplVectorSeq;
	}
	public String getTmplMapDocFlag() {
		return tmplMapDocFlag;
	}
	public void setTmplMapDocFlag(String tmplMapDocFlag) {
		this.tmplMapDocFlag = tmplMapDocFlag;
	}
	public String getVariantName() {
		return variantName;
	}
	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}
	public String getVariantSequence() {
		return variantSequence;
	}
	public void setVariantSequence(String variantSequence) {
		this.variantSequence = variantSequence;
	}
	public String getCloningFlag() {
		return cloningFlag;
	}
	public void setCloningFlag(String cloningFlag) {
		this.cloningFlag = cloningFlag;
	}
	public Integer getStdPlasmidWt() {
		return stdPlasmidWt;
	}
	public void setStdPlasmidWt(Integer stdPlasmidWt) {
		this.stdPlasmidWt = stdPlasmidWt;
	}
	public String getStdPlasmidWtUom() {
		return stdPlasmidWtUom;
	}
	public void setStdPlasmidWtUom(String stdPlasmidWtUom) {
		this.stdPlasmidWtUom = stdPlasmidWtUom;
	}
	public String getPlasmidPrepFlag() {
		return plasmidPrepFlag;
	}
	public void setPlasmidPrepFlag(String plasmidPrepFlag) {
		this.plasmidPrepFlag = plasmidPrepFlag;
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
	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
	public List<Integer> getDelDocIdList() {
		return delDocIdList;
	}
	public void setDelDocIdList(List<Integer> delDocIdList) {
		this.delDocIdList = delDocIdList;
	}
	public Integer getVariantSeqLength() {
		return variantSeqLength;
	}
	public void setVariantSeqLength(Integer variantSeqLength) {
		this.variantSeqLength = variantSeqLength;
	}
	public String getGeneUsageText() {
		return geneUsageText;
	}
	public void setGeneUsageText(String geneUsageText) {
		this.geneUsageText = geneUsageText;
	}
	public String getTmplVectorOther() {
		return tmplVectorOther;
	}
	public void setTmplVectorOther(String tmplVectorOther) {
		this.tmplVectorOther = tmplVectorOther;
	}
	public String getTmplResistanceOther() {
		return tmplResistanceOther;
	}
	public void setTmplResistanceOther(String tmplResistanceOther) {
		this.tmplResistanceOther = tmplResistanceOther;
	}
	
}
