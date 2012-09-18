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
<form action="customer!saveRedeemPoint.action" method="post">
   <table width="620" border="0" cellpadding="0" cellspacing="0" class="Customer_table">
   
     <tr>
    <td><div class="invoice_title">Amazon Gift Card</div></td>
    </tr>
    <tr>
    <td><table border="0" cellpadding="0" cellspacing="0" class="Customer_table">
      <tr>
        <th width="133">First Name</th>
        <td width="184" align="left">　${firstName}</td>
        </tr>
      <tr>
        <th>Last Name</th>
        <td align="left">　${lastName}</td>
        </tr>
      <tr>
        <th>Email</th>
        <td align="left">　${email}</td>
        </tr>
      <c:if test='${searchGiftCard != null && searchGiftCard != ""}'>
      <tr>
        <th>Amazon Gift Card</th>
        <td align="left">　$${searchGiftCard.cardValue}</td>
        </tr>
      </c:if>  
    </table></td>
    </tr>
     <tr>
    <td><div class="invoice_title">GenScript Coupons</div></td>
    </tr>
     <tr>
       <td height="30" style="padding-left:20px;"><table width="580" border="0" cellpadding="0" cellspacing="0" class="Customer_table">
         
         <tr>
           <td height="30">&nbsp;</td>
         </tr>
         <tr>
           <td height="30"><table width="580" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td width="712"><table width="563" border="0" cellspacing="0" cellpadding="0"  class="list_table">
                 <tr>
                   <th width="65">Exchange Num</th>
                   <th width="129">Coupon Code</th>
                   <th width="65">Coupon Value</th>
                   <th width="146">Expiration Date</th>
                   <th >Comments</th>
                   </tr>
               </table>
                 <div class="frame_box2" style="height:150px;width:580px;">
                   <table width="563" border="0" cellspacing="0" cellpadding="0" class="list_table">
                   <c:forEach items="${couponDTOlist}" var="couponDTO">
                     <tr>
                       <td width="65" align="center">${couponDTO.number}</td>
                       <td width="129" align="center">${couponDTO.code}</td>
                       <td width="65" align="center">${couponDTO.value}</td>
                       <td width="146" align="center">${couponDTO.exprDate}</td>
                       <td align="center">${couponDTO.comments}</td>
                       </tr>
                   </c:forEach>    
                   </table>
                 </div></td>
             </tr>
    
             <tr>
               <td colspan="8">&nbsp;</td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td height="100" align="center">
             <input type="submit" id="saveCardBtn" name="Submit2" value="Confirm" class="style_botton" />
             <input type="button" name="Submit3" value="Cancel" class="style_botton" onclick="javascript:parent.$('#show_Redeem_Point_Dialog').dialog('close');"  />
           </td>
         </tr>
       </table></td>
     </tr>
    </table>
</form>    
</body>
</html>
