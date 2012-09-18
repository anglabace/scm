<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script type="text/javascript" language="javascript">
$(document).ready(function(){
	$("#updateQuoteItemScheduleDeliveryBtn").click(function(){
		var itemId = parent.$("#itemDetailIframe").contents().find("#itemId").val();
		var newScheduleShipment = $("#newScheduleShipment").val();
		if (newScheduleShipment == undefined || newScheduleShipment == "") {
			alert("Please enter the New Schedule Delivery.");
			return;
		}
		newScheduleShipment = newScheduleShipment.replace(/\s/g, "").replace(/\D+/g, "");
		if (newScheduleShipment == "") {
			alert("Invalid New Schedule Delivery, the New Schedule Delivery must be number.");
			return;
		}
		var reason = $("#reason").val();
		if(reason == undefined || $.trim(reason) == ""){
			alert("Please enter the modify reason.");
			return;
		}
		reason = encodeURIComponent(reason);
		$.ajax({
			type:"post",
			url:orderquoteObj.url("updateItemScheduleShipment"),
			data:"&itemId="+itemId+"&newScheduleShipment="+newScheduleShipment+"&reason="+reason,
			dataType:"json",
			success:function(data){
				if (data.newScheduleShipment != undefined) {
					parent.$("#itemDetailIframe").contents().find("#shipSchedule1").val(data.newScheduleShipment);
			   	}	
				parent.$('#updateQuoteScheduleDeliveryDialog').dialog('close');
			},
			error:function(){
	//				alert("Fail to get the right status");
			},
			async:false
		});
	});
	
	$("#calculate").click(function(){
		var itemId = parent.$("#itemDetailIframe").contents().find("#itemId").val();
		$.ajax({
			type:"post",
			url:orderquoteObj.url("calculateTargetDate"),
			data:"&itemId="+itemId,
			dataType:"json",
			success:function(data){
				if (data.scheduleDays != undefined) {
					$("#newScheduleShipment").val(data.scheduleDays);
			   	}	
			},
			error:function(){
	//				alert("Fail to get the right status");
			},
			async:false
		});
	});
});
</script>
<s:include value="quote_config.jsp"></s:include>
<title>Update Quote Schedule Delivery</title>
</head>
<body>
<table id="updateQuoteScheduleDeliveryTable"  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
	  <tr>
	    <th width="150" valign="top"><span class="important">*</span>New Schedule Delivery:</th>
	    <td  >
	      <input type="text" id="newScheduleShipment" name="newScheduleShipment" value="${quoteItem.shipSchedule}" class="NFText" size="34"/>
	    	<input type="button" id="calculate" name="calculate" class="style_botton"  value="Calculate" />
	    </td>
	  </tr>
	  <tr>
	    <th valign="top"><span class="important">*</span>The modify reason:</th>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <th valign="top">&nbsp;</th>
	    <td width="75%"><textarea name="reason" id="reason" class="content_textarea2"></textarea></td>
	  </tr>
	  <tr>
	    <td colspan="2">
	    	<div align="center"><br />
		       <input type="button" class="style_botton" value="Confirm" id="updateQuoteItemScheduleDeliveryBtn" />
		       <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="parent.$('#updateQuoteScheduleDeliveryDialog').dialog('close');" />
	    	</div>
	    </td>
	  </tr>
	</table>
</body>
</html>	