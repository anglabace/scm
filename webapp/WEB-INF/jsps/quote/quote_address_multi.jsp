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
<s:include value="quote_config.jsp"></s:include>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>

<script>  
$(document).ready(function(){
	function init(){
		if($("#shiptoAddrFlag").val() != 3){
			$("#changeShipAddressDialogTrigger").attr("disabled", true);
		}

	     var quorderStatus = $("#quorderStatus").val();
	     if(quorderStatus == "NW" || quorderStatus == "RV" ||quorderStatus=="OP"|| quorderStatus == "BO" || quorderStatus == "PB"){
			$(".style_botton2").attr("disabled", false);
			$("#giftMsgContent").attr("readonly", false);
			$("#giftMsg").attr("disabled", false);
		 }else{
			 $(".style_botton2").attr("disabled", true);
			 $("#giftMsgContent").attr("readonly", true); 
			 $("#giftMsg").attr("disabled", true); 
		 }
	}
//Change Address for 'multiAddrIframe'
	parent.$('#changeAddressDialog2').dialog({
		autoOpen: false,
		height: 520,
		width: 670,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
		}
	});
     $("#changeShipAddressDialogTrigger").click(function(){
        if($("#shiptoAddrFlag").val() != 3){
            return;
        }
   		var itemIds = [];
   		parent.$("#itemListIframe").contents().find("[name='itemId']").each(function(i, n){
   			if($(n).attr("checked") == true){
   				itemIds.push($(n).val());
   			}
   		});
    	var itemCheckedStr = itemIds.toString();
  		if(itemCheckedStr == ''){
        	alert("No Item selected");
        	return;	 
         }
    	 var custNo = $("#custNo").val();
    	 var giftMsgContent = $("#giftMsgContent").val();
    	 var itemId = $("#itemId").val();
    	 parent.$('#changeAddressDialog2').dialog('option', "open", function(){
				var url = "qu_order_address!list.action?mainAddrType=&saveType=item&custNo="+custNo+"&codeType="+orderquoteObj.type+'&quorderNo='+orderquoteObj.sessNoValue+'&itemId='+itemId+"&message="+giftMsgContent;
				var htmlStr = '<iframe src="'+url+'" height="465" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$(this).html(htmlStr);
        	 });
    	 parent.$('#changeAddressDialog2').dialog('open');
     });
     $("#shipItems").change(function(){
    	 changeShipAddr('shipItems');
      });
     $("#giftMsg").change(function(){
    	 changeMsg('giftMsg');
    	 $("#giftMsgContent").trigger("blur");
      });
     $("#giftMsgContent").blur(function updateGiftMessage(){
			var oldMsg = $(this).attr("_v");
			var newMsg = $(this).val();
			var itemId = $("#itemId").val();
			var itemIds = [];
			parent.$("#itemListIframe").contents().find("[name='itemId']").each(function(i, n){
				if($(n).attr("checked") == true){
					itemIds.push("&itemIds="+$(n).val());
				}
			});
			var itemIdParam = itemIds.join("");
			//如果没有任何checkbox选中，则取当前item
			if(itemIdParam == "" && itemId!= ""){
				itemIdParam = "&itemIds="+itemId;
			}
			if(oldMsg != newMsg){
				$.ajax({
				    type: "GET",
				    url: "qu_order_address!updateGiftMsg.action",
				    data: "mainAddrType=&saveType=item&itemId="+itemId+itemIdParam+"&giftMsg="+newMsg+"&quorderNo="+orderquoteObj.sessNoValue+"&codeType="+orderquoteObj.type,
					success:function(){
						$("#giftMsgContent").attr("_v", newMsg);
						//$("#shipItems").find("option[value='"+shipToAddrId+"']").attr("_m", newMsg);
					},
					async: false
				    });
			}
		});
     init();
});
function changeShipAddr(selId){
	var shipToAddrId = $("#"+selId).val();
	$("#shipToAddrId").val(shipToAddrId);
	var _v = $("#"+selId).find("option:selected").attr("_v");
	var _msg = $("#"+selId).find("option:selected").attr("_m");
	if(_v == undefined){
		return;
	}
	var tmpArr = _v.split("&**&");
	var tmpDisplay = tmpArr[0];
	var itemIdStr = tmpArr[1];
	$("#tmpDisplay").html(tmpDisplay);
	$("#giftMsgContent").val(_msg);	
	if(itemIdStr != ""){
		var tmpArr = itemIdStr.split(",");
		var firstChecked = false;//set true when trigger the first tr click
		parent.$("#itemListIframe").contents().find("#itemTable :checkbox").each(function(){
			$(this).attr("checked", false);
			for(var i=0; i<tmpArr.length; i++){
					if($(this).val() == tmpArr[i]){
						$(this).attr("checked", true);
						if(firstChecked === false && !$(this).parent().parent().hasClass("tr_click")){
							firstChecked = this;
						}
						break;
					}
			}
		});
		if(firstChecked !== false){
			//$("#isJustChange").val("true");
			//$(firstChecked).trigger("click");
			//$(firstChecked).attr("checked", true);
			//$("#isJustChange").val("false");
		}
	}else{
		parent.$("#itemListIframe").contents().find("#itemTable :checkbox").each(function(){
			$(this).attr("checked", false);
		});
	}
}

