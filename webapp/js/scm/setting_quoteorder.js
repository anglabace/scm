$(function(){

	$('#source_add_dialog').dialog({
		autoOpen: false,
		height: 600,
		width: 790,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			//var custNo = $("#custNo").val();
			var htmlStr = '<iframe src="quote_order_source!showCreateForm.action" height="540" width="760" scrolling="yes" style="border:0px" frameborder="0"></iframe>'; 
			$('#source_add_dialog').html(htmlStr);
		}
	});
	

	$("#source_add_btn")
		.click(function(){
			$('#source_add_dialog').dialog('open');
	});


	$('#source_edit_dialog').dialog({
		autoOpen: false,
		height: 600,
		width: 790,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#editSourceDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" height="540" width="760" scrolling="yes" style="border:0px" frameborder="0"></iframe>';
			$('#source_edit_dialog').html(htmlStr);
		}
	});
	
	$("#editSourceDialogTrigger")
		.click(function(){
			$('#source_edit_dialog').dialog('open');
	});

	$('#promotion_add_dialog').dialog({
		autoOpen: false,
		height: 510,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			//var custNo = $("#custNo").val();
			var htmlStr = '<iframe id="promotion_add_iframe" src="quote_order_promotion!showCreateForm.action" height="460" width="750" scrolling="yes" style="border:0px" frameborder="0"></iframe>'; 
			$('#promotion_add_dialog').html(htmlStr);
		}
	});
	

	$("#promotion_add_btn")
		.click(function(){
			$('#promotion_add_dialog').dialog('open');
	});

	$('#promotion_edit_dialog').dialog({
		autoOpen: false,
		height: 510,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#editPromotionDialogTrigger").val();
			var htmlStr = '<iframe id="promotion_edit_iframe" src="'+url+'" height="460" width="750" scrolling="yes" style="border:0px" frameborder="0"></iframe>';
			$('#promotion_edit_dialog').html(htmlStr);
		}
	});
	
	$("#editPromotionDialogTrigger")
		.click(function(){
			$('#promotion_edit_dialog').dialog('open');
	});
	
	$("#editCouponDialogTrigger")
		.click(function(){
			$('#coupon_edit_dialog').dialog('open');
	});

	$("#coupon_add_btn")
		.click(function(){
			$('#coupon_add_dialog').dialog('open');
	});
	
	$('#coupon_add_dialog').dialog({
		autoOpen: false,
		height: 300,
		width: 500,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var htmlStr = '<iframe src="quote_order_coupon!showCreateForm.action" height="300" width="500" scrolling="no" style="border:0px" frameborder="0"></iframe>'; 
			$('#coupon_add_dialog').html(htmlStr);
		}
	});
	
	$('#coupon_edit_dialog').dialog({
		autoOpen: false,
		height: 300,
		width: 500,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#editCouponDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" height="300" width="500" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#coupon_edit_dialog').html(htmlStr);
		}
	});
});

function show_edit_source(id)
{
	//alert (id);
	var href = 'quote_order_source!input.action?id='+id +'&operation_method=edit';
	$('#editSourceDialogTrigger').val(href);
	$('#editSourceDialogTrigger').click();	
}

function show_edit_promotion(id)
{
	//alert (id);
	var href = 'quote_order_promotion!input.action?id='+id + '&operation_method=edit';
	$('#editPromotionDialogTrigger').val(href);
	$('#editPromotionDialogTrigger').click();
}

function show_edit_coupon(id)
{
	//alert (id);
	var href = 'quote_order_coupon!input.action?id='+id + '&operation_method=edit';
	$('#editCouponDialogTrigger').val(href);
	$('#editCouponDialogTrigger').click();
}

