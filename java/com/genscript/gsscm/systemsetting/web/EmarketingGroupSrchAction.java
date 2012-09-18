/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genscript.gsscm.systemsetting.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.system.entity.EmarketingClerk;
import com.genscript.gsscm.system.entity.EmarketingGroup;
import com.genscript.gsscm.system.entity.EmarketingGroupAssigned;
import com.genscript.gsscm.systemsetting.service.EmarketingClerkService;

/**
 * @author jinsite
 */
@Results({
        @Result(location = "systemsetting/emarketingclerk_srch.jsp"),
        @Result(name = "list", location = "systemsetting/group_list.jsp"),
        @Result(name = "input", location = "systemsetting/group_input.jsp"),
        @Result(name = "inputClerk", location = "systemsetting/emarketingclerk_input.jsp"),
        @Result(name = "clerkUsers", location = "systemsetting/clerk_users.jsp")
})
public class EmarketingGroupSrchAction extends BaseAction {

    @Autowired
    private EmarketingClerkService service;
    private Page<EmarketingGroup> page;
    private Page<User> users;
    private String sessionGroupId;
    private EmarketingGroup group;
    private EmarketingClerk clerk;

    @Override
    public String execute() throws Exception {
        Struts2Util.getRequest().setAttribute("allcls", service.getProductAndService());

        return SUCCESS;
    }

    @Override
    public String list() throws Exception {
        PagerUtil<EmarketingGroup> pagerUtil = new PagerUtil<EmarketingGroup>();
        setPage(pagerUtil.getRequestPage());
        if (getPage().isOrderBySetted()) {
            getPage().setOrder(Page.DESC);
            getPage().setOrderBy("creationDate");
        }
        getPage().setPageSize(12);
        getPage().setAutoCount(Boolean.FALSE);
        List<PropertyFilter> filters = WebUtil.buildPropertyFilters(Struts2Util.getRequest());
        String assign = Struts2Util.getParameter("assign");
        if (StringUtils.isBlank(assign)) {
            setPage(service.searchEmarketingGroup(getPage(), filters));
        } else {
            String[] assignInfo = assign.split(":");
            setPage(service.searchEmarketingGroup(getPage(), filters, assignInfo));
        }
        List<EmarketingGroup> list = getPage().getResult();
        for (int i = 0; i < list.size(); i++) {
            ((EmarketingGroup) list.get(i)).setTypeNames(service.getNames(list.get(i).getGroupId()));
            if (list.get(i).getSupervisor() != null && list.get(i).getSupervisor().equals(0)) {
                list.get(i).setName(service.getEmployeeName(list.get(i).getSupervisor()));
            }
        }
        getPage().setResult(list);
        ServletActionContext.getRequest().setAttribute("pagerInfo",
                getPage());
        return "list";
    }

