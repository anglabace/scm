package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;

public class CustomServiceDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7824553084729640151L;
	private Integer orderItemId;
	private Integer orderNo;
	private Integer quoteItemId;
	private Integer quoteNo;
	private String customDesc;
	private String comments;
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
	public String getCustomDesc() {
		return customDesc;
	}
	public void setCustomDesc(String customDesc) {
		this.customDesc = customDesc;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
