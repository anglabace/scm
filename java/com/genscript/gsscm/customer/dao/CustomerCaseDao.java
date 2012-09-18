package com.genscript.gsscm.customer.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerCase;

@Repository
public class CustomerCaseDao extends HibernateDao<CustomerCase, Integer>{

}
