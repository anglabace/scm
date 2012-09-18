package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceReference;

@Repository
public class ServiceReferenceDao extends HibernateDao<ServiceReference, Integer> {
	public void delReferenceList(Object ids){
		String del_rece = "delete from ServiceReference c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_rece, map);
	}
}
