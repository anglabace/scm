package com.genscript.gsscm.application.dto;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ApplicationModuleDTO", namespace = WsConstants.NS)
public class ApplicationModuleDTO {

	private Integer id;
	private String name;
	private String code;
	private String masterFlag;
	private Boolean localFlag;
	private Integer parentId;
	private String description;
	private Integer modifiedBy;
	private Date modifiedDate;
	private Integer revisionNo;
	private Integer appId;
	
	private Set<ApplicationInterfaceDTO> appInterfaces = new LinkedHashSet<ApplicationInterfaceDTO>(0);
	
	@XmlElementWrapper(name = "appInterfaces")
	@XmlElement(name = "applicationInterface")
	public Set<ApplicationInterfaceDTO> getAppInterfaces() {
		return appInterfaces;
	}
	public void setAppInterfaces(Set<ApplicationInterfaceDTO> appInterfaces) {
		this.appInterfaces = appInterfaces;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMasterFlag() {
		return masterFlag;
	}
	public void setMasterFlag(String masterFlag) {
		this.masterFlag = masterFlag;
	}
	public Boolean getLocalFlag() {
		return localFlag;
	}
	public void setLocalFlag(Boolean localFlag) {
		this.localFlag = localFlag;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
