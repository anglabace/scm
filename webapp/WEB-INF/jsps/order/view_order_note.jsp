<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css"/>
<script language="javascript"> 
function showhid(){
  var agree = document.getElementById("agree");
  var hid= document.getElementById("hid");
  if (agree.checked == false){
    hid.style.display = 'none';
  }else {
    hid.style.display = '';
  }
}
   function a(obj){
     var _selectedvalue = obj.value;
	  if(_selectedvalue==''){
	     _selectedvalue = '-1';
	  }
	 
	  for(i=1;i<=8;i++){
	    var divobj = document.getElementById('tb'+i);
	    if(parseInt(_selectedvalue)==i){
	       divobj.style.display = 'block';
	    }else{
	       divobj.style.display = 'none';
	    }
	  }
   }
   function b(obj){
     var _selectedvalue = obj.value;
	  if(_selectedvalue==''){
	     _selectedvalue = '-1';
	  }
	 
	  for(i=1;i<=8;i++){
	    var divobj = document.getElementById('pr'+i);
	    if(parseInt(_selectedvalue)==i){
	       divobj.style.display = 'block';
	    }else{
	       divobj.style.display = 'none';
	    }
	  }
   }
   function   ff(url,filePath,fileName){
	  
	    $("#filePath").val(filePath);
		$("#fileName").val(fileName);
		$("#fileForm").attr('action',url);
        $("#fileForm").submit();
}
 </script>
</head>
<body>
<table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table">
	<tr>
    <th width="99" valign="top">Order No</th>
    <td colspan="2"><input type="text" value="<s:property value='orderNote.orderNo'/>" disabled="disabled"/></td>
  </tr>
  <tr>
    <th width="99" valign="top">Type</th>
    <td colspan="2"><input type="text" value="<s:property value='orderNote.type'/>" disabled="disabled"/></td>
  </tr>
  <tr>
    <th valign="top">Description</th>
    <td colspan="2"><textarea name="textarea2" cols="70" rows="2" disabled="disabled" class="content_textarea"><s:property value="orderNote.description"/></textarea></td>
  </tr>
  <tr>
    <th valign="top">Attachment</th>
    <td colspan="2" width="64" align="left">
		<div id="doc_list">
			<s:iterator value="orderNote.documentList" id="item" status="st">
				<div id="${st.index}_docDiv">
					
					<a href="javaScript:void(0);" onclick="ff('download.action','${item.filePath}','${item.docName}')" target="self">${item.docName}</a>
        	 		
					&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</s:iterator>
		</div>
	</td>
  </tr>
  <tr>
    <th colspan="3"><div class="botton_box">
      <input type="button" name="Submit622" value="Close"  class="style_botton" onclick="parent.$('#instruction_view_dlg').dialog('close');" />
   </div></th>
  </tr>
</table>
<form action="" id="fileForm" method="post">
			<input name ="filePath" id="filePath" type="hidden"/>
      		<input name ="fileName" id="fileName" type="hidden"/>
      </form>
</body>
</html>

