package com.genscript.gsscm.customer.web;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.report.dao.ReportDao;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.xwork.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.entity.PbLanguage;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.entity.Bank;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.OrganizationBilling;
import com.genscript.gsscm.customer.entity.OrganizationCategory;
import com.genscript.gsscm.customer.entity.OrganizationGroup;
import com.genscript.gsscm.customer.entity.OrganizationPreference;
import com.genscript.gsscm.customer.entity.OrganizationType;
import com.genscript.gsscm.customer.service.OrganizationService;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * orgnization_group 编辑相关Action
 * 
 * @author wangsf
 */
@Results({
		@Result(name = "search_group_from", location = "customer/organization_group_search_form.jsp"),
		@Result(name = "search_group_result", location = "customer/organization_group_search_result.jsp"),
		@Result(name = "search_from", location = "customer/organization_search_form.jsp"),
		@Result(name = "search_result", location = "customer/organization_search_result.jsp"),
		@Result(name = "org_group_edit", location = "customer/organization_group_edit.jsp"),
		@Result(name = "org_group_organization_list", location = "customer/org_group_organization_list.jsp"),
		@Result(name = "organization_edit", location = "customer/organization_edit.jsp"),
		@Result(name = "show_instruction", location = "customer/organization_instruction.jsp"),
		@Result(name = "organization_billing", location = "customer/organization_billing.jsp"),
		@Result(name = "update_status", location = "customer/organization_util_status.jsp"),
		@Result(name = "bank_list", location = "customer/organization_util_bankList.jsp"),
		@Result(name = "organization_preference", location = "customer/organization_preference.jsp"),
		@Result(name = "sub_organization_list", location = "customer/organization_sub_list.jsp"),
		@Result(name = "show_add_sublist", location = "customer/organization_show_add_sublist.jsp"),
		@Result(name = "del_obj_reason_dlg", location = "customer/del_obj_reason_dlg.jsp") })
public class OrganizationAction extends ActionSupport {

	/**
     *
     */
	private static final long serialVersionUID = 5341184600895452503L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private PublicService publicService;
	// 文件处理
	@Autowired
	private FileService fileService;
	@Autowired
	private ReportDao reportDao;
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
	// Instruction & note
	private Integer noteId;
	private String sessNoteId;
	private List<CustNoteDTO> noteList;
	private Map<String, String> noteJsonMap;
	private CustNoteDTO noteDTO;
	private List<CustNoteDTO> custNoteList;
	private String docDelIndexs;
	private String sessCustNo;

	private Integer id;
	private List<Integer> groupIdList;
	private List<Integer> idList;
	private Page<OrganizationGroup> orgGroupPage;
	private OrganizationGroup orgGroup;
	private Page<Organization> organizationPage;
	private Organization organization;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private OrganizationBilling billing;
	private OrganizationPreference preference;
	private String operation_method;
	private String delUrl;
	private String delObjType;
	private String note;
	private String name;

	private List<Catalog> catalogList;
	private List<DropDownDTO> searchGroupList;

	/**
	 * 进入OrganizationGroup的主页面
	 * 
	 * @return
	 */
	public String searchOrgGroup() {
		return "search_group_from";
	}

