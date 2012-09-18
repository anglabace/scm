package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.PriceFormulas;

@Repository
public class PriceFormulasDao extends HibernateDao<PriceFormulas, Integer>{
	public void delPriceFormulas(Object ids){
		String del_attribute="delete from PriceFormulas a where a.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
	
	public void delPriceFormulasByRuleId(Object ids){
		String del_attribute="delete from PriceFormulas a where a.ruleId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
}
