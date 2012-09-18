<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script type="text/javascript">
$(function(){
	$('#userChoiceDialog').dialog({
		autoOpen: false,
		height: 440,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	var isSaved = false;
	$('#salesResourceForm').validate({
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
		            $(this).find("name=[" + key + "]").focus();
		            return false;
		        });
			 },
			 errorPlacement: function(error, element) {
			 }
		});
		            
	$("#save_btn").click (function() {
	   if ($('#salesResourceForm').valid()  === false ) {
	      return false;
	   }
	   var formStr = $('#salesResourceForm').serialize();
	   $('#save_btn').attr("disabled", true);
	   $.ajax({
				type: "POST",
				url: "sales_resource!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{
						alert(data.message);                              
					  isSaved = true;
					  location.href="sales_resource!input.action";
					}
				},
				error: function(xhr, textStatus){
				   alert("System error! Please contact system administrator for help.");
		 	       $('#save_btn').attr("disabled", false);
				}
			});                
	});//end of {$("#save_btn").click};  
});

//choice user
function userSelect() {
	$('#userChoiceDialog').dialog("option", "open", function() {	
 		 var htmlStr = '<iframe src="sales_resource!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        $('#userChoiceDialog').html(htmlStr);
	});
	$('#userChoiceDialog').dialog('open');
}
</script>
</head>

<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title">Sales Resource <s:if test="salesRep.salesId!=null">-<s:property value="salesRep.resourceName"/></s:if></div>
</div>
<s:form id="salesResourceForm" Class="niceform">
<div class="input_box">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th> Sales Resource No </th>
                  <td>
                  <s:textfield name="salesRep.salesId" id="salesId" Class="NFText"  size="20" readonly="true" />
                  </td>
                  <th width="250">Status</th>
                  <td width="278">
	                  <s:select id="status_sel"  
		          			list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" 
		          			name="salesRep.status" 
		          			listKey="key" 
		          			listValue="value">
		              </s:select>
	              </td>
                </tr>
                <tr>
                  <th> Sales Resource Name </th>
                  <td>
                  <s:textfield name="salesRep.resourceName" id="resourceName" Class="NFText"  size="20" readonly="true" />
                  <a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
                  </td>
                	<th width="250">Function</th>
                  <td><s:if test="dropDownMap['SALES_REP_FUNCTION']!=null">
			          	 <s:select name="salesRep.function"
			      			list="dropDownMap['SALES_REP_FUNCTION']"  
			      			listKey="value" 
			      			listValue="text"
			      			headerKey="" 
							headerValue="Please select..."
			      			style="width:157px;">
			      		</s:select>
			          </s:if>
			          <s:else>
			          <select name="salesRep.function">
			          	<option value="">Please select...</option>
			          </select>
			          </s:else>
          		</td>
                  </tr>
			     <tr>
			     <th valign="top">Department</th>
                  <td><s:textfield name="salesRep.deptName" id="deptName" Class="NFText"  size="20" readonly="true" />
                  <s:hidden name="salesRep.deptId" id="deptId"/>
			     </td>
			    <td>&nbsp;</td>
         		<td>&nbsp;</td>
			     </tr>
                  <tr>
                    <th valign="top">Modified Date</th>
					  <td>
					  	<s:textfield name="salesRep.modifyDate" Class="NFText" size="20"  readonly="true">
				         <s:param name='value'>
							<s:date name="salesRep.modifyDate" format="yyyy-MM-dd"/>
				         </s:param>
				        </s:textfield>
					  </td>
					  <th>Modified By</th>
					  <td valign="top">
					  <s:textfield name="salesRep.modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
				      <s:hidden name="salesRep.modifiedBy"></s:hidden>
					  </td>
                </tr>
              </table>
  
</div>
<div class="button_box">
    <saveButton:saveBtn parameter="${operation_method}"
disabledBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
saveBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" id="save_btn"/>'
/> 
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="javascript:history.go(-1)"/>
</div>
<s:hidden name="salesRep.salesGroupId"></s:hidden>
 <s:hidden name="salesRep.gsCoId"></s:hidden>
 <s:hidden name="salesRep.creationDate"></s:hidden>
  <s:hidden name="salesRep.createdBy"></s:hidden>
</s:form>
<div id="userChoiceDialog" title="Sales Resource choice" style="display:none"></div>
</div>
</body>
</html>