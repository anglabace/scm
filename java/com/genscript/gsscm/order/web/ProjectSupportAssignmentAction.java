package com.genscript.gsscm.order.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.order.dto.ProjectSupportAssignmentDTO;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.service.ProjectSupportAssignmentService;
import com.genscript.gsscm.serv.entity.ServiceClassification;
import com.genscript.gsscm.serv.service.ServiceClassificationService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhangyong
 */
@Results( {
	@Result(location = "service/setups/project_support_assignment_search_form.jsp"),
	@Result(name = "project_support_assignment_list", location = "service/setups/project_support_assignment_list.jsp"),
	@Result(name = "project_support_assignment_edit", location = "service/setups/project_support_assignment_edit.jsp"),
	@Result(name = "project_support_assignment_prosup_list", location = "service/setups/project_support_assignment_prosup_list.jsp")
})
public class ProjectSupportAssignmentAction extends ActionSupport {

	private static final long serialVersionUID = 4667478603695374634L;
	@Autowired
	private ProjectSupportAssignmentService projectSupportAssignmentService;
	@Autowired
	private ServiceClassificationService serviceClassificationService;
	@Autowired
	private ExceptionService exceptionUtil;
	private ProjectSupportAssignmentDTO projectSupportAssignmentDto;
	private List<SalesRep> saleRepList;
	private List<ServiceClassification> serviceClassificationList;
	private Page<ProjectSupportAssignmentDTO> page;
	@SuppressWarnings("unchecked")
	private Page proSupPage;
	private String ids;

	/**
	 * 初始化查询的下拉框
	 * @return
	 */
	public String searchInit () {
		serviceClassificationList = serviceClassificationService.findAll();
		return SUCCESS;
	}
	
	/**
	 * 查询列表
	 * @return
	 */
	public String list () {
		PagerUtil<ProjectSupportAssignmentDTO> pagerUtil = new PagerUtil<ProjectSupportAssignmentDTO>();
		page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("psa.id");
			page.setOrder(Page.ASC);
		}
		page.setPageSize(20);
		page = projectSupportAssignmentService.searchproSupAssignment(page, projectSupportAssignmentDto);
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		return "project_support_assignment_list";
	}
	
	/**
	 * 编辑
	 * @return
	 */
	public String edit () {
		projectSupportAssignmentDto =
			projectSupportAssignmentService.searchproSupAssignment(projectSupportAssignmentDto);
		serviceClassificationList = serviceClassificationService.findAll();
		return "project_support_assignment_edit";
	}
	
	/**
	 * 查询projectSupport
	 * @return
	 */
	public String searchProjectSupport () {
		// 获得分页请求相关数据：如第几页
		PagerUtil<OrderMainBean> pagerUtil = new PagerUtil<OrderMainBean>();
		proSupPage = pagerUtil.getRequestPage();
		if (!proSupPage.isOrderBySetted()) {
			proSupPage.setOrderBy("b.loginName");
			proSupPage.setOrder(Page.ASC);
		}
		proSupPage.setPageSize(10);
		proSupPage = this.projectSupportAssignmentService.findProSupPage(proSupPage,projectSupportAssignmentDto);
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", proSupPage);
		//saleRepList = salesRepService.findProjectSupportSalesRep();
		return "project_support_assignment_prosup_list";
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String save () {
		boolean status = this.projectSupportAssignmentService
				.saveProjectSupportAssignment(projectSupportAssignmentDto);
		if (status) {
			Struts2Util.renderText("success");
		} else {
			Struts2Util.renderText("error");
		}
		return null;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete () {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.projectSupportAssignmentService.delProjectSupportAssignment(ids.trim());
			rt.put("message", "Delete Project Support Assignment sucessfully !");
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

	public ProjectSupportAssignmentDTO getProjectSupportAssignmentDto() {
		return projectSupportAssignmentDto;
	}

	public void setProjectSupportAssignmentDto(
			ProjectSupportAssignmentDTO projectSupportAssignmentDto) {
		this.projectSupportAssignmentDto = projectSupportAssignmentDto;
	}

	public List<SalesRep> getSaleRepList() {
		return saleRepList;
	}

	public void setSaleRepList(List<SalesRep> saleRepList) {
		this.saleRepList = saleRepList;
	}

	public List<ServiceClassification> getServiceClassificationList() {
		return serviceClassificationList;
	}

	public void setServiceClassificationList(
			List<ServiceClassification> serviceClassificationList) {
		this.serviceClassificationList = serviceClassificationList;
	}

	public Page<ProjectSupportAssignmentDTO> getPage() {
		return page;
	}

	public void setPage(Page<ProjectSupportAssignmentDTO> page) {
		this.page = page;
	}


	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@SuppressWarnings("unchecked")
	public Page getProSupPage() {
		return proSupPage;
	}

	@SuppressWarnings("unchecked")
	public void setProSupPage(Page proSupPage) {
		this.proSupPage = proSupPage;
	}

}
