package com.genscript.gsscm.system.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.system.entity.MailAddress;
import com.genscript.gsscm.system.entity.MailGroup;

@XmlType(name = "MailGroupDTO", namespace = WsConstants.NS)
public class MailGroupDTO {
	private MailGroup mailGroup;
	private List<MailAddress> mailAddress;
	private String modifiedByText;
	private String createdByText;
	public MailGroupDTO(){
		mailGroup = new MailGroup();
	}
	public MailGroup getMailGroup() {
		return mailGroup;
	}
	public void setMailGroup(MailGroup mailGroup) {
		this.mailGroup = mailGroup;
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
	public List<MailAddress> getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(List<MailAddress> mailAddress) {
		this.mailAddress = mailAddress;
	}
	
}
