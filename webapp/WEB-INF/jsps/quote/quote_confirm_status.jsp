 <%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
        <link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
        <link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
        <link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
        <script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
        <script type="text/javascript">
        $(document).ready(function(){
        	 //更新order转态为CC
            $("#infoUpdateQuoteStatusConfirm").click(function(){
         		var quoteStatusNotes=$('#quoteStatusNotes').val();
         		quoteStatusNotes=quoteStatusNotes.replace(/(^\s*)|(\s*$)/g, "");
         		if(quoteStatusNotes==''){
        	 		alert("Please enter the reason.");
        	 		return;
         		}
        		var status = "CO";
        		var statusText = "Close without order";
        		var updateConfirmStatus = $("#updateConfirmStatus").val();
            	var updateConfirmStatusHTML = $("#updateConfirmStatus option:selected").text();
            	if (updateConfirmStatus == "OH") {
            		status = updateConfirmStatus;
                	statusText = updateConfirmStatusHTML;
            	}
        		var itemId = parent.$("#itemDetailIframe").contents().find("#itemId").val();
        		$.ajax({
        			url:"quote!updateStatus.action",
        			data:"sessQuoteNo="+parent.orderquoteObj.sessNoValue+"&status="+status+"&statusText="+statusText+"&statusReason="+quoteStatusNotes,
        			dataType:"text",
        			type:"POST",
        			success:function(data){
        				if(data == "success"){
        					//刷新
        					parent.document.getElementById("itemListIframe").contentWindow.location.href = parent.orderquoteObj.url("getItemList");
        					parent.document.getElementById("itemDetailIframe").src = parent.orderquoteObj.url("getItemDetail")+"&itemId="+itemId;
        					parent.document.getElementById("salesInformationIframe").src = parent.$("#salesInformationIframe").attr("src");
        				}else{
        					alert("System error! Please contact system administrator for help.");
        				}
        			},
        			error:function(){
        				alert("System error! Please contact system administrator for help.");
        			},
        			async:false
        		});
        		parent.$("#confirmStatusDialog").dialog("close");
        	});
        });
        </script>
    </head>
<body>    
<table border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:40px;height:180px; width: 440px;">
       <tr>
         <th height="26">
           
         	<div align="left">Status
              <select id="updateConfirmStatus" style="width: auto;">
              	<option value="CC">Closed W/O order</option>
              	<s:if test='quote.status == "OP"'>
              	<option value="OH">On Hold</option>
              	</s:if>
              </select>
        	</div>
         </th>
         <tr><th align="left"><div align="left"><font color="red">*</font>Status Reason</div></th></tr>
       <tr>
<th style="height:70px" valign="top"><div align="right"><textarea id="quoteStatusNotes" name="comment" class="content_textarea2"></textarea>
</div>
</th>
</tr>
<tr>
       
       <tr>
          <th valign="bottom">
          <div align="center" style="margin:10px;">
            <input type="button"  class="style_botton" value="Confirm" id="infoUpdateQuoteStatusConfirm" />&nbsp;&nbsp;
            <input type="button"  value="Cancel"  class="style_botton" onclick="parent.$('#confirmStatusDialog').dialog('close');" /> 
          </div>
        </th>
      </tr>
</table>
</body>
</html>