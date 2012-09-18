package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.PriceRuleAttrValueMapping;

@Repository
public class PriceRuleAttrValueMappingDao extends HibernateDao<PriceRuleAttrValueMapping, Integer> {
	
	public void delPriceRuleAttrValueMapping(Object ids){
		String del_attribute="delete from PriceRuleAttrValueMapping a where a.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
	
	public void delPriceRuleAttrValueMappingByRuleId(Object ids){
		String del_attribute="delete from PriceRuleAttrValueMapping a where a.ruleId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
	
	public List<PriceRuleAttrValueMapping> searchPriceRuleAttrValueMappingByAttributeIdAndRuleId(Integer ruleId,Integer attributerId){
		String hql = "from PriceRuleAttrValueMapping a where a.attributeId ="+attributerId+" and ruleId ="+ruleId;
		
		return this.find(hql);
	}
}
