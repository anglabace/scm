<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
<script type="text/javascript">
	function confirm() {
		var delObjType = $("#delObjType").val();
		var delUrl = $("#delUrl").val();
		var note = $("#note").val();
		$.ajax({
			type: "POST",
			url: delUrl,
			data: parent.document.getElementById("srch_workCenter_iframe").contentWindow.$("#del_form").serialize()+"&note="+note,
			dataType: 'json',
			success: function(data){
				if(hasException(data)){//有错误信息.
					parent.document.getElementById("srch_workCenter_iframe").contentWindow.$("#check_del").attr("disabled", true);			
				}else {                        
					parent.$('#delReasonDlg').dialog('close');
					alert(data.message);
					parent.document.getElementById("srch_workCenter_iframe").contentWindow.location.reload();
				}
			},
			error: function(msg){
				var resultMsg = "Delete "+delObjType+" failed !";
				alert(resultMsg);		
			}
		});    
	}
	function cancel() {
		parent.$('#delReasonDlg').dialog('close');
	}
</script>
</head>
<body>

<table width="450" border="0" align="center" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
              <th colspan="2"><div align="left">Choose the reason to delete the Organization:</div></th>
            </tr>
            <tr>
              <th valign="top">Note:</th>
              <td><textarea name="textarea" id="note" class="content_textarea2"></textarea></td>
            </tr>
            <tr>
              <td colspan="2"><div align="center"><br />
                <input type="submit" name="Submit2"  class="style_botton" value="Confirm" onclick="confirm();"/>
                <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="cancel();"/>
              </div></td>
            </tr>
            <s:hidden name="delObjType" id="delObjType"></s:hidden>
            <s:hidden name="delUrl" id="delUrl"></s:hidden>
</table>
</body>
</html>
