package com.genscript.gsscm.system.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.system.dto.MailAddressDTO;
import com.genscript.gsscm.system.dto.MailGroupDTO;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.genscript.gsscm.system.entity.MailAddress;
import com.genscript.gsscm.system.entity.MailGroup;
import com.genscript.gsscm.system.service.MailGroupService;
import com.genscript.gsscm.ws.WSException;

/**
*
* @author mingrs
*/
@Results({
   @Result(name="success",location="system/mail_group_list.jsp"),
   @Result(name="edit",location="system/mail_group_creetion_from.jsp"),
   @Result(name="addressList",location="system/mail_address_list.jsp"),
   @Result(name="addMailAddress",location="system/add_mail_address.jsp")
   
})
public class MailGroupAction extends BaseAction<MailGroupDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7845412329622293855L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private MailGroupService mailGroupService;
	
	private MailGroupDTO entity;
	private MailGroup mailGroup;
	private Page<MailAddress> mailAddressPage;
	private List<MailAddress> mailAddressList;
	private MailAddressDTO mailAddress;
	private List<DepartmentSystem> department;
	private Integer id;
	
	private Page<MailGroup> page;
	
	
	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Integer id) {
		/**
		 * id是prepareModel()二次绑定的参数;
		 */
		this.id = id;
	}
	
	public MailGroupDTO getModel() {
		/**
		 * 向跳转页面绑定catalogDTO类型的输出数据。
		 */
		return entity;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		/**
		 * 对请求进行二次绑定，获取对应的catalogDTO数据;
		 */
		if (id != null) {
			entity = this.mailGroupService.getMailGroupById(id);
		} else {
			entity = new MailGroupDTO();
		}
	}
	
	@Override
	public String delete() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String dataStr = ServletActionContext.getRequest().getParameter("delStr");
			if(dataStr!=null){
				List<String> idList = Arrays.asList(dataStr.split(","));
				this.mailGroupService.delMailGroup(idList);
				rt.put("message",SUCCESS);
			}else{
				rt.put("message","This date is null.");
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0203",
					SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);			
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	@Override
	public String input() throws Exception {
		return "edit";
	}
	
	public String mailAddressAdd(){
		String mailId = Struts2Util. getParameter("mailId");
		if(mailId!=null&&!mailId.equals("")){
			this.mailAddress = this.mailGroupService.getMailAddressById(Integer.valueOf(mailId));
		}else{
			this.mailAddress = new MailAddressDTO();
		}
		department = this.mailGroupService.getDepartmentList();
		return "addMailAddress";
	}
	/*
	 * 判断新增的user是否已经存在。
	 */
	public String checkUser(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String userId = Struts2Util.getParameter("userId");
		String groupId = Struts2Util.getParameter("groupId");
		if(userId!=null&&!userId.equals("")&&groupId!=null&&!groupId.equals("")){
			Long count = this.mailGroupService.getMailAddressCountByUserIdGroupId(Integer.valueOf(userId),Integer.valueOf(groupId));
			if(count>0){
				rt.put("message", ERROR);
			}else{
				rt.put("message", SUCCESS);
			}
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String findAddressList(){
		// 获得分页请求相关数据：如第几页
/*		PagerUtil<MailAddress> pagerUtil = new PagerUtil<MailAddress>();
		mailAddressPage = pagerUtil.getRequestPage();
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		// 设置默认每页显示记录条数
		if (mailAddressPage.getPageSize() == null
				|| mailAddressPage.getPageSize().intValue() < 1) {
			mailAddressPage.setPageSize(20);
		}
		if (!mailAddressPage.isOrderBySetted()) {
			mailAddressPage.setOrderBy("mailId");
			mailAddressPage.setOrder(Page.DESC);
		}*/
		String groupId = Struts2Util.getParameter("groupId");
		if(groupId!=null&&!groupId.equals("groupId")){
			this.mailAddressList = this.mailGroupService.searchMailAddress(Integer.valueOf(groupId));
		}else{
			this.mailAddressList = new ArrayList<MailAddress>();
		}
		department = this.mailGroupService.getDepartmentList();
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
	/*	PageDTO pagerInfo = pagerUtil.formPage(mailAddressPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);*/
		return "addressList";
	}
	
	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<MailGroup> pagerUtil = new PagerUtil<MailGroup>();
		page = pagerUtil.getRequestPage();
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null
				|| page.getPageSize().intValue() < 1) {
			page.setPageSize(20);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("groupId");
			page.setOrder(Page.DESC);
		}
		page = this.mailGroupService.searchMailGroup(page, filters);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return SUCCESS;
	}


	@Override
	public String save() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		Integer loginUserId = SessionUtil.getUserId();
		List<Integer> addList = new ArrayList<Integer>();
		List<Integer> delList = new ArrayList<Integer>();
		try{
			MailGroupDTO dto = new MailGroupDTO();
			String addMailAddress = Struts2Util.getParameter("addMailAddress");
			if(addMailAddress!=null&&!addMailAddress.equals("")){
				String[] addMailAddresss = addMailAddress.split("<;>");
				
				for(int i=0;i<addMailAddresss.length;i++){
					if(addMailAddresss[i]!=null&&!addMailAddresss[i].equals("")){
						addList.add(Integer.valueOf(addMailAddresss[i]));
					}
				}
			}
			String delMailAddress = Struts2Util.getParameter("delMailAddress");
			if(delMailAddress!=null&&!delMailAddress.equals("")){
				String[] delMailAddresss = delMailAddress.split(",");
				
				for(int i=0;i<delMailAddresss.length;i++){
					if(delMailAddresss[i]!=null&&!delMailAddresss[i].equals("")){
						delList.add(Integer.valueOf(delMailAddresss[i]));
					}
				}
			}
			dto.setMailGroup(mailGroup);
			
			this.mailGroupService.saveMailGroup(dto, loginUserId,addList,delList);
			rt.put("message", SUCCESS);
			rt.put("id", dto.getMailGroup().getGroupId());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0203",
					SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	public Page<MailGroup> getPage() {
		return page;
	}

	public void setPage(Page<MailGroup> page) {
		this.page = page;
	}

	public MailGroup getMailGroup() {
		return mailGroup;
	}

	public void setMailGroup(MailGroup mailGroup) {
		this.mailGroup = mailGroup;
	}

	public Page<MailAddress> getMailAddressPage() {
		return mailAddressPage;
	}

	public void setMailAddressPage(Page<MailAddress> mailAddressPage) {
		this.mailAddressPage = mailAddressPage;
	}

	public MailAddressDTO getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(MailAddressDTO mailAddress) {
		this.mailAddress = mailAddress;
	}

	public List<DepartmentSystem> getDepartment() {
		return department;
	}

	public void setDepartment(List<DepartmentSystem> department) {
		this.department = department;
	}

	public List<MailAddress> getMailAddressList() {
		return mailAddressList;
	}

	public void setMailAddressList(List<MailAddress> mailAddressList) {
		this.mailAddressList = mailAddressList;
	}

}
