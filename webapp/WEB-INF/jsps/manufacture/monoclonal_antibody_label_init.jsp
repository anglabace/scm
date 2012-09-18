<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
});
function createLabel() {
	var orderNo = $("#orderNo").val();
	var cloneId = $("#cloneId").val();
    $('#create').attr("disabled", true);
    var url = "workorder_proc!monoclonalAntibodyEdit.action?orderNo="+orderNo+"&cloneIds="+cloneId;
    parent.$("#monoclonal_antibody_labels_dlg").dialog("close");
	parent.$("#monoclonal_antibody_labels_dlg").dialog("option","open",function(){
		 var htmlStr = '<iframe src="'+url+'" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
		 parent.$("#monoclonal_antibody_labels_dlg").html(htmlStr);
    });
	parent.$("#monoclonal_antibody_labels_dlg").dialog("open");
}
</script>
</head>

<body>
<s:form id="antibodyForm" method="post">
<s:hidden name="orderNo" id="orderNo"></s:hidden>
<table  border="0" cellpadding="0" cellspacing="0" class="General_table" width="100%">
          <tr>
          	<th>clone Ids:</th>
          	<td><input type="text" id="cloneId" class="NFText" size="38"/></td>
          </tr>
          <tr>
          	<th></th>
          	<td>(Enter the clone id and separate them with comma(",") if the multiple clone id are entered.)</td>
          </tr>
          <tr>
            <td colspan="2"><div  class="botton_box">
              <br />
               <input name="Submit22" type="button" id="create" class="style_botton" value="Create" onclick="createLabel();"/>
              <input type="button" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#monoclonal_antibody_labels_dlg').dialog('close');"/>
            <br />
           </div></td>
          </tr>
</table>
</s:form>
</body>
</html>
