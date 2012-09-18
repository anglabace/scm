package com.genscript.gsscm.systemsetting.web;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.privilege.entity.EmailGroup;
import com.genscript.gsscm.systemsetting.dto.EmailGroupDTO;
import com.genscript.gsscm.systemsetting.service.EmailGroupService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 6/21/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
@Results({
                @Result(name="email_group_list", location = "systemsetting/emailGroup/email_group_list.jsp"),
                @Result(name="email_group_search", location = "systemsetting/emailGroup/email_group_search.jsp"),
                @Result(name="email_group_input", location = "systemsetting/emailGroup/email_group_input.jsp")
        })
public class EmailGroupAction extends ActionSupport {

    @Autowired
    private EmailGroupService emailGroupService;
    @Autowired
	private PublicService publicService;
    @Autowired
	private ExceptionService exceptionUtil;
    @Autowired
    private DozerBeanMapper dozer;
    private Page<EmailGroup> emailGroupPage;
    private List<DropDownDTO> mailGroupDropdownList;
    private String emaliGroupFun;
    private String groupId;
    private EmailGroupDTO emailGroupDTO;
    private String allChoiceVal;

    /**
	 * 查询页
	 */
	public String search() {
		this.dropDownList();
		return "email_group_search";
	}

    /**
	 * 列表页
	 * @return
	 */
	public String list() {
		emailGroupPage = this.emailGroupService.searchEmailGroupPage(emailGroupPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",emailGroupPage);
		return "email_group_list";
	}

    public String input() {
		this.dropDownList();
		if(groupId!=null && !"".equals(groupId.trim())) {
			EmailGroup emailGroup = this.emailGroupService.findById(Integer.parseInt(groupId.trim()));
            emailGroupDTO = this.dozer.map(emailGroup, EmailGroupDTO.class);
            emailGroupDTO.setModifiedByName(SessionUtil.getUserName());
		}
		if(emailGroupDTO==null) {
            emailGroupDTO = new EmailGroupDTO();
			emailGroupDTO.setCreatedBy(SessionUtil.getUserId());
			emailGroupDTO.setModifiedBy(SessionUtil.getUserId());
			emailGroupDTO.setModifiedByName(SessionUtil.getUserName());
            Calendar cal = Calendar.getInstance();
            emailGroupDTO.setModifyDate(cal.getTime());
            emailGroupDTO.setCreationDate(cal.getTime());
		}
//		manufacturingClerks.setModifyDate(new Date());
//		manufacturingClerks.setModifiedBy(SessionUtil.getUserId());
//		manufacturingClerks.setModifyName(SessionUtil.getUserName());
		return "email_group_input";
	}
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.emailGroupService.saveEmailGroup(this.dozer.map(emailGroupDTO, EmailGroup.class));
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
			this.emailGroupService.deleteEmailGroup(allChoiceVal);
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

    //add by zhanghuibin
    public String getEmailGroupList(){
        String groupName = Struts2Util.getRequest().getParameter("groupName");
        EmailGroup emailGroup = this.emailGroupService.getEmailGroupByName(groupName);
        Struts2Util.renderJson(emailGroup);
        return null;
    }

    /**
	 * 给页面下拉列表框赋值
	 */
	private void dropDownList () {
        mailGroupDropdownList  = publicService.getSpecDropDownList(SpecDropDownListName.MAIL_GROUP_FUNCTION);
	}

    public Page<EmailGroup> getEmailGroupPage() {
        return emailGroupPage;
    }

    public void setEmailGroupPage(Page<EmailGroup> emailGroupPage) {
        this.emailGroupPage = emailGroupPage;
    }

    public String getEmaliGroupFun() {
        return emaliGroupFun;
    }

    public void setEmaliGroupFun(String emaliGroupFun) {
        this.emaliGroupFun = emaliGroupFun;
    }

    public String getAllChoiceVal() {
        return allChoiceVal;
    }

    public void setAllChoiceVal(String allChoiceVal) {
        this.allChoiceVal = allChoiceVal;
    }

    public List<DropDownDTO> getMailGroupDropdownList() {
        return mailGroupDropdownList;
    }

    public void setMailGroupDropdownList(List<DropDownDTO> mailGroupDropdownList) {
        this.mailGroupDropdownList = mailGroupDropdownList;
    }

    public EmailGroupDTO getEmailGroupDTO() {
        return emailGroupDTO;
    }

    public void setEmailGroupDTO(EmailGroupDTO emailGroupDTO) {
        this.emailGroupDTO = emailGroupDTO;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
