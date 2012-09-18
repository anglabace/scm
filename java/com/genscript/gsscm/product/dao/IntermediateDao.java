package com.genscript.gsscm.product.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.Intermediate;

@Repository
public class IntermediateDao extends HibernateDao<Intermediate, Integer> {
	private static final String DEL_INTMD = "delete from Intermediate c where c.id in (:ids)";
	
	public void delIntmdList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_INTMD, map);
	}
}
