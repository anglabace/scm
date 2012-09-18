<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
    String itemIds = request.getParameter("itemIds");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base id="myBaseId" href="${global_url}" />
<title>scm Cloning</title>
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
<script language="javascript" type="text/javascript" src="${global_js_url}quoteorder/gene_batch.js"></script>
<form id="cloningForm" enctype="multipart/form-data">

<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="150"><span class="important">*</span>Vector name </th>
           <td width="350">
           		<s:select id="tgtVector" name="tgtVector" list="specDropDownMap['VECTOR'].dropDownDTOs" listKey="name" listValue="name"/>
           &nbsp;&nbsp;&nbsp;<input type="text" id="tgtVectorOther" name="tgtVectorOther" style="display: none;" class="NFText" size="20" />
           </td>
           <th width="100"><span class="important">*</span>Vector size(kb) </th>
           <td><input name="tgtVectorSize" type="text" class="NFText" size="20" readonly="readonly" onkeypress="return vectorSizeValid(event);"/></td>
         </tr>
         <tr>
           <th><span class="important">*</span>Resistance</th>
           <td>
	           <s:select name="tgtResistance" id="tgtResistance" list="dropDownMap['VECTOR_RESISTANCE']" listKey="value" listValue="value" disabled="true"/>
           </td>
           <th>
           		<span class="important">*</span>Copy number
           </th>
	           <td>
	           	 <input type="radio" name="tgtCopyNo" value="High" checked="checked" disabled/>
	             High
	             <input name="tgtCopyNo" type="radio" value="Low" disabled/>
	             Low
	            </td>
         </tr>
         <tr>
           <th>Vector sequence </th>
           <td><textarea name="tgtVectorSeq" id="tgtVectorSeq" class="content_textarea2" style="width:330px;" readonly="readonly"></textarea></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>Vector map </th>
           <td>
           	  <label>
           		<input type="file" name="upload" />
           	   </label>
               <input name="cloningUploadBtn" type="button" class="style_botton" value="Upload" disabled/>
               <input name="tgtMapDocFlag" type="hidden" id="" />
            </td>
           <td align="left">&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
         	<th>&nbsp;</th>
         	<td colspan="1">
	         	<table id="fileListTable" name="fileListTable"></table>
           	</td>
         </tr>
         <tr>
           <th><span class="important">*</span>Cloning method </th>
           <td colspan="3"><input name="tgtCloningMethod" type="radio" checked="checked" value="Standard"/>
	             Cloning site :
	             <input name="tgtCloningSite" type="text" class="NFText" size="25"/>(Example:BamHI-HindIII) <input name="tgtCloningMethod" type="radio" value="Gateway"/>Gateway <sup>TM</sup>
			</td>
         </tr>
    <tr>
        <th></th>
        <td height="80" colspan="3" align="center">
                <input type="button" name="close2" value="Save" class="style_botton" onclick="closeCloning();"/>
        </td>
    </tr>
       </table>
</form>
<script type="text/javascript">
var sessNoName = "sessQuoteNo";
var cloningUrlObj = {
    saveBatchCloning : "quote_more!saveCustCloning.action",
    cloningParentId : "<%=itemIds%>",
    cloningSessNo : "${sessQuoteNo}",
    itemId : "<%=itemIds%>",
    uploadfile : "quote_more!batchUploadFile.action"
};

$("#cloningForm").find("[name='cloningUploadBtn']").click(function(){
		var tmpId = $("#cloningForm").find("[name='itemId']").val();
		var refType = getRefType("cloning", 2);
		uploadServiceFile(tmpId, "custCloning", "cloningForm", refType, this);
	});

$("#tgtVector").change(function(){
    if ($(this).val() == "Other") {
        $("input").each(function() {
            $(this).attr("disabled", false);
            $(this).attr("readonly", false);
        });
        $("#tgtVectorSeq").attr("readonly", false);
        $("#tgtResistance").attr("disabled", false);
        $("#tgtVectorOther").css("display", "");
    }else{
        $("input").each(function() {
            if ($(this).attr("name") != "tgtCloningMethod" && $(this).attr("name") != "tgtCloningSite") {
                $(this).attr("disabled", false);
                $(this).attr("readonly", false);
            }
        });
        $("#tgtVectorSeq").attr("readonly", true);
        $("#tgtResistance").val("");
        $("#tgtResistance").attr("disabled", true);
        $("#tgtVectorOther").css("display", "none");
    }
});
</script>
</head>
</html>