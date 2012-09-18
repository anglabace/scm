package com.genscript.gsscm.manufacture.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.genscript.gsscm.manufacture.entity.GroupResource;
import com.genscript.gsscm.manufacture.entity.OperationResource;
import com.genscript.gsscm.manufacture.entity.Resource;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.dto.UserSrchDTO;
import com.genscript.gsscm.privilege.entity.Role;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "search_from", location = "manufacture/workGroup_search_form.jsp"),
		@Result(name = "search_result", location = "manufacture/workGroup_search_result.jsp"),
		@Result(name = "workGroup_add", location = "manufacture/workGroup_add.jsp"),
		@Result(name = "workGroup_edit", location = "manufacture/workGroup_edit.jsp") ,
        @Result(name = "center_select_group", location = "manufacture/center_select_group.jsp"),
        @Result(name="workGroup_resource_list",location="manufacture/workGroup_resource_list.jsp"),
        @Result(name="supervisor_select",location="manufacture/supervisor_select.jsp")
})
public class WorkGroupAction extends ActionSupport {

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

	private WorkGroup workGroup;
	private Integer id;
	private Page<WorkGroup> workGroupPage;
	private Page<User> usersPage;
	private String[] delWorkGroupId;
	private List<UserDTO> superList;
	private Map<String, GroupResource> groupResourceMap;
	private List<Integer> resourceIdList;
	private List<Double> quantityList;

	private String operation_method;
	
	private UserSrchDTO srch;
	private Integer employeeId;
	private String employeeName;
	private List<WorkCenter> workCenterList;
	
	private String resKey;
	private String quantity;
	
