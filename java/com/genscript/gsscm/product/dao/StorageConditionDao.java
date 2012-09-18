package com.genscript.gsscm.product.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.StorageCondition;

@Repository
public class StorageConditionDao extends HibernateDao<StorageCondition, Integer> {
	public StorageCondition getStorageCondition(Integer productId) {
		StorageCondition storageCondition = null;
		Criterion criterion = Restrictions.eq("productId", productId);
		List<StorageCondition> list = find(criterion);
		if (list!=null && ! list.isEmpty()) {
			storageCondition = list.get(0);
		}
		return storageCondition;
	}
}
