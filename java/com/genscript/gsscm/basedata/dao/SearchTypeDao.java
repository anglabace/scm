package com.genscript.gsscm.basedata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbSearchType;

@Repository
public class SearchTypeDao extends HibernateDao<PbSearchType, Integer> {
    public PbSearchType getSearchType(String type) {
    	PbSearchType searchType = null;
    	String hql = "from PbSearchType where name=?";
    	List<PbSearchType> list = this.find(hql, type);
    	if (list != null && list.size()>0) {
    		searchType = list.get(0);
    	}
    	return searchType;
    }
}
