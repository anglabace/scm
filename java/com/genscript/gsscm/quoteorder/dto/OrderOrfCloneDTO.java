package com.genscript.gsscm.quoteorder.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "OrderOrfCloneDTO", namespace = WsConstants.NS)
public class OrderOrfCloneDTO {
	private Integer 	quoteItemId;
	private Integer 	quoteNo;
	private Integer 	orderItemId;
	private Integer 	orderNo;
	private String 		accessionNo;
	private String 		seqType;
	private String 		geneUsage;
	private String 		geneUsageText;
	private String 		readingFrame;
	private String 		otherDescription;
	private String 		otherRequirement;
	private String 		serviceDocFlag;
	private String		comments;
	//以下为业务属性
	private List<Document> documentList;
	private List<Integer> delDocIdList;
	private String 	accessionInfo;
	private String	preOrfCloneId;
	private String	genePrice;
	private String	subcloningPrice;
    //add by zhanghuibin for Email
    private String plasmidPrepFlag;

    public String getPlasmidPrepFlag() {
        return plasmidPrepFlag;
    }

    public void setPlasmidPrepFlag(String plasmidPrepFlag) {
        this.plasmidPrepFlag = plasmidPrepFlag;
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
	public String getGeneUsageText() {
		return geneUsageText;
	}
	public void setGeneUsageText(String geneUsageText) {
		this.geneUsageText = geneUsageText;
	}
	public String getAccessionInfo() {
		return accessionInfo;
	}
	public void setAccessionInfo(String accessionInfo) {
		this.accessionInfo = accessionInfo;
	}
	public String getPreOrfCloneId() {
		return preOrfCloneId;
	}
	public void setPreOrfCloneId(String preOrfCloneId) {
		this.preOrfCloneId = preOrfCloneId;
	}
	public String getGenePrice() {
		return genePrice;
	}
	public void setGenePrice(String genePrice) {
		this.genePrice = genePrice;
	}
	public String getSubcloningPrice() {
		return subcloningPrice;
	}
	public void setSubcloningPrice(String subcloningPrice) {
		this.subcloningPrice = subcloningPrice;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
