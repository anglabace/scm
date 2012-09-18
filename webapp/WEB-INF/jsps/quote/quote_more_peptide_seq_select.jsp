<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#leftTable tr:odd").css("backgroundColor", "#cacaca");
	$("#leftTable tr:even").css("backgroundColor", "#e1e1e1");
	$("#rightTable tr:odd").css("backgroundColor", "#cacaca");
	$("#rightTable tr:even").css("backgroundColor", "#e1e1e1");
});
function addA(str){
	var oldStr = $(window.parent.document).find("#moreDetailIframe").contents().find('#stSequence').val();
	oldStr += str;
	$(window.parent.document).find("#moreDetailIframe").contents().find('#stSequence').val(oldStr);
}
</script>
</head>

<body>

  <table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
    <tbody>
      <tr>
        <th width="39">Note:</th>
        <th width="48" bgcolor="#99ff66">    S     </th>
        <th width="154" align="left">　Single Letter Code </th>

        <th width="53" bgcolor="#cccc66">    M     </th>
        <th width="168" align="left">　Multiple Letter Code </th>
        <th width="48" bgcolor="#ffcc66">     D    </th>
        <th width="146" align="left">　D-Amino Acid Code</th>
      </tr>
    </tbody>
  </table>

  <table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="9">Please click <img src="${global_image_url}append.gif" /> button to add you amino acid code. <br />
      After you   have finished your selection, click 'OK' to add them to your   quotation.</td>
  </tr>
</table>

  <table width="700" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#999999">

    <tbody>
      <tr>
        <td valign="top" bgcolor="#FFFFFF"><table width="100%">
          <tbody id="leftTable">
            <tr>
              <th width="34%" bgcolor="#66ccff">Name</th>
              <th width="10%" bgcolor="#99ff66">S</th>
              <th width="16%" bgcolor="#cccc66">M</th>

              <th width="9%" bgcolor="#66ccff"> </th>
              <th width="21%" bgcolor="#ffcc66">D</th>
              <th width="10%" bgcolor="#66ccff"> </th>
            </tr>
            <s:iterator value="peptideCodeList" status="sts">
            
            <s:if test="#sts.odd == true"><tr>
              <td><strong><s:if test="%{name.length()>15}"><s:property value="name.substring(0,15)"/></s:if><s:else>${name}</s:else></strong></td>
              <td>${code1}</td>
              <td>${code3}</td><td><c:if test="${! empty code3}">
              <img src="${global_image_url}append.gif" name="a0" border="0" id="a0" onclick="addA('${code3}')" /></c:if></td>
              <td>${dcode}</td><td><c:if test="${! empty dcode}">
              <img src="${global_image_url}append.gif" name="d0" border="0" id="d0" onclick="addA('${dcode}')" /></c:if></td>
            </tr>
            </s:if>
            </s:iterator>
          </tbody>
        </table></td>
        <td valign="top" bgcolor="#FFFFFF">
        <table width="100%">
          <tbody id="rightTable">
            <tr>
              <th bgcolor="#66ccff">Name</th>
              <th bgcolor="#99ff66">S</th>

              <th bgcolor="#cccc66">M</th>
              <th bgcolor="#66ccff"> </th>
              <th bgcolor="#ffcc66">D</th>
              <th bgcolor="#66ccff"> </th>
            </tr>
            
            <s:iterator value="peptideCodeList" status="sts">
            <s:if test="#sts.odd == false"><tr>
              <td><strong>${name}</strong></td>

              <td>${code1}</td>
              <td>${code3}</td><td><c:if test="${! empty code3}">
              <img src="${global_image_url}append.gif" name="a23" border="0" id="a23" onclick="addA('${code3}')" /></c:if></td>
              <td>${dcode}</td><td><c:if test="${! empty dcode}">
              <img src="${global_image_url}append.gif" name="d23" border="0" id="d23" onclick="addA('${dcode}')" /></c:if></td>
            </tr>
            </s:if>
            </s:iterator>
          </tbody>
        </table>
        </td>
      </tr>
       <tr>
        <td height="40" align="center" colspan="2">
          <input name="Submit14" type="button" class="style_botton2" value="OK, I am done." onclick="javascript:parent.$('#seqSelectDlg').dialog('close');"/>
        </td>
      </tr>
    </tbody>
    </table>

</body>
</html>
