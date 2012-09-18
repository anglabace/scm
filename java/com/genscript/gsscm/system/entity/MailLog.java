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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 
 * @author zhangyong
 *
 */
@Entity
@Table(name = "mail_log", catalog="system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MailLog implements Serializable {

	private static final long serialVersionUID = 8602268023523944551L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String functionName;
	private Integer templateId;
	private String type;
	private String refType;
	private Integer refId;
	private String sender;
	private String recipient;
	private String recipientCc;
	private String recipientBcc;
	private String subject;
	private String content;
	private String docFlag;
	private Integer sendBy;
	private Date sendDate;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getRecipientCc() {
		return recipientCc;
	}
	public void setRecipientCc(String recipientCc) {
		this.recipientCc = recipientCc;
	}
	public String getRecipientBcc() {
		return recipientBcc;
	}
	public void setRecipientBcc(String recipientBcc) {
		this.recipientBcc = recipientBcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDocFlag() {
		return docFlag;
	}
	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}
	public Integer getSendBy() {
		return sendBy;
	}
	public void setSendBy(Integer sendBy) {
		this.sendBy = sendBy;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

}
