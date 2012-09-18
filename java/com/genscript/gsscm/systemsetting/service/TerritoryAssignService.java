package com.genscript.gsscm.systemsetting.service;

import java.util.Date;
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
import com.genscript.gsscm.customer.dao.DivisionDao;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.dao.SalesRepBean2Dao;
import com.genscript.gsscm.customer.dao.SalesRepBeanDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.dao.SalesResourceAssignOrgDao;
import com.genscript.gsscm.customer.dao.SalesResourceAssignTerritoryDao;
import com.genscript.gsscm.customer.dao.SalesTerritoryDao;
import com.genscript.gsscm.customer.dto.SalesRepDTO;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.entity.SalesRepBean;
import com.genscript.gsscm.customer.entity.SalesRepBean2;
import com.genscript.gsscm.customer.entity.SalesResourceAssignOrg;
import com.genscript.gsscm.customer.entity.SalesResourceAssignTerritory;
import com.genscript.gsscm.customer.entity.SalesTerritory;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.system.dao.DepartmentSystemDao;
import com.genscript.gsscm.system.entity.DepartmentSystem;

@Service
@Transactional
public class TerritoryAssignService {
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private SalesRepBeanDao salesRepBeanDao;
	@Autowired
	private SalesRepDao salesRepDao;
	@Autowired
	private SalesRepBean2Dao salesRepBean2Dao;
	@Autowired
	private DepartmentSystemDao departmentSystemDao;
	@Autowired
	private SalesResourceAssignOrgDao salesResourceAssignOrgDao;
	@Autowired
	private SalesResourceAssignTerritoryDao salesResourceAssignTerritoryDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private SalesTerritoryDao salesTerritoryDao;
	@Autowired
	private DivisionDao divisionDao;
	@Autowired
	private PbDropdownListDao pbDropdownListDao;
	