function searchSource(page_no){
	$('#sourceSearchResult').html("");
	$('#sourceSearchResult').html("<div><img src='images/indicator.gif' /></div>");
	var sCode = $('#filter_LIKES_code').attr('value');
	var sName = $('#filter_LIKES_name').attr('value');
	var sDesc = $('#filter_LIKES_description').attr('value');
	if (sCode == undefined){
		sCode = "";
	}
	if (sName == undefined){
		sName = "";
	}
	if (sDesc == undefined){
		sDesc = "";
	}

	if (! page_no){
		page_no = 1;
	}
	//get tabe pane result
	var reqUrl = "quote_order.action?ajax=yes&p_no="+page_no+"&filter_LIKES_code="+sCode+"&filter_LIKES_name="+sName+"&filter_LIKES_description="+sDesc;
	//alert (reqUrl);

	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
     		var msg = eval('(' + data + ')');
			$('#sourceSearchResult').html('');
			$('#sourceSearchPager').html('');
			var i = -1;
			for ( i in msg.map ) { 
				var content = "<tr id='source_row_"+i+"'>"; 
				//content += getTD(i,parseInt(i)+1 + (page_no-1) * 10, 60);
				content += "<td width='46'><input type='checkbox' name='sou1' value='"+i+"' /></td>";
				content += "<td width='150'><a href='javascript:void(0)' onclick=\"javascript:show_edit_source('"+i+"')\" title='"+msg.map[i].code+"'><span id='code_"+msg.map[i].sourceId+"'>"+msg.map[i].code+"</a></span></td>";
				content += "<td width='150'>&nbsp;<span id='name_"+msg.map[i].sourceId+"'>"+msg.map[i].name+"</span></td>";
				content += "<td>&nbsp;<span id='desc_"+msg.map[i].sourceId+"'>"+msg.map[i].description+"</span></td>";
				content += "</tr>";
				$('#sourceSearchResult').append(content);
			} 
			if (i == -1){
				$('#sourceSearchResult').html('No data found.'); 
			}
			pager = "<font size='2'>Total:&nbsp;"+msg.total+"</font>&nbsp;" + msg.pager;
			$('#sourceSearchPager').html(pager);
			$('tr:even >td').addClass('list_td2');  
		},
		error: function(xhr, textStatus){
			alert("Failed to access the web server. Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
			}
		}
	}); 

}

function searchPromotion(page_no){
	$('#promotionSearchResult').html('');
	$('#prmo_indicator').html("<div><img src='images/indicator.gif' /></div>");
	var sCode = $('#filter_LIKES_prmtCode').attr('value');
	var sDesc = $('#filter_LIKES_description1').attr('value'); 
	if (! page_no){
		page_no = 1;
	}
	//get tabe pane result
	var reqUrl = "quote_order!listPromotion.action?ajax=yes&p_no="+page_no+"&filter_LIKES_prmtCode="+sCode+"&filter_LIKES_description="+sDesc;
	//alert (reqUrl);

	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
     		var msg = eval('(' + data + ')');
			$('#promoSearchPager').html('');
			var i = -1;
			for ( i in msg.map ) {
				var content = "<tr id='prmt_row_"+i+"'>"; 
				content += "<td width='40'><input type='checkbox' name='prm' value='"+i+"' /></td>";

				content += "<td width='80'><span id='code_"+msg.map[i].prmtId+"'>";
				content += "<a href='javascript:void(0)' onclick=\"javascript:show_edit_promotion('"+msg.map[i].prmtId+"\')\" title='"+msg.map[i].prmtCode+"'>"+msg.map[i].prmtCode+"</a>";
				content += "</span></td>";

				content += "<td width='296'>&nbsp;<span id='desc_"+msg.map[i].prmtId+"'>"+msg.map[i].description+"</span></td>";
				content += "<td width='90'>&nbsp;<span id='apply_"+msg.map[i].prmtId+"'>"+msg.map[i].applyType+"</span></td>";

				//content += "<td width='70'>&nbsp;<span id='discType_"+msg.map[i].prmtId+"'>";
				//if (msg.map[i].discType == "1"){
				//	content += msg.map[i].discPercent+ " %";
				//}else if (msg.map[i].discType == "2"){
				//	content += msg.map[i].discAmount; 
				//}else{
				//	content += msg.map[i].discProd; 
				//}
				//content += "</span></td>"; 

				content += "<td width='60'>&nbsp;<span id='rfm_"+msg.map[i].prmtId+"'>";
				if (msg.map[i].rfmValueCd != undefined){
					content += msg.map[i].rfmValueCd	;
				}
				content += "</span></td>";

				content += "<td width='80'>&nbsp;<span id='source_"+msg.map[i].prmtId+"'>";
				if (msg.map[i].orderSourceCd != undefined){
					content += msg.map[i].orderSourceCd;
				}
				content += "</span></td>";

				//content += "<td width='100'>&nbsp;<span id='eff_"+msg.map[i].prmtId+"'>"+msg.map[i].orderTotalMin1;
				//if (msg.map[i].orderTotalMax != undefined){
				//	content += "~" + msg.map[i].orderTotalMax;
				//}
				//content += "</span></td>";

				content += "<td width='90'>&nbsp;<span id='from_"+msg.map[i].prmtId+"'>";
				if (msg.map[i].orderEffFrom != undefined){
					var orderEffFrom = new Date(msg.map[i].orderEffFrom);
					content += orderEffFrom.format("yyyy-mm-dd"); 
				}
				content += "</span></td>";

				content += "<td width='90'>&nbsp;<span id='to_"+msg.map[i].prmtId+"'>";
				if (msg.map[i].orderEffTo != undefined){
					var orderEffTo = new Date(msg.map[i].orderEffTo);
					content += orderEffTo.format("yyyy-mm-dd");
				}
				content += "</span></td>";
				$('#promotionSearchResult').append(content);
			} 
			if (i == -1){
				$('#promotionSearchResult').html('No data found.'); 
			}
			$('#prmo_indicator').html('');
			pager = "<font size='2'>Total:&nbsp;"+msg.total+"</font>&nbsp;" + msg.pager;
			$('#promoSearchPager').html(pager);
			$('tr:even >td').addClass('list_td2');  
		},
		error: function(xhr, textStatus){
			$('#prmo_indicator').html('');
			pager = "<font size='2'>Total:&nbsp;"+msg.total+"</font>&nbsp;" + msg.pager;
			alert("Failed to access the web server. Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
			}
		}
	}); 

}

