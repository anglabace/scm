package com.genscript.gsscm.system.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;
@XmlType(name = "MailLogDTO", namespace = WsConstants.NS)
public class MailLogDTO {
	private Integer id;
	private String functionName;
	private Integer templateId;
	private String refType;
	private Integer refId;
	private String sender;
	private String recipient;
	private String recipientCc;
	private String recipientBcc;
	private String subject;
	private String content;
	private Integer sendBy;
	private Date sendDate;
	private Date instructionDate;
	private String createUser;
	private String docFlag;
	private String type;
	private OrderInstructionType orderInstructionType;
	private List<Document> documentList;
	private Document wordDoc;
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
	public Date getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public OrderInstructionType getOrderInstructionType() {
		return orderInstructionType;
	}
	public void setOrderInstructionType(OrderInstructionType orderInstructionType) {
		this.orderInstructionType = orderInstructionType;
	}
	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
	public String getDocFlag() {
		return docFlag;
	}
	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Document getWordDoc() {
		return wordDoc;
	}
	public void setWordDoc(Document wordDoc) {
		this.wordDoc = wordDoc;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
