package com.genscript.gsscm.customer.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.SessionBaseDTO;

public class CustPubsDTO extends SessionBaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7186164079358197180L;
	private Integer id;
	private Integer custNo;
	private String publicationName;
	private String title;
	private String abst;
	private String coAuthor;
	private String relatedArea;
	private String keyWords;
	private String email;
	private String url;
	private Date issueDate;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	private String pubIdStr;
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
	public String getPublicationName() {
		return publicationName;
	}
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbst() {
		return abst;
	}
	public void setAbst(String abst) {
		this.abst = abst;
	}
	public String getCoAuthor() {
		return coAuthor;
	}
	public void setCoAuthor(String coAuthor) {
		this.coAuthor = coAuthor;
	}
	public String getRelatedArea() {
		return relatedArea;
	}
	public void setRelatedArea(String relatedArea) {
		this.relatedArea = relatedArea;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getPubIdStr() {
		return pubIdStr;
	}
	public void setPubIdStr(String pubIdStr) {
		this.pubIdStr = pubIdStr;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
