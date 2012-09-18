package com.genscript.gsscm.manufacture.web;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.genscript.gsscm.manufacture.dto.*;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.constant.WorkOrderType;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.PdfUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.manufacture.service.WorkOrderEntryService;
import com.genscript.gsscm.manufacture.service.WorkOrderProcService;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.system.dto.MailLogDTO;
import com.genscript.gsscm.system.service.MailLogService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "search_task_from", location = "manufacture/workorder_proc_task_search.jsp"),
		@Result(name = "search_from", location = "manufacture/workorder_proc_search.jsp"),
		@Result(name = "workorder_proc_result", location = "manufacture/workorder_proc_result.jsp"),
		@Result(name = "workorder_proc_task_result", location = "manufacture/workorder_proc_task_result.jsp"),
		@Result(name="generate_qc_batch",location="manufacture/generate_qc_batch.jsp"),
		@Result(name="generate_qc_batch_error",location="manufacture/generate_qc_batch_error.jsp"),
		@Result(name="create_labels",location="manufacture/create_labels.jsp"),
		@Result(name="print_labels",location="manufacture/print_labels.jsp"),
		@Result(name="protein_label",location="manufacture/protein_label.jsp"),
		@Result(name="oligo_label",location="manufacture/oligo_label.jsp"),
		@Result(name="polyclonal_antibody_label",location="manufacture/polyclonal_antibody_label.jsp"),
		@Result(name="monoclonal_antibody_label_init",location="manufacture/monoclonal_antibody_label_init.jsp"),
		@Result(name="monoclonal_antibody_label_edit",location="manufacture/monoclonal_antibody_label_edit.jsp"),
		@Result(name="batch_order_processing",location="manufacture/processing_order_batch.jsp"),
		@Result(name="assignment_workGroup",location="manufacture/assignment_workGroup.jsp"),
        @Result(name="show_batch_upload",location="manufacture/show_batch_upload.jsp"),
		@Result(name="workOrder_operation_operate_main",location="manufacture/workOrder_operation_operate_main.jsp"),
		@Result(name="workOrder_excel_list",location="manufacture/workOrder_excel_list.jsp"),
		@Result(name="mail_edit",location="manufacture/supply_cust_mail_edit.jsp"),
		@Result(name="wo_peptide_info_list",location="manufacture/wo_peptide_info_list.jsp")
		
		
})
public class WorkorderProcAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8362147274629373630L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ServService servService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private FileService fileService;
	@Autowired
	private SetupService setupService;
	@Autowired
	private WorkOrderEntryService workOrderEntryService;
	@Autowired
	private WorkOrderProcService workOrderProcService;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private MailLogService mailLogService;
	@Autowired
	private CustomerService customerService;

	private List<RouteClassDTO> classList;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;	
	private List<WorkCenter> workCenterList;
	private List<WorkGroup> workGroupList;
	private Page<WorkOrder> workOrderPage;
	private String orderNoStrs;//要打包的所有订单的订单号
	private List<WorkOrderDTO> workOrderList;//所有要打包的订单列表
	private WoBatcheDTO woBatcheDto;
	
	private List<PeptideTemplateDTO> peptideTempList;
	
	private String workOrderStatus;
	private String selectWorkOrderStatus;
	private String comment;
	private String saveStatus;
	private Integer centerId;
	private String roleName;
	
	private String reason;//取消订单原因
	
	//print labels
	private Integer orderNo;
	private ProteinLabelsDTO proteinLabelsDTO;
	private String allChoiceVal;
	private String[] orderNoArray;
	private String[] labelArray;
	private String antibody;
	private String cloneIds;
	
	//send mail
	private MailLogDTO workOrderNote;
	private Document wordDoc;
	private Integer sessWorkOrderNo;
	private WorkOrderDTO workOrder;
	private String fileName;
	private String filePath;
	
	//flg
	private Integer wo_peptide_info_look;
	private Integer wo_sendMail_flag;
	private Integer customFlag;
	
	
	/**
	 * 进入WorkOrder Processing的主页面
	 * 
	 * @return
	 */
	public String search() {
		try {
			this.classList = new ArrayList<RouteClassDTO>();
			List<ProductClass> pdtClassList = this.productService
					.getAllProductClass();
			for (ProductClass pdtClass : pdtClassList) {
				RouteClassDTO dto = new RouteClassDTO();
				dto.setType("Product - " + pdtClass.getName());
				dto.setValue("PRODUCT-" + pdtClass.getClsId());
				this.classList.add(dto);
			}
			List<ServiceClass> servClassList = this.servService
					.getAllServiceClass();
			for (ServiceClass servClass : servClassList) {
				RouteClassDTO dto = new RouteClassDTO();
				dto.setType("Service - " + servClass.getName());
				dto.setValue("SERVICE-" + servClass.getClsId());
				this.classList.add(dto);
			}
			
//			List<DropDownListName> listName = new ArrayList<DropDownListName>();
//			listName.add(DropDownListName.WORK_ORDER_STATUS);
//			dropDownMap = publicService.getDropDownMap(listName);
			
			this.workCenterList = this.setupService.getAllWorkCenter(Constants.ROLE_WOPROCESS_MANAGER);
			if(workCenterList!=null) {
				for(WorkCenter workCenter:workCenterList) {
					if(workCenter.getName().toLowerCase().equals("peptide department")) {
						wo_peptide_info_look = 1;
					}
					if(workCenter.getName().toLowerCase().equals("antibody department")) {
						wo_sendMail_flag = 1;
					}
				}
			}
			if(workCenterList!=null&&workCenterList.size()>0) {
				workGroupList = this.setupService.getGroupListByCenter(workCenterList.get(0).getId(),Constants.ROLE_WOPROCESS_MANAGER);
			}
			
		} catch(Exception e ) {
			e.printStackTrace();
		}
		
		return "search_from";
	}

	public String searchTask () {
		try {
			this.classList = new ArrayList<RouteClassDTO>();
			List<ProductClass> pdtClassList = this.productService
					.getAllProductClass();
			for (ProductClass pdtClass : pdtClassList) {
				RouteClassDTO dto = new RouteClassDTO();
				dto.setType("Product - " + pdtClass.getName());
				dto.setValue("PRODUCT-" + pdtClass.getClsId());
				this.classList.add(dto);
			}
			List<ServiceClass> servClassList = this.servService
					.getAllServiceClass();
			for (ServiceClass servClass : servClassList) {
				RouteClassDTO dto = new RouteClassDTO();
				dto.setType("Service - " + servClass.getName());
				dto.setValue("SERVICE-" + servClass.getClsId());
				this.classList.add(dto);
			}
			
//			List<DropDownListName> listName = new ArrayList<DropDownListName>();
//			listName.add(DropDownListName.WORK_ORDER_STATUS);
//			dropDownMap = publicService.getDropDownMap(listName);
			
			this.workCenterList = this.setupService.getAllWorkCenter(Constants.ROLE_WOASSIGN_MANAGER);
			if(workCenterList!=null&&workCenterList.size()>0) {
				workGroupList = this.setupService.getGroupListByCenter(workCenterList.get(0).getId(),Constants.ROLE_WOPROCESS_MANAGER);
				if(workGroupList==null) {
					workGroupList = new ArrayList<WorkGroup>();
				}
				WorkGroup workGroup = new WorkGroup();
				workGroup.setName("All");
				workGroupList.add(0,workGroup);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "search_task_from";
	}

	/**
	 * 分页查找
	 */
	public String list() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<WorkOrder> pagerUtil = new PagerUtil<WorkOrder>();
			workOrderPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!workOrderPage.isOrderBySetted()) {
				workOrderPage.setOrderBy("soNo,soItemNo,creationDate,orderNo");
				workOrderPage.setOrder(Page.DESC+","+Page.ASC+","+Page.DESC+","+Page.ASC);
			}
			// 设置默认每页显示记录条数
			workOrderPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			String production = Struts2Util.getParameter("production");
			if (StringUtils.isNotBlank(production)) {
				String[] typeAndClsId = production.split("-");		
				PropertyFilter typeFilter = new PropertyFilter("EQS_itemType", typeAndClsId[0]);
				PropertyFilter clsFilter = new PropertyFilter("EQI_clsId", typeAndClsId[1]);
				filters.add(typeFilter);
				filters.add(clsFilter);
			}
			String startOrderDate = Struts2Util.getParameter("start_orderDate");
			String endOrderDate = Struts2Util.getParameter("end_orderDate");
			if(StringUtils.isNotBlank(endOrderDate)) {
				PropertyFilter endDate = new PropertyFilter("EQS_endOrderDate",endOrderDate);
				filters.add(endDate);
			}
			if(StringUtils.isNotBlank(startOrderDate)) {
				PropertyFilter startDate = new PropertyFilter("EQS_startOrderDate",startOrderDate);
				filters.add(startDate);
			}
			workOrderPage = this.workOrderEntryService.searchWorkOrderPage(workOrderPage, filters,"QC",Constants.ROLE_WOPROCESS_MANAGER,true);
			workOrderPage = this.workOrderEntryService.getDescription(workOrderPage);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					workOrderPage);
		} catch (Exception ex) {
			return "workorder_proc_result";
		}
		return "workorder_proc_result";
	}
	
	public String taskList () {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<WorkOrder> pagerUtil = new PagerUtil<WorkOrder>();
			workOrderPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!workOrderPage.isOrderBySetted()) {
				workOrderPage.setOrderBy("soNo,soItemNo,creationDate,orderNo");
				workOrderPage.setOrder(Page.DESC+","+Page.ASC+","+Page.DESC+","+Page.ASC);
			}
			// 设置默认每页显示记录条数
			workOrderPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			String production = Struts2Util.getParameter("production");
			if (StringUtils.isNotBlank(production)) {
				String[] typeAndClsId = production.split("-");		
				PropertyFilter typeFilter = new PropertyFilter("EQS_itemType", typeAndClsId[0]);
				PropertyFilter clsFilter = new PropertyFilter("EQI_clsId", typeAndClsId[1]);
				filters.add(typeFilter);
				filters.add(clsFilter);
			}
			workOrderPage = this.workOrderEntryService.searchWorkOrderPage(workOrderPage, filters,"QD",Constants.ROLE_WOASSIGN_MANAGER,false);
			workOrderPage = this.workOrderEntryService.getDescription(workOrderPage);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					workOrderPage);
		} catch (Exception ex) {
            ex.printStackTrace();
			return "workorder_proc_result";
		}
		return "workorder_proc_task_result";
	}

    //add by zhanghuibin
    public String taskListForExcel () {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<WorkOrder> pagerUtil = new PagerUtil<WorkOrder>();
			workOrderPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!workOrderPage.isOrderBySetted()) {
				workOrderPage.setOrderBy("soNo,creationDate");
				workOrderPage.setOrder(Page.DESC+","+Page.DESC);
			}
			// 设置默认每页显示记录条数
			workOrderPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			String production = Struts2Util.getParameter("production");
			if (StringUtils.isNotBlank(production)) {
				String[] typeAndClsId = production.split("-");
				PropertyFilter typeFilter = new PropertyFilter("EQS_itemType", typeAndClsId[0]);
				PropertyFilter clsFilter = new PropertyFilter("EQI_clsId", typeAndClsId[1]);
				filters.add(typeFilter);
				filters.add(clsFilter);
			}
			ArrayList<WorkOrderExcelDTO> workList = this.workOrderEntryService.searchWorkOrderPageForExcel(workOrderPage, filters);
			ServletActionContext.getRequest().setAttribute("workList", workList);
		} catch (Exception ex) {
            ex.printStackTrace();
			return "workOrder_excel_list";
		}
		return "workOrder_excel_list";
	}
	
	public String deleteWo() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			rt.put("message", this.workOrderProcService.deleteWo(allChoiceVal, reason,roleName));
		} catch(Exception ex) {
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
	 * 显示workOrderOperation主窗口
	 */
	public String showWorkOrderOperationMain() {
		try {
			List<DropDownListName> listName = new ArrayList<DropDownListName>();
			listName.add(DropDownListName.WO_OPERATION_STATUS);
			dropDownMap = publicService.getDropDownMap(listName);
			this.workCenterList = this.setupService.getAllWorkCenter(Constants.ROLE_WOPROCESS_MANAGER);
			if(workCenterList!=null&&workCenterList.size()>0) {
				workGroupList = this.setupService.getGroupListByCenter(workCenterList.get(0).getId(),Constants.ROLE_WOPROCESS_MANAGER);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "workOrder_operation_operate_main";
	}
	
	/**
	 * 显示订单打包操作窗口
	 * @author lizhang
	 * @return
	 */
	public String showOperBatch() {
		woBatcheDto = new WoBatcheDTO();
		workOrderList = new ArrayList<WorkOrderDTO>();
		if(orderNoStrs!=null&&!("").equals(orderNoStrs)) {
			String itemType = null;
			Integer clsId = null;
			woBatcheDto.setOrderNoStr(orderNoStrs);
			for(String orderNo:orderNoStrs.split(",")) {
				WorkOrderDTO workOrderDto = this.workOrderEntryService.getWorkOrder(Integer.parseInt(orderNo));
				if(workOrderDto!=null) {
					if(itemType==null||clsId==null) {
						itemType = workOrderDto.getItemType();
						clsId = workOrderDto.getClsId();
					} else if(!itemType.equals(workOrderDto.getItemType())||!clsId.equals(workOrderDto.getClsId())){
						return "generate_qc_batch_error";//打包出错，订单类型不匹配
					}
					workOrderList.add(workOrderDto);
				}
			}
			String batchType = null;
			if(WorkOrderType.PRODUCT.value().equals(itemType)) {
				ProductClass productClass = productService.getProductClass(clsId);
				batchType = productClass==null?null:productClass.getName();
			} else if(WorkOrderType.SERVICE.value().equals(itemType)) {
				ServiceDTO serviceDto = servService.getServDetail(clsId);
				batchType = serviceDto==null?null:serviceDto.getName();
			}
			woBatcheDto.setBatchType(batchType);
			woBatcheDto.setBatchFunction("QC");
			woBatcheDto.setStatus("Delivered");
		}
		
		return "generate_qc_batch";
	}
	
	/**
	 * 确认打包
	 * @author lizhang
	 * @return
	 */
	public String confrim() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			rt.put("message", this.workOrderProcService.saveWoBatche(woBatcheDto));
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
	 * 校验workOrderNo的一致性
	 * @author zhangyong
	 */
	public void checkWorkOrderNo () {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String,List<WorkOrder>> wkstatusMap = workOrderProcService.checkWorkOrderNos(orderNoStrs);
			if (wkstatusMap != null && wkstatusMap.size() == 1) {
				rt.put("status", "Success");
			}
		} catch (Exception ex) {
			rt.put("hasException", true);
		}
		Struts2Util.renderJson(rt);
	}
	
	/**
	 * showBatchOrder
	 * @return
	 * @author zhangyong
	 */
	public String showBatchOrder () {
		try {
			Map<String,List<WorkOrder>> wkstatusMap = 
				workOrderProcService.checkWorkOrderNos(orderNoStrs);
			if (wkstatusMap != null && wkstatusMap.size() == 1) {
				for (String wkstatus : wkstatusMap.keySet()) {
					workOrderStatus = wkstatus;
				}
			}
		} catch (Exception ex) {
		}
		return "batch_order_processing";
	}
	
	/**
	 * saveBatchOrderProcess
	 * @author zhangyong
	 */
	public String saveBatchOrderProcess () {
		try {
			boolean isSuccess = workOrderProcService.saveBatchOrderProcess(orderNoStrs, 
					workOrderStatus, selectWorkOrderStatus, comment);
			if (isSuccess) {
				saveStatus = "success";
			} else {
				saveStatus = "failure";
			}
		} catch (Exception ex) {
			saveStatus = "failure";
		}
		return "batch_order_processing";
	}
	
	/**
	 * 显示订单打印窗口
	 * @return
	 */
	public String showPrintLabel() {
		orderNoStrs.replace("-", ",");
		return "create_labels";
	}
	
	/**
	 * 创建订单打印列表页
	 * @return
	 */
	public String createPrintLabel() {
		return "print_labels";
	}
	
	/**
	 * 显示订单标签窗口
	 */
	public String printLabels() {
		workOrderList = workOrderProcService.getSelWOLabel(allChoiceVal);
		return "print_labels";
	}
	

	
	/**
	 * 显示生成protein标签的页面
	 */
	public String proteinEdit() {
		try {
			proteinLabelsDTO = workOrderProcService.createProteinLabelsDTO(orderNo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "protein_label";
	}
	
	/**
	 * 显示生成oligo标签的页面
	 */
	public String oligoEdit() {
		antibody = workOrderProcService.createOligoLabels(orderNo);
		return "oligo_label";
	}
	
	/**
	 * 显示生成antibody标签的页面(多抗)
	 */
	public String polyclonalAntibodyEdit() {
		antibody = this.workOrderProcService.getLabelToMonoclonal(orderNo);
		return "polyclonal_antibody_label";
	}
	
	/**
	 * 显示生成monoclonal antibody标签的页面（单抗）
	 */
	public String monoclonalAntibodyInit() {
		return "monoclonal_antibody_label_init";
	}
	
	/**
	 * 显示生成antibody标签的页面（单抗）
	 */
	public String monoclonalAntibodyEdit() {
		antibody = this.workOrderProcService.getInfoToAnti(orderNo,cloneIds);
		return "monoclonal_antibody_label_edit";
	}
	
	/**
	 * 创建protein标签
	 */
	public String createProteinLabel() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String label = this.createLabel();
		rt.put("label", label);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 创建monoclonal antibody标签(单抗)
	 */
	public String createMonoclonalLabel() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String label = this.workOrderProcService.getInfoToAnti(orderNo,cloneIds);
		rt.put("label", label);
		Struts2Util.renderJson(rt);
		return null;
	}
	/**
	 * 
	 * @return
	 */
	private String createLabel() {
		StringBuffer label =  new StringBuffer("");
		label.append("Protein:").append(proteinLabelsDTO.getProteinName()!=null?proteinLabelsDTO.getProteinName():"")
					.append(",");
		StringBuffer label1 = new StringBuffer("");
		label1.append(proteinLabelsDTO.getFusedWith());
		int i=0;
		for(i=0;i<26-proteinLabelsDTO.getFusedWith().length()-String.valueOf(proteinLabelsDTO.getFinalPrep()).length()-2;i++) {
			label1.append("&nbsp;");
		}
		label1.append(proteinLabelsDTO.getFinalPrep()).append("ml,").append(proteinLabelsDTO.getConcentration()).append("mg/ml");
		for(i=0;i<26-String.valueOf(proteinLabelsDTO.getConcentration()).length()-13-String.valueOf(proteinLabelsDTO.getPurity()).length();i++) {
			label1.append("&nbsp;");
		}
		label1.append("Purity>").append(proteinLabelsDTO.getPurity()).append("%,Lot: ").append(proteinLabelsDTO.getLotNo());
		for(i=0;i<40-19-proteinLabelsDTO.getLotNo().length();i++) {
			label1.append("&nbsp;");
		}
		label1.append("&nbsp;");
		label1.append("Store at:-80℃");
		label.append(label1).append("</br>");
		if(proteinLabelsDTO.isLyophilizedFlg()) {
			label.append("Protein of ").append(proteinLabelsDTO.getOrderNo()).append("_").append(proteinLabelsDTO.getItemNo()).append(",");
		} else {
			label.append("Protein solution,");
		}
		label.append(label1).append("</br>");
		label.append(proteinLabelsDTO.getPlasmid2()).append("-").append(proteinLabelsDTO.getProteinName()).append(",").append("~")
				.append(proteinLabelsDTO.getPlasmid4()).append("ng/ul");
		for(i=0;i<26-8-String.valueOf(proteinLabelsDTO.getPlasmid4()).length()-String.valueOf(proteinLabelsDTO.getPlasmid3()).length();i++) {
			label.append("&nbsp;");
		}
		label.append(proteinLabelsDTO.getPlasmid3()).append("ul,Lot: ").append(proteinLabelsDTO.getPlasmid5()).append("/P")
				.append(proteinLabelsDTO.getPlasmid6());
		for(i=0;i<40-21-proteinLabelsDTO.getPlasmid5().length()-proteinLabelsDTO.getPlasmid6().length();i++) {
			label.append("&nbsp;");
		}
		label.append("&nbsp;");
		label.append("Store at:-20℃");
		return label.toString();
	}
	
	/**
	 * 订单标签生成pdf文件下载
	 */
	public void print() {
		workOrderList = new ArrayList<WorkOrderDTO>();
		if(orderNoArray!=null&&labelArray!=null&&orderNoArray.length==labelArray.length) {
			for(int i=0;i<orderNoArray.length;i++) {
				WorkOrderDTO workOrderDTO = new WorkOrderDTO();
				workOrderDTO.setOrderNo(Integer.parseInt(orderNoArray[i]));
				if(labelArray[i].indexOf("<a href")!=-1||StringUtils.isEmpty(labelArray[i])) {
					continue;
				}
				workOrderDTO.setLabels(labelArray[i]);
				workOrderList.add(workOrderDTO);
			}
		}
		
//		workOrderList = workOrderProcService.getSelWOLabel(allChoiceVal);
		String responsePdfName = "workorder_label.txt";
		String pdfPath = workOrderProcService.createTxt(workOrderList);
		List<String> attachmentNameList = new ArrayList<String>();
//		attachmentNameList.add(pdfPath);
		String mailTo = "";
		User user = privilegeService.findUserByUserId(SessionUtil.getUserId());
		if(user!=null&&user.getEmployee()!=null) {
			Employee employee = this.privilegeService.findEmployeeById(user.getEmployee().getEmployeeId());
			if(employee!=null) {
				mailTo = employee.getEmail();
			}
		}
		String subject = "Create Labels";
		if(StringUtils.isNotEmpty(mailTo)) {
			mimeMailService.sendMail(mailTo, subject, "", attachmentNameList);
		}
		if(pdfPath!=null) {
			PdfUtils.downloadFile(pdfPath, responsePdfName);
			File file = new File(pdfPath);
			file.deleteOnExit();
		}
	}
	
	/**
	 * 显示peptide订单信息页面
	 */
	public String peptideInfoShow() {
		try {
			peptideTempList = this.workOrderEntryService.getSelWOPeptideInfo(allChoiceVal);
			wordDoc = this.workOrderProcService.createExcel(peptideTempList, this.fileService.getUploadPath()+"quote_notes/");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return "wo_peptide_info_list";
	}
	
	/**
	 * 下载Excel
	 */
	public void downLoadExcel() {
		
	}
	
	/**
	 * workcenter联动workgroup
	 * @return
	 */
	public String workCenterSelect() {
		
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.workGroupList = this.setupService.getGroupListByCenter(centerId,roleName);
			rt.put("workGroupList", workGroupList);
		}  catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return NONE;
	}
	
	/**
	 * 检测work order的work center 的一致性
	 */
	public String checkWorkOrder() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			rt.put("message", workOrderProcService.checkWoWorkCenter(orderNoStrs));
		} catch (Exception ex) {
			rt.put("hasException", true);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 显示批量给work order分配组的对话框
	 * @return
	 */
	public String showAssignGroupDlg() {
		try {
			this.workCenterList = this.setupService.getAllWorkCenter(Constants.ROLE_WOASSIGN_MANAGER);
			this.workGroupList = this.setupService.getGroupListByCenter(centerId,Constants.ROLE_WOASSIGN_MANAGER);
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return "assignment_workGroup";
	}

    /*
    * add by zhanghuibin
    * Upload File的显示页面
    * */
    public String showBatchUpload(){
        //取得上传文件夹的路径
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("application.properties");
        try{
            Properties properties = new Properties();
            properties.load(url.openStream());
            String uploadPath = properties.getProperty("order_upload");
            Struts2Util.getRequest().setAttribute("uploadPath", uploadPath);
        }catch (Exception e){
            Struts2Util.getRequest().setAttribute("uploadPath", "");
        }
        return "show_batch_upload";
    }
    
    /**
	 * 弹出发送邮件页面
	 * @author lizhang
	 */
	public String sendMail() {
		try {
			workOrderNote = new MailLogDTO();
			workOrder = this.workOrderProcService.getWorkOrder(sessWorkOrderNo);
			workOrderNote.setType(OrderInstructionType.SUPPLY_CONFIRM_EMAIL.value());
			workOrderNote.setSubject("Order Status Report");
			workOrderNote.setRecipient(workOrder.getEmail());
			wordDoc = this.workOrderProcService.createAttachment(sessWorkOrderNo,this.fileService.getUploadPath()+"quote_notes/");
			
		} catch(Exception w) {
			w.printStackTrace();
		}
		return "mail_edit";
	}
	
	/**
	 * 保存邮件
	 */
	public String saveMail() {
		try {
			workOrderNote.setRefId(sessWorkOrderNo);
			workOrderNote.setCreateUser(SessionUtil.getUserName());
			workOrderNote.setSendDate(new Date());
			workOrderNote.setInstructionDate(workOrderNote.getSendDate());
			
			// 更新documentList列表里的内容(追加要上传的document对象).
			List<Document> documentList = new ArrayList<Document>();
			// 重新关联并保存或更新Quote模块下Instruction/Note标签.
			if(wordDoc!=null) {
				documentList.add(wordDoc);
			}
			workOrderNote.setDocumentList(documentList);
			workOrderNote.setFunctionName("Manufacturing: Order Status Report Email");
			workOrderNote.setRefType("work_order.order_no ");
			this.mailLogService.saveMail(workOrderNote);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Struts2Util.renderText("Add success");
		return null;
	}
	
	/**
	 * 下载附件
	 */
	public void  downloadFile() {
		PdfUtils.downloadFile(filePath, fileName);
	}

	public List<RouteClassDTO> getClassList() {
		return classList;
	}


	public void setClassList(List<RouteClassDTO> classList) {
		this.classList = classList;
	}


	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}


	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}


	public List<WorkCenter> getWorkCenterList() {
		return workCenterList;
	}


	public void setWorkCenterList(List<WorkCenter> workCenterList) {
		this.workCenterList = workCenterList;
	}


	public Page<WorkOrder> getWorkOrderPage() {
		return workOrderPage;
	}


	public void setWorkOrderPage(Page<WorkOrder> workOrderPage) {
		this.workOrderPage = workOrderPage;
	}


	public String getOrderNoStrs() {
		return orderNoStrs;
	}


	public void setOrderNoStrs(String orderNoStrs) {
		this.orderNoStrs = orderNoStrs;
	}


	public List<WorkOrderDTO> getWorkOrderList() {
		return workOrderList;
	}


	public void setWorkOrderList(List<WorkOrderDTO> workOrderList) {
		this.workOrderList = workOrderList;
	}


	public WoBatcheDTO getWoBatcheDto() {
		return woBatcheDto;
	}


	public void setWoBatcheDto(WoBatcheDTO woBatcheDto) {
		this.woBatcheDto = woBatcheDto;
	}


	public String getWorkOrderStatus() {
		return workOrderStatus;
	}


	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}


	public String getSelectWorkOrderStatus() {
		return selectWorkOrderStatus;
	}


	public void setSelectWorkOrderStatus(String selectWorkOrderStatus) {
		this.selectWorkOrderStatus = selectWorkOrderStatus;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getSaveStatus() {
		return saveStatus;
	}


	public void setSaveStatus(String saveStatus) {
		this.saveStatus = saveStatus;
	}

	public List<WorkGroup> getWorkGroupList() {
		return workGroupList;
	}

	public void setWorkGroupList(List<WorkGroup> workGroupList) {
		this.workGroupList = workGroupList;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public String getAllChoiceVal() {
		return allChoiceVal;
	}

	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public ProteinLabelsDTO getProteinLabelsDTO() {
		return proteinLabelsDTO;
	}

	public void setProteinLabelsDTO(ProteinLabelsDTO proteinLabelsDTO) {
		this.proteinLabelsDTO = proteinLabelsDTO;
	}

	public String[] getOrderNoArray() {
		return orderNoArray;
	}

	public void setOrderNoArray(String[] orderNoArray) {
		this.orderNoArray = orderNoArray;
	}

	public String[] getLabelArray() {
		return labelArray;
	}

	public void setLabelArray(String[] labelArray) {
		this.labelArray = labelArray;
	}

	public String getAntibody() {
		return antibody;
	}

	public void setAntibody(String antibody) {
		this.antibody = antibody;
	}

	public String getCloneIds() {
		return cloneIds;
	}

	public void setCloneIds(String cloneIds) {
		this.cloneIds = cloneIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public MailLogDTO getWorkOrderNote() {
		return workOrderNote;
	}

	public void setWorkOrderNote(MailLogDTO workOrderNote) {
		this.workOrderNote = workOrderNote;
	}

	public Document getWordDoc() {
		return wordDoc;
	}

	public void setWordDoc(Document wordDoc) {
		this.wordDoc = wordDoc;
	}

	public Integer getSessWorkOrderNo() {
		return sessWorkOrderNo;
	}

	public void setSessWorkOrderNo(Integer sessWorkOrderNo) {
		this.sessWorkOrderNo = sessWorkOrderNo;
	}

	public WorkOrderDTO getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrderDTO workOrder) {
		this.workOrder = workOrder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getWo_peptide_info_look() {
		return wo_peptide_info_look;
	}

	public void setWo_peptide_info_look(Integer wo_peptide_info_look) {
		this.wo_peptide_info_look = wo_peptide_info_look;
	}

	public List<PeptideTemplateDTO> getPeptideTempList() {
		return peptideTempList;
	}

	public void setPeptideTempList(List<PeptideTemplateDTO> peptideTempList) {
		this.peptideTempList = peptideTempList;
	}

	public Integer getCustomFlag() {
		return customFlag;
	}

	public void setCustomFlag(Integer customFlag) {
		this.customFlag = customFlag;
	}

	public Integer getWo_sendMail_flag() {
		return wo_sendMail_flag;
	}

	public void setWo_sendMail_flag(Integer wo_sendMail_flag) {
		this.wo_sendMail_flag = wo_sendMail_flag;
	}


}
