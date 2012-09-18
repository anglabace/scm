package com.genscript.gsscm.customer.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.constant.AmPm;
import com.genscript.gsscm.common.constant.CallTime;
import com.genscript.gsscm.common.constant.ContactStatusType;
import com.genscript.gsscm.common.constant.NamePfx;
import com.genscript.gsscm.common.constant.NameSfx;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.customer.dao.CreditRatingDao;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustGrantsDTO;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.dto.CustPointHistoryDTO;
import com.genscript.gsscm.customer.dto.CustPubsDTO;
import com.genscript.gsscm.customer.dto.CustSpecialPriceDTO;
import com.genscript.gsscm.customer.dto.CustomerActivityDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.dto.CustomerInterestDTO;
import com.genscript.gsscm.customer.dto.RedeemHistoryDTO;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.CustomerCase;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;
import com.genscript.gsscm.customer.entity.CustomerGrants;
import com.genscript.gsscm.customer.entity.CustomerInterest;
import com.genscript.gsscm.customer.entity.CustomerPersonalInfo;
import com.genscript.gsscm.customer.entity.CustomerPointsHistory;
import com.genscript.gsscm.customer.entity.CustomerPublications;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.service.CustomerPointsHistoryService;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.EmailGroup;
import com.genscript.gsscm.systemsetting.service.EmailGroupService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Customer 编辑相关Action
 * 
 * @author zouyulu
 */

@Results({
		@Result(name = "edit", location = "customer/customer_edit.jsp"),
		@Result(name = "showAllPointhistory", location = "customer/show_all_point_history.jsp"),
		@Result(name = "view_activity", location = "customer/customer_view_activity.jsp"),
		@Result(name = "show_redeem_point", location = "customer/show_redeem_point.jsp"),
		@Result(name = "next_redeem_point", location = "customer/next_redeem_point.jsp"),
		@Result(name = "openaccount", location = "customer/openaccount.jsp"),
		@Result(name = "accountsso", location = "customer/account_sso.jsp"),
		@Result(name = "success_redeem_point", location = "customer/success_redeem_point.jsp"),
		@Result(name = "show_redeem_point_history", location = "customer/show_redeem_point_history.jsp"),
		@Result(name = "show_amazon_point_history", location = "customer/show_amazon_point_history.jsp"),
		@Result(name = "show_instruction", location = "customer/customer_instruction.jsp") })
public class CustomerAction extends BaseAction<ContactDTO> {

	/**
     *
     */
	private static final long serialVersionUID = -213548121178901573L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private CustomerPointsHistoryService customerPointsHistoryService;
	@Autowired
	private DozerBeanMapper dozer;
	private int custNo;
	private String sessCustNo;
	private int defaultTab;
	private List<DropDownListDTO> specDropDownist;
	private CustomerDTO customerDetail;

	private Map<String, List<PbDropdownListOptions>> dropDownList;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private List<SalesRep> salesManagerList;
	private List<SalesRep> techManagerList;
	@Autowired
	private EmailGroupService emailGroupService;
	private List<DropDownListDTO> arrDropdownList;

	private NamePfx[] namePfxList;
	private NameSfx[] nameSfxList;
	private CallTime[] callTimeList;
	private AmPm[] amPmList;
	private ContactStatusType[] contactStatusList;

	// Instruction & note
	private Integer noteId;
	private String sessNoteId;
	private List<CustNoteDTO> noteList;
	private Map<String, String> noteJsonMap;
	private CustNoteDTO noteDTO;
	private List<CustNoteDTO> custNoteList;
	private String docDelIndexs;
	@Autowired
	private CreditRatingDao creditRatingDao;

	private Integer salesContact;
	private Integer techSupport;
	// 修改密码
	private String newPassword;
	private String confirmPassW;
	private String modifiedBy;
	private String dept_func;
	// 文件处理
	@Autowired
	private FileService fileService;
	/**
	 * 实际上传文件
	 */
	private List<File> upload;
	/**
	 * 文件的内容类型
	 */
	private List<String> uploadContentType;
	/**
	 * 上传文件名
	 */
	private List<String> uploadFileName;

	// delete customer
	private Integer delSuccessCount = 0;
	private Integer delTotal = 0;

	// 用户获取非customer列表页面到修改页面的URL--Zhang Yong
	private String operation_method;
	// accounting
	private List<PbDropdownListOptions> cardTypeList;
	private List<CreditCard> cardList;
	private CreditCard creditCard;
	private Integer creditCardId;
	private String orgId;
	private String modifyByUsername;
	private Page<RedeemHistoryDTO> redeemPage = new Page<RedeemHistoryDTO>(10);
	// add by zhou gang 2011 9 -27
	private Page<CustomerPointsHistory> amazonhistoryPage = new Page<CustomerPointsHistory>(
			10);

	public Page<CustomerPointsHistory> getAmazonhistoryPage() {
		return amazonhistoryPage;
	}

	public String accountsso() {
		String id = ServletActionContext.getRequest().getParameter("id");
		ServletActionContext.getRequest().setAttribute("id", id);
		String cookie_name = ServletActionContext.getRequest().getParameter(
				"cookie_name");
		String custno = ServletActionContext.getRequest()
				.getParameter("custno");
		String firstName = ServletActionContext.getRequest().getParameter(
				"firstName");
		String lastName = ServletActionContext.getRequest().getParameter(
				"lastName");
		String email = ServletActionContext.getRequest().getParameter("email");
		ServletActionContext.getRequest().setAttribute("cookie_name",
				cookie_name);
		ServletActionContext.getRequest().setAttribute("custno", custno);
		ServletActionContext.getRequest().setAttribute("firstName", firstName);
		ServletActionContext.getRequest().setAttribute("lastName", lastName);
		ServletActionContext.getRequest().setAttribute("email", email);
		return "accountsso";
	}

	public String openaccount() {
		String cookie_name = ServletActionContext.getRequest().getParameter(
				"cookie_name");
		String custno = ServletActionContext.getRequest()
				.getParameter("custno");
		String firstName = ServletActionContext.getRequest().getParameter(
				"firstName");
		String lastName = ServletActionContext.getRequest().getParameter(
				"lastName");
		String email = ServletActionContext.getRequest().getParameter("email");
		ServletActionContext.getRequest().setAttribute("cookie_name",
				cookie_name);
		ServletActionContext.getRequest().setAttribute("custno", custno);
		ServletActionContext.getRequest().setAttribute("firstName", firstName);
		ServletActionContext.getRequest().setAttribute("lastName", lastName);
		ServletActionContext.getRequest().setAttribute("email", email);

		return "openaccount";
	}

	public void setAmazonhistoryPage(
			Page<CustomerPointsHistory> amazonhistoryPage) {
		this.amazonhistoryPage = amazonhistoryPage;
	}

	private Departments department;

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public String getModifyByUsername() {
		return modifyByUsername;
	}

	public void setModifyByUsername(String modifyByUsername) {
		this.modifyByUsername = modifyByUsername;
	}

	@Autowired
	private UserDao userDao;

