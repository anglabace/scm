package com.genscript.gsscm.serv.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ServiceDocTemplateDTO", namespace = WsConstants.NS)
public class ServiceDocTemplateDTO {

	private Integer templateId;
	private String name;
	private String description;
	private String function;
	private Integer serviceClsId;
	private Integer docId;
	private String status;
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public Integer getServiceClsId() {
		return serviceClsId;
	}
	public void setServiceClsId(Integer serviceClsId) {
		this.serviceClsId = serviceClsId;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
