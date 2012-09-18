<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Resources</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript">
	
            $(function() {
	
		          var count = $("#status_sel").get(0).options.length;        
			      for(var i=0;i<count;i++) {
			          if($("#status_sel").get(0).options[i].value == '${param.srcStatus}')
			          {
			              $("#status_sel").get(0).remove(i);
			              break;
			          }
			      }  

				
				  //绑定保存按钮事件.            
                  $("#save_btn").click (function() {
                       $('#save_btn').attr("disabled", true);
	                   var index = $("#status_sel").get(0).selectedIndex;
	                   parent.$("#status_txt").val($("#status_sel").get(0).options[index].text);
	                   

	                   parent.$("#status_hid").val($("#status_sel").val());
	                   
	                   parent.$("#reason_hid").val($("#reason_area").val());	                   
                       parent.$('#note_dlg').dialog('close');	
           
                   });//end of {$("#save_btn").click};               
            
            });
        </script>
	</head>
	<body style="overflow: hidden" id="input_body">
		<form method="get" id="div_form">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id="edit_tbl" align="center">
				<tr>
					<td height="32">
						<div align="left" style="padding-left: 95px;">
							Status
							<select name="select" id="status_sel">
								<option value="Y">
									ACTIVE
								</option>
								<option value="N">
									INACTIVE
								</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td height="32" valign="top">
						<div align="left" style="padding-left: 95px">
							Choose the reason to update the status:
						</div>
					</td>
				</tr>
				<tr>
					<th valign="top">
						<textarea name="textarea3" cols="70" rows="2"
							class="content_textarea" id="reason_area"></textarea>
					</th>
				</tr>
				<tr>
					<td height="100" align="center">
						<input type="submit" name="Submit4" value="Modify"
							class="style_botton" id="save_btn"/>
						<input type="submit" name="Submit3" value="Cancel"
							class="style_botton" onclick="parent.$('#note_dlg').dialog('close');" />
					</td>
				</tr>

			</table>
		</form>
	</body>
</html>