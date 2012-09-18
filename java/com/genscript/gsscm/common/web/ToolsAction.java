package com.genscript.gsscm.common.web;

import static java.io.File.separator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.HostIpUtil;
import com.genscript.gsscm.common.MyX509TrustManager;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.systemsetting.service.ShipClerkService;
import com.genscript.gsscm.tools.dto.OrderFollowupDTO;
import com.genscript.gsscm.tools.entity.FollowUp;
import com.genscript.gsscm.tools.service.OrderFollowupService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Results({
		@Result(name = "salesList", location = "tools/sales.jsp"),
		@Result(name = "mysearchp", location = "tools/MysearchO.jsp"),
		@Result(name = "accountingList", location = "tools/accounting.jsp"),
		@Result(name = "generalList", location = "tools/general.jsp"),
		@Result(name = "Searchout", location = "tools/Searchout.jsp"),
		@Result(name = "manufacturingManagementList", location = "tools/manufacturingManagement.jsp"),
		@Result(name = "allFollowups", location = "tools/FollowUp_list.jsp"),
		@Result(name = "reportDataTable", location = "tools/reportDataT.jsp"),
		@Result(name = "exl_export", location = "tools/report_Excel.jsp"),
		@Result(name = "pdf_export", location = "tools/report_Pdf.jsp"),
		@Result(name = "productionList", location = "tools/production.jsp") })
public class ToolsAction extends ActionSupport {

	/**
	 * zhou gang 2011 07 27
	 */
	private Page<FollowUp> page;

	private HostIpUtil hostip = new HostIpUtil();

	private static long serialVersionUID = 1L;
	@Autowired
	private ShipClerkService shipClerkService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ExceptionService exceptionUtil;

	@Autowired
	private OrderFollowupService orderFollowupService;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private List<PbDropdownListOptions> priorityList;
	private OrderFollowupDTO orderFollowupDTO;
	private Page<OrderFollowupDTO> reportDataDtoPage;

	// -----------------------------------------------

	// 接受所有的参数
	private String orderNo;
	private String custNo;
	private String orderStatus;
	private String custName;
	private String dataFrom;
	private String dataTo;
	private String organization;
	private String priority;
	private String productServiceType;
	private String confirmationDate;
	private String orderTotal;
	private String location;
	private String productionTargetDate;
	private String guaranteedDeliveryDate;
	private String deliveryDate;
	private String numofItems;
	private String overdueNumOfitems;
	private String salesManager;
	private String tam;
	private String projectManager;
	private String po;
	private String followupStatus;
	private String followupDate;
	private String followupMessage;
	private String sortBy;
	private ArrayList<String> columnlist = new ArrayList<String>();
	private ArrayList<String> columns = new ArrayList<String>();
	public static String FILE_STORAGE = "files";
	public static String DOCUMENT_ITEM_STORAGE = "temps";

	public static String getFileStorage(HttpServletRequest request, String desc) {
		return request.getSession().getServletContext()
				.getRealPath(FILE_STORAGE + separator + desc);
	}

	public static String getDocumentItemStorage(HttpServletRequest request) {
		return getFileStorage(request, DOCUMENT_ITEM_STORAGE);
	}

