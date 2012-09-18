package com.genscript.gsscm.systemsetting.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dto.SalesGroupDTO;
import com.genscript.gsscm.customer.entity.SalesGroup;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.genscript.gsscm.systemsetting.service.SalesGroupService;
import com.genscript.gsscm.systemsetting.service.SalesResourceService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Description:Sales Groups control class
 * @Copyright: Copyright genscrpit (c)2011
 * @author: lizhang
 * @version: 1.0
 * @date:   2011-02-14
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Results({
	@Result(name = "sales_group", location = "systemsetting/SalesTerritories/sales_group.jsp"),
	@Result(name = "sales_group_list", location = "systemsetting/SalesTerritories/sales_group_list.jsp"),
	@Result(name = "resource_list", location="systemsetting/SalesTerritories/resource_list.jsp"),
	@Result(name = "sales_group_detail", location = "systemsetting/SalesTerritories/sales_group_detail.jsp"),
	@Result(name = "resource_detail", location = "systemsetting/SalesTerritories/resource_detail.jsp"),
	@Result(name="group_supervisor",location="systemsetting/SalesTerritories/group_supervisor.jsp"),
	@Result(name="resource_select",location="systemsetting/SalesTerritories/resource_select.jsp")
})
public class SalesGroupAction extends ActionSupport{

	private static final long serialVersionUID = 7553332491444775258L;
	/**自动装载实例**/
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private PublicService publicService;
	@Autowired
	private SalesGroupService salesGroupService;
	@Autowired
	private SalesResourceService salesResourceService;
	/**action变量**/
	private Integer groupId;
	private String sessionId;
	private String salesId;
	private String resourceSessionId;
	private String operation_method;
	private String allChoiceVal;//所有被选中的
	private SalesGroup salesGroup;
	private SalesGroupDTO salesGroupDTO;
	private SalesRep salesRep;
	private Page<SalesGroup> salesGroupPage;
	private Page<User> usersPage;
	private Page<SalesRep> salesRepPage;
	private List<DepartmentSystem> depList;
	private Map<String,SalesRep> salesResourceMap;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;//function 
	
