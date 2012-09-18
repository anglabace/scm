package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrder;

@Repository
public class WorkGroupDao extends HibernateDao<WorkGroup, Integer> {

	/**
	 * 批量更新status.
	 * 
	 * @param idList
	 *            id list
	 * @param status
	 *            更新后的状态
	 */
	public void batchUpdateStatus(Object idList, String status) {
		String hql = "update WorkGroup c set c.status='" + status
				+ "' where c.id in (:idList)";
		Map<String, Object> map = Collections.singletonMap("idList", idList);
		batchExecute(hql, map);
	}

	/**
	 * 获得WorkGroup list根据workCenterId.
	 * 
	 * @param workCenterId
	 * @return
	 */
	public List<WorkGroup> getGroupListByCenter(Integer workCenterId) {
		String hql = "from WorkGroup c where c.workCenterId=:workCenterId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workCenterId", workCenterId);
		return this.find(hql, map);
	}

	/**
	 * 批量关联orkGroup所属的workCenterId.
	 * 
	 * @param idList
	 *            id list
	 * @param workCenterId
	 *            更新到的workCenterId
	 */
	public void batchAttachCenter(Object idList, Integer workCenterId) {
		String hql = "update WorkGroup c set c.workCenterId=" + workCenterId
				+ " where c.id in (:idList)";
		Map<String, Object> map = Collections.singletonMap("idList", idList);
		batchExecute(hql, map);
	}
	
	/**
	 * 批量消除关联.
	 * @param idList
	 * @param workCenterId
	 */
	public void batchDetachCenter(Object idList) {
		String hql = "update WorkGroup c set c.workCenterId=null where c.id in (:idList)";
		Map<String, Object> map = Collections.singletonMap("idList", idList);
		batchExecute(hql, map);
	}
	
	/**
	 * 查找一个WorkCenter可选的Work Group list.(涉及到'不等于' '或' '非空'几种关系用默认的框架无法解决.
	 * 
	 * @param page
	 * @param filters
	 * @param workCenterId
	 * @return
	 */
	public Page<WorkGroup> findPage(final Page<WorkGroup> page,
			final List<PropertyFilter> filters, Integer workCenterId) {
		List<Criterion> finalList = new ArrayList<Criterion>();
/*
		// 1. '不等于' '或' '非空'的逻辑
		Disjunction disjunction = Restrictions.disjunction();
		// 不等于
		Criterion neCriterion = buildPropertyFilterCriterion("workCenterId",
				workCenterId, java.lang.Integer.class,
				PropertyFilter.MatchType.NE);
		// 或为空
		Criterion nullCriterion = buildPropertyFilterCriterion("workCenterId",
				null, java.lang.Integer.class, PropertyFilter.MatchType.NULL);
		disjunction.add(neCriterion);
		disjunction.add(nullCriterion);
		finalList.add(disjunction);
*/
		PropertyFilter pf = new PropertyFilter("NULLI_workCenterId", workCenterId);
		filters.add(pf);
		// 2. 追加获得到的查询条件
		List<Criterion> criterionList = this.buildCriterions(filters);
		if (criterionList!=null && !criterionList.isEmpty()) {
			finalList.addAll(criterionList);
		}
		Criterion[] criterions = finalList
				.toArray(new Criterion[finalList.size()]);
		return findPage(page, criterions);
	}

	
	public Page<WorkGroup> findPage(final Page<WorkGroup> page,
			final List<PropertyFilter> filters, List<Integer> workGroupIdList) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		// 判断是否有或的查询条件.
		
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		if (workGroupIdList != null && !workGroupIdList.isEmpty()) {
			Criterion criterion = Restrictions.in("id", workGroupIdList);
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
	 * 获得查询条件
	 * 
	 * @param filters
	 * @return
	 */
	protected List<Criterion> buildCriterions(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			if (!filter.isMultiProperty()) { // There is only one property
				// need to compare the
				// situation.
				Criterion criterion = buildPropertyFilterCriterion(filter
						.getPropertyName(), filter.getPropertyValue(), filter
						.getPropertyType(), filter.getMatchType());
				criterionList.add(criterion);
			} else {// Contain multiple attributes need to compare the situation
				// to carry out or deal with.
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildPropertyFilterCriterion(param,
							filter.getPropertyValue(),
							filter.getPropertyType(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList;
	}
	
	
	/**
	 * 查询所有WorkCenter对象
	 */
	public List<WorkGroup> findAll() {
		String hql = "from WorkGroup";
		return this.find(hql);
	}
}
