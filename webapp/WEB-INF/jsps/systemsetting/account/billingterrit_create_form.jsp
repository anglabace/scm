<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SCM</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.validate.js" ></script>
<script src="${global_js_url}scm/gsCountryState.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
var countryIdNames = ['country'];
var countryDefaults = ['${billingTerritDTO.country != null?billingTerritDTO.country:"US"}'];
var countryChangeHandlers = [''];
var stateIdNames = ['state'];
var stateDefaults = ['${billingTerritDTO.state}'];
var stateChangeHandlers = ['']; 
$(document).ready(function(){  
    $('tr:odd >td').addClass('list_td2');
    $('.ui-datepicker').each(function(){
		$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
				});
	});
    initCountry();
  
 
    $('#saveAllTrigger').click(function() {
    	if ($('#territoryform').valid() == false || $('#territBillingForm').valid() == false) {
    		return false;
    	}
    	var state = $('#state').val();
    	if (state == undefined || state == "") {
    		alert("Please enter/select the State.");
    		return false;
    	}
    	var country = $('#country').val();
    	if (country == undefined || country == "") {
    		alert("Please select the Country.");
    		return false;
    	}
    	var territoryId = $("#territoryId").val();
		var formStr = "";
		formStr += $('#territoryform').serialize() + "&";
		formStr += $('#territBillingForm').serialize();
		$.ajax({
			type : "POST",
			url : "billing_territ!save.action",
			data : formStr,
			dataType : 'json',
			success : function(data, textStatus) {
				if (hasException(data)) {
					$('#saveAllTrigger').attr(
							"disabled", false);
				} else {
					if (data.message != undefined && data.message != "") {
						alert(data.message);
						return;
					} else {
						alert("Save successful.");
						location.href = "billing_territ!searchForm.action";
					}
				}
			},
			error : function(xhr, textStatus) {
				alert("System error! Please contact system administrator for help.");
				$('#saveAllTrigger').attr("disabled",
						false);
				return;
			}
		});
	});
    
 	// validate territory form on keyup and submit
    $("#territoryform").validate({
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
			'billingTerritDTO.territoryCode':{required:true},
			'billingTerritDTO.territoryName':{required:true},
			'billingTerritDTO.accountCode':{required:true}
			
		},
		messages: {
			'billingTerritDTO.territoryCode':{required:"Please enter the Billing Territory Code."},
			'billingTerritDTO.territoryName':{required:"Please enter the Billing Territory Name."},
			'billingTerritDTO.accountCode':{required:"Please select the Billing Account Code."}
		},
		errorPlacement: function(error, element) {
		}			
	});
	
	// validate territBilling form on keyup and submit
    $("#territBillingForm").validate({
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
			'billingTerritDTO.zipCode':{required:true},
			'billingTerritDTO.state':{required:true},
			'billingTerritDTO.country':{required:true}
			
		},
		messages: {
			'billingTerritDTO.zipCode':{required:"Please enter the Zip Code."},
			'billingTerritDTO.state':{required:"Please enter/select the State."},
			'billingTerritDTO.country':{required:"Please select the Country."}
		},
		errorPlacement: function(error, element) {
		}			
	});
});
</script>
</head>
<body class="content" >
<div class="scm">
<div class="title_content">
<div class="title"><s:if test="operation_method != 'edit'">Bill Territory - </s:if><s:else>Bill Territory - ${billingTerritDTO.territoryName}</s:else></div>
</div>
<div class="input_box">
<form id="territoryform">
<input type="hidden" id="territoryId" name="sessTerritoryId" value="${sessTerritoryId}" />
<input type="hidden" id="operation_method" name="operation_method" value="${operation_method}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="140"><span class="important">*</span>Billing Territory Code </th>
      <td width="424">
      <input id="territoryCode" name="billingTerritDTO.territoryCode" type="text" class="NFText" value="${billingTerritDTO.territoryCode}" size="20" <s:if test="billingTerritDTO != null && billingTerritDTO.territoryId != null">readonly="readonly"</s:if> /></td>
      <th width="92"><span class="important">*</span>Billing Territory Name</th>
      <td width="346">
        <input id="territoryName" name="billingTerritDTO.territoryName" type="text" class="NFText" value="${billingTerritDTO.territoryName}" size="20" /> 
      </td>
    </tr>
	<tr>
	  <th valign="top">Description</th>
	  <td>
	    <textarea name="billingTerritDTO.description" class="content_textarea2">${billingTerritDTO.description}</textarea>
	    </td>
	  <th>Status</th>
	  <td valign="top">
	  	<select name="billingTerritDTO.status" id="status" style="width:131px">
	  		<option value="ACTIVE" <s:if test="billingTerritDTO.status == 'ACTIVE'">selected="selected"</s:if>>ACTIVE</option>
	  		<option value="INACTIVE" <s:if test="billingTerritDTO.status == 'INACTIVE'">selected="selected"</s:if>>INACTIVE</option>
	  	</select>
	  </td>
	</tr>
	<tr>
	  <th valign="top"><span class="important">*</span>Billing Account Code</th>
	  <td colspan="3">
	    <select name="billingTerritDTO.accountCode" id="accountCode" style="width:131px">
	  		<option value="US" <s:if test="billingTerritDTO.accountCode == 'US'">selected="selected"</s:if>>US</option>
	  		<option value="HK" <s:if test="billingTerritDTO.accountCode == 'HK'">selected="selected"</s:if>>HK</option>
	  	</select>
	  </td>
	</tr>
    <tr>
      <th> Modified Date</th>
      <td><input type="text" class="NFText" value="<s:date name="billingTerritDTO.modifyDate" format="yyyy-MM-dd"/>" size="20" readonly="readonly" /></td>

      <th>Modified By </th>
      <td><input type="text" class="NFText" value="${billingTerritDTO.modifiedByUser}" size="20" readonly="readonly"/></td>
    </tr>
  </table>
