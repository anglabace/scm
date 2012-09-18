/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genscript.gsscm.serv.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.serv.dto.ServiceIntermediateDTO;
import com.genscript.gsscm.serv.entity.Service;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;
import com.genscript.gsscm.serv.service.ServService;

/**
 * @author jinsite
 */
@Results({
        @Result(name = "list", location = "service/service/breakdownList.jsp"),
        @Result(name = "input", location = "service/service/breakdown_add.jsp")
})
public class BreakdownAction extends BaseAction<ServiceIntermediateDTO> {

    @Autowired
    private ServService servService;
    private String sessionServiceId;
    private Integer psId;
    private String type;
    private Map<String, ServiceIntermediateDTO> breakdownList = new HashMap<String, ServiceIntermediateDTO>();
    private List<Integer> delList = new ArrayList<Integer>();
    private ServiceIntermediateDTO breakdown;
    private List<ServiceIntermediateDTO> intermediateList;
    private List<Service> pStdPrice;
    private Page<com.genscript.gsscm.serv.entity.Service> ppStdPrice;

    /**
     * @return
     * @throws Exception
     */
    @Override
    public String list() throws Exception {
        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
        }
        System.out.println(sessionServiceId+"<<<<<<<<<<<<<<<<<<<<<<,");
        breakdownList = ((Map<String, ServiceIntermediateDTO>) SessionUtil.getRow(SessionPdtServ.BreakdownList.value(), sessionServiceId));
        if (breakdownList == null || breakdownList.isEmpty()) {
            Page<ServiceIntermediate> page = new Page<ServiceIntermediate>();
            page.setPageSize(10000);
            page.setOrderBy("listSeq");
            page.setOrder(Page.ASC);
            Page<ServiceIntermediateDTO> serviceIntermediateList = servService.getServiceIntermediateList(page, psId);
            List<ServiceIntermediateDTO> list = serviceIntermediateList.getResult();// servService.getServiceIntermediateAllList(psId);

            if (list != null && list.size() > 0) {
                this.breakdownList = new HashMap<String, ServiceIntermediateDTO>();
                for (int i = 0; i < list.size(); i++) {
                    ServiceIntermediateDTO temp = list.get(i);
                    breakdownList.put(temp.getId().toString(), temp);
                }
                SessionUtil.insertRow(SessionPdtServ.BreakdownList.value(), sessionServiceId, breakdownList);
            }
        }
        return "list";
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public String input() throws Exception {
        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
        }
        // 获得分页请求相关数据：如第几页
        PagerUtil<com.genscript.gsscm.serv.entity.Service> pagerUtil = new PagerUtil<com.genscript.gsscm.serv.entity.Service>();
        ppStdPrice = pagerUtil.getRequestPage();
        // 设置默认每页显示记录条数
        if (ppStdPrice.getPageSize() == null
                || ppStdPrice.getPageSize().intValue() < 1) {
            ppStdPrice.setPageSize(15);
        }
        List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
        if (!ppStdPrice.isOrderBySetted()) {
            ppStdPrice.setOrderBy("serviceId");
            ppStdPrice.setOrder(Page.DESC);
        }
        ppStdPrice = this.servService.searchServList(ppStdPrice, filters);
        // 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
        PageDTO pagerInfo = pagerUtil.formPage(ppStdPrice);
        ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
        //SessionUtil.deleteRow(SessionPdtServ.BreakdownList.value(), sessionServiceId);
        // SessionUtil.deleteRow(SessionPdtServ.DelBreakdownList.value(), sessionServiceId);
        /*String catNo = Struts2Util.getParameter("catNo");
        catNo = StringUtils.isNotBlank(catNo) && StringUtils.isNotEmpty(catNo) ? catNo.trim() : "";
        String name = Struts2Util.getParameter("name");
        name = StringUtils.isNotBlank(name) && StringUtils.isNotEmpty(name) ? name.trim() : "";
        String noList = Struts2Util.getParameter("noList");
        ServletActionContext.getRequest().setAttribute("noListStr", noList);
        noList = StringUtils.isNotBlank(noList) && StringUtils.isNotEmpty(noList) ? noList.trim() : "";
        List<String> noLists = new ArrayList<String>();
        String[] arrList = noList.split(",");
        noLists.addAll(Arrays.asList(arrList));
        pStdPrice = servService.searchServiceBreakDownList(catNo, name, noLists);*/
        return "input";
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public String save() throws Exception {
        String catStr = Struts2Util.getParameter("catStr");

        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
        }

        breakdownList = ((Map<String, ServiceIntermediateDTO>) SessionUtil.getRow(SessionPdtServ.BreakdownList.value(), sessionServiceId));

