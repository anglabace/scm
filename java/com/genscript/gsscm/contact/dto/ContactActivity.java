package com.genscript.gsscm.contact.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "ContactActivity", namespace = WsConstants.NS)
public class ContactActivity {
	private Date lastPhoneDate;
	private String lastPhoneContactUser;
	private String lastPhoneContactBy;
	private Date lastEmailSent;
	private String lastEmailSubject;
	private Date lastMailSent;
	private String lastMailSubject;
	private Date lastFaxSent;
	private String lastFaxSubject;
	private Date lastCatalogSent;
	private String lastCatalogName;
	private Date lastModifyDate;
	private String lastModifyUser;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the lastPhoneDate
	 */
	public Date getLastPhoneDate() {
		return lastPhoneDate;
	}
	/**
	 * @param lastPhoneDate the lastPhoneDate to set
	 */
	public void setLastPhoneDate(Date lastPhoneDate) {
		this.lastPhoneDate = lastPhoneDate;
	}
	/**
	 * @return the lastPhoneContactUser
	 */
	public String getLastPhoneContactUser() {
		return lastPhoneContactUser;
	}
	/**
	 * @param lastPhoneContactUser the lastPhoneContactUser to set
	 */
	public void setLastPhoneContactUser(String lastPhoneContactUser) {
		this.lastPhoneContactUser = lastPhoneContactUser;
	}
	/**
	 * @return the lastPhoneContactBy
	 */
	public String getLastPhoneContactBy() {
		return lastPhoneContactBy;
	}
	/**
	 * @param lastPhoneContactBy the lastPhoneContactBy to set
	 */
	public void setLastPhoneContactBy(String lastPhoneContactBy) {
		this.lastPhoneContactBy = lastPhoneContactBy;
	}
	/**
	 * @return the lastEmailSent
	 */
	public Date getLastEmailSent() {
		return lastEmailSent;
	}
	/**
	 * @param lastEmailSent the lastEmailSent to set
	 */
	public void setLastEmailSent(Date lastEmailSent) {
		this.lastEmailSent = lastEmailSent;
	}
	/**
	 * @return the lastEmailSubject
	 */
	public String getLastEmailSubject() {
		return lastEmailSubject;
	}
	/**
	 * @param lastEmailSubject the lastEmailSubject to set
	 */
	public void setLastEmailSubject(String lastEmailSubject) {
		this.lastEmailSubject = lastEmailSubject;
	}
	/**
	 * @return the lastMailSent
	 */
	public Date getLastMailSent() {
		return lastMailSent;
	}
	/**
	 * @param lastMailSent the lastMailSent to set
	 */
	public void setLastMailSent(Date lastMailSent) {
		this.lastMailSent = lastMailSent;
	}
	/**
	 * @return the lastMailSubject
	 */
	public String getLastMailSubject() {
		return lastMailSubject;
	}
	/**
	 * @param lastMailSubject the lastMailSubject to set
	 */
	public void setLastMailSubject(String lastMailSubject) {
		this.lastMailSubject = lastMailSubject;
	}
	/**
	 * @return the lastFaxSent
	 */
	public Date getLastFaxSent() {
		return lastFaxSent;
	}
	/**
	 * @param lastFaxSent the lastFaxSent to set
	 */
	public void setLastFaxSent(Date lastFaxSent) {
		this.lastFaxSent = lastFaxSent;
	}
	/**
	 * @return the lastFaxSubject
	 */
	public String getLastFaxSubject() {
		return lastFaxSubject;
	}
	/**
	 * @param lastFaxSubject the lastFaxSubject to set
	 */
	public void setLastFaxSubject(String lastFaxSubject) {
		this.lastFaxSubject = lastFaxSubject;
	}
	/**
	 * @return the lastCatalogSent
	 */
	public Date getLastCatalogSent() {
		return lastCatalogSent;
	}
	/**
	 * @param lastCatalogSent the lastCatalogSent to set
	 */
	public void setLastCatalogSent(Date lastCatalogSent) {
		this.lastCatalogSent = lastCatalogSent;
	}
	/**
	 * @return the lastCatalogName
	 */
	public String getLastCatalogName() {
		return lastCatalogName;
	}
	/**
	 * @param lastCatalogName the lastCatalogName to set
	 */
	public void setLastCatalogName(String lastCatalogName) {
		this.lastCatalogName = lastCatalogName;
	}
	/**
	 * @return the lastModifyDate
	 */
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	/**
	 * @param lastModifyDate the lastModifyDate to set
	 */
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	/**
	 * @return the lastModifyUser
	 */
	public String getLastModifyUser() {
		return lastModifyUser;
	}
	/**
	 * @param lastModifyUser the lastModifyUser to set
	 */
	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

}