</form>
</div>

  <div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
		<iframe id="zone_frame" name="zone_frame" src="billing_territ!listZone.action?sessTerritoryId=${sessTerritoryId}" height="390" width="980" frameborder="0"  scrolling="no"></iframe>
    </div>
    <div class="dhtmlgoodies_aTab">
	<div class="general_box" style="height:370px;">
	<form id="territBillingForm">
    <table border="0" cellpadding="0" cellspacing="0" class="General_table">
    	<tr>
          <th colspan="2" width="173">&nbsp;</th>
          <td width="192" valign="top">&nbsp;</td>
          <td width="139" valign="top">&nbsp;</td>
          <th>&nbsp;</th>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr>
          <th colspan="2" width="173">Account Usage</th>
          <td width="192" valign="top">
			<s:select cssStyle="width:192px" name="billingTerritDTO.accountUsage" list="dropDownMap['BILL_TERRITORY_ACCOUNT_USAGE']" listKey="value" listValue="value" value="billingTerritDTO.accountUsage" ></s:select>
          </td>
          <td width="139" valign="top">&nbsp;</td>
          <th>Bank Account Type</th>
          <td valign="top">
			<s:select cssStyle="width:192px" name="billingTerritDTO.accountType" list="dropDownMap['BILL_TERRITORY_ACCOUNT_TYPE']" listKey="value" listValue="value" value="billingTerritDTO.accountType" ></s:select>
         </td>
        </tr>
        <tr>
          <th colspan="2" valign="top">Bank</th>
          <td width="192" valign="top">
          	<s:if test="billingTerritDTO.bankList != null">
				<s:select cssStyle="width:192px" name="billingTerritDTO.bank" list="billingTerritDTO.bankList" listKey="bankName" listValue="bankName" value="billingTerritDTO.bank"></s:select>
		  	</s:if>
		  	<s:else>
		  		<select name="billingTerritDTO.bank" style="width:192px">
		  			<option>Please select</option>
		  		</select>
		  	</s:else>
		  </td>
		  <td width="139" valign="top">&nbsp;</td>
          <th>Account No</th>
          <td valign="top"><input name="billingTerritDTO.accountNo" type="text" class="NFText" size="32" value="${billingTerritDTO.accountNo}" /></td>
        </tr>
        <tr>
          <th colspan="2">Routing No</th>
          <td width="192" valign="top"><input name="billingTerritDTO.routingNo" type="text" class="NFText" size="32" id="routingNo" value="${billingTerritDTO.routingNo}"/></td>
          <td width="139" valign="top">&nbsp;</td>
          <th>IBAN</th>
          <td valign="top"> 
			<input name="billingTerritDTO.iban" type="text" class="NFText" size="32" id="iban" value="${billingTerritDTO.iban}"/>
          </td>
        </tr>
        <tr>
          <th colspan="2">BBAN</th>
          <td width="192" valign="top">
			<input name="billingTerritDTO.bban" type="text" class="NFText" size="32" id="bban" value="${billingTerritDTO.bban}"/>
		  </td>
		  <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <th colspan="2">Phone</th>
          <td width="192" valign="top">
			<input name="billingTerritDTO.phone" type="text" class="NFText" size="12" id="phone" value="${billingTerritDTO.phone}"/>
		   &nbsp;&nbsp;Ext <input name="billingTerritDTO.phoneExt" type="text" class="NFText" size="7" id="phoneExt" value="${billingTerritDTO.phoneExt}"/>
          </td>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <th colspan="2">Address</th>
          <td width="192" valign="top">
			<input name="billingTerritDTO.addrLine1" type="text" class="NFText" size="32" id="addrLine1" value="${billingTerritDTO.addrLine1}"/>
		  </td>
		  <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <th colspan="2">&nbsp;</th>
          <td width="192" valign="top">
			<input name="billingTerritDTO.addrLine2" type="text" class="NFText" size="32" id="addrLine2" value="${billingTerritDTO.addrLine2}"/>
		  </td>
		  <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <th colspan="2">&nbsp;</th>
          <td width="192" valign="top">
			<input name="billingTerritDTO.addrLine3" type="text" class="NFText" size="32" id="addrLine3" value="${billingTerritDTO.addrLine3}"/>
		  </td>
		  <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <th colspan="2">City</th>
          <td width="192" valign="top"><input name="billingTerritDTO.city" type="text" class="NFText" size="32" id="city" value="${billingTerritDTO.city}"/></td>
          <td width="139" valign="top">&nbsp;</td>
          <th><span class="important">*</span>Zip Code</th>
          <td valign="top"> 
			<input name="billingTerritDTO.zipCode" type="text" class="NFText" size="32" id="zipCode" value="${billingTerritDTO.zipCode}"/>
          </td>
        </tr>
        <tr>
          <th colspan="2"><span class="important">*</span>State</th>
          <td width="192" valign="top">
          	<input name="billingTerritDTO.state" type="text" class="NFText" 
          		value="${billingTerritDTO.state}" id="state" size="32" />
          </td>	
          <td width="139" valign="top">&nbsp;</td>
          <th><span class="important">*</span>Country</th>
          <td valign="top"> 
          	<select name="billingTerritDTO.country" id="country" style="width:192px">
				 <option value="">United States</option>
			</select>
		  </td>
        </tr>
      </table>
	</form>
      </div>
    </div>
  </div>
</div>
<div class="button_box">
<saveButton:saveBtn parameter="${operation_method}"
	disabledBtn='<input type="button" id="saveAllTrigger" value="Save" class="search_input" disabled="disabled" />'
	saveBtn='<input type="button" id="saveAllTrigger" value="Save" class="search_input" />'
/> 
  <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)" />
</div>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Zones','Billing Info'), 0, 998, 395);
</script>
</body>

</html>
 