	/**
	 * 根据条件查找SalesRepBean并分页显示
	 * @param salesRepBeanPage 分页对象
	 * @return
	 */
	public Page<SalesRepBean> searchSalesRepBeanPage(Page<SalesRepBean> salesRepBeanPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesRepBean> pagerUtil = new PagerUtil<SalesRepBean>();
		salesRepBeanPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!salesRepBeanPage.isOrderBySetted()) {
			salesRepBeanPage.setOrderBy("salesId");
			salesRepBeanPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		salesRepBeanPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			salesRepBeanPage = this.salesRepBeanDao.getAll(salesRepBeanPage);
		} else {
			salesRepBeanPage = this.salesRepBeanDao.findPage(salesRepBeanPage, filters);
		}
		if(salesRepBeanPage!=null&&salesRepBeanPage.getResult()!=null) {
			for(SalesRepBean salesRepBean:salesRepBeanPage.getResult()) {
				StringBuffer orgAssigned = null;
				StringBuffer terrAssigned = null;
				List<SalesResourceAssignOrg> orgAssignedList = this.salesResourceAssignOrgDao.findBySalesId(salesRepBean.getSalesId());
				if(orgAssignedList!=null&&orgAssignedList.size()>0) {
					for(SalesResourceAssignOrg salesResourceAssignOrg:orgAssignedList) {
						if(salesResourceAssignOrg.getOrganization()!=null) {
							if(orgAssigned==null) {
								orgAssigned = new StringBuffer();
								orgAssigned.append(salesResourceAssignOrg.getOrganization().getName());
							} else {
								orgAssigned.append(",").append(salesResourceAssignOrg.getOrganization().getName());
							}
						}
					}
				}
				salesRepBean.setOrgAssigned(orgAssigned==null?"":orgAssigned.toString());
				List<SalesResourceAssignTerritory> terrAssignedList = this.salesResourceAssignTerritoryDao.findBySalesId(salesRepBean.getSalesId());
				if(terrAssignedList!=null&&terrAssignedList.size()>0) {
					for(SalesResourceAssignTerritory salesResourceAssignTerritory:terrAssignedList) {
						if(salesResourceAssignTerritory.getSalesTerritory()!=null) {
							if(terrAssigned==null) {
								terrAssigned = new StringBuffer();
								terrAssigned.append(salesResourceAssignTerritory.getSalesTerritory().getTerritoryCode());
							} else {
								terrAssigned.append(",").append(salesResourceAssignTerritory.getSalesTerritory().getTerritoryCode());
							}
						}
					}
				}
				salesRepBean.setTerrAssigned(terrAssigned==null?"":terrAssigned.toString());
				PbDropdownListOptions option = this.getDropdownOpt(DropDownListName.SALES_REP_FUNCTION.value(), salesRepBean.getFunction());
				salesRepBean.setFunctionText(option!=null?option.getText():null);
			}
		}
		return salesRepBeanPage;
	}
	
	
	/**
	 * 根据条件查找SalesRepBean2并分页显示
	 * @param salesRepPage 分页对象
	 * @return
	 */
	public Page<SalesRepBean2> searchSalesRepBean2Page(Page<SalesRepBean2> salesRepPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesRepBean2> pagerUtil = new PagerUtil<SalesRepBean2>();
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
			salesRepPage = this.salesRepBean2Dao.findPage(salesRepPage);
		} else {
			salesRepPage = this.salesRepBean2Dao.findPage(salesRepPage,filters);
		}
		if(salesRepPage!=null&&salesRepPage.getResult()!=null) {
			for(SalesRepBean2 salesRepBean2 : salesRepPage.getResult()) {
				if(salesRepBean2.getModifiedBy()!=null) {
					User user = this.userDao.getById(salesRepBean2.getModifiedBy());
					salesRepBean2.setModifiedName(user==null?"":user.getLoginName());
				}
			}
			
		}
		return salesRepPage;
	}
	
	/**
	 * 根据条件查找Organization并分页
	 * @param 
	 */
	public Page<Organization> searchOrgPage(Page<Organization> orgPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<Organization> pagerUtil = new PagerUtil<Organization>();
		orgPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!orgPage.isOrderBySetted()) {
			orgPage.setOrderBy("orgId");
			orgPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		orgPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			orgPage = this.organizationDao.getAll(orgPage);
		} else {
			orgPage = this.organizationDao.findPage(orgPage,filters);
		}
		if(orgPage!=null&&orgPage.getResult()!=null) {
			for(Organization org:orgPage.getResult()) {
				if(org.getModifiedBy()!=null) {
					User user = this.userDao.getById(org.getModifiedBy());
					org.setModifiedName(user==null?"":user.getLoginName());
				}
			}
		}
		return orgPage;
	}
	
	
	/**
	 * 根据salesId查找SalesRep对象
	 * @param salesId
	 */
	public SalesRepDTO findById(Integer salesId) {
		SalesRep salesRep =  this.salesRepDao.getById(salesId);
		SalesRepDTO salesRepDTO = this.dozer.map(salesRep, SalesRepDTO.class);
		List<SalesResourceAssignOrg> orgAssignedList = this.salesResourceAssignOrgDao.findBySalesId(salesId);
		List<SalesResourceAssignTerritory> terrAssignedList = this.salesResourceAssignTerritoryDao.findBySalesId(salesId);
		salesRepDTO.setOrgAssignedList(orgAssignedList);
		salesRepDTO.setTerrAssignedList(terrAssignedList);
		return salesRepDTO;
	}
	
	/**
	 * 保存SalesRep和SalesResourceAssignOrg以及SalesResourceAssignTerritory的关系
	 * @param sessionId 所有关联的organization
	 * @param salesId 
	 */
	public String saveConnection(String sessionId,Integer salesId) {
		this.salesResourceAssignOrgDao.deleteBySalesId(salesId);
		this.salesResourceAssignTerritoryDao.deleteBySalesId(salesId);
		Map<String,SalesResourceAssignOrg> orgMap = (Map<String,SalesResourceAssignOrg>)SessionUtil.getRow(SessionConstant.OrgAssignedList.value(), sessionId);
		Map<String,SalesResourceAssignTerritory> terrMap = (Map<String,SalesResourceAssignTerritory>)SessionUtil.getRow(SessionConstant.TerrAssignedList.value(), sessionId);
		List<SalesResourceAssignOrg> orgList = SessionUtil.convertMap2List(orgMap);
		List<SalesResourceAssignTerritory> terrList = SessionUtil.convertMap2List(terrMap);
		if((orgList==null||orgList.size()==0)
				&&(terrList==null||terrList.size()==0)) {
			return "failed";
		}
		for(SalesResourceAssignOrg salesResourceAssignOrg : orgList) {
			salesResourceAssignOrg.setAssignId(null);
			salesResourceAssignOrg.setSalesId(salesId);
			this.salesResourceAssignOrgDao.save(salesResourceAssignOrg);
		}
		for(SalesResourceAssignTerritory salesResourceAssignTerritory : terrList) {
			salesResourceAssignTerritory.setAssignId(null);
			salesResourceAssignTerritory.setSalesId(salesId);
			this.salesResourceAssignTerritoryDao.save(salesResourceAssignTerritory);
		}
		return "Success";
	}
	
	/**
	 * 删除SalesRep和SalesResourceAssignOrg以及SalesResourceAssignTerritory的关系
	 */
	public void deleteConnection(String allChoiceVal) {
		if(allChoiceVal!=null) {
			for(String salesId:allChoiceVal.split("-")) {
				this.salesResourceAssignOrgDao.deleteBySalesId(Integer.parseInt(salesId));
				this.salesResourceAssignTerritoryDao.deleteBySalesId(Integer.parseInt(salesId));
			}
		}
	}
	
	/**
	 * 删除SalesResourceAssignOrg对象(内存中删除)
	 */
	public void deleteAssignOrg(String allChoiceVal,String sessionId) {
		Map<String,SalesResourceAssignOrg> orgMap = (Map<String,SalesResourceAssignOrg>)SessionUtil.getRow(SessionConstant.OrgAssignedList.value(), sessionId);
		if(allChoiceVal!=null) {
			for(String assignId:allChoiceVal.split("-")) {
				if(orgMap.containsKey(assignId)) {
					orgMap.remove(assignId);
				}
			}
		}
		SessionUtil.updateRow(SessionConstant.OrgAssignedList.value(), sessionId, orgMap);
	}
	
	/**
	 * 删除SalesResourceAssignTerritory对象(内存中删除)
	 */
	public void deleteAssignTerr(String allChoiceVal,String sessionId) {
		Map<String,SalesResourceAssignTerritory> terrMap = (Map<String,SalesResourceAssignTerritory>)SessionUtil.getRow(SessionConstant.TerrAssignedList.value(), sessionId);
		if(allChoiceVal!=null) {
			for(String assignId:allChoiceVal.split("-")) {
				if(terrMap.containsKey(assignId)) {
					terrMap.remove(assignId);
				}
			}
		}
		SessionUtil.updateRow(SessionConstant.TerrAssignedList.value(), sessionId, terrMap);
	}
	
	/**
	 * 保存SalesResourceAssignTerritory
	 * @param salesResourceAssignTerritory
	 */
	public SalesResourceAssignTerritory overwriteSalesResourceAssignTerritory(SalesResourceAssignTerritory salesResourceAssignTerritory) {
		salesResourceAssignTerritory.setModifiedBy(SessionUtil.getUserId());
		salesResourceAssignTerritory.setModifyDate(new Date());
		if(salesResourceAssignTerritory.getSalesTerritory()!=null) {
			SalesTerritory terr = this.salesTerritoryDao.getById(salesResourceAssignTerritory.getSalesTerritory().getTerritoryId());
			User modUser = this.userDao.getById(terr.getModifiedBy());
			terr.setModifiedName(modUser==null?"":modUser.getLoginName());
			salesResourceAssignTerritory.setSalesTerritory(terr);
		}
		return salesResourceAssignTerritory;
	}
	
	/**
	 * 保存SalesResourceAssignOrg
	 * @param salesResourceAssignOrg
	 */
	public SalesResourceAssignOrg overwriteSalesResourceAssignOrg(SalesResourceAssignOrg salesResourceAssignOrg) {
		salesResourceAssignOrg.setModifiedBy(SessionUtil.getUserId());
		salesResourceAssignOrg.setModifyDate(new Date());
		if(salesResourceAssignOrg.getOrganization()!=null) {
			Organization org = this.organizationDao.getById(salesResourceAssignOrg.getOrganization().getOrgId());
			User modUser = this.userDao.getById(org.getModifiedBy());
			org.setModifiedName(modUser==null?"":modUser.getLoginName());
			salesResourceAssignOrg.setOrganization(org);
		}
		return salesResourceAssignOrg;
	}
	
	
	
	/**
	 * 获取所有Department
	 */
	public List<DepartmentSystem> getAllDep() {
		return this.departmentSystemDao.getAll();
	}
	
	/**
	 * 根据orgId获取所有Divisions
	 */
	public List<Divisions> findByOrg(Integer orgId) {
		return this.divisionDao.findByOrg(orgId);
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
