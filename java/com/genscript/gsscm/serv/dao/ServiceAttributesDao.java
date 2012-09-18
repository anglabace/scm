package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceAttributes;

@Repository
public class ServiceAttributesDao extends HibernateDao<ServiceAttributes, Integer> {
	
	public void delAttributes(Object ids){
		String del_attribute="delete from ServiceAttributes a where a.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
}
