package com.genscript.gsscm.product.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.CatalogNORules;

@Repository
public class CatalogNORulesDao extends HibernateDao<CatalogNORules, Integer> {
	public void flush(){
		Session session = this.getSession();
		session.flush();
		session.clear();
	}
}
