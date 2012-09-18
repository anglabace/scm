package com.genscript.gsscm.quoteorder.dto;

import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "AntibodyDTO", namespace = WsConstants.NS)
public class AntibodyDTO {
	private Integer quoteItemId;
	private Integer orderItemId;
	private Integer orderNo;
	private Integer quoteNo;
	private String antibodyName;
	private String antigenType;
	private String customSequence;
	private String comments;
	private Map<String, String> specialCatalogNoMap;

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

	public String getAntibodyName() {
		return antibodyName;
	}

	public void setAntibodyName(String antibodyName) {
		this.antibodyName = antibodyName;
	}

	public String getAntigenType() {
		return antigenType;
	}

	public void setAntigenType(String antigenType) {
		this.antigenType = antigenType;
	}

	public String getCustomSequence() {
		return customSequence;
	}

	public void setCustomSequence(String customSequence) {
		this.customSequence = customSequence;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}

	public Map<String, String> getSpecialCatalogNoMap() {
		return specialCatalogNoMap;
	}

	public void setSpecialCatalogNoMap(Map<String, String> specialCatalogNoMap) {
		this.specialCatalogNoMap = specialCatalogNoMap;
	}
}
