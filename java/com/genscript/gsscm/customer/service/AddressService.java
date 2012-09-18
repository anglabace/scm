package com.genscript.gsscm.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.entity.ContactAddress;
import com.genscript.gsscm.customer.dao.AddressDao;
import com.genscript.gsscm.customer.dao.ContactAddressDao;/*
import com.genscript.gsscm.customer.dao.OrganizationDao;*/
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.entity.Address;/*
import com.genscript.gsscm.customer.entity.Organization;*/
import com.genscript.gsscm.customer.service.DepartmentService.StatusType;

@Service
@Transactional
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ContactAddressDao contactAddressDao;
	/*@Autowired
	private OrganizationDao organizationDao;*/
	@Autowired
	private DozerBeanMapper dozer;/*
	@Autowired
	private OrganizationDao orgDao;
*/
	/**
	 * 根据CustomerAddress主健获得Customer's Address
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Address getAddress(Integer id) {
		return addressDao.get(id);
	}

	/**
	 * 根据ContactAddress主健获得Contact's Address
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public ContactAddress getContactAddress(Integer id) {
		return contactAddressDao.get(id);
	}

	
	public void saveAddress(Address entity) {
		addressDao.save(entity);
	}

	public void deleteAddress(Integer id) {
		addressDao.delete(id);
	}

	public List<CustAddrOperDTO> getAddrList(CustAddrSrchDTO addrSrch) {
		List<CustAddrOperDTO> dtoList = new ArrayList<CustAddrOperDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (addrSrch.getCustNo() != null
				&& addrSrch.getCustNo().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter("EQI_custNo",
					addrSrch.getCustNo());
			filterList.add(orgFilter);
		}
		if (addrSrch.getFirstName() != null
				&& addrSrch.getFirstName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_firstName",
					addrSrch.getFirstName());
			filterList.add(filter);
		}
		if (addrSrch.getLastName() != null
				&& addrSrch.getLastName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_lastName",
					addrSrch.getLastName());
			filterList.add(filter);
		}
		if (addrSrch.getEmail() != null
				&& addrSrch.getEmail().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_busEmail",
					addrSrch.getEmail());
			filterList.add(filter);
		}
		if (addrSrch.getAddrType() != null
				&& addrSrch.getAddrType().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_addrType",
					addrSrch.getAddrType());
			filterList.add(filter);
		}
	/*	if (addrSrch.getOrgId() != null && addrSrch.getOrgId().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter(
					"EQI_organization.orgId", addrSrch.getOrgId());
			filterList.add(orgFilter);
		}*/
		PropertyFilter statusFilter = new PropertyFilter("EQS_status",
				StatusType.ACTIVE.getValue());
		filterList.add(statusFilter);
		List<Address> addrList = this.addressDao.find(filterList);
		if (addrList != null) {
			for (Address addr : addrList) {
				CustAddrOperDTO dto = dozer.map(addr, CustAddrOperDTO.class);
				System.out.println(">>>>>>>>>>>"+dto.getOrgName());
				System.out.println(">>>>>>>>>>"+addrSrch.getOrgName());
			/*	if(dto.getOrgId()!=null){
					Organization dtoOrg = this.orgDao.getById(dto.getOrgId());
					dto.setOrgName(dtoOrg.getName());
					
				}else{
					dto.setOrgName("");
				} if (dto.getOrganization() != null) {
					Organization dtoOrg = new Organization();
					dtoOrg.setOrgId(addr.getOrganization().getOrgId());
					dtoOrg.setName(addr.getOrganization().getName());
					
					dto.setOrganization(null);
				} else {
					dto.setOrganization(null);
				} */
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	@SuppressWarnings("unchecked")
	public Page<CustAddrOperDTO> getAddrList(Page<CustAddrOperDTO> page, CustAddrSrchDTO addrSrch) {
		List<CustAddrOperDTO> dtoList = new ArrayList<CustAddrOperDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (addrSrch.getCustNo() != null
				&& addrSrch.getCustNo().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter("EQI_custNo",
					addrSrch.getCustNo());
			filterList.add(orgFilter);
		}
		if (addrSrch.getFirstName() != null
				&& addrSrch.getFirstName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_firstName",
					addrSrch.getFirstName());
			filterList.add(filter);
		}
		if (addrSrch.getLastName() != null
				&& addrSrch.getLastName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_lastName",
					addrSrch.getLastName());
			filterList.add(filter);
		}
		if (addrSrch.getEmail() != null
				&& addrSrch.getEmail().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_busEmail",
					addrSrch.getEmail());
			filterList.add(filter);
		}
		if (addrSrch.getAddrType() != null
				&& addrSrch.getAddrType().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_addrType",
					addrSrch.getAddrType());
			filterList.add(filter);
		}
	/*	if (addrSrch.getOrgId() != null && addrSrch.getOrgId().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter(
					"EQI_organization.orgId", addrSrch.getOrgId());
			filterList.add(orgFilter);
		}*/
		Page<Address> addressPage = dozer.map(page, Page.class);
		addressPage = this.addressDao.findPage(addressPage, filterList);
		List<Address> addrList = addressPage.getResult();
		
		if (addrList != null) {
			for (Address addr : addrList) {
				CustAddrOperDTO dto = dozer.map(addr, CustAddrOperDTO.class); 
				dtoList.add(dto);
			}
		}
		addressPage.setResult(null);
		page = dozer.map(addressPage, Page.class);
		page.setResult(dtoList);
		return page;
	}

	public CustAddrOperDTO getAddressDTO(Integer addrId) {
		CustAddrOperDTO custAddrOperDTO = new CustAddrOperDTO();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQI_addrId", addrId);
		filterList.add(filter);
		List<Address> addrList = this.addressDao.find(filterList);
		if(addrList != null && !addrList.isEmpty()){
			Address address = addrList.get(0);
			custAddrOperDTO = dozer.map(address, CustAddrOperDTO.class); 
		}
		return custAddrOperDTO;
	}
	
	public List<ContactAddressDTO> getContactAddrList(CustAddrSrchDTO addrSrch) {
		List<ContactAddressDTO> dtoList = new ArrayList<ContactAddressDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (addrSrch == null) {
			addrSrch = new CustAddrSrchDTO();
		}
		if (addrSrch.getCustNo() != null
				&& addrSrch.getCustNo().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter("EQI_contactNo",
					addrSrch.getCustNo());
			filterList.add(orgFilter);
		}
		if (addrSrch.getFirstName() != null
				&& addrSrch.getFirstName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_firstName",
					addrSrch.getFirstName());
			filterList.add(filter);
		}
		if (addrSrch.getLastName() != null
				&& addrSrch.getLastName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_lastName",
					addrSrch.getLastName());
			filterList.add(filter);
		}
		if (addrSrch.getEmail() != null
				&& addrSrch.getEmail().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_busEmail",
					addrSrch.getEmail());
			filterList.add(filter);
		}
		if (addrSrch.getAddrType() != null
				&& addrSrch.getAddrType().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_addrType",
					addrSrch.getAddrType());
			filterList.add(filter);
		}
	/*	if (addrSrch.getOrgId() != null && addrSrch.getOrgId().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter(
					"EQI_organization.orgId", addrSrch.getOrgId());
			filterList.add(orgFilter);
		}*/
		List<ContactAddress> addrList = this.contactAddressDao.find(filterList);
		if (addrList != null) {
			for (ContactAddress addr : addrList) {
				ContactAddressDTO dto = dozer
						.map(addr, ContactAddressDTO.class); 
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/**
	 * 分页查找
	 * @param addrSrch
	 * @return
	 */
	public Page<ContactAddress> getContactAddrPage(CustAddrSrchDTO addrSrch,Page<ContactAddress> addressPage) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (addrSrch == null) {
			addrSrch = new CustAddrSrchDTO();
		}
		if (addrSrch.getCustNo() != null
				&& addrSrch.getCustNo().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter("EQI_contactNo",
					addrSrch.getCustNo());
			filterList.add(orgFilter);
		}
		if (addrSrch.getFirstName() != null
				&& addrSrch.getFirstName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_firstName",
					addrSrch.getFirstName());
			filterList.add(filter);
		}
		if (addrSrch.getLastName() != null
				&& addrSrch.getLastName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_lastName",
					addrSrch.getLastName());
			filterList.add(filter);
		}
		if (addrSrch.getEmail() != null
				&& addrSrch.getEmail().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_busEmail",
					addrSrch.getEmail());
			filterList.add(filter);
		}
		if (addrSrch.getAddrType() != null
				&& addrSrch.getAddrType().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_addrType",
					addrSrch.getAddrType());
			filterList.add(filter);
		}
	/*	if (addrSrch.getOrgId() != null && addrSrch.getOrgId().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter(
					"EQI_organization.orgId", addrSrch.getOrgId());
			filterList.add(orgFilter);
		}*/
		addressPage = this.contactAddressDao.findPage(addressPage,filterList);
		 
		return addressPage;
	}
}
