package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.dto.RoleDTO;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.entity.LoginHistory;

@XmlType(name = "PrivilegeResponse", namespace = WsConstants.NS)
public class PrivilegeResponse extends WsResponse {

	private PageDTO pagerDTO;
	private UserDTO user;
	private RoleDTO role;
	private List<PrivilegeDTO> privilegeList;
	private List<UserDTO> userList;
	private List<RoleDTO> roleList;
	private List<LoginHistory> loginList;
	/**
	 * @return the privilegeList
	 */
	public List<PrivilegeDTO> getPrivilegeList() {
		return privilegeList;
	}
	/**
	 * @param privilegeList the privilegeList to set
	 */
	public void setPrivilegeList(List<PrivilegeDTO> privilegeList) {
		this.privilegeList = privilegeList;
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}
	/**
	 * @return the role
	 */
	public RoleDTO getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(RoleDTO role) {
		this.role = role;
	}

	/**
	 * @return the userList
	 */
	public List<UserDTO> getUserList() {
		return userList;
	}
	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}
	/**
	 * @return the pagerDTO
	 */
	public PageDTO getPagerDTO() {
		return pagerDTO;
	}
	/**
	 * @param pagerDTO the pagerDTO to set
	 */
	public void setPagerDTO(PageDTO pagerDTO) {
		this.pagerDTO = pagerDTO;
	}
	/**
	 * @return the loginList
	 */
	public List<LoginHistory> getLoginList() {
		return loginList;
	}
	/**
	 * @param loginList the loginList to set
	 */
	public void setLoginList(List<LoginHistory> loginList) {
		this.loginList = loginList;
	}
	/**
	 * @return the roleList
	 */
	public List<RoleDTO> getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<RoleDTO> roleList) {
		this.roleList = roleList;
	}

	
	
}
