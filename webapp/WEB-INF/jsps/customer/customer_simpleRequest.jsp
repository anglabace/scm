<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglib.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>User list</title>
    <base href="${global_url}"/>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
          type="text/css"/>
    <link href="${global_css_url}tab-view.css" rel="stylesheet"
          type="text/css"/>
    <script language="javascript" type="text/javascript"
            src="${global_js_url}ajax.js"></script>
    <script language="javascript" type="text/javascript"
            src="${global_js_url}tab-view.js"></script>
    <script language="javascript" type="text/javascript"
            src="${global_js_url}TabbedPanels.js"></script>
</head>
<body>
<div style="height:265px; width:740px; overflow:scroll">
    <table width="765" border="0"
           style="TABLE-LAYOUT: fixed; word-break: break-all" cellpadding="0"
           cellspacing="0" class="list_table">
        <c:set var="rowcount" value="${rowId}"></c:set>
        <s:iterator value="sampleRequestDTOList.result" status="st">
            <c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
            </c:if>
            <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
            </c:if>
            <tr>
                <td width="65">${seqNo}</td>
                <td width="409"><a href="${url}" target="product">${sampleName}</a></td>
                <td width="81">${requestDate}</td>
                <td width="83">${deliveryDate}</td>
                <td>${note}</td>
            </tr>
            <c:set var="rowcount" value="${rowcount+1}"></c:set>
        </s:iterator>

    </table>
</div>

<div style="margin-left: 300px;" id="simpleRequest_view_indicator"></div>
<div class="grayr" id="simpleRequest_view_pane_pager">
    <jsp:include page="/common/db_pager.jsp">
        <jsp:param value="${ctx}/customer_web_behavior!getSampleRequestViewList.action" name="moduleURL"/>
    </jsp:include>
</div>
</body>
</html>