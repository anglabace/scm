package com.genscript.gsscm.privilege.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.dto.RoleDTO;
import com.genscript.gsscm.privilege.dto.RoleSrchDTO;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.dto.UserSrchDTO;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.privilege.entity.Role;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.entity.UserRole;
import com.genscript.gsscm.privilege.service.PrivilegeService;

@Results( {
		@Result(location = "user/user_manage_frame.jsp"),
		@Result(name = "manageList", location = "user/user_manage_list.jsp"),
		@Result(name = "roleList", location = "user/role_manage_list.jsp"),
		@Result(name = "userSearch", location = "user/user_search.jsp"),
		@Result(name = "roleSearch", location = "user/role_search.jsp"),
		@Result(name = "userMngSch", location = "user/user_manage_list.jsp"),
		@Result(name = "roleMngSch", location = "user/role_manage_list.jsp"),
		@Result(name = "userInfoFrame", location = "user/user_Info_Frame.jsp"),
		@Result(name = "roleInfoFrame", location = "user/role_Info_Frame.jsp"),
		@Result(name = "showUserInf", location = "user/user_person_Inf.jsp"),
		@Result(name = "showRoleInf", location = "user/role_person_Inf.jsp"),
		@Result(name = "searchUserListByRole", location = "user/search_user_list_byRole.jsp"),
		@Result(name = "searchUserList", location = "user/choose_user_list.jsp"),
		@Result(name = "userOrRole_privilege_acl", location = "user/userOrRole_privilege_acl.jsp") })
public class UserAction extends BaseAction<User> {

	
	private static final long serialVersionUID = -256680314502180140L;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private PrivilegeService privilegeService;

	private Page<UserDTO> pageUserDTO;

	private Page<UserRole> pageUseRole;

	private Page<UserDTO> pageUserDTOList;

	private UserDTO userDto;

	private RoleDTO roleDto;

//	private HttpServletRequest request;

	private Integer id = null;
	private UserDTO user = null;
	private Employee employee = null;
	private Integer sign;

	private UserSrchDTO userSrchDTO;

	private RoleSrchDTO roleSrchDTO;

	private RoleDTO role;

	private Page<RoleDTO> pageRoleDTO;

	private Page<User> pageuser;
	
	private List<RoleDTO> listRole;

	private List<PrivilegeDTO> userListPrivilegeAcl;

	private List<PrivilegeDTO> roleListPrivilegeAcl;

	private int userId;
	private static int roleId;
	private static int searRoleId;
	private Integer employeeId;
	private String choiceVal;//列表页面选择的项

	/**
	 * 运行User_Manage_Frame框架页面
	 * @return String
	 * @author zhangyang
	 */
	public String execute() throws Exception {

		return SUCCESS;
	}

	/**
	 * 删除Customer并返回删除的个数和删除成功的个数
	 * 
	 * @return delTotal,delSuccessCount
	 * 
	 * @throws Exception
	 */
	public String  delete() {
		try{
			String delIdStr = ServletActionContext.getRequest().getParameter("roleIds");
			if (StringUtils.isNotEmpty(delIdStr)) {
				String[] roleNoArray = delIdStr.split(",");
				List<String> delIdList = Arrays.asList(roleNoArray);				
//				for (String roleNo : roleNoArray) {
//					Integer roleId=Integer.parseInt(roleNo);
//					privilegeService.delPvlgList(roleNoArray);
//				}
				privilegeService.delRoleList(roleNoArray);
					Struts2Util.renderText("success");
					
			}
		}catch(Exception e){
			e.printStackTrace();
			Struts2Util.renderText("error");
		}
			return NONE;
		
	}
	
