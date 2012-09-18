package com.genscript.gsscm.ws.request;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.dto.RoleDTO;
import com.genscript.gsscm.privilege.dto.RoleSrchDTO;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.dto.UserSrchDTO;

@XmlType(name = "PrivilegeRequest", namespace = WsConstants.NS)
public class PrivilegeRequest extends WsRequest {

	private PageDTO pagerParm;
	private UserDTO userDTO;
	private RoleDTO roleDTO;
	private PrivilegeDTO privilegeDTO;
	private List<PrivilegeDTO> privilegeList;
	private String parentCode;
	private Integer paramId;
	private UserSrchDTO userSrchDTO;
	private RoleSrchDTO roleSrchDTO;
    private String loginName;
    private String ipAddress;
    private String pvlgCode;
	/**
	 * @return the pagerParm
	 */
	public PageDTO getPagerParm() {
		return pagerParm;
	}

	/**
	 * @param pagerParm
	 *            the pagerParm to set
	 */
	public void setPagerParm(PageDTO pagerParm) {
		this.pagerParm = pagerParm;
	}

	/**
	 * @return the userDTO
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}

	/**
	 * @param userDTO
	 *            the userDTO to set
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	/**
	 * @return the roleDTO
	 */
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	/**
	 * @param roleDTO
	 *            the roleDTO to set
	 */
	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	/**
	 * @return the privilegeDTO
	 */
	public PrivilegeDTO getPrivilegeDTO() {
		return privilegeDTO;
	}

	/**
	 * @param privilegeDTO
	 *            the privilegeDTO to set
	 */
	public void setPrivilegeDTO(PrivilegeDTO privilegeDTO) {
		this.privilegeDTO = privilegeDTO;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode
	 *            the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the paramId
	 */
	public Integer getParamId() {
		return paramId;
	}

	/**
	 * @param paramId
	 *            the paramId to set
	 */
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	/**
	 * @return the privilegeList
	 */
	public List<PrivilegeDTO> getPrivilegeList() {
		return privilegeList;
	}

	/**
	 * @param privilegeList
	 *            the privilegeList to set
	 */
	public void setPrivilegeList(List<PrivilegeDTO> privilegeList) {
		this.privilegeList = privilegeList;
	}

	/**
	 * @return the userSrchDTO
	 */
	public UserSrchDTO getUserSrchDTO() {
		return userSrchDTO;
	}

	/**
	 * @param userSrchDTO
	 *            the userSrchDTO to set
	 */
	public void setUserSrchDTO(UserSrchDTO userSrchDTO) {
		this.userSrchDTO = userSrchDTO;
	}

	/**
	 * @return the roleSrchDTO
	 */
	public RoleSrchDTO getRoleSrchDTO() {
		return roleSrchDTO;
	}

	/**
	 * @param roleSrchDTO
	 *            the roleSrchDTO to set
	 */
	public void setRoleSrchDTO(RoleSrchDTO roleSrchDTO) {
		this.roleSrchDTO = roleSrchDTO;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the pvlgCode
	 */
	public String getPvlgCode() {
		return pvlgCode;
	}

	/**
	 * @param pvlgCode the pvlgCode to set
	 */
	public void setPvlgCode(String pvlgCode) {
		this.pvlgCode = pvlgCode;
	}

}
