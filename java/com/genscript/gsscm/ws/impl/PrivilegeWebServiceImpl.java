package com.genscript.gsscm.ws.impl;

import java.util.List;

import javax.jws.WebService;

import org.dozer.DozerBeanMapper;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.dto.RoleDTO;
import com.genscript.gsscm.privilege.dto.RoleSrchDTO;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.dto.UserSrchDTO;
import com.genscript.gsscm.privilege.entity.LoginHistory;
import com.genscript.gsscm.privilege.entity.Role;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.entity.UserRole;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.PrivilegeWebService;
import com.genscript.gsscm.ws.request.PrivilegeRequest;
import com.genscript.gsscm.ws.response.PrivilegeResponse;

@WebService(serviceName = "PrivilegeService", portName = "PrivilegeServicePort", endpointInterface = "com.genscript.gsscm.ws.api.PrivilegeWebService", targetNamespace = WsConstants.NS)
public class PrivilegeWebServiceImpl implements PrivilegeWebService {
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private DozerBeanMapper dozer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.genscript.gsscm.ws.api.PrivilegeWebService#savePrivilege(com.genscript
	 * .gsscm.ws.request.PrivilegeRequest)
	 */
	@Override
	public PrivilegeResponse savePrivilege(PrivilegeRequest request) {
		Integer loginUserId = request.getUserId();
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			List<PrivilegeDTO> pvlgDTOList = request.getPrivilegeList();
			this.privilegeService.savePrivilege(pvlgDTOList, loginUserId);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF1002",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.genscript.gsscm.ws.api.PrivilegeWebService#saveRole(com.genscript
	 * .gsscm.ws.request.PrivilegeRequest)
	 */
	@Override
	public PrivilegeResponse saveRole(PrivilegeRequest request) {
		Integer loginUserId = request.getUserId();
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			RoleDTO roleDTO = request.getRoleDTO();
			roleDTO.setModifiedBy(loginUserId);
			this.privilegeService.saveOrUpdateRole(roleDTO);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF1010",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.genscript.gsscm.ws.api.PrivilegeWebService#saveUser(com.genscript
	 * .gsscm.ws.request.PrivilegeRequest)
	 */
	@Override
	public PrivilegeResponse saveUser(PrivilegeRequest request) {
		Integer loginUserId = request.getUserId();
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			UserDTO userDTO = request.getUserDTO();
			userDTO.setModifiedBy(loginUserId);
			this.privilegeService.saveOrUpdateUser(userDTO);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF1001",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getRole(PrivilegeRequest request) {
		Integer loginUserId = request.getUserId();
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			Integer roleId = request.getParamId();
			RoleDTO roleDTO = this.privilegeService.getRole(roleId);
			response.setRole(roleDTO);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF1008",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getUser(PrivilegeRequest request) {
		Integer loginUserId = request.getUserId();
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			Integer paramUserId = request.getParamId();
			UserDTO userDTO = this.privilegeService.getUser(paramUserId);
			response.setUser(userDTO);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF1012",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getTree(PrivilegeRequest request) {
		Integer loginUserId = request.getUserId();
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			List<PrivilegeDTO> list = this.privilegeService.getTreeAll();
			response.setPrivilegeList(list);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF1003",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PrivilegeResponse getUserListByRole(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			PageDTO pageDTO = privilegeRequest.getPagerParm();
			Integer roleId = privilegeRequest.getParamId();
			Page<UserRole> page = dozer.map(pageDTO, Page.class);
			Page<UserDTO> pageUserList = this.privilegeService
					.getUserListByRole(page, roleId);
			PageDTO pagerInfo = (PageDTO) dozer
					.map(pageUserList, PageDTO.class);
			response.setPagerDTO(pagerInfo);
			response.setUserList(pageUserList.getResult());
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getPvlgListByRole(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			Integer roleId = privilegeRequest.getParamId();
			List<PrivilegeDTO> pvlgList = this.privilegeService
					.getPvlgListByRole(roleId);
			response.setPrivilegeList(pvlgList);
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PrivilegeResponse searchUser(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			PageDTO pageDTO = privilegeRequest.getPagerParm();
			UserSrchDTO srch = privilegeRequest.getUserSrchDTO();
			Page<User> page = dozer.map(pageDTO, Page.class);
			page.setOrder(pageDTO.getOrder());
			page.setOrderBy(pageDTO.getOrderBy());
			Page<UserDTO> pageUserList = this.privilegeService.searchUser(page,
					srch);
			PageDTO pagerInfo = (PageDTO) dozer
					.map(pageUserList, PageDTO.class);
			response.setPagerDTO(pagerInfo);
			response.setUserList(pageUserList.getResult());
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PrivilegeResponse searchRole(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			PageDTO pageDTO = privilegeRequest.getPagerParm();
			RoleSrchDTO srch = privilegeRequest.getRoleSrchDTO();
			Page<Role> page = dozer.map(pageDTO, Page.class);
			Page<RoleDTO> pageRoleList = this.privilegeService.searchRole(page,
					srch);
			PageDTO pagerInfo = (PageDTO) dozer
					.map(pageRoleList, PageDTO.class);
			response.setPagerDTO(pagerInfo);
			response.setRoleList(pageRoleList.getResult());
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getPvlgListByUser(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			Integer userId = privilegeRequest.getParamId();
			List<PrivilegeDTO> pvlgList = this.privilegeService
					.getPvlgListByUser(userId);
			response.setPrivilegeList(pvlgList);
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getRoleListByUser(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			Integer userId = privilegeRequest.getParamId();
			List<RoleDTO> roleList = this.privilegeService
					.getRoleListByUser(userId);
			response.setRoleList(roleList);
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getMenuListForUser(
			PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			Integer parmUserId = privilegeRequest.getParamId();
			List<PrivilegeDTO> privilegeList = this.privilegeService
					.getMenuListForUser(parmUserId);
			response.setPrivilegeList(privilegeList);
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getUIListForUser(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			Integer parmUserId = privilegeRequest.getParamId();
			List<PrivilegeDTO> privilegeList = this.privilegeService
					.getUIListForUser(parmUserId);
			response.setPrivilegeList(privilegeList);
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@Override
	public PrivilegeResponse login(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			String loginName = privilegeRequest.getLoginName();
			String ipAddress = privilegeRequest.getIpAddress();
			UserDTO user = this.privilegeService
					.validUser(loginName, ipAddress);
			response.setUser(user);
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PrivilegeResponse getLoginList(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			PageDTO pageDTO = privilegeRequest.getPagerParm();
			Page<LoginHistory> page = dozer.map(pageDTO, Page.class);
			Integer userId = privilegeRequest.getParamId();
			Page<LoginHistory> pageLoginList = this.privilegeService
					.searchLogin(page, userId);
			PageDTO pagerInfo = (PageDTO) dozer.map(pageLoginList,
					PageDTO.class);
			response.setPagerDTO(pagerInfo);
			response.setLoginList(pageLoginList.getResult());
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@Override
	public PrivilegeResponse logout(PrivilegeRequest privilegeRequest) {
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			Integer loginUserId = privilegeRequest.getUserId();
			Integer loginId = privilegeRequest.getParamId();
			this.privilegeService.logout(loginUserId, loginId);
			response.setHasException(false);
		} catch (JDBCException ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getSQLException().getMessage());
			wsEX.setMessageDetail("The SQL: " + ex.getSQL()
					+ wsEX.getMessageDetail());
			response.setWsException(wsEX);
			response.setHasException(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException wsEX = new WSException(ex);
			wsEX.setMessage(ex.getMessage());
			response.setWsException(wsEX);
			response.setHasException(true);
		}
		return response;
	}

	@Override
	public PrivilegeResponse getParentPathList(PrivilegeRequest privilegeRequest) {
		Integer loginUserId = privilegeRequest.getUserId();
		PrivilegeResponse response = new PrivilegeResponse();
		try {
			String pvlgCode = privilegeRequest.getPvlgCode();
			List<PrivilegeDTO> privilegeList = this.privilegeService
					.getParentPath(pvlgCode);
			response.setPrivilegeList(privilegeList);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0110",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}
}
