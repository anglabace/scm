package com.genscript.gsscm.basedata.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dto.PaymentTermDTO;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.customer.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;

@Results({
        @Result(name = "showCreateForm", location = "systemsetting/quoteorder_source_create_form.jsp"),
        @Result(name = "source_list", location = "basedata/source_list.jsp")
})
public class BaseDataAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private PublicService publicService;
    @Autowired
    private CustomerService customerService;

    private String sourceId;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获得所有souce, 并返回列表页面.
     *
     * @return
     */
    ///--------------add by zhougang -----------2011 5 9
    public String getSourceList() {
        Source source = new Source();
        String sourceIds= ServletActionContext.getRequest().getParameter("sourceId");
        if (sourceIds != null && !"".equals(sourceIds)) {
            source = this.publicService.getSourceById(Integer.parseInt(sourceIds));
        }
        ServletActionContext.getRequest().setAttribute("source", source);
        return "source_list";
    }

    public String getSourceById() {
        Source source = null;
        System.out.println(sourceId);
        if (sourceId != null  && !"".equals(sourceId)) {
            source = this.publicService.getSourceById(Integer.parseInt(sourceId));
        }
        ServletActionContext.getRequest().setAttribute("source", source);
        return "showCreateForm";
    }
    //============================

    /**
     * 活得term list ，以json格式返回。
     *
     * @return
     */
    public String getTermList() {
        List<PaymentTermDTO> termList = customerService.getPaymentTermList();
        Struts2Util.renderJson(termList);
        return null;
    }

}
