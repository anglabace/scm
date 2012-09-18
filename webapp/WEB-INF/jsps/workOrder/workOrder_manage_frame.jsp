<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Warehouse Manager's Workstation</title>
		<%@ include file="/common/taglib.jsp"%>
		<base href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<script language="javascript" type="text/javascript"
			src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}TabbedPanels.js"></script>
		<script language="javascript" type="text/javascript"
			src="js/newwindow.js"></script>

		<script type="text/javascript">
	var GB_ROOT_DIR = "./greybox/";
</script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
		<script type="text/javascript"
			src="${global_js_url}greybox/gb_scripts.js"></script>
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<script
			src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js"
			type="text/javascript"></script>

		<script type="text/javascript" language="javascript">
				 $(function() {            
            	$('#vendor_search_dlg2').dialog({
					autoOpen: false,
					height: 340,
					width: 800,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });

function openSearchDlgs() {
var warehouse = $('#searchBody').contents().find( '#type' ).attr("value");
var cksObj = $('#userInfo').contents().find( '#cks' ) ;
var d1 = new Array();
cksObj.each(
	function()
	{
		if( $(this).attr( 'checked' ) == true ) 
		{
			d1.push($( this ).val() ) ;
		}
	}
) ;

	$('#vendor_search_dlg2').dialog("option", "open", function() {
         var htmlStr = '<iframe src="work_order!batchOrderReceiving.action?warehouse='+warehouse+'&cks='+d1+'" height="290" width="760" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         $('#vendor_search_dlg2').html(htmlStr);
	});
	$('#vendor_search_dlg2').dialog('open');

}		


				 $(function() {            
            	$('#vendor_search_dlg3').dialog({
					autoOpen: false,
					height: 300,
					width: 650,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
 $(function() {            
            	$('#vendor_search_dlg4').dialog({
					autoOpen: false,
					height: 300,
					width: 650,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });

function openSearchDlgss() {
	$('#vendor_search_dlg3').dialog("option", "open", function() {	
         var htmlStr = '<iframe src="work_order!createReceivingReport.action"  height="250" width="600" scrolling="no"  style="border:0px" frameborder="0"></iframe>';
         $('#vendor_search_dlg3').html(htmlStr);
	});
	$('#vendor_search_dlg3').dialog('open');

}
function toggleShowMore_img(obj,divID){
		var oId = document.getElementById(divID);
		if (obj.src.indexOf('arrow.jpg') > 0){
			obj.src="${global_url}images/title_bgewe.jpg";
			oId.style.display = "none"; 
		}else{
			obj.src="${global_url}images/arrow.jpg";
			oId.style.display = "block"; 
		}
	}
</script>
	</head>
	<body class="content">
		<div class="scm" style="overflow:auto; width: 1100PX">
				<div class="title_content">
  				<div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>&nbsp;&nbsp;Purchase Orders for Receiving</div>
				</div>
			<div style="display:block;" class="search_box" id="total_title">
				<div class="search_order">
					&nbsp;
					<iframe src="work_order!workOrderSearch.action?type=${type }" id="searchBody"
						allowTransparency="true" name="searchBody" width="100%"
						height="120" frameborder="0" scrolling="no"></iframe>
				</div>
			</div>
			<iframe name="userInfo" id="userInfo"
						src="work_order!getWorkOrderManageList.action?type=${type }" width="100%"
						height="400PX" frameborder="0" scrolling="no"></iframe>
				
			<div class="button_box">
				<input type="button" name="Submit193" value="Batch Order Receiving"
					class="search_input2" onclick="openSearchDlgs();" />
				<input type="button" name="Submit2"
					value="View/Print Receiving Report" class="search_input3"
					onclick="openSearchDlgss();" />
			</div>
		</div>
		
		<div id="vendor_search_dlg" title=" Vendor Search "
			style="visible: hidden">
			<div id="vendor_search_dlg2" title="Batch Order Receiving "
				style="visible: hidden;">
				<div id="vendor_search_dlg3" title="Create Receiving Report "
					style="visible: hidden;">
				</div>
				</div>
				</div>
	</body>
</html>
