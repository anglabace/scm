package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.PriceRuleGroups;

@Repository
public class PriceRuleGroupsDao extends HibernateDao<PriceRuleGroups, Integer> {
	public void delPriceRuleGroups(Object ids){
		String del_hql="delete from PriceRuleGroups a where a.groupId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_hql, map);
	}
}
