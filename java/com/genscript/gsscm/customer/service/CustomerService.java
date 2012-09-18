package com.genscript.gsscm.customer.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.customer.entity.*;
import com.genscript.gsscm.report.dao.ReportDao;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dozer.DozerBeanMapper;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.ContactMethod;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.constant.SalesRepSalesRole;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.events.NewCustomerEvent;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionEmergeUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.contact.dao.ContactDao;
import com.genscript.gsscm.contact.dto.ContactActivity;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.contact.entity.Contact;
import com.genscript.gsscm.customer.dao.AddressDao;
import com.genscript.gsscm.customer.dao.CouponDao;
import com.genscript.gsscm.customer.dao.CreditCardDao;
import com.genscript.gsscm.customer.dao.CreditRatingDao;
import com.genscript.gsscm.customer.dao.CustContactHistDao;
import com.genscript.gsscm.customer.dao.CustInfoStatDao;
import com.genscript.gsscm.customer.dao.CustPersInfoDao;
import com.genscript.gsscm.customer.dao.CustomerBeanDao;
import com.genscript.gsscm.customer.dao.CustomerCaseDao;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerGrantsDao;
import com.genscript.gsscm.customer.dao.CustomerInterestDao;
import com.genscript.gsscm.customer.dao.CustomerNoteDao;
import com.genscript.gsscm.customer.dao.CustomerPointsDao;
import com.genscript.gsscm.customer.dao.CustomerPointsHistoryDao;
import com.genscript.gsscm.customer.dao.CustomerPublicationDao;
import com.genscript.gsscm.customer.dao.CustomerSpecialPriceDao;
import com.genscript.gsscm.customer.dao.CustomerStatusHistoryDao;
import com.genscript.gsscm.customer.dao.DepartmentDao;
import com.genscript.gsscm.customer.dao.DivisionDao;
import com.genscript.gsscm.customer.dao.GiftCardDao;
import com.genscript.gsscm.customer.dao.NoteDocumentDao;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.dao.PaymentTermDao;
import com.genscript.gsscm.customer.dao.RfmRatingDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.dao.SourceDao;
import com.genscript.gsscm.customer.dao.webbehavior.AccessAnalysisDao;
import com.genscript.gsscm.customer.dao.webbehavior.AccessLogDao;
import com.genscript.gsscm.customer.dao.webbehavior.VisitDao;
import com.genscript.gsscm.customer.dto.AccessStatDTO;
import com.genscript.gsscm.customer.dto.AnalysisDTO;
import com.genscript.gsscm.customer.dto.AnalysisReport;
import com.genscript.gsscm.customer.dto.AnalysisReportSrchDTO;
import com.genscript.gsscm.customer.dto.AnalysisSrchDTO;
import com.genscript.gsscm.customer.dto.CouponDTO;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustContactInfoDTO;
import com.genscript.gsscm.customer.dto.CustGrantsDTO;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.dto.CustPubsDTO;
import com.genscript.gsscm.customer.dto.CustSpecialPriceDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.dto.DefaultAddressDTO;
import com.genscript.gsscm.customer.dto.PageViewDTO;
import com.genscript.gsscm.customer.dto.PaymentTermDTO;
import com.genscript.gsscm.customer.dto.RedeemHistoryDTO;
import com.genscript.gsscm.customer.dto.SalesStaticsSrchDTO;
import com.genscript.gsscm.order.dao.DocumentDao;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dto.SalesOrderedDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.CatalogDao;
import com.genscript.gsscm.product.dao.ProductCategoryDao;
import com.genscript.gsscm.product.dto.ProductViewDTO;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.quote.dao.QuoteDao;
import com.genscript.gsscm.serv.dao.ServiceCategoryDao;
import com.genscript.gsscm.serv.dto.ServiceViewDTO;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryDao;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class CustomerService implements ApplicationContextAware {

	@Autowired
	private CustomerBeanDao customerBeanDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private CustContactHistDao custContactHistDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private DivisionDao divisionDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private PaymentTermDao paymentTermDao;
	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;
	@Autowired
	private CustomerPointsDao customerPointsDao;
	@Autowired
	private CustomerPointsHistoryDao customerPointsHistoryDao;
	@Autowired
	private CustomerGrantsDao customerGrantsDao;
	@Autowired
	private CustInfoStatDao custInfoStatDao;
	@Autowired
	private CustomerPublicationDao customerPublicationDao;
	@Autowired
	private CustPersInfoDao custPersInfoDao;
	@Autowired
	private CustomerCaseDao customerCaseDao;
	@Autowired
	private CustomerInterestDao customerInterestDao;
	@Autowired
	private VisitDao visitDao;
	@Autowired
	private AccessAnalysisDao accessAnalysisDao;
	@Autowired
	private AccessLogDao accessLogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ServiceCategoryDao serviceCategoryDao;
	@Autowired
	private NoteDocumentDao noteDocumentDao;
	@Autowired
	private CustomerNoteDao customerNoteDao;
	@Autowired
	private CreditCardDao creditCardDao;
	@Autowired
	private CatalogDao catalogDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private QuoteDao quoteDao;
	@Autowired
	private SourceDao sourceDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private FileService fileService;
	@Autowired
	private CustomerStatusHistoryDao customerStatusHistoryDao;
	@Autowired
	private SalesRepDao salesRepDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private GiftCardDao giftCardDao;
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private CreditRatingDao creditRatingDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RfmRatingDao rfmRatingDao;

	@Autowired
	private BillTerritoryDao billTerritoryDao;

	private static final String WEB_BEHAVIOR_DT = "yyyy-MM-dd HH:mm:ss";
	private ApplicationContext context;

	@Transactional(readOnly = true)
	public Customer findById(Integer custNo) {
		return this.customerDao.getById(custNo);
	}
	@Transactional(readOnly = true)
	public Page<CustomerBean> searchCustomer(Page<CustomerBean> page) {
		Page<CustomerBean> customerList = customerBeanDao.getCustomerList(page);
		return customerList;
	}

	public void saveCustGrants(String sessCustNo, Integer custNo)
			throws Exception {
		SessionEmergeUtil<CustGrantsDTO> sessionEmergeUtil = new SessionEmergeUtil<CustGrantsDTO>();
		Map<String, CustGrantsDTO> custGrants = sessionEmergeUtil
				.convertMap(SessionUtil.getRow(
						SessionConstant.CustGrantList.value(), custNo,
						sessCustNo)); // 获取当前session里面 的 所有的grants
										// 对象然后将其保存至数据库中。
		if (custGrants == null) {
			return;
		}
		for (Map.Entry<String, CustGrantsDTO> entry : custGrants.entrySet()) { // 循环保存所有的对象。
			System.out.println(entry.getKey() + "     " + entry.getValue());
			CustGrantsDTO custdtd = entry.getValue();// 首先进行类型转换..
			custdtd.setCustNo(custNo);
			custdtd.setCreatedBy(SessionUtil.getUserId());
			custdtd.setCreationDate(new Date());
			custdtd.setModifiedBy(SessionUtil.getUserId());
			custdtd.setModifyDate(new Date());
			CustomerGrants customerGrants = dozer.map(custdtd,
					CustomerGrants.class);
			this.customerGrantsDao.save(customerGrants);
		}
		SessionUtil.deleteRow(SessionConstant.CustGrantList.value(),
				String.valueOf(custNo));
	}

	public void saveCustPublishs(String sessCustNo, Integer custNo)
			throws Exception {
		SessionEmergeUtil<CustPubsDTO> sessionEmergeUtil = new SessionEmergeUtil<CustPubsDTO>();
		Map<String, CustPubsDTO> custPublishs = sessionEmergeUtil
				.convertMap(SessionUtil.getRow(
						SessionConstant.CustPubList.value(), custNo, sessCustNo)); // 获取当前session里面
																					// 的
																					// 所有的grants
																					// 对象然后将其保存至数据库中。
		if (custPublishs == null) {
			return;
		}
		for (Map.Entry<String, CustPubsDTO> entry : custPublishs.entrySet()) { // 循环保存所有的对象。
			CustPubsDTO custdtd = entry.getValue();
			custdtd.setCustNo(custNo);
			custdtd.setCreatedBy(SessionUtil.getUserId());
			custdtd.setCreationDate(new Date());
			custdtd.setModifiedBy(SessionUtil.getUserId());
			custdtd.setModifyDate(new Date());
			CustomerPublications customerPw = dozer.map(custdtd,
					CustomerPublications.class);
			this.customerPublicationDao.save(customerPw);
		}

		SessionUtil.deleteRow(SessionConstant.CustPubList.value(),
				String.valueOf(custNo));

	}

	public void saveOrUpdateCustGrants(CustomerGrants customerGrants) {
		this.customerGrantsDao.save(customerGrants);
	}

	@SuppressWarnings("unused")
	private void saveOrUpdateCustPubs(CustomerPublications customerPublications) {
		this.customerPublicationDao.save(customerPublications);
	}

	public CustomerContactHistory getContactHistory(Integer custNo,
			String method) {

		return this.custContactHistDao.getCustomerContactHistoryByCustNo(
				custNo, method);
	}

	@Transactional(readOnly = true)
	public Page<CustomerBean> searchCustomer(Page<CustomerBean> page,
			List<PropertyFilter> filters) throws Exception {
		String userName = SessionUtil.getUserName();
		// 判断当前用户是否含有销售经理角色
		boolean salesManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_SALES_MANAGER);
		if (Constants.USERNAME_ADMIN.equals(userName) || salesManager) {
			page = customerBeanDao.findPage(page, filters);
			return page;
		}
		// 判断当前用户是否含有内部订单管理员角色，是，则查询自己部门的customer
		boolean internalOrderManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_INTERNAL_ORDER_MANAGER);
		List<Criterion> criterionList = new ArrayList<Criterion>();
		if (internalOrderManager) {
			if (filters == null || filters.isEmpty()) {
				filters = new ArrayList<PropertyFilter>();
			}
			filters.add(new PropertyFilter("EQS_custType",
					Constants.INTERNAL_TYPE_CUSTOMER));
		} else {
			Map<String, Object> salesRoleAndUserIdsMap = salesRepDao
					.getSameGroupUser();
			if (salesRoleAndUserIdsMap.get("function") != null
					&& salesRoleAndUserIdsMap.get("User_Ids") != null) {
				String salesRole = salesRoleAndUserIdsMap.get("function")
						.toString();
				Integer[] userIdsArr = (Integer[]) salesRoleAndUserIdsMap
						.get("User_Ids");
				if (SalesRepSalesRole.SALES_CONTACT.value().equals(salesRole)) {
					Criterion criterionSalesContact = Restrictions.in(
							"salesContactId", userIdsArr);
					criterionList.add(criterionSalesContact);
				} else if (SalesRepSalesRole.TECH_SUPPORT.value().equals(
						salesRole)) {
					Criterion criterionSalesContact = Restrictions.in(
							"techSupportId", userIdsArr);
					criterionList.add(criterionSalesContact);
				}
			}
		}
		page = customerBeanDao.searchCustomer(page, criterionList, filters);
		return page;
	}

	@Transactional(readOnly = true)
	public Page<CustomerBean> searchCustomerByFilter(Page<CustomerBean> page,
			List<PropertyFilter> filters) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		if (filters != null && filters.size() > 0) {
			for (int i = 0; i < filters.size(); i++) {
				String filterName = filters.get(i).getPropertyName();
				Object filterValue = filters.get(i).getPropertyValue();
				if (("firstName").equals(filterName)) {
					filterMap.put("firstName", filterValue);
					filterMap.put("midName", filterValue);
					filterMap.put("lastName", filterValue);
				} else {
					filterMap.put(filterName, filterValue);
				}
			}
		}
		Page<CustomerBean> customerList = customerBeanDao
				.searchCustomerByFilter(page, filterMap);
		return customerList;
	}

	@Transactional(readOnly = true)
	public Page<CustomerBean> searchCustomer(Page<CustomerBean> page,
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
		return customerBeanDao.findPage(page, filterList);
	}

	public int delCustomer(Integer userId, Integer customerNo) {
		return customerDao.delete(userId, customerNo);
	}

	public void saveOrUpdateCust(Customer customer) {
		String paymentCurrency = customer.getPaymentCurrency();
		String priceCatalogId = customer.getPriceCatalogId();
		if (StringUtils.isEmpty(priceCatalogId)) {
			Catalog baseCatalog = catalogDao.getBaseCatalog();
			String catalogCurrency = baseCatalog.getCurrencyCode();
			if (!catalogCurrency.equalsIgnoreCase(paymentCurrency)) {
				throw new BussinessException(
						BussinessException.CUST_SP_CATALOGID_DIS_CODE);
			}
		} else {
			Catalog catalog = catalogDao.findUniqueBy("catalogId",
					priceCatalogId);
			String catalogCurrency = catalog.getCurrencyCode();
			if (!catalogCurrency.equalsIgnoreCase(paymentCurrency)) {
				throw new BussinessException(
						BussinessException.CUST_SP_CATALOGID_DIS_CODE);
			}

		}
		Integer custNo = customer.getCustNo();
		Date now = new Date();
		customer.setModifyDate(now);
		if (custNo == null || custNo.intValue() == 0) {
			customer.setCustNo(null);
			customer.setCreatedBy(customer.getModifiedBy());
			customer.setCreationDate(now);
		}
		if (customer.getBstCallTimeFrom() != null
				&& customer.getBstCallTimeFrom().trim().length() < 1) {
			customer.setBstCallTimeFrom(null);
		}
		if (customer.getBstCallTimeTo() != null
				&& customer.getBstCallTimeTo().trim().length() < 1) {
			customer.setBstCallTimeTo(null);
		}

		// 新增customer时，将Contact与Customer具有相同的Bussiness
		// email的Contact的status状态设置为inactive
		if (customer.getCustNo() == null) {
			List<Contact> contactList = this.contactDao
					.findContactByBusEmail(customer.getBusEmail());
			if (contactList != null && contactList.size() > 0) {
				for (Contact contact : contactList) {
					contact.setStatus(StatusType.INACTIVE.value());
					contact.setModifyDate(customer.getCreationDate());
					contact.setModifiedBy(customer.getCreatedBy());
					this.contactDao.save(contact);
				}
			}
		}
		this.customerDao.save(customer);

	}

	/*
	 * private Organization attachOrg(Customer customer, Organization org) { if
	 * (org == null) { return null; } boolean bNew = org.getOrgId() == null ||
	 * (org.getOrgId().intValue() == 0); if (bNew && (org.getName() == null ||
	 * org.getName().trim().length() < 1)) { return org; } Integer id =
	 * org.getOrgId(); Date now = new Date(); org.setModifyDate(now);
	 * org.setModifiedBy(customer.getModifiedBy()); if (id == null ||
	 * id.intValue() == 0) { org.setOrgId(null);
	 * org.setCreatedBy(customer.getModifiedBy()); org.setCreationDate(now); }
	 * this.organizationDao.save(org); customer.setOrgId(org.getOrgId()); return
	 * org; }
	 */

	private Divisions attachDivision(Customer customer, Divisions div) {
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
		div.setModifiedBy(customer.getModifiedBy());
		if (div.getOrgId() != null && div.getOrgId().intValue() == 0) {
			div.setOrgId(null);
		}
		if (id == null || id.intValue() == 0) {// new
			div.setDivisionId(null);
			div.setCreatedBy(customer.getModifiedBy());
			div.setCreationDate(now);
		}
		this.divisionDao.save(div);
		customer.setDivisionId(div.getDivisionId());
		return div;
	}

	private Departments attachDept(Customer customer, Departments dept) {
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
		// System.out.println(dept.getDeptFuncId() +
		// "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		dept.setModifiedBy(customer.getModifiedBy());
		dept.setDeptFuncId(dept.getDeptFuncId());
		if (dept.getOrgId() != null && dept.getOrgId().intValue() == 0) {
			dept.setOrgId(null);
		}
		if (dept.getDivisionId() != null
				&& dept.getDivisionId().intValue() == 0) {
			dept.setDivisionId(null);
		}
		if (id == null || id.intValue() == 0) {
			dept.setDeptId(null);
			dept.setCreatedBy(customer.getModifiedBy());
			dept.setCreationDate(now);
		}
		this.departmentDao.save(dept);
		customer.setDeptId(dept.getDeptId());
		return dept;
	}

	private void attachAddress(Customer customer,
			List<CustAddrOperDTO> addrDTOList, List<Integer> delAddrIdList) {
		if (delAddrIdList != null && delAddrIdList.get(0) != null) {
			this.addressDao.delAddrList(delAddrIdList);
		}
		if (addrDTOList == null || addrDTOList.get(0) == null) {
			return;
		}
		Date now = new Date();
		Integer custNo = customer.getCustNo();
		for (CustAddrOperDTO dto : addrDTOList) {
			Address addr = dozer.map(dto, Address.class);
			if (addr.getAddrId() == null || addr.getAddrId().intValue() == 0) {
				addr.setAddrId(null);
				addr.setCreatedBy(customer.getModifiedBy());
				addr.setCreationDate(now);
			}
			// addr.setOrgId(orgId);
			// addr.setOrgName(orgName);
			/*
			 * Organization org = this.saveAddrOrg(addr.getOrganization(),
			 * customer.getModifiedBy()); if (org == null || org.getOrgId() ==
			 * null || org.getOrgId().intValue() == 0) {
			 * addr.setOrganization(null); } else { addr.setOrganization(org); }
			 */
			addr.setModifyDate(now);
			addr.setModifiedBy(customer.getModifiedBy());
			addr.setCustNo(custNo);
			addressDao.save(addr);
		}
	}

	/**
	 * 保存Customer的同时保存CustomerNotes.及其关联的多个document.
	 * 
	 * @param customer
	 * @param dtoList
	 * @return
	 */
	private List<Integer> attachNote(Customer customer,
			List<CustNoteDTO> dtoList) {
		List<Integer> sendMailIdList = new ArrayList<Integer>();
		if (dtoList == null || dtoList.isEmpty()) {
			return sendMailIdList;
		}
		Date now = new Date();
		for (CustNoteDTO dto : dtoList) {
			if (dto.getDelDocIdList() != null
					&& !dto.getDelDocIdList().isEmpty()) {
				this.noteDocumentDao.delDocList(dto.getDelDocIdList());
			}

			CustomerNote note = this.dozer.map(dto, CustomerNote.class);
			note.setCustNo(customer.getCustNo());
			note.setCreatedBy(customer.getModifiedBy());
			note.setModifiedBy(customer.getModifiedBy());
			note.setCreationDate(now);
			note.setModifyDate(now);
			this.customerNoteDao.save(note);
			if (dto.getDocumentList() != null
					&& !dto.getDocumentList().isEmpty()) {
				for (NoteDocument doc : dto.getDocumentList()) {
					doc.setCreatedBy(customer.getModifiedBy());
					doc.setModifiedBy(customer.getModifiedBy());
					doc.setCreationDate(now);
					doc.setModifyDate(now);
					doc.setRefId(note.getId());
					doc.setRefType(DocumentType.CUSTOMER_NOTE_TYPE.value());
					this.noteDocumentDao.save(doc);
				}
			}

		}
		return sendMailIdList;
	}

	@SuppressWarnings("unused")
	private Organization saveAddrOrg(Organization addrOrg, Integer userId) {
		if (addrOrg == null) {
			return null;
		}
		if (addrOrg.getOrgId() != null && addrOrg.getOrgId().intValue() > 0) {
			Organization dbOrg = new Organization();
			dbOrg.setOrgId(addrOrg.getOrgId());
			return dbOrg;
		}
		addrOrg.setOrgId(null);
		Date now = new Date();
		addrOrg.setModifyDate(now);
		addrOrg.setModifiedBy(userId);
		addrOrg.setCreatedBy(userId);
		addrOrg.setCreationDate(now);
		this.organizationDao.save(addrOrg);
		return addrOrg;
	}

	private void attachContact(Customer customer,
			List<ContactDTO> contactDTOList, List<Integer> delContactsIdList)
			throws ParseException {
		if (delContactsIdList != null && !delContactsIdList.isEmpty()) {
			this.custContactHistDao.delContactsList(delContactsIdList);
		}
		if (contactDTOList == null || contactDTOList.isEmpty()) {
			return;
		}
		Date now = new Date();
		Integer custNo = customer.getCustNo();
		for (ContactDTO dto : contactDTOList) {
			if (dto.getCallTime() == null
					|| dto.getCallTime().trim().length() < 1) {
				dto.setCallTime(null);
			}
			CustomerContactHistory history = dozer.map(dto,
					CustomerContactHistory.class);
			history.setCustNo(custNo);
			history.setModifyDate(now);
			history.setModifiedBy(customer.getModifiedBy());
			if (dto.getContactId() == null
					|| dto.getContactId().intValue() == 0) {
				history.setContactId(null);
				history.setCreatedBy(customer.getModifiedBy());
				history.setCreationDate(now);
			}
			System.out.println("history: " + history);
			this.custContactHistDao.save(history);
		}
	}

	private void attachCustInterest(Customer customer,
			List<CustomerInterest> custInterestList,
			List<Integer> delCustIntIdList) {
		if (delCustIntIdList != null && !delCustIntIdList.isEmpty()) {
			this.customerInterestDao.deleteCustomerInterests(delCustIntIdList);
		}
		if (custInterestList == null || custInterestList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (CustomerInterest intereset : custInterestList) {
			intereset.setInterestId(null);
			intereset.setCreatedBy(customer.getModifiedBy());
			intereset.setModifiedBy(customer.getModifiedBy());
			intereset.setCustNo(customer.getCustNo());
			intereset.setCreationDate(now);
			intereset.setModifyDate(now);
			this.customerInterestDao.save(intereset);
		}
	}

	// public void attachCustomerPoints(Customer customer,
	// CustomerPoints customerPoints) {
	// Date now = new Date();
	// customerPoints.setCreatedBy(customer.getModifiedBy());
	// customerPoints.setCreationDate(now);
	// customerPoints.setModifyDate(now);
	// customerPoints.setModifiedBy(customer.getModifiedBy());
	// customerPoints.setCustNo(customer.getCustNo());
	// this.customerPointsDao.save(customerPoints);
	// }

	private void attachSpecialPrice(Customer customer,
			List<CustSpecialPriceDTO> custSpecialPriceList,
			List<Integer> delSpecialPriceIdList) throws ParseException {
		if (delSpecialPriceIdList != null
				&& delSpecialPriceIdList.get(0) != null) {
			this.customerSpecialPriceDao
					.delSpecialPriceList(delSpecialPriceIdList);
		}
		if (custSpecialPriceList == null || custSpecialPriceList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (CustSpecialPriceDTO dto : custSpecialPriceList) {
			Integer custNo = customer.getCustNo();
			CustomerSpecialPrice price = dozer.map(dto,
					CustomerSpecialPrice.class);
			price.setCustNo(custNo);
			price.setModifyDate(now);
			price.setModifiedBy(customer.getModifiedBy());
			if (price.getPriceId() == null
					|| price.getPriceId().intValue() == 0) {
				price.setPriceId(null);
				price.setCreatedBy(customer.getModifiedBy());
				price.setCreationDate(now);
			}
			CustomerSpecialPrice dbSpecialPrice = this.customerSpecialPriceDao
					.getSpecialPrice(custNo, price.getCatalogNo());
			if (dbSpecialPrice != null) {
				// 新增或修改其它记录中的catalogNo为数据库中已存在的.
				if (price.getPriceId() == null
						|| (!price.getPriceId().equals(
								dbSpecialPrice.getPriceId()))) {
					throw new BussinessException(
							BussinessException.CUST_SP_CATALOGNO_DULP_CODE);
				}
				this.customerSpecialPriceDao.getSession().evict(dbSpecialPrice);
			}
			this.customerSpecialPriceDao.save(price);
		}
	}

	private void attachCustPublications(Customer customer,
			List<CustomerPublications> custPubList, List<Integer> delPubIdList) {
		if (delPubIdList != null && !delPubIdList.isEmpty()) {
			this.customerPublicationDao.deleteCustomerPubs(delPubIdList);
		}
		if (custPubList == null || custPubList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (CustomerPublications pub : custPubList) {
			pub.setModifyDate(now);
			pub.setModifiedBy(customer.getModifiedBy());
			pub.setCustNo(customer.getCustNo());
			if (pub.getId() == null || pub.getId().intValue() == 0) {
				pub.setId(null);
				pub.setCreatedBy(pub.getModifiedBy());
				pub.setCreationDate(now);
			}
			this.customerPublicationDao.save(pub);
		}
	}

	private void attachCustGrants(Customer customer,
			List<CustomerGrants> custGrantsList, List<Integer> delIdList) {
		if (delIdList != null && !delIdList.isEmpty()) {
			this.customerGrantsDao.deleteCustomerGrants(delIdList);
		}
		if (custGrantsList == null || custGrantsList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (CustomerGrants custGrants : custGrantsList) {
			custGrants.setModifyDate(now);
			custGrants.setModifiedBy(customer.getModifiedBy());
			custGrants.setCustNo(customer.getCustNo());
			if (custGrants.getGrantId() == null
					|| custGrants.getGrantId().intValue() == 0) {
				custGrants.setGrantId(null);
				custGrants.setCreatedBy(customer.getModifiedBy());
				custGrants.setCreationDate(now);
			}
			this.saveCustGrantss(custGrants);
		}
	}

	private void attachCustCases(Customer customer,
			List<CustomerCase> custCaseList) {
		if (custCaseList == null || custCaseList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (CustomerCase custCase : custCaseList) {
			custCase.setModifyDate(now);
			custCase.setModifiedBy(customer.getModifiedBy());
			if (custCase.getCaseId() == null
					|| custCase.getCaseId().intValue() == 0) {
				custCase.setCaseId(null);
				custCase.setCreatedBy(customer.getModifiedBy());
				custCase.setCreationDate(now);
			}
			custCase.setModifyDate(now);
			custCase.setCustNo(customer.getCustNo());
			this.customerCaseDao.save(custCase);
			Map<String, NoteDocument> documentMap = custCase.getDocumentMap();
			if (documentMap != null && documentMap.size() > 0) {
				Set<Entry<String, NoteDocument>> set = documentMap.entrySet();
				for (Entry<String, NoteDocument> entry : set) {
					NoteDocument noteDocumnet = entry.getValue();
					if (noteDocumnet == null) {
						this.noteDocumentDao.delete(noteDocumnet);
						continue;
					} else if (noteDocumnet.getRefId() == null
							|| noteDocumnet.getRefId() == 0) {
						noteDocumnet.setRefId(custCase.getCaseId());
					}
					this.noteDocumentDao.save(noteDocumnet);
				}
			}
		}
	}

	public void attachCustPersonal(Customer customer,
			CustomerPersonalInfo personal) {
		if (personal == null) {
			return;
		}
		Date now = new Date();
		personal.setModifyDate(now);
		personal.setModifiedBy(customer.getModifiedBy());
		personal.setCreatedBy(customer.getModifiedBy());
		personal.setCreationDate(now);
		personal.setModifyDate(now);
		personal.setCustNo(customer.getCustNo());
		this.custPersInfoDao.save(personal);

	}

	public Customer saveCustomer(CustomerDTO custOperDTO) throws ParseException {
		Customer customer = null;
		boolean isAdd = false;
		if (custOperDTO.getCustNo() == null || custOperDTO.getCustNo() == 0) {
			isAdd = true;
		}
		customer = dozer.map(custOperDTO, Customer.class);
		Organization org = custOperDTO.getOrganization();
		Integer orgId = null;
		if (org != null) {
			orgId = org.getOrgId();
			if (orgId != null) {
				customer.setOrgId(orgId);
			} else {
				customer.setOrgId(custOperDTO.getOrgId());
			}
		}
		customer.setBillAccountCode(custOperDTO.getBillAccountCode());
		Divisions div = null;
		if (custOperDTO.getDivision() != null
				&& custOperDTO.getDivision().getDivisionId() != null) {
			div = this.divisionDao.getById(custOperDTO.getDivision()
					.getDivisionId());
		}
		if (div != null) {
			div.setOrgId(orgId);
			// div = this.attachDivision(customer, div);
		}
		Departments dept = null;
		// Departments dept = new Departments();
		// System.out.println("custOperDTO.getDepartment().getDeptId()========"
		// + custOperDTO.getDepartment().getDeptId());
		// System.out.println("custOperDTO.getDepartment().getDeptFuncId()========"
		// + custOperDTO.getDepartment().getDeptFuncId());
		if (custOperDTO.getDepartment() != null
				&& custOperDTO.getDepartment().getDeptId() != null) {
			dept = this.departmentDao.getById(custOperDTO.getDepartment()
					.getDeptId());
		}

		if (dept != null) {
			// System.out.println(dept.getDeptFuncId());
			dept.setDeptFuncId(custOperDTO.getDepartment().getDeptFuncId());
			dept.setOrgId(orgId);
			if (div != null) {

				dept.setDivisionId(div.getDivisionId());
			}

			this.attachDept(customer, dept);
		}
		this.saveOrUpdateCust(customer);
		// this.organizationDao.save(org);
		this.attachAddress(customer, custOperDTO.getCustAddrList(),
				custOperDTO.getDelAddrIdList());
		this.attachContact(customer, custOperDTO.getContactList(),
				custOperDTO.getDelContactsIdList());
		attachSpecialPrice(customer, custOperDTO.getCustSpecialPriceList(),
				custOperDTO.getDelSpecialPriceIdList());
		// more info
		this.attachCustInterest(customer, custOperDTO.getCustInterestList(),
				custOperDTO.getDelCustIntIdList());
		this.attachCustPublications(customer, custOperDTO.getCustPubList(),
				custOperDTO.getDelCustPubIdList());
		this.attachCustGrants(customer, custOperDTO.getCustGrantsList(),
				custOperDTO.getDelCustGrantsIdList());
		this.attachCustCases(customer, custOperDTO.getCustCaseList());
		this.attachCustPersonal(customer, custOperDTO.getPersonal());
		this.attachNote(customer, custOperDTO.getCustNoteList());

		if (isAdd) {
			// 触发调用ERP
			ApplicationEvent evt = new NewCustomerEvent(this, custOperDTO,
					customer.getCustNo().toString());
			context.publishEvent(evt);
		} else {
			ApplicationEvent evt = new NewCustomerEvent(this, custOperDTO,
					customer.getCustNo().toString());
			context.publishEvent(evt);
		}
		return customer;
	}

	@Transactional(readOnly = true)
	public Page<CustSpecialPriceDTO> getProductSpecialPriceList(Integer custNo,
			Page<CustomerSpecialPrice> page) {
		return this.getSpecialPriceList(custNo, page, "PRODUCT");
	}

	@Transactional(readOnly = true)
	public Page<CustSpecialPriceDTO> getServiceSpecialPriceList(Integer custNo,
			Page<CustomerSpecialPrice> page) {
		return this.getSpecialPriceList(custNo, page, "SERVICE");
	}

	@Transactional(readOnly = true)
	public CustSpecialPriceDTO getSpecialPrice(Integer custNo, String catalogNo) {
		CustSpecialPriceDTO specialPriceDTO = null;
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter custFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(custFilter);
		PropertyFilter typeFilter = new PropertyFilter("EQS_catalogNo",
				catalogNo.trim());
		filterList.add(typeFilter);
		List<CustomerSpecialPrice> priceList = this.customerSpecialPriceDao
				.find(filterList);
		if (priceList != null && !priceList.isEmpty()) {
			specialPriceDTO = dozer.map(priceList.get(0),
					CustSpecialPriceDTO.class);
			Integer sourceId = specialPriceDTO.getSource();
			if (sourceId != null) {
				Source source = sourceDao.get(sourceId);
				if (source != null) {
					specialPriceDTO.setSourceKey(source.getName());
				}
			}
		}
		return specialPriceDTO;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	private Page<CustSpecialPriceDTO> getSpecialPriceList(Integer custNo,
			Page<CustomerSpecialPrice> page, String type) {
		List<CustSpecialPriceDTO> pricedtoList = new ArrayList<CustSpecialPriceDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(orgFilter);
		PropertyFilter typeFilter = new PropertyFilter("EQS_type", type);
		filterList.add(typeFilter);
		Page<CustomerSpecialPrice> pagePriceList = this.customerSpecialPriceDao
				.findPage(page, filterList);
		List<CustomerSpecialPrice> priceList = pagePriceList.getResult();
		if (priceList != null) {
			for (CustomerSpecialPrice price : priceList) {
				if (price.getPriceId() != null) {
					CustSpecialPriceDTO priceDto = dozer.map(price,
							CustSpecialPriceDTO.class);
					pricedtoList.add(priceDto);
				}
			}
		}
		pagePriceList.setResult(null);
		Page<CustSpecialPriceDTO> dtoPage = this.dozer.map(pagePriceList,
				Page.class);
		dtoPage.setResult(pricedtoList);
		return dtoPage;
	}

	/**
	 * 获得Customer的基本信息, 不包含其它表的关联内容.
	 * 
	 * @param customerNo
	 * @return
	 * @author wangsf
	 * @serialData 2010-10-20
	 */
	@Transactional(readOnly = true)
	public CustomerDTO getCustomerBase(Integer customerNo) {
		Customer customer = customerDao.getById(customerNo);
		if (customer != null) {
			CustomerDTO dto = dozer.map(customer, CustomerDTO.class);
			return dto;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public CustomerDTO getCustomerDetail(Integer customerNo) throws Exception {
		this.customerDao.getSessionFactory().evict(Customer.class);
		System.out
				.println("===============getCustomerDetail  evict=============");
		Customer customer = customerDao.getById(customerNo);
		Integer custNo = customer.getCustNo();
		CustomerDTO dto = dozer.map(customer, CustomerDTO.class);
		// attach address
		List<CustAddrOperDTO> custAddrList = new ArrayList<CustAddrOperDTO>();
		List<Address> addrList = this.addressDao.getListByCust(custNo);
		if (addrList != null) {
			for (Address addr : addrList) {
				CustAddrOperDTO addrDTO = dozer
						.map(addr, CustAddrOperDTO.class);
				// Organization dbOrg = addr.getOrganization();
				// if (dbOrg != null) {
				/*
				 * Organization org = new Organization();
				 * org.setName(dbOrg.getName()); org.setOrgId(dbOrg.getOrgId());
				 * addrDTO.setOrganization(org);
				 */
				// addrDTO.setOrgId(dbOrg.getOrgId());
				// }
				custAddrList.add(addrDTO);
			}
		}
		dto.setCustAddrList(custAddrList);

		// attach contact include history and sche
		List<ContactDTO> contactList = new ArrayList<ContactDTO>();
		List<CustomerContactHistory> tactHistList = this.custContactHistDao
				.getListByCust(custNo);
		if (tactHistList != null) {
			for (CustomerContactHistory hist : tactHistList) {
				ContactDTO contactDTO = dozer.map(hist, ContactDTO.class);
				contactList.add(contactDTO);
			}
			CustomerContactHistory contactHistory = this.custContactHistDao
					.getLastContact(custNo);
			if (contactHistory != null) {
				dto.setLastActivity(contactHistory.getModifyDate());
			}
		}
		String billingAccountCode = "";

		if (dto.getBillAccountCode() == null
				&& !"".equals(dto.getBillAccountCode())) {
			if (customerNo != null && !"".equals(customerNo)) {
				Address dd = this.addressDao.getDefaultBillTOAddress(customerNo
						.intValue());
				if (dd != null && !"".equals(dd)) {
					String billingAccount = dd.getCountry();
					if (billingAccount != null && !"".equals(billingAccount)) {
						if (billingAccount.equals("US")
								|| billingAccount.equals("CA")) {
							billingAccountCode = "US";
						} else {
							billingAccountCode = "HK";
						}
					}
				}
			}
			dto.setBillAccountCode(billingAccountCode);
		}
		dto.setContactList(contactList);
		// attach catalogId
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_custNo",
				customer.getCustNo());
		filterList.add(orgFilter);

		// attach points
		// -----------------------Modify by zhang yong
		// Start----------------------------------//
		if (customerNo != null) {
			Long pointsSum = this.customerPointsHistoryDao
					.getCustomerPointsSumByCustNo(customerNo);
			if (pointsSum != null) {
				dto.setPointsAvailable(pointsSum.intValue());
			}
		}
		// List<CustomerPoints> pointsList = this.customerPointsDao
		// .find(filterList);
		// if (pointsList != null && !pointsList.isEmpty()) {
		// CustomerPoints customerPoints = pointsList.get(0);
		// dto.setPointsAvailable(customerPoints.getPointsAvailable());
		// }
		// -----------------------Modify by zhang yong End
		// ----------------------------------//
		// attach org
		Integer orgId = customer.getOrgId();
		if (orgId != null) {
			dto.setOrganization(organizationDao.getById(orgId));
		}
		Integer divId = customer.getDivisionId();
		if (divId != null) {
			dto.setDivision(this.divisionDao.getById(customer.getDivisionId()));
		}
		Integer deptId = customer.getDeptId();
		if (deptId != null) {
			dto.setDepartment(this.departmentDao.getById(customer.getDeptId()));
		}
		Integer rfmRatingId = customer.getRfmRatingId();
		if (rfmRatingId != null) {
			RfmRating rfm = this.rfmRatingDao.getById(rfmRatingId);
			dto.setRfmRatingCd(rfm != null ? rfm.getRfmRatingCd() : "");
		}
		// attach more info
		CustInfoStat custInfoStat = this.custInfoStatDao
				.getInfoByCustNo(custNo);
		if (custInfoStat != null) {
			dto.setCustInfoStat(custInfoStat);
		}
		// 获取Erp数据
		// BigDecimal totOpenCredit =
		// erpSalesOrderService.getCustomerTotOpenCredit(custNo);
		// if (totOpenCredit != null) {
		// if (custInfoStat != null) {
		// dto.getCustInfoStat().setCurrentBalance(totOpenCredit.doubleValue());
		// } else {
		// custInfoStat = new CustInfoStat();
		// custInfoStat.setCurrentBalance(totOpenCredit.doubleValue());
		// dto.setCustInfoStat(custInfoStat);
		// }
		// }
		CustomerPersonalInfo personal = this.custPersInfoDao
				.getInfoByCustNo(custNo);
		if (personal != null) {
			dto.setPersonal(personal);
		}
		return dto;
	}

	@Transactional(readOnly = true)
	public List<PaymentTermDTO> getPaymentTermList() {
		List<PaymentTermDTO> dtoList = new ArrayList<PaymentTermDTO>();
		List<PaymentTerm> termList = this.paymentTermDao.getAll();
		if (termList != null) {
			for (PaymentTerm po : termList) {
				PaymentTermDTO dto = new PaymentTermDTO();
				dto.setDueDays(po.getDueDays());
				dto.setNetDays(po.getNetDays());
				dto.setName(po.getName());
				dto.setTermId(po.getTermId());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	public void saveCustGrantss(CustomerGrants entity) {
		customerGrantsDao.save(entity);
	}

	@Transactional(readOnly = true)
	public Page<CustomerGrants> listCustGrantsOld(Page<CustomerGrants> page,
			Integer custNo) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(orgFilter);
		return customerGrantsDao.findPage(page, filterList);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<CustGrantsDTO> listCustGrants(Page<CustomerGrants> page,
			Integer custNo) {
		List<CustGrantsDTO> dtoList = new ArrayList<CustGrantsDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(orgFilter);
		Page<CustomerGrants> pageList = this.customerGrantsDao.findPage(page,
				filterList);
		List<CustomerGrants> resultList = pageList.getResult();
		if (resultList != null) {
			for (CustomerGrants pub : resultList) {
				CustGrantsDTO custGrantsDTO = dozer.map(pub,
						CustGrantsDTO.class);
				dtoList.add(custGrantsDTO);
			}
		}
		pageList.setResult(null);
		Page<CustGrantsDTO> dtoPage = this.dozer.map(pageList, Page.class);
		dtoPage.setResult(dtoList);
		return dtoPage;
	}

	@Transactional(readOnly = true)
	public List<CustomerInterest> getCustIntList(Integer custNo) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(orgFilter);
		return this.customerInterestDao.find(filterList);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<ContactDTO> getCustContactList(Integer custNo,
			Page<CustomerContactHistory> page) {
		List<ContactDTO> dtoList = new ArrayList<ContactDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(orgFilter);
		Page<CustomerContactHistory> pageList = this.custContactHistDao
				.findPage(page, filterList);
		List<CustomerContactHistory> resultList = pageList.getResult();
		if (resultList != null) {
			User contactUser = null;
			for (CustomerContactHistory hist : resultList) {
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

	public String modCustPassword(String password, int custNo) {
		Integer i = null;
		if (null != password && custNo > 0) {
			i = this.customerDao.modCustPassword(password, custNo);
		}
		if (i == 1) {
			return "true";
		} else {
			return "false";
		}
	}

	@Transactional(readOnly = true)
	public CustContactInfoDTO getContactInfo(Integer custNo) {
		CustContactInfoDTO dto = new CustContactInfoDTO();
		int emailCount = 0;
		emailCount += this.custContactHistDao.getMethodCount("Email", custNo);
		dto.setEmailCount(emailCount);
		int faxCount = 0;
		faxCount += this.custContactHistDao.getMethodCount("Fax", custNo);
		dto.setFaxCount(faxCount);
		int letterCount = 0;
		letterCount += this.custContactHistDao.getMethodCount("Mail", custNo);
		dto.setLetterCount(letterCount);
		int phoneCount = 0;
		phoneCount += this.custContactHistDao.getMethodCount("Phone", custNo);
		dto.setPhoneCount(phoneCount);
		return dto;
	}

	@Transactional(readOnly = true)
	public CustInfoStat getCustInfoStat(Integer custNo) {
		return custInfoStatDao.get(custNo);
	}

	@Transactional(readOnly = true)
	public Page<CustomerPublications> getCustPublicationsListOld(
			Page<CustomerPublications> page, Integer custNo) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(orgFilter);
		return customerPublicationDao.findPage(page, filterList);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<CustPubsDTO> getCustPublicationsList(
			Page<CustomerPublications> page, Integer custNo) {
		List<CustPubsDTO> dtoList = new ArrayList<CustPubsDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(orgFilter);
		Page<CustomerPublications> pageList = this.customerPublicationDao
				.findPage(page, filterList);
		List<CustomerPublications> resultList = pageList.getResult();
		if (resultList != null) {
			for (CustomerPublications pub : resultList) {
				CustPubsDTO custPubsDTO = dozer.map(pub, CustPubsDTO.class);
				dtoList.add(custPubsDTO);
			}
		}
		pageList.setResult(null);
		Page<CustPubsDTO> dtoPage = this.dozer.map(pageList, Page.class);
		dtoPage.setResult(dtoList);
		return dtoPage;
	}

	@Transactional(readOnly = true)
	public Page<CustomerCase> searchCustCase(Page<CustomerCase> page) {
		return this.customerCaseDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<CustomerCase> searchCustCase(Page<CustomerCase> page,
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
		return this.customerCaseDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public Page<CustomerCase> searchCustCase(Page<CustomerCase> page,
			List<PropertyFilter> filterList) {
		if (filterList == null) {
			return searchCustCase(page);
		} else {
			return this.customerCaseDao.findPage(page, filterList);
		}
	}

	@Transactional(readOnly = true)
	public CustomerCase getCustCase(Integer caseId) {
		CustomerCase customerCase = this.customerCaseDao.getById(caseId);
		if (customerCase.getOwner() != null) {
			User user = this.userDao.getById(customerCase.getOwner());
			customerCase.setOwnerName(user != null ? user.getLoginName() : "");
		}
		User modifyUser = this.userDao.getById(customerCase.getModifiedBy());
		customerCase.setModifiedUser(modifyUser != null ? modifyUser
				.getLoginName() : "");
		customerCase.setDocumentMap(SessionUtil.convertList2Map(
				this.noteDocumentDao.getDocumentList(customerCase.getCaseId(),
						DocumentType.CUST_CASE), "docId"));
		return customerCase;
	}

	public void saveCustomerInterest(Integer custNo, CustomerInterest entity) {
		if (custNo != null) {
			entity.setCustNo(custNo);
		}
		customerInterestDao.save(entity);
	}

	public void deleteCustomerInterest(List<Integer> ids) {
		customerInterestDao.deleteCustomerInterests(ids);
	}

	@Transactional(readOnly = true)
	public List<CustomerInterest> getCustomerInterestList() {
		return customerInterestDao.getAll();
	}

	@Transactional(readOnly = true)
	public ContactActivity getContactActivity(Integer custNo) {
		ContactActivity act = new ContactActivity();
		Customer customer = this.customerDao.get(custNo);
		if (customer != null) {
			CustomerContactHistory lastPhone = custContactHistDao
					.getLastContactByMethod(custNo, ContactMethod.Phone.value());
			if (lastPhone != null) {
				act.setLastPhoneDate(lastPhone.getContactDate());
				act.setLastPhoneContactUser(lastPhone.getContactName());
				User contactUser = this.userDao.get(lastPhone.getContactBy());
				if (contactUser != null) {
					act.setLastPhoneContactBy(contactUser.getLoginName());
				}
			}
			CustomerContactHistory lastEmail = custContactHistDao
					.getLastContactByMethod(custNo, ContactMethod.Email.value());
			if (lastEmail != null) {
				act.setLastEmailSubject(lastEmail.getSubject());
				act.setLastEmailSent(lastEmail.getContactDate());
			}
			CustomerContactHistory lastFax = custContactHistDao
					.getLastContactByMethod(custNo, ContactMethod.Fax.value());
			if (lastFax != null) {
				act.setLastFaxSent(lastFax.getContactDate());
				act.setLastFaxSubject(lastFax.getSubject());
			}
			CustomerContactHistory lastLetter = custContactHistDao
					.getLastContactByMethod(custNo, ContactMethod.Mail.value());
			if (lastLetter != null) {
				act.setLastMailSent(lastLetter.getContactDate());
				act.setLastMailSubject(lastLetter.getSubject());
			}
			CustomerContactHistory contactHistory = custContactHistDao
					.getLastContactByContentType(custNo, "Catalog");
			if (contactHistory != null) {
				act.setLastCatalogSent(contactHistory.getContactDate());
				act.setLastCatalogName(contactHistory.getReferName());
			}
			act.setLastModifyDate(customer.getModifyDate());
			act.setLastModifyUser(this.userDao.get(customer.getModifiedBy())
					.getLoginName());
		}
		return act;
	}

	@Transactional(readOnly = true)
	public AccessStatDTO getAccessStat(Integer custNo) {
		SimpleDateFormat dateFmt = new SimpleDateFormat(WEB_BEHAVIOR_DT);
		AccessStatDTO statDTO = new AccessStatDTO();
		statDTO.setVisitTotal(this.visitDao.getVisitTotal(custNo));
		Visit first = this.visitDao.getFirstVisit(custNo);
		if (first != null && first.getInTime() != null) {
			statDTO.setFirstVisit(dateFmt.format(first.getInTime()));
		}
		Visit last = this.visitDao.getLastVisit(custNo);
		if (last != null && last.getInTime() != null) {
			statDTO.setLastVisit(dateFmt.format(last.getInTime()));
		}

		Long visitTimeTotal = this.visitDao.getTotalVisitTime(custNo);
		if (visitTimeTotal != null && statDTO.getVisitTotal() != null
				&& statDTO.getVisitTotal().longValue() > 0) {
			statDTO.setAvgStaySecd(visitTimeTotal / statDTO.getVisitTotal());
		}
		statDTO.setVisitPagesTotal(this.visitDao.getVisitPagesTotal(custNo));
		return statDTO;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<AnalysisDTO> searchAccess(AnalysisSrchDTO srch,
			Page<AccessAnalysis> page) throws ParseException {
		List<AnalysisDTO> dtoList = new ArrayList<AnalysisDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		PropertyFilter custFilter = new PropertyFilter("EQI_custNo",
				srch.getCustNo());
		filterList.add(custFilter);
		if (srch.getDateFrom() != null
				&& srch.getDateFrom().trim().length() > 1) {
			PropertyFilter filter = new PropertyFilter("GED_visitTime",
					dateFmt.parse(srch.getDateFrom()));
			filterList.add(filter);
		}
		if (srch.getDateTo() != null && srch.getDateTo().trim().length() > 1) {
			PropertyFilter filter = new PropertyFilter("LED_visitTime",
					dateFmt.parse(srch.getDateTo()));
			filterList.add(filter);
		}
		page = this.accessAnalysisDao.findPage(page, filterList);
		if (page.getResult() != null) {
			for (AccessAnalysis model : page.getResult()) {
				AnalysisDTO dto = dozer.map(model, AnalysisDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		Page<AnalysisDTO> retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);

		return retPage;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly = true)
	public Page<PageViewDTO> getAccessPageView(Page<AccessAnalysis> page,
			Integer custNo) {
		SimpleDateFormat dateFmt = new SimpleDateFormat(WEB_BEHAVIOR_DT);
		page = this.accessAnalysisDao.getPageView(page, custNo);
		List<PageViewDTO> dtoList = new ArrayList<PageViewDTO>();
		List list = page.getResult();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				PageViewDTO dto = new PageViewDTO();
				dto.setUrl((String) obj[0]);
				dto.setViewTimes((Long) obj[1]);
				if (obj[2] != null) {
					dto.setFirstView(dateFmt.format((Date) obj[2]));
				}
				if (obj[3] != null) {
					dto.setLastView(dateFmt.format((Date) obj[3]));
				}
				dto.setAvgVisitTime((Double) obj[4]);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		Page<PageViewDTO> pageViewDTO = dozer.map(page, Page.class);
		pageViewDTO.setResult(dtoList);
		return pageViewDTO;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly = true)
	public Page<ProductViewDTO> getAccessProductView(Page<AccessAnalysis> page,
			Integer custNo) {
		SimpleDateFormat dateFmt = new SimpleDateFormat(WEB_BEHAVIOR_DT);
		page = this.accessAnalysisDao.getProductView(page, custNo);
		List<ProductViewDTO> dtoList = new ArrayList<ProductViewDTO>();
		List list = page.getResult();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				ProductViewDTO dto = new ProductViewDTO();
				ProductCategory temp = this.productCategoryDao
						.getById((Integer) obj[0]);
				if (temp != null) {
					dto.setProductName(temp.getName());
				}
				dto.setViewTimes((Long) obj[1]);
				if (obj[2] != null) {
					dto.setFirstView(dateFmt.format((Date) obj[2]));
				}
				if (obj[3] != null) {
					dto.setLastView(dateFmt.format((Date) obj[3]));
				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		Page<ProductViewDTO> pageViewDTO = dozer.map(page, Page.class);
		pageViewDTO.setResult(dtoList);
		return pageViewDTO;
	}

	/**
	 * 本方法不支持分页.
	 * 
	 * @param page
	 * @param srchDTO
	 * @return
	 * @throws ParseException
	 */
	@Transactional(readOnly = true)
	public List<AnalysisReport> searchAnalysis(final PageDTO page,
			final AnalysisReportSrchDTO srchDTO) throws ParseException {
		List<AnalysisReport> dtoList = new ArrayList<AnalysisReport>();
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		Date dEndDate = dateFmt.parse(srchDTO.getEndDate());
		Date fromDate = dateFmt.parse(srchDTO.getBeginDate());
		Integer custNo = srchDTO.getCustNo();
		int period = srchDTO.getPeriod();
		Date toDate = this.getToDate(page, srchDTO);
		boolean bLoop = true;
		for (; bLoop;) {
			String tempFromDate = dateFmt.format(fromDate);
			String tempToDate = dateFmt.format(toDate);
			// 实际发生到数据库的数据要比显示为DTO的大一天， 否则取不到<=后面的数据.
			String actToDate = dateFmt.format(DateUtils.defineDayBefore2Object(
					toDate, 1, DateUtils.C_DATE_PATTON_DEFAULT, new Date()));
			if (tempToDate.equals(srchDTO.getEndDate())) {
				bLoop = false;
				actToDate = tempToDate;
			} else if (!toDate.before(dEndDate)) {
				bLoop = false;
				tempToDate = srchDTO.getEndDate();
				actToDate = tempToDate;
			}
			AnalysisReport report = new AnalysisReport();
			report.setFromDate(tempFromDate);
			report.setToDate(tempToDate);
			report.setVisit(accessAnalysisDao.getAnalysisVisits(custNo,
					tempFromDate, actToDate));
			report.setRefered(accessAnalysisDao.getAnalysisRefer(custNo,
					tempFromDate, actToDate));
			report.setSearching(accessAnalysisDao.getAnalysisSearching(custNo,
					tempFromDate, actToDate));
			report.setPageView(accessAnalysisDao.getAnalysisPageViewed(custNo,
					tempFromDate, actToDate));
			report.setAvgViewTime(accessAnalysisDao.getAnalysisAvgPageStays(
					custNo, tempFromDate, actToDate));
			dtoList.add(report);
			// 下次循环从本次截止日期的后一天开始.
			fromDate = DateUtils.defineDayBefore2Object(toDate, 1,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			if (period == 1) {
				toDate = DateUtils.defineDayBefore2Object(toDate, 1,
						DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			} else if (period == 7) {// by week
				toDate = DateUtils.getWeekEndDay(fromDate);
			} else if (period == 30) {// by month
				toDate = DateUtils.getMonthEndDay(fromDate);
			} else if (period == 90) {// by quarter
				toDate = DateUtils.getQuarterEndDay(fromDate);
			} else if (period == 365) {// by year
				toDate = DateUtils.getYearEndDay(fromDate);
			} else {
				toDate = DateUtils.defineDayBefore2Object(toDate, period - 1,
						DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			}
		}
		return dtoList;
	}

	/**
	 * 本方法不支持分页.
	 * 
	 * @param
	 * @param srchDTO
	 * @return
	 * @throws ParseException
	 */
	@Transactional(readOnly = true)
	public List<AnalysisReport> searchSalesStatistics(
			final SalesStaticsSrchDTO srchDTO) throws ParseException {
		List<AnalysisReport> dtoList = new ArrayList<AnalysisReport>();
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = dateFmt.format(srchDTO.getBeginDate());
		String toDateStr = dateFmt.format(srchDTO.getEndDate());
		Date dEndDate = dateFmt.parse(toDateStr);
		Date fromDate = dateFmt.parse(fromDateStr);
		Integer custNo = srchDTO.getCustNo();
		int period = srchDTO.getPeriod();
		Date toDate = this.getToDate(srchDTO);
		boolean bLoop = true;
		for (; bLoop;) {
			String tempFromDate = dateFmt.format(fromDate);
			String tempToDate = dateFmt.format(toDate);
			// 实际发生到数据库的数据要比显示为DTO的大一天， 否则取不到<=后面的数据.
			String actToDate = dateFmt.format(DateUtils.defineDayBefore2Object(
					toDate, 1, DateUtils.C_DATE_PATTON_DEFAULT, new Date()));
			if (tempToDate.equals(srchDTO.getEndDate())) {
				bLoop = false;
				actToDate = tempToDate;
			} else if (!toDate.before(dEndDate)) {
				bLoop = false;
				tempToDate = toDateStr;
				actToDate = tempToDate;
			}
			AnalysisReport report = new AnalysisReport();
			report.setFromDate(tempFromDate);
			report.setToDate(tempToDate);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", custNo);
			map.put("fromDate", dateFmt.parse(tempFromDate));
			map.put("toDate", dateFmt.parse(actToDate));
			BigDecimal amountBD = new BigDecimal(
					orderDao.getSalesStatistics(map));
			long amount = amountBD.divide(new BigDecimal(100))
					.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
			report.setVisit(amount);
			dtoList.add(report);
			// 下次循环从本次截止日期的后一天开始.
			fromDate = DateUtils.defineDayBefore2Object(toDate, 1,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			if (period == 1) {
				toDate = DateUtils.defineDayBefore2Object(toDate, 1,
						DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			} else if (period == 7) {// by week
				toDate = DateUtils.getWeekEndDay(fromDate);
			} else if (period == 30) {// by month
				toDate = DateUtils.getMonthEndDay(fromDate);
			} else if (period == 90) {// by quarter
				toDate = DateUtils.getQuarterEndDay(fromDate);
			} else if (period == 365) {// by year
				toDate = DateUtils.getYearEndDay(fromDate);
			} else {
				toDate = DateUtils.defineDayBefore2Object(toDate, period - 1,
						DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			}
		}
		return dtoList;
	}

	/**
	 * 获得第一次循环的截止日期.
	 * 
	 * @param page
	 * @param srchDTO
	 * @return
	 * @throws ParseException
	 */
	@Transactional(readOnly = true)
	private Date getToDate(final PageDTO page,
			final AnalysisReportSrchDTO srchDTO) throws ParseException {
		Date toDate = null;
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		Date dEndDate = dateFmt.parse(srchDTO.getEndDate());
		Date fromDate = dateFmt.parse(srchDTO.getBeginDate());
		int period = srchDTO.getPeriod();
		if (period == 1) {
			toDate = fromDate;
		} else if (period == 7) {// by week
			toDate = DateUtils.getWeekEndDay(fromDate);
		} else if (period == 30) {// by month
			toDate = DateUtils.getMonthEndDay(fromDate);
		} else if (period == 90) {// by quarter
			toDate = DateUtils.getQuarterEndDay(fromDate);
		} else if (period == 365) {// by year
			toDate = DateUtils.getYearEndDay(fromDate);
		} else {
			toDate = DateUtils.defineDayBefore2Object(fromDate, period - 1,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
		}
		if (!toDate.before(dEndDate)) {
			toDate = dEndDate;
		}
		return toDate;
	}

	/**
	 * 获得第一次循环的截止日期.
	 * 
	 * @param
	 * @param srchDTO
	 * @return
	 * @throws ParseException
	 */
	private Date getToDate(final SalesStaticsSrchDTO srchDTO)
			throws ParseException {
		Date toDate = null;
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = dateFmt.format(srchDTO.getBeginDate());
		String toDateStr = dateFmt.format(srchDTO.getEndDate());
		Date dEndDate = dateFmt.parse(toDateStr);
		Date fromDate = dateFmt.parse(fromDateStr);
		int period = srchDTO.getPeriod();
		if (period == 1) {
			toDate = fromDate;
		} else if (period == 7) {// by week
			toDate = DateUtils.getWeekEndDay(fromDate);
		} else if (period == 30) {// by month
			toDate = DateUtils.getMonthEndDay(fromDate);
		} else if (period == 90) {// by quarter
			toDate = DateUtils.getQuarterEndDay(fromDate);
		} else if (period == 365) {// by year
			toDate = DateUtils.getYearEndDay(fromDate);
		} else {
			toDate = DateUtils.defineDayBefore2Object(fromDate, period - 1,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
		}
		if (!toDate.before(dEndDate)) {
			toDate = dEndDate;
		}
		return toDate;
	}

	@Transactional(readOnly = true)
	public List<SalesOrderedDTO> getMostOrdered(Integer custNo, Integer top,
			String type) {
		return orderItemDao.getMostOrdered(custNo, top, type);
	}

	@Transactional(readOnly = true)
	public List<SalesOrderedDTO> getOrdersBy(Integer custNo, String ordersByType) {
		return orderItemDao.getOrdersBy(custNo, ordersByType);
	}

	@Transactional(readOnly = true)
	public String getPreferPaymentMthd(Integer custNo) {
		return customerDao.getPreferPaymentMthd(custNo);
	}

	/**
	 * 保存一个Customer的CreditCard.
	 * 
	 * @param card
	 * @param userId
	 */
	public Integer saveCreditCard(CreditCard card, Integer userId) {
		Date now = new Date();
		card.setCreatedBy(userId);
		card.setModifiedBy(userId);
		card.setCreationDate(now);
		card.setModifyDate(now);
		card.setStatus("ACTIVE");
		this.creditCardDao.save(card);
		return card.getId();
	}

	/**
	 * 删除一个CreditCard.
	 * 
	 * @param id
	 * @param userId
	 */
	public void delCreditCard(Integer id, Integer userId) {
		this.creditCardDao.delCard(userId, id);
	}

	@Transactional(readOnly = true)
	public DefaultAddressDTO getDefaultAddress(final Integer custNo) {
		DefaultAddressDTO defaultAddressDTO = new DefaultAddressDTO();
		Address billToAddr = addressDao.getDefaultBillTOAddress(custNo);
		Address shipToAddr = addressDao.getDefaultShipTOAddress(custNo);
		if (billToAddr != null) {
			defaultAddressDTO.setDefBillToAddr(dozer.map(billToAddr,
					CustAddrOperDTO.class));
		} else {
			defaultAddressDTO.setDefBillToAddr(null);
		}
		if (shipToAddr != null) {
			defaultAddressDTO.setDefShipToAddr(dozer.map(shipToAddr,
					CustAddrOperDTO.class));
		} else {
			defaultAddressDTO.setDefShipToAddr(null);
		}
		return defaultAddressDTO;
	}

	/**
	 * 获得一个Customer的所有note， 每个Note又包含documentList.
	 * 
	 * @param custNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CustNoteDTO> getNoteList(Integer custNo) {
		List<CustNoteDTO> dtoList = new ArrayList<CustNoteDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_custNo", custNo);
		filterList.add(quoteFilter);
		List<CustomerNote> noteList = this.customerNoteDao.find(filterList);
		if (noteList != null) {
			for (CustomerNote note : noteList) {
				CustNoteDTO dto = dozer.map(note, CustNoteDTO.class);
				List<PropertyFilter> docFilterList = new ArrayList<PropertyFilter>();
				PropertyFilter refIdFilter = new PropertyFilter("EQI_refId",
						note.getId());
				PropertyFilter refTypeFilter = new PropertyFilter(
						"EQS_refType", DocumentType.CUSTOMER_NOTE_TYPE.value());
				docFilterList.add(refIdFilter);
				docFilterList.add(refTypeFilter);
				List<NoteDocument> docList = this.noteDocumentDao
						.find(docFilterList);
				dto.setDocumentList(docList);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 合并多个customer.
	 * 
	 * @param masterNo
	 * @param slaveNoList
	 * @param userId
	 */
	public void combineCustomer(Integer masterNo, List<Integer> slaveNoList,
			Integer userId) {
		for (Integer slaveNo : slaveNoList) {
			this.combineCustomer(masterNo, slaveNo, userId);
		}
	}

	/**
	 * 合并两个Customer.
	 * 
	 * @param masterNo
	 * @param slaveNo
	 * @param userId
	 */
	private void combineCustomer(Integer masterNo, Integer slaveNo,
			Integer userId) {

		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter custNoFilter = new PropertyFilter("EQI_custNo", slaveNo);
		filterList.add(custNoFilter);
		Date now = new Date();

		// CustomerPoints
		CustomerPoints slavePoint = this.customerPointsDao.getById(slaveNo);
		if (slavePoint != null) {
			CustomerPoints custPoint = this.customerPointsDao.getById(masterNo);

			if (custPoint == null) {
				custPoint = new CustomerPoints();
				custPoint.setCustNo(masterNo);
				custPoint.setCreatedBy(userId);
				custPoint.setCreationDate(now);
				custPoint.setPointsAvailable(0);
				custPoint.setPointsHistory(0);
				custPoint.setPointsUsed(0);
			}
			custPoint.setPointsAvailable(slavePoint.getPointsAvailable()
					+ custPoint.getPointsAvailable());
			custPoint.setPointsHistory(slavePoint.getPointsHistory()
					+ custPoint.getPointsHistory());
			custPoint.setPointsUsed(slavePoint.getPointsUsed()
					+ custPoint.getPointsUsed());
			custPoint.setModifiedBy(userId);
			custPoint.setModifyDate(now);

			this.customerPointsDao.save(custPoint);
		}
		// CustomerContactHistory
		List<CustomerContactHistory> conactList = this.custContactHistDao
				.find(filterList);
		for (int i = 0; conactList != null && i < conactList.size(); i++) {
			CustomerContactHistory db = conactList.get(i);
			this.custContactHistDao.getSession().evict(db);
			db.setContactId(null);
			db.setCustNo(masterNo);
			db.setModifiedBy(userId);
			db.setStatus("ACTIVE");
			db.setModifyDate(now);
			this.custContactHistDao.save(db);
		}
		// TODO 暂且去掉MoreInfo.
		// More info.
		// this.combinCustMoreInfo(masterNo, slaveNo, userId, filterList);
		// Order, TemplateOrder, Quote, TemplateQuote.
		this.orderDao.updateCustomer(slaveNo, masterNo, userId);
		this.quoteDao.updateCustomer(slaveNo, masterNo, userId);
		// INVALID slave Customer.
		String reason = "Combined to new customer #" + masterNo;
		this.customerDao.delSlaveCustomer(userId, slaveNo, reason);
		this.customerDao.ActiveMasterCustomer(userId, masterNo, reason);

		Customer slaveCust = this.customerDao.getById(slaveNo);
		this.customerDao.getSession().evict(slaveCust);
		CustomerStatusHistory log = new CustomerStatusHistory();
		log.setCustNo(slaveNo);
		log.setUpdateDate(new Date());
		User updateUser = new User();
		updateUser.setUserId(userId);
		log.setUpdatedBy(updateUser);
		log.setUpdateReason(reason);
		log.setCurrentStat("INACTIVE");
		log.setPriorStat(slaveCust.getStatus());
		this.customerStatusHistoryDao.save(log);

	}

	/**
	 * 合并两个Customer时， 合并它们的More information. AccessAnalysis, AccessLog, Visit,
	 * CustomerGrants, CustomerPublication, CustomerCase.
	 * 
	 * @param masterNo
	 * @param slaveNo
	 * @param userId
	 * @param filterList
	 */
	@SuppressWarnings("unused")
	private void combinCustMoreInfo(Integer masterNo, Integer slaveNo,
			Integer userId, List<PropertyFilter> filterList) {
		Date now = new Date();
		// AccessLog
		List<AccessLog> agList = this.accessLogDao.find(filterList);
		for (int i = 0; agList != null && i < agList.size(); i++) {
			AccessLog db = agList.get(i);
			Integer srcLogId = db.getLogId();
			this.accessLogDao.getSession().evict(db);
			db.setLogId(null);
			db.setCustNo(masterNo);
			this.accessLogDao.save(db);
			Integer newLogId = db.getLogId();
			// AccessAnalysis
			AccessAnalysis analysis = accessAnalysisDao.getById(srcLogId);
			if (analysis != null) {
				this.accessAnalysisDao.getSession().evict(analysis);
				analysis.setLogId(newLogId);
				analysis.setCustNo(masterNo);
				this.accessAnalysisDao.save(analysis);
			}
		}
		// Visit
		List<Visit> visitList = this.visitDao.find(filterList);
		for (int i = 0; visitList != null && i < visitList.size(); i++) {
			Visit db = visitList.get(i);
			this.visitDao.getSession().evict(db);
			db.setVisitId(null);
			db.setCustNo(masterNo);
			this.visitDao.save(db);
		}
		// CustomerGrants
		List<CustomerGrants> grantList = this.customerGrantsDao
				.find(filterList);
		for (int i = 0; grantList != null && i < grantList.size(); i++) {
			CustomerGrants db = grantList.get(i);
			this.customerGrantsDao.getSession().evict(db);
			db.setGrantId(null);
			db.setCustNo(masterNo);
			db.setModifiedBy(userId);
			db.setModifyDate(now);
			this.customerGrantsDao.save(db);
		}
		// CustomerPublication
		List<CustomerPublications> pubsList = this.customerPublicationDao
				.find(filterList);
		for (int i = 0; pubsList != null && i < pubsList.size(); i++) {
			CustomerPublications db = pubsList.get(i);
			this.customerPublicationDao.getSession().evict(db);
			db.setId(null);
			db.setCustNo(masterNo);
			db.setModifiedBy(userId);
			db.setModifyDate(now);
			this.customerPublicationDao.save(db);
		}
		// CustomerCase
		List<CustomerCase> caseList = this.customerCaseDao.find(filterList);
		for (int i = 0; caseList != null && i < caseList.size(); i++) {
			CustomerCase db = caseList.get(i);
			this.customerCaseDao.getSession().evict(db);
			db.setCaseId(null);
			db.setCustNo(masterNo);
			db.setModifiedBy(userId);
			db.setModifyDate(now);
			this.customerCaseDao.save(db);
		}
	}

	public void sendCustMailJob() {
		List<CustomerContactHistory> custContactHistoryList = null;
		custContactHistoryList = custContactHistDao.queryMailSend();
		if (custContactHistoryList != null && custContactHistoryList.size() > 0) {
			for (CustomerContactHistory his : custContactHistoryList) {
				String mailTo = his.getEmail();
				String content = his.getContent();
				String subject = his.getSubject();
				if (StringUtils.isNotBlank(mailTo)) {
					mimeMailService.sendMail(mailTo, subject, content,
							"scm_admin@genscriptcorp.com");
					his.setStatus("COMPLETE");
					custContactHistDao.save(his);
				}
			}
		}
	}

	@Transactional(readOnly = true)
	public Page<CustomerBean> advSearchCustomer(Page<CustomerBean> page,
			List<PropertyFilter> filters, Set<Integer> custNoSet)
			throws Exception {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		String userName = SessionUtil.getUserName();
		// 判断当前用户是否含有销售经理角色
		boolean salesManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_SALES_MANAGER);
		if (Constants.USERNAME_ADMIN.equals(userName) || salesManager) {
			page = customerBeanDao.advSearchCustomer(page, criterionList,
					filters, custNoSet);
			return page;
		}
		// 判断当前用户是否含有内部订单管理员角色，是，则查询自己部门的customer
		boolean internalOrderManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_INTERNAL_ORDER_MANAGER);
		if (internalOrderManager) {
			if (filters == null || filters.isEmpty()) {
				filters = new ArrayList<PropertyFilter>();
			}
			filters.add(new PropertyFilter("EQS_custType",
					Constants.INTERNAL_TYPE_CUSTOMER));
		} else {
			Map<String, Object> salesRoleAndUserIdsMap = salesRepDao
					.getSameGroupUser();
			if (salesRoleAndUserIdsMap.get("function") != null
					&& salesRoleAndUserIdsMap.get("User_Ids") != null) {
				String salesRole = salesRoleAndUserIdsMap.get("function")
						.toString();
				Integer[] userIdsArr = (Integer[]) salesRoleAndUserIdsMap
						.get("User_Ids");
				if (SalesRepSalesRole.SALES_CONTACT.value().equals(salesRole)) {
					Criterion criterionSalesContact = Restrictions.in(
							"salesContactId", userIdsArr);
					criterionList.add(criterionSalesContact);
				} else if (SalesRepSalesRole.TECH_SUPPORT.value().equals(
						salesRole)) {
					Criterion criterionSalesContact = Restrictions.in(
							"techSupportId", userIdsArr);
					criterionList.add(criterionSalesContact);
				}
			}
		}
		page = customerBeanDao.advSearchCustomer(page, criterionList, filters,
				custNoSet);
		return page;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly = true)
	public Page<ServiceViewDTO> getAccessServiceView(Page<AccessAnalysis> page,
			Integer custNo) {
		SimpleDateFormat dateFmt = new SimpleDateFormat(WEB_BEHAVIOR_DT);
		page = this.accessAnalysisDao.getServiceView(page, custNo);
		List<ServiceViewDTO> dtoList = new ArrayList<ServiceViewDTO>();
		List list = page.getResult();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				ServiceViewDTO dto = new ServiceViewDTO();
				ServiceCategory temp = this.serviceCategoryDao
						.getById((Integer) obj[0]);
				if (temp != null) {
					dto.setServiceName(temp.getName());
				}
				dto.setViewTimes((Long) obj[1]);
				if (obj[2] != null) {
					dto.setFirstView(dateFmt.format((Date) obj[2]));
				}
				if (obj[3] != null) {
					dto.setLastView(dateFmt.format((Date) obj[3]));
				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		Page<ServiceViewDTO> pageViewDTO = dozer.map(page, Page.class);
		pageViewDTO.setResult(dtoList);
		return pageViewDTO;
	}

	/*
	 * public Page<SampleRequestDTO>
	 * getSampleRequestDTOPage(Page<SampleRequestDTO>page, Integer custNo){
	 * page= this.orderDao. }
	 */

	public boolean sendEmailToCustomer(List<File> upload,
			List<String> uploadContentType, List<String> uploadFileName,
			Integer custNo, String customerBusEmail, String subject,
			String content) throws Exception {
		boolean sendStatus = false;
		try {
			if (custNo != null && custNo > 0 && customerBusEmail != null
					&& !("").equals(customerBusEmail)) {
				Customer customer = customerDao.get(custNo);
				if (customer != null
						&& customerBusEmail.equals(customer.getBusEmail())) {
					// 目前页面上只能一次上传一个文件.
					List<String> fileNameList = new ArrayList<String>();
					Date now = new Date();
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					Object userId = session.get(StrutsActionContant.USER_ID);
					String logName = session.get(StrutsActionContant.USER_NAME)
							.toString();
					if (upload != null && upload.size() > 0) {
						for (int i = 0; i < upload.size(); i++) {
							String srcFileName = uploadFileName.get(i);
							uploadFileName.set(i, SessionUtil.generateTempId()
									+ "_" + srcFileName);
							fileNameList.add(uploadFileName.get(i));
							// 将上传的文件记录下来
							Document doc = new Document();
							doc.setCreatedBy((Integer) userId);
							doc.setModifiedBy((Integer) userId);
							doc.setCreationDate(now);
							doc.setModifyDate(now);
							doc.setRefId(custNo);
							doc.setRefType(DocumentType.CUSTOMER_NOTE_TYPE
									.value());
							doc.setDocName(srcFileName);
							doc.setFilePath("customerService/"
									+ uploadFileName.get(i));
							this.documentDao.save(doc);
						}
						fileService.uploadFile(upload, uploadContentType,
								uploadFileName,
								FilePathConstant.Email_Customer.value());
					}
					String realPath = FilePathConstant.Email_Customer.value();
					// 发送邮件
					mimeMailService.sendMail(customerBusEmail, subject,
							content, realPath, fileNameList);
					// 保存发送邮件记录
					CustomerContactHistory his = new CustomerContactHistory();
					his.setEmail(customerBusEmail);
					his.setContent(content);
					his.setSubject(subject);
					his.setCustNo(custNo);
					his.setContactMethod("EMail");
					his.setStatus("COMPLETED");
					his.setCreationDate(now);
					his.setCreatedBy((Integer) userId);
					his.setModifyDate(now);
					his.setModifiedBy((Integer) userId);
					his.setContactBy((Integer) userId);
					his.setContactDate(now);
					his.setContactName(logName);
					his.setStatus("COMPLETE");
					custContactHistDao.save(his);
					sendStatus = true;
				}
			}
		} catch (Exception ex) {
			return sendStatus;
		}
		return sendStatus;
	}

	/**
	 * 查询customer可兑换的GiftCard
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 * @author zhangyong
	 */
	@SuppressWarnings("unchecked")
	public void searchCustomerAvailableCard() throws Exception {
		String paramCustNo = ServletActionContext.getRequest().getParameter(
				"custNo");
		Customer customer = this.customerDao.findUniqueBy("custNo",
				Integer.parseInt(paramCustNo.trim()));
		ServletActionContext.getRequest().setAttribute("custNo", paramCustNo);
		ServletActionContext.getRequest().setAttribute("firstName",
				customer.getFirstName());
		ServletActionContext.getRequest().setAttribute("lastName",
				customer.getLastName());
		ServletActionContext.getRequest().setAttribute("email",
				customer.getBusEmail());
		// 获得当前的Customer可用积分
		Integer custPointsHistSum = customerPointsHistoryDao
				.getCustomerPointsSumByCustNo(customer.getCustNo()).intValue();
		// 获得Customer可用积分可以兑换的Amazon卡
		List<GiftCard> giftCardlist = giftCardDao
				.findGiftCardByValue(custPointsHistSum / 200);
		if (giftCardlist != null && giftCardlist.size() > 0) {
			GiftCard giftCard = giftCardlist.get(0);
			ServletActionContext.getRequest().setAttribute("redeemGiftCard",
					giftCard);
		}
		// 获得所有的未被兑换的coupon
		List<Coupon> couponlist = this.couponDao.findAvaliableCoupon();
		ServletActionContext.getRequest().setAttribute("redeemCouponList",
				couponlist);
		// 将要兑换的customer custNo存入session中
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
				.getRow(SessionConstant.RedeemPointCustomerManagement.value(),
						String.valueOf(userId));
		if (sessionMap == null) {
			sessionMap = new HashMap<String, Object>();
		}
		sessionMap.put("sessionCustNo", paramCustNo.trim());
		SessionUtil.insertRow(
				SessionConstant.RedeemPointCustomerManagement.value(),
				String.valueOf(userId), sessionMap);
	}

	/**
	 * 通过cardId查询
	 * 
	 * @param
	 * @return
	 * @author zhangyong
	 */
	@SuppressWarnings("unchecked")
	public boolean searchGiftCardByCardId() throws Exception {
		boolean status = false;
		String requestCustNo = ServletActionContext.getRequest().getParameter(
				"custNo");
		if (requestCustNo == null || ("").equals(requestCustNo.trim())) {
			return status;
		}
		Customer reqCustomer = this.customerDao.findUniqueBy("custNo",
				Integer.parseInt(requestCustNo.trim()));
		// 获得当前的Customer可用积分
		Integer reqCustPointsHistSum = customerPointsHistoryDao
				.getCustomerPointsSumByCustNo(reqCustomer.getCustNo())
				.intValue();
		// 获得Customer可用积分可以兑换的Amazon卡
		List<GiftCard> reqGiftCardlist = giftCardDao
				.findGiftCardByValue(reqCustPointsHistSum / 200);
		if (reqGiftCardlist != null && reqGiftCardlist.size() > 0) {
			GiftCard giftCard = reqGiftCardlist.get(0);
			ServletActionContext.getRequest().setAttribute("redeemGiftCard",
					giftCard);
		}

		String couponIdValues = Struts2Util.getParameter("couponIdValues");
		String requestFirstName = Struts2Util.getParameter("firstName");
		String requestLastName = Struts2Util.getParameter("lastName");
		String requestEmail = Struts2Util.getParameter("email");
		String requestGiftCardId = Struts2Util.getParameter("giftCardId");
		Integer userId = SessionUtil.getUserId();

		ServletActionContext.getRequest().setAttribute("custNo", requestCustNo);
		ServletActionContext.getRequest().setAttribute("firstName",
				requestFirstName);
		ServletActionContext.getRequest().setAttribute("lastName",
				requestLastName);
		ServletActionContext.getRequest().setAttribute("email", requestEmail);
		// 获得所有的未被兑换的coupon
		List<Coupon> couponlist = this.couponDao.findAvaliableCoupon();
		ServletActionContext.getRequest().setAttribute("redeemCouponList",
				couponlist);

		Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
				.getRow(SessionConstant.RedeemPointCustomerManagement.value(),
						String.valueOf(userId));
		if (sessionMap == null) {
			return status;
		}
		String sessionCustNo = (String) sessionMap.get("sessionCustNo");
		if (!requestCustNo.equals(sessionCustNo)) {
			return status;
		}

		Customer customer = this.customerDao.findByCustNo(Integer
				.parseInt(sessionCustNo));
		// 获得当前的Customer可用积分
		Integer custPointsHistSum = customerPointsHistoryDao
				.getCustomerPointsSumByCustNo(customer.getCustNo()).intValue();
		GiftCard searchGiftCard = null;
		if (requestGiftCardId != null && !("").equals(requestGiftCardId.trim())) {
			searchGiftCard = this.giftCardDao.findById(Integer
					.parseInt(requestGiftCardId.trim()));
			if (searchGiftCard == null) {
				ServletActionContext.getRequest().setAttribute("message",
						"Data anomalies, please re-exchange!");
				return status;
			}
			// 获得Customer可用积分可以兑换的Amazon卡
			List<GiftCard> giftCardlist = giftCardDao
					.findGiftCardByValue(custPointsHistSum / 200);
			if (giftCardlist != null && giftCardlist.size() > 0) {
				GiftCard giftCard = giftCardlist.get(0);
				if (giftCard.getId().intValue() != searchGiftCard.getId()
						.intValue()) {
					ServletActionContext.getRequest().setAttribute(
							"message",
							"You can only exchange $" + giftCard.getCardValue()
									+ " amazon card");
					// 返回到兑换页面
					return status;
				}
				ServletActionContext.getRequest().setAttribute(
						"redeemGiftCard", giftCard);
			}
		}
		int countCouponValue = 0;
		Map<String, Map<String, Coupon>> couponMap = new HashMap<String, Map<String, Coupon>>();
		if (couponIdValues != null && !("").equals(couponIdValues.trim())) {
			if (couponlist == null || couponlist.size() == 0) {
				ServletActionContext.getRequest().setAttribute("message",
						"There are no available GenScript coupons!");
				// 返回到兑换页面
				return status;
			}
			Map<String, String> couponIdsMap = new HashMap<String, String>();
			for (Coupon inCoupon : couponlist) {
				couponIdsMap.put(inCoupon.getId().toString(), null);
			}
			String[] couponIdValueArr = couponIdValues.trim().split(",");
			for (String couponIdValue : couponIdValueArr) {
				if (couponIdValue != null && couponIdValue.trim().contains("-")) {
					String[] idNum = couponIdValue.trim().split("-");
					if (idNum.length == 2) {
						String id = idNum[0];
						String num = idNum[1];
						if (Integer.parseInt(num) <= 0) {
							continue;
						}
						Coupon coupon = this.couponDao.findUniqueBy("id",
								Integer.parseInt(id));
						if (coupon == null
								|| !couponIdsMap.containsKey(coupon.getId()
										.toString())) {
							continue;
						}
						Map<String, Coupon> couponNum = new HashMap<String, Coupon>();
						couponNum.put(num, coupon);
						couponMap.put(id, couponNum);
					}
				}
			}
		}

		if (searchGiftCard != null || couponMap.size() > 0) {
			if (searchGiftCard != null) {
				custPointsHistSum = custPointsHistSum
						- searchGiftCard.getCardValue() * 200;
			}
			if (couponMap.size() > 0) {
				for (String key : couponMap.keySet()) {
					Map<String, Coupon> couponNum = couponMap.get(key);
					for (String inKey : couponNum.keySet()) {
						countCouponValue += Integer.parseInt(inKey)
								* couponNum.get(inKey).getValue();
					}
				}
			}
			custPointsHistSum = custPointsHistSum - countCouponValue * 100;
			// 积分不够
			if (custPointsHistSum < 0) {
				if (searchGiftCard != null) {
					ServletActionContext.getRequest().setAttribute(
							"redeemGiftCard", searchGiftCard);
				}
				ServletActionContext.getRequest().setAttribute("message",
						"Your points is not enough to redeem!");
				return status;
			}
			if (searchGiftCard != null) {
				sessionMap.put("sessionGiftCard", searchGiftCard);
				ServletActionContext.getRequest().setAttribute(
						"searchGiftCard", searchGiftCard);
			}
			if (couponMap.size() > 0) {
				List<CouponDTO> couponDTOlist = new ArrayList<CouponDTO>();
				for (String key : couponMap.keySet()) {
					Map<String, Coupon> couponNum = couponMap.get(key);
					for (String inKey : couponNum.keySet()) {
						Coupon inCoupon = couponNum.get(inKey);
						CouponDTO couponDTO = new CouponDTO();
						couponDTO.setId(inCoupon.getId());
						couponDTO.setCode(StringUtil.getIntRandom(9));
						couponDTO.setName(inCoupon.getName());
						couponDTO.setValue(inCoupon.getValue());
						couponDTO.setExprDate(inCoupon.getExprDate());
						couponDTO.setStatus(inCoupon.getStatus());
						couponDTO.setComments(inCoupon.getComments());
						couponDTO.setNumber(Integer.parseInt(inKey));
						couponDTOlist.add(couponDTO);
					}
				}
				ServletActionContext.getRequest().setAttribute("couponDTOlist",
						couponDTOlist);
				sessionMap.put("sessionCouponDTOList", couponDTOlist);
			}
			sessionMap.put("sessionFrstName", requestFirstName.trim());
			sessionMap.put("sessionLastName", requestLastName);
			sessionMap.put("sessionEmail", requestEmail);
			SessionUtil.insertRow(
					SessionConstant.RedeemPointCustomerManagement.value(),
					String.valueOf(userId), sessionMap);
			status = true;
		} else {
			ServletActionContext
					.getRequest()
					.setAttribute("message",
							"Please select a amazon card or fill the number of GenScript coupons!");
		}
		return status;
	}

	/**
	 * 保存积分兑换优惠券，更新数据库
	 * 
	 * @return
	 * @author zhangyong
	 */
	@SuppressWarnings("unchecked")
	public boolean saveRedeemPoint() throws Exception {
		boolean status = false;
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
				.getRow(SessionConstant.RedeemPointCustomerManagement.value(),
						String.valueOf(userId));
		if (sessionMap == null) {
			return status;
		}
		String sessionCustNo = (String) sessionMap.get("sessionCustNo");
		if (sessionCustNo == null || ("").equals(sessionCustNo)) {
			return status;
		}
		String sessionFrstName = (String) sessionMap.get("sessionFrstName");
		if (sessionFrstName == null || ("").equals(sessionFrstName)) {
			return status;
		}
		String sessionLastName = (String) sessionMap.get("sessionLastName");
		if (sessionLastName == null || ("").equals(sessionLastName)) {
			return status;
		}
		String sessionEmail = (String) sessionMap.get("sessionEmail");
		if (sessionEmail == null || ("").equals(sessionEmail)) {
			return status;
		}
		ServletActionContext.getRequest().setAttribute("custNo", sessionCustNo);
		ServletActionContext.getRequest().setAttribute("firstName",
				sessionFrstName);
		ServletActionContext.getRequest().setAttribute("lastName",
				sessionLastName);
		ServletActionContext.getRequest().setAttribute("email", sessionEmail);

		// 获得所有的未被兑换的coupon
		List<Coupon> couponlistToRequest = this.couponDao.findAvaliableCoupon();
		ServletActionContext.getRequest().setAttribute("redeemCouponList",
				couponlistToRequest);

		GiftCard sessionGiftCard = (GiftCard) sessionMap.get("sessionGiftCard");
		ServletActionContext.getRequest().setAttribute("redeemGiftCard",
				sessionGiftCard);

		List<CouponDTO> sessionCouponDTOList = (List<CouponDTO>) sessionMap
				.get("sessionCouponDTOList");
		if ((sessionGiftCard != null)
				|| (sessionCouponDTOList != null && sessionCouponDTOList.size() > 0)) {
			Date nowDate = new Date();
			StringBuffer successMsg = new StringBuffer();
			StringBuffer emailMsg = new StringBuffer();
			emailMsg.append(
					"Dear " + sessionFrstName + sessionLastName + ".<br/>")
					.append("<center>You have successfully redeemed:<br/>");
			Integer custPointsHistSum = customerPointsHistoryDao
					.getCustomerPointsSumByCustNo(
							Integer.parseInt(sessionCustNo)).intValue();
			if (sessionGiftCard != null) {
				GiftCard giftCard = giftCardDao.findGiftCardByValue(
						custPointsHistSum / 200).get(0);
				ServletActionContext.getRequest().setAttribute(
						"redeemGiftCard", giftCard);
				if (sessionGiftCard.getId().intValue() != giftCard.getId()
						.intValue()) {
					ServletActionContext.getRequest().setAttribute(
							"message",
							"You can only exchange $" + giftCard.getCardValue()
									+ " amazon card.");
					// 返回到兑换页面
					return status;
				}
				successMsg.append(sessionGiftCard.getCardValue() * 200
						+ " points for 1 $" + sessionGiftCard.getCardValue()
						+ " Amazon Gift Card.<br />");
				emailMsg.append(sessionGiftCard.getCardValue() * 200
						+ " points for 1 $" + sessionGiftCard.getCardValue()
						+ " Amazon Gift Card.<br/>");
				custPointsHistSum = custPointsHistSum
						- sessionGiftCard.getCardValue() * 200;
			}
			int countCouponValue = 0;
			List<Coupon> couponlist = new ArrayList<Coupon>();
			if (sessionCouponDTOList != null && sessionCouponDTOList.size() > 0) {
				ServletActionContext.getRequest().setAttribute(
						"redeemCouponDTOList", sessionCouponDTOList);
				for (CouponDTO couponDTO : sessionCouponDTOList) {
					int dtocardValue = couponDTO.getNumber()
							* couponDTO.getValue();
					countCouponValue += dtocardValue;
					successMsg.append(dtocardValue * 100 + " points for "
							+ couponDTO.getNumber() + " $"
							+ couponDTO.getValue()
							+ " GenScript Coupon(s).<br />");
					emailMsg.append(dtocardValue * 100 + " points for "
							+ couponDTO.getNumber() + " $"
							+ couponDTO.getValue()
							+ " GenScript Coupon(s).<br/>");
					for (int i = 0; i < couponDTO.getNumber(); i++) {
						Coupon incoupon = new Coupon();
						incoupon.setCustNo(Integer.parseInt(sessionCustNo));
						incoupon.setCode(couponDTO.getCode());
						incoupon.setName(couponDTO.getName());
						incoupon.setValue(couponDTO.getValue());
						incoupon.setCreationDate(nowDate);
						incoupon.setCreatedBy(userId);
						// incoupon.setExprDate(couponDTO.getExprDate());
						incoupon.setStatus(StatusType.ACTIVE.value());
						incoupon.setComments(couponDTO.getComments());
						// incoupon.setConfirmDate(nowDate);
						// incoupon.setConfirmedBy(userId);
						couponlist.add(incoupon);
					}
				}
				custPointsHistSum = custPointsHistSum - countCouponValue * 100;
			}
			// 积分不够
			if (custPointsHistSum < 0) {
				if (sessionGiftCard != null) {
					ServletActionContext.getRequest().setAttribute(
							"redeemGiftCard", sessionGiftCard);
				}
				ServletActionContext.getRequest().setAttribute("message",
						"Your points is not enough to redeem!");
				return status;
			}
			// 保存兑换amazon记录
			if (sessionGiftCard != null) {
				//
				CustomerPointsHistory customerPointsHistory = new CustomerPointsHistory();
				customerPointsHistory
						.setCustNo(Integer.parseInt(sessionCustNo));
				customerPointsHistory.setIssueDate(nowDate);
				customerPointsHistory
						.setPoints(-sessionGiftCard.getCardValue() * 200);
				this.customerPointsHistoryDao.save(customerPointsHistory);
				//
				GiftCard newGiftCard = this.giftCardDao.findUniqueBy("id",
						sessionGiftCard.getId());
				newGiftCard.setSendDate(nowDate);
				newGiftCard.setSentBy(userId);
				newGiftCard.setCustNo(Integer.parseInt(sessionCustNo));
				this.giftCardDao.save(newGiftCard);
			}
			// 保存兑换Genscript Coupon记录
			if (countCouponValue > 0) {
				CustomerPointsHistory customerPointsHistory = new CustomerPointsHistory();
				customerPointsHistory
						.setCustNo(Integer.parseInt(sessionCustNo));
				customerPointsHistory.setIssueDate(nowDate);
				customerPointsHistory.setPoints(-countCouponValue * 100);
				this.customerPointsHistoryDao.save(customerPointsHistory);
				for (Coupon inlistcoupon : couponlist) {
					this.couponDao.save(inlistcoupon);
				}
			}
			String subject = "Redeem Point";
			emailMsg.append("</center>");
			// 发送邮件
			mimeMailService.sendMail(sessionEmail, subject,
					emailMsg.toString(), "scm_admin@genscriptcorp.com");
			// 保存发送邮件记录
			CustomerContactHistory his = new CustomerContactHistory();
			his.setEmail(sessionEmail);
			his.setContent(emailMsg.toString());
			his.setSubject(subject);
			his.setCustNo(Integer.parseInt(sessionCustNo));
			his.setContactMethod("EMail");
			his.setStatus("COMPLETED");
			his.setCreationDate(nowDate);
			his.setCreatedBy((Integer) userId);
			his.setModifyDate(nowDate);
			his.setModifiedBy((Integer) userId);
			his.setContactBy((Integer) userId);
			his.setContactDate(nowDate);
			his.setContactName(sessionFrstName + sessionLastName);
			his.setStatus("COMPLETE");
			custContactHistDao.save(his);
			// 清除session
			SessionUtil.deleteRow(
					SessionConstant.RedeemPointCustomerManagement.value(),
					String.valueOf(userId));
			ServletActionContext.getRequest().setAttribute("successMsg",
					successMsg.toString());
			status = true;
		} else {
			ServletActionContext
					.getRequest()
					.setAttribute("message",
							"Please select a amazon card or fill the number of GenScript coupons!");
		}
		return status;
	}

	/**
	 * 通过custNo查询兑换优惠券的历史记录
	 */
	public Page<RedeemHistoryDTO> searchCustomerRedeemCouponHistory(
			Page<RedeemHistoryDTO> redeemPage, String paramCustNo, String p_no) {
		Customer customer = this.customerDao.findUniqueBy("custNo",
				Integer.parseInt(paramCustNo.trim()));
		if (customer != null) {
			List<RedeemHistoryDTO> redeemHistoryDTOList = new ArrayList<RedeemHistoryDTO>();
			List<Coupon> couponlist = this.couponDao
					.findCouponByCustNo(customer.getCustNo());
			List<GiftCard> giftCardlist = this.giftCardDao
					.findGiftCardByCustNo(customer.getCustNo());
			if (giftCardlist != null && giftCardlist.size() > 0) {
				for (GiftCard giftCard : giftCardlist) {
					RedeemHistoryDTO redeemHistoryDTO = new RedeemHistoryDTO();
					redeemHistoryDTO.setRedeemDate(giftCard.getSendDate());
					redeemHistoryDTO
							.setRedeemedPoints(giftCard.getCardValue() * 200);
					redeemHistoryDTO.setCouponCode("*");
					redeemHistoryDTO.setCouponValue(giftCard.getCardValue());
					redeemHistoryDTO.setExpirationDate(new Date());
					redeemHistoryDTO.setComments("Amazon Gift Card");
					redeemHistoryDTO.setStatus("--");
					redeemHistoryDTOList.add(redeemHistoryDTO);
				}
			}
			if (couponlist != null && couponlist.size() > 0) {
				for (Coupon coupon : couponlist) {
					RedeemHistoryDTO redeemHistoryDTO = new RedeemHistoryDTO();
					redeemHistoryDTO.setRedeemDate(coupon.getCreationDate());
					redeemHistoryDTO.setRedeemedPoints(coupon.getValue() * 100);
					redeemHistoryDTO.setCouponCode(coupon.getCode());
					redeemHistoryDTO.setCouponValue(coupon.getValue());
					redeemHistoryDTO.setExpirationDate(coupon.getExprDate());
					redeemHistoryDTO.setComments(coupon.getComments());
					redeemHistoryDTO.setStatus(coupon.getStatus());
					redeemHistoryDTOList.add(redeemHistoryDTO);
				}
			}
			List<RedeemHistoryDTO> pageList = new ArrayList<RedeemHistoryDTO>();
			int pageNo = redeemPage.getPageNo();
			if (!StringUtils.isEmpty(p_no) && StringUtils.isNumeric(p_no)) {
				pageNo = Integer.parseInt(p_no);
				redeemPage.setPageNo(pageNo);
			}
			for (int i = (pageNo - 1) * redeemPage.getPageSize(); i < redeemHistoryDTOList
					.size(); i++) {
				pageList.add(redeemHistoryDTOList.get(i));
				if (pageList.size() == redeemPage.getPageSize()) {
					break;
				}
			}
			redeemPage.setResult(pageList);
			redeemPage.setTotalCount(Long.valueOf(redeemHistoryDTOList.size()));
		} else {
			redeemPage.setTotalCount(0l);
		}
		return redeemPage;
	}

	/**
	 * 获得customer 的信用额度。
	 * 
	 * @param custNo
	 * @param toCurrency
	 * @return
	 */
	public Double getCreditLimit(Integer custNo, String toCurrency) {
		// 精度
		int scale = 2;
		if ("JPY".equalsIgnoreCase(toCurrency)) {
			scale = 0;
		}
		Customer customer = customerDao.get(custNo);
		Integer crRatingId = customer.getCrRatingId();
		if (crRatingId != null) {
			CreditRating creditRating = creditRatingDao.get(crRatingId);
			Double crLimit = creditRating.getCrLimit();

			Double rate = exchRateByDateDao.getCurrencyRate("USD", toCurrency,
					new Date());
			if (rate != null) {
				crLimit = ArithUtil.mul(crLimit, rate, scale);
				return crLimit;
			}
		}
		return null;
	}

	/**
	 * 查询busEmail
	 * 
	 * @param custNo
	 * @return
	 * @author zhangyong
	 */
	public String findBusEmailByCustNo(Integer custNo) {
		Customer customer = this.customerDao.findByCustNo(custNo);
		if (customer != null) {
			return customer.getBusEmail();
		}
		return null;
	}

	/**
	 * 检查customer的Business E-Mail和机构是否在黑名单中
	 * 
	 * @author Zhang Yong
	 * @param custNo
	 * @return
	 */
	public String checkCustomer(int custNo) {
		String message = "";
		Customer customer = this.customerDao.findByCustNo(custNo);
		if (customer == null) {
			message = "The customer:" + custNo + " is not exist.";
		} else if (customer.getBusEmail() == null) {
			message = "The customer:" + custNo + " Business E-Mail is null.";
		} else if (customer.getOrgId() == null) {
			message = "The customer:" + custNo + " organization is null.";
		} else {
			Organization organization = organizationDao.getById(customer
					.getOrgId());
			if (organization == null) {
				message = "The customer:" + custNo + " organization:"
						+ customer.getOrgId() + " is not exist.";
			} else {
				Address shipToAddr = addressDao.getDefaultShipTOAddress(custNo);
				if (shipToAddr == null) {
					message = "The customer:" + custNo
							+ " shipTo Address is null.";
				} else {
					boolean inBlackListFlag = false;
					String dialog = " This account is temporary suspended due to the 120 days or "
							+ "even older outstanding balances. Please notify customer to consult "
							+ "with his or her accounts payable department for the payment status "
							+ "before processing a new order.";
					// 检查customer的Business E-Mail,organization,shipTo Address
					// organization
					List<Object[]> instBlackList = customerDao
							.findInstitutionBlackList();
					if (instBlackList != null && !instBlackList.isEmpty()) {
						for (Object[] objs : instBlackList) {
							String inst_domain = objs[0] == null ? "" : objs[0]
									.toString();
							String inst_name = objs[1] == null ? "" : objs[1]
									.toString();
							if (StringUtils.isNotBlank(inst_domain)
									&& customer
											.getBusEmail()
											.toUpperCase()
											.trim()
											.contains(
													inst_domain.toUpperCase()
															.trim())) {
								message = "The customer:" + custNo
										+ " Business E-Mail:"
										+ customer.getBusEmail()
										+ " is in institution black list."
										+ dialog;
								inBlackListFlag = true;
								break;
							}
							if (StringUtils.isNotBlank(inst_name)
									&& organization
											.getName()
											.toUpperCase()
											.trim()
											.contains(
													inst_name.toUpperCase()
															.trim())) {
								message = "The customer:" + custNo
										+ " organization:"
										+ organization.getName()
										+ " is in institution black list."
										+ dialog;
								inBlackListFlag = true;
								break;
							}
							if (StringUtils.isNotBlank(inst_name)
									&& shipToAddr != null
									&& StringUtils.isNotBlank(shipToAddr
											.getOrgName())
									&& shipToAddr
											.getOrgName()
											.toUpperCase()
											.trim()
											.contains(
													inst_name.toUpperCase()
															.trim())) {
								message = "The customer:" + custNo
										+ " shipTo Address organization:"
										+ shipToAddr.getOrgName()
										+ " is in institution black list."
										+ dialog;
								inBlackListFlag = true;
								break;
							}
						}
					}
					if (StringUtils.isBlank(message)) {
						List<Object> accBlackList = customerDao
								.findAccountBlackList(custNo);
						if (accBlackList != null && !accBlackList.isEmpty()) {
							message = "The customer:" + custNo
									+ " is in account black list." + dialog;
							inBlackListFlag = true;
						} else {
							message = "SUCCESS";
						}
					} else {
						message = "SUCCESS";
					}
					if (inBlackListFlag) {
						Integer salesContact = customer.getSalesContact();
						if (salesContact != null) {
							User user = userDao.findByUserId(salesContact);
							if (user != null
									&& StringUtils.isNotBlank(user.getEmail())) {
								String mailTo = user.getEmail();
								StringBuilder sb = new StringBuilder();
								sb.append("This account is temporary suspended due to the huge amount of 120 days or even older outstanding balances. Please notify customer to consult with his or her accounts payable department for the payment status before processing this new order. ");
								mimeMailService.sendMail(mailTo, "Note Email",
										sb.toString(),
										"scm_admin@genscriptcorp.com");
							}
						}

					}
				}
			}
		}
		return message;
	}

	/**
	 * 根据departmentId分页查找Customers
	 * 
	 * @param page
	 * @param deptId
	 * @return
	 * @author wangsf
	 * @serialData 2011-03-02
	 */
	public Page<Customer> searchByDept(Page<Customer> page, Integer deptId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter pf = new PropertyFilter("EQI_deptId", deptId);
		filters.add(pf);
		return this.customerDao.findPage(page, filters);
	}

	/**
	 * 获得customer所有的case
	 */
	public List<CustomerCase> getAllCases(Integer custNo) {
		List<CustomerCase> custCaseList = this.customerCaseDao.findBy("custNo",
				custNo);
		for (CustomerCase customerCase : custCaseList) {
			customerCase.setDocumentMap(SessionUtil.convertList2Map(
					this.noteDocumentDao.getDocumentList(
							customerCase.getCaseId(), DocumentType.CUST_CASE),
					"docId"));
		}
		return custCaseList;
	}

	/**
	 * 给新建的customer随机生成了密码
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
	public boolean isReapt(String custNo, String busEmail) {
		Integer custNoInt = this.customerDao.getCustNoByEmail(busEmail);
		if (StringUtils.isNotEmpty(custNo) && !StringUtil.isNumeric(custNo)
				&& custNoInt != null) {
			return true;
		}
		if (StringUtils.isNotEmpty(custNo) && StringUtil.isNumeric(custNo)
				&& custNoInt != null
				&& !String.valueOf(custNoInt).equals(custNo)) {
			return true;
		}
		return false;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;

	}

	public void sendMailtoUserByCustNo(String custNo, Integer id) {
		System.out.println(custNo);
		Customer customer = this.customerDao.getById(Integer.parseInt(custNo));
		GiftCard gc = this.giftCardDao.findById(id);

		if (customer != null && gc != null) {
			// System.out.println(customer.getBusEmail());
			// 给新增用户的用户发送邮件 2011 11 15 zhou gang update
			String subject = " You've received a  $ " + gc.getCardValue()
					+ " Amazon.com Gift Card from GenScript";
			StringBuffer content = new StringBuffer();
			content.append("Dear ").append(
					customer.getFirstName() + ",<br><br>");
			content.append("Congratulations! Your GenScript EzCoupon points are converted to Amazon Gift Card. The following is your Amazon Gift Card information:<br/><br/>");
			content.append("Amazon Card Claim Code: <B>")
					.append(gc.getCardNo()).append("</B>;")
					.append("Value: <B>$").append(gc.getCardValue())
					.append("</B><br/><br/>");
			content.append("Thanks for using GenScript services.<br/><br/>GenScript USA Inc.<br/><br/>");
			/*
			 * content.append("Thanks for your redeemed Amazon gift card.")
			 * .append("<br><br>"); content.append("You've received a  $ " +
			 * gc.getCardValue() + " Amazon.com Gift Card from GenScript" +
			 * ",please use the claim code:<font size=\"14px\"><strong>" +
			 * gc.getCardNo() +
			 * "</strong></font>,on something just for you.<br><br>");
			 * content.append("By the way, Gift Cards must be redeemed on");
			 */
			/*
			 * content.append(
			 * " <a href=\"http:\\www.amazon.com\">www.amazon.com</a>  or its affiliated website  <a href=\"http:\\www.endless.com\">www.endless.com</a>"
			 * ) .append(
			 * ", toward the purchase of eligible products. For more information on using Amazon gift card,"
			 * +
			 * " visit  <a href=\"http:\\www.amazon.com/help/gc\">www.amazon.com/help/gc</a><br><br>"
			 * );
			 */
			content.append("=== GenScript: One Stop for Gene, Peptide, Protein and Antibody Services ===  <br><br>");
			content.append("Thank you. <br><br>");
			content.append("CONFIDENTIAL <br><br>");
			content.append("Unless expressly stated otherwise, this message is confidential and may be privileged. "
					+ "It is intended for the addressee(s) only. Access to this e-mail by anyone else is unauthorized."
					+ " If you are not an addressee, any disclosure or copying of the content of this e-mail or any action taken (or not taken) in reliance on it is unauthorized and may be unlawful. "
					+ "If you are not an addressee, please inform the sender immediately.<br> ");

			content.append("<br><a href='http://www.genscript.com'>http://www.genscript.com</a><br>");
			mimeMailService.sendMail(customer.getBusEmail() == null ? ""
					: customer.getBusEmail(), subject, content.toString(),
					"support@genscript.com");

		}

	}

	@SuppressWarnings("unchecked")
	public Page<CustomerPointsHistory> searchCustomerRedeemAmazonHistory(
			Page<CustomerPointsHistory> page, String paramCustNo, String p_no) {
		if (!StringUtils.isEmpty(p_no) && StringUtils.isNumeric(p_no)) {
			Integer pageNo = Integer.parseInt(p_no);
			page.setPageNo(pageNo);
		}
		BigDecimal totalCount = new BigDecimal(0);
		String hql = "select * from `customer`.`customer_points_history` where `cust_no`='"
				+ paramCustNo + "'";
		Query query = this.customerPointsHistoryDao.getSession()
				.createSQLQuery(hql);
		if (page.isAutoCount()) {
			String sql2 = "select count(*) from `customer`.`customer_points_history` where `cust_no`='"
					+ paramCustNo + "'";
			Query query2 = this.customerPointsHistoryDao.getSession()
					.createSQLQuery(sql2);

			if (query2.list() != null) {
				totalCount = (BigDecimal) query2.list().get(0);
			}
			page.setTotalCount(totalCount.longValue());
		}
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		page.setResult(query.list());
		return page;
	}

	public Page<CustomerPointsHistory> searchCustomerAllHistory(
			Page<CustomerPointsHistory> page, Integer paramCustNo) {
		return this.customerPointsHistoryDao.searchpointhistoryListAll(page,
				paramCustNo);
	}

	public String getBusEmailByCustomerNo(Integer custNo) {
		return this.customerDao.getBusEmailByCustNo(custNo);
	}

	/**
	 * 通过custNo查询company，查询结果是在获取的AccountCode前加上"GS"，结果例如：GSUS
	 * 
	 * @author Zhang Yong add date 2011-11-04
	 * @param custNo
	 * @return
	 */
	public String getCustomerCompany(Integer custNo, CustomerDTO customerDTO) {
		String company = "GSUS";
		if (customerDTO != null
				&& StringUtils.isNotBlank(customerDTO.getBillAccountCode())) {
			return "GS" + customerDTO.getBillAccountCode();
		}
		if (custNo == null) {
			return company;
		}
		Customer customer = customerDao.getById(custNo);
		if (customer == null
				|| StringUtils.isBlank(customer.getBillAccountCode())) {
			return company;
		}
		return "GS" + customer.getBillAccountCode();
	}

	// add by zhanghuibin
	public List<SalesRep> getSalesManages(String funName) {
		return salesRepDao.getSalesRepsByFunction(funName);
	}

}
