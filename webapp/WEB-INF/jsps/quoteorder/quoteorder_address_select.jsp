<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js"
	type="text/javascript"></script>
<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">
var baseUrl = '${global_url}';
</script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/gsCountryState.js?v=14"></script>
<script type="text/javascript">
$().ready(function() {
	jQuery.validator.addMethod("isPhone", function(value, element, param) {
		  var tmpVal = value.replace(/ /g,"");
		  return this.optional(element) || /^([\d-])*$/.test(tmpVal);       
		}, "Please enter a valid phone");
	
	// validate signup form on keyup and submit
	$("#selectAddrForm").validate({
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
			firstName: { required:true,  maxlength:50 },
			lastName: { required:true, maxlength:50 },
			midName: { maxlength:50 },
			title: { maxlength:50 }, 
			busPhone: { required:true , maxlength:20},
			addrLine1: {required:true, maxlength:50 },
			addrLine2: {maxlength:50 },
			addrLine3: {maxlength:50 },
			city: {required:true, maxlength:50},
			orgName: { required:true/*  , maxlength:50 */},
			busEmail: {required:true, /* email:true, */ maxlength:50},
			state: {required:true, maxlength:50},
			fax:{maxlength:20},
			zipCode: {required:true, maxlength:10},
			country: {required:true}
		},
		messages: {
			firstName: {required:"Please enter your firstname",  maxlength:"The first Name should be no more than 50 characters."},
			lastName:  {required:"Please enter your lastname",   maxlength:"The lastName should be no more than 50 characters."},
			midName: { maxlength:"The midName should be no more than 50 characters." },
			title: { maxlength:"The title should be no more than 50 characters." }, 
			busPhone: {required:"Please enter your phone number", maxlength:"The phone number should be no more than 20 characters." },
			addrLine1: {required:"Address is required", maxlength:"The Address1 should be no more than 50 characters."},
			addrLine2: { maxlength:"The Address2 should be no more than 50 characters."},
			addrLine3: { maxlength:"The Address3 should be no more than 50 characters."},
			city: {required:"City is required", maxlength:"The city should be no more than 50 characters."},
			orgName: {required:"Please select an organization"/* , maxlength:"The organization should be no more than 50 characters." */}, 
			busEmail: {required:"Please enter your email"/* ,email:"Please enter a valid email address."  */},
			state: {required:"State is required", maxlength:"The phone number should be no more than 50 characters."},
			fax:{maxlength:"The fax should be no more than 20 characters."},
			country: "Country is required",
			zipCode: {required:"Zip is required",  maxlength:"The phone number should be no more than 10 characters."}
				
		},
		errorPlacement: function(error, element) {
		}
				
	});

	$('#orgAddDialogTrigger').click(function(){
		dataHolderWin.jQuery.data(dataHolderWin.document.body, 'disableNew', 1);
		dataHolderWin.$('#orgDialogWindow').dialog('open');
		dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgLoc', self);
		dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgNameStr', 'orgName');
	}); 
	
 
	init();//初始化页面
});


var countryIdNames = ['country'];
var countryDefaults = ['US'];
var countryChangeHandlers = [''];

var stateIdNames = ['state'];
var stateDefaults = [''];
var stateChangeHandlers = [''];

function set_selected(id, val) {
	if(!document.getElementById(id))
	return false;
	var obj = document.getElementById(id);
	for ( var i = 0; i < obj.options.length; i++)
		if (obj.options[i].value == val)
			obj.options[i].selected = true;
}

