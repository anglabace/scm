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
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script   language="JavaScript" type="text/javascript">
var GB_ROOT_DIR = "./greybox/";
$(function(){
	var isSaved = false;
	$('#resourceChoiceDialog').dialog({
		autoOpen: false,
		height: 440,
		width: 850,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
		            
	$("#save_btn").click (function() {
		if($('#salesId').val()=="") {
			alert("Please select a sales resource to save!");
			return false;
		}
	   var formStr = "sessionId="+$("#sessionId").val()+"&salesId="+$('#salesId').val();
	   $('#save_btn').attr("disabled", true);
	   $.ajax({
				type: "POST",
				url: "territory_assign!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{
						if(data.message.toLowerCase()=='failed') {
							alert("Please create the connection!");
							return false;
						}
						alert(data.message);                              
					  isSaved = true;
					  location.href="territory_assign!input.action";
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
function resourceSelect() {
	$('#resourceChoiceDialog').dialog("option", "open", function() {	
		 var htmlStr = '<iframe src="territory_assign!selectResource.action" height="100%" width="850" scrolling="no" style="border:0px" frameborder="0"></iframe>';
       $('#resourceChoiceDialog').html(htmlStr);
	});
	$('#resourceChoiceDialog').dialog('open');
}

</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title"><span class="title_new">Sales Resource  <s:if test="salesRepDTO.resourceName!=null">-<s:property value="salesRepDTO.resourceName"/></s:if></span></div>
</div><div class="input_box">
<s:form id="saleRepForm" Class="niceform">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                <th  width="188">Sales Resource No </th>
                  <td width="221">
                   <s:textfield name="salesRepDTO.salesId" id="salesId"  Class="NFText"  size="20" readonly="true" /></td>
                  <th width="250">Status</th>
                  <td width="278">
	                  <s:select id="status_sel"  
		          			list="#{'INACTIVE':'INACTIVE','ACTIVE':'ACTIVE'}" 
		          			name="salesRepDTO.status" 
		          			listKey="key" 
		          			listValue="value" 
		          			headerKey="" 
							headerValue="Please select..." 
							disabled="true">
		              </s:select>
	              </td>
                </tr>
                <tr>
                <th> Sales Resource Name </th>
                  <td>
                  <s:textfield name="salesRepDTO.resourceName" id="resourceName" Class="NFText"  size="20" readonly="true"/>
                  <s:if test="salesRepDTO.salesId==null">
                  	 <a href="#" onclick="resourceSelect()"><img  src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
                  </s:if>
                  </td>
                	<th width="250">Function</th>
                  <td><s:if test="dropDownMap['SALES_REP_FUNCTION']!=null">
			          	 <s:select name="salesRepDTO.function"
			          	 	id="function"
			      			list="dropDownMap['SALES_REP_FUNCTION']"  
			      			listKey="value" 
			      			listValue="text"
			      			headerKey="" 
							headerValue="Please select..."
			      			style="width:157px;" disabled="true">
			      		</s:select>
			          </s:if>
			          <s:else>
			          <select name="salesRepDTO.function" id="function" disabled="true">
			          	<option value="">Please select...</option>
			          </select>
			          </s:else>
          		</td>
                 
                </tr>
                <tr>
                <th valign="top">Department</th>
                  <td><s:if test="depList!=null">
			          <s:select name="salesRepDTO.deptId"
			          	id="deptId"
			          	list="depList"
			          	listKey="deptId"
			          	listValue="name"
			          	headerKey=""
			          	headerValue="Please select..." disabled="true">
			          </s:select>
			          </s:if>
			          <s:else>
			          	<select name="salesRepDTO.deptId" id="deptId" disabled="true">
			          		<option value="">Please select...</option>
						</select>
			          </s:else>
			     </td>
			     <th>Supervisor</th>
      			 <td><s:textfield name="salesRepDTO.supervisor" id="supervisor" Class="NFText" size="20" readonly="true" /></td>
                  </tr>
                  <tr>
                    <th valign="top">Modified Date</th>
					  <td>
					  	<s:textfield name="salesRepDTO.modifyDate" id="modifyDate"  Class="NFText" size="20"  readonly="true">
				         <s:param name='value'>
							<s:date name="salesRepDTO.modifyDate"  format="yyyy-MM-dd"/>
				         </s:param>
				        </s:textfield>
					  </td>
					  <th>Modified By</th>
					  <td valign="top">
					  <s:textfield name="salesRepDTO.modifiedName" id="modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
					  </td>
                </tr>
              </table>
  <s:hidden name="sessionId" id="sessionId"></s:hidden>
  </s:form>
</div>
  <div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
      <iframe width="100%" height="310px" id="org_list_frame"
					name="org_list_frame" scrolling="no" frameborder="0"
					src="territory_assign!listOrg.action?sessionId=${sessionId}&defaultTab=0"></iframe>
    </div>
    <div class="dhtmlgoodies_aTab">
      <iframe width="100%" height="310px" id="territory_list_frame"
					name="territory_list_frame" scrolling="no" frameborder="0"
					src="territory_assign!listTerr.action?sessionId=${sessionId}&defaultTab=1"></iframe>
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
<div id="resourceChoiceDialog" title="Sales Resource choice" style="display:none"></div>
</body>
</html>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Orgnizations','Territories'),${defaultTab},998,320);
</script>