package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.Operation;

@Repository
public class OperationDao extends HibernateDao<Operation, Integer> {

	/**
	 * 批量更新status.
	 * @param idList id list
	 * @param status 更新后的状态
	 */
	public void batchUpdateStatus(Object idList, String status){
		String hql = "update Operation c set c.status='" + status + "' where c.id in (:idList)";
		Map<String,Object> map = Collections.singletonMap("idList", idList);
		batchExecute(hql, map);
	}
	
	/**
	 * 
	 */
	public Page<Operation> findPage(Page<Operation> page,List<PropertyFilter> filters,boolean flg) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		if(flg) {
			Criterion idCriterion = Restrictions.ne("id", -1);
			criterionList.add(idCriterion);
		}
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		return findPage(page, criterionList.toArray(new Criterion[criterionList
				.size()]));
	}
	
	/**
	 * 根据work_center_id 查询 name
	 * 
	 *
	 */
	public List<Operation> getOperation(Integer workCenterId){
		String hql = "SELECT o.name FROM Operation o where o.workCenterId="+workCenterId+"";
		List<Operation> operationList = this.find(hql);
		if (operationList == null || operationList.isEmpty()) {
			return null;
		}
		return operationList;
	}
}
