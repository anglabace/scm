<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript">
        var baseUrl = "${global_url}";
    </script>
<script language="JavaScript" src="${global_js_url}scm/contactAddr.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/config.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.validate.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script>
        $(document).ready(function() {
            jQuery.validator.addMethod("isPhone", function(value, element, param) {
                var tmpVal = value.replace(/ /g, "");
                return this.optional(element) || /^([\d-])*$/.test(tmpVal);
            }, "Please enter a valid phone");

            // validate signup form on keyup and submit
            $("#addr_add_form").validate({
                invalidHandler: function(form, validator) {
                    $.each(validator.invalid, function(key, value) {
                        alert(value);
                        $(this).find("[name='" + key + "']").focus();
                        return false;
                    });
                },
                rules: {
                    "addrData.firstName": { required:true, maxlength : 50 },
                    "addrData.lastName": { required:true, maxlength : 50 },
                    "addrData.orgName": { required:true /* , maxlength : 50 */},
                    "addrData.busPhone": { required:true,maxlength : 20 },
                    "addrData.busPhoneExt":{digits:true},
                    "addrData.altPhoneExt":{digits:true},
                    "addrData.faxExt":{digits:true},
                    "addrData.busEmail": {required:true/* ,email:true */,maxlength : 50},
                    "addrData.addrLine1": {required:true ,maxlength : 50},
                    "addrData.city": {required:true ,maxlength : 50},
                    state: {required:true ,maxlength : 50},
                    "addrData.fax":{ maxlength:20},
                    "addrData.zipCode": {required:true,maxlength:10},
                    "addrData.country": {required:true}
                },
                messages: {
                    "addrData.firstName": 
                    {
                    	required: "Please enter your firstname",
                 	maxlength : "the first Name   - Max length: 50 characters"
                 	},
                	 "addrData.lastName":  {required:"Please enter your lastname",
                 	maxlength : "the last Name   - Max length: 50 characters"},
                     "addrData.orgName":  {required:"Please select an organization"/* ,
                 	maxlength : "the organization name   - Max length: 50 characters" */},
                    "addrData.busPhone": {required:"Please enter your phone number" ,
                 	maxlength : "the bus Phone    - Max length: 20 characters"},
                    "addrData.busPhoneExt":{digits:"Please enter the ext for digits."},
                   "addrData.busEmail": {required:"Please enter your email",
                	 /*   email: 'Please enter a valid email address', */
                 	maxlength : "the bus Email   - Max length: 50 characters" },
                    "addrData.faxExt":{digits:"Please enter a Number for fax Ext."},
                    "addrData.addrLine1": {required:"Address is required",
                 	maxlength : "the addr Line1    - Max length: 50 characters"},
                "addrData.city": {required:"City is required",
                 	maxlength : "the city    - Max length: 50 characters"},
                state: {required:"State is required",
                 	maxlength : "the State   - Max length: 50 characters"},
                	 "addrData.fax":{
                		 maxlength:"the fax  - Max length: 20 characters"},
                    "addrData.country": "Please select the country.",
                       "addrData.zipCode": {required: "Please enter the zip Code.",
                 	maxlength : "the zip Code   - Max length: 10 characters"}
                },
                errorPlacement: function(error, element) {
                }

            });
            $('.ui-datepicker').each(function() {
                $(this).datepicker(
                {
                    dateFormat: 'yy-mm-dd',
                    changeMonth: true,
                    changeYear: true
                });
            });
            $('#effFrom').datepicker();
            $('#effTo').datepicker();
        });


        $(function () {
            $('#orgAddDialogTrigger')
                    .click(function() {
                dataHolderWin.jQuery.data(dataHolderWin.document.body, 'disableNew', 1);
                dataHolderWin.$('#orgDialogWindow').dialog('open');
                dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgLoc', self);
                dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgIdStr', 'orgId');
                dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgNameStr', 'orgName');
            });
            
            $('#editTrigger').click(function(){
            	$("#orgName").attr("readonly",false);
            	alert("Now  you can enter an organization name!");
            });
            
        	$("#orgName").blur(function(){
        		var orgname=$("#orgName").val();
        		if(orgname!=""){
        			$.ajax({
    					type: "POST",
    					url : "basedata/org_picker!checkOrg.action?orgName="
							+ orgname,
					dataType : "json",
					success : function(data, textStatus) {
						if (data.message != 'ok') {
							alert(data.message);
							$("#orgName").val("");
							$("#orgName").focus();
							return false;
						}
					} 
    				});
    				
        		}
        	});
        });
    </script>
