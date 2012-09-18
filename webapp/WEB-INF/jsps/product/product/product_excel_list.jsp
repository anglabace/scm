<%@ page contentType="application/msexcel; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<!-- 以上这行设定本网页为excel格式的网页 -->
<%
   response.setHeader("Content-disposition","inline; filename=product.xls");
   response.setCharacterEncoding("UTF-8");
   response.setContentType("application/msexcel");
%>
<html>
<head>
<title>Excel档案呈现方式</title>
</head>
<body>
  <table width="976"  border="1">
  <tr>
    <th width="100">Catalog No</th>
    <th width="125">Type</th>
    <th width="150">Name</th>
    <th width="200">Description</th>
    <th width="70">Status</th>
    <th width="55">Size</th>
    <th width="100">Unit Price </th>
    <th width="100">Modify Date </th>
    <th>Creation Date</th>
    </tr>
      <s:iterator value="excelResult">
      <tr>
        <td width="70">
            	${catalogNo}
        </td>
        <td width="125">${type}&nbsp;</td>
        <td width="150">${name}&nbsp;</td>
        <td width="200">${description}&nbsp;</td>
        <td width="60">${status}&nbsp;</td>
        <td width="55">${size}${uom }&nbsp;</td>
        <td width="65"><div align="right">${symbol }${unitPrice}&nbsp;</div></td>
        <td width="80"><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" />&nbsp;</div></td>
        <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" />&nbsp;</div></td>
      </tr>
      </s:iterator>
    </table>
</body>
</html>

