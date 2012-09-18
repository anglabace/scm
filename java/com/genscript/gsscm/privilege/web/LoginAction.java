package com.genscript.gsscm.privilege.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.ws.client.GetUserResult;
import com.genscript.gsscm.ws.client.MessageWSResult;
import com.genscript.gsscm.ws.client.WsClient;
import com.opensymphony.xwork2.ActionContext;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.Service;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.ServiceSoap;

@Results({
		@Result(name = LoginAction.RELOAD, location = "login.action", type = "redirect"),
		@Result(name = "home", location = "user/home.jsp"),
		@Result(name = "input", location = "user/login.jsp"),
		@Result(name = "forget_password", location = "user/forget_password.jsp"),
		@Result(name = "forgetPassword_info", location = "user/forgetPassword_info.jsp"),
		@Result(name = "enter_newPassword", location = "user/enter_newPassword.jsp"),
		@Result(name = "forgetPassword_info", location = "user/forgetPassword_info.jsp"),
		@Result(name = "error", location = "user/login_error.jsp") })
public class LoginAction extends BaseAction<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6105775764893388959L;

	@Autowired
	private ArInvoicePaymentDao arInvoicePaymentDao;
	@Autowired
	private PrivilegeService privilegeService;
	private String userId;
	private String password;
	private String remeberId;
	private String randomStr;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerDao customerDao;
	/**
	 * 用于对应cookie中的userId
	 */
	private String cookieUserId;

	private MessageWSResult result = new MessageWSResult();

	@Override
	public String list() throws Exception {
		if (remeberId != null) {
			Cookie cookieId = new Cookie("userId", userId);
			cookieId.setMaxAge(30 * 60 * 60 * 24);
			ServletActionContext.getResponse().addCookie(cookieId);
			this.setCookieUserId(userId);
		} else {
			Cookie cookies[] = ServletActionContext.getRequest().getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie coo : cookies) {
					if ("userId".equalsIgnoreCase(coo.getName())) {
						this.setCookieUserId(coo.getValue());
					}
				}
			}
		}

		WsClient client = new WsClient();
		System.out.println("userId: " + userId);
		System.out.println("password: " + password);
		if (userId != null) {
			result = client.auth(userId, password);

			System.out.println(result.getWsException().getCode() + "====");
			if (result.getWsException().getCode().equals("0")) {
				// ActionContext.getContext().getSession().put(StrutsActionContant.USER
				// , value)
				if (result.getWsException().getDay() > 5
						|| result.getWsException().getDay() == 0) {
					String ipAddress = ServletActionContext.getRequest()
							.getRemoteAddr();
					UserDTO user = privilegeService
							.validUser(userId, ipAddress);
					if (user == null) {
						return ERROR;
					} else {
						List<PrivilegeDTO> privilegeUIDTOList = privilegeService
								.getUIListForUser(user.getUserId());
						List<PrivilegeDTO> privilegeMenuDTOList = privilegeService
								.getMenuListForUser(user.getUserId());
						if (privilegeUIDTOList == null
								|| privilegeMenuDTOList == null) {
							return ERROR;
						} else {
							ActionContext
									.getContext()
									.getSession()
									.put(StrutsActionContant.USER_ID,
											user.getUserId());
							ActionContext
									.getContext()
									.getSession()
									.put(StrutsActionContant.UID,
											user.getUserId());
							ActionContext
									.getContext()
									.getSession()
									.put(StrutsActionContant.LOGIN_ID,
											user.getLastLoginId());
							ActionContext
									.getContext()
									.getSession()
									.put(StrutsActionContant.USER_NAME,
											user.getLoginName());
							ActionContext
									.getContext()
									.getSession()
									.put(StrutsActionContant.PRIVILEGE,
											privilegeUIDTOList);
							return "home";
						}
					}
				} else {
					return ERROR;
				}
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	

	/*
	 * public String changePassword() throws Exception { if(userId != null ||
	 * oldPassword != null || newPassword != null){ WsClient client = new
	 * WsClient(); boolean changeResult = client.changPassword(userId,
	 * oldPassword, newPassword); if (changeResult) { return
	 * CHANGE_PASSWORD_SUCCESS; }else{ return CHANGE_PASSWORD_FAIL; } }else{
	 * return INPUT; } }
	 */

	public String forgetPassword() {
		return "forget_password";
	}

	public String judge() throws Exception {
		String body;
		String to;
		String subject;
		if (userId != null) {
			WsClient client = new WsClient();
			HttpServletRequest req = ServletActionContext.getRequest();
			if (randomStr == null) {
				UUID uuid = UUID.randomUUID();
				StringBuilder randomSb = new StringBuilder();
				randomSb.append(uuid.toString()).append("-")
						.append(new Date().getTime());
				randomStr = randomSb.toString();

				GetUserResult userResult = client.getUser(userId);
				System.out.println(userResult);
				if (userResult.getUser() != null) {
					com.genscript.gsscm.ws.client.UserDTO userDTO = userResult
							.getUser();
					User user = privilegeService.findByLoginName(userId);
					String email = null;
					if (user != null && user.getEmployee() != null) {
						Employee employee = this.privilegeService
								.findEmployeeById(user.getEmployee()
										.getEmployeeId());
						if (employee != null) {
							email = employee.getEmail();
						}
					}
					userDTO.setComment(randomStr);
					client.updateUser(userDTO);
					if (email != null) {
						String path = req.getContextPath();
						String basePath = req.getScheme() + "://"
								+ req.getServerName() + ":"
								+ req.getServerPort() + path + "/";
						to = email;
						subject = "GenScript Password Assistance";
						String address = basePath
								+ "login!createNewPassword.action?userId="
								+ userId + "&randomStr=" + randomStr;
						// String href = "<a href=\""
						// + address
						// +
						// "\" target=\"_blank\">Please click me to reset password!</a>";
						String href = address;
						body = convertTOBody(href);
						client.sendMail(to, subject, body);
						req.setAttribute("message", "Please check you email.");
						req.setAttribute("code", "101");
					} else {
						req.setAttribute("message",
								"The notify mail sended failed.");
						req.setAttribute("code", "102");
					}
				} else {
					req.setAttribute("message", "The user ID " + userId
							+ " which you entered is not in our system.");
					req.setAttribute("code", "103");
				}
			}
			return "forgetPassword_info";
		} else {
			return INPUT;
		}
	}

	private static String convertTOBody(String address) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"<html>Dear Customer: <br><br>This email was sent to you in response to your request to create a new password.<br>Please click the link below to create a  new password. If youdid not request the change, please ignore this Email.<br><br><a href=\"")
				.append(address)
				.append("\" target='_blank'>Create New Password</a><br><br>Please note this link has a life span of 3 days only.<br>If you can't open the link above, please copy and paste the following web address into your browser and press 'Enter':<br><br>")
				.append(address)
				.append("<br><br>Thanks for using GenScript services.<br><br>GenScript Customer Service<br><a href='http://www.genscript.com'>http://www.genscript.com</a><br><br>==========================================<br>GenScript --- The Biology CRO.<br>");
		return sb.toString();
	}

	public String createNewPassword() {
		return "enter_newPassword";
	}

	public String resetPassword() throws Exception {
		if (userId != null || randomStr != null) {
			WsClient client = new WsClient();
			GetUserResult userResult = client.getUser(userId);
			com.genscript.gsscm.ws.client.UserDTO userDTO = userResult
					.getUser();
			HttpServletRequest req = ServletActionContext.getRequest();
			if (userResult.getUser() != null) {
				boolean result = client.resetPassword(userId, password);
				if (result) {
					req.setAttribute("message",
							"Your password is already changed.");
					req.setAttribute("code", "104");
					userDTO.setComment(null);
					client.updateUser(userDTO);
				} else {
					req.setAttribute("message", "Reset password error.");
					req.setAttribute("code", "105");
				}
			} else {
				req.setAttribute("message", "The user ID " + userId
						+ " which you entered is not in our system.");
				req.setAttribute("code", "103");
			}
			return "forgetPassword_info";
		}
		return INPUT;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemeberId() {
		return remeberId;
	}

	public void setRemeberId(String remeberId) {
		this.remeberId = remeberId;
	}

	public MessageWSResult getResult() {
		return result;
	}

	public void setResult(MessageWSResult result) {
		this.result = result;
	}

	public void setCookieUserId(String cookieUserId) {
		this.cookieUserId = cookieUserId;
	}

	public String getCookieUserId() {
		return cookieUserId;
	}

}
