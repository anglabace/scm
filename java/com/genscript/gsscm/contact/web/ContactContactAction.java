package com.genscript.gsscm.contact.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.AmPm;
import com.genscript.gsscm.common.constant.CallTime;
import com.genscript.gsscm.common.constant.ContactMethod;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionEmergeUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.ContentTemplate;
import com.genscript.gsscm.contact.service.ContactService;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.service.AddressService;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.productservice.dto.CatalogDTO;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "contact_list", location = "contact/contact_contact_list.jsp"),
		@Result(name = "contact_add", location = "contact/contact_contact_add.jsp"),
		@Result(name = "contact_edit", location = "contact/contact_contact_edit.jsp") })
public class ContactContactAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534283335416418911L;
	private Integer contactAddrsCount = 0;
	private Integer shipAddrsCount = 0;
	private Integer billAddrsCount = 0;
	private Integer soldAddrsCount = 0;
	private String saveType;
	private Integer phoneCount = 0;
	private Integer emailCount = 0;
	private Integer faxCount = 0;
	private Integer mailCount = 0;  
	private Integer meetingCount = 0;
	private String callTimeFromAPM;
	private String callTimeFrom;
	private String callTimeToAPM;
	private String callTimeTo;
	private String fax;
	private String faxExt;
	private String bstCallTmzn;
	private String callTmzn;
	private String addrType;
	private String email;
	private String firstName;
	private String lastName;
	private CallTime[] callTimeList;
	private AmPm[] amPmList;
	private String sessContactNo;
	private String sessContactId;
	private List<ContactAddressDTO> contactAddrs;
	private List<ContactAddressDTO> billAddrs;
	private List<ContactAddressDTO> shipAddrs;
	private List<ContactAddressDTO> soldAddrs;
	private Integer orgId = 0;
	private Integer contactId;
	private Integer statusFlag = 0;
	private String contactIdStr;
	private String status;
	private int contactNo;
	private ContactDTO allData;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private List<ContentTemplate> contentTmplList;
	private List<CatalogDTO> catalogDTOList;
	private Page<ContactDTO> pageContactDTO;
	private Date contactDateFrom;
	private Date contactDateTo;
	private Date contactDateDefault = new Date();
	private String contactMethod;
	private String contactBy;
	private List<ContactDTO> retContactList;
	private List<String> contactUserNameList;
	private List<DropDownListDTO> specDropDownist;
	private Map<String, ContactDTO> contactsMap;// 用于返回列表页面时显示数据
	private String contactsKey;// 在edit时用来
	@Autowired
	private AddressService addressService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ContactService contactService;

	/**
	 * 删除contact contact方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String delete() throws Exception {
		// 取得要删除的contact id字符串
		String delIdStr = ServletActionContext.getRequest().getParameter("contact_id_str");
		System.out.println("delIdStr: " + delIdStr);
		if (StringUtils.isNotEmpty(delIdStr)) {
			String[] delKeyArray = delIdStr.split(",");
			contactsMap = (Map<String, ContactDTO>) SessionUtil.getRow(
					SessionConstant.ContactContact.value(), sessContactNo);
			if (contactsMap == null) {
				contactsMap = new HashMap<String, ContactDTO>();
				SessionUtil.insertRow(SessionConstant.ContactContact.value(),
						sessContactNo, contactsMap);
			}
			for (int i = 0; i < delKeyArray.length; i++) {
				if (StringUtils.isNumeric(delKeyArray[i])) {
					contactsMap.put(delKeyArray[i], null);
				} else {
					contactsMap.remove(delKeyArray[i]);
				}
			}
			SessionUtil.updateRow(SessionConstant.ContactContact.value(),
					sessContactNo, contactsMap);
			Struts2Util.renderText("success");
		}
		return NONE;
	}

	/**
	 * 编辑已有contact contact方法
	 * 
	 * @return "contactCntctEditForm"
	 * 
	 * @throws Exception
	 */
	public String edit() throws Exception {
		System.out.println("Contact contactsKey: " + contactsKey);
		Object obj = SessionUtil.getOneRow(SessionConstant.ContactContact
				.value(), this.sessContactNo, this.getContactsKey());
		if (obj != null) {
			allData = (ContactDTO) obj;
		} else {
			allData = this.contactService.getContactsDetail(Integer
					.parseInt(this.contactsKey));
		}
		if (allData.getStatus() != null
				&& allData.getStatus().indexOf("UN") == -1) {
			statusFlag = 1;
		}
		showContactAddEdit();
		System.out.println("Contact allData: " + allData);
		return "contact_edit";
	}

	/**
	 * 创建contact contact表单方法
	 * 
	 * @return "contactCntctCreForm"
	 * 
	 * @throws Exception
	 */
	public String add() throws Exception {
		showContactAddEdit();
		return "contact_add";
	}

	/**
	 * 新建和保存contact contact所调用公共方法
	 */
	private void showContactAddEdit() {
		catalogDTOList = new ArrayList<CatalogDTO>();
		contactAddrs = new ArrayList<ContactAddressDTO>();
		billAddrs = new ArrayList<ContactAddressDTO>();
		shipAddrs = new ArrayList<ContactAddressDTO>();
		soldAddrs = new ArrayList<ContactAddressDTO>();
		ContactModelDTO sessContact = null;
		if (StringUtils.isNumeric(this.sessContactNo)) {
			contactNo = Integer.parseInt(this.sessContactNo);
			sessContact = this.contactService.getContactDetail(contactNo);
		}
		System.out.println("sessContact: " + sessContact);
		if(allData!=null) {
			callTimeFromAPM = DateUtils.getAPM(allData.getCallTime());
			callTimeFrom = DateUtils.getAPMTime(allData.getCallTime(), 5);
			callTmzn = allData.getCallTmzn();
		}
		if (sessContact != null) {
			
			callTimeToAPM = DateUtils.getAPM(sessContact.getBstCallTimeTo());
			System.out.println("callTimeToAPM: " + callTimeToAPM);
			callTimeTo = DateUtils
					.getAPMTime(sessContact.getBstCallTimeTo(), 5);
			System.out.println("callTimeTo: " + callTimeTo);
			bstCallTmzn = sessContact.getBstCallTmzn();
			fax = sessContact.getFax();
			faxExt = sessContact.getFaxExt();
		}
		// 对call time列表进行设值
		setCallTimeList(CallTime.values());
		// 对AM，PM列表设值
		setAmPmList(AmPm.values());
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.ORIGINAL_SOURCE);
		speclListName.add(SpecDropDownListName.TIME_ZONE);
		// 获得Source和time zone下拉框数值
		specDropDownist = publicService.getSpecDropDownList(speclListName);
		CustAddrSrchDTO addrSrchDTO = new CustAddrSrchDTO();
		addrSrchDTO.setCustNo(contactNo);
		addrSrchDTO.setAddrType(addrType);
		addrSrchDTO.setEmail(email);
		addrSrchDTO.setFirstName(firstName);
		addrSrchDTO.setLastName(lastName);
		addrSrchDTO.setOrgId(orgId);
		List<ContactAddressDTO> addrdtoList = null;
		// 如果是修改contact, 则从数据库查找.
		// TODO: 新增时是否需要从Session中取出， 以及查找时addrType是否需要指定.
		if (contactNo != 0) {
			addrdtoList = addressService.getContactAddrList(addrSrchDTO);
		}

		if (addrdtoList != null && addrdtoList.size() > 0) {
			for (ContactAddressDTO contactAddDTO : addrdtoList) {
				String fullAddr = AddressUtil.getFullAddress(contactAddDTO
						.getAddrLine1(), contactAddDTO.getAddrLine2(),
						contactAddDTO.getAddrLine3(), contactAddDTO.getCity(),
						contactAddDTO.getState(), contactAddDTO.getZipCode(),
						contactAddDTO.getCountry());
				String addrType = contactAddDTO.getAddrType();
				if (StringUtils.isNotEmpty(addrType)) {
					if (addrType.equals(AddressType.CONTACT.value())) {
						contactAddrsCount++;
						contactAddrs.add(contactAddDTO);
					} else if (addrType.equals(AddressType.BILL_TO.value())) {
						billAddrsCount++;
						billAddrs.add(contactAddDTO);
					} else if (addrType.equals(AddressType.SHIP_TO.value())) {
						shipAddrsCount++;
						shipAddrs.add(contactAddDTO);
					} else if (addrType.equals(AddressType.SOLD_TO.value())) {
						soldAddrsCount++;
						soldAddrs.add(contactAddDTO);
					}
				}
				contactAddDTO.setFullAddrLine(fullAddr);
			}
		}
		// 获得content template下拉框数值
		contentTmplList = publicService.getContentTmplList();
		List<Catalog> catalogList = productService.getSpecailCatalogList();
		for (Catalog po : catalogList) {
			CatalogDTO dto = new CatalogDTO();
			dto.setCatalogId(po.getCatalogId());
			dto.setCatalogName(po.getCatalogName());
			catalogDTOList.add(dto);
		}
	}

	/**
	 * 显示contact下的所有contact列表
	 * 
	 * @return "listCustCntct"
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map<String, ContactDTO> resultMap = null;
		Map<String, ContactDTO> dbContactsMap = null;
		// 如果sessContactNo为数字则取得数据中的contacts, 否则数据库中的contacts没有记录不用去查询
		if (StringUtils.isNumeric(this.sessContactNo)) {
			dbContactsMap = new LinkedHashMap<String, ContactDTO>();
			List<ContactDTO> dbContactsList = this.contactService
					.getContactsList(Integer.parseInt(sessContactNo));
			for (ContactDTO dto : dbContactsList) {
				dbContactsMap.put(dto.getContactId() + "", dto);
			}
		}
		// 取得session中的contacts
		Map<String, ContactDTO> sessContactsMap = (Map<String, ContactDTO>) SessionUtil
				.getRow(SessionConstant.ContactContact.value(), sessContactNo);
		SessionEmergeUtil<ContactDTO> sessionEmergeUtil = new SessionEmergeUtil<ContactDTO>();
		// 获得当前list页面所有的Contacts 记录(包括数据库和当前session中的).
		resultMap = sessionEmergeUtil.mergeList(sessContactsMap, dbContactsMap,
				1);
		// 根据查找条件对已获得的Contacts记录进行筛选
		contactsMap = this.getSearchFromMap(resultMap);
		return "contact_list";
	}

	/**
	 * 根据查找条件对已获得的Contacts记录进行筛选
	 * 
	 * @param resultMap
	 */
	private Map<String, ContactDTO> getSearchFromMap(
			final Map<String, ContactDTO> resultMap) {
		Map<String, ContactDTO> returnMap = new LinkedHashMap<String, ContactDTO>();
		if (resultMap == null || resultMap.isEmpty()) {
			return returnMap;
		}
		contactUserNameList = new ArrayList<String>();
		// 遍历所有记录根据contact搜索条件进行判断
		for (Iterator<String> iter = resultMap.keySet().iterator(); iter
				.hasNext();) {
			String key = iter.next();
			ContactDTO val = (ContactDTO) resultMap.get(key);
			// 数据库存在的却在操作过程中被删除了.
			if (val == null && StringUtils.isNumeric(key)) {
				continue;
			} else if (val == null && !StringUtils.isNumeric(key)) {
				System.out.println("error key:" + key);
				throw new RuntimeException(
						"Contact Contacts Session has Error !");
			}
			if (val.getContactMethod().equals(ContactMethod.Email.value())) {
				emailCount++;
			} else if (val.getContactMethod().equals(ContactMethod.Fax.value())) {
				faxCount++;
			} else if (val.getContactMethod()
					.equals(ContactMethod.Mail.value())) {
				mailCount++;
			} else if (val.getContactMethod().equals(
					ContactMethod.Phone.value())) {
				phoneCount++;
			}else if (val.getContactMethod().equals(ContactMethod.Meeting.value())){
				meetingCount++;
			}
			if (StringUtils.isNotEmpty(contactMethod)
					&& !(val.getContactMethod().equals(contactMethod))) {
				continue;
			}
			if (StringUtils.isNotEmpty(status)
					&& !(val.getStatus().equals(status))) {
				continue;
			}
			if (StringUtils.isNotEmpty(contactBy)
					&& val.getContactUserName().indexOf(contactBy) == -1) {
				continue;
			}
			if (contactDateFrom != null
					&& val.getContactDate().before(contactDateFrom)) {
				continue;
			}
			if (contactDateTo != null
					&& val.getContactDate().after(contactDateTo)) {
				continue;
			}
			if (!contactUserNameList.contains(val.getContactUserName())) {
				contactUserNameList.add(val.getContactUserName());
			}
			returnMap.put(key, val);
		}
		return returnMap;
	}

	/**
	 * 保存contact contact并写入Session方法
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String save() throws Exception {
		// allData = new ContactDTO();
		System.out.println("allData of Save is: " + allData);
		String source = ServletActionContext.getRequest().getParameter("source");
		if (StringUtils.isNotEmpty(source)) {
			allData.setSource(Integer.parseInt(source));
			allData.setSourceName(ServletActionContext.getRequest().getParameter("sourceName"));
		}
		allData.setContactName(ServletActionContext.getRequest().getParameter("contactName"));
		allData.setCustNo(contactNo);
		String scheduleDateStr = ServletActionContext.getRequest().getParameter("scheduleDate");
		if (StringUtils.isNotEmpty(scheduleDateStr)) {
			allData.setScheduleDate(DateUtils.formatStr2Date(scheduleDateStr,
					"yyyy-MM-dd"));
		}
		String contactDateStr = ServletActionContext.getRequest().getParameter("contactDate");
		if (StringUtils.isNotEmpty(contactDateStr)) {
			allData.setContactDate(DateUtils.formatStr2Date(contactDateStr,
					"yyyy-MM-dd"));
		}
		allData.setContactMethod(ServletActionContext.getRequest().getParameter("contactMethod"));

		String cntctMethod = allData.getContactMethod();
		if (cntctMethod.equals(ContactMethod.Email.value())) {
			allData.setEmail(ServletActionContext.getRequest().getParameter("email"));
			System.out.println("EmailSubject: "
					+ ServletActionContext.getRequest().getParameter("EmailSubject"));
			allData.setSubject(ServletActionContext.getRequest().getParameter("EmailSubject"));
			allData.setContent(ServletActionContext.getRequest().getParameter("EmailContent"));
		} else if (cntctMethod.equals(ContactMethod.Fax.value())) {
			allData.setFax(ServletActionContext.getRequest().getParameter("fax"));
			allData.setFaxExt(ServletActionContext.getRequest().getParameter("faxExt"));
			allData.setSubject(ServletActionContext.getRequest().getParameter("FaxSubject"));
		} else if (cntctMethod.equals(ContactMethod.Mail.value())) {
			String rec = ServletActionContext.getRequest().getParameter("recipient");
			if (StringUtils.isNotEmpty(rec)) {
				allData.setAddress(ServletActionContext.getRequest().getParameter(rec));
			}
			allData.setSubject(ServletActionContext.getRequest().getParameter("MailSubject"));
			allData.setContent(ServletActionContext.getRequest().getParameter("MailContent"));
		} else if (cntctMethod.equals(ContactMethod.Phone.value())) {
			allData.setPhone(ServletActionContext.getRequest().getParameter("phoneNumber"));
			allData.setSubject(ServletActionContext.getRequest().getParameter("PhoneSubject"));
			allData.setContent(ServletActionContext.getRequest().getParameter("PhoneContent"));
			allData.setInterestScore(Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("interestScore")));
			String callTime = ServletActionContext.getRequest().getParameter("callTimeFrom");
			String callTimeAmPm = ServletActionContext.getRequest().getParameter("callTimeFromAPM");
			allData.setCallTime(DateUtils.apmStr2Time(callTime, callTimeAmPm));
			allData.setCallTmzn(ServletActionContext.getRequest().getParameter("callTmzn"));
		} else if (cntctMethod.equals(ContactMethod.Meeting.value())) {
			allData.setSubject(ServletActionContext.getRequest().getParameter("meeting_name"));
			allData.setContent(ServletActionContext.getRequest().getParameter("meeting_content"));
		}
		if (cntctMethod.equals(ContactMethod.Email.value())
				|| cntctMethod.equals(ContactMethod.Mail.value())) {
			String tmpContentType = ServletActionContext.getRequest().getParameter("contactMethod")
					+ "ContentType";
			if(StringUtils.isNotEmpty(ServletActionContext.getRequest()
					.getParameter(tmpContentType))){
			Integer contentType = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter(tmpContentType));
			List<ContentTemplate> contentTmplList = publicService
					.getContentTmplList();
			if (contentTmplList != null && contentTmplList.size() > 0) {
				for (ContentTemplate tpl : contentTmplList) {
					Integer tplId = tpl.getTemplateId();
					if (tplId.compareTo(contentType) == 0) {
						allData.setContentType(tpl.getName());
						String tmpReferName = cntctMethod + "ReferName"
								+ tpl.getTemplateId();
						allData.setReferName(tmpReferName);
					}
				}
			}
			}
		}
		System.out.println("statusFlag: " + statusFlag);
		if (statusFlag == 1) {
			allData.setStatus(ServletActionContext.getRequest().getParameter("status_name"));
		} else {
			allData.setStatus("UN" + ServletActionContext.getRequest().getParameter("status_name"));
		}
		if (cntctMethod.equals(ContactMethod.Meeting)) {
			allData.setStatus("COMPLETED");
		}
		allData.setContactBy(SessionUtil.getUserId());
		allData.setContactUserName(SessionUtil.getUserName());
		if (StringUtils.isBlank(this.contactsKey)) {
			if (allData.getContactId() == null) {
				contactsKey = SessionUtil.generateTempId();
			} else {
				contactsKey = allData.getContactId() + "";
			}
		}
		// 保存或更新Contact模块下的Contacts标签.
		SessionUtil.updateOneRow(SessionConstant.ContactContact.value(),
				sessContactNo, contactsKey, allData);
		return NONE;
	}

	public Integer getPhoneCount() {
		return phoneCount;
	}

	public Integer getEmailCount() {
		return emailCount;
	}

	public Integer getFaxCount() {
		return faxCount;
	}

	public Integer getMailCount() {
		return mailCount;
	}

	public Integer getCustAddrsCount() {
		return contactAddrsCount;
	}

	public Integer getShipAddrsCount() {
		return shipAddrsCount;
	}

	public Integer getBillAddrsCount() {
		return billAddrsCount;
	}

	public Integer getSoldAddrsCount() {
		return soldAddrsCount;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getSaveType() {
		return saveType;
	}

	public String getSessContactId() {
		return sessContactId;
	}

	public void setSessContactId(String sessContactId) {
		this.sessContactId = sessContactId;
	}

	public String getContactIdStr() {
		return contactIdStr;
	}

	public void setContactIdStr(String contactIdStr) {
		this.contactIdStr = contactIdStr;
	}

	public ContactDTO getAllData() {
		return allData;
	}

	public void setAllData(ContactDTO allData) {
		this.allData = allData;
	}

	public String getCallTimeFromAPM() {
		return callTimeFromAPM;
	}

	public void setCallTimeFromAPM(String callTimeFromAPM) {
		this.callTimeFromAPM = callTimeFromAPM;
	}

	public String getCallTimeFrom() {
		return callTimeFrom;
	}

	public void setCallTimeFrom(String callTimeFrom) {
		this.callTimeFrom = callTimeFrom;
	}

	public String getCallTimeToAPM() {
		return callTimeToAPM;
	}

	public void setCallTimeToAPM(String callTimeToAPM) {
		this.callTimeToAPM = callTimeToAPM;
	}

	public String getCallTimeTo() {
		return callTimeTo;
	}

	public void setCallTimeTo(String callTimeTo) {
		this.callTimeTo = callTimeTo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFaxExt() {
		return faxExt;
	}

	public void setFaxExt(String faxExt) {
		this.faxExt = faxExt;
	}

	public String getBstCallTmzn() {
		return bstCallTmzn;
	}

	public String getAddrType() {
		return addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCallTimeList(CallTime[] callTimeList) {
		this.callTimeList = callTimeList;
	}

	public CallTime[] getCallTimeList() {
		return callTimeList;
	}

	public void setAmPmList(AmPm[] amPmList) {
		this.amPmList = amPmList;
	}

	public AmPm[] getAmPmList() {
		return amPmList;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<ContactAddressDTO> getCustAddrs() {
		return contactAddrs;
	}

	public List<ContactAddressDTO> getBillAddrs() {
		return billAddrs;
	}

	public List<ContactAddressDTO> getShipAddrs() {
		return shipAddrs;
	}

	public List<ContactAddressDTO> getSoldAddrs() {
		return soldAddrs;
	}

	public List<ContentTemplate> getContentTmplList() {
		return contentTmplList;
	}

	public void setContentTmplList(List<ContentTemplate> contentTmplList) {
		this.contentTmplList = contentTmplList;
	}

	public List<CatalogDTO> getCatalogDTOList() {
		return catalogDTOList;
	}

	public Page<ContactDTO> getPageContactDTO() {
		return pageContactDTO;
	}

	public void setPageContactDTO(Page<ContactDTO> pageContactDTO) {
		this.pageContactDTO = pageContactDTO;
	}

	public Date getContactDateFrom() {
		return contactDateFrom;
	}

	public void setContactDateFrom(Date contactDateFrom) {
		this.contactDateFrom = contactDateFrom;
	}

	public Date getContactDateTo() {
		return contactDateTo;
	}

	public void setContactDateTo(Date contactDateTo) {
		this.contactDateTo = contactDateTo;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public String getContactBy() {
		return contactBy;
	}

	public void setContactBy(String contactBy) {
		this.contactBy = contactBy;
	}

	public List<ContactDTO> getRetContactList() {
		return retContactList;
	}

	public void setRetContactList(List<ContactDTO> retContactList) {
		this.retContactList = retContactList;
	}

	public List<String> getContactUserNameList() {
		return contactUserNameList;
	}

	public void setContactUserNameList(List<String> contactUserNameList) {
		this.contactUserNameList = contactUserNameList;
	}

	public Date getContactDateDefault() {
		return contactDateDefault;
	}

	public void setContactDateDefault(Date contactDateDefault) {
		this.contactDateDefault = contactDateDefault;
	}

	public List<DropDownListDTO> getSpecDropDownist() {
		return specDropDownist;
	}

	public void setSessCustNo(String sessContactNo) {
		this.sessContactNo = sessContactNo;
	}

	public String getSessCustNo() {
		return sessContactNo;
	}

	public void setCustNo(Integer contactNo) {
		this.contactNo = contactNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCustNo() {
		return contactNo;
	}

	public Integer getContactAddrsCount() {
		return contactAddrsCount;
	}

	public void setContactAddrsCount(Integer contactAddrsCount) {
		this.contactAddrsCount = contactAddrsCount;
	}

	public String getSessContactNo() {
		return sessContactNo;
	}

	public void setSessContactNo(String sessContactNo) {
		this.sessContactNo = sessContactNo;
	}

	public List<ContactAddressDTO> getContactAddrs() {
		return contactAddrs;
	}

	public void setContactAddrs(List<ContactAddressDTO> contactAddrs) {
		this.contactAddrs = contactAddrs;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	public void setShipAddrsCount(Integer shipAddrsCount) {
		this.shipAddrsCount = shipAddrsCount;
	}

	public void setBillAddrsCount(Integer billAddrsCount) {
		this.billAddrsCount = billAddrsCount;
	}

	public void setSoldAddrsCount(Integer soldAddrsCount) {
		this.soldAddrsCount = soldAddrsCount;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public void setPhoneCount(Integer phoneCount) {
		this.phoneCount = phoneCount;
	}

	public void setEmailCount(Integer emailCount) {
		this.emailCount = emailCount;
	}

	public void setFaxCount(Integer faxCount) {
		this.faxCount = faxCount;
	}

	public void setMailCount(Integer mailCount) {
		this.mailCount = mailCount;
	}

	public void setBstCallTmzn(String bstCallTmzn) {
		this.bstCallTmzn = bstCallTmzn;
	}

	public void setBillAddrs(List<ContactAddressDTO> billAddrs) {
		this.billAddrs = billAddrs;
	}

	public void setShipAddrs(List<ContactAddressDTO> shipAddrs) {
		this.shipAddrs = shipAddrs;
	}

	public void setSoldAddrs(List<ContactAddressDTO> soldAddrs) {
		this.soldAddrs = soldAddrs;
	}

	public void setCatalogDTOList(List<CatalogDTO> catalogDTOList) {
		this.catalogDTOList = catalogDTOList;
	}

	public void setSpecDropDownist(List<DropDownListDTO> specDropDownist) {
		this.specDropDownist = specDropDownist;
	}

	public Map<String, ContactDTO> getContactsMap() {
		return contactsMap;
	}

	public String getContactsKey() {
		return contactsKey;
	}

	public void setContactsKey(String contactsKey) {
		this.contactsKey = contactsKey;
	}

	public Integer getMeetingCount() {
		return meetingCount;
	}

	public void setMeetingCount(Integer meetingCount) {
		this.meetingCount = meetingCount;
	}

	public String getCallTmzn() {
		return callTmzn;
	}

	public void setCallTmzn(String callTmzn) {
		this.callTmzn = callTmzn;
	}
}
