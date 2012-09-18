package com.genscript.gsscm.customer.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.customer.entity.NoteDocument;

public class CustomerNoteDTO {
	
	private Integer id;
	private Integer custNo;
	private String type;
	private String massage;
	private String custName;
	private String docFlag;
	private String status;
	private Date sendDate;
	private String subject;
	private String content;
	private String sender;
	private String functionName;
	private Integer templateId;
	private List<NoteDocument> documentList;
	private List<Integer> delDocIdList;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public String getDocFlag() {
		return docFlag;
	}

	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
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

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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

	public List<NoteDocument> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<NoteDocument> documentList) {
		this.documentList = documentList;
	}

	public List<Integer> getDelDocIdList() {
		return delDocIdList;
	}

	public void setDelDocIdList(List<Integer> delDocIdList) {
		this.delDocIdList = delDocIdList;
	}
	
	
}
