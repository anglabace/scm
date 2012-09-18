package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.PriceFormulaCriterias;

@Repository
public class PriceFormulaCriteriasDao extends HibernateDao<PriceFormulaCriterias, Integer>{
	public void delPriceFormulaCriterias(Object ids){
		String del_attribute="delete from PriceFormulaCriterias a where a.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
	
	public void delPriceFormulaCriteriasBy(Object ids){
		String del_attribute="delete from PriceFormulaCriterias a where a.formulaId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
}
