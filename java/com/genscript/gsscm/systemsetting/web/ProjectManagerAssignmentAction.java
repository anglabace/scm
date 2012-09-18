package com.genscript.gsscm.systemsetting.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.entity.SalesProjectManagerAssignment;
import com.genscript.gsscm.customer.entity.SalesRepBean2;
import com.genscript.gsscm.serv.entity.ServiceClassification;
import com.genscript.gsscm.systemsetting.service.ProjectManagerAssignmentService;
import com.genscript.gsscm.systemsetting.service.TerritoryAssignService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Description:ProjectManagerAssignmentAction (模块 system setting/quote&order/Project Manager Assignment维护Action)
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date:   2011-4-1
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Results({
	@Result(name = "project_manager_assignment", location = "systemsetting/SalesTerritories/projectManagerAssignment.jsp"),
	@Result(name = "project_manager_assignment_list", location = "systemsetting/SalesTerritories/projectManagerAssignment_list.jsp"),
	@Result(name = "project_manager_assignment_detail", location="systemsetting/SalesTerritories/projectManagerAssignment_detail.jsp"),
	@Result(name = "project_manager_select", location = "systemsetting/SalesTerritories/project_manager_select.jsp")
})
public class ProjectManagerAssignmentAction extends ActionSupport{
	private static final long serialVersionUID = -603635074266009954L;
	/**自动装载实例**/
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ProjectManagerAssignmentService projectManagerAssignmentService;
	@Autowired
	private TerritoryAssignService territoryAssignService;
	
	/**action变量**/
	private Integer assignId;
	private String operation_method;
	private String allChoiceVal;
	private SalesProjectManagerAssignment salesProjectManagerAssignment;
	private List<ServiceClassification> serviceClassificationList;
	private Page<SalesProjectManagerAssignment> salesProjectManagerAssignmentPage;
	private Page<SalesRepBean2> salesRepPage;
	
	/**********************************action method************************************/
	
	/**
	 * 进入该模块默认执行的action方法
	 */
	public String input() {
		serviceClassificationList = this.projectManagerAssignmentService.getAllServiceClassification();
		return "project_manager_assignment";
	}
	
	/**
	 * 列表页面显示
	 */
	public String list() {
		salesProjectManagerAssignmentPage = this.projectManagerAssignmentService.searchsalesProjectManagerAssignmentPage(salesProjectManagerAssignmentPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				salesProjectManagerAssignmentPage);
		return "project_manager_assignment_list";
	}
	
	/**
	 * 添加或修改页面显示
	 */
	public String load() {
		serviceClassificationList = this.projectManagerAssignmentService.getAllServiceClassification();
		try {
			if(assignId!=null) {
				// 判断将要修改的单号是否正在被操作
				String editUrl = "systemsetting/project_manager_assignment!load.action?assignId=" + assignId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
			}

			if(assignId!=null) {
				salesProjectManagerAssignment = this.projectManagerAssignmentService.findById(assignId);
			} else {
				salesProjectManagerAssignment = new SalesProjectManagerAssignment();
				salesProjectManagerAssignment.setCreatedBy(SessionUtil.getUserId());
			}
			salesProjectManagerAssignment.setModifiedBy(SessionUtil.getUserId());
			salesProjectManagerAssignment.setModifiedName(SessionUtil.getUserName());
			salesProjectManagerAssignment.setModifyDate(new Date());
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		} catch(Exception e) {
			e.printStackTrace();
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		}
		return "project_manager_assignment_detail";
	}
	
	/**
	 * 保存或更新
	 */
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		
		try {
			// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (salesProjectManagerAssignment.getAssignId() != null && !("").equals(salesProjectManagerAssignment.getAssignId())) {
				String editUrl = "systemsetting/project_manager_assignment!load.action?assignId=" + salesProjectManagerAssignment.getAssignId();
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message",
							"Failed to save the project manager assignment ,the project manager assignment is editing by "+operation_method);
					rt.put("no", salesProjectManagerAssignment.getAssignId());
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			projectManagerAssignmentService.saveAssignment(salesProjectManagerAssignment);
			rt.put("message", "Save successfully.");
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
	 * 删除
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.projectManagerAssignmentService.deleteAssignments(allChoiceVal);
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
	
	/**
	 * project manager 选择页面显示
	 */
	public String selectProjectManager() {
		salesRepPage = territoryAssignService.searchSalesRepBean2Page(salesRepPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				salesRepPage);
		return "project_manager_select";
	}

	
	/******************************getter setter*********************************************/
	public Integer getAssignId() {
		return assignId;
	}

	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}

	public SalesProjectManagerAssignment getSalesProjectManagerAssignment() {
		return salesProjectManagerAssignment;
	}

	public void setSalesProjectManagerAssignment(
			SalesProjectManagerAssignment salesProjectManagerAssignment) {
		this.salesProjectManagerAssignment = salesProjectManagerAssignment;
	}

	public List<ServiceClassification> getServiceClassificationList() {
		return serviceClassificationList;
	}

	public void setServiceClassificationList(
			List<ServiceClassification> serviceClassificationList) {
		this.serviceClassificationList = serviceClassificationList;
	}

	public Page<SalesProjectManagerAssignment> getSalesProjectManagerAssignmentPage() {
		return salesProjectManagerAssignmentPage;
	}

	public void setSalesProjectManagerAssignmentPage(
			Page<SalesProjectManagerAssignment> salesProjectManagerAssignmentPage) {
		this.salesProjectManagerAssignmentPage = salesProjectManagerAssignmentPage;
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

	public Page<SalesRepBean2> getSalesRepPage() {
		return salesRepPage;
	}

	public void setSalesRepPage(Page<SalesRepBean2> salesRepPage) {
		this.salesRepPage = salesRepPage;
	}
	
	
	
}
