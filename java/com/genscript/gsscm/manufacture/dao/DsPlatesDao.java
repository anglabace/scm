package com.genscript.gsscm.manufacture.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.DsPlates;

@Repository
public class DsPlatesDao extends HibernateDao<DsPlates,Integer>{
	public Long findPlateNum() {
		String hql = "select count(id) from DsPlates";
		return this.findUnique(hql);
	}

}
