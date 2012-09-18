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
import com.genscript.gsscm.customer.entity.Departments;

@Repository
public class DepartmentDao extends HibernateDao<Departments, Integer> {
	/* (non-Javadoc)
	 * @see com.genscript.core.orm.hibernate.SimpleHibernateDao#save(java.lang.Object)
	 */
	@Override
	public void save(Departments entity) {
		String name = entity.getName().trim();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQS_name", name);
		filterList.add(quoteFilter);
		PropertyFilter orgFilter = new PropertyFilter("EQI_orgId", entity.getOrgId());
		filterList.add(orgFilter);
		if (entity.getDivisionId() == null || entity.getDivisionId().intValue() == 0) {
			entity.setDivisionId(null);				
			PropertyFilter divFilter = new PropertyFilter("NULLI_divisionId", null);
			filterList.add(divFilter);	
		} else {
			PropertyFilter divFilter = new PropertyFilter("EQI_divisionId", entity.getDivisionId());			
			filterList.add(divFilter);	
		}
		if (entity.getDeptId() == null || entity.getDeptId().intValue() == 0) {
			entity.setDeptId(null);				
		} else {
			PropertyFilter idFilter = new PropertyFilter("NEI_deptId", entity.getDeptId());			
			filterList.add(idFilter);	
		}
		List<Departments> list = this.find(filterList);
		if (list != null && !list.isEmpty()) {
			throw new BussinessException(
					BussinessException.DIVDEPT_REPEATNAME_CODE);
		}
		entity.setName(name);				
		super.save(entity);
	}
	
	/**
	 * 批量更新Departments的状态
	 * 
	 * @param idList
	 * @param status
	 */
	public void batchUpdateDepartment(List<Integer> idList, String status) {
		String hql = "update Departments o set o.activeFlag=:status where o.divisionId in (:idList)";
		Map<String, List<Integer>> map = Collections.singletonMap("idList",
				idList);
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("status", status);
		values.put("idList", map.get("idList"));
		batchExecute(hql, values);
	}
	
	/**
	 * 根据divisionId获得所有关联的Departments
	 * 
	 * @param page
	 * @param divisionId
	 * @return
	 */
	public Page<Departments> searchDepartment(Page<Departments> page,
			Integer divisionId) {
		String hql = "from Departments where divisionId=?";
		return this.findPage(page, hql, divisionId);
	}
}