<script>
        function aCheck1() {
            document.getElementById("Periodical_date").style.display = "none";
        }

        function bCheck2() {
            document.getElementById("Periodical_date").style.display = "block";
        }

        function add_address(bt_obj, form_name, action_name) {
            var sessNo = $("#sessContactNo").val();
            if ($("#" + form_name).valid() == false) {
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
             
            if ($("[name='validation_date'][checked=true]:radio").val() == 'Y') {
                if ($("#effFrom").attr('value') == "") {
                    alert("Please enter the correct Validation Date.");
                    $("#effFrom").focus();
                    return false;
                }
                if ($("#effTo").attr('value') == "") {
                    alert("Please enter the correct Validation Date.");
                    $("#effTo").focus();
                    return false;
                }
                if ($("#effFrom").attr('value') > $("#effTo").attr('value')) {
                    alert("Please select the correct date range.");
                    $("#effFrom").focus();
                    return false;
                }
            }
            var moreData = '';
            var defaultTmpId = $("#defaultTmpId").val();
            if (defaultTmpId != '' && $("#defaultFlag").attr('checked') == true) {
                var addrIframe = parent.document.getElementById("addrIframe");
            }
            var bt_val = bt_obj.value;
            bt_obj.value = bt_val + '...';
            bt_obj.disabled = true;
            var form = $("#" + form_name);
            //ajax form post
            var options = {
                success:function(data) {
                    if (data == 'exist_default') {
                        alert('There is already one default address');
                        bt_obj.value = bt_val;
                        bt_obj.disabled = false;
                        return;
                    }
                    if (data) {
                        alert(data);
                    }
                    bt_obj.value = bt_val + ":success";
                    // refresh the address list in address tab
                    parent.$('<c:if test="${! empty addressKey}">#editContactAddrDialog</c:if><c:if test="${empty addressKey}">#addContactAddrDialog</c:if>').dialog('close');
                    parent.addrIframe.location.reload();
                    //parent.document.getElementById('addrIframe').src = parent.document.getElementById('addrIframe').src;
                },
                error: function() {
                    alert('error...');
                    bt_obj.value = bt_val;
                    bt_obj.disabled = true;
                },
                resetForm:false,
                url:action_name + moreData,
                type:"POST"
            };
            form.ajaxForm(options);
            form.submit();
        }

        function check_only(obj) {
      $("#status").attr("checked", true);
        if (!$(obj).attr('checked')) {
        <s:if test='addrData.status == "ACTIVE"'>
                    $("#status").attr("checked", true);
        </s:if>
            <s:else>
            $("#status").attr("checked", false);
        </s:else>
            return true;
        }

            var need_check_flag = "${result.need_check_flag}";
            if (need_check_flag != 1) {
                return true;
            }
            var check_type_index = document.getElementById("addrType").selectedIndex;
            var arr = new Array();
            arr[0] = "";
            arr[1] = "";
            arr[2] = "";
            arr[3] = "";
            if (arr[check_type_index] != '') {
                if (confirm("There is already one default address, Are you sure to set as default?")) {
                    $("#defaultTmpId").val(arr[check_type_index]);
                    return true;
                }
                else {
                    return false;
                }
            } else {
                return true;
            }
        }
    </script>
</head>
<body>
	<form method="get" action="contact_address!save.action"
		id="addr_add_form">
		<input type="hidden" id="sessContactNo" name="sessContactNo"
			value="${sessContactNo}" /> <input type="hidden" name="contactNo"
			value="${contactNo}" /> <input type="hidden" value="${defaultFlags}"
			name='defaultFlags' id="defaultFlags" /> <input type="hidden"
			value="${addrData.addrId}" id='defaultTmpId' name="addrData.addrId" />
		<input type="hidden" name="addressKey" value="${addressKey}" />
		<table border="0" align="center" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<td align="right">Type</td>
				<td><s:select id="addrType" name="addrData.addrType"
						list="dropDownList['ADDRESS_TYPE']" listKey="value"
						listValue="value" cssStyle="width:100px" value="addrData.addrType"
						onchange="change_type();"></s:select></td>
				<td align="right">Description</td>
				<td colspan="3"><input name="addrData.description"
					value="${addrData.description}" type="text" id="description"
					size="40" class="NFText" /></td>
			</tr>
			<tr>
				<td height="40">&nbsp;</td>
				<td valign="top"><input type="checkbox" id="status"
					name="addrData.status"
					<s:if test='addrData.status=="ACTIVE"||addrData.defaultFlag=="Y"'>checked="checked"</s:if>
					value="ACTIVE" /> ACTIVE <input onclick="return check_only(this);"
					name="addrData.defaultFlag" type="checkbox" id="defaultFlag"
					<s:if test='addrData.defaultFlag=="Y"'>checked="checked"</s:if>
					value="Y" /> Set as Default</td>
				<td>&nbsp;</td>
				<td colspan="3" valign="top"><input type="button"
					value="Select Address"
					onclick="select_address('${addressKey}', '${sessContactNo}');" />
				</td>
			</tr>
			<tr>
				<td align="right">Contact #</td>
				<td><input name="addrData.contactNo"
					value="${contactDetail.contactNo}" type="hidden" /> <input name=""
					disabled="disabled" readonly value="${contactDetail.contactNo}"
					type="text" class="NFText3" size="38" /></td>
				<td align="right">Alternate #</td>
				<td colspan="3"><input name="alternate" disabled="disabled"
					value="${contactDetail.altNo}" type="text" id="alternate" size="40"
					class="NFText3" readonly="readonly" /></td>
			</tr>
			<tr>
				<td valign="top" align="right">Name</td>
				<td colspan="5">
					<div align="left">
						<select size="1" id="namePfx" name="addrData.namePfx"
							style="width: 45px;">
							<option value=""></option>
							<option value="Dr.">Dr.</option>
							<option value="Mr.">Mr.</option>
							<option value="Mrs.">Mrs.</option>
							<option value="Ms.">Ms.</option>
						</select> <span class="important">*</span>First <input
							name="addrData.firstName" type="text" id="firstName" size="15"
							value="${addrData.firstName}" class="NFText" /> Middle <input
							name="addrData.midName" value="${addrData.midName}" type="text"
							id="midName" size="15" class="NFText" /><span class="important">*</span>Last
						<input name="addrData.lastName" type="text" id="lastName"
							value="${addrData.lastName}" size="17" class="NFText" /> <select
							id="nameSfx" name="addrData.nameSfx" style="width: 70px;">
							<option value=""></option>
							<option value="CPA">CPA</option>
							<option value="Ed.D">Ed.D</option>
							<option value="Esq.">Esq.</option>
							<option value="II">II</option>
							<option value="III">III</option>
							<option value="IV">IV</option>
							<option value="Jr.">Jr.</option>
							<option value="M.D.">M.D.</option>
							<option value="Ph.D">Ph.D</option>
							<option value="Sr.">Sr.</option>
							<option value="V">V</option>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td align="right">Title</td>
				<td><input name="addrData.title" type="text" id="title"
					value="${addrData.title}" size="38" class="NFText" /></td>
				<td><span class="important">*</span>Organization</td>
				<td colspan="3"><input name="addrData.orgId"
					value="${addrData.orgId}" type="hidden" id="orgId" /> <input
					name="orgname" value="${addrData.orgName}" readonly="readonly"
					type="text" id="orgName" size="40" class="NFText" /> <img
					id="orgAddDialogTrigger"
					src="${global_images_url}images/search.gif" width="16" height="16" />
				</td>
			</tr>
			<tr>
				<td align="right"><span class="important">*</span>Phone</td>
				<td>
					<div align="left">
						<input name="addrData.busPhone" type="text"
							value="<s:if test='addrData.busPhone!=""'>${addrData.busPhone}</s:if>
                    <s:else></s:else>"
							id="busPhone" size="20" class="NFText" /> Ext <input
							name="addrData.busPhoneExt" value="${addrData.busPhoneExt}"
							type="text" size="8" id="busPhoneExt" class="NFText" />
					</div>
				</td>
				<td align="right">Alt</td>
				<td><input name="addrData.altPhone"
					value="${addrData.altPhone}" type="text" id="altPhone" size="15"
					class="NFText" /></td>
				<td align="right">Ext</td>
				<td><input name="addrData.altPhoneExt"
					value="${addrData.altPhoneExt}" type="text" size="14"
					id="altPhoneExt" class="NFText" /></td>
			</tr>
			<tr>
				<td align="right"><span class="important">*</span>Email</td>
				<td><input name="addrData.busEmail"
					value="${addrData.busEmail}" type="text" id="busEmail" size="38"
					class="NFText" /></td>
				<td align="right">Fax</td>
				<td><input name="addrData.fax" type="text" id="fax" size="15"
					value="${addrData.fax}" class="NFText" /></td>
				<td align="right">Ext</td>
				<td><input name="addrData.faxExt" type="text" size="14"
					id="faxExt" value="${addrData.faxExt}" class="NFText" /></td>
			</tr>

			<tr>
				<td align="right"><span class="important">*</span>Address</td>
				<td><input name="addrData.addrLine1" type="text" id="addrLine1"
					value="${addrData.addrLine1}" size="38" class="NFText" /></td>
				<td align="right"><span class="important">*</span>City</td>
				<td colspan="3"><input name="addrData.city" type="text"
					id="city" size="40" value="${addrData.city}" class="NFText" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input name="addrData.addrLine2" type="text"
					value="${addrData.addrLine2}" class="NFText" id="addrLine2"
					size="38" /></td>
				<td align="right"><span class="important">*</span>State</td>
				<td><select name="state" id="state"></select></td>
				<td align="right"><span class="important">*</span>Zip</td>
				<td><input name="addrData.zipCode" value="${addrData.zipCode}"
					type="text" class="NFText" id="zipCode" size="14" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input name="addrData.addrLine3" type="text"
					value="${addrData.addrLine3}" class="NFText" id="addrLine3"
					size="38" /></td>
				<td align="right"><span class="important">*</span>Country</td>
				<td colspan="3"><select name="addrData.country" id="country">

				</select></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="5"><input name="addrData.addrClass" type="radio"
					value="Commercial"
					<s:if
                    test='addrData.addrClass=="Commercial"'>checked="checked"</s:if> />
					Commercial <input type="radio" name="addrData.addrClass"
					value="Residential"
					<s:if
                    test='addrData.addrClass=="Residential"'>checked="checked"</s:if> />
					Residential</td>
			</tr>
			<tr>
				<td valign="top" align="right">Validation Date</td>
				<td colspan="5"><input name="validation_date" type="radio"
					value="N" checked onclick="aCheck1()" /> No date restrictions <input
					type="radio" name="validation_date" value="Y" onclick="bCheck2()" />
					Periodical

					<div id="Periodical_date"
						style="<s:if test='addrData.effFrom!=null'>display:block</s:if> <s:else>display:none;</s:else>padding-left:146px;padding-top:3px;">
						From<input name="effFrom" type="text" class="NFText ui-datepicker"
							value="${effFrom}" size="18" id="effFrom" style="width: 129px" />
						&nbsp;&nbsp;&nbsp;&nbsp;To <input name="effTo" type="text"
							class="NFText ui-datepicker" value="${effTo}" size="18"
							id="effTo" style="width: 129px" /> &nbsp;
					</div></td>
			</tr>
			<tr>
				<td valign="top" align="right">Comments</td>
				<td colspan="5"><textarea id="note" name="addrData.note"
						class="content_textarea">${addrData.note}</textarea></td>
			</tr>
			<tr>
				<td colspan="6" valign="top">
					<div align="center">
						<br /> <input type="button"
							onclick="add_address(this, 'addr_add_form', 'contact/contact_address!save.action');"
							id="addr_add_button"
							value="<c:if test="${! empty addressKey}"> Edit  </c:if><c:if
                            test="${empty addressKey}"> Add </c:if>" />
						<input type="button" value="Cancel"
							onclick="parent.$('<c:if test="${! empty addressKey}">#editContactAddrDialog</c:if><c:if test="${empty addressKey}">#addContactAddrDialog</c:if>').dialog('close');" />
					</div>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
    set_selected("addrType", "${addrData.addrType}");
    set_selected("namePfx", "${addrData.namePfx}");
    set_selected("nameSfx", "${addrData.nameSfx}");
    <s:if test = 'addrData.effFrom!=null'>
            set_checked("validation_date", "Y");
    </s:if>
    <s:else>
    set_checked("validation_date", "N");
    </s:else>
    set_checked("addrClass", "${addrData.addrClass}");
</script>
	<script type="text/javascript"
		src="${global_js_url}scm/gsCountryState.js?v=1"></script>
	<s:if test='addrData.country!=""'>
		<script>
        var countryIdNames = ['country'];
        var countryDefaults = ['${addrData.country}'];
        var countryChangeHandlers = [''];
        var stateIdNames = ['state'];
        var stateDefaults = ['${addrData.state}'];
        var stateChangeHandlers = [''];
        $(document).ready(function() {
            initCountry();
            document.getElementById("state").value = stateDefaults;
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
</body>
</html>