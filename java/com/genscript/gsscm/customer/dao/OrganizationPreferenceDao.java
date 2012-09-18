package com.genscript.gsscm.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.OrganizationPreference;

/**
 * The generic DAO class of OrganizationPreference.
 * 
 * @author wangsf
 */
@Repository
public class OrganizationPreferenceDao extends HibernateDao<OrganizationPreference, Integer>{
	/**
	 * 获得一个Organization的Order Preference.
	 * @param orgId
	 * @return
	 */
	public OrganizationPreference getPreference(Integer orgId) {
		OrganizationPreference obj = null;
		String hql = "from OrganizationPreference where orgId=?";
		List<OrganizationPreference> list = this.find(hql, orgId);
		if (list != null && !list.isEmpty()) {
			obj = list.get(0);
		}
		return obj;
	}
}
