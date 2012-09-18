package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.Route;
import com.genscript.gsscm.manufacture.entity.WorkCenter;

@Repository
public class RouteDao extends HibernateDao<Route, Integer> {

	/**
	 * 批量更新status.
	 * @param idList id list
	 * @param status 更新后的状态
	 */
	public void batchUpdateStatus(Object idList, String status){
		String hql = "update Route c set c.status='" + status + "' where c.id in (:idList)";
		Map<String,Object> map = Collections.singletonMap("idList", idList);
		batchExecute(hql, map);
	}
	
	/**
	 * 更新defaultFlag
	 */
	public void batchUpdateDefaultFlag(Route route) {
		String hql = "update Route c set c.defaultFlag='N' where c.id<>"+route.getId()+" and c.itemType='"+route.getItemType()+"' and c.clsId="+route.getClsId();
		batchExecute(hql);
	}

	/**
	 * 分组查询
	 * @author Zhang Yong
	 * @return
	 */
	public List<Object[]> findTypeClsId () {
		String hql = "SELECT rt.itemType, rt.clsId FROM Route rt GROUP BY rt.itemType, rt.clsId";
		List<Object[]> tyleClsIdList = this.find(hql);
		if (tyleClsIdList == null || tyleClsIdList.isEmpty()) {
			return null;
		}
		return tyleClsIdList;
	}
	
	/**
	 * 根据clsId,itemType,warehouseId查找route
	 */
	public List<Route> findRoute(Integer clsId,String itemType,Integer warehouseId) {
		String hql = "from Route  where clsId=? and itemType=? and warehouseId=? order by name asc";
		return this.find(hql,clsId,itemType,warehouseId);
		
	}
	
	public List<Route> findAll(String order,String orderBy) {
		String hql = "from Route order by "+orderBy+" "+order;
		return this.find(hql);
	}
	

	/**
	 * 根据workCenterId,warehouseId查找route
	 */
	public List<Route> findRoute(Integer workCenterId,Integer warehouseId) {
		String sql = "select distinct r.* from manufacturing.routings r left join manufacturing.work_center_assigned w on r.cls_id= w.cls_id and r.item_type=w.item_type " +
				"where r.warehouse ="+warehouseId+" and w.work_center_id="+workCenterId;
		return this.getSession().createSQLQuery(sql).addEntity("r",Route.class).list();
		
	}
}
