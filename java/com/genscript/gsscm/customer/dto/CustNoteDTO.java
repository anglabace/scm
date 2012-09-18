package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.NoteDocument;


@XmlType(name = "CustNoteDTO", namespace = WsConstants.NS)
public class CustNoteDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2109367861626719462L;
	private Integer id;
	private Integer custNo;
	private String type;
	private String description;
	private String docFlag;	
	private List<NoteDocument> documentList;
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

	/**
	 * @return the custNo
	 */
	public Integer getCustNo() {
		return custNo;
	}

	/**
	 * @param custNo the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
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
	 * @return the documentList
	 */
	public List<NoteDocument> getDocumentList() {
		return documentList;
	}

	/**
	 * @param documentList the documentList to set
	 */
	public void setDocumentList(List<NoteDocument> documentList) {
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
}
