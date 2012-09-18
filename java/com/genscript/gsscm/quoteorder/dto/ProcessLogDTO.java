package com.genscript.gsscm.quoteorder.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ProcessLogDTO", namespace = WsConstants.NS)
public class ProcessLogDTO {

	private Integer logId;
	private Integer no;
	private Integer itemId;
	private String priorStat;
	private String priorStatName;
	private String currentStat;
	private String currentStatName;
	private Date processDate;
	private String actionName;
	private String comment;
	private String reason;
	private String updateBy;
	private String note;
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getPriorStat() {
		return priorStat;
	}
	public void setPriorStat(String priorStat) {
		this.priorStat = priorStat;
	}
	public String getCurrentStat() {
		return currentStat;
	}
	public void setCurrentStat(String currentStat) {
		this.currentStat = currentStat;
	}
	
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPriorStatName() {
		return priorStatName;
	}
	public void setPriorStatName(String priorStatName) {
		this.priorStatName = priorStatName;
	}
	public String getCurrentStatName() {
		return currentStatName;
	}
	public void setCurrentStatName(String currentStatName) {
		this.currentStatName = currentStatName;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
