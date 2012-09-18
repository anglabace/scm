package com.genscript.gsscm.ws.client;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;



/**
 * JAX-WS2.0的WebService接口定义类.
 * 
 * 
 * 
 * @author golf
 */
@WebService(name = "GSAWSService", targetNamespace = WsConstants.AWS_NS)
public interface GSAWSWebService {

	/**
	 * 验证用户名密码.
	 */
	MessageWSResult authUserPrimary(@WebParam(name = "userId") String userID, @WebParam(name = "password") String password);

	/**
	 * 显示所有用户.
	 */
	GetAllUserResult getAllUser();

	/**
	 * 新建用户.
	 */
	WSResult createUser(@WebParam(name = "user") com.genscript.gsscm.ws.client.UserDTO user);

	/**
	 * 更新用户.
	 */
	WSResult updateUser(@WebParam(name = "user") com.genscript.gsscm.ws.client.UserDTO user);

	/**
	 * 根据Id查询用户.
	 */
	GetUserResult getUser(@WebParam(name = "userId")String id);

	WSResult deleteUser(@WebParam(name = "userId")String id);

	/**
	 * 修改用户密码.
	 */
	boolean changeUserPassword(@WebParam(name = "userId") String userId ,
			@WebParam(name="oldPassword")String oldPassword,@WebParam(name="newPassword")String newPassword);
	
	/**
	 * 重设用户密码.
	 */
	boolean resetPassword(@WebParam(name = "userId") String userId ,
			@WebParam(name="newPassword")String newPassword);
	
	/**
	 * 发送邮件.
	 */
	boolean sendMail(@WebParam(name = "to") String to ,@WebParam(name="subject")String subject, @WebParam(name="body")String body);
	
	/**
	 * 找回密码.
	 */
	WSResult forgetPasswd(@WebParam(name = "userId") String userId, @WebParam(name = "basePath") String basePath);
	
	/**
	 * 找回密码检查随机字符串.
	 */
	WSResult checkRandomStr(@WebParam(name = "userId") String userId, @WebParam(name = "randomStr") String randomStr);
	
	/**
	 * 找回重设密码.
	 */
	WSResult forgetResetPasswd(@WebParam(name = "userId") String userId, @WebParam(name = "randomStr") String randomStr, @WebParam(name = "newPassword") String newPassword);
	
	/**
	 * 修改密码.
	 */
	WSResult changePasswd(@WebParam(name = "userId") String userId, @WebParam(name="oldPassword")String oldPassword,@WebParam(name="newPassword")String newPassword);
}