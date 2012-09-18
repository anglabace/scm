<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<script src ="${global_js_url }table.js" type="text/javascript"></script>
<style type="text/css">
<!--
body {
	width:570px; margin:5px auto;
}
-->
</style>
<script><!--
function setOldVersion(id){
	var version = $("#version"+id).val();
	var docFileName = $("#docFileName"+id).val();
	var docFilePath = $("#docFilePath"+id).val();
	var imageFileName = $("#imageFileName"+id).val();
	var imageFilePath = $("#imageFileName"+id).val();
	var docHref = '<a href="download.action?filePath='+docFilePath+'&fileName='+docFileName+'" target="self">'+docFileName+'</a>';
	//docHref += '&nbsp;&nbsp;&nbsp';
    //docHref += '<input type="button" class="style_botton"  value="Delete" onclick="delFile(\'docFilePath\',\'docFilePathDiv\')"/>';

	var imageHref = '<a href="download.action?filePath='+imageFilePath+'&fileName='+imageFileName+'" target="self">'+imageFileName+'</a>';
	//imageHref	+= '&nbsp;&nbsp;&nbsp';	
	//imageHref += '<input type="button" class="style_botton"  value="Delete" onclick="delFile(\'imageFilePath\',\'imageFilePathDiv\')"/>';

	parent.$("#docFileName").val(docFileName);
	parent.$("#docFilePath").val(docFilePath);
	parent.$("#imageFileName").val(imageFileName);
	parent.$("#imageFilePath").val(imageFilePath);

	parent.$("#docFilePathDiv").html(docHref);
	parent.$("#imageFilePathDiv").html(imageHref);
	parent.$("#viewOldVersionDialog").dialog("close");
}
</script>
</head>
<body>
	<table width="570" border="0" cellpadding="0" cellspacing="0">
		<tr>
		    <td colspan="4" style="padding-top:10px;"><table width="550" border="0" cellpadding="0" cellspacing="0" class="list_table">
		      <tr>  
		        
		        <th width="100">Version</th>
		        <th width="180">File Type</th>
		        <th>Description</th>
		        </tr>
		    </table></td>
		  </tr>
		  <tr>
	    <td colspan="4" style="padding-bottom:20px; "><div  style="width:567px; height:300px; overflow:scroll;">
	      <table width="550" border="0" cellpadding="0" cellspacing="0" class="list_table">
	      		<s:iterator value="documentVersionList">
	      			 <tr>
				     <td width="100"><a href="javascript:setOldVersion(${id});" >${version }</a></td>
				     <td width="180">${docType}</td>
				     <td>${description}</td>
				     <input type="hidden" id="version${id }"  value="${version}"/>
				     <input type="hidden" id="docFileName${id }" value="${docFileName}"/>
				     <input type="hidden" id="docFilePath${id }" value="${docFilePath}"/>
				     <input type="hidden" id="imageFileName${id }" value="${imageFileName}"/>
				     <input type="hidden" id="imageFilePath${id }" value="${imageFilePath}"/>
				     </tr>
	      		</s:iterator>
	      		
	      </table>
	      </div>
	      </td>
	      </tr>
	      <tr>
	      			<td align="center"><input type="button" value="Closed" class="style_botton" onclick="parent.$('#viewOldVersionDialog').dialog('close');" />  </td>
	      			
	      		</tr>
	</table>
</body>
</html>