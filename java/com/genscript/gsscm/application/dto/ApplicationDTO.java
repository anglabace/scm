package com.genscript.gsscm.application.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "ApplicationDTO", namespace = WsConstants.NS)
public class ApplicationDTO {

	private Integer id;
	private String name;
	private String description;
	private String versionNo;
	private Integer modifiedBy;
	private Date modifiedDate;
	private Integer revisionNo;
	
	private List<ApplicationModuleDTO> appModuleList = new ArrayList<ApplicationModuleDTO>(0);
	
	@XmlElementWrapper(name = "appModuleList")
	@XmlElement(name = "applicationModule")
	public List<ApplicationModuleDTO> getAppModuleList() {
		return appModuleList;
	}
	public void setAppModules(List<ApplicationModuleDTO> appModuleList) {
		this.appModuleList = appModuleList;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getRevisionNo() {
		return revisionNo;
	}
	public void setRevisionNo(Integer revisionNo) {
		this.revisionNo = revisionNo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
