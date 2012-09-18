package com.genscript.gsscm.product.dto;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "OligoBackbonesDTO", namespace = WsConstants.NS)
public class OligoBackbonesDTO {
	private Integer id;
	private String bName;
	private String bNameShow;
	private String availableLetter;
	private String validationRule;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBName() {
		return bName;
	}
	public void setBName(String name) {
		bName = name;
	}
	public String getBNameShow() {
		return bNameShow;
	}
	public void setBNameShow(String nameShow) {
		bNameShow = nameShow;
	}
	public String getAvailableLetter() {
		return availableLetter;
	}
	public void setAvailableLetter(String availableLetter) {
		this.availableLetter = availableLetter;
	}
	public String getValidationRule() {
		return validationRule;
	}
	public void setValidationRule(String validationRule) {
		this.validationRule = validationRule;
	}
	
	
}