	/**
	 * 分页查找OrganizationGroup
	 */
	public String listOrgGroup() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<OrganizationGroup> pagerUtil = new PagerUtil<OrganizationGroup>();
			orgGroupPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!orgGroupPage.isOrderBySetted()) {
				orgGroupPage.setOrderBy("id");
				orgGroupPage.setOrder(Page.DESC);
			}
			// 设置默认每页显示记录条数
			orgGroupPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			orgGroupPage = organizationService.searchOrganizationGroup(
					orgGroupPage, filters);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					orgGroupPage);
		} catch (Exception ex) {
			return "search_group_result";
		}
		return "search_group_result";
	}

	/**
	 * 批量删除OrganizationGroup.
	 */
	public String deleteOrgGroup() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.organizationService.delOrganizationGroup(this.groupIdList,
					note);
			rt.put("message", "Delete organization group sucessfully !");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 进入新增或修改OrganizationGroup页面
	 * 
	 * @return
	 */
	public String editOrgGroup() {
		if (this.id == null) {// 新增

		} else {
			this.orgGroup = this.organizationService.getOrganizationGroup(id);
		}
		return "org_group_edit";
	}

	/**
	 * 利用Ajax请求保存OrganizationGroup, 并返回客户端相应信息. 输入groupIdList:
	 * 此处是用来作移除关系的Organization orgId list. 输入orgGroup
	 * 
	 * @return
	 */
	public String saveOrgGroup() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			if (orgGroup != null && orgGroup.getId() == null) {
				if (this.organizationService.findByOrgGroupCode(orgGroup
						.getGroupCode()) != null) {
					rt.put("result", "Failure");
					rt.put("message", "The organization group code is exist!");
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			this.organizationService.saveOrgGroup(orgGroup,
					SessionUtil.getUserId(), this.groupIdList);
			rt.put("message", "Save organization group sucessfully !");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 显示一个OrganizationGroup相关联的Organization列表页面.
	 * 
	 * @return
	 */
	public String showOrganizationListForGroup() {
		if (this.id != null) {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Organization> pagerUtil = new PagerUtil<Organization>();
			Page<Organization> page = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!page.isOrderBySetted()) {
				page.setOrderBy("id");
				page.setOrder(Page.DESC);
			}
			// 设置默认每页显示记录条数
			page.setPageSize(20);
			page = this.organizationService.searchOrganization(page, this.id);
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		}
		return "org_group_organization_list";
	}

	/**
	 * 进入Organization的主页面
	 * 
	 * @return
	 */
	public String search() {
		return "search_from";
	}

	/**
	 * 分页查找Organization
	 */
	public String list() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Organization> pagerUtil = new PagerUtil<Organization>();
			this.organizationPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!organizationPage.isOrderBySetted()) {
				organizationPage.setOrderBy("modifyDate");
				organizationPage.setOrder(Page.DESC);
			}
			// 设置默认每页显示记录条数
			organizationPage.setPageSize(20);
			String orgGroupId = Struts2Util.getParameter("orgGroupId");
			String orgName = Struts2Util.getParameter("orgName");

			organizationPage = organizationService.searchOrganization(
					organizationPage, orgName, orgGroupId);
			/*Organization organization = null;
			if (organizationPage != null
					&& organizationPage.getResult() != null) {
				for (int i = 0; i < organizationPage.getTotalCount(); i++) {
					organization = organizationPage.getResult().get(i);
					if (organization != null) {
						System.out.print(organization.getParentOrgId());
						Organization a = this.organizationDao
								.getById(organization.getParentOrgId());
						organization.setParentOrganization(a);
					}

				}
			}*/
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					organizationPage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "search_result";
	}

	/**
	 * 批量删除Organization.
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.organizationService.delOrganization(this.groupIdList, note);
			rt.put("message", "Delete organization sucessfully !");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 进入organization的编辑页面
	 * 
	 * @return
	 * @throws Exception
	 */

	private String saleVolume1;
	private String Balance1;
	private String Anr1;
	private String Percentageofsales1;
	private String AnnualExpected1;
	private String Creditlimit1;

	public String getCreditlimit1() {
		return Creditlimit1;
	}

	public void setCreditlimit1(String creditlimit1) {
		Creditlimit1 = creditlimit1;
	}

	public String getAnnualExpected1() {
		return AnnualExpected1;
	}

	public void setAnnualExpected1(String annualExpected1) {
		AnnualExpected1 = annualExpected1;
	}

	public String getPercentageofsales1() {
		return Percentageofsales1;
	}

	public void setPercentageofsales1(String percentageofsales1) {
		Percentageofsales1 = percentageofsales1;
	}

	public String getAnr1() {
		return Anr1;
	}

	public void setAnr1(String anr1) {
		Anr1 = anr1;
	}

	public String getBalance1() {
		return Balance1;
	}

	public void setBalance1(String balance1) {
		Balance1 = balance1;
	}

	public String getSaleVolume1() {
		return saleVolume1;
	}

	public void setSaleVolume1(String saleVolume1) {
		this.saleVolume1 = saleVolume1;
	}

	public String edit() throws Exception {
		if (this.id != null) {
			sessCustNo = this.id + "";
			this.organization = organizationService.getOrganization(id);
			Double saleVolume = organization.getSaleVolume();
			Double Balance = organization.getBalance();
			Double Anr = organization.getAnnualActualRevenue();
			Double Percentageofsales = organization.getPercentageOfSales();
			Double AnnualExpected = organization.getAnnualRevenueExpected();
			Double Creditlimit = organization.getCreditLimit();
			DecimalFormat df = new DecimalFormat("0.00");
			if (saleVolume != null && !"".equals(saleVolume)) {
				saleVolume1 = df.format(saleVolume);
			}
			if (Balance != null && !"".equals(Balance)) {
				Balance1 = df.format(Balance);
			}
			if (Anr != null && !"".equals(Anr)) {
				Anr1 = df.format(Anr);
			}
			if (Percentageofsales != null && !"".equals(Percentageofsales)) {
				Percentageofsales1 = df.format(Percentageofsales);
			}
			if (AnnualExpected != null && !"".equals(AnnualExpected)) {
				AnnualExpected1 = df.format(AnnualExpected);
			}
			if (Creditlimit != null && !"".equals(Creditlimit)) {
				Creditlimit1 = df.format(Creditlimit);

			}
			/*
			 * Double saleVolume1 =new Double(df.format(saleVolume)); Double
			 * Balance1 = new Double(df.format(Balance)); Double Anr1 =
			 * Double.parseDouble(df.format(Anr)); Double Percentageofsales1 =
			 * Double.parseDouble(df.format(Percentageofsales)); Double
			 * AnnualExpected1 = Double.parseDouble(df.format(AnnualExpected));
			 * Double Creditlimit1 = Double.parseDouble(df.format(Creditlimit));
			 * System.out.println(saleVolume1);
			 * organization.setSaleVolume(saleVolume1);
			 * organization.setAnnualActualRevenue(Anr1);
			 * organization.setPercentageOfSales(Percentageofsales1);
			 * organization.setAnnualRevenueExpected(AnnualExpected1);
			 * organization.setBalance(Balance1);
			 * organization.setCreditLimit(Creditlimit1);
			 */
			SessionUtil.deleteRow(SessionConstant.OrganizationNoteList.value(),
					sessCustNo);
			// 填充下拉框的数据源, 并存入session
			noteList = this.organizationService.getNoteList(this.id);
			Map<String, CustNoteDTO> custNoteMap = new HashMap<String, CustNoteDTO>();
			if (!noteList.isEmpty()) {
				noteJsonMap = new HashMap<String, String>();
				for (CustNoteDTO custNoteDTO : noteList) {
					custNoteMap.put(Integer.toString(custNoteDTO.getId()),
							custNoteDTO);
				}
			}
			SessionUtil.insertRow(SessionConstant.OrganizationNoteList.value(),
					sessCustNo, custNoteMap);
			// 清除session
			SessionUtil.deleteRow(SessionConstant.OrganizationSubList.value(),
					this.id + "");
		} else {
			sessCustNo = SessionUtil.generateTempId();
		}
		// 下拉框的数据源-1
		List<OrganizationType> orgTypeList = this.organizationService
				.getAllOrganizationType();
		List<OrganizationCategory> orgCategoryList = this.organizationService
				.getAllOrganizationCategory();
		List<OrganizationGroup> orgGroupList = this.organizationService
				.getAllOrganizationGroup();
		List<PbLanguage> languageList = publicService.getAllLangList();
		Struts2Util.getRequest().setAttribute("orgTypeList", orgTypeList);
		Struts2Util.getRequest().setAttribute("orgCategoryList",
				orgCategoryList);
		Struts2Util.getRequest().setAttribute("orgGroupList", orgGroupList);
		Struts2Util.getRequest().setAttribute("languageList", languageList);

		// 下拉框的数据源-2
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.CREDIT_STATUS);
		listName.add(DropDownListName.ORGANIZATION_RATING);
		dropDownMap = publicService.getDropDownMap(listName);
		return "organization_edit";
	}

	@SuppressWarnings("unchecked")
	private void attachSaveSubOrg() {
		// 新增时不处理
		if (this.organization == null || this.organization.getOrgId() == null) {
			return;
		}
		List<Integer> addIdList = new ArrayList<Integer>();
		List<Integer> delIdList = new ArrayList<Integer>();
		// 取得session中的Organization
		Map<String, Organization> sessOrgMap = (Map<String, Organization>) SessionUtil
				.getRow(SessionConstant.OrganizationSubList.value(),
						this.organization.getOrgId() + "");
		if (sessOrgMap == null) {
			return;
		}
		for (Entry<String, Organization> entry : sessOrgMap.entrySet()) {
			Organization org = (Organization) entry.getValue();
			if (org == null) {// 执行了临时的删除操作
				delIdList.add(Integer.parseInt(entry.getKey()));
			} else {
				if ((org.getParentOrgId() + "").equals(this.organization
						.getOrgId() + "")) {// 本来就是数据库里的子级organization
					// 不作处理.
				} else {
					addIdList.add(org.getOrgId());
				}
			}
		}
		this.organization.setSubOrgIdList(addIdList);
		this.organization.setDelSubOrgIdList(delIdList);
	}

	/**
	 * 利用Ajax请求保存Organization, 并返回客户端相应信息. 输入Organization
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String, CustNoteDTO> noteMap = null;
			List<CustNoteDTO> noteList = new ArrayList<CustNoteDTO>();
			noteMap = (Map<String, CustNoteDTO>) SessionUtil
					.getRow(SessionConstant.OrganizationNoteList.toString(),
							sessCustNo);
			if (noteMap != null) {
				for (Entry<String, CustNoteDTO> entry : noteMap.entrySet()) {
					CustNoteDTO tmpDTO = entry.getValue();
					if (tmpDTO.getId() == null || tmpDTO.getId() == 0) {
						tmpDTO.setId(null);
					}
					noteList.add(tmpDTO);
				}
			}
			this.attachSaveSubOrg();
			// System.out.println(ServletActionContext.getRequest().getParameter("organization.name"));
			String names = ServletActionContext.getRequest().getParameter(
					"organization.name");
			String namestr = StringEscapeUtils.escapeHtml(names);
			this.organization.setName(namestr);
			// System.out.println(this.organization.getName());
			this.organizationService.saveOrganization(noteList,
					this.organization, this.billing, this.preference,
					SessionUtil.getUserId());
			SessionUtil.deleteRow(SessionConstant.OrganizationNoteList.value(),
					sessCustNo);
			rt.put("message", "Save organization sucessfully !");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * @throws Exception
	 * @author wangsf 显示增加 Organization Note 输入: id, sessNoteId, sessCustNo
	 */
	@SuppressWarnings("unchecked")
	public String showInstruction() throws Exception {
		if (!StringUtils.isEmpty(sessNoteId)) {
			noteDTO = (CustNoteDTO) SessionUtil.getOneRow(
					SessionConstant.OrganizationNoteList.toString(),
					sessCustNo, sessNoteId);
		}
		return "show_instruction";
	}

	public String showBilling() {
		billing = organizationService.getBilling(this.id);
		return "organization_billing";
	}

	public String showPreference() {
		this.preference = this.organizationService.getPreference(this.id);
		catalogList = productService.getSpecailCatalogList();
		return "organization_preference";
	}

	/**
	 * 保存Instruction note 输入: sessCustNo等
	 * 
	 * @return
	 * @throws Exception
	 * @author wangsf
	 * @serialData 2011-02-22
	 */
	public String saveInstruction() throws Exception {
		List<NoteDocument> newNoteDocumentList = new ArrayList<NoteDocument>();
		List<Integer> newDelDocIdList = null;
		List<NoteDocument> noteDocumentList = null;
		try {
			if (sessNoteId == null || sessNoteId.trim().length() < 1
					|| sessNoteId.equals("0")) {
				// 新建
				sessNoteId = SessionUtil.generateTempId();
				noteDocumentList = new ArrayList<NoteDocument>();
			} else {
				// 编辑
				CustNoteDTO sessNoteDTO = (CustNoteDTO) SessionUtil.getOneRow(
						SessionConstant.OrganizationNoteList.toString(),
						this.sessCustNo, sessNoteId);
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
							newDelDocIdList.add(noteDocumentList.get(i)
									.getDocId());
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
					uploadFileName.set(
							i,
							SessionUtil.generateTempId()
									+ srcFileName.substring(srcFileName
											.lastIndexOf(".")));
					nd.setFilePath("organization_notes/"
							+ uploadFileName.get(i));
					nd.setDocName(srcFileName);
					newNoteDocumentList.add(nd);
				}
			}
			noteDTO.setDocumentList(newNoteDocumentList);
			noteDTO.setDelDocIdList(newDelDocIdList);
			SessionUtil.updateOneRow(
					SessionConstant.OrganizationNoteList.toString(),
					this.sessCustNo, sessNoteId, noteDTO);
			// 上传文件
			fileService.uploadFile(upload, uploadContentType, uploadFileName,
					"organization_notes");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Struts2Util.renderText("<pre>" + sessNoteId + "</pre>");// ajaxForm方式提交返回需要用html标记包含进去.
		return null;
	}

	/**
	 * 显示查找一个Organization的子级列表
	 * 
	 * @return
	 * @author wangsf
	 * @serialData 2011-03-03
	 */
	@SuppressWarnings("unchecked")
	public String showSubList() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<Organization> pagerUtil = new PagerUtil<Organization>();
		Page<Organization> page = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		page.setPageSize(20);
		page = this.organizationService.searchSubList(page, this.id);
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		// 把数据库记录放入Map中
		Map<String, Organization> dbOrgMap = new LinkedHashMap<String, Organization>();
		for (Organization org : page.getResult()) {
			// 数据库记录放入Map时Key为数值, 而后面操作放入Map的记录Key为随机串
			dbOrgMap.put(org.getOrgId() + "", org);
		}
		// 取得session中的Organization
		Map<String, Organization> sessOrgMap = (Map<String, Organization>) SessionUtil
				.getRow(SessionConstant.OrganizationSubList.value(), this.id
						+ "");
		Map<String, Organization> orgMap = SessionUtil.mergeList(sessOrgMap,
				dbOrgMap, page.getPageNo());
		ServletActionContext.getRequest().setAttribute("orgMap", orgMap);
		return "sub_organization_list";
	}

	@SuppressWarnings("unchecked")
	public String addSubList() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			// 取得session中的Organization
			Map<String, Organization> sessOrgMap = (Map<String, Organization>) SessionUtil
					.getRow(SessionConstant.OrganizationSubList.value(),
							Struts2Util.getParameter("parentOrgId"));
			if (sessOrgMap == null) {// Session中为空
				sessOrgMap = new LinkedHashMap<String, Organization>();
			}
			for (Integer orgId : this.idList) {
				Organization organization = organizationService
						.getOrganization(orgId);
				sessOrgMap.put(SessionUtil.generateTempId() + "", organization);
			}
			// 更新Session中的Organization, 解决刚开始Session为空的问题， 否则因为是引用，则不需要更新Map.
			SessionUtil.insertRow(SessionConstant.OrganizationSubList.value(),
					Struts2Util.getParameter("parentOrgId"), sessOrgMap);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 显示Organization列表, 用来为一个Organization添加子的.
	 * 
	 * @return
	 * @author wangsf
	 * @serialData 2011-03-11
	 */
	public String showListForAddSub() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<Organization> pagerUtil = new PagerUtil<Organization>();
		Page<Organization> page = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		page.setPageSize(20);
		page = this.organizationService.searchUnSubList(page,
				Integer.parseInt(Struts2Util.getParameter("orgId")));
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		return "show_add_sublist";
	}

	/**
	 * 批量删除子级organization, 但是指更新Session, 并未操作数据库
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteSubList() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String orgId = Struts2Util.getParameter("parentOrgId");
			String[] keyList = Struts2Util.getRequest().getParameterValues(
					"keyList");
			// 取得session中的Organization
			Map<String, Organization> sessOrgMap = (Map<String, Organization>) SessionUtil
					.getRow(SessionConstant.OrganizationSubList.value(), orgId);
			if (sessOrgMap == null) {// Session中为空
				sessOrgMap = new LinkedHashMap<String, Organization>();
			}
			for (String key : keyList) {
				if (sessOrgMap.get(key) == null) {// 数据库有而session目前没有.
					sessOrgMap.put(key, null);
				} else {
					String tempParentId = sessOrgMap.get(key).getParentOrgId()
							+ "";
					if (tempParentId.equals(orgId)) {// 数据库里的子级organization
						sessOrgMap.put(key, null);
					} else {
						sessOrgMap.remove(key);// session里的子级organization
					}
				}
			}
			// 更新Session中的Organization, 解决刚开始Session为空的问题， 否则因为是引用，则不需要更新Map.
			SessionUtil.insertRow(SessionConstant.OrganizationSubList.value(),
					orgId, sessOrgMap);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 进入更新状态页面(organization, division, department三个模块共用)
	 * 
	 * @return
	 */
	public String showUpdateStatus() {
		return "update_status";
	}

	/**
	 * 进行银行选择页面
	 * 
	 * @return
	 */
	public String showSeclectBank() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<Bank> pagerUtil = new PagerUtil<Bank>();
		Page<Bank> page = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(20);
		}
		// 获得查询条件
		List<PropertyFilter> filterList = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		page = this.publicService.searchBank(page, filterList);
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		return "bank_list";
	}

	/**
	 * 显示填写删除orgGroup原因的对话框
	 * 
	 * @return
	 */
	public String showDelReasonDlg() {
		return "del_obj_reason_dlg";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Integer> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<Integer> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public Page<OrganizationGroup> getOrgGroupPage() {
		return orgGroupPage;
	}

	public void setOrgGroupPage(Page<OrganizationGroup> orgGroupPage) {
		this.orgGroupPage = orgGroupPage;
	}

	public OrganizationGroup getOrgGroup() {
		return orgGroup;
	}

	public void setOrgGroup(OrganizationGroup orgGroup) {
		this.orgGroup = orgGroup;
	}

	public Page<Organization> getOrganizationPage() {
		return organizationPage;
	}

	public void setOrganizationPage(Page<Organization> organizationPage) {
		this.organizationPage = organizationPage;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
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

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getSessNoteId() {
		return sessNoteId;
	}

	public void setSessNoteId(String sessNoteId) {
		this.sessNoteId = sessNoteId;
	}

	public List<CustNoteDTO> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<CustNoteDTO> noteList) {
		this.noteList = noteList;
	}

	public Map<String, String> getNoteJsonMap() {
		return noteJsonMap;
	}

	public void setNoteJsonMap(Map<String, String> noteJsonMap) {
		this.noteJsonMap = noteJsonMap;
	}

	public CustNoteDTO getNoteDTO() {
		return noteDTO;
	}

	public void setNoteDTO(CustNoteDTO noteDTO) {
		this.noteDTO = noteDTO;
	}

	public List<CustNoteDTO> getCustNoteList() {
		return custNoteList;
	}

	public void setCustNoteList(List<CustNoteDTO> custNoteList) {
		this.custNoteList = custNoteList;
	}

	public String getDocDelIndexs() {
		return docDelIndexs;
	}

	public void setDocDelIndexs(String docDelIndexs) {
		this.docDelIndexs = docDelIndexs;
	}

	public String getSessCustNo() {
		return sessCustNo;
	}

	public void setSessCustNo(String sessCustNo) {
		this.sessCustNo = sessCustNo;
	}

	public OrganizationBilling getBilling() {
		return billing;
	}

	public void setBilling(OrganizationBilling billing) {
		this.billing = billing;
	}

	public OrganizationPreference getPreference() {
		return preference;
	}

	public void setPreference(OrganizationPreference preference) {
		this.preference = preference;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public String getDelUrl() {
		return delUrl;
	}

	public void setDelUrl(String delUrl) {
		this.delUrl = delUrl;
	}

	public String getDelObjType() {
		return delObjType;
	}

	public void setDelObjType(String delObjType) {
		this.delObjType = delObjType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Catalog> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<Catalog> catalogList) {
		this.catalogList = catalogList;
	}

	public List<DropDownDTO> getSearchGroupList() {
		searchGroupList = reportDao
				.getCommonList("select id,name from OrganizationGroup order by name");
		return searchGroupList;
	}

	public void setSearchGroupList(List<DropDownDTO> searchGroupList) {
		this.searchGroupList = searchGroupList;
	}
}
