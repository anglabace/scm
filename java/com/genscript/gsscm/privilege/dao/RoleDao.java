package com.genscript.gsscm.privilege.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.privilege.entity.Role;

@Repository
public class RoleDao extends HibernateDao<Role, Integer> {
   /**
    * 根据name获得唯一的role, 注意: role表中并没有要求name是唯一的
    * @param name
    * @return
    */
	public Role getRoleByUniqueName(String roleName) {
		Role role = null;
		String hql = "from Role where roleName=:roleName";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		List<Role> list = this.find(hql, map);
		if (list != null && !list.isEmpty()) {
			role = list.get(0);
		}
		return role;
   }

}
