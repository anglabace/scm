package com.genscript.gsscm.common.interceptor;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.UserAware;
import com.genscript.gsscm.common.constant.NoAuPrivilegeType;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {
	private static final long serialVersionUID = 3845667709185365513L;
	@SuppressWarnings("unused")
	@Autowired
	private PrivilegeService privilegeService;
	
	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		String name = actionInvocation.getInvocationContext().getName();
		Map<String, Object> session = actionInvocation.getInvocationContext()
				.getSession();
		String userId = String
				.valueOf(session.get(StrutsActionContant.USER_ID));
		
		/*List<PrivilegeDTO> privilegeUIDTOList = (List<PrivilegeDTO>) ActionContext
				.getContext().getSession().get(StrutsActionContant.PRIVILEGE);*/
		ActionContext ctx = actionInvocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		String scm = request.getContextPath();
		String path = request.getRequestURI().replaceAll(scm + "/", "");
		String scmPath = request.getQueryString();
		if(scmPath==null){
			scmPath = "";
		}else{
			scmPath = "?"+scmPath;
		}
		System.out.println("path:  " + path + scmPath);
		String url = path + scmPath;
		
		/*MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean(); 
		MemoryUsage usage = memorymbean.getHeapMemoryUsage(); 
		System.out.println("INIT HEAP: " + usage.getInit()); 
		System.out.println("MAX HEAP: " + usage.getMax()); 
		System.out.println("USE HEAP: " + usage.getUsed()); 
		System.out.println("\nFull Information:"); 
		System.out.println("Heap Memory Usage: " 
		+ memorymbean.getHeapMemoryUsage()); 
		System.out.println("Non-Heap Memory Usage: " 
		+ memorymbean.getNonHeapMemoryUsage()); */
		
		

		if (name.equalsIgnoreCase("login")) {
			if (!userId.equals("null")) {
				return "home";
			}
			return actionInvocation.invoke();
		} else {
			if (userId.equals("null") || userId.equals("")) {
				return Action.LOGIN;
			} else {
				Action action = (Action) actionInvocation.getAction();
				if (action instanceof UserAware) {
					((UserAware) action).setUserId(userId);
				}
			}
			//请求URL中的action名
			String userName = SessionUtil.getUserName();
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			if (userName.equals("admin") || NoAuPrivilegeType.booleanValue(path)
					|| name.equals("login")) {
				if (scmPath.contains("operation_method=edit")) {
					boolean isEdit = checkEditNum(url,userName);
					if (isEdit) {
						actionInvocation.invoke();
					}
				} else if (scmPath.contains("out_lock=true") 
						|| (path.equals(NoAuPrivilegeType.privilege.value()) 
								&& scmPath.indexOf("privilegeCode=") != -1)) {
					realeseOrderLock.releaseOrderLock();
				}
				return actionInvocation.invoke();
			} else if (scmPath.contains("operation_method=edit")) {
				boolean isEdit = checkEditNum(url,userName);
				if (isEdit) {
					actionInvocation.invoke();
				}
			} else if (scmPath.contains("out_lock=true") 
					|| (path.equals(NoAuPrivilegeType.privilege.value()) 
							&& scmPath.indexOf("privilegeCode=") != -1)) {
				realeseOrderLock.releaseOrderLock();
			}
			return actionInvocation.invoke();
			/*for (PrivilegeDTO privilege : privilegeUIDTOList) {
				String[] paths = path.split("/");
				Integer length = paths.length;
				path = paths[length-1];
				if(privilege.getPrivilegeAction()!=null){
					String[] privileges = privilege.getPrivilegeAction().split("/");
					String[] pathAction = path.split("!");
					if(pathAction.length<2){
						pathAction = path.split(".action");
						System.out.println(pathAction[0]+"!list.action");
						if((pathAction[0]+"!list.action").equals(privileges[privileges.length-1])
								||(pathAction[0]+"!execute.action").equals(privileges[privileges.length-1])){
							return actionInvocation.invoke();
						}
					}else{
						if (path.equals(privileges[privileges.length-1])) {
							return actionInvocation.invoke();
						}
					}
				}
			}*/
			//return "noAuprivilegeType";
		}
	}
	
	/**
	 * 检测同一单号是否被其他人正在编辑
	 * @param url
	 * @param userName
	 */
	@SuppressWarnings("unchecked")
	public boolean checkEditNum (String url, String userName) {
		boolean isEdit = false;
		if (url.contains("&")) {
			url = url.substring(0, url.indexOf("&"));
		}
		String loginUserName = "login_"+userName;
		ServletContext application = SessionListener.getApplication();
		Map sessions = SessionListener.getSessions();
		if (application != null) {
			//application中含有该单号
			if (application.getAttribute(url) != null) {
				//单号正被其他人操作
				if (!(loginUserName).equals(application.getAttribute(url).toString())) {
					isEdit = true;
				}
			//application中没有该单号
			} else {
				//去掉application中当前用户之前操作的单号
				if (null != sessions.get(loginUserName) 
						&& !("").equals(sessions.get(loginUserName).toString())) {
					application.removeAttribute(sessions.get(loginUserName).toString());
				}
				//更新application中添加当前用户新操作的单号
				application.setAttribute(url, loginUserName);
				//更新session监听器
				sessions.put(loginUserName, url);
			}
		}
		return isEdit;
	}
}
