package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.PriceRules;

@Repository
public class PriceRulesDao  extends HibernateDao<PriceRules, Integer> {

	public void delPriceRules(Object ids){
		String del_attribute="delete from PriceRules a where a.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
	
	public Page<PriceRules> findPageByGroupId(Page<PriceRules> page, Integer clsId,String groupId){
		String hql = "from PriceRules a where a.clsId="+clsId +" and (a.groupId<>"+groupId+" or a.groupId is null)";
		return this.findPage(page, hql);
	}
}
