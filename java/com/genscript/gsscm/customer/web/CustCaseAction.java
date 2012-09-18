package com.genscript.gsscm.customer.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.entity.CustomerCase;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.privilege.entity.EmailGroup;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.entity.Service;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.systemsetting.service.EmailGroupService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 处理 customer case类
 * 
 * @author Administrator
 * 
 */
@Results({
		@Result(name = "list", location = "customer/customer_case_list.jsp"),
		@Result(name = "pdt_list", location = "customer/customer_case_pdt_list.jsp"),
		@Result(name = "input", location = "customer/customer_case_edit.jsp"),
		@Result(name = "search", location = "customer/customer_case_search.jsp") })
public class CustCaseAction extends BaseAction<Object> {

	private static final long serialVersionUID = 2845821616750759075L;

	@Autowired
	private PublicService publicService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ServService servService;
	private String sessCustNo;
	private Integer custNo;
	private Map<String, List<PbDropdownListOptions>> dropDownList;
	private Map<String, CustomerCase> custCaseMap;
	// 查询相关参数
	private String type;
	private String status;
	private Date fromDate;
	private Date toDate;
	// 保存
	private CustomerCase customerCase;
	private String sessCaseId;
	// product picker 查询相关参数
	private List<Product> productList;
	private List<Service> serviceList;
	private String searchType;
	private String searchOperator;
	private String propertyValue1;
	private String propertyName;

