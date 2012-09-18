<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/common/taglib.jsp"%>
	<head>
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
			<ul class="TabbedPanelsTabGroup" style="width:300px">
				<li class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="0"
					id="TabbedPanelsTab1">
					Product
				</li>
				<li class="TabbedPanelsTab" tabindex="1" id="TabbedPanelsTab2">
					Service
				</li>
			</ul>
			<div class="TabbedPanelsContentGroup">
				<div style="display: block; overflow:hidden; margin-top: 10px;" class="TabbedPanelsContent"
					id="TabbedPanelsContent1">        
                     <iframe id="product_category_frame" name="product_category_frame" src="category_picker!getProductCategory.action?type=${type}" width="100%" scrolling="yes" height="350px" frameborder="0"></iframe>
				</div>
				<div style="display: block; overflow:hidden; margin-top: 10px;" class="TabbedPanelsContent"
					id="TabbedPanelsContent2">   
                     <iframe id="service_category_frame" name="service_category_frame" src="" width="100%" scrolling="yes" height="350px" frameborder="0"></iframe>
				</div>
            </div>
      </div>
<script type="text/javascript"> 
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
// publication and grant
$('#TabbedPanelsTab2').click( function() {

		if($('#service_category_frame').attr('src') == undefined || $('#service_category_frame').attr('src') == '') {
			$('#service_category_frame').attr('src', "category_picker!getServiceCategory.action?type=${type}");
		}
	}
);

$(function(){


});
</script>

	</body>
</html>
