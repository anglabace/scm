<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Warehouse Shipping</title>
		<%@ include file="/common/taglib.jsp"%>
		<base href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
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
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
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
		
		<script>
			function cc(e) {
				var a = document.getElementsByName("uu1");
				for ( var i = 0; i < a.length; i++)
					a[i].checked = e.checked;
			}
			function refresh(){
				window.location.reload();
			}
			function shipping(form){
				var cksObj = $('#appInfo').contents().find('#chk') ;
				var d1 = new Array();
				var ids = new Array();
				var shipments = new Array();
				var status = new Array();
				var str = '';
				var shipmentId;
				cksObj.each(
					function(){
						if( $(this).attr('checked') == true ){
							var val = $(this).val().split(",");
							shipmentId = val[3];
							d1.push(val[0]+","+val[1]+","+val[2]);
						}
					}
				);
				if(d1 == null || d1.length == 0){
					alert('Please choose an order to be shiped.');
					return;
				}
				for(var i=0;i<d1.length;i++){
					var d1_ = d1[i].split(',');
					ids.push(d1_[0]);
					shipments.push(d1_[1]);
					status.push(d1_[2]);
				}
				var usaFlag = true;
				var chinaFlag = true;
				var flag = true;
				for(var j=0;j<status.length;j++){
					if(status[j] != 'SALES'){
						usaFlag = false;
						break;
					}
				}
				for(var j=0;j<status.length;j++){
					if(status[j] != 'MANUFACTURING'){
						chinaFlag = false;
						break;
					}
				}
				if(usaFlag == false && chinaFlag == false){
					alert('Please choose the record(s) which in the same warehouse!');
					return;
				}
				var temp = '';
				if(usaFlag == true){
					for(var k=0;k<shipments.length;k++){
						if(k == 0)
							temp = shipments[0];
						if(k > 0){
							if(temp != shipments[k]){
								flag = false;
								break;
							}
						}
					}
					if(flag == false){
						alert('Please choose the record(s) which in the same shipment!');
						return;
					}
				}
				form.action = "shipping!packingDetail.action?shipmentId="+shipmentId+"&chks="+ids;
				form.submit();
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
		<form name="shippingForm" action="" method="post">
			<div class="scm" style="overflow:auto; width: 1100PX">
				
				<div class="title_content">
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>&nbsp;&nbsp;Shipping Workstation</div>
</div>
<div style="display:block;" class="search_box" id="total_title">
					<div class="search_order">
						<iframe src="shipping!shipping_search.action" id="searchBody"
							name="searchBody" allowTransparency="true" width="100%" height="88" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>


						<iframe src="shipping!shippingList.action" name="appInfo"
							id="appInfo" width="100%" height="350px" frameborder="0" scrolling="no"></iframe>


				<div class="button_box">
					<input type="button" name="Submit193" value="Ship" class="search_input" onclick="shipping(shippingForm);" />
					<!-- <input type="button" name="Submit" class="search_input" value="Refresh List" onclick="refresh()" /> -->
					<input type="button" name="Submit" class="search_input" value="Refresh List" onclick="refresh()" />
				</div>
			</div>
		</form>
	</body>
</html>