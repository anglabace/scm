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
			var arrayId = id.split("_");
			if(arrayId!=null&&arrayId.length==1) {
				str = str +"<br>"+arrayId[0]+"<br>";
				$("#span_"+arrayId[0]+" input:checkbox").each(function(){
					if(this.checked) {
						var id2 = this.id;
						$("#span_"+id2+" input").each(function(){
							var inputVal = this.value;
							$(this).parent().html(inputVal);
						});
						str = str +$("#span_"+id2).html();
					}
				});
			}
		}
	});
	if(!success) {
		alert("Please enter a valid string.");
		return;
	}
	str = "<input type='hidden' name='labelArray' id='hidden_"+orderNo+"' value='"+str+"'/>" +str;
	parent.$("#printLabelFrame").contents().find("#"+orderNo).html(str);
	parent.$("#monoclonal_antibody_labels_dlg").dialog("close");
}
</script>
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
              <input type="button" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#monoclonal_antibody_labels_dlg').dialog('close');"/>
            <br />
           </div></td>
          </tr>
</table>
</s:form>
</body>
</html>
