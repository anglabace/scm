<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Resources</title>
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
            	$('#new_resource_dlg').dialog({
					autoOpen: false,
					height: 360,
					width: 660,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#new_resource_dlg_btn").click( function() {
					$('#new_resource_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="resource!add.action" height="260" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#new_resource_dlg').html(htmlStr);
					});
					$('#new_resource_dlg').dialog('open');
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
            function check() {
            	var filter_LIKES_resourceNo = $("#filter_LIKES_resourceNo").val();
            	$("#filter_LIKES_resourceNo").val($.trim(filter_LIKES_resourceNo));
            	var filter_LIKES_name = $("#filter_LIKES_name").val();
            	$("#filter_LIKES_name").val($.trim(filter_LIKES_name));
            	var filter_LIKES_description = $("#filter_LIKES_description").val();
            	$("#filter_LIKES_description").val($.trim(filter_LIKES_description));
            	return true;
            }
        </script>
	</head>
	<body class="content" style="background-image: none;">
		<form action="resource!list.action" method="get"
			target="result_iframe">
			<div class="scm">
			<div class="title_content">
  				<div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow1.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Production Resources</div>
			</div>
				<div class="search_box" id="search_box1">
					<div class="single_search">
						<table border="0" cellspacing="0" cellpadding="0"
							class="General_table">
							<tr>
								<td>
									Resource No
								</td>
								<td width="120">
									<input id="filter_LIKES_resourceNo" name="filter_LIKES_resourceNo" type="text" class="NFText"
										size="20" />
								</td>
								<td>
									Resource Name
								</td>
								<td width="120">
									<input id="filter_LIKES_name" name="filter_LIKES_name" type="text" class="NFText"
										size="20" />
								</td>
								<td>
									Description
								</td>
								<td width="120">
									<input id="filter_LIKES_description" name="filter_LIKES_description" type="text"
										class="NFText" size="20" />
								</td>
								<td>User</td>
								<td width="120">
								<s:if test="userDeptList!=null&&userDeptList.size()>0">
									<s:select id="filter_EQI_userDept" list="userDeptList" name="filter_EQI_userDept" listKey="id" listValue="name" headerKey="0" headerValue="All">
									</s:select>
								</s:if>
								<s:else>
									<select id="filter_EQI_userDept" name="filter_EQI_userDept">
										<option value="0">All</option>
									</select>
								</s:else>
								
								</td>
								<td>Resource Group</td>
								<td width="120">
								<s:if test="dropDownMap['RESOURCE_GROUP']!=null&&dropDownMap['RESOURCE_GROUP'].size()>0">
							
								<s:select id="filter_EQS_group" list="dropDownMap['RESOURCE_GROUP']" name="filter_EQS_group" listKey="value" listValue="text" headerKey="" headerValue="All"></s:select>
								</s:if>
								<s:else>
								<select id="filter_EQS_group" name="filter_EQS_group">
									<option value="">All</option>
								</select>
								</s:else>
								</td>
								</tr>
								<tr>
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
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								</tr>
								<tr>
								<td colspan="8" align="center">
									<input type="submit" name="Submit5" value="Search"
										class="search_input" onclick="return check();"/>
								</td>

							</tr>
						</table>
					</div>
				</div>
				<div class="input_box"  style="height:350px;" >
					<div class="content_box">
						<iframe id="srchCustAct_iframe" name="result_iframe"
							src="resource!list.action?filter_EQS_status=ACTIVE" width="100%"
							height="630" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
				<div class="button_box">
					<input type="button" name="Submit52" id="new_resource_dlg_btn"
						value="New" class="search_input" />
				</div>
			</div>
		</form>
		<div id="new_resource_dlg" title=" New Resource "
			style="visible: hidden">
		</div>
	</body>
</html>