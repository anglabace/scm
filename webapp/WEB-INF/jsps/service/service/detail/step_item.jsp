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

<script language="javascript" type="text/javascript" src="${global_js_url}/jquery/jquery.js"></script>
<style type="text/css">
<!--
body {
	padding:0px 5px;
	width:930px;
}
-->
</style>
<script>

function openc(str1,str2)
{
   if (document.getElementById(str1).style.display=="none")
	  {
	    document.getElementById(str2).src="images/ad.gif";
	    document.getElementById(str1).style.display="block";
	  }
	  else
	  {
		  document.getElementById(str2).src="images/ar.gif";
	    document.getElementById(str1).style.display="none";
	  }
}
function desChange(id){
	$("#description"+id).val($("#select_"+id).val());
}
</script>
</head>

<body>
<div class="extend_title">Step Items</div>
<s:iterator value="breakDown">
<div class="extend_new">
<div class="extend_line" id="Protein_01">
<div class="extend" style="display:block;">
<div class="extend_l"><a href="javascript:void(0);" onclick="openc('exce${id}','img${id}');" id="exce1Item"><img src="images/ad.gif" width="11" height="11" id="img${id}"/></a></div>
<div class="extend_r">
<div class="extnd_rt">${intmdCatalogNo }: <span class="css_blue_b">${item }</span></div>
</div>
</div>

<div id="exce${id}" class="extnd_an" style="display:block;">
<div class="extnd_ant"><span class="css_b">Select Services Detail</span> <select id="select_${id }" style="width:250px" onchange="desChange(${id })">
    <option value=""></option>
    <s:iterator value="stepList">
    	<option value="${description}">${name }</option>
    </s:iterator>
</select>
</div>
<div class="extnd_ant">
<textarea class="content_textarea3" name="description${id }" id="description${id }" disabled="disabled"></textarea></div>
</div>
</div>
</div>
</s:iterator>
</body>
</html>