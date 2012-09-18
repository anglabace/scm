package com.genscript.gsscm.quoteorder.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "OrderSirnaAndMirnaDTO", namespace = WsConstants.NS)
public class OrderProteinDTO {
	private Integer quoteItemId;
	private Integer quoteNo;
	private Integer orderItemId;
	private String description;
	private Integer itemNo;
	private String name;
	private Integer orderNo;
	private Integer quantity;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
