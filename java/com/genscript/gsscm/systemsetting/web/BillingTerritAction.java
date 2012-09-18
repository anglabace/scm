package com.genscript.gsscm.systemsetting.web;

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
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.systemsetting.dto.BillTerritoryDTO;
import com.genscript.gsscm.systemsetting.dto.BillTerritoryZoneDTO;
import com.genscript.gsscm.systemsetting.entity.BillTerritory;
import com.genscript.gsscm.systemsetting.service.BillTerritoryService;

@Results({
		@Result(name = "billingterrit_search_form", location = "systemsetting/account/billingterrit_srch.jsp"),
		@Result(name = "billingterrit_list", location = "systemsetting/account/billingterrit_list.jsp"),
		@Result(name = "billingterrit_input", location = "systemsetting/account/billingterrit_create_form.jsp"),
		@Result(name = "billingterrit_zone_list", location = "systemsetting/account/billingterrit_zone_list.jsp"),
		@Result(name = "billingterrit_zone_edit", location = "systemsetting/account/billingterrit_zone_detail.jsp") })
public class BillingTerritAction extends BaseAction<BillTerritoryDTO> {

	private static final long serialVersionUID = 3726923926485350498L;
	private BillTerritoryDTO billingTerritDTO;
	private BillTerritoryZoneDTO billTerritoryZoneDTO;
	@Autowired
	private BillTerritoryService billTerritoryService;
	@Autowired
	private PublicService publicService;
	private Page<BillTerritory> page;
	private Page<BillTerritoryZoneDTO> zonePage;
	private String sessTerritoryId;
	private String sessZoneId;
	private String delZoneIds;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private String operation_method;
	private List<PbCountry> continentList;
	private String rtnMessage;

	public String searchForm() {
		return "billingterrit_search_form";
	}

	@Override
	public String list() throws Exception {
		PagerUtil<BillTerritory> pagerUtil = new PagerUtil<BillTerritory>();
		page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("territoryId");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(20);
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		page = billTerritoryService.searchBillTerritory(page, filters);
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		return "billingterrit_list";
	}

	public String listZone() throws Exception {
		PagerUtil<BillTerritoryZoneDTO> pagerUtil = new PagerUtil<BillTerritoryZoneDTO>();
		zonePage = pagerUtil.getRequestPage();
		if (!zonePage.isOrderBySetted()) {
			zonePage.setOrderBy("zoneId");
			zonePage.setOrder(Page.DESC);
		}
		zonePage.setPageSize(20);
		zonePage = billTerritoryService.searchBillTerritoryZone(zonePage,
				sessTerritoryId);
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", zonePage);
		return "billingterrit_zone_list";
	}

	@Override
	public String input() throws Exception {
		if (StringUtils.isNotBlank(sessTerritoryId)) {
			SessionUtil.deleteRow(SessionConstant.BillTerritory.value(), sessTerritoryId);
			SessionUtil.deleteRow(SessionConstant.BillTerritoryZoneList.value(), sessTerritoryId);
		} else {
			sessTerritoryId = SessionUtil.generateTempId();
		}
		billingTerritDTO = billTerritoryService
				.getBillTerritoryById(sessTerritoryId);
		dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownMap.put("BILL_TERRITORY_ACCOUNT_USAGE",
				publicService.getDropdownList("BILL_TERRITORY_ACCOUNT_USAGE"));
		dropDownMap.put("BILL_TERRITORY_ACCOUNT_TYPE",
				publicService.getDropdownList("BILL_TERRITORY_ACCOUNT_TYPE"));
		return "billingterrit_input";
	}

	/**
	 * 新增或修改Zone
	 * 
	 * @author Zhang Yong
	 * @return
	 * @throws Exception
	 */
	public String zoneInput() throws Exception {
		billTerritoryZoneDTO = billTerritoryService.getBillTerritoryZoneById(
				sessTerritoryId, sessZoneId);
		continentList = billTerritoryService.getContinent();
		return "billingterrit_zone_edit";
	}

