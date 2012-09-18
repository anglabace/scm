package com.genscript.gsscm.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignProcess;
@Repository
public class SalesTerritoryAssignProcessDao extends HibernateDao<SalesTerritoryAssignProcess, Integer>{
	private  final String SEACHBYRULEID = "from SalesTerritoryAssignProcess where ruleId=?" ;
	private final String DELBYRULEID="update SalesTerritoryAssignProcess set ruleId=null where ruleId=?";
	
	/**
	 * 根据ruleId查找所有的SalesTerritoryAssignProcess
	 * @param ruleId
	 * @return
	 */
	public List<SalesTerritoryAssignProcess> findByRuleId(Integer ruleId) {
		return this.find(SEACHBYRULEID, ruleId);
	}
	
	/**
	 * 根据ruleId删除所有相关的SalesTerritoryAssignProcess
	 */
	public void deleteByRuleId(Integer ruleId) {
		this.batchExecute(DELBYRULEID, ruleId);
	}

}
