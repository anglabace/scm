package com.genscript.gsscm.serv.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceStorageCondition;

@Repository
public class ServiceStorageConditionDao extends HibernateDao<ServiceStorageCondition, Integer> {
	public ServiceStorageCondition getStorageCondition(Integer serviceId) {
		ServiceStorageCondition storageCondition = null;
		Criterion criterion = Restrictions.eq("serviceId", serviceId);
		List<ServiceStorageCondition> list = find(criterion);
		if (list!=null && ! list.isEmpty()) {
			storageCondition = list.get(0);
		}
		return storageCondition;
	}
}    
