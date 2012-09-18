<%@ page pageEncoding="UTF-8"%>
<%@page import="com.genscript.gsscm.common.constant.Constants"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String query = request.getQueryString();
    
	String regex = Constants.PAGERNO_PARMNAME + "="
			+ request.getParameter(Constants.PAGERNO_PARMNAME);// pageNo
	if (query != null
			&& query.indexOf(Constants.PAGERNO_PARMNAME) != -1) {
		Integer currentPage = Integer.parseInt(request
				.getParameter(Constants.PAGERNO_PARMNAME));
		query = query.replaceFirst(regex, "");
	}

	regex = Constants.PAGERSIZE_PARMNAME + "="
			+ request.getParameter(Constants.PAGERSIZE_PARMNAME);// this.pageSize
	if (query != null
			&& query.indexOf(Constants.PAGERSIZE_PARMNAME) != -1) {
		Integer pageSize = Integer.parseInt(request
				.getParameter(Constants.PAGERSIZE_PARMNAME));
		query = query.replaceFirst(regex, Constants.PAGERSIZE_PARMNAME
				+ "=" + pageSize);
	}
	if (query != null && query.length() > 0) {
		if (query.startsWith("&")) {
			query = query.substring(1);
		}
		if (query.endsWith("&")) {
			query = query.substring(0, query.length() - 1);
		}
	}
	String url = request.getParameter("moduleURL");
 
	if (query != null && !url.contains(query)) {
		url = url + "?" + query;
	}
	if (url.indexOf("?") != -1) {
		url = url + "&";
	} else {
		url = url + "?";
	}
 
	url = url + Constants.PAGERNO_PARMNAME;
	request.setAttribute("url", url);
%>
<font size="1">Total:&nbsp;${pagerInfo.totalCount}</font>
<c:if test="${pagerInfo.pageNo <= 4}">
	<c:if test="${pagerInfo.pageNo <= 1}">
		<span class="disabled">&lt;</span>
	</c:if>
	<c:if test="${pagerInfo.pageNo > 1}">
		<a href="${url}=${pagerInfo.pageNo-1}">&lt;</a>
	</c:if>

	<c:forEach begin="1" end="${pagerInfo.pageNo-1}" var="subcount">
		<a href="${url}=${subcount}">${subcount}</a>
	</c:forEach>
	<span class="current">${pagerInfo.pageNo}</span>
	<c:forEach begin="${pagerInfo.pageNo+1}"
		end="${pagerInfo.totalPage>9 ? 9 : pagerInfo.totalPage}"
		var="subcount">
		<a href="${url}=${subcount}">${subcount}</a>
	</c:forEach>
	<c:if test="${pagerInfo.pageNo >= pagerInfo.totalPage}">
		<span class="disabled">&gt;</span>
	</c:if>
	<c:if test="${pagerInfo.pageNo < pagerInfo.totalPage}">
		<a href="${url}=${pagerInfo.pageNo+1}">&gt;</a>
	</c:if>
</c:if>

<c:if test="${pagerInfo.pageNo > 4}">
	<a href="${url}=${pagerInfo.pageNo-1}">&lt;</a>
	<c:forEach begin="${pagerInfo.pageNo-4}"
		end="${pagerInfo.pageNo+4>pagerInfo.totalPage ? pagerInfo.totalPage : pagerInfo.pageNo+4}"
		var="subcount">
		<c:if test="${pagerInfo.pageNo == subcount}">
			<span class="current">${pagerInfo.pageNo}</span>
		</c:if>
		<c:if test="${pagerInfo.pageNo != subcount}">
			<a href="${url}=${subcount}">${subcount}</a>
		</c:if>
	</c:forEach>
	<c:if test="${pagerInfo.pageNo >= pagerInfo.totalPage}">
		<span class="disabled">&gt;</span>
	</c:if>
	<c:if test="${pagerInfo.pageNo < pagerInfo.totalPage}">
		<a href="${url}=${pagerInfo.pageNo+1}">&gt;</a>
	</c:if>
</c:if>