	/**
	 * 修改Customer资料。
	 * 
	 * @return "edit" location="customer/customer_edit.jsp"
	 * @throws Exception
	 * @author zouyulu
	 */
	public String edit() throws Exception {
		// 如果custNo不为空则是修改Customer,并在session中插入该Customer Detail
		if (custNo != 0) {
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 判断将要修改的单号是否正在被操作
			String editUrl = "customer/customer!edit.action?custNo=" + custNo;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			// *********** Add By Zhang Yong End *****************************//
			// accounting
			cardList = orderService.getCustActiveCardList(custNo);
			cardTypeList = publicService.getDropdownList("CREDIT_CARD_TYPE");
			customerDetail = customerService.getCustomerDetail(custNo);
			StringBuilder sb = new StringBuilder();
			sb.append(customerDetail.getFirstName());
			if (StringUtils.isNotEmpty(customerDetail.getLastName())) {
				sb.append(" ");
			}
			if (customerDetail != null) {
				if (customerDetail.getModifiedBy() != null) {
					modifyByUsername = userDao.getLoginName(customerDetail
							.getModifiedBy());
				}
			}
			sb.append(customerDetail.getLastName());
			if (StringUtils.isNotEmpty(customerDetail.getMidName())) {
				sb.append(", ");
			}
			sb.append(customerDetail.getMidName());
			SessionUtil.insertRow("customerName", Integer.toString(custNo),
					sb.toString());
			SessionUtil.insertRow("customer", Integer.toString(custNo),
					customerDetail);
			this.setSessCustNo(Integer.toString(custNo));
			clearSession(sessCustNo);// 刷新的时候清除过期的session
			if (customerDetail.getCustAddrList() != null
					&& !customerDetail.getCustAddrList().isEmpty()) {
				SessionUtil.insertRow(SessionConstant.CustAddressList.value(),
						Integer.toString(custNo),
						customerDetail.getCustAddrList());
			}
			if (!StringUtils.isEmpty(customerDetail.getPasswd())) {
				SessionUtil.insertRow("passwd", this.sessCustNo, new String(
						this.customerDetail.getPasswd()));
			}
		} else {// 如果custNo为空也在session中插入Customer空记录，custNo为sessionCustNo
			cardList = new ArrayList<CreditCard>();
			cardTypeList = new ArrayList<PbDropdownListOptions>();

			// *********** Add By Zhang Yong Start
			// *****************************//
			// 释放application中的订单锁，主要针对从其他模块中新建此对象时
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
			// *********** Add By Zhang Yong End *****************************//

			this.setSessCustNo(SessionUtil.generateTempId());
			if (customerDetail == null) {
				customerDetail = new CustomerDTO();
			}
			SessionUtil.insertRow("customer", this.getSessCustNo(),
					customerDetail);
		}
		if (customerDetail != null) {
			salesContact = customerDetail.getSalesContact();
			techSupport = customerDetail.getTechSupport();
		}
		// add by zhanghuibin
		if (salesContact == null || techSupport == null) {
			String sessNo = (custNo != 0 ? String.valueOf(custNo) : sessCustNo);
			Map<String, List<SalesRep>> salesMap = publicService
					.searchManager_Cust_Contact("customer", sessNo);
			if (salesContact == null && salesMap != null
					&& salesMap.get("salesManager") != null
					&& salesMap.get("salesManager").size() > 0) {
				salesContact = salesMap.get("salesManager").get(0).getSalesId();
			}
			if (techSupport == null && salesMap != null
					&& salesMap.get("techAcountManager") != null
					&& salesMap.get("techAcountManager").size() > 0) {
				techSupport = salesMap.get("techAcountManager").get(0)
						.getSalesId();
			}
		}
		this.initManagerList();
		this.setNamePfxList(NamePfx.values());
		this.setNameSfxList(NameSfx.values());
		this.setCallTimeList(CallTime.values());
		this.setAmPmList(AmPm.values());
		this.setContactStatusList(ContactStatusType.values());
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("CUST_PRIORITY_LEVEL",
				publicService.getDropdownList("CUST_PRIORITY_LEVEL"));
		dropDownList.put("CONTACT_METHOD",
				publicService.getDropdownList("CONTACT_METHOD"));
		dropDownList.put("EMAIL_FORMAT",
				publicService.getDropdownList("EMAIL_FORMAT"));
		dropDownList.put("PAYMENT_METHOD",
				publicService.getDropdownList("PAYMENT_METHOD"));

		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.ORGANIZATION_CATEGORY);
		speclListName.add(SpecDropDownListName.ORGANIZATION_TYPE);
		speclListName.add(SpecDropDownListName.DEPARTMENT_FUNCTION);
		speclListName.add(SpecDropDownListName.CUSTOMER_ROLE);
		speclListName.add(SpecDropDownListName.ORIGINAL_SOURCE);
		speclListName.add(SpecDropDownListName.TIME_ZONE);
		speclListName.add(SpecDropDownListName.LANGUAGE_CODE);
		speclListName.add(SpecDropDownListName.DECIPLINE_INTEREST);
		speclListName.add(SpecDropDownListName.APPLICATION_INTEREST);
		speclListName.add(SpecDropDownListName.CREDIT_LIMIT);
		speclListName.add(SpecDropDownListName.CURRENCY);
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		speclListName.add(SpecDropDownListName.PROJECT_SUPPORT);
		arrDropdownList = this.publicService.getSpecDropDownList(speclListName);// add
																				// by
																				// zhou
																				// gang

