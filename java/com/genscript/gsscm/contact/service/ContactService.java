package com.genscript.gsscm.contact.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.ContactMethod;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.contact.dao.ContactBeanDao;
import com.genscript.gsscm.contact.dao.ContactContactHistDao;
import com.genscript.gsscm.contact.dao.ContactDao;
import com.genscript.gsscm.contact.dao.ContactGrantsDao;
import com.genscript.gsscm.contact.dao.ContactInterestDao;
import com.genscript.gsscm.contact.dao.ContactPersInfoDao;
import com.genscript.gsscm.contact.dao.ContactPublicationDao;
import com.genscript.gsscm.contact.dto.ContactActivity;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.Contact;
import com.genscript.gsscm.contact.entity.ContactAddress;
import com.genscript.gsscm.contact.entity.ContactBean;
import com.genscript.gsscm.contact.entity.ContactContactHistory;
import com.genscript.gsscm.contact.entity.ContactGrants;
import com.genscript.gsscm.contact.entity.ContactInterest;
import com.genscript.gsscm.contact.entity.ContactPersonalInfo;
import com.genscript.gsscm.contact.entity.ContactPublications;
import com.genscript.gsscm.customer.dao.AddressDao;
import com.genscript.gsscm.customer.dao.ContactAddressDao;
import com.genscript.gsscm.customer.dao.DepartmentDao;
import com.genscript.gsscm.customer.dao.DivisionDao;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.dao.SourceDao;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustContactInfoDTO;
import com.genscript.gsscm.customer.entity.Address;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;

@Service
@Transactional
public class ContactService {
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private ContactBeanDao contactBeanDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ContactGrantsDao contactGrantsDao;
	@Autowired
	private ContactContactHistDao contactContactHistDao;
	@Autowired
	private ContactAddressDao contactAddressDao;
	@Autowired
	private ContactPublicationDao contactPublicationDao;
	@Autowired
	private ContactPersInfoDao contactPersInfoDao;
	@Autowired
	private ContactInterestDao contactInterestDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private DivisionDao divisionDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private SourceDao sourceDao;

	public int delContact(Integer userId, Integer contactNo) {
		return this.contactDao.delContact(userId, contactNo);
	}

	@Transactional(readOnly = true)
	public Page<ContactBean> searchContact(Page<ContactBean> page) {
		Page<ContactBean> contactList = contactBeanDao.getcontactList(page);
		return contactList;
	}

	@Transactional(readOnly = true)
	public Page<ContactBean> searchContact(Page<ContactBean> page,
			List<PropertyFilter> filters) {
		Page<ContactBean> contactList = contactBeanDao.searchContact(page,
				filters);
		return contactList;
	}

