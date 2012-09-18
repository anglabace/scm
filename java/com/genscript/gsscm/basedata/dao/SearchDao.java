package com.genscript.gsscm.basedata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbSearch;

@Repository
public class SearchDao extends HibernateDao<PbSearch, Integer> {
	public List<PbSearch> getList(String type, Integer owerId) {
		String hql = "FROM PbSearch attr where attr.pbSearchType.name=? and attr.owner=?";
		return this.find(hql, type, owerId);
	}

	public PbSearch getSearch(Integer searchId, Integer ownerId) {
		PbSearch pbSearch = null;
		String hql = "FROM PbSearch where searchId=? and owner=?";
		List<PbSearch> retList = this.find(hql, searchId, ownerId);
		if (retList!=null && retList.size()>0) {
			pbSearch = retList.get(0);
		}
		return pbSearch;
	}
	
	public Boolean isMySrchUnique(final Integer searchType,final Integer searchOwner, final Object newValue){
		if (newValue == null )
			return true;
		Object object = findUnique("from PbSearch where name=? and pbSearchType.typeId=? and owner=?", newValue, searchType, searchOwner);
		return (object == null);
	}
	
	public PbSearch getMySrch(final Integer searchType,final Integer searchOwner, final String newValue){
		return this.findUnique("from PbSearch where name=? and pbSearchType.typeId=? and owner=?", newValue, searchType, searchOwner);
	}
}
