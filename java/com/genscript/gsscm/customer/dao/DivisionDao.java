package com.genscript.gsscm.customer.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.customer.entity.Divisions;

@Repository
public class DivisionDao extends HibernateDao<Divisions, Integer> {

	/* (non-Javadoc)
	 * @see com.genscript.core.orm.hibernate.SimpleHibernateDao#save(java.lang.Object)
	 */
	@Override
	public void save(Divisions entity) {
		String name = entity.getName().trim();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQS_name", name);
		filterList.add(quoteFilter);
		PropertyFilter orgFilter = new PropertyFilter("EQI_orgId", entity.getOrgId());
		filterList.add(orgFilter);
		if (entity.getDivisionId() == null || entity.getDivisionId().intValue() == 0) {
			entity.setDivisionId(null);				
		} else {
			PropertyFilter idFilter = new PropertyFilter("NEI_divisionId", entity.getDivisionId());			
			filterList.add(idFilter);	
		}
		List<Divisions> list = this.find(filterList);
		if (list != null && !list.isEmpty()) {
			throw new BussinessException(
					BussinessException.ORGDIV_REPEATNAME_CODE);
		}
		entity.setName(name);				
		super.save(entity);
	}
	
	/**
	 * 指更新Divisions的状态
	 * 
	 * @param divIdList
	 * @param status
	 */
	public void batchUpdateDivision(List<Integer> divIdList, String status) {
		String hql = "update Divisions o set o.activeFlag=:status where o.divisionId in (:idList)";
		Map<String, List<Integer>> map = Collections.singletonMap("idList",
				divIdList);
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("status", status);
		values.put("idList", map.get("idList"));
		batchExecute(hql, values);
	}

	/**
	 * 根据orgId获得所有关联的Divisions
	 * 
	 * @param page
	 * @param orgId
	 * @return
	 */
	public Page<Divisions> searchDivision(Page<Divisions> page,
			Integer orgId) {
		String hql = "from Divisions where orgId=?";
		return this.findPage(page, hql, orgId);
	}
	
	/**
	 * 根据orgId获得所有关联的Divisions
	 */
	public List<Divisions> findByOrg(Integer orgId) {
		return this.findBy("orgId", orgId);
	}
}
