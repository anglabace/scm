package com.genscript.gsscm.ws.response;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.privilege.dto.EmailCampaignDTO;

@XmlType(name="MailRequestResponse", namespace=WsConstants.NS)
public class MailRequestResponse extends WsResponse {
	private EmailCampaignDTO emailCampaignDTO;
	private ContactModelDTO contactDetail;
	private CustomerDTO customerDetail;
	//private List<AllCountryDTO> countryStateList;

	public EmailCampaignDTO getEmailCampaignDTO() {
		return emailCampaignDTO;
	}

	public void setEmailCampaignDTO(EmailCampaignDTO emailCampaignDTO) {
		this.emailCampaignDTO = emailCampaignDTO;
	}

	public CustomerDTO getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDTO customerDetail) {
		this.customerDetail = customerDetail;
	}

	public ContactModelDTO getContactDetail() {
		return contactDetail;
	}

	public void setContactDetail(ContactModelDTO contactDetail) {
		this.contactDetail = contactDetail;
	}

	/*public List<AllCountryDTO> getCountryStateList() {
		return countryStateList;
	}

	public void setCountryStateList(List<AllCountryDTO> countryStateList) {
		this.countryStateList = countryStateList;
	}*/
	
	
}
