<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Department Edit</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}tab-view.js"></script>

		<script type="text/javascript">
            $(function() {		                                       
                $('#tabTabdhtmlgoodies_tabView1_1').click( function () {
						if($('#accountFrame').attr('src') == undefined || $('#accountFrame').attr('src') == '')
						{
							$('#accountFrame').attr('src', "department!showAccount.action?id=${param.deptId}");
						}
				});            
            });
        </script>
	</head>
	<body>
		<div id="dhtmlgoodies_tabView1">
			<div class="dhtmlgoodies_aTab">
				<iframe id="contactIframe"
					src="department!showContact.action?id=${param.deptId}" width="100%"
					height="320" frameborder="0" scrolling="no"></iframe>
			</div>
			<div class="dhtmlgoodies_aTab">
				<iframe id="accountFrame" name="billingFrame" src="" width="100%"
					height="320" frameborder="0" scrolling="no"></iframe>
			</div>
			<div class="dhtmlgoodies_aTab">
<table width="942" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div style="margin-right: 12px;">
									<table width="922" border="0" cellspacing="0" cellpadding="0"
										class="list_table2">
										<tr>
											<th width="145">
												Customer No
											</th>
											<th width="97">
												Status
											</th>
											<th width="187">
												Name
											</th>
											<th width="153">
												Email
											</th>
											<th width="159">
												Office/Lab
											</th>
											<th>
												Phone
											</th>
										</tr>
									</table>
									<div style="width: 940px; height: 260px; overflow: scroll;">

									</div>
							</td>
						</tr>
					</table>
			</div>
		</div>

		<script type="text/javascript"> 
	      initTabs('dhtmlgoodies_tabView1',Array('Contacts','Accounts','Others'),0,972,324);
		  disableTabByTitle('Purchase Preferences');
          var isSaved = false;
		  window.onbeforeunload = function() {
			 if(isSaved === false){
					//return 'Do you want to leave without saving data?';
			 }
		  }
        </script>
	</body>
</html>