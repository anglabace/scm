package com.genscript.gsscm.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.DepartmentBilling;

/**
 * The generic DAO class of DepartmentBilling.
 * 
 * @author wangsf
 */
@Repository
public class DepartmentBillingDao extends HibernateDao<DepartmentBilling, Integer>{
	/**
	 * 获得一个Department的Billing.
	 * @param orgId
	 * @return
	 */
	public DepartmentBilling getBilling(Integer deptId) {
		DepartmentBilling billing = null;
		String hql = "from DepartmentBilling where deptId=?";
		List<DepartmentBilling> billingList = this.find(hql, deptId);
		if (billingList != null && !billingList.isEmpty()) {
			billing = billingList.get(0);
		}
		return billing;
	}
}
