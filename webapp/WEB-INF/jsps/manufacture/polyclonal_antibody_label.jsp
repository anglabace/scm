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
	var smallTitle = "";
	var bigTitle = "";
	var success = true;
	var orderNo=$("#orderNo").val();
	$(":checkbox").each(function() {
		if(this.checked) {
			var id = this.id;
			var array = id.split("_");
			if(bigTitle!=array[0]) {
				bigTitle = array[0];
				str =str+"<br/>"+bigTitle+"<br/>";
			}
			if(smallTitle!=array[1]) {
				smallTitle = array[1];
				str =str+"<br/>"+smallTitle+"<br/>";
			}
			var i =1;
			$("#span_"+id+" input").each(function(){
				var inputVal = this.value;
				$("#span_"+id+"_"+i).html(inputVal);
				i++;
			});
			str = str +$("#span_"+id).html();
		}
	});
	if(!success) {
		alert("Please enter a valid string.");
		return;
	}
	str = "<input type='hidden' name='labelArray' id='hidden_"+orderNo+"' value='"+str+"'/>" +str;
	parent.$("#printLabelFrame").contents().find("#"+orderNo).html(str);
	parent.$("#polyclonal_antibody_labels_dlg").dialog("close");
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
              <input type="button" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#polyclonal_antibody_labels_dlg').dialog('close');"/>
            <br />
           </div></td>
          </tr>
</table>
</s:form>
</body>
</html>
