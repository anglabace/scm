package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.OrganizationGroup;

@Repository
public class OrganizationGroupDao extends HibernateDao<OrganizationGroup, Integer> {
	/**
	 * 批量更新status.
	 * @param idList id list
	 * @param status 更新后的状态
	 */
	public void batchUpdateStatus(Object idList, String status,String updateStatusReason){
		String hql = "update OrganizationGroup c set c.status='" + status + "' , c.updateStatusReason='"+updateStatusReason+"' where c.id in (:idList)";
		Map<String,Object> map = Collections.singletonMap("idList", idList);
		batchExecute(hql, map);
	}
	
	/**
	 * 根据name查找OrganizationGroup的id列表
	 * @param groupName
	 * @return
	 */
	public List<Integer> queryIdListByName(String groupName) {
		String hql = "select id from OrganizationGroup where name like ?";
		return this.find(hql, "%" + groupName + "%");
	}

}