	// 上传附件相关
	@Autowired
	private FileService fileService;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 编辑customer case
	 * 
	 * @author zouyulu
	 */
	@Override
	public String input() throws Exception {
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();

		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.ALL_DEPARTMENTS);
		speclListName.add(SpecDropDownListName.CASE_SEND_EMAILTO);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);

		dropDownList.put("CASE_TYPE",
				publicService.getDropdownList("CASE_TYPE"));
		dropDownList.put("CASE_STATUS",
				publicService.getDropdownList("CASE_STATUS"));
		dropDownList.put("CASE_REASON",
				publicService.getDropdownList("CASE_REASON"));
		dropDownList.put("CASE_PRIORITY",
				publicService.getDropdownList("CASE_PRIORITY"));
		dropDownList.put("CASE_ORIGIN",
				publicService.getDropdownList("CASE_ORIGIN"));
		/*
		 * dropDownList.put("CASE_POT_LIABILITY", publicService
		 * .getDropdownList("CASE_POT_LIABILITY"));
		 */
		dropDownList.put("RELATION_ITEM_TYPE",
				publicService.getDropdownList("RELATION_ITEM_TYPE"));
		ServletActionContext.getRequest().setAttribute("userName",
				SessionUtil.getUserName());
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.containsKey(SessionConstant.CustCaseDocument.value())) {
			session.remove(SessionConstant.CustCaseDocument.value());
		}
		if (sessCaseId == null) {
			customerCase = new CustomerCase();
			customerCase.setOwner(SessionUtil.getUserId());
			customerCase.setOwnerName(SessionUtil.getUserName());
			sessCaseId = SessionUtil.generateTempId();
		} else {
			customerCase = (CustomerCase) SessionUtil.getOneRow(
					SessionConstant.CustCaseList.value(), sessCustNo,
					sessCaseId);
			if (customerCase == null && StringUtils.isNumeric(sessCaseId)) {
				customerCase = customerService.getCustCase(Integer
						.parseInt(sessCaseId));
			}
		}
		customerCase.setModifiedBy(SessionUtil.getUserId());
		customerCase.setModifiedUser(SessionUtil.getUserName());
		customerCase.setModifyDate(new Date());
		if (customerCase.getDocumentMap() == null) {
			customerCase
					.setDocumentMap(new LinkedHashMap<String, NoteDocument>());
		}
		SessionUtil.insertRow(SessionConstant.CustCaseDocument.value(),
				this.sessCaseId, customerCase.getDocumentMap());

		return "input";
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	/**
	 * 显示搜索case form
	 */
	@Override
	public String list() throws Exception {
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("CASE_TYPE",
				publicService.getDropdownList("CASE_TYPE"));
		dropDownList.put("CASE_STATUS",
				publicService.getDropdownList("CASE_STATUS"));
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.containsKey(SessionConstant.CustCaseDocument.value())) {
			session.remove(SessionConstant.CustCaseDocument.value());
		}
		return "list";
	}

	/**
	 * 处理搜索结果
	 * 
	 * @author zouyulu
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String search() throws Exception {
		Map<String, CustomerCase> sessCaseMap = (Map<String, CustomerCase>) SessionUtil
				.getRow(SessionConstant.CustCaseList.value(), sessCustNo);
		if ((sessCaseMap == null || sessCaseMap.size() == 0)
				&& StringUtil.isNumeric(sessCustNo)) {
			custCaseMap = SessionUtil.convertList2Map(this.customerService
					.getAllCases(Integer.parseInt(sessCustNo)), "caseId");
			SessionUtil.insertRow(SessionConstant.CustCaseList.value(),
					sessCustNo, custCaseMap);
		} else {
			custCaseMap = sessCaseMap;
		}
		// PagerUtil<CustomerCase> pagerUtil = new PagerUtil<CustomerCase>();
		// // 分页条件
		// Page<CustomerCase> page = pagerUtil.getRequestPage();
		// List<PropertyFilter> filters =
		// WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		// if (!page.isOrderBySetted()) {
		// page.setOrderBy("caseId");
		// page.setOrder(Page.DESC);
		// page.setPageSize(10);
		// }
		// // 搜索条件
		// PropertyFilter pf = new PropertyFilter("EQI_custNo", custNo);
		// filters.add(pf);
		// if (!StringUtils.isEmpty(type)) {
		// filters.add(new PropertyFilter("EQS_type", type));
		// }
		// if (!StringUtils.isEmpty(status)) {
		// filters.add(new PropertyFilter("EQS_status", status));
		// }
		// if (fromDate != null) {
		// filters.add(new PropertyFilter("GED_creationDate", fromDate));
		// }
		// if (toDate != null) {
		// filters.add(new PropertyFilter("LED_creationDate", toDate));
		// }
		// Page<CustomerCase> casePage = customerService.searchCustCase(page,
		// filters);
		// PageDTO pagerInfo = pagerUtil.formPage(casePage);
		// ServletActionContext.getRequest().setAttribute("pagerInfo",
		// pagerInfo);
		// Map<String, CustomerCase> dbCaseMap = SessionUtil.convertList2Map(
		// casePage.getResult(), "caseId");
		// Map<String, CustomerCase> sessCaseMap = (Map<String, CustomerCase>)
		// SessionUtil
		// .getRow(SessionConstant.CustCaseList.value(), sessCustNo);
		Map<String, CustomerCase> newCustCaseMap = new LinkedHashMap<String, CustomerCase>();
		// custCaseMap = SessionUtil.mergeList(sessCaseMap, dbCaseMap, page
		// .getPageNo());
		// 根据搜索条件过滤sessCaseMap
		if (custCaseMap != null && !custCaseMap.isEmpty()) {
			Iterator<Entry<String, CustomerCase>> it = custCaseMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, CustomerCase> e = it.next();
				CustomerCase tmpCustomerCase = e.getValue();
				if (!StringUtils.isEmpty(type)) {
					if (!tmpCustomerCase.getType().equalsIgnoreCase(type)) {
						continue;
					}
				}
				if (!StringUtils.isEmpty(status)) {
					if (!tmpCustomerCase.getStatus().equalsIgnoreCase(status)) {
						continue;
					}
				}
				if (fromDate != null) {
					if (fromDate.compareTo(tmpCustomerCase.getCreationDate()) == 1) {
						continue;
					}
				}
				if (toDate != null) {
					if (toDate.compareTo(tmpCustomerCase.getCreationDate()) == -1) {
						continue;
					}
				}
				newCustCaseMap.put(e.getKey(), tmpCustomerCase);
			}
		}
		custCaseMap = newCustCaseMap;
		return "search";
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}


	/**
	 * 保存customer case
	 * 
	 * @author zouyulu
	 */
	@Override
	public String save() throws Exception {
		if (StringUtils.isEmpty(sessCaseId)) {
			sessCaseId = SessionUtil.generateTempId();
		}
		System.out.println(customerCase.getSendEmailTo()); 
		if (customerCase.getCaseId() == null || customerCase.getCaseId() == 0) {
			customerCase.setCreationDate(new Date());
			customerCase.setCreatedBy(SessionUtil.getUserId());
		}   
		customerCase.setModifiedBy(SessionUtil.getUserId());
		customerCase.setModifyDate(new Date());
		if (customerCase.getStatus().equalsIgnoreCase("CLOSE")) {
			customerCase.setCloseDate(new Date());
		}
		Map<String, NoteDocument> sessMap = (LinkedHashMap<String, NoteDocument>) SessionUtil
				.getRow(SessionConstant.CustCaseDocument.value(),
						this.sessCaseId);
		customerCase.setDocumentMap(sessMap);
		SessionUtil.deleteRow(SessionConstant.CustCaseDocument.value(),
				this.sessCaseId);
		SessionUtil.updateOneRow(SessionConstant.CustCaseList.value(),
				sessCustNo, sessCaseId, customerCase);
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	public String searchPdtList() throws Exception {
		if (StringUtils.isEmpty(searchType)) {
			searchType = "PRODUCT";
		}
		// product 搜索
		if (searchType.equalsIgnoreCase("PRODUCT")) {
			PagerUtil<Product> pagerUtil = new PagerUtil<Product>();
			// 分页条件
			Page<Product> page = pagerUtil.getRequestPage();
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			page.setOrderBy("catalogNo");
			page.setOrder(Page.DESC);
			page.setPageSize(20);
			page.setPageNo(1);// 不进行分页
			if (filters == null) {
				page = productService.getProductList(page);
			} else {
				page = productService.getProductList(page, filters);
			}
			productList = page.getResult();
		} else {
			// service 搜索
			PagerUtil<Service> pagerUtil = new PagerUtil<Service>();
			// 分页条件
			Page<Service> page = pagerUtil.getRequestPage();
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			page.setOrderBy("catalogNo");
			page.setOrder(Page.DESC);
			page.setPageSize(20);
			page.setPageNo(1);// 不进行分页
			if (filters == null) {
				page = servService.searchServList(page);
			} else {
				page = servService.searchServList(page, filters);
			}
			serviceList = page.getResult();
		}
		return "pdt_list";
	}

	/**
	 * 上传附件
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String uploadFile() throws Exception {
		// 因为edit()方法里， 已经进行insert session, 所以这里sessMap肯定非空，
		// 可以直接使用且用完之后无须再update session(因为是引用);
		Map<String, NoteDocument> sessMap = (LinkedHashMap<String, NoteDocument>) SessionUtil
				.getRow(SessionConstant.CustCaseDocument.value(),
						this.sessCaseId);
		if (sessMap == null) {
			sessMap = new LinkedHashMap<String, NoteDocument>();
		}
		// 这次请求上传的文件， 要返回文件信息给客户端.
		Map<String, NoteDocument> uploadMap = new LinkedHashMap<String, NoteDocument>();

		// 目前页面上只能一次上传一个文件.
		String srcFileName = uploadFileName;
		NoteDocument doc = new NoteDocument();
		doc.setDocName(srcFileName);
		doc.setRefType(DocumentType.CUST_CASE.value());
		doc.setCreatedBy(SessionUtil.getUserId());
		doc.setCreationDate(new Date());
		doc.setModifiedBy(SessionUtil.getUserId());
		doc.setModifyDate(new Date());
		uploadFileName = SessionUtil.generateTempId() + "_" + srcFileName;
		doc.setFilePath(FilePathConstant.customer_case.value() + "/"
				+ uploadFileName);
		String tempId = SessionUtil.generateTempId();
		sessMap.put(tempId, doc);
		uploadMap.put(tempId, doc);
		fileService.uploadFile(upload, uploadContentType, uploadFileName,
				FilePathConstant.customer_case.value());
		return tohtml(tempId, srcFileName, doc.getFilePath());
	}

	protected String tohtml(String docId, String fileName, String filePath) {
		HttpServletResponse r = ServletActionContext.getResponse();
		r.setHeader("Cache-Control", "no-cache, must-revalidate");
		r.setHeader("Pragma", "no-cache");
		r.setContentType("text/html;charset=utf-8");
		try {
			r.getOutputStream().write(docId.getBytes("utf-8"));
			r.getOutputStream().write("@".getBytes());
			r.getOutputStream().write(fileName.getBytes("utf-8"));
			r.getOutputStream().write("@".getBytes());
			r.getOutputStream().write(filePath.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除附件
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deleteFile() throws Exception {
		Map<String, NoteDocument> sessMap = (LinkedHashMap<String, NoteDocument>) SessionUtil
				.getRow(SessionConstant.CustCaseDocument.value(),
						this.sessCaseId);
		// 操作Session内容删除相应记录， 对于数据库中的则置其value为null.
		String key = Struts2Util.getParameter("fileKey");
		if (StringUtils.isNumeric(key)) {
			sessMap.put(key, null);
		} else {
			sessMap.remove(key);
		}
		// 返回信号交由js执行reload页面
		Struts2Util.renderText("Success");
		return null;
	}

	public String getSessCustNo() {
		return sessCustNo;
	}

	public void setSessCustNo(String sessCustNo) {
		this.sessCustNo = sessCustNo;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownList() {
		return dropDownList;
	}

	public void setDropDownList(
			Map<String, List<PbDropdownListOptions>> dropDownList) {
		this.dropDownList = dropDownList;
	}

	public Map<String, CustomerCase> getCustCaseMap() {
		return custCaseMap;
	}

	public void setCustCaseMap(Map<String, CustomerCase> custCaseMap) {
		this.custCaseMap = custCaseMap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = DateUtils.formatStr2Date(fromDate, "yyyy-MM-dd");
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public CustomerCase getCustomerCase() {
		return customerCase;
	}

	public void setCustomerCase(CustomerCase customerCase) {
		this.customerCase = customerCase;
	}

	public String getSessCaseId() {
		return sessCaseId;
	}

	public void setSessCaseId(String sessCaseId) {
		this.sessCaseId = sessCaseId;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchOperator() {
		return searchOperator;
	}

	public void setSearchOperator(String searchOperator) {
		this.searchOperator = searchOperator;
	}

	public String getPropertyValue1() {
		return propertyValue1;
	}

	public void setPropertyValue1(String propertyValue1) {
		this.propertyValue1 = propertyValue1;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
