<%-- 
    Document   : exception_list
    Created on : 2010-9-6, 13:49:05
    Author     : jinsite
--%>

<%@page contentType="text/html" pageEncoding="GBK"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_url}stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="${global_url}stylesheet/table.css" rel="stylesheet" type="text/css" />
<script>
function toggleShowMore(divID){
	var oId = document.getElementById(divID);	// no item suffix
	var oLink = document.getElementById(divID + 'Item');	//

	var divDisplay = oId.style.display;
	if (divDisplay == "none") {
		oId.style.display = "";
		oLink.style.background="url(${global_url}images/ad.gif) no-repeat left 2px";
		oLink.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		oLink.href = 'javascript:void(0)';
	} else {

		oId.style.display = "none";
		oLink.style.background="url(${global_url}images/ar.gif) no-repeat left 2px";
		oLink.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		//oLink.href = '#' + divID.substring(8, divID.length);
		oLink.href = 'javascript:void(0)';
	}
}
</script>
</head>

<body class="content" style="background-image:none;">
<div class="scm" style="height:340px; overflow:scroll;">
<div class="input_box">


<s:iterator  value="messageLogPage.result" status="list1" id="number">
    <div class="Exception_style" style="padding-top:0px;"><a href="javascript:void(0);" onclick="toggleShowMore('exce<s:property value="#list1.index"/>');" id="exce<s:property value="#list1.index"/>Item">
            <img src="${global_url}images/ar.gif" width="11" height="11" />&nbsp;</a><s:date format="yyyy-MM-dd HH:mm:ss" name="logDate"/>  [<s:property value="severity"/> #<s:property value="code"/>] [<s:property value="loggedName"/>] <s:property value="description"/>
   </div>
     <div id="exce<s:property value="#list1.index"/>" style="display:none;padding-left:40px;">
        at Interface: <s:property value="interfaceName"/><br />
        at path:<s:property value="fullPath"/> <br />
        at Function:<s:property value="functionName"/> <br />
        Details:<s:property value="detail"/> <br />
        Suggested Action:<s:property value="action"/>
        <div class="java_app"><s:property value="stackInfo"/></div>
    </div>
   
    </s:iterator>
</div>
</div>
<div class="grayr">
    <jsp:include page="/common/db_pager.jsp">
        <jsp:param value="${ctx}/exception/exception!list.action" name="moduleURL"/>
    </jsp:include>
</div>
</body>
</html>