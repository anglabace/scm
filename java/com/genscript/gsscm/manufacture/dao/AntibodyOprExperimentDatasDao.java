package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.AntibodyOprExperimentDatas;

@Repository
public class AntibodyOprExperimentDatasDao extends HibernateDao<AntibodyOprExperimentDatas, Integer>{
	
	/**
	 * 根据WoOperation List 删除  AntibodyOprExperimentDatas
	 * @param woOperationIdList
	 */
	public void delAntibodyOprExperimentDatasByWoOPerationList(List<Integer> woOperationIdList){
		String hql = "delete from AntibodyOprExperimentDatas c where c.woOperationId in (:woOperationIdList)";
		Map<String,List<Integer>> map = Collections.singletonMap("woOperationIdList", woOperationIdList);
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("woOperationIdList", map.get("woOperationIdList"));
		batchExecute(hql, values);
	}

}
