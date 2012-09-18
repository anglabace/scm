package com.genscript.gsscm.customer.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.SalesGroup;

@Repository
public class SalesGroupDao extends HibernateDao<SalesGroup, Integer> {

	/**
	 * 通过主键查询
	 * @author zhangyong
	 * @param groupId
	 * @return
	 */
	public SalesGroup findByGroupId (Integer groupId) {
		return this.findUniqueBy("groupId", groupId);
	}
}
