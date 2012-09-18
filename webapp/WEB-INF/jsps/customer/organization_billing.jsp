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
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
		<script type="text/javascript"
        src="${global_js_url}scm/gsCountryState.js?v=1"></script>
        <s:if test='billing.accountCountry!=""'>
    <script>
        var countryIdNames = ['country'];
        var countryDefaults = ['${billing.accountCountry}'];
        var countryChangeHandlers = [''];

        var stateIdNames = ['state'];
        var stateDefaults = [''];
        var stateChangeHandlers = [''];
        $(document).ready(function() {
            initCountry();
            document.getElementById("state").value = '${billing.accountState}';//stateDefaults;
        });
    </script>
</s:if>
<s:else>
    <script>
        var countryIdNames = ['country'];
        var countryDefaults = ['US'];
        var countryChangeHandlers = [''];

        var stateIdNames = ['state'];
        var stateDefaults = [''];
        var stateChangeHandlers = [''];
        $(document).ready(function() {
            initCountry();
            document.getElementById("state").value = stateDefaults;
        });
    </script>

</s:else>
		<script type="text/javascript">
		   var baseUrl="${global_url}";
		   
		   function getMyChanged() {
		     alert($("#changed_hid").val());
		   }
		   
           $(function() {            
	               $("input[type=text]").change(function() {
	                  $("#changed_hid").val("Y");
	               })
	               $("select").change(function() {
	                  $("#changed_hid").val("Y");
	               })        
	               $("#bank_btn").click (function() {
		                var url = "organization!showSeclectBank.action";
						parent.$('#note_dlg').dialog("option", "open", function() {	
		              		 var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
					         parent.$('#note_dlg').html(htmlStr);
						});
						parent.$('#note_dlg').dialog('option', 'height', "390");
						parent.$('#note_dlg').dialog('option', 'title', "Select Bank");      
						parent.$('#note_dlg').dialog('open');  
	               
	               });  
            });
        </script>
	</head>
	<body id="input_body">
		<form method="get" id="billing_form">
			<input type="hidden" id="changed_hid" value="N" />
			<input type="hidden" name="billing.id" value="${billing.id}" />
			<input type="hidden" name="billing.orgId" value="${billing.orgId}" />
			<table width="950" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="138">
									Contact Name
								</th>
								<td width="385">
									<input name="billing.contactName" value="${billing.contactName}" type="text" class="NFText" size="35" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<div class="invoice_title" style="padding-top: 0px;">
							<span style="cursor: pointer"
								onclick="toggle_showmore('Credit_InfoItem', 'Credit_Info');"><img
									src="${global_image_url}/ad.gif" width="11" height="11"
									id="Credit_InfoItem" />&nbsp;Credit Card Information</span>
						</div>
						<div id="Credit_Info" style="display: block;">
							<table border="0" cellpadding="0" cellspacing="0"
								class="General_table">
								<tr>
									<th width="138">
										Credit Card
									</th>
									<td width="385">
									<s:select name="billing.creditCard"
											list="#{'EXPRESS':'American Express', 'DISCOVER':'Discover', 'MASTER':'Master', 'VISA':'VISA'}" cssStyle="width:207px;"
											value="billing.creditCard"></s:select>
									</td>
									<th width="138">
										Card Number
									</th>
									<td width="187">
										<input name="billing.number" value="${billing.number}" type="text" class="NFText"
											size="35" />
									</td>
								</tr>
								<tr>
									<th>
										Expiration Date
									</th>
									<td style="margin: 0px; padding: 0px;">
										<s:select name="billing.expMonth"
											list="{'01','02','03','04','05','06','07','08','09',10,11,12}" cssStyle="width:100px;"
											value="billing.expMonth" headerKey=" " headerValue=" "></s:select>
										<s:select name="billing.expYear" 
											list="{2011,2012,2013,2014,2015,2016,2017,2018,2019,2020}" cssStyle="width:102px;"
											value="billing.expYear" headerKey=" " headerValue=" "></s:select>
									</td>
									<th>
										&nbsp;
									</th>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</div>
						<div class="invoice_title">
							<span style="cursor: pointer"
								onclick="toggle_showmore('Account_InfoItem', 'Account_Info');"><img
									src="${global_image_url}/ar.gif" width="11" height="11"
									id="Account_InfoItem" />&nbsp;Bank Account Information</span>
						</div>
						<table id="Account_Info" border="0" cellpadding="0"
							cellspacing="0" class="General_table" style="display: none;">
							<tr>
								<th width="138">
									Account Usage
								</th>
								<td width="385">
									<s:select name="billing.accountUsage"
											list="#{'BOTH':'Both', 'DEBIT':'Direct Debit', 'DEPOSIT':'Direct Deposit', '':'None'}" cssStyle="width:207px;"
											value="billing.accountUsage"></s:select>
								</td>
								<th width="138">
									Bank Account Type
								</th>
								<td width="187">
									<s:select name="billing.bankAccountType"
											list="{'Checking', 'Savings'}" cssStyle="width:207px;"
											value="billing.bankAccountType"></s:select>
								</td>
							</tr>
							<tr>
								<th>
									Bank
								</th>
								<td>
									<input name="billing.bank" value="${billing.bank}" type="text"
										class="NFText" size="35" id="bank_txt" />
									<img src="images/search.gif" width="16" height="16"
										align="middle" id="bank_btn" style="cursor: pointer" />
								</td>
								<th>
									Account No
								</th>
								<td>
									<input name="billing.accountNo" value="${billing.accountNo}" type="text" class="NFText" size="35" />
								</td>
							</tr>
							<tr>
								<th>
									Routing No
								</th>
								<td>
									<input name="billing.routingNo" value="${billing.routingNo}" type="text" class="NFText" size="35" />
								</td>
								<th>
									IBAN
								</th>
								<td>
									<input name="billing.iban" value="${billing.iban}" type="text" class="NFText" size="35" />
								</td>
							</tr>
							<tr>
								<th>
									BBAN
								</th>
								<td>
									<input name="billing.bban" value="${billing.bban}" type="text" class="NFText" size="35" />
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
						<div class="invoice_title">
							<span style="cursor: pointer"
								onclick="toggle_showmore('Refer_InfoItem', 'Refer_Info');"><img
									src="${global_image_url}/ar.gif" width="11" height="11"
									id="Refer_InfoItem" />&nbsp;Reference</span>
						</div>
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table" id="Refer_Info" style="display: none;">
							<tr>
								<th width="138">
									Account Name
								<br /></th>
								<td width="385">
									<input name="billing.accountName" value="${billing.accountName}" type="text" class="NFText" size="35" />
								<br /></td>
								<th width="138">
									Account Street
								<br /></th>
								<td>
									<input name="billing.accountStreet" value="${billing.accountStreet}" type="text" class="NFText"
										size="35" />
								<br /></td>
							</tr>
							<tr>
								<th>
									Account City
								<br /></th>
								<td>
									<input name="billing.accountCity" value="${billing.accountCity}" type="text" class="NFText"
										size="35" />
								<br /></td>
								<th>
									Account Zip/Postal
								<br /></th>
								<td>
									<input name="billing.accountZip" value="${billing.accountZip}" type="text" class="NFText"
										size="35" />
								<br /></td>
							</tr>
							<tr>
								<th>
									Account State
								<br /></th>
								<td>
									<select name="billing.accountState" id="state"></select>
								<br /></td>
								<th>
									Account Country
								<br /></th>
								<td>
								<select name="billing.accountCountry" id="country">
                    			</select>
								<br /></td>
							</tr>
							<tr>
								<th>
									Driver License
								<br /></th>
								<td>
									<input name="billing.driveLicense" value="${billing.driveLicense}" type="text" class="NFText" size="35" />
								<br /></td>
								<th>
									Social Security No
								<br /></th>
								<td>
									<input name="billing.socialSecurityNo" value="${billing.socialSecurityNo}" type="text" class="NFText" size="35" />
								<br /></td>
							</tr>
							<tr>
								<th>
									Account Email
								<br /></th>
								<td>
									<input name="billing.accountEmail" value="${billing.accountEmail}" type="text" class="NFText" size="35" />
								<br /></td>
								<td>
									&nbsp;
								<br /></td>
								<td>
									&nbsp;
								<br /></td>
							</tr>
							<tr>
								<th>
									Address verified
								<br /></th>
								<td>
									<s:select name="billing.addressVerified"
											list="#{'NoMatch':'No Match', 'Unavailable':'Unavailable', 'Match':'Match'}" cssStyle="width:207px;"
											value="billing.addressVerified"></s:select>
								<br /></td>
								<td>
									&nbsp;
								<br /></td>
								<td>&nbsp; 
								<br /></td>
							</tr>
						</table>

					</td>
				</tr>
			</table>
		</form>
	</body>
</html>