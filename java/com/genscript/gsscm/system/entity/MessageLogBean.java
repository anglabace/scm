package com.genscript.gsscm.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * MESSAGE_LOG_BEAN.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "v_exception_logs", catalog="system")
public class MessageLogBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8572477499063298412L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
	private Integer logId;
	private Integer loggedBy;
	private String action;
	private String detail;
	private String summary;
	private String severity;
	private String interfaceName;
	private String description;
	private String code;
	private String stackInfo;
	private String functionName;
	private String fullPath;
	private Date logDate;
	private String loggedName;
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getLoggedBy() {
		return loggedBy;
	}
	public void setLoggedBy(Integer loggedBy) {
		this.loggedBy = loggedBy;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStackInfo() {
		return stackInfo;
	}
	public void setStackInfo(String stackInfo) {
		this.stackInfo = stackInfo;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getLoggedName() {
		return loggedName;
	}
	public void setLoggedName(String loggedName) {
		this.loggedName = loggedName;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
