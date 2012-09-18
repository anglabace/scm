package com.genscript.gsscm.systemsetting.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.genscript.gsscm.systemsetting.service.SalesResourceService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Description:Sales Resource control class
 * @Copyright: Copyright genscrpit (c)2011
 * @author: lizhang
 * @version: 1.0
 * @date:   2011-02-12
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Results({
	@Result(name = "sales_resources", location = "systemsetting/SalesTerritories/sales_resources.jsp"),
	@Result(name = "sales_resource_list", location = "systemsetting/SalesTerritories/sales_resource_list.jsp"),
	@Result(name = "sales_resource_detail", location = "systemsetting/SalesTerritories/sales_resource_detail.jsp"),
	@Result(name = "sales_resource_select", location = "systemsetting/SalesTerritories/sales_resource_select.jsp")
})
public class SalesResourceAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1334232522669691645L;

	/**自动装载实例**/
	@Autowired
	private SalesResourceService salesResourceService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ExceptionService exceptionUtil;
	
	/**action变量**/
	private Integer salesId;
	private SalesRep salesRep;
	private String allChoiceVal;
	private Page<SalesRep> salesRepPage;
	private Page<User> usersPage;
	private List<DepartmentSystem> depList;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;//function 
	
	/*****************************action method******************************************/
	public String input() {
		this.dropDownList();
		return "sales_resources";
	}
	public String list() {
		salesRepPage = this.salesResourceService.searchSalesRepPage(salesRepPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				salesRepPage);
		return "sales_resource_list";
	}
	public String load() {
		this.dropDownList();
		if(salesId!=null) {
			/**修改**/
			salesRep = this.salesResourceService.findById(salesId);
		}
		if(salesRep==null) {
			/**添加**/
			salesRep = new SalesRep();
			salesRep.setGsCoId(1);
			salesRep.setCreatedBy(SessionUtil.getUserId());
			salesRep.setCreationDate(new Date());
		}
		salesRep.setModifyDate(new Date());
		salesRep.setModifiedBy(SessionUtil.getUserId());
		salesRep.setModifiedName(SessionUtil.getUserName());
		return "sales_resource_detail";
	}
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.salesResourceService.saveSalesRep(salesRep);
			rt.put("message", "Save success!");
		}  catch (Exception ex) {
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
			this.salesResourceService.deleteSalesRep(allChoiceVal);
			rt.put("message", "Delete success!");
		}  catch (Exception ex) {
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
	
	/**
	 * 选择人员
	 */
	public String selectUser() {
		usersPage = this.salesResourceService.searchUserPage(usersPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				usersPage);
		return "sales_resource_select";
	}
	/*******************private method***********************************************/
	/**
	 * 给页面下拉列表框赋值
	 */
	private void dropDownList() {
		depList = this.salesResourceService.getAllDep();
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.SALES_REP_FUNCTION);
		dropDownMap = publicService.getDropDownMap(listName);
	}
	/*******************getter setter***************************************************/
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}
	public SalesRep getSalesRep() {
		return salesRep;
	}
	public void setSalesRep(SalesRep salesRep) {
		this.salesRep = salesRep;
	}
	public Page<SalesRep> getSalesRepPage() {
		return salesRepPage;
	}
	public void setSalesRepPage(Page<SalesRep> salesRepPage) {
		this.salesRepPage = salesRepPage;
	}
	public List<DepartmentSystem> getDepList() {
		return depList;
	}
	public void setDepList(List<DepartmentSystem> depList) {
		this.depList = depList;
	}
	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}
	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}
	public String getAllChoiceVal() {
		return allChoiceVal;
	}
	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}
	public Page<User> getUsersPage() {
		return usersPage;
	}
	public void setUsersPage(Page<User> usersPage) {
		this.usersPage = usersPage;
	}
	
	
}
