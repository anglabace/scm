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
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dao.SalesTerritoryAssignProcessDao;
import com.genscript.gsscm.customer.dao.SalesTerritoryAssignRulesDao;
import com.genscript.gsscm.customer.dto.SalesTerritoryAssignRulesDTO;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignProcess;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignRules;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;

/**
 * 
 * @Description:Assignment Rules service class
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date:   2011-02-10
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Service
@Transactional
public class AssignmentRulesService {
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private SalesTerritoryAssignRulesDao salesTerritoryAssignRulesDao;
	@Autowired
	private SalesTerritoryAssignProcessDao salesTerritoryAssignProcessDao;
	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据条件查找SalesTerritoryAssignRules并分页显示
	 * @param salesTerritoryAssignRulesPage 分页对象
	 * @return
	 */
	public Page<SalesTerritoryAssignRules> searchSalesTerritoryAssignRulesPage(Page<SalesTerritoryAssignRules> salesTerritoryAssignRulesPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesTerritoryAssignRules> pagerUtil = new PagerUtil<SalesTerritoryAssignRules>();
		salesTerritoryAssignRulesPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!salesTerritoryAssignRulesPage.isOrderBySetted()) {
			salesTerritoryAssignRulesPage.setOrderBy("ruleId");
			salesTerritoryAssignRulesPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		salesTerritoryAssignRulesPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			salesTerritoryAssignRulesPage = this.salesTerritoryAssignRulesDao.getAll(salesTerritoryAssignRulesPage);
		} else {
			salesTerritoryAssignRulesPage = this.salesTerritoryAssignRulesDao.findPage(salesTerritoryAssignRulesPage, filters);
		}
		return salesTerritoryAssignRulesPage;
	}
	
	/**
	 * 根据条件查找SalesTerritoryAssignProcess并分页显示
	 * @param processPage 分页对象
	 * @return
	 */
	public Page<SalesTerritoryAssignProcess> searchProcessPage(Page<SalesTerritoryAssignProcess> processPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesTerritoryAssignProcess> pagerUtil = new PagerUtil<SalesTerritoryAssignProcess>();
		processPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!processPage.isOrderBySetted()) {
			processPage.setOrderBy("sequence");
			processPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		processPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			processPage = this.salesTerritoryAssignProcessDao.getAll(processPage);
		} else {
			processPage = this.salesTerritoryAssignProcessDao.findPage(processPage, filters);
		}
		if(processPage!=null&&processPage.getResult()!=null) {
			for(SalesTerritoryAssignProcess process:processPage.getResult()) {
				if(process.getModifiedBy()!=null) {
					User user = this.userDao.getById(process.getModifiedBy());
					process.setModifiedName(user==null?"":user.getLoginName());
				}
			}
		}
		return processPage;
	}
	
	/**
	 * 根据ruleId查找SalesTerritoryAssignRulesDTO对象
	 * @param ruleId
	 */
	public SalesTerritoryAssignRulesDTO findById(Integer ruleId) {
		SalesTerritoryAssignRules salesTerritoryAssignRules = this.salesTerritoryAssignRulesDao.getById(ruleId);
		SalesTerritoryAssignRulesDTO salesTerritoryAssignRulesDTO = this.dozer.map(salesTerritoryAssignRules, SalesTerritoryAssignRulesDTO.class);
		if(salesTerritoryAssignRulesDTO!=null&&salesTerritoryAssignRulesDTO.getRuleId()!=null) {
			List<SalesTerritoryAssignProcess> assignProcessList = this.salesTerritoryAssignProcessDao.findByRuleId(ruleId);
			salesTerritoryAssignRulesDTO.setAssignProcessList(assignProcessList);
		}
		return salesTerritoryAssignRulesDTO;
	}
	
	/**
	 * 保存SalesTerritoryAssignRules对象以及相关信息
	 */
	public void saveAssignRules(SalesTerritoryAssignRulesDTO salesTerritoryAssignRulesDTO,String sessionId) {
		if(salesTerritoryAssignRulesDTO.getRuleId()!=null) {
			this.salesTerritoryAssignProcessDao.deleteByRuleId(salesTerritoryAssignRulesDTO.getRuleId());
		}
		SalesTerritoryAssignRules salesTerritoryAssignRules = this.dozer.map(salesTerritoryAssignRulesDTO, SalesTerritoryAssignRules.class);
		this.salesTerritoryAssignRulesDao.save(salesTerritoryAssignRules);
		if(salesTerritoryAssignRulesDTO.getAssignProcessList()!=null
				&&salesTerritoryAssignRulesDTO.getAssignProcessList().size()>0) {
			for(SalesTerritoryAssignProcess salesTerritoryAssignProcess:salesTerritoryAssignRulesDTO.getAssignProcessList()) {
				salesTerritoryAssignProcess.setRuleId(salesTerritoryAssignRules.getRuleId());
				this.salesTerritoryAssignProcessDao.save(salesTerritoryAssignProcess);
			}
		}
	}
	
	/**
	 * 删除SalesTerritoryAssignRules对象
	 */
	public void deleteAssignRules(String allChoiceVal) {
		if(allChoiceVal!=null) {
			for(String ruleId:allChoiceVal.split("-")) {
				this.salesTerritoryAssignRulesDao.delete(Integer.parseInt(ruleId));
				this.salesTerritoryAssignProcessDao.deleteByRuleId(Integer.parseInt(ruleId));
			}
		}
	}
	
	
	public SalesTerritoryAssignProcess findProcessById(Integer sequence) {
		return this.salesTerritoryAssignProcessDao.getById(sequence);
	}
	
	/**
	 * 删除SalesTerritoryAssignProcess对象(内存中删除)
	 */
	public void deleteAssignProcess(String allChoiceVal,String sessionId) {
		Map<String,SalesTerritoryAssignProcess> assignProcessMap = (Map<String,SalesTerritoryAssignProcess>)SessionUtil.getRow(SessionConstant.AssignProcessList.value(), sessionId);
		SalesTerritoryAssignRulesDTO salesTerritoryAssignRulesDTO = (SalesTerritoryAssignRulesDTO)SessionUtil.getRow(SessionConstant.AssignmentRules.value(), sessionId);
		if(allChoiceVal!=null) {
			for(String sequence:allChoiceVal.split("-")) {
				if(assignProcessMap.containsKey(sequence)) {
					assignProcessMap.remove(sequence);
				}
			}
		}
		SessionUtil.updateRow(SessionConstant.AssignProcessList.value(), sessionId, assignProcessMap);
		SessionUtil.updateRow(SessionConstant.AssignmentRules.value(), sessionId, salesTerritoryAssignRulesDTO);
	}

}
