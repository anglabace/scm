package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "PeptideDTO", namespace = WsConstants.NS)
public class PeptideDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4964776365562771844L;
	private Integer orderItemId;
	private Integer orderNo;
	private Integer quoteItemId;
	private Integer quoteNo;
	private String name;
	private Integer aliquoteVialQty;
	private String aminoChangeFlag;
	private String CTerminal;
	private String comments;
	private String disulfideBridge;
	private String modification;
	private String NTerminal;
	private String purity;
	private String quantity;
    private String realPurity;
	private String realQuantity;
	private Integer seqLength;
	private String sequence;
	private String qcReport;
    private String synMembrane;
    private String deliveryFormat;
	//以下为业务属性
	private List<Document> documentList;
	private List<Integer> delDocIdList;
	private String catalogNo;
	private Integer peptideCount;
	private String status;
	private boolean convertFlag;
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
	public Integer getAliquoteVialQty() {
		return aliquoteVialQty;
	}
	public void setAliquoteVialQty(Integer aliquoteVialQty) {
		this.aliquoteVialQty = aliquoteVialQty;
	}
	public String getAminoChangeFlag() {
		return aminoChangeFlag;
	}
	public void setAminoChangeFlag(String aminoChangeFlag) {
		this.aminoChangeFlag = aminoChangeFlag;
	}

    public String getRealPurity() {
        return realPurity;
    }

    public void setRealPurity(String realPurity) {
        this.realPurity = realPurity;
    }

    public String getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(String realQuantity) {
        this.realQuantity = realQuantity;
    }

    public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDisulfideBridge() {
		return disulfideBridge;
	}
	public void setDisulfideBridge(String disulfideBridge) {
		this.disulfideBridge = disulfideBridge;
	}
	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}
	public String getPurity() {
		return purity;
	}
	public void setPurity(String purity) {
		this.purity = purity;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Integer getSeqLength() {
		return seqLength;
	}
	public void setSeqLength(Integer seqLength) {
		this.seqLength = seqLength;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
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
	public String getCTerminal() {
		return CTerminal;
	}
	public void setCTerminal(String terminal) {
		CTerminal = terminal;
	}
	public String getNTerminal() {
		return NTerminal;
	}
	public void setNTerminal(String terminal) {
		NTerminal = terminal;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getQcReport() {
		return qcReport;
	}
	public void setQcReport(String qcReport) {
		this.qcReport = qcReport;
	}
	public String getSynMembrane() {
		return synMembrane;
	}
	public void setSynMembrane(String synMembrane) {
		this.synMembrane = synMembrane;
	}
	public String getDeliveryFormat() {
		return deliveryFormat;
	}
	public void setDeliveryFormat(String deliveryFormat) {
		this.deliveryFormat = deliveryFormat;
	}
	public Integer getPeptideCount() {
		return peptideCount;
	}
	public void setPeptideCount(Integer peptideCount) {
		this.peptideCount = peptideCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isConvertFlag() {
		return convertFlag;
	}
	public void setConvertFlag(boolean convertFlag) {
		this.convertFlag = convertFlag;
	}
}
