package com.genscript.gsscm.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.UpdateRequestLog;

@Repository
public class UpdateRequestLogDao extends
		HibernateDao<UpdateRequestLog, Integer> {
	
	public List<UpdateRequestLog> findBySomeCondition(Object... param) {
		String hql = "from UpdateRequestLog where objectEntity=? and objectId=?";
		return this.find(hql, param);
	}

}
