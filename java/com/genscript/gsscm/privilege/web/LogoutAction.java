package com.genscript.gsscm.privilege.web;

import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Results({@Result(name="login", location="user/login.jsp")})
public class LogoutAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8745834056861158776L;

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(StrutsActionContant.USER);
		session.clear();
		return "login";
	}

}