    @Override
    public String input() throws Exception {
        String groupId = Struts2Util.getParameter("groupId");
        List<EmarketingGroupAssigned> list;
        if (groupId == null || StringUtils.isBlank(groupId) || !StringUtils.isNumeric(groupId)) {
            if (sessionGroupId == null || StringUtils.isBlank(sessionGroupId)) {
                sessionGroupId = SessionUtil.generateTempId();
                ServletActionContext.getRequest().setAttribute("sessionGroupId", sessionGroupId);
            }
            list = (List<EmarketingGroupAssigned>) SessionUtil.getRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId);
            if (list == null) {
                list = new ArrayList<EmarketingGroupAssigned>();
            }
        } else {
            group = service.getEmarketingGroup(Integer.parseInt(groupId));
            // shipClerk.setName(shipClerkService.getEmployeeName(Integer.parseInt(clerkId)));
            sessionGroupId = group.getGroupId().toString();
            ServletActionContext.getRequest().setAttribute("edit", "true");
            list = (List<EmarketingGroupAssigned>) SessionUtil.getRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId);
            if (list == null) {
                list = service.getEmarketingGroupAssignedByGroupId(Integer.parseInt(groupId));
            }
        }
        String listStr = "";
        for (int i = 0; i < list.size(); i++) {
            listStr += "<td><input type=\"checkbox\" value=\"" + list.get(i).getItemType() + ":" + list.get(i).getClsId() + "\" name=\"assigned\"/>" + list.get(i).getClsName() + "</td>";
            if ((i + 1) % 2 == 0) {
                listStr += "</tr><tr>";
            }
        }
        if (list.size() % 2 == 1) {
            listStr += "<td>&nbsp;</td>";
        }
        listStr = "<tr>" + listStr + "</tr>";
        Struts2Util.getRequest().setAttribute("listStr", listStr);
        SessionUtil.insertRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId, list);
        Struts2Util.getRequest().setAttribute("allcls", service.getProductAndService());
        List<EmarketingClerk> clerkList = (List<EmarketingClerk>) SessionUtil.getRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId);
        if (clerkList == null && group != null) {
            clerkList = group.getClerkList();
            SessionUtil.insertRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId, clerkList);
        }
        if (clerkList != null) {
            for (EmarketingClerk temp : clerkList) {
                temp.setName(service.getEmployeeName(temp.getClerkId()));
            }
        }
        Struts2Util.getRequest().setAttribute("allClerks", clerkList);
        return "input";
    }
    
    /**
     * Show Users Page, and can select user as clerk.
     *
     * @return
     * @throws Exception
     */
    public String inputClerk() throws Exception {
        String clerkId = Struts2Util.getParameter("clerkId");
        if (clerkId == null || StringUtils.isBlank(clerkId) || !StringUtil.isNumeric(clerkId)) {
            clerk = new EmarketingClerk();
            clerk.setCreatedBy(SessionUtil.getUserId());
            clerk.setCreationDate(new Date());
            return "inputClerk";
        }
        @SuppressWarnings("unchecked")
		List<EmarketingClerk> clerkList = (List<EmarketingClerk>) SessionUtil.getRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId);
        if (clerkList == null) {
            clerk = service.getEmarketingClerk(Integer.parseInt(clerkId));
        } else {
            for (EmarketingClerk temp : clerkList) {
                if (temp.getClerkId().toString().equals(clerkId)) {
                    clerk = temp;
                }
            }
        }
        if (clerk != null) {
            clerk.setModifyDate(new Date());
            clerk.setModifiedBy(SessionUtil.getUserId());
        }
        return "inputClerk";
    }

    /**
     * Display all users for selecting
     *
     * @return
     * @throws Exception
     */
   public String getClerkUser() throws Exception {
        /*PagerUtil<User> pagerUtil = new PagerUtil<User>();
        users = pagerUtil.getRequestPage();
        if (!users.isOrderBySetted()) {
            users.setAutoCount(Boolean.TRUE);
        }
        if (users.getPageSize() == null
                || users.getPageSize().intValue() < 1) {
            users.setPageSize(20);
        }
        String p_no = Struts2Util.getParameter("p_no");
        System.out.println(p_no);
        if (StringUtils.isNotBlank(p_no) && StringUtils.isNumeric(p_no)) {
            users.setPageNo(Integer.parseInt(p_no));
        }
        users = service.getUsers(users);
        PageDTO pagerInfo = pagerUtil.formPage(users);
        Struts2Util.getRequest().setAttribute("pagerInfo", pagerInfo);*/
       users = this.service.getUsers(users);
		ServletActionContext.getRequest().setAttribute("pagerInfo", users);
        return "clerkUsers";
    }




    /**
     * Get Clerk Supervisor
     *
     * @return
     */
    public List<User> getSupervisor() {
        if (StringUtils.isNumeric(sessionGroupId)) {
            return service.getSupervisor(Integer.parseInt(sessionGroupId));
        } else {
            return null;
        }
    }

    @Override
    public String save() throws Exception {
        List<EmarketingClerk> clerkList = (List<EmarketingClerk>) SessionUtil.getRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId);
        List<EmarketingGroupAssigned> list = (List<EmarketingGroupAssigned>) SessionUtil.getRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId);
        List<Integer> delAssignedList = (List<Integer>) SessionUtil.getRow(SessionConstant.DelEmarketingGroupAssigned.value(), sessionGroupId);
        List<Integer> delClerkList = (List<Integer>) SessionUtil.getRow(SessionConstant.DelEmarketingClerkList.value(), sessionGroupId);
        // group.setClerkList(clerkList);
        if (group.getGroupId() == null || group.getGroupId().equals(Integer.parseInt("0"))) {
            group.setCreatedBy(SessionUtil.getUserId());
            group.setCreationDate(new Date());
            group.setModifiedBy(SessionUtil.getUserId());
            group.setModifyDate(new Date());
        } else {
            group.setModifiedBy(SessionUtil.getUserId());
            group.setModifyDate(new Date());
        }
        service.savaEmarketingGroup(group);
        service.saveEmarketingClerk(group.getGroupId(), clerkList);
        service.saveEmarketingGroupAssigned(group.getGroupId(), list);
        service.deleteEmarketingClerk(delClerkList);
        service.deleteEmarketingGroupAssigned(delAssignedList);
        SessionUtil.deleteRow(SessionConstant.DelEmarketingClerkList.value(), sessionGroupId);
        SessionUtil.deleteRow(SessionConstant.DelEmarketingGroupAssigned.value(), sessionGroupId);
        SessionUtil.deleteRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId);
        SessionUtil.deleteRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId);
        Struts2Util.getResponse().sendRedirect("emarketing_group_srch.action");
        return null;
    }

    /**
     * Save the EmarketingClerk
     *
     * @return
     * @throws Exception
     */
    public String saveClerk() throws Exception {
        // System.out.println(clerk.getClerkId());
        List<EmarketingClerk> clerkList = (List<EmarketingClerk>) SessionUtil.getRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId);
        if (clerkList == null) {
            clerkList = new ArrayList<EmarketingClerk>();
        }
        clerk.setName(service.getEmployeeName(clerk.getClerkId()));
        if (StringUtils.isNumeric(sessionGroupId) && service.isExistEmarketingClerk(Integer.parseInt(sessionGroupId), clerk.getClerkId())) {

            clerk.setModifyDate(new Date());
            clerk.setModifiedBy(SessionUtil.getUserId());
        } else {
            clerk.setCreationDate(new Date());
            clerk.setCreatedBy(SessionUtil.getUserId());
            clerk.setModifyDate(new Date());
            clerk.setModifiedBy(SessionUtil.getUserId());
        }
        for (EmarketingClerk temp : clerkList) {
            if (temp.getClerkId().equals(clerk.getClerkId())) {
                clerkList.remove(temp);
            }
        }
        clerkList.add(clerk);

        SessionUtil.insertRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId, clerkList);
        Struts2Util.getResponse().sendRedirect("emarketing_group_srch!input.action?sessionGroupId=" + sessionGroupId);
        return null;
    }

    /**
     * Assign the EmarketingGroup Product/Service.
     *
     * @return
     * @throws Exception
     */
    public String assign() throws Exception {
        if (sessionGroupId == null) {
            sessionGroupId = group.getGroupId().toString();
        }
        //System.out.println(sessionGroupId);
        String assignValue = Struts2Util.getParameter("assignValue");
        //System.out.println(assignValue);
        String[] assignValues = assignValue.split(":");
        EmarketingGroupAssigned groupAssigned = new EmarketingGroupAssigned();
        groupAssigned.setClsName(assignValues[0]);
        groupAssigned.setItemType(assignValues[2]);
        groupAssigned.setClsId(Integer.parseInt(assignValues[1]));
        groupAssigned.setCreatedBy(SessionUtil.getUserId());
        groupAssigned.setCreationDate(new Date());
        groupAssigned.setModifiedBy(SessionUtil.getUserId());
        groupAssigned.setModifyDate(new Date());
        List<EmarketingGroupAssigned> list = (List<EmarketingGroupAssigned>) SessionUtil.getRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId);
        if (list == null) {
            list = new ArrayList<EmarketingGroupAssigned>();
        }
        list.add(groupAssigned);
        SessionUtil.insertRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId, list);
        String listStr = "";
        for (int i = 0; i < list.size(); i++) {
            listStr += "<td><input type=\"checkbox\" value=\"" + list.get(i).getItemType() + ":" + list.get(i).getClsId() + "\" name=\"assigned\"/>" + list.get(i).getClsName() + "</td>";
            if ((i + 1) % 2 == 0) {
                listStr += "</tr><tr>";
            }
        }
        if (list.size() % 2 == 1) {
            listStr += "<td>&nbsp;</td>";
        }
        listStr = "<tr>" + listStr + "</tr>";
        //Struts2Util.getRequest().setAttribute("listStr", listStr);
        Struts2Util.renderText(listStr);
        return null;
    }

    /**
     * Unassign the EmarketingGroup Product/Service.
     *
     * @return
     * @throws Exception
     */
    public String unassign() throws Exception {
        if (sessionGroupId == null && group.getGroupId() != null) {
            sessionGroupId = group.getGroupId().toString();
        }
        List<EmarketingGroupAssigned> list = (List<EmarketingGroupAssigned>) SessionUtil.getRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId);
        if (list == null) {
            return null;
        }
        String unassignValue = Struts2Util.getParameter("unassignValue");
        List<Integer> delList = new ArrayList<Integer>();

        for (int i = 0; i < list.size(); i++) {
            if (unassignValue.contains(list.get(i).getItemType() + ":" + list.get(i).getClsId())) {
                if (list.get(i).getId() != null) {
                    delList.add(list.get(i).getId());
                }
                list.remove(i);
                i--;
            }
        }
        SessionUtil.insertRow(SessionConstant.EmarketingGroupAssigned.value(), sessionGroupId, list);
        SessionUtil.insertRow(SessionConstant.DelEmarketingGroupAssigned.value(), sessionGroupId, delList);
        if (list.size() > 0) {
            String listStr = "";
            for (int i = 0; i < list.size(); i++) {
                listStr += "<td><input type=\"checkbox\" value=\"" + list.get(i).getItemType() + ":" + list.get(i).getClsId() + "\" name=\"assigned\"/>" + list.get(i).getClsName() + "</td>";
                if ((i + 1) % 2 == 0) {
                    listStr += "</tr><tr>";
                }

            }
            if (list.size() % 2 == 1) {
                listStr += "<td>&nbsp;</td>";
            }
            listStr = "<tr>" + listStr + "</tr>";
            Struts2Util.renderText(listStr);
        }
        return null;
    }

    /**
     * Get UserName (Operator name)
     *
     * @return
     */
    public String getUserName() {
        return SessionUtil.getUserName();
    }

    @Override
    public String delete() throws Exception {

        return null;
    }

    public String deleteClerk() throws Exception {
        String delIds = Struts2Util.getParameter("clerkIds");
        List<EmarketingClerk> clerkList = (List<EmarketingClerk>) SessionUtil.getRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId);
        List<Integer> delList = (List<Integer>) SessionUtil.getRow(SessionConstant.DelEmarketingClerkList.value(), sessionGroupId);
        if (clerkList == null) {
            return null;
        }
        if (delList == null) {
            delList = new ArrayList<Integer>();
        }
        if (clerkList != null) {
            for (EmarketingClerk temp : clerkList) {
                if (StringUtils.isNumeric(sessionGroupId) && delIds.contains("," + temp.getClerkId().toString() + ",")) {
                    if (service.isExistEmarketingClerk(Integer.parseInt(sessionGroupId), temp.getClerkId())) {
                        delList.add(temp.getClerkId());
                    }
                    clerkList.remove(temp);
                }
            }
        }
        SessionUtil.insertRow(SessionConstant.EmarketingClerkList.value(), sessionGroupId, clerkList);
        SessionUtil.insertRow(SessionConstant.DelEmarketingClerkList.value(), sessionGroupId, delList);
        Struts2Util.renderText("SUCCESS");
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
    }

    /**
     * @return the page
     */
    public Page<EmarketingGroup> getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Page<EmarketingGroup> page) {
        this.page = page;
    }

    public Page<User> getUsers() {
        return users;
    }

    public void setUsers(Page<User> users) {
        this.users = users;
    }

    /**
     * @return the sessionGroupId
     */
    public String getSessionGroupId() {
        return sessionGroupId;
    }

    /**
     * @param sessionGroupId the sessionGroupId to set
     */
    public void setSessionGroupId(String sessionGroupId) {
        this.sessionGroupId = sessionGroupId;
    }

    /**
     * @return the group
     */
    public EmarketingGroup getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(EmarketingGroup group) {
        this.group = group;
    }

    /**
     * @return the clerk
     */
    public EmarketingClerk getClerk() {
        return clerk;
    }

    /**
     * @param clerk the clerk to set
     */
    public void setClerk(EmarketingClerk clerk) {
        this.clerk = clerk;
    }
}
