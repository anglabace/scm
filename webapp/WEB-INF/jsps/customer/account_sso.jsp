<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%  
String domainId=request.getParameter("id");   
String cookie_name = request.getParameter("cookie_name");
String custno = request.getParameter("custno");
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String email = request.getParameter("email");
String newcookies=custno+"&&"+firstName+"&&"+lastName+"&&"+email;
//HttpServletResponse response = new HttpServletResponse();  
response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");   
out.println(newcookies); 
Cookie _cookie=new Cookie(cookie_name,newcookies);  
_cookie.setMaxAge(30*60*100);    
_cookie.setPath("/");   
response.addCookie(_cookie); 
/* response.sendRedirect("https://www.genscript.com/ssl-bin/myaccount"); */
response.sendRedirect(domainId);
%>
