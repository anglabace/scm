package com.genscript.gsscm.manufacture.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.manufacture.entity.GroupResource;
import com.genscript.gsscm.manufacture.entity.Resource;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkCenterAssigned;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.entity.Role;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "search_from", location = "manufacture/workCenter_search_form.jsp"),
		@Result(name = "search_result", location = "manufacture/workCenter_search_result.jsp"),
		@Result(name = "workCenter_add", location = "manufacture/workCenter_add.jsp"),
		@Result(name = "workCenter_edit", location = "manufacture/workCenter_edit.jsp"),
		@Result(name="center_workGroup_list",location="manufacture/center_workGroup_list.jsp")
		})
public class WorkCenterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534283335416418911L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private SetupService setupService;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private WarehouseDao warehouseDao;
	private WorkCenter workCenter;
	private Integer id;
	private Page<WorkCenter> workCenterPage;
	private String[] delWorkCenterId;
	private List<UserDTO> superList;
	private List<Integer> groupIdList;
	private List<Integer> dettachGroupIdList;
	private List<Warehouse> warehouseList;
	private List<WorkGroup> groupList;
	private String sessWorkCenterId;
	private Map<String,String> producatServiceMap;
	private Map<String,WorkGroup> centerGroupMap;
 	private List<WorkCenterAssigned> workCenterAssignedList;
	private String workCenterAssignedStr;//已分配的产品或服务
	private String assignValue;//前台选择分配的产品或服务
	private String operation_method;
	private String type;
	
	private String addAuth;//是否具有添加权限

	/**
	 * 进入WorkCenter的主页面
	 * 
	 * @return
	 */
	public String search() {
		if((Constants.USERNAME_ADMIN.equals(SessionUtil.getUserName()))) {
			addAuth = "Y";
		} else {
			addAuth = "N";
		}
		return "search_from";
	}

	/**
	 * 进入新增页面
	 */
	public String add() {
		try {
			this.doInput();
			//****add by lizhang Product/Service Type to be Processed***//
			producatServiceMap = this.setupService.getProductAndService("product");
			sessWorkCenterId = SessionUtil.generateTempId();
			workCenter = new WorkCenter();
			workCenter.setModifyDate(null);
			workCenter.setModifiedBy(SessionUtil.getUserId());
			workCenter.setModifyUser(SessionUtil.getUserName());
			//**end***//
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "workCenter_add";
	}

	/**
	 * 进入修改页面.
	 * 
	 * @return
	 */
	public String edit() {
		//*********** Add By Zhang Yong Start *****************************//
		if (this.id != null && !("").equals(this.id)) {
			//判断将要修改的单号是否正在被操作
			String editUrl = "work_center!edit.action?id="+id;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
		} else {
			//释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
		}
		//*********** Add By Zhang Yong End *****************************//
		this.doInput();
		workCenter = this.setupService.getWorkCenter(this.id);
		//取得数据库中的
//		this.groupList = this.setupService.getGroupListByCenter(this.id);
		//****add by lizhang Product/Service Type to be Processed***//
		sessWorkCenterId = String.valueOf(id);
		SessionUtil.deleteRow(SessionConstant.WorkCenterAssigned.value(), sessWorkCenterId);
		producatServiceMap = this.setupService.getProductAndService("product");
		workCenterAssignedList = this.setupService.getWorkCenterAssignedList(sessWorkCenterId);
		this.workCenterAssignedStr();
		//**end***//
		return "workCenter_edit";
	}

	public void doInput() {
		Role role = privilegeService.getSuperRole();
		if (role != null) {
			this.superList = privilegeService.getUserListByRole(role
					.getRoleId());
		} else {
			this.superList = new ArrayList<UserDTO>();
		}
		this.warehouseList = this.warehouseDao.findAll(Page.ASC, "name");

	}

	
	/**
	 * 进入一个WorkCenter的WorkGroup list页面.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getWorkGroupList() {
		Map<String, WorkGroup> dbMap = null;
		Map<String, WorkGroup> sessMap = (Map<String, WorkGroup>) SessionUtil
		.getRow(SessionConstant.CenterGroup.value(),
				String.valueOf(this.id));
		if(sessMap==null) {
			List<WorkGroup> dbList = this.setupService.getGroupListByCenter(this.id);
			dbMap = new LinkedHashMap<String, WorkGroup>();
			for (WorkGroup dto : dbList) {
				dbMap.put(dto.getId() + "", dto);
			}
			SessionUtil.insertRow(SessionConstant.CenterGroup.value(),
					String.valueOf(this.id), dbMap);
		} else {
			dbMap = sessMap;
		}
		centerGroupMap = dbMap;
		return "center_workGroup_list";
	}
	
	/**
	 * 分页查找
	 */
	public String list() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<WorkCenter> pagerUtil = new PagerUtil<WorkCenter>();
			workCenterPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!workCenterPage.isOrderBySetted()) {
				workCenterPage.setOrderBy("name");
				workCenterPage.setOrder(Page.ASC);
			}
			// 设置默认每页显示记录条数
			workCenterPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			workCenterPage = setupService.searchWorkCenterPage(workCenterPage,
					filters);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					workCenterPage);
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.remove(SessionConstant.CenterGroup.value());
		} catch (Exception ex) {
			return "search_result";
		}
		return "search_result";
	}

	/**
	 * 新增或修改一个WorkCenter
	 */
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			//*********** Add By Zhang Yong Start *****************************//
			//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (this.workCenter!= null && this.workCenter.getId() != null) {
				String editUrl = "work_center!edit.action?id="+this.workCenter.getId();
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message", "Save fail,the workCenter is editing by "+operation_method);
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			//*********** Add By Zhang Yong End *****************************//	
			this.setupService.saveWorkCenter(workCenter, this.groupIdList, this.dettachGroupIdList, sessWorkCenterId);
			SessionUtil.deleteRow(SessionConstant.GroupResource.value(),
					this.workCenter.getId()+"");
			SessionUtil.deleteRow(SessionConstant.CenterGroup.value(),
					this.workCenter.getId()+"");
			rt.put("message", "Save work center sucessfully.");
			//*********** Add By Zhang Yong Start *****************************//
			//释放同步锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
			//*********** Add By Zhang Yong End *****************************//
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
	 * 
	 * 批量删除WorkCenter.
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			List<Integer> idList = new ArrayList<Integer>();
			for (String temp : this.delWorkCenterId) {
				idList.add(Integer.valueOf(temp));
			}
			this.setupService.delWorkCenter(idList);
			rt.put("message", "Delete work center sucessfully !");
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
	 * type选择触发事件
	 */
	public String typeChange() {
		Map<String,Object> rt = new HashMap<String,Object>();
		producatServiceMap = this.setupService.getProductAndService(type);
		rt.put("producatServiceMap", producatServiceMap);
		Struts2Util.renderJson(rt);
		return null;
		
	}
	/**
	 * 分配产品和服务 
	 */
	@SuppressWarnings("unchecked")
	public String assign() {
		Map<String,Object> rt = new HashMap<String,Object>();
		if(sessWorkCenterId!=null) {
			workCenterAssignedList = (List<WorkCenterAssigned>)SessionUtil.getRow(SessionConstant.WorkCenterAssigned.value(), sessWorkCenterId);
			if(workCenterAssignedList==null) {
				workCenterAssignedList = new ArrayList<WorkCenterAssigned>();
			}
			createWorkCenterAssigned();
			this.workCenterAssignedStr();
		}
		rt.put("workCenterAssignedStr", workCenterAssignedStr);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 去除已分配产品和服务
	 */
	@SuppressWarnings("unchecked")
	public String unassign() {
		Map<String,Object> rt = new HashMap<String,Object>();
		if(sessWorkCenterId!=null) {
			workCenterAssignedList = (List<WorkCenterAssigned>)SessionUtil.getRow(SessionConstant.WorkCenterAssigned.value(), sessWorkCenterId);
			removeWorkCenterAssigned();
			this.workCenterAssignedStr();
		}
		rt.put("workCenterAssignedStr", workCenterAssignedStr);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 在新增workGroup时通过select操作,选择多个workGroup后需要调用这个方法.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectCallBack() throws Exception {
		Map<Integer, String> retMap = new HashMap<Integer, String>();
		if(this.groupIdList!=null&&this.groupIdList.size()>0) {
			for (int i = this.groupIdList.size()-1; i >=0; i--) {
				String tempId = SessionUtil.generateTempId();
				retMap.put(this.groupIdList.get(i), tempId);
				WorkGroup workGroup = this.setupService.getWorkGroup(this.groupIdList.get(i));
				workGroup.setWorkCenterId(this.id);
				SessionUtil.updateOneRow(SessionConstant.CenterGroup.toString(),
						this.getId()+"", tempId, workGroup);
			}
		}
		Struts2Util.renderJson(retMap);
		return null;
	}
	/**
	 * 删除workGroup
	 * @return
	 */
	public String deleteSelect() {
		Map<String, WorkGroup> sessMap = (Map<String, WorkGroup>) SessionUtil
		.getRow(SessionConstant.CenterGroup.value(),
				this.getId()+"");
		System.out.println("deleteSelect sessMap: " + sessMap);
		String[] centerResIdList = ServletActionContext.getRequest().getParameterValues("centerResId");
		for (String id : centerResIdList) {
			if (StringUtils.isNumeric(id)) {
				sessMap.put(id, null);
			} else {
				sessMap.remove(id);
			}

		}
		System.out.println("sessMap===" + sessMap);
		Struts2Util.renderText("Successful");
		return null;
	}

	
	/*************************私有方法***********************************/
	/**
	 * 给qaGroupAssignedStr赋值
	 */
	private void workCenterAssignedStr() {
		StringBuffer qaGroupAssignedStrBuf = new StringBuffer();
		if(workCenterAssignedList!=null&&workCenterAssignedList.size()>0) {
			int i=0;
			for(WorkCenterAssigned workCenterAssigned:workCenterAssignedList) {
				if(i%2==0) {
					if(i==0) {
						qaGroupAssignedStrBuf.append("<tr>");
					} else {
						qaGroupAssignedStrBuf.append("</tr><tr>");
					}
				}
				qaGroupAssignedStrBuf.append("<td><input type=\"checkbox\" value=\"")
									 .append(workCenterAssigned.getClsId()).append(":")
									 .append(workCenterAssigned.getItemType()).append(":").append(workCenterAssigned.getClsName()).append("\" name=\"assigned\" checked/>")
									 .append(workCenterAssigned.getClsName()).append("</td>");
				i++;
			}
			
			if (workCenterAssignedList.size() % 2 == 1) {
				qaGroupAssignedStrBuf.append("<td>&nbsp;</td>");
		    }
			qaGroupAssignedStrBuf.append("</tr>");
		}
		workCenterAssignedStr = qaGroupAssignedStrBuf.toString();
	}
	
	/**
	 * 构造QaGroupAssigned实例
	 */
	private void createWorkCenterAssigned() {
		WorkCenterAssigned workCenterAssigned = null;
		String[] assignValues = assignValue.split(":");
		if(assignValues!=null&&assignValues.length==3) {
			for(WorkCenterAssigned workCenterAssigned1:workCenterAssignedList) {
				if(workCenterAssigned1.getItemType().equals(assignValues[1])&&workCenterAssigned1.getClsId()==Integer.parseInt(assignValues[0])) {
					return;
				}
			}
			workCenterAssigned = new WorkCenterAssigned();
			workCenterAssigned.setClsId(Integer.parseInt(assignValues[0]));
			workCenterAssigned.setCreatedBy(SessionUtil.getUserId());
			workCenterAssigned.setItemType(assignValues[1]);
			workCenterAssigned.setClsName(assignValues[2]);
			workCenterAssigned.setModifiedBy(SessionUtil.getUserId());
			workCenterAssigned.setModifyDate(new Date());
		}
		if(workCenterAssigned!=null) {
			workCenterAssignedList.add(workCenterAssigned);
		}
		SessionUtil.insertRow(SessionConstant.WorkCenterAssigned.value(), sessWorkCenterId, workCenterAssignedList);
	}
	
	/**
	 * 删除QaGroupAssigned实例
	 */
	private void removeWorkCenterAssigned() {
		List<WorkCenterAssigned> delList = new ArrayList<WorkCenterAssigned>();
		for(WorkCenterAssigned workCenterAssigned:workCenterAssignedList) {
			if(assignValue.contains(workCenterAssigned.getClsId()+":"+workCenterAssigned.getItemType()+":"+workCenterAssigned.getClsName())) {
				delList.add(workCenterAssigned);
			}
		}
		workCenterAssignedList.removeAll(delList);
		SessionUtil.insertRow(SessionConstant.WorkCenterAssigned.value(), sessWorkCenterId, workCenterAssignedList);
	}

	
	
	public WorkCenter getWorkCenter() {
		return workCenter;
	}

	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}

	public Page<WorkCenter> getWorkCenterPage() {
		return workCenterPage;
	}

	public void setWorkCenterPage(Page<WorkCenter> workCenterPage) {
		this.workCenterPage = workCenterPage;
	}

	public String[] getDelWorkCenterId() {
		return delWorkCenterId;
	}

	public void setDelWorkCenterId(String[] delWorkCenterId) {
		this.delWorkCenterId = delWorkCenterId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<UserDTO> getSuperList() {
		return superList;
	}

	public void setSuperList(List<UserDTO> superList) {
		this.superList = superList;
	}

	public List<Integer> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<Integer> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public List<WorkGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<WorkGroup> groupList) {
		this.groupList = groupList;
	}

	public List<Integer> getDettachGroupIdList() {
		return dettachGroupIdList;
	}

	public void setDettachGroupIdList(List<Integer> dettachGroupIdList) {
		this.dettachGroupIdList = dettachGroupIdList;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public Map<String, String> getProducatServiceMap() {
		return producatServiceMap;
	}

	public void setProducatServiceMap(Map<String, String> producatServiceMap) {
		this.producatServiceMap = producatServiceMap;
	}

	

	public List<WorkCenterAssigned> getWorkCenterAssignedList() {
		return workCenterAssignedList;
	}

	public void setWorkCenterAssignedList(
			List<WorkCenterAssigned> workCenterAssignedList) {
		this.workCenterAssignedList = workCenterAssignedList;
	}

	public String getWorkCenterAssignedStr() {
		return workCenterAssignedStr;
	}

	public void setWorkCenterAssignedStr(String workCenterAssignedStr) {
		this.workCenterAssignedStr = workCenterAssignedStr;
	}

	public String getAssignValue() {
		return assignValue;
	}

	public void setAssignValue(String assignValue) {
		this.assignValue = assignValue;
	}

	public String getSessWorkCenterId() {
		return sessWorkCenterId;
	}

	public void setSessWorkCenterId(String sessWorkCenterId) {
		this.sessWorkCenterId = sessWorkCenterId;
	}

	public Map<String, WorkGroup> getCenterGroupMap() {
		return centerGroupMap;
	}

	public void setCenterGroupMap(Map<String, WorkGroup> centerGroupMap) {
		this.centerGroupMap = centerGroupMap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddAuth() {
		return addAuth;
	}

	public void setAddAuth(String addAuth) {
		this.addAuth = addAuth;
	}
}
