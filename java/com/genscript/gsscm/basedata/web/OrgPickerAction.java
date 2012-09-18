package com.genscript.gsscm.basedata.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.DeptDTO;
import com.genscript.gsscm.customer.dto.DivisionDTO;
import com.genscript.gsscm.customer.dto.OrganizationDTO;

/**
 * 显示org picker
 * 
 * @author zouyulu
 */
@Results({ @Result(name = "org_picker", location = "basedata/org_picker.jsp"),
		@Result(name = "div_picker", location = "basedata/div_picker.jsp"),
		@Result(name = "dept_picker", location = "basedata/dept_picker.jsp") })
public class OrgPickerAction extends BaseAction<Object> {

	private static final long serialVersionUID = 6174330817370744486L;
	@Autowired
	private PublicService publicService;
	private String searchKey;
	private String disableNew;
	private Page<OrganizationDTO> orgPage;
	private List<OrganizationDTO> orgList;
	// division picker parameters
	private List<DivisionDTO> divList;
	private Integer orgId;
	// department picker parameters
	private Integer divId;
	private List<DeptDTO> deptList;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	
	private String shippingInput;

	/**
	 * check Org
	 */
	public String checkOrg() {
		String orgId2 = ServletActionContext.getRequest().getParameter(
				"orgId");
		//System.out.println(orgName);
		Map<String, Object> rt = new HashMap<String, Object>();
		boolean flag=false;
		if(orgId2!=null){
			flag = publicService.checkOrgs(Integer.parseInt(orgId2));
		}
		if (flag) { // 已经存在此organization Name
		//	rt.put("message", "Please enter other Organization name !");
		} else{
			rt.put("message", "ok");
		}
		 Struts2Util.renderJson(rt);
	        return null;
	}

	/**
	 * 选择organization
	 * 
	 * @author zouyulu
	 */
	public String list() throws Exception {
		PagerUtil<OrganizationDTO> pagerUtil = new PagerUtil<OrganizationDTO>();
		orgPage = pagerUtil.getRequestPage();
		if (!orgPage.isOrderBySetted()) {
			orgPage.setOrderBy("name");
			orgPage.setOrder(Page.ASC);
		}
		if (orgPage.getPageSize() == null
				|| orgPage.getPageSize().intValue() < 1) {
			orgPage.setPageSize(20);
		}
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		orgPage = publicService.getOrganizationList(orgPage, filters);
		orgList = orgPage.getResult();
		ServletActionContext.getRequest().setAttribute("pagerInfo", orgPage);
		return "org_picker";
	}

	/**
	 * 选择division
	 * 
	 * @return
	 * @throws Exception
	 * @author zouyulu
	 */
	public String showDivList() throws Exception {
		PagerUtil<DivisionDTO> pagerUtil = new PagerUtil<DivisionDTO>();
		Page<DivisionDTO> divPage = pagerUtil.getRequestPage();
		if (!divPage.isOrderBySetted()) {
			divPage.setOrderBy("name");
			divPage.setOrder(Page.ASC);
		}
		if (divPage.getPageSize() == null
				|| divPage.getPageSize().intValue() < 1) {
			divPage.setPageSize(8);
		}
		PageDTO pagerInfo = pagerUtil.formPage(divPage);
		divPage = publicService.getDivisionList(pagerInfo, searchKey, orgId);
		divList = divPage.getResult();
		pagerInfo = pagerUtil.formPage(divPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "div_picker";
	}

	/**
	 * 选择department
	 * 
	 * @return
	 * @throws Exception
	 * @author zouyulu
	 */
	public String showDeptList() throws Exception {
		// PB_DEPARTMENT select
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.PB_DEPARTMENT);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);

		PagerUtil<DeptDTO> pagerUtil = new PagerUtil<DeptDTO>();
		Page<DeptDTO> deptPage = pagerUtil.getRequestPage();
		if (!deptPage.isOrderBySetted()) {
			deptPage.setOrderBy("name");
			deptPage.setOrder(Page.ASC);
		}
		if (deptPage.getPageSize() == null
				|| deptPage.getPageSize().intValue() < 1) {
			deptPage.setPageSize(8);
		}
		PageDTO pagerInfo = pagerUtil.formPage(deptPage);
		deptPage = publicService
				.getDeptList(pagerInfo, searchKey, orgId, divId);
		deptList = deptPage.getResult();
		pagerInfo = pagerUtil.formPage(deptPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "dept_picker";
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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getDisableNew() {
		return disableNew;
	}

	public void setDisableNew(String disableNew) {
		this.disableNew = disableNew;
	}

	public List<OrganizationDTO> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<OrganizationDTO> orgList) {
		this.orgList = orgList;
	}

	public List<DivisionDTO> getDivList() {
		return divList;
	}

	public void setDivList(List<DivisionDTO> divList) {
		this.divList = divList;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getDivId() {
		return divId;
	}

	public void setDivId(Integer divId) {
		this.divId = divId;
	}

	public List<DeptDTO> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<DeptDTO> deptList) {
		this.deptList = deptList;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public String getShippingInput() {
		return shippingInput;
	}

	public void setShippingInput(String shippingInput) {
		this.shippingInput = shippingInput;
	}
	
	
}
