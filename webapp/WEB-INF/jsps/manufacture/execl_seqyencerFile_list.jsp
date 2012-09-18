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
   response.setHeader("Content-disposition","inline; filename=seqyencerFile_"+plateNo+".xls");
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
		<th width="100">wordOrderNo</th>
		<th width="100">orderNo</th>
		<th width="100">itemNo</th>
		<th width="100">SampleName</th>
		<th width="300">abl</th>
		<th width="300">seq</th>
		
	</tr>
    <%	
        List<DsPlateItems> dsPlateItemsList = (List<DsPlateItems>)request.getAttribute("dsPlateItemsList");
        for(int i=1;i<=dsPlateItemsList.size();i++){       	
        	DsPlateItems dsPlateItems = dsPlateItemsList.get(i-1);
        	Object workOrderNo=dsPlateItems.getWorkOrderNo()== null? "" : dsPlateItems.getWorkOrderNo();
        	Object orderNo=dsPlateItems.getOrderNo()== null? "" : dsPlateItems.getOrderNo();
        	Object itemNo=dsPlateItems.getItemNo()== null? "" : dsPlateItems.getItemNo();
        	Object position=dsPlateItems.getPlatePosition()== null? "" : dsPlateItems.getPlatePosition();
        	Object sampleName="";
        	if(dsPlateItems.getOrderDnaSequencing()!=null){
				OrderDnaSequencing ods=dsPlateItems.getOrderDnaSequencing();
				sampleName=ods.getSampleName()== null? "" : ods.getSampleName();;
        	}	
    %>	  
	     <tr>
	       <td width="100">
	       	<%=workOrderNo%>
	       </td>
	      <td width="100">
	      	<%=orderNo%>
	      </td>
	      <td width="100">
	      	<%=itemNo %>
	      </td>
	      <td width="100">
	      	<%=sampleName %>
	      </td>
		  <td width="300">
		  <%if(workOrderNo.equals("") && orderNo.equals("") && itemNo.equals("") && sampleName.equals("")){ %>
		  			
		  	<%}else{ %>
		  	<%=position+"_"+orderNo+"-"+itemNo+"_"+sampleName+".abl" %>
		  	<%} %>
		  </td>
		  <td width="300">
		  	<%if(workOrderNo.equals("") && orderNo.equals("") && itemNo.equals("") && sampleName.equals("")){ %>
		  			
		  	<%}else{ %>
		  	<%=position+"_"+orderNo+"-"+itemNo+"_"+sampleName+".seq" %>
		  	<%} %>
		  </td>
    <%
        }
    %>
</table>
</body>
</html>