	/**
	 * 根据departmentId分页查找Contacts
	 * 
	 * @author wangsf
	 * @serialData 2011-03-02
	 * @param page
	 * @param deptId
	 * @return
	 */
	public Page<Contact> searchByDept(Page<Contact> page, Integer deptId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter pf = new PropertyFilter("EQI_deptId", deptId);
		filters.add(pf);
		return this.contactDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ContactBean> searchContact(Page<ContactBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return contactBeanDao.findPage(page, filterList);
	}

	private Organization attachOrg(Contact contact, Organization org) {
		if (org == null) {
			return null;
		}
		boolean bNew = org.getOrgId() == null
				|| (org.getOrgId().intValue() == 0);
		if (bNew
				&& (org.getName() == null || org.getName().trim().length() < 1)) {
			return org;
		}
		Integer id = org.getOrgId();
		Date now = new Date();
		org.setModifyDate(now);
		org.setModifiedBy(contact.getModifiedBy());
		if (id == null || id.intValue() == 0) {
			org.setOrgId(null);
			org.setCreatedBy(contact.getModifiedBy());
			org.setCreationDate(now);
		}
		this.organizationDao.save(org);
		contact.setOrgId(org.getOrgId());
		return org;
	}

	private Divisions attachDivision(Contact contact, Divisions div) {
		if (div == null) {
			return null;
		}
		boolean bNew = div.getDivisionId() == null
				|| div.getDivisionId().intValue() == 0;
		if (bNew
				&& (div.getName() == null || div.getName().trim().length() < 1)) {
			return div;
		}
		Integer id = div.getDivisionId();
		Date now = new Date();
		div.setModifyDate(now);
		div.setModifiedBy(contact.getModifiedBy());
		if (div.getOrgId() != null && div.getOrgId().intValue() == 0) {
			div.setOrgId(null);
		}
		if (id == null || id.intValue() == 0) {// new
			div.setDivisionId(null);
			div.setCreatedBy(contact.getModifiedBy());
			div.setCreationDate(now);
		}
		this.divisionDao.save(div);
		contact.setDivisionId(div.getDivisionId());
		return div;
	}

	private Departments attachDept(Contact contact, Departments dept) {
		if (dept == null) {
			return null;
		}
		boolean bNew = dept.getDeptId() == null
				|| dept.getDeptId().intValue() == 0;
		if (bNew
				&& (dept.getName() == null || dept.getName().trim().length() < 1)) {
			return dept;
		}
		Integer id = dept.getDeptId();
		Date now = new Date();
		dept.setModifyDate(now);
		dept.setModifiedBy(contact.getModifiedBy());
		if (dept.getOrgId() != null && dept.getOrgId().intValue() == 0) {
			dept.setOrgId(null);
		}
		if (dept.getDivisionId() != null
				&& dept.getDivisionId().intValue() == 0) {
			dept.setDivisionId(null);
		}
		if (id == null || id.intValue() == 0) {
			dept.setDeptId(null);
			dept.setCreatedBy(contact.getModifiedBy());
			dept.setCreationDate(now);
		}
		this.departmentDao.save(dept);
		contact.setDeptId(dept.getDeptId());
		return dept;
	}

	public void saveOrUpdateContact(Contact contact) {
		Integer contactNo = contact.getContactNo();
		Date now = new Date();
		contact.setModifyDate(now);
		if (contactNo == null || contactNo.intValue() == 0) {
			contact.setContactNo(null);
			contact.setCreatedBy(contact.getModifiedBy());
			contact.setCreationDate(now);
			contact.setStatus("ACTIVE");
		}
		if (contact.getBstCallTimeFrom() != null
				&& contact.getBstCallTimeFrom().trim().length() < 1) {
			contact.setBstCallTimeFrom(null);
		}
		if (contact.getBstCallTimeTo() != null
				&& contact.getBstCallTimeTo().trim().length() < 1) {
			contact.setBstCallTimeTo(null);
		}
		this.contactDao.save(contact);
	}

	private void attachInterest(Contact contact,
			List<ContactInterest> interestList, List<Integer> delInstIdList) {
		if (delInstIdList != null && !delInstIdList.isEmpty()) {
			this.contactInterestDao.delList(delInstIdList,
					contact.getContactNo());
		}
		if (interestList == null || interestList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (ContactInterest intereset : interestList) {
			intereset.setInterestId(null);
			intereset.setCreatedBy(contact.getModifiedBy());
			intereset.setModifiedBy(contact.getModifiedBy());
			intereset.setContactNo(contact.getContactNo());
			intereset.setCreationDate(now);
			intereset.setModifyDate(now);
			this.contactInterestDao.save(intereset);
		}
	}

	private void attachAddress(Contact contact,
			List<ContactAddressDTO> addrList, List<Integer> delAddrIdList) {
		if (delAddrIdList != null && !delAddrIdList.isEmpty()) {
			this.contactAddressDao.delAddressList(delAddrIdList);
		}
		if (addrList == null || addrList.isEmpty()) {
			return;
		}
		Integer contactNo = contact.getContactNo();
		Date now = new Date();
		for (ContactAddressDTO dto : addrList) {
			ContactAddress addr = dozer.map(dto, ContactAddress.class);
			if (addr.getAddrId() == null || addr.getAddrId().intValue() == 0) {
				addr.setAddrId(null);
				addr.setCreatedBy(contact.getModifiedBy());
				addr.setCreationDate(now);
			}
			if (addr.getDefaultFlag() != null
					&& addr.getDefaultFlag().equals("Y")) {
				this.contactAddressDao.removeDefaltFlag(addr.getAddrId(),
						addr.getAddrType(), contactNo);
			}
			/*
			 * Organization addrOrg = addr.getOrganization(); if (addrOrg ==
			 * null || addrOrg.getOrgId() == null ||
			 * addrOrg.getOrgId().intValue() == 0) { Organization org = new
			 * Organization(); org.setOrgId(contact.getOrgId());
			 * addr.setOrganization(org); }
			 */
			addr.setModifyDate(now);
			addr.setModifiedBy(contact.getModifiedBy());
			addr.setContactNo(contactNo);
			this.contactAddressDao.save(addr);

		}
	}

	private void attachContact(Contact contact,
			List<ContactDTO> contactDTOList, List<Integer> delContactsIdList)
			throws ParseException {
		if (delContactsIdList != null && !delContactsIdList.isEmpty()) {
			this.contactContactHistDao.delContactsList(delContactsIdList);
		}
		if (contactDTOList == null || contactDTOList.isEmpty()) {
			return;
		}
		Date now = new Date();
		Integer contactNo = contact.getContactNo();
		for (ContactDTO dto : contactDTOList) {
			if (dto.getCallTime() == null
					|| dto.getCallTime().trim().length() < 1) {
				dto.setCallTime(null);
			}
			ContactContactHistory history = dozer.map(dto,
					ContactContactHistory.class);
			history.setContactNo(contactNo);
			history.setModifyDate(now);
			history.setModifiedBy(contact.getModifiedBy());
			if (dto.getContactId() == null
					|| dto.getContactId().intValue() == 0) {
				history.setContactId(null);
				history.setCreatedBy(contact.getModifiedBy());
				history.setCreationDate(now);
			}
			this.contactContactHistDao.save(history);
		}
	}

	private void attachPublications(Contact contact,
			List<ContactPublications> pubsList, List<Integer> delPubIdList) {
		if (delPubIdList != null && !delPubIdList.isEmpty()) {
			this.contactPublicationDao.delPubsList(delPubIdList);
		}
		if (pubsList == null || pubsList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (ContactPublications pub : pubsList) {
			pub.setModifyDate(now);
			pub.setModifiedBy(contact.getModifiedBy());
			pub.setContactNo(contact.getContactNo());
			if (pub.getId() == null || pub.getId().intValue() == 0) {
				pub.setId(null);
				pub.setCreatedBy(pub.getModifiedBy());
				pub.setCreationDate(now);
			}
			this.contactPublicationDao.save(pub);
		}
	}

	private void attachGrants(Contact contact, List<ContactGrants> grantsList,
			List<Integer> delIdList) {
		if (delIdList != null && !delIdList.isEmpty()) {
			this.contactGrantsDao.delGrantsList(delIdList);
		}
		if (grantsList == null || grantsList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (ContactGrants grants : grantsList) {
			grants.setModifyDate(now);
			grants.setModifiedBy(contact.getModifiedBy());
			grants.setContactNo(contact.getContactNo());
			if (grants.getGrantId() == null
					|| grants.getGrantId().intValue() == 0) {
				grants.setGrantId(null);
				grants.setCreatedBy(contact.getModifiedBy());
				grants.setCreationDate(now);
			}
			this.contactGrantsDao.save(grants);
		}
	}

	public void attachPersonal(Contact contact, ContactPersonalInfo personal) {
		if (personal == null) {
			return;
		}
		Date now = new Date();
		personal.setModifyDate(now);
		personal.setModifiedBy(contact.getModifiedBy());
		personal.setCreatedBy(contact.getModifiedBy());
		personal.setCreationDate(now);
		personal.setModifyDate(now);
		personal.setContactNo(contact.getContactNo());
		this.contactPersInfoDao.save(personal);

	}

	public Contact saveContact(final ContactModelDTO contactModel)
			throws ParseException {
		Contact contact = null;
		contact = dozer.map(contactModel, Contact.class);
		Organization org = contactModel.getOrganization();
		if (org != null) {
			contact.setOrgId(org.getOrgId());
		}
		Divisions div = contactModel.getDivision();
		if (div != null) {
			if (org.getOrgId() != null) {
				div.setOrgId(org.getOrgId());
			}
			div = this.attachDivision(contact, div);
		}
		Departments dept = contactModel.getDepartment();
		if (dept != null) {
			if (org.getOrgId() != null) {
				dept.setOrgId(org.getOrgId());
			}
			if (div != null) {
				dept.setDivisionId(div.getDivisionId());
			}
			this.attachDept(contact, dept);
		}

		this.saveOrUpdateContact(contact);
		this.attachDefaultAddress(contactModel);
		this.attachAddress(contact, contactModel.getAddressList(),
				contactModel.getDelAddrIdList());
		this.attachContact(contact, contactModel.getContactDTOList(),
				contactModel.getDelContactIdList());

		// more info
		this.attachInterest(contact, contactModel.getInterestList(),
				contactModel.getDelIntIdList());
		this.attachPublications(contact, contactModel.getPubsList(),
				contactModel.getDelPubIdList());
		this.attachGrants(contact, contactModel.getGrantsList(),
				contactModel.getDelGrantIdList());
		this.attachPersonal(contact, contactModel.getPersonal());
		return contact;
	}

	/**
	 * 如果是新增Contact, 且Contact Address都为空或没有类型为CONTACT的， 则根据Contact信息自动创建一个
	 * 类型为CONTACT的Contact Address.
	 * 
	 * @param contact
	 */
	private void attachDefaultAddress(final ContactModelDTO contact) {
		boolean bAutoNew = true; // 自动创建Contact Address.
		if (contact.getContactNo() != null) {// 修改则跳过下面.
			return;
		} else {// 新增ContactModel.
			if (contact.getAddressList() == null
					|| contact.getAddressList().isEmpty()) {
				bAutoNew = true;
				contact.setAddressList(new ArrayList<ContactAddressDTO>());
			} else {
				for (ContactAddressDTO dto : contact.getAddressList()) {
					if (dto.getAddrType().equals(AddressType.CONTACT.value())) {// 已经手动加了
						bAutoNew = false;
						break;
					}
				}
			}
		}
		if (bAutoNew) {// 需要创建
			ContactAddressDTO addr = new ContactAddressDTO();
			addr.setAddrId(null);
			addr.setDefaultFlag("Y");
			addr.setStatus("ACTIVE");
			addr.setAddrType(AddressType.CONTACT.value());
			addr.setNamePfx(contact.getNamePfx());
			addr.setFirstName(contact.getFirstName());
			addr.setMidName(contact.getMidName());
			addr.setNameSfx(contact.getNameSfx());
			addr.setLastName(contact.getLastName());
			addr.setTitle(contact.getTitle());
			// addr.setOrganization(null);
			addr.setOrgId(contact.getOrgId());
			if (organizationDao.getById(contact.getOrgId()) != null) {
				String orgName = organizationDao.getById(contact.getOrgId())
						.getName();
				addr.setOrgName(orgName);
			}
			addr.setBusPhone(contact.getBusPhone());
			addr.setBusPhoneExt(contact.getBusPhoneExt());
			addr.setAltPhone(contact.getAltPhone());
			addr.setAltPhoneExt(contact.getAltPhoneExt());
			addr.setBusEmail(contact.getBusEmail());
			addr.setFax(contact.getFax());
			addr.setFaxExt(contact.getFaxExt());
			addr.setAddrLine1(contact.getAddrLine1());
			addr.setAddrLine2(contact.getAddrLine2());
			addr.setAddrLine3(contact.getAddrLine3());
			addr.setCountry(contact.getCountry());
			addr.setState(contact.getState());
			addr.setCity(contact.getCity());
			addr.setZipCode(contact.getZipCode());
			List<ContactAddressDTO> addrList = contact.getAddressList();
			addrList.add(addr);
			contact.setAddressList(addrList);
		}
	}

	@Transactional(readOnly = true)
	public ContactModelDTO getContactDetail(Integer contactNo) {
		this.contactDao.getSessionFactory().evict(Contact.class);
		Contact contact = contactDao.getById(contactNo);
		ContactModelDTO dto = dozer.map(contact, ContactModelDTO.class);
		dto.setInterestList(this.getIntsList(contactNo));

		// attach address
		List<ContactAddressDTO> contactAddrList = new ArrayList<ContactAddressDTO>();
		List<ContactAddress> addrList = this.contactAddressDao
				.getListByContact(contactNo);
		if (addrList != null) {
			for (ContactAddress addr : addrList) {
				ContactAddressDTO addrDTO = dozer.map(addr,
						ContactAddressDTO.class);
				/*
				 * Organization dbOrg = addr.getOrganization(); if (dbOrg !=
				 * null) { Organization org = new Organization();
				 * org.setName(dbOrg.getName()); org.setOrgId(dbOrg.getOrgId());
				 * addrDTO.setOrganization(org); }
				 */

				contactAddrList.add(addrDTO);
			}
		}
		dto.setAddressList(contactAddrList);
		// attach org
		Integer orgId = contact.getOrgId();
		if (orgId != null) {
			dto.setOrganization(organizationDao.getById(orgId));

		}
		Integer divId = contact.getDivisionId();
		if (divId != null) {
			dto.setDivision(this.divisionDao.getById(divId));
		}
		Integer deptId = contact.getDeptId();
		if (deptId != null) {
			dto.setDepartment(this.departmentDao.getById(deptId));
		}
		ContactPersonalInfo personal = this.contactPersInfoDao
				.getInfoByContact(contactNo);
		if (personal != null) {
			dto.setPersonal(personal);
		}
		ContactContactHistory contactHistory = contactContactHistDao
				.getLastContact(contactNo);
		if (contactHistory != null) {
			dto.setLastActivity(contactHistory.getModifyDate());
		}
		Long contactCount = this.contactContactHistDao
				.getContactCount(contactNo);
		dto.setContactCount(contactCount);
		return dto;
	}

	/**
	 * 获得Contact's Contacts的一个具体记录.
	 * 
	 * @param contactId
	 * @return
	 */
	public ContactDTO getContactsDetail(Integer contactId) {
		ContactContactHistory hist = this.contactContactHistDao
				.getById(contactId);
		ContactDTO contactDTO = dozer.map(hist, ContactDTO.class);
		if (hist.getContactBy() != null) {
			User contactUser = this.userDao.getById(hist.getContactBy());
			if (contactUser != null) {
				contactDTO.setContactUserName(contactUser.getLoginName());
			}
		}
		return contactDTO;
	}

	@Transactional(readOnly = true)
	public Page<ContactGrants> getGrantsList(Page<ContactGrants> page,
			Integer contactNo) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_contactNo",
				contactNo);
		filterList.add(orgFilter);
		return this.contactGrantsDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public ContactGrants getGrants(Integer grantId) {
		return this.contactGrantsDao.findUniqueBy("grantId", grantId);
	}

	@Transactional(readOnly = true)
	public Page<ContactPublications> getPubsList(
			Page<ContactPublications> page, Integer contactNo) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_contactNo",
				contactNo);
		filterList.add(orgFilter);
		return this.contactPublicationDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public ContactPublications getPubs(Integer id) {
		return this.contactPublicationDao.findUniqueBy("id", id);
	}

	@Transactional(readOnly = true)
	public List<ContactInterest> getIntsList(Integer contactNo) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_contactNo",
				contactNo);
		filterList.add(orgFilter);
		return this.contactInterestDao.find(filterList);
	}

