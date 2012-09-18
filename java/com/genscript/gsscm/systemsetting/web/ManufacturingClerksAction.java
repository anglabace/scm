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
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.manufacture.entity.ManufacturingClerks;
import com.genscript.gsscm.manufacture.entity.ManufacturingClerksBean;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.systemsetting.service.ManufacturingClerksService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Description:
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date:   2010-12-7
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Results( {
	@Result(name = "manufacturing_clerks_search", location = "systemsetting/Manufacturing/manufacturing_clerks_search.jsp"),
	@Result(name = "manufacturing_clerks_list", location = "systemsetting/Manufacturing/manufacturing_clerks_list.jsp"),
	@Result(name="manufacturing_clerks_detail",location="systemsetting/Manufacturing/manufacturing_clerks_detail.jsp"),
 	@Result(name="clerk_users",location="systemsetting/Manufacturing/clerk_users.jsp")
})
public class ManufacturingClerksAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	/**自动装载实例**/
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ManufacturingClerksService manufacturingClerksService;
	/**action变量**/
	private Page<ManufacturingClerksBean> manClerksPage;
	private Page<User> usersPage;
	private List<WorkGroup> workGroupList;
	private List<WorkCenter> workCenterList;
	private ManufacturingClerks manufacturingClerks;
	private Integer id;
	private String workCenterId;
	private String workGroupId;
	private String workCenterSupervisor;
	private String workGroupSupervisor;
	private String allChoiceVal;//保存前台被选中的对象ID
	
	
	/****************************************action method********************************************/
	
	/**
	 * 查询页
	 */
	public String search() {
		this.dropDownList();
		return "manufacturing_clerks_search";
	}
	/**
	 * 列表页
	 * @return
	 */
	public String list() {
		manClerksPage = this.manufacturingClerksService.searchManClerksPage(manClerksPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				manClerksPage);
		return "manufacturing_clerks_list";
	}
	
	public String load() {
		this.dropDownList();
		if(id!=null) {
			manufacturingClerks = this.manufacturingClerksService.findById(id);
		}
		if(manufacturingClerks==null) {
			manufacturingClerks = new ManufacturingClerks();
			manufacturingClerks.setCreatedBy(SessionUtil.getUserId());
		} 
		manufacturingClerks.setModifyDate(new Date());
		manufacturingClerks.setModifiedBy(SessionUtil.getUserId());
		manufacturingClerks.setModifyName(SessionUtil.getUserName());
		return "manufacturing_clerks_detail";
	}
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.manufacturingClerksService.saveManufacturingClerks(manufacturingClerks);
			rt.put("message", "Save success!");
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
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.manufacturingClerksService.deleteManufacturingClerks(allChoiceVal);
			rt.put("message", "Delete success!");
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
	 * 由workCenterId获取workgroup列表
	 * @return
	 */
	public String changeWorkCenter() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			if(workCenterId!=null&&!"".equals(workCenterId)) {
				workGroupList = this.manufacturingClerksService.findByCenterId(Integer.parseInt(workCenterId));
			} else {
				workGroupList = this.manufacturingClerksService.findAllWorkGroup();
			}
			rt.put("workGroupList", workGroupList);
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
	 * 由workCenterId获取workCenterSupervisor和workgroup列表
	 */
	public String workCenterSel() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			//System.out.println(workCenterId);
			if(workCenterId!=null&&!"".equals(workCenterId)) {
				workCenterSupervisor = this.manufacturingClerksService.getSupervisorByCenterId(Integer.parseInt(workCenterId));
				workGroupList = this.manufacturingClerksService.findByCenterId(Integer.parseInt(workCenterId));
			} else {
				workGroupList = this.manufacturingClerksService.findAllWorkGroup();
			}
			rt.put("workCenterSupervisor", workCenterSupervisor);
			rt.put("workGroupList", workGroupList);
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
	 * 由workGroupId获取workGroupSupervisor
	 */
	public String workGroupSel() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			if(workGroupId!=null&&!"".equals(workGroupId)) {
				workGroupSupervisor = this.manufacturingClerksService.getSupervisorByGroupId(Integer.parseInt(workGroupId));
			}
			rt.put("workGroupSupervisor", workGroupSupervisor);
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
	 * 选择人员
	 */
	public String selectUser() {
		usersPage = this.manufacturingClerksService.searchUserPage(usersPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				usersPage);
		return "clerk_users";
	}
	/************************************private method******************************************/
	/**
	 * 给页面下拉列表框赋值
	 */
	private void dropDownList () {
		workCenterList = this.manufacturingClerksService.findAllWorkCenter();
		workGroupList = this.manufacturingClerksService.findAllWorkGroup();
	}
	
	/*************************************getter setter*******************************************/
	public Page<ManufacturingClerksBean> getManClerksPage() {
		return manClerksPage;
	}
	public void setManClerksPage(Page<ManufacturingClerksBean> manClerksPage) {
		this.manClerksPage = manClerksPage;
	}
	public List<WorkGroup> getWorkGroupList() {
		return workGroupList;
	}
	public void setWorkGroupList(List<WorkGroup> workGroupList) {
		this.workGroupList = workGroupList;
	}
	public List<WorkCenter> getWorkCenterList() {
		return workCenterList;
	}
	public void setWorkCenterList(List<WorkCenter> workCenterList) {
		this.workCenterList = workCenterList;
	}
	public ManufacturingClerks getManufacturingClerks() {
		return manufacturingClerks;
	}
	public void setManufacturingClerks(ManufacturingClerks manufacturingClerks) {
		this.manufacturingClerks = manufacturingClerks;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWorkCenterId() {
		return workCenterId;
	}
	public void setWorkCenterId(String workCenterId) {
		this.workCenterId = workCenterId;
	}
	public String getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}
	public String getWorkCenterSupervisor() {
		return workCenterSupervisor;
	}
	public void setWorkCenterSupervisor(String workCenterSupervisor) {
		this.workCenterSupervisor = workCenterSupervisor;
	}
	public String getWorkGroupSupervisor() {
		return workGroupSupervisor;
	}
	public void setWorkGroupSupervisor(String workGroupSupervisor) {
		this.workGroupSupervisor = workGroupSupervisor;
	}
	public Page<User> getUsersPage() {
		return usersPage;
	}
	public void setUsersPage(Page<User> usersPage) {
		this.usersPage = usersPage;
	}
	public String getAllChoiceVal() {
		return allChoiceVal;
	}
	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}

}