function searchCoupon(page_no){
	$('#couponSearchResult').html('');
	$('#coupon_indicator').html("<div><img src='images/indicator.gif' /></div>");
	var sCode = $('#filterLIKEScode').attr('value'); 
	if (! page_no){
		page_no = 1;
	}
	//get tabe pane result
	var reqUrl = "quote_order!listCoupon.action?ajax=yes&p_no="+page_no+"&filter_LIKES_code="+sCode;
	//alert (reqUrl);

	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
     		var msg = eval('(' + data + ')');
			$('#couponSearchPager').html('');
			var i = -1;
			for ( i in msg.map ) {
				var content = "<tr id='coupon_row_"+i+"'>"; 
				content += "<td width='40'><input type='checkbox' name='prm' value='"+i+"' /></td>";

				content += "<td width='100'><span id='code_"+msg.map[i].id+"'>";
				content += "<a href='javascript:void(0)' onclick=\"javascript:show_edit_coupon('"+msg.map[i].id+"\')\" title='"+msg.map[i].code+"'>"+msg.map[i].code+"</a>";
				content += "</span></td>";

				content += "<td width='200'>&nbsp;<span id='desc_"+msg.map[i].id+"'>"+msg.map[i].name+"</span></td>";
				content += "<td width='150'>&nbsp;<span id='apply_"+msg.map[i].id+"'>"+msg.map[i].value+"</span></td>";

				content += "<td>&nbsp;<span id='discType_"+msg.map[i].id+"'>";
				content += msg.map[i].comments+"</span></td>";
				$('#couponSearchResult').append(content);
			} 
			if (i == -1){
				$('#couponSearchResult').html('No data found.'); 
			}
			$('#coupon_indicator').html('');
			pager = "<font size='2'>Total:&nbsp;"+msg.total+"</font>&nbsp;" + msg.pager;
			$('#couponSearchPager').html(pager);
			$('tr:even >td').addClass('list_td2');  
		},
		error: function(xhr, textStatus){
			$('#coupon_indicator').html('');
			pager = "<font size='2'>Total:&nbsp;"+msg.total+"</font>&nbsp;" + msg.pager;
			alert("Failed to access the web server. Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
			}
		}
	}); 

}

