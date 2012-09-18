package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.manufacture.entity.WorkCenter;

@Repository
public class WorkCenterDao extends HibernateDao<WorkCenter, Integer> {

	/**
	 * 批量更新status.
	 * @param idList id list
	 * @param status 更新后的状态
	 */
	public void batchUpdateStatus(Object idList, String status){
		String hql = "update WorkCenter c set c.status='" + status + "' where c.id in (:idList)";
		Map<String,Object> map = Collections.singletonMap("idList", idList);
		batchExecute(hql, map);
	}
	
	/**
	 * 查询所有WorkCenter对象
	 */
	public List<WorkCenter> findAll(String order,String orderBy) {
		String hql = "from WorkCenter order by "+orderBy+" "+order;
		return this.find(hql);
	}
	
	public List<WorkCenter> findBy(final String propertyName, final Object value,final String order,String orderBy) {
		String hql = "from WorkCenter where "+propertyName+"=? order by "+orderBy+" "+order;
		return this.find(hql, value);
	}
	
	public Page<WorkCenter> findPageForCenter(final Page<WorkCenter> page,final List<PropertyFilter> filters,Set<Integer> centerIdSet) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		if(centerIdSet!=null&&!centerIdSet.isEmpty()) {
			Criterion criterion = Restrictions.in("id", centerIdSet);
			criterionList.add(criterion);
			Criterion criterionTemp =null;
			for(Criterion criterionIndex:criterionList) {
				if(criterionTemp==null) {
					criterionTemp = criterionIndex;
				} else {
					criterionTemp = Restrictions.and(criterionTemp, criterionIndex);
				}
			}
			criterionList.clear();
			Criterion criterion1 = Restrictions.eq("createdBy", SessionUtil.getUserId());
			criterionList.add(Restrictions.or(criterionTemp, criterion1));
		} 
		return findPage(page, criterionList.toArray(new Criterion[criterionList
				.size()]));
	}
	
	/**
	 * 通过name模糊查找所有匹配的id
	 * @param deptName
	 * @return
	 */
	public List<Integer> findByName(String name) {
		String hql="select id from WorkCenter where name like'%'||:name||'%'";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		return this.find(hql, map);
	}
	

	/**
	 * 通过用户Id查询该用户同一部门的WC的下内部订单的custNo
	 * modify date 2011-10-17
	 * @author Zhang Yong
	 * @param userId
	 * @return
	 */
	public List<Integer> getInternalCustNoByUserId (Integer userId) {
		String hql = "select wc.internalCustNo from WorkCenter wc, User u where u.userId=:userId and u.deptId = wc.deptId and wc.internalCustNo is not null";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return this.find(hql, map);
	}

}
