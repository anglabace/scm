package com.genscript.gsscm.serv.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.serv.entity.ServiceDocTemplate;

@XmlType(name = "ServiceDocumentsDTO", namespace = WsConstants.NS)
public class ServiceDocumentsDTO {
	private Integer docId;
	private ServiceDocTemplate serviceDocTemplate;
	private String docName;
	private String docType;
	private String fileType;
	private String filePath;
	private String description;
	private String dispProp;
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public ServiceDocTemplate getServiceDocTemplate() {
		return serviceDocTemplate;
	}
	public void setServiceDocTemplate(ServiceDocTemplate serviceDocTemplate) {
		this.serviceDocTemplate = serviceDocTemplate;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDispProp() {
		return dispProp;
	}
	public void setDispProp(String dispProp) {
		this.dispProp = dispProp;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}
