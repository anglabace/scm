<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<s:include value="order_config.jsp"></s:include>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	function initDisplayShipAddr(shiptoAddrFlag){
		var multiAddrTabIndex = parent.window.getTabIndexBy("Multi-Ship/Gift Msg");
		if(multiAddrTabIndex == -1){
			return;
		}
		if(shiptoAddrFlag == 1){
	      	 changeTabClass(multiAddrTabIndex, 'parent', 'tabDisable');
	   	}else{
	   		var thisTab = getTabByIndex(multiAddrTabIndex, "parent");
		   	 if(thisTab.className != 'tabActive')
	      	 changeTabClass(multiAddrTabIndex, 'parent', 'tabInactive');
	   	}
	   	if(shiptoAddrFlag == 3){
	   		$("#defaultShipToDis").hide();
	   		$("#nodefaultShipToDis").show();
	   		$("#changeShipAddressDialogTrigger").attr("disabled", true);	
	  	}else{
	  		$("#defaultShipToDis").show();
	 		$("#nodefaultShipToDis").hide();
	 		$("#changeShipAddressDialogTrigger").attr("disabled", false);
		}
		var custNo = $("#custNo").val();
		var packageTabIndex = parent.window.getTabIndexBy("Packaging");
		if(packageTabIndex == -1 || custNo == "-1"){//custNo == "-1" 为内部订单，特殊处理
			return;
		}
		if($("#haveShipTo").val() == 0){
			 changeTabClass(packageTabIndex, 'parent', 'tabDisable');
		}else{
			var thisTab = getTabByIndex(packageTabIndex, "parent");
		   	 if(thisTab.className != 'tabActive')
			 	changeTabClass(packageTabIndex, 'parent', 'tabInactive');
		}
	}
	//Change Address
		parent.$('#changeAddressDialog').dialog({
			autoOpen: false,
			height: 570,
			width: 670,
			modal: true,
			bgiframe: true,
			open: function(){
			}
		});
	     $("#changeBillAddressDialogTrigger, #changeShipAddressDialogTrigger, #changeSoldAddressDialogTrigger")
	     .click(function(){
		     if($(this).attr("id") == "changeShipAddressDialogTrigger"){
			     if($("#changeMultiAddr").val() == 3){
				     return;
				  }
			 }
	    	 var mainAddrType = $(this).attr('title');
	    	 var custNo = $("#custNo").val();
	    	 parent.$('#changeAddressDialog').dialog('option', "open", function(){
					var url = "qu_order_address!list.action?custNo="+custNo+"&quorderNo="+orderquoteObj.sessNoValue+"&codeType="+orderquoteObj.type
					+'&saveType=main&mainAddrType='+mainAddrType;
					var htmlStr = '<iframe src="'+url+'" height="510" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
					$(this).html(htmlStr);
		     });
	    	 parent.$('#changeAddressDialog').dialog('open');
	     });

	     $("#changeMultiAddr").change(function(){
		    	var tmpVal = $(this).val();
		    	var quorderNo = $("#quorderNo").val();
		    	var codeType = $("#codeType").val();
	    	   	$.ajax({
	    	    	type:"get",
	    	    	url:"qu_order_address!changeShiptoAddrFlag.action",
	    	    	data:"quorderNo="+orderquoteObj.sessNoValue+"&shiptoAddrFlag="+tmpVal+'&codeType='+orderquoteObj.type,
	    	    	dataType:"text",
	    	    	success:function(data){
						if(data == "SUCCESS"){
							
						}else{
							if(data){
								alert(data);
							}else{
								alert("System error! Please contact system administrator for help.");
							}
						}
		    	   		initDisplayShipAddr(tmpVal);
				    //refresh multiAddr tab
		    	   		parent.document.getElementById("multiAddrIframe").src = parent.document.getElementById("multiAddrIframe").src;
	    	    	},
	    	    	error:function(){
		    	    	alert("Failed to save the address.");
		    	    	return;
		    	    },
	    	    	async:false   	
	    	   	});
		    });
		 var shiptoAddrFlag = $("#shiptoAddrFlag").val();
	     $("#changeMultiAddr option[value='"+shiptoAddrFlag+"']").attr("selected", true);
	     initDisplayShipAddr(shiptoAddrFlag);
	     var quorderStatus = $("#quorderStatus").val();
	     if(quorderStatus == "NW" || quorderStatus == "RV" || quorderStatus == "BO" || quorderStatus == "PB"){
			$(".style_botton2").attr("disabled", false);
			$("#changeMultiAddr").attr("disabled", false);
		 }else{
			 $(".style_botton2").attr("disabled", true);
			 $("#changeMultiAddr").attr("disabled", true); 
		 } 
});
</script>


</head>

<body class="content" style="background-color:#FFFFFF;">
<div class="scm" style="background-color:#FFFFFF;">
<input id="haveShipTo" type="hidden" value="${haveShipTo}" />
<input id="shiptoAddrFlag" type="hidden" value="${shiptoAddrFlag}" />
<input id="quorderStatus" type="hidden" value="${mainStatus}" />

<input id="custNo" type="hidden" value="${custNo}" />
<input id="quorderNo" type="hidden" value="${quorderNo}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<div class="address_box" style="margin-left: 12px;">
		<div class="address_title">Bill To</div>
		<div class="address_content">
			${billToAddrDisplay}
		</div>
		<div class="address_botton">
			<input type="button" title="BILL_TO" id="changeBillAddressDialogTrigger" class="style_botton2" value="Change Bill-To" />
		</div>
		<div class="botton_box"></div>
		</div>
		</td>
		<td width="33%" valign="middle">
		<div class="address_box">
		<div class="address_title">Ship To
			<span style="padding: 0px; margin: 0px;"> 
				<select id="changeMultiAddr" style="width: 210px;">
					<option value="1">Single Address</option>
					<option value="2">Single Address W/ Gift Message</option>
					<option value="3">Multiple Addresses (Gift Message)</option>
				</select>
			</span>
		</div>
		<div class="address_content">
			<span id="defaultShipToDis">${shipToAddrDisplay}<br /></span>
			<span id="nodefaultShipToDis" style="display:none;">Choose Multiple Addresses(Gift Message)</span>
		</div>
		<div class="address_botton">
			<input title="SHIP_TO" id="changeShipAddressDialogTrigger" type="button" class="style_botton2" value="Change Ship-To" />
		</div>
		</div>
		</td>
		<td>
		<div class="address_box">
		<div class="address_title">Sold To (Optional)</div>
		<div class="address_content">${soldToAddrDisplay}</div>
		<div class="address_botton">
			<input title="SOLD_TO" id="changeSoldAddressDialogTrigger" type="button" class="style_botton2" value="Change Sold-To" />
		</div>
		</div>
		</td>
	</tr>
</table>
</div>

</body>
</html>
    