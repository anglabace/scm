<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Division Picker</title>
<base id="myBaseId" href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}scm/config.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<script type="text/javascript">  
function hideen(){  
	document.all.div1.style.display="";  
} 
function hideene(){
	document.getElementById("subenter").style.display="none";  
	document.getElementById("subcancel").style.display="block"; 
	document.getElementById("txt2").style.display="block"; 
	}  
	function hideenc(){
	document.getElementById("subenter").style.display="block";  
	document.getElementById("subcancel").style.display="none"; 
	document.getElementById("txt2").style.display="none";
	} 
</script>  
<style type="text/css">
<!--
.General_table{margin:15px auto;}
-->
</style>
<title>Insert title here</title>
</head>
<body>
<form method="GET" action="org_picker!showDivList.action">
<table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>

    <td width="37">Name</td>
    <td width="253"><label>
      <input name="searchKey" value="${searchKey}" type="text" class="NFText" size="40" />
      <input type="hidden" name="orgId" value="${orgId}" />
    </label></td>
    <td width="311" height="40"><input type="submit" name="Submit" value="Search" class="style_botton"/></td>
  </tr>
  <tr>
    <td colspan="3"><table width="580" border="0" cellpadding="0" cellspacing="0" class="list_table">

      <tr>
        <th width="25">&nbsp;</th>
        <th width="143">Name</th>
        <th width="56">Status</th>
        <th width="168">Description</th>
        <th style="border-right:1px solid #ccc;">Address</th>
      </tr>

    </table>
	<div style="width:597px;height:170px; overflow:scroll;">
      <table width="580" border="0" cellpadding="0" cellspacing="0" class="list_table">
	 <s:iterator value="divList" id="onediv">
        <tr>
          <td width="25">
            <input name="cust_org_div_match" type="radio" value="${divisionId}:${name}" />          
          </td>
          <td width="143">${name}&nbsp;</td>
          <td width="56">
	          <s:if test='activeFlag == "Y"'>ACTIVE</s:if>
	          <s:else>INACTIVE</s:else>
          </td>
          <td width="168">${description}&nbsp;</td>
          <td>
          ${addrLine1}&nbsp;${state}&nbsp;${country}&nbsp;
          &nbsp;
          </td>
        </tr>
        </s:iterator>
      </table>
	  </div>
	  </td>
  </tr>
  <tr>
  <td colspan="3">
  <div class="grayr">
  		<jsp:include page="/common/db_pager.jsp">
	  		<jsp:param value="${ctx}/org_picker!showDivList.action" name="moduleURL"/>
		</jsp:include>
  </div>
<br />
<input type="button" value="Select" class="style_botton" onclick="getPickDiv('cust_org_div_match', 'cust_org_div_id_other', 'cust_org_div_new');" style="margin-left:240px;"/>
&nbsp;&nbsp;<input type="button" class="style_botton" value="Cancel" onclick="closePickDiv();" />
  </td>
  </tr>

</table>
</form>

<script language="javascript" type="text/javascript">
function toggleDisplay()
{
	var toggleButton = arguments[0];
	var divId = arguments[1];
	if($('#'+divId).css('display') == 'none')
	{
		$('#'+divId).css('display', '');
		$('#cust_org_div_id_other').get(0).checked = true;
		
		// set all radio button not checked
		$("input:radio").each(function(){
			this.checked = false;
		});
		
		toggleButton.value = "Cancel";
	}else
	{
		$('#'+divId).css('display', 'none');
		$('#cust_org_div_id_other').get(0).checked = false;
		toggleButton.value = "Enter New";
	}
}

function switchCheckBox(){
	var other_flag = arguments[0];
	var new_value = arguments[1];	
	if($('#'+other_flag).attr('checked') == true){
		$('#'+new_value).attr('disabled', false);
	}else{
		$('#'+new_value).attr('disabled', true);
	}
}

function getPickDiv(){
	var myid = arguments[0];	// radio name
	var other_flag = arguments[1];	// other flag if no matched
	var new_div = arguments[2];		// new division typed
	var divIdReturn = '';
	var divNameReturn = '';
	var hasWarning = false;
	
	
	if($("#"+other_flag).attr("checked") === true){
		if($("#"+new_div).attr('value') == ''){
			alert("Please type a new division in the textbox!!!");
			hasWarning = true;
		}else{
			// TODO: add new division to parent window
			divIdReturn = '';
			divNameReturn = $("#"+new_div).attr('value');
		}
	}else{
		if($("input:radio").length == 0){
			alert("No matched!! please click the checkbox and add new division");
			hasWarning = true;
		}else{
			// set parent element
			if($("input:radio[checked=true]").length == 0){
				alert("Please select one matched division!!!");
				hasWarning = true;
			}else{	
				var divArray = $("input:radio[checked=true]").attr('value').split(':', 2);
				var divid = divArray[0];
				var divname = divArray[1];
				
				divIdReturn = divid;
				divNameReturn = divname;
			}
		}
	}
	
	if(hasWarning === true) return false;
	
	if((typeof dataHolderWin) != 'undefined')
	{
		// transmit the data from iframe to top window
		dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divPickerData', divIdReturn + "::" + divNameReturn);
		
		// alert(jQuery.data(top, 'orgPickerData'));
		dataHolderWin.$("#"+window.name+"Window").dialog("close");
	}else
	{
		alert("Warning: Division Picker container is not loaded!\n(modules/util/templates/division_picker.tpl)!");
	}
}

function closePickDiv () {
	if ((typeof dataHolderWin) != 'undefined') {
		dataHolderWin.$("#"+window.name+"Window").dialog("close");
	}
}
</script>
</body>
</html>