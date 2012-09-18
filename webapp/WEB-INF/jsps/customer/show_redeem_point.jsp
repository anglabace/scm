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
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
   
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}select.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>

<script type="text/javascript" src="${global_js_url}scm/config.js"></script>
<script type="text/javascript" src="${global_js_url}util/util.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js" language="javascript" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" language="javascript" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){

	// validate signup form on keyup and submit
	$("#redeem_Card_Form").validate({ 
		invalidHandler: function(form, validator) { 
			$.each(validator.invalid, function(key,value){ 
				alert(value); 
				$("#"+key).focus(); 
				return false; 
			}); 
		}, 
		rules: { 
			firstName: { required:true, maxlength:25}, 
			lastName: { required:true, maxlength:25},
			email: { required:true, maxlength:50, email:true}
		}, 
		messages: { 
			firstName: "Please enter First Name, and the First Name should be less than 25 digits", 
			lastName: "Please enter Last Name, and the Last Name should be less than 25 digits",
			email: "Please enter Email, and the Email should be compliance with the rules"
		}, 
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
		$(element).addClass(errorClass);
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass(errorClass);
		},
		errorPlacement: function(error, element) {

		}
	});	
	
	$("#nextRedeemPoint").click(function () {
		//var giftCardId=""; 
		//$("[name='giftCardId'][checked]").each(function(){ 
			//giftCardId+=$(this).val()+"\r\n"; 
		//});
		//if (giftCardId != "") {
			if($("#redeem_Card_Form").valid() == false) {
				return false;
			}
		//}
		var list = "${redeemCouponList}";
		var coupons = list.split("]");
		var couponIdValues = "";
		for (var i in coupons) {
			if (coupons[i] != "") {
				var idstr = "";
				if (i==0) {
					idstr = ((coupons[i]).split(","))[0];
				} else {
					idstr = ((coupons[i]).split(","))[1];
				}
				if (idstr.indexOf("id=") > 0) {
					var id = idstr.substring(idstr.indexOf("id=")+3,idstr.length);
					var value = $("#couponNum"+id).val();
					couponIdValues += id + "-" + value + ",";
				}
			}
		}
		$("#couponIdValues").val(couponIdValues);
		$("#redeem_Card_Form").action = "customer!nextRedeemPoint.action";
		$("#redeem_Card_Form").submit();
	});
	
});

