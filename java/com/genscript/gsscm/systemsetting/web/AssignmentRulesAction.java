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
import com.genscript.gsscm.customer.dto.SalesTerritoryAssignRulesDTO;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignProcess;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignRules;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.systemsetting.service.AssignmentRulesService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Description:Assignment rules control class
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date:   2011-02-10
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Results({
	@Result(name = "assignment_rules", location = "systemsetting/SalesTerritories/assignment_rules.jsp"),
	@Result(name = "assignment_rules_list", location = "systemsetting/SalesTerritories/assignment_rules_list.jsp"),
	@Result(name = "process_list", location="systemsetting/SalesTerritories/process_list.jsp"),
	@Result(name = "assignment_rules_detail", location = "systemsetting/SalesTerritories/assignment_rules_detail.jsp"),
	@Result(name = "process_detail", location = "systemsetting/SalesTerritories/process_detail.jsp"),
	@Result(name = "process_select", location = "systemsetting/SalesTerritories/process_select.jsp")
	
})
public class AssignmentRulesAction extends ActionSupport{
	private static final long serialVersionUID = 6759383089049077356L;

	/**自动装载实例**/
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private AssignmentRulesService assignmentRulesService;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private PublicService publicService;
	
	/**action变量**/
	private Integer ruleId;
	private String sequence;
	private String sessionId;
	private String processSessionId;
	private String operation_method;
	private String isSalesManager;
	private String allChoiceVal;//所有被选中的
	private SalesTerritoryAssignRules salesTerritoryAssignRules;
	private SalesTerritoryAssignRulesDTO salesTerritoryAssignRulesDTO;
	private SalesTerritoryAssignProcess salesTerritoryAssignProcess;
	private Page<SalesTerritoryAssignRules> salesTerritoryAssignRulesPage;
	private Page<SalesTerritoryAssignProcess> processPage;
	private Map<String,SalesTerritoryAssignProcess> assignProcessMap;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;//Process name

	/*******************action method*******************************/
	public String input() {
		return "assignment_rules";
	}
	
