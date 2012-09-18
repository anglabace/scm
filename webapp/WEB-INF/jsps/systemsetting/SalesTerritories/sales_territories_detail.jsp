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
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script   language="JavaScript" type="text/javascript">
var GB_ROOT_DIR = "./greybox/";
$(function(){
	var isSaved = false;
	$('#saleTerritoryForm').validate({
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
			 rules: {
				 "salesTerritoryDTO.territoryCode": { required:true}
			 },
			 messages: {
				 "salesTerritoryDTO.territoryCode": { required : "Please enter the territory code !" }
			 },
			 errorPlacement: function(error, element) {
			 }
		});
		            
	$("#save_btn").click (function() {
	   if ($('#saleTerritoryForm').valid()  == false ) {
	      return false;
	   }
	   var formStr = $('#saleTerritoryForm').serialize();
	   $('#save_btn').attr("disabled", true);
	   $.ajax({
				type: "POST",
				url: "sales_territories!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{
						alert(data.message);                              
					  isSaved = true;
					  location.href="sales_territories!input.action";
					}
				},
				error: function(xhr, textStatus){
				   alert("System error! Please contact system administrator for help.");
		 	       $('#save_btn').attr("disabled", false);
				}
			});                
	});//end of {$("#save_btn").click};  
	 
	
	
	    
	window.onbeforeunload = function() {
		if(isSaved === false){
			return 'Do you want to leave without saving data?';
		}
	}

});


</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title"><span class="title_new">Sales Territory <s:if test="salesTerritoryDTO.territoryId!=null">-<s:property value="salesTerritoryDTO.territoryName"/></s:if></span></div>
</div><div class="input_box">
<s:form id="saleTerritoryForm" Class="niceform">
 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="131">Sales Territory Code</th>
      <td width="410">
      <s:textfield name="salesTerritoryDTO.territoryCode" Class="NFText" size="20"/></td>
      <th width="131">Sales Territory Name</th>
      <td width="293">
        <s:textfield name="salesTerritoryDTO.territoryName" Class="NFText"  size="20" />
      </td>
	  </tr>
	<tr>
	  <th rowspan="2" valign="top">Description</th>
	  <td rowspan="2">
	    <s:textarea name="salesTerritoryDTO.description" Class="content_textarea2"></s:textarea>
	    </td>
	  <th>Status</th>
	  <td valign="top">
	   <s:select id="status_sel"  
	          			list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" 
	          			name="salesTerritoryDTO.status" 
	          			listKey="key" 
	          			listValue="value">
	    </s:select>
       </td>
	  </tr>
<%--	  <tr>
	  <th>Function</th>
	  <td valign="top">
	  <s:if test="dropDownMap2['SALES_REP_FUNCTION']!=null">
			          	 <s:select name="salesTerritoryDTO.territoryClassification"
			      			list="dropDownMap2['SALES_REP_FUNCTION']"  
			      			listKey="value" 
			      			listValue="text"
			      			headerKey="" 
							headerValue="Please select..."
			      			style="width:157px;">
			      		</s:select>
			          </s:if>
			          <s:else>
			          <select name="salesTerritoryDTO.territoryClassification">
			          	<option value="">Please select...</option>
			          </select>
			          </s:else>
		</td>
	  </tr> --%>

     <tr>
	  <th></th>
	  <td></td>
	  </tr>
    <tr>
        <th valign="top">Modified Date</th>
	  <td>
	  	<s:textfield name="salesTerritoryDTO.modifyDate" Class="NFText" size="20"  readonly="true">
         <s:param name='value'>
			<s:date name="salesTerritoryDTO.modifyDate" format="yyyy-MM-dd"/>
         </s:param>
        </s:textfield>
	  </td>
	  <th>Modified By</th>
	  <td valign="top">
	  <s:textfield name="salesTerritoryDTO.modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
      <s:hidden name="salesTerritoryDTO.modifiedBy"></s:hidden>
	  </td>
    </tr>
  </table>
  <s:hidden name="salesTerritoryDTO.territoryId"></s:hidden>
  <s:hidden name="salesTerritoryDTO.gsCoId"></s:hidden>
  <s:hidden name="salesTerritoryDTO.territoryType"></s:hidden>
  <s:hidden name="salesTerritoryDTO.createdBy"></s:hidden>
  <s:hidden name="salesTerritoryDTO.creationDate"></s:hidden>
  <s:hidden name="sessionId" id="sessionId"></s:hidden>
  </s:form>
</div>
  <div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
      <iframe width="100%" height="310px" id="zone_list_frame"
					name="zone_list_frame" scrolling="no" frameborder="0"
					src="sales_territories!listZone.action?sessionId=${sessionId}"></iframe>
    </div>
  </div>
<div class="button_box">
           <saveButton:saveBtn parameter="${operation_method}"
disabledBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
saveBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" id="save_btn" />'
/> 
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)"/>
</div>

</div>
</body>
</html>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Zones'),0,998,320);
</script>