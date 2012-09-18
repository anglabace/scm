package com.genscript.gsscm.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductClass;

@Repository
public class ProductClassDao extends HibernateDao<ProductClass, Integer> {

	/**
	 * 
	 */
	public List<ProductClass> findAll(String order, String orderBy) {
		String hql = "from ProductClass order by " + orderBy + " " + order;
		return this.find(hql);
	}

	public List<ProductClass> findAlls() {
		String hql = "from ProductClass order by clsId desc";
		return this.find(hql);
	}

}
