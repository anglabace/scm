package com.genscript.gsscm.basedata.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbLanguage;

@Repository
public class PbLanguageDao extends HibernateDao<PbLanguage,Integer> {

private static final String GET_LANGNAME = "select l.name from PbLanguage l where l.langCode=?";
	
	public String getLangName(String langCode){
		return findUnique(GET_LANGNAME, langCode);
	}
}
