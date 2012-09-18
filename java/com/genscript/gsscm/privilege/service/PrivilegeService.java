package com.genscript.gsscm.privilege.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.PrivilegeType;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.util.PrivilegeServletUtil;
import com.genscript.gsscm.privilege.dao.EmployeeDao;
import com.genscript.gsscm.privilege.dao.LoginHistoryDao;
import com.genscript.gsscm.privilege.dao.PrivilegeDao;
import com.genscript.gsscm.privilege.dao.RoleDao;
import com.genscript.gsscm.privilege.dao.RolePrivilegeDao;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserPrivilegeDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.dto.RoleDTO;
import com.genscript.gsscm.privilege.dto.RoleSrchDTO;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.dto.UserSrchDTO;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.privilege.entity.LoginHistory;
import com.genscript.gsscm.privilege.entity.Privilege;
import com.genscript.gsscm.privilege.entity.Role;
import com.genscript.gsscm.privilege.entity.RolePrivilege;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.entity.UserPrivilege;
import com.genscript.gsscm.privilege.entity.UserRole;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class PrivilegeService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private PrivilegeDao privilegeDao;
	@Autowired
	private RolePrivilegeDao rolePrivilegeDao;
	@Autowired
	private UserPrivilegeDao userPrivilegeDao;
	@Autowired
	private LoginHistoryDao loginHistoryDao;
	@Autowired
	private DozerBeanMapper dozer;

	private final static String ROOTNODE = "0000";
	
	private Map<String,String> actionNameMappMethodNameMap = new HashMap<String,String>();

	@SuppressWarnings("unchecked")
	public Page<UserDTO> searchUser(Page<User> page, UserSrchDTO srch) {
		Page<UserDTO> retPage = new Page<UserDTO>();
		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		page = this.userDao.searchUser(page, srch);
		if (page.getResult() != null) {
			for (User user : page.getResult()) {
				UserDTO dto = dozer.map(user, UserDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}

	public void delRoleList(String[] roleNoArray) {
		for (String roleId : roleNoArray) {
			Integer roleNo = Integer.parseInt(roleId);
			this.rolePrivilegeDao.delByRole(roleNo);
			this.userRoleDao.delByRole(roleNo);
			roleDao.delete(roleNo);

		}
	}

	public Page<User> searchUserList(Page<User> page,
			List<PropertyFilter> filters){
		return userDao.findPage(page, filters);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<UserDTO> searchUser(Page<User> page,
			List<PropertyFilter> filters) {
		Page<UserDTO> retPage = new Page<UserDTO>();
		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		page = userDao.searchUser(page, filters);
		if (page.getResult() != null) {
			for (User user : page.getResult()) {
				UserDTO dto = dozer.map(user, UserDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}

	public Page<LoginHistory> searchLogin(Page<LoginHistory> page,
			Integer userId) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (userId != null && userId.intValue() != 0) {
			PropertyFilter filter = new PropertyFilter("EQI_user.userId",
					userId);
			filterList.add(filter);
		}
		if (page.getOrderBy() == null || page.getOrderBy().trim().length() < 1) {
			page.setOrder(Page.DESC);
			page.setOrderBy("loginDate");
		}
		return this.loginHistoryDao.findPage(page, filterList);
	}

	/**
	 * Get the menus list for a user by the user and the user's all roles.
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PrivilegeDTO> getMenuListForUser(Integer userId) {
		List<PrivilegeDTO> retList = new ArrayList<PrivilegeDTO>();
		List<Integer> pvlgIdList = this.privilegeDao
				.getPvlgIdListForUserByMenu(userId);
		if (pvlgIdList != null && !pvlgIdList.isEmpty()) {
			List<Privilege> rootLevelList = this.privilegeDao
					.getSubMenuWithPvlgIdList(ROOTNODE, pvlgIdList);
			List<Privilege> pvlgList = new ArrayList<Privilege>();
			List<Privilege> retPvlgList = this.getSubMenuList(pvlgList,
					rootLevelList, pvlgIdList);
			if (retPvlgList == null || retPvlgList.isEmpty()) {
				return null;
			}
			for (Privilege src : retPvlgList) {
				PrivilegeDTO dto = dozer.map(src, PrivilegeDTO.class);
				retList.add(dto);
			}
		}
		return retList;
	}

	private List<Privilege> getSubMenuList(List<Privilege> retList,
			List<Privilege> levelList, final List<Integer> pvlgIdList) {
		if (levelList != null) {
			for (Privilege pvlg : levelList) {
				if(pvlg!=null){
					retList.add(pvlg);
					List<Privilege> subList = new ArrayList<Privilege>();/*this.privilegeDao
							.getSubMenuWithPvlgIdList(pvlg.getPrivilegeCode(),
									pvlgIdList);*/
					String privilegeCode = pvlg.getPrivilegeCode();
					if(privilegeCode!=null){
						List<Privilege> allPrivilegeList = PrivilegeServletUtil.getPrivilegeList();
						if(allPrivilegeList!=null&&!allPrivilegeList.isEmpty()){
							for(Privilege privilege:allPrivilegeList){
								if(privilege!=null){
									if(privilegeCode.equals(privilege.getParentCode())&&privilege.getPrivilegeType().equals(PrivilegeType.MENU.value())){
										if(pvlgIdList!=null){
											for(Integer id : pvlgIdList){
												if(id.equals(privilege.getPrivilegeId())){
													System.out.println(privilege.getPrivilegeName());
													subList.add(privilege);
													break;
												}
											}
										}else{
											subList.add(privilege);
										}
									}
								}
							}
						}
						
						if (subList == null || subList.isEmpty()) {
							continue;
						} else {
							getSubMenuList(retList, subList, pvlgIdList);
						}
					}
					
				}
			}
		}
		return retList;
	}
	
	public List<Privilege> getAllPrivilege (){
		System.out.println(">>>>>>>>>>><<<<<<<<<<<<<<,");
		return this.privilegeDao.getAllPrivilegeList();
	}
/*	private List<PrivilegeDTO> getSubMenuDTOList(List<Privilege> retList,
			List<Privilege> levelList, final List<Integer> pvlgIdList) {
		if (levelList != null) {
			for (Privilege pvlg : levelList) {
				retList.add(pvlg);
				List<Privilege> subList = this.privilegeDao
						.getSubMenuWithPvlgIdList(pvlg.getPrivilegeCode(),
								pvlgIdList);
				if (subList == null || subList.isEmpty()) {
					continue;
				} else {
					getSubMenuList(retList, subList, pvlgIdList);
				}
			}
		}
		return retList;
	}*/

	/**
	 * Get the EC list for a user by the user and the user's all roles.
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PrivilegeDTO> getECListForUser(Integer userId) {
		List<PrivilegeDTO> retList = new ArrayList<PrivilegeDTO>();
		List<Privilege> rpList = this.privilegeDao.getPvlgListForUserByType(
				userId, PrivilegeType.EMAILCAMPAIGN);
		if (rpList != null) {
			for (Privilege src : rpList) {
				PrivilegeDTO dto = new PrivilegeDTO();
				dto = this.dozer.map(src, PrivilegeDTO.class);
				retList.add(dto);
			}
		}
		return retList;
	}

	/**
	 * Get the UIs list for a user by the user and the user's all roles.
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PrivilegeDTO> getUIListForUser(Integer userId) {
		List<PrivilegeDTO> retList = new ArrayList<PrivilegeDTO>();
		List<Privilege> rpList = this.privilegeDao.getPvlgListForUserByType(
				userId, PrivilegeType.UI);
		if (rpList != null) {
			for (Privilege src : rpList) {
				PrivilegeDTO dto = new PrivilegeDTO();
				dto.setPrivilegeCode(src.getPrivilegeCode());
				dto.setPrivilegeAction(src.getPrivilegeAction());
				retList.add(dto);
			}
		}
		return retList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<UserDTO> getUserListByRole(Page<UserRole> page, Integer roleId) {
		Page<UserDTO> retPage = null;
		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		page = this.userRoleDao.getUserListByRole(page, roleId);
		System.out.println("page: " + page);
		if (page.getResult() != null) {
			for (UserRole userRole : page.getResult()) {
				UserDTO dto = dozer.map(userRole.getUser(), UserDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}

	/**
	 * Get All privilege contains 'MENU' and 'UI' by roleId.
	 * 
	 * @param roleId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PrivilegeDTO> getPvlgListByRole(Integer roleId) {
		List<PrivilegeDTO> retList = new ArrayList<PrivilegeDTO>();
		List<Privilege> rpList = this.rolePrivilegeDao
				.getPvlgListByRole(roleId);
		if (rpList != null) {
			for (Privilege src : rpList) {
				PrivilegeDTO dto = dozer.map(src, PrivilegeDTO.class);
				retList.add(dto);
			}
		}
		return retList;
	}
	
	/**
	 * Get All privilege contains 'MENU' and 'UI' by roleId.
	 * 
	 * @param roleId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PrivilegeDTO> getPvlgListByRoleAndUI(Integer roleId) {
		List<PrivilegeDTO> retList = new ArrayList<PrivilegeDTO>();
		List<Privilege> rpList = this.rolePrivilegeDao
				.getPvlgListByRoleAndUI(roleId);
		if (rpList != null) {
			for (Privilege src : rpList) {
				PrivilegeDTO dto = dozer.map(src, PrivilegeDTO.class);
				retList.add(dto);
			}
		}
		return retList;
	}

	@Transactional(readOnly = true)
	public List<PrivilegeDTO> getPvlgListByUser(Integer userId) {
		List<PrivilegeDTO> retList = new ArrayList<PrivilegeDTO>();
		List<Privilege> rpList = this.userPrivilegeDao
				.getPvlgListByUser(userId);
		if (rpList != null) {
			for (Privilege src : rpList) {
				PrivilegeDTO dto = new PrivilegeDTO();
				dto.setParentCode(src.getParentCode());
				dto.setPrivilegeCode(src.getPrivilegeCode());
				dto.setPrivilegeAction(src.getPrivilegeAction());
				retList.add(dto);
			}
		}
		return retList;
	}
	
	@Transactional(readOnly = true)
	public List<PrivilegeDTO> getPvlgListByUserAndUI(Integer userId) {
		List<PrivilegeDTO> retList = new ArrayList<PrivilegeDTO>();
		List<Privilege> rpList = this.userPrivilegeDao
				.getPvlgListByUserAndUI(userId);
		if (rpList != null) {
			for (Privilege src : rpList) {
				PrivilegeDTO dto = new PrivilegeDTO();
				dto.setParentCode(src.getParentCode());
				dto.setPrivilegeCode(src.getPrivilegeCode());
				dto.setPrivilegeAction(src.getPrivilegeAction());
				retList.add(dto);
			}
		}
		return retList;
	}

	@Transactional(readOnly = true)
	public List<RoleDTO> getRoleListByUser(Integer userId) {
		List<RoleDTO> dtoList = new ArrayList<RoleDTO>();
		List<Role> roleList = this.userRoleDao.getRoleListByUser(userId);
		if (roleList != null) {
			for (Role role : roleList) {
				RoleDTO dto = dozer.map(role, RoleDTO.class);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@SuppressWarnings("unchecked")
	public Page<RoleDTO> searchRole(Page<Role> page, RoleSrchDTO srch) {
		Page<RoleDTO> retPage = new Page<RoleDTO>();
		List<RoleDTO> dtoList = new ArrayList<RoleDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (srch == null) {
			srch = new RoleSrchDTO();
		}
		if (srch.getRoleId() != null && srch.getRoleId().intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter("EQI_roleId", srch
					.getRoleId());
			filterList.add(orgFilter);
		}
		if (srch.getRoleName() != null
				&& srch.getRoleName().trim().length() > 0) {
			PropertyFilter filter = new PropertyFilter("LIKES_roleName", srch
					.getRoleName());
			filterList.add(filter);
		}
		page = this.roleDao.findPage(page, filterList);
		if (page.getResult() != null) {
			for (Role role : page.getResult()) {
				RoleDTO dto = dozer.map(role, RoleDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}

	public List<Privilege> getTreeAllOrderByCode() {
		return privilegeDao.getAllPrivilegeList();
	}
	
	public List<PrivilegeDTO> getTreeAll() {
		List<PrivilegeDTO> uiList = new ArrayList<PrivilegeDTO>();
		return this.getSubTreeAll(uiList, ROOTNODE);
	}

	private List<PrivilegeDTO> getSubTreeAll(List<PrivilegeDTO> retList,
			String parentCode) {
		List<Privilege> topList = this.privilegeDao.getSubList(parentCode);
		if (topList != null) {
			for (Privilege pvlg : topList) {
				PrivilegeDTO dto = dozer.map(pvlg, PrivilegeDTO.class);
				this.trimDTOofList(dto);
				retList.add(dto);
				retList = this.getSubTreeAll(retList, dto.getPrivilegeCode());
			}
		}
		return retList;
	}

	/**
	 * The DTO could set NULL value for some properties.
	 * 
	 * @param dto
	 */
	private void trimDTOofList(PrivilegeDTO dto) {
		dto.setCreatedBy(null);
		dto.setCreationDate(null);
		dto.setModifiedBy(null);
		dto.setModifyDate(null);
	}

	public UserDTO validUser(String loginName, String ipAddress) {
		User user = this.userDao.findUniqueBy("loginName", loginName);
		UserDTO dto = null;
		if (user != null) {
			dto = dozer.map(user, UserDTO.class);
			Date now = new Date();
			LoginHistory loginHistory = new LoginHistory();
			loginHistory.setIpAddress(ipAddress);
			loginHistory.setUser(user);
			loginHistory.setLoginDate(now);
			this.loginHistoryDao.save(loginHistory);
			dto.setLastLoginId(loginHistory.getLoginId());
		}
		return dto;
	}

	public LoginHistory logout(Integer userId, Integer loginId) {
		Date now = new Date();
		LoginHistory loginHistory = this.loginHistoryDao.get(loginId);
		loginHistory.setLogoutDate(now);
		this.loginHistoryDao.save(loginHistory);
		return loginHistory;
	}

	public UserDTO getUser(Integer userId) {
		User user = this.userDao.get(userId);
		UserDTO dto = null;
		if (user != null) {
			dto = dozer.map(user, UserDTO.class);
		}
		this.userDao.getSession().evict(user);
		return dto;
	}

	public UserDTO getUserByEmployeeId(Integer employeeId) {
		UserDTO dto = null;
		User user = this.userDao
				.findUniqueBy("employee.employeeId", employeeId);
		if (user != null) {
			dto = dozer.map(user, UserDTO.class);
		}
		this.userDao.getSession().evict(user);
		return dto;
	}

	public RoleDTO getRole(Integer roleId) {
		Role role = this.roleDao.getById(roleId);
		RoleDTO dto = null;
		if (role != null) {
			dto = dozer.map(role, RoleDTO.class);
		}
		return dto;
	}

	public User saveOrUpdateUser(UserDTO userDTO) {
		Integer loginUserId = userDTO.getModifiedBy();
		User user = dozer.map(userDTO, User.class);
		Date now = new Date();
		user.setModifyDate(now);
		Employee employee = user.getEmployee();
		if (employee != null) {
			employee.setCreatedBy(loginUserId);
			employee.setCreationDate(now);
			employee.setModifiedBy(loginUserId);
			employee.setModifyDate(now);
			this.employeeDao.getSession().evict(employee);
			this.employeeDao.save(employee);
			System.out.println("do save employee");
		}

		if (user.getEffFrom() != null && user.getEffFrom().trim().length() < 1) {
			user.setEffFrom(null);
		}

		if (user.getEffTo() != null && user.getEffTo().trim().length() < 1) {
			user.setEffTo(null);
		}
		if (userDTO.getUserId() == null || userDTO.getUserId().intValue() == 0) {// insert

			user.setUserId(null);
			user.setCreatedBy(userDTO.getModifiedBy());
			user.setCreationDate(now);
		}
		this.userDao.getSession().saveOrUpdate(user);
		this.userDao.getSession().flush();
		this.userDao.getSession().evict(user);
		//处理User和Role关联关系
		if (userDTO.getRoleIdList() != null && !userDTO.getRoleIdList().isEmpty()) {
			Map<String, Integer> dbRoleIdMap = new HashMap<String, Integer>();
			List<UserRole> urlist = userRoleDao.getUserListByUser(user.getUserId());
			if (urlist != null && !urlist.isEmpty()) {
				for (Iterator<UserRole> item = urlist.iterator();item.hasNext();) {
					UserRole ur = item.next();
					dbRoleIdMap.put(ur.getRole().getRoleId().toString(), ur.getId());
				}
			}
			for (Integer roleId : userDTO.getRoleIdList()) {
				if (roleId == null || roleId.intValue() == 0) {
					continue;
				}
				if (dbRoleIdMap != null && dbRoleIdMap.containsKey(roleId.toString())) {
					dbRoleIdMap.remove(roleId.toString());
					continue;
				}
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				Role role = new Role();
				role.setRoleId(roleId);
				userRole.setRole(role);
				userRole.setAssignedBy(loginUserId);
				userRole.setAssignDate(now);
				userRole.setCreatedBy(loginUserId);
				userRole.setCreationDate(now);
				userRole.setModifiedBy(loginUserId);
				userRole.setModifyDate(now);
				this.userRoleDao.save(userRole);
			}
			if (dbRoleIdMap != null && !dbRoleIdMap.isEmpty()) {
				for (String key : dbRoleIdMap.keySet()) {
					userRoleDao.delete(dbRoleIdMap.get(key));
				}
			}
		} else {
			userRoleDao.delByUser(userDTO.getUserId());
		}
		//处理User与权限的关联关系
		Map<String, Integer> dbPrivilegeIdMap = new HashMap<String, Integer>();
		List<UserPrivilege> uplist = userPrivilegeDao.getPvlgListByUserId(user.getUserId());
		if (uplist != null && !uplist.isEmpty()) {
			for (Iterator<UserPrivilege> item = uplist.iterator();item.hasNext();) {
				UserPrivilege up = item.next();
				dbPrivilegeIdMap.put(up.getPrivilege().getPrivilegeId().toString(), up.getId());
			}
		}
		if (userDTO.getPvlgIdList() != null && !userDTO.getPvlgIdList().isEmpty()) {
			//将页面传过来的privilegeId组成一个字符串来查询其每一级父项
			StringBuffer paramSbf = new StringBuffer();
			//页面传过来的privilegeId集合
			Map<String, Integer> paramPrivilegeIdMap = new HashMap<String, Integer>();
			List<Privilege> paramPrvList = privilegeDao.findPrivilegesByIds(userDTO.getPvlgIdList());
			for (Iterator<Privilege> item = paramPrvList.iterator();item.hasNext();) {
				Integer prvId = item.next().getPrivilegeId();
				paramSbf.append(prvId).append(",");
				paramPrivilegeIdMap.put(prvId.toString(), prvId);
			}
			//查询所有parentId
			String parentIdStr = "";
			try {
				parentIdStr = privilegeDao.getParentPrivByIds(paramSbf.toString().substring(0,paramSbf.toString().length()-1));
			} catch (Exception ex) {
				ex.printStackTrace();
				try {
					throw ex;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return user;
			}
			if (parentIdStr != null) {
				String[] parentIds = parentIdStr.split(",");
				for (String parentId : parentIds) {
					paramPrivilegeIdMap.put(parentId, Integer.parseInt(parentId));
				}
			}
			Iterator<Entry<String, Integer>> colItem = paramPrivilegeIdMap.entrySet().iterator();
			//保存用户新加的权限
			while (colItem.hasNext()) {
				int i = 0;
				Entry<String, Integer> entry = colItem.next();
				String prvIdKey = entry.getKey();
				Integer prvIdValue = entry.getValue();
				if (dbPrivilegeIdMap != null && dbPrivilegeIdMap.containsKey(prvIdKey)) {
					dbPrivilegeIdMap.remove(prvIdKey);
					continue;
				}
				UserPrivilege userPrivilege = new UserPrivilege();
				userPrivilege.setUser(user);
				Privilege privilege = new Privilege();
				privilege.setPrivilegeId(prvIdValue);
				userPrivilege.setPrivilege(privilege);
				userPrivilege.setGrantedBy(loginUserId);
				userPrivilege.setGrantDate(now);
				userPrivilege.setCreatedBy(loginUserId);
				userPrivilege.setCreationDate(now);
				userPrivilege.setModifiedBy(loginUserId);
				userPrivilege.setModifyDate(now);
				userPrivilegeDao.save(userPrivilege);
				if (i == 20) {
					userPrivilegeDao.getSession().flush();
					i = 0;
				} else {
					i++;
				}
			}
			//删除用户去掉的权限
			if (dbPrivilegeIdMap != null && !dbPrivilegeIdMap.isEmpty()) {
				List<Integer> grantIds = new ArrayList<Integer>();
				Iterator<Entry<String, Integer>> lastIdItem = dbPrivilegeIdMap.entrySet().iterator();
				while (lastIdItem.hasNext()) {
					Entry<String, Integer> upv = lastIdItem.next();
					Integer grantIdValue = upv.getValue();
					grantIds.add(grantIdValue);
				}
				try {
					//通过主键删除用户去掉的权限
					userPrivilegeDao.delByPvlgIds(grantIds);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else {
			this.userPrivilegeDao.delByUser(userDTO.getUserId());
		}
		return user;
	}

	public Role saveOrUpdateRole(RoleDTO roleDTO) {
		Integer loginUserId = roleDTO.getModifiedBy();
		Role role = dozer.map(roleDTO, Role.class);
		Date now = new Date();
		role.setModifyDate(now);
		if (roleDTO.getRoleId() == null || roleDTO.getRoleId().intValue() == 0) {// insert
			role.setRoleId(null);
			role.setCreatedBy(roleDTO.getModifiedBy());
			role.setCreationDate(now);
		}
		this.roleDao.save(role);
		List<RolePrivilege> dbRolePrvList = rolePrivilegeDao.getByRoleId(role.getRoleId());
		Map<String, Integer> dbRolePrvMap = new HashMap<String, Integer>();
		if (dbRolePrvList != null && !dbRolePrvList.isEmpty()) {
			for (Iterator<RolePrivilege> item = dbRolePrvList.iterator();item.hasNext();) {
				RolePrivilege rp = item.next();
				dbRolePrvMap.put(rp.getPrivilege().getPrivilegeId().toString(), rp.getId());
			}
		}
		if (roleDTO.getPvlgIdList() != null && !roleDTO.getPvlgIdList().isEmpty()) {
			//将页面传过来的privilegeId组成一个字符串来查询其每一级父项
			StringBuffer paramSbf = new StringBuffer();
			//页面传过来的privilegeId集合
			Map<String, Integer> paramPrivilegeIdMap = new HashMap<String, Integer>();
			List<Privilege> paramPrvList = privilegeDao.findPrivilegesByIds(roleDTO.getPvlgIdList());
			for (Iterator<Privilege> item = paramPrvList.iterator();item.hasNext();) {
				Integer prvId = item.next().getPrivilegeId();
				paramSbf.append(prvId).append(",");
				paramPrivilegeIdMap.put(prvId.toString(), prvId);
			}
			//查询所有parentId
			String parentIdStr = "";
			try {
				parentIdStr = privilegeDao.getParentPrivByIds(paramSbf.toString().substring(0,paramSbf.toString().length()-1));
			} catch (Exception ex) {
				ex.printStackTrace();
				try {
					throw ex;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return role;
			}
			if (parentIdStr != null) {
				String[] parentIds = parentIdStr.split(",");
				for (String parentId : parentIds) {
					paramPrivilegeIdMap.put(parentId, Integer.parseInt(parentId));
				}
			}
			Iterator<Entry<String, Integer>> colItem = paramPrivilegeIdMap.entrySet().iterator();
			//保存角色新加的权限
			while (colItem.hasNext()) {
				Entry<String, Integer> entry = colItem.next();
				String prvIdKey = entry.getKey();
				Integer prvIdValue = entry.getValue();
				if (dbRolePrvMap != null && dbRolePrvMap.containsKey(prvIdKey)) {
					dbRolePrvMap.remove(prvIdKey);
					continue;
				}
				//保存当前pvlgId所有父节点与角色的关联关系，主要是针对给角色分配权限时，半勾选状态的pvlgId未传过来
				RolePrivilege rolePrivilege = new RolePrivilege();
				Privilege inprivilege = new Privilege();
				inprivilege.setPrivilegeId(prvIdValue);
				rolePrivilege.setPrivilege(inprivilege);
				rolePrivilege.setRole(role);
				rolePrivilege.setGrantedBy(loginUserId);
				rolePrivilege.setGrantDate(now);
				rolePrivilege.setCreatedBy(loginUserId);
				rolePrivilege.setCreationDate(now);
				rolePrivilege.setModifiedBy(loginUserId);
				rolePrivilege.setModifyDate(now);
				this.rolePrivilegeDao.save(rolePrivilege);
			}
			//删除角色去掉的权限
			if (dbRolePrvMap != null && !dbRolePrvMap.isEmpty()) {
				List<Integer> grantIds = new ArrayList<Integer>();
				Iterator<Entry<String, Integer>> lastIdItem = dbRolePrvMap.entrySet().iterator();
				while (lastIdItem.hasNext()) {
					Entry<String, Integer> upv = lastIdItem.next();
					Integer grantIdValue = upv.getValue();
					grantIds.add(grantIdValue);
				}
				try {
					//通过主键删除角色去掉的权限
					rolePrivilegeDao.delByPvlgIds(grantIds);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else {
			this.rolePrivilegeDao.delByRole(roleDTO.getRoleId());
		}
		return role;
	}

	@SuppressWarnings("unused")
	private List<Integer> getRemoveIdList(List<Integer> dbIdList,
			List<Integer> tagIdList) {
		List<Integer> retList = new ArrayList<Integer>();
		if (dbIdList != null) {
			for (Integer dbId : dbIdList) {
				boolean bHave = false;
				for (Integer tagId : tagIdList) {
					if (dbId.equals(tagId)) {
						bHave = true;
						break;
					}
				}
				if (!bHave) {
					retList.add(dbId);
				}
			}
		}
		return retList;
	}

	@SuppressWarnings("unused")
	private List<Integer> getAddIdList(List<Integer> dbIdList,
			List<Integer> tagIdList) {
		List<Integer> retList = new ArrayList<Integer>();
		if (tagIdList != null) {
			for (Integer tagId : tagIdList) {
				boolean bHave = false;
				for (Integer dbId : dbIdList) {
					if (dbId.equals(tagId)) {
						bHave = true;
						break;
					}
				}
				if (!bHave) {
					retList.add(tagId);
				}
			}
		}
		return retList;
	}

	public void savePrivilege(List<PrivilegeDTO> dtoList, Integer userId) {
		if (dtoList == null || dtoList.isEmpty() || dtoList.get(0) == null) {
			return;
		}
		for (PrivilegeDTO pvlgDTO : dtoList) {
			if (pvlgDTO.getOperationType() != null
					&& pvlgDTO.getOperationType().equals(OperationType.DEL)) {
				this.userPrivilegeDao.delByPrivilege(pvlgDTO.getPrivilegeId());
				this.rolePrivilegeDao.delByPrivilege(pvlgDTO.getPrivilegeId());
				this.privilegeDao.delete(pvlgDTO.getPrivilegeId());
			} else {
				Privilege pvlg = null;
				if (pvlgDTO.getPrivilegeId() != null
						&& pvlgDTO.getPrivilegeId().intValue() > 0) {
					pvlg = this.privilegeDao.get(pvlgDTO.getPrivilegeId());
					// pvlg.setParentCode(pvlgDTO.getParentCode());
					// pvlg.setPrivilegeType(pvlgDTO.getPrivilegeType());
					pvlg.setPrivilegeName(pvlgDTO.getPrivilegeName());
					pvlg.setPrivilegeAttr(pvlgDTO.getPrivilegeAttr());
					pvlg.setPrivilegeAction(pvlgDTO.getPrivilegeAction());
					// pvlg.setRelatedPrivilege(pvlgDTO.getRelatedPrivilege());
					// pvlg.setDescription(pvlgDTO.getDescription());
				} else {
					pvlg = dozer.map(pvlgDTO, Privilege.class);
				}
				Date now = new Date();
				pvlg.setModifiedBy(userId);
				pvlg.setModifyDate(now);
				pvlg.setCreatedBy(userId);
				pvlg.setCreationDate(now);
				this.privilegeDao.save(pvlg);
			}
		}
	}

	public List<PrivilegeDTO> getParentPath(String privilegeCode) {
		List<PrivilegeDTO> retList = new ArrayList<PrivilegeDTO>();
		if (privilegeCode != null && !("").equals(privilegeCode)) {
			Privilege curtNode = privilegeDao.getPrivilegeByCode(privilegeCode);
			for (; curtNode != null;) {
				if (!curtNode.getParentCode().equals(ROOTNODE)) {
					Privilege parentNode = privilegeDao.getPrivilegeByCode(curtNode
							.getParentCode());
					PrivilegeDTO dto = dozer.map(parentNode, PrivilegeDTO.class);
					retList.add(dto);
					curtNode = parentNode;
				} else {
					break;
				}
			}
		}
		return retList;
	}

	/**
	 * 获得Manufacturing Work Group中的supervisor list对应的Role.
	 * 
	 * @return
	 */
	public Role getSuperRole() {
		String roleName = "WorkGroup Supervisor";// 暂且为固定的.
		return this.roleDao.getRoleByUniqueName(roleName);
	}

	/**
	 * 获得一个Role的所有User.
	 * 
	 * @param roleId
	 * @return
	 */
	public List<UserDTO> getUserListByRole(Integer roleId) {
		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		List<UserRole> list = this.userRoleDao.getUserListByRole(roleId);
		for (UserRole userRole : list) {
			UserDTO dto = dozer.map(userRole.getUser(), UserDTO.class);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	/**
	 * 获取所有数据库中未被使用过的Action名
	 * @param acMethodNameMap
	 * @return
	 * auther zhangyong
	 */
//	public String[] getAllActionName (Map<String, String> acMethodNameMap) {
//		String[] allActionNameArr = null;
//		if (acMethodNameMap != null && acMethodNameMap.size()>0) {
//			allActionNameArr = new String[acMethodNameMap.keySet().size()];
//			int i=0;
//			// 以下是将操作转化为XML。
//			for (String namespaceAndActionName : acMethodNameMap.keySet()) {
//				if (namespaceAndActionName == null || ("").equals(namespaceAndActionName)) {
//					continue;
//				}
//				String[] spaceAndActionName = namespaceAndActionName.split(",");
//				if (spaceAndActionName.length == 2) {
//					allActionNameArr[i] = spaceAndActionName[1];
//					i++;
//				}
//			}
//		}
//		return allActionNameArr;
//	}
	
	/**
	 * 获取所有的Action名和方法名，并将这些信息写到xml文件中
	 * @return
	 * auther zhangyong
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getAllActionAndMethod (String xmlPath, Integer userId) throws Exception {
		String directoty = this.getClassDir();
		@SuppressWarnings("rawtypes")
		List allActionNameList = new ArrayList();
		//过滤掉父类中的一些方法
		Map<String, String> spacialMethodName = new HashMap<String, String>();
//		spacialMethodName.put("execute", null);
		spacialMethodName.put("prepare", null);
		spacialMethodName.put("prepareInput", null);
		spacialMethodName.put("prepareSave", null);
		spacialMethodName.put("prepareModel", null);
		spacialMethodName.put("getModel", null);
		allActionNameList = directoty!= null? readFile (allActionNameList,directoty,""):allActionNameList;
		for (Object obj : allActionNameList) {
			getFieldNameAndMethodName(obj,spacialMethodName);
		}
		//往XML中写入
		return recordOperation(actionNameMappMethodNameMap, xmlPath, userId);
	} 
	
	/**
	 * 通过登陆类LoginAction获取Java类编译后的class文件路径
	 * @return Java类编译后的class文件路径
	 * auther zhangyong
	 */
	public String getClassDir () {
		String directoty = null;
		try {
			@SuppressWarnings("rawtypes")
			Class loginAction = Class.forName("com.genscript.gsscm.privilege.web.LoginAction");
			String classdir = loginAction.getName().replace(".", "/").concat(".class");
			URL result = null;
			ProtectionDomain pd = loginAction.getProtectionDomain();
			if (pd != null) {
				final CodeSource cs = pd.getCodeSource();
				if (cs != null) {
					result = cs.getLocation();
				}
				if (result != null) {
					if ("file".equals(result.getProtocol())) {
						try {
				    		if (result.toExternalForm().endsWith(".jar") 
				    				|| result.toExternalForm().endsWith(".zip")) {
				    			result = new URL("jar:".concat(
				    					result.toExternalForm()).concat("!/")
				    					.concat(classdir));
					    	} else if (new File(result.getFile()).isDirectory()) {
						       result = new URL(result, classdir);
					    	}
					    } catch (MalformedURLException ignore) {
					    }
					}
			    }
			}
			if (result != null) {
				directoty = result.getPath().substring(1, result.getPath().length()).replace("%20", " ");
				String namespace = "/com/genscript/gsscm/";
				if (directoty.lastIndexOf(namespace) != -1) {
					directoty = directoty.substring(0, directoty.lastIndexOf(namespace)+namespace.length());
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return directoty;
	}
	
	/**
	 * 获取dir路径下的所有web文件下的所有Action
	 * @param allActionNameList
	 * @param dir
	 * @param packg
	 * @return
	 * auther zhangyong
	 */
	public List<String> readFile (List<String> allActionNameList, String dir, String packg) {
        File directory = new File(dir);
        if (directory.exists() && directory.isDirectory()) {
           File files[]=directory.listFiles();   
           for (int i=0;i<files.length;i++) {   
               if (files[i].isFile()) {  
            	   String fielname = files[i].getName();
            	   if (fielname.contains("Action.class")) {
            		   String[] packgArr = packg.substring(packg.indexOf("/com/genscript/gsscm/")+1, 
            				   packg.length()).split("/");
            		   StringBuffer sbf = new StringBuffer();
            		   for (String pg : packgArr) {
            			   sbf.append(pg+"."); 
            		   }
            		   String pkgActionName = sbf.toString() + fielname.substring(0, fielname.indexOf("."));
            		   allActionNameList.add(pkgActionName);
            	   }
               } else {   
            		   if (("web").equals(files[i].getName())) {
            			   packg = dir + files[i].getName();
            		   }
            		   readFile(allActionNameList, dir + files[i].getName() + "/", packg);
               }   
           }
        } else if (directory.isFile() && directory.getName().endsWith("Action.class")) {
        	String[] packgArr = packg.substring(packg.indexOf("/com/genscript/gsscm/")+1, 
 				   packg.length()).split("/");
 		    StringBuffer sbf = new StringBuffer();
 		    for (String pg : packgArr) {
 			   sbf.append(pg+"."); 
 		    }
 		    String pkgActionName = sbf.toString() + directory.getName().substring(0, directory.getName().indexOf("."));
 		    allActionNameList.add(pkgActionName);
        }
        return allActionNameList;
	}
	
	/**
	 * 获取某一个类下的所有Action方法
	 * @param obj
	 * @param spacialMethodName
	 * auther zhangyong
	 */
	public void getFieldNameAndMethodName (Object obj, Map<String, String> spacialMethodName) {
		List<String> methodlist = new ArrayList<String>();
		List<String> fieldlist = new ArrayList<String>();
		StringBuffer sbf = new StringBuffer();
		String actionName = null;
		String nameSapce = null;
		@SuppressWarnings("rawtypes")
		Class ownerClass = null;
		try {
			ownerClass = Class.forName(obj.toString());
		} catch (ClassNotFoundException e) {}
		if (ownerClass != null) {
			Method[] methods = ownerClass.getDeclaredMethods();
			for (Method method : methods) {
				if (("public").equals(method.toString().substring(0, 6))) {
					String methodName02 = method.toString().substring(0, method.toString().indexOf("("));
					String methodName = methodName02.substring(methodName02.lastIndexOf(".")+1, 
							methodName02.length());
					if (!spacialMethodName.containsKey(methodName)) {
						methodlist.add(methodName);
					}
				}
			}
			Field[] fields = ownerClass.getDeclaredFields();
			for (Field field : fields) {
				String midname = field.toString().substring(0, field.toString().indexOf(".web."));
				nameSapce = nameSapce == null?midname.substring(midname.lastIndexOf(".")+1, 
						midname.length()):nameSapce;
				actionName = actionName == null?field.toString().substring(field.toString()
						.indexOf(".web.")+5, field.toString().lastIndexOf(".")):actionName;
				String fieldName = field.toString().substring(field.toString().lastIndexOf(".")+1, 
						field.toString().length());
				String getFieldName = new StringBuffer().append("get")
						.append(fieldName.substring(0,1).toUpperCase())
						.append(fieldName.substring(1, fieldName.length()))
						.toString();
				String setFieldName = new StringBuffer().append("set")
						.append(fieldName.substring(0,1).toUpperCase())
						.append(fieldName.substring(1, fieldName.length()))
						.toString();
				String isFieldName = new StringBuffer().append("is")
						.append(fieldName.substring(0,1).toUpperCase())
						.append(fieldName.substring(1, fieldName.length()))
						.toString();
				fieldlist.add(getFieldName);
				fieldlist.add(setFieldName);
				fieldlist.add(isFieldName);
			}
			
			for (String fieldName : fieldlist) {
				if (methodlist != null && methodlist.size() > 0) {
					for (String methodName : methodlist) {
						if (methodName.equals(fieldName)) {
							methodlist.remove(methodName);
							break;
						}
					}
				}
			}
			if (methodlist != null && methodlist.size() > 0) {
				for (String methodName : methodlist) {
					sbf.append(methodName).append(",");
				}
			}
		}
		actionNameMappMethodNameMap.put(actionName, nameSapce+"-"+sbf.toString());
	}
	
	/**
	 * 将内容写入allActionMethodName.xml文件中
	 * @param acMethodNameMap
	 * @throws IOException
	 * auther zhangyong
	 */
	public Map<String,String> recordOperation(Map<String,String> acMethodNameMap, String xmlPath, Integer userId) throws IOException {
		if (acMethodNameMap.size() == 0) {
			return null;
		}
		Map<String,String> actionNameMap = new HashMap<String,String>();
		int i=1;
		for (String actionName : acMethodNameMap.keySet()) {
			i++;
			//Action名为空或不包含"Action"循环下一个
			if (actionName == null || ("").equals(actionName.trim())
					|| !actionName.trim().contains("Action")) {
				continue;
			}
			//namespaceAndMethodNames内容类似如：namespace-method,method,method
			String namespaceAndMethodNames = acMethodNameMap.get(actionName);
			//没有"-"标示没有namespace,则循环下一个
			if (!namespaceAndMethodNames.contains("-")) {
				continue;
			}
			String actionDBName = getActionDbName(actionName);
			String[] namespaceAndMethodNameArr = namespaceAndMethodNames.split("-");
			String namespace = namespaceAndMethodNameArr[0];
			String methodNames = null;
			if (namespaceAndMethodNameArr.length == 2) {
				methodNames = namespaceAndMethodNameArr[1];
			}
			//通过Action名查询该条记录得到ActionCode
			List<Privilege> privilegeByActNameList = privilegeDao.getPrivilegeByActionName(actionName);
			if (privilegeByActNameList != null && privilegeByActNameList.size() > 0) {
				for (Privilege privilegeByActName : privilegeByActNameList) {
					//查询该Action下的所有action方法
					List<Privilege> privilegeList = privilegeDao.getPrivilegeByActionPriCode(privilegeByActName.getPrivilegeCode());
					Map<String,Integer> prileNameIDmap = new HashMap<String,Integer>();
					if (privilegeList != null && privilegeList.size() >0) {
						long maxMethodCode = Long.parseLong(privilegeList.get(0).getPrivilegeCode());
						for (Privilege privilege : privilegeList) {
							prileNameIDmap.put(privilege.getPrivilegeName(), privilege.getPrivilegeId());
						}
						if (methodNames != null && !("").equals(methodNames)) {
							String[] methodNameArr = methodNames.split(",");
							if (methodNameArr.length > 0) {
								int j=0;
								for (String methodName : methodNameArr) {
									if (("").equals(methodName)) {
										continue;
									}
									if (!prileNameIDmap.containsKey(methodName)) {
										j++;
										String methodCode = maxMethodCode + j + "";
										//methodName在数据库中没有则存入数据库中
										Privilege insertMethodPrivilege = new Privilege();
										insertMethodPrivilege.setPrivilegeCode(methodCode);
										insertMethodPrivilege.setParentCode(privilegeByActName.getPrivilegeCode());
										insertMethodPrivilege.setPrivilegeType("UI");
										insertMethodPrivilege.setPrivilegeName(methodName);
										insertMethodPrivilege.setPrivilegeAction(namespace+"/"+actionDBName+"!"+methodName+".action");
										Date nowDate  = new Date();
										if (userId != null) {
											insertMethodPrivilege.setCreatedBy(userId);
											insertMethodPrivilege.setModifiedBy(userId);
										}
										insertMethodPrivilege.setCreationDate(nowDate);
										insertMethodPrivilege.setModifyDate(nowDate);
										privilegeDao.save(insertMethodPrivilege);
									} else {
										//从数据库中过滤掉这次已搜索到的
										prileNameIDmap.remove(methodName);
									}
								}
								//****************************************
								//删除同一Action中数据库有而搜索结果集中无的记录
//								if (prileNameIDmap != null && prileNameIDmap.size() >0) {
//									for (String key : prileNameIDmap.keySet()) {
//										privilegeDao.delete(prileNameIDmap.get(key));
//									}
//								}
								//****************************************
							}
						}
					} else {
						//该Action在数据库中无actionMethod记录，则判断搜集到的此Action是否有actionMethod，
						//如果有则记录到数据库中并不记录此Action
						if (methodNames != null && !("").equals(methodNames)) {
							String[] methodNameArr = methodNames.split(",");
							if (methodNameArr.length > 0) {
								int j=0;
								for (String methodName : methodNameArr) {
									if (("").equals(methodName)) {
										continue;
									}
									j++;
									String methodCode = privilegeByActName.getPrivilegeCode() + 
											(j<10?("50"+j):(j<100?("5"+j):
											(privilegeByActName.getPrivilegeCode()+500+j)));
									//methodName在数据库中没有则存入数据库中
									Privilege insertprivilege = new Privilege();
									insertprivilege.setPrivilegeCode(methodCode);
									insertprivilege.setParentCode(privilegeByActName.getPrivilegeCode());
									insertprivilege.setPrivilegeType("UI");
									insertprivilege.setPrivilegeName(methodName);
									insertprivilege.setPrivilegeAction(namespace+"/"+actionName+"!"+methodName+".action");
									Date nowDate  = new Date();
									if (userId != null) {
										insertprivilege.setCreatedBy(userId);
										insertprivilege.setModifiedBy(userId);
									}
									insertprivilege.setCreationDate(nowDate);
									insertprivilege.setModifyDate(nowDate);
									privilegeDao.save(insertprivilege);
								}
							}
						}
					}
				}
			}
			//将所有的Action放入Map中供前台显示
			actionNameMap.put(actionName, namespaceAndMethodNames);
		}
		return actionNameMap;
	}
	
	/**
	 * 保存模块与Action间的映射关系
	 * @param moduleCode
	 * @param actionNames
	 * @return
	 * author zhangyong
	 */
	public String saveMappingUI (String moduleCode, String actionNames, int userId) throws Exception {
		String status = "";
		if (moduleCode == null || ("").equals(moduleCode)) {
			status = "Please select a munu!";
			return status;
		}
		if (actionNames == null || ("").equals(actionNames) || actionNames.trim().split("-").length == 0) {
			status = "Please select at less one Action!";
			return status;
		}
		//查询是否存在
		Privilege privilege = privilegeDao.getPrivilegeByCode(moduleCode);
		if (privilege == null) {
			status = "The menu is not exist!";
			return status;
		}
		//判断是否为MENU
		if (!("MENU").equals(privilege.getPrivilegeType())) {
			status = "Please select a MENU!";
			return status;
		}
///////////////////////////////////////////////////////////		
		//判断是否为最低一级的MENU
//		List<Privilege> menuPrivilegeList = privilegeDao.getMenuPrivilegeByModuleCode(moduleCode);
//		if (menuPrivilegeList != null && menuPrivilegeList.size() > 0) {
//			status = "The menu is not last one!";
//			return status;
//		}
///////////////////////////////////////////////////////////		
		
		
		//获得所有数据库中没有的Action及对应的namespace和actionmethod 
		String xmlPath = ServletActionContext.getServletContext().getRealPath("/xml");
		Map<String,String> actionNameAndSpaceMethodMap = getAllActionAndMethod(xmlPath,userId);
		
		String[] actionArr = actionNames.split("-");
		for (String actionName : actionArr) {
			if (!actionNameAndSpaceMethodMap.containsKey(actionName)) {
				continue;
			}
			//每一条记录类似格式为namespace-method,method,...
			String[] namespaceAndMethodNameArr = actionNameAndSpaceMethodMap.get(actionName).split("-");
			String namespace = namespaceAndMethodNameArr[0];
			if (("").equals(namespace)) {
				continue;
			}
			Privilege privilegeByModuleCode = privilegeDao.getUIPrivilegeByModuleCode(moduleCode);
			//保存Action
			Privilege actionPrivilege = new Privilege();
			Date nowDate = new Date();
			if (privilegeByModuleCode != null) {
				long methodCode = Long.parseLong(privilegeByModuleCode.getPrivilegeCode()) + 1;
				actionPrivilege.setPrivilegeCode(String.valueOf(methodCode));				
			} else {
				actionPrivilege.setPrivilegeCode(moduleCode+"501");
			}
			actionPrivilege.setParentCode(moduleCode);
			actionPrivilege.setPrivilegeType("UI");
			actionPrivilege.setPrivilegeName(actionName);
			actionPrivilege.setPrivilegeAction("#");
			actionPrivilege.setCreationDate(nowDate);
			actionPrivilege.setCreatedBy(userId);
			actionPrivilege.setModifyDate(nowDate);
			actionPrivilege.setModifiedBy(userId);
			privilegeDao.save(actionPrivilege);
			//保存method
			if (namespaceAndMethodNameArr.length == 2) {
				String methods = namespaceAndMethodNameArr[1];
				String[] methodArr = methods.trim().split(",");
				if (methodArr.length != 0) {
					int i = 0;
					for (String methodName : methodArr) {
						i++;
						String methodPrivilegeType = namespace+"/"+getActionDbName(actionName)+"!"+methodName+".action";
						Privilege methodprivilege = new Privilege();
						String methodCode =  i<10?("50"+i):(i<100?("5"+i):500+i+"");
						methodprivilege.setPrivilegeCode(actionPrivilege.getPrivilegeCode()+methodCode);
						methodprivilege.setParentCode(actionPrivilege.getPrivilegeCode());
						methodprivilege.setPrivilegeType("UI");
						methodprivilege.setPrivilegeName(methodName);
						methodprivilege.setPrivilegeAction(methodPrivilegeType);
						methodprivilege.setCreationDate(nowDate);
						methodprivilege.setCreatedBy(userId);
						methodprivilege.setModifyDate(nowDate);
						methodprivilege.setModifiedBy(userId);
						privilegeDao.save(methodprivilege);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 将Action转换，如PrivilegeAction  ->  privilege
	 * @param actionName
	 * @return
	 */
	public String getActionDbName (String actionName) {
		StringBuffer actionDBName = new StringBuffer();
		String actionDeltail = actionName.substring(0, 1).toLowerCase() + actionName.substring(1);
		
		//将Action名去掉"Action"的结尾，然后第一个字母改为小写，后面遇到大写的字母前面加"_"并将字母改为小写
		actionDeltail = actionDeltail.substring(0, actionDeltail.lastIndexOf("Action"));
		
		for (int j=0;j<actionDeltail.length();j++) {
			if (String.valueOf(actionDeltail.charAt(j)).matches("[A-Z]")) {
				actionDBName.append("_" + String.valueOf(actionDeltail.charAt(j))
						.toLowerCase());
			} else {
				actionDBName.append(String.valueOf(actionDeltail.charAt(j)));
			}
		}
		return actionDBName.toString();
	} 
	
	/**
	 * 校验模块Code是否还有下一级MENU
	 * @param moduleCode
	 * @return
	 * auther zhangyong
	 */
	public boolean checkModuleCode (String moduleCode) {
		List<Privilege> privilegelist = privilegeDao.getMenuPrivilegeByModuleCode(moduleCode);
		if (privilegelist != null && privilegelist.size() >0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 通过当前登录用户的ID查询用户所拥有的角色中是否含有销售部经理的角色(超级管理员拥有所有权限)，有返回true，无则返回false
	 * @return
	 */
	public boolean checkIsSalesManagerRole ()  throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (Constants.USERNAME_ADMIN.equals(userName)) {
			return true;
		} else {
			return userRoleDao.checkIsContainsManagerRole(Constants.ROLE_SALES_MANAGER);
		}
	}
	
	/**
	 * 通过当前登录用户的ID查询用户所拥有的角色中是否含有生产部经理的角色(超级管理员拥有所有权限)，
	 * 有返回true，无则返回false
	 * @author zhangyong
	 * @return
	 */
	public boolean checkIsProductionManagerRole ()  throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (Constants.USERNAME_ADMIN.equals(userName)) {
			return true;
		} else {
			return userRoleDao.checkIsContainsManagerRole(Constants.ROLE_PRODUCTION_MANAGER);
		}
	}
	
	/**
	 * 通过privilegeName查询
	 * @author zhangyong
	 * @param privilegeName
	 * @return
	 */
	public Privilege getPrivilegeByPrvName (String privilegeName) {
		if (privilegeName != null && !("").equals(privilegeName.trim())) {
			List<Privilege> prvlist = this.privilegeDao.getPrivilegeByPrvName(privilegeName.trim());
			if (prvlist != null && prvlist.size() >0) {
				return prvlist.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 通过loginName查找user
	 */
	public User findByLoginName(String loginName) {
		return this.userDao.findUniqueBy("loginName", loginName);
	}
	
	/**
	 * 通过EmployeeId查找Employee
	 */
	public Employee findEmployeeById(Integer employeeId) {
		return this.employeeDao.getById(employeeId);
	}
	
	/**
	 * 通过userId查询用户
	 * @author Zhang Yong
	 * @param userId
	 * @return
	 */
	public User findUserByUserId (Integer userId) {
		return userDao.getById(userId);
	}
}
