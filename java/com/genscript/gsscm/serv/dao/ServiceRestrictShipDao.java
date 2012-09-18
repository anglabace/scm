package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceRestrictShip;

@Repository
public class ServiceRestrictShipDao extends HibernateDao<ServiceRestrictShip, Integer>  {

	public void delShipList(Object ids){
	    String del_restship = "delete from ServiceRestrictShip c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_restship, map);
	}
}
