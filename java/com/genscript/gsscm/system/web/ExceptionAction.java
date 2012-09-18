/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.system.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.system.dto.MessageDTO;
import com.genscript.gsscm.system.entity.MessageLogBean;
import com.genscript.gsscm.system.service.MessageService;

/**
 *
 * @author jinsite
 */
@Results({
    @Result(name="form",location="system/exception_srch.jsp"),
    @Result(name="list",location="system/exception_list.jsp")
})
public class ExceptionAction extends BaseAction<MessageDTO> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1411111399237662001L;
	@Autowired
    private MessageService messageService;
    private Page<MessageLogBean> messageLogPage;
    private String excode;
    private String exname;
    private String iname;
    private String severity;
    private String startdate;
    private String enddate;
    @Override
    public String execute()
    {
        System.out.println("Test");
        return "form";
    }
    @Override
    public String list() throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        if (StringUtils.isNotBlank(excode) && StringUtils.isNotEmpty(excode)) {
            map.put("EQS_code", excode);
        }
        if (StringUtils.isNotBlank(exname) && StringUtils.isNotEmpty(exname)) {
            map.put("LIKES_description", exname);
        }
        if (StringUtils.isNotBlank(iname) && StringUtils.isNotEmpty(iname)) {
            map.put("LIKES_interfaceName", iname);
        }
        if (StringUtils.isNotBlank(severity) && StringUtils.isNotEmpty(severity)) {
            map.put("EQS_severity", severity);
        }
        if (StringUtils.isNotBlank(startdate) && StringUtils.isNotEmpty(startdate)) {
            map.put("GED_logDate", startdate);
        }
        if (StringUtils.isNotBlank(enddate) && StringUtils.isNotEmpty(enddate)) {
            map.put("LED_logDate", enddate + " 23:59:59");
        }
        messageLogPage=(new Page<MessageLogBean>(20));
        if (!messageLogPage.isOrderBySetted()) {
        	messageLogPage.setOrderBy("logDate");
        	messageLogPage.setOrder(Page.DESC);
		}
        if(StringUtils.isNotEmpty(ServletActionContext.getRequest().getParameter("p_no"))&&StringUtils.isNotBlank(ServletActionContext.getRequest().getParameter("p_no")))
             messageLogPage.setPageNo(Integer.parseInt(ServletActionContext.getRequest().getParameter("p_no")));
        messageLogPage=(messageService.searchMessageLog(getMessageLogPage(), map));
        ServletActionContext.getRequest().setAttribute("pagerInfo", messageLogPage);
        return "list";
    }

    @Override
    public String input() throws Exception {
        return NONE;
    }

    @Override
    public String save() throws Exception {
        return NONE;
    }

    @Override
    public String delete() throws Exception {
      return NONE;
    }

    @Override
    protected void prepareModel() throws Exception {
        
    }

    /**
     * @return the name
     */
    public String getExcode() {
        return excode;
    }

    /**
     * @param name the name to set
     */
    public void setExcode(String excode) {
        this.excode = excode;
    }

    /**
     * @return the exname
     */
    public String getExname() {
        return exname;
    }

    /**
     * @param exname the exname to set
     */
    public void setExname(String exname) {
        this.exname = exname;
    }

    /**
     * @return the iname
     */
    public String getIname() {
        return iname;
    }

    /**
     * @param iname the iname to set
     */
    public void setIname(String iname) {
        this.iname = iname;
    }

    /**
     * @return the severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * @param severity the severity to set
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * @return the startdate
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * @return the enddate
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * @param enddate the enddate to set
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     * @return the messageLogPage
     */
    public Page<MessageLogBean> getMessageLogPage() {
        return messageLogPage;
    }

  

}
