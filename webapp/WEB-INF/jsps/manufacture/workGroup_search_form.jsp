<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Work Groups</title>
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
		<script type="text/javascript">
            $(function() {
            
            	$('#new_workGroup_dlg').dialog({
					autoOpen: false,
					height: 360,
					width: 660,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	$('#userChoiceDialog').dialog({
            		autoOpen: false,
            		height: 440,
            		width: 700,
            		modal: true,
            		bgiframe: true,
            		buttons: {
            		}
            	});
				
                $("#new_workGroup_dlg_btn").click( function() {
					$('#new_workGroup_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="work_group!add.action" id="workGroupAddFrame" height="260" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#new_workGroup_dlg').html(htmlStr);
					});
					$('#new_workGroup_dlg').dialog('open');
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
		<form action="work_group!list.action" method="get"
			target="result_iframe">
			<div class="scm">
			<div class="title_content">
  				<div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow1.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Work Group</div>
			</div>
				<div class="search_box" id="search_box1">
					<div class="single_search">
						<table border="0" cellspacing="0" cellpadding="0"
							class="General_table">
							<tr>
								<td>
									Work Group Name
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
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" name="Submit5" value="Search"
										class="search_input" />
								</td>

							</tr>
						</table>
					</div>
				</div>
				<div class="input_box"  style="height:350px;" >
					<div class="content_box">
						<iframe id="srch_workGroup_iframe" name="result_iframe"
							src="work_group!list.action?filter_EQS_status=ACTIVE" width="100%"
							height="630" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
				<div class="button_box">
				<s:if test='addAuth=="Y"'>
					<input type="button" name="Submit52" id="new_workGroup_dlg_btn"
						value="New" class="search_input" />
				</s:if>
					
				</div>
			</div>
		</form>
		<div id="new_workGroup_dlg" title=" New Work Group "
			style="visible: hidden">
		<div id="userChoiceDialog" title="Select Supervisor"></div>
		</div>
	</body>
</html>