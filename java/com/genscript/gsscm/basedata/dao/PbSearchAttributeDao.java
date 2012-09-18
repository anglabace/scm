package com.genscript.gsscm.basedata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbSearchAttribute;

@Repository
public class PbSearchAttributeDao extends HibernateDao<PbSearchAttribute, Integer> {

	private static final String QUERY_SEARCH_ATTRIBUTE = "select a from PbSearchAttribute a where a.pbSearchType.name=?";
	
	@SuppressWarnings("unchecked")
	public List<PbSearchAttribute> getCustomerAttribute(String customerType){
		List<PbSearchAttribute> list = createQuery(QUERY_SEARCH_ATTRIBUTE, customerType).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<PbSearchAttribute> getListByType(String type) {
		String hql = "FROM PbSearchAttribute attr where attr.pbSearchType.name=? order by attr.name";
		return this.createQuery(hql, type).list();
	}
}