	// ------------------------------------------------
	public String Searchout() {
		orderNo = ServletActionContext.getRequest().getParameter("orderNo");
		custNo = ServletActionContext.getRequest().getParameter("custNo");
		orderStatus = ServletActionContext.getRequest().getParameter(
				"orderStatus");
		custName = ServletActionContext.getRequest().getParameter("custName");
		dataFrom = ServletActionContext.getRequest().getParameter("dataFrom");
		dataTo = ServletActionContext.getRequest().getParameter("dataTo");
		organization = ServletActionContext.getRequest().getParameter(
				"organization");
		priority = ServletActionContext.getRequest().getParameter("priority");
		productServiceType = ServletActionContext.getRequest().getParameter(
				"productServiceType");
		confirmationDate = ServletActionContext.getRequest().getParameter(
				"confirmationDate");
		orderTotal = ServletActionContext.getRequest().getParameter(
				"orderTotal");
		location = ServletActionContext.getRequest().getParameter("location");
		productionTargetDate = ServletActionContext.getRequest().getParameter(
				"productionTargetDate");
		guaranteedDeliveryDate = ServletActionContext.getRequest()
				.getParameter("guaranteedDeliveryDate");
		deliveryDate = ServletActionContext.getRequest().getParameter(
				"deliveryDate");
		numofItems = ServletActionContext.getRequest().getParameter(
				"numofItems");
		overdueNumOfitems = ServletActionContext.getRequest().getParameter(
				"overdueNumOfitems");
		salesManager = ServletActionContext.getRequest().getParameter(
				"salesManager");
		tam = ServletActionContext.getRequest().getParameter("tam");
		projectManager = ServletActionContext.getRequest().getParameter(
				"projectManager");
		po = ServletActionContext.getRequest().getParameter("po");
		followupStatus = ServletActionContext.getRequest().getParameter(
				"followupStatus");
		followupDate = ServletActionContext.getRequest().getParameter(
				"followupDate");
		followupMessage = ServletActionContext.getRequest().getParameter(
				"followupMessage");
		sortBy = ServletActionContext.getRequest().getParameter("sortBy");
		String tempstr = "";
		if (orderNo != null && !"".equals(orderNo)) {
			tempstr = "orderNo:" + orderNo;
			columnlist.add(tempstr);
			columns.add("orderNo");
		}

		if (custNo != null && !"".equals(custNo)) {
			tempstr = "custNo:" + custNo;
			columnlist.add(tempstr);
			columns.add("custNo");
		}

		if (orderStatus != null && !"".equals(orderStatus)) {
			tempstr = "orderStatus:" + orderStatus;
			columnlist.add(tempstr);
			columns.add("orderStatus");
		}

		if (custName != null && !"".equals(custName)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("custName");
		}

		if (dataFrom != null && !"".equals(dataFrom)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("dataFrom");
		}
		if (dataTo != null && !"".equals(dataTo)) {
			tempstr = "dataTo:" + dataTo;
			columnlist.add(tempstr);
			columns.add("dataTo");
		}
		if (organization != null && !"".equals(organization)) {
			tempstr = "organization:" + organization;
			columnlist.add(tempstr);
			columns.add("organization");
		}
		if (priority != null && !"".equals(priority)) {
			tempstr = "priority:" + priority;
			columnlist.add(tempstr);
			columns.add("priority");
		}
		if (productServiceType != null && !"".equals(productServiceType)) {
			tempstr = "productServiceType:" + productServiceType;
			columnlist.add(tempstr);
			columns.add("productServiceType");
		}
		if (confirmationDate != null && !"".equals(confirmationDate)) {
			tempstr = "confirmationDate:" + confirmationDate;
			columnlist.add(tempstr);
			columns.add("confirmationDate");
		}
		if (orderTotal != null && !"".equals(orderTotal)) {
			tempstr = "orderTotal:" + orderTotal;
			columnlist.add(tempstr);
			columns.add("orderTotal");
		}
		if (location != null && !"".equals(location)) {
			tempstr = "location:" + location;
			columnlist.add(tempstr);
			columns.add("location");
		}
		if (productionTargetDate != null && !"".equals(productionTargetDate)) {
			tempstr = "productionTargetDate:" + productionTargetDate;
			columnlist.add(tempstr);
			columns.add("productionTargetDate");
		}
		if (guaranteedDeliveryDate != null
				&& !"".equals(guaranteedDeliveryDate)) {
			tempstr = "guaranteedDeliveryDate:" + guaranteedDeliveryDate;
			columnlist.add(tempstr);
			columns.add("guaranteedDeliveryDate");
		}
		if (deliveryDate != null && !"".equals(deliveryDate)) {
			tempstr = "deliveryDate:" + deliveryDate;
			columnlist.add(tempstr);
			columns.add("deliveryDate");
		}
		if (numofItems != null && !"".equals(numofItems)) {
			tempstr = "numofItems:" + numofItems;
			columnlist.add(tempstr);
			columns.add("numofItems");
		}
		if (overdueNumOfitems != null && !"".equals(overdueNumOfitems)) {
			tempstr = "overdueNumOfitems:" + overdueNumOfitems;
			columnlist.add(tempstr);
			columns.add("overdueNumOfitems");
		}
		if (salesManager != null && !"".equals(salesManager)) {
			tempstr = "salesManager:" + salesManager;
			columnlist.add(tempstr);
			columns.add("salesManager");
		}
		if (tam != null && !"".equals(tam)) {
			tempstr = "tam:" + tam;
			columnlist.add(tempstr);
			columns.add("tam");
		}
		if (projectManager != null && !"".equals(projectManager)) {
			tempstr = "projectManager:" + projectManager;
			columnlist.add(tempstr);
			columns.add("projectManager");
		}
		if (po != null && !"".equals(po)) {
			tempstr = "po:" + po;
			columnlist.add(tempstr);
			columns.add("po");
		}
		if (followupStatus != null && !"".equals(followupStatus)) {
			tempstr = "followupStatus:" + followupStatus;
			columnlist.add(tempstr);
			columns.add("followupStatus");
		}
		if (followupDate != null && !"".equals(followupDate)) {
			tempstr = "followupDate:" + followupDate;
			columnlist.add(tempstr);
			columns.add("followupDate");
		}
		if (followupMessage != null && !"".equals(followupMessage)) {
			tempstr = "followupMessage:" + followupMessage;
			columnlist.add(tempstr);
			columns.add("followupMessage");
		}

		if (null == orderFollowupDTO) {
			orderFollowupDTO = (OrderFollowupDTO) ServletActionContext
					.getRequest().getSession().getAttribute("toolsAction");
		}
		if (null != orderFollowupDTO) {
			PagerUtil<OrderFollowupDTO> pagerUtil = new PagerUtil<OrderFollowupDTO>();
			reportDataDtoPage = pagerUtil.getRequestPage();
			if (reportDataDtoPage.getPageSize() == null
					|| reportDataDtoPage.getPageSize().intValue() < 1) {
				reportDataDtoPage.setPageSize(15);
			}

			orderFollowupDTO = orderFollowupService.getOrderFollowupDate(
					reportDataDtoPage, orderFollowupDTO, false, sortBy,
					columnlist);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					reportDataDtoPage);
			ServletActionContext.getRequest().setAttribute("reportData",
					orderFollowupDTO.getReportData());
			ServletActionContext.getRequest().setAttribute("orderFollowupDTO",
					orderFollowupDTO);
			ServletActionContext.getRequest().setAttribute("columns", columns);

			ServletActionContext.getRequest().getSession()
					.setAttribute("toolsAction", orderFollowupDTO);

			ServletActionContext.getRequest().setAttribute("orderNo", orderNo);
			ServletActionContext.getRequest().setAttribute("custNo", custNo);
			ServletActionContext.getRequest().setAttribute("orderStatus",
					orderStatus);
			ServletActionContext.getRequest()
					.setAttribute("custName", custName);
			ServletActionContext.getRequest()
					.setAttribute("dataFrom", dataFrom);
			ServletActionContext.getRequest().setAttribute("dataTo", dataTo);
			ServletActionContext.getRequest().setAttribute("organization",
					organization);
			ServletActionContext.getRequest()
					.setAttribute("priority", priority);
			ServletActionContext.getRequest().setAttribute(
					"productServiceType", productServiceType);
			ServletActionContext.getRequest().setAttribute("confirmationDate",
					confirmationDate);
			ServletActionContext.getRequest().setAttribute("orderTotal",
					orderTotal);
			ServletActionContext.getRequest()
					.setAttribute("location", location);
			ServletActionContext.getRequest().setAttribute(
					"productionTargetDate", productionTargetDate);
			ServletActionContext.getRequest().setAttribute(
					"guaranteedDeliveryDate", guaranteedDeliveryDate);
			ServletActionContext.getRequest().setAttribute("deliveryDate",
					deliveryDate);
			ServletActionContext.getRequest().setAttribute("numofItems",
					numofItems);
			ServletActionContext.getRequest().setAttribute("overdueNumOfitems",
					overdueNumOfitems);
			ServletActionContext.getRequest().setAttribute("salesManager",
					salesManager);
			ServletActionContext.getRequest().setAttribute("tam", tam);
			ServletActionContext.getRequest().setAttribute("projectManager",
					projectManager);
			ServletActionContext.getRequest().setAttribute("po", po);
			ServletActionContext.getRequest().setAttribute("followupStatus",
					followupStatus);
			ServletActionContext.getRequest().setAttribute("followupDate",
					followupDate);
			ServletActionContext.getRequest().setAttribute("followupMessage",
					followupMessage);

			return "reportDataTable";
		} else {
			orderFollowupDTO = new OrderFollowupDTO();
			return "Searchout";
		}
	}

	public String searchSales() {
		return "salesList";
	}

	// //////------------------------------------------------------------
	public String Mysearch() {
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.GET_ALLORDER_STATUS);
		speclListName.add(SpecDropDownListName.GET_ALLSALES_MANAGER);
		speclListName.add(SpecDropDownListName.GET_ALLTECHACCOUNT_MANAGER);
		speclListName.add(SpecDropDownListName.GET_ALLPROJECT_MANAGER);
		speclListName.add(SpecDropDownListName.ALL_COUNTRY);

		specDropDownList = publicService.getSpecDropDownMap(speclListName);
		this.priorityList = this.publicService
				.getDropdownList("ORDER_PRIORITY");
		Map<String, String> productAndServiceCls = shipClerkService
				.getProductAndService();
		ServletActionContext.getRequest().setAttribute("allcls",
				productAndServiceCls);

		return "mysearchp";
	}

	// //////------------------------------------------------------------
	public void getOrderFollowUpSummaryExcelExport() throws Exception {
		orderNo = ServletActionContext.getRequest().getParameter("orderNo");
		custNo = ServletActionContext.getRequest().getParameter("custNo");
		orderStatus = ServletActionContext.getRequest().getParameter(
				"orderStatus");
		custName = ServletActionContext.getRequest().getParameter("custName");
		dataFrom = ServletActionContext.getRequest().getParameter("dataFrom");
		dataTo = ServletActionContext.getRequest().getParameter("dataTo");
		organization = ServletActionContext.getRequest().getParameter(
				"organization");
		priority = ServletActionContext.getRequest().getParameter("priority");
		productServiceType = ServletActionContext.getRequest().getParameter(
				"productServiceType");
		System.out.println(productServiceType);
		confirmationDate = ServletActionContext.getRequest().getParameter(
				"confirmationDate");
		orderTotal = ServletActionContext.getRequest().getParameter(
				"orderTotal");
		location = ServletActionContext.getRequest().getParameter("location");
		productionTargetDate = ServletActionContext.getRequest().getParameter(
				"productionTargetDate");
		guaranteedDeliveryDate = ServletActionContext.getRequest()
				.getParameter("guaranteedDeliveryDate");
		deliveryDate = ServletActionContext.getRequest().getParameter(
				"deliveryDate");
		numofItems = ServletActionContext.getRequest().getParameter(
				"numofItems");
		overdueNumOfitems = ServletActionContext.getRequest().getParameter(
				"overdueNumOfitems");
		salesManager = ServletActionContext.getRequest().getParameter(
				"salesManager");
		tam = ServletActionContext.getRequest().getParameter("tam");
		projectManager = ServletActionContext.getRequest().getParameter(
				"projectManager");
		po = ServletActionContext.getRequest().getParameter("po");
		followupStatus = ServletActionContext.getRequest().getParameter(
				"followupStatus");
		followupDate = ServletActionContext.getRequest().getParameter(
				"followupDate");
		followupMessage = ServletActionContext.getRequest().getParameter(
				"followupMessage");
		sortBy = ServletActionContext.getRequest().getParameter("sortBy");
		String tempstr = "";
		if (orderNo != null && !"".equals(orderNo)) {
			tempstr = "orderNo:" + orderNo;
			columnlist.add(tempstr);
			columns.add("orderNo");
		}

		if (custNo != null && !"".equals(custNo)) {
			tempstr = "custNo:" + custNo;
			columnlist.add(tempstr);
			columns.add("custNo");
		}

		if (orderStatus != null && !"".equals(orderStatus)) {
			tempstr = "orderStatus:" + orderStatus;
			columnlist.add(tempstr);
			columns.add("orderStatus");
		}

		if (custName != null && !"".equals(custName)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("custName");
		}

		if (dataFrom != null && !"".equals(dataFrom)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("dataFrom");
		}
		if (dataTo != null && !"".equals(dataTo)) {
			tempstr = "dataTo:" + dataTo;
			columnlist.add(tempstr);
			columns.add("dataTo");
		}
		if (organization != null && !"".equals(organization)) {
			tempstr = "organization:" + organization;
			columnlist.add(tempstr);
			columns.add("organization");
		}
		if (priority != null && !"".equals(priority)) {
			tempstr = "priority:" + priority;
			columnlist.add(tempstr);
			columns.add("priority");
		}
		if (productServiceType != null && !"".equals(productServiceType)) {
			tempstr = "productServiceType:" + productServiceType;
			columnlist.add(tempstr);
			columns.add("productServiceType");
		}
		if (confirmationDate != null && !"".equals(confirmationDate)) {
			tempstr = "confirmationDate:" + confirmationDate;
			columnlist.add(tempstr);
			columns.add("confirmationDate");
		}
		if (orderTotal != null && !"".equals(orderTotal)) {
			tempstr = "orderTotal:" + orderTotal;
			columnlist.add(tempstr);
			columns.add("orderTotal");
		}
		if (location != null && !"".equals(location)) {
			tempstr = "location:" + location;
			columnlist.add(tempstr);
			columns.add("location");
		}
		if (productionTargetDate != null && !"".equals(productionTargetDate)) {
			tempstr = "productionTargetDate:" + productionTargetDate;
			columnlist.add(tempstr);
			columns.add("productionTargetDate");
		}
		if (guaranteedDeliveryDate != null
				&& !"".equals(guaranteedDeliveryDate)) {
			tempstr = "guaranteedDeliveryDate:" + guaranteedDeliveryDate;
			columnlist.add(tempstr);
			columns.add("guaranteedDeliveryDate");
		}
		if (deliveryDate != null && !"".equals(deliveryDate)) {
			tempstr = "deliveryDate:" + deliveryDate;
			columnlist.add(tempstr);
			columns.add("deliveryDate");
		}
		if (numofItems != null && !"".equals(numofItems)) {
			tempstr = "numofItems:" + numofItems;
			columnlist.add(tempstr);
			columns.add("numofItems");
		}
		if (overdueNumOfitems != null && !"".equals(overdueNumOfitems)) {
			tempstr = "overdueNumOfitems:" + overdueNumOfitems;
			columnlist.add(tempstr);
			columns.add("overdueNumOfitems");
		}
		if (salesManager != null && !"".equals(salesManager)) {
			tempstr = "salesManager:" + salesManager;
			columnlist.add(tempstr);
			columns.add("salesManager");
		}
		if (tam != null && !"".equals(tam)) {
			tempstr = "tam:" + tam;
			columnlist.add(tempstr);
			columns.add("tam");
		}
		if (projectManager != null && !"".equals(projectManager)) {
			tempstr = "projectManager:" + projectManager;
			columnlist.add(tempstr);
			columns.add("projectManager");
		}
		if (po != null && !"".equals(po)) {
			tempstr = "po:" + po;
			columnlist.add(tempstr);
			columns.add("po");
		}
		if (followupStatus != null && !"".equals(followupStatus)) {
			tempstr = "followupStatus:" + followupStatus;
			columnlist.add(tempstr);
			columns.add("followupStatus");
		}
		if (followupDate != null && !"".equals(followupDate)) {
			tempstr = "followupDate:" + followupDate;
			columnlist.add(tempstr);
			columns.add("followupDate");
		}
		if (followupMessage != null && !"".equals(followupMessage)) {
			tempstr = "followupMessage:" + followupMessage;
			columnlist.add(tempstr);
			columns.add("followupMessage");
		}


		pdfExport("getOrderFollowUpExcelExport", "OrderFollowUpSummary",
				columns, columnlist);

	}

	public String getOrderFollowUpExcelExport() {
		orderNo = ServletActionContext.getRequest().getParameter("orderNo");
		custNo = ServletActionContext.getRequest().getParameter("custNo");
		orderStatus = ServletActionContext.getRequest().getParameter(
				"orderStatus");
		custName = ServletActionContext.getRequest().getParameter("custName");
		dataFrom = ServletActionContext.getRequest().getParameter("dataFrom");
		dataTo = ServletActionContext.getRequest().getParameter("dataTo");
		organization = ServletActionContext.getRequest().getParameter(
				"organization");
		priority = ServletActionContext.getRequest().getParameter("priority");
		productServiceType = ServletActionContext.getRequest().getParameter(
				"productServiceType");
		confirmationDate = ServletActionContext.getRequest().getParameter(
				"confirmationDate");
		orderTotal = ServletActionContext.getRequest().getParameter(
				"orderTotal");
		location = ServletActionContext.getRequest().getParameter("location");
		productionTargetDate = ServletActionContext.getRequest().getParameter(
				"productionTargetDate");
		guaranteedDeliveryDate = ServletActionContext.getRequest()
				.getParameter("guaranteedDeliveryDate");
		deliveryDate = ServletActionContext.getRequest().getParameter(
				"deliveryDate");
		numofItems = ServletActionContext.getRequest().getParameter(
				"numofItems");
		overdueNumOfitems = ServletActionContext.getRequest().getParameter(
				"overdueNumOfitems");
		salesManager = ServletActionContext.getRequest().getParameter(
				"salesManager");
		tam = ServletActionContext.getRequest().getParameter("tam");
		projectManager = ServletActionContext.getRequest().getParameter(
				"projectManager");
		po = ServletActionContext.getRequest().getParameter("po");
		followupStatus = ServletActionContext.getRequest().getParameter(
				"followupStatus");
		followupDate = ServletActionContext.getRequest().getParameter(
				"followupDate");
		followupMessage = ServletActionContext.getRequest().getParameter(
				"followupMessage");
		sortBy = ServletActionContext.getRequest().getParameter("sortBy");
		
		String tempstr = "";
		if (orderNo != null && !"".equals(orderNo)) {
			tempstr = "orderNo:" + orderNo;
			columnlist.add(tempstr);
			columns.add("orderNo");
		}

		if (custNo != null && !"".equals(custNo)) {
			tempstr = "custNo:" + custNo;
			columnlist.add(tempstr);
			columns.add("custNo");
		}

		if (orderStatus != null && !"".equals(orderStatus)) {
			tempstr = "orderStatus:" + orderStatus;
			columnlist.add(tempstr);
			columns.add("orderStatus");
		}

		if (custName != null && !"".equals(custName)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("custName");
		}

		if (dataFrom != null && !"".equals(dataFrom)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("dataFrom");
		}
		if (dataTo != null && !"".equals(dataTo)) {
			tempstr = "dataTo:" + dataTo;
			columnlist.add(tempstr);
			columns.add("dataTo");
		}
		if (organization != null && !"".equals(organization)) {
			tempstr = "organization:" + organization;
			columnlist.add(tempstr);
			columns.add("organization");
		}
		if (priority != null && !"".equals(priority)) {
			tempstr = "priority:" + priority;
			columnlist.add(tempstr);
			columns.add("priority");
		}
		if (productServiceType != null && !"".equals(productServiceType)) {
			tempstr = "productServiceType:" + productServiceType;
			columnlist.add(tempstr);
			columns.add("productServiceType");
		}
		if (confirmationDate != null && !"".equals(confirmationDate)) {
			tempstr = "confirmationDate:" + confirmationDate;
			columnlist.add(tempstr);
			columns.add("confirmationDate");
		}
		if (orderTotal != null && !"".equals(orderTotal)) {
			tempstr = "orderTotal:" + orderTotal;
			columnlist.add(tempstr);
			columns.add("orderTotal");
		}
		if (location != null && !"".equals(location)) {
			tempstr = "location:" + location;
			columnlist.add(tempstr);
			columns.add("location");
		}
		if (productionTargetDate != null && !"".equals(productionTargetDate)) {
			tempstr = "productionTargetDate:" + productionTargetDate;
			columnlist.add(tempstr);
			columns.add("productionTargetDate");
		}
		if (guaranteedDeliveryDate != null
				&& !"".equals(guaranteedDeliveryDate)) {
			tempstr = "guaranteedDeliveryDate:" + guaranteedDeliveryDate;
			columnlist.add(tempstr);
			columns.add("guaranteedDeliveryDate");
		}
		if (deliveryDate != null && !"".equals(deliveryDate)) {
			tempstr = "deliveryDate:" + deliveryDate;
			columnlist.add(tempstr);
			columns.add("deliveryDate");
		}
		if (numofItems != null && !"".equals(numofItems)) {
			tempstr = "numofItems:" + numofItems;
			columnlist.add(tempstr);
			columns.add("numofItems");
		}
		if (overdueNumOfitems != null && !"".equals(overdueNumOfitems)) {
			tempstr = "overdueNumOfitems:" + overdueNumOfitems;
			columnlist.add(tempstr);
			columns.add("overdueNumOfitems");
		}
		if (salesManager != null && !"".equals(salesManager)) {
			tempstr = "salesManager:" + salesManager;
			columnlist.add(tempstr);
			columns.add("salesManager");
		}
		if (tam != null && !"".equals(tam)) {
			tempstr = "tam:" + tam;
			columnlist.add(tempstr);
			columns.add("tam");
		}
		if (projectManager != null && !"".equals(projectManager)) {
			tempstr = "projectManager:" + projectManager;
			columnlist.add(tempstr);
			columns.add("projectManager");
		}
		if (po != null && !"".equals(po)) {
			tempstr = "po:" + po;
			columnlist.add(tempstr);
			columns.add("po");
		}
		if (followupStatus != null && !"".equals(followupStatus)) {
			tempstr = "followupStatus:" + followupStatus;
			columnlist.add(tempstr);
			columns.add("followupStatus");
		}
		if (followupDate != null && !"".equals(followupDate)) {
			tempstr = "followupDate:" + followupDate;
			columnlist.add(tempstr);
			columns.add("followupDate");
		}
		if (followupMessage != null && !"".equals(followupMessage)) {
			tempstr = "followupMessage:" + followupMessage;
			columnlist.add(tempstr);
			columns.add("followupMessage");
		}
	 
		orderFollowupDTO = orderFollowupService.getOrderFollowupDate2Pdf(
				reportDataDtoPage, orderFollowupDTO, false, sortBy, columnlist);

		ServletActionContext.getRequest().setAttribute("pagerInfo",
				reportDataDtoPage);
		ServletActionContext.getRequest().setAttribute("reportData",
				orderFollowupDTO.getReportData());
		ServletActionContext.getRequest().setAttribute("orderFollowupDTO",
				orderFollowupDTO);
		ServletActionContext.getRequest().setAttribute("columns", columns);
 
		return "exl_export";
	}

	// //////------------------------------------------------------------

	public void getOrderFollowUpSummaryPdfExport() throws Exception {
		orderNo = ServletActionContext.getRequest().getParameter("orderNo");
		custNo = ServletActionContext.getRequest().getParameter("custNo");
		orderStatus = ServletActionContext.getRequest().getParameter(
				"orderStatus");
		custName = ServletActionContext.getRequest().getParameter("custName");
		dataFrom = ServletActionContext.getRequest().getParameter("dataFrom");
		dataTo = ServletActionContext.getRequest().getParameter("dataTo");
		organization = ServletActionContext.getRequest().getParameter(
				"organization");
		priority = ServletActionContext.getRequest().getParameter("priority");
		productServiceType = ServletActionContext.getRequest().getParameter(
				"productServiceType");
		confirmationDate = ServletActionContext.getRequest().getParameter(
				"confirmationDate");
		orderTotal = ServletActionContext.getRequest().getParameter(
				"orderTotal");
		location = ServletActionContext.getRequest().getParameter("location");
		productionTargetDate = ServletActionContext.getRequest().getParameter(
				"productionTargetDate");
		guaranteedDeliveryDate = ServletActionContext.getRequest()
				.getParameter("guaranteedDeliveryDate");
		deliveryDate = ServletActionContext.getRequest().getParameter(
				"deliveryDate");
		numofItems = ServletActionContext.getRequest().getParameter(
				"numofItems");
		overdueNumOfitems = ServletActionContext.getRequest().getParameter(
				"overdueNumOfitems");
		salesManager = ServletActionContext.getRequest().getParameter(
				"salesManager");
		tam = ServletActionContext.getRequest().getParameter("tam");
		projectManager = ServletActionContext.getRequest().getParameter(
				"projectManager");
		po = ServletActionContext.getRequest().getParameter("po");
		followupStatus = ServletActionContext.getRequest().getParameter(
				"followupStatus");
		followupDate = ServletActionContext.getRequest().getParameter(
				"followupDate");
		followupMessage = ServletActionContext.getRequest().getParameter(
				"followupMessage");
		sortBy = ServletActionContext.getRequest().getParameter("sortBy");
		String tempstr = "";
		if (orderNo != null && !"".equals(orderNo)) {
			tempstr = "orderNo:" + orderNo;
			columnlist.add(tempstr);
			columns.add("orderNo");
		}

		if (custNo != null && !"".equals(custNo)) {
			tempstr = "custNo:" + custNo;
			columnlist.add(tempstr);
			columns.add("custNo");
		}

		if (orderStatus != null && !"".equals(orderStatus)) {
			tempstr = "orderStatus:" + orderStatus;
			columnlist.add(tempstr);
			columns.add("orderStatus");
		}

		if (custName != null && !"".equals(custName)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("custName");
		}

		if (dataFrom != null && !"".equals(dataFrom)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("dataFrom");
		}
		if (dataTo != null && !"".equals(dataTo)) {
			tempstr = "dataTo:" + dataTo;
			columnlist.add(tempstr);
			columns.add("dataTo");
		}
		if (organization != null && !"".equals(organization)) {
			tempstr = "organization:" + organization;
			columnlist.add(tempstr);
			columns.add("organization");
		}
		if (priority != null && !"".equals(priority)) {
			tempstr = "priority:" + priority;
			columnlist.add(tempstr);
			columns.add("priority");
		}
		if (productServiceType != null && !"".equals(productServiceType)) {
			tempstr = "productServiceType:" + productServiceType;
			columnlist.add(tempstr);
			columns.add("productServiceType");
		}
		if (confirmationDate != null && !"".equals(confirmationDate)) {
			tempstr = "confirmationDate:" + confirmationDate;
			columnlist.add(tempstr);
			columns.add("confirmationDate");
		}
		if (orderTotal != null && !"".equals(orderTotal)) {
			tempstr = "orderTotal:" + orderTotal;
			columnlist.add(tempstr);
			columns.add("orderTotal");
		}
		if (location != null && !"".equals(location)) {
			tempstr = "location:" + location;
			columnlist.add(tempstr);
			columns.add("location");
		}
		if (productionTargetDate != null && !"".equals(productionTargetDate)) {
			tempstr = "productionTargetDate:" + productionTargetDate;
			columnlist.add(tempstr);
			columns.add("productionTargetDate");
		}
		if (guaranteedDeliveryDate != null
				&& !"".equals(guaranteedDeliveryDate)) {
			tempstr = "guaranteedDeliveryDate:" + guaranteedDeliveryDate;
			columnlist.add(tempstr);
			columns.add("guaranteedDeliveryDate");
		}
		if (deliveryDate != null && !"".equals(deliveryDate)) {
			tempstr = "deliveryDate:" + deliveryDate;
			columnlist.add(tempstr);
			columns.add("deliveryDate");
		}
		if (numofItems != null && !"".equals(numofItems)) {
			tempstr = "numofItems:" + numofItems;
			columnlist.add(tempstr);
			columns.add("numofItems");
		}
		if (overdueNumOfitems != null && !"".equals(overdueNumOfitems)) {
			tempstr = "overdueNumOfitems:" + overdueNumOfitems;
			columnlist.add(tempstr);
			columns.add("overdueNumOfitems");
		}
		if (salesManager != null && !"".equals(salesManager)) {
			tempstr = "salesManager:" + salesManager;
			columnlist.add(tempstr);
			columns.add("salesManager");
		}
		if (tam != null && !"".equals(tam)) {
			tempstr = "tam:" + tam;
			columnlist.add(tempstr);
			columns.add("tam");
		}
		if (projectManager != null && !"".equals(projectManager)) {
			tempstr = "projectManager:" + projectManager;
			columnlist.add(tempstr);
			columns.add("projectManager");
		}
		if (po != null && !"".equals(po)) {
			tempstr = "po:" + po;
			columnlist.add(tempstr);
			columns.add("po");
		}
		if (followupStatus != null && !"".equals(followupStatus)) {
			tempstr = "followupStatus:" + followupStatus;
			columnlist.add(tempstr);
			columns.add("followupStatus");
		}
		if (followupDate != null && !"".equals(followupDate)) {
			tempstr = "followupDate:" + followupDate;
			columnlist.add(tempstr);
			columns.add("followupDate");
		}
		if (followupMessage != null && !"".equals(followupMessage)) {
			tempstr = "followupMessage:" + followupMessage;
			columnlist.add(tempstr);
			columns.add("followupMessage");
		}

		pdfExport("getFollowUpSummaryPdfExport", "OrderFollowUpSummary",
				columns, columnlist);

	}

	public String getFollowUpSummaryPdfExport() {
		orderNo = ServletActionContext.getRequest().getParameter("orderNo");
		custNo = ServletActionContext.getRequest().getParameter("custNo");
		orderStatus = ServletActionContext.getRequest().getParameter(
				"orderStatus");
		custName = ServletActionContext.getRequest().getParameter("custName");
		dataFrom = ServletActionContext.getRequest().getParameter("dataFrom");
		dataTo = ServletActionContext.getRequest().getParameter("dataTo");
		organization = ServletActionContext.getRequest().getParameter(
				"organization");
		priority = ServletActionContext.getRequest().getParameter("priority");
		productServiceType = ServletActionContext.getRequest().getParameter(
				"productServiceType");
		confirmationDate = ServletActionContext.getRequest().getParameter(
				"confirmationDate");
		orderTotal = ServletActionContext.getRequest().getParameter(
				"orderTotal");
		location = ServletActionContext.getRequest().getParameter("location");
		productionTargetDate = ServletActionContext.getRequest().getParameter(
				"productionTargetDate");
		guaranteedDeliveryDate = ServletActionContext.getRequest()
				.getParameter("guaranteedDeliveryDate");
		deliveryDate = ServletActionContext.getRequest().getParameter(
				"deliveryDate");
		numofItems = ServletActionContext.getRequest().getParameter(
				"numofItems");
		overdueNumOfitems = ServletActionContext.getRequest().getParameter(
				"overdueNumOfitems");
		salesManager = ServletActionContext.getRequest().getParameter(
				"salesManager");
		tam = ServletActionContext.getRequest().getParameter("tam");
		projectManager = ServletActionContext.getRequest().getParameter(
				"projectManager");
		po = ServletActionContext.getRequest().getParameter("po");
		followupStatus = ServletActionContext.getRequest().getParameter(
				"followupStatus");
		followupDate = ServletActionContext.getRequest().getParameter(
				"followupDate");
		followupMessage = ServletActionContext.getRequest().getParameter(
				"followupMessage");
		sortBy = ServletActionContext.getRequest().getParameter("sortBy");
		
		String tempstr = "";
		if (orderNo != null && !"".equals(orderNo)) {
			tempstr = "orderNo:" + orderNo;
			columnlist.add(tempstr);
			columns.add("orderNo");
		}

		if (custNo != null && !"".equals(custNo)) {
			tempstr = "custNo:" + custNo;
			columnlist.add(tempstr);
			columns.add("custNo");
		}

		if (orderStatus != null && !"".equals(orderStatus)) {
			tempstr = "orderStatus:" + orderStatus;
			columnlist.add(tempstr);
			columns.add("orderStatus");
		}

		if (custName != null && !"".equals(custName)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("custName");
		}

		if (dataFrom != null && !"".equals(dataFrom)) {
			tempstr = "dataFrom:" + dataFrom;
			columnlist.add(tempstr);
			columns.add("dataFrom");
		}
		if (dataTo != null && !"".equals(dataTo)) {
			tempstr = "dataTo:" + dataTo;
			columnlist.add(tempstr);
			columns.add("dataTo");
		}
		if (organization != null && !"".equals(organization)) {
			tempstr = "organization:" + organization;
			columnlist.add(tempstr);
			columns.add("organization");
		}
		if (priority != null && !"".equals(priority)) {
			tempstr = "priority:" + priority;
			columnlist.add(tempstr);
			columns.add("priority");
		}
		if (productServiceType != null && !"".equals(productServiceType)) {
			tempstr = "productServiceType:" + productServiceType;
			columnlist.add(tempstr);
			columns.add("productServiceType");
		}
		if (confirmationDate != null && !"".equals(confirmationDate)) {
			tempstr = "confirmationDate:" + confirmationDate;
			columnlist.add(tempstr);
			columns.add("confirmationDate");
		}
		if (orderTotal != null && !"".equals(orderTotal)) {
			tempstr = "orderTotal:" + orderTotal;
			columnlist.add(tempstr);
			columns.add("orderTotal");
		}
		if (location != null && !"".equals(location)) {
			tempstr = "location:" + location;
			columnlist.add(tempstr);
			columns.add("location");
		}
		if (productionTargetDate != null && !"".equals(productionTargetDate)) {
			tempstr = "productionTargetDate:" + productionTargetDate;
			columnlist.add(tempstr);
			columns.add("productionTargetDate");
		}
		if (guaranteedDeliveryDate != null
				&& !"".equals(guaranteedDeliveryDate)) {
			tempstr = "guaranteedDeliveryDate:" + guaranteedDeliveryDate;
			columnlist.add(tempstr);
			columns.add("guaranteedDeliveryDate");
		}
		if (deliveryDate != null && !"".equals(deliveryDate)) {
			tempstr = "deliveryDate:" + deliveryDate;
			columnlist.add(tempstr);
			columns.add("deliveryDate");
		}
		if (numofItems != null && !"".equals(numofItems)) {
			tempstr = "numofItems:" + numofItems;
			columnlist.add(tempstr);
			columns.add("numofItems");
		}
		if (overdueNumOfitems != null && !"".equals(overdueNumOfitems)) {
			tempstr = "overdueNumOfitems:" + overdueNumOfitems;
			columnlist.add(tempstr);
			columns.add("overdueNumOfitems");
		}
		if (salesManager != null && !"".equals(salesManager)) {
			tempstr = "salesManager:" + salesManager;
			columnlist.add(tempstr);
			columns.add("salesManager");
		}
		if (tam != null && !"".equals(tam)) {
			tempstr = "tam:" + tam;
			columnlist.add(tempstr);
			columns.add("tam");
		}
		if (projectManager != null && !"".equals(projectManager)) {
			tempstr = "projectManager:" + projectManager;
			columnlist.add(tempstr);
			columns.add("projectManager");
		}
		if (po != null && !"".equals(po)) {
			tempstr = "po:" + po;
			columnlist.add(tempstr);
			columns.add("po");
		}
		if (followupStatus != null && !"".equals(followupStatus)) {
			tempstr = "followupStatus:" + followupStatus;
			columnlist.add(tempstr);
			columns.add("followupStatus");
		}
		if (followupDate != null && !"".equals(followupDate)) {
			tempstr = "followupDate:" + followupDate;
			columnlist.add(tempstr);
			columns.add("followupDate");
		}
		if (followupMessage != null && !"".equals(followupMessage)) {
			tempstr = "followupMessage:" + followupMessage;
			columnlist.add(tempstr);
			columns.add("followupMessage");
		}
	 
		
		orderFollowupDTO = orderFollowupService.getOrderFollowupDate2Pdf(
				reportDataDtoPage, orderFollowupDTO, false, sortBy, columnlist);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				reportDataDtoPage);
		ServletActionContext.getRequest().setAttribute("reportData",
				orderFollowupDTO.getReportData());
		ServletActionContext.getRequest().setAttribute("orderFollowupDTO",
				orderFollowupDTO);
		ServletActionContext.getRequest().setAttribute("columns", columns);
		 

		return "pdf_export";
	}

	// //////------------------------------------------------------------

	public void pdfExport(String method, String fileName,
			ArrayList<String> arr, ArrayList<String> arr2) throws Exception {
		StringBuffer params = new StringBuffer("");
		if (null != orderFollowupDTO) {
			Map reportDataMap = BeanUtils.describe(orderFollowupDTO);
			Iterator it = reportDataMap.keySet().iterator();
			while (it.hasNext()) {
				String dtoName = (String) it.next();
				System.out.println(dtoName);
				if ("class".equals(dtoName)) {
					continue;
				} else {
					String dtoValue = BeanUtils.getProperty(orderFollowupDTO,
							dtoName);
					if (dtoValue != null && !"null".equals(dtoValue)
							&& !"".equals(dtoValue)) {
						params.append("orderFollowupDTO."
								+ dtoName
								+ "="
								+ BeanUtils.getProperty(orderFollowupDTO,
										dtoName) + "&");
					}
				}
			}
		}
		if (arr != null && arr2 != null) {
			for (int i = 0; i < arr.size(); i++) {
				String names = arr.get(i);
				String namesd = arr2.get(i);
				params.append(names + "=" + namesd + "&");
			}
		}
		if (params.length() > 0) {
			params = params.deleteCharAt(params.length() - 1);
		}

		System.out.println(params.toString());
		HttpURLConnection con = null;
		HttpsURLConnection connection = null;
		URL url = null;
		Process p = null;
		// String cmd = "wkhtmltopdf";
		String cmd = "C:\\Program Files\\wkhtmltopdf\\wkhtmltopdf.exe";
		try {
			String sessionid = Struts2Util.getRequest().getSession().getId();
			String port = "";
			if (Struts2Util.getRequest().getServerPort() != 80) {
				port = ":" + Struts2Util.getRequest().getServerPort();
			}
			String basePath = Struts2Util.getRequest().getScheme() + "://"
					+ Struts2Util.getRequest().getServerName() + port
					+ Struts2Util.getRequest().getContextPath() + "/";
			url = new URL(basePath + "tools/tools!" + method + ".action");
			if (basePath.startsWith("https://")) {
				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
				TrustManager[] tm = { new MyX509TrustManager() };
				SSLContext sslContext = SSLContext
						.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				HostnameVerifier hv = new HostnameVerifier() {
					public boolean verify(String urlHostName, SSLSession session) {
						System.out.println("Warning: URL Host: " + urlHostName
								+ " vs. " + session.getPeerHost());
						return true;
					}
				};

				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				connection = (HttpsURLConnection) url.openConnection();
				connection.setSSLSocketFactory(ssf);
				connection.setRequestProperty("Cookie", "JSESSIONID="
						+ sessionid);
				byte[] b = params.toString().getBytes();
				connection.getOutputStream().write(b, 0, b.length);
				connection.connect();
			} else {
				con = (HttpURLConnection) url.openConnection();
				con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
				con.setDoOutput(true);
				byte[] b = params.toString().getBytes();
				con.getOutputStream().write(b, 0, b.length);
				con.connect();
			}
			int size = 0;
			byte[] buf = new byte[1024];
			BufferedInputStream bis = null;
			if (con != null) {
				bis = new BufferedInputStream(con.getInputStream());
			}
			StringBuffer strb = new StringBuffer();
			while ((size = bis.read(buf)) != -1) {
				strb.append(new String(buf, 0, size));
			}
			FileWriter writer = null;
			HttpServletRequest request = ServletActionContext.getRequest();
			File pdftempFile = new File(getDocumentItemStorage(request),
					separator + fileName + ".pdf");
			File htmltempFile = new File(getDocumentItemStorage(request),
					separator + fileName + ".html");
			FileUtils.forceMkdir(pdftempFile.getParentFile());
			FileUtils.forceMkdir(htmltempFile.getParentFile());
			writer = new FileWriter(htmltempFile);
			writer.write(strb.toString());
			writer.flush();
			writer.close();
			ProcessBuilder pb = new ProcessBuilder(cmd,
					getDocumentItemStorage(request) + separator + fileName
							+ ".html", getDocumentItemStorage(request)
							+ separator + fileName + ".pdf");
			pb.redirectErrorStream(true);
			p = pb.start();
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			// 提示下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName + (new Random()).nextInt() + ".pdf");
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream(
					getDocumentItemStorage(request) + separator + fileName
							+ ".pdf");
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
			bis.close();
			if (con != null) {
				con.disconnect();
			}
		} catch (Exception e) {
			if (p != null)
				p.destroy();
			System.out.println("Error exec!");
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			Struts2Util.renderText(exDTO.getMessageDetail() + "\n"
					+ exDTO.getAction());
			e.printStackTrace();
		}
	}

	// //////------------------------------------------------------------
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public ExceptionService getExceptionUtil() {
		return exceptionUtil;
	}

	public void setExceptionUtil(ExceptionService exceptionUtil) {
		this.exceptionUtil = exceptionUtil;
	}

	public Page<OrderFollowupDTO> getReportDataDtoPage() {
		return reportDataDtoPage;
	}

	public void setReportDataDtoPage(Page<OrderFollowupDTO> reportDataDtoPage) {
		this.reportDataDtoPage = reportDataDtoPage;
	}

	public Page<FollowUp> getPage() {
		return page;
	}

	public void setPage(Page<FollowUp> page) {
		this.page = page;
	}

	public HostIpUtil getHostip() {
		return hostip;
	}

	public void setHostip(HostIpUtil hostip) {
		this.hostip = hostip;
	}

	public String searchAccounting() {
		return "accountingList";
	}

	public List<PbDropdownListOptions> getPriorityList() {
		return priorityList;
	}

	public void setPriorityList(List<PbDropdownListOptions> priorityList) {
		this.priorityList = priorityList;
	}

	public String searchManufacturingManagement() {
		return "manufacturingManagementList";
	}

	public String searchProduction() {
		return "productionList";
	}

	public String searchGeneral() {
		return "generalList";
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public OrderFollowupDTO getOrderFollowupDTO() {
		return orderFollowupDTO;
	}

	public void setOrderFollowupDTO(OrderFollowupDTO orderFollowupDTO) {
		this.orderFollowupDTO = orderFollowupDTO;
	}

}
