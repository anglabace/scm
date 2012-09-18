package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.GroupResource;

@Repository
public class GroupResourceDao extends HibernateDao<GroupResource, Integer> {

	/**
	 * 根据(work)groupId获得所有的Resource, 从对象GroupResource中查找.
	 * @param groupId
	 * @return
	 */
	public List<GroupResource> getGroupResourceList(Integer groupId) {
		String hql = "from GroupResource gr where gr.groupId=?";
		return this.find(hql, groupId);
	}
	
	/**
	 * 批量删除Group Resource
	 * @param ids
	 */
	public void delGroupResourceList(Object ids){
		String hql = "delete from GroupResource c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
}
