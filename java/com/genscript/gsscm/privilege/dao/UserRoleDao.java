package com.genscript.gsscm.privilege.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.privilege.entity.Role;
import com.genscript.gsscm.privilege.entity.UserRole;
import com.opensymphony.xwork2.ActionContext;

@Repository
public class UserRoleDao extends HibernateDao<UserRole, Integer> {
	public void delByUserAndRoleList(Integer userId, List<Integer> roleIdList) {
		String hql = "delete from UserRole where user.userId=:userId and role.roleId =:roleId";
		for (Integer roleId : roleIdList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("roleId", roleId);
			batchExecute(hql, map);
		}
	}

	public void delByRole(Integer roleId) {
		String hql = "delete from UserRole where role.roleId =:roleId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		batchExecute(hql, map);
	}

	public void delByUser(Integer userId) {
		String hql = "delete from UserRole where user.userId =:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		batchExecute(hql, map);
	}
	
	public List<Role> getRoleListByUser(Integer userId) {
		String hql = "select ur.role from UserRole ur where ur.user.userId=:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return this.find(hql, map);
	}

	public Page<UserRole> getUserListByRole(Page<UserRole> page, Integer roleId) {
		String hql = "from UserRole ur where ur.role.roleId=:roleId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		return this.findPage(page, hql, map);
	}
	
	/**
	 * 获得一个Role对应的所有UserRole.
	 * @param roleId
	 * @return
	 */
	public List<UserRole> getUserListByRole(Integer roleId) {
		String hql = "from UserRole ur where ur.role.roleId=:roleId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		return this.find(hql, map);
	}
	
	/**
	 * 获得一个User对应的所有UserRole.
	 * @param userId
	 * @return
	 */
	public List<UserRole> getUserListByUser(Integer userId) {
		String hql = "from UserRole ur where ur.user.userId=:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return this.find(hql, map);
	}

	/**
	 * 通过用户表的主键Id和角色表的主键Id查询关联关系数据
	 * @author Zhang Yong
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public List<UserRole>  getByUserAndRole(Integer userId, Integer roleId) {
		String hql = "from UserRole ur where ur.user.userId=:userId and ur.role.roleId =:roleId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return this.find(hql, map);
	}
	
	/**
	 * 通过当前登录用户的ID查询用户所拥有的角色中是否含有指定的角色，有返回true，无则返回false
	 * @return
	 */
	public boolean checkIsContainsManagerRole (String roleName) {
		boolean isContainsManagerRole = false;
		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer userId = (Integer)session.get(StrutsActionContant.USER_ID);
		List<Role> userRolelist = this.getRoleListByUser(userId);
		if (userRolelist != null && userRolelist.size() > 0) {
			for (Role role:userRolelist) {
				if (roleName.equals(role.getRoleName())) {
					isContainsManagerRole = true;
					break;
				}
			}
		}
		return isContainsManagerRole;
	}
}
