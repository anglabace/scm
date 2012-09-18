package com.genscript.gsscm.systemsetting.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.systemsetting.entity.BillTerritoryBilling;

@Repository
public class BillTerritoryBillingDao extends HibernateDao<BillTerritoryBilling, Integer> {

	/**
	 * 通过territoryId查找唯一记录
	 * @author Zhang Yong
	 * @param territoryId
	 * @return
	 */
	public BillTerritoryBilling getByTerrId (Integer territoryId) {
		String hql = "from BillTerritoryBilling where territoryId=:territoryId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("territoryId", territoryId);
		return findUnique(hql, map);
	}
}
