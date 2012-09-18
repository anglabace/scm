package com.genscript.gsscm.basedata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbState;

@Repository
public class PbStateDao extends HibernateDao<PbState, Integer> {
	public List<PbState> getListByCountry(Integer countryId) {
	  String hql = "FROM PbState  where countryId=?";
	  List<PbState> list = this.find(hql, countryId);
	  return list;
  }
	
	public void deleteByCountryId(Integer countryId) {
		String hql = "delete from PbState where countryId=?";
		this.batchExecute(hql, countryId);
	}
}
