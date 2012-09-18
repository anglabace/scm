package com.genscript.gsscm.manufacture.web;

import static java.io.File.separator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.WorkOrderStatus;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.PdfUtils;
import com.genscript.gsscm.common.util.ReadRTF;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.inventory.service.InventoryService;
import com.genscript.gsscm.manufacture.dto.PeptideTemplateDTO;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.QaClerk;
import com.genscript.gsscm.manufacture.entity.QaGroup;
import com.genscript.gsscm.manufacture.entity.Route;
import com.genscript.gsscm.manufacture.entity.WoOperationComponent;
import com.genscript.gsscm.manufacture.entity.WoOperationResource;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.entity.WorkOrderLot;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;
import com.genscript.gsscm.manufacture.service.DsPlateService;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.manufacture.service.WorkOrderEntryService;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.MfgOrderDTO2;
import com.genscript.gsscm.order.entity.MfgOrderItem;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderItemCenterBean;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.service.OrderItemService;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.product.dto.ProductDTO;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.product.entity.ShipCondition;
import com.genscript.gsscm.product.entity.StorageCondition;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.entity.ServiceListBean;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;
import com.genscript.gsscm.serv.entity.ServiceStorageCondition;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.system.dto.MailLogDTO;
import com.genscript.gsscm.system.dto.MailTemplatesDTO;
import com.genscript.gsscm.system.entity.MailGroup;
import com.genscript.gsscm.system.entity.UpdateRequestLog;
import com.genscript.gsscm.system.service.MailLogService;
import com.genscript.gsscm.system.service.UpdateRequestLogService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "workorder_entry_list", location = "manufacture/workorder_entry_list.jsp"),
		@Result(name = "workorder_entry_detail", location = "manufacture/workorder_entry_detail.jsp"),
		@Result(name = "workorder_entry_add", location = "manufacture/workorder_entry_add.jsp"),
		@Result(name = "workorder_entry_task_add", location = "manufacture/workorder_entry_task_add.jsp"),
		@Result(name = "workorder_entry_select_order", location = "manufacture/workorder_entry_select_order.jsp"),
		@Result(name = "workorder_entry_select_product", location = "manufacture/workorder_entry_select_product.jsp"),
		@Result(name = "workorder_entry_productQa_reason", location = "manufacture/workorder_entry_productQa_reason.jsp"),
		@Result(name = "workorder_entry_documentQa_reason", location = "manufacture/workorder_entry_documentQa_reason.jsp"),
		@Result(name = "workorder_entry_show_productcondition", location = "manufacture/workorder_entry_show_productcondition.jsp"),
		@Result(name = "mail_edit", location = "manufacture/mail_edit.jsp"),
		@Result(name = "order_item_list", location = "manufacture/order_item_list.jsp"),
		@Result(name = "Generate_Batch_Work_Orders", location = "manufacture/Generate_Batch_Work_Orders.jsp"),
		@Result(name="work_group_assign",location= "manufacture/work_group_assign.jsp")
})
public class WorkorderEntryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8362147274629373630L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private SetupService setupService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private MailLogService mailLogService;
	@Autowired
	private SalesRepDao salesRepDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ServService servService;
	@Autowired
	private WorkOrderEntryService workOrderEntryService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private UpdateRequestLogService updateRequestLogService;
	@Autowired
	private DsPlateService dsPlateService;
	@Autowired
	private DozerBeanMapper dozer;
	private Integer id;
	private Page<WorkCenter> workCenterPage;
	private List<WorkGroup> groupList;
	private WorkCenter workCenter;
	private List<Warehouse> warehouseList;
	private List<UserDTO> superList;
	// 新增一个WorkOrder Entry.
	private WorkOrderDTO workOrder;
	private List<WorkCenter> workCenterList;
	private List<WorkGroup> workGroupList;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private Integer itemNo;
	private List<Route> routeList;
	private List<DropDownDTO> dropdownItemList;
	private String sessId;
	private List<QaGroup> qaGroupList;
	private List<QaClerk> qaClerkList;
	private List<QaClerk> qcClerkList;
	private List<PbDropdownListOptions> pdtDocTypeList;
	// 上传附件相关
	@Autowired
	private FileService fileService;
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	private String refType;
	private String delFilePath;
	private String filePath;
	private String fileName;

	private String operation_method;
	private Integer orderOrderNo;
	// 产品相关信息
	private ShipCondition shipCondition;
	private StorageCondition storageCondition;
	//邮件
	private String workGroupId;
	private String sessWorkOrderNo;
	private String contactDate;// 用于接受页面String，再转为Date
	private String scheduleDate;// 用于接受页面String，再转为Date
	private MailLogDTO workOrderNote;
	private List<MailTemplatesDTO> mailTmplList;
	private List<MailGroup> mailGroupList;
	private Document wordDoc;
	private List<Document> wordDocList;
	private String[] docNameArray;
	private String[] filePathArray;
	//generate batch work orders
	private boolean today;//是否今天
	private String fromDate;
	private String toDate;
	private List<OrderItemCenterBean> itemList;
	private String itemIds;
	private Long size;
	
	private String operStatus;
	private String workGroupAssignStr;
	private Integer centerId;
	private String workGroupNames;
	private String workGroupIds;
	private String orderNoStrs;
	
	private Integer orderNo;
	private Integer warehouseId;
	
	private Integer srcSoNo;
	//create lot no
	private Integer lotNoCount;
	private Integer addCount;
	private Date modifyDate;
	private String modifyUser;
	
	private WorkOrderLot workOrderLot;
	private String workOrderLotSessId;
	
	private Integer antibodyFlg;
	
	private String processFlag;
	
	private Integer plateId;

    private PeptideTemplateDTO peptideTemplateDTO;
    private String peptideTemplateFileName;
	
	
	/**
	 * 分页查找workcenter
	 */
	public String list() {
		workCenterPage = setupService.searchWorkCenterPage(workCenterPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",workCenterPage);
		return "workorder_entry_list";
	}
	/**
	 * 获得workcenter detail
	 * @return
	 */
	public String detail() {
		workCenter = this.setupService.getWorkCenter(this.id);
		return "workorder_entry_detail";
	}

	/**
	 * 进入Product QA Reason填写页面.
	 * 
	 * @return
	 */
	public String showProductQaReason() {
		return "workorder_entry_productQa_reason";
	}

	/**
	 * 进入Document QA Reason填写页面.
	 * 
	 * @return
	 */
	public String showDocumentQaReason() {
		return "workorder_entry_documentQa_reason";
	}





	private void addOrEdit() {
		this.warehouseList = this.inventoryService.getAllWarehouse();
		this.workCenterList = this.setupService.getAllWorkCenter();
//		qaGroupList = workOrderEntryService.getAllQaGroup(Constants.ROLE_QC_MANAGER);
		this.pdtDocTypeList = this.publicService
				.getDropdownList("PRODUCT_FILE_TYPE");
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.WORK_ORDER_SOURCE);
		listName.add(DropDownListName.WORK_ORDER_PRIORITY);
		listName.add(DropDownListName.WORK_ORDER_STATUS);
		listName.add(DropDownListName.QC_STATUS);
		listName.add(DropDownListName.WORK_OPERATION_UOM);
		listName.add(DropDownListName.WORK_ORDER_TYPE);
		dropDownMap = publicService.getDropDownMap(listName);
		
		modifyDate = new Date();
		modifyUser = SessionUtil.getUserName();

	}

	public String add() throws Exception {
		addOrEdit();
		this.routeList = this.setupService.findRouteForWO(id,2);
		this.sessId = SessionUtil.generateTempId();
		workOrder = new WorkOrderDTO();
		workOrder.setWorkCenterId(id);
		workOrder.setStatus("New");
		workOrder.setWarehouseId(2);//Nj wareHouse
		// 新增时itemList为空.
		this.dropdownItemList = new ArrayList<DropDownDTO>();
		this.workGroupList = this.setupService.getGroupListByCenter(id);
		if (workGroupList == null) {
			workGroupList = new ArrayList<WorkGroup>();
		} else {

		}
		this.qaGroupList = this.workOrderEntryService.getQaGroup(workOrder);
		// 为null时要给其赋值， 否则struts2标签访问时会出错.
		this.qaClerkList = new ArrayList<QaClerk>();
		this.qcClerkList = new ArrayList<QaClerk>();
		if(this.qaGroupList!=null&&this.qaGroupList.size()>0) {
			this.qcClerkList = this.workOrderEntryService.getClerkList(this.qaGroupList.get(0).getId());
		}
		
		// 页面s:select标签不能访问为null的属性.
		List<DropDownDTO> workCenterSuperList = new ArrayList<DropDownDTO>();
		WorkCenter workCenter = this.setupService.getWorkCenter(id);
		if (workCenter.getSupervisor() != null
				&& workCenter.getSuperName() != null) {
			DropDownDTO centerSuper = new DropDownDTO();
			centerSuper.setName(workCenter.getSuperName());
			centerSuper.setValue(workCenter.getSupervisor() + "");
			workCenterSuperList.add(centerSuper);
		}
		if(workCenter.getName().toLowerCase().equals("antibody department")||workCenter.getName().toLowerCase().equals("animal model department ")) {
			antibodyFlg = 1;
		}
		List<DropDownDTO> superList = new ArrayList<DropDownDTO>();
		workOrder.setWorkCenterSuperList(workCenterSuperList);
		workOrder.setWorkGroupSuperList(superList);
		workOrder.setCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		workOrder.setCreatedBy(SessionUtil.getUserId());
		workOrder.setActualStart(new java.sql.Date(new java.util.Date().getTime()));
		workOrder.setScheduleStart(new java.sql.Date(new java.util.Date().getTime()));
		if(routeList!=null&&routeList.size()>0) {
			for(Route route:this.routeList) {
				if("Y".equals(route.getDefaultFlag())) {
					this.workOrder.setStandardRoutine(route.getId());
					break;
				}
			}
			if(this.workOrder.getStandardRoutine()==null) {
				workOrder.setStandardRoutine(routeList.get(0).getId());
			}
		}
		List<WorkOrderLot> workOrderLotList = new ArrayList<WorkOrderLot>();
		workOrder.setWorkOrderLotList(workOrderLotList);
		SessionUtil.insertRow(SessionConstant.WorkOrderLot.value(),
				this.sessId, SessionUtil.convertList2Map(workOrderLotList, "id"));
		SessionUtil.insertRow(SessionConstant.WorkOrderLotTemp.value(),
				this.sessId, SessionUtil.convertList2Map(workOrderLotList, "id"));
		// 初始化session数据
		Map<String, ManuDocument> sessDocMap = new LinkedHashMap<String, ManuDocument>();
		// 因为sessMap可能在session中尚不存在, 所以这句必须要执行
		SessionUtil.insertRow(SessionConstant.WorkOrderDocument.value(),
				this.sessId, sessDocMap);
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(this.sessId, workOrder);
		return "workorder_entry_add";
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String edit() {
		try {
			// *********** Add By Zhang Yong Start *****************************//
			if (id != null && !("").equals(id)) {
				// 判断将要修改的单号是否正在被操作
				String editUrl = "workorder_entry!edit.action?id=" + id;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
			} else {
				// 释放application中的订单锁
				OrderLockRelease realeseOrderLock = new OrderLockRelease();
				realeseOrderLock.releaseOrderLock();
			}
			// *********** Add By Zhang Yong End *****************************//
			addOrEdit();
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			// 从Select Operation页面进入的， 要恢复之前的数据（借助session）
			if (Struts2Util.getParameter("referURL") != null
					&& Struts2Util.getParameter("referURL").equals("select")) {
				// 不进行清除Session等操作, 反而需要从session中取数据.
				
				this.workOrder = (WorkOrderDTO) session.get(this.sessId);
				List<ManuDocument> docList = new ArrayList<ManuDocument>();
				Map<String, ManuDocument> sessMap = (Map<String, ManuDocument>) SessionUtil
						.getRow(SessionConstant.WorkOrderDocument.value(),
								this.sessId);
				for (Entry<String, ManuDocument> entry : sessMap.entrySet()) {
					if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
						if (entry.getValue() == null) {// 执行了临时的删除操作
						} else {
							docList.add(entry.getValue());
						}
					} else {
						docList.add(entry.getValue());// 新增的
					}
				}
				this.workOrder.setDocumentList(docList);
				List<WorkOrderLot> workOrderLotList = new ArrayList<WorkOrderLot>();
				Map<String,WorkOrderLot> workOrderLotMap = (Map<String,WorkOrderLot>)SessionUtil.getRow(SessionConstant.WorkOrderLot.value(), this.sessId);
				for (Entry<String, WorkOrderLot> entry : workOrderLotMap.entrySet()) {
					if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
						if (entry.getValue() == null) {// 执行了临时的删除操作
						} else {
							workOrderLotList.add(entry.getValue());
						}
					} else {
						workOrderLotList.add(entry.getValue());// 新增的
					}
				}
				this.workOrder.setWorkOrderLotList(workOrderLotList);
				
			} else {
				this.workOrder = workOrderEntryService.getWorkOrder(id);
				this.sessId = Integer.toString(this.id);
				// 清除session
				SessionUtil.deleteRow(SessionConstant.WorkOrderOperation.value(),
						this.sessId);
				SessionUtil.deleteRow(SessionConstant.WorkOrderDocument.value(),
						this.sessId);
				SessionUtil.deleteRow(SessionConstant.WorkOrderLot.value(),
						this.sessId);
				SessionUtil.deleteRow(SessionConstant.WorkOrderLotTemp.value(),
						this.sessId);
				SessionUtil.deleteRow(SessionConstant.UpdateRequestLog.value(), this.sessId);
				Map<String, ManuDocument> sessDocMap = new LinkedHashMap<String, ManuDocument>();
				List<ManuDocument> docList = this.workOrder.getDocumentList();
				for (ManuDocument doc : docList) {
					sessDocMap.put(doc.getDocId() + "", doc);
				}
				// 因为sessMap可能在session中尚不存在, 所以这句必须要执行
				SessionUtil.insertRow(SessionConstant.WorkOrderDocument.value(),
						this.sessId, sessDocMap);
				if(workOrder.getWorkOrderLotList()==null) {
					workOrder.setWorkOrderLotList(new ArrayList<WorkOrderLot>());
				}
				SessionUtil.insertRow(SessionConstant.WorkOrderLot.value(),
						this.sessId, SessionUtil.convertList2Map(workOrder.getWorkOrderLotList(), "id"));
				SessionUtil.insertRow(SessionConstant.WorkOrderLotTemp.value(),
						this.sessId, SessionUtil.convertList2Map(workOrder.getWorkOrderLotList(), "id"));
			}
			WorkCenter workCenter = this.setupService.getWorkCenter(workOrder.getWorkCenterId());
			if(workCenter.getName().toLowerCase().equals("antibody department")||workCenter.getName().toLowerCase().equals("animal model department ")) {
				antibodyFlg = 1;
			}
			if(workOrder.getWarehouseId()==null) {
				workOrder.setWarehouseId(warehouseList!=null&&warehouseList.size()>0?warehouseList.get(0).getWarehouseId():null);
			}
			this.routeList = this.setupService.findRouteForWO(workOrder.getWorkCenterId(),workOrder.getWarehouseId());
			if(this.workOrder.getStandardRoutine()==null&&this.routeList!=null&&this.routeList.size()>0) {
				for(Route route:this.routeList) {
					if("Y".equals(route.getDefaultFlag())) {
						this.workOrder.setStandardRoutine(route.getId());
						break;
					}
				}
				if(this.workOrder.getStandardRoutine()==null) {
					this.workOrder.setStandardRoutine(this.routeList.get(0).getId());
				}
			}
			this.workOrderEntryService.getWorkOrderExtraInfo(this.workOrder);
			// 获得选中的WorkCenter关联的多个 WorkGroup.
//			this.workGroupList = this.setupService.getGroupListByCenter(workOrder
//					.getWorkCenterId());
			this.qaGroupList = this.workOrderEntryService.getQaGroup(workOrder);
			// 为null时要给其赋值， 否则struts2标签访问时会出错.
			this.qaClerkList = new ArrayList<QaClerk>();
			this.qcClerkList = new ArrayList<QaClerk>();
			if(this.qaGroupList!=null&&this.qaGroupList.size()>0) {
				this.qcClerkList = this.workOrderEntryService.getClerkList(this.qaGroupList.get(0).getId());
			}
			this.dropdownItemList = new ArrayList<DropDownDTO>();
			if (workOrder.getSoNo() != null) {
				List<MfgOrderItem> srcList = this.orderService
						.getVendorConfirmItemList(workOrder.getSoNo());
				for (MfgOrderItem item : srcList) {
					DropDownDTO dto = new DropDownDTO();
					dto.setValue(item.getItemNo() + "");
					dto.setName("#" + item.getItemNo() + " - "
							+ item.getCatalogNo());
					dropdownItemList.add(dto);
				}
			}
			session.put(this.sessId, this.workOrder);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "workorder_entry_add";
	}

	public String taskEdit() {
		try {
			sessWorkOrderNo = id.toString();
			// *********** Add By Zhang Yong Start *****************************//
			if (id != null && !("").equals(id)) {
				// 判断将要修改的单号是否正在被操作
				String editUrl = "workorder_entry!taskEdit.action?id=" + id;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
			} else {
				// 释放application中的订单锁
				OrderLockRelease realeseOrderLock = new OrderLockRelease();
				realeseOrderLock.releaseOrderLock();
			}
			// *********** Add By Zhang Yong End *****************************//
			addOrEdit();
			// 从Select Operation页面进入的， 要恢复之前的数据（借助session）
			if (Struts2Util.getParameter("referURL") != null
					&& Struts2Util.getParameter("referURL").equals("select")) {
				// 不进行清除Session等操作, 反而需要从session中取数据.
				Map<String, Object> session = ActionContext.getContext()
						.getSession();
				this.workOrder = (WorkOrderDTO) session.get(this.sessId);
				List<ManuDocument> docList = new ArrayList<ManuDocument>();
				@SuppressWarnings("unchecked")
				Map<String, ManuDocument> sessMap = (Map<String, ManuDocument>) SessionUtil
						.getRow(SessionConstant.WorkOrderDocument.value(),
								this.sessId);
				for (Entry<String, ManuDocument> entry : sessMap.entrySet()) {
					if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
						if (entry.getValue() == null) {// 执行了临时的删除操作
						} else {
							docList.add(entry.getValue());
						}
					} else {
						docList.add(entry.getValue());// 新增的
					}
				}
				this.workOrder.setDocumentList(docList);
				List<WorkOrderLot> workOrderLotList = new ArrayList<WorkOrderLot>();
				Map<String,WorkOrderLot> workOrderLotMap = (Map<String,WorkOrderLot>)SessionUtil.getRow(SessionConstant.WorkOrderLot.value(), this.sessId);
				for (Entry<String, WorkOrderLot> entry : workOrderLotMap.entrySet()) {
					if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
						if (entry.getValue() == null) {// 执行了临时的删除操作
						} else {
							workOrderLotList.add(entry.getValue());
						}
					} else {
						workOrderLotList.add(entry.getValue());// 新增的
					}
				}
				this.workOrder.setWorkOrderLotList(workOrderLotList);
			} else {
				this.workOrder = workOrderEntryService.getWorkOrder(id);
				this.sessId = Integer.toString(this.id);
				// 清除session
				SessionUtil.deleteRow(SessionConstant.WorkOrderOperation.value(),
						this.sessId);
				SessionUtil.deleteRow(SessionConstant.WorkOrderDocument.value(),
						this.sessId);
				SessionUtil.deleteRow(SessionConstant.WorkOrderLot.value(),
						this.sessId);
				SessionUtil.deleteRow(SessionConstant.WorkOrderLotTemp.value(),
						this.sessId);
				SessionUtil.deleteRow(SessionConstant.UpdateRequestLog.value(), this.sessId);
				Map<String, ManuDocument> sessDocMap = new LinkedHashMap<String, ManuDocument>();
				List<ManuDocument> docList = this.workOrder.getDocumentList();
				for (ManuDocument doc : docList) {
					sessDocMap.put(doc.getDocId() + "", doc);
				}
				// 因为sessMap可能在session中尚不存在, 所以这句必须要执行
				SessionUtil.insertRow(SessionConstant.WorkOrderDocument.value(),
						this.sessId, sessDocMap);
				if(workOrder.getWorkOrderLotList()==null) {
					workOrder.setWorkOrderLotList(new ArrayList<WorkOrderLot>());
				}
				SessionUtil.insertRow(SessionConstant.WorkOrderLot.value(),
						this.sessId, SessionUtil.convertList2Map(workOrder.getWorkOrderLotList(), "id"));
				SessionUtil.insertRow(SessionConstant.WorkOrderLotTemp.value(),
						this.sessId, SessionUtil.convertList2Map(workOrder.getWorkOrderLotList(), "id"));
			}
			WorkCenter workCenter = this.setupService.getWorkCenter(workOrder.getWorkCenterId());
			if(workCenter.getName().toLowerCase().equals("antibody department")||workCenter.getName().toLowerCase().equals("animal model department ")) {
				antibodyFlg = 1;
			}
			this.routeList = this.setupService.findRouteForWO(workOrder.getWorkCenterId(),workOrder.getWarehouseId());
			if(this.workOrder.getStandardRoutine()==null&&this.routeList!=null&&this.routeList.size()>0) {
				for(Route route:this.routeList) {
					if("Y".equals(route.getDefaultFlag())) {
						this.workOrder.setStandardRoutine(route.getId());
						break;
					}
				}
				if(this.workOrder.getStandardRoutine()==null) {
					this.workOrder.setStandardRoutine(this.routeList.get(0).getId());
				}
			}
			this.workOrderEntryService.getWorkOrderExtraInfo(this.workOrder);
			// 获得选中的WorkCenter关联的多个 WorkGroup.
