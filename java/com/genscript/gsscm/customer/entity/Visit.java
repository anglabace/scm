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
 * Visit.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "visits", catalog="web_behavior")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Visit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985134634326832438L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visit_id")
	private Integer visitId;
	private Integer custNo;
	private String sessionId;
	private Date inTime;
	private Date outTime;
	private Integer staySeconds;
	@Column(name = "pages_visited")
	private Integer pageVisited;
	private String sourceIp;
	private String os;
	private String browser;
	private String hostName;
	private String country;
	private String state;
	private String zip;
	private String institution;
	private String isp;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the visitId
	 */
	public Integer getVisitId() {
		return visitId;
	}
	/**
	 * @param visitId the visitId to set
	 */
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the inTime
	 */
	public Date getInTime() {
		return inTime;
	}
	/**
	 * @param inTime the inTime to set
	 */
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	/**
	 * @return the outTime
	 */
	public Date getOutTime() {
		return outTime;
	}
	/**
	 * @param outTime the outTime to set
	 */
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
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
	 * @return the pageVisited
	 */
	public Integer getPageVisited() {
		return pageVisited;
	}
	/**
	 * @param pageVisited the pageVisited to set
	 */
	public void setPageVisited(Integer pageVisited) {
		this.pageVisited = pageVisited;
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}
	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
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

}
