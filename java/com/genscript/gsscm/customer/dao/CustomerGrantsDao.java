package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerGrants;

@Repository
public class CustomerGrantsDao extends HibernateDao<CustomerGrants, Integer>{

	private static final String DELETE_CUSTOMER_GRANTS = "delete from CustomerGrants c where c.grantId in (:ids)";
	
	public void deleteCustomerGrants(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		System.out.println(map.get("ids"));
		batchExecute(DELETE_CUSTOMER_GRANTS, map);
	}
}
