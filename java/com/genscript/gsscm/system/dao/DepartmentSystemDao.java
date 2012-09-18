package com.genscript.gsscm.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.DepartmentSystem;

/**
 * 
 * 
 * 
 * @author mingrs
 */
@Repository
public class DepartmentSystemDao extends HibernateDao<DepartmentSystem, Integer>{
	/**
	 * 通过deptName模糊查找所有匹配的deptId
	 * @param deptName
	 * @return
	 */
	public List<Integer> findByDeptName(String deptName) {
		String hql="select deptId from DepartmentSystem where name like'%'||:name||'%'";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", deptName);
		return this.find(hql, map);
	}
	
	/**
	 * 
	 */
	public List<DepartmentSystem> findAll(String order,String orderBy) {
		String hql = "from DepartmentSystem order by "+orderBy+" "+order;
		return this.find(hql);
	}
	
}
