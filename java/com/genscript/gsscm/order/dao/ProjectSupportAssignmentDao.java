package com.genscript.gsscm.order.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.ProjectSupportAssignment;

/**
 * 
 * @author zhangyong
 *
 */
@Repository
public class ProjectSupportAssignmentDao extends HibernateDao<ProjectSupportAssignment, Integer> {

	/**
	 * 通过主键查询
	 * @param id
	 * @return
	 */
	public ProjectSupportAssignment findById (Integer id) {
		if (id != null) {
			return this.findUniqueBy("id", id);
		}
		return null;
	}
}