</script>
</head>
<body>
<c:if test="${message != null}"><script type="text/javascript">alert("${message}");</script></c:if>
<form action="customer!nextRedeemPoint.action" method="post" id="redeem_Card_Form" name="redeem_Card_Form">
<c:if test="${custNo != null && custNo != ''}">
<input type="hidden" name="custNo" value="${custNo}"/>
<input type="hidden" name="couponIdValues" id="couponIdValues"/>
<input type="hidden" name="redeemGiftCard" value="${redeemGiftCard}"/>
<table width="620" border="0" cellpadding="0" cellspacing="0" class="Customer_table">
	<tr><td>
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="Customer_table">
	     <tr>
	       <th align="left"><span class="important">*</span>First Name</th>
	       <td colspan="2" align="left"><input name="firstName" id="firstName" type="text" class="NFText" value="${firstName}" size="30" /></td>
	       <th width="80" align="left"><span class="important">*</span>Last Name</th>
	       <td width="164" align="left"><input name="lastName" id="lastName" type="text" class="NFText" value="${lastName}" size="30" /></td>
	     </tr>
	     <tr>
	       <th><span class="important">*</span>Email</th>
	       <td colspan="2" align="left"><input name="email" id="email" type="text" class="NFText" value="${email}" size="30" /></td>
	       <td align="left">&nbsp;</td>
	       <td align="left">&nbsp;</td>
	     </tr>
	    </table></td>
    </tr>
    <tr>
    	<td><div class="invoice_title">Reward Selection</div></td>
    </tr>
     <tr>
       <td height="30" style="padding-left:20px;"><table width="580" border="0" cellpadding="0" cellspacing="0" class="Customer_table">
         
         <tr>
           <td height="30"><span class="important">Please make your choice among following Genscipt Coupons and Amazon Gift Cards.</span></td>
         </tr>
         <tr>
           <td height="30"><span class="important">*</span> Amazon Gift Card offers are available to USA and Canada Customers only.</td>
         </tr>
         <tr>
           <td height="30"><div  class="general_box" style="width:522px;height:70px;padding:8px; ">
           	<c:if test='${redeemGiftCard != null && redeemGiftCard != ""}'>
             <table width="100%" border="0" cellpadding="0" cellspacing="0" class="Customer_table">
               <tr>
                 <td width="105" rowspan="3" align="left"><img src="${global_url}images/card5.jpg" width="99" height="62" /></td>
                 <td width="87" align="left"><c:if test="${redeemGiftCard.cardValue != null}">${(redeemGiftCard.cardValue) * 200} Points</c:if></td>
                 <td width="80" rowspan="3" align="left">&nbsp;</td>
                 <td width="164" rowspan="3" align="left">&nbsp;</td>
               </tr>
               <tr>
                 <td align="left"><input name="giftCardId" type="checkbox" value="${redeemGiftCard.id}" checked="checked" /></td>
               </tr>
             </table>
             </c:if>
             <c:if test='${redeemGiftCard == null || redeemGiftCard == ""}'>
             	Your points can not be redeemed any one amazon card.
             </c:if>
           </div></td>
         </tr>
         <tr>
           <td><span class="important">*</span> GenScript Coupons expire 90 days after its date of generation. Each Coupon is for one-time use only. If a customer order is cancelled, the associated Coupon(s) will still be valid to future use.</td>
         </tr>
         <tr>
           <td height="30"><div  class="general_box" style="width:522px;height:120px;padding:8px 8px 8px 18px; ">
             <table width="500" border="0" cellpadding="0" cellspacing="0" class="Customer_table">
               <c:forEach items="${redeemCouponList}" var="coupon" varStatus="index">
               	<c:if test="${index.index mod 2 == 0}">
	               <tr><td width="289"><table width="90%" border="0" cellspacing="0" cellpadding="0" >
	                   <tr>
	                     <td width="38%" rowspan="2">
		                     <c:if test="${coupon.value == 10}">
		                     	<img src="${global_url}images/card1.jpg" width="99" height="62" />
		                     </c:if>
		                     <c:if test="${coupon.value == 25}">
		                     	<img src="${global_url}images/card2.jpg" width="99" height="62" />
		                     </c:if>
		                     <c:if test="${coupon.value == 50}">
		                     	<img src="${global_url}images/card3.jpg" width="99" height="62" />
		                     </c:if>
		                     <c:if test="${coupon.value == 100}">
		                     	<img src="${global_url}images/card4.jpg" width="99" height="62" />
		                     </c:if>
	                     </td>
	                     <td width="62%" valign="top">${coupon.value}00 Points</td>
	                   </tr>
	                   <tr>
	                     <td valign="top"><input name="couponNum${coupon.id}" id="couponNum${coupon.id}" type="text" class="NFText" value="0" size="5" /></td>
	                   </tr>
	                 </table></td>
	               </c:if>
	               <c:if test="${index.index mod 2 == 1}">
	               	<td width="211"><table width="90%" border="0" cellspacing="0" cellpadding="0">
	                   <tr>
	                     <td width="38%" rowspan="2">
						 	<c:if test="${coupon.value == 10}">
		                     	<img src="${global_url}images/card1.jpg" width="99" height="62" />
		                     </c:if>
		                     <c:if test="${coupon.value == 25}">
		                     	<img src="${global_url}images/card2.jpg" width="99" height="62" />
		                     </c:if>
		                     <c:if test="${coupon.value == 50}">
		                     	<img src="${global_url}images/card3.jpg" width="99" height="62" />
		                     </c:if>
		                     <c:if test="${coupon.value == 100}">
		                     	<img src="${global_url}images/card4.jpg" width="99" height="62" />
		                     </c:if>
						 </td>
	                     <td width="62%" valign="top">${coupon.value}00 Points</td>
	                   </tr>
	                   <tr>
	                     <td valign="top"><input name="couponNum${coupon.id}" id="couponNum${coupon.id}" type="text" class="NFText" value="0" size="5" /></td>
	                   </tr>
	                 </table></td>
	               </tr>
               </c:if> 
               </c:forEach>
             </table>
           </div></td>
         </tr>
         <tr>
           <td height="100" align="center">
             <input name="button" type="button" id="nextRedeemPoint" class="style_botton4"  value="Redeem My Points"/>
             <input type="button" name="Submit2" value="Cancel" class="style_botton" onclick="javascript:parent.$('#show_Redeem_Point_Dialog').dialog('close');"  />
          </td>
         </tr>
       </table></td>
     </tr>
    </table>
</c:if>    
</form>    
</body>
</html>

