<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:redirect url="${ip}">
<c:param name="userId" value="${userId}"></c:param>
<c:param name="sessionUserId" value="${sessionUserId}"></c:param>
</c:redirect>