/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genscript.gsscm.systemsetting.web;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.purchase.entity.VendorProduct;
import com.genscript.gsscm.shipment.entity.ShipClerk;
import com.genscript.gsscm.shipment.entity.ShipClerkAssigned;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.service.ShipmentsService;
import com.genscript.gsscm.systemsetting.service.EmarketingClerkService;
import com.genscript.gsscm.systemsetting.service.ShipClerkService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jinsite
 */
@Results({
		@Result(name = "searchForm", location = "systemsetting/shipclerk_srch.jsp"),
		@Result(name = "list", location = "systemsetting/shipclerks_list.jsp"),
		@Result(name = "input", location = "systemsetting/shipclerks_input.jsp"),
		@Result(name = "clerkUsers", location = "systemsetting/clerk_users.jsp") })
public class ShippingClerksSrchAction extends ActionSupport {

	@Autowired
	private ShipClerkService shipClerkService;
	@Autowired
	private ShipmentsService shipmentService;

	private String msg;

	@Autowired
	private EmarketingClerkService service;
	@Autowired
	private UserDao userDao;
	private Page<ShipClerk> page;
	private Page<User> users;
	private ShipClerk shipClerk;
	private String sessionClerkId;

	/**
	 * Get All Users can transform to Clerk. zhougang update 2011 06 09
	 * 
	 * @return
	 */
	public String getClerkUser() throws Exception {
		PagerUtil<User> pagerUtil = new PagerUtil<User>();
		users = pagerUtil.getRequestPage();
		if (!users.isOrderBySetted()) {
			users.setAutoCount(Boolean.TRUE);
		}
		if (users.getPageSize() == null || users.getPageSize().intValue() < 1) {
			users.setPageSize(20);
		}
		String p_no = Struts2Util.getParameter("p_no");
		System.out.println(p_no);
		if (org.apache.commons.lang.StringUtils.isNotBlank(p_no)
				&& org.apache.commons.lang.StringUtils.isNumeric(p_no)) {
			users.setPageNo(Integer.parseInt(p_no));
		}
		users = service.getUsers(users);
		PageDTO pagerInfo = pagerUtil.formPage(users);
		Struts2Util.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "clerkUsers";
	}

	/**
	 * Get All Clerks as default.
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		PagerUtil<ShipClerk> pagerUtil = new PagerUtil<ShipClerk>();
		page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("creationDate");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(12);
		// page.setAutoCount(Boolean.FALSE);
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(Struts2Util
				.getRequest());
		String assign = Struts2Util.getParameter("assign");
		System.out.println(assign);
		if (StringUtils.isBlank(assign)) {
			page = shipClerkService.searchShipClerk(page, filters);
		} else {
			String[] assignInfo = assign.split(":");
			page = shipClerkService.searchShipClerk(page, filters, assignInfo);
		}
		System.out.println("page=" + page.getTotalCount());
		List<ShipClerk> list = page.getResult();
		for (int i = 0; i < list.size(); i++) {
			((ShipClerk) list.get(i)).setTypeNames(shipClerkService
					.getNames(list.get(i).getClerkId()));
			list.get(i).setName(
					shipClerkService.getEmployeeName(list.get(i).getClerkId()));
		}
		page.setResult(list);
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		msg = "";
		return "list";
	}

	/**
	 * Create and Edit Clerk
	 * 
	 * @return
	 */
	@Override
	public String input() throws Exception {
		String clerkId = Struts2Util.getParameter("clerkId");
		Map<String, String> productAndServiceCls = shipClerkService
				.getProductAndService();
		if (clerkId == null || StringUtils.isBlank(clerkId)) {
			ServletActionContext.getRequest().setAttribute("sessionClerkId",
					SessionUtil.generateTempId());
		} else {
			shipClerk = shipClerkService
					.getShipClerk(Integer.parseInt(clerkId));
			User user = this.shipClerkService
					.getUser(Integer.parseInt(clerkId));
			if (user != null) {
				shipClerk.setName(user.getEmployee().getEmployeeName());
				shipClerk.setEmployeeId(user.getEmployee().getEmployeeId());
			}
			if (sessionClerkId == null) {
				sessionClerkId = shipClerk.getClerkId().toString();
			}
			SessionUtil.deleteRow(SessionConstant.ShipClerkAssigned.value(),
					sessionClerkId);
			SessionUtil.deleteRow(SessionConstant.DelShipClerkAssigned.value(),
					sessionClerkId);
			ServletActionContext.getRequest().setAttribute("edit", "true");
			List<ShipClerkAssigned> list = (List<ShipClerkAssigned>) SessionUtil
					.getRow(SessionConstant.ShipClerkAssigned.value(),
							sessionClerkId);
			if (list == null) {
				list = shipClerkService.getShipClerkAssignedByClerkId(Integer
						.parseInt(clerkId));
			}
			String listStr = "";
			for (int i = 0; i < list.size(); i++) {
				for (Map.Entry<String, String> entry : productAndServiceCls
						.entrySet()) {
					if (entry.getKey().equals(
							entry.getKey().split(":")[0] + ":"
									+ list.get(i).getClsId() + ":"
									+ list.get(i).getItemType())) {
						productAndServiceCls.remove(entry.getKey());
						break;
					}
				}
				listStr += "<td><input type=\"checkbox\" value=\""
						+ list.get(i).getItemType() + ":"
						+ list.get(i).getClsId() + "\" name=\"assigned\"/>"
						+ list.get(i).getItemType() + ":"
						+ list.get(i).getClsName() + "</td>";
				if ((i + 1) % 2 == 0) {
					listStr += "</tr><tr>";
				}
			}
			if (list.size() % 2 == 1) {
				listStr += "<td>&nbsp;</td>";
			}
			listStr = "<tr>" + listStr + "</tr>";

			Struts2Util.getRequest().setAttribute("listStr", listStr);
			SessionUtil.insertRow(SessionConstant.ShipClerkAssigned.value(),
					clerkId, list);
		}
		operation_method = ServletActionContext.getRequest().getParameter(
				"operation_method");
		ServletActionContext.getRequest().setAttribute("operation_method",
				operation_method);
		ServletActionContext.getRequest().setAttribute("allcls",
				productAndServiceCls);
		return "input";
	}

