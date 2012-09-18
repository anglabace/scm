<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ page import="com.genscript.gsscm.manufacture.dto.WorkOrderExcelDTO" %>
<%@ page contentType="application/msexcel; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<!-- 以上这行设定本网页为excel格式的网页 -->
<%
   String excelName = request.getAttribute("excelName")==null? "report" : request.getAttribute("excelName").toString();
   response.setHeader("Content-disposition","inline; filename="+excelName+".xls");
   response.setCharacterEncoding("UTF-8");
   response.setContentType("application/msexcel");
%>
<html>
    <head>
        <%@ include file="/common/taglib.jsp" %>
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
    <tr>
        <th>Order_ID</th>
        <th>Descrition</th>
        <th>Supply Confirm Date</th>
        <th>Target Date</th>
        <th>Delay</th>
        <%--<th></th>--%>
        <th>Manager</th>
        <th>Length</th>
        <th>Cost</th>
        <%--<th></th>--%>
        <%--<th></th>--%>
        <th>TAM</th>
    </tr>
    <%
        ArrayList workList = request.getAttribute("workList") == null ? new ArrayList() : (ArrayList)request.getAttribute("workList");
        for(Object workObj : workList){
            WorkOrderExcelDTO workDTO = (WorkOrderExcelDTO)workObj;
    %>
    <tr>
       <td width="160px"><%=workDTO.getOrderNo()%></td>
       <td width="160px"><%=workDTO.getItemDesc()%></td>
       <td width="160px"><%=workDTO.getConfirmDate()%></td>
       <td width="160px"><%=workDTO.getTargetDate()%></td>
       <td width="160px"></td>
        <%
            String workGroupName = workDTO.getWorkGroupName() == null? "" : workDTO.getWorkGroupName();
        %>
       <td width="160px"><%=workGroupName%></td>
       <td width="160px"><%=workDTO.getSquenceLength()%></td>
       <td width="160px"><%=workDTO.getCost()%></td>
       <%--<td width="160px"></td>
       <td width="160px"></td>--%>
       <td width="160px"><%=workDTO.getTam()%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>