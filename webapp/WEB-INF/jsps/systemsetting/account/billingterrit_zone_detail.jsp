<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SCM</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
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
<script src="${global_js_url}jquery/jquery.validate.js"
	type="text/javascript"></script>
<script src="${global_js_url}scm/gsCountryState.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript">
	var countryIdNames = [ 'country' ];
	var countryDefaults = [ '${billTerritoryZoneDTO.country != null?billTerritoryZoneDTO.country:"US"}' ];
	var countryChangeHandlers = [ '' ];
	var stateIdNames = [ 'state' ];
	var stateDefaults = [ '${billTerritoryZoneDTO.state}' ];
	var stateChangeHandlers = [ '' ];
	$(document).ready(function() {
		$('tr:odd >td').addClass('list_td2');
		$('.ui-datepicker').each(function() {
			$(this).datepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});
		initCountry();
		
		// validate territory form on keyup and submit
	    $("#zoneform").validate({
			errorClass:"validate_error",
			highlight: function(element, errorClass) {
				$(element).addClass(errorClass);
		    },
		    unhighlight: function(element, errorClass, validClass) {
		        $(element).removeClass(errorClass);
		    },
			invalidHandler: function(form, validator) {
		        $.each(validator.invalid, function(key,value){
		            alert(value);
		            return false;
		        });
			},
			rules: {
				'billTerritoryZoneDTO.country':{required:true},
				'billTerritoryZoneDTO.state':{required:true}
			},
			messages: {
				'billTerritoryZoneDTO.country':{required:"Please select the Country Name."},
				'billTerritoryZoneDTO.state':{required:"Please enter the State/Province."}
			},
			errorPlacement: function(error, element) {
			}			
		});
		
		$('#saveZoneBtn').click(function () {
			if ($("#zoneform").valid == false) {
				return false;
			}
			var country = $("#country").val();
			if (country == undefined || country == "") {
				alert("Please select the Country Name.");
				return false;
			}
			var state = $("#state").val();
			if (state == undefined || state == "") {
				alert("Please enter the State/Province.");
				return false;
			}
			$("#zoneform").submit();
		});
		
		var rtnMessage = '${rtnMessage}';
		if (rtnMessage != undefined && rtnMessage != null && rtnMessage != '') {
			alert(rtnMessage);
			window.history.go(-2);
		}
	});
</script>
</head>
<body class="content">
	<div class="scm">
		<div class="title_content">
			<div class="title">
				Zone
				<s:if
					test="billTerritoryZoneDTO != null && billTerritoryZoneDTO.zoneId != null">${billTerritoryZoneDTO.zoneId}</s:if>
			</div>
		</div>
		<div class="input_box">
			<div class="content_box">
				<form id="zoneform" action="billing_territ!saveZone.action"
					method="post">
					<input type="hidden" name="sessTerritoryId"
						value="${sessTerritoryId}" />
					<s:if
						test="billTerritoryZoneDTO != null && billTerritoryZoneDTO.sessZoneId != null">
						<input type="hidden" name="billTerritoryZoneDTO.sessZoneId"
							value="${billTerritoryZoneDTO.sessZoneId}" />	
					</s:if>
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<th width="187">Zone ID</th>

							<td width="275"><input type="text" class="NFText"
								value="${billTerritoryZoneDTO.zoneId}" size="20" readonly="readonly" />
							</td>
							<th width="151">Continent</th>
							<td width="428"><s:select cssStyle="width:192px"
									name="billTerritoryZoneDTO.continent" list="continentList"
									listKey="continent" listValue="continent"
									value="billTerritoryZoneDTO.continent"></s:select></td>
						</tr>
						<tr>
							<th valign="top"><span class="important">*</span>Country Name</th>
							<td><select name="billTerritoryZoneDTO.country" id="country"
								style="width: 192px">
									<option value="">United States</option>
							</select></td>
							<th><span class="important">*</span>State/Province</th>
							<td><input name="billTerritoryZoneDTO.state" type="text"
								class="NFText" value="${billTerritoryZoneDTO.state}" id="state"
								size="32" /></td>
						</tr>
						<tr>
							<th valign="top">From Zip</th>

							<td><input name="billTerritoryZoneDTO.zipFrom" type="text"
								class="NFText" size="20" value="${billTerritoryZoneDTO.zipFrom}" />
							</td>
							<th>To Zip</th>
							<td><input name="billTerritoryZoneDTO.zipTo" type="text"
								class="NFText" size="20" value="${billTerritoryZoneDTO.zipTo}" />
							</td>
						</tr>
						<tr>
							<th>Modified Date</th>
							<td><input type="text" class="NFText"
								value="<s:date name="billTerritoryZoneDTO.modifyDate" format="yyyy-MM-dd"/>"
								size="20" readonly="readonly" /></td>

							<th>Modified By</th>
							<td><input type="text" class="NFText"
								value="${billTerritoryZoneDTO.modifiedByUser}" size="20"
								readonly="readonly" /></td>
						</tr>
					</table>
					<div class="button_box">
						<input type="button" value="Save" class="search_input" id="saveZoneBtn"/> <input
							type="button" value="Cancel" class="search_input"
							onclick="window.history.go(-1)" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
