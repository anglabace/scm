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
 * CUSTOMER_CONTACT_HISTORY.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "customer_contact_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerContactHistory  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6282250127426349034L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id")
	private Integer contactId;
	private Integer custNo;
	private Integer contactBy;
	private Date contactDate;
	private Date scheduleDate;
	private Integer source;
	private String contactName;
	private String contactMethod;
	private String phone;
	private String phoneExt;
	private String fax;
	private String faxExt;
	private String email;
	private String address;
	private String subject;
	private String contentType;
	private String referName;
	private String content;
	private Integer interestScore;
	private String status;
	private String callTmzn;
	private String callTime;
	@Column(insertable=true,updatable=false)
	private Date creationDate;
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the contactId
	 */
	public Integer getContactId() {
		return contactId;
	}
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
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
	 * @return the contactBy
	 */
	public Integer getContactBy() {
		return contactBy;
	}
	/**
	 * @param contactBy the contactBy to set
	 */
	public void setContactBy(Integer contactBy) {
		this.contactBy = contactBy;
	}
	/**
	 * @return the contactDate
	 */
	public Date getContactDate() {
		return contactDate;
	}
	/**
	 * @param contactDate the contactDate to set
	 */
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
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
	 * @return the source
	 */
	public Integer getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return the contactMethod
	 */
	public String getContactMethod() {
		return contactMethod;
	}
	/**
	 * @param contactMethod the contactMethod to set
	 */
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the phoneExt
	 */
	public String getPhoneExt() {
		return phoneExt;
	}
	/**
	 * @param phoneExt the phoneExt to set
	 */
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return the faxExt
	 */
	public String getFaxExt() {
		return faxExt;
	}
	/**
	 * @param faxExt the faxExt to set
	 */
	public void setFaxExt(String faxExt) {
		this.faxExt = faxExt;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the interestScore
	 */
	public Integer getInterestScore() {
		return interestScore;
	}
	/**
	 * @param interestScore the interestScore to set
	 */
	public void setInterestScore(Integer interestScore) {
		this.interestScore = interestScore;
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
	 * @return the callTmzn
	 */
	public String getCallTmzn() {
		return callTmzn;
	}
	/**
	 * @param callTmzn the callTmzn to set
	 */
	public void setCallTmzn(String callTmzn) {
		this.callTmzn = callTmzn;
	}
	/**
	 * @return the callTime
	 */
	public String getCallTime() {
		return callTime;
	}
	/**
	 * @param callTime the callTime to set
	 */
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the referName
	 */
	public String getReferName() {
		return referName;
	}
	/**
	 * @param referName the referName to set
	 */
	public void setReferName(String referName) {
		this.referName = referName;
	}

}
