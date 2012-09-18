package com.genscript.gsscm.ws.client;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

/**
 * Web Service传输User信息的DTO.
 * 
 * @author golf
 */

@XmlType(name = "User", namespace = WsConstants.NS)
public class UserDTO {
	private String samAccount;
	private String fullName;
	private String lastName;
	private String givenName;
	private String displayName;
	private String description;
	private String phone;
	private String email;
	private String userPrincipalName;
	private String accountControl;
	private String comment;

	public UserDTO(String samAccount, String fullName, String lastName,
			String description, String phone, String email,
			String accountControl,String comment) {
		super();
		this.samAccount = samAccount;
		this.fullName = fullName;
		this.lastName = lastName;
		this.description = description;
		this.phone = phone;
		this.email = email;
		this.accountControl = accountControl;
		this.comment = comment;
	}

	public UserDTO(){}



	public String getSamAccount() {
		return samAccount;
	}

	public void setSamAccount(String samAccount) {
		this.samAccount = samAccount;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountControl() {
		return accountControl;
	}
	public void setAccountControl(String accountControl) {
		this.accountControl = accountControl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
