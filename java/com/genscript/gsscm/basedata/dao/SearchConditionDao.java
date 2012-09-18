package com.genscript.gsscm.basedata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbSearch;
import com.genscript.gsscm.basedata.entity.PbSearchCondition;

@Repository
public class SearchConditionDao extends
		HibernateDao<PbSearchCondition, Integer> {
	public List<PbSearchCondition> getListBySearch(PbSearch search) {
		String hql = "FROM PbSearchCondition cond where cond.pbSearch=?";
		return this.find(hql, search);
	}
	
	public void delBySearch(PbSearch search) {
		String hql = "Delete FROM PbSearchCondition cond where cond.pbSearch=?";
		this.batchExecute(hql, search);
	}
}
