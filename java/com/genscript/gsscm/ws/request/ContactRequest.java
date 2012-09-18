package com.genscript.gsscm.ws.request;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.ContactBean;
import com.genscript.gsscm.contact.entity.ContactGrants;
import com.genscript.gsscm.contact.entity.ContactPublications;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;

@XmlType(name = "ContactRequest", namespace = WsConstants.NS)
public class ContactRequest extends WsRequest {

	private Integer contactNo;
	private Page<ContactBean> pageContactBean;
	private List<Integer> delContactNoList;
	private List<SearchProperty> searchPropertyList;
	private ContactModelDTO contactModel;
    private Page<ContactGrants> pageGrants;
    private Page<ContactPublications> pagePubs;    
	private CustAddrSrchDTO custAddrSrchDTO;
    
	public Page<ContactBean> getPageContactBean() {
		return pageContactBean;
	}
	public void setPageContactBean(Page<ContactBean> pageContactBean) {
		this.pageContactBean = pageContactBean;
	}
	public List<SearchProperty> getSearchPropertyList() {
		return searchPropertyList;
	}
	public void setSearchPropertyList(List<SearchProperty> searchPropertyList) {
		this.searchPropertyList = searchPropertyList;
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
	 * @return the contactModel
	 */
	public ContactModelDTO getContactModel() {
		return contactModel;
	}
	/**
	 * @param contactModel the contactModel to set
	 */
	public void setContactModel(ContactModelDTO contactModel) {
		this.contactModel = contactModel;
	}
	/**
	 * @return the pageGrants
	 */
	public Page<ContactGrants> getPageGrants() {
		return pageGrants;
	}
	/**
	 * @param pageGrants the pageGrants to set
	 */
	public void setPageGrants(Page<ContactGrants> pageGrants) {
		this.pageGrants = pageGrants;
	}
	/**
	 * @return the pagePubs
	 */
	public Page<ContactPublications> getPagePubs() {
		return pagePubs;
	}
	/**
	 * @param pagePubs the pagePubs to set
	 */
	public void setPagePubs(Page<ContactPublications> pagePubs) {
		this.pagePubs = pagePubs;
	}
	/**
	 * @return the delContactNoList
	 */
	public List<Integer> getDelContactNoList() {
		return delContactNoList;
	}
	/**
	 * @param delContactNoList the delContactNoList to set
	 */
	public void setDelContactNoList(List<Integer> delContactNoList) {
		this.delContactNoList = delContactNoList;
	}
	/**
	 * @return the custAddrSrchDTO
	 */
	public CustAddrSrchDTO getCustAddrSrchDTO() {
		return custAddrSrchDTO;
	}
	/**
	 * @param custAddrSrchDTO the custAddrSrchDTO to set
	 */
	public void setCustAddrSrchDTO(CustAddrSrchDTO custAddrSrchDTO) {
		this.custAddrSrchDTO = custAddrSrchDTO;
	}

	
}
