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
<script  language="JavaScript" type="text/javascript">
  

$(document).ready(function(){
	 var currency=$("#currencyCode1").val();
     $("#currencyCode2").val(currency);
     var language=$("#langCode1").val();
     $("#langCode2").val(language);
	$("#save_btn").click(function(){
		var countryName=$("#countryName").val();
		var countryCode=$("#countryCode").val();
		var taxRate =$("#nationalRate").val();
		if(countryName=="") {
			alert("Please enter a countryName.");
			return false;
		}
		if(countryCode=="") {
			alert("Please enter a countryCode.");
			return false;
		}
		if(isNaN(taxRate)) {
			alert("Please enter a valid taxRate .");
			return false;
		}
		$.ajax({
			type:"POST",
			url:"systemsetting/account_sales_tax!save.action",
			data:$("#countryForm").serialize(),
			success:function(msg){
			//document.location.reload();
			if(msg.message=='Success'){
			alert('Save operation successfully.');
			}
			window.location.href="systemsetting/account_sales_tax!input.action";
			},
			error:function(msg){
			alert("Country save failed !");
		}
	});	
});

		$('#currencyCode1').change( function() {
	     var currency=$("#currencyCode1").val();
	     $("#currencyCode2").val(currency);
		});
		
		$('#langCode1').change( function() {
	     var language=$("#langCode1").val();
	     $("#langCode2").val(language);
		});
	
});


</script>
</head>
<body class="content">
<div class="scm">
<div class="title_content">
  <div class="title">Currency & Tax - United States</div>
</div>
<div class="input_box">
		  <div class="content_box">
		    <form class="niceform" name="countryForm" id="countryForm">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
		        <tr>
		          <th>Country </th>
		          <td>
		          	  <s:textfield name="pbCountry.name" id="countryName" size="20" cssClass="NFText"></s:textfield>
		     	  </td>
		          <th>Country Code</th>
		          <td>
					  <s:textfield name="pbCountry.countryCode" id="countryCode" size="20" cssClass="NFText"></s:textfield>
				  </td>
	            </tr>
		        <tr>
		          <th>Currency</th>
		          <td>
		          <s:if test="currencyList!=null">
		          	<s:select  name="pbCountry.currencyCode" list="currencyList" id="currencyCode1"
								listKey="currencyCode" listValue="description" headerKey=""
								headerValue="Please select..." onchange="">
				    </s:select>
		          </s:if>
		          <s:else>
		          	<select name="pbCountry.currencyCode" id="currencyCode1">
		          		<option value="">Please select...</option>
		          	</select>
		          </s:else>
		          </td>
		          <th>Currency Code</th>
		          <td>
		          <s:hidden name="pbCountry.symbol" id="symbol"></s:hidden>
		          <s:textfield name="currencyCode" id="currencyCode2" readonly="true" size="20" cssClass="NFText"></s:textfield>
				  </td>
	            </tr>
		        <tr>
		          <th>Language</th>
		          <td>
		          <s:if test="languageList!=null">
		          	<s:select  name="pbCountry.langCode" list="languageList" id="langCode1"
								listKey="langCode" listValue="name" headerKey=""
								headerValue="Please select..." >
				    </s:select>
		          </s:if>
		          <s:else>
		          	<select>
		          		<option value="">Please select...</option>
		          	</select>
		          </s:else>
				  </td>
		          <th>Language Code</th>
		          <td>
		          <s:hidden name="pbCountry.langName" id="langName"></s:hidden>
		          <s:textfield name="langCode" id="langCode2" readonly="true" size="20" cssClass="NFText"></s:textfield>
		          </td>
	            </tr>
		        <tr>
		          <th>National Sales Tax Rate</th>
		          <td><s:textfield name="pbCountry.nationalRate" id="nationalRate"  size="20" cssClass="NFText"></s:textfield>
		          </td>
		          <th>Continent</th>
		          <td>
		          <s:if test="dropDownMap['CONTINENT']!=null">
		          	<s:select name="pbCountry.continent" id="continent"
			      			list="dropDownMap['CONTINENT']"  
			      			listKey="value" 
			      			listValue="text"
			      			cssStyle="width:157px;"
			      			headerKey=""
			      			headerValue="Please select...">
			      		</s:select>
		          </s:if>
		          <s:else>
		          	<select name="pbCountry.continent" id="continent">
		          		<option value=""></option>
		          	</select>
		          </s:else>		 
		          </td>
	            </tr>
                <tr>
                  <th width="160">Status</th>
                  <td><s:select list="{'ACTIVE','INACTIVE'}" name="pbCountry.status" value="pbCountry.status"></s:select></td>
                  <th width="150">&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>Description</th>
                  <td><s:textarea name="pbCountry.description" cssClass="content_textarea2"></s:textarea>
                  <s:hidden name="pbCountry.note"></s:hidden>
                  </td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                  <tr>
      <th>Modified Date</th>
      <td><input name="pbCountry.modifyDate" type="text" class="NFText" value="<s:date name="pbCountry.modifyDate" format="yyyy-MM-dd"></s:date>" size="20" readonly="yes" /></td>
      <th>Modified By </th>
      <td><input name="pbCountry.modifiedName" type="text" class="NFText" value="${pbCountry.modifiedName }" size="20" readonly="readonly"/></td>
      <s:hidden name="sessionId" id="sessionId"></s:hidden>
      <s:hidden name="pbCountry.countryId" id="countryId"></s:hidden>
      <s:hidden name="pbCountry.creationDate" id="creationDate"></s:hidden>
      <s:hidden name="pbCountry.createdBy" id="createdBy"></s:hidden>
    </tr>
   </table>
 </form>
</div>
</div>
  <s:if test="pbCountry.countryId!=null">
  	<div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
    <iframe width="100%" height="310px" id="state_list_frame"
					name="state_list_frame" scrolling="no" frameborder="0"
					src="account_sales_tax!stateList.action?id=${sessionId}"></iframe>
    </div>
  </div>
 </s:if>	

<script type="text/javascript"> 
initTabs('dhtmlgoodies_tabView1',Array('States/Provinces'),0,998,320);
 
</script>

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