	@Transactional(readOnly = true)
	public List<ContactDTO> getContactsList(Integer contactNo) {
		List<ContactDTO> contactList = new ArrayList<ContactDTO>();
		List<ContactContactHistory> tactHistList = this.contactContactHistDao
				.getListByContact(contactNo);
		User contactUser = null;
		if (tactHistList != null) {
			for (ContactContactHistory hist : tactHistList) {
				ContactDTO contactDTO = dozer.map(hist, ContactDTO.class);
				if (hist.getContactBy() != null) {
					contactUser = this.userDao.getById(hist.getContactBy());
					if (contactUser != null) {
						contactDTO.setContactUserName(contactUser
								.getLoginName());
					}
					if (hist.getSource() != null) {
						Source source = sourceDao.get(hist.getSource());
						contactDTO.setSourceName(source.getName());
					}
				}
				contactList.add(contactDTO);
			}
		}
		return contactList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<ContactDTO> getContactContactList(Integer contactNo,
			Page<ContactContactHistory> page) {
		List<ContactDTO> dtoList = new ArrayList<ContactDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_contactNo",
				contactNo);
		filterList.add(orgFilter);
		Page<ContactContactHistory> pageList = this.contactContactHistDao
				.findPage(page, filterList);
		List<ContactContactHistory> resultList = pageList.getResult();
		if (resultList != null) {
			User contactUser = null;
			for (ContactContactHistory hist : resultList) {
				ContactDTO contactDTO = dozer.map(hist, ContactDTO.class);
				if (hist.getContactBy() != null) {
					contactUser = this.userDao.getById(hist.getContactBy());
					if (contactUser != null) {
						contactDTO.setContactUserName(contactUser
								.getLoginName());
					}
					if (hist.getSource() != null) {
						Source source = sourceDao.get(hist.getSource());
						contactDTO.setSourceName(source.getName());
					}
				}
				dtoList.add(contactDTO);
			}
		}
		pageList.setResult(null);
		Page<ContactDTO> dtoPage = this.dozer.map(pageList, Page.class);
		dtoPage.setResult(dtoList);
		return dtoPage;
	}

