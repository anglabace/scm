<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div>
	 <table border="0" cellpadding="0" cellspacing="0" class="Customer_table">
	     <tr>
	    	<td width="740"><div class="invoice_title">My Coupons</div></td>
	     </tr>
	 </table>  
</div>
<div>	 
     <table width="720" border="0" cellspacing="0" cellpadding="0"  class="list_table">
       <tr>
         <th width="86">Redeem Date</th>
         <th width="113">Redeemed Points</th>
         <th width="89">Coupon Code</th>
         <th width="100" >Coupon Value</th>
         <th width="94" >Expiration Date</th>
         <th width="111" >Remaining Points</th>
         <th width="107" >Comments</th>
         <th >Status2</th>
       </tr>
       <c:if test="${!empty redeemPage.result}">
       <c:forEach items="${redeemPage.result}" var="redeemHistoryDTO">
         <tr>
           <td width="86" align="center">${fn:substring(redeemHistoryDTO.redeemDate, 0, 10)}</td>
           <td width="113" align="center">${redeemHistoryDTO.redeemedPoints}</td>
           <td width="89" align="center">${redeemHistoryDTO.couponCode}</td>
           <td width="100" align="center">$${redeemHistoryDTO.couponValue}</td>
           <td width="94" align="center">${fn:substring(redeemHistoryDTO.expirationDate, 0, 10)}</td>
           <td width="111" align="center">--</td>
           <td width="107" align="center">${redeemHistoryDTO.comments}</td>
           <td align="center">${redeemHistoryDTO.status}</td>
          </tr>
        </c:forEach>   
        </c:if>
    </table>  
</div>     
<div class="grayr">
	<jsp:include page="/common/db_pager.jsp">
	 <jsp:param value="${ctx}/customer/customer!showRedeemPointHistory.action" name="moduleURL"/>
	</jsp:include>	
</div>
<div align="left">
    <span class="important">*</span> For security reasons, the Amazon Gift Card codes are delivered to customers' account email addresses.</td>
</div>
<!-- <div align="center">
    <input type="submit" name="Submit3" value="Close" class="style_botton" onclick="javascript:parent.$('#show_Redeem_Point_History_Dialog').dialog('close');"  />      
</div> -->
</body>
</html>
