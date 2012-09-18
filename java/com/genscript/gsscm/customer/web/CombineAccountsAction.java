package com.genscript.gsscm.customer.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.entity.CustomerBean;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results(
        {
                @Result(name = "fromCustNos", location = "customer/fromCustNo.jsp"),
                @Result(name = "toCustNos", location = "customer/toCustNo.jsp"),
                @Result(name = "listCustNos", location = "customer/customer_combine_new.jsp"),
                @Result(location = "customer/customer_combine.jsp")
        }
)
public class CombineAccountsAction extends ActionSupport {

    private static final long serialVersionUID = 5364625100507527613L;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ExceptionService exceptionUtil;

    private Page<CustomerBean> pageCustomerBean;
    private Integer custNo;
    private String firstName;
    private String lastName;
    private String busEmail;
    private String organizationName;
    private  String status;

    /**
     * 通过过滤器查询Customer，若页面有输入查询条件则根据条件查询，若没有则查询所有Customer
     *
     * @return customer/customer_combine.jsp
     * @throws Exception
     */
    public String searCustomerList() {
        try {
            PagerUtil<CustomerBean> pagerUtil = new PagerUtil<CustomerBean>();
            pageCustomerBean = pagerUtil.getRequestPage();
            if (!pageCustomerBean.isOrderBySetted()) {
                pageCustomerBean.setOrderBy("custNo");
                pageCustomerBean.setOrder(Page.DESC);
            }
            pageCustomerBean.setPageSize(10);
            List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
            if (filters == null) {
                // 无查询条件执行此句
                pageCustomerBean = customerService.searchCustomer(pageCustomerBean);
            } else {
                // 有查询条件执行此句
                String custNo = ServletActionContext.getRequest().getParameter("filter_EQI_custNo");
                if (StringUtils.isNotEmpty(custNo) && StringUtil.isNumeric(custNo)) {
                    this.custNo = Integer.parseInt(custNo);
                }
                firstName = ServletActionContext.getRequest().getParameter("filter_LIKES_firstName");
                lastName = ServletActionContext.getRequest().getParameter("filter_LIKES_lastName");
                busEmail = ServletActionContext.getRequest().getParameter("filter_LIKES_busEmail");
                organizationName = ServletActionContext.getRequest().getParameter("filter_LIKES_organizationName");
                pageCustomerBean = customerService.searchCustomer(pageCustomerBean,
                        filters);
            }
            ServletActionContext.getRequest().setAttribute("pagerInfo", pageCustomerBean);
            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String listCustNos() {
        try {
            PagerUtil<CustomerBean> pagerUtil = new PagerUtil<CustomerBean>();
            pageCustomerBean = pagerUtil.getRequestPage();
            if (!pageCustomerBean.isOrderBySetted()) {
                pageCustomerBean.setOrderBy("custNo");
                pageCustomerBean.setOrder(Page.DESC);
            }
            pageCustomerBean.setPageSize(10);
            List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
            if (filters == null) {
                // 无查询条件执行此句
                System.out.println(">>>>>>>>>>>>>>>>11111>>>>>>>>>>>");
                pageCustomerBean = customerService.searchCustomer(pageCustomerBean);
            } else {
                System.out.println(">>>>>>>>>>>>>>>>222222>>>>>>>>>>>");
                // 有查询条件执行此句
                String custNo = ServletActionContext.getRequest().getParameter("filter_EQI_custNo");
                  status=ServletActionContext.getRequest().getParameter("filter_EQS_status") ;
                if (StringUtils.isNotEmpty(custNo) && StringUtil.isNumeric(custNo)) {
                    this.custNo = Integer.parseInt(custNo);
                }
                firstName = ServletActionContext.getRequest().getParameter("filter_LIKES_firstName");
                lastName = ServletActionContext.getRequest().getParameter("filter_LIKES_lastName");
                busEmail = ServletActionContext.getRequest().getParameter("filter_LIKES_busEmail");
                organizationName = ServletActionContext.getRequest().getParameter("filter_LIKES_organizationName");
                pageCustomerBean = customerService.searchCustomer(pageCustomerBean,
                        filters);
            }
            ServletActionContext.getRequest().setAttribute("pagerInfo", pageCustomerBean);
            return "listCustNos";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String toCustNos() {
        return "toCustNos";
    }

    public String fromCustNo() {
        return "fromCustNos";
    }

    /**
     * 接收合并的customerID和userId,合并Customer
     *
     * @return 把信息封装成JSON对象返回
     * @author zhangyang
     */
    public String combineCustomer() {
        Map<String, Object> rt = new HashMap<String, Object>();
        Integer userId = SessionUtil.getUserId();
        try {
            //获取customerId
            Integer CustNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("toCustNo"));
            String fromCustNo = ServletActionContext.getRequest().getParameter("fromCustNo");
            //解析页面合并的ID，存入List
            List<Integer> slaveNoList = resolveNumberStr(fromCustNo);
            customerService.combineCustomer(CustNo, slaveNoList, userId);
            rt.put("message", "combine success");
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

    public List<Integer> resolveNumberStr(String str) {
        List<Integer> slaveNoList = new ArrayList<Integer>();
        String str1 = "";
        String tempStr = "";
        // 把字符串里的数字解析出来
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                str1 = String.valueOf(c);
                tempStr = tempStr + str1;
                continue;
            } else {
                if (tempStr != "") {
                    slaveNoList.add(Integer.parseInt(tempStr.trim()));
                    str1 = "";
                    tempStr = "";
                }

            }
        }
        if (tempStr != "")
            slaveNoList.add(Integer.parseInt(tempStr.trim()));
        return slaveNoList;
    }


    public CustomerService getCustomerService() {
        return customerService;
    }

    public Page<CustomerBean> getPageCustomerBean() {
        return pageCustomerBean;
    }

    public void setPageCustomerBean(Page<CustomerBean> pageCustomerBean) {
        this.pageCustomerBean = pageCustomerBean;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBusEmail() {
        return busEmail;
    }

    public void setBusEmail(String busEmail) {
        this.busEmail = busEmail;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getCustNo() {
        return custNo;
    }

    public void setCustNo(Integer custNo) {
        this.custNo = custNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
