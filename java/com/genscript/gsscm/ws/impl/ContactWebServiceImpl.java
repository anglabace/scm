package com.genscript.gsscm.ws.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.dto.ContactActivity;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.Contact;
import com.genscript.gsscm.contact.entity.ContactBean;
import com.genscript.gsscm.contact.entity.ContactGrants;
import com.genscript.gsscm.contact.entity.ContactPublications;
import com.genscript.gsscm.contact.service.ContactService;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.dto.CustContactInfoDTO;
import com.genscript.gsscm.customer.service.AddressService;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.ContactWebService;
import com.genscript.gsscm.ws.request.ContactRequest;
import com.genscript.gsscm.ws.response.ContactResponse;

@WebService(serviceName = "ContactService", portName = "ContactServicePort", endpointInterface = "com.genscript.gsscm.ws.api.ContactWebService", targetNamespace = WsConstants.NS)
public class ContactWebServiceImpl implements ContactWebService {
	@Autowired
	private AddressService addressService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public ContactResponse searchContactList(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		Page<ContactBean> page = null;
		ContactResponse response = new ContactResponse();
		Map<String, String> fiterMap = new HashMap<String, String>();
		Page<ContactBean> pageContact = contactRequest.getPageContactBean();
		if (!pageContact.isOrderBySetted()) {
			pageContact.setOrderBy("contactId");
			pageContact.setOrder(Page.ASC);
		}
		try {
			List<SearchProperty> srchPropList = contactRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = contactService.searchContact(pageContact);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propertyName = searchProperty.getPropertyName();
					String searchOperator = searchProperty.getSearchOperator()
							.name();
					String propertyType = searchProperty
							.getSearchPropertyType().name();
					String propertyValue1 = searchProperty.getPropertyValue1();
					String propertyValue2 = searchProperty.getPropertyValue2();
					if ("BETWEEN".equals(searchOperator)) {
						StringBuilder searchString1 = new StringBuilder();
						StringBuilder searchString2 = new StringBuilder();
						searchString1.append("GE").append(propertyType).append(
								"_").append(propertyName);
						searchString2.append("LE").append(propertyType).append(
								"_").append(propertyName);
						fiterMap.put(searchString1.toString(), propertyValue1
								.toString());
						fiterMap.put(searchString2.toString(), propertyValue2
								.toString());
					} else {
						StringBuilder searchString = new StringBuilder();
						searchString.append(searchOperator)
								.append(propertyType).append("_").append(
										propertyName);
						fiterMap.put(searchString.toString(), propertyValue1
								.toString());
					}
				}
				page = contactService.searchContact(pageContact, fiterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			response.setPagerDTO(pageDTO);
			List<ContactBean> list = page.getResult();
			final int size = list.size();
			response.setContactList(page.getResult().toArray(
					new ContactBean[size]));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0201",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}	

	@Override
	public ContactResponse getContactDetail(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		ContactResponse response = new ContactResponse();
		try {
			Integer contactNo = contactRequest.getContactNo();
			ContactModelDTO modelDTO = this.contactService
					.getContactDetail(contactNo);
			response.setContactModel(modelDTO);
			response.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0202",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public ContactResponse saveContact(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		ContactResponse response = new ContactResponse();
		try {
			Integer userId = contactRequest.getUserId();
			ContactModelDTO contactModel = contactRequest.getContactModel();
			contactModel.setModifiedBy(userId);

			Contact contact = this.contactService.saveContact(contactModel);
			response.setHasException(Boolean.FALSE);
			response.setContactNo(contact.getContactNo());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0203",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}
	
	@Override
	public ContactResponse delContact(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		ContactResponse response = new ContactResponse();
		List<Integer> delContactNoList = contactRequest.getDelContactNoList();
		int delSuccessCount = 0;
		int delTotal = 0;
		try {
			if (delContactNoList != null) {
				delTotal = delContactNoList.size();
			}
			for (Integer contactNo : delContactNoList) {
				delSuccessCount += this.contactService.delContact(loginUserId,
						contactNo);
			}
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		response.setDelSuccessCount(delSuccessCount);
		response.setDelTotal(delTotal);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.genscript.gsscm.ws.api.ContactWebService#getContactsList(com.genscript
	 * .gsscm.ws.request.ContactRequest)
	 */
	@Override
	public ContactResponse getContactsList(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		ContactResponse response = new ContactResponse();
		try {
			Integer contactNo = contactRequest.getContactNo();
			List<ContactDTO> contactList = this.contactService
					.getContactsList(contactNo);
			CustContactInfoDTO dto = this.contactService
					.getContactsInfo(contactNo);
			response.setContactsDTOList(contactList);
			response.setContactsInfo(dto);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0205",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public ContactResponse getAddrList(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		ContactResponse response = new ContactResponse();
		try {
			CustAddrSrchDTO custAddrSrchDTO = contactRequest
					.getCustAddrSrchDTO();
			List<ContactAddressDTO> dtoList = this.addressService
					.getContactAddrList(custAddrSrchDTO);
			response.setAddrDTOList(dtoList);
			response.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0206",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}


	@Override
	public ContactResponse getGrantsList(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		ContactResponse response = new ContactResponse();
		try {
			Integer contactNo = contactRequest.getContactNo();
			Page<ContactGrants> page = contactRequest.getPageGrants();
			page = this.contactService.getGrantsList(page, contactNo);
			List<ContactGrants> grantsList = page.getResult();
			PageDTO pageGrants = (PageDTO) dozer.map(page, PageDTO.class);
			response.setGrantsList(grantsList);
			response.setPagerDTO(pageGrants);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0207",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public ContactResponse getPublicationsList(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		ContactResponse response = new ContactResponse();
		try {
			Integer contactNo = contactRequest.getContactNo();
			Page<ContactPublications> page = contactRequest.getPagePubs();
			page = this.contactService.getPubsList(page, contactNo);
			List<ContactPublications> pubsList = page.getResult();
			PageDTO pageInfo = (PageDTO) dozer.map(page, PageDTO.class);
			response.setPubsList(pubsList);
			response.setPagerDTO(pageInfo);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0208",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public ContactResponse getContactHistory(ContactRequest contactRequest) {
		Integer loginUserId = contactRequest.getUserId();
		ContactResponse response = new ContactResponse();
		try {
			Integer contactNo = contactRequest.getContactNo();
			ContactActivity contactActivity = this.contactService.getContactActivity(contactNo);
			response.setHasException(Boolean.FALSE);
			response.setContactActivity(contactActivity);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0210",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

}
