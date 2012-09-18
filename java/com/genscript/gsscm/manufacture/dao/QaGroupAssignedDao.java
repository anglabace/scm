package com.genscript.gsscm.manufacture.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.QaGroupAssigned;

@Repository
public class QaGroupAssignedDao extends HibernateDao<QaGroupAssigned, Integer>{
	
	/**
	 * 根据groupId删除关联的所有的QaGroupAssigned对象
	 */
	public void deleteByGroupId(Integer groupId) {
		String hql = "delete from QaGroupAssigned aga where aga.qaGroup.id="+groupId;
		this.batchExecute(hql);
	}

}
