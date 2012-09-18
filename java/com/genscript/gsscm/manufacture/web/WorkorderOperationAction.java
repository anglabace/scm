package com.genscript.gsscm.manufacture.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.WoOperStatus;
import com.genscript.gsscm.common.constant.WorkOrderStatus;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.manufacture.dto.AntibodyOprExperimentDatasDTO;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.dto.WorkOrderOperationBean;
import com.genscript.gsscm.manufacture.entity.AntibodyOprExperimentDatas;
import com.genscript.gsscm.manufacture.entity.AntibodyOprPurificationResults;
import com.genscript.gsscm.manufacture.entity.DsPlateItems;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.OperationResource;
import com.genscript.gsscm.manufacture.entity.Resource;
import com.genscript.gsscm.manufacture.entity.RouteOperation;
import com.genscript.gsscm.manufacture.entity.WoOperationComponent;
import com.genscript.gsscm.manufacture.entity.WoOperationResource;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.manufacture.service.WorkOrderEntryService;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.entity.Role;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.system.entity.UpdateRequestLog;
import com.genscript.gsscm.system.service.UpdateRequestLogService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "workorder_operation_list", location = "manufacture/workorder_operation_list.jsp"),
		@Result(name = "workorder_operation_list_anti", location = "manufacture/workorder_operation_list_anti.jsp"),
		@Result(name = "workorder_operation_task_list", location = "manufacture/workorder_operation_task_list.jsp"),
		@Result(name = "workorder_operation_task_list_anti", location = "manufacture/workorder_operation_task_list_anti.jsp"),
		@Result(name = "workorder_operation_edit", location = "manufacture/workorder_operation_edit.jsp"),
		@Result(name = "workorder_operation_select", location = "manufacture/workorder_operation_select.jsp"),
		@Result(name = "workorder_operation_component_list", location = "manufacture/workorder_operation_component_list.jsp"),
		@Result(name = "workorder_operation_component_edit", location = "manufacture/workorder_operation_component_edit.jsp"),
		@Result(name = "workorder_operation_resource_list", location = "manufacture/workorder_operation_resource_list.jsp"),
		@Result(name = "workorder_operation_resource_edit", location = "manufacture/workorder_operation_resource_edit.jsp"),
		@Result(name="workOrder_operation_operate_list",location="manufacture/workOrder_operation_operate_list.jsp"),
		@Result(name="inner_workOrder_operation_list",location="manufacture/inner_workOrder_operation_list.jsp"),
		@Result(name="batch_operation_dlg",location="manufacture/batch_operation_dlg.jsp"),
		@Result(name="seq_input_dlg",location="manufacture/seq_input_dlg.jsp"),
		@Result(name="custom_date_change_log",location="manufacture/custom_date_change_log.jsp"),
		@Result(name="plate_items",location="manufacture/plate_items.jsp")
		})