		specDropDownList = publicService.getSpecDropDownMap(speclListName);
		if (custNo != 0) {
			ServletActionContext.getRequest().setAttribute("callTimeFromAPM",
					DateUtils.getAPM(customerDetail.getBstCallTimeFrom()));
			ServletActionContext.getRequest()
					.setAttribute(
							"callTimeFrom",
							DateUtils.getAPMTime(
									customerDetail.getBstCallTimeFrom(), 5));
			ServletActionContext.getRequest().setAttribute("callTimeToAPM",
					DateUtils.getAPM(customerDetail.getBstCallTimeTo()));
			ServletActionContext.getRequest().setAttribute("callTimeTo",
					DateUtils.getAPMTime(customerDetail.getBstCallTimeTo(), 5));
			// Accounting note list
			noteList = customerService.getNoteList(custNo);
			Map<String, CustNoteDTO> custNoteMap = new HashMap<String, CustNoteDTO>();
			if (!noteList.isEmpty()) {
				noteJsonMap = new HashMap<String, String>();
				for (CustNoteDTO custNoteDTO : noteList) {
					custNoteMap.put(Integer.toString(custNoteDTO.getId()),
							custNoteDTO);
				}
			}
			SessionUtil.insertRow(SessionConstant.CustNoteList.value(),
					sessCustNo, custNoteMap);
		}
		return "edit";
	}

	private CustomerActivityDTO customerActivityDTO;

	public CustomerActivityDTO getCustomerActivityDTO() {
		return customerActivityDTO;
	}

	public void setCustomerActivityDTO(CustomerActivityDTO customerActivityDTO) {
		this.customerActivityDTO = customerActivityDTO;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String showAllPointHistory() throws Exception {

		return "showAllPointhistory";
	}

	/**
	 * 显示活动信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewActivitys() throws Exception {
		customerActivityDTO = new CustomerActivityDTO();
		Integer custNo = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("custNo"));
		Integer OrderNO = 0;
		Date OrderDate = new java.util.Date();
		Integer Soldby = 0;
		Integer Enteredby = 0;
		String EnteredbyName = "";
		String SoldbyName = "";
		String statusname = "";

		OrderMain orderMain = orderService.getOrderMain(custNo);
		if (orderMain != null) {
			OrderNO = orderMain.getOrderNo();
			OrderDate = orderMain.getOrderDate();
			Soldby = orderMain.getSalesContact();
			Enteredby = orderMain.getCreatedBy();
			statusname = orderService.getStatusName(orderMain.getStatus());
			SoldbyName = orderService.getSoldbyName(Soldby);
			EnteredbyName = orderService.getEnteredbyName(Enteredby);

		}

		// 再根据cust_no从customer_contact_history表中取数据
		Date DateTime = new Date();
		String ContactBy = "";
		String ContactPerson = "";
		String LastEmailSent = "";
		String LastEmailSubject = "";
		String LastFaxSent = "";
		String LastFaxSubject = "";
		String LastMailingSent = "";
		String LastMailingSubject = "";
		CustomerContactHistory customerContactHistoryByEmail = customerService
				.getContactHistory(custNo, "Email");
		CustomerContactHistory customerContactHistoryByFax = customerService
				.getContactHistory(custNo, "Fax");
		CustomerContactHistory customerContactHistoryByMail = customerService
				.getContactHistory(custNo, "Mail");
		CustomerContactHistory customerContactHistoryByPhone = customerService
				.getContactHistory(custNo, "Phone");
		if (customerContactHistoryByEmail != null) {
			LastEmailSent = customerContactHistoryByEmail.getEmail();
			LastEmailSubject = customerContactHistoryByEmail.getSubject();
		}
		if (customerContactHistoryByFax != null) {
			LastFaxSent = customerContactHistoryByFax.getFax();
			LastFaxSubject = customerContactHistoryByFax.getSubject();
		}
		if (customerContactHistoryByMail != null) {
			LastMailingSent = customerContactHistoryByMail.getAddress();
			LastMailingSubject = customerContactHistoryByMail.getSubject();
		}

		if (customerContactHistoryByPhone != null) {
			DateTime = customerContactHistoryByPhone.getContactDate();
			ContactPerson = customerContactHistoryByPhone.getContactName();
			ContactBy = orderService
					.getEnteredbyName(customerContactHistoryByPhone
							.getContactBy());
		}

		Date LastModifiedDate = new Date();
		String LastModifiedBy = "";
		CustomerDTO customerDTO = customerService.getCustomerBase(custNo);
		if (customerDTO != null) {
			LastModifiedDate = customerDTO.getModifyDate();
			LastModifiedBy = orderService.getEnteredbyName(customerDTO
					.getModifiedBy());
		}
		customerActivityDTO.setOrderNo(OrderNO);
		customerActivityDTO.setContactBy(ContactBy);
		customerActivityDTO.setContactPerson(ContactPerson);
		customerActivityDTO.setCustomNo(custNo);
		customerActivityDTO.setDateTime(DateTime);
		customerActivityDTO.setEnteredby(EnteredbyName);
		customerActivityDTO.setLastFaxSent(LastFaxSent);
		customerActivityDTO.setLastModifiedBy(LastModifiedBy);
		customerActivityDTO.setLastModifiedDate(LastModifiedDate);
		customerActivityDTO.setLasFaxSubject(LastFaxSubject);
		customerActivityDTO.setLastEmailSubject(LastEmailSubject);
		customerActivityDTO.setLastEmailSent(LastEmailSent);
		customerActivityDTO.setLastMailingSent(LastMailingSent);
		customerActivityDTO.setLastMailingSubject(LastMailingSubject);
		customerActivityDTO.setStatus(statusname);
		customerActivityDTO.setOrderDate(OrderDate);
		customerActivityDTO.setSoldby(SoldbyName);

		return "view_activity";
	}

	private void initManagerList() {
		salesManagerList = customerService.getSalesManages("SALES_CONTACT");
		techManagerList = customerService.getSalesManages("TECH_SUPPORT");

	}

	public void modifyPassWord() throws Exception {
		if (this.newPassword.equals(this.confirmPassW)) {
			String s = "true";
			this.sessCustNo = String.valueOf(this.custNo);
			SessionUtil.insertRow("passwd", this.sessCustNo, new String(
					this.confirmPassW));
			Struts2Util.renderText(s);
		}

	}

	public List<DropDownListDTO> getArrDropdownList() {
		return arrDropdownList;
	}

	public void setArrDropdownList(List<DropDownListDTO> arrDropdownList) {
		this.arrDropdownList = arrDropdownList;
	}

	/**
	 * Save customer detail保存所有的Customer info
	 * 
	 * @return null
	 */

	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();

		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessCustNo != null && !("").equals(sessCustNo)) {
			String editUrl = "customer/customer!edit.action?custNo="
					+ sessCustNo;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message",
						"Save customer fail,the customer is editing by "
								+ operation_method);
				rt.put("custNo", sessCustNo);
				Struts2Util.renderJson(rt);
				// 清除Session
				clearSession(sessCustNo);
				return null;
			}
		}
		// *********** Add By Zhang Yong End *****************************//
		try {
			boolean isAdd = false;
			if (customerDetail.getCustNo() == null
					|| customerDetail.getCustNo() == 0) {
				this.confirmPassW = customerService.generalPass();
				isAdd = true;
			} else {
				this.confirmPassW = (String) SessionUtil.getRow("passwd",
						this.sessCustNo);
			}

			String crRatingId = "";
			crRatingId = ServletActionContext.getRequest().getParameter(
					"crRatingIds");
			if (crRatingId != null && !"".equals(crRatingId)) {
				if (crRatingId.equals("99")) {
					String creditLimit = ServletActionContext.getRequest()
							.getParameter("creditLimit");
					customerDetail.setCreditLimit(new BigDecimal(creditLimit));
				} else {
					customerDetail
							.setCreditLimit(new BigDecimal(this.creditRatingDao
									.getById(Integer.parseInt(crRatingId))
									.getCrLimit()));
				}
				customerDetail.setCrRatingId(Integer.parseInt(crRatingId));
			}

			customerDetail.setSpecialDiscount(BigDecimal.ZERO);
			customerDetail.setPasswd(this.confirmPassW);
			customerDetail.setModifiedBy(SessionUtil.getUserId());
			customerDetail.setCompanyId(1);
			customerDetail.setBillAccountCode(ServletActionContext.getRequest()
					.getParameter("billAccountCode"));

			String callTimeFrom = ServletActionContext.getRequest()
					.getParameter("callTimeFrom");
			String callTimeFromPm = ServletActionContext.getRequest()
					.getParameter("callTimeFromPm");
			customerDetail.setBstCallTimeFrom(DateUtils.apmStr2Time(
					callTimeFrom, callTimeFromPm));
			String callTimeTo = ServletActionContext.getRequest().getParameter(
					"callTimeTo");
			String callTimeToPm = ServletActionContext.getRequest()
					.getParameter("callTimeToPm");
			customerDetail.setBstCallTimeTo(DateUtils.apmStr2Time(callTimeTo,
					callTimeToPm));
	/*		if (StringUtils.isEmpty(customerDetail.getCcpayFlag())) {
				customerDetail.setCcpayFlag("N");// 默认值
			}*/
			String orgCountry = customerDetail.getOrganization().getCountry();
			// String billCountry=customerDetail.getCustAddrList().
			/*
			 * Finland, @Slovakia#, Germany, Malta, San Marino, Austria, Greece,
			 * Netherlands, Spain, Czech Republic, Belgium, Sweden, France,
			 * Luxembourg, Slovenia, United Kingdom / UK, Iceland, Norway,
			 * Switzerland, Ireland, Portugal, Denmark, Italy, Canada, United
			 * States / USA, Israel, Cyprus, Singapore, Taiwan, Japan, South
			 * Korea, Hong Kong, New Zealand, Australia, Puerto Rico, U.S.
			 * Virgin Islands
			 */
				String country=customerDetail.getCountry();
			if (country.equals("FI") || country.equals("DE")
					|| country.equals("MT") || country.equals("SM")
					|| country.equals("AT") || country.equals("GR")
					|| country.equals("NL") || country.equals("ES")
					|| country.equals("CZ") || country.equals("BE")
					|| country.equals("SE") || country.equals("FR")
					|| country.equals("LU") || country.equals("SI")
					|| country.equals("GB") || country.equals("IS")
					|| country.equals("NO") || country.equals("CH")
					|| country.equals("PT") || country.equals("DK")
					|| country.equals("IT") || country.equals("CA")
					|| country.equals("US") || country.equals("IL")
					|| country.equals("CY") || country.equals("SG")
					|| country.equals("TW") || country.equals("JP")
					|| country.equals("KR") || country.equals("HK")
					|| country.equals("NZ") || country.equals("AU")
					|| country.equals("US-PR") || country.equals("VI")) {
				customerDetail.setCcpayFlag("Y");

			} else {
				customerDetail.setCcpayFlag("N");
			}

			if (customerDetail.getPrefShipMthd() == null) {
				if (orgCountry.equals("US")) {
					customerDetail.setPrefShipMthd(2);
				} else {
					customerDetail.setPrefShipMthd(8);
				}

			}
			// 关联Customer Contact
			attachCustContact();
			// Related Address Information
			attachAddress();
			// 关联More Info里的Customer Area Interest
			attachCustInterest();
			// 关联More Info里的Customer Publications
			attachCustPublications();
			// 关联More Info里的Customer Grants
			attachCustGrants();
			// 处理custNoteList
			attachCustNoteList();
			// 处理 customer special price list
			attachCustSpclPrcList();
			// 处理customer case list
			attachCustCaseList();
			// attachAddress();

			CustomerPersonalInfo custPersonalInfo;
			if (custNo != 0) {
				custPersonalInfo = (CustomerPersonalInfo) SessionUtil.getRow(
						SessionConstant.PersonalInfo.value(),
						Integer.toBinaryString(custNo));
			} else {
				custPersonalInfo = (CustomerPersonalInfo) SessionUtil.getRow(
						SessionConstant.PersonalInfo.value(), sessCustNo);
			}
			if (custPersonalInfo != null) {
				customerDetail.setPersonal(custPersonalInfo);
			}
			initManagerList();
			if (customerDetail.getSalesContact() == null
					&& (customerDetail.getCustNo() == null || customerDetail
							.getCustNo() == 0)) {
				customerDetail.setSalesContact(salesManagerList != null
						&& salesManagerList.size() > 0 ? salesManagerList
						.get(0).getSalesId() : null);
				customerDetail.setTechSupport(techManagerList != null
						&& techManagerList.size() > 0 ? techManagerList.get(0)
						.getSalesId() : null);
			}
			if (customerDetail.getTechSupport() == null) {
				rt.put("message", "Please select one tech account manager.");
				Struts2Util.renderJson(rt);
				return null;
			}
			if (customerService.isReapt(sessCustNo,
					customerDetail.getBusEmail())) {
				rt.put("message",
						"The customer busEmail has been used by other customer.");
				Struts2Util.renderJson(rt);
				return null;
			}
			Customer customer = customerService.saveCustomer(customerDetail);

			if (isAdd) {
				// 给新增用户加1000points
				customerPointsHistoryService.addPointsToCust(1000,
						customer.getCustNo(), null);
				// 给新增用户的用户发送邮件
				String subject = "Your New GenScript Account Information";
				StringBuffer content = new StringBuffer();
				content.append("Dear ").append(customerDetail.getFirstName())
						.append(" ").append(customerDetail.getLastName())
						.append(":<br><br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;GenScript would like to welcome you as a new customer to our firm. The following is your account information:<br><br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;Account Number:")
						.append(customer.getCustNo()).append("<br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;Email:")
						.append(customerDetail.getBusEmail()).append("<br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;Password:")
						.append(confirmPassW).append("<br><br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;To view or modify your account, please log in via <a href='https://10.168.2.225/ssl-bin/myaccount'>https://10.168.2.225/ssl-bin/myaccount.</a><br><br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;As the way of welcoming you, we have enclosed 1,000 EzCouponTM points, which could be redeemed to $10 coupon--entitles you to $10 off on your next order. The points will never expire.<br><br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;To learn more about EzCouponTM points, please visit<a href='http://www.genscript.com/ezcoupon.html'> http://www.genscript.com/ezcoupon.html</a><br><br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;GenScript constantly launches new services and products to meet your needs for life science research and drug discovery. Find out more at<a href='http://www.genscript.com'> http://www.genscript.com</a><br><br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;We're looking forward to serving you in the near future.<br><br><br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;Sincerely.<br>");
				content.append("&nbsp;&nbsp;&nbsp;&nbsp;GenScript Customer Service<br>");
				content.append("&nbsp;&nbsp;&nbsp;<a href='http://www.genscript.com'>http://www.genscript.com</a><br>");
				mimeMailService.sendMail(
						customerDetail.getBusEmail() == null ? ""
								: customerDetail.getBusEmail(), subject,
						content.toString(), "support@genscript.com");
			}

			custNo = customer.getCustNo();
			// 清除session
			clearSession(sessCustNo);
			// 返回
			rt.put("custNo", Integer.toString(custNo));
			rt.put("message", "The customer account #" + custNo + " is saved.");
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * Save the Address Information
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private void attachAddress() {
		List<CustAddrOperDTO> addrList = null;
		if (custNo == 0) {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(), sessCustNo);
		} else {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(),
					String.valueOf(custNo));
		}
		List<Integer> delAddrList = new ArrayList<Integer>();
		CustAddrOperDTO tempAddr3 = null;
		if (addrList != null) {
			boolean contactFlg = false;
			boolean shipToFlg = false;
			boolean billToFlg = false;
			for (int i = 0; i < addrList.size(); i++) {
				CustAddrOperDTO tempAddr = (CustAddrOperDTO) addrList.get(i);
				if ("CONTACT".equals(tempAddr.getAddrType())
						&& "Y".equals(tempAddr.getDefaultFlag())
						&& !OperationType.DEL.value().equals(
								tempAddr.getOperateType())
						&& StatusType.ACTIVE.value().equals(
								tempAddr.getStatus())) {
					contactFlg = true;
				}
				if ("SHIP_TO".equals(tempAddr.getAddrType())
						&& "Y".equals(tempAddr.getDefaultFlag())
						&& !OperationType.DEL.value().equals(
								tempAddr.getOperateType())
						&& StatusType.ACTIVE.value().equals(
								tempAddr.getStatus())) {
					shipToFlg = true;
				}
				if ("BILL_TO".equals(tempAddr.getAddrType())
						&& "Y".equals(tempAddr.getDefaultFlag())
						&& !OperationType.DEL.value().equals(
								tempAddr.getOperateType())
						&& StatusType.ACTIVE.value().equals(
								tempAddr.getStatus())) {
					billToFlg = true;
				}
				if (tempAddr.getOperateType() != null) {
					if (tempAddr.getOperateType().equals(
							OperationType.DEL.value())) {
						delAddrList.add(tempAddr.getAddrId());
						addrList.remove(i);
						i--;
					} else if (tempAddr.getOperateType().equals(
							OperationType.ADD.value())) {
						tempAddr.setAddrId(null);
						addrList.remove(i);
						addrList.add(i, tempAddr);
					} else if (tempAddr.getOperateType() == null) {
						addrList.remove(i);
						i--;
					}
				}
			}
			if (addrList.size() == 0) {
				CustAddrOperDTO tempAddr1 = newAddress("CONTACT");
				addrList.add(tempAddr1);
				CustAddrOperDTO tempAddr2 = newAddress("SHIP_TO");
				addrList.add(tempAddr2);
				tempAddr3 = newAddress("BILL_TO");
				addrList.add(tempAddr3);

			} else {
				if (!contactFlg) {
					CustAddrOperDTO tempAddr1 = newAddress("CONTACT");
					addrList.add(tempAddr1);

				}
				if (!shipToFlg) {
					CustAddrOperDTO tempAddr2 = newAddress("SHIP_TO");
					addrList.add(tempAddr2);
				}
				if (!shipToFlg) {
					tempAddr3 = newAddress("BILL_TO");
					addrList.add(tempAddr3);
				}
			}
			if (delAddrList.size() > 0) {
				customerDetail.setDelAddrIdList(delAddrList);
			}
		} else {
			addrList = new ArrayList<CustAddrOperDTO>();
			CustAddrOperDTO tempAddr1 = newAddress("CONTACT");
			addrList.add(tempAddr1);
			CustAddrOperDTO tempAddr2 = newAddress("SHIP_TO");
			addrList.add(tempAddr2);
			tempAddr3 = newAddress("BILL_TO");
			addrList.add(tempAddr3);
		}
		customerDetail.setCustAddrList(addrList);
		String billAccountCode = "";
		if (tempAddr3 != null && !"".equals(tempAddr3)) {
			String billingAccount = tempAddr3.getCountry();
			if (billingAccount != null && !"".equals(billingAccount)) {
				if (billingAccount.equals("US") || billingAccount.equals("CA")) {
					billAccountCode = "US";
				} else {
					billAccountCode = "HK";
				}
			}
		}
		customerDetail.setBillAccountCode(billAccountCode);
		SessionUtil.updateRow(SessionConstant.CustAddressList.value(),
				sessCustNo, addrList);
	}

	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public CustAddrOperDTO newAddress(String type) {
		CustAddrOperDTO tempAddr = new CustAddrOperDTO();
		tempAddr.setAddrClass("Commercial");
		tempAddr.setAddrType(type);
		tempAddr.setOperateType(OperationType.ADD.value());
		tempAddr.setDefaultFlag("Y");
		tempAddr.setNamePfx(customerDetail.getNamePfx());
		tempAddr.setFirstName(customerDetail.getFirstName());
		tempAddr.setMidName(customerDetail.getMidName());
		tempAddr.setLastName(customerDetail.getLastName());
		tempAddr.setNameSfx(customerDetail.getNameSfx());
		// tempAddr.setOrganization(customerDetail.getOrganization());
		tempAddr.setOrgId(customerDetail.getOrgId());
		tempAddr.setBusPhone(customerDetail.getBusPhone());
		tempAddr.setBusPhoneExt(customerDetail.getBusPhoneExt());
		tempAddr.setAltPhone(customerDetail.getAltPhone());
		tempAddr.setAltPhoneExt(customerDetail.getAltPhoneExt());
		tempAddr.setFax(customerDetail.getFax());
		tempAddr.setFaxExt(customerDetail.getFaxExt());
		tempAddr.setBusEmail(customerDetail.getBusEmail());
		tempAddr.setAddrLine1(customerDetail.getAddrLine1());
		tempAddr.setAddrLine2(customerDetail.getAddrLine2());
		tempAddr.setAddrLine3(customerDetail.getAddrLine3());
		tempAddr.setCity(customerDetail.getCity());
		tempAddr.setState(customerDetail.getState());
		tempAddr.setCountry(customerDetail.getCountry());
		tempAddr.setMobile(customerDetail.getMobile());
		tempAddr.setPersonalEmail(customerDetail.getPersonalEmail());
		tempAddr.setAltBusEmail(customerDetail.getAltBusEmail());
		tempAddr.setTitle(customerDetail.getTitle());
		tempAddr.setZipCode(customerDetail.getZipCode());
		tempAddr.setEffFrom(new Date());
		tempAddr.setEffTo(new Date());
		tempAddr.setAddrClass("Commercial");
		tempAddr.setStatus("ACTIVE");
		tempAddr.setOrgName(customerDetail.getOrganization().getName()); // 添加orgName
		return tempAddr;

	}

	/**
	 * 根据cusNo删除所属的session数据
	 * 
	 * @param sessCustNo
	 */
	private void clearSession(String sessCustNo) {
		SessionUtil.deleteRow(SessionConstant.CustContact.value(), sessCustNo);
		SessionUtil.deleteRow(SessionConstant.CustInterestList.value(),
				sessCustNo);
		SessionUtil.deleteRow(SessionConstant.CustPubList.value(), sessCustNo);
		SessionUtil
				.deleteRow(SessionConstant.CustGrantList.value(), sessCustNo);
		SessionUtil.deleteRow(SessionConstant.CustNoteList.value(), sessCustNo);
		SessionUtil
				.deleteRow(SessionConstant.CustPriceList.value(), sessCustNo);
		SessionUtil.deleteRow(SessionConstant.CustAddressList.value(),
				sessCustNo);
		SessionUtil.deleteRow(SessionConstant.CustCaseList.value(), sessCustNo);
		SessionUtil.deleteRow(SessionConstant.PersonalInfo.value(), sessCustNo);
	}

	/**
	 * 总体保存中关联保存customer contact列表
	 * 
	 * @return
	 */
	private void attachCustContact() {
		// Map<String, Object> custContactList = SessionUtil
		// .getAllRows("custContact");
		Map<String, Object> custContactList = null;
		// 取得session中的contacts
		System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
		custContactList = SessionUtil.getRow(
				SessionConstant.CustContact.value(), custNo, sessCustNo);
		System.out.println("custContactList: " + custContactList);
		// Map<String, Object> contactMap;
		Map<String, Object> sessMap = custContactList;
		List<ContactDTO> contactList = new ArrayList<ContactDTO>();
		List<Integer> delCntctIdList = new ArrayList<Integer>();
		if (custContactList != null && !custContactList.isEmpty()) {
			// contactMap = new LinkedHashMap<String, Object>();
			for (Iterator<String> iter = custContactList.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				ContactDTO val = (ContactDTO) custContactList.get(key);
				System.out.println("key: " + key + " , val: " + val);
				if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.DEL)) {
					delCntctIdList.add(Integer.parseInt(key));
					// contactList.remove(key);
					iter.remove();
					sessMap.remove(key);
				} else if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.ADD)) {
					contactList.add((ContactDTO) val);
					iter.remove();
					sessMap.remove(key);
				} else if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.EDIT)) {
					contactList.add((ContactDTO) val);
					iter.remove();
					sessMap.remove(key);
				}
			}

		}
		System.out.println("delCntctIdList: " + delCntctIdList);
		System.out.println("contactList: " + contactList);
		customerDetail.setContactList(contactList);
		customerDetail.setDelContactsIdList(delCntctIdList);
	}

	/**
	 * 总体保存中关联保存customer publication列表
	 * 
	 * @return
	 */
	private void attachCustPublications() {
		Map<String, Object> custPubList = null;
		// 取得session中的contacts
		System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
		custPubList = SessionUtil.getRow(SessionConstant.CustPubList.value(),
				custNo, sessCustNo);
		System.out.println("custContactList: " + custPubList);
		Map<String, Object> sessMap = custPubList;
		List<CustomerPublications> pubList = new ArrayList<CustomerPublications>();
		List<Integer> delPubIdList = new ArrayList<Integer>();
		if (custPubList != null && !custPubList.isEmpty()) {
			for (Iterator<String> iter = custPubList.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				CustPubsDTO val = (CustPubsDTO) custPubList.get(key);
				System.out.println("key: " + key + " , val: " + val);
				if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.DEL)) {
					delPubIdList.add(Integer.parseInt(key));
					iter.remove();
					sessMap.remove(key);
				} else if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.ADD)) {
					pubList.add(dozer.map(val, CustomerPublications.class));
					iter.remove();
					sessMap.remove(key);
				} else if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.EDIT)) {
					pubList.add(dozer.map(val, CustomerPublications.class));
					iter.remove();
					sessMap.remove(key);
				}
			}

		}
		System.out.println("delPubIdList: " + delPubIdList);
		System.out.println("pubList: " + pubList);
		customerDetail.setCustPubList(pubList);
		customerDetail.setDelCustPubIdList(delPubIdList);
	}

	/**
	 * 总体保存中关联保存customer publication列表
	 * 
	 * @return
	 */
	private void attachCustGrants() {
		Map<String, Object> custGrantList = null;
		// 取得session中的contacts
		System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
		custGrantList = SessionUtil.getRow(
				SessionConstant.CustGrantList.value(), custNo, sessCustNo);
		System.out.println("custContactList: " + custGrantList);
		Map<String, Object> sessMap = custGrantList;
		List<CustomerGrants> grantList = new ArrayList<CustomerGrants>();
		List<Integer> delGrantIdList = new ArrayList<Integer>();
		if (custGrantList != null && !custGrantList.isEmpty()) {
			for (Iterator<String> iter = custGrantList.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				CustGrantsDTO val = (CustGrantsDTO) custGrantList.get(key);
				System.out.println("key: " + key + " , val: " + val);
				if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.DEL)) {
					delGrantIdList.add(Integer.parseInt(key));
					iter.remove();
					sessMap.remove(key);
				} else if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.ADD)) {
					grantList.add(dozer.map(val, CustomerGrants.class));
					iter.remove();
					sessMap.remove(key);
				} else if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.EDIT)) {
					grantList.add(dozer.map(val, CustomerGrants.class));
					iter.remove();
					sessMap.remove(key);
				}
			}

		}
		System.out.println("delGrantIdList: " + delGrantIdList);
		System.out.println("grantList: " + grantList);
		customerDetail.setCustGrantsList(grantList);
		customerDetail.setDelCustGrantsIdList(delGrantIdList);
	}

	/**
	 * 总体保存中关联保存customer Interest Area List列表
	 * 
	 * @return
	 */
	private void attachCustInterest() {
		Map<String, Object> custAreaInts = null;
		// 取得session中的contacts
		System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
		custAreaInts = SessionUtil.getRow(
				SessionConstant.CustInterestList.value(), custNo, sessCustNo);
		System.out.println("custAreaInts: " + custAreaInts);
		Map<String, Object> sessMap = custAreaInts;
		List<CustomerInterest> customerInterestList = new ArrayList<CustomerInterest>();
		List<Integer> delAreaIdList = new ArrayList<Integer>();
		if (custAreaInts != null && !custAreaInts.isEmpty()) {
			for (Iterator<String> iter = custAreaInts.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				CustomerInterestDTO val = (CustomerInterestDTO) custAreaInts
						.get(key);
				System.out.println("key: " + key + " , val: " + val);
				if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.DEL)) {
					delAreaIdList.add(Integer.parseInt(key));
					// contactList.remove(key);
					iter.remove();
					sessMap.remove(key);
				} else if (val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.ADD)) {
					CustomerInterest customerInterest = dozer.map(val,
							CustomerInterest.class);
					customerInterestList.add(customerInterest);
					iter.remove();
					sessMap.remove(key);
				}
			}

		}
		System.out.println("delAreaIdList: " + delAreaIdList);
		System.out.println("customerInterestList: " + customerInterestList);
		customerDetail.setCustInterestList(customerInterestList);
		customerDetail.setDelCustIntIdList(delAreaIdList);
	}

	/**
	 * 显示活动信息
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String viewActivitys() throws Exception { return "view_activity";
	 * }
	 */

	/**
	 * @return
	 * @throws Exception
	 * @author zouyulu 显示增加Accounting Note
	 */
	public String showInstruction() throws Exception {
		if (!StringUtils.isEmpty(sessNoteId)) {
			noteDTO = (CustNoteDTO) SessionUtil.getOneRow(
					SessionConstant.CustNoteList.toString(), sessCustNo,
					sessNoteId);
		}
		return "show_instruction";
	}

	/**
	 * 保存Instruction note
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveInstruction() throws Exception {
		List<NoteDocument> newNoteDocumentList = new ArrayList<NoteDocument>();
		List<Integer> newDelDocIdList = null;
		List<NoteDocument> noteDocumentList = null;
		if (StringUtils.isEmpty(sessNoteId)) {
			// 新建
			sessNoteId = SessionUtil.generateTempId();
			noteDocumentList = new ArrayList<NoteDocument>();
		} else {
			// 编辑
			CustNoteDTO sessNoteDTO = (CustNoteDTO) SessionUtil.getOneRow(
					SessionConstant.CustNoteList.toString(), sessCustNo,
					sessNoteId);
			noteDocumentList = sessNoteDTO.getDocumentList();
			newDelDocIdList = sessNoteDTO.getDelDocIdList();
		}
		if (newDelDocIdList == null) {
			newDelDocIdList = new ArrayList<Integer>();
		}
		if (noteDocumentList != null && !noteDocumentList.isEmpty()) {
			List<String> tmpArr2 = new ArrayList<String>();
			if (!StringUtils.isEmpty(docDelIndexs)) {
				String[] tmpArr = StringUtils.split(docDelIndexs, ",");
				for (String s : tmpArr) {
					tmpArr2.add(s);
				}
			}
			for (int i = 0; i < noteDocumentList.size(); i++) {
				if (tmpArr2.contains(Integer.toString(i))) {
					if (noteDocumentList.get(i).getDocId() != null
							&& noteDocumentList.get(i).getDocId() > 0) {
						newDelDocIdList.add(noteDocumentList.get(i).getDocId());
					}
					continue;
				} else {
					newNoteDocumentList.add(noteDocumentList.get(i));
				}
			}
		}
		if (upload != null && !upload.isEmpty()) {
			for (int i = 0; i < upload.size(); i++) {
				NoteDocument nd = new NoteDocument();
				String srcFileName = uploadFileName.get(i);
				uploadFileName.set(i, SessionUtil.generateTempId()
						+ srcFileName.substring(srcFileName.lastIndexOf(".")));
				nd.setFilePath("accounting_notes/" + uploadFileName.get(i));
				nd.setDocName(srcFileName);
				newNoteDocumentList.add(nd);
			}
		}
		noteDTO.setDocumentList(newNoteDocumentList);
		noteDTO.setDelDocIdList(newDelDocIdList);
		SessionUtil.updateOneRow(SessionConstant.CustNoteList.toString(),
				sessCustNo, sessNoteId, noteDTO);
		fileService.uploadFile(upload, uploadContentType, uploadFileName,
				"accounting_notes");
		Struts2Util.renderText("<pre>" + sessNoteId + "</pre>");// ajaxForm方式提交返回需要用html标记包含进去.
		return null;
	}

	/**
	 * 保存credit
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveCredit() throws Exception {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Integer ccId = customerService.saveCreditCard(this.creditCard,
				SessionUtil.getUserId());
		retMap.put("ccId", ccId);
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 删除credit
	 * 
	 * @return
	 * @throws Exception
	 */
	public String removeCredit() throws Exception {
		customerService.delCreditCard(this.creditCardId,
				SessionUtil.getUserId());
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	/**
	 * 总体保存CustNoteList 列表
	 * 
	 * @author zouyulu
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void attachCustNoteList() {
		Map<String, CustNoteDTO> custNoteMap = null;
		List rsNoteList = new ArrayList<CustNoteDTO>();
		custNoteMap = (Map<String, CustNoteDTO>) SessionUtil.getRow(
				SessionConstant.CustNoteList.toString(), sessCustNo);
		if (custNoteMap != null) {
			Iterator<Entry<String, CustNoteDTO>> it = custNoteMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry entry = it.next();
				CustNoteDTO tmpDTO = (CustNoteDTO) entry.getValue();
				if (tmpDTO.getId() == 0) {
					tmpDTO.setId(null);
				}
				rsNoteList.add(tmpDTO);
			}
		}
		if (!rsNoteList.isEmpty()) {
			customerDetail.setCustNoteList(rsNoteList);
		}
	}

	/**
	 * 总体保存special price list
	 * 
	 * @author zouyulu
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void attachCustSpclPrcList() {
		Map<String, CustSpecialPriceDTO> priceMap = null;
		List rsPriceList = new ArrayList<CustSpecialPriceDTO>();
		List<Integer> delSpecialPriceIdList = new ArrayList<Integer>();
		priceMap = (Map<String, CustSpecialPriceDTO>) SessionUtil.getRow(
				SessionConstant.CustPriceList.value(), sessCustNo);
		if (priceMap != null) {
			Iterator<Entry<String, CustSpecialPriceDTO>> it = priceMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, CustSpecialPriceDTO> entry = it.next();
				CustSpecialPriceDTO tmpDTO = (CustSpecialPriceDTO) entry
						.getValue();
				if (tmpDTO == null && StringUtils.isNumeric(entry.getKey())) {
					delSpecialPriceIdList.add(Integer.parseInt(entry.getKey()));
				} else {
					if (tmpDTO.getPriceId() != null && tmpDTO.getPriceId() == 0) {
						tmpDTO.setPriceId(null);
					}
					if (tmpDTO != null) {
						rsPriceList.add(tmpDTO);
					}
				}
			}
		}
		if (!delSpecialPriceIdList.isEmpty()) {
			customerDetail.setDelSpecialPriceIdList(delSpecialPriceIdList);
		}
		if (!rsPriceList.isEmpty()) {
			customerDetail.setCustSpecialPriceList(rsPriceList);
		}
	}

	/**
	 * 总体保存customer case list 注意customre case 没有删除。
	 * 
	 * @author zouyulu
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void attachCustCaseList() {
		Map<String, CustomerCase> caseMap = null;
		List rsCaseList = new ArrayList<CustomerCase>();
		List<Integer> delCaseIdList = new ArrayList<Integer>();
		caseMap = (Map<String, CustomerCase>) SessionUtil.getRow(
				SessionConstant.CustCaseList.value(), sessCustNo);
		if (caseMap != null) {
			Iterator<Entry<String, CustomerCase>> it = caseMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, CustomerCase> entry = it.next();
				CustomerCase tmpDTO = (CustomerCase) entry.getValue();
				if (tmpDTO == null && StringUtils.isNumeric(entry.getKey())) {
					delCaseIdList.add(Integer.parseInt(entry.getKey()));

				} else {
					if (tmpDTO.getCaseId() != null && tmpDTO.getCaseId() == 0) {
						tmpDTO.setCaseId(null);
					}
					sendAllEmails(tmpDTO);
					rsCaseList.add(tmpDTO);
				}
			}
		}
		if (!delCaseIdList.isEmpty()) {
			// @todo
		}
		if (!rsCaseList.isEmpty()) {
			customerDetail.setCustCaseList(rsCaseList);
		}
	}

	public void sendAllEmails(CustomerCase tmpDTO) {
		System.out.println(tmpDTO.getSendEmailTo());
		String[] egs = null;
		System.out.println(" orderNo===" + tmpDTO.getOrderNo());
		System.out.println(" CatalogNo===" + tmpDTO.getCatalogNo());
		String subject = "subject:" + tmpDTO.getSubject() + ":"
				+ tmpDTO.getOrderNo() + "---------" + tmpDTO.getCatalogNo();
		String content = "Order. No :" + tmpDTO.getOrderNo() + "/n  Cat. No. "
				+ tmpDTO.getCatalogNo() + "/n Description:"
				+ tmpDTO.getDescription();
		String from = "support@genscript.com";

		if (tmpDTO.getSendEmailTo() != null
				&& !"".equals(tmpDTO.getSendEmailTo())) {
			// send mail to all emails...
			EmailGroup eg = emailGroupService.findById(Integer.parseInt(tmpDTO
					.getSendEmailTo()));
			if (eg != null) {
				System.out.println(eg);
				if (eg.getGroupAddress() != null) {
					String groupAddress = eg.getGroupAddress();
					egs = groupAddress.split(";");
					int l = egs.length;
					if (l >= 1) {
						for (int i = 0; i < l; i++) {
							System.out.println(egs[i]);
							mimeMailService.sendMail(egs[i], subject, content,
									from);
						}
					}
				}

			}
		}
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	/**
	 * 删除Customer并返回删除的个数和删除成功的个数
	 * 
	 * @return delTotal, delSuccessCount
	 * @throws Exception
	 */
	public String delCust() throws Exception {
		String delIdStr = ServletActionContext.getRequest().getParameter(
				"custNos");
		System.out.println("delIdStr: " + delIdStr);
		if (StringUtils.isNotEmpty(delIdStr)) {
			String[] customerNoArray = delIdStr.split(",");
			System.out.println("strs: " + customerNoArray);
			List<String> delIdList = Arrays.asList(customerNoArray);
			System.out.println("delIdList: " + delIdList);
			int delSuccessCount = 0;
			int delTotal = 0;
			if (customerNoArray != null) {
				delTotal = customerNoArray.length;

			}
			for (String customerNo : customerNoArray) {
				Integer custNo = Integer.valueOf(customerNo);
				delSuccessCount += customerService.delCustomer(
						SessionUtil.getUserId(), custNo);
			}
			if (delTotal == delSuccessCount && delTotal == delIdList.size()) {
				Struts2Util.renderText("success");
			} else {
				Struts2Util.renderText("error");
			}
		}
		return NONE;
	}

	/**
	 * 显示积分兑换优惠券页面
	 * 
	 * @return
	 */
	public String showRedeemPoint() {
		try {
			this.customerService.searchCustomerAvailableCard();
		} catch (Exception ex) {
		}
		return "show_redeem_point";
	}

	public String showAmazonPointHistory() {
		try {
			String paramCustNo = ServletActionContext.getRequest()
					.getParameter("custNo");
			if (paramCustNo != null && !"".equals(paramCustNo)) {
				amazonhistoryPage = this.customerService
						.searchCustomerAllHistory(amazonhistoryPage,
								Integer.parseInt(paramCustNo));
			}
			// 把分页相关数据放入request作用域内
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					amazonhistoryPage);
		} catch (Exception ex) {
		}
		return "show_amazon_point_history";
	}

	public String showRedeemPointHistory() {
		try {
			String paramCustNo = ServletActionContext.getRequest()
					.getParameter("custNo");
			String p_no = ServletActionContext.getRequest()
					.getParameter("p_no");
			redeemPage = this.customerService
					.searchCustomerRedeemCouponHistory(redeemPage, paramCustNo,
							p_no);
			// 把分页相关数据放入request作用域内
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					redeemPage);
		} catch (Exception ex) {
		}
		return "show_redeem_point_history";
	}

	/**
	 * 点击兑换进入下一步，将兑换内容保存到session中
	 * 
	 * @return
	 * @author zhangyong
	 */
	public String nextRedeemPoint() {

		try {
			boolean status = this.customerService.searchGiftCardByCardId();
			if (status) {
				return "next_redeem_point";
			}
		} catch (Exception ex) {
			ServletActionContext.getRequest().setAttribute("message",
					"Data anomalies, please re-exchange!");
		}
		return "show_redeem_point";
	}

	/**
	 * 保存兑换
	 * 
	 * @return
	 * @author zhangyong
	 */
	public String saveRedeemPoint() {
		try {
			boolean status = this.customerService.saveRedeemPoint();
			if (status) {
				return "success_redeem_point";
			}
		} catch (Exception ex) {
			ServletActionContext.getRequest().setAttribute("message",
					"Data anomalies, please re-exchange!");
			return "show_redeem_point";
		}
		return "show_redeem_point";
	}

	/**
	 * 校验Customer是否在黑名单中
	 * 
	 * @author Zhang Yong
	 * @return
	 */
	public String checkCustomer() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String message = null;
		try {
			message = customerService.checkCustomer(custNo);
			retMap.put("message", message);
		} catch (Exception ex) {
			retMap.put("message", "failure");
		}
		Struts2Util.renderJson(retMap);
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "edit";
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public CustomerDTO getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDTO customerDetail) {
		this.customerDetail = customerDetail;
	}

	public void setSessCustNo(String sessCustNo) {
		this.sessCustNo = sessCustNo;
	}

	public String getSessCustNo() {
		return sessCustNo;
	}

	public void setDropDownList(
			Map<String, List<PbDropdownListOptions>> dropDownList) {
		this.dropDownList = dropDownList;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownList() {
		return dropDownList;
	}

	public NamePfx[] getNamePfxList() {
		return namePfxList;
	}

	public void setNamePfxList(NamePfx[] namePfxList) {
		this.namePfxList = namePfxList;
	}

	public void setNameSfxList(NameSfx[] nameSfxList) {
		this.nameSfxList = nameSfxList;
	}

	public NameSfx[] getNameSfxList() {
		return nameSfxList;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
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

	public void setContactStatusList(ContactStatusType[] contactStatusList) {
		this.contactStatusList = contactStatusList;
	}

	public ContactStatusType[] getContactStatusList() {
		return contactStatusList;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public List<CustNoteDTO> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<CustNoteDTO> noteList) {
		this.noteList = noteList;
	}

	public CustNoteDTO getNoteDTO() {
		return noteDTO;
	}

	public void setNoteDTO(CustNoteDTO noteDTO) {
		this.noteDTO = noteDTO;
	}

	public Map<String, String> getNoteJsonMap() {
		return noteJsonMap;
	}

	public void setNoteJsonMap(Map<String, String> noteJsonMap) {
		this.noteJsonMap = noteJsonMap;
	}

	public String getSessNoteId() {
		return sessNoteId;
	}

	public void setSessNoteId(String sessNoteId) {
		this.sessNoteId = sessNoteId;
	}

	public List<CustNoteDTO> getCustNoteList() {
		return custNoteList;
	}

	public void setCustNoteList(List<CustNoteDTO> custNoteList) {
		this.custNoteList = custNoteList;
	}

	public Integer getDelSuccessCount() {
		return delSuccessCount;
	}

	public Integer getDelTotal() {
		return delTotal;
	}

	public List<DropDownListDTO> getSpecDropDownist() {
		return specDropDownist;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDocDelIndexs() {
		return docDelIndexs;
	}

	public void setDocDelIndexs(String docDelIndexs) {
		this.docDelIndexs = docDelIndexs;
	}

	public int getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(int defaultTab) {
		this.defaultTab = defaultTab;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}

	public List<CreditCard> getCardList() {
		return cardList;
	}

	public void setCardList(List<CreditCard> cardList) {
		this.cardList = cardList;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public List<PbDropdownListOptions> getCardTypeList() {
		return cardTypeList;
	}

	public void setCardTypeList(List<PbDropdownListOptions> cardTypeList) {
		this.cardTypeList = cardTypeList;
	}

	public Integer getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(Integer creditCardId) {
		this.creditCardId = creditCardId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassW() {
		return confirmPassW;
	}

	public void setConfirmPassW(String confirmPassW) {
		this.confirmPassW = confirmPassW;
	}

	public Page<RedeemHistoryDTO> getRedeemPage() {
		return redeemPage;
	}

	public void setRedeemPage(Page<RedeemHistoryDTO> redeemPage) {
		this.redeemPage = redeemPage;
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

	public Integer getSalesContact() {
		return salesContact;
	}

	public void setSalesContact(Integer salesContact) {
		this.salesContact = salesContact;
	}

	public Integer getTechSupport() {
		return techSupport;
	}

	public void setTechSupport(Integer techSupport) {
		this.techSupport = techSupport;
	}

	public String getDept_func() {
		return dept_func;
	}

	public void setDept_func(String dept_func) {
		this.dept_func = dept_func;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
