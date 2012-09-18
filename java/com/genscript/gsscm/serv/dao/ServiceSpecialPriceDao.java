package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceSpecialPrice;

@Repository
public class ServiceSpecialPriceDao extends HibernateDao<ServiceSpecialPrice, Integer> {

	public void delSpecialPriceList(Object ids) {
		String hql = "delete from ServiceSpecialPrice c where c.id in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
}
