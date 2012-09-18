<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />

<link href="${global_css_url }SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
function saveSubStep(){
	name = $("#name").val();
	
	if(!name){
		alert("Please enter the Name.");return;
	}
	stepNo = $("#stepNo").val()
	$.ajax({
		type:"POST",
		url:"serv/serv!saveSubStepItem.action",
		data:$("#mainForm").serialize(),
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				htmlStr = '<tr id="del_'+msg.id+'"><td width="67"><input type="checkbox" name="subStepSeq" value="'+msg.id+'" /></td><td width="142"><a href="javascript:void(0);" onclick="subStepAddAndEdit(\''+msg.id+'\');" title="${stepNo}">'+stepNo+'</a></td><td width="271">'+name+'</td></tr>';
				if(msg.saveSubStep=='0'){
					parent.$("#detailIframe").contents().find("#del_"+msg.id).remove();
                }
				parent.$("#detailIframe").contents().find('#subStepList').prepend(htmlStr);
				parent.$('#subStepAddDialog').dialog('close');
				parent.$('#subStepAddDialog').dialog('destory');
			}
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
		}
	});
}

</script>
<body style="padding-top:20px;padding-left:20px;">
<form id="mainForm">

<table border="0" cellspacing="0" cellpadding="0" class="General_table">
  <tr>
    <th>Step Name </th>
    <td><input name="subStep.name" id="name" type="text" class="NFText" size="45" value="${subStep.name }"/></td>
  </tr>
  <tr>
    <th>Description</th>
    <td colspan="2"><textarea name="subStep.description" class="content_textarea2">${subStep.description }</textarea></td>
  </tr>
   <tr>
    <th>Cost</th>
    <td colspan="2"><input name="subStep.cost" type="text" class="NFText" size="45" value="${subStep.cost}"/></td>
  </tr>
   <tr>
    <th>Lead Time</th>
    <td colspan="2"><input name="subStep.leadTime" type="text" class="NFText" size="45" value="${subStep.leadTime}"/></td>
  </tr>
  <tr>
    <th>Step no</th>
    <td colspan="2"><input name="subStep.stepNo" id="stepNo" type="text" class="NFText" size="45" value="${subStep.stepNo}"/></td>
  </tr>
   <tr>
    <td align="right"">
    <s:if test="subStep.seqFlag==\"Y\"">
    	<input type="checkbox" name="subStep.seqFlag" value="Y" checked="checked"/>
    </s:if>
    <s:else>
    	<input type="checkbox" name="subStep.seqFlag" value="Y"/>
    </s:else>
    </td>
    <td align="left"><font size="1">sequence</font></td>
    <td>
    	
    </td>
   </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <input type="hidden" name="id" value="${id }"/>
  <input type="hidden" name="subStep.stepId" value="${subStep.stepId}"/>
  <input type="hidden" name="sessionServiceId" value="${sessionServiceId}"/>
  <tr>
    <td colspan="3"><div class="botton_box">
      <input type="button" name="Save"class="style_botton"  value="Save" onclick="saveSubStep()"/>
      <input type="button" name="cancelAllTrigger" value="Cancel"  class="style_botton" onclick="parent.$('#subStepAddDialog').dialog('close');parent.$('#subStepAddDialog').dialog('destory');"/></div>
</td>
  </tr>
</table>

</form>
</body>


</html>