	public ShipClerkService getShipClerkService() {
		return shipClerkService;
	}

	public void setShipClerkService(ShipClerkService shipClerkService) {
		this.shipClerkService = shipClerkService;
	}

	/**
	 * Get All Clerks as Supervisor
	 * 
	 * @return
	 */
	public List<User> getSupervisor() {
		List<User> users = userDao
				.find("Select user from User user, ShipClerk shipClerk where user.userId=shipClerk.clerkId");
		return users;
	}

	/**
	 * Get Operator Name
	 * 
	 * @return
	 */
	public String getUserName() {
		return SessionUtil.getUserName();
	}

	public String searchClerks() {

		return null;
	}

	/**
	 * Show the search Form.
	 * 
	 * @return
	 */
	public String searchForm() {
		ServletActionContext.getRequest().setAttribute("allcls",
				shipClerkService.getProductAndService());
		return "searchForm";
	}

	public boolean checkallmangageName(List<ShipClerk> shipClerkList,
			String name) {
		boolean flag = false;
		for (int s = 0; s < shipClerkList.size(); s++) {
			ShipClerk shipClerk = (ShipClerk) shipClerkList.get(s);
			String employerName = shipClerkService.getEmployeeName(shipClerk
					.getClerkId());
			if (name.equals(employerName)) {
				flag = true;
			}
		}
		return flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Save Clerk
	 * 
	 * @return
	 */
	private String operation_method;

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public String save() {
		@SuppressWarnings("unchecked")
		List<ShipClerkAssigned> list = (List<ShipClerkAssigned>) SessionUtil
				.getRow(SessionConstant.ShipClerkAssigned.value(),
						sessionClerkId);

		String username = ServletActionContext.getRequest().getParameter(
				"username");
		/*
		 * String operation_method = ServletActionContext.getRequest()
		 * .getParameter("operation_method");
		 */
		System.out.println(operation_method);
		if (operation_method.equals("edit")) {
			Integer userId = SessionUtil.getUserId();
			Date now = new Date();
			if (sessionClerkId == null) {
				sessionClerkId = shipClerk.getClerkId().toString();
			}
			try {
				shipClerk.setModifiedBy(userId);
				shipClerk.setModifyDate(now);
				if (shipClerk.getCreatedBy() == null) {
					shipClerk.setCreationDate(now);
					shipClerk.setCreatedBy(userId);
				}
				shipClerkService.saveShipClerk(shipClerk);
				@SuppressWarnings("unchecked")
				List<Integer> delList = (List<Integer>) SessionUtil.getRow(
						SessionConstant.DelShipClerkAssigned.value(),
						sessionClerkId);
				shipClerkService.saveAssigned(shipClerk.getClerkId(), list);
				shipClerkService.delAssigned(delList);
				msg = "ok";

			} catch (Exception ex) {

			}
			ServletActionContext.getRequest().setAttribute("allcls",
					shipClerkService.getProductAndService());
		} else {
			List<ShipClerk> shipClerkList = shipClerkService.getAllClerks();
			if (shipClerkList != null && shipClerkList.size() > 0) {

				if (checkallmangageName(shipClerkList, username)) {
					msg = "Please select an another one Manager Name! ";
				} else {
					Integer userId = SessionUtil.getUserId();
					Date now = new Date();
					if (sessionClerkId == null) {
						sessionClerkId = shipClerk.getClerkId().toString();
					}
					try {
						shipClerk.setModifiedBy(userId);
						shipClerk.setModifyDate(now);
						if (shipClerk.getCreatedBy() == null) {
							shipClerk.setCreationDate(now);
							shipClerk.setCreatedBy(userId);
						}
						shipClerkService.saveShipClerk(shipClerk);
						@SuppressWarnings("unchecked")
						List<Integer> delList = (List<Integer>) SessionUtil
								.getRow(SessionConstant.DelShipClerkAssigned
										.value(), sessionClerkId);
						shipClerkService.saveAssigned(shipClerk.getClerkId(),
								list);
						shipClerkService.delAssigned(delList);
						msg = "ok";

					} catch (Exception ex) {

					}
					ServletActionContext.getRequest().setAttribute("allcls",
							shipClerkService.getProductAndService());
				}
			} else {
				Integer userId = SessionUtil.getUserId();
				Date now = new Date();
				if (sessionClerkId == null) {
					sessionClerkId = shipClerk.getClerkId().toString();
				}
				try {
					shipClerk.setModifiedBy(userId);
					shipClerk.setModifyDate(now);
					if (shipClerk.getCreatedBy() == null) {
						shipClerk.setCreationDate(now);
						shipClerk.setCreatedBy(userId);
					}
					shipClerkService.saveShipClerk(shipClerk);
					@SuppressWarnings("unchecked")
					List<Integer> delList = (List<Integer>) SessionUtil.getRow(
							SessionConstant.DelShipClerkAssigned.value(),
							sessionClerkId);
					shipClerkService.saveAssigned(shipClerk.getClerkId(), list);
					shipClerkService.delAssigned(delList);
					msg = "";

				} catch (Exception ex) {

				}
				ServletActionContext.getRequest().setAttribute("allcls",
						shipClerkService.getProductAndService());
			}
		}
		ServletActionContext.getRequest().setAttribute("msg", msg);
		return "searchForm";
	}

	/**
	 * Assign the Product/Service
	 * 
	 * @return
	 */
	public String assign() {
		if (sessionClerkId == null) {
			sessionClerkId = shipClerk.getClerkId().toString();
		}
		String assignValue = Struts2Util.getParameter("assignValue");
		System.out.println(assignValue);
		String[] assignValues = assignValue.split(":");
		ShipClerkAssigned shipClerkAssigned = new ShipClerkAssigned();
        //modify by zhanghuibin
		shipClerkAssigned.setClsName(assignValues[1]);
		shipClerkAssigned.setItemType(assignValues[3]);
		shipClerkAssigned.setClsId(Integer.parseInt(assignValues[2]));
		shipClerkAssigned.setAssignDate(new Date());
		shipClerkAssigned.setAssignedBy(SessionUtil.getUserId());
		List<ShipClerkAssigned> list = (List<ShipClerkAssigned>) SessionUtil.getRow(SessionConstant.ShipClerkAssigned.value(), sessionClerkId);
		if (list == null) {
			list = new ArrayList<ShipClerkAssigned>();
		}
		list.add(shipClerkAssigned);
		SessionUtil.insertRow(SessionConstant.ShipClerkAssigned.value(),
				sessionClerkId, list);
		String listStr = "";
		for (int i = 0; i < list.size(); i++) {
			listStr += "<td><input type=\"checkbox\" value=\""
					+ list.get(i).getItemType() + ":" + list.get(i).getClsId()
					+ "\" name=\"assigned\"/>" + list.get(i).getClsName()
					+ "</td>";
			if ((i + 1) % 2 == 0) {
				listStr += "</tr><tr>";
			}
		}
		if (list.size() % 2 == 1) {
			listStr += "<td>&nbsp;</td>";
		}
		listStr = "<tr>" + listStr + "</tr>";
		Struts2Util.getRequest().setAttribute("listStr", listStr);
		Struts2Util.renderText(listStr);
		return null;
	}

	/**
	 * Unassign the Product/Service
	 * 
	 * @return
	 */
	public String unassign() {
		if (sessionClerkId == null && shipClerk.getClerkId() != null) {
			sessionClerkId = shipClerk.getClerkId().toString();
		}
		List<ShipClerkAssigned> list = (List<ShipClerkAssigned>) SessionUtil
				.getRow(SessionConstant.ShipClerkAssigned.value(),
						sessionClerkId);
		String unassignValue = Struts2Util.getParameter("unassignValue");
		List<Integer> delList = new ArrayList<Integer>();
		// String[] unassignValues=unassignValue.split(",");
		for (int i = 0; i < list.size(); i++) {
			if (unassignValue.contains(list.get(i).getItemType() + ":"
					+ list.get(i).getClsId())) {
				if (list.get(i).getAssignId() != null) {
					delList.add(list.get(i).getAssignId());
				}
				list.remove(i);
				i--;
			}
		}
		SessionUtil.insertRow(SessionConstant.ShipClerkAssigned.value(),
				sessionClerkId, list);
		SessionUtil.insertRow(SessionConstant.DelShipClerkAssigned.value(),
				sessionClerkId, delList);
		if (list.size() > 0) {
			String listStr = "";
			for (int i = 0; i < list.size(); i++) {
				listStr += "<td><input type=\"checkbox\" value=\""
						+ list.get(i).getItemType() + ":"
						+ list.get(i).getClsId() + "\" name=\"assigned\"/>"
						+ list.get(i).getClsName() + "</td>";
				if ((i + 1) % 2 == 0) {
					listStr += "</tr><tr>";
				}

			}
			if (list.size() % 2 == 1) {
				listStr += "<td>&nbsp;</td>";
			}
			listStr = "<tr>" + listStr + "</tr>";
			// Struts2Util.getRequest().setAttribute("listStr", listStr);
			Struts2Util.renderText(listStr);
		}
		return null;
	}

	/**
	 * Delete a Clerk
	 * 
	 * @return
	 */
	public String delete() {
		String clerkIds = Struts2Util.getParameter("clerkIds");
		if (clerkIds != null && !clerkIds.equals("")) {
			String[] clerkArr = clerkIds.split(",");
			for (String clerkId : clerkArr) {
				List<Shipment> shipmentList = this.shipmentService.searchShipmentListByClick(Integer.valueOf(clerkId));
				if(shipmentList!=null&&!shipmentList.isEmpty()){
					Struts2Util.renderText("Please unassign the job of this clerk first.");
					return null;
				}
				List<ShipClerkAssigned> list = shipClerkService
				.getShipClerkAssignedByClerkId(Integer
						.parseInt(clerkId));
				
				shipClerkService.delShipClerkAssigned(list);
				shipClerkService.delShipClerk(Integer.parseInt(clerkId));
			}
		}
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	/**
	 * @return the page
	 */
	public Page<ShipClerk> getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page<ShipClerk> page) {
		this.page = page;
	}

	/**
	 * @return the shipClerk
	 */
	public ShipClerk getShipClerk() {
		return shipClerk;
	}

	/**
	 * @param shipClerk
	 *            the shipClerk to set
	 */
	public void setShipClerk(ShipClerk shipClerk) {
		this.shipClerk = shipClerk;
	}

	/**
	 * @return the sessionClerkId
	 */
	public String getSessionClerkId() {
		return sessionClerkId;
	}

	public Page<User> getUsers() {
		return users;
	}

	public void setUsers(Page<User> users) {
		this.users = users;
	}

	/**
	 * @param sessionClerkId
	 *            the sessionClerkId to set
	 */
	public void setSessionClerkId(String sessionClerkId) {
		this.sessionClerkId = sessionClerkId;
	}
}
