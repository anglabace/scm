package com.genscript.gsscm.customer.entity;

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
 * CustomerPublications.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "customer_publications")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerPublications implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2404657685571784214L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "publication_id")
	private Integer id;
	private Integer custNo;
	private String publicationName;
	private String title;
	@Column(name = "abstract")
	private String abst;
	private String coAuthor;
	private String relatedArea;
	@Column(name = "keywords")
	private String keyWords;
	private String email;
	private String url;
	private Date issueDate;
	@Column(insertable = true, updatable = false)
	private Date creationDate;
	@Column(insertable = true, updatable = false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;

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
	 * @param id
	 *            the id to set
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
	 * @param custNo
	 *            the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	/**
	 * @return the publicationName
	 */
	public String getPublicationName() {
		return publicationName;
	}

	/**
	 * @param publicationName
	 *            the publicationName to set
	 */
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
}