	private String addAuth;//是否具有添加权限
	/**
	 * 进入WorkGroup的主页面
	 * 
	 * @return
	 */
	public String search() {
		if(setupService.isHasTheRole(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER)) {
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
		//this.doInput();
		return "workGroup_add";
	}

	/**
	 * 进入修改页面.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String edit() {
		//*********** Add By Zhang Yong Start *****************************//
		if (this.id != null && !("").equals(this.id)) {
			//判断将要修改的单号是否正在被操作
			String editUrl = "work_group!edit.action?id="+id;
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
		//this.doInput();
		workGroup = this.setupService.getWorkGroup(this.id);
//		Map<String, GroupResource> dbMap = new LinkedHashMap<String, GroupResource>();
//		Map<String, GroupResource> sesMap = (Map<String, GroupResource>)SessionUtil.getRow(SessionConstant.GroupResource.toString(), this.id+"");
//		if(sesMap!=null) {
//			dbMap = sesMap;
//		} else {
//			//取得数据库中的
//			List<GroupResource> groupResourceList = this.setupService
//					.getGroupResourceList(this.id);
//
//			for (GroupResource gr : groupResourceList) {
//				dbMap.put(gr.getId() + "", gr);
//				//从数据库中取出来的每一行都放入session中.
//				SessionUtil.updateOneRow(SessionConstant.GroupResource.toString(),
//						this.id+"", gr.getId()+"", gr);
//			}
//		}
//		this.groupResourceMap = dbMap;	
		return "workGroup_edit";
	}

	public void doInput() {
		Role role = privilegeService.getSuperRole();
		if (role != null) {
			this.superList = privilegeService.getUserListByRole(role
					.getRoleId());
		} else {
			this.superList = new ArrayList<UserDTO>();
		}

	}
	/**
	 * 进入一个WorkGroup的Resource list页面.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getResourceList() {
		Map<String, GroupResource> dbMap = null;
		Map<String, GroupResource> sessMap = (Map<String, GroupResource>) SessionUtil
		.getRow(SessionConstant.GroupResource.value(),
				String.valueOf(this.id));
		if(sessMap==null) {
			List<GroupResource> dbList = this.setupService.getGroupResourceList(this.id);
			dbMap = new LinkedHashMap<String, GroupResource>();
			for (GroupResource dto : dbList) {
				dbMap.put(dto.getId() + "", dto);
			}
			SessionUtil.insertRow(SessionConstant.GroupResource.value(),
					String.valueOf(this.id), dbMap);
		} else {
			dbMap = sessMap;
		}
		groupResourceMap = dbMap;
		return "workGroup_resource_list";
	}


	/**
	 * 分页查找
	 */
	public String list() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<WorkGroup> pagerUtil = new PagerUtil<WorkGroup>();
			workGroupPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!workGroupPage.isOrderBySetted()) {
				workGroupPage.setOrderBy("name");
				workGroupPage.setOrder(Page.ASC);
			}
			// 设置默认每页显示记录条数
			workGroupPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			// 有查询条件的结果集(含分页信息)
			workGroupPage = setupService.searchWorkGroupPage(workGroupPage, filters);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					workGroupPage);
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.remove(SessionConstant.GroupResource.value());
		} catch (Exception ex) {
			return "search_result";
		}
		return "search_result";
	}

	public String selectForCenter() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<WorkGroup> pagerUtil = new PagerUtil<WorkGroup>();
		workGroupPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!workGroupPage.isOrderBySetted()) {
			workGroupPage.setOrderBy("id");
			workGroupPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		workGroupPage.setPageSize(20);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
        for (PropertyFilter pf : filters) {
        	String temp[] = pf.getPropertyNames();
        	String names = "";
        	for (String str : temp) {
        		names += "," + str;
        	}
        	System.out.println("names:" + names + " getPropertyType(): " + pf.getMatchType().name() + " getPropertyValue: " + pf.getPropertyValue());
        }
		
		// 有查询条件的结果集(含分页信息)
		workGroupPage = setupService.selectForCenter(workGroupPage, filters, Integer.parseInt(Struts2Util.getParameter("centerId")));
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				workGroupPage);
		return "center_select_group";
	}
	
	/**
	 * 新增或修改一个WorkGroup
	 */
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			//*********** Add By Zhang Yong Start *****************************//
			//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (workGroup!= null && workGroup.getId() != null) {
				String editUrl = "work_group!edit.action?id="+workGroup.getId();
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message", "Save fail,the workGroup is editing by "+operation_method);
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			//*********** Add By Zhang Yong End *****************************//	
			this.attachResource();
			this.setupService.saveWorkGroup(workGroup, SessionUtil.getUserId());
			SessionUtil.deleteRow(SessionConstant.GroupResource.value(),
					this.workGroup.getId()+"");	
			rt.put("message", "Save work group sucessfully.");
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
	 * 保存Group同时保存Group Resoource.
	 */
	@SuppressWarnings("unchecked")
	private void attachResource() {			
		List<GroupResource> groupResList = new ArrayList<GroupResource>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, GroupResource> sessMap = (Map<String, GroupResource>) SessionUtil
		.getRow(SessionConstant.GroupResource.value(),
				this.getWorkGroup().getId()+"");
		if (sessMap != null) {
			Iterator<Entry<String, GroupResource>> it = sessMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, GroupResource> entry = it.next();
				GroupResource groupRes = (GroupResource) entry
						.getValue();
				//本例中对session中的数据只有新增和删除操作
				if (StringUtils.isNumeric(entry.getKey())) {//数据库已有的
					if (groupRes == null) {//执行了临时的删除操作
						delIdList.add(Integer.parseInt(entry.getKey()));
					} else {
						groupResList.add(groupRes);
					}
					//TODO 修改部分的处理暂不考虑
				} else {
					groupRes.setId(null);
					groupResList.add(groupRes);//新增的
				}
			}
		}
		this.workGroup.setDelGroupResIdList(delIdList);
		this.workGroup.setGroupResList(groupResList);
	}

	/**
	 * 
	 * 批量删除WorkGroup.
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			List<Integer> idList = new ArrayList<Integer>();
			for (String temp : this.delWorkGroupId) {
				idList.add(Integer.valueOf(temp));
			}
			this.setupService.delWorkGroup(idList);
			rt.put("message", "Delete work group sucessfully !");
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
	 * 在新增Resource时通过select操作,选择多个Resource后需要调用这个方法.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectCallBack() throws Exception {
		Map<String, String> retMap = new HashMap<String, String>();
		Map<String, GroupResource> sessMap = (Map<String, GroupResource>) SessionUtil
		.getRow(SessionConstant.GroupResource.value(),
				String.valueOf(this.id));
		for (int i = this.resourceIdList.size()-1; i >=0; i--) {
			boolean flg = true;
			if(sessMap!=null&&sessMap.size()>0) {
				for(Map.Entry<String, GroupResource> entry:sessMap.entrySet()) {
					GroupResource groupResource = entry.getValue();
					if(groupResource!=null&&groupResource.getResource()!=null&&groupResource.getResource().getResourceId().intValue()==this.resourceIdList.get(i).intValue()) {
						groupResource.setQuantity(new BigDecimal((groupResource.getQuantity()==null?0:groupResource.getQuantity().doubleValue())+this.quantityList.get(i).doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
						flg = false;
						break;
					}
				}
			}
			if(flg) {
				String tempId = SessionUtil.generateTempId();
				Resource resource = this.setupService.getResource(this.resourceIdList.get(i));
				GroupResource gr = new GroupResource();
				gr.setResource(resource);
				gr.setGroupId(this.id);
				gr.setQuantity(new BigDecimal(this.quantityList.get(i)).setScale(2, BigDecimal.ROUND_HALF_UP));
				sessMap.put(tempId, gr);
//				SessionUtil.updateOneRow(SessionConstant.GroupResource.toString(),
//						this.getId()+"", tempId, gr);
			}
			
//			retMap.put(this.resourceIdList.get(i), tempId);
		
		}
		SessionUtil.updateRow(SessionConstant.GroupResource.value(),
				String.valueOf(this.id),sessMap);
		retMap.put("message", "success");
		Struts2Util.renderJson(retMap);
		return null;
	}
	
	/**
	 * 删除选中的Resource(此时有可能是删除已经存在的， 也可能删除刚刚加入的)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteSelect() {
		Map<String, GroupResource> sessMap = (Map<String, GroupResource>) SessionUtil
		.getRow(SessionConstant.GroupResource.value(),
				this.getId()+"");
		System.out.println("deleteSelect sessMap: " + sessMap);
		String[] groupResIdList = ServletActionContext.getRequest().getParameterValues("groupResId");
		for (String id : groupResIdList) {
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
	
	public String updateQty() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		// 取得session中的
		Map<String, GroupResource> sessMap = (Map<String, GroupResource>) SessionUtil.getRow(SessionConstant.GroupResource.value(),String.valueOf(this.id));
		GroupResource ors = sessMap.get(resKey);
		ors.setQuantity(new BigDecimal(quantity).setScale(2, BigDecimal.ROUND_HALF_UP));
		SessionUtil.updateOneRow(SessionConstant.GroupResource.value(),
				String.valueOf(this.id), resKey, ors);
		Struts2Util.renderJson(retMap);
		return NONE;
	}
	
	/**
	 * 弹出选择Supervisor的对话框
	 * @return
	 */
	public String selectUser() {
		this.workCenterList = this.setupService.getAllWorkCenter();
		usersPage = this.setupService.searchUserPage(usersPage,srch);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				usersPage);
		return "supervisor_select";
	}

	public WorkGroup getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(WorkGroup workGroup) {
		this.workGroup = workGroup;
	}

	public Page<WorkGroup> getWorkGroupPage() {
		return workGroupPage;
	}

	public void setWorkGroupPage(Page<WorkGroup> workGroupPage) {
		this.workGroupPage = workGroupPage;
	}

	public String[] getDelWorkGroupId() {
		return delWorkGroupId;
	}

	public void setDelWorkGroupId(String[] delWorkGroupId) {
		this.delWorkGroupId = delWorkGroupId;
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

	public List<Integer> getResourceIdList() {
		return resourceIdList;
	}

	public void setResourceIdList(List<Integer> resourceIdList) {
		this.resourceIdList = resourceIdList;
	}

	public List<Double> getQuantityList() {
		return quantityList;
	}

	public void setQuantityList(List<Double> quantityList) {
		this.quantityList = quantityList;
	}

	public Map<String, GroupResource> getGroupResourceMap() {
		return groupResourceMap;
	}

	public void setGroupResourceMap(Map<String, GroupResource> groupResourceMap) {
		this.groupResourceMap = groupResourceMap;
	}

	public Page<User> getUsersPage() {
		return usersPage;
	}

	public void setUsersPage(Page<User> usersPage) {
		this.usersPage = usersPage;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public UserSrchDTO getSrch() {
		return srch;
	}

	public void setSrch(UserSrchDTO srch) {
		this.srch = srch;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAddAuth() {
		return addAuth;
	}

	public void setAddAuth(String addAuth) {
		this.addAuth = addAuth;
	}

	public List<WorkCenter> getWorkCenterList() {
		return workCenterList;
	}

	public void setWorkCenterList(List<WorkCenter> workCenterList) {
		this.workCenterList = workCenterList;
	}

}