public class WorkorderOperationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8362147274629373630L;
	@Autowired
	private SetupService setupService;
	@Autowired
	private WorkOrderEntryService workOrderEntryService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private PublicService publicService;
	// 内部订单时需要.
	@Autowired
	private OrderService orderService;
	@Autowired
	private UpdateRequestLogService updateRequestLogService;
	private Integer id;
	private Map<String, WorkOrderOperation> woOperationMap;
	private String sessId;
	private String sessWOPKey;// Work Order Operation.
	private List<String> delROIdList;
	private List<Integer> operationIdList;
	// 用于批量更新seqNo.
	private List<Integer> seqNoList;
	private List<String> keyList;
	// 编辑Operation
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private WorkOrderOperation woOperation;
	private List<WorkCenter> centerList;
	private List<UserDTO> superList;
	//
	private String sessResKey;
	private String sessComKey;
	private WoOperationResource woResource;
	private WoOperationComponent woComponent;
	private String changeStatusFlg="Y";
	
	private Integer workCenterId;
	private String sequence;
	private String sequence3;
	private String sequence5;
	private String jobName;
	
	private String woStatus;
	
	private String comments;
	private String customStartDate;
	
	private Integer srcSoNo;
	
	private String customConfirmInfo;
	
	private WorkOrderOperationBean workOrderOperationDTO;
	private List<WorkOrderOperationBean> workOrderOperationList;
	private String woOperationIds;
	
	private List<WorkCenter> workCenterList;
	
	private List<UpdateRequestLog> updateRequestLogList;
	
	private List<DsPlateItems> woProcessList;
	
	//experiment
	private List<String> experimentDate;
	private List<String> hostNo;
	private List<String> loading;
	private List<String> remainsGel;
	private List<String> concentration;
	private List<String> volume;
	private List<String> quantity;
	private List<String> comment;
	private List<String> ids;
	private String delResultIds;
	private List<String> hostNoList;
	
	private AntibodyOprExperimentDatasDTO antibodyOprExperimentDatasDTO;
	
	//flag
	private Integer customFlag;
	
	private Integer antibodyFlg;
	public String add() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public String edit() {
		try {
			// 删除Session Temp Resource.
			SessionUtil.deleteRow(SessionConstant.WOTempResource.value(),
					this.sessWOPKey);
			SessionUtil.deleteRow(SessionConstant.WOTempComponent.value(),
					this.sessWOPKey);

			Map<String, WorkOrderOperation> woOperationMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
					.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
			// 得到数据用于返回页面.
			this.woOperation = woOperationMap.get(this.sessWOPKey);
			Map<String, Object> session = ActionContext.getContext().getSession();
			WorkOrderDTO workOrder = (WorkOrderDTO)session.get(this.sessId);
			antibodyOprExperimentDatasDTO = new AntibodyOprExperimentDatasDTO();
			antibodyOprExperimentDatasDTO.setHostAmount(workOrder!=null?workOrder.getHostAmount():null);
			antibodyOprExperimentDatasDTO.setHostName(workOrder!=null?workOrder.getHostName():null);
			if(StringUtil.isNumeric(this.sessWOPKey)
					&&(this.woOperation.getAntibodyOprExperimentDatasMap()==null||this.woOperation.getAntibodyOprExperimentDatasMap().size()==0)
					&&(this.woOperation.getAntibodyOprPurificationResultsMap()==null||this.woOperation.getAntibodyOprPurificationResultsMap().size()==0)) {
				List<AntibodyOprExperimentDatas> antibodyOprExperimentDatasList = workOrderEntryService.getAntibodyOprExperimentDatas(Integer.parseInt(this.sessWOPKey));
				if(antibodyOprExperimentDatasList==null||antibodyOprExperimentDatasList.size()==0) {
					antibodyOprExperimentDatasList = new ArrayList<AntibodyOprExperimentDatas>();
					if(StringUtils.isNotEmpty(workOrder.getHostNo())) {
						for(String hostNo:workOrder.getHostNo().split(",")) {
							AntibodyOprExperimentDatas antibodyOprExperimentDatas = new AntibodyOprExperimentDatas();
							antibodyOprExperimentDatas.setHostNo(hostNo);
							antibodyOprExperimentDatas.setHostName(workOrder!=null?workOrder.getHostName():null);
							antibodyOprExperimentDatas.setHostAmount(workOrder!=null?workOrder.getHostAmount():null);
							antibodyOprExperimentDatasList.add(antibodyOprExperimentDatas);
						}
					}
				}
				List<AntibodyOprPurificationResults> list = workOrderEntryService.getAntibodyOprPurificationResultsList(Integer.parseInt(this.sessWOPKey));
				Map<String,AntibodyOprPurificationResults> antibodyOprPurificationResultsMap = SessionUtil.convertList2Map(list, "id");
				Map<String,AntibodyOprExperimentDatas> AntibodyOprExperimentDatasMap = SessionUtil.convertList2Map(antibodyOprExperimentDatasList, "hostNo");
				this.woOperation.setAntibodyOprExperimentDatasMap(AntibodyOprExperimentDatasMap);
				this.woOperation.setAntibodyOprPurificationResultsMap(antibodyOprPurificationResultsMap);
			}
			if(this.woOperation.getOperation()!=null&&this.woOperation.getOperation().getSetupTime()==null) {
				this.woOperation.getOperation().setSetupTime(0);
			}
			if(this.woOperation.getOperation()!=null&&this.woOperation.getOperation().getRunTime()==null) {
				this.woOperation.getOperation().setRunTime(new BigDecimal(0));
			}
			if(this.woOperation.getStatus()==null||StringUtils.isEmpty(this.woOperation.getStatus())) {
				this.woOperation.setStatus(WoOperStatus.New.value());
			}
			if(woOperation.getSeqNo()>1) {
				a:for(int i=1;i<woOperation.getSeqNo();i++) {
					Iterator<Map.Entry<String,WorkOrderOperation>> it = woOperationMap.entrySet().iterator();
					while(it.hasNext()) {
						Entry<String,WorkOrderOperation> entry = it.next();
						WorkOrderOperation WorkOrderOperation = entry.getValue();
						if(WorkOrderOperation!=null&&WorkOrderOperation.getSeqNo()==i&&!WoOperStatus.Completed.value().equals(WorkOrderOperation.getStatus())) {
							changeStatusFlg = "N";
							break a;
						}
					}
				}
			}
			
			// 1. 从session中获得WOResource, 如果为空且sessWOPKey是数字则从数据库中查找, 并把结果存入session
			// Session WOResource中
			// 2. 如果Session WOResource不为空则复制到Temp Resource.
			Map<String, WoOperationResource> woResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
					.getRow(SessionConstant.WOResource.value(), this.sessWOPKey);
			if (woResMap == null) {
				if (StringUtil.isNumeric(this.sessWOPKey)) {
					List<WoOperationResource> dbWoResList = workOrderEntryService
							.getWoResourceList(Integer.parseInt(this.sessWOPKey));
					if (dbWoResList != null && dbWoResList.size() > 0) {
						woResMap = new LinkedHashMap<String, WoOperationResource>();
						for (WoOperationResource dbWoRes : dbWoResList) {
							woResMap.put(dbWoRes.getId() + "", dbWoRes);
						}
						SessionUtil.insertRow(SessionConstant.WOResource.value(),
								this.sessWOPKey, woResMap);
					} else {
						woResMap = new LinkedHashMap<String, WoOperationResource>();
						List<OperationResource> opReslist = setupService.getAllOperationResource(this.woOperation.getOperation().getId());
						for (OperationResource dbOperationRes : opReslist) {
							Resource res = dbOperationRes.getResource();
							WoOperationResource woRes = new WoOperationResource();
							woRes.setSeqNo(dbOperationRes.getSeqNo());
							woRes.setResource(res);
							woRes.setStatus(res.getStatus());
							woResMap.put(SessionUtil.generateTempId(), woRes);
						}
					}
				}
			}
			if (woResMap != null) {
				Map<String, WoOperationResource> woTempResMap = new LinkedHashMap<String, WoOperationResource>();
				for (Entry<String, WoOperationResource> entry : woResMap.entrySet()) {
					WoOperationResource value = entry.getValue();
					if (value != null) {
						value = (WoOperationResource) value.clone();
					}
					woTempResMap.put(entry.getKey(), value);
				}
				SessionUtil.insertRow(SessionConstant.WOTempResource.value(),
						this.sessWOPKey, woTempResMap);
			}
			// 3. 经过以上步骤: WOTempResource可能为null, 也可能不为null.
			/** ******************************************************** */
			// 1. 从session中获得WOComponent, 如果为空且sessWOPKey是数字则从数据库中查找, 并把结果存入session
			// Session WOComponent中
			// 2. 如果Session WOComponent不为空则复制到Temp Component.
			Map<String, WoOperationComponent> woComMap = (LinkedHashMap<String, WoOperationComponent>) SessionUtil
					.getRow(SessionConstant.WOComponent.value(), this.sessWOPKey);
			if (woComMap == null) {
				if (StringUtils.isNumeric(this.sessWOPKey)) {
					List<WoOperationComponent> dbWoComList = workOrderEntryService
							.getWoComponentList(Integer.parseInt(this.sessWOPKey));
					if (dbWoComList != null && dbWoComList.size() > 0) {
						woComMap = new LinkedHashMap<String, WoOperationComponent>();
						for (WoOperationComponent dbWoCom : dbWoComList) {
							woComMap.put(dbWoCom.getId() + "", dbWoCom);
						}
						SessionUtil.insertRow(SessionConstant.WOComponent.value(),
								this.sessWOPKey, woComMap);
					}
				}
			}
			if (woComMap != null) {
				Map<String, WoOperationComponent> woTempComMap = new LinkedHashMap<String, WoOperationComponent>();
				for (Entry<String, WoOperationComponent> entry : woComMap
						.entrySet()) {
					WoOperationComponent value = entry.getValue();
					if (value != null) {
						value = (WoOperationComponent) value.clone();
					}
					woTempComMap.put(entry.getKey(), value);
				}
				SessionUtil.insertRow(SessionConstant.WOTempComponent.value(),
						this.sessWOPKey, woTempComMap);
			}
			// 3. 经过以上步骤: WOTempComponent可能为null, 也可能不为null.

			// 获取下拉框数据源
			this.centerList = this.setupService.getAllWorkCenter();
			Role role = privilegeService.getSuperRole();
			if (role != null) {
				this.superList = privilegeService.getUserListByRole(role
						.getRoleId());
			}
			List<DropDownListName> listName = new ArrayList<DropDownListName>();
			listName.add(DropDownListName.WO_OPERATION_STATUS);
			listName.add(DropDownListName.HOSTNAME);
			listName.add(DropDownListName.REMAINSGEL);
			dropDownMap = publicService.getDropDownMap(listName);
			hostNoList = new ArrayList<String>();
			if(StringUtils.isNotEmpty(workOrder.getHostNo())) {
				for(String hostNo:workOrder.getHostNo().split(",")) {
					hostNoList.add(hostNo);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "workorder_operation_edit";
	}

	/**
	 * 前端点击Routing Apply触发 需要传入的值有: id , sessId. 获得当前session中的map, 并剔除已删除的，
	 * 使得当前apply的能够和之前的seqNo连续上.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String applyRouting() throws Exception {
		Map<String, WorkOrderOperation> sessMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		Map<String, WorkOrderOperation> sessMap2 = new LinkedHashMap<String, WorkOrderOperation>();
		if(sessMap!=null&&sessMap.size()>0) {
			Iterator<Entry<String, WorkOrderOperation>> it = sessMap.entrySet().iterator();
			// 先清除所有.
			while (it.hasNext()) {
				// 数据库已有的
				Entry<String, WorkOrderOperation> entry = it.next();
				if (StringUtils.isNumeric(entry.getKey())) {
					sessMap2.put(entry.getKey(), null);
				} 
			}
		}
		
		int seqNo = 1;
		// 此时this.id为routing的id值.
		List<RouteOperation> dbList = this.setupService
				.getAllRouteOperation(this.id);
		Date preExptdEndDate = new Date();
		for (RouteOperation dto : dbList) {
			WorkOrderOperation wo = new WorkOrderOperation();
			wo.setOperation(dto.getOperation());
			wo.setStatus(WoOperStatus.New.value());
			if (seqNo == 1) {
				wo.setExptdStartDate(new java.sql.Date(new Date().getTime()));// 第一条设置Schedule Start Date.
				preExptdEndDate = wo.getExptdEndDate();
				wo.setCustomStartDate(wo.getExptdStartDate());
				wo.setCustomEndDate(wo.getExptdEndDate());
			} else {
				wo.setExptdStartDate(new java.sql.Date(preExptdEndDate.getTime()));// 第一条设置Schedule Start Date.
				preExptdEndDate = wo.getExptdEndDate();
				wo.setCustomStartDate(wo.getExptdStartDate());
				wo.setCustomEndDate(wo.getExptdEndDate());
			}
			wo.setSeqNo(seqNo);
			String key = SessionUtil.generateTempId();
			sessMap2.put(key, wo);
			seqNo++;
			Integer srcOperationId = dto.getOperation().getId();
			List<OperationResource> opReslist = setupService.getAllOperationResource(srcOperationId);
			Map<String, WoOperationResource> woTempResMap = new LinkedHashMap<String, WoOperationResource>();
			for (OperationResource dbOperationRes : opReslist) {
				Resource res = dbOperationRes.getResource();
				WoOperationResource woRes = new WoOperationResource();
				woRes.setSeqNo(dbOperationRes.getSeqNo());
				woRes.setResource(res);
				woRes.setStatus(res.getStatus());
				woTempResMap.put(SessionUtil.generateTempId(), woRes);
			}
			SessionUtil.insertRow(SessionConstant.WOTempResource.value(),
					key, woTempResMap);
			SessionUtil.insertRow(SessionConstant.WOResource.value(),
					key, woTempResMap);
		}
		SessionUtil.updateRow(SessionConstant.WorkOrderOperation.value(), this.sessId,sessMap2);
		System.out.println("sessMap: " + sessMap2);
		Struts2Util.renderText("success");
		return null;
	}

	/**
	 * 为WorkOrder选择一或多个Operation.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectOperationForWO() throws Exception {
		// 在getOperationList方法已经初始化， 这里sessMap肯定不为null.
		// 操作完成之后也不需要更新session, 因为它是引用类型的.
		Map<String, WorkOrderOperation> sessMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		String operationIdList = Struts2Util.getParameter("operationIdList");
		String[] idList = operationIdList.split(",");
		for (String operationId : idList) {
			createNewWoOperation(sessMap,Integer.parseInt(operationId));
		}
		// 重排Session中数据.
		this.sortSessionOperation(sessMap);
		System.out.println("sessMap: " + sessMap);
		Struts2Util.renderText("success");
		return null;
	}
	
	private void createNewWoOperation(Map<String, WorkOrderOperation> sessMap,Integer operationId) {
		try {
			WorkOrderOperation wo = new WorkOrderOperation();
			wo.setOperation(this.setupService.getOperation(operationId));
			wo.setStatus(WoOperStatus.New.value());
			String key = SessionUtil.generateTempId();
			sessMap.put(key, wo);
			List<OperationResource> opReslist = setupService.getAllOperationResource(operationId);
			Map<String, WoOperationResource> woTempResMap = new LinkedHashMap<String, WoOperationResource>();
			for (OperationResource dbOperationRes : opReslist) {
				Resource res = dbOperationRes.getResource();
				WoOperationResource woRes = new WoOperationResource();
				woRes.setSeqNo(dbOperationRes.getSeqNo());
				woRes.setResource(res);
				woRes.setStatus(res.getStatus());
				woTempResMap.put(SessionUtil.generateTempId(), woRes);
			}
			SessionUtil.insertRow(SessionConstant.WOTempResource.value(),
					key, woTempResMap);
			SessionUtil.insertRow(SessionConstant.WOResource.value(),
					key, woTempResMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 创建了一个内部订单作为WO Operation. 传过来的参数: orderNo, sessId.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectOrderForWO() throws Exception {
		// 在getOperationList方法已经初始化， 这里sessMap肯定不为null.
		// 操作完成之后也不需要更新session, 因为它是引用类型的.
		Map<String, WorkOrderOperation> sessMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		WorkOrderOperation wo = new WorkOrderOperation();
		wo.setStatus(WoOperStatus.New.value());
		Integer orderNo = Integer.parseInt(Struts2Util.getParameter("orderNo"));
		if(orderNo==null) {
			Struts2Util.renderText("error");
			return null;
		}
		Integer maxLeadTime = this.workOrderEntryService.getMaxLeadtime(orderNo);
		Operation operation = this.setupService.getInnerOPeration();
		if(operation==null) {
			operation = new Operation();
		}
		operation.setName(orderNo.toString());
		operation.setSetupTime(maxLeadTime);
		wo.setOperation(operation);
		wo.setInternalOrderNo(orderNo);
		sessMap.put(SessionUtil.generateTempId(), wo);
		// 重排Session中数据.
		this.sortSessionOperation(sessMap);
		Struts2Util.renderText("success");
		return null;
	}

	/**
	 * 进入WorkOrder Operation页面. 对于已删除的数据库中的记录（还没有最终保存), 不显示在页面中woOperationMap.
	 * 输入: sessId 输出: this.woOperationMap
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getOperationList() {
		try {
			Map<String, WorkOrderOperation> sessMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
					.getRow(SessionConstant.WorkOrderOperation.value(),
							this.sessId);
			Map<String, Object> session = ActionContext.getContext().getSession();
			WorkOrderDTO workOrder =(WorkOrderDTO)session.get(this.sessId);
			woStatus = workOrder.getStatus();
			// 初始时sessMap为null.
			if (sessMap == null) {
				// 是编辑workorder, 则从数据库中查找, 并放进session
				if (StringUtil.isNumeric(this.sessId)) {
					List<WorkOrderOperation> dbList = this.workOrderEntryService
							.getAllWOOperation(Integer.parseInt(this.sessId));
					sessMap = new LinkedHashMap<String, WorkOrderOperation>();
					if (dbList != null && dbList.size() > 0) {
						for (WorkOrderOperation dto : dbList) {
							sessMap.put(dto.getId() + "", dto);
						}
					} else {
						if (workOrder.getStandardRoutine() != null) {
							int seqNo = 1;
							List<RouteOperation> dbList2 = this.setupService
									.getAllRouteOperation(workOrder
											.getStandardRoutine());
							Date preExptdEndDate = new Date();
							for (RouteOperation dto : dbList2) {
								WorkOrderOperation wo = new WorkOrderOperation();
								wo.setOperation(dto.getOperation());
								wo.setStatus(WoOperStatus.New.value());
								if (seqNo == 1) {
									wo.setExptdStartDate(new java.sql.Date(new Date().getTime()));// 第一条设置Schedule
																		// Start
																		// Date.
									preExptdEndDate = wo.getExptdEndDate();
									wo.setCustomStartDate(wo.getExptdStartDate());
									wo.setCustomEndDate(wo.getExptdEndDate());
								} else {
									wo.setExptdStartDate(new java.sql.Date(preExptdEndDate.getTime()));// 第一条设置Schedule Start Date.
									preExptdEndDate = wo.getExptdEndDate();
									wo.setCustomStartDate(wo.getExptdStartDate());
									wo.setCustomEndDate(wo.getExptdEndDate());
								}
								wo.setSeqNo(seqNo);
								String key = SessionUtil.generateTempId();
								sessMap.put(key, wo);
								seqNo++;
								Integer srcOperationId = dto.getOperation().getId();
								List<OperationResource> opReslist = setupService.getAllOperationResource(srcOperationId);
								Map<String, WoOperationResource> woTempResMap = new LinkedHashMap<String, WoOperationResource>();
								for (OperationResource dbOperationRes : opReslist) {
									Resource res = dbOperationRes.getResource();
									WoOperationResource woRes = new WoOperationResource();
									woRes.setSeqNo(dbOperationRes.getSeqNo());
									woRes.setResource(res);
									woRes.setStatus(res.getStatus());
									woTempResMap.put(SessionUtil.generateTempId(), woRes);
								}
								SessionUtil.insertRow(SessionConstant.WOTempResource.value(),
										key, woTempResMap);
								SessionUtil.insertRow(SessionConstant.WOResource.value(),
										key, woTempResMap);
							}
						}
					}

				}
			}
			if (sessMap == null) {
				sessMap = new LinkedHashMap<String, WorkOrderOperation>();
			} else {
				// 以下最终显示到页面上的数据集合, 对已剔除的数据进行筛选.
				this.woOperationMap = new LinkedHashMap<String, WorkOrderOperation>();
				for (Entry<String, WorkOrderOperation> entry : sessMap
						.entrySet()) {
					WorkOrderOperation groupRes = (WorkOrderOperation) entry
							.getValue();
					// 数据库已有的
					if (StringUtils.isNumeric(entry.getKey())) {
						if (groupRes == null) {// 执行了临时的删除操作则不应再显示

						} else {
							woOperationMap
									.put(entry.getKey(), entry.getValue());
						}
					} else {
						// Session中的都应显示.
						woOperationMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
			SessionUtil.insertRow(SessionConstant.WorkOrderOperation.value(),
					this.sessId, sessMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(antibodyFlg!=null&&antibodyFlg==1) {
			return "workorder_operation_list_anti";
		}
		return "workorder_operation_list";
	}

	public String getOperationTaskList() {
		try {
			Map<String, WorkOrderOperation> sessMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
					.getRow(SessionConstant.WorkOrderOperation.value(),
							this.sessId);
			// 初始时sessMap为null.
			if (sessMap == null) {
				// 是编辑workorder, 则从数据库中查找, 并放进session
				if (StringUtils.isNumeric(this.sessId)) {
					List<WorkOrderOperation> dbList = this.workOrderEntryService
							.getAllWOOperation(Integer.parseInt(this.sessId));
					sessMap = new LinkedHashMap<String, WorkOrderOperation>();
					if (dbList != null && dbList.size() > 0) {
						for (WorkOrderOperation dto : dbList) {
							sessMap.put(dto.getId() + "", dto);
						}
					} else {
						WorkOrderDTO workOrder = workOrderEntryService
								.getWorkOrder(Integer.parseInt(this.sessId));
						if (workOrder.getStandardRoutine() != null) {
							int seqNo = 1;
							List<RouteOperation> dbList2 = this.setupService
									.getAllRouteOperation(workOrder
											.getStandardRoutine());
							Date preExptdEndDate = new Date();
							for (RouteOperation dto : dbList2) {
								WorkOrderOperation wo = new WorkOrderOperation();
								wo.setOperation(dto.getOperation());
								wo.setStatus(WoOperStatus.New.value());
								if (seqNo == 1) {
									wo.setExptdStartDate(new java.sql.Date(new Date().getTime()));// 第一条设置Schedule
									// Start
									// Date.
									preExptdEndDate = wo.getExptdEndDate();
									wo.setCustomStartDate(wo.getExptdStartDate());
									wo.setCustomEndDate(wo.getExptdEndDate());
								} else {
									wo.setExptdStartDate(new java.sql.Date(preExptdEndDate.getTime()));// 第一条设置Schedule Start Date.
									preExptdEndDate = wo.getExptdEndDate();
									wo.setCustomStartDate(wo.getExptdStartDate());
									wo.setCustomEndDate(wo.getExptdEndDate());
								}
								wo.setSeqNo(seqNo);
								sessMap.put(SessionUtil.generateTempId(), wo);
								seqNo++;
							}
						}
					}
				}
			}
			if (sessMap == null) {
				sessMap = new LinkedHashMap<String, WorkOrderOperation>();
			} else {
				// 以下最终显示到页面上的数据集合, 对已剔除的数据进行筛选.
				this.woOperationMap = new LinkedHashMap<String, WorkOrderOperation>();
				for (Entry<String, WorkOrderOperation> entry : sessMap
						.entrySet()) {
					WorkOrderOperation groupRes = (WorkOrderOperation) entry
							.getValue();
					// 数据库已有的
					if (StringUtils.isNumeric(entry.getKey())) {
						if (groupRes == null) {// 执行了临时的删除操作则不应再显示

						} else {
							woOperationMap
									.put(entry.getKey(), entry.getValue());
						}
					} else {
						// Session中的都应显示.
						woOperationMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
			SessionUtil.insertRow(SessionConstant.WorkOrderOperation.value(),
					this.sessId, sessMap);
		} catch (Exception e) {

		}
		if(antibodyFlg!=null&&antibodyFlg==1) {
			return "workorder_operation_task_list_anti";
		}
		return "workorder_operation_task_list";
	}

	/**
	 * 操作Session内容更新交换seqNo的两条相应记录， 再给map中的数据根据seqNo进行重排后更新session，
	 * 返回由js执行reload页面.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateSeqNo() {
		// 取得session中的
		Map<String, WorkOrderOperation> sessMap = (Map<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		// 操作Session内容更新交换seqNo的两条相应记录.
		Date startDate = new Date();
		if(this.keyList.size()==2&&this.seqNoList.size()==2) {
			WorkOrderOperation ors_0 = sessMap.get(keyList.get(0));
			WorkOrderOperation ors_1 = sessMap.get(keyList.get(1));
			if(ors_0.getSeqNo().intValue()>=ors_1.getSeqNo().intValue()) {
				startDate = ors_1.getExptdStartDate();
			} else {
				startDate = ors_0.getExptdStartDate();
			}
			ors_0.setSeqNo(this.seqNoList.get(0));
			ors_0.setExptdStartDate(new java.sql.Date(startDate.getTime()));
			ors_1.setExptdStartDate(ors_0.getExptdEndDate());
			ors_0.setCustomStartDate(ors_0.getExptdStartDate());
			ors_0.setCustomEndDate(ors_0.getExptdEndDate());
			ors_1.setCustomStartDate(ors_1.getExptdStartDate());
			ors_1.setCustomEndDate(ors_1.getExptdEndDate());
			ors_1.setSeqNo(this.seqNoList.get(1));
			
			Map<String, WorkOrderOperation> sortedSessMap = new LinkedHashMap<String, WorkOrderOperation>();
			for (Entry<String, WorkOrderOperation> entry : sessMap.entrySet()) {
				WorkOrderOperation wo = (WorkOrderOperation) entry.getValue();
				if(entry.getKey().equals(keyList.get(0))) {
					sortedSessMap.put(keyList.get(1), ors_1);
					continue;
				}
				if(entry.getKey().equals(keyList.get(1))) {
					sortedSessMap.put(keyList.get(0), ors_0);
					continue;
				}
				sortedSessMap.put(entry.getKey(), wo);
				
			}
			SessionUtil.insertRow(SessionConstant.WorkOrderOperation.value(),
					this.sessId, sortedSessMap);// 重设为已排过序的Map.
		}
		
//		for (int k = 0; k < this.keyList.size(); k++) {
//			WorkOrderOperation ors = sessMap.get(keyList.get(k));
//			if(ors.getSeqNo().intValue()!=this.seqNoList.get(k)) {
//				startDate = ors.getExptdStartDate();
//				endDate = ors.getExptdEndDate();
//			}
//			ors.setSeqNo(this.seqNoList.get(k));
//		}

		// 以下最终显示到页面上的数据集合（即session中的值, 以防页面刷新）, 进行重新排序.
//		Map<String, WorkOrderOperation> sortedSessMap = new LinkedHashMap<String, WorkOrderOperation>();
//		int len = sessMap.size();
		// 循环依次获得每序号递增的map.
//		for (int i = 1; i <= len; i++) {
//			for (Entry<String, WorkOrderOperation> entry : sessMap.entrySet()) {
//				WorkOrderOperation wo = (WorkOrderOperation) entry.getValue();
//				// 数据库已有的
//				if (StringUtils.isNumeric(entry.getKey())) {
//					if (wo == null) {// 执行了临时的删除操作也应包含在.
//						sortedSessMap.put(entry.getKey(), wo);
//					} else {
//						if (wo.getSeqNo().intValue() == i) {
//							sortedSessMap.put(entry.getKey(), wo);
//							break;
//						}
//					}
//				} else {
//					if (wo.getSeqNo().intValue() == i) {
//						sortedSessMap.put(entry.getKey(), wo);
//						break;
//					}
//				}
//			}
//		}
		// 重排后更新session.
//		SessionUtil.insertRow(SessionConstant.WorkOrderOperation.value(),
//				this.sessId, sortedSessMap);// 重设为已排过序的Map.
		// 返回信号交由js执行reload页面
		Struts2Util.renderText("success");
		return NONE;
	}
	
	public String changeOperationColumn() {
		Map<String, Object> rt = new HashMap<String, Object>();
		// 取得session中的
		Map<String, WorkOrderOperation> sessMap = (Map<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		if(sessMap==null) {
			sessMap = new LinkedHashMap<String, WorkOrderOperation>();
		}
		WorkOrderOperation workOrderOperation = sessMap.get(this.sessWOPKey);
		if(workOrderOperation==null) {
			rt.put("message", "Error");
			Struts2Util.renderJson(rt);
			return NONE;
		}
		if(comments!=null) {
			workOrderOperation.getOperation().setComment(comments);
		}
		if(customStartDate!=null) {
			boolean isOk = false;
			Integer seqNo = workOrderOperation.getSeqNo();
			
			java.sql.Date preExptdEndDate = null;
			for(Entry<String, WorkOrderOperation> entity:sessMap.entrySet()) {
				WorkOrderOperation workOrderOperation2 =  entity.getValue();
				if(seqNo.intValue()==workOrderOperation2.getSeqNo().intValue()) {
					if(preExptdEndDate==null) {
						workOrderOperation2.setCustomStartDate(new java.sql.Date(DateUtils.formatStr2Date(customStartDate,"yyyy-MM-dd").getTime()));
					} else {
						workOrderOperation2.setCustomStartDate(preExptdEndDate);
					}
					preExptdEndDate = workOrderOperation2.getCustomEndDate();
					seqNo++;
				}
			}
		}
		rt.put("message", "Success");
		Struts2Util.renderJson(rt);
		return NONE;
	}

	/**
	 * 保存当前页面信息并合并当前session WOTempOperation ==> WorkOrderOperation, 从Temp
	 * Resource 复制到 Session Resource 输入参数 this.sessWOPKey, this.woOperation
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveOperation() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String, WorkOrderOperation> sessOperMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
					.getRow(SessionConstant.WorkOrderOperation.value(),
							this.sessId);
			if(sessOperMap==null) {
				sessOperMap = new LinkedHashMap<String, WorkOrderOperation>();
			}
			WorkOrderOperation workOrderOperation = sessOperMap.get(this.sessWOPKey);
			if(workOrderOperation==null) {
				workOrderOperation = this.woOperation;
			}
			boolean ischangeDate = false;
			workOrderOperation.setStatus(this.woOperation.getStatus());
			workOrderOperation.setComment(this.woOperation.getComment());
			Map<String,AntibodyOprExperimentDatas> map = new HashMap<String,AntibodyOprExperimentDatas>();
			if(antibodyOprExperimentDatasDTO!=null&&antibodyOprExperimentDatasDTO.getHostNo()!=null
					&&antibodyOprExperimentDatasDTO.getHostNo().size()>0) {
				for(int i =0 ;i<antibodyOprExperimentDatasDTO.getHostNo().size();i++) {
					AntibodyOprExperimentDatas antibodyOprExperimentDatas = new AntibodyOprExperimentDatas();
					antibodyOprExperimentDatas.setHostAmount(antibodyOprExperimentDatasDTO.getHostAmount());
					antibodyOprExperimentDatas.setHostName(antibodyOprExperimentDatasDTO.getHostName());
					antibodyOprExperimentDatas.setHostNo(antibodyOprExperimentDatasDTO.getHostNo().get(i));
					antibodyOprExperimentDatas.setExperimentalResult(antibodyOprExperimentDatasDTO.getExperimentalResult()!=null?
																	antibodyOprExperimentDatasDTO.getExperimentalResult().get(i):null);
					antibodyOprExperimentDatas.setId(antibodyOprExperimentDatasDTO.getId()!=null?
																	antibodyOprExperimentDatasDTO.getId().get(i):null);
					antibodyOprExperimentDatas.setLocation(antibodyOprExperimentDatasDTO.getLocation()!=null?
																	antibodyOprExperimentDatasDTO.getLocation().get(i):null);
					antibodyOprExperimentDatas.setRemains(antibodyOprExperimentDatasDTO.getRemains()!=null?
																	antibodyOprExperimentDatasDTO.getRemains().get(i):null);
					antibodyOprExperimentDatas.setRemainsLocation(antibodyOprExperimentDatasDTO.getRemainsLocation()!=null?
																	antibodyOprExperimentDatasDTO.getRemainsLocation().get(i):null);
					antibodyOprExperimentDatas.setVolume(antibodyOprExperimentDatasDTO.getVolume()!=null?
																	antibodyOprExperimentDatasDTO.getVolume().get(i):null);
					antibodyOprExperimentDatas.setComment(antibodyOprExperimentDatasDTO.getComment()!=null?
																	antibodyOprExperimentDatasDTO.getComment().get(i):null);
					map.put(antibodyOprExperimentDatasDTO.getHostNo().get(i), antibodyOprExperimentDatas);
				}
			}
			workOrderOperation.setAntibodyOprExperimentDatasMap(map);
			if((this.woOperation.getCustomStartDate()!=null&&this.woOperation.getCustomStartDate()!=workOrderOperation.getCustomStartDate())||
					(this.woOperation.getCustomEndDate()!=null&&this.woOperation.getCustomEndDate()!=workOrderOperation.getCustomEndDate())) {
				ischangeDate = true;
			}
			workOrderOperation.setCustomStartDate(this.woOperation.getCustomStartDate());
			workOrderOperation.setCustomEndDate(this.woOperation.getCustomEndDate());
			Map<String,AntibodyOprPurificationResults> resultMap = new HashMap<String,AntibodyOprPurificationResults>();
			if(experimentDate!=null&&experimentDate.size()>0) {
				for(int i =0;i<experimentDate.size();i++) {
					AntibodyOprPurificationResults antibodyOprPurificationResults = new AntibodyOprPurificationResults();
					antibodyOprPurificationResults.setId(StringUtil.isNumeric(ids.get(i))?Integer.parseInt(ids.get(i)):null);
					antibodyOprPurificationResults.setComment(comment.get(i));
					antibodyOprPurificationResults.setConcentration(concentration.get(i));
					antibodyOprPurificationResults.setExperimentDate(StringUtils.isNotEmpty(experimentDate.get(i))
							?DateUtils.formatStr2Date(experimentDate.get(i),DateUtils.C_DATE_PATTON_DEFAULT):null);
					antibodyOprPurificationResults.setHostNo(hostNo.get(i));
					antibodyOprPurificationResults.setLoading(loading.get(i));
					antibodyOprPurificationResults.setQuantity(StringUtil.isNumeric(quantity.get(i))?new BigDecimal(quantity.get(i)):null);
					antibodyOprPurificationResults.setRemainsGel(remainsGel.get(i));
					antibodyOprPurificationResults.setVolume(StringUtil.isNumeric(volume.get(i))?new BigDecimal(volume.get(i)):null);
					String key = SessionUtil.generateTempId();
					resultMap.put(key,antibodyOprPurificationResults);
				}
			}
			workOrderOperation.setAntibodyOprPurificationResultsMap(resultMap);
			workOrderOperation.setDelResultIds(delResultIds);
			if(workOrderOperation!=null&&WoOperStatus.Inprogress.value().equals(workOrderOperation.getStatus())) {
				if(workOrderOperation.getActualStartDate()==null) {
					workOrderOperation.setActualStartDate(new java.sql.Date(new Date().getTime()));
				}
				Map<String, Object> session = ActionContext.getContext().getSession();
				WorkOrderDTO workOrder = (WorkOrderDTO)session.get(this.sessId);
				workOrder.setStatus(WorkOrderStatus.Inprogress.value());
				if(workOrder.getActualStart()==null) {
					workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
				}
				session.put(this.sessId, workOrder);
			}
			if(ischangeDate) {
				Integer seqNoCurrent = workOrderOperation.getSeqNo();
				java.sql.Date preExptdEndDate = null;
				for(Entry<String, WorkOrderOperation> entity:sessOperMap.entrySet()) {
					WorkOrderOperation workOrderOperation2 =  entity.getValue();
					if(seqNoCurrent.intValue()==workOrderOperation2.getSeqNo().intValue()) {
						if(preExptdEndDate==null) {
							workOrderOperation2.setCustomStartDate(new java.sql.Date(this.woOperation.getCustomStartDate().getTime()));
						} else {
							workOrderOperation2.setCustomStartDate(preExptdEndDate);
						}
						preExptdEndDate = workOrderOperation2.getCustomEndDate();
						seqNoCurrent++;
					}
				}
			}
			if(workOrderOperation!=null&&WoOperStatus.Completed.value().equals(workOrderOperation.getStatus())) {
				Integer seqNo = null;
				if(workOrderOperation.getActualEndDate()==null) {
					workOrderOperation.setActualEndDate(new java.sql.Date(new Date().getTime()));
					seqNo = workOrderOperation.getSeqNo()+1;
				}
				boolean isChangeStatus = true;
				for (Entry<String, WorkOrderOperation> entry : sessOperMap
						.entrySet()) {
					WorkOrderOperation workOrderOPeration = entry.getValue();
					if(isChangeStatus&&workOrderOPeration!=null&&(workOrderOPeration.getStatus()==null||!WoOperStatus.Completed.value().equals(workOrderOPeration.getStatus()))) {
						isChangeStatus = false;
					}
					if(seqNo!=null&&workOrderOPeration.getSeqNo().intValue()==seqNo.intValue()) {
						workOrderOPeration.setActualStartDate(workOrderOperation.getActualEndDate());
						seqNo = null;
					}
					if(!isChangeStatus&&seqNo==null) {
						break;
					}
				}
				if(isChangeStatus) {
					Map<String, Object> session = ActionContext.getContext().getSession();
					WorkOrderDTO workOrder = (WorkOrderDTO)session.get(this.sessId);
					workOrder.setStatus(WorkOrderStatus.Completed.value());
					session.put(this.sessId, workOrder);
				} else {
					Map<String, Object> session = ActionContext.getContext().getSession();
					WorkOrderDTO workOrder = (WorkOrderDTO)session.get(this.sessId);
					workOrder.setStatus(WorkOrderStatus.Inprogress.value());
					if(workOrder.getActualStart()==null) {
						workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
					}
					session.put(this.sessId, workOrder);
				}
			}
			

			// 从Temp Resource 复制到 Session Resource
			Map<String, WoOperationResource> woTempResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
					.getRow(SessionConstant.WOTempResource.value(),
							this.sessWOPKey);
			if (woTempResMap != null) {
				Map<String, WoOperationResource> woResMap = new LinkedHashMap<String, WoOperationResource>();
				for (Entry<String, WoOperationResource> entry : woTempResMap
						.entrySet()) {
					WoOperationResource value = entry.getValue();
					if (value != null) {
						value = (WoOperationResource) value.clone();
					}
					woResMap.put(entry.getKey(), value);
				}
				SessionUtil.insertRow(SessionConstant.WOResource.value(),
						this.sessWOPKey, woResMap);
			}

			// 从Temp Component 复制到 Session Component
			Map<String, WoOperationComponent> woTempComMap = (LinkedHashMap<String, WoOperationComponent>) SessionUtil
					.getRow(SessionConstant.WOTempComponent.value(),
							this.sessWOPKey);
			if (woTempComMap != null) {
				Map<String, WoOperationComponent> woComMap = new LinkedHashMap<String, WoOperationComponent>();
				for (Entry<String, WoOperationComponent> entry : woTempComMap
						.entrySet()) {
					WoOperationComponent value = entry.getValue();
					if (value != null) {
						value = (WoOperationComponent) value.clone();
					}
					woComMap.put(entry.getKey(), value);
				}
				SessionUtil.insertRow(SessionConstant.WOComponent.value(),
						this.sessWOPKey, woComMap);
			}

			rt.put("message", "Save Operation sucessfully.");
		} catch (Exception ex) {
			ex.printStackTrace();
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
	
	public String batchOperate() {
		Map<String, Object> rt = new HashMap<String, Object>();
		rt.put("message", this.setupService.batchOperate(delROIdList,woStatus,sessId,comments,customStartDate));
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String batchUpdateWoOperation() {
		Map<String, Object> rt = new HashMap<String, Object>();
		rt.put("message", this.setupService.batchUpdateWoOperation(woOperationIds, woStatus,comments,customStartDate));
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String batchUpdateWoOperationCustom() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			List<WorkOrderOperation> woOperationList = new ArrayList<WorkOrderOperation>();
			if(StringUtils.isNotEmpty(customConfirmInfo)) {
				String[] rows = customConfirmInfo.split("\n");
				for(String row:rows) {
					String[] elements = StringUtils.split(row, ",");
					WorkOrderOperation woOperation = new WorkOrderOperation();
					woOperation.setWorkOrderNo(Integer.parseInt(elements[0]));
					Operation operation = new Operation();
					operation.setName(elements[2]);
					woOperation.setOperation(operation);
					woOperation.setStatus(elements[3]);
					woOperationList.add(woOperation);
				}
			}
			rt.put("message", this.setupService.batchUpdateWoOperation(woOperationList));
		} catch(Exception ex) {
			ex.printStackTrace();
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
	 * 操作Session内容删除相应记录， 对于数据库中的则置其value为null, 再给map中的seqNo进行重排，
	 * 返回由js执行reload页面.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteOperation() {
		Map<String, WorkOrderOperation> sessMap = (Map<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		// 操作Session内容删除相应记录， 对于数据库中的则置其value为null.
		for (String key : this.delROIdList) {
			if (StringUtils.isNumeric(key)) {
				sessMap.put(key, null);
			} else {
				// 临时添加的internal order, 现在又删除了， 则Cancel源Order
				if (sessMap.get(key).getInternalOrderNo() != null) {
					String reason = "Cancel by work order";
					OrderMainDTO order = orderService.getOrderDetail(sessMap.get(key).getInternalOrderNo());
					order.setStatus(OrderStatusType.CN.value());
					orderService.delOrder(
							sessMap.get(key).getInternalOrderNo(), reason,
							SessionUtil.getUserId(), reason, order);
				}
				sessMap.remove(key);
			}
		}
		this.sortSessionOperation(sessMap);
		// 返回信号交由js执行reload页面
		Struts2Util.renderText("success");
		return NONE;
	}

	/**
	 * 对WorkOrder 中的Operation进行重新排序, 使能有效显示的Operation的seqNo可以连续.
	 * 
	 * @param sessMap
	 */
	private void sortSessionOperation(Map<String, WorkOrderOperation> sessMap) {
		int seqNo = 1;
		Date preExptdEndDate = new Date();
		Date firstWoOperationSchStartDate = null;
		for (Entry<String, WorkOrderOperation> entry : sessMap.entrySet()) {
			WorkOrderOperation wo = (WorkOrderOperation) entry.getValue();
			if(firstWoOperationSchStartDate==null) {
				firstWoOperationSchStartDate = wo.getExptdStartDate();
			} else if(wo.getExptdStartDate()!=null&&firstWoOperationSchStartDate.after(wo.getExptdStartDate())) {
				firstWoOperationSchStartDate = wo.getExptdStartDate();
			}
		}
		for (Entry<String, WorkOrderOperation> entry : sessMap.entrySet()) {
			WorkOrderOperation wo = (WorkOrderOperation) entry.getValue();
			if (seqNo == 1) {
				wo.setExptdStartDate(new java.sql.Date((firstWoOperationSchStartDate!=null?firstWoOperationSchStartDate:new Date()).getTime()));// 第一条设置Schedule Start Date.

			} else {
					wo.setExptdStartDate(new java.sql.Date(preExptdEndDate.getTime()));// 第一条设置Schedule Start Date.
			}
			preExptdEndDate = wo.getExptdEndDate();
			wo.setCustomStartDate(wo.getExptdStartDate());
			wo.setCustomEndDate(wo.getExptdEndDate());
			// 数据库已有的
			if (StringUtils.isNumeric(entry.getKey())) {
				if (wo != null) {// 没有执行页面上的删除操作
					wo.setSeqNo(seqNo);
					seqNo++;
				}
			} else {
				// 数据库中没有的也要更新它的seqNo.以使删除后的集合在显示时seqNo是连续的.
				wo.setSeqNo(seqNo);
				seqNo++;
			}
			
			sessMap.put(entry.getKey(), wo);
		}

	}

	/**
	 * 点击New按钮选择新的Operation.
	 * 
	 * @return
	 */
	public String selectOperation() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		WorkOrderDTO workOrderDTO = (WorkOrderDTO)session.get(this.sessId);
		woStatus = workOrderDTO!=null?workOrderDTO.getStatus():"";
		this.workCenterList = this.setupService.getAllWorkCenter();
		return "workorder_operation_select";
	}

	/**
	 * 根据wo operation获得wo resource list. 如果wo operation还没有保存进数据库则从 输入参数: sessId,
	 * sessWOPKey
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getResourceList() throws Exception {
		// 此时WorkOrderOperation在session中肯定存在的.
		Map<String, WorkOrderOperation> sessWOPMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
				.getRow(SessionConstant.WorkOrderOperation.value(), this.sessId);
		woOperation = sessWOPMap.get(this.sessWOPKey);

		// woResMap此时可能为null, 也可能不为null(session中有值， 或从数据库里带出来).
		Map<String, WoOperationResource> woResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
				.getRow(SessionConstant.WOResource.value(), this.sessWOPKey);

		Map<String, WoOperationResource> woTempResMap = null;
		if (SessionUtil.getRow(SessionConstant.WOTempResource.value(),
				sessWOPKey) != null) {
			woTempResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
					.getRow(SessionConstant.WOTempResource.value(),
							this.sessWOPKey);
		} else {
			woTempResMap = new LinkedHashMap<String, WoOperationResource>();
		}

		// 只有是新增的WoOperation, 而且是第一次进入WoOperation Eidt页面时woResMap才可能为null.
		// 这时根据WoOperation的srcOperationId导入原Operation关联的Resource.
		if (woResMap == null) {
			// 只要进入这个页面则给WoResource设为有值.
			woResMap = new LinkedHashMap<String, WoOperationResource>();
			SessionUtil.insertRow(SessionConstant.WOResource.value(),
					this.sessWOPKey, woResMap);
			if (!StringUtils.isNumeric(this.sessWOPKey)) {
				// 还没保存的Work order Operation(因为可以取消)
				Integer srcOperationId = woOperation.getOperation().getId();
				List<OperationResource> dbList = setupService
						.getAllOperationResource(srcOperationId);
				for (OperationResource dbOperationRes : dbList) {
					Resource res = dbOperationRes.getResource();
					WoOperationResource woRes = new WoOperationResource();
					woRes.setSeqNo(dbOperationRes.getSeqNo());
					woRes.setResource(res);
					woRes.setStatus(res.getStatus());
					woTempResMap.put(SessionUtil.generateTempId(), woRes);
				}
			}
			SessionUtil.insertRow(SessionConstant.WOTempResource.value(),
					this.sessWOPKey, woTempResMap);
			SessionUtil.insertRow(SessionConstant.WOResource.value(),
					this.sessWOPKey, woTempResMap);
		}

		Map<String, WoOperationResource> responseMap = new LinkedHashMap<String, WoOperationResource>();
		{
			// 以下最终显示到页面上的数据集合, 对已剔除的数据进行筛选.
			for (Entry<String, WoOperationResource> entry : woTempResMap
					.entrySet()) {
				WoOperationResource groupRes = (WoOperationResource) entry
						.getValue();
				// 数据库已有的
				if (StringUtils.isNumeric(entry.getKey())) {
					if (groupRes == null) {// 执行了临时的删除操作则不应再显示

					} else {
						responseMap.put(entry.getKey(), entry.getValue());
					}
				} else {
					// Session中的都应显示.
					responseMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
		Struts2Util.getRequest().setAttribute("requestMap", responseMap);
		return "workorder_operation_resource_list";
	}

	@SuppressWarnings("unchecked")
	public String selectResourceForWO() throws Exception {
		// 在getOperationList方法已经初始化， 这里sessMap肯定不为null.
		// 操作完成之后也不需要更新session, 因为它是引用类型的.
		Map<String, WoOperationResource> tempResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
				.getRow(SessionConstant.WOTempResource.value(), this.sessWOPKey);
		String operationIdList = Struts2Util.getParameter("idList");
		String[] idList = operationIdList.split(",");
		for (String operationId : idList) {
			WoOperationResource wo = new WoOperationResource();
			wo.setResource(this.setupService.getResource(Integer
					.parseInt(operationId)));
			tempResMap.put(SessionUtil.generateTempId(), wo);
		}
		// 重排Session中数据.
		int seqNo = 1;
		for (Entry<String, WoOperationResource> entry : tempResMap.entrySet()) {
			WoOperationResource wo = (WoOperationResource) entry.getValue();
			// 数据库已有的
			if (StringUtils.isNumeric(entry.getKey())) {
				if (wo != null) {// 没有执行页面上的删除操作
					wo.setSeqNo(seqNo);
					tempResMap.put(entry.getKey(), wo);
					seqNo++;
				}
			} else {
				// 数据库中没有的也要更新它的seqNo.以使删除后的集合在显示时seqNo是连续的.
				wo.setSeqNo(seqNo);
				tempResMap.put(entry.getKey(), wo);
				seqNo++;
			}
		}
		Struts2Util.renderText("success");
		return null;
	}

	/**
	 * 传入值： sessWOPKey, sessResKey
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String editWoResource() {
		// 这个经过了getResourceList之后， 是肯定非空（可能size为0)集合.
		Map<String, WoOperationResource> woTempResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
				.getRow(SessionConstant.WOTempResource.value(), this.sessWOPKey);
		this.woResource = woTempResMap.get(this.sessResKey);
		// 提供数据源
		List<PbCurrency> currencyList = this.publicService.getCurrencyList();
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.RESOURCE_COST_BASIS);
		listName.add(DropDownListName.RESOURCE_UOM);
		listName.add(DropDownListName.RESOURCE_GROUP);
		dropDownMap = publicService.getDropDownMap(listName);
		Struts2Util.getRequest().setAttribute("currencyList", currencyList);
		return "workorder_operation_resource_edit";
	}

	@SuppressWarnings("unchecked")
	public String deleteResource() {
		// 这个经过了getResourceList之后， 是肯定非空（可能size为0)集合.
		Map<String, WoOperationResource> woTempResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
				.getRow(SessionConstant.WOTempResource.value(), this.sessWOPKey);
		for (String key : this.delROIdList) {
			if (StringUtils.isNumeric(key)) {
				woTempResMap.put(key, null);
			} else {
				woTempResMap.remove(key);
			}
		}

		// 重新排序.
		int seqNo = 1;
		for (Entry<String, WoOperationResource> entry : woTempResMap.entrySet()) {
			WoOperationResource wo = (WoOperationResource) entry.getValue();
			// 数据库已有的
			if (StringUtils.isNumeric(entry.getKey())) {
				if (wo != null) {// 没有执行页面上的删除操作
					wo.setSeqNo(seqNo);
					woTempResMap.put(entry.getKey(), wo);
					seqNo++;
				}
			} else {
				// 数据库中没有的也要更新它的seqNo.以使删除后的集合在显示时seqNo是连续的.
				wo.setSeqNo(seqNo);
				woTempResMap.put(entry.getKey(), wo);
				seqNo++;
			}
		}
		Struts2Util.renderText("success");
		return null;
	}

	@SuppressWarnings("unchecked")
	public String saveResource() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String, WoOperationResource> woTempResMap = (LinkedHashMap<String, WoOperationResource>) SessionUtil
					.getRow(SessionConstant.WOTempResource.value(),
							this.sessWOPKey);
			WoOperationResource oldWoRes = woTempResMap.get(this.sessResKey);
			this.woResource.setResource(oldWoRes.getResource());
			woTempResMap.put(this.sessResKey, this.woResource);
			rt.put("message", "Save Wo Resource sucessfully.");
		} catch (Exception ex) {
			ex.printStackTrace();
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
	 * 输入数据： sessWOPKey, keyList, seqNoList
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateResSeqNo() {
		// 取得session中的
		Map<String, WoOperationResource> sessMap = (Map<String, WoOperationResource>) SessionUtil
				.getRow(SessionConstant.WOTempResource.value(), this.sessWOPKey);
		// 操作Session内容更新交换seqNo的两条相应记录.
		for (int k = 0; k < this.keyList.size(); k++) {
			WoOperationResource ors = sessMap.get(keyList.get(k));
			ors.setSeqNo(this.seqNoList.get(k));
		}

		// 以下最终显示到页面上的数据集合（即session中的值, 以防页面刷新）, 进行重新排序.
		Map<String, WoOperationResource> sortedSessMap = new LinkedHashMap<String, WoOperationResource>();
		int len = sessMap.size();
		// 循环依次获得每序号递增的map.
		for (int i = 1; i <= len; i++) {
			for (Entry<String, WoOperationResource> entry : sessMap.entrySet()) {
				WoOperationResource wo = (WoOperationResource) entry.getValue();
				// 数据库已有的
				if (StringUtils.isNumeric(entry.getKey())) {
					if (wo == null) {// 执行了临时的删除操作也应包含在.
						sortedSessMap.put(entry.getKey(), wo);
					} else {
						if (wo.getSeqNo().intValue() == i) {
							sortedSessMap.put(entry.getKey(), wo);
							break;
						}
					}
				} else {
					if (wo.getSeqNo().intValue() == i) {
						sortedSessMap.put(entry.getKey(), wo);
						break;
					}
				}
			}
		}
		// 重排后更新session.
		SessionUtil.insertRow(SessionConstant.WOTempResource.value(),
				this.sessWOPKey, sortedSessMap);// 重设为已排过序的Map.
		// 返回信号交由js执行reload页面
		Struts2Util.renderText("success");
		return NONE;
	}

	/**
	 * 根据wo operation获得wo component list. 如果wo operation还没有保存进数据库则从 输入参数:
	 * sessId, sessWOPKey
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getComponentList() throws Exception {
		Map<String, WoOperationComponent> woTempComMap = null;
		if (SessionUtil.getRow(SessionConstant.WOTempComponent.value(),
				sessWOPKey) != null) {
			woTempComMap = (LinkedHashMap<String, WoOperationComponent>) SessionUtil
					.getRow(SessionConstant.WOTempComponent.value(),
							this.sessWOPKey);
		} else {
			woTempComMap = new LinkedHashMap<String, WoOperationComponent>();
			SessionUtil.insertRow(SessionConstant.WOTempComponent.value(),
					this.sessWOPKey, woTempComMap);
		}
		Map<String, WoOperationComponent> responseMap = new LinkedHashMap<String, WoOperationComponent>();
		{
			// 以下最终显示到页面上的数据集合, 对已剔除的数据进行筛选.
			for (Entry<String, WoOperationComponent> entry : woTempComMap
					.entrySet()) {
				WoOperationComponent groupRes = (WoOperationComponent) entry
						.getValue();
				// 数据库已有的
				if (StringUtils.isNumeric(entry.getKey())) {
					if (groupRes == null) {// 执行了临时的删除操作则不应再显示

					} else {
						responseMap.put(entry.getKey(), entry.getValue());
					}
				} else {
					// Session中的都应显示.
					responseMap.put(entry.getKey(), entry.getValue());
				}
			}
		}

		Struts2Util.getRequest().setAttribute("requestMap", responseMap);
		return "workorder_operation_component_list";
	}

	/**
	 * 传入值： sessWOPKey, sessComKey
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String editWoComponent() {
		if (StringUtils.isBlank(this.sessComKey)) {
			this.woComponent = new WoOperationComponent();
		} else {
			// 这个经过了getComponentList之后， 是肯定非空（可能size为0)集合.
			Map<String, WoOperationComponent> woTempComMap = (LinkedHashMap<String, WoOperationComponent>) SessionUtil
					.getRow(SessionConstant.WOTempComponent.value(),
							this.sessWOPKey);
			this.woComponent = woTempComMap.get(this.sessComKey);
		}
		// 提供数据源
		List<PbCurrency> currencyList = this.publicService.getCurrencyList();
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.RESOURCE_COST_BASIS);
		listName.add(DropDownListName.RESOURCE_UOM);
		listName.add(DropDownListName.RESOURCE_GROUP);
		dropDownMap = publicService.getDropDownMap(listName);
		Struts2Util.getRequest().setAttribute("currencyList", currencyList);
		System.out.println("editWoComponent woComponent: " + woComponent);
		return "workorder_operation_component_edit";
	}

	@SuppressWarnings("unchecked")
	public String deleteComponent() {
		// 这个经过了getComponentList之后， 是肯定非空（可能size为0)集合.
		Map<String, WoOperationComponent> woTempComMap = (LinkedHashMap<String, WoOperationComponent>) SessionUtil
				.getRow(SessionConstant.WOTempComponent.value(),
						this.sessWOPKey);
		for (String key : this.delROIdList) {
			if (StringUtils.isNumeric(key)) {
				woTempComMap.put(key, null);
			} else {
				woTempComMap.remove(key);
			}
		}

		// 重新排序.
		int seqNo = 1;
		for (Entry<String, WoOperationComponent> entry : woTempComMap
				.entrySet()) {
			WoOperationComponent wo = (WoOperationComponent) entry.getValue();
			// 数据库已有的
			if (StringUtils.isNumeric(entry.getKey())) {
				if (wo != null) {// 没有执行页面上的删除操作
					wo.setSeqNo(seqNo);
					woTempComMap.put(entry.getKey(), wo);
					seqNo++;
				}
			} else {
				// 数据库中没有的也要更新它的seqNo.以使删除后的集合在显示时seqNo是连续的.
				wo.setSeqNo(seqNo);
				woTempComMap.put(entry.getKey(), wo);
				seqNo++;
			}
		}
		Struts2Util.renderText("success");
		return null;
	}

	@SuppressWarnings("unchecked")
	public String saveComponent() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String, WoOperationComponent> tempComMap = (LinkedHashMap<String, WoOperationComponent>) SessionUtil
					.getRow(SessionConstant.WOTempComponent.value(),
							this.sessWOPKey);
			if (StringUtils.isEmpty(this.sessComKey)) {
				tempComMap.put(SessionUtil.generateTempId(), this.woComponent);
				// 重排Session中数据.
				int seqNo = 1;
				for (Entry<String, WoOperationComponent> entry : tempComMap
						.entrySet()) {
					WoOperationComponent wo = (WoOperationComponent) entry
							.getValue();
					// 数据库已有的
					if (StringUtils.isNumeric(entry.getKey())) {
						if (wo != null) {// 没有执行页面上的删除操作
							wo.setSeqNo(seqNo);
							tempComMap.put(entry.getKey(), wo);
							seqNo++;
						}
					} else {
						// 数据库中没有的也要更新它的seqNo.以使删除后的集合在显示时seqNo是连续的.
						wo.setSeqNo(seqNo);
						tempComMap.put(entry.getKey(), wo);
						seqNo++;
					}
				}
			} else {
				tempComMap.put(this.sessComKey, this.woComponent);
			}
			rt.put("message", "Save Wo Component sucessfully.");
		} catch (Exception ex) {
			ex.printStackTrace();
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
	 * 输入数据： sessWOPKey, keyList, seqNoList
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateComSeqNo() {
		// 取得session中的
		Map<String, WoOperationComponent> sessMap = (Map<String, WoOperationComponent>) SessionUtil
				.getRow(SessionConstant.WOTempComponent.value(),
						this.sessWOPKey);
		// 操作Session内容更新交换seqNo的两条相应记录.
		for (int k = 0; k < this.keyList.size(); k++) {
			WoOperationComponent ors = sessMap.get(keyList.get(k));
			ors.setSeqNo(this.seqNoList.get(k));
		}

		// 以下最终显示到页面上的数据集合（即session中的值, 以防页面刷新）, 进行重新排序.
		Map<String, WoOperationComponent> sortedSessMap = new LinkedHashMap<String, WoOperationComponent>();
		int len = sessMap.size();
		// 循环依次获得每序号递增的map.
		for (int i = 1; i <= len; i++) {
			for (Entry<String, WoOperationComponent> entry : sessMap.entrySet()) {
				WoOperationComponent wo = (WoOperationComponent) entry
						.getValue();
				// 数据库已有的
				if (StringUtils.isNumeric(entry.getKey())) {
					if (wo == null) {// 执行了临时的删除操作也应包含在.
						sortedSessMap.put(entry.getKey(), wo);
					} else {
						if (wo.getSeqNo().intValue() == i) {
							sortedSessMap.put(entry.getKey(), wo);
							break;
						}
					}
				} else {
					if (wo.getSeqNo().intValue() == i) {
						sortedSessMap.put(entry.getKey(), wo);
						break;
					}
				}
			}
		}
		// 重排后更新session.
		SessionUtil.insertRow(SessionConstant.WOTempComponent.value(),
				this.sessWOPKey, sortedSessMap);// 重设为已排过序的Map.
		// 返回信号交由js执行reload页面
		Struts2Util.renderText("success");
		return NONE;
	}
	
	/**
	 * 
	 * @return
	 */
	public String workOrderOperationList() {
		try {
			workOrderOperationList = this.workOrderEntryService.searchWoOperation(workOrderOperationDTO,customFlag);
			if(workOrderOperationList==null) {
				workOrderOperationList = new ArrayList<WorkOrderOperationBean>();
			}
			for(WorkOrderOperationBean workOrderOperationBean:workOrderOperationList) {
				if(StringUtils.isEmpty(woOperationIds)) {
					woOperationIds = "";
					woOperationIds = woOperationIds+workOrderOperationBean.getId();
				} else {
					woOperationIds = woOperationIds+","+workOrderOperationBean.getId();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "workOrder_operation_operate_list";
	}
	
	
	public String innerOrderWorkOrderOperationList() {
		try {
			workOrderOperationList = this.workOrderEntryService.searchWoOperationByUSOrder(srcSoNo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "inner_workOrder_operation_list";
	}
	
	public String batchOperationDlg() {
		return "batch_operation_dlg";
	}
	
	public String createInternalOrderPreOp() {
		Map<String,Object> rt = new HashMap<String,Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		WorkOrderDTO workOrder = (WorkOrderDTO)session.get(this.sessId);
		Integer originalCenterId = workOrder!=null?workOrder.getWorkCenterId():null;
		if(this.setupService.workCenterJudge(originalCenterId, this.workCenterId)) {
			rt.put("message", "seqCreate");
		} else {
			rt.put("message", "orderCreate");
		}
		Struts2Util.renderJson(rt);
		return NONE;
	}
	
	public String inputSeqPage() {
		Map<String,String> seqMap = this.workOrderEntryService.getSequenceByWO(this.sessId);
		sequence = seqMap.get("sequence")!=null?seqMap.get("sequence"):"";
		sequence3 = seqMap.get("sequence3")!=null?seqMap.get("sequence3"):"";
		sequence5 = seqMap.get("sequence5")!=null?seqMap.get("sequence5"):"";
		jobName = seqMap.get("job_name")!=null?seqMap.get("job_name"):"";
		return "seq_input_dlg";
	}
	
	public String downLoadExcel() {
		Map<String,Object> rt = new HashMap<String,Object>();
		StringBuffer url = new StringBuffer("https://www.genscript.com/ssl-bin/app/oligo_design.cgi");
		StringBuffer param = new StringBuffer();
		param.append("sequence=").append(sequence).append("&op=Send").append("&job_name=").append(Struts2Util.getParameter("job_name"))
			 .append("&tm_min=").append(Struts2Util.getParameter("tm_min")).append("&tm_max=").append(Struts2Util.getParameter("tm_max"))
			 .append("&ov_min=").append(Struts2Util.getParameter("ov_min")).append("&ol_max=").append(Struts2Util.getParameter("ov_max"))
			 .append("&ol_max=").append(Struts2Util.getParameter("ol_max")).append("&left_primer=").append(Struts2Util.getParameter("left_primer"))
			 .append("&right_primer=").append(Struts2Util.getParameter("right_primer"));
		try {
			String reuslt = postReq(url.toString(),param.toString());
			if(StringUtils.isNotEmpty(reuslt)) {
				String regex = "<a href=\".*?\">Excel file</a>";
				Pattern pt = Pattern.compile(regex);
				Matcher mt = pt.matcher(reuslt);
				if (mt.find()) {
					Matcher urlMatcher = Pattern.compile("href=.*?>").matcher(mt.group());
				    if(urlMatcher.find()) {
				    	String excelUrl = urlMatcher.group().replaceAll("href=|>", "").replace("\"", "");
				    	rt.put("url", excelUrl);
				    }
					
			    }
			}
		} catch (Exception e) {
			rt.put("message", "Remote server error.");
			e.printStackTrace();
		}
		Struts2Util.renderJson(rt);
		return NONE;
	}
	
	/**
	 * post请求
	 */
	public static String postReq(String url,String param) throws Exception{
		String resultStr = null;
		StringBuffer readOneLineBuff = new StringBuffer(); 
		URL postUrl = new URL(url);   
        // 打开连接   
		if(url.startsWith("https://")) {
			HttpsURLConnection connection = (HttpsURLConnection) postUrl   
             .openConnection(); 
			connection.setDoOutput(true);     
		    connection.setDoInput(true);   
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty(" Content-Type ",   
            " application/x-www-form-urlencoded "); 
		    connection.connect();  
		    DataOutputStream out = new DataOutputStream(connection   
	                .getOutputStream()); 
		    out.writeBytes(param);   
	        out.flush();   
	        out.close();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream()));   
	        String line=null;    
	        while ((line = reader.readLine()) != null) {   
	            readOneLineBuff.append(line);   
	        } 
	        connection.disconnect();
		} else {
			HttpURLConnection connection = (HttpURLConnection) postUrl   
            .openConnection();
			connection.setDoOutput(true);     
		    connection.setDoInput(true);   
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty(" Content-Type ",   
            " application/x-www-form-urlencoded "); 
		    connection.connect();  
		    DataOutputStream out = new DataOutputStream(connection   
	                .getOutputStream()); 
		    out.writeBytes(param);   
	        out.flush();   
	        out.close();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream()));   
	        String line=null;   
	        while ((line = reader.readLine()) != null) {   
	            readOneLineBuff.append(line);   
	        } 
	        connection.disconnect();
		}    
        resultStr = readOneLineBuff.toString();
		return resultStr;
	}
	
	public String customDateChangeLog() {
		updateRequestLogList = this.updateRequestLogService.searchUpdateRequestLog("WorkOrder",id);
		return "custom_date_change_log";
	}
	
	public String plateItemsEdit() {
		woProcessList = new ArrayList<DsPlateItems>();
		for(int i =0;i<96;i++) {
			DsPlateItems woDsProcess = new DsPlateItems();
			woProcessList.add(woDsProcess);
		}
		return "plate_items";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, WorkOrderOperation> getWoOperationMap() {
		return woOperationMap;
	}

	public void setWoOperationMap(Map<String, WorkOrderOperation> woOperationMap) {
		this.woOperationMap = woOperationMap;
	}

	public String getSessId() {
		return sessId;
	}

	public void setSessId(String sessId) {
		this.sessId = sessId;
	}

	public List<String> getDelROIdList() {
		return delROIdList;
	}

	public void setDelROIdList(List<String> delROIdList) {
		this.delROIdList = delROIdList;
	}

	public List<Integer> getOperationIdList() {
		return operationIdList;
	}

	public void setOperationIdList(List<Integer> operationIdList) {
		this.operationIdList = operationIdList;
	}

	public List<Integer> getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(List<Integer> seqNoList) {
		this.seqNoList = seqNoList;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}

	public List<WorkCenter> getCenterList() {
		return centerList;
	}

	public void setCenterList(List<WorkCenter> centerList) {
		this.centerList = centerList;
	}

	public List<UserDTO> getSuperList() {
		return superList;
	}

	public void setSuperList(List<UserDTO> superList) {
		this.superList = superList;
	}

	public WorkOrderOperation getWoOperation() {
		return woOperation;
	}

	public void setWoOperation(WorkOrderOperation woOperation) {
		this.woOperation = woOperation;
	}

	public String getSessResKey() {
		return sessResKey;
	}

	public void setSessResKey(String sessResKey) {
		this.sessResKey = sessResKey;
	}

	public String getSessComKey() {
		return sessComKey;
	}

	public void setSessComKey(String sessComKey) {
		this.sessComKey = sessComKey;
	}

	public String getSessWOPKey() {
		return sessWOPKey;
	}

	public void setSessWOPKey(String sessWOPKey) {
		this.sessWOPKey = sessWOPKey;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public WoOperationResource getWoResource() {
		return woResource;
	}

	public void setWoResource(WoOperationResource woResource) {
		this.woResource = woResource;
	}

	public WoOperationComponent getWoComponent() {
		return woComponent;
	}

	public void setWoComponent(WoOperationComponent woComponent) {
		this.woComponent = woComponent;
	}

	public String getChangeStatusFlg() {
		return changeStatusFlg;
	}

	public void setChangeStatusFlg(String changeStatusFlg) {
		this.changeStatusFlg = changeStatusFlg;
	}

	public String getWoStatus() {
		return woStatus;
	}

	public void setWoStatus(String woStatus) {
		this.woStatus = woStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<WorkOrderOperationBean> getWorkOrderOperationList() {
		return workOrderOperationList;
	}

	public void setWorkOrderOperationList(
			List<WorkOrderOperationBean> workOrderOperationList) {
		this.workOrderOperationList = workOrderOperationList;
	}

	public WorkOrderOperationBean getWorkOrderOperationDTO() {
		return workOrderOperationDTO;
	}

	public void setWorkOrderOperationDTO(WorkOrderOperationBean workOrderOperationDTO) {
		this.workOrderOperationDTO = workOrderOperationDTO;
	}

	public String getWoOperationIds() {
		return woOperationIds;
	}

	public void setWoOperationIds(String woOperationIds) {
		this.woOperationIds = woOperationIds;
	}

	public List<WorkCenter> getWorkCenterList() {
		return workCenterList;
	}

	public void setWorkCenterList(List<WorkCenter> workCenterList) {
		this.workCenterList = workCenterList;
	}

	public String getCustomStartDate() {
		return customStartDate;
	}

	public void setCustomStartDate(String customStartDate) {
		this.customStartDate = customStartDate;
	}

	public ExceptionService getExceptionUtil() {
		return exceptionUtil;
	}

	public void setExceptionUtil(ExceptionService exceptionUtil) {
		this.exceptionUtil = exceptionUtil;
	}

	public List<String> getExperimentDate() {
		return experimentDate;
	}

	public void setExperimentDate(List<String> experimentDate) {
		this.experimentDate = experimentDate;
	}

	public List<String> getHostNo() {
		return hostNo;
	}

	public void setHostNo(List<String> hostNo) {
		this.hostNo = hostNo;
	}

	public List<String> getLoading() {
		return loading;
	}

	public void setLoading(List<String> loading) {
		this.loading = loading;
	}

	public List<String> getRemainsGel() {
		return remainsGel;
	}

	public void setRemainsGel(List<String> remainsGel) {
		this.remainsGel = remainsGel;
	}

	public List<String> getConcentration() {
		return concentration;
	}

	public void setConcentration(List<String> concentration) {
		this.concentration = concentration;
	}

	public List<String> getVolume() {
		return volume;
	}

	public void setVolume(List<String> volume) {
		this.volume = volume;
	}

	

	public List<String> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<String> quantity) {
		this.quantity = quantity;
	}

	public List<String> getComment() {
		return comment;
	}

	public void setComment(List<String> comment) {
		this.comment = comment;
	}

	public String getDelResultIds() {
		return delResultIds;
	}

	public void setDelResultIds(String delResultIds) {
		this.delResultIds = delResultIds;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<String> getHostNoList() {
		return hostNoList;
	}

	public void setHostNoList(List<String> hostNoList) {
		this.hostNoList = hostNoList;
	}

	public Integer getSrcSoNo() {
		return srcSoNo;
	}

	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}

	public String getCustomConfirmInfo() {
		return customConfirmInfo;
	}

	public void setCustomConfirmInfo(String customConfirmInfo) {
		this.customConfirmInfo = customConfirmInfo;
	}

	public Integer getWorkCenterId() {
		return workCenterId;
	}

	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Integer getCustomFlag() {
		return customFlag;
	}

	public void setCustomFlag(Integer customFlag) {
		this.customFlag = customFlag;
	}

	public String getSequence3() {
		return sequence3;
	}

	public void setSequence3(String sequence3) {
		this.sequence3 = sequence3;
	}

	public String getSequence5() {
		return sequence5;
	}

	public void setSequence5(String sequence5) {
		this.sequence5 = sequence5;
	}

	public AntibodyOprExperimentDatasDTO getAntibodyOprExperimentDatasDTO() {
		return antibodyOprExperimentDatasDTO;
	}

	public void setAntibodyOprExperimentDatasDTO(
			AntibodyOprExperimentDatasDTO antibodyOprExperimentDatasDTO) {
		this.antibodyOprExperimentDatasDTO = antibodyOprExperimentDatasDTO;
	}

	public Integer getAntibodyFlg() {
		return antibodyFlg;
	}

	public void setAntibodyFlg(Integer antibodyFlg) {
		this.antibodyFlg = antibodyFlg;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public List<UpdateRequestLog> getUpdateRequestLogList() {
		return updateRequestLogList;
	}

	public void setUpdateRequestLogList(List<UpdateRequestLog> updateRequestLogList) {
		this.updateRequestLogList = updateRequestLogList;
	}

	public List<DsPlateItems> getWoProcessList() {
		return woProcessList;
	}

	public void setWoProcessList(List<DsPlateItems> woProcessList) {
		this.woProcessList = woProcessList;
	}

}