function changeMsg(selId){
	var title = $("#"+selId).find("option:selected").attr("title");
	$("#giftMsgContent").val(title);
}
</script>
</head>

<body class="content" style="background-color:#FFFFFF;">
<div class="scm" style="background-color:#FFFFFF;">
<input id="custNo" name="custNo" type="hidden" value="${custNo}" />
<input id="shiptoAddrFlag" type="hidden" value="${shiptoAddrFlag}" />
<input id="shipToAddrId" type="hidden" value="${shipToAddrId}" />
<input id="quorderStatus" type="hidden" value="${mainStatus}" />
<input id="itemId" type="hidden" value="${itemId}" />

<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="General_table">
	<tr>
		<td class="gift_td">(Note:if the multiple shippping address are
		requested, select the line iterms from the table above and then enter
		the shipping address and/or the gift message for the seleted items.
		Repeat steps for all items in the quotation.)</td>
		<th valign="top" width="460">&nbsp;</th>
	</tr>

	<tr>
		<td>
		<div class="address_box">
		<div class="address_title">Ship To</div>
		<div class="address_content" id="tmpDisplay">
			<span id="multiAddrContent">${shipToAddrDisplay}</span>
		</div>
		<div class="address_botton">
			<input id="changeShipAddressDialogTrigger" type="button" class="style_botton2" value="Change Ship-To" />
		<div class="botton_box"></div>
		</div>
		</div>
		</td>

		<td rowspan="2" valign="top">
		<table width="400" border="0" cellpadding="0" cellspacing="0"
			style="margin-top: 15px; float: right">
			<tr>
				<th rowspan="2" valign="top">Gift Message</th>
				<td>
				<select name="giftMsg" style="width: 182px;" id='giftMsg' >
					<option>Please select</option>
					<s:iterator value="templateList" id="item">
						<option value="${item.name}" title="${item.content}">${item.name}</option>
					</s:iterator>
				</select>
				</td>
			</tr>
			<tr>
				<td><textarea name="giftMsgContent" cols="" rows="" _v="${shipToMessage}"
					class="content_textarea" style="width: 300px; height: 100px;"
					id='giftMsgContent'>${message}</textarea></td>

			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<th style="text-align: left; padding-left: 15px; padding-top: 20px;"
			align="left">View items for ship-to 
			<select name="shipItems" id="shipItems" style="width: 240px;" >
				<option _v="&**&&**&">Please select</option>
				<s:iterator value="addrList" id="item">
				<option _m="${item.quoteAddress.message}" _v="${item.addrDisplay}&**&${item.itemIdStr}">
				${item.addrStr}
				</option>
				</s:iterator>
			</select>
			<input type="hidden" id="isJustChange" value="false" />
		</th>
	</tr>
</table>
</div>
</body>
</html>
    