package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.RouteOperation;

@Repository
public class RouteOperationDao extends HibernateDao<RouteOperation, Integer> {

	/**
	 * 获得一个Routing相关的Operation.
	 * @param operationId
	 * @return
	 */
	public List<RouteOperation> getAllList(Integer routingId) {
		String hql = "from RouteOperation gr where gr.routingId=? order by seqNo asc";
		return this.find(hql, routingId);		
	}
	
	/**
	 * 批量删除Route Operation.
	 * @param ids
	 */
	public void delRouteOperationList(Object ids){
		String hql = "delete from RouteOperation c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
}