	/**
	 * 此方法返回所有用户信息列表
	 * 
	 * @return String
	 * @author zhangyang
	 */
	@SuppressWarnings("unchecked")
	public String manageList() throws Exception {
		PagerUtil<UserDTO> pagerUtil = new PagerUtil<UserDTO>();
		pageUserDTO = pagerUtil.getRequestPage();
		pageUserDTO.setPageSize(10);
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		Page<User> retPage = this.dozer.map(pageUserDTO, Page.class);

		if (filters == null || filters.isEmpty()) {
			UserSrchDTO srch = null;
			pageUserDTO = this.privilegeService.searchUser(retPage, srch);
		} else {
			pageUserDTO = this.privilegeService.searchUser(retPage, filters);
		}
		pageUserDTO.setOrder("asc");
		pageUserDTO.setOrderBy("employeeId");
		PageDTO pagerInfo = pagerUtil.formPage(pageUserDTO);
		Integer count=pageUserDTO.getResult().size();
		ServletActionContext.getRequest().setAttribute("count",count);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "manageList";
	}

	/**
	 * 此方法返回所有角色信息列表
	 * 
	 * @return String
	 * @author zhangyang
	 */
	@SuppressWarnings("unchecked")
	public String RoleList(){
		try{
		PagerUtil<RoleDTO> pageUtil = new PagerUtil<RoleDTO>();
		pageRoleDTO = pageUtil.getRequestPage();
		pageRoleDTO.setPageSize(10);
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		Page<Role> retPage = this.dozer.map(pageRoleDTO, Page.class);

		if (filters == null || filters.isEmpty()) {
			RoleSrchDTO srch = null;
			pageRoleDTO = this.privilegeService.searchRole(retPage, srch);
		} else {
		}
		pageRoleDTO.setOrder("asc");
		pageRoleDTO.setOrderBy("roleId");

		PageDTO pageInfo = pageUtil.formPage(pageRoleDTO);

		ServletActionContext.getRequest().setAttribute("pagerInfo", pageInfo);

		}catch(Exception e){
			e.printStackTrace();
		}
		return "roleList";

	}

	/**
	 * 此方法返回员工搜索页面
	 * 
	 * @return String
	 * @author zhangyang
	 */
	public String userSearch() {
		return "userSearch";
	}

	/**
	 * 此方法返回角色搜索页面
	 * 
	 * @return String
	 * @author zhangyang
	 */
	public String roleSearch() {
		return "roleSearch";
	}

