package com.genscript.gsscm.contact.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.SearchAttributeDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.CommonSearchService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.CallTime;
import com.genscript.gsscm.common.constant.NamePfx;
import com.genscript.gsscm.common.constant.NameSfx;
import com.genscript.gsscm.common.constant.SearchType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.contact.dto.ContactActivity;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.Contact;
import com.genscript.gsscm.contact.entity.ContactBean;
import com.genscript.gsscm.contact.entity.ContactGrants;
import com.genscript.gsscm.contact.entity.ContactInterest;
import com.genscript.gsscm.contact.entity.ContactPublications;
import com.genscript.gsscm.contact.service.ContactService;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results({
		@Result(name = "search", location = "contact/contact_search_form.jsp"),
		@Result(name = "search_result", location = "contact/contact_search_result.jsp"),
		@Result(name = "edit", location = "contact/contact_edit.jsp"),
		@Result(name = "contact_activity", location = "contact/contact_activities.jsp")

})
public class ContactAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8029698034152652101L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private ContactService contactService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CommonSearchService commonSearchService;
	/**
	 * 列表页面中的数据
	 */
	private String modifyByUsername;
	private Page<ContactBean> contactPage;
	private List<SearchAttributeDTO> attrList;
	private List<CountryDTO> countryList;
	private Integer contactNo;
	private String sessContactNo;
	private ContactModelDTO contact;
	/**
	 * 详细页面中的名称前缀
	 */
	private NamePfx[] namePfxList;
	/**
	 * 详细页面中的名称后缀
	 */
	private NameSfx[] nameSfxList;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private List<SalesRep> salesManagerList;
	private List<SalesRep> techManagerList;

	// 获取从非Contact列表页面点过来的请求--Zhang Yong
	private String operation_method;

	public String getModifyByUsername() {
		return modifyByUsername;
	}

	public void setModifyByUsername(String modifyByUsername) {
		this.modifyByUsername = modifyByUsername;
	}

	/**
	 * 查询页面上的基本数据, 进入查询页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		attrList = commonSearchService
				.getSearchAttributeList(SearchType.CONTACT);
		countryList = publicService.getCountryList();
		// -----
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
		// contactService.delContactByduplicateEmail2customer();
		return "search";
	}

	/**
	 * 查找数据, 返回查询结果页面.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ContactBean> pagerUtil = new PagerUtil<ContactBean>();
		contactPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!contactPage.isOrderBySetted()) {
			contactPage.setOrderBy("contactNo");
			contactPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		if (contactPage.getPageSize() == null
				|| contactPage.getPageSize().intValue() < 1) {
			contactPage.setPageSize(20);
		}
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		// 有查询条件的结果集(含分页信息)
		contactPage = contactService.searchContact(contactPage, filters);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(contactPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "search_result";
	}

	/**
	 * 执行删除操作， 返回list页面.
	 * 
	 * @return
	 */
	public String delete() {
		String contactNoList = ServletActionContext.getRequest().getParameter(
				"contactNoList");
		for (String cc : contactNoList.split(",")) {
			this.contactService.delContact(SessionUtil.getUserId(),
					Integer.parseInt(cc));
		}
		Struts2Util.renderText("success");
		return null;
	}

	/**
	 * 返回contact的新增或修改页面; 如果参数contactNo为null则意为新增，
	 * 不为null则是修改该contactNo对应的contact 新增与修改返回的是同一个页面，
	 * 但修改则要查出该contact对应的各个Tab中的数据 对于页面中的下拉框的数据源则无论是新增还是修改都需要提供
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		if (contactNo != null) {// 修改
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 判断将要修改的单号是否正在被操作
			String editUrl = "contact/contact!edit.action?contactNo="
					+ contactNo;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			// *********** Add By Zhang Yong End *****************************//

			this.contact = this.contactService.getContactDetail(contactNo);
			ServletActionContext.getRequest().setAttribute("callTimeFromAPM",
					DateUtils.getAPM(contact.getBstCallTimeFrom()));
			ServletActionContext.getRequest().setAttribute("callTimeFrom",
					DateUtils.getAPMTime(contact.getBstCallTimeFrom(), 5));
			ServletActionContext.getRequest().setAttribute("callTimeToAPM",
					DateUtils.getAPM(contact.getBstCallTimeTo()));
			ServletActionContext.getRequest().setAttribute("callTimeTo",
					DateUtils.getAPMTime(contact.getBstCallTimeTo(), 5));

			this.sessContactNo = Integer.toString(contactNo);
			// 清除session
			SessionUtil.deleteRow(SessionConstant.ContactContact.value(),
					sessContactNo);
			SessionUtil.deleteRow(SessionConstant.ContactAddress.value(),
					sessContactNo);
			SessionUtil.deleteRow(SessionConstant.ContactGrants.value(),
					sessContactNo);
			SessionUtil.deleteRow(SessionConstant.ContactPubs.value(),
					sessContactNo);
			// 建新的session
			if (contact.getAddressList() != null
					&& !contact.getAddressList().isEmpty()) {
				Map<String, ContactAddressDTO> addressMap = new LinkedHashMap<String, ContactAddressDTO>();
				for (ContactAddressDTO dto : contact.getAddressList()) {
					addressMap.put(dto.getAddrId() + "", dto);
				}
				SessionUtil.insertRow(SessionConstant.ContactAddress.value(),
						Integer.toString(contactNo), addressMap);
			}
			SessionUtil.insertRow(SessionConstant.Contact.value(),
					sessContactNo, contact);
		} else {// 新增
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
			// *********** Add By Zhang Yong End *****************************//
			this.sessContactNo = SessionUtil.generateTempId();
			SessionUtil.insertRow(SessionConstant.Contact.value(),
					this.sessContactNo, new ContactModelDTO());
		}
		initManagerList();
		if (contact != null) {
			if (contact.getModifiedBy() != null) {
				modifyByUsername = userDao
						.getLoginName(contact.getModifiedBy());
			}
		}

		this.setNamePfxList(NamePfx.values());
		this.setNameSfxList(NameSfx.values());
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.DEPARTMENT_FUNCTION);
		speclListName.add(SpecDropDownListName.CUSTOMER_ROLE);
		speclListName.add(SpecDropDownListName.TIME_ZONE);
		speclListName.add(SpecDropDownListName.ORIGINAL_SOURCE);
		// Org Tab.
		speclListName.add(SpecDropDownListName.ORGANIZATION_CATEGORY);
		speclListName.add(SpecDropDownListName.ORGANIZATION_TYPE);
		speclListName.add(SpecDropDownListName.LANGUAGE_CODE);
		// Interest Tab
		speclListName.add(SpecDropDownListName.DECIPLINE_INTEREST);
		speclListName.add(SpecDropDownListName.APPLICATION_INTEREST);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);
		// 时间下拉框
		CallTime[] callTimeList = CallTime.values();
		ServletActionContext.getRequest().setAttribute("callTimeList",
				callTimeList);
		return "edit";
	}

	private void initManagerList() { 
	/*	String sessNo = (contactNo != null && contactNo != 0 ? String
				.valueOf(contactNo) : sessContactNo);
		Map<String, List<SalesRep>> salesMap = null;
		salesMap = publicService.searchManager_Cust_Contact("contact", sessNo);
		if (salesMap != null && salesMap.get("salesManager") != null) {
			List<SalesRep> list = (List<SalesRep>) salesMap.get("salesManager");
			salesManagerList = list;
		}
		if (salesMap != null && salesMap.get("techAcountManager") != null) {
			List<SalesRep> list = (List<SalesRep>) salesMap
					.get("techAcountManager");
			techManagerList = list;
		}*/
		
		salesManagerList = contactService.getSalesManages("SALES_CONTACT");
		techManagerList = contactService.getSalesManages("TECH_SUPPORT");
	}

	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 根据页面提交的数据保存Contact模块中各个Tab的内容, 返回提示信息 对于提交的时间要做格式转化处理
	 * 
	 * @return
	 */
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessContactNo != null && !("").equals(sessContactNo)) {
			String editUrl = "contact/contact!edit.action?contactNo="
					+ sessContactNo;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				// 清除session
				SessionUtil.deleteRow(SessionConstant.ContactContact.value(),
						sessContactNo);
				SessionUtil.deleteRow(SessionConstant.ContactAddress.value(),
						sessContactNo);
				SessionUtil.deleteRow(SessionConstant.ContactGrants.value(),
						sessContactNo);
				SessionUtil.deleteRow(SessionConstant.ContactPubs.value(),
						sessContactNo);
				rt.put("message",
						"Save contact fail,the contact is editing by "
								+ operation_method);
				rt.put("contactNo", sessContactNo);
				Struts2Util.renderJson(rt);
				return null;
			}
		}
		// *********** Add By Zhang Yong End *****************************//
		Contact model = null;
		try {
			contact.setModifiedBy(SessionUtil.getUserId());
			if (contact.getState() == null) {
				contact.setState(ServletActionContext.getRequest()
						.getParameter("state"));
			}
			if (contact.getGsCoId() == null) {
				contact.setGsCoId(2);
			}
			// 对Interest Tab相关请求数据的获得.
			contact.setInterestList(this.getInterestListByRequest());
			contact.setDelIntIdList(this.getDelInterestListByRequest());
			// 根据前台传来的时间值及AM或PM, 转化成数据库需要的时间格式.
			String callTimeFrom = ServletActionContext.getRequest()
					.getParameter("callTimeFrom");
			String callTimeFromPm = ServletActionContext.getRequest()
					.getParameter("callTimeFromPm");
			contact.setBstCallTimeFrom(DateUtils.apmStr2Time(callTimeFrom,
					callTimeFromPm));
			String callTimeTo = ServletActionContext.getRequest().getParameter(
					"callTimeTo");
			String callTimeToPm = ServletActionContext.getRequest()
					.getParameter("callTimeToPm");
			contact.setBstCallTimeTo(DateUtils.apmStr2Time(callTimeTo,
					callTimeToPm));
			contact.setCreationDate(new Date());
			// 获取 personal birthDate 数据。
			String birth = ServletActionContext.getRequest().getParameter(
					"birth");
			if (StringUtils.isNotEmpty(birth)) {
				contact.getPersonal().setBirthDate(
						DateUtils.formatStr2Date(birth, "yyyy-MM-dd"));
			}
			// 关联Contact Contact
			attachContactContact();
			attachContactAddress();
			this.attachGrants();
			this.attachPubs();
			initManagerList();
			if (contact.getSalesContact() == null
					&& contact.getContactNo() == null
					|| contact.getContactNo() == 0) {
				contact.setSalesContact(salesManagerList != null
						&& salesManagerList.size() > 0 ? salesManagerList
						.get(0).getSalesId() : null);
				contact.setTechSupport(techManagerList != null
						&& techManagerList.size() > 0 ? techManagerList.get(0)
						.getSalesId() : null);
			}
			if (contactService.isReapt(sessContactNo, contact.getBusEmail())) {
				rt.put("message",
						"The contact busEmail has been used by other contact.");
				Struts2Util.renderJson(rt);
				return null;
			}

			model = this.contactService.saveContact(contact);

			// 清除session
			SessionUtil.deleteRow(SessionConstant.ContactContact.value(),
					sessContactNo);
			SessionUtil.deleteRow(SessionConstant.ContactAddress.value(),
					sessContactNo);
			SessionUtil.deleteRow(SessionConstant.ContactGrants.value(),
					sessContactNo);
			SessionUtil.deleteRow(SessionConstant.ContactPubs.value(),
					sessContactNo);
			rt.put("contactNo", model.getContactNo() + "");
			rt.put("message", "The contact account #" + model.getContactNo()
					+ " is saved.");
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			String s1 = exDTO.getMessageDetail();
			int tmpid = s1.indexOf("sales_contact");
			if (tmpid == -1) {

			} else {
				s1 = s1.replaceAll("sales_contact", "sales_manager");
				exDTO.setMessageDetail(s1);
			}
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 获得ContactActivities相关信息并返回指定页面.
	 * 
	 * @return
	 */
	public String getContactActivity() {
		ContactActivity contactActivity = this.contactService
				.getContactActivity(contactNo);
		ServletActionContext.getRequest().setAttribute("contactActivity",
				contactActivity);
		return "contact_activity";
	}

	/**
	 * 通过从request中获得删除的Interest的list值, 分两种类别进行处理
	 * 
	 * @return
	 */
	private List<Integer> getDelInterestListByRequest() {
		List<Integer> delIdList = new ArrayList<Integer>();
		String delATId = ServletActionContext.getRequest().getParameter(
				"del_at_interestId");
		if (delATId != null) {
			String[] delATIdList = delATId.split(",");
			for (int i = 0; i < delATIdList.length; i++) {
				if (delATIdList[i].length() > 0) {
					delIdList.add(Integer.valueOf(delATIdList[i]));
				}
			}
		}
		String delDRId = ServletActionContext.getRequest().getParameter(
				"del_dr_interestId");
		if (delDRId != null) {
			String[] delDRIdList = delDRId.split(",");
			for (int i = 0; i < delDRIdList.length; i++) {
				if (delDRIdList[i].length() > 0) {
					delIdList.add(Integer.valueOf(delDRIdList[i]));
				}
			}
		}
		return delIdList;
	}

	/**
	 * 通过从request中获得新增的Interest的list值, 分两种类别进行处理
	 * 
	 * @return
	 */
	private List<ContactInterest> getInterestListByRequest() {
		List<ContactInterest> interestList = new ArrayList<ContactInterest>();
		String[] appInterest = ServletActionContext.getRequest()
				.getParameterValues("appInterest");
		if (appInterest != null) {
			for (int i = 0; i < appInterest.length; i++) {
				System.out.println("appInterest" + i + ":" + appInterest[i]);
				ContactInterest interest = new ContactInterest();
				interest.setType("AT");
				interest.setAreaId(Integer.valueOf(appInterest[i].split(":")[0]));
				interest.setName(appInterest[i].split(":")[1]);
				interestList.add(interest);
			}
		}
		String[] decInterest = ServletActionContext.getRequest()
				.getParameterValues("decInterest");
		if (decInterest != null) {
			for (int i = 0; i < decInterest.length; i++) {
				ContactInterest interest = new ContactInterest();
				interest.setType("DR");
				interest.setAreaId(Integer.valueOf(decInterest[i].split(":")[0]));
				interest.setName(decInterest[i].split(":")[1]);
				interestList.add(interest);
			}
		}
		return interestList;
	}

	/**
	 * 总体保存中关联保存contact contacts列表
	 * 
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void attachContactContact() {
		List<ContactDTO> contactsList = new ArrayList<ContactDTO>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, ContactDTO> sessContactsMap = (Map<String, ContactDTO>) SessionUtil
				.getRow(SessionConstant.ContactContact.value(), sessContactNo);
		if (sessContactsMap != null) {
			Iterator<Entry<String, ContactDTO>> it = sessContactsMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, ContactDTO> entry = it.next();
				ContactDTO tmpDTO = (ContactDTO) entry.getValue();
				if (tmpDTO == null && StringUtils.isNumeric(entry.getKey())) {
					delIdList.add(Integer.parseInt(entry.getKey()));
				} else {
					if (tmpDTO.getContactId() != null
							&& tmpDTO.getContactId().intValue() == 0) {
						tmpDTO.setContactId(null);
					}
					contactsList.add(tmpDTO);
				}
			}
		}
		if (!delIdList.isEmpty()) {
			contact.setDelContactIdList(delIdList);
		}
		if (!contactsList.isEmpty()) {
			contact.setContactDTOList(contactsList);
		}
	}

	/**
	 * 在整体保存Contact时，关联保存Address相关.
	 */
	@SuppressWarnings("unchecked")
	private void attachContactAddress() {
		List<ContactAddressDTO> addrList = new ArrayList<ContactAddressDTO>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, ContactAddressDTO> sessAddrMap = null;
		if (contactNo == null || contactNo == 0) {
			sessAddrMap = (Map<String, ContactAddressDTO>) SessionUtil.getRow(
					SessionConstant.ContactAddress.value(), sessContactNo);
		} else {
			sessAddrMap = (Map<String, ContactAddressDTO>) SessionUtil.getRow(
					SessionConstant.ContactAddress.value(),
					String.valueOf(contactNo));
		}

		if (sessAddrMap != null) {
			boolean contactFlg = false;
			boolean shipToFlg = false;
			Iterator<Entry<String, ContactAddressDTO>> it = sessAddrMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, ContactAddressDTO> entry = it.next();
				ContactAddressDTO tmpDTO = (ContactAddressDTO) entry.getValue();
				if (tmpDTO != null && "CONTACT".equals(tmpDTO.getAddrType())
						&& "Y".equals(tmpDTO.getDefaultFlag())) {
					contactFlg = true;
				}
				if (tmpDTO != null && "SHIP_TO".equals(tmpDTO.getAddrType())
						&& "Y".equals(tmpDTO.getDefaultFlag())) {
					shipToFlg = true;
				}
				if (tmpDTO == null && StringUtils.isNumeric(entry.getKey())) {
					delIdList.add(Integer.parseInt(entry.getKey()));
				} else {
					if (tmpDTO.getAddrId() != null
							&& tmpDTO.getAddrId().intValue() == 0) {
						tmpDTO.setAddrId(null);
					}
					addrList.add(tmpDTO);
				}
			}
			if (addrList.size() == 0) {
				ContactAddressDTO tempAddr1 = newAddress("CONTACT");
				addrList.add(tempAddr1);
				ContactAddressDTO tempAddr2 = newAddress("SHIP_TO");
				addrList.add(tempAddr2);

			} else {
				if (!contactFlg) {
					ContactAddressDTO tempAddr1 = newAddress("CONTACT");
					addrList.add(tempAddr1);

				}
				if (!shipToFlg) {
					ContactAddressDTO tempAddr2 = newAddress("SHIP_TO");
					addrList.add(tempAddr2);
				}
			}
		} else {
			ContactAddressDTO tempAddr1 = newAddress("CONTACT");
			addrList.add(tempAddr1);
			ContactAddressDTO tempAddr2 = newAddress("SHIP_TO");
			addrList.add(tempAddr2);
		}
		if (!delIdList.isEmpty()) {
			contact.setDelAddrIdList(delIdList);
		}
		if (!addrList.isEmpty()) {
			contact.setAddressList(addrList);
		}
		SessionUtil.updateRow(SessionConstant.ContactAddress.value(),
				sessContactNo, SessionUtil.convertList2Map(addrList, "addrId"));
	}

	private ContactAddressDTO newAddress(String type) {
		ContactAddressDTO tempAddr = new ContactAddressDTO();
		tempAddr.setAddrClass("Commercial");
		tempAddr.setAddrType(type);
		tempAddr.setDefaultFlag("Y");
		tempAddr.setNamePfx(contact.getNamePfx());
		tempAddr.setFirstName(contact.getFirstName());
		tempAddr.setMidName(contact.getMidName());
		tempAddr.setLastName(contact.getLastName());
		tempAddr.setNameSfx(contact.getNameSfx());
		// tempAddr.setOrganization(contact.getOrganization());
		tempAddr.setOrgId(contact.getOrgId());
		// Organization ao = organizationDao.getById(contact.getOrgId());
		tempAddr.setOrgName("");
		tempAddr.setBusPhone(contact.getBusPhone());
		tempAddr.setBusPhoneExt(contact.getBusPhoneExt());
		tempAddr.setAltPhone(contact.getAltPhone());
		tempAddr.setAltPhoneExt(contact.getAltPhoneExt());
		tempAddr.setFax(contact.getFax());
		tempAddr.setFaxExt(contact.getFaxExt());
		tempAddr.setBusEmail(contact.getBusEmail());
		tempAddr.setAddrLine1(contact.getAddrLine1());
		tempAddr.setAddrLine2(contact.getAddrLine2());
		tempAddr.setAddrLine3(contact.getAddrLine3());
		tempAddr.setCity(contact.getCity());
		tempAddr.setState(contact.getState());
		tempAddr.setCountry(contact.getCountry());
		tempAddr.setMobile(contact.getMobile());
		tempAddr.setPersonalEmail(contact.getPersonalEmail());
		tempAddr.setAltBusEmail(contact.getAltBusEmail());
		tempAddr.setTitle(contact.getTitle());
		tempAddr.setZipCode(contact.getZipCode());
		tempAddr.setEffFrom(new Date());
		tempAddr.setEffTo(new Date());
		tempAddr.setAddrClass("Commercial");
		tempAddr.setStatus("ACTIVE");
		return tempAddr;

	}

	/**
	 * 在整体保存Contact时，关联保存Grants相关.
	 */
	@SuppressWarnings("unchecked")
	private void attachGrants() {
		List<ContactGrants> grantList = new ArrayList<ContactGrants>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, ContactGrants> sessAddrMap = (Map<String, ContactGrants>) SessionUtil
				.getRow(SessionConstant.ContactGrants.value(), sessContactNo);
		if (sessAddrMap != null) {
			Iterator<Entry<String, ContactGrants>> it = sessAddrMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, ContactGrants> entry = it.next();
				ContactGrants tmpDTO = (ContactGrants) entry.getValue();
				if (tmpDTO == null && StringUtils.isNumeric(entry.getKey())) {
					delIdList.add(Integer.parseInt(entry.getKey()));
				} else {
					if (tmpDTO.getGrantId() != null
							&& tmpDTO.getGrantId().intValue() == 0) {
						tmpDTO.setGrantId(null);
					}
					grantList.add(tmpDTO);
				}
			}
		}
		if (!delIdList.isEmpty()) {
			contact.setDelGrantIdList(delIdList);
		}
		if (!grantList.isEmpty()) {
			contact.setGrantsList(grantList);
		}
	}

	/**
	 * 在整体保存Contact时，关联保存Pubs相关.
	 */
	@SuppressWarnings("unchecked")
	private void attachPubs() {
		List<ContactPublications> pubList = new ArrayList<ContactPublications>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, ContactPublications> sessAddrMap = (Map<String, ContactPublications>) SessionUtil
				.getRow(SessionConstant.ContactPubs.value(), sessContactNo);
		if (sessAddrMap != null) {
			Iterator<Entry<String, ContactPublications>> it = sessAddrMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, ContactPublications> entry = it.next();
				ContactPublications tmpDTO = (ContactPublications) entry
						.getValue();
				if (tmpDTO == null && StringUtils.isNumeric(entry.getKey())) {
					delIdList.add(Integer.parseInt(entry.getKey()));
				} else {
					if (tmpDTO.getId() != null
							&& tmpDTO.getId().intValue() == 0) {
						tmpDTO.setId(null);
					}
					pubList.add(tmpDTO);
				}
			}
		}
		if (!delIdList.isEmpty()) {
			contact.setDelPubIdList(delIdList);
		}
		if (!pubList.isEmpty()) {
			contact.setPubsList(pubList);
		}
	}

	public Page<ContactBean> getContactPage() {
		return contactPage;
	}

	public void setContactPage(Page<ContactBean> contactPage) {
		this.contactPage = contactPage;
	}

	public List<SearchAttributeDTO> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<SearchAttributeDTO> attrList) {
		this.attrList = attrList;
	}

	public List<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryDTO> countryList) {
		this.countryList = countryList;
	}

	public Integer getContactNo() {
		return contactNo;
	}

	public void setContactNo(Integer contactNo) {
		this.contactNo = contactNo;
	}

	public NamePfx[] getNamePfxList() {
		return namePfxList;
	}

	public void setNamePfxList(NamePfx[] namePfxList) {
		this.namePfxList = namePfxList;
	}

	public NameSfx[] getNameSfxList() {
		return nameSfxList;
	}

	public void setNameSfxList(NameSfx[] nameSfxList) {
		this.nameSfxList = nameSfxList;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public String getSessContactNo() {
		return sessContactNo;
	}

	public void setSessContactNo(String sessContactNo) {
		this.sessContactNo = sessContactNo;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public ContactModelDTO getContact() {
		return contact;
	}

	public void setContact(ContactModelDTO contact) {
		this.contact = contact;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}

	public List<SalesRep> getSalesManagerList() {
		return salesManagerList;
	}

	public void setSalesManagerList(List<SalesRep> salesManagerList) {
		this.salesManagerList = salesManagerList;
	}

	public List<SalesRep> getTechManagerList() {
		return techManagerList;
	}

	public void setTechManagerList(List<SalesRep> techManagerList) {
		this.techManagerList = techManagerList;
	}

}
