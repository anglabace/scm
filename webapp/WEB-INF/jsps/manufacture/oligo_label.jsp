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
	var str = "";
	var hidden_value= "";
	var smallTitle = "";
	var bigTitle = "";
	var success = true;
	var orderNo=$("#orderNo").val();
	var nmole = 0;
	if($("#OD").val()!="") {
		var od = $("#OD").val();
		nmole = $("#OD").val()*$("#args").val();
	}
	nmole = Math.round(nmole*Math.pow(10,2))/Math.pow(10,2);    
	var strSingle = "";
	strSingle = strSingle +"OligoName:"+$("#oligo").val()+","+"Purity:"+$("#purity").val()+","+"Amount:"+$("#OD").val()+"OD "+nmole+"nmol,"+"Cat.No."+$("#catalog").val();
	str = strSingle;
	hidden_value = strSingle;
	var aliquoting_into = $("#aliquotingInto").val();
	for(var i=1;i<aliquoting_into;i++) {
		str = str+"<br>"+strSingle;
		hidden_value = hidden_value+"<br>"+strSingle;
	}
	str = "<input type='hidden' name='labelArray' id='hidden_"+orderNo+"' value='"+hidden_value+"'/>" +str;
	parent.$("#printLabelFrame").contents().find("#"+orderNo).html(str);
	parent.$("#oligo_labels_dlg").dialog("close");
}
</script>



<style>
.add{
	margin: 0px;
	padding: 0px;
	font:11px/18px Arial, Helvetica, sans-serif;
	color:#06F;
	text-decoration:underline;
	}
.ab{
	margin: 0px;
	padding: 0px;
	font:11px/18px Arial, Helvetica, sans-serif;
	color:#F00;
	
}
</style>
</head>

<body>
<s:form id="antibodyForm" method="post">
<s:hidden name="orderNo" id="orderNo"></s:hidden>
<table  border="0" cellpadding="0" cellspacing="0" class="General_table" width="100%">
          <tr>
          	<td>${antibody}</td>
          </tr>
          <tr>
            <td><div  class="botton_box">
              <br />
               <input name="Submit22" type="button" id="create" class="style_botton" value="Create" onclick="createLabel();"/>
              <input type="button" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#oligo_labels_dlg').dialog('close');"/>
            <br />
           </div></td>
          </tr>
</table>
</s:form>
</body>
</html>
