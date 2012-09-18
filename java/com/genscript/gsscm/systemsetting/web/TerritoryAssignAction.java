package com.genscript.gsscm.systemsetting.web;

import java.util.*;

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
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dto.SalesRepDTO;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.SalesRepBean;
import com.genscript.gsscm.customer.entity.SalesRepBean2;
import com.genscript.gsscm.customer.entity.SalesResourceAssignOrg;
import com.genscript.gsscm.customer.entity.SalesResourceAssignTerritory;
import com.genscript.gsscm.customer.entity.SalesTerritory;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.genscript.gsscm.systemsetting.service.SalesTerritoriesService;
import com.genscript.gsscm.systemsetting.service.TerritoryAssignService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Description:Territory Assignments control class
 * @Copyright: Copyright genscrpit (c)2011
 * @author: lizhang
 * @version: 1.0
 * @date: 2011-02-16
 * <p/>
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 */
@Results({
        @Result(name = "territory_assignments", location = "systemsetting/SalesTerritories/territory_assignments.jsp"),
        @Result(name = "territory_assignments_list", location = "systemsetting/SalesTerritories/territory_assignments_list.jsp"),
        @Result(name = "organization_list", location = "systemsetting/SalesTerritories/organization_list.jsp"),
        @Result(name = "territory_list", location = "systemsetting/SalesTerritories/territory_list.jsp"),
        @Result(name = "territory_assignments_detail", location = "systemsetting/SalesTerritories/territory_assignments_detail.jsp"),
        @Result(name = "organization_detail", location = "systemsetting/SalesTerritories/organization_detail.jsp"),
        @Result(name = "territory_detail", location = "systemsetting/SalesTerritories/territory_detail.jsp"),
        @Result(name = "salesRep_select", location = "systemsetting/SalesTerritories/salesRep_select.jsp"),
        @Result(name = "organization_select", location = "systemsetting/SalesTerritories/organization_select.jsp"),
        @Result(name = "territory_select", location = "systemsetting/SalesTerritories/territory_select.jsp")
})
public class TerritoryAssignAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -2175420103816070128L;
    /**
     * 自动装载实例*
     */
    @Autowired
    private PublicService publicService;
    @Autowired
    private ExceptionService exceptionUtil;
    @Autowired
    private TerritoryAssignService territoryAssignService;
    @Autowired
    private SalesTerritoriesService salesTerritoriesService;

    /**
     * action 变量*
     */
    private Integer salesId;
    private String orgId;
    private String territoryId;
    private String assignId;
    private String sessionId;
    private String orgSessionId;
    private String terrSessionId;
    private String operation_method;
    private String allChoiceVal;
    private Integer defaultTab = 0;
    private SalesRepDTO salesRepDTO;
    private SalesResourceAssignOrg salesResourceAssignOrg;
    private SalesResourceAssignTerritory salesResourceAssignTerritory;
    private List<DepartmentSystem> depList;
    private Page<SalesRepBean> salesRepBeanPage;
    private Page<SalesRepBean2> salesRepPage;
    private Page<Organization> orgPage;
    private Page<SalesTerritory> terrPage;
    private Map<String, List<PbDropdownListOptions>> dropDownMap;//function
    private Map<String, SalesResourceAssignOrg> orgMap;
    private Map<String, SalesResourceAssignTerritory> terrMap;
    private List<Divisions> divisionList;

    /**
     * *********************************action method******************************
     */
    public String input() {
        this.dropDownList();
        return "territory_assignments";
    }

    public String list() {
        salesRepBeanPage = this.territoryAssignService.searchSalesRepBeanPage(salesRepBeanPage);
        ServletActionContext.getRequest().setAttribute("pagerInfo",
                salesRepBeanPage);
        return "territory_assignments_list";
    }

    public String load() {
        this.dropDownList();
        try {
            if (salesId != null) {
                // 判断将要修改的单号是否正在被操作
                String editUrl = "systemsetting/territory_assign!load.action?salesId=" + salesId;
                OrderLockRelease orderLockRelease = new OrderLockRelease();
                String byUser = orderLockRelease.checkOrderStatus(editUrl);
                if (byUser != null) {
                    operation_method = byUser;
                }
            }
            if (Struts2Util.getParameter("referURL") != null
                    && Struts2Util.getParameter("referURL").equals("select")) {
                salesRepDTO = (SalesRepDTO) SessionUtil.getRow(SessionConstant.SalesRep.value(), sessionId);
                orgMap = (Map<String, SalesResourceAssignOrg>) SessionUtil.getRow(SessionConstant.OrgAssignedList.value(), sessionId);
                terrMap = (Map<String, SalesResourceAssignTerritory>) SessionUtil.getRow(SessionConstant.TerrAssignedList.value(), sessionId);
            } else {
                if (salesId != null) {
                    salesRepDTO = this.territoryAssignService.findById(salesId);
                    this.sessionId = String.valueOf(salesId);

                } else {
                    this.sessionId = SessionUtil.generateTempId();
                    salesRepDTO = new SalesRepDTO();
                    salesRepDTO.setGsCoId(1);
                    salesRepDTO.setCreatedBy(SessionUtil.getUserId());
                }
                salesRepDTO.setModifiedBy(SessionUtil.getUserId());
                salesRepDTO.setModifiedName(SessionUtil.getUserName());
                salesRepDTO.setModifyDate(new Date());
                // 建新的session
                SessionUtil.insertRow(SessionConstant.SalesRep.value(), sessionId,
                        salesRepDTO);
                orgMap = SessionUtil.convertList2Map(salesRepDTO.getOrgAssignedList(),
                        "assignId");
                terrMap = SessionUtil.convertList2Map(salesRepDTO.getTerrAssignedList(),
                        "assignId");
                SessionUtil.insertRow(SessionConstant.OrgAssignedList.value(),
                        sessionId, orgMap);
                SessionUtil.insertRow(SessionConstant.TerrAssignedList.value(),
                        sessionId, terrMap);
            }
            OrderLockRelease realeseOrderLock = new OrderLockRelease();
            realeseOrderLock.releaseOrderLock();
        } catch (Exception e) {
            e.printStackTrace();
            OrderLockRelease realeseOrderLock = new OrderLockRelease();
            realeseOrderLock.releaseOrderLock();
        }
        return "territory_assignments_detail";
    }

    public String save() {
        Map<String, Object> rt = new HashMap<String, Object>();

        try {
            // 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
            if (sessionId != null && !("").equals(sessionId)) {
                String editUrl = "systemsetting/territory_assign!load.action?salesId=" + salesId;
                OrderLockRelease orderLockRelease = new OrderLockRelease();
                String byUser = orderLockRelease.checkOrderStatus(editUrl);
                if (byUser != null) {
                    operation_method = byUser;
                    rt.put("message",
                            "Failed to save the sales resource ,the sales resource is editing by " + operation_method);
                    rt.put("no", sessionId);
                    Struts2Util.renderJson(rt);
                    return null;
                }
                rt.put("message", territoryAssignService.saveConnection(sessionId, salesId));
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
        return null;

    }

    public String delete() {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            this.territoryAssignService.deleteConnection(allChoiceVal);
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

    @SuppressWarnings("unchecked")
    public String listOrg() {
        orgMap = (Map<String, SalesResourceAssignOrg>) SessionUtil.getRow(SessionConstant.OrgAssignedList.value(), sessionId);
        return "organization_list";
    }

    public String loadOrg() {
        try {
            if (StringUtils.isNotEmpty(orgSessionId)) {
                Map<String, SalesResourceAssignOrg> orgMap = (Map<String, SalesResourceAssignOrg>) SessionUtil.getRow(SessionConstant.OrgAssignedList.value(), sessionId);
                salesResourceAssignOrg = orgMap.get(orgSessionId);
                if (salesResourceAssignOrg != null && salesResourceAssignOrg.getOrganization() != null) {
                    divisionList = this.territoryAssignService.findByOrg(salesResourceAssignOrg.getOrganization().getOrgId());
                }
            } else {
                orgSessionId = SessionUtil.generateTempId();
                salesResourceAssignOrg = new SalesResourceAssignOrg();
                salesResourceAssignOrg.setCreatedBy(SessionUtil.getUserId());
                salesResourceAssignOrg.setCreationDate(new Date());
            }
            salesResourceAssignOrg = territoryAssignService.overwriteSalesResourceAssignOrg(salesResourceAssignOrg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "organization_detail";
    }

    public String saveOrg() {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            salesResourceAssignOrg = territoryAssignService.overwriteSalesResourceAssignOrg(salesResourceAssignOrg);
            Map<String, SalesResourceAssignOrg> orgMap = (Map<String, SalesResourceAssignOrg>) SessionUtil.getRow(SessionConstant.OrgAssignedList.value(), sessionId);
            orgMap.put(orgSessionId, salesResourceAssignOrg);
            SessionUtil.updateRow(SessionConstant.OrgAssignedList.value(), sessionId, orgMap);
            rt.put("message", "Save the organization assignment successfully.");
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

    public String deleteOrg() {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            this.territoryAssignService.deleteAssignOrg(allChoiceVal, sessionId);
        } catch (Exception ex) {
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

    @SuppressWarnings("unchecked")
    public String listTerr() {
        terrMap = (Map<String, SalesResourceAssignTerritory>) SessionUtil.getRow(SessionConstant.TerrAssignedList.value(), sessionId);
        return "territory_list";
    }

    public String loadTerr() {
        try {
            if (StringUtils.isNotEmpty(terrSessionId)) {
                Map<String, SalesResourceAssignTerritory> terrMap = (Map<String, SalesResourceAssignTerritory>) SessionUtil.getRow(SessionConstant.TerrAssignedList.value(), sessionId);
                salesResourceAssignTerritory = terrMap.get(terrSessionId);
            } else {
                terrSessionId = SessionUtil.generateTempId();
                salesResourceAssignTerritory = new SalesResourceAssignTerritory();
                salesResourceAssignTerritory.setCreatedBy(SessionUtil.getUserId());
                salesResourceAssignTerritory.setCreationDate(new Date());
            }
            salesResourceAssignTerritory = this.territoryAssignService.overwriteSalesResourceAssignTerritory(salesResourceAssignTerritory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "territory_detail";
    }

    public String saveTerr() {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            salesResourceAssignTerritory = this.territoryAssignService.overwriteSalesResourceAssignTerritory(salesResourceAssignTerritory);
            Map<String, SalesResourceAssignTerritory> terrMap = (Map<String, SalesResourceAssignTerritory>) SessionUtil.getRow(SessionConstant.TerrAssignedList.value(), sessionId);
            System.out.println(terrMap.size());
            if (terrMap.size() > 0) {
                Set<Map.Entry<String, SalesResourceAssignTerritory>> set = terrMap.entrySet();
                for (Iterator<Map.Entry<String, SalesResourceAssignTerritory>> it = set.iterator(); it.hasNext();) {
                    Map.Entry<String, SalesResourceAssignTerritory> entry = it.next();
                    if (!entry.getValue().getSalesTerritory().getTerritoryCode().equals(salesResourceAssignTerritory.getSalesTerritory().getTerritoryCode())) {
                        terrMap.put(terrSessionId, salesResourceAssignTerritory);
                        SessionUtil.updateRow(SessionConstant.TerrAssignedList.value(), sessionId, terrMap);
                        rt.put("message", "Save the sales territory assignment successfully.");
                    } else {
                        SessionUtil.updateRow(SessionConstant.TerrAssignedList.value(), sessionId, terrMap);
                        rt.put("message", "Save the sales territory assignment failure.");
                    }
                }
            } else {
                terrMap.put(terrSessionId, salesResourceAssignTerritory);
                SessionUtil.updateRow(SessionConstant.TerrAssignedList.value(), sessionId, terrMap);
                rt.put("message", "Save the sales territory assignment successfully.");
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

    public String deleteTerr() {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            this.territoryAssignService.deleteAssignTerr(allChoiceVal, sessionId);
        } catch (Exception ex) {
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

    /**
     * 选择Sales Resource
     */
    public String selectResource() {
        salesRepPage = territoryAssignService.searchSalesRepBean2Page(salesRepPage);
        ServletActionContext.getRequest().setAttribute("pagerInfo",
                salesRepPage);
        return "salesRep_select";
    }

    /**
     * 选择Sales Resource
     */
    public String selectOrg() {
        orgPage = territoryAssignService.searchOrgPage(orgPage);
        ServletActionContext.getRequest().setAttribute("pagerInfo",
                orgPage);
        return "organization_select";
    }


    /**
     * 选择Sales Resource
     */
    public String selectTerr() {
        terrPage = this.salesTerritoriesService.searchSalesTerritoryPage(terrPage);
        ServletActionContext.getRequest().setAttribute("pagerInfo",
                terrPage);
        return "territory_select";
    }


    public String save2Session() {
        SessionUtil.updateRow(SessionConstant.SalesRep.value(), sessionId,
                salesRepDTO);
        Struts2Util.renderText("Success");
        return null;
    }

    /**
     * 由orgId获取Divisions列表
     */
    public String changeOrg() {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            divisionList = this.territoryAssignService.findByOrg(Integer.parseInt(orgId));
            rt.put("divisionList", divisionList);
        } catch (Exception ex) {
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
    /******************************private method**********************************************/
    /**
     * 给页面下拉列表框赋值
     */
    private void dropDownList() {
        depList = this.territoryAssignService.getAllDep();
        List<DropDownListName> listName = new ArrayList<DropDownListName>();
        listName.add(DropDownListName.SALES_REP_FUNCTION);
        dropDownMap = publicService.getDropDownMap(listName);
    }


    /**
     * *****************************getter setter******************************************
     */
    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
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

    public SalesRepDTO getSalesRepDTO() {
        return salesRepDTO;
    }

    public void setSalesRepDTO(SalesRepDTO salesRepDTO) {
        this.salesRepDTO = salesRepDTO;
    }

    public List<DepartmentSystem> getDepList() {
        return depList;
    }

    public void setDepList(List<DepartmentSystem> depList) {
        this.depList = depList;
    }

    public Page<SalesRepBean> getSalesRepBeanPage() {
        return salesRepBeanPage;
    }

    public void setSalesRepBeanPage(Page<SalesRepBean> salesRepBeanPage) {
        this.salesRepBeanPage = salesRepBeanPage;
    }

    public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
        return dropDownMap;
    }

    public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
        this.dropDownMap = dropDownMap;
    }

    public Map<String, SalesResourceAssignOrg> getOrgMap() {
        return orgMap;
    }

    public void setOrgMap(Map<String, SalesResourceAssignOrg> orgMap) {
        this.orgMap = orgMap;
    }

    public Map<String, SalesResourceAssignTerritory> getTerrMap() {
        return terrMap;
    }

    public void setTerrMap(Map<String, SalesResourceAssignTerritory> terrMap) {
        this.terrMap = terrMap;
    }

    public Page<SalesRepBean2> getSalesRepPage() {
        return salesRepPage;
    }

    public void setSalesRepPage(Page<SalesRepBean2> salesRepPage) {
        this.salesRepPage = salesRepPage;
    }

    public String getAllChoiceVal() {
        return allChoiceVal;
    }

    public void setAllChoiceVal(String allChoiceVal) {
        this.allChoiceVal = allChoiceVal;
    }

    public String getOrgSessionId() {
        return orgSessionId;
    }

    public void setOrgSessionId(String orgSessionId) {
        this.orgSessionId = orgSessionId;
    }

    public String getTerrSessionId() {
        return terrSessionId;
    }

    public void setTerrSessionId(String terrSessionId) {
        this.terrSessionId = terrSessionId;
    }

    public SalesResourceAssignOrg getSalesResourceAssignOrg() {
        return salesResourceAssignOrg;
    }

    public void setSalesResourceAssignOrg(
            SalesResourceAssignOrg salesResourceAssignOrg) {
        this.salesResourceAssignOrg = salesResourceAssignOrg;
    }

    public SalesResourceAssignTerritory getSalesResourceAssignTerritory() {
        return salesResourceAssignTerritory;
    }

    public void setSalesResourceAssignTerritory(
            SalesResourceAssignTerritory salesResourceAssignTerritory) {
        this.salesResourceAssignTerritory = salesResourceAssignTerritory;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public Page<Organization> getOrgPage() {
        return orgPage;
    }

    public void setOrgPage(Page<Organization> orgPage) {
        this.orgPage = orgPage;
    }

    public Page<SalesTerritory> getTerrPage() {
        return terrPage;
    }

    public void setTerrPage(Page<SalesTerritory> terrPage) {
        this.terrPage = terrPage;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        this.territoryId = territoryId;
    }

    public Integer getDefaultTab() {
        return defaultTab;
    }

    public void setDefaultTab(Integer defaultTab) {
        this.defaultTab = defaultTab;
    }

    public List<Divisions> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<Divisions> divisionList) {
        this.divisionList = divisionList;
    }


}
