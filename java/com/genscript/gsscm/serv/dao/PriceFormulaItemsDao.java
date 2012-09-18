package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.PriceFormulaItems;

@Repository
public class PriceFormulaItemsDao extends HibernateDao<PriceFormulaItems,Integer>{
	public void delPriceFormulaItem(Object ids){
		String del_attribute="delete from PriceFormulaItems a where a.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
	
	public void delPriceFormulaItemByFormulaId(Object ids){
		String del_attribute="delete from PriceFormulaItems a where a.formulaId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
	
	public List<PriceFormulaItems> findByFormulaId(Integer formulaId){
		String hql = "from PriceFormulaItems a where a.formulaId = "+formulaId+" order by seqNo asc";
		return this.find(hql);
	}
}
