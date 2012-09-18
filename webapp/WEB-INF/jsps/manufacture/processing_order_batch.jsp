<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}ui.all.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script>
	$(function(){
		$("#process_a").click(function(){
			var comment = $("#comment").val();
			comment = $.trim(comment);
			if (jmz.GetLength(comment) > 250) {
				alert("Comment's length could not more than 250");
				return false;
			}
		});
	});

	//Calculat charactor length
	var jmz = {};
	jmz.GetLength = function(str) {
	    ///<summary>获得字符串实际长度，中文2，英文1</summary>
	    ///<param name="str">要获得长度的字符串</param>
	    var realLength = 0, len = str.length, charCode = -1;
	    for (var i = 0; i < len; i++) {
	        charCode = str.charCodeAt(i);
	        if (charCode >= 0 && charCode <= 128) realLength += 1;
	        else realLength += 2;
	    }
	    return realLength;
	};
	
	function aCheck1(){
 		document.getElementById("Periodical_date").style.display="none";
	}
	function bCheck2(){
		document.getElementById("Periodical_date").style.display="block";  
	}  

	function cancel() {
		window.$ = window.parent.$;
		$("#batchOrder").dialog("close");
	}
</script>
</head>
<body>
<s:if test="saveStatus == 'success'">
<script>alert("Save success!");window.$ = window.parent.$;$('#batchOrder').dialog('close');</script>
</s:if>
<s:elseif test="saveStatus == 'failure'">
<script>alert("Save failure!");</script>
</s:elseif>
<form action="${global_css_url}workorder_proc!saveBatchOrderProcess.action" id="batchProcessForm" method="post">
<s:hidden name="workOrderStatus"/>
<s:hidden name="orderNoStrs"/>
<table width="700"  border="0" cellpadding="0" cellspacing="0" class="General_table">
          <tr>
          <td colspan="4">
          <div class="invoice_title" >Work Order</div>
          </td>
          </tr>
          <tr>
            <th>Order Status</th>
            <td><select name="selectWorkOrderStatus" id="select_a">
            		<option value="In Production">In Production</option>
            	  	<option value="Production Complete">Production Complete</option>
	            </select>
	        </td>
            <th>&nbsp;</th>
            <td >&nbsp;</td>
          </tr>
          <tr>
            <th>Comment</th>
            <td colspan="3"><input name="comment" type="text" id="comment" maxlength="250" class="NFText" style="width:360px;"/></td>
          </tr>
          <tr>
            <td colspan="4" valign="top"><div  class="botton_box">
              <input name="Submit22" type="submit" class="style_botton" value="Save" id="process_a"/>
              <input  type="button" name="close2" value="Cancel" class="style_botton" onclick="cancel();" />
            </div><br /><br />
			</td>
          </tr>
        </table>
</form>
</body>
</html>

