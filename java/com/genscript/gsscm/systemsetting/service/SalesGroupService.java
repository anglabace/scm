package com.genscript.gsscm.systemsetting.service;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.PbDropdownListDao;
import com.genscript.gsscm.basedata.entity.PbDropdownList;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dao.SalesGroupDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.dto.SalesGroupDTO;
import com.genscript.gsscm.customer.entity.SalesGroup;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.system.dao.DepartmentSystemDao;
import com.genscript.gsscm.system.entity.DepartmentSystem;

@Service
@Transactional
public class SalesGroupService {
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private SalesGroupDao salesGroupDao;
	@Autowired
	private SalesRepDao salesRepDao;
	@Autowired
	private DepartmentSystemDao departmentSystemDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PbDropdownListDao pbDropdownListDao;
	
	
	/**
	 * 根据条件查找SalesGroup并分页显示
	 * @param salesGroupPage 分页对象
	 * @return
	 */
	public Page<SalesGroup> searchSalesGroupPage(Page<SalesGroup> salesSalesGroupPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesGroup> pagerUtil = new PagerUtil<SalesGroup>();
		salesSalesGroupPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!salesSalesGroupPage.isOrderBySetted()) {
			salesSalesGroupPage.setOrderBy("groupId");
			salesSalesGroupPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		salesSalesGroupPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			salesSalesGroupPage = this.salesGroupDao.getAll(salesSalesGroupPage);
		} else {
			salesSalesGroupPage = this.salesGroupDao.findPage(salesSalesGroupPage, filters);
		}
		if(salesSalesGroupPage!=null&&salesSalesGroupPage.getResult()!=null) {
			for(SalesGroup salesGroup:salesSalesGroupPage.getResult()) {
				if(salesGroup.getDeptId()!=null) {
					DepartmentSystem dep = departmentSystemDao.getById(salesGroup.getDeptId());
					salesGroup.setDeptName(dep==null?"":dep.getName());
				}
				if(salesGroup.getSupervisor()!=null) {
					User user = this.userDao.getById(salesGroup.getSupervisor());
					salesGroup.setSupervisorName(user==null?"":user.getLoginName());
				}
			}
		}
		return salesSalesGroupPage;
	}
	
	/**
	 * 根据groupId查找SalesGroupDTO对象
	 * @param groupId
	 */
	public SalesGroupDTO findById(Integer groupId) {
		SalesGroup salesGroup = this.salesGroupDao.getById(groupId);
		if(salesGroup.getSupervisor()!=null) {
			User user = this.userDao.getById(salesGroup.getSupervisor());
			salesGroup.setSupervisorName(user==null?"":user.getLoginName());
		}
		SalesGroupDTO salesGroupDTO = this.dozer.map(salesGroup, SalesGroupDTO.class);
		if(salesGroupDTO!=null&&salesGroupDTO.getGroupId()!=null) {
			List<SalesRep> salesResourceList = this.salesRepDao.findByGroupId(groupId);
			for(SalesRep salesRep:salesResourceList) {
				if(salesRep.getDeptId()!=null) {
					DepartmentSystem dep = departmentSystemDao.getById(salesRep.getDeptId());
					salesRep.setDeptName(dep==null?"":dep.getName());
				}
//				PbDropdownListOptions option = this.getDropdownOpt(DropDownListName.SALES_REP_FUNCTION.value(), salesRep.getFunction());
//				salesRep.setFunctionText(option!=null?option.getText():null);
			}
			salesGroupDTO.setResourceList(salesResourceList);
		}
		return salesGroupDTO;
	}
	
	/**
	 * 保存SalesGroup对象以及相关信息
	 */
	public void saveSalesGroup(SalesGroupDTO salesGroupDTO,String sessionId) {
		if(salesGroupDTO.getGroupId()!=null) {
			this.salesRepDao.deleteByGroupId(salesGroupDTO.getGroupId());
		}
		SalesGroup salesGroup = this.dozer.map(salesGroupDTO, SalesGroup.class);
		this.salesGroupDao.save(salesGroup);
		if(salesGroupDTO.getResourceList()!=null
				&&salesGroupDTO.getResourceList().size()>0) {
			for(SalesRep salesRep:salesGroupDTO.getResourceList()) {
				salesRep.setSalesGroupId(salesGroup.getGroupId());
				this.salesRepDao.save(salesRep);
			}
		}
	}
	
	/**
	 * 删除SalesGroup对象
	 */
	public void deleteSalesGroups(String allChoiceVal) {
		if(allChoiceVal!=null) {
			for(String groupId:allChoiceVal.split("-")) {
				this.salesGroupDao.delete(Integer.parseInt(groupId));
				this.salesRepDao.deleteByGroupId(Integer.parseInt(groupId));
			}
		}
	}
	
	/**
	 * 删除SalesRep对象(内存中删除)
	 */
	public void deleteSalesRep(String allChoiceVal,String sessionId) {
		Map<String,SalesRep> salesResourceMap = (Map<String,SalesRep>)SessionUtil.getRow(SessionConstant.SalesResourceList.value(), sessionId);
		SalesGroupDTO salesGroupDTO = (SalesGroupDTO)SessionUtil.getRow(SessionConstant.SalesGroup.value(), sessionId);
		if(allChoiceVal!=null) {
			for(String salesId:allChoiceVal.split(",")) {
				if(salesResourceMap.containsKey(salesId)) {
					salesResourceMap.remove(salesId);
				}
			}
		}
		SessionUtil.updateRow(SessionConstant.SalesResourceList.value(), sessionId, salesResourceMap);
		SessionUtil.updateRow(SessionConstant.SalesGroup.value(), sessionId, salesGroupDTO);
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
		return usersPage;
	}
	
	
	/**
	 * 获取所有Department
	 */
	public List<DepartmentSystem> getAllDep() {
		return this.departmentSystemDao.getAll();
	}
	
//	public PbDropdownListOptions getDropdownOpt(String listName,String optValue) {
//		PbDropdownList pbDropdownList = pbDropdownListDao.findUniqueBy(
//				"listName", listName);
//		if(pbDropdownList!=null&&pbDropdownList.getPbOptionItems()!=null) {
//			for(PbDropdownListOptions option :pbDropdownList.getPbOptionItems()) {
//				if(option.getValue().equals(optValue)) {
//					return option;
//				}
//			}
//		}
//		return null;
//	}

}