	/**
	 * 保存Territory
	 * @author Zhang Yong
	 */
	@Override
	public String save() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		String state = Struts2Util.getParameter("state");
		if (billingTerritDTO != null) {
			billingTerritDTO.setState(state);
		}
		String rtnMessage = billTerritoryService.saveBillTerritory(sessTerritoryId, billingTerritDTO);
		if (StringUtils.isNotBlank(rtnMessage)) {
			rt.put("message", rtnMessage);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 保存Zone信息到session中，保存成功后返回到billTerritory的编辑页面
	 * @author Zhang Yong
	 * @return
	 * @throws Exception
	 */
	public String saveZone () throws Exception {
		String state = Struts2Util.getParameter("state");
		if (billTerritoryZoneDTO != null) {
			billTerritoryZoneDTO.setState(state);
		}
		//保存Zone信息到session中
		rtnMessage = billTerritoryService.saveZone(billTerritoryZoneDTO, sessTerritoryId);
		if (StringUtils.isNotBlank(rtnMessage)) {
			continentList = billTerritoryService.getContinent();
			return "billingterrit_zone_edit";
		}
		//保存成功后返回到billTerritory的编辑页面
		billingTerritDTO = billTerritoryService
				.getBillTerritoryById(sessTerritoryId);
		dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownMap.put("BILL_TERRITORY_ACCOUNT_USAGE",
				publicService.getDropdownList("BILL_TERRITORY_ACCOUNT_USAGE"));
		dropDownMap.put("BILL_TERRITORY_ACCOUNT_TYPE",
				publicService.getDropdownList("BILL_TERRITORY_ACCOUNT_TYPE"));
		return "billingterrit_input";
	}

	@Override
	public String delete() throws Exception {
		String territoryIds = Struts2Util.getParameter("territoryIds");
		billTerritoryService.delBillTerritory(territoryIds);
		Struts2Util.renderText("SUCCESS");
		return null;
	}
	
	public String deleteZone() throws Exception {
		String delZoneIds = Struts2Util.getParameter("delZoneIds");
		billTerritoryService.delBillTerritoryZones(delZoneIds, sessTerritoryId);
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	public BillTerritoryDTO getBillingTerritDTO() {
		return billingTerritDTO;
	}

	public void setBillingTerritDTO(BillTerritoryDTO billingTerritDTO) {
		this.billingTerritDTO = billingTerritDTO;
	}

	public Page<BillTerritory> getPage() {
		return page;
	}

	public void setPage(Page<BillTerritory> page) {
		this.page = page;
	}

	public Page<BillTerritoryZoneDTO> getZonePage() {
		return zonePage;
	}

	public void setZonePage(Page<BillTerritoryZoneDTO> zonePage) {
		this.zonePage = zonePage;
	}

	public String getSessTerritoryId() {
		return sessTerritoryId;
	}

	public void setSessTerritoryId(String sessTerritoryId) {
		this.sessTerritoryId = sessTerritoryId;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public String getSessZoneId() {
		return sessZoneId;
	}

	public void setSessZoneId(String sessZoneId) {
		this.sessZoneId = sessZoneId;
	}

	public String getDelZoneIds() {
		return delZoneIds;
	}

	public void setDelZoneIds(String delZoneIds) {
		this.delZoneIds = delZoneIds;
	}

	public BillTerritoryZoneDTO getBillTerritoryZoneDTO() {
		return billTerritoryZoneDTO;
	}

	public void setBillTerritoryZoneDTO(
			BillTerritoryZoneDTO billTerritoryZoneDTO) {
		this.billTerritoryZoneDTO = billTerritoryZoneDTO;
	}

	public List<PbCountry> getContinentList() {
		return continentList;
	}

	public void setContinentList(List<PbCountry> continentList) {
		this.continentList = continentList;
	}

	public String getRtnMessage() {
		return rtnMessage;
	}

	public void setRtnMessage(String rtnMessage) {
		this.rtnMessage = rtnMessage;
	}

}
