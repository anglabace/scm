package com.genscript.gsscm.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.DivisionBilling;

/**
 * The generic DAO class of DivisionBilling.
 * 
 * @author wangsf
 */
@Repository
public class DivisionBillingDao extends HibernateDao<DivisionBilling, Integer>{
	/**
	 * 获得一个Division的Billing.
	 * @param orgId
	 * @return
	 */
	public DivisionBilling getBilling(Integer orgId) {
		DivisionBilling billing = null;
		String hql = "from DivisionBilling where divisionId=?";
		List<DivisionBilling> billingList = this.find(hql, orgId);
		if (billingList != null && !billingList.isEmpty()) {
			billing = billingList.get(0);
		}
		return billing;
	}
}
