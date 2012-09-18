package com.genscript.gsscm.contact.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.contact.entity.ContactPublications;

@Repository
public class ContactPublicationDao extends HibernateDao<ContactPublications, Integer>{
	private static final String DELETE_CONTACT_PUBS = "delete from ContactPublications c where c.id in (:ids)";
	
	public void delPubsList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DELETE_CONTACT_PUBS, map);
	}
}
