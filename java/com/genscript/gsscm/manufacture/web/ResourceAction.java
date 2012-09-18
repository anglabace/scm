package com.genscript.gsscm.manufacture.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.manufacture.entity.OperationResource;
import com.genscript.gsscm.manufacture.entity.Resource;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "search_from", location = "manufacture/resource_search_form.jsp"),
		@Result(name = "search_result", location = "manufacture/resource_search_result.jsp"),
		@Result(name = "resource_add", location = "manufacture/resource_add.jsp"),
		@Result(name = "resource_edit", location = "manufacture/resource_edit.jsp"),
		@Result(name = "group_select_resource", location = "manufacture/group_select_resource.jsp"),
		@Result(name = "operation_resource_select", location = "manufacture/operation_resource_select.jsp"),
		//Work order > Operation -> Resource new.
		@Result(name = "wo_resource_select", location = "manufacture/workorder_operation_resource_select.jsp")
})
public class ResourceAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534283335416418911L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private SetupService setupService;
	@Autowired
	private PublicService publicService;
	private Resource resource;
	private Integer resourceId;
	private Page<Resource> resourcePage;
	private String[] delResourceId;
	private List<PbCurrency> currencyList;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private List<WorkGroup> workGroupList;
	private String operation_method;
	private boolean workGroupShow;
	private Integer centerId;
	private Integer operationId;
	private String workGroupId;
	private List<WorkCenter> userDeptList;
	/**
	 * 进入Resource的主页面
	 * 
	 * @return
	 */
	public String search() {
		try {
			this.userDeptList = this.setupService.getAllWorkCenter(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER);
			List<DropDownListName> listName = new ArrayList<DropDownListName>();
			listName.add(DropDownListName.RESOURCE_GROUP);
			dropDownMap = publicService.getDropDownMap(listName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "search_from";
	}

	/**
	 * 进入新增页面
	 */
	public String add() {
		this.doInput();
		resource = new Resource();
		resource.setCostBasis("Per Item");
		return "resource_add";
	}

	/**
	 * 进入修改页面.
	 * 
	 * @return
	 */
	public String edit() {
		//*********** Add By Zhang Yong Start *****************************//
		if (this.resourceId != null && !("").equals(this.resourceId)) {
			//判断将要修改的单号是否正在被操作
			String editUrl = "resource!edit.action?resourceId="+this.resourceId;
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
		resource = this.setupService.getResource(this.resourceId);
		if(workGroupShow) {
			workGroupList = this.setupService.getGroupListByCenter(centerId);
			OperationResource operationResource = this.setupService.findByOpAndRE(operationId,this.resourceId);
			resource.setWorkGroupId(operationResource!=null?operationResource.getWorkGroupId():null);
		}
		return "resource_edit";
	}
	
	private void doInput(){
		try {
			this.userDeptList =  this.setupService.getAllWorkCenter(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER);
			this.currencyList = this.publicService.getCurrencyList();
			List<DropDownListName> listName = new ArrayList<DropDownListName>();
			listName.add(DropDownListName.RESOURCE_COST_BASIS);
			listName.add(DropDownListName.RESOURCE_UOM);
			listName.add(DropDownListName.RESOURCE_GROUP);
			dropDownMap = publicService.getDropDownMap(listName);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页查找
	 */
	public String list() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Resource> pagerUtil = new PagerUtil<Resource>();
			resourcePage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!resourcePage.isOrderBySetted()) {
				resourcePage.setOrderBy("resourceNo");
				resourcePage.setOrder(Page.ASC);
			}
			// 设置默认每页显示记录条数
			resourcePage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			resourcePage = setupService.searchResourcePage(resourcePage, filters);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					resourcePage);
		} catch (Exception ex) {
			return "search_result";
		}
		return "search_result";
	}

	/**
	 * 新增或修改一个Resource
	 */
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			//*********** Add By Zhang Yong Start *****************************//
			//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (resource!= null && resource.getResourceId() != null) {
				String editUrl = "resource!edit.action?resourceId="+resource.getResourceId();
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message", "Save fail,the resource is editing by "+operation_method);
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			//*********** Add By Zhang Yong End *****************************//	
			this.setupService.saveResource(resource, SessionUtil.getUserId());
			rt.put("message", "Save resource sucessfully.");
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
	 * 保存resource与operation关联的表的信息
	 */
	public String updateOperationResource() {
		Map<String, Object> rt = new HashMap<String, Object>();
		Map<String, OperationResource> sessMap = (Map<String, OperationResource>) SessionUtil
		.getRow(SessionConstant.OperationResource.value(),
				String.valueOf(this.operationId));
		if (sessMap == null) {
			return NONE;
		}
		for (Entry<String, OperationResource> entry : sessMap.entrySet()) {
			OperationResource groupRes =  entry.getValue();
			if(groupRes.getResource().getResourceId().intValue()==resourceId.intValue()&&groupRes.getOperationId().intValue()==this.operationId.intValue()) {
				groupRes.setWorkGroupId(StringUtils.isNotEmpty(workGroupId)?Integer.parseInt(workGroupId):null);
				break;
			}
		}
		SessionUtil.updateRow(SessionConstant.OperationResource.value(), String.valueOf(this.operationId), sessMap);
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 
	 * 批量删除Resource.
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			List<Integer> idList = new ArrayList<Integer>();
			for (String temp : this.delResourceId) {
				idList.add(Integer.valueOf(temp));
			}
			this.setupService.delResource(idList);
			rt.put("message", "Delete resource sucessfully !");
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
	 * WorkGroup编辑页面的select resource.
	 * @return
	 */
	public String selectForGroup() {
		String pageName = "group_select_resource";
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Resource> pagerUtil = new PagerUtil<Resource>();
			resourcePage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!resourcePage.isOrderBySetted()) {
				resourcePage.setOrderBy("resourceNo");
				resourcePage.setOrder(Page.ASC);
			}
			// 设置默认每页显示记录条数
			resourcePage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			if (filters == null || filters.isEmpty()) {
				// 默认列表的结果集(含分页信息)
				resourcePage = setupService.searchResourcePage(resourcePage, null);
			} else {
				// 有查询条件的结果集(含分页信息)
				resourcePage = setupService.searchResourcePage(resourcePage, filters);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					resourcePage);
			if (Struts2Util.getParameter("pageName") != null) {
				pageName = Struts2Util.getParameter("pageName");
			}
		} catch (Exception ex) {
			return pageName;	
		}
		return pageName;	
	}

	
	/**
	 * WorkGroup编辑页面的select resource.
	 * @return
	 */
	public String selectForOperation() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Resource> pagerUtil = new PagerUtil<Resource>();
			resourcePage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!resourcePage.isOrderBySetted()) {
				resourcePage.setOrderBy("resourceId");
				resourcePage.setOrder(Page.DESC);
			}
			// 设置默认每页显示记录条数
			resourcePage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			if (filters == null || filters.isEmpty()) {
				// 默认列表的结果集(含分页信息)
				resourcePage = setupService.searchResourcePage(resourcePage, null);
			} else {
				// 有查询条件的结果集(含分页信息)
				resourcePage = setupService.searchResourcePage(resourcePage, filters);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					resourcePage);
		} catch (Exception ex) {
			return "operation_resource_select";	
		}
		return "operation_resource_select";	
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Page<Resource> getResourcePage() {
		return resourcePage;
	}

	public void setResourcePage(Page<Resource> resourcePage) {
		this.resourcePage = resourcePage;
	}

	public String[] getDelResourceId() {
		return delResourceId;
	}

	public void setDelResourceId(String[] delResourceId) {
		this.delResourceId = delResourceId;
	}

	public List<PbCurrency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<PbCurrency> currencyList) {
		this.currencyList = currencyList;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public List<WorkGroup> getWorkGroupList() {
		return workGroupList;
	}

	public void setWorkGroupList(List<WorkGroup> workGroupList) {
		this.workGroupList = workGroupList;
	}

	public boolean isWorkGroupShow() {
		return workGroupShow;
	}

	public void setWorkGroupShow(boolean workGroupShow) {
		this.workGroupShow = workGroupShow;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	public String getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}

	public List<WorkCenter> getUserDeptList() {
		return userDeptList;
	}

	public void setUserDeptList(List<WorkCenter> userDeptList) {
		this.userDeptList = userDeptList;
	}


}
