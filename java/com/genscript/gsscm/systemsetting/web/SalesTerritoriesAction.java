package com.genscript.gsscm.systemsetting.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dto.SalesTerritoryDTO;
import com.genscript.gsscm.customer.entity.SalesTerritory;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignment;
import com.genscript.gsscm.systemsetting.service.SalesTerritoriesService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Description:Sales Territories control class
 * @Copyright: Copyright genscrpit (c)2011
 * @author: lizhang
 * @version: 1.0
 * @date: 2011-02-15
 *        <p/>
 *        Modification History: Date Author Version Description
 *        -------------------------------------------------------------
 */
@Results({
		@Result(name = "sales_territories", location = "systemsetting/SalesTerritories/sales_territories.jsp"),
		@Result(name = "sales_territories_list", location = "systemsetting/SalesTerritories/sales_territories_list.jsp"),
		@Result(name = "zone_list", location = "systemsetting/SalesTerritories/zone_list.jsp"),
		@Result(name = "sales_territories_detail", location = "systemsetting/SalesTerritories/sales_territories_detail.jsp"),
		@Result(name = "zone_detail", location = "systemsetting/SalesTerritories/zone_detail.jsp"),
		@Result(name = "zone_select", location = "systemsetting/SalesTerritories/zone_select.jsp") })
public class SalesTerritoriesAction extends ActionSupport {
	private static final long serialVersionUID = 5190523152056253897L;
	/**
	 * 自动装载实例*
	 */
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private SalesTerritoriesService salesTerritoriesService;
	@Autowired
	private PublicService publicService;
	/**
	 * action 变量*
	 */
	private Integer territoryId;
	private String assignId;
	private String sessionId;
	private String zoneSessionId;
	private String operation_method;
	private String countryId;// 页面选择的country
	private String allChoiceVal;// 页面选择的territory
	private SalesTerritory salesTerritory;
	private SalesTerritoryDTO salesTerritoryDTO;
	private SalesTerritoryAssignment salesTerritoryAssignment;
	private Page<SalesTerritory> salesTerritoryPage;
	private Page<SalesTerritoryAssignment> zonePage;
	private Map<String, SalesTerritoryAssignment> zoneMap;
	private List<PbCountry> countryList;
	private List<PbState> stateList;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private Map<String, List<PbDropdownListOptions>> dropDownMap2;// function
	private String continent;

	/**
	 * ******************************action
	 * method**************************************
	 */

	public String input() {
		return "sales_territories";
	}

	public String list() {
		salesTerritoryPage = this.salesTerritoriesService
				.searchSalesTerritoryPage(salesTerritoryPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				salesTerritoryPage);
		return "sales_territories_list";
	}

