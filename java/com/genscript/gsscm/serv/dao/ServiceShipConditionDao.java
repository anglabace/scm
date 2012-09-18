package com.genscript.gsscm.serv.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;

@Repository
public class ServiceShipConditionDao extends HibernateDao<ServiceShipCondition, Integer> {
	public ServiceShipCondition getShipCondition(Integer serviceId) {
		ServiceShipCondition shipCondition = null;
		Criterion criterion = Restrictions.eq("serviceId", serviceId);
		List<ServiceShipCondition> list = find(criterion);
		if (list!=null && ! list.isEmpty()) {
			shipCondition = list.get(0);
		}
		return shipCondition;
	}
}
