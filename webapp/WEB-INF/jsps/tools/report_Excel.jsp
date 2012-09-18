<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ page contentType="application/msexcel; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<%
   response.setHeader("Content-disposition","inline; filename=excel.xls");
   response.setCharacterEncoding("UTF-8");
   response.setContentType("application/msexcel");
%>
<html>
    <head> 
        <title>Reports Excel</title>
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="cache-control" content="no-cache"/>
        <meta http-equiv="expires" content="0"/>
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
        <meta http-equiv="description" content="This is my page"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <script type="text/javascript">var baseUrl="${global_url}";</script>
    </head>
<body>
<table width="100%" border="1">
    <s:iterator value="orderFollowupDTO.columnName" id="colName">
        <th width="160px"><s:property value="colName"/></th>
    </s:iterator>
    <%
        ArrayList dataList = (ArrayList) request.getAttribute("reportData");
        int i = 0;
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
        <td width="160px"><%=coltd == null ? "" : coltd%>
        </td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>