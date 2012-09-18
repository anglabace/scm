package com.genscript.gsscm.ws.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.genscript.gsscm.basedata.dto.AllCountryDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.common.util.MailCampaignWSUtil;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.service.ContactService;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.privilege.dto.EmailCampaignDTO;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.MailCampaignWebService;
import com.genscript.gsscm.ws.request.MailRequest;
import com.genscript.gsscm.ws.response.MailRequestResponse;

@WebService(serviceName = "MailCampaignService", portName = "MailCampaignServicePort", endpointInterface = "com.genscript.gsscm.ws.api.MailCampaignWebService", targetNamespace = WsConstants.NS)
public class MailCampaignWebServiceImpl implements MailCampaignWebService{

	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private PublicService publicService;
	
	@Override
	public MailRequestResponse getRoleByUserId(MailRequest mailRequest) {
		MailRequestResponse mailResponse = new MailRequestResponse();
		Integer userId = mailRequest.getUserId();
		EmailCampaignDTO emailCam = new EmailCampaignDTO(); 
		emailCam.setUserId(userId);
		emailCam.setFlase(false);
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(mailRequest.getSessionUserId(), "sessionUser id can't be null");
			if(mailRequest.getSessionUserId().equals(MailCampaignWSUtil.getSessionIdByUserId(userId.toString()))){
				MailCampaignWSUtil.rmoveUserIdMap(userId.toString());
				List<PrivilegeDTO> treeByECList = this.privilegeService.getECListForUser(userId);
				UserDTO user = this.privilegeService.getUser(userId);
				emailCam.setUserDTO(user);
				emailCam.setTreeList(this.privilegeService.getMenuListForUser(userId));
				emailCam.setTreeList(treeByECList);
				emailCam.setFlase(true);
			}else{
				emailCam.setFlase(false);
			}
			mailResponse.setEmailCampaignDTO(emailCam);
			mailResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			mailResponse.setHasException(true);
			mailResponse.setWsException(exDTO);
		}
		return mailResponse;
	}

	@Override
	public MailRequestResponse getCustomerDetailByCustNo(MailRequest mailRequest) {
		MailRequestResponse mailResponse = new MailRequestResponse();
		Integer userId = mailRequest.getUserId();
		CustomerDTO customerDetail = new CustomerDTO();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(mailRequest.getCustNo(), "Customer_no id can't be null");
			customerDetail = customerService.getCustomerDetail(mailRequest.getCustNo());
			List<AllCountryDTO> countryStateList = publicService.getAllCountryState();
			for(AllCountryDTO countryName : countryStateList){
				if(countryName.getCountryCode().equals(customerDetail.getCountry())){
					customerDetail.setCountry(countryName.getName());
				}
			}
			//mailResponse.setCountryStateList(countryStateList);
			mailResponse.setCustomerDetail(customerDetail);
			mailResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			mailResponse.setHasException(true);
			mailResponse.setWsException(exDTO);
		}
		return mailResponse;
	}

	@Override
	public MailRequestResponse getContactDetailByContactNo(
			MailRequest mailRequest) {
		MailRequestResponse mailResponse = new MailRequestResponse();
		Integer userId = mailRequest.getUserId();
		ContactModelDTO contactDetail = new ContactModelDTO();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(mailRequest.getContactNo(), "Contact_no id can't be null");
			contactDetail = this.contactService.getContactDetail(mailRequest.getContactNo());
			List<AllCountryDTO> countryStateList = publicService.getAllCountryState();
			for(AllCountryDTO countryName : countryStateList){
				if(countryName.getCountryCode().equals(contactDetail.getCountry())){
					contactDetail.setCountry(countryName.getName());
				}
			}
			//mailResponse.setCountryStateList(countryStateList);
			mailResponse.setContactDetail(contactDetail);
			mailResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			mailResponse.setHasException(true);
			mailResponse.setWsException(exDTO);
		}
		return mailResponse;
	}
}