	@Transactional(readOnly = true)
	public CustContactInfoDTO getContactsInfo(Integer contactNo) {
		CustContactInfoDTO dto = new CustContactInfoDTO();
		int emailCount = 0;
		emailCount += this.contactContactHistDao.getMethodCount(
				ContactMethod.Email.value(), contactNo);
		dto.setEmailCount(emailCount);
		int faxCount = 0;
		faxCount += this.contactContactHistDao.getMethodCount(
				ContactMethod.Fax.value(), contactNo);
		dto.setFaxCount(faxCount);
		int letterCount = 0;
		letterCount += this.contactContactHistDao.getMethodCount(
				ContactMethod.Mail.value(), contactNo);
		dto.setLetterCount(letterCount);
		int phoneCount = 0;
		phoneCount += this.contactContactHistDao.getMethodCount(
				ContactMethod.Phone.value(), contactNo);
		dto.setPhoneCount(phoneCount);
		return dto;
	}

	@Transactional(readOnly = true)
	public ContactActivity getContactActivity(Integer contactNo) {
		ContactActivity act = new ContactActivity();
		Contact contact = this.contactDao.get(contactNo);
		if (contact != null) {
			ContactContactHistory lastPhone = contactContactHistDao
					.getLastContactByMethod(contactNo,
							ContactMethod.Phone.value());
			if (lastPhone != null) {
				act.setLastPhoneDate(lastPhone.getContactDate());
				act.setLastPhoneContactUser(lastPhone.getContactName());
				User contactUser = this.userDao.get(lastPhone.getContactBy());
				if (contactUser != null) {
					act.setLastPhoneContactBy(contactUser.getLoginName());
				}
			}
			ContactContactHistory lastEmail = contactContactHistDao
					.getLastContactByMethod(contactNo,
							ContactMethod.Email.value());
			if (lastEmail != null) {
				act.setLastEmailSubject(lastEmail.getSubject());
				act.setLastEmailSent(lastEmail.getContactDate());
			}
			ContactContactHistory lastFax = contactContactHistDao
					.getLastContactByMethod(contactNo,
							ContactMethod.Fax.value());
			if (lastFax != null) {
				act.setLastFaxSent(lastFax.getContactDate());
				act.setLastFaxSubject(lastFax.getSubject());
			}
			ContactContactHistory lastLetter = contactContactHistDao
					.getLastContactByMethod(contactNo,
							ContactMethod.Mail.value());
			if (lastLetter != null) {
				act.setLastMailSent(lastLetter.getContactDate());
				act.setLastMailSubject(lastLetter.getSubject());
			}
			ContactContactHistory contactHistory = contactContactHistDao
					.getLastContactByContentType(contactNo, "Catalog");
			if (contactHistory != null) {
				act.setLastCatalogSent(contactHistory.getContactDate());
				act.setLastCatalogName(contactHistory.getReferName());
			}
			act.setLastModifyDate(contact.getModifyDate());
			act.setLastModifyUser(this.userDao.get(contact.getModifiedBy())
					.getLoginName());
		}
		return act;
	}

