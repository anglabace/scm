package com.genscript.gsscm.basedata.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.Organization;

public class GetLocJsonAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6475840852538616606L;

	@Autowired
	private PublicService publicService;
	private int orgId;
	private int divId;
	private int deptId;

	public String getOrgLocJson() throws Exception {
		Organization org = publicService.getOrgLocation(orgId);
		Struts2Util.renderJson(org);
		return null;
	}

	public String getDivLocJson() throws Exception {
		Divisions div = publicService.getDivLocation(divId);
		Struts2Util.renderJson(div);
		return null;
	}

	public String getDeptLocJson() throws Exception {
		Departments dep = publicService.getDeptLocation(deptId);
		Struts2Util.renderJson(dep);
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
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

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getDivId() {
		return divId;
	}

	public void setDivId(int divId) {
		this.divId = divId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
}
