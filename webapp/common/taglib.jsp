<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  prefix="saveButton" uri="/WEB-INF/MyTagLib/MySaveButtonTagLib"%> 
<%@page import="com.genscript.core.util.SpringContextUtils, com.genscript.gsscm.common.FileService" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:set var="global_url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<c:set var="file_upload_tmp" value="documents_tmp/"/>
<c:set var="file_upload_rela" value="<%= SpringContextUtils.getBean(FileService.class).getUploadRelaPath() %>"/>
<c:set var="global_css_url" value="${pageContext.request.contextPath}/stylesheet/"/>
<c:set var="global_js_url" value="${pageContext.request.contextPath}/js/"/>
<c:set var="global_image_url" value="${pageContext.request.contextPath}/images/"/>