package com.genscript.gsscm.systemsetting.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.PbDropdownListDao;
import com.genscript.gsscm.basedata.entity.PbDropdownList;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.system.dao.DepartmentSystemDao;
import com.genscript.gsscm.system.entity.DepartmentSystem;

/**
 * 
 * @Description:Sales Resource class
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
@Service
@Transactional
public class SalesResourceService {
	@Autowired
	private SalesRepDao salesRepDao;
	@Autowired
	private DepartmentSystemDao departmentSystemDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PbDropdownListDao pbDropdownListDao;
	
	/**
	 * 根据条件查找SalesRep并分页显示
	 * @param salesRepPage 分页对象
	 * @return
	 */
	public Page<SalesRep> searchSalesRepPage(Page<SalesRep> salesRepPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesRep> pagerUtil = new PagerUtil<SalesRep>();
		salesRepPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!salesRepPage.isOrderBySetted()) {
			salesRepPage.setOrderBy("salesId");
			salesRepPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		salesRepPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			salesRepPage = this.salesRepDao.getAll(salesRepPage);
		} else {
			salesRepPage = this.salesRepDao.findPage(salesRepPage, filters);
		}
		if(salesRepPage!=null&&salesRepPage.getResult()!=null) {
			for(SalesRep salesRep:salesRepPage.getResult()) {
				if(salesRep.getDeptId()!=null) {
					DepartmentSystem dep = departmentSystemDao.getById(salesRep.getDeptId());
					salesRep.setDeptName(dep==null?"":dep.getName());
				}
				if(salesRep.getModifiedBy()!=null) {
					User user = this.userDao.getById(salesRep.getModifiedBy());
					salesRep.setModifiedName(user==null?"":user.getLoginName());
				}
				PbDropdownListOptions option = this.getDropdownOpt(DropDownListName.SALES_REP_FUNCTION.value(), salesRep.getFunction());
				salesRep.setFunctionText(option!=null?option.getText():null);
			}
		}
		
		return salesRepPage;
	}
	
	/**
	 * 根据salesId查找SalesRep对象
	 * @param salesId
	 */
	public SalesRep findById(Integer salesId) {
		SalesRep salesRep = salesRepDao.getById(salesId);
		if(salesRep != null && salesRep.getDeptId() != null){
			DepartmentSystem departmentSystem = departmentSystemDao.getById(salesRep.getDeptId());
			if(departmentSystem != null && departmentSystem.getName() != null){
				salesRep.setDeptName(departmentSystem.getName());
			}
		}
		return salesRep;
	}
	/**
	 * 保存SalesRep对象
	 */
	public void saveSalesRep(SalesRep salesRep) {
		this.salesRepDao.save(salesRep);
	}
	
	/**
	 * 删除SalesRep对象
	 */
	public void deleteSalesRep(String allChoiceVal) {
		if(allChoiceVal!=null) {
			for(String salesId:allChoiceVal.split("-")) {
				this.salesRepDao.delete(Integer.parseInt(salesId));
			}
		}
	}
	/**
	 * 获取所有Department
	 */
	public List<DepartmentSystem> getAllDep() {
		return this.departmentSystemDao.getAll();
	}
	
	/**
	 * 根据条件查找User并分页显示
	 * @param usersPage 分页对象
	 */
	public Page<User> searchUserPage(Page<User> usersPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<User> pagerUtil = new PagerUtil<User>();
		usersPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!usersPage.isOrderBySetted()) {
			usersPage.setOrderBy("id");
			usersPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		usersPage.setPageSize(10);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			usersPage = this.userDao.getAll(usersPage);
		} else {
			usersPage = this.userDao.searchUser(usersPage, filters);
		}
		if(usersPage!=null&&usersPage.getResult()!=null) {
			for(User user:usersPage.getResult()) {
				if(user.getDeptId()!=null) {
					DepartmentSystem departmentSystem = departmentSystemDao.getById(user.getDeptId());
					user.setDeptName(departmentSystem==null?"":departmentSystem.getName());
				}
				
			}
		}
		return usersPage;
	}
	
	public PbDropdownListOptions getDropdownOpt(String listName,String optValue) {
		PbDropdownList pbDropdownList = pbDropdownListDao.findUniqueBy(
				"listName", listName);
		if(pbDropdownList!=null&&pbDropdownList.getPbOptionItems()!=null) {
			for(PbDropdownListOptions option :pbDropdownList.getPbOptionItems()) {
				if(option.getValue().equals(optValue)) {
					return option;
				}
			}
		}
		return null;
	}

}
