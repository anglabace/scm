package com.genscript.gsscm.systemsetting.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.systemsetting.entity.BillTerritory;

@Repository
public class BillTerritoryDao extends HibernateDao<BillTerritory, Integer> {

	/**
	 * 批量更新BillTerritory
	 * 
	 * @author Zhang Yong
	 * @param btIds
	 * @param modifyId
	 */
	public void updateBillTerritorys(List<Integer> btIds, Integer modifyId) {
		String hql = "update BillTerritory set status=:status, modifiedBy=:modifiedBy, "
				+ " modifyDate=:modifyDate where territoryId in(:territoryIds)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", StatusType.INACTIVE.value());
		map.put("modifiedBy", modifyId);
		map.put("modifyDate", new Date());
		map.put("territoryIds", btIds);
		this.batchExecute(hql, map);
	}

	/**
	 * 通过territoryCode查询是否数据库中有记录，territoryCode具有唯一性
	 * 
	 * @author Zhang Yong
	 * @param territoryCode
	 * @return
	 */
	public List<BillTerritory> findByTerriCode(String territoryCode) {
		String hql = "from BillTerritory where territoryCode=:territoryCode";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("territoryCode", territoryCode);
		return find(hql, map);
	}

	/**
	 * 查询accountCode
	 * @author Zhang Yong
	 * @param countryCode
	 * @param state
	 * @param zipCode
	 * @return
	 */
	public String getAccountCode(String countryCode, String state,
			String zipCode) {
		String hql = "select bt.accountCode from BillTerritory bt, BillTerritoryZone btz " +
				" where btz.country=:country and (:state BETWEEN IFNULL(btz.state,:state) " +
				" AND IFNULL(btz.state,:state)) and (:zipCode BETWEEN IFNULL(btz.zipFrom,:zipCode) " +
				" AND IFNULL(btz.zipTo,:zipCode)) and btz.territoryId = bt.territoryId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("country", countryCode);
		map.put("state", state);
		map.put("zipCode", zipCode);
		List<String> accountCodeList = find(hql, map);
		if (accountCodeList != null && !accountCodeList.isEmpty()) {
			return accountCodeList.get(0);
		}
		return null;
	}
}
