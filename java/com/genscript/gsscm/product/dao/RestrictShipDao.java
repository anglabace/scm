package com.genscript.gsscm.product.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.RestrictShip;

@Repository
public class RestrictShipDao extends HibernateDao<RestrictShip, Integer> {
	private static final String DEL_RESTSHIP = "delete from RestrictShip c where c.id in (:ids)";
	
	public void delShipList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_RESTSHIP, map);
	}
}
