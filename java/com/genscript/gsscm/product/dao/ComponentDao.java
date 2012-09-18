package com.genscript.gsscm.product.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.Component;

@Repository
public class ComponentDao extends HibernateDao<Component, Integer> {
	private static final String DEL_COM = "delete from Component c where c.id in (:ids)";
	
	public void delComponentList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_COM, map);
	}
}
