package com.genscript.gsscm.customer.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "CustContactInfoDTO", namespace = WsConstants.NS)
public class CustContactInfoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1510059634346219560L;
	private Integer phoneCount;
	private Integer emailCount;
	private Integer faxCount;
	private Integer letterCount;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the phoneCount
	 */
	public Integer getPhoneCount() {
		return phoneCount;
	}
	/**
	 * @param phoneCount the phoneCount to set
	 */
	public void setPhoneCount(Integer phoneCount) {
		this.phoneCount = phoneCount;
	}
	/**
	 * @return the emailCount
	 */
	public Integer getEmailCount() {
		return emailCount;
	}
	/**
	 * @param emailCount the emailCount to set
	 */
	public void setEmailCount(Integer emailCount) {
		this.emailCount = emailCount;
	}
	/**
	 * @return the faxCount
	 */
	public Integer getFaxCount() {
		return faxCount;
	}
	/**
	 * @param faxCount the faxCount to set
	 */
	public void setFaxCount(Integer faxCount) {
		this.faxCount = faxCount;
	}
	/**
	 * @return the letterCount
	 */
	public Integer getLetterCount() {
		return letterCount;
	}
	/**
	 * @param letterCount the letterCount to set
	 */
	public void setLetterCount(Integer letterCount) {
		this.letterCount = letterCount;
	}
}
