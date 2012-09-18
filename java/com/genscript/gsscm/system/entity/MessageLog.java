package com.genscript.gsscm.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * MESSAGE_LOG.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "message_log", catalog="system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String fullPath;
	private String functionName;
	private String stackInfo;
	private Integer logedBy;
	private Date logDate = new Date();
	@Column(name = "message_id")
	private Integer messageId;
	private String appIrfCode;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getStackInfo() {
		return stackInfo;
	}
	public void setStackInfo(String stackInfo) {
		this.stackInfo = stackInfo;
	}
	public Integer getLogedBy() {
		return logedBy;
	}
	public void setLogedBy(Integer logedBy) {
		this.logedBy = logedBy;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	
	/**
	 * @return the appIrfCode
	 */
	public String getAppIrfCode() {
		return appIrfCode;
	}
	/**
	 * @param appIrfCode the appIrfCode to set
	 */
	public void setAppIrfCode(String appIrfCode) {
		this.appIrfCode = appIrfCode;
	}
	public MessageLog() {
		super();
	}
	public MessageLog(String fullPath, String functionName, String stackInfo,
			Integer loggedBy, Integer msgId, String intfCode) {
		super();
		this.fullPath = fullPath;
		this.functionName = functionName;
		this.stackInfo = stackInfo;
		this.logedBy = loggedBy;
		this.messageId = msgId;
		this.appIrfCode = intfCode;
	}
	public MessageLog(String fullPath, String functionName,
			Integer loggedBy, Integer msgId, String intfCode) {
		super();
		this.fullPath = fullPath;
		this.functionName = functionName;
		this.logedBy = loggedBy;
		this.messageId = msgId;
		this.appIrfCode = intfCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
