package com.genscript.gsscm.customer.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.Visit;


@XmlType(name = "AnalysisDTO", namespace = WsConstants.NS)
public class AnalysisDTO {
	private Integer logId;
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
	private String sourceIp;
	private String os;
	private String browser;
	private String hostName;
	private String country;
	private String zip;
	private String isp;	
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
	 * @return the sourceIp
	 */
	public String getSourceIp() {
		return sourceIp;
	}
	/**
	 * @param sourceIp the sourceIp to set
	 */
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}
	/**
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}
	/**
	 * @return the browser
	 */
	public String getBrowser() {
		return browser;
	}
	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * @return the isp
	 */
	public String getIsp() {
		return isp;
	}
	/**
	 * @param isp the isp to set
	 */
	public void setIsp(String isp) {
		this.isp = isp;
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
