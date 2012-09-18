package com.genscript.gsscm.quoteorder.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "OrderPeptideDTO", namespace = WsConstants.NS)
public class OrderPeptideDTO {
	private Integer quoteItemId;
	private Integer quoteNo;
	private Integer orderItemId;
	private Integer orderNo;
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
	//以下为业务属性
	private Document document;
	private Integer delDocId;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDisulfideBridge() {
		return disulfideBridge;
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

    public void setDisulfideBridge(String disulfideBridge) {
		this.disulfideBridge = disulfideBridge;
	}
	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}
	public String getCTerminal() {
		return CTerminal;
	}
	public void setCTerminal(String cTerminal) {
		CTerminal = cTerminal;
	}
	public String getNTerminal() {
		return NTerminal;
	}
	public void setNTerminal(String nTerminal) {
		NTerminal = nTerminal;
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
