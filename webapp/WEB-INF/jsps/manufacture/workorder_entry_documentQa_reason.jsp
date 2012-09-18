<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Quality Control</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>	
		<script type="text/javascript">
		
 $(function() {	               
  
     
     //Save按钮处理
     $("#save_btn").click( function() {
            var str = $("#reason").val();
            parent.$('#documentQaReason').val(str);
            window.parent.$('#document_qa_dlg').dialog('close');
     });
 });//end of $(function() {}...

    
        </script>
	</head>
	<body>
		<table width="420" border="0" cellpadding="0" cellspacing="0"
			class="General_table" style="margin: 10px auto 0px auto;">
			<tr>
				<th width="188">
					&nbsp;
				</th>
				<th width="222">
					&nbsp;
				</th>
			</tr>
			<tr>
				<th colspan="3">
					<div align="left">
						Choose the reason to reject the production documents:
					</div>
				</th>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<textarea name="textarea3" class="content_textarea2" id="reason"></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="3">
					&nbsp;
				</th>
			</tr>
			<tr>
				<td colspan="3" valign="top">
					<div class="botton_box">
						<br />
						<input name="Submit22" type="submit" class="style_botton"
							value="Save" id="save_btn" />
						<input type="button" name="close2" value="Cancel"
							class="style_botton" onclick="window.parent.$('#document_qa_dlg').dialog('close');" />
						<br />
						<br />
					</div>
					<br />
					<br />
				</td>
			</tr>
		</table>
	</body>
</html>