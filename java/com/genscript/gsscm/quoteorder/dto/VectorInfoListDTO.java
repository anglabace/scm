package com.genscript.gsscm.quoteorder.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "VectorInfoListDTO", namespace = WsConstants.NS)
public class VectorInfoListDTO {
	private Integer 	id;
	private String		vectorName;
	private String		vectorNameShow;
	private String		resistance;
	private String		vectorLen;
	private String		cloningSites;
	private BigDecimal 	vectorPrice;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVectorName() {
		return vectorName;
	}
	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}
	public String getVectorNameShow() {
		return vectorNameShow;
	}
	public void setVectorNameShow(String vectorNameShow) {
		this.vectorNameShow = vectorNameShow;
	}
	public String getResistance() {
		return resistance;
	}
	public void setResistance(String resistance) {
		this.resistance = resistance;
	}
	public String getVectorLen() {
		return vectorLen;
	}
	public void setVectorLen(String vectorLen) {
		this.vectorLen = vectorLen;
	}
	public String getCloningSites() {
		return cloningSites;
	}
	public void setCloningSites(String cloningSites) {
		this.cloningSites = cloningSites;
	}
	public BigDecimal getVectorPrice() {
		return vectorPrice;
	}
	public void setVectorPrice(BigDecimal vectorPrice) {
		this.vectorPrice = vectorPrice;
	}
}
