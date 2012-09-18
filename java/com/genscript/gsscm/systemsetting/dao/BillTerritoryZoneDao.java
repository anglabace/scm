package com.genscript.gsscm.systemsetting.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.systemsetting.entity.BillTerritoryZone;

@Repository
public class BillTerritoryZoneDao extends HibernateDao<BillTerritoryZone, Integer> {

	/**
	 * 通过territoryId查询Zone
	 * @author Zhang Yong
	 * @param territoryId
	 * @return
	 */
	public List<BillTerritoryZone> findByTerrId (Integer territoryId) {
		String hql = "from BillTerritoryZone where territoryId=:territoryId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("territoryId", territoryId);
		return find(hql, map);
	}
	
	/**
	 * 通过territoryId删除数据
	 * @author Zhang Yong
	 * @param territoryId
	 */
	public void deleteByTerrId (Integer territoryId) {
		String hql = "delete from BillTerritoryZone where territoryId=:territoryId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("territoryId", territoryId);
		createQuery(hql, map).executeUpdate();
	}
	
	/**
	 * 待条件查询列表
	 * @author Zhang Yong
	 * @param continent
	 * @param country
	 * @param state
	 * @param zipFrom
	 * @param zipTo
	 * @return
	 */
	public List<BillTerritoryZone> getTerritoryZone (String continent, String country, 
			String state, String zipFrom, String zipTo) {
		String hql = "from BillTerritoryZone btz where btz.continent=:continent and btz.country=:country "
			+ " and btz.state=:state and btz.zipFrom=:zipFrom and btz.zipTo=:zipTo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("continent", continent==null?"":continent.trim());
		map.put("country", country==null?"":country.trim());
		map.put("state", state==null?"":state.trim());
		map.put("zipFrom", zipFrom==null?"":zipFrom.trim());
		map.put("zipTo", zipTo==null?"":zipTo.trim());
		return this.find(hql, map);
	}
}
