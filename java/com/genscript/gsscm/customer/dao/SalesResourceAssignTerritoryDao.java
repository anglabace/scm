package com.genscript.gsscm.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.SalesResourceAssignTerritory;

@Repository
public class SalesResourceAssignTerritoryDao extends HibernateDao<SalesResourceAssignTerritory, Integer>{
	
	/**
	 * 根据salesId查找SalesResourceAssignTerritory
	 * @param salesId
	 */
	public List<SalesResourceAssignTerritory> findBySalesId(Integer salesId) {
		String hql = "from SalesResourceAssignTerritory where salesId=?";
		return this.find(hql, salesId);
		
	}
	
	/**
	 * 根据salesId删除SalesResourceAssignTerritory
	 * @param salesId
	 */
	public void deleteBySalesId(Integer salesId) {
		String hql = "delete from SalesResourceAssignTerritory where salesId=?";
		this.batchExecute(hql, salesId);
	}
	
	/**
	 * 通过salesIds查找SalesResourceAssignTerritory列表
	 * @author zhang yong
	 * @param salesIds
	 * @return
	 */
	public List<SalesResourceAssignTerritory> findBySalesIds (String salesIds) {
		String hql = "from SalesResourceAssignTerritory where salesId in (" + salesIds + ")";
		return this.find(hql);
	}

	/**
	 * 通过territoryId查找SalesResourceAssignTerritory列表
	 * @author zhang yong
	 * @param territoryId
	 * @return
	 */
	public List<SalesResourceAssignTerritory> findByTerritoryId (Integer territoryId) {
		String hql = "from SalesResourceAssignTerritory where salesTerritory.territoryId = ?";
		return this.find(hql, territoryId);
	}
	
	/**
	 * 通过territoryId和salesId查找salesResourceTerritory
	 */
	public SalesResourceAssignTerritory findBy_terrId_salesId(Integer territoryId,Integer salesId) {
		String hql = "from SalesResourceAssignTerritory where salesTerritory.territoryId = ? and salesId = ?";
		return this.findUnique(hql, territoryId,salesId);
	}
}
