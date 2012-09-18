package com.genscript.gsscm.systemsetting.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.manufacture.dao.ManufacturingClerksBeanDao;
import com.genscript.gsscm.manufacture.dao.ManufacturingClerksDao;
import com.genscript.gsscm.manufacture.dao.WorkCenterDao;
import com.genscript.gsscm.manufacture.dao.WorkGroupDao;
import com.genscript.gsscm.manufacture.entity.ManufacturingClerks;
import com.genscript.gsscm.manufacture.entity.ManufacturingClerksBean;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;

@Service
@Transactional
public class ManufacturingClerksService {
	@Autowired
	private ManufacturingClerksBeanDao manufacturingBeanDao;
	@Autowired
	private ManufacturingClerksDao manufacturingDao;
	@Autowired
	private WorkCenterDao workCenterDao;
	@Autowired
	private WorkGroupDao workGroupDao;
	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据条件查找ManufacturingClerks并分页显示
	 * @param manClerksPage 分页对象
	 * @return
	 */
	public Page<ManufacturingClerksBean> searchManClerksPage(Page<ManufacturingClerksBean> manClerksPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ManufacturingClerksBean> pagerUtil = new PagerUtil<ManufacturingClerksBean>();
		manClerksPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!manClerksPage.isOrderBySetted()) {
			manClerksPage.setOrderBy("id");
			manClerksPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		manClerksPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			manClerksPage = this.manufacturingBeanDao.getAll(manClerksPage);
		} else {
			manClerksPage = this.manufacturingBeanDao.findPageByFilter(manClerksPage, filters);
		}
		return manClerksPage;
	}
	
	/**
	 * 根据clerkId查找ManufacturingClerks对象
	 * @param clerkId 
	 */
	public ManufacturingClerks findById(Integer id) {
		ManufacturingClerks manufacturingClerks = this.manufacturingDao.getById(id);
		if(manufacturingClerks!=null&&manufacturingClerks.getWorkCenterId()!=null) {
			WorkCenter workCenter = this.workCenterDao.getById(manufacturingClerks.getWorkCenterId());
			if(workCenter!=null&&workCenter.getSupervisor()!=null) {
				User user = this.userDao.getById(workCenter.getSupervisor());
				manufacturingClerks.setWorkCenterSupervisor(user!=null?user.getLoginName():"");
			}
		}
		if(manufacturingClerks!=null&&manufacturingClerks.getWorkGroupId()!=null) {
			WorkGroup workGroup = this.workGroupDao.getById(manufacturingClerks.getWorkGroupId());
			if(workGroup!=null&&workGroup.getSupervisor()!=null) {
				User user = this.userDao.getById(workGroup.getSupervisor());
				manufacturingClerks.setWorkGroupSupervisor(user!=null?user.getLoginName():"");
			}
			
		}
		if(manufacturingClerks!=null&&manufacturingClerks.getClerkId()!=null) {
			User user = this.userDao.getById(manufacturingClerks.getClerkId());
			manufacturingClerks.setClerkName(user!=null?user.getLoginName():"");
		}
		return manufacturingClerks;
	}
	
	/**
	 * 保存ManufacturingClerks对象
	 */
	public void saveManufacturingClerks(ManufacturingClerks manufacturingClerks) throws Exception{
		this.manufacturingDao.save(manufacturingClerks);
	}
	
	/**
	 * 根据id删除ManufacturingClerks
	 * @param allChoiceVal 所有被选中的ManufacturingClerks
	 */
	public void deleteManufacturingClerks(String allChoiceVal) throws Exception{
		
		if(allChoiceVal!=null) {
			for(String templateId:allChoiceVal.split(",")) {
				this.manufacturingDao.delete(Integer.parseInt(templateId));
			}
		}
	}
	
	/**
	 * 根据workCenter获取workCenterSupervisor
	 * @param workCenterId
	 */
	public String getSupervisorByCenterId(Integer workCenterId) {
		WorkCenter workCenter = this.workCenterDao.getById(workCenterId);
		System.out.println(workCenter.getSupervisor());
		if(workCenter!=null&&workCenter.getSupervisor()!=null) {
			User user = this.userDao.getById(workCenter.getSupervisor());
			return user.getLoginName();
		}
		return null;
	}
	
	/**
	 * 根据workGroup获取workGroupSupervisor
	 * @param workGroupId
	 */
	public String getSupervisorByGroupId(Integer workGroupId) {
		WorkGroup workGroup = this.workGroupDao.getById(workGroupId);
		if(workGroup!=null&&workGroup.getSupervisor()!=null) {
			User user = this.userDao.getById(workGroup.getSupervisor());
			return user!=null?user.getLoginName():null;
		}
		return null;
	}
	
	/**
	 * 查找所有workcenter
	 */
	public List<WorkCenter> findAllWorkCenter() {
		return this.workCenterDao.getAll();
	}
	
	/**
	 * 查找所有workgroup
	 */
	public List<WorkGroup> findAllWorkGroup() {
		return this.workGroupDao.findAll();
	}
	
	/**
	 * 根据workcenterid查找workgroup
	 * @param workCenterId 工作中心ID
	 */
	public List<WorkGroup> findByCenterId(Integer workCenterId) {
		return this.workGroupDao.getGroupListByCenter(workCenterId);
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
			usersPage = this.userDao.searchClerkUser(usersPage, filters);
		}
		return usersPage;
	}

}
