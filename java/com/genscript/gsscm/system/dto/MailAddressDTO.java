package com.genscript.gsscm.system.dto;

import com.genscript.gsscm.system.entity.MailAddress;

public class MailAddressDTO {
	private MailAddress mailAddress;
	private String modifiedByText;
	private String createdByText;
	private String deptName;
	public MailAddress getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(MailAddress mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getModifiedByText() {
		return modifiedByText;
	}
	public void setModifiedByText(String modifiedByText) {
		this.modifiedByText = modifiedByText;
	}
	public String getCreatedByText() {
		return createdByText;
	}
	public void setCreatedByText(String createdByText) {
		this.createdByText = createdByText;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
