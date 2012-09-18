package com.genscript.gsscm.serv.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceClassification;

@Repository
public class ServiceClassificationDao extends HibernateDao<ServiceClassification, Integer> {
	
	/**
	 * 查询所有serviceClassification对象
	 */
	public List<ServiceClassification> findAll() {
		String hql = "from ServiceClassification order by name";
		return this.find(hql);
	}

}
