<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base id="myBaseId" href="${global_url}" />
<title>scm more detail</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />

<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.validate.js" ></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
</head>
<body>
         <table width="500" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <th colspan="2" align="right" width="108"><strong>Files naming conventions:</strong></th>
                <th align="left" > &nbsp;&nbsp;Order No + &quot;-&quot;Item No &nbsp;&nbsp; + &quot;-&quot; + File type &quot;-&quot;+Filename</th>
                  <td>&nbsp;</td>
              </tr>
             <tr>
                 <th>&nbsp;</th>
                 <th>&nbsp;</th>
                 <td>&nbsp;</td>
             </tr>
              <tr>
                <td colspan="3">File Type Example: COA, DATASHEET, EXAMPLE, GC, HPLC, MAP, MAP_HIGH, MASS_SPECE, MS, MSDS, PRODUCTINFO, PROTOCOL, STRUCTURE, TECHNICAL_MANUAL, Image & Graph, Reference, Other</td>
                <%--<th colspan="2"></th>--%>
                <%--<td></td>--%>
              </tr>

              <tr>
                <td height="80" colspan="3" align="center">
                    <input type="button" name="Submit" value="Continue"
                           class="style_botton" onclick="uploadDoc();"/>
                    <input type="button" name="Submit52" value="Cancel"
                           class="style_botton"
                           onclick="closeWindow();"/>
                </td>
              </tr>
            </table>
<div align="center"><font style="color:red;"><strong>If the button of "Continue" doesn't work, please use this URL :"${uploadPath}".</strong></font></div>
</body>
<script type="text/javascript">
 function closeWindow(){
     parent.$('#uploadHelp').dialog('close');
 }

function uploadDoc(){
    var uploadPath = "${uploadPath}";
    if(uploadPath == ""){
        alert("System error! Please contact system administrator for help.")
    }else{
       window.open(uploadPath);
    }
}
</script>
</html>