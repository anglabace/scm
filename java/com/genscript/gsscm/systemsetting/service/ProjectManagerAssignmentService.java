package com.genscript.gsscm.systemsetting.service;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dao.SalesProjectManagerAssignmentDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.entity.SalesProjectManagerAssignment;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.serv.dao.ServiceClassificationDao;
import com.genscript.gsscm.serv.entity.ServiceClassification;

@Service
@Transactional
public class ProjectManagerAssignmentService {
	@Autowired
	private SalesProjectManagerAssignmentDao salesProjectManagerAssignmentDao;
	@Autowired
	private ServiceClassificationDao serviceClassificationDao;
	@Autowired
	private SalesRepDao salesRepDao;
	/**
	 * 根据条件查找SalesRepBean并分页显示
	 * @param salesRepBeanPage 分页对象
	 * @return
	 */
	public Page<SalesProjectManagerAssignment> searchsalesProjectManagerAssignmentPage(Page<SalesProjectManagerAssignment> salesProjectManagerAssignmentPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesProjectManagerAssignment> pagerUtil = new PagerUtil<SalesProjectManagerAssignment>();
		salesProjectManagerAssignmentPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!salesProjectManagerAssignmentPage.isOrderBySetted()) {
			salesProjectManagerAssignmentPage.setOrderBy("assignId");
			salesProjectManagerAssignmentPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		salesProjectManagerAssignmentPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			salesProjectManagerAssignmentPage = this.salesProjectManagerAssignmentDao.getAll(salesProjectManagerAssignmentPage);
		} else {
			String resourceName = Struts2Util.getRequest().getParameter("filter_LIKES_resourceName");
			List<Integer> salesIdList = null;
			if(StringUtils.isNotEmpty(resourceName)) {
				salesIdList = salesRepDao.findByResourceName(resourceName);
			}
			salesProjectManagerAssignmentPage = this.salesProjectManagerAssignmentDao.findPageBySomeFilter(salesProjectManagerAssignmentPage, filters,salesIdList);
		}
		if(salesProjectManagerAssignmentPage!=null&&salesProjectManagerAssignmentPage.getResult()!=null) {
			for(SalesProjectManagerAssignment salesProjectManagerAssignment:salesProjectManagerAssignmentPage.getResult()) {
				if(salesProjectManagerAssignment.getServiceType()!=null) {
					ServiceClassification serviceClassification = serviceClassificationDao.getById(salesProjectManagerAssignment.getServiceType());
					salesProjectManagerAssignment.setServiceClassification(serviceClassification);
				}
				if(salesProjectManagerAssignment.getSalesId()!=null) {
					SalesRep salesRep = this.salesRepDao.getById(salesProjectManagerAssignment.getSalesId());
					salesProjectManagerAssignment.setSalesRep(salesRep);
				}
			}
		}
		return salesProjectManagerAssignmentPage;
	}
	
	/**
	 * 根据id查找sales_project_manager_assignment
	 */
	public SalesProjectManagerAssignment findById(Integer assignId) {
		SalesProjectManagerAssignment salesProjectManagerAssignment = this.salesProjectManagerAssignmentDao.getById(assignId);
		if(salesProjectManagerAssignment.getServiceType()!=null) {
			ServiceClassification serviceClassification = serviceClassificationDao.getById(salesProjectManagerAssignment.getServiceType());
			salesProjectManagerAssignment.setServiceClassification(serviceClassification);
		}
		if(salesProjectManagerAssignment.getSalesId()!=null) {
			SalesRep salesRep = this.salesRepDao.getById(salesProjectManagerAssignment.getSalesId());
			salesProjectManagerAssignment.setSalesRep(salesRep);
			if(salesRep!=null) {
				salesProjectManagerAssignment.setResourceName(salesRep.getResourceName());
			}
		}
		return salesProjectManagerAssignment;
	}
	
	/**
	 * 保存或更新sales_project_manager_assignment
	 */
	public void saveAssignment(SalesProjectManagerAssignment salesProjectManagerAssignment) throws Exception{
		this.salesProjectManagerAssignmentDao.save(salesProjectManagerAssignment);
	}
	
	/**
	 * 删除所有选中的sales_project_manager_assignment
	 */
	public void deleteAssignments(String allChoiceVal) {
		if(allChoiceVal!=null) {
			for(String assignId:allChoiceVal.split("-")) {
				this.salesProjectManagerAssignmentDao.delete(Integer.parseInt(assignId));
			}
		}
	}
	
	/**
	 * 查找出所有ServiceClassification
	 */
	public List<ServiceClassification> getAllServiceClassification() {
		return this.serviceClassificationDao.findAll();
	}

}