	/**
	 * 此方法返回搜索出的用户信息返回页面显示
	 * 
	 * @return String
	 * @author zhangyang
	 */
	@SuppressWarnings("unchecked")
	public String userMngSch() throws Exception {
		Page<User> retPage = new Page<User>();
		List<User> dtoList = new ArrayList<User>();
		pageUserDTO = new Page<UserDTO>();
		for (UserDTO userDTO : this.pageUserDTO.getResult()) {
			User dto = dozer.map(userDTO, User.class);
			dtoList.add(dto);
		}
		this.pageUserDTO.setResult(null);
		retPage = dozer.map(this.pageUserDTO, Page.class);
		retPage.setResult(dtoList);
		retPage.setPageSize(10);
		pageUserDTO = this.privilegeService.searchUser(retPage, userSrchDTO);
		if (pageUserDTO.getResult() == null
				|| (pageUserDTO.getResult().size() < 1)) {
			sign = 1;
			ServletActionContext.getRequest().setAttribute("sign", sign);
		}
		Integer count=pageUserDTO.getResult().size();
		ServletActionContext.getRequest().setAttribute("count",count);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pageUserDTO);
		return "userMngSch";
	}

	/**
	 * 此方法返回搜索出的角色信息返回页面显示
	 * 
	 * @return String
	 * @author zhangyang
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public String roleMngSch() throws Exception {
		Page<Role> retPage = new Page<Role>();
		List<Role> dtoList = new ArrayList<Role>();
		pageRoleDTO = new Page<RoleDTO>();
		for (RoleDTO userDTO : this.pageRoleDTO.getResult()) {
			Role dto = dozer.map(pageRoleDTO, Role.class);
			dtoList.add(dto);
		}
		this.pageRoleDTO.setResult(null);
		retPage = dozer.map(this.pageRoleDTO, Page.class);
		retPage.setResult(dtoList);
		retPage.setPageSize(10);
		pageRoleDTO = this.privilegeService.searchRole(retPage, roleSrchDTO);
		if (pageRoleDTO.getResult() == null
				|| pageRoleDTO.getResult().size() < 1) {
			sign = 1;
			ServletActionContext.getRequest().setAttribute("sign", sign);
		}
		Integer count=pageRoleDTO.getResult().size();
		ServletActionContext.getRequest().setAttribute("count",count);
		ServletActionContext.getRequest().setAttribute("pagerInfo",pageRoleDTO);
		return "roleMngSch";
	}

	/**
	 * 记录页面获取的UserID,转向userInfoFrame页面加载信息
	 * 
	 * @return String
	 * @author zhangyang
	 */
	public String getUserPSInf() {
		if(employeeId==null){
			userId=010101;
		}else{
			userId = this.privilegeService.getUserByEmployeeId(
					employeeId).getUserId();
		}
		//传入一个userId参数，根据userId查出用户有哪些角色，
		listRole = this.privilegeService.getRoleListByUser(userId);
		if(listRole!=null) {
			StringBuffer str = new StringBuffer("");
			for(RoleDTO role:listRole) {
				if("".equals(str.toString())) {
					str.append(role.getRoleId());
				} else {
					str.append(",").append(role.getRoleId());
				}
			}
			choiceVal = str.toString();
		}
		return "userInfoFrame"; 
	}

	/**
	 * 记录页面获取的RoleID，转向roleInfoFrame页面加载信息
	 * 
	 * @return String
	 * @author zhangyang
	 */
	public String getRolePSInf() {
		Integer roleid = null;
		if (ServletActionContext.getRequest().getParameter("roleId") != null) {
			roleid = Integer.parseInt(ServletActionContext.getRequest().getParameter("roleId"));
			UserAction.roleId = roleid;
			UserAction.searRoleId = roleid;
		} else {
			UserAction.roleId = 0;
		}
		return "roleInfoFrame";
	}

	/**
	 * @查询User信息转向页面显示
	 * @return String
	 * @author zhangyang
	 */
	public String showUserInf() {
		try{
			userDto = this.privilegeService.getUser(userId);
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return "showUserInf";
	}

	/**
	 * 查询Role信息转向页面显示
	 * 
	 * @return String
	 * @author zhangyang
	 */
	public String showRoleInf() {
		if (UserAction.roleId != 0) {
			roleDto = this.privilegeService.getRole(UserAction.roleId);
		}
		return "showRoleInf";
	}

	/**
	 * 修改或保存UserInfo到数据库
	 * 
	 * @param
	 * @return String
	 * @author zhangyang
	 */
	public String saveUser() {
		String xml = null;
		String temp = ServletActionContext.getRequest().getParameter("employeeId");
		List<Integer> listRoleid = saveRoleId();
		List<Integer> listPrivilegeId = savePrivilege();
		boolean b = stringOrNumber(temp);
		if (b == true) {
			id = Integer.parseInt(temp);
			if (this.privilegeService.getUserByEmployeeId(id) == null) {
				user = new UserDTO();
				employee = new Employee();
				modifyOrNewUser();
				user.setEmployee(employee);
				user.setRoleIdList(listRoleid);
				user.setPvlgIdList(listPrivilegeId);
				user.setModifiedBy(SessionUtil.getUserId());
				user.setCompanyId(1);
				if(user.getLoginName().equals(employee.getEmployeeName())){
					xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><result>3</result></root>";
					Struts2Util.renderXml(xml);
					return null;
				}
				this.privilegeService.saveOrUpdateUser(user);
			} else {
				System.out.println("------------------------->>>>>>>>>>>>>>>>>>>>>>update="+id);
				user = this.privilegeService.getUserByEmployeeId(id);
				
				Integer tempId = this.privilegeService.getUserByEmployeeId(
						id).getUserId();
				System.out.println("--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>userId=="+tempId);
				user.setUserId(tempId);
				employee = user.getEmployee();
				employee.setEmployeeId(id);
				modifyOrNewUser();
				user.setEmployee(employee);
				user.setRoleIdList(listRoleid);
				user.setPvlgIdList(listPrivilegeId);
				user.setModifiedBy(SessionUtil.getUserId());
				user.setCompanyId(1);
				System.out.println("--------------->>>>>>>>>>>>>>>>>>>.user="+user);
				this.privilegeService.saveOrUpdateUser(user);
			}
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><result>1</result></root>";
			Struts2Util.renderXml(xml);
			return null;
		} else {
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><result>2</result></root>";
			Struts2Util.renderXml(xml);
			return null;
		}
	}

	/**
	 * 此方法获取页面字段数据
	 * 
	 * @return String
	 * @author zhangyang
	 */
	@SuppressWarnings("unused")
	public void modifyOrNewUser() {
		String status = ServletActionContext.getRequest().getParameter("status");
		String employeeName = ServletActionContext.getRequest().getParameter("employeeName");
		String loginName = ServletActionContext.getRequest().getParameter("loginName");
		String loginDate = ServletActionContext.getRequest().getParameter("loginDate");
		String sid = ServletActionContext.getRequest().getParameter("sid");
		String location = ServletActionContext.getRequest().getParameter("location");
		String department = ServletActionContext.getRequest().getParameter("department");
		String phone = ServletActionContext.getRequest().getParameter("phone");
		String phoneExt = ServletActionContext.getRequest().getParameter("phoneExt");
		String email = ServletActionContext.getRequest().getParameter("email");
		String address = ServletActionContext.getRequest().getParameter("address");
		if (loginDate == null) {
			loginDate = new Date().toString();
		}
		String effFrom = ServletActionContext.getRequest().getParameter("effFrom");
		String effTo = ServletActionContext.getRequest().getParameter("effTo");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Date hireDate = null;
		// try {
		// String s = Struts2Util.getParameter("hireDate");
		// if (s == null || s.trim().length()<1) {
		// hireDate = simpleDateFormat.parse(new Date().toString());
		// } else {
		// hireDate = simpleDateFormat.parse(s);
		// }
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		employee.setEmployeeId(id);
		employee.setEmployeeName(employeeName);
		employee.setHireDate(new Date());
		employee.setSid(sid);
		employee.setLocation(location);
		employee.setDepartment(department);
		employee.setPhone(phone);
		employee.setPhoneExt(phoneExt);
		employee.setEmail(email);
		employee.setAddress(address);
		user.setStatus(status);
		user.setCompanyId(1);
		user.setEffFrom(effFrom);
		user.setLoginName(loginName);
		user.setEffTo(effTo);
	}

	/**
	 * 转向privilege_acl.jsp页面
	 * 
	 * @return String
	 * @author zhangyang
	 */
	public String userRedirect_acl() throws Exception{
		userListPrivilegeAcl = this.privilegeService.getPvlgListByUserAndUI(userId);
		sign = 2;
		ServletActionContext.getRequest().setAttribute("sign", sign);
		return "userOrRole_privilege_acl";
	}

	/**
	 * 转向privilege_acl.jsp页面
	 * 
	 * @return String
	 * @author zhangyang
	 */
	public String roleRedirect_acl() {
		Integer roId = UserAction.roleId;
		roleListPrivilegeAcl = this.privilegeService.getPvlgListByRoleAndUI(roId);
		sign = 3;
		ServletActionContext.getRequest().setAttribute("sign", sign);
		return "userOrRole_privilege_acl";
	}

	public List<Integer> saveRoleId() {
		List<Integer> listRoleId = new ArrayList<Integer>();
		String roleIds = ServletActionContext.getRequest().getParameter("roleIds");
		String str = "";
		String tempStr = "";
		for (int i = 0; i < roleIds.length(); i++) {
			char c = roleIds.charAt(i);
			if (Character.isDigit(c)) {
				str = String.valueOf(c);
				tempStr = tempStr + str;
				continue;
			} else {
				if (tempStr != "") {
					listRoleId.add(Integer.parseInt(tempStr.trim()));
					str = "";
					tempStr = "";
				}

			}
		}

		if (tempStr != "")
			listRoleId.add(Integer.parseInt(tempStr.trim()));
		return listRoleId;
	}

	public List<Integer> savePrivilege() {
		List<Integer> listPrivilege = new ArrayList<Integer>();
		String privilegeIds = ServletActionContext.getRequest().getParameter("privilegeId");
		String str = "";
		String tempStr = "";
		for (int i = 0; i < privilegeIds.length(); i++) {
			char c = privilegeIds.charAt(i);
			if (Character.isDigit(c)) {
				str = String.valueOf(c);
				tempStr = tempStr + str;
				continue;
			} else {
				if (tempStr != "") {
					listPrivilege.add(Integer.parseInt(tempStr.trim()));
					str = "";
					tempStr = "";
				}

			}
		}
		if (tempStr != "")
			listPrivilege.add(Integer.parseInt(tempStr.trim()));
		return listPrivilege;

	}

	/**
	 * 判断字符串是否为纯数字
	 * @param s
	 * @return boolean type
	 * @author zhangyang
	 */
	public boolean stringOrNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 修改或新建Role保存到数据库
	 * @return String
	 * @author zhangyang
	 * 
	 */
	public String saveRole() {
		Integer userId = SessionUtil.getUserId();
		String roleName = ServletActionContext.getRequest().getParameter("roleName");
		String description = ServletActionContext.getRequest().getParameter("description");
		List<Integer> listPrivilegeId = saveRolePrivilege();
		if (ServletActionContext.getRequest().getParameter("roleId") == null) {
			role = new RoleDTO();
			role.setRoleName(roleName);
			role.setDescription(description);
			role.setModifiedBy(userId);
			role.setPvlgIdList(listPrivilegeId);
			this.privilegeService.saveOrUpdateRole(role);
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><result>1</result></root>";
			Struts2Util.renderXml(xml);
			return null;

		} else {
			role = new RoleDTO();
			role.setRoleId(UserAction.roleId);
			role.setRoleName(roleName);
			role.setDescription(description);
			role.setModifiedBy(userId);
			role.setPvlgIdList(listPrivilegeId);
			this.privilegeService.saveOrUpdateRole(role);
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><result>1</result></root>";
			Struts2Util.renderXml(xml);
			return null;
		}

	}
	/**
	 * 解析出此角色有哪些privilege
	 * @return privilege_list
	 * @author zhangyang
	 */
	public List<Integer> saveRolePrivilege() {
		List<Integer> listPrivilege = new ArrayList<Integer>();
		String privilegeIds = ServletActionContext.getRequest().getParameter("privilegeId");
		String str = "";
		String tempStr = "";
		//把字符串里的数字解析出来
		for (int i = 0; i < privilegeIds.length(); i++) {
			char c = privilegeIds.charAt(i);
			if (Character.isDigit(c)) {
				str = String.valueOf(c);
				tempStr = tempStr + str;
				continue;
			} else {
				if (tempStr != "") {
					listPrivilege.add(Integer.parseInt(tempStr.trim()));
					str = "";
					tempStr = "";
				}

			}
		}
		if (tempStr != "")
			listPrivilege.add(Integer.parseInt(tempStr.trim()));
		return listPrivilege;

	}
/**
 * 获得搜索拥有此角色的用户信息列表
 * @return user/search_user_list_byRole.jsp页面显示
 * @author zhangyang
 */
	public String getSearchUserList() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<UserRole> pagerUtil = new PagerUtil<UserRole>();
		pageUseRole = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (pageUseRole.getPageSize() == null
				|| pageUseRole.getPageSize().intValue() <= 1) {
			pageUseRole.setPageSize(5);
		}
		if (UserAction.searRoleId == 0) {
			UserAction.searRoleId = UserAction.roleId;
		}
		pageUserDTOList = this.privilegeService.getUserListByRole(pageUseRole,
				UserAction.searRoleId);
		UserAction.searRoleId = 0;
		Integer count=pageUserDTOList.getResult().size();
		ServletActionContext.getRequest().setAttribute("count",count);
		ServletActionContext.getRequest().setAttribute("pagerInfo",pageUserDTOList);
		return "searchUserListByRole";
	}

	public String searchUserList() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<User> pagerUtil = new PagerUtil<User>();
		pageuser = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (pageuser.getPageSize() == null
				|| pageuser.getPageSize().intValue() <= 1) {
			pageuser.setPageSize(15);
		}
		
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		pageuser = this.privilegeService.searchUserList(pageuser, filters);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(pageuser);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "searchUserList";
	}
	
	public UserSrchDTO getUserSrchDTO() {
		return userSrchDTO;
	}

	public RoleSrchDTO getRoleSrchDTO() {
		return roleSrchDTO;
	}

	public void setRoleSrchDTO(RoleSrchDTO roleSrchDTO) {
		this.roleSrchDTO = roleSrchDTO;
	}

	public Page<UserDTO> getUserDTO() {
		return pageUserDTO;
	}

	public Page<RoleDTO> getRoleDTO() {
		return pageRoleDTO;
	}

	public void setUserDTO(Page<UserDTO> userDTO) {
		this.pageUserDTO = userDTO;
	}

	public void setUserSrchDTO(UserSrchDTO userSrchDTO) {
		this.userSrchDTO = userSrchDTO;
	}

	public void setRoleDTO(Page<RoleDTO> roleDTO) {
		this.pageRoleDTO = roleDTO;
	}

	/*
	 * public String userDel() { if (this.checkbox != null) { for (int i = 0; i
	 * < this.checkbox.size(); i++) { // this.接口名.删除方法名(this.checkbox.get(i)); }
	 * } return "userDel";
	 * 
	 * }
	 */

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}

	public RoleDTO getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDTO roleDto) {
		this.roleDto = roleDto;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public Page<UserDTO> getPageUserDTOList() {
		return pageUserDTOList;
	}

	public void setPageUserDTOList(Page<UserDTO> pageUserDTOList) {
		this.pageUserDTOList = pageUserDTOList;
	}

	public List<RoleDTO> getListRole() {
		return listRole;
	}

	public void setListRole(List<RoleDTO> listRole) {
		this.listRole = listRole;
	}

	public Page<UserDTO> getPageUserDTO() {
		return pageUserDTO;
	}

	public void setPageUserDTO(Page<UserDTO> pageUserDTO) {
		this.pageUserDTO = pageUserDTO;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Page<RoleDTO> getPageRoleDTO() {
		return pageRoleDTO;
	}

	public void setPageRoleDTO(Page<RoleDTO> pageRoleDTO) {
		this.pageRoleDTO = pageRoleDTO;
	}

	public List<PrivilegeDTO> getUserListPrivilegeAcl() {
		return userListPrivilegeAcl;
	}

	public void setUserListPrivilegeAcl(List<PrivilegeDTO> userListPrivilegeAcl) {
		this.userListPrivilegeAcl = userListPrivilegeAcl;
	}

	public List<PrivilegeDTO> getRoleListPrivilegeAcl() {
		return roleListPrivilegeAcl;
	}

	public void setRoleListPrivilegeAcl(List<PrivilegeDTO> roleListPrivilegeAcl) {
		this.roleListPrivilegeAcl = roleListPrivilegeAcl;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	
	
	public String getChoiceVal() {
		return choiceVal;
	}

	public void setChoiceVal(String choiceVal) {
		this.choiceVal = choiceVal;
	}

	public Page<User> getPageuser() {
		return pageuser;
	}

	public void setPageuser(Page<User> pageuser) {
		this.pageuser = pageuser;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
