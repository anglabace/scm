package com.genscript.gsscm.quoteorder.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "OrderCustCloningDTO", namespace = WsConstants.NS)
public class OrderCustCloningDTO {
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
	private String tgtInsertName;
	private String tgtCloningMethod;
	private String tgtCloningSite;
	private String tgtCloningSite3;
	private String tgtSequence;
	private String tgtVector;
	private String tgtVectorOther;
	private String tgtVectorSize;
	private String tgtResistance;
	private String tgtResistanceOther;
	private String tgtCopyNo;
	private String tgtVectorSeq;
	private String tgtMapDocFlag;
	private Integer stdPlasmidWt;
	private String stdPlasmidWtUom;
	private String plasmidPrepFlag;
	private String geneUsage;
	private String readingFrame;
	private String geneUsageText;
	private String otherDescription;
	private String otherRequirement;
	private String serviceDocFlag;
	private String cloningFlag;
	private Integer parentClsId;
	private Integer tgtSeqLength;
	private String tgtSeqSameFlag;
	//以下为业务属性
	private List<Document> documentList;
	private List<Integer> delDocIdList;
	private String tbdFlag;; //js判断用 add by Zhang Yong
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
	public String getTgtInsertName() {
		return tgtInsertName;
	}
	public void setTgtInsertName(String tgtInsertName) {
		this.tgtInsertName = tgtInsertName;
	}
	public String getTgtCloningMethod() {
		return tgtCloningMethod;
	}
	public void setTgtCloningMethod(String tgtCloningMethod) {
		this.tgtCloningMethod = tgtCloningMethod;
	}
	public String getTgtCloningSite() {
		return tgtCloningSite;
	}
	public void setTgtCloningSite(String tgtCloningSite) {
		this.tgtCloningSite = tgtCloningSite;
	}
	public String getTgtSequence() {
		return tgtSequence;
	}
	public void setTgtSequence(String tgtSequence) {
		this.tgtSequence = tgtSequence;
	}
	public String getTgtVector() {
		return tgtVector;
	}
	public void setTgtVector(String tgtVector) {
		this.tgtVector = tgtVector;
	}

    public String getTgtVectorSize() {
        return tgtVectorSize;
    }

    public void setTgtVectorSize(String tgtVectorSize) {
        this.tgtVectorSize = tgtVectorSize;
    }

    public String getTgtResistance() {
		return tgtResistance;
	}
	public void setTgtResistance(String tgtResistance) {
		this.tgtResistance = tgtResistance;
	}
	public String getTgtCopyNo() {
		return tgtCopyNo;
	}
	public void setTgtCopyNo(String tgtCopyNo) {
		this.tgtCopyNo = tgtCopyNo;
	}
	public String getTgtVectorSeq() {
		return tgtVectorSeq;
	}
	public void setTgtVectorSeq(String tgtVectorSeq) {
		this.tgtVectorSeq = tgtVectorSeq;
	}
	public String getTgtMapDocFlag() {
		return tgtMapDocFlag;
	}
	public void setTgtMapDocFlag(String tgtMapDocFlag) {
		this.tgtMapDocFlag = tgtMapDocFlag;
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

	public String getCloningFlag() {
		return cloningFlag;
	}

	public void setCloningFlag(String cloningFlag) {
		this.cloningFlag = cloningFlag;
	}

	public Integer getParentClsId() {
		return parentClsId;
	}

	public void setParentClsId(Integer parentClsId) {
		this.parentClsId = parentClsId;
	}

	public Integer getTgtSeqLength() {
		return tgtSeqLength;
	}

	public void setTgtSeqLength(Integer tgtSeqLength) {
		this.tgtSeqLength = tgtSeqLength;
	}

	public String getGeneUsageText() {
		return geneUsageText;
	}

	public void setGeneUsageText(String geneUsageText) {
		this.geneUsageText = geneUsageText;
	}

	public String getTgtSeqSameFlag() {
		return tgtSeqSameFlag;
	}

	public void setTgtSeqSameFlag(String tgtSeqSameFlag) {
		this.tgtSeqSameFlag = tgtSeqSameFlag;
	}

	public String getTgtVectorOther() {
		return tgtVectorOther;
	}

	public void setTgtVectorOther(String tgtVectorOther) {
		this.tgtVectorOther = tgtVectorOther;
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

	public String getTgtResistanceOther() {
		return tgtResistanceOther;
	}

	public void setTgtResistanceOther(String tgtResistanceOther) {
		this.tgtResistanceOther = tgtResistanceOther;
	}

	public String getTgtCloningSite3() {
		return tgtCloningSite3;
	}

	public void setTgtCloningSite3(String tgtCloningSite3) {
		this.tgtCloningSite3 = tgtCloningSite3;
	}

	public String getTbdFlag() {
		return tbdFlag;
	}

	public void setTbdFlag(String tbdFlag) {
		this.tbdFlag = tbdFlag;
	}

}
