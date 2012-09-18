<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Routings</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
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
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script type="text/javascript">
            $(function() {
            	$("select").each(function(){
         			var changeWidth=false;
         	   		var len = this.offsetWidth;
         	   		if(len!=0) {
        	 	   		this.style.width = 'auto';
        	 	   		if(len<this.offsetWidth) {
        	 	   			changeWidth = true;
        	 	   		}
        	 	   		this.style.width=len+"px";
        	 	   		$(this).mousedown(function(){
        	 	   			if(changeWidth) {
        	 	   				this.style.width = 'auto';
        	 	   			}
        	 	   			});
        	 	   		$(this).blur(function() {this.style.width = len+"px";});
        	 	   		$(this).change(function(){this.style.width = len+"px";});
         	   		}
         	   		
         	   	});
            	$('#new_route_dlg').dialog({
					autoOpen: false,
					height: 360,
					width: 660,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#new_route_dlg_btn").click( function() {
					$('#new_route_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="route!add.action" height="260" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#new_route_dlg').html(htmlStr);
					});
					$('#new_route_dlg').dialog('open');
                });
            
            
            });
            function toggleShowMore_img(obj,divID){
          		var oId = document.getElementById(divID);
          		if (obj.src.indexOf('arrow1.jpg') > 0){
          			obj.src="${global_url}images/arrow.jpg";
          			oId.style.display = "none"; 
          		}else{
          			obj.src="${global_url}images/arrow1.jpg";
          			oId.style.display = "block"; 
          		}
          }
        </script>
	</head>
	<body class="content" style="background-image: none;">
		<form action="route!list.action" method="get" target="result_iframe">
			<div class="scm">
			<div class="title_content">
  				<div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow1.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Routings</div>
			</div>
				<div class="search_box" id="Routings">
					<div class="single_search">
						<table border="0" cellspacing="0" cellpadding="0"
							class="General_table">
							<tr>
								<td>
									Routing Name
								</td>
								<td width="120">
									<input name="filter_LIKES_name" type="text" class="NFText"
										size="20" />
								</td>
								<td>
									Description
								</td>
								<td width="120">
									<input name="filter_LIKES_description" type="text"
										class="NFText" size="20" />
								</td>
								<td>
									Production
								</td>
								<td width="120">
									<select name="filter_EQS_production">
										<option>All</option>
									<s:iterator value="productionList" var="typeClsId">
										<option value="${typeClsId[0]}">${typeClsId[1]}</option>
									</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">
									Warehouse
								</td>
								<td width="120">
									<s:select id="warehouse_sel" name="filter_EQI_warehouseId"
										list="warehouseList" headerKey="" headerValue="All" listKey="warehouseId" listValue="name"></s:select>
								</td>
								<td align="right">
									Status
								</td>
								<td width="120">
									<select name="filter_EQS_status">
										<option>
											ACTIVE
										</option>
										<option>
											INACTIVE
										</option>
									</select>
								</td>
								<th>
									
								</th>
								<td width="120">
									
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="Search"
										class="search_input" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="input_box" style="height: 320px; margin-top: -20px">
					<div class="content_box">
						<iframe id="srch_route_iframe" name="result_iframe"
							src="route!list.action?filter_EQS_status=ACTIVE" width="100%"
							height="630" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
				<div class="button_box">
					<input type="button" name="Submit52" id="new_route_dlg_btn"
						value="New" class="search_input" />
				</div>
			</div>
		</form>
		<div id="new_route_dlg" title=" New Routing "
			style="visible: hidden">
		</div>
	</body>
</html>