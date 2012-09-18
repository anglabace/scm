<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<c:set var="method" value="${requestScope.method}" scope="request"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
</head>
<body class="content" style="background-image: none;">
<div class="input_box">
<div class="content_box">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
     <div style="margin-right:17px;height:340px;"  class="list_box">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
        <s:iterator value="orderFollowupDTO.columnName" id="colName">
            <th width="160px"><s:property value="colName"/></th>
         </s:iterator>
         </tr>
            <%
                ArrayList dataList = (ArrayList) request.getAttribute("reportData");
                int i = 0;
                if (dataList != null) {
                    for (Object data : dataList) {
                        i++;
            %>
            <tr>
                <%
                    ArrayList<String> columns = (ArrayList<String>) request.getAttribute("columns");
                    for (String col : columns) {
                        if ("".equals(col)) continue;
                        String coltd = BeanUtils.getProperty(data, col);
                %>
                <td class="<%=(i%2)==0 ? "list_td2" : ""%>" width="160px"><%=coltd == null ? "" : coltd%>
                </td>
                <%
                    }
                %>
            </tr>
            <%
                    }
                }
            %>
        </table>
	</div>
      </td>
  </tr>
</table>
</div>
</div>
</body>
</html>