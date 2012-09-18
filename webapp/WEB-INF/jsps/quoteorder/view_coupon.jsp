<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script>
$(function(){
	$("#select").click(function(){
		var hasCouponIds = parent.$("#couponId").val().split(",");
		var addOption = new Array();
		var addCouponId = "";
		var flg = false;
		$( '[name="couponCode"]' ).each(
				function(){
					if( $(this).attr('checked')== true ){
						if(!flg) {
							flg = true;
						}
						var array = $(this).val().split("::");
						var couponCode = array[0];
						var couponId = array[1];
						if(!contains(hasCouponIds,couponId)) {
							hasCouponIds.push(couponId);
							var option = "<option value='"+couponId+"'>"+couponCode+"</option>";
							addOption.push(option);
							if(addCouponId=="") {
								addCouponId = couponId;
							} else {
								addCouponId = addCouponId+","+couponId;
							}
						}
	
					}
				}
		);
		if(!flg) {
			alert("Please enter the gift card number.");
			return;
		}
		var couponIds = "";
		if(addCouponId==""){
			alert("The gift card added has already been existed.Failed to apply the gift card.");
			return;
		}
		if(parent.$("#couponId").val()==null||parent.$("#couponId").val()=='') {
			couponIds = addCouponId;
		} else{
			couponIds = parent.$("#couponId").val()+","+addCouponId;
		}
		if(!parent.applyCouponCode(couponIds))  {
			return;
		} else {
			for(var i =0;i<addOption.length;i++) {
				parent.$("#couponCode").append(addOption[i]);
			}
			parent.$("#couponId").attr("value",couponIds);
			parent.$('#viewCouponCodeDialog').dialog('close');
		}
		if(parent.$("#couponCode").val()!="") {
			parent.$("#deleteSpan").show();
		}
		
	});

	$('#allCheck').click( function() {
		if ($(this).attr('checked') === true) {
			$(':check [name="couponCode"]').attr('checked', true);
		} else {
			$(':check [name="couponCode"]').attr('checked', false);
			$(this).attr('checked', false);
		}
	});
	
});





</script>
</head>
<body>
<table width="100%" border="0" cellspacing="3" cellpadding="0" id="table11">
    <tr>
      <td style="padding-top:10px;">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
           <tr>
              <th width="10%"><input name="allCheck" id="allCheck"  type="checkbox"/></th>
              <th>Gift card code</th>
           </tr>
      </table>
      </td>
   </tr>
   <tr>
      <td><div  style="width:100%; height:130px; overflow:scroll;">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
          <s:iterator value="couponPage.result" status="index">
          	<s:if test="#index.odd">
          	 <tr>
                 <td  width="10%">
	                 <div align="center">
	                 <input type="checkbox" name="couponCode" value="${code}::${id}" />
	                 </div>
                 </td>
                 <td>${code}</td>
              </tr>
          	</s:if>
          	<s:else>
          	<tr>
                <td class="list_td2">
                	<div align="center">
                	<input type="checkbox" name="couponCode"  value="${code}::${id}" />
                    </div>
                </td>
                <td class="list_td2">${code}</td>
             </tr>
          	</s:else>
          </s:iterator>
         </table>
         </div>
        </td>
       </tr>
       <tr>
           <td>
           <div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/qu_order!viewCoupon.action"
								name="moduleURL" />
						</jsp:include>
					</div>
			</td>
       </tr>
       <tr>
        <td height="60">
        <div align="center">
        <input id="select" name="Submit1" type="button" class="style_botton"  value="Select"/>
        <input id="sub2" type="button" name="Submit2" value="Cancel" class="style_botton" onclick="parent.$('#viewCouponCodeDialog').dialog('close');"/>
        </div>
        </td>
      </tr>
 </table>     
</body>
</html>