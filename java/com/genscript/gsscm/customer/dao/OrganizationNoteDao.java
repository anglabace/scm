package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.OrganizationNote;

@Repository
public class OrganizationNoteDao extends HibernateDao<OrganizationNote, Integer> {
	
	/**
	 * 批量删除
	 * @param ids id列表
	 */
	public void delDocList(Object ids) {
		String hql = "delete from OrganizationNote c where c.id in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
}
