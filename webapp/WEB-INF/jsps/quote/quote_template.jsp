<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Quote Management</title>
		<base id="myBaseId" href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>

		<style type="text/css">
<!--
body {
	width: 520px;
	margin: 10px auto 0px auto;
}
-->
</style>
	</head>

	<body>
      <form id="save_tmpl_form" onsubmit="return false;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<td align="left" ><font  style="font-weight:bold;">Enter my template name:</font><input id="tmplName" name="tmplName" type="text"
						class="NFText" size="40" />
					<input type="hidden" id="maxTemplate" name="maxTemplate"
						value="${maxTemplate}" />
					<input type="hidden" id="overrideFlag" name="overrideFlag"
						value="N" />
					<input type="hidden" id="sessQuoteNo" name="sessQuoteNo"
						value="${sessQuoteNo}" />
				</td>
			</tr>
			<tr>
				<td height="80">
					<div class="botton_box">
						<input type="submit" class="style_botton" value="Save"
							id="saveTmplBtn" />
						<input type="button" class="style_botton" id="cancelBtn"
							value="Cancel" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="500" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<th width="46">
								<input name="templateListCheckAll" type="checkbox"
									id="templateListCheckAll" />
								<img id="del_tmpl_img" src="images/file_delete.gif"
									alt="Delete" width="16" height="16" border="0" />
							</th>
							<th width="185">
								Template Name
							</th>
							<th width="125" id="srcQuorderNoTh">
								Src Quote No
							</th>
							<th>
								Creation Date
							</th>
						</tr>
					</table>
					<div style="width: 517px; height: 210px; overflow: scroll;">
						<table width="500" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="tmpl_tbl">
							<s:iterator value="templateList" status="key">
								<tr>
									<td width="46">
										<div align="center">
											<input type="checkbox" value="${tmplId}" name="tmplId" />
										</div>
									</td>
									<td width="185">
										${tmplName}
									</td>
									<td width="125">
										${srcQuoteNo}
									</td>
									<td>
										<s:date name="creationDate" format="yyyy-MM-dd" />
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</form>
		<script language="javascript"> 
         function trim(str) {
	         //   用正则表达式将前后空格
	         //   用空字符串替代。
	         return str.replace(/(^\s*)|(\s*$)/g,   "");

         }
		$(function(){
		    $("img").css("cursor","pointer");
		    $("#templateListCheckAll").css("cursor","pointer");
		   
		    //绑定Cancel按钮关闭窗口事件.
			$("input[value='Cancel']").click (
				function() {
					window.parent.$( '#saveTmplDialog' ).dialog( 'close' )  ;
				}
			);	
			
			//绑定全选与全不选操作.
			$("#templateListCheckAll").click( 
				 function() {
				 	$(":checkbox").attr("checked", this.checked);	
	   			  }
			); //end of 全选与全不选.			
			
			//绑定删除事件.
			$("#del_tmpl_img").click( function() {
			   	if ($("#tmpl_tbl input:checked").length < 1) {
			   	   alert("Please select the item to continue your operation.");
			   	   return;
			   	} 
			   	if (! confirm("Are you sure to delete ?")) {
			   	   return false;
			   	}

			   	 $.ajax( {
				       type: "post",
				        url: "quote_extra!delTemplate.action",
				       data: $("#tmpl_tbl input:checked").serialize(),
				    success: function(data){
							   window.location = document.URL;
						     },
					  error: function(data){
							   alert("System error! Please contact system administrator for help.");
						     }
				 });	
			});
			
			//绑定保存事件.
			$("#saveTmplBtn").click( function(event) {	
			     var bConfirm = true;		  		 
				 $("#tmplName").val(trim($("#tmplName").val()));
				 if( $("#tmplName").val() == ""){
					 alert("Please enter the template name.");
					 return;
				}
				 $("#tmpl_tbl tr").each( function() {				 
				     if (trim($(this).find("td:eq(1)").text()) == $("#tmplName").val()) {
				        if (confirm("The template's name cannot be repeat. Are you sure to override?")) {
				           $("#overrideFlag").val("Y");
				        } else {
				           bConfirm = false;
				           return false;//只是跳出each循环，没有实际返回.
				        }	
				     }			 
				 });
				 //返回这个函数体的标识位， 因为在each块中不能直接退出函数体.
				 if (! bConfirm) {
				    return;
				 }
				 if ($("#maxTemplate").val()<=$("#tmpl_tbl tr").length) {
				    if ($("#overrideFlag").val()=='N') {
				      alert("Your quote template's count is more than the maxium " + $("#maxTemplate").val() + "; Please delete one quote template first. ");
				      return false;
				    }
				 }	
				 $.ajax( {
				       type: "post",
				        url: "quote_extra!saveTemplate.action",
				       data: $("#save_tmpl_form").serialize(),
				    success: function(data){
							   //window.location = document.URL;
							   alert("The template is saved.");
							   window.parent.$( '#saveTmplDialog' ).dialog( 'close' )  ;
						     },
					  error: function(data){
							   alert("System error! Please contact system administrator for help.");
						     }
				 });			 
			});  
		}) ;
        </script>
	</body>
</html>
