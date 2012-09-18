<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ page import="com.genscript.gsscm.manufacture.entity.DsPlateItems" %>
<%@ page import="com.genscript.gsscm.order.entity.OrderDnaSequencing" %>
<%@ page contentType="application/msexcel; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<!-- 以上这行设定本网页为excel格式的网页 -->
<%
   String plateNo = request.getAttribute("plateNo")==null? "report" : request.getAttribute("plateNo").toString();
   response.setHeader("Content-disposition","inline; filename=plateMap_"+plateNo+".xls");
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
    <%	
        List<DsPlateItems> dsPlateItemsList = (List<DsPlateItems>)request.getAttribute("dsPlateItemsList");
        System.out.println("dsPlateItemsList================="+dsPlateItemsList.size());
        for(int i=1;i<=dsPlateItemsList.size();i++){       	
        	DsPlateItems dsPlateItems = dsPlateItemsList.get(i-1);
        	
    %>
	   <%if((i-1)%12==0) {
	   %> 	
	       	<tr>
	       <%} %>	
	       <td width="100">
	       	<%=dsPlateItems.getWorkOrderNo()== null? "" : dsPlateItems.getWorkOrderNo()%><br>
	       	<%=dsPlateItems.getOrderNo()== null? "" : dsPlateItems.getOrderNo()+"_"%><%=dsPlateItems.getItemNo()== null? "" : dsPlateItems.getItemNo()%><br>
			<%if(dsPlateItems.getOrderDnaSequencing()!=null){
				OrderDnaSequencing ods=dsPlateItems.getOrderDnaSequencing();
				%>
				<%= ods.getCode()%><br>
				<%= ods.getSampleName()%><br>
				<%= ods.getPrimerName()%><br>
			<%}else{ %>
				<br>
				<br>
				<br>
			<%} %>
			</td>
	    <%if(i%12==0) {
	    %>   		
	       	</tr>
	       <%} %>  
    <%
        }
    %>
</table>
</body>
</html>