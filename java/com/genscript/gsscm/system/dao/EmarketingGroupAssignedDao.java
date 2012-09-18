/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.EmarketingGroupAssigned;

/**
 *
 * @author jinsite
 */
@Repository
public class EmarketingGroupAssignedDao  extends HibernateDao<EmarketingGroupAssigned,Integer>{

	/**
	 * 查询clsId
	 * @author Zhang Yong
	 * @param supervisor
	 * @param itemType
	 * @return
	 */
	public List<Integer> getClsIdByStaffIdAndType (Integer staffId, String itemType) {
		String hql = "SELECT ekg.clsId FROM EmarketingGroupAssigned ekg, MarketingStaff ms " +
				" WHERE ms.staffId=:staffId AND ms.marketingGroup = ekg.marketingGroupId " +
				" AND ekg.itemType=:itemType ORDER BY ekg.clsId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("staffId", staffId);
		map.put("itemType", itemType);
		return this.find(hql, map);
	}
}
