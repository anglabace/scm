package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceComponent;

@Repository
public class ServiceComponentDao  extends HibernateDao<ServiceComponent, Integer> {
	
	public void delComponentList(Object ids){
		String del_com = "delete from ServiceComponent c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_com, map);
	}
}
