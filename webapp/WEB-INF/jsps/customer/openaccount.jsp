<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");
	String ssocookie = "https://www.genscript.com/ssl-bin/myaccount";
	String cookie_name = request.getParameter("cookie_name");
	String custno = request.getParameter("custno");
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email"); 
	String newcookies = custno + "&&" + firstName + "&&" + lastName
			+ "&&" + email;
	response.setHeader(
			"P3P",
			"CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
	out.println(newcookies);
	Cookie _cookie = new Cookie(cookie_name, newcookies);
	_cookie.setMaxAge(30 * 60 * 100);
	_cookie.setPath("/");
	_cookie.setDomain("genscript.com");
	response.addCookie(_cookie);
	response.sendRedirect(ssocookie);
%>
