package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerPublications;

@Repository
public class CustomerPublicationDao extends HibernateDao<CustomerPublications, Integer>{
	private static final String DELETE_CUSTOMER_PUBS = "delete from CustomerPublications c where c.id in (:ids)";
	
	public void deleteCustomerPubs(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DELETE_CUSTOMER_PUBS, map);
	}

}
