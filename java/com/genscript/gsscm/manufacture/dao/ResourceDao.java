package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.Resource;

@Repository
public class ResourceDao extends HibernateDao<Resource, Integer> {

	/**
	 * 批量更新status.
	 * @param idList resourceId list
	 * @param status 更新后的状态
	 */
	public void batchUpdateStatus(Object idList, String status){
		String hql = "update Resource c set c.status='" + status + "' where c.resourceId in (:idList)";
		Map<String,Object> map = Collections.singletonMap("idList", idList);
		batchExecute(hql, map);
	}
	
	/**
	 * 分页查找（带user_dept模糊查找）
	 */
	public Page<Resource> findPage(Page<Resource> page,List<PropertyFilter> filters,Set<Integer> deptId) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		if(deptId.size()>0) {
			Criterion criterion = Restrictions.in("userDept", deptId);
			criterionList.add(criterion);
		}
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		return findPage(page, criterionList.toArray(new Criterion[criterionList
		                                          				.size()]));
	}
	
	public List<Resource> findByResorceNo(String resourceNo) {
		String hql = "from Resource where resourceNo=? and status='ACTIVE'";
		return this.find(hql, resourceNo);
	}
	
	public List<Resource> findByResourceNoAndDept(String resourceNo,Integer userDept) {
		String hql = "from Resource where resourceNo=? and userDept=? and status='ACTIVE'";
		return this.find(hql, resourceNo,userDept);
	}

}