	/***********************action method******************************************/
	public String input() {
		this.dropDownList();
		return "sales_group";
	}
	public String list() {
		salesGroupPage = this.salesGroupService.searchSalesGroupPage(salesGroupPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				salesGroupPage);
		return "sales_group_list";
	}
	public String load() {
		
		try {
			if(groupId!=null) {
				// 判断将要修改的单号是否正在被操作
				String editUrl = "systemsetting/sales_group!load.action?groupId=" + groupId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
			}
			if (Struts2Util.getParameter("referURL") != null
					&& Struts2Util.getParameter("referURL").equals("select")) {
				salesGroupDTO = (SalesGroupDTO)SessionUtil.getRow(SessionConstant.SalesGroup.value(), sessionId);
				salesResourceMap = (Map<String,SalesRep>)SessionUtil.getRow(SessionConstant.SalesResourceList.value(), sessionId);
			} else {
				if(groupId!=null) {
					salesGroupDTO = this.salesGroupService.findById(groupId);
					this.sessionId = String.valueOf(groupId);
					
				} else {
					this.sessionId = SessionUtil.generateTempId();
					salesGroupDTO = new SalesGroupDTO();
					salesGroupDTO.setGsCoId(1);
					salesGroupDTO.setCreatedBy(SessionUtil.getUserId());
				}
				salesGroupDTO.setModifiedBy(SessionUtil.getUserId());
				salesGroupDTO.setModifiedName(SessionUtil.getUserName());
				salesGroupDTO.setModifyDate(new Date());
				// 建新的session
				SessionUtil.insertRow(SessionConstant.SalesGroup.value(), sessionId,
						salesGroupDTO);
				salesResourceMap = SessionUtil.convertList2Map(salesGroupDTO.getResourceList(),
				"salesId");
				SessionUtil.insertRow(SessionConstant.SalesResourceList.value(),
				sessionId, salesResourceMap);
				dropDownList();
			}
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		} catch(Exception e) {
			e.printStackTrace();
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		}
		return "sales_group_detail";
	}
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (sessionId != null && !("").equals(sessionId)) {
				String editUrl = "systemsetting/sales_group!load.action?groupId=" + groupId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message",
							"Failed to save the sales group ,the sales group is editing by "+operation_method);
					rt.put("no", sessionId);
					Struts2Util.renderJson(rt);
					return null;
				}
				salesResourceMap = (Map<String,SalesRep>)SessionUtil.getRow(SessionConstant.SalesResourceList.value(), sessionId); 
				List<SalesRep> resourceList = SessionUtil.convertMap2List(salesResourceMap);
				salesGroupDTO.setResourceList(resourceList);
				this.salesGroupService.saveSalesGroup(salesGroupDTO,sessionId);
				rt.put("message", "Save sales group sucessfully.");
			}
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
		return null;
	}
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.salesGroupService.deleteSalesGroups(allChoiceVal);
		}  catch (Exception ex) {
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
	
	public String listResource() {
		salesResourceMap = (Map<String,SalesRep>)SessionUtil.getRow(SessionConstant.SalesResourceList.value(), sessionId);
		return "resource_list";
	}
	
	public String loadResource() {
		try {
			if(StringUtils.isNotEmpty(resourceSessionId)) {
				Map<String,SalesRep> salesResourceMap = (Map<String,SalesRep>)SessionUtil.getRow(SessionConstant.SalesResourceList.value(), sessionId);
				salesRep = salesResourceMap.get(resourceSessionId);
			} else {
				resourceSessionId = SessionUtil.generateTempId();
				salesRep = new SalesRep();
				salesRep.setGsCoId(1);
				salesRep.setCreatedBy(SessionUtil.getUserId());
				salesRep.setCreationDate(new Date());
			}
			salesRep.setModifiedBy(SessionUtil.getUserId());
			salesRep.setModifiedName(SessionUtil.getUserName());
			salesRep.setModifyDate(new Date());
			this.dropDownList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "resource_detail";
	}
	
	public String saveResource() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String,SalesRep> salesResourceMap = (Map<String,SalesRep>)SessionUtil.getRow(SessionConstant.SalesResourceList.value(), sessionId);
			if(StringUtils.isNotEmpty(salesId)&&StringUtil.isNumeric(salesId)) {
				salesRep = salesResourceService.findById(Integer.parseInt(salesId));
				salesResourceMap.put(salesId, salesRep);
			}
			SessionUtil.updateRow(SessionConstant.SalesResourceList.value(), sessionId, salesResourceMap);
			rt.put("message", "Save sales resource sucessfully.");
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
	public String deleteResource() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.salesGroupService.deleteSalesRep(allChoiceVal,sessionId);
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
	
	public String save2Session() {
		SessionUtil.updateRow(SessionConstant.SalesGroup.value(), sessionId,
				salesGroupDTO);
		Struts2Util.renderText("Success");
		return null;
	}
	
	/**
	 * 选择人员
	 */
	public String selectUser() {
		usersPage = this.salesGroupService.searchUserPage(usersPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				usersPage);
		return "group_supervisor";
	}
	
	/**
	 * 选择Sales Resource
	 */
	public String selectResource() {
		salesRepPage = salesResourceService.searchSalesRepPage(salesRepPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				salesRepPage);
		return "resource_select";
	}
	/*******************private method***********************************************/
	/**
	 * 给页面下拉列表框赋值
	 */
	private void dropDownList() {
		depList = this.salesGroupService.getAllDep();
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.SALES_REP_FUNCTION);
		dropDownMap = publicService.getDropDownMap(listName);
	}
	/*************getter setter****************************************************/
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getResourceSessionId() {
		return resourceSessionId;
	}
	public void setResourceSessionId(String resourceSessionId) {
		this.resourceSessionId = resourceSessionId;
	}
	public String getOperation_method() {
		return operation_method;
	}
	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}
	public String getAllChoiceVal() {
		return allChoiceVal;
	}
	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}
	public SalesGroup getSalesGroup() {
		return salesGroup;
	}
	public void setSalesGroup(SalesGroup salesGroup) {
		this.salesGroup = salesGroup;
	}
	public Page<SalesGroup> getSalesGroupPage() {
		return salesGroupPage;
	}
	public void setSalesGroupPage(Page<SalesGroup> salesGroupPage) {
		this.salesGroupPage = salesGroupPage;
	}
	public List<DepartmentSystem> getDepList() {
		return depList;
	}
	public void setDepList(List<DepartmentSystem> depList) {
		this.depList = depList;
	}
	public SalesGroupDTO getSalesGroupDTO() {
		return salesGroupDTO;
	}
	public void setSalesGroupDTO(SalesGroupDTO salesGroupDTO) {
		this.salesGroupDTO = salesGroupDTO;
	}
	public Map<String, SalesRep> getSalesResourceMap() {
		return salesResourceMap;
	}
	public void setSalesResourceMap(Map<String, SalesRep> salesResourceMap) {
		this.salesResourceMap = salesResourceMap;
	}
	public SalesRep getSalesRep() {
		return salesRep;
	}
	public void setSalesRep(SalesRep salesRep) {
		this.salesRep = salesRep;
	}
	public Page<User> getUsersPage() {
		return usersPage;
	}
	public void setUsersPage(Page<User> usersPage) {
		this.usersPage = usersPage;
	}
	public Page<SalesRep> getSalesRepPage() {
		return salesRepPage;
	}
	public void setSalesRepPage(Page<SalesRep> salesRepPage) {
		this.salesRepPage = salesRepPage;
	}
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}
	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}
	
	
	

}