//			this.workGroupList = this.setupService.getGroupListByCenter(workOrder
//					.getWorkCenterId());
			this.qaGroupList = this.workOrderEntryService.getQaGroup(workOrder);
			// 为null时要给其赋值， 否则struts2标签访问时会出错.
			this.qaClerkList = new ArrayList<QaClerk>();
			this.qcClerkList = new ArrayList<QaClerk>();
			if(this.qaGroupList!=null&&this.qaGroupList.size()>0) {
				this.qcClerkList = this.workOrderEntryService.getClerkList(this.qaGroupList.get(0).getId());
			}
			this.dropdownItemList = new ArrayList<DropDownDTO>();
			if (workOrder.getSoNo() != null) {
				List<MfgOrderItem> srcList = this.orderService
						.getVendorConfirmItemList(workOrder.getSoNo());
				for (MfgOrderItem item : srcList) {
					DropDownDTO dto = new DropDownDTO();
					dto.setValue(item.getItemNo() + "");
					dto.setName("#" + item.getItemNo() + " - "
							+ item.getCatalogNo());
					dropdownItemList.add(dto);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "workorder_entry_task_add";
	}

    /*
    * add by zhanghuibin
    * 生成 peptide 的doc文档
    * */
    public String downOperationTable(){
        String delivery_date = ServletActionContext.getRequest().getParameter("delivery_date");
        String work_order = ServletActionContext.getRequest().getParameter("work_order");
        String order_no = ServletActionContext.getRequest().getParameter("order_no");
        String so_item_no = ServletActionContext.getRequest().getParameter("so_item_no");
        peptideTemplateFileName = "peptide_opr_list.rtf";
        peptideTemplateDTO = workOrderEntryService.operationTableData(delivery_date, work_order, order_no, so_item_no);
        downTable("peptide_opr_list_template.rtf", "peptide_opr_list_content.rtf");
        return null;
    }

    /*
    * add by zhanghuibin
    * 批量生成 peptide 的doc文档
    * */
    public String batchDownOperationTable(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String delivery_date_str = ServletActionContext.getRequest().getParameter("delivery_dates");
        String work_order_str = ServletActionContext.getRequest().getParameter("work_orders");
        String order_no_str = ServletActionContext.getRequest().getParameter("order_nos");
        String so_item_no_str = ServletActionContext.getRequest().getParameter("so_item_nos");
        peptideTemplateFileName = "peptide_opr_list.rtf";
        ReadRTF readRTF = new ReadRTF();
        String filePath = request.getSession().getServletContext().getRealPath("documents" + separator + "work_order_template" + separator);
        StringBuffer docContentStr = new StringBuffer("");
        for(int i = 0; i < work_order_str.split(",").length; i++){
            String delivery_date = delivery_date_str.split(",")[i];
            if("-1".equals(delivery_date)) delivery_date = "";
            String work_order = work_order_str.split(",")[i];
            String order_no = order_no_str.split(",")[i];
            String so_item_no = so_item_no_str.split(",")[i];
             peptideTemplateDTO = workOrderEntryService.operationTableData(delivery_date, work_order, order_no, so_item_no);
             docContentStr.append(readRTF.generateDoc(filePath + separator + "peptide_opr_list_content.rtf", peptideTemplateDTO));
            docContentStr.append(readRTF.getRTFPageCut());
        }
        String docTemplateStr = readRTF.readRtf(filePath + separator + "peptide_opr_list_template.rtf");
        String docStr = docTemplateStr.replace("$content", docContentStr.toString());
        download(docStr);
        return null;
    }

    public String downQCTable(){
        peptideTemplateFileName = "peptide_list_qc.rtf";
        String delivery_date = ServletActionContext.getRequest().getParameter("delivery_date");
        String work_order = ServletActionContext.getRequest().getParameter("work_order");
        String order_no = ServletActionContext.getRequest().getParameter("order_no");
        String so_item_no = ServletActionContext.getRequest().getParameter("so_item_no");
        peptideTemplateDTO = workOrderEntryService.qcTableData(delivery_date, work_order, order_no, so_item_no);
        downTable("peptide_list_qc_template.rtf", "peptide_list_qc_content.rtf");
        return null;
    }

    private void downTable(String templateName, String contentName){
        HttpServletRequest request = ServletActionContext.getRequest();
        String filePath = request.getSession().getServletContext().getRealPath("documents" + separator + "work_order_template" + separator);
        ReadRTF readRTF = new ReadRTF();
        String docContentStr = readRTF.generateDoc(filePath + separator, peptideTemplateDTO) + readRTF.getRTFPageCut();
        String docTemplateStr = readRTF.readRtf(filePath + separator);
        String docStr = docTemplateStr.replace("$content", docContentStr);
        download(docStr);
    }

    public String download(String docStr) {
        java.io.OutputStream os = null;
		try {
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + peptideTemplateFileName);
		    os = response.getOutputStream();
            os.write(docStr.getBytes("UTF-8"));
			os.flush();
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            if(os != null){
                try{
                    os.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
		return null;
	}

	
	/**
	 * 弹出发送邮件页面
	 * @author lizhang
	 */
	public String sendMail() {
		try {
			workOrderNote = new MailLogDTO();
			workOrderNote.setType(OrderInstructionType.SUPPLY_CONFIRM_EMAIL.value());
//			this.mailGroupList = publicService.getMailGroupList();// 获得已配置的邮箱列表模板
			workOrderNote.setRecipient(this.workOrderEntryService.getWGSupervisorEmail(workGroupIds));
			this.mailTmplList = publicService.getMailTmplDTOList(OrderInstructionType.SUPPLY_CONFIRM_EMAIL.value());// 获得已配置的邮箱内容列表.
			if(StringUtils.isEmpty(orderNoStrs)&&this.sessWorkOrderNo!=null) {
				orderNoStrs = String.valueOf(sessWorkOrderNo);
			}
			this.mailTmplList = this.workOrderEntryService.convertParam(mailTmplList, orderNoStrs);
			wordDocList = this.workOrderEntryService.createWordDoc(orderNoStrs,fileService.getUploadPath()+"quote_notes/");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "mail_edit";
	}
	
	/**
	 * 保存邮件
	 */
	public String saveMail() {
		try {
			workOrderNote.setRefId(StringUtils.isEmpty(sessWorkOrderNo)||!StringUtil.isNumeric(sessWorkOrderNo)?null:Integer.parseInt(sessWorkOrderNo));
			workOrderNote.setCreateUser(SessionUtil.getUserName());
			workOrderNote.setSendDate(new Date());
			workOrderNote.setInstructionDate(workOrderNote.getSendDate());
			
			// 更新documentList列表里的内容(追加要上传的document对象).
			List<Document> documentList = new ArrayList<Document>();
			if (upload != null && !upload.isEmpty()) {
				for (int i = 0; i < upload.size(); i++) {
					Document doc = new Document();
					doc.setDocName(uploadFileName.get(i));
					uploadFileName.set(i, SessionUtil.generateTempId() + "_"
							+ uploadFileName.get(i));
					doc.setFilePath("quote_notes/" + uploadFileName.get(i));
					documentList.add(doc);
				}
			}
			fileService.uploadFile(upload, uploadContentType, uploadFileName, "quote_notes");
			// 重新关联并保存或更新Quote模块下Instruction/Note标签.
			if(docNameArray!=null&&docNameArray.length>0) {
				for(int i=0;i<docNameArray.length;i++) {
					Document doc = new Document();
					doc.setDocName(docNameArray[i]);
					doc.setFilePath(filePathArray[i]);
					documentList.add(doc);
				}
			}
			workOrderNote.setDocumentList(documentList);
			workOrderNote.setFunctionName("Manufacturing: Assign work order");
			workOrderNote.setRefType("work_order.order_no ");
			this.mailLogService.saveMail(workOrderNote);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Struts2Util.renderText("Add success");
		return null;
	}
	
	public String lotNoChange() {
		Map<String, Object> rt = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Map<String,WorkOrderLot> workOrderLotMap = (HashMap<String,WorkOrderLot>)SessionUtil.getRow(SessionConstant.WorkOrderLotTemp.value(), this.sessId);
		if(StringUtils.isNotEmpty(workOrderLotSessId)) {
			WorkOrderLot workOrderLot = workOrderLotMap.get(workOrderLotSessId);
			workOrderLot.setCreationDateStr(DateUtils.formatDate2Str(workOrderLot.getCreationDate(), DateUtils.C_DATE_PATTON_DEFAULT));
			rt.put("workOrderLot", workOrderLot);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 清除session中workOrderLot
	 */
	public String clearWorkOrderLotTemp() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if(SessionUtil.getRow(SessionConstant.WorkOrderLotTemp.value(), this.sessId)!=null) {
			SessionUtil.deleteRow(SessionConstant.WorkOrderLotTemp.value(), this.sessId);
		}
		Map<String,WorkOrderLot> workOrderLotMap = (HashMap<String,WorkOrderLot>)SessionUtil.getRow(SessionConstant.WorkOrderLot.value(), this.sessId);
		Map<String,WorkOrderLot> workOrderLotTempMap = new HashMap<String,WorkOrderLot>();
		if(workOrderLotMap!=null&&workOrderLotMap.size()>0) {
			for(Entry<String,WorkOrderLot> entry:workOrderLotMap.entrySet()) {
				workOrderLotTempMap.put(entry.getKey(), entry.getValue());
			}
		}
		SessionUtil.insertRow(SessionConstant.WorkOrderLotTemp.value(),this.sessId,workOrderLotTempMap);
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 创建lotNo
	 * 
	 * @return
	 * @author lizhang
	 */
	public String createLotNo() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			if(workOrderLot==null) {
				rt.put("message", "false");
			}
			Map<String,WorkOrderLot> workOrderLotMap = (HashMap<String,WorkOrderLot>)SessionUtil.getRow(SessionConstant.WorkOrderLotTemp.value(), this.sessId);
			Integer addCount = 0;
			if(workOrderLotMap!=null) {
				for(Entry<String,WorkOrderLot> entry:workOrderLotMap.entrySet()) {
					if(!StringUtil.isNumeric(entry.getKey())) {
						addCount++;
					}
				}
			} else {
				workOrderLotMap = new HashMap<String,WorkOrderLot>();
			}
			if(StringUtils.isEmpty(this.workOrderLotSessId)) {
				Long total = this.workOrderEntryService.getTotalForLotNo();
				if(total==null||total.intValue()==0) {
					total = 1l;
				}
				total = total+addCount;
				int year = Calendar.getInstance().get(Calendar.YEAR);
				int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
				String lotNoPre = String.valueOf(year%100)+(month < 10 ? ("0" + month) : (String.valueOf(month))); 
				total = total+1;
				String seqNo = total%999==0?"999":String.valueOf(total%999);
				for(int j =seqNo.length();j<3;j++) {
					seqNo = "0"+seqNo;
				}
				String lotNo = workOrderLot.getDepartmentCode()+workOrderLot.getWorkGroupCode()+seqNo+lotNoPre;
				String key = SessionUtil.generateTempId();
				workOrderLotSessId = key;
				workOrderLot.setLotNo(lotNo);
				workOrderLot.setCreationDate(new Date());
				workOrderLot.setCreatedBy(SessionUtil.getUserId());
				workOrderLot.setCreatedByName(SessionUtil.getUserName());
				workOrderLot.setStatus("ACTIVE");
			} else {
				WorkOrderLot workOrderLot2 = workOrderLotMap.get(this.workOrderLotSessId);
				workOrderLot.setId(workOrderLot2.getId());
				workOrderLot.setStatus("ACTIVE");
				String lotNo = workOrderLot.getDepartmentCode()+workOrderLot.getWorkGroupCode()+workOrderLot2.getLotNo().substring(2);
				workOrderLot.setLotNo(lotNo);
				workOrderLot.setCreatedBy(workOrderLot2!=null?workOrderLot2.getCreatedBy():null);
				workOrderLot.setCreatedByName(workOrderLot2!=null?workOrderLot2.getCreatedByName():null);
				workOrderLot.setCreationDate(workOrderLot2!=null?workOrderLot2.getCreationDate():null);
			}
			workOrderLotMap.put(this.workOrderLotSessId, workOrderLot);
			SessionUtil.updateRow(SessionConstant.WorkOrderLotTemp.value(), this.sessId, workOrderLotMap);
			rt.put("message", "true");
			rt.put("lotNo",workOrderLot.getLotNo());
			rt.put("key", this.workOrderLotSessId);
			rt.put("creationDate", DateUtils.formatDate2Str(workOrderLot.getCreationDate(), DateUtils.C_DATE_PATTON_DEFAULT));
			rt.put("createdByName", workOrderLot.getCreatedByName());
		} catch (Exception ex) {
			rt.put("hasException", true);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 删除LotNo
	 */
	public String delWorkOrderLot() {
		Map<String, Object> rt = new HashMap<String, Object>();
		Map<String,WorkOrderLot> workOrderLotMap = (HashMap<String,WorkOrderLot>)SessionUtil.getRow(SessionConstant.WorkOrderLotTemp.value(), this.sessId);
		if(workOrderLotMap.get(this.workOrderLotSessId)!=null) {
			if(StringUtil.isNumeric(this.workOrderLotSessId)) {
				workOrderLotMap.put(this.workOrderLotSessId, null);
			} else {
				workOrderLotMap.remove(this.workOrderLotSessId);
			}
		}
		SessionUtil.updateRow(SessionConstant.WorkOrderLotTemp.value(), this.sessId, workOrderLotMap);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String confirmLotNo() {
		Map<String, Object> rt = new HashMap<String, Object>();
		SessionUtil.updateRow(SessionConstant.WorkOrderLot.value(),this.sessId,SessionUtil.getRow(SessionConstant.WorkOrderLotTemp.value(), this.sessId));
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 保存当前页面信息WorkOrder到Session中.
	 * 
	 * @return
	 */
	public String save2Session() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(this.sessId, this.workOrder);
		Struts2Util.renderText("success");
		return null;
	}

	/**
	 * 保存WorkOrder及相关的其它Model.
	 * 
	 * @return
	 */
	public String save() {
		System.out.println("workOrder: " + workOrder);
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (sessId != null && !("").equals(sessId)) {
				String editUrl = "workorder_entry!edit.action?id=" + sessId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message",
							"Save fail,the workOrder is editing by "+operation_method);
					// 清除Session
					SessionUtil.deleteRow(SessionConstant.WorkOrderOperation
							.value(), this.sessId);
					SessionUtil.deleteRow(SessionConstant.WorkOrderDocument
							.value(), this.sessId);
					SessionUtil.deleteRow(SessionConstant.WorkOrderLot.value(),
							this.sessId);
					SessionUtil.deleteRow(SessionConstant.WorkOrderLotTemp.value(),
							this.sessId);
					SessionUtil.deleteRow(SessionConstant.UpdateRequestLog.value(), this.sessId);
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					session.remove(this.sessId);
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			// *********** Add By Zhang Yong End *****************************//
			this.attachOperation();
			this.attachDocument();
			this.attachLotNo();
			if(StringUtils.isNotEmpty(this.workOrder.getProductQc())) {
				this.workOrder.setProductQcDate(new java.sql.Date(new java.util.Date().getTime()));
			}
			if(StringUtils.isNotEmpty(this.workOrder.getDocumentQc())) {
				this.workOrder.setDocumentQcDate(new java.sql.Date(new java.util.Date().getTime()));
			}

			// 保存Qa信息
//			String wo_productQa = this.workOrder.getProductQa();
//			String wo_documentQa = this.workOrder.getDocumentQa();
//			String productQa = Struts2Util.getParameter("productQa");
//			String documentQa = Struts2Util.getParameter("documentQa");
//			String productQaReason = Struts2Util
//					.getParameter("productQaReason");
//			String documentQaReason = Struts2Util
//					.getParameter("documentQaReason");
//			if (productQa != null&&StringUtils.isNotEmpty(productQa)&&!wo_productQa.equals(productQa)&&"Passed".equals(productQa)) {
//				this.workOrder.setProductQa(productQa);
//				this.workOrder.setProductQaDate(new Date());
//			}
//			if(documentQa != null&&StringUtils.isNotEmpty(documentQa)&&!wo_documentQa.equals(documentQa)&&"Passed".equals(documentQa)) {
//				this.workOrder.setDocumentQa(documentQa);
//				this.workOrder.setDocumentQaDate(new Date());
//			}
			if(this.workOrder.getOrderNo()==null) {
				this.workOrder.setStatus(WorkOrderStatus.New.value());
			}
			this.workOrderEntryService.save(this.workOrder, SessionUtil
					.getUserId());
			rt.put("id", this.workOrder.getOrderNo());
			List<Integer> workOrderNoList = new ArrayList<Integer>();
			workOrderNoList.add(this.workOrder.getOrderNo());
			this.workOrderEntryService.processOrderByQc(workOrderNoList,this.workOrder.getProductQc(),this.workOrder.getDocumentQc(),null,null,SessionUtil.getUserId());
			List<UpdateRequestLog> list = (ArrayList<UpdateRequestLog>)SessionUtil.getRow(SessionConstant.UpdateRequestLog.value(), this.sessId);
			this.updateRequestLogService.batchSave(list);
			// 保存Qa信息
//			if (productQa != null) {
//				boolean bUpdate = false;
//				if (wo_productQa == null || !wo_productQa.equals(productQa)) {
//					bUpdate = true;
//				}
//				if (wo_documentQa == null || !wo_documentQa.equals(documentQa)) {
//					bUpdate = true;
//				}
//				if (bUpdate) {
//					this.workOrderEntryService.processOrderByQa(workOrder
//							.getOrderNo(), productQa, documentQa,
//							productQaReason, documentQaReason, SessionUtil
//									.getUserId());
//				}
//			}

			// 删除时, sessionId要为sessId不能为workOrder.getOrderNo()
			// 因为这样WorkOrder新增时两者是不一样的,删除不了.为防止WorkOrder本身属性保存失败可还原，
			// 故放在save()方法之后.
			SessionUtil.deleteRow(SessionConstant.WorkOrderOperation.value(),
					this.sessId);
			SessionUtil.deleteRow(SessionConstant.WorkOrderDocument.value(),
					this.sessId);
			SessionUtil.deleteRow(SessionConstant.WorkOrderLot.value(),
					this.sessId);
			SessionUtil.deleteRow(SessionConstant.WorkOrderLotTemp.value(),
					this.sessId);
			SessionUtil.deleteRow(SessionConstant.UpdateRequestLog.value(), this.sessId);
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.remove(this.sessId);
			rt.put("message", "Save work order sucessfully.");
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 释放同步锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
			// *********** Add By Zhang Yong End *****************************//
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
	
	public String refreshTime() {
		Map<String, Object> rt = new HashMap<String, Object>();
		WorkOrder workOrder = this.workOrderEntryService.refreshTime(Integer.parseInt(sessWorkOrderNo));
		rt.put("ScheduleEnd", workOrder.getScheduleEnd());
		Struts2Util.renderJson(rt);
		return null;
	}

	public String saveTask() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (sessId != null && !("").equals(sessId)) {
				String editUrl = "workorder_entry!taskEdit.action?id=" + sessId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message",
							"Save fail,the workOrder is editing by "+operation_method);
					// 清除Session
					SessionUtil.deleteRow(SessionConstant.WorkOrderOperation
							.value(), this.sessId);
					SessionUtil.deleteRow(SessionConstant.WorkOrderDocument
							.value(), this.sessId);
					SessionUtil.deleteRow(SessionConstant.WorkOrderLot.value(),
							this.sessId);
					SessionUtil.deleteRow(SessionConstant.WorkOrderLotTemp.value(),
							this.sessId);
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					session.remove(this.sessId);
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			// *********** Add By Zhang Yong End *****************************//
			Map<String,List<WorkOrder>> map = this.workOrderEntryService.batchAssignGroup(this.workOrder.getOrderNo().toString(),this.workOrder.getWorkGroupIds(),processFlag);
			if(map.size()>0&&map.get("tubeWorkOrderList").size()>0) {
				dsPlateService.saveOrUpdatePlate(map.get("tubeWorkOrderList"), false);
			}
			if(map.size()>0&&map.get("plateWorkOrderList").size()>0) {
				dsPlateService.saveOrUpdatePlate(map.get("plateWorkOrderList"), true);
			}
			List<WorkOrder> list = (ArrayList<WorkOrder>)map.get("workOrderList");
			WorkOrder workOrder1 = list!=null&&list.size()>0?list.get(0):new WorkOrder();
			MfgOrder mfgOrder = this.orderService.getMfgOrderDetail(workOrder1.getSoNo());
			if(mfgOrder!=null) {
				OrderItem orderItem = this.orderItemService.saveMfgOrderItem(workOrder1.getSoItemNo(),mfgOrder);
				orderService.updateMfgOrder(workOrder1.getSoNo(),orderItem!=null?orderItem.getTargetDate():null);//改order状态和expDate
			} else {
				OrderItem orderItem = this.orderItemService.saveOrderItem(workOrder1.getSoItemNo(),workOrder1.getSoNo());
				orderService.updateOrder(workOrder1.getSoNo(),orderItem!=null?orderItem.getTargetDate():null);//改order状态和expDate
			}
			
			// 删除时, sessionId要为sessId不能为workOrder.getOrderNo()
			// 因为这样WorkOrder新增时两者是不一样的,删除不了.为防止WorkOrder本身属性保存失败可还原，
			// 故放在save()方法之后.
			SessionUtil.deleteRow(SessionConstant.WorkOrderOperation.value(),
					this.sessId);
			SessionUtil.deleteRow(SessionConstant.WorkOrderDocument.value(),
					this.sessId);
			SessionUtil.deleteRow(SessionConstant.WorkOrderLot.value(),
					this.sessId);
			SessionUtil.deleteRow(SessionConstant.WorkOrderLotTemp.value(),
					this.sessId);
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.remove(this.sessId);
			rt.put("message", "Save work order sucessfully.");
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 释放同步锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
			// *********** Add By Zhang Yong End *****************************//
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
	
	public String assignGroup() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String,List<WorkOrder>> workOrderMap = this.workOrderEntryService.batchAssignGroup(orderNoStrs, workGroupIds,processFlag);
			if(workOrderMap.size()>0&&workOrderMap.get("tubeWorkOrderList").size()>0) {
				dsPlateService.saveOrUpdatePlate(workOrderMap.get("tubeWorkOrderList"), false);
			}
			if(workOrderMap.size()>0&&workOrderMap.get("plateWorkOrderList").size()>0) {
				dsPlateService.saveOrUpdatePlate(workOrderMap.get("plateWorkOrderList"), true);
			}
			List<WorkOrder> workOrderList = (ArrayList<WorkOrder>)workOrderMap.get("workOrderList");
			for(WorkOrder workOrder:workOrderList) {
				MfgOrder mfgOrder = this.orderService.getMfgOrder(workOrder.getSoNo());
				if(mfgOrder!=null) {
					OrderItem orderItem = this.orderItemService.saveMfgOrderItem(workOrder.getSoItemNo(),mfgOrder);
					orderService.updateMfgOrder(workOrder.getSoNo(),orderItem!=null?orderItem.getTargetDate():null);//改order状态和expDate
				} else {
					OrderItem orderItem = this.orderItemService.saveOrderItem(workOrder.getSoItemNo(),workOrder.getSoNo());
					orderService.updateOrder(workOrder.getSoNo(),orderItem!=null?orderItem.getTargetDate():null);//改order状态和expDate
				}
			}
			rt.put("message", "Success");
			
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("message", "Error");
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		
		Struts2Util.renderJson(rt);
		return null;
	}

	@SuppressWarnings("unchecked")
	private void attachOperation() {
		List<WorkOrderOperation> roList = new ArrayList<WorkOrderOperation>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, WorkOrderOperation> sessMap = (Map<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		if(sessMap!=null&&sessMap.size()>0) {
			for (Entry<String, WorkOrderOperation> entry : sessMap.entrySet()) {
				WorkOrderOperation woOperation = (WorkOrderOperation) entry
						.getValue();
				// 本例中对session中的数据只有新增和删除操作
				if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
					if (woOperation == null) {// 执行了临时的删除操作
						// 删除了， 则Cancel源Order
						woOperation = this.workOrderEntryService
								.getWoOperation(Integer.parseInt(entry.getKey()));
						if (woOperation.getInternalOrderNo() != null) {
							String reason = "Cancel by work order";
							OrderMainDTO order = orderService.getOrderDetail(woOperation.getInternalOrderNo());
							order.setStatus(OrderStatusType.CN.value());
							orderService.delOrder(woOperation.getInternalOrderNo(),
									reason, SessionUtil.getUserId(), reason, order);
						}
						delIdList.add(Integer.parseInt(entry.getKey()));
						continue;
					} 
				} else {
					woOperation.setId(null);
					// 新增的
				}
				if(woOperation.getSeqNo()==1&&WorkOrderStatus.Inprogress.value().equals(this.workOrder.getStatus())&&woOperation.getActualStartDate()==null) {
					woOperation.setActualStartDate(new java.sql.Date(new Date().getTime()));
				}
				roList.add(woOperation);
				this.attachOperationRes(woOperation, entry.getKey());
				this.attachOperationComponent(woOperation, entry.getKey());
			}
			this.workOrder.setDelOperationIdList(delIdList);
			this.workOrder.setWorkOrderOperationList(roList);
		}
		
	}

	@SuppressWarnings("unchecked")
	private void attachOperationRes(WorkOrderOperation woOperation,
			String sessWOPKey) {
		List<Integer> delIdList = new ArrayList<Integer>();
		List<WoOperationResource> woResList = new ArrayList<WoOperationResource>();
		Map<String, WoOperationResource> woResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
				.getRow(SessionConstant.WOResource.value(), sessWOPKey);
		if (woResMap != null) {
			for (Entry<String, WoOperationResource> entry : woResMap.entrySet()) {
				WoOperationResource woRes = (WoOperationResource) entry
						.getValue();
				// 本例中对session中的数据只有新增和删除操作
				if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
					if (woRes == null) {// 执行了临时的删除操作
						delIdList.add(Integer.parseInt(entry.getKey()));
					} else {
						woResList.add(woRes);// 修改的
					}
				} else {
					woRes.setId(null);
					woResList.add(woRes);// 新增的
				}
			}
		}
		woOperation.setWoResourceList(woResList);
		woOperation.setDelWoResIdList(delIdList);
	}

	@SuppressWarnings("unchecked")
	private void attachOperationComponent(WorkOrderOperation woOperation,
			String sessWOPKey) {
		List<Integer> delIdList = new ArrayList<Integer>();
		List<WoOperationComponent> woComList = new ArrayList<WoOperationComponent>();
		Map<String, WoOperationComponent> woComMap = (LinkedHashMap<String, WoOperationComponent>) SessionUtil
				.getRow(SessionConstant.WOComponent.value(), sessWOPKey);
		if (woComMap != null) {
			for (Entry<String, WoOperationComponent> entry : woComMap
					.entrySet()) {
				WoOperationComponent woCom = (WoOperationComponent) entry
						.getValue();
				// 本例中对session中的数据只有新增和删除操作
				if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
					if (woCom == null) {// 执行了临时的删除操作
						delIdList.add(Integer.parseInt(entry.getKey()));
					} else {
						woComList.add(woCom);// 修改的
					}
				} else {
					woCom.setId(null);
					woComList.add(woCom);// 新增的
				}
			}
		}
		woOperation.setWoComponentList(woComList);
		woOperation.setDelWoComIdList(delIdList);
	}

	/**
	 * 保存WorkOrder时级联保存Document.
	 */
	@SuppressWarnings("unchecked")
	private void attachDocument() {
		List<ManuDocument> roList = new ArrayList<ManuDocument>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, ManuDocument> sessMap = (Map<String, ManuDocument>) SessionUtil
				.getRow(SessionConstant.WorkOrderDocument.value(), this.sessId);
		if(sessMap!=null) {
			for (Entry<String, ManuDocument> entry : sessMap.entrySet()) {
				ManuDocument doc = entry.getValue();
				// 本例中对session中的数据只有新增和删除操作
				if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
					if (doc == null) {// 执行了临时的删除操作
						delIdList.add(Integer.parseInt(entry.getKey()));
					} else {
						// 修改的不需要作任何操作.
					}
				} else {
					doc.setDocId(null);
					roList.add(doc);// 新增的
				}
			}
		}
		this.workOrder.setDelDocIdList(delIdList);
		this.workOrder.setDocumentList(roList);
		System.out.println("roList: " + roList);
	}
	
	/**
	 * 保存workOrder时级联保存lotNo
	 */
	private void attachLotNo() {
		List<WorkOrderLot> workOrderLotList = new ArrayList<WorkOrderLot>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, WorkOrderLot> sessMap = (Map<String, WorkOrderLot>) SessionUtil
				.getRow(SessionConstant.WorkOrderLot.value(), this.sessId);
		if(sessMap!=null) {
			for (Entry<String, WorkOrderLot> entry : sessMap.entrySet()) {
				WorkOrderLot lot = entry.getValue();
				// 本例中对session中的数据只有新增和删除操作
				if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
					if (lot == null) {// 执行了临时的删除操作
						delIdList.add(Integer.parseInt(entry.getKey()));
					} else {
						// 修改的不需要作任何操作.
						workOrderLotList.add(lot);
						
					}
				} else {
					lot.setId(null);
					workOrderLotList.add(lot);// 新增的
				}
			}
		}
		this.workOrder.setWorkOrderLotList(workOrderLotList);
		this.workOrder.setDelWorkOrderLotList(delIdList);
	}

	/**
	 * 前端选择一个WorkCenter触发的ajax.
	 * 
	 * @return
	 */
	public String selectWorkCenter() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<WorkGroup> groupList = this.setupService
				.getGroupListByCenter(this.id);
		WorkCenter workCenter = this.setupService.getWorkCenter(id);
		retMap.put("groupList", groupList);
		retMap.put("superName", workCenter.getSuperName());
		retMap.put("superId", workCenter.getSupervisor());
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 前端选择一个WorkGroup触发的ajax请求.
	 * 
	 * @return
	 */
	public String selectWorkGroup() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		WorkGroup workGroup = this.setupService.getWorkGroup(id);
		retMap.put("superName", workGroup.getSuperName()==null?"":workGroup.getSuperName());
		retMap.put("superId", workGroup.getSupervisor()==null?"":workGroup.getSupervisor());
		Struts2Util.renderJson(retMap);
		return null;
	}
	
	/**
	 * 为WorkOrder Entry显示Order选择页面
	 */
	public String showPageForSearchOrder() {
		OrderStatusType[] statusType = OrderStatusType.values();
		ServletActionContext.getRequest()
				.setAttribute("statusType", statusType);
		return "workorder_entry_select_order";
	}

	/**
	 * 为WorkOrder Entry显示Order选择页面.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showOrderForSelect() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<MfgOrderDTO2> pagerUtil = new PagerUtil<MfgOrderDTO2>();
		Page<MfgOrderDTO2> page = pagerUtil.getRequestPage();
		page.setPageSize(20);
		// end of modify 1;
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		PropertyFilter filter = new PropertyFilter("EQS_status",OrderStatusType.CC.value()); 
		if(filters==null) {
			filters = new ArrayList<PropertyFilter>();
		}
		filters.add(filter);
		page = orderService.searchMfgOrderDTOPage(page, filters);
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		OrderStatusType[] statusType = OrderStatusType.values();
		ServletActionContext.getRequest()
				.setAttribute("statusType", statusType);
		// end of modify 2;
		return "workorder_entry_select_order";
	}

	/**
	 * 前端选择一个(Sales) Order触发的ajax请求.
	 * 
	 * @return
	 */
	public String selectOrder() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String salesContact = null;
		String projectSupport = null;
		String techSupport = null;
		OrderMain order = this.orderService.getOrder(id);
		if (order.getSalesContact() != null) {
			SalesRep salesContactUser = this.salesRepDao.getById(order
					.getSalesContact());
			if (salesContactUser != null) {
				salesContact = salesContactUser.getResourceName();
			}
		}
		if(order.getProjectManager()!=null) {
			SalesRep projectManager = this.salesRepDao.getById(order.getProjectManager());
			if(projectManager!=null) {
				projectSupport = projectManager.getResourceName();
			}
		}
		if(order.getTechSupport()!=null) {
			SalesRep techSupportUser = this.salesRepDao.getById(order.getTechSupport());
			if(techSupportUser!=null) {
				techSupport = techSupportUser.getResourceName();
			}
		}
		List<MfgOrderItem> srcList = this.orderService
				.getMfgItemListNotInType(orderNo);
		List<DropDownDTO> tagList = new ArrayList<DropDownDTO>();
		for (MfgOrderItem item : srcList) {
			DropDownDTO dto = new DropDownDTO();
			dto.setValue(item.getItemNo() + "");
			dto.setName("#" + item.getItemNo() + " - " + item.getCatalogNo());
			tagList.add(dto);
		}
		retMap.put("itemList", tagList);
		retMap.put("projectSupport", projectSupport);
		retMap.put("salesContact", salesContact);
		retMap.put("techSupport", techSupport);
		retMap.put("companyId", order.getGsCoId());
		retMap.put("priority", order.getPriority());
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 前端选择一个(Sales) Order item 触发的ajax请求.
	 * 
	 * @return
	 */
	public String selectOrderItem() {
		OrderItem orderItem = this.orderService.getItemByItemNo(id, itemNo);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("orderItem", orderItem);
//		List<Route> routeList = this.setupService.findRouteForWO(orderItem.getClsId(),orderItem.getType(),warehouseId);
//		if(routeList!=null&&routeList.size()>0) {
//			for(Route route:routeList) {
//				if("Y".equals(route.getDefaultFlag())) {
//					retMap.put("defaultRouteId",route.getId());
//					break;
//				}
//			}
//			if(!retMap.containsKey("defaultRouteId")) {
//				retMap.put("defaultRouteId",routeList.get(0).getId());
//			}
//		} else {
//			routeList = new ArrayList<Route>();
//		}
//		retMap.put("routeList", routeList);
		Struts2Util.renderJson(retMap);
		return null;
	}
	
	/**
	 * 前端选择一个(Sales) warehouse 触发的ajax请求.
	 * 
	 * @return
	 */
	public String selectWarehouse() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<Route> routeList = this.setupService.findRouteForWO(centerId,warehouseId);
		if(routeList!=null&&routeList.size()>0) {
			for(Route route:routeList) {
				if("Y".equals(route.getDefaultFlag())) {
					retMap.put("defaultRouteId",route.getId());
					break;
				}
			}
			if(!retMap.containsKey("defaultRouteId")) {
				retMap.put("defaultRouteId",routeList.get(0).getId());
			}
		} else {
			routeList = new ArrayList<Route>();
		}
		retMap.put("routeList", routeList);
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 为WorkOrder Entry显示Product/Service选择页面.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showProductForSelect() throws Exception {
		// 只有选中Service时才显示Service list
		if ("Service".equals(Struts2Util.getParameter("type"))) {
			PagerUtil<ServiceListBean> pagerUtil = new PagerUtil<ServiceListBean>();
			Page<ServiceListBean> pageBean = pagerUtil.getRequestPage();
			// 设置默认每页显示记录条数
			if (pageBean.getPageSize() == null
					|| pageBean.getPageSize().intValue() < 1) {
				pageBean.setPageSize(6);
			}
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			if (!pageBean.isOrderBySetted()) {
				pageBean.setOrderBy("modifyDate");
				pageBean.setOrder(Page.DESC);
			}
			pageBean = this.servService.searchServiceList(pageBean, filters);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					pageBean);
		} else {
			// 默认或选中为Product时才显示Product list.
			// 获得分页请求相关数据：如第几页
			PagerUtil<ProductListBean> pagerUtil = new PagerUtil<ProductListBean>();
			Page<ProductListBean> pageBean = pagerUtil.getRequestPage();
			// 设置默认每页显示记录条数
			if (pageBean.getPageSize() == null
					|| pageBean.getPageSize().intValue() < 1) {
				pageBean.setPageSize(15);
			}
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			if (!pageBean.isOrderBySetted()) {
				pageBean.setOrderBy("modifyDate");
				pageBean.setOrder(Page.DESC);
			}
			pageBean = this.productService.searchProductList(pageBean, filters);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					pageBean);
		}
		return "workorder_entry_select_product";
	}

	/**
	 * 前端选择一个Product/Service触发的ajax请求.
	 * 
	 * @return
	 */
	public String selectProduct() {System.out.println("type:  " + Struts2Util.getParameter("type"));
		Map<String, Object> retMap = new HashMap<String, Object>();
		if ("Service".equals(Struts2Util.getParameter("type"))) {
			ServiceDTO dto = this.servService.getServDetail(this.id);
			retMap.put("name", dto.getName());
			retMap.put("catalogNo", dto.getCatalogNo());
			retMap.put("clsId", dto.getServiceClsId());
			retMap.put("itemType", "SERVICE");
			retMap.put("size", dto.getSize());
			retMap.put("sizeUom", dto.getSize());
			retMap.put("qtyUom", dto.getQtyUom());
		} else {
			ProductDTO dto = this.productService.getProductDetail(this.id);
			retMap.put("name", dto.getName());
			retMap.put("catalogNo", dto.getCatalogNo());
			retMap.put("clsId", dto.getProductClsId());
			retMap.put("itemType", "PRODUCT");
			retMap.put("size", dto.getSize());
			retMap.put("sizeUom", dto.getSize());
			retMap.put("qtyUom", dto.getQtyUom());
		}
		Struts2Util.renderJson(retMap);System.out.println(retMap);
		return null;
	}

	/**
	 * 查看一个产品的ShipCondition, StorageCondition 输入catalogNo,
	 * type('PRODUCT'或'SERVICE')
	 * 
	 * @return
	 */
	public String showProductShipAndStorage() {
		String catalogNo = Struts2Util.getParameter("catalogNo");
		if ("SERVICE".equals(Struts2Util.getParameter("type"))) {
			ServiceShipCondition servShip = this.servService.queryShipCondition(catalogNo);
			ServiceStorageCondition servStorage = this.servService.queryStorageCondition(catalogNo);
			this.shipCondition = this.dozer.map(servShip, ShipCondition.class);
			this.storageCondition = this.dozer.map(servStorage, StorageCondition.class);
		} else {
			this.shipCondition = this.productService
					.queryShipCondition(catalogNo);
			this.storageCondition = this.productService
					.queryStorageCondition(catalogNo);
		}
		return "workorder_entry_show_productcondition";
	}

	/**
	 * 选择QaGroup或QcGroup, 输入值为它们的id.
	 * 
	 * @return
	 */
	public String selectQaQcGroup() {
		try {
			Map<String, Object> retMap = new HashMap<String, Object>();
			qaClerkList = this.workOrderEntryService.getClerkListByQa(id,Constants.ROLE_QC_MANAGER);
			retMap.put("qaClerkList", qaClerkList);
			Struts2Util.renderJson(retMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
		Map<String, ManuDocument> sessMap = (LinkedHashMap<String, ManuDocument>) SessionUtil
				.getRow(SessionConstant.WorkOrderDocument.value(), this.sessId);
		// 这次请求上传的文件， 要返回文件信息给客户端.
		Map<String, ManuDocument> uploadMap = new LinkedHashMap<String, ManuDocument>();

		String docType = Struts2Util.getParameter("docType");
		// 目前页面上只能一次上传一个文件.
		for (int i = 0; i < upload.size(); i++) {
			String srcFileName = uploadFileName.get(i);
			ManuDocument doc = new ManuDocument();
			doc.setDocName(srcFileName);
			uploadFileName.set(i, SessionUtil.generateTempId() + "_"
					+ srcFileName);
			doc.setFilePath(FilePathConstant.Manu_WorkOrder.value() + "/"
					+ uploadFileName.get(i));
			doc.setDocType(docType);
			doc.setModifyUser(SessionUtil.getUserName());
			String tempId = SessionUtil.generateTempId();
			sessMap.put(tempId, doc);
			uploadMap.put(tempId, doc);
		}
		fileService.uploadFile(upload, uploadContentType, uploadFileName,
				FilePathConstant.Manu_WorkOrder.value());
		System.out.println("uploadMap : " + uploadMap);
		System.out.println("sessMap size : " + sessMap.size());
		String html = Struts2Util.conventJavaObjToJson(uploadMap);
		Struts2Util.renderHtml(html);
		return null;
	}
	
	/**
	 * 下载附件
	 */
	public void  downloadFile() {
		filePath = this.fileService.getUploadPath()+filePath;
		PdfUtils.downloadFile(filePath, fileName);
	}

	/**
	 * 删除附件
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deleteFile() throws Exception {
		Map<String, ManuDocument> sessMap = (Map<String, ManuDocument>) SessionUtil
				.getRow(SessionConstant.WorkOrderDocument.value(), this.sessId);
		// 操作Session内容删除相应记录， 对于数据库中的则置其value为null.
		String docIds = Struts2Util.getParameter("docIds");
		String[] docIdArray = docIds.split(",");
		for(String key:docIdArray) {
			if (StringUtils.isNumeric(key)) {
				sessMap.put(key, null);
			} else {
				sessMap.remove(key);
			}
		}
		// 返回信号交由js执行reload页面
		Struts2Util.renderText("Success");
		return null;
	}

	/**
	 * 没有保存当前的WorkOrder, 为解决在添加了一个或多个临时的内部order, 取消所有源Order. 输入参数: sessId
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String cancelSave() {
		System.out.println("cancelSave++++++++++++++++++++++++++++++++");
		Map<String, WorkOrderOperation> sessMap = (Map<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		for (Entry<String, WorkOrderOperation> entry : sessMap.entrySet()) {
			if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的

			} else {
				WorkOrderOperation woOperation = (WorkOrderOperation) entry
						.getValue();
				// 删除了， 则Cancel源Order
				if (woOperation.getInternalOrderNo() != null) {
					String reason = "Cancel by work order";
					OrderMainDTO order = orderService.getOrderDetail(woOperation.getInternalOrderNo());
					order.setStatus(OrderStatusType.CN.value());
					orderService.delOrder(woOperation.getInternalOrderNo(),
							reason, SessionUtil.getUserId(), reason, order);
				}
			}
		}
		Struts2Util.renderText("Success");
		return null;
	}
	
	/**
	 *GenerateBatchWorkOrders 弹出页面显示
	 */
	public String GenerateBatchWorkOrders() {
		return "Generate_Batch_Work_Orders";
	}
	
	/**
	 * workGroupAssign弹出页面显示
	 */
	public String showWorkGroupAssign() {
		// 获得选中的WorkCenter关联的多个 WorkGroup.
		this.workGroupList = this.setupService.getGroupListByCenter(centerId);
		String[] workGroupNameArray = workGroupNames.split(",");
		String[] workGroupIdArray = workGroupIds.split(",");
		StringBuffer buf = new StringBuffer();
		if(workGroupNameArray.length==workGroupIdArray.length&&StringUtils.isNotEmpty(workGroupIds)) {
			for(int i =0;i<workGroupIdArray.length;i++) {
				buf.append("<li><input type='checkbox' value='").append(workGroupIdArray[i]).append("::").append(workGroupNameArray[i]).append("' checked/>").append(workGroupNameArray[i]).append("</li>");
			}
		}
		workGroupAssignStr=buf.toString();
		return "work_group_assign";
	}
	
	/**
	 * 给work_order添加work_group
	 */
	public String assign() {
		return NONE;
	}
	
	/**
	 * 删除给work_order分配的work_group
	 */
	public String unassign() {
		return NONE;
	}
	
	/**
	 * 显示在某段时间里创建的所有orderItem
	 * @author lizhang
	 * @return
	 */
	public String showOrderItemList() {
		Date from = DateUtils.formatStr2Date(DateUtils.formatDate2Str(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
		Date to =DateUtils.formatStr2Date(DateUtils.formatDate2Str(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
		if(!today) {
			from = DateUtils.formatStr2Date(fromDate,"yyyy-MM-dd");
			to = DateUtils.formatStr2Date(toDate,"yyyy-MM-dd");
		}
		to = DateUtils.defineDayBefore2Object(to,1,"yyyy-MM-dd",new Date());
		if(itemNo!=null&&itemNo==0) {
			itemNo = null;
		}
		itemList = this.workOrderEntryService.searchItem(from, to,srcSoNo,orderNo,itemNo,id);
		size = Long.valueOf(itemList.size());
		if(itemList!=null&&itemList.size()>0) {
			StringBuffer buf = new StringBuffer();
			for(OrderItemCenterBean item:itemList) {
				buf.append(item.getOrderItemId()).append(",");
			}
			itemIds = buf.substring(0, buf.length()-1);
		}
		return "order_item_list";
	}
	
	/**
	 * 批量生成work order
	 * @return
	 */
	public String genernateBatchWorkOrders() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.workOrderEntryService.createWorkOrders(itemIds, id);
			rt.put("message", "Success");
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
	 * 判断当前选择的item能否生成Work Order
	 * @return
	 */
	public String judgeItem() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			rt.put("message", this.workOrderEntryService.judgeItem(id,itemNo));
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Page<WorkCenter> getWorkCenterPage() {
		return workCenterPage;
	}

	public void setWorkCenterPage(Page<WorkCenter> workCenterPage) {
		this.workCenterPage = workCenterPage;
	}

	public List<WorkGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<WorkGroup> groupList) {
		this.groupList = groupList;
	}

	public WorkCenter getWorkCenter() {
		return workCenter;
	}

	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}

	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public List<UserDTO> getSuperList() {
		return superList;
	}

	public void setSuperList(List<UserDTO> superList) {
		this.superList = superList;
	}

	public WorkOrderDTO getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrderDTO workOrder) {
		this.workOrder = workOrder;
	}

	public List<WorkCenter> getWorkCenterList() {
		return workCenterList;
	}

	public void setWorkCenterList(List<WorkCenter> workCenterList) {
		this.workCenterList = workCenterList;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		if (itemNo == null || ("").equals(itemNo) || ("null").equals(itemNo)) {
			this.itemNo = 0;
		} else {
			try {
				this.itemNo = Integer.parseInt(itemNo);
			} catch (Exception e) {
				this.itemNo = 0;
			}
		}
	}

	public List<Route> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}

	public List<WorkGroup> getWorkGroupList() {
		return workGroupList;
	}

	public void setWorkGroupList(List<WorkGroup> workGroupList) {
		this.workGroupList = workGroupList;
	}

	public List<DropDownDTO> getDropdownItemList() {
		return dropdownItemList;
	}

	public void setDropdownItemList(List<DropDownDTO> dropdownItemList) {
		this.dropdownItemList = dropdownItemList;
	}

	public String getSessId() {
		return sessId;
	}

	public void setSessId(String sessId) {
		this.sessId = sessId;
	}

	public List<QaGroup> getQaGroupList() {
		return qaGroupList;
	}

	public void setQaGroupList(List<QaGroup> qaGroupList) {
		this.qaGroupList = qaGroupList;
	}

	public List<QaClerk> getQaClerkList() {
		return qaClerkList;
	}

	public void setQaClerkList(List<QaClerk> qaClerkList) {
		this.qaClerkList = qaClerkList;
	}

	public List<QaClerk> getQcClerkList() {
		return qcClerkList;
	}

	public void setQcClerkList(List<QaClerk> qcClerkList) {
		this.qcClerkList = qcClerkList;
	}

	public List<PbDropdownListOptions> getPdtDocTypeList() {
		return pdtDocTypeList;
	}

	public void setPdtDocTypeList(List<PbDropdownListOptions> pdtDocTypeList) {
		this.pdtDocTypeList = pdtDocTypeList;
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

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getDelFilePath() {
		return delFilePath;
	}

	public void setDelFilePath(String delFilePath) {
		this.delFilePath = delFilePath;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public Integer getOrderOrderNo() {
		return orderOrderNo;
	}

	public void setOrderOrderNo(String orderOrderNo) {
		if (orderOrderNo == null || ("").equals(orderOrderNo)
				|| ("null").equals(orderOrderNo)) {
			this.orderOrderNo = 0;
		} else {
			try {
				this.orderOrderNo = Integer.parseInt(orderOrderNo);
			} catch (Exception ex) {
				this.orderOrderNo = 0;
			}
		}
	}

	public ShipCondition getShipCondition() {
		return shipCondition;
	}

	public void setShipCondition(ShipCondition shipCondition) {
		this.shipCondition = shipCondition;
	}

	public StorageCondition getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(StorageCondition storageCondition) {
		this.storageCondition = storageCondition;
	}

	public List<MailTemplatesDTO> getMailTmplList() {
		return mailTmplList;
	}

	public void setMailTmplList(List<MailTemplatesDTO> mailTmplList) {
		this.mailTmplList = mailTmplList;
	}

	public List<MailGroup> getMailGroupList() {
		return mailGroupList;
	}

	public void setMailGroupList(List<MailGroup> mailGroupList) {
		this.mailGroupList = mailGroupList;
	}

	public String getSessWorkOrderNo() {
		return sessWorkOrderNo;
	}

	public void setSessWorkOrderNo(String sessWorkOrderNo) {
		this.sessWorkOrderNo = sessWorkOrderNo;
	}


	public MailLogDTO getWorkOrderNote() {
		return workOrderNote;
	}

	public void setWorkOrderNote(MailLogDTO workOrderNote) {
		this.workOrderNote = workOrderNote;
	}

	public String getContactDate() {
		return contactDate;
	}

	public void setContactDate(String contactDate) {
		this.contactDate = contactDate;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}

	public boolean isToday() {
		return today;
	}

	public void setToday(boolean today) {
		this.today = today;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public List<OrderItemCenterBean> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItemCenterBean> itemList) {
		this.itemList = itemList;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	public String getWorkGroupAssignStr() {
		return workGroupAssignStr;
	}

	public void setWorkGroupAssignStr(String workGroupAssignStr) {
		this.workGroupAssignStr = workGroupAssignStr;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public String getWorkGroupNames() {
		return workGroupNames;
	}

	public void setWorkGroupNames(String workGroupNames) {
		this.workGroupNames = workGroupNames;
	}

	public String getWorkGroupIds() {
		return workGroupIds;
	}

	public void setWorkGroupIds(String workGroupIds) {
		this.workGroupIds = workGroupIds;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getSrcSoNo() {
		return srcSoNo;
	}
	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getOrderNoStrs() {
		return orderNoStrs;
	}
	public void setOrderNoStrs(String orderNoStrs) {
		this.orderNoStrs = orderNoStrs;
	}
	public Integer getLotNoCount() {
		return lotNoCount;
	}
	public void setLotNoCount(Integer lotNoCount) {
		this.lotNoCount = lotNoCount;
	}
	public Integer getAddCount() {
		return addCount;
	}
	public void setAddCount(Integer addCount) {
		this.addCount = addCount;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) throws Exception{
		this.filePath = java.net.URLDecoder.decode(filePath,"utf-8");;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) throws Exception{
		this.fileName = java.net.URLDecoder.decode(fileName,"utf-8");
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Document getWordDoc() {
		return wordDoc;
	}
	public void setWordDoc(Document wordDoc) {
		this.wordDoc = wordDoc;
	}
	public WorkOrderLot getWorkOrderLot() {
		return workOrderLot;
	}
	public void setWorkOrderLot(WorkOrderLot workOrderLot) {
		this.workOrderLot = workOrderLot;
	}
	public String getWorkOrderLotSessId() {
		return workOrderLotSessId;
	}
	public void setWorkOrderLotSessId(String workOrderLotSessId) {
		this.workOrderLotSessId = workOrderLotSessId;
	}
	public Integer getAntibodyFlg() {
		return antibodyFlg;
	}
	public void setAntibodyFlg(Integer antibodyFlg) {
		this.antibodyFlg = antibodyFlg;
	}
	public String getProcessFlag() {
		return processFlag;
	}
	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}
	public List<Document> getWordDocList() {
		return wordDocList;
	}
	public void setWordDocList(List<Document> wordDocList) {
		this.wordDocList = wordDocList;
	}
	public String[] getDocNameArray() {
		return docNameArray;
	}
	public void setDocNameArray(String[] docNameArray) {
		this.docNameArray = docNameArray;
	}
	public String[] getFilePathArray() {
		return filePathArray;
	}
	public void setFilePathArray(String[] filePathArray) {
		this.filePathArray = filePathArray;
	}
	public Integer getPlateId() {
		return plateId;
	}
	public void setPlateId(Integer plateId) {
		this.plateId = plateId;
	}



}
