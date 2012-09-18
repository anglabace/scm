package com.genscript.gsscm.ws.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 调用WebService的的客户端.
 * 
 * 
 * @author golf
 */
public class WsClient {
	private static ClassPathXmlApplicationContext context = null;
	private static GSAWSWebService client = null;
	static
	{
		context = new ClassPathXmlApplicationContext(new String[]{"wsclient/wsclient-beans.xml"});
		client = (GSAWSWebService)context.getBean("client");
	}

	public MessageWSResult auth(String userId,String password)
	{
		MessageWSResult result = client.authUserPrimary(userId, password);
		return result;
	}

	public GetAllUserResult getAllUser()
	{
		GetAllUserResult result = client.getAllUser();
		return result;
	}

	public GetUserResult getUser(String id)
	{
		GetUserResult result = client.getUser(id);
		return result;
	}

	public WSResult createUser(com.genscript.gsscm.ws.client.UserDTO user)
	{
		WSResult result = client.createUser(user);
		return result;
	}

	public WSResult updateUser(com.genscript.gsscm.ws.client.UserDTO user)
	{
		WSResult result = client.updateUser(user);
		return result;
	}

	public WSResult deleteUser(String id)
	{
		WSResult result = client.deleteUser(id);
		return result;
	}

	public boolean changPassword(String userName,String oldPassword,String newPassword)
	{
		boolean result = client.changeUserPassword(userName, oldPassword, newPassword);
		return result;
	}
	
	public boolean resetPassword(String userName,String newPassword)
	{
		boolean result = client.resetPassword(userName, newPassword);
		return result;
	}
	
	public boolean sendMail(String to,String subject,String body)
	{
		boolean result = client.sendMail(to, subject, body);
		return result;
	}
}
