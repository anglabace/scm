package com.genscript.gsscm.systemsetting.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.manufacture.entity.QaClerk;
import com.genscript.gsscm.manufacture.entity.QaGroup;
import com.genscript.gsscm.manufacture.entity.QaGroupAssigned;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.systemsetting.service.QClerksService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Description:qa/qc clerks管理控制类
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date:   2010-12-9
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */

@Results( {
	@Result(name = "q_clerks_search", location = "systemsetting/Manufacturing/q_clerks_search.jsp"),
	@Result(name = "q_clerks_list", location = "systemsetting/Manufacturing/q_clerks_list.jsp"),
	@Result(name = "qa_clerks_list", location = "systemsetting/Manufacturing/qa_clerks_list.jsp"),
	@Result(name="q_clerks_detail",location="systemsetting/Manufacturing/q_clerks_detail.jsp"),
	@Result(name="qa_clerks_detail",location="systemsetting/Manufacturing/qa_clerks_detail.jsp"),
	@Result(name="group_supervisor",location="systemsetting/Manufacturing/group_supervisor.jsp"),
	@Result(name="clerk_users",location="systemsetting/Manufacturing/clerk_users.jsp")
})
public class QClerksAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	/**自动装载实例**/
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private QClerksService qClerksService;
	@Autowired
	private PublicService publicService;
	
	/**action变量**/
	private Page<QaGroup> qaGroupPage;
	private Page<User> usersPage;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;//group function
	private Map<String,String> producatServiceMap;
	private List<QaGroupAssigned> qaGroupAssignedList;
	private Map<String,QaClerk> qaClerkMap;
	private QaGroup qaGroup;
	private Integer groupId;
	private QaClerk qaClerk;
	private String id;
	private String sessionGroupId;
	private String allChoiceVal;
	private String qaGroupAssignedStr;//已分配的产品或服务
	private String assignValue;//前台选择分配的产品或服务
	private String referURL;//判断是否从添加子项后进入修改页面
	private String type;//product or service
	
	/***********************************action method**********************************/
	public String search() {
		this.dropDownList();
		return "q_clerks_search";
	}
	
	public String list() {
		qaGroupPage = this.qClerksService.searchQaGroupPage(qaGroupPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				qaGroupPage);
		return "q_clerks_list";
	}
	
	public String load() {
		this.dropDownList();
		producatServiceMap = this.qClerksService.getProductAndService("product");
		if(referURL!=null&&"select".equals(referURL)) {//子项修改返回
			Map<String, Object> session = ActionContext.getContext()
			.getSession();
			this.qaGroup = (QaGroup) session.get(sessionGroupId);
			session.remove(sessionGroupId);
			qaGroupAssignedList = this.qClerksService.getQaGroupAssignedList(sessionGroupId);
			this.qaGroupAssignedStr();
			
		} else {
			if(groupId!=null) {
				sessionGroupId = String.valueOf(groupId);
				qaGroup = this.qClerksService.findById(groupId);
				qaGroupAssignedList = this.qClerksService.getQaGroupAssignedList(sessionGroupId);
				this.qaGroupAssignedStr();
				
				
			}
			if(qaGroup==null) {
				try {
					sessionGroupId = SessionUtil.generateTempId();
				} catch (Exception e) {
					e.printStackTrace();
				}
				qaGroup = new QaGroup();
				qaGroup.setCreatedBy(SessionUtil.getUserId());
			}
			qaGroup.setModifiedBy(SessionUtil.getUserId());
			qaGroup.setModifyDate(new Date());
			qaGroup.setModifyUser(SessionUtil.getUserName());
		}
		return "q_clerks_detail";
	}
	
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.qClerksService.saveQaGroup(qaGroup,sessionGroupId);
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
			this.qClerksService.deleteQaGroup(allChoiceVal);
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
	 * 获取qaClerks列表
	 */
	@SuppressWarnings("unchecked")
	public String qaClerksList() {
		qaClerkMap = (Map<String,QaClerk>) SessionUtil.getRow(SessionConstant.QACLERKLIST.value(), sessionGroupId);
		if(qaClerkMap==null) {
			if(StringUtil.isNumeric(sessionGroupId)) {
				qaClerkMap = this.qClerksService.getQaClerkMap(Integer.parseInt(sessionGroupId));
			}
		}
		return "qa_clerks_list";
	}
	
	/**
	 * 添加或加载qaClerk对象之前作出的操作
	 */
	public String saveGroupToSession() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(sessionGroupId, this.qaGroup);
		Struts2Util.renderText("Success");
		return null;
	}
	/**
	 * 添加或加载qaClerk对象
	 */
	@SuppressWarnings("unchecked")
	public String loadQaClerk() {
		try {
			qaClerkMap = (Map<String,QaClerk>) SessionUtil.getRow(SessionConstant.QACLERKLIST.value(), sessionGroupId);
			if(id!=null) { 
				this.qaClerk = qaClerkMap.get(id);
				this.qaClerk = qClerksService.getOtherParam(qaClerk);
			} else {
				id = SessionUtil.generateTempId();
				qaClerk = new QaClerk();
				qaClerk.setCreatedBy(SessionUtil.getUserId());
			}
			qaClerk.setModifiedBy(SessionUtil.getUserId());
			qaClerk.setModifyDate(new Date());
			qaClerk.setModifyUser(SessionUtil.getUserName());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "qa_clerks_detail";
	}
	
	/**
	 * 保存qaClerk对象到session
	 */
	public String saveClerk() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.qClerksService.saveClerkToSession(qaClerk, sessionGroupId,id);
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
	
	/**
	 * 删除session中的qaClerk对象
	 */
	public String deleteClerk() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.qClerksService.deleteClerks(allChoiceVal,sessionGroupId);
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
	 * type选择触发事件
	 */
	public String typeChange() {
		Map<String,Object> rt = new HashMap<String,Object>();
		producatServiceMap = this.qClerksService.getProductAndService(type);
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
		if(sessionGroupId!=null) {
			qaGroupAssignedList = (List<QaGroupAssigned>)SessionUtil.getRow(SessionConstant.QaGroupAssigned.value(), sessionGroupId);
			if(qaGroupAssignedList==null) {
				qaGroupAssignedList = new ArrayList<QaGroupAssigned>();
			}
			createQaGroupAssigned();
			this.qaGroupAssignedStr();
		}
		rt.put("qaGroupAssignedStr", qaGroupAssignedStr);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 去除已分配产品和服务
	 */
	@SuppressWarnings("unchecked")
	public String unassign() {
		Map<String,Object> rt = new HashMap<String,Object>();
		if(sessionGroupId!=null) {
			qaGroupAssignedList = (List<QaGroupAssigned>)SessionUtil.getRow(SessionConstant.QaGroupAssigned.value(), sessionGroupId);
			removeQaGroupAssigned();
			this.qaGroupAssignedStr();
		}
		rt.put("qaGroupAssignedStr", qaGroupAssignedStr);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 选择人员
	 */
	public String selectUser() {
		usersPage = this.qClerksService.searchUserPage(usersPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				usersPage);
		return "group_supervisor";
	}
	/***********************************private method**************************************/
	/**
	 * 给页面下拉列表框赋值
	 */
	private void dropDownList() {
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.GROUP_FUNCTION);
		dropDownMap = publicService.getDropDownMap(listName);
	}
	
	/**
	 * 给qaGroupAssignedStr赋值
	 */
	private void qaGroupAssignedStr() {
		StringBuffer qaGroupAssignedStrBuf = new StringBuffer();
		if(qaGroupAssignedList!=null&&qaGroupAssignedList.size()>0) {
			int i=0;
			for(QaGroupAssigned qaGroupAssigned:qaGroupAssignedList) {
				if(i%2==0) {
					if(i==0) {
						qaGroupAssignedStrBuf.append("<tr>");
					} else {
						qaGroupAssignedStrBuf.append("</tr><tr>");
					}
				}
				qaGroupAssignedStrBuf.append("<td><input type=\"checkbox\" value=\"")
									 .append(qaGroupAssigned.getClsId()).append(":")
									 .append(qaGroupAssigned.getItemType()).append(":").append(qaGroupAssigned.getClsName()).append("\" name=\"assigned\"/>")
									 .append(qaGroupAssigned.getClsName()).append("</td>");
				i++;
			}
			
			if (qaGroupAssignedList.size() % 2 == 1) {
				qaGroupAssignedStrBuf.append("<td>&nbsp;</td>");
		    }
			qaGroupAssignedStrBuf.append("</tr>");
		}
		qaGroupAssignedStr = qaGroupAssignedStrBuf.toString();
	}
	
	/**
	 * 构造QaGroupAssigned实例
	 */
	private void createQaGroupAssigned() {
		QaGroupAssigned qaGroupAssigned = null;
		String[] assignValues = assignValue.split(":");
		if(assignValues!=null&&assignValues.length==3) {
			for(QaGroupAssigned qaGroupAssigned1:qaGroupAssignedList) {
				if(qaGroupAssigned1.getItemType().equals(assignValues[1])&&qaGroupAssigned1.getClsId()==Integer.parseInt(assignValues[0])) {
					return;
				}
			}
			qaGroupAssigned = new QaGroupAssigned();
			qaGroupAssigned.setClsId(Integer.parseInt(assignValues[0]));
			qaGroupAssigned.setCreatedBy(SessionUtil.getUserId());
			qaGroupAssigned.setItemType(assignValues[1]);
			qaGroupAssigned.setClsName(assignValues[2]);
			qaGroupAssigned.setModifiedBy(SessionUtil.getUserId());
			qaGroupAssigned.setModifyDate(new Date());
		}
		if(qaGroupAssigned!=null) {
			qaGroupAssignedList.add(qaGroupAssigned);
		}
		SessionUtil.insertRow(SessionConstant.QaGroupAssigned.value(), sessionGroupId, qaGroupAssignedList);
	}
	
	/**
	 * 删除QaGroupAssigned实例
	 */
	private void removeQaGroupAssigned() {
		List<QaGroupAssigned> delList = new ArrayList<QaGroupAssigned>();
		for(QaGroupAssigned qaGroupAssigned1:qaGroupAssignedList) {
			if(assignValue.contains(qaGroupAssigned1.getClsId()+":"+qaGroupAssigned1.getItemType()+":"+qaGroupAssigned1.getClsName())) {
				delList.add(qaGroupAssigned1);
			}
		}
		qaGroupAssignedList.removeAll(delList);
		SessionUtil.insertRow(SessionConstant.QaGroupAssigned.value(), sessionGroupId, qaGroupAssignedList);
	}

	/**********************************getter setter****************************************/
	public Page<QaGroup> getQaGroupPage() {
		return qaGroupPage;
	}

	public void setQaGroupPage(Page<QaGroup> qaGroupPage) {
		this.qaGroupPage = qaGroupPage;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public Map<String, String> getProducatServiceMap() {
		return producatServiceMap;
	}

	public void setProducatServiceMap(Map<String, String> producatServiceMap) {
		this.producatServiceMap = producatServiceMap;
	}

	public QaGroup getQaGroup() {
		return qaGroup;
	}

	public void setQaGroup(QaGroup qaGroup) {
		this.qaGroup = qaGroup;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public List<QaGroupAssigned> getQaGroupAssignedList() {
		return qaGroupAssignedList;
	}

	public void setQaGroupAssignedList(List<QaGroupAssigned> qaGroupAssignedList) {
		this.qaGroupAssignedList = qaGroupAssignedList;
	}

	public String getAllChoiceVal() {
		return allChoiceVal;
	}

	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}

	public Map<String,QaClerk> getQaClerkMap() {
		return qaClerkMap;
	}

	public void setQaClerkMap(Map<String,QaClerk> qaClerkMap) {
		this.qaClerkMap = qaClerkMap;
	}

	public String getQaGroupAssignedStr() {
		return qaGroupAssignedStr;
	}

	public void setQaGroupAssignedStr(String qaGroupAssignedStr) {
		this.qaGroupAssignedStr = qaGroupAssignedStr;
	}

	public String getAssignValue() {
		return assignValue;
	}

	public void setAssignValue(String assignValue) {
		this.assignValue = assignValue;
	}

	public String getSessionGroupId() {
		return sessionGroupId;
	}

	public void setSessionGroupId(String sessionGroupId) {
		this.sessionGroupId = sessionGroupId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QaClerk getQaClerk() {
		return qaClerk;
	}

	public void setQaClerk(QaClerk qaClerk) {
		this.qaClerk = qaClerk;
	}

	public String getReferURL() {
		return referURL;
	}

	public void setReferURL(String referURL) {
		this.referURL = referURL;
	}

	public Page<User> getUsersPage() {
		return usersPage;
	}

	public void setUsersPage(Page<User> usersPage) {
		this.usersPage = usersPage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
	
}