function save(from_type){
	var save_type = $('#current_tab_name').val();
	//alert (save_type);
	var reqUrl = "quote_order!save"+save_type+".action";
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			if (data.indexOf("SUCCESS") == 0){
				if (data.indexOf("##") > 0){
					var str = data.substr(data.indexOf("##")+2);
					alert (str + " can not be deleted!");
				}else{
					alert ("Saved successfully.");
				}
				if (from_type != "from_check"){
					eval('search'+save_type+'(1)'); 
				}
			}else{
				//alert (data);
			}
			return;
		},
		async: false
	}); 
}

function clear_session(from_type){
  //  alert("sssssss");
	var save_type = $('#current_tab_name').val();
	if (! from_type){
		if(!confirm("Are you sure to cancel all the update in this tab?")){
			return;
		}
	}
	var reqUrl = "quote_order!clearSession.action?type=" + save_type;
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			if (data == "SUCCESS"){
				alert ("Canceled successfully."); 
				//alert ('search'+save_type+'(1)');
				if (from_type != "from_check"){
					eval('search'+save_type+'(1)');
				}
			}
		},
		async: false
	}); 
}

//check if exist unsaved data in session
function check_session(){
	var save_type = $('#current_tab_name').val();
	var reqUrl = "quote_order!checkSession.action?type=" + save_type;
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			if (data == "Y"){
				if(confirm("There are unsaved data. Select 'Yes' to save, 'No' to cancel.")){
					save('from_check');
				}else{
					clear_session('from_check');
				}
			}
			return;
		},
		async: false
	}); 
}

function  sur(e)  
{
  var a = document.getElementsByName("sou1");  
  for (var i=0; i<a.length; i++)  a[i].checked  =  e.checked; 
}

function del_source(){
	var a = document.getElementsByName("sou1");
	var to_del = ""; 
	var total = a.length;
	for (var i=(total-1); i>=0; i--){
		if (a[i].checked ){
			to_del += a[i].value+"-"; 
			$('#source_row_'+a[i].value).remove();
		}
	}
	var reqUrl = "quote_order!delRow.action?type=Source&toDel="+to_del;
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			//alert (data);
			if (data == 'SUCCESS'){
			}else{
				alert(data);
				return;
			}
		},
		async: false
	}); 
	
}

function del_promotion(){
	var a = document.getElementsByName("prm");
	var to_del = ""; 
	var total = a.length;
	for (var i=(total-1); i>=0; i--){
		if (a[i].checked ){
			to_del += a[i].value+"-"; 
			$('#prmt_row_'+a[i].value).remove();
		}
	}
	var reqUrl = "quote_order!delRow.action?type=Promotion&toDel="+to_del;
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			//alert (data);
			if (data == 'SUCCESS'){
			}else{
				alert(data);
				return;
			}
		},
		async: false
	});
}

function del_coupon(){
	var a = document.getElementsByName("prm");
	var to_del = ""; 
	var total = a.length;
	for (var i=(total-1); i>=0; i--){
		if (a[i].checked ){
			to_del += a[i].value+"-"; 
			$('#coupon_row_'+a[i].value).remove();
		}
	}
	var reqUrl = "quote_order_coupon!delete.action?toDel="+to_del;
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			if (data == 'SUCCESS'){
			}else{
				alert(data);
				return;
			}
		},
		async: false
	});
}

function prm(e)  
{
  var a =   document.getElementsByName("prm");  
  for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
} 	
function   cat(e)  
{
  var   a   =   document.getElementsByName("cat");  
  for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}
function   ntc(e)  
{  
  var   a   =   document.getElementsByName("ntc");  
  for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}
function   mes(e)  
{  
  var   a   =   document.getElementsByName("mes");  
  for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}
function   typ(e)  
{  
  var   a   =   document.getElementsByName("typ");  
  for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}

// onload init all country dropdown list and state city
$(document).ready(function(){
//		alert ("ready");
	// init country dropdown menu
	//initCountry();
	//initTerritory();
	$(document).ready(function(){  
		$('tr:even >td').addClass('list_td2');  
	});	
});

