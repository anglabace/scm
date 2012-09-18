package com.genscript.gsscm.privilege.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.privilege.entity.Privilege;
import com.genscript.gsscm.privilege.entity.UserPrivilege;

@Repository
public class UserPrivilegeDao extends HibernateDao<UserPrivilege, Integer> {
	
	private static final String DELETE__BY_ID = "delete from UserPrivilege up where up.id in (:ids)";
	
	public void delByUser(Integer userId) {
		String hql = "delete from UserPrivilege where user.userId =:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		batchExecute(hql, map);
	}
	
	/**
	 * 根据Privilege删除UserPrivilege
	 * @param privilegeId
	 */
	public void delByPrivilege(Integer privilegeId) {
		String hql = "delete from UserPrivilege where privilege.privilegeId =:privilegeId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("privilegeId", privilegeId);
		batchExecute(hql, map);
	}
	
	public List<Privilege> getPvlgListByUser(Integer userId) {
		String hql = "select userPv.privilege from UserPrivilege userPv where userPv.user.userId=:userId ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return this.find(hql, map);
	}
	
	/**
	 * 通过用户主键Id查询用户权限的集合
	 * @author Zhang Yong
	 * @param userId
	 * @return
	 */
	public List<UserPrivilege> getPvlgListByUserId(Integer userId) {
		String hql = "From UserPrivilege userPv where userPv.user.userId=:userId ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return this.find(hql, map);
	}
	
	public List<Privilege> getPvlgListByUserAndUI(Integer userId) {
		String hql = "select userPv.privilege from UserPrivilege userPv, Privilege p where "
			+ " userPv.user.userId=:userId and userPv.privilege.privilegeType = 'UI' "
			+ " and userPv.privilege.parentCode = p.privilegeCode and p.privilegeAction = '#'";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return this.find(hql, map);
	}

	public UserPrivilege getByUserAndPvlg(Integer userId, Integer privilegeId) {
		UserPrivilege userPrivilege = null;
		String hql = "from UserPrivilege where user.userId=:userId and privilege.privilegeId =:privilegeId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("privilegeId", privilegeId);
		List<UserPrivilege> list = this.find(hql, map);
		if (list != null && list.size() > 0) {
			userPrivilege = list.get(0);
		}
		return userPrivilege;
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
