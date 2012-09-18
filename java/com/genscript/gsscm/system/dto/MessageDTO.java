package com.genscript.gsscm.system.dto;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "MessageDTO", namespace = WsConstants.NS)
public class MessageDTO {

	private Integer id;
	private String code;
	private String description;
	private String severity;
	private Integer modifiedBy;
	private Date modifiedDate;
	private Integer revisionNo;
	
	private Set<MessageDetailDTO> messageDetails = new LinkedHashSet<MessageDetailDTO>(0);
	
	//private Set<MessageLogDTO> messageLogs = new LinkedHashSet<MessageLogDTO>(0);
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
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

	@XmlElementWrapper(name = "messageDetails")
	@XmlElement(name = "messageDetail")
	public Set<MessageDetailDTO> getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(Set<MessageDetailDTO> messageDetails) {
		this.messageDetails = messageDetails;
	}
//
//	public Set<MessageLog> getMessageLogs() {
//		return messageLogs;
//	}
//
//	public void setMessageLogs(Set<MessageLog> messageLogs) {
//		this.messageLogs = messageLogs;
//	}

	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