	public void sendCntctMailJob() {
		List<ContactContactHistory> cntctContactHistoryList = null;
		cntctContactHistoryList = contactContactHistDao.queryMailSend();
		if (cntctContactHistoryList != null
				&& cntctContactHistoryList.size() > 0) {
			for (ContactContactHistory his : cntctContactHistoryList) {
				String mailTo = his.getEmail();
				String content = his.getContent();
				String subject = his.getSubject();
				if (StringUtils.isNotBlank(mailTo)) {
					mimeMailService.sendMail(mailTo, subject, content,
							"scm_admin@genscriptcorp.com");
					his.setStatus("COMPLETE");
					contactContactHistDao.save(his);
				}
			}
		}
	}

	/**
	 * 给新建的contact随机生成了密码
	 */
	public String generalPass() {
		StringBuffer newPassword = new StringBuffer();
		String rand = "";
		for (int i = 1; i <= 6; i++) {
			if (i % 2 != 0) {
				rand = StringUtil.getCharRandom(1);
			} else {
				rand = StringUtil.getIntRandom(1);
			}
			newPassword.append(rand);
		}

		return newPassword.toString();
	}

	/**
	 * 用户email重复验证
	 */
	public boolean isReapt(String contactNo, String busEmail) {
		Integer contactNoInt = this.contactDao.getContactNoByEmail(busEmail);
		if (StringUtils.isNotEmpty(contactNo)
				&& !StringUtil.isNumeric(contactNo) && contactNoInt != null) {
			return true;
		}
		if (StringUtils.isNotEmpty(contactNo)
				&& StringUtil.isNumeric(contactNo) && contactNoInt != null
				&& !String.valueOf(contactNoInt).equals(contactNo)) {
			return true;
		}
		return false;
	}

	public void delContactByduplicateEmail2customer() {
		contactDao.delContactByduplicateEmail2customer();
	}

	@Autowired
	private SalesRepDao salesRepDao;

	public List<SalesRep> getSalesManages(String funName) {
		return salesRepDao.getSalesRepsByFunction(funName);
	}

}