        if (StringUtils.isNotBlank(catStr) && StringUtils.isNotEmpty(catStr)) {
            if (breakdownList == null) {
                breakdownList = new HashMap<String, ServiceIntermediateDTO>();
            }
            String[] catStrArr = catStr.split("<==>");
            for (int i = 0; i < catStrArr.length; i++) {
                System.out.println("======catStrArr:" + catStrArr[i]);
                ServiceIntermediateDTO temp = new ServiceIntermediateDTO();
                temp.setListSeq(breakdownList.size() + 1);
                String[] tempArr = catStrArr[i].split("<;>");
                if (StringUtils.isNotBlank(tempArr[0]) && StringUtils.isNotEmpty(tempArr[0])) {
                    temp.setIntmdCatalogNo(tempArr[0]);
                }
                if (StringUtils.isNotBlank(tempArr[1]) && StringUtils.isNotEmpty(tempArr[1])) {
                    temp.setItem(tempArr[1]);
                }
                if (StringUtils.isNotBlank(tempArr[2]) && StringUtils.isNotEmpty(tempArr[2]) && StringUtils.isNumeric(tempArr[2])) {
                    temp.setLeadTime(Integer.parseInt(tempArr[2]));
                }
                if (StringUtils.isNotBlank(tempArr[3]) && StringUtils.isNotEmpty(tempArr[3])) {
                    temp.setSymbol(tempArr[3]);
                }

                if (StringUtils.isNotBlank(tempArr[4]) && StringUtils.isNotEmpty(tempArr[4]) && StringUtils.isNumeric(tempArr[4])) {
                    temp.setPrice(Double.parseDouble(tempArr[4]));
                }
                if (StringUtils.isNotBlank(tempArr[5]) && StringUtils.isNotEmpty(tempArr[5]) && StringUtils.isNumeric(tempArr[5])) {
                    temp.setQuantity(Integer.parseInt(tempArr[5]));
                }
                if (psId != null) {
                    temp.setServiceId(psId);
                }
                breakdownList.put(SessionUtil.generateTempId(), temp);
            }
        }


        SessionUtil.insertRow(SessionPdtServ.BreakdownList.value(), sessionServiceId, breakdownList);

        return null;
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public String delete() throws Exception {
        if (psId != null)
            sessionServiceId = String.valueOf(psId);
        setBreakdownList((Map<String, ServiceIntermediateDTO>) SessionUtil.getRow(SessionPdtServ.BreakdownList.value(), sessionServiceId));
        String delIdStrs = ServletActionContext.getRequest().getParameter("itemId");
        String[] delIdStr = delIdStrs.split(",");
        for (int i = 0; i < delIdStr.length; i++) {
            getBreakdownList().remove(delIdStr[i]);
            if (StringUtils.isNumeric(delIdStr[i])) {
                delList.add(Integer.parseInt(delIdStr[i]));
            }
        }
        SessionUtil.insertRow(SessionPdtServ.DelBreakdownList.value(), sessionServiceId, delList);
        SessionUtil.insertRow(SessionPdtServ.BreakdownList.value(), sessionServiceId, breakdownList);

        return null;

    }

    /**
     *
     * @return
     * @throws Exception
     */
    /**
     * @return
     * @throws Exception
     */
    @Override
    protected void prepareModel() throws Exception {
    }

    /**
     * @return the psId
     */
    public Integer getPsId() {
        return psId;
    }

    /**
     * @param psId the psId to set
     */
    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the breakdown
     */
    public ServiceIntermediateDTO getBreakdown() {
        return breakdown;
    }

    /**
     * @param breakdown the breakdown to set
     */
    public void setBreakdown(ServiceIntermediateDTO breakdown) {
        this.breakdown = breakdown;
    }

    /**
     * @return the intermediateList
     */
    public List<ServiceIntermediateDTO> getIntermediateList() {
        return intermediateList;
    }

    /**
     * @param intermediateList the intermediateList to set
     */
    public void setIntermediateList(List<ServiceIntermediateDTO> intermediateList) {
        this.intermediateList = intermediateList;
    }

    /**
     * @return the breakdownList
     */
    public Map<String, ServiceIntermediateDTO> getBreakdownList() {
        return breakdownList;
    }

    /**
     * @param breakdownList the breakdownList to set
     */
    public void setBreakdownList(Map<String, ServiceIntermediateDTO> breakdownList) {
        this.breakdownList = breakdownList;
    }

    /**
     * @return the sessionServiceId
     */
    public String getSessionServiceId() {
        return sessionServiceId;
    }

    /**
     * @param sessionServiceId the sessionServiceId to set
     */
    public void setSessionServiceId(String sessionServiceId) {
        this.sessionServiceId = sessionServiceId;
    }

    /**
     * @return the pStdPrice
     */
    public List<Service> getPStdPrice() {
        return pStdPrice;
    }

    /**
     * @param pStdPrice the pStdPrice to set
     */
    public void setPStdPrice(List<Service> pStdPrice) {
        this.pStdPrice = pStdPrice;
    }

    public Page<Service> getPpStdPrice() {
        return ppStdPrice;
    }

    public void setPpStdPrice(Page<Service> ppStdPrice) {
        this.ppStdPrice = ppStdPrice;
    }


}
