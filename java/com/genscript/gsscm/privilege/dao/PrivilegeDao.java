package com.genscript.gsscm.privilege.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.PrivilegeType;
import com.genscript.gsscm.privilege.entity.Privilege;

@Repository
public class PrivilegeDao extends HibernateDao<Privilege, Integer> {

	private static final String DELETE_PVLG = "delete from Privilege c where c.privilegeId in (:ids)";
	private static final String SELECT_PVLG = "FROM Privilege p WHERE p.privilegeId in (:ids)";
	
	
	public void delPvlgList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DELETE_PVLG, map);
	}
	
	/**
	 * 通过Id查询
	 * @author Zhang Yong
	 * @param ids
	 * @return
	 */
	public List<Privilege> findPrivilegesByIds (Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		return find(SELECT_PVLG, map);
	}
	
	public  List<Privilege> getAllPrivilegeList(){
		String hql = "from Privilege c order by privilegeCode";
		return this.find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Privilege> getSubList(final String parentCode) {
		List<Privilege> menuList = null;
		String hql = "from Privilege Where parentCode=? order by privilegeCode";
		Query q = createQuery(hql, parentCode);
		menuList = q.list();
		return menuList;
	}
	
	public List<Privilege> getSubMenuWithPvlgIdList(final String parentCode, List<Integer> pvlgIdList) {
		String hql = "from Privilege Where parentCode=:parentCode and privilegeType=:privilegeType";
		if (pvlgIdList!=null && !pvlgIdList.isEmpty()) {
			hql += " AND privilegeId in (:pvlgIdList)";
		}
		hql += " order by privilegeCode";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentCode", parentCode);
		map.put("privilegeType", PrivilegeType.MENU.value());
		map.put("pvlgIdList", pvlgIdList);
		return this.find(hql, map);
	}
	
	public Privilege getPrivilegeByCode(String pvlgCode) {
		String hql = "from Privilege Where privilegeCode=:privilegeCode";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("privilegeCode", pvlgCode);
		Privilege pvlg = this.findUnique(hql, map);
		return pvlg;
	}

	@SuppressWarnings("unchecked")
	public List<Privilege> getRootUIList() {
		String hql = "from Privilege prv Where privilegeType=? and exists ";
		hql += "(SELECT privilegeCode from Privilege Where parentCode=prv.privilegeCode and privilegeType=? order by privilegeCode)";
		Query q = createQuery(hql, PrivilegeType.MENU.value(), PrivilegeType.UI
				.value());
		List<Privilege> uiList = q.list();
		return uiList;
	}
	

	public List<Integer> getPvlgIdListForUserByMenu(Integer userId) {
		//Get user's roleList
		String roleHql = "select ur.role.roleId from UserRole ur where ur.user.userId=:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		List<Integer> roleIdList = this.find(roleHql, map);
		//Get privilegeId by the user's roleList
		List<Integer> rolePvlgIdList = new ArrayList<Integer>();
		if (roleIdList != null && !roleIdList.isEmpty()) {
			String rolePvlgHql = "select distinct rp.privilege.privilegeId from RolePrivilege rp where rp.role.roleId IN (:roleIdList) and rp.privilege.privilegeType=:privilegeType";
			Map<String, Object> pvlgMaps = new HashMap<String, Object>();
			pvlgMaps.put("roleIdList", roleIdList);
			pvlgMaps.put("privilegeType", PrivilegeType.MENU.value());
			rolePvlgIdList = this.find(rolePvlgHql, pvlgMaps);
		}
        //Get privilegeId by the user only.
		String userPvlgHql = "select userPv.privilege.privilegeId from UserPrivilege userPv where userPv.user.userId=:userId and userPv.privilege.privilegeType=:privilegeType";
		Map<String, Object> userPvlgMap = new HashMap<String, Object>();
		userPvlgMap.put("userId", userId);
		userPvlgMap.put("privilegeType", PrivilegeType.MENU.value());
		List<Integer> userPvlgIdList = this.find(userPvlgHql, userPvlgMap);
        //Combine the privilegeId between from the user and from the user's roleList.
		rolePvlgIdList.addAll(userPvlgIdList);
		return rolePvlgIdList;
	}

	public List<Privilege> getPvlgListForUserByType(Integer userId,
			PrivilegeType privilegeType) {
		String privilegeHql = "select distinct p from Privilege p where " +
				"p.privilegeId in (select distinct userPv.privilege.privilegeId from UserPrivilege userPv " +
				"where userPv.user.userId="+userId+" and userPv.privilege.privilegeType='"+privilegeType.value()+"')" +
				" or " +
				"p.privilegeId in (select distinct rp.privilege.privilegeId from RolePrivilege rp " +
				"where rp.role.roleId IN (select ur.role.roleId from UserRole ur where ur.user.userId="+userId+") " +
						"and rp.privilege.privilegeType='"+privilegeType.value()+"')";
		return this.find(privilegeHql);
		/*//Get user's roleList
		String roleHql = "select ur.role.roleId from UserRole ur where ur.user.userId=:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		List<Integer> roleIdList = this.find(roleHql, map);
		//Get privilegeId by the user's roleList
		List<Integer> rolePvlgIdList = new ArrayList<Integer>();
		if (roleIdList != null && !roleIdList.isEmpty()) {
			String rolePvlgHql = "select distinct rp.privilege.privilegeId from RolePrivilege rp where rp.role.roleId IN (:roleIdList) and rp.privilege.privilegeType=:privilegeType";
			Map<String, Object> pvlgMaps = new HashMap<String, Object>();
			pvlgMaps.put("roleIdList", roleIdList);
			pvlgMaps.put("privilegeType", privilegeType.value());
			rolePvlgIdList = this.find(rolePvlgHql, pvlgMaps);
		}
        //Get privilegeId by the user only.
		String userPvlgHql = "select userPv.privilege.privilegeId from UserPrivilege userPv where userPv.user.userId=:userId and userPv.privilege.privilegeType=:privilegeType";
		Map<String, Object> userPvlgMap = new HashMap<String, Object>();
		userPvlgMap.put("userId", userId);
		userPvlgMap.put("privilegeType", privilegeType.value());
		List<Integer> userPvlgIdList = this.find(userPvlgHql, userPvlgMap);
        //Combine the privilegeId between from the user and from the user's roleList.
		rolePvlgIdList.addAll(userPvlgIdList);
		List<Integer> privilegeIdList = rolePvlgIdList;
		//Get the privilegeList from the user and the user's roleList.
		if (privilegeIdList != null && !privilegeIdList.isEmpty()) {
			String hql = "select p from Privilege p where p.privilegeId in (:privilegeIdList) order by privilegeCode";
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("privilegeIdList", privilegeIdList);
			return this.find(hql, values);
		}*/
	}
	
	/**
	 * 通过Action名查询数据库中是否存在
	 * @param actionName
	 * @return
	 * auther zhangyong
	 */
	public List<Privilege> getPrivilegeByActionName (String actionName) {
		String hql = "FROM Privilege WHERE privilegeType = 'UI' AND privilegeAction = '#' AND privilegeName = ?";
		return this.find(hql, actionName);
	}
	
	/**
	 * 通过ActionCode查询该Action下的所有方法
	 * @param actionPrivilegeCode
	 * @return
	 * auther zhangyong
	 */
	public List<Privilege> getPrivilegeByActionPriCode (String actionPrivilegeCode) {
		String hql = "FROM Privilege WHERE parentCode = ? ORDER BY privilegeCode DESC";
		return this.find(hql, actionPrivilegeCode);
	}
	
	/**
	 * 通过模块编码查询下一级目录(这里用于判断传进来的模块编码是否还有是MENU的下一级)
	 * @param moduleCode
	 * @return
	 * auther zhangyong
	 */
	public List<Privilege> getMenuPrivilegeByModuleCode (String moduleCode) {
		return this.find("FROM Privilege a WHERE a.parentCode = ? AND a.privilegeType = 'MENU'", moduleCode);
	}
	
	/**
	 * 查询模块下privilegeCode最大的Action
	 * @param moduleCode
	 * @return
	 * auther zhangyong
	 */
	public Privilege getUIPrivilegeByModuleCode (String moduleCode) {
		List<Privilege> privilegeList = this.find("FROM Privilege a WHERE a.parentCode = ? AND a.privilegeType = 'UI' ORDER BY a.privilegeCode DESC  LIMIT 1 ", moduleCode);
		if (privilegeList != null && privilegeList.size() >0) {
			return privilegeList.get(0);
		}
		return null;
	}
	
	/**
	 * 通过privilegeName查询
	 * @author zhangyong
	 * @param privilegeName
	 * @return
	 */
	public List<Privilege> getPrivilegeByPrvName (String privilegeName) {
		return this.find("FROM Privilege WHERE privilegeName = ?",privilegeName);
	}
	
	/**
	 * 通过privilegeId查询所有parentId
	 * @author Zhang Yong
	 * @param privIds
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getParentPrivByIds (final String privIds) {
		final String obj;
		HibernateTemplate hibernateTemplate = new HibernateTemplate(this.sessionFactory);
		obj = (String) hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				@SuppressWarnings("deprecation")
				Connection conn = s.connection();
				CallableStatement cstmt = conn.prepareCall("{call system.sp_get_parent_privilege_list(?,?)}");
				cstmt.setString(1, privIds);
				cstmt.registerOutParameter("o_parent_privilege_list", java.sql.Types.LONGVARCHAR);
				cstmt.execute();
				String strs = cstmt.getString("o_parent_privilege_list");
				return strs;
			}
		});
		return obj;
	}
}
