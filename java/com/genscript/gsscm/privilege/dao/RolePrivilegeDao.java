package com.genscript.gsscm.privilege.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.privilege.entity.Privilege;
import com.genscript.gsscm.privilege.entity.RolePrivilege;

@Repository
public class RolePrivilegeDao extends HibernateDao<RolePrivilege, Integer> {
	private static final String DELETE__BY_ID = "delete from RolePrivilege up where up.id in (:ids)";
	
	public List<Privilege> getPvlgListByRole(Integer roleId) {
		String hql = "select rp.privilege from RolePrivilege rp where rp.role.roleId=:roleId ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		return this.find(hql, map);
	}
	
	public List<Privilege> getPvlgListByRoleAndUI(Integer roleId) {
		String hql = "select rp.privilege from RolePrivilege rp, Privilege p where rp.role.roleId=:roleId "
			+ " and rp.privilege.privilegeType = 'UI' and rp.privilege.parentCode = p.privilegeCode and "
			+ " p.privilegeAction = '#'";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		return this.find(hql, map);
	}

	public void delByRole(Integer roleId) {
		String hql = "delete from RolePrivilege where role.roleId =:roleId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		batchExecute(hql, map);
	}

	/**
	 * 根据privilegeId删除roleprivilege
	 * @param privilegeId
	 */
	public void delByPrivilege(Integer privilegeId) {
		String hql = "delete from RolePrivilege where privilege.privilegeId =:privilegeId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("privilegeId", privilegeId);
		batchExecute(hql, map);
	}
	
	public RolePrivilege getByRoleAndPvlg(Integer roleId, Integer privilegeId) {
		RolePrivilege rolePrivilege = null;
		String hql = "from RolePrivilege where role.roleId=:roleId and privilege.privilegeId =:privilegeId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("privilegeId", privilegeId);
		List<RolePrivilege> list = this.find(hql, map);
		if (list != null && list.size() > 0) {
			rolePrivilege = list.get(0);
		}
		return rolePrivilege;
	}
	
	/**
	 * 通过角色主键Id查询角色权限的集合
	 * @author Zhang Yong
	 * @param roleId
	 * @return
	 */
	public List<RolePrivilege> getByRoleId (Integer roleId) {
		String hql = "FROM RolePrivilege rp WHERE rp.role.roleId=:roleId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		return this.find(hql, map);
	}
	
	/**
	 * 通过主键批量删除
	 * @author Zhang Yong
	 * @param ids
	 */
	public void delByPvlgIds(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DELETE__BY_ID, map);
	}
}
