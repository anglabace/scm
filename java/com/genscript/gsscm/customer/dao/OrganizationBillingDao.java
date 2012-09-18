package com.genscript.gsscm.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.OrganizationBilling;

/**
 * The generic DAO class of OrganizationBilling.
 * 
 * @author wangsf
 */
@Repository
public class OrganizationBillingDao extends HibernateDao<OrganizationBilling, Integer>{
	/**
	 * 获得一个Organization的Billing.
	 * @param orgId
	 * @return
	 */
	public OrganizationBilling getBilling(Integer orgId) {
		OrganizationBilling billing = null;
		String hql = "from OrganizationBilling where orgId=?";
		List<OrganizationBilling> billingList = this.find(hql, orgId);
		if (billingList != null && !billingList.isEmpty()) {
			billing = billingList.get(0);
		}
		return billing;
	}
}
