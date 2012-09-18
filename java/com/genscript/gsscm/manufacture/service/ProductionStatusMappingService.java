package com.genscript.gsscm.manufacture.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.manufacture.dao.OperationDao;
import com.genscript.gsscm.manufacture.dao.ProductionStatusMappingDao;
import com.genscript.gsscm.manufacture.dao.WorkCenterDao;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.ProductionStatusMapping;
import com.genscript.gsscm.manufacture.entity.WorkCenter;

@Service
@Transactional
public class ProductionStatusMappingService {
	
	@Autowired
	private ProductionStatusMappingDao productionStatusMappingDao;
	@Autowired
	private WorkCenterDao workCenterDao;
	@Autowired
	private OperationDao operationDao;
	
	/**
	 * 获取workcenter分页对象
	 * @param page workcenter分页对象
	 * @return Page<WorkCenter>
	 */
	public Page<ProductionStatusMapping> search(Page<ProductionStatusMapping> page){
		PagerUtil<ProductionStatusMapping> pagerUtil = new PagerUtil<ProductionStatusMapping>();
		page = pagerUtil.getRequestPage();// 获得分页请求相关数据：如第几页
		if (!page.isOrderBySetted()) {// 设置默认排序
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page.setPageSize(20);// 设置默认每页显示记录条数
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());//搜索条件
		if (filters == null) {
			filters = new ArrayList<PropertyFilter>();
		}
		String status = Struts2Util.getParameter("status");
		String wordId = Struts2Util.getParameter("wordId");
		if(wordId!=null){
			PropertyFilter wordIdFilter = new PropertyFilter("EQI_workCenterId", wordId);
			filters.add(wordIdFilter);
		}
		if (status==null) {
			PropertyFilter typeFilter = new PropertyFilter("EQS_status", "ACTIVE");
			filters.add(typeFilter);
		}else {
			if(!status.equals("All")){
			PropertyFilter typeFilter = new PropertyFilter("EQS_status", status);
			filters.add(typeFilter);
			}
		}
		page = this.productionStatusMappingDao.findPageForCenter(page,filters,true);
		if (page != null && page.getResult() != null
				&& page.getResult().size() > 0) {
			for (ProductionStatusMapping productionStatusMapping : page.getResult()) {
				if (productionStatusMapping.getWorkCenterId()!= null) {
					WorkCenter workCenter = this.workCenterDao.get(productionStatusMapping
							.getWorkCenterId());
					if (workCenter != null) {
						productionStatusMapping.setWorkCenterName(workCenter.getName());
					}
				}
			}
		}
		return page;
	}
	
	/**
	 * 删除
	 */
	
	public void delProduction(String productionId) {
		productionStatusMappingDao.delProduction(productionId);
	}
	
	/**
	 * 保存
	 */
	
	public void saveProduction(ProductionStatusMapping productionStatusMapping)throws Exception {
		productionStatusMappingDao.saveProduction(productionStatusMapping);
	}
	
	
	
	/**
	 * 根据ID查询对象
	 */
	public ProductionStatusMapping getProductionStatusMapping(Integer id){
		return this.productionStatusMappingDao.getProductionStatusMapping(id);
	}
	
	
	/**
	 * 更新
	 */
	public void updateProduction(ProductionStatusMapping productionStatusMapping) throws Exception {
		productionStatusMappingDao.updateProduction(productionStatusMapping);
	}
	
	/**
	 * 获得Routing页面Production下拉框内容
	 * @author Zhang Yong
	 * @return
	 */
	public List<Operation> getProductionList (Integer workCenterId) {
		
		
		return operationDao.getOperation(workCenterId);
	}
	

}
