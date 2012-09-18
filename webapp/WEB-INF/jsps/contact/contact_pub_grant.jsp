<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/common/taglib.jsp"%>
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>scm</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" src="${global_js_url}scm/config.js"></script>
		<script src="${global_js_url}TabbedPanels.js" type="text/javascript"></script>

		<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>

	</head>
	<body>
		<div id="TabbedPanels1" class="TabbedPanels" style="overflow:auto;overflow: auto">
			<ul class="TabbedPanelsTabGroup" style="width:945px">
				<li class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="0"
					id="TabbedPanelsTab1">
					Grants
				</li>
				<li class="TabbedPanelsTab" tabindex="1" id="TabbedPanelsTab2">
					Publications
				</li>
			</ul>
			<div class="TabbedPanelsContentGroup">
				<div style="display: block; overflow:hidden; margin-top: 10px;" class="TabbedPanelsContent"
					id="TabbedPanelsContent1">        
                     <iframe id="grant_list_frame" name="grant_list_frame" src="contact_grant!list.action?sessContactNo=${sessContactNo}" width="100%" scrolling="yes" height="350px" frameborder="0"></iframe>
				</div>
				<div style="display: block; overflow:hidden; margin-top: 10px;" class="TabbedPanelsContent"
					id="TabbedPanelsContent2">   
                     <iframe id="pub_list_frame" name="pub_list_frame" src="" width="100%" scrolling="yes" height="350px" frameborder="0"></iframe>
				</div>
            </div>
      </div>
<div id="newPublicationDialog" title="Add New Publication" style="visible:hidden"></div>

<div id="newGrantDialog" title="Add New Grant" style="visible:hidden"></div>
 
<div id="editPublicationDialog" title="Edit Publication" style="visible:hidden"></div>
<input type="hidden" id="editPublicationDialogTrigger" />
<div id="editGrantDialog" title="Edit Grant" style="visible:hidden"></div>
<input type="hidden" id="editGrantDialogTrigger" />
<script type="text/javascript"> 
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
// publication and grant
$('#TabbedPanelsTab2').click( function() {

		if($('#pub_list_frame').attr('src') == undefined || $('#pub_list_frame').attr('src') == '') {
			$('#pub_list_frame').attr('src', "contact_pub!list.action?sessContactNo=${sessContactNo}");
		}
	}
);

$(function(){

	$("#newGrantDialog").dialog({
		autoOpen: false,
		height: 360,
		width: 660,
		modal: true,
		bgiframe: true,
		open: function(){
			htmlStr = '<iframe src="contact_grant!edit.action?sessContactNo=${sessContactNo}" height="300" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#newGrantDialog').html(htmlStr);
		}
	});
	
	//定义弹出修改Grant窗口事件内容
    $('#editGrantDialog').dialog({
		autoOpen: false,
		height: 500,
		width: 730,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
	//触发Grant修改框弹出事件
	$("#editGrantDialogTrigger")
		.click(function(){
		$('#editGrantDialog').dialog("option","open",function(){
			var url = $("#editGrantDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" height="440" width="690" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#editGrantDialog').html(htmlStr);
		
		});
		$('#editGrantDialog').dialog('open');
	});
	


   //定义弹出新增Pub窗口事件内容
	$("#newPublicationDialog").dialog({
		autoOpen: false,
		height: 360,
		width: 660,
		modal: true,
		bgiframe: true,
		open: function(){
			htmlStr = '<iframe src="contact_pub!edit.action?sessContactNo=${sessContactNo}" height="300" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#newPublicationDialog').html(htmlStr);
		}
	});

    //定义弹出修改Pub窗口事件内容
    $('#editPublicationDialog').dialog({
		autoOpen: false,
		height: 500,
		width: 730,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
	//触发Pub修改框弹出事件
	$("#editPublicationDialogTrigger")
		.click(function(){
		$('#editPublicationDialog').dialog("option","open",function(){
			var url = $("#editPublicationDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" height="440" width="690" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#editPublicationDialog').html(htmlStr);
		
		});
		$('#editPublicationDialog').dialog('open');
	});

});
</script>

	</body>
</html>