	public String list() {
		salesTerritoryAssignRulesPage = this.assignmentRulesService.searchSalesTerritoryAssignRulesPage(salesTerritoryAssignRulesPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				salesTerritoryAssignRulesPage);
		return "assignment_rules_list";
	}
	public String load() {
		try {
//			//判断当前用户是否含有销售经理角色(超级管理员拥有所有权限)
//			boolean salesManager = privilegeService.checkIsSalesManagerRole();
//			if (salesManager) {
//				isSalesManager = "true";
//			} else {
//				isSalesManager = "false";
//			}
			if(ruleId!=null) {
				// 判断将要修改的单号是否正在被操作
				String editUrl = "systemsetting/assignment_rules!load.action?ruleId=" + ruleId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
			}
			if (Struts2Util.getParameter("referURL") != null
					&& Struts2Util.getParameter("referURL").equals("select")) {
				salesTerritoryAssignRulesDTO = (SalesTerritoryAssignRulesDTO)SessionUtil.getRow(SessionConstant.AssignmentRules.value(), sessionId);
				assignProcessMap = (Map<String,SalesTerritoryAssignProcess>)SessionUtil.getRow(SessionConstant.AssignProcessList.value(), sessionId);
			} else {
				if(ruleId!=null) {
					salesTerritoryAssignRulesDTO = this.assignmentRulesService.findById(ruleId);
					this.sessionId = String.valueOf(ruleId);
					
				} else {
					this.sessionId = SessionUtil.generateTempId();
					salesTerritoryAssignRulesDTO = new SalesTerritoryAssignRulesDTO();
				}
				salesTerritoryAssignRulesDTO.setModifiedBy(SessionUtil.getUserId());
				salesTerritoryAssignRulesDTO.setModifiedName(SessionUtil.getUserName());
				salesTerritoryAssignRulesDTO.setModifyDate(new Date());
				// 建新的session
				SessionUtil.insertRow(SessionConstant.AssignmentRules.value(), sessionId,
						salesTerritoryAssignRulesDTO);
				assignProcessMap = SessionUtil.convertList2Map(salesTerritoryAssignRulesDTO.getAssignProcessList(),
				"sequence");
				SessionUtil.insertRow(SessionConstant.AssignProcessList.value(),
				sessionId, assignProcessMap);
			}
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		} catch(Exception e) {
			e.printStackTrace();
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		}
		return "assignment_rules_detail";
	}
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (sessionId != null && !("").equals(sessionId)) {
				String editUrl = "systemsetting/assignment_rules!load.action?ruleId=" + sessionId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message",
							"Failed to save the assignment rule ,the assignment rule is editing by "+operation_method);
					rt.put("no", sessionId);
					Struts2Util.renderJson(rt);
					return null;
				}
				assignProcessMap = (Map<String,SalesTerritoryAssignProcess>)SessionUtil.getRow(SessionConstant.AssignProcessList.value(), sessionId); 
				List<SalesTerritoryAssignProcess> assignProcessList = SessionUtil.convertMap2List(assignProcessMap);
				salesTerritoryAssignRulesDTO.setAssignProcessList(assignProcessList);
				this.assignmentRulesService.saveAssignRules(salesTerritoryAssignRulesDTO,sessionId);
				rt.put("message", "Save assgignment rules sucessfully.");
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
		return NONE;
	}
	
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.assignmentRulesService.deleteAssignRules(allChoiceVal);
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
	
	public String listProcess() {
		assignProcessMap = (Map<String,SalesTerritoryAssignProcess>)SessionUtil.getRow(SessionConstant.AssignProcessList.value(), sessionId);
		return "process_list";
	}
	
	public String loadProcess() {
		try {
			if(StringUtils.isNotEmpty(processSessionId)) {
				Map<String,SalesTerritoryAssignProcess> assignProcessMap = (Map<String,SalesTerritoryAssignProcess>)SessionUtil.getRow(SessionConstant.AssignProcessList.value(), sessionId);
				salesTerritoryAssignProcess = assignProcessMap.get(processSessionId);
			} else {
				processSessionId = SessionUtil.generateTempId();
				salesTerritoryAssignProcess = new SalesTerritoryAssignProcess();
			}
			salesTerritoryAssignProcess.setModifiedBy(SessionUtil.getUserId());
			salesTerritoryAssignProcess.setModifiedName(SessionUtil.getUserName());
			salesTerritoryAssignProcess.setModifyDate(new Date());
			List<DropDownListName> listName = new ArrayList<DropDownListName>();
			listName.add(DropDownListName.PROCESS_NAME);
			dropDownMap = publicService.getDropDownMap(listName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "process_detail";
	}
	
	public String saveProcess() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Map<String,SalesTerritoryAssignProcess> assignProcessMap = (Map<String,SalesTerritoryAssignProcess>)SessionUtil.getRow(SessionConstant.AssignProcessList.value(), sessionId);
			if(StringUtils.isNotEmpty(sequence)&&StringUtil.isNumeric(sequence)) {
				salesTerritoryAssignProcess = assignmentRulesService.findProcessById(Integer.parseInt(sequence));
				assignProcessMap.put(sequence, salesTerritoryAssignProcess);
			}
			SessionUtil.updateRow(SessionConstant.AssignProcessList.value(), sessionId, assignProcessMap);
			rt.put("message", "Save assgignment process sucessfully.");
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
	public String deleteProcess() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.assignmentRulesService.deleteAssignProcess(allChoiceVal,sessionId);
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
		SessionUtil.updateRow(SessionConstant.AssignmentRules.value(), sessionId,
				salesTerritoryAssignRulesDTO);
		Struts2Util.renderText("success");
		return null;
	}

	/**
	 * 选择Process
	 */
	public String selectProcess() {
		processPage = assignmentRulesService.searchProcessPage(processPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				processPage);
		return "process_select";
	}
	
	
	/****************getter setter*************************************/
	
	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getAllChoiceVal() {
		return allChoiceVal;
	}

	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}

	public SalesTerritoryAssignRules getSalesTerritoryAssignRules() {
		return salesTerritoryAssignRules;
	}

	public void setSalesTerritoryAssignRules(
			SalesTerritoryAssignRules salesTerritoryAssignRules) {
		this.salesTerritoryAssignRules = salesTerritoryAssignRules;
	}

	public Page<SalesTerritoryAssignRules> getSalesTerritoryAssignRulesPage() {
		return salesTerritoryAssignRulesPage;
	}

	public void setSalesTerritoryAssignRulesPage(
			Page<SalesTerritoryAssignRules> salesTerritoryAssignRulesPage) {
		this.salesTerritoryAssignRulesPage = salesTerritoryAssignRulesPage;
	}


	public Map<String, SalesTerritoryAssignProcess> getAssignProcessMap() {
		return assignProcessMap;
	}

	public void setAssignProcessMap(
			Map<String, SalesTerritoryAssignProcess> assignProcessMap) {
		this.assignProcessMap = assignProcessMap;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public String getIsSalesManager() {
		return isSalesManager;
	}

	public void setIsSalesManager(String isSalesManager) {
		this.isSalesManager = isSalesManager;
	}

	public SalesTerritoryAssignRulesDTO getSalesTerritoryAssignRulesDTO() {
		return salesTerritoryAssignRulesDTO;
	}

	public void setSalesTerritoryAssignRulesDTO(
			SalesTerritoryAssignRulesDTO salesTerritoryAssignRulesDTO) {
		this.salesTerritoryAssignRulesDTO = salesTerritoryAssignRulesDTO;
	}

	public SalesTerritoryAssignProcess getSalesTerritoryAssignProcess() {
		return salesTerritoryAssignProcess;
	}

	public void setSalesTerritoryAssignProcess(
			SalesTerritoryAssignProcess salesTerritoryAssignProcess) {
		this.salesTerritoryAssignProcess = salesTerritoryAssignProcess;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getProcessSessionId() {
		return processSessionId;
	}

	public void setProcessSessionId(String processSessionId) {
		this.processSessionId = processSessionId;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Page<SalesTerritoryAssignProcess> getProcessPage() {
		return processPage;
	}

	public void setProcessPage(Page<SalesTerritoryAssignProcess> processPage) {
		this.processPage = processPage;
	}
	
}
