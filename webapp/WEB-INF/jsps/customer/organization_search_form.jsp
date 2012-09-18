<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Organization Groups</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
		<script type="text/javascript">
            function checkForm() {
		       $("#code_txt").val($.trim($("#code_txt").val()));
		       $("#name_txt").val($.trim($("#name_txt").val()));		    
		    }
            
            $(function() {           			
                $("#new_btn").click( function() {
					window.location = 'organization!edit.action';
                });
                $('#delReasonDlg').dialog({
            		autoOpen: false,
            		height: 200,
            		width: 500,
            		modal: true,
            		bgiframe: true,
            		buttons: {
            		}
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
		<form action="organization!list.action" method="get"
			target="result_iframe"  onsubmit="checkForm();">
			<input name="filter_EQS_activeFlag" value="Y" type="hidden" />
			<div class="scm">
				<div class="title_content">
					<div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow1.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>Organizations</div>
				</div>
				<div class="search_box" id="search_box1">
					<div class="single_search">
						<table border="0" cellspacing="0" cellpadding="0"
							class="General_table">
							<tr>
								<td>
									Organization Name
								</td>
								<td width="120">
									<input name="orgName" type="text"
										class="NFText" size="20" id="code_txt"/>
								</td>
								<td>
									Organization Group
								</td>
								<td width="120">
                                    <s:select list="searchGroupList" listKey="id" listValue="name" name="orgGroupId" value="" cssStyle="width:160px" headerKey="" headerValue="All"/>
								</td>
								<td width="100">
									<input type="submit" name="Submit5" value="Search"
										class="search_input" />
								</td>

							</tr>
						</table>
					</div>
				</div>
				<div class="input_box"  style="height:350px;" >
					<div class="content_box">
						<iframe id="srch_workCenter_iframe" name="result_iframe"
							src="organization!list.action?filter_EQS_activeFlag=Y" width="100%"
							height="630" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
				<div class="button_box">
					<input type="button" name="Submit52" id="new_btn"
						value="New" class="search_input" />
				</div>
			</div>
		</form>
		<div id="delReasonDlg"/>
	</body>
</html>