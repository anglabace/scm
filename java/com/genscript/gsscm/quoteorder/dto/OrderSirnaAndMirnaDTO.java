package com.genscript.gsscm.quoteorder.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "OrderSirnaAndMirnaDTO", namespace = WsConstants.NS)
public class OrderSirnaAndMirnaDTO {
	private Integer quoteItemId;
	private Integer quoteNo;
	private Integer orderItemId;
	private String comments;
	private String geneName;
	private Integer orderNo;
	private String quantity;
	private String sequenceInsert;
	private String type;
	private String vectorName;
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
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
