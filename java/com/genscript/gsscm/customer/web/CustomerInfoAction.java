/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genscript.gsscm.customer.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.constant.Ethnics;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.CustomerPersonalInfo;
import com.genscript.gsscm.customer.service.CustomerService;


/**
 *
 * @author jinsite
 */
@SuppressWarnings("rawtypes")
@Results({
    @Result(name = "show", location = "customer/customer_personal_info.jsp")
})
public class CustomerInfoAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8726153888913850492L;
	@Autowired
    private CustomerService customerService;
    private int custNo;
    private CustomerPersonalInfo custPersonalInfo;
    private Ethnics[] ethnicsList;
    private String sessCustNo;

    public String show() throws Exception {
         this.setEthnicsList(Ethnics.values());
        if (custNo != 0) {
            custPersonalInfo = (CustomerPersonalInfo) SessionUtil.getRow(SessionConstant.PersonalInfo.value(), Integer.toString(custNo));
        } else {
            custPersonalInfo = (CustomerPersonalInfo) SessionUtil.getRow(SessionConstant.PersonalInfo.value(), sessCustNo);
        }
        
        if (custPersonalInfo == null && custNo!=0) {
            CustomerDTO customer_detail = customerService.getCustomerDetail(custNo);
            custPersonalInfo = customer_detail.getPersonal();
        }
        if (custPersonalInfo != null && StringUtils.isNotEmpty(custPersonalInfo.getHobby()) && StringUtils.isNotBlank(custPersonalInfo.getHobby())) {
            ServletActionContext.getRequest().setAttribute("bskt", custPersonalInfo.getHobby().indexOf("bskt"));
            ServletActionContext.getRequest().setAttribute("fish", custPersonalInfo.getHobby().indexOf("fish"));
            ServletActionContext.getRequest().setAttribute("golf", custPersonalInfo.getHobby().indexOf("golf"));
            ServletActionContext.getRequest().setAttribute("tnns", custPersonalInfo.getHobby().indexOf("tnns"));
        } else {
            ServletActionContext.getRequest().setAttribute("bskt", -1);
            ServletActionContext.getRequest().setAttribute("fish", -1);
            ServletActionContext.getRequest().setAttribute("golf", -1);
            ServletActionContext.getRequest().setAttribute("tnns", -1);
        }

       
        return "show";
    }

    @Override
    public String list() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String input() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String save() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String[] hobbies = request.getParameterValues("hobby");
        if (hobbies != null && hobbies.length > 0) {
            custPersonalInfo.setHobby(org.springframework.util.StringUtils.arrayToDelimitedString(hobbies, ";"));
        }
        if (StringUtils.isNotEmpty(ServletActionContext.getRequest().getParameter("birthDate")) && StringUtils.isNotBlank(ServletActionContext.getRequest().getParameter("birthDate"))) {
            custPersonalInfo.setBirthDate(DateUtils.formatStr2Date(ServletActionContext.getRequest().getParameter("birthDate"), "yyyy-MM-dd"));
        }
        SessionUtil.insertRow(SessionConstant.PersonalInfo.value(), custNo, sessCustNo, custPersonalInfo);
        return NONE;
    }

    @Override
    public String delete() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void prepareModel() throws Exception {

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the cust_no
     */
    public int getCustNo() {
        return custNo;
    }

    /**
     * @param cust_no the cust_no to set
     */
    public void setCustNo(int cust_no) {
        this.custNo = cust_no;
    }

    /**
     * @return the custPersonalInfo
     */
    public CustomerPersonalInfo getCustPersonalInfo() {
        return custPersonalInfo;
    }

    /**
     * @param custPersonalInfo the custPersonalInfo to set
     */
    public void setCustPersonalInfo(CustomerPersonalInfo custPersonalInfo) {
        this.custPersonalInfo = custPersonalInfo;
    }

    /**
     * @return the ethnicsList
     */
    public Ethnics[] getEthnicsList() {
        return ethnicsList;
    }

    /**
     * @param ethnicsList the ethnicsList to set
     */
    public void setEthnicsList(Ethnics[] ethnicsList) {
        this.ethnicsList = ethnicsList;
    }

    /**
     * @return the sessCustNo
     */
    public String getSessCustNo() {
        return sessCustNo;
    }

    /**
     * @param sessCustNo the sessCustNo to set
     */
    public void setSessCustNo(String sessCustNo) {
        this.sessCustNo = sessCustNo;
    }
}
