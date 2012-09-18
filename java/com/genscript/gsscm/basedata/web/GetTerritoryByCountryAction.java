package com.genscript.gsscm.basedata.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.GetInfoByRegionDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.SalesGroup;
import com.genscript.gsscm.customer.entity.SalesRep;

/**
 * 获得Territory 列表。
 * 
 * @author zouyulu
 * 
 */
public class GetTerritoryByCountryAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6174330817370744486L;
	/**
	 * 
	 */

	@Autowired
	private PublicService publicService;
	private String countryCode;
	private String stateCode;
	private Integer orgId;
	private String salesTerrId;
	private String zipCode;
	
	private String defTerrId;
	private String defSalesContactId;
	private String defaultTechSupportId;
	private String defaultSalesGroup;
	
	private String custNo;
	private String sessCustNo;
	private String type;
	
	private List<SalesRep> salesManagerList;
	private List<SalesRep> techManagerList;

	@Override
	public String list() throws Exception {
		List<GetInfoByRegionDTO> infoList = publicService
			.getTerritoryByCustNo(type, custNo, sessCustNo);
		Struts2Util.renderJson(infoList);
		return null;
	}
	
	/**
	 * 根据ship to address获得salesContact和techSupport
	 */
	public String getSalesManagerAndtechSupport() {
		Map<String,Object> rt = new HashMap<String,Object>();
	    Map<String, List<SalesRep>> salesMap = null;
        salesMap =  publicService.searchManager_Cust_Contact(type,sessCustNo);
        if (salesMap != null && salesMap.get("salesManager") != null) {
            List<SalesRep> list = (List<SalesRep>) salesMap.get("salesManager");
            salesManagerList = list;
        }
        if (salesMap != null && salesMap.get("techAcountManager") != null) {
            List<SalesRep> list = (List<SalesRep>) salesMap.get("techAcountManager");
            techManagerList = list;
        }
        rt.put("salesManagerList", salesManagerList);
		rt.put("techManagerList", techManagerList);
		Struts2Util.renderJson(rt);
		return null;    
	        
//		Map<String,Object> rt = new HashMap<String,Object>();
//		String defaultSalesContact = null;
//		String defaultTechSupport = null;
//		String defaultSalesGroup = null;
//		if("customer".equals(type)) {
//			CustomerDTO customerDetail = (CustomerDTO)SessionUtil.getRow("customer", sessCustNo);
//			defaultSalesContact = customerDetail!=null?String.valueOf(customerDetail.getSalesContact()):null;
//			defaultTechSupport = customerDetail!=null?String.valueOf(customerDetail.getTechSupport()):null;
//			defaultSalesGroup = customerDetail!=null?String.valueOf(customerDetail.getSalesGroup()):null;
//		} else if("contact".equals(type)) {
//			ContactModelDTO contact = (ContactModelDTO)SessionUtil.getRow("contact",sessCustNo);
//			defaultSalesContact = contact!=null?String.valueOf(contact.getSalesContact()):null;
//			defaultTechSupport = contact!=null?String.valueOf(contact.getTechSupport()):null;
//			defaultSalesGroup = contact!=null?String.valueOf(contact.getSalesGroup()):null;
//		}
//		Integer salesTerrId = null;
//		Integer techTerrId = null;
//		List<GetInfoByRegionDTO> infoList = publicService.getTerritoryByCustNo(type, String.valueOf(custNo), sessCustNo);
//		if(infoList!=null) {
//			for(GetInfoByRegionDTO getInfoByRegionDTO:infoList) {
//				if(salesTerrId==null&&"SALES_CONTACT".equals(getInfoByRegionDTO.getTerritoryClassification())) {
//					salesTerrId = Integer.parseInt(getInfoByRegionDTO.getId());
//				}
//				if(techTerrId==null&&"TECH_SUPPORT".equals(getInfoByRegionDTO.getTerritoryClassification())) {
//					techTerrId = Integer.parseInt(getInfoByRegionDTO.getId());
//				}
//				if(salesTerrId!=null&&techTerrId!=null) {
//					break;
//				}
//			}
//		}
//		Map<String, Object> salesMap = null;
//		salesMap = publicService.searchSalesByTerritory(salesTerrId==null?null:String.valueOf(salesTerrId),defaultSalesContact,defaultSalesGroup);
//		if(salesMap!=null&&salesMap.get("salesRepListBySearch")!=null) {
//			List<SalesRep> list =(List<SalesRep>)salesMap.get("salesRepListBySearch");
//			salesManagerList = list;
//		}
//		salesMap = publicService.searchSalesByTerritory(techTerrId==null?null:String.valueOf(techTerrId),defaultTechSupport,defaultSalesGroup);
//		if(salesMap!=null&&salesMap.get("salesRepListBySearch")!=null) {
//			List<SalesRep> list =(List<SalesRep>)salesMap.get("salesRepListBySearch");
//			techManagerList = list;
//		}
//		rt.put("salesManagerList", salesManagerList);
//		rt.put("techManagerList", techManagerList);
//		Struts2Util.renderJson(rt);
//		return null;
	}
	
	/**
	 * 通过TerritoryId查询sales及sales对应group相关信息
	 * @author zhang yong
	 * @return
	 */
//	public String searchSalesByTerritory () {
//		Map<String, Object> salesMap = publicService
//			.searchSalesByTerritory(salesTerrId, defSalesContactId, 
//					defaultTechSupportId, defaultSalesGroup);
//		Struts2Util.renderJson(salesMap); 
//		return null;
//	}
	
	/**
	 * 通过salesId查询其对应group信息
	 * @return
	 */
	public String searchGroupBySalesId () {
		SalesGroup salesGroup = publicService.searchGroupBySalesId(defSalesContactId);
		Struts2Util.renderJson(salesGroup);
		return null;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getSalesTerrId() {
		return salesTerrId;
	}

	public void setSalesTerrId(String salesTerrId) {
		this.salesTerrId = salesTerrId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDefTerrId() {
		return defTerrId;
	}

	public void setDefTerrId(String defTerrId) {
		this.defTerrId = defTerrId;
	}

	public String getDefSalesContactId() {
		return defSalesContactId;
	}

	public void setDefSalesContactId(String defSalesContactId) {
		this.defSalesContactId = defSalesContactId;
	}

	public String getDefaultTechSupportId() {
		return defaultTechSupportId;
	}

	public void setDefaultTechSupportId(String defaultTechSupportId) {
		this.defaultTechSupportId = defaultTechSupportId;
	}

	public String getDefaultSalesGroup() {
		return defaultSalesGroup;
	}

	public void setDefaultSalesGroup(String defaultSalesGroup) {
		this.defaultSalesGroup = defaultSalesGroup;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getSessCustNo() {
		return sessCustNo;
	}

	public void setSessCustNo(String sessCustNo) {
		this.sessCustNo = sessCustNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SalesRep> getSalesManagerList() {
		return salesManagerList;
	}

	public void setSalesManagerList(List<SalesRep> salesManagerList) {
		this.salesManagerList = salesManagerList;
	}

	public List<SalesRep> getTechManagerList() {
		return techManagerList;
	}

	public void setTechManagerList(List<SalesRep> techManagerList) {
		this.techManagerList = techManagerList;
	}


}
