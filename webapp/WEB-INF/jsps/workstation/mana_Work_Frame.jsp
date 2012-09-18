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
			src="${global_js_url}ajax.j	s"></script>
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
            	$('#shipClerk').dialog({
					autoOpen: false,
					height: 300,
					width: 550,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });

function openSearchDlg(orderItemId) {
		var cks = $('#orderItem').contents().find( '#cks' ) ;
		   var d1 = new Array();
			for(var i=0;i<cks.length;i++){
				 if(cks[i].checked){
					var ck = cks[i].value.split(',');
					d1.push(ck[0]);
		
					}
			}
           if(d1 == null || d1.length == 0){
				alert('Please choose a record & records!');
				return;
			}
               
	$('#shipClerk').dialog("option", "open", function() {	
         var htmlStr = '<iframe src="work_station!shipClerk.action?cks='+d1+'" height="300" width="500" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         $('#shipClerk').html(htmlStr);
	});
	$('#shipClerk').dialog('open');
}
	function unassign(){
		var cksObj = $('#orderItem').contents().find( '#cks' ) ;
		var assignedTo = "";
		var d1 = new Array();
			 for(var i=0;i<cksObj.length;i++){
				 if(cksObj[i].checked){
					var ck = cksObj[i].value.split(',');
					d1.push(ck[0]);
					assignedTo = ck[1];
					}
			}
		if(d1 == null || d1.length == 0){
			alert('Please choose a record & records!');
			return;
		}
		if(assignedTo==null || assignedTo.length==0){
			alert('AssignedTo is empty!');
			return;
		}
		if(confirm("Shipping clerk are set to null?")){
	    	document.forms[0].action="work_station!unassign.action?chk="+d1;
	    	document.forms[0].submit();
	    }
	    return;
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
		<form name="workStationForm" action="" method="post" >
		<div class="scm" style="overflow:auto; width: 1100PX">
	<div class="title_content">
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>&nbsp;&nbsp;Warehouse Manager's Workstation</div>
</div>
<div style="display:block;" class="search_box" id="total_title">
				<div class="search_order">
					&nbsp;
					<iframe src="work_station!manaSearch.action" id="searchBody"
						allowTransparency="true" name="searchBody" width="99%"
						height="100" frameborder="0" scrolling="yes"></iframe>
				</div>	
			</div>
			<div>

				<div>
					<iframe id="orderItem" name="orderItem"
						src="work_station!manaList.action"  width="100%" height="400PX"
						frameborder="0" scrolling="yes"></iframe>
				</div>
			</div>

			<div class="button_box">
				<input type="button" name="Submit193" value="Assign Shipping Clerk"
					class="search_input3" onclick="openSearchDlg();" />

				<input type="button" name="Submit2"
					value="Unassign Shipping Clerk" class="search_input3" onclick="unassign();"/>
			</div>
		</div>
		<div id="shipClerk" title=" Assign Shipping Clerk"
			style="visible: hidden" >
			
		</div>
		</form>
	</body>
</html>