package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * AccessAnalysis.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "access_log_anls", catalog="web_behavior")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccessAnalysis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985134634326832438L;
	@Id
	private Integer logId;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_id")
	private Visit visit;
	private String posInVisit;
	private Integer custNo;
	private Integer pageId;
	private Date visitTime;
	private String visitPage;
	private Integer staySeconds;
	private String categoryType;
	private Integer categoryId;
	private String searchTerm;
	private String refer;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the logId
	 */
	public Integer getLogId() {
		return logId;
	}
	/**
	 * @param logId the logId to set
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
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
	 * @return the pageId
	 */
	public Integer getPageId() {
		return pageId;
	}
	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	/**
	 * @return the visitTime
	 */
	public Date getVisitTime() {
		return visitTime;
	}
	/**
	 * @param visitTime the visitTime to set
	 */
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	/**
	 * @return the visitPage
	 */
	public String getVisitPage() {
		return visitPage;
	}
	/**
	 * @param visitPage the visitPage to set
	 */
	public void setVisitPage(String visitPage) {
		this.visitPage = visitPage;
	}
	/**
	 * @return the staySeconds
	 */
	public Integer getStaySeconds() {
		return staySeconds;
	}
	/**
	 * @param staySeconds the staySeconds to set
	 */
	public void setStaySeconds(Integer staySeconds) {
		this.staySeconds = staySeconds;
	}
	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the searchTerm
	 */
	public String getSearchTerm() {
		return searchTerm;
	}
	/**
	 * @param searchTerm the searchTerm to set
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	/**
	 * @return the refer
	 */
	public String getRefer() {
		return refer;
	}
	/**
	 * @param refer the refer to set
	 */
	public void setRefer(String refer) {
		this.refer = refer;
	}
	/**
	 * @return the categoryType
	 */
	public String getCategoryType() {
		return categoryType;
	}
	/**
	 * @param categoryType the categoryType to set
	 */
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	/**
	 * @return the visit
	 */
	public Visit getVisit() {
		return visit;
	}
	/**
	 * @param visit the visit to set
	 */
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	/**
	 * @return the posInVisit
	 */
	public String getPosInVisit() {
		return posInVisit;
	}
	/**
	 * @param posInVisit the posInVisit to set
	 */
	public void setPosInVisit(String posInVisit) {
		this.posInVisit = posInVisit;
	}
	
}
