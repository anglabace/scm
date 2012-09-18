package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.dto.ContactActivity;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.ContactBean;
import com.genscript.gsscm.contact.entity.ContactGrants;
import com.genscript.gsscm.contact.entity.ContactInterest;
import com.genscript.gsscm.contact.entity.ContactPublications;
import com.genscript.gsscm.customer.dto.CustContactInfoDTO;

@XmlType(name = "ContactResponse", namespace = WsConstants.NS)
public class ContactResponse extends WsResponse {

	private PageDTO pagerDTO;
	private Page<ContactBean> pagerContact;
	private ContactBean[] contactList;
	private List<ContactDTO> contactsDTOList;
	private CustContactInfoDTO contactsInfo;
	private List<ContactAddressDTO> addrDTOList;
	private ContactModelDTO contactModel;	
	private List<ContactGrants> grantsList;
	private List<ContactInterest> intsList;
	private List<ContactPublications> pubsList;
	private Integer contactNo;
	private Integer delTotal;
	private Integer delSuccessCount;
	private ContactActivity contactActivity;
	public PageDTO getPagerDTO() {
		return pagerDTO;
	}

	public void setPagerDTO(PageDTO pagerDTO) {
		this.pagerDTO = pagerDTO;
	}

	public Page<ContactBean> getPagerContact() {
		return pagerContact;
	}

	public void setPagerContact(Page<ContactBean> pagerContact) {
		this.pagerContact = pagerContact;
	}

	public ContactBean[] getContactList() {
		return contactList;
	}

	public void setContactList(ContactBean[] contactList) {
		this.contactList = contactList;
	}

	/**
	 * @return the contactsDTOList
	 */
	public List<ContactDTO> getContactsDTOList() {
		return contactsDTOList;
	}

	/**
	 * @param contactsDTOList
	 *            the contactsDTOList to set
	 */
	public void setContactsDTOList(List<ContactDTO> contactsDTOList) {
		this.contactsDTOList = contactsDTOList;
	}

	/**
	 * @return the addrDTOList
	 */
	public List<ContactAddressDTO> getAddrDTOList() {
		return addrDTOList;
	}

	/**
	 * @param addrDTOList
	 *            the addrDTOList to set
	 */
	public void setAddrDTOList(List<ContactAddressDTO> addrDTOList) {
		this.addrDTOList = addrDTOList;
	}

	/**
	 * @return the contactModel
	 */
	public ContactModelDTO getContactModel() {
		return contactModel;
	}

	/**
	 * @param contactModel
	 *            the contactModel to set
	 */
	public void setContactModel(ContactModelDTO contactModel) {
		this.contactModel = contactModel;
	}

	/**
	 * @return the contactNo
	 */
	public Integer getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(Integer contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the contactsInfo
	 */
	public CustContactInfoDTO getContactsInfo() {
		return contactsInfo;
	}

	/**
	 * @param contactsInfo the contactsInfo to set
	 */
	public void setContactsInfo(CustContactInfoDTO contactsInfo) {
		this.contactsInfo = contactsInfo;
	}

	/**
	 * @return the grantsList
	 */
	public List<ContactGrants> getGrantsList() {
		return grantsList;
	}

	/**
	 * @param grantsList the grantsList to set
	 */
	public void setGrantsList(List<ContactGrants> grantsList) {
		this.grantsList = grantsList;
	}

	/**
	 * @return the intsList
	 */
	public List<ContactInterest> getIntsList() {
		return intsList;
	}

	/**
	 * @param intsList the intsList to set
	 */
	public void setIntsList(List<ContactInterest> intsList) {
		this.intsList = intsList;
	}

	/**
	 * @return the pubsList
	 */
	public List<ContactPublications> getPubsList() {
		return pubsList;
	}

	/**
	 * @param pubsList the pubsList to set
	 */
	public void setPubsList(List<ContactPublications> pubsList) {
		this.pubsList = pubsList;
	}

	/**
	 * @return the delTotal
	 */
	public Integer getDelTotal() {
		return delTotal;
	}

	/**
	 * @param delTotal the delTotal to set
	 */
	public void setDelTotal(Integer delTotal) {
		this.delTotal = delTotal;
	}

	/**
	 * @return the delSuccessCount
	 */
	public Integer getDelSuccessCount() {
		return delSuccessCount;
	}

	/**
	 * @param delSuccessCount the delSuccessCount to set
	 */
	public void setDelSuccessCount(Integer delSuccessCount) {
		this.delSuccessCount = delSuccessCount;
	}

	/**
	 * @return the contactActivity
	 */
	public ContactActivity getContactActivity() {
		return contactActivity;
	}

	/**
	 * @param contactActivity the contactActivity to set
	 */
	public void setContactActivity(ContactActivity contactActivity) {
		this.contactActivity = contactActivity;
	}

}