function initAddress(addrId){
	var addrInfo = null;
	$.ajax({
		url:"qu_order_address!getCustAddr.action",
		data:"addrId="+addrId,
		dataType:"json",
		success:function(data){
			addrInfo = data;
		},
		error:function(data){
			if(data){
				alert(data);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		async:false
	});
	set_selected("namePfx", ''+addrInfo.namePfx);
	$("#firstName").val(addrInfo.firstName);
	$("#lastName").val(addrInfo.lastName);
	$("#midName").val(addrInfo.midName);
	set_selected("nameSfx", ''+addrInfo.nameSfx);
	$("#title").val(addrInfo.title); 
	$("#orgName").val(addrInfo.orgName);
	$("#busEmail").val(addrInfo.busEmail);
	$("#busPhone").val(addrInfo.busPhone);
	$("#busPhoneExt").val(addrInfo.busPhoneExt);
	$("#altPhone").val(addrInfo.altPhoneExt);
	$("#fax").val(addrInfo.fax);
	$("#faxExt").val(addrInfo.faxExt);
	$("#addrLine1").val(addrInfo.addrLine1);
	$("#addrLine2").val(addrInfo.addrLine2);
	$("#addrLine3").val(addrInfo.addrLine3);
	$("#city").val(addrInfo.city);
	$("#zipCode").val(addrInfo.zipCode); 
	$("[value='"+addrInfo.addrClass+"']").attr("checked", true);
	countryDefaults = [''+addrInfo.country];
	stateDefaults = [''+addrInfo.state];
	initCountry();
	
	$(":radio[title='"+addrInfo.addrId+"']").attr("checked", true);
}

function selectAddress(){
	if($('#selectAddrForm').valid() == false){
		return false; 
	}
    var bussemail=$("#busEmail").val(); 
    var strs=bussemail.split("@"); 
    if(strs.length>=3){
    	alert("You can input only one email.");
    	return false;
    }else{
    	var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
    	   if(!search_str.test(bussemail)){
    	        alert('Please enter a valid email address');
    	        return false;
    	       } 
    }
    
	var zipCode = $("#zipCode").val();
	var country = $("#country").val(); 
	var state=$("#state").val();
	if (country == "US" && state == "CA") {
		if (zipCode != null && zipCode != "") { 
			if (!(parseInt(zipCode) >= 90000
					&& parseInt(zipCode) <= 97000)) {
				alert("Please Enter the zip code scope about the 90000 to 97000 . ");
				return false;
			}
		}
	}
	var itemIds = [];
	parent.$("#itemListIframe").contents().find("[name='itemId']").each(function(i, n){
		if($(n).attr("checked") == true){
			if(itemIds.length == 0){
				itemIds.push("?itemIds="+$(n).val());
			}else{
				itemIds.push("&itemIds="+$(n).val());
			}
		}
	});
	var itemIdParam = itemIds.join("");
	var form = $("#selectAddrForm");

	//alert(itemIdParam);
	//ajax form post
	var options = {
		success:function(data){
			if(data == 'main'){
				cancelSelectAddr();
				//refresh
				parent.document.getElementById('addrIframe').src = parent.document.getElementById('addrIframe').src;
				parent.document.getElementById('multiAddrIframe').src = parent.document.getElementById('multiAddrIframe').src;
				window.parent.frames["totalIframe"].clearTotalAll();
			}else{
				if(data)alert(data);
				cancelSelectAddr();
				//refresh
				parent.document.getElementById('addrIframe').src = parent.document.getElementById('addrIframe').src;
				parent.document.getElementById('multiAddrIframe').src = parent.document.getElementById('multiAddrIframe').src;
			}
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		resetForm:false,
		url:"qu_order_address!saveAddress.action"+itemIdParam,
		type:"POST",
		async:false
	};
	//alert(options);
	form.ajaxForm(options);
	form.submit();
}

function cancelSelectAddr(){
	if(parent.$('#changeAddressDialog').dialog('isOpen') == true){
		parent.$('#changeAddressDialog').dialog('close');
	}
	
	if(parent.$('#changeAddressDialog2').dialog('isOpen') == true){
		parent.$('#changeAddressDialog2').dialog('close');
	}
}

function initSpan(){
	var codeType = $("#codeType").val();
	if(codeType == "quote"){
		//$("#citySpan").hide();
		//$("#addrLine1Span").hide();
		//$("#busPhoneSpan").hide();
	}
}

function init(){
	countryDefaults = ['${country}'];
	stateDefaults = ['${state}'];
	initCountry();
	var addrClass = "${addrClass}";
	$("[value='"+addrClass+"']").attr("checked", true);
	if( !addrClass){
		$("[value='Residential']").attr("checked", true);
	}
	//initSpan();
}
</script>
</head>

<body>
	<form id="selectAddrForm" name="selectAddrForm">
		<input type="hidden" name="quorderNo" value="${quorderNo}" /> <input
			type="hidden" name="saveType" value="${saveType}" /> <input
			type="hidden" name="mainAddrType" value="${mainAddrType}" /> <input
			type="hidden" id="codeType" name="codeType" value="${codeType}" /> <input
			type="hidden" name="giftMsgContent" value="${giftMsgContent}" /> <input
			type="hidden" name="itemId" value="${itemId}" />
		<table border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<th>Customer #</th>
				<td><input name="custNo" type="text" class="NFText" id="custNo"
					value="${custNo}" size="38" disabled="disabled" /></td>
				<th>Alternate #</th>
				<td><input name="altNo" type="text" id="altNo" size="38"
					value="${custAltNo}" class="NFText" disabled="disabled" /></td>
			</tr>
			<tr>
				<th valign="top">Name</th>
				<th>
					<div align="left">
						<s:select size="1" id="namePfx" name="namePfx" list="namePfxList"
							listKey="value" listValue="value" cssStyle="width:50px;"
							value="namePfx"></s:select>
						&nbsp; <span class="important" id="firstNameSpan">*</span> First <input
							name="firstName" type="text" id="firstName" size="18"
							value="${firstName}" class="NFText" />
					</div>
				</th>
				<th><span class="important" id="lastNameSpan">*</span> Last</th>
				<td><input name="lastName" type="text" id="lastName"
					value="${lastName}" size="20" class="NFText" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:select id="nameSfx"
						name="nameSfx" list="nameSfxList" listKey="value"
						listValue="value" cssStyle="width:58px;" value="nameSfx"></s:select>
				</td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
				<th>Middle</th>
				<td><input name="midName" value="${midName}" type="text"
					id="midName" size="20" class="NFText" /></td>
			</tr>
			<tr>
				<th>Title</th>
				<td><input name="title" type="text" id="title" value="${title}"
					size="38" class="NFText" /></td>
				<th><span class="important">*</span>Organization</th>
				<td><input name="orgName" type="text" id="orgName"
					value="${orgName}" size="38" class="NFText" readonly="readonly" />
					<img id="orgAddDialogTrigger" src="${global_image_url}search.gif"
					width="16" height="16" /> <%--  <img id="editOrgTrigger"
					src="${global_image_url}b_edit.jpg" width="16" height="16" align="absmiddle" /> --%>
				</td>
			</tr>
			<tr>
				<th><span class="important" id="busPhoneSpan">* </span>Phone</th>
				<td><input name="busPhone" type="text" id="busPhone"
					value="${busPhone}" size="20" class="NFText" />&nbsp;&nbsp; <strong>Ext</strong>
					<input name="busPhoneExt" type="text" value="${busPhoneExt}"
					size="5" id="busPhoneExt" class="NFText" /></td>
				<th>Alt</th>
				<td><input name="altPhone" type="text" id="altPhone" size="20"
					class="NFText" value="${altPhone}" />&nbsp;&nbsp; <strong>Ext</strong>
					<input name="altPhoneExt" type="text" value="${altPhoneExt}"
					size="5" id="altPhoneExt" class="NFText" /></td>
			</tr>
			<tr>
				<th><span class="important">*</span>Email</th>
				<td><input name="busEmail" type="text" id="busEmail"
					value="${busEmail}" size="38" class="NFText" /></td>
				<th>Fax</th>
				<td><input name="fax" type="text" id="fax" size="20"
					class="NFText" value="${fax}" />&nbsp;&nbsp; <strong>Ext</strong>
					<input name="faxExt" type="text" value="${faxExt}" size="5"
					id="faxExt" class="NFText" /></td>
			</tr>

			<tr>
				<th><span class="important" id="addrLine1Span">*</span>Address</th>
				<td><input name="addrLine1" type="text" id="addrLine1"
					value="${addrLine1}" size="38" class="NFText" /></td>
				<th><span class="important" id="citySpan">*</span>City</th>
				<td><input name="city" type="text" id="city" value="${city}"
					size="38" class="NFText" /></td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td><input name="addrLine2" type="text" class="NFText"
					id="addrLine2" size="38" value="${addrLine2}" /></td>
				<th><span class="important">*</span>State</th>
				<td><select name="state" id="state"></select> <span
					style="float: right;"> <span class="important">*</span><strong>Zip</strong>
						<input name="zipCode" type="text" class="NFText" id="zipCode"
						value="${zipCode}" size="5" />
				</span></td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td><input name="addrLine3" type="text" class="NFText"
					id="addrLine3" size="38" value="${addrLine3}" /></td>
				<th><span class="important">*</span>Country</th>
				<td><select name="country" id="country"></select></td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td><input type="radio" name="addrClass" value="Commercial" />Commercial
					<input type="radio" name="addrClass" value="Residential" />Residential
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" valign="top"><br />
					<table width="610" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<th width="24">&nbsp;</th>
							<th>Customer Name</th>
							<th width="90">Address Type</th>
							<th width="80">Zip Code</th>
							<th width="150" style="border-right: 1px solid #ccc;">Country</th>
						</tr>
					</table>
					<div style="width: 627px; height: 150px; overflow: scroll">
						<table width="610" border="0" cellpadding="0" cellspacing="0"
							class="list_table">
							<s:iterator value="addrList" id="item">
								<tr>
									<td width="24"><input name="selectAddr" type="radio"
										onclick="initAddress(${item.addrId});" title="${item.addrId}" />
									</td>
									<td>${item.firstName}&nbsp;${item.lastName}</td>
									<td width="90"><div align="center">&nbsp;${item.addrType}</div>
									</td>
									<td width="80">&nbsp;${item.zipCode}</td>
									<td width="150">&nbsp;${item.country}</td>
								</tr>
							</s:iterator>
						</table>
					</div></td>
			</tr>
			<tr>
				<td colspan="4" valign="top">
					<div align="center">
						<br /> <input type="button" class="style_botton" value="Select"
							onclick="selectAddress();" /> <input type="button"
							value="Cancel" class="style_botton" onclick="cancelSelectAddr();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
