<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User list</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>	
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script type="text/javascript">
$(document).ready(function(){
$("#save_btn").click(function(){
	var stateName=$("#stateName").val();
	var stateCode=$("#stateCode").val();
	var taxRate =$("#taxRate").val();
	if(stateName=="") {
		alert("Please enter a stateName.");
		return false;
	}
	if(stateCode=="") {
		alert("Please enter a stateCode.");
		return false;
	}
	if(isNaN(taxRate)) {
		alert("Please enter a valid taxRate .");
		return false;
	}
		$.ajax({
			type:"POST",
			url:"systemsetting/account_sales_tax!saveState.action",
			data:$("#stateForm").serialize(),
			dataType:"json",
			success:function(data){
				alert(data.message);
				window.history.go(-1);
			},
			error:function(msg){
			alert("State save failed !");
		}
	});	
});
});
</script>
<style type="text/css"> 
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style></head>
 
<body class="content">
  
<div class="scm">
<div class="title_content">
  <div class="title">Tax Rate For New Jersey, US</div>
</div>
<div class="input_box">
		  <div class="content_box">
 
		    <form class="niceform" id="stateForm" name="stateForm">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
		         <tr>
		          <th>Country </th>
		          <td>
		          	  <s:textfield name="pbCountry.name" id="countryName" size="20" cssClass="NFText" readonly="true"></s:textfield>
		     	  </td>
		          <th>Country Code</th>
		          <td>
					  <s:textfield name="pbCountry.countryCode" id="countryCode" size="20" cssClass="NFText" readonly="true"></s:textfield>
				  </td>
	            </tr>
		        <tr>
		          <th valign="top">State/Province</th>
		          <td>
		          <s:textfield name="pbState.name" id="stateName" size="20" cssClass="NFText" ></s:textfield>
				  </td>
		          <th>State/Province Code</th>
		          <td>
		           <s:textfield name="pbState.stateCode" id="stateCode" size="20" cssClass="NFText" ></s:textfield>
		          </td>
	            </tr>
		        <tr>
		          <th valign="top">Sales Tax Rate</th>
		          <td>
		           <s:textfield name="pbState.taxRate" id="taxRate" size="20" cssClass="NFText" ></s:textfield>
		          </td>
		          <th>Status</th>
		          <td><s:select list="{'ACTIVE', 'INACTIVE'}"  value="pbState.status" name="pbState.status"></s:select></td>
	            </tr>
		        <tr>
		          <th>Description</th>
		          <td><s:textarea name="pbState.description" cssClass="content_textarea2"></s:textarea>
		           <s:hidden name="pbState.note"></s:hidden>
		          </td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
	            </tr>
	            <tr>
		        <th>Modified Date</th>
			      <td><input name="pbState.modifyDate" type="text" class="NFText" value="<s:date name="pbState.modifyDate" format="yyyy-MM-dd"></s:date>" size="20" readonly="yes" /></td>
			      <th>Modified By </th>
			      <td><input name="pbState.modifiedName" type="text" class="NFText" value="${pbState.modifiedName }" size="20" readonly="readonly"/></td>
			    </tr>
			      <s:hidden name="sessionId" id="sessionId"></s:hidden>
			      <s:hidden name="stateSessionId" id="stateSessionId"></s:hidden>
			      <s:hidden name="pbState.stateId" id="stateId"></s:hidden>
			      <s:hidden name="pbState.creationDate" id="creationDate"></s:hidden>
			      <s:hidden name="pbState.createdBy" id="createdBy"></s:hidden>
	          </table>
		    </form>
		</div>
  </div>	
 
 
<div class="button_box">
      <saveButton:saveBtn parameter="${operation_method}"
disabledBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
saveBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" id="save_btn" />'
/> 
      <input type="submit" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)"/>
</div>
</div>
</body>
</html>