	@SuppressWarnings("unchecked")
	public String load() {
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.SALES_REP_FUNCTION);
		dropDownMap2 = publicService.getDropDownMap(listName);
		try {
			if (territoryId != null) {
				// 判断将要修改的单号是否正在被操作
				String editUrl = "systemsetting/sales_territories!load.action?territoryId="
						+ territoryId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
			}
			if (Struts2Util.getParameter("referURL") != null
					&& Struts2Util.getParameter("referURL").equals("select")) {
				salesTerritoryDTO = (SalesTerritoryDTO) SessionUtil.getRow(
						SessionConstant.SalesTerritory.value(), sessionId);
				zoneMap = (Map<String, SalesTerritoryAssignment>) SessionUtil
						.getRow(SessionConstant.ZoneList.value(), sessionId);
			} else {
				if (territoryId != null) {
					salesTerritoryDTO = this.salesTerritoriesService
							.findById(territoryId);
					this.sessionId = String.valueOf(territoryId);

				} else {
					this.sessionId = SessionUtil.generateTempId();
					salesTerritoryDTO = new SalesTerritoryDTO();
					salesTerritoryDTO.setGsCoId(1);
					salesTerritoryDTO.setTerritoryType("sales");
					salesTerritoryDTO.setCreatedBy(SessionUtil.getUserId());
				}
				salesTerritoryDTO.setModifiedBy(SessionUtil.getUserId());
				salesTerritoryDTO.setModifiedName(SessionUtil.getUserName());
				salesTerritoryDTO.setModifyDate(new Date());
				// 建新的session
				SessionUtil.insertRow(SessionConstant.SalesTerritory.value(),
						sessionId, salesTerritoryDTO);
				zoneMap = SessionUtil.convertList2Map(
						salesTerritoryDTO.getZoneList(), "assignId");
				SessionUtil.insertRow(SessionConstant.ZoneList.value(),
						sessionId, zoneMap);
			}
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		} catch (Exception e) {
			e.printStackTrace();
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		}
		return "sales_territories_detail";
	}

	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (sessionId != null && !("").equals(sessionId)) {
				String editUrl = "systemsetting/sales_territories!load.action?territoryId="
						+ territoryId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message",
							"Failed to save the sales territory ,the sales territory is editing by "
									+ operation_method);
					rt.put("no", sessionId);
					Struts2Util.renderJson(rt);
					return null;
				}
				zoneMap = (Map<String, SalesTerritoryAssignment>) SessionUtil
						.getRow(SessionConstant.ZoneList.value(), sessionId);
				List<SalesTerritoryAssignment> zoneList = SessionUtil
						.convertMap2List(zoneMap);
				salesTerritoryDTO.setZoneList(zoneList);
				this.salesTerritoriesService.saveSalesTerritory(
						salesTerritoryDTO, sessionId);
				rt.put("message", "Save sales territory sucessfully.");
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
			this.salesTerritoriesService.deleteSalesTerritory(allChoiceVal);
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

	public String listZone() {
		zoneMap = (Map<String, SalesTerritoryAssignment>) SessionUtil.getRow(
				SessionConstant.ZoneList.value(), sessionId);
		return "zone_list";
	}

	public String loadZone() {
		try {
			if (StringUtils.isNotEmpty(zoneSessionId)) {
				Map<String, SalesTerritoryAssignment> zoneMap = (Map<String, SalesTerritoryAssignment>) SessionUtil
						.getRow(SessionConstant.ZoneList.value(), sessionId);
				salesTerritoryAssignment = zoneMap.get(zoneSessionId);

			} else {
				zoneSessionId = SessionUtil.generateTempId();
				salesTerritoryAssignment = new SalesTerritoryAssignment();
				salesTerritoryAssignment.setCreatedBy(SessionUtil.getUserId());
				salesTerritoryAssignment.setCreationDate(new Date());
			}
			if (salesTerritoryAssignment.getCountryCode() != null) {
				stateList = this.salesTerritoriesService
						.findStateByCountry(salesTerritoryAssignment
								.getCountryCode());
				PbCountry country = this.salesTerritoriesService
						.getCountryByCode(salesTerritoryAssignment
								.getCountryCode());
				if (country != null) {
					continent = country.getContinent();
				}
			}
			List<DropDownListName> listName = new ArrayList<DropDownListName>();
			listName.add(DropDownListName.CONTINENT);
			dropDownMap = publicService.getDropDownMap(listName);
			if (StringUtils.isEmpty(continent)
					&& dropDownMap != null
					&& dropDownMap.get(DropDownListName.CONTINENT.value()) != null
					&& dropDownMap.get(DropDownListName.CONTINENT.value())
							.size() > 0) {
				continent = dropDownMap.get(DropDownListName.CONTINENT.value())
						.get(0).getValue();
			}
			countryList = this.salesTerritoriesService
					.findCountryBycontinent(continent);

			if (stateList == null && countryList != null
					&& countryList.size() > 0) {

				stateList = this.salesTerritoriesService
						.findStateByCountry(countryList.get(0).getCountryCode());
			}
			salesTerritoryAssignment.setModifiedBy(SessionUtil.getUserId());
			salesTerritoryAssignment.setModifiedName(SessionUtil.getUserName());
			salesTerritoryAssignment.setModifyDate(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "zone_detail";
	}

	public String saveZone() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			/*
			 * Map<String, SalesTerritoryAssignment> zoneMap = (Map<String,
			 * SalesTerritoryAssignment>) SessionUtil
			 * .getRow(SessionConstant.ZoneList.value(), sessionId);
			 */
			/*
			 * List listall = this.salesTerritoriesService
			 * .getAllzonelistById(Integer.parseInt(sessionId));
			 */
			// Set<Map.Entry<String, SalesTerritoryAssignment>> set = zoneMap
			// .entrySet();
			/*
			 * System.out.println(set.size());
			 * System.out.println(set.isEmpty()); if (set.size() == 0) {
			 * zoneMap.put(zoneSessionId, salesTerritoryAssignment);
			 * rt.put("message", "Save zone sucessfully."); }else{
			 */
			/*
			 * for (Iterator<Map.Entry<String, SalesTerritoryAssignment>> it =
			 * set .iterator(); it.hasNext();) {
			 */
			// Map.Entry entry = (Map.Entry) it.next() ;

			zoneMap = (Map<String, SalesTerritoryAssignment>) SessionUtil
					.getRow(SessionConstant.ZoneList.value(), sessionId);
			System.out.println(sessionId); 
			if (!zoneMap.isEmpty()) {
				Iterator it = zoneMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, SalesTerritoryAssignment> entry = (Map.Entry<String, SalesTerritoryAssignment>) it
							.next();
					SalesTerritoryAssignment alesTerritoryAssignment = entry
							.getValue();
					if (alesTerritoryAssignment.getCountryCode().equals(
							salesTerritoryAssignment.getCountryCode())
							&& alesTerritoryAssignment.getStateCode().equals(
									salesTerritoryAssignment.getStateCode())) {
						if ((alesTerritoryAssignment.getFromZip() != null && !""
								.equals(alesTerritoryAssignment.getFromZip()))
								&& (alesTerritoryAssignment.getToZip() != null && !""
										.equals(alesTerritoryAssignment
												.getToZip()))) {
							if ((alesTerritoryAssignment.getFromZip()
									.equals(salesTerritoryAssignment
											.getFromZip()))
									&& (alesTerritoryAssignment.getToZip()
											.equals(salesTerritoryAssignment
													.getToZip()))) {
								rt.put("message",
										"Please select an other FromZip and ToZip !");
							} else {
								zoneMap.put(zoneSessionId,
										salesTerritoryAssignment);
								rt.put("message", "Save zone sucessfully.");
							}
						} else {
							rt.put("message",
									"Please select an other Country and State!");
						}

					} else {
						zoneMap.put(zoneSessionId, salesTerritoryAssignment);
						rt.put("message", "Save zone sucessfully.");
					}
				}  
			} else {
				zoneMap.put(zoneSessionId, salesTerritoryAssignment);
				rt.put("message", "Save zone sucessfully.");
			}

			SessionUtil.updateRow(SessionConstant.ZoneList.value(), sessionId,
					zoneMap);
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

	/*
	 * public String saveZone() { Map<String, Object> rt = new HashMap<String,
	 * Object>(); try { Map<String, SalesTerritoryAssignment> zoneMap =
	 * (Map<String, SalesTerritoryAssignment>)
	 * SessionUtil.getRow(SessionConstant.ZoneList.value(), sessionId);
	 * zoneMap.put(zoneSessionId, salesTerritoryAssignment);
	 * SessionUtil.updateRow(SessionConstant.ZoneList.value(), sessionId,
	 * zoneMap); rt.put("message", "Save zone sucessfully."); } catch (Exception
	 * ex) { ex.printStackTrace(); WSException exDTO =
	 * exceptionUtil.getExceptionDetails(ex); exceptionUtil.logException(exDTO,
	 * this.getClass(), ex, new Exception().getStackTrace()[0].getMethodName(),
	 * "INTF0203", SessionUtil.getUserId()); rt.put("hasException", "Y");
	 * rt.put("exception", exDTO); } Struts2Util.renderJson(rt); return NONE; }
	 */
	public String deleteZone() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.salesTerritoriesService.deleteSalesTerritoryAssignmnet(
					allChoiceVal, sessionId);
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

	public String save2Session() {
		SessionUtil.updateRow(SessionConstant.SalesTerritory.value(),
				sessionId, salesTerritoryDTO);
		Struts2Util.renderText("Success");
		return null;
	}

	/**
	 * 选择Zone
	 */
	public String selectZone() {
		zonePage = salesTerritoriesService.searchZonePage(zonePage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", zonePage);
		return "zone_select";
	}

	/**
	 * 选择continent
	 */
	public String selectContinent() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			countryList = this.salesTerritoriesService
					.findCountryBycontinent(continent);
			rt.put("countryList", countryList);
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
	 * 选择country
	 */
	public String selectCountry() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			System.out.println(">>>>>>>>>>>>>>>>>" + countryId);
			if (countryId != null && StringUtils.isNotEmpty(countryId)
					&& !"undefined".equals(countryId)
					&& countryId != "undefined") {
				stateList = this.salesTerritoriesService
						.findStateByCountryId(Integer.parseInt(countryId));
			}
			if (stateList != null) {
				System.out.println(stateList.size());
				rt.put("stateList", stateList);
			}
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
	 * ***************************getter
	 * setter*************************************
	 */
	public Integer getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public SalesTerritory getSalesTerritory() {
		return salesTerritory;
	}

	public void setSalesTerritory(SalesTerritory salesTerritory) {
		this.salesTerritory = salesTerritory;
	}

	public Page<SalesTerritory> getSalesTerritoryPage() {
		return salesTerritoryPage;
	}

	public void setSalesTerritoryPage(Page<SalesTerritory> salesTerritoryPage) {
		this.salesTerritoryPage = salesTerritoryPage;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public String getZoneSessionId() {
		return zoneSessionId;
	}

	public void setZoneSessionId(String zoneSessionId) {
		this.zoneSessionId = zoneSessionId;
	}

	public String getAllChoiceVal() {
		return allChoiceVal;
	}

	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}

	public SalesTerritoryDTO getSalesTerritoryDTO() {
		return salesTerritoryDTO;
	}

	public void setSalesTerritoryDTO(SalesTerritoryDTO salesTerritoryDTO) {
		this.salesTerritoryDTO = salesTerritoryDTO;
	}

	public SalesTerritoryAssignment getSalesTerritoryAssignment() {
		return salesTerritoryAssignment;
	}

	public void setSalesTerritoryAssignment(
			SalesTerritoryAssignment salesTerritoryAssignment) {
		this.salesTerritoryAssignment = salesTerritoryAssignment;
	}

	public Map<String, SalesTerritoryAssignment> getZoneMap() {
		return zoneMap;
	}

	public void setZoneMap(Map<String, SalesTerritoryAssignment> zoneMap) {
		this.zoneMap = zoneMap;
	}

	public String getAssignId() {
		return assignId;
	}

	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}

	public Page<SalesTerritoryAssignment> getZonePage() {
		return zonePage;
	}

	public void setZonePage(Page<SalesTerritoryAssignment> zonePage) {
		this.zonePage = zonePage;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public List<PbCountry> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<PbCountry> countryList) {
		this.countryList = countryList;
	}

	public List<PbState> getStateList() {
		return stateList;
	}

	public void setStateList(List<PbState> stateList) {
		this.stateList = stateList;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap2() {
		return dropDownMap2;
	}

	public void setDropDownMap2(
			Map<String, List<PbDropdownListOptions>> dropDownMap2) {
		this.dropDownMap2 = dropDownMap2;
	}

}
