package com.genscript.gsscm.privilege.dto;

import java.util.List;

public class EmailCampaignDTO {
	private com.genscript.gsscm.privilege.dto.UserDTO userDTO;
	private Integer userId;
	private boolean isFlase;
	private List<PrivilegeDTO> treeList;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public boolean isFlase() {
		return isFlase;
	}
	public void setFlase(boolean isFlase) {
		this.isFlase = isFlase;
	}
	public List<PrivilegeDTO> getTreeList() {
		return treeList;
	}
	public void setTreeList(List<PrivilegeDTO> treeList) {
		this.treeList = treeList;
	}
	public com.genscript.gsscm.privilege.dto.UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(com.genscript.gsscm.privilege.dto.UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
}
