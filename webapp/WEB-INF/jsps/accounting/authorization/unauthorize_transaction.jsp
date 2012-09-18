<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    

	 <script type="text/javascript">

var GB_ROOT_DIR = "./greybox/";

//-->
</script>

<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>

<style type="text/css">
<!--
.hidlayer {
	font-size: 12px;
	height: 370px;
	width: 666px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
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
<!--  
<script language="javascript" type="text/javascript" src="../js/newwindow.js"></script>
-->
<style>
table{
	border-collapse:collapse;
}
.new_table{
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: solid;
	border-top-color: #CCC;
	border-left-color: #CCC;
}
.new_table th{
	font-weight: bold;
	text-align: right;
	padding: 2px;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #999;
	border-right-color: #999;
	border-bottom-color: #999;
	border-left-color: #999;
}
.new_table td{
	font-weight: bold;
	text-align: left;
	padding: 2px;
	border-top-width: 0px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 0px;
	border-top-style: none;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: none;
	border-right-color: #CCC;
	border-bottom-color: #CCC;
}
</style>
<script   language="JavaScript" type="text/javascript">  

function doConfirm()
{
    
   var note=$("#note").val();
   
   if(""==$.trim(note))
   {
   	  alert("Note Can not be NULL ! ");
   	  return;
   }
      
   window.parent.$("#reason").val(note);
   window.parent.Unauthorize();
}

</script>
</head>

<body>
<table width="600" border="0" cellpadding="0" cellspacing="3">
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td style="padding-left:20px;"><table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><br />
                  <br />
                  <table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table">
                     <tr>
    <th colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <th><div align="left">Choose the reason to unauthorize transaction:</div></th>
        </tr>
    </table></th>
    
    </tr>
                      
                     <tr>
                       <th width="129">Note:</th>
                       <td>&nbsp;</td>
                     </tr>
                     <tr>
                       <th >&nbsp;</th>
                       <td width="471">
                         <textarea  id="note" name="textarea2" class="content_textarea2" style="width:368px;"></textarea>
                       </td>
                     </tr>
  <tr>
    <td height="100" colspan="2">
      <div align="center">
        <input type="button" name="Submit3" class="style_botton" value="Confirm" onclick="doConfirm()" />    
        <input type="button" name="Submit" class="style_botton" value="Cancel" onclick="window.parent.closeDlg()"/>
        </div></td>
  </tr>
</table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
