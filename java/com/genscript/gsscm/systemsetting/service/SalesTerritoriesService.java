package com.genscript.gsscm.systemsetting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.dao.PbStateDao;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dao.SalesTerritoryAssignmentDao;
import com.genscript.gsscm.customer.dao.SalesTerritoryDao;
import com.genscript.gsscm.customer.dto.SalesTerritoryDTO;
import com.genscript.gsscm.customer.entity.SalesTerritory;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignment;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;

@Service
@Transactional
public class SalesTerritoriesService {
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private SalesTerritoryDao salesTerritoryDao;
	@Autowired
	private SalesTerritoryAssignmentDao salesTerritoryAssignmentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PbStateDao pbStateDao;
	@Autowired
	private PbCountryDao pbCountryDao;

	/**
	 * 根据条件查找SalesTerritory并分页显示
	 * 
	 * @param salesTerritoryPage
	 *            分页对象
	 * @return
	 */
	public Page<SalesTerritory> searchSalesTerritoryPage(
			Page<SalesTerritory> salesTerritoryPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesTerritory> pagerUtil = new PagerUtil<SalesTerritory>();
		salesTerritoryPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!salesTerritoryPage.isOrderBySetted()) {
			salesTerritoryPage.setOrderBy("territoryId");
			salesTerritoryPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		salesTerritoryPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			salesTerritoryPage = this.salesTerritoryDao
					.getAll(salesTerritoryPage);
		} else {
			salesTerritoryPage = this.salesTerritoryDao.findPage(
					salesTerritoryPage, filters);
		}
		if (salesTerritoryPage != null
				&& salesTerritoryPage.getResult() != null) {
			for (SalesTerritory terr : salesTerritoryPage.getResult()) {
				if (terr.getModifiedBy() != null) {
					User user = this.userDao.getById(terr.getModifiedBy());
					terr.setModifiedName(user == null ? "" : user
							.getLoginName());
				}
			}
		}
		return salesTerritoryPage;
	}

	/**
	 * 根据条件查找SalesTerritoryAssignment并分页显示
	 * 
	 * @param zonePage
	 *            分页对象
	 * @return
	 */
	public Page<SalesTerritoryAssignment> searchZonePage(
			Page<SalesTerritoryAssignment> zonePage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<SalesTerritoryAssignment> pagerUtil = new PagerUtil<SalesTerritoryAssignment>();
		zonePage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!zonePage.isOrderBySetted()) {
			zonePage.setOrderBy("assignId");
			zonePage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		zonePage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			zonePage = this.salesTerritoryAssignmentDao.getAll(zonePage);
		} else {
			zonePage = this.salesTerritoryAssignmentDao.findPage(zonePage,
					filters);
		}
		if (zonePage != null && zonePage.getResult() != null) {
			for (SalesTerritoryAssignment zone : zonePage.getResult()) {
				if (zone.getModifiedBy() != null) {
					User user = this.userDao.getById(zone.getModifiedBy());
					zone.setModifiedName(user == null ? "" : user
							.getLoginName());
				}
			}
		}
		return zonePage;
	}

	/**
	 * 根据territoryId查找SalesTerritoryDTO对象
	 */
	public SalesTerritoryDTO findById(Integer territoryId) {
		SalesTerritory salesTerritory = this.salesTerritoryDao
				.getById(territoryId);

		SalesTerritoryDTO salesTerritoryDTO = this.dozer.map(salesTerritory,
				SalesTerritoryDTO.class);
		if (salesTerritoryDTO != null
				&& salesTerritoryDTO.getTerritoryId() != null) {
			List<SalesTerritoryAssignment> zoneList = this.salesTerritoryAssignmentDao
					.findByTerritoryId(territoryId);
			if (zoneList != null && zoneList.size() > 0) {
				for (SalesTerritoryAssignment salesTerritoryAssignment : zoneList) {
					PbCountry country = this.pbCountryDao.findUniqueBy(
							"countryCode",
							salesTerritoryAssignment.getCountryCode());
					salesTerritoryAssignment
							.setContinent(country != null ? country
									.getContinent() : null);
				}
			}
			salesTerritoryDTO.setZoneList(zoneList);
		}
		return salesTerritoryDTO;
	}

	public SalesTerritoryAssignment findZoneById(Integer assignId) {
		return this.salesTerritoryAssignmentDao.getById(assignId);
	}

	/**
	 * 保存SalesTerritory对象以及相关信息
	 */
	public void saveSalesTerritory(SalesTerritoryDTO salesTerritoryDTO,
			String sessionId) {
		if (salesTerritoryDTO.getTerritoryId() != null) {
			this.salesTerritoryAssignmentDao
					.deleteByTerritoryId(salesTerritoryDTO.getTerritoryId());
		}
		SalesTerritory salesTerritory = this.dozer.map(salesTerritoryDTO,
				SalesTerritory.class);
		this.salesTerritoryDao.save(salesTerritory);
		if (salesTerritoryDTO.getZoneList() != null
				&& salesTerritoryDTO.getZoneList().size() > 0) {
			for (SalesTerritoryAssignment salesTerritoryAssignment : salesTerritoryDTO
					.getZoneList()) {
				salesTerritoryAssignment.setAssignId(null);
				salesTerritoryAssignment.setTerritoryId(salesTerritory
						.getTerritoryId());
				this.salesTerritoryAssignmentDao.save(salesTerritoryAssignment);
			}
		}
	}

	/**
	 * 删除SalesTerritory对象
	 */
	public void deleteSalesTerritory(String allChoiceVal) {
		if (allChoiceVal != null) {
			for (String territoryId : allChoiceVal.split("-")) {
				this.salesTerritoryDao.delete(Integer.parseInt(territoryId));
				this.salesTerritoryAssignmentDao.deleteByTerritoryId(Integer
						.parseInt(territoryId));
			}
		}
	}

	/**
	 * 删除SalesTerritoryAssignment对象(内存中删除)
	 */
	public void deleteSalesTerritoryAssignmnet(String allChoiceVal,
			String sessionId) {
		Map<String, SalesTerritoryAssignment> zoneMap = (Map<String, SalesTerritoryAssignment>) SessionUtil
				.getRow(SessionConstant.ZoneList.value(), sessionId);
		SalesTerritoryDTO salesTerritoryDTO = (SalesTerritoryDTO) SessionUtil
				.getRow(SessionConstant.SalesTerritory.value(), sessionId);
		if (allChoiceVal != null) {
			for (String territoryId : allChoiceVal.split("-")) {
				if (zoneMap.containsKey(territoryId)) {
					zoneMap.remove(territoryId);
				}
			}
		}
		SessionUtil.updateRow(SessionConstant.ZoneList.value(), sessionId,
				zoneMap);
		SessionUtil.updateRow(SessionConstant.SalesTerritory.value(),
				sessionId, salesTerritoryDTO);
	}

	/**
	 * 获取continent下的country
	 */
	public List<PbCountry> findCountryBycontinent(String continent) {
		return this.pbCountryDao.findBy("continent", continent);
	}

	/**
	 * 获取country下的state
	 * 
	 * @param countryCode
	 * 
	 */
	public List<PbState> findStateByCountry(String countryCode) {
		PbCountry country = this.pbCountryDao.findUniqueBy("countryCode",
				countryCode);
		if (country != null) {
			return this.pbStateDao.getListByCountry(country.getCountryId());
		}
		return null;

	}

	/**
	 * 获取country下的state
	 * 
	 * @param countryId
	 */
	public List<PbState> findStateByCountryId(Integer countryId) {
		return this.pbStateDao.findBy("countryId", countryId);
	}

	/**
	 * 获取所有country
	 */
	public List<PbCountry> findAllCountry() {
		return this.pbCountryDao.getAll();
	}

	/**
	 * 由countryCode获取country
	 */
	public PbCountry getCountryByCode(String countryCode) {
		PbCountry country = this.pbCountryDao.findUniqueBy("countryCode",
				countryCode);
		return country;
	}

	public List getAllzonelistById(Integer sessionId) {
		SalesTerritory salesTerritory = this.salesTerritoryDao
				.getById(sessionId);
		List lists = new ArrayList();
		SalesTerritoryDTO salesTerritoryDTO = this.dozer.map(salesTerritory,
				SalesTerritoryDTO.class);
		if (salesTerritoryDTO != null) {
			lists = salesTerritoryDTO.getZoneList();
		}
		return lists;
	}

}
