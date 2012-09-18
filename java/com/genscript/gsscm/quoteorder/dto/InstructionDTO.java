package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.constant.QuoteInstructionType;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "InstructionDTO", namespace = WsConstants.NS)
public class InstructionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4754412081552060779L;
	private Integer id;
	private String custNoteId;
	private Integer orderNo;
	private Integer quoteNo;
	private Integer workOrderNo;
	private String type;
	private String sendFlag;
	private Date scheduleDate;
	private Date sendDate;
	private String receipt;
	private String subject;
	private String content;
	private String docFlag;
	private String status;
	private String description;
	private Date noteDate;
	private String source;
	private Date instructionDate;
	private String createUser;
	private QuoteInstructionType quoteInstructionType; 
	private OrderInstructionType orderInstructionType;
	private List<Document> documentList;
	private List<NoteDocument> custNoteDocumentList;
	private List<Integer> delDocIdList;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getCustNoteId() {
		return custNoteId;
	}

	public void setCustNoteId(String custNoteId) {
		this.custNoteId = custNoteId;
	}

	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the quoteNo
	 */
	public Integer getQuoteNo() {
		return quoteNo;
	}

	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the sendFlag
	 */
	public String getSendFlag() {
		return sendFlag;
	}

	/**
	 * @param sendFlag the sendFlag to set
	 */
	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * @param scheduleDate the scheduleDate to set
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * @return the sendDate
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * @param sendDate the sendDate to set
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * @return the receipt
	 */
	public String getReceipt() {
		return receipt;
	}

	/**
	 * @param receipt the receipt to set
	 */
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the docFlag
	 */
	public String getDocFlag() {
		return docFlag;
	}

	/**
	 * @param docFlag the docFlag to set
	 */
	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the instructionDate
	 */
	public Date getInstructionDate() {
		return instructionDate;
	}

	/**
	 * @param instructionDate the instructionDate to set
	 */
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the quoteInstructionType
	 */
	public QuoteInstructionType getQuoteInstructionType() {
		return quoteInstructionType;
	}

	/**
	 * @param quoteInstructionType the quoteInstructionType to set
	 */
	public void setQuoteInstructionType(QuoteInstructionType quoteInstructionType) {
		this.quoteInstructionType = quoteInstructionType;
	}

	/**
	 * @return the orderInstructionType
	 */
	public OrderInstructionType getOrderInstructionType() {
		return orderInstructionType;
	}

	/**
	 * @param orderInstructionType the orderInstructionType to set
	 */
	public void setOrderInstructionType(OrderInstructionType orderInstructionType) {
		this.orderInstructionType = orderInstructionType;
	}

	/**
	 * @return the documentList
	 */
	public List<Document> getDocumentList() {
		return documentList;
	}

	/**
	 * @param documentList the documentList to set
	 */
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}

	/**
	 * @return the delDocIdList
	 */
	public List<Integer> getDelDocIdList() {
		return delDocIdList;
	}

	/**
	 * @param delDocIdList the delDocIdList to set
	 */
	public void setDelDocIdList(List<Integer> delDocIdList) {
		this.delDocIdList = delDocIdList;
	}

	/**
	 * @return the noteDate
	 */
	public Date getNoteDate() {
		return noteDate;
	}

	/**
	 * @param noteDate the noteDate to set
	 */
	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	public Integer getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(Integer workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public List<NoteDocument> getCustNoteDocumentList() {
		return custNoteDocumentList;
	}

	public void setCustNoteDocumentList(List<NoteDocument> custNoteDocumentList) {
		this.custNoteDocumentList = custNoteDocumentList;
	}
}
