package com.genscript.gsscm.customer.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.contact.entity.Contact;
import com.genscript.gsscm.contact.service.ContactService;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.DepartmentBilling;
import com.genscript.gsscm.customer.entity.DepartmentFunction;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.customer.service.DepartmentService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Department 编辑相关Action
 * 
 * @author wangsf
 * 
 */
@Results( {
		@Result(name = "search_result", location = "customer/department_search_result.jsp"),
		@Result(name = "department_edit", location = "customer/department_edit.jsp"),
		@Result(name = "department_add", location = "customer/department_add.jsp"),		
		@Result(name = "department_billing", location = "customer/department_billing.jsp"),
		@Result(name = "show_instruction", location = "customer/department_instruction.jsp"),
        @Result(name = "customer_index", location = "customer/department_customer_index.jsp"),
		@Result(name = "contact_list", location = "customer/department_contact_list.jsp"),
		@Result(name = "account_list", location = "customer/department_account_list.jsp")
		
})
public class DepartmentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5341184600895452503L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private CustomerService customerService;

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
	private List<Integer> idList;
	private Page<Departments> departmentPage;
	private Departments department;
	private DepartmentBilling billing;
	private Page<Contact> contactPage;
	private Page<Customer> customerPage;
	/**
	 * 进入Department的主页面
	 * 
	 * @return
	 */
	public String search() {
		return "search_from";
	}

	/**
	 * 分页查找Department
	 */
	public String list() {
		String divisionId = Struts2Util.getParameter("divisionId");
		try {
			if (divisionId != null && divisionId.trim().length() > 0) {
				// 获得分页请求相关数据：如第几页
				PagerUtil<Departments> pagerUtil = new PagerUtil<Departments>();
				this.departmentPage = pagerUtil.getRequestPage();
				// 设置默认排序
				if (!departmentPage.isOrderBySetted()) {
					departmentPage.setOrderBy("id");
					departmentPage.setOrder(Page.DESC);
				}
				// 设置默认每页显示记录条数
				departmentPage.setPageSize(20);
				departmentPage = departmentService.searchDepartment(departmentPage,
						Integer.parseInt(divisionId));
				ServletActionContext.getRequest().setAttribute("pagerInfo",
						departmentPage);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "search_result";
	}

	/**
	 * 
	 * 批量删除Department.
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.departmentService.delDepartment(this.idList);
			rt.put("message", "Delete Department sucessfully !");
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
	 * 进入department的编辑页面
	 * 
	 * @return
	 */
	public String edit() {
		//session处理
		sessCustNo = this.id + "";
		SessionUtil.deleteRow(SessionConstant.DepartmentNoteList.value(), sessCustNo);
		// 填充下拉框的数据源, 并存入session
		noteList = this.departmentService.getNoteList(this.id);
		Map<String, CustNoteDTO> custNoteMap = new HashMap<String, CustNoteDTO>();
		if (!noteList.isEmpty()) {
			noteJsonMap = new HashMap<String, String>();
			for (CustNoteDTO custNoteDTO : noteList) {
				custNoteMap.put(Integer.toString(custNoteDTO.getId()),
						custNoteDTO);
			}
		}
		SessionUtil.insertRow(SessionConstant.DepartmentNoteList.value(),
				sessCustNo, custNoteMap);
		
		this.department = departmentService.getDepartment(id);
		//以下为下拉框数据源
		List<DepartmentFunction> functionList = this.departmentService.getAllDepartmentFunction();
		Struts2Util.getRequest().setAttribute("functionList", functionList);
		return "department_edit";
	}

	/**
	 * 进入department的新增页面
	 * @return
	 */
	public String add() {
		List<DepartmentFunction> functionList = this.departmentService.getAllDepartmentFunction();
		Struts2Util.getRequest().setAttribute("functionList", functionList);
		return "department_add";
	}

	/**
	 * 利用Ajax请求保存Department, 并返回客户端相应信息. 输入Department
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			//对session中的instructions进行处理
			Map<String, CustNoteDTO> noteMap = null;
			List<CustNoteDTO> noteList = new ArrayList<CustNoteDTO>();
			noteMap = (Map<String, CustNoteDTO>) SessionUtil.getRow(
					SessionConstant.DepartmentNoteList.value(), sessCustNo);
			if (noteMap != null) {
				for (Entry<String, CustNoteDTO> entry : noteMap.entrySet()) {
					CustNoteDTO tmpDTO = entry.getValue();
					if (tmpDTO.getId()==null || tmpDTO.getId() == 0) {
						tmpDTO.setId(null);
					}
					noteList.add(tmpDTO);
				}			
			}	
			
			this.departmentService.saveDepartment(noteList, this.department, this.billing, SessionUtil
					.getUserId());
			rt.put("message", "Save Department sucessfully !");
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
	 * @author wangsf 显示增加 Department Note 输入: id, sessNoteId, sessCustNo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String showInstruction() throws Exception {
		if (!StringUtils.isEmpty(sessNoteId)) {
			noteDTO = (CustNoteDTO) SessionUtil.getOneRow(
					SessionConstant.DepartmentNoteList.value(),
					sessCustNo, sessNoteId);
		}		
		return "show_instruction";
	}
	
	/**
	 * 保存Instruction note 输入: sessCustNo等
	 * @author wangsf
	 * @serialData 2011-02-28
	 * @return
	 * @throws Exception
	 */
	public String saveInstruction() throws Exception {
		List<NoteDocument> newNoteDocumentList = new ArrayList<NoteDocument>();
		List<Integer> newDelDocIdList = null;
		List<NoteDocument> noteDocumentList = null;
		try {
			if (sessNoteId == null || sessNoteId.trim().length() < 1 || sessNoteId.equals("0")) {
				// 新建
				sessNoteId = SessionUtil.generateTempId();
				noteDocumentList = new ArrayList<NoteDocument>();
			} else {
				// 编辑
				CustNoteDTO sessNoteDTO = (CustNoteDTO) SessionUtil.getOneRow(
						SessionConstant.DepartmentNoteList.value(),
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
					uploadFileName.set(i, SessionUtil.generateTempId() + "_"
							+ srcFileName);
					nd.setFilePath("organization_notes/"
							+ uploadFileName.get(i));
					nd.setDocName(srcFileName);
					newNoteDocumentList.add(nd);
				}
			}
			noteDTO.setDocumentList(newNoteDocumentList);
			noteDTO.setDelDocIdList(newDelDocIdList);
			SessionUtil.updateOneRow(SessionConstant.DepartmentNoteList
					.value(), this.sessCustNo, sessNoteId, noteDTO);
			//上传文件
			fileService.uploadFile(upload, uploadContentType, uploadFileName,
					"organization_notes");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Struts2Util.renderText("<pre>" + sessNoteId + "</pre>");// ajaxForm方式提交返回需要用html标记包含进去.
		return null;
	}
	
	/**
	 * 进入Department Billing页面.
	 * @return
	 */
	public String showBilling() {
		billing = this.departmentService.getBilling(this.id);
		return "department_billing";
	}

	/**
	 * 进入Customer标签页面.
	 * @return
	 */
	public String showCustomerTabIndex() {
		return "customer_index";
	}
	
	/**
	 * 进入Customers标签的子标签Contacts页面
	 * 输入id(作为deptId)
	 * @return
	 */
	public String showContact() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<Contact> pagerUtil = new PagerUtil<Contact>();
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
		contactPage = this.contactService.searchByDept(contactPage, this.id);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				contactPage);		
		return "contact_list";
	}
	
	/**
	 * 进入Customers标签的子标签Accounts页面
	 * @return
	 */
	public String showAccount() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<Customer> pagerUtil = new PagerUtil<Customer>();
		this.customerPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!customerPage.isOrderBySetted()) {
			customerPage.setOrderBy("custNo");
			customerPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		if (customerPage.getPageSize() == null
				|| customerPage.getPageSize().intValue() < 1) {
			customerPage.setPageSize(20);
		}
		customerPage = this.customerService.searchByDept(customerPage, this.id);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				customerPage);
		return "account_list";
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public Page<Departments> getDepartmentPage() {
		return departmentPage;
	}

	public void setDepartmentPage(Page<Departments> departmentPage) {
		this.departmentPage = departmentPage;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public DepartmentBilling getBilling() {
		return billing;
	}

	public void setBilling(DepartmentBilling billing) {
		this.billing = billing;
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

	public Page<Contact> getContactPage() {
		return contactPage;
	}

	public void setContactPage(Page<Contact> contactPage) {
		this.contactPage = contactPage;
	}

	public Page<Customer> getCustomerPage() {
		return customerPage;
	}

	public void setCustomerPage(Page<Customer> customerPage) {
		this.customerPage = customerPage;
	}

}
