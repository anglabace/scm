package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.AntibodyOprPurificationResults;

@Repository
public class AntibodyOprPurificationResultsDao extends HibernateDao<AntibodyOprPurificationResults, Integer>{

	
	/**
	 * 根据WoOperation List 删除  AntibodyOprPurificationResults
	 * @param woOperationIdList
	 */
	public void delAntibodyOprPurificationResultsByWoOPerationList(List<Integer> woOperationIdList){
		String hql = "delete from AntibodyOprPurificationResults c where c.refId in (:woOperationIdList)";
		Map<String,List<Integer>> map = Collections.singletonMap("woOperationIdList", woOperationIdList);
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("woOperationIdList", map.get("woOperationIdList"));
		batchExecute(hql, values);
	}
	
	/**
	 * 根据id 删除  AntibodyOprPurificationResults
	 * @param list
	 */
	public void delAntibodyOprPurificationResultsById(List<Integer> list){
		String hql = "delete from AntibodyOprPurificationResults c where c.id in (:idList)";
		Map<String,List<Integer>> map = Collections.singletonMap("idList", list);
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("idList", map.get("idList"));
		batchExecute(hql, values);
	}
}
