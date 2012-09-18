package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerInterest;

@Repository
public class CustomerInterestDao extends HibernateDao<CustomerInterest, Integer> {

	private static final String DELETE_CUSTOMER_INTERESTS = "delete from CustomerInterest c where c.interestId in (:ids)";
	
	public void deleteCustomerInterests(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		System.out.println(map.get("ids"));
		batchExecute(DELETE_CUSTOMER_INTERESTS, map);
	}
}
