package com.genscript.gsscm.product.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ShipCondition;

@Repository
public class ShipConditionDao extends HibernateDao<ShipCondition, Integer> {
	public ShipCondition getShipCondition(Integer productId) {
		ShipCondition shipCondition = null;
		Criterion criterion = Restrictions.eq("productId", productId);
		List<ShipCondition> list = find(criterion);
		if (list!=null && ! list.isEmpty()) {
			shipCondition = list.get(0);
		}
		return shipCondition;
	}
}
