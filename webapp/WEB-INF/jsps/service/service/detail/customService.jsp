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
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>

 
<script>function aCheck1(){
 
document.getElementById("yes").style.display="block";
document.getElementById("no").style.display="none";  
 
}
 
function bCheck2(){
 
document.getElementById("yes").style.display="none"; 
document.getElementById("no").style.display="block";  
 
} 
function cCheck1(){
 
document.getElementById("other").style.display="none";
document.getElementById("puc").style.display="block";
}
 
function dCheck1(){
 
document.getElementById("other").style.display="block";  
document.getElementById("puc").style.display="none";
} 
function usage1(){
document.getElementById("usage").style.display="block";  
document.getElementById("promo").style.display="none";
}
function usage2(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
function usage3(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
function usage4(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}function usage5(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
function usage6(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
function usage7(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
 
</script> 
<script language="javascript" type="text/javascript" src="js/newwindow.js"></script>
<style type="text/css"> 
<!--
body {
	width:930px;
}
 
.hidlayer1 {
	font-size: 12px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
	height: 200px;
	width: 200px;
}
 
-->
</style>
 
 
</head>
 
<body>
 
 
 
<div id="dhtmlgoodies_tabView1" >
 
 
    <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
            <tr>
        <th width="11%">Descrition</th>
        <td width="44%"><input name="textfield2" type="text" class="NFText" size="34" /></td>
        <th width="18%">&nbsp;</th>
        <td width="27%">&nbsp;</td>
      </tr>
            <tr>
              <th>Service Detail</th>
              <td><textarea name="textarea" class="content_textarea2"></textarea></td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
    </table>
 
</div>
 
 
 
 
  
 
</body>
</html>

