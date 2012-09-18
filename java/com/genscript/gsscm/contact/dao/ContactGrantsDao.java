package com.genscript.gsscm.contact.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.contact.entity.ContactGrants;

@Repository
public class ContactGrantsDao extends HibernateDao<ContactGrants, Integer>{

	private static final String DELETE_GRANTS = "delete from ContactGrants c where c.grantId in (:ids)";
	
	public void delGrantsList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DELETE_GRANTS, map);
	}
}
