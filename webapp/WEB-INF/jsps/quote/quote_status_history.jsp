<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Quote Management</title>
		<base id="myBaseId" href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>

		<style type="text/css">
<!--
body {
	width: 520px;
	margin: 10px auto 0px auto;
}
-->  
</style>
	</head>

	<body>
		<table width="500" border="0" cellpadding="0" cellspacing="0"
			class="list_table">
			<tr>
				<th width="27">&nbsp; 
				</th>
				<th width="113">
					Status
				</th>
				<th width="157">
					Reason
				</th>
				<th width="80">
					Update Date
				</th>
				<th>
					Update By
				</th>
			</tr>
		</table>
		<div style="width: 517px; height: 150px; overflow: scroll;">
			<table width="500" border="0" cellpadding="0" cellspacing="0"
				class="list_table">
              <s:iterator value="quoteStatusList" status="key" >
				<tr>
					<td width="27">
						<div align="center">
							${key.index + 1}
						</div>
					</td>
					<td width="113">
						&nbsp;${currentStatName}
					</td>
					<td width="157">
						&nbsp;${reason}
					</td>
					<td width="80">
						<div align="center">
							&nbsp;<s:date name="processDate" format="yyyy-MM-dd" />
						</div>
					</td>
					<td>
						&nbsp;${updateBy}
					</td>
				</tr>
			  </s:iterator>
			</table>
		</div>
		<table width="100%" border="0" height="50" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center">
					<input type="button" name="closeHistoryButton"
						id="closeHistoryButton" value="Close" class="style_botton" />
				</td>
			</tr>
		</table>
	<script language="javascript"> 
		$(function(){
			$('#closeHistoryButton').click (
				function() {
					window.parent.$( '#viewStatusHistoryDialog' ).dialog( 'close' )  ;
				}
			);	
		}) ;
</script>
	</body>
</html>
