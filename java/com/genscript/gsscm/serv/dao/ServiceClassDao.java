package com.genscript.gsscm.serv.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceClass;

@Repository
public class ServiceClassDao extends HibernateDao<ServiceClass, Integer> {
	
	/**
	 * 
	 */
	public List<ServiceClass> findAll(String order,String orderBy) {
		String hql = "from ServiceClass order by "+orderBy+" "+order;
		return this.find(hql);
	}
	public List<ServiceClass> findAlls() {
		String hql = "from ServiceClass order by clsId desc ";
		return this.find(hql);
	}

}
