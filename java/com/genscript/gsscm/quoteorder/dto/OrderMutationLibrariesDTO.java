package com.genscript.gsscm.quoteorder.dto;

import java.util.List;

import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "OrderMutationLibrariesDTO", namespace = WsConstants.NS)
public class OrderMutationLibrariesDTO {
	private Integer quoteItemId;
	private Integer quoteNo;
	private Integer orderItemId;
	private Integer orderNo;
	private String constructName;
	private String libraryType;
	private String tmplFlag;
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
	private String interestSequence;
	private String degeneratedSites;
	private String cloningFlag;
	private Integer stdPlasmidWt;
	private String stdPlasmidWtUom;
	private String plasmidPrepFlag;
	private String geneUsage;
	private String readingFrame;
	private String geneUsageText;
	private String otherDescription;
	//以下为业务属性
	private List<Document> documentList;
	private List<Integer> delDocIdList;

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
	public String getConstructName() {
		return constructName;
	}
	public void setConstructName(String constructName) {
		this.constructName = constructName;
	}
	public String getLibraryType() {
		return libraryType;
	}
	public void setLibraryType(String libraryType) {
		this.libraryType = libraryType;
	}
	public String getTmplFlag() {
		return tmplFlag;
	}
	public void setTmplFlag(String tmplFlag) {
		this.tmplFlag = tmplFlag;
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
	public String getInterestSequence() {
		return interestSequence;
	}
	public void setInterestSequence(String interestSequence) {
		this.interestSequence = interestSequence;
	}
	public String getDegeneratedSites() {
		return degeneratedSites;
	}
	public void setDegeneratedSites(String degeneratedSites) {
		this.degeneratedSites = degeneratedSites;
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
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public Integer getDelDocId() {
		return delDocId;
	}
	public void setDelDocId(Integer delDocId) {
		this.delDocId = delDocId;
	}
	@Lob
	private String otherRequirement;
	private String tgtVectorName;
	private String serviceDocFlag;
	//以下为业务属性
	private Document document;
	private Integer delDocId;
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
	public String getGeneUsageText() {
		return geneUsageText;
	}
	public void setGeneUsageText(String geneUsageText) {
		this.geneUsageText = geneUsageText;
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
	public String getTgtVectorName() {
		return tgtVectorName;
	}
	public void setTgtVectorName(String tgtVectorName) {
		this.tgtVectorName = tgtVectorName;
	}
	
}
