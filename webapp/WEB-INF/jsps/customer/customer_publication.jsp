<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css?v=1" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css?v=1" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css?v=1" rel="stylesheet" />
<link href="${global_js_url}thickbox/thickbox.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="https://dev.genscriptcorp.com/scm/js/jquery/themes/base/ui.all.css" rel="stylesheet" />
<link href="${global_css_url}stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_css_url}themes/base/ui.all.css" rel="stylesheet" />
<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/customerPubGrant.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}thickbox/thickbox-compressed.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>

</head>

<body>
<div style="width:700px; height:270px; overflow-y:scroll;margin-top:10px;">
  <table border="0"  cellpadding="0" cellspacing="0" class="list_table" style="width:100%">
    <tr>
      <th width="46"> <div align="left">
        <input type="checkbox" value="pubcheck" onclick="toggleCheck(this, 'pubcheck')"/>
        <img src="${global_image_url}file_delete.gif" alt="s" width="16" height="16" border="0"  onclick="delPubTR('pubcheck');"  /></div></th>
     <th width="45%">Title</th>
     <th>Publication Name </th>
     <th width="10%">Issue Date</th>   
     <th width="5%">URL</th>

    </tr>
	<s:iterator value="pagePubs.result">
    <tr id="trpub<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>">
       <td style="display:none">
			<input type="hidden" id="pub_title_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${title}" /> 
			<input type="hidden" id="pub_publicationName_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${publicationName}" />
			<input type="hidden" id="pub_issueDate_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${issueDate}" />		
			<input type="hidden" id="pub_coAuthor_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${coAuthor}" />		
			<input type="hidden" id="pub_abst_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${abst}" />	
			<input type="hidden" id="pub_relatedArea_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${relatedArea}" />	
			<input type="hidden" id="pub_keyWords_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${keyWords}" />		
			<input type="hidden" id="pub_email_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${email}" />		
			<input type="hidden" id="pub_url_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="<s:property value="url"/>" />	
			<input type="hidden" id="pub_id_<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>_pubsfx" value="${id}" /> 	       
	  </td>
      <td><input type="checkbox" name="pubcheck" value="<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>" /></td>
      <td title="${title}">&nbsp;<a href="javascript:void(0);" onclick="editPublication('<c:choose><c:when test="${empty id}">${pubIdStr}</c:when><c:otherwise>${id}</c:otherwise></c:choose>')"><s:if test="%{title.length()>60}"><s:property value="title.substring(0,60)"/></s:if><s:else>${title}</s:else></a></td>
     <td title="${publicationName}">&nbsp;<s:if test="%{publicationName.length()>45}"><s:property value="publicationName.substring(0,45)"/></s:if><s:else>${publicationName}</s:else></td>
     <td title="<s:date name="issueDate" format="yyyy-MM-dd"/>">&nbsp;<s:date name="issueDate" format="yyyy-MM-dd"/></td>
      <td>&nbsp;<a href="<s:property value="url"/>" target="_blank"><img src="${global_image_url}link.gif" border="0" /></a></td>
    </tr>
	</s:iterator>
  </table>
</div>
<div class="grayr">
<jsp:include page="/common/db_pager.jsp">
<jsp:param value="${ctx}/cust_pub_grant!listPub.action" name="moduleURL"/>
</jsp:include>	
	</div>
   <br/> 
   <div align="left">
              <form  method="post" id="UploadForm" enctype="multipart/form-data">
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>Grants Files Upload: <img src="images/excel.jpg" width="16" height="16" align="absmiddle"/></th>
            <td>
              <span class="TabbedPanelsContent" style="display: block;">
              <input name="xls" type="file" id="fileField" class="type-file-file"/>
              </span>
            </td>
            <td><span class="TabbedPanelsContent" style="display: block;">
                    <input name="Submit" type="submit" class="style_botton" value="Upload" />
                </span></td>
        </tr>
    </table>
         <div id="upMessage" style="displan:hidden"></div>
    </form>
   </div>
              <br>
       <div id="newPublication" title="Add New Publication" style="visible:hidden"></div>
<div id="editPublication" title="Edit Publication" style="visible:hidden"></div>
     <div align="center" >
      <input type="button" name="Submit3" class="style_botton" value="Save" id="Save" onclick="DoSaveAll()"/>
      <input  type="button" name="close" value="Cancel" class="style_botton" onclick="javascript:parent.parent.$('#grantPublicationDialog').dialog('close');"  />
     <input type="button" value="New" class="style_botton" onclick="newPublication()" />
        
     </div>
     <input type="hidden" id="custNo" value="${custNo}"/>
     <input type="hidden" id="sessCustNo" value="${sessCustNo }" />
      

<script type="text/javascript">
fileinput("fileField","textfield");
		var custNo = $("#custNo").val();
		var sessCustNo = $("#sessCustNo").val();
	$(document).ready(function(){
		$('#newPublication').dialog({
			autoOpen: false,
			height: 310,
			width: 620,
			modal: true,
			bgiframe: true,
			open: function(){
				var htmlStr ='<iframe src="cust_pub_grant!showPubCreateForm.action?custNo=' +custNo+'&sessCustNo='+ sessCustNo + '" height="260" width="580" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#newPublication').html(htmlStr);
			}
		});

             var validateForm = function() {
                             var fileFieldpathw = $('#fileField');
                             var pathsw = getPath(fileFieldpathw);
                             var fileName = $('#fileField').val();
                             if(pathsw==""){
                                 alert("Please Select a Excel2003 File !");
                                 return false;
                             }

                              var m=parseInt(fileName.toString().lastIndexOf("."))+1;
                              var extVal=fileName.toString().substr(m);
                              if(extVal!="xls") {
                                  alert(' Please try to change this file into type of excel2003  for this uploading ,Thanks!');
                                 $("#textfield").val("");
                                  return false;
                              }
                              $('#upMessage').html('Please wait for this File uploading Up... ...');
                              return true;
                    };
                      var showResponse = function(data,status) {
                          $('#upMessage').fadeIn("fast",function(){
                              alert("The file uploading successly ..");
                              window.location.reload();
                          });
                         return true;
                       };

                           var url2="cust_pub_grant!upLoadExcel_Publish.action?custNo=" + custNo + "&sessCustNo=" + sessCustNo;
                             var options={
                              beforeSubmit: validateForm,
                              target : '#upMessage',
                              url:url2,
                              success : showResponse,
                              resetForm:true
                          };

          $('#UploadForm').ajaxForm(options);
	});

    function DoSaveAll(){
        $.ajax({
            type: "POST",
            url: "cust_pub_grant!saveAllPublishs.action?custNo="+custNo+"&sessCustNo="+sessCustNo,
            success: function(msg) {
              alert("Success to save all new publishs.");
               window.location.reload();
            },
               error: function(msg) {
               alert("Failed to save new publishs. Please contact system administrator for help.");
         }
         });
    }

function getPath(obj)
    {
      if(obj)
       {
        if (window.navigator.userAgent.indexOf("MSIE")>=1)
         {
           obj.select();
          return document.selection.createRange().text;
         }
        else if(window.navigator.userAgent.indexOf("Firefox")>=1)
         {
          if(obj.files)
           {
            return obj.files.item(0).getAsDataURL();
           }
          return obj.value;
         }
        return obj.value;
       }
    }


	function newPublication(){
			$('#newPublication').dialog('open');
	}

	function editPublication(){
		var pubId = arguments[0];
		$('#editPublication').dialog({
			autoOpen: false,
			height: 310,
			width: 620,
			modal: true,
			bgiframe: true,
			open: function(){
				var htmlStr ='<iframe src="cust_pub_grant!showPubEditForm.action?pubIdStr='+pubId+'&custNo=' +custNo+'&sessCustNo='+ sessCustNo + '" height="260" width="580" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#editPublication').html(htmlStr);
			}
		});
		$('#editPublication').dialog('open');
}

	function toggleCheck()
{
	var checkObj = arguments[0];
	var checkName = arguments[1];
	if(checkObj.checked)
	{
		$("input[name='"+checkName+"']").each(function(index){
			this.checked="checked";
		});
	}else
	{
		$("input[name='"+checkName+"']").each(function(index){
			this.checked="";
		});
	}
}

function delPubTR()
{
	if(!confirm("Are you sure to delete checked publications?"))
	{
		return;
	}
	var checkName = arguments[0];
	var pubList = "";
	$(":checkbox[name='"+checkName+"']").each( function(){
		//$("#tr"+this.value).remove();
		if(this.checked) pubList += this.value+",";
	});
	pubList = pubList.replace(new RegExp(",$", 'g'), '');
	
	$.ajax({
		type: "POST",
		url: "cust_pub_grant!delPub.action",
		data: "custNo=${custNo}&pubIds="+pubList,
		success: function(msg) {
//			alert(msg);
			$(":checkbox[name='"+checkName+"']").each( function(){
				if(this.checked) $("#trpub"+this.value).remove();
			});
			// parent.parent.GB_hide();
		},
		error: function(msg) {
			alert("Failed to delete the Grant. Please contact system administrator for help.");
		}
	});
}


function initPubEditForm()
{
	var pubId = arguments[0];
	tb_show("Edit Publication","cust_pub_grant!showPubEditForm.action?pubIdStr="+pubId+"&custNo=${custNo}"+"&sessCustNo=${sessCustNo}"+"&TB_iframe=true&height=250&width=600","thickbox");
}

</script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>

<script type="text/javascript" src="${global_js_url}customer/customer_validate.js"></script>
<script type="text/javascript" src="${global_js_url}customer/customer_trigger.js"></script>
<script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>
<script type="text/javascript" src="${global_js_url}scm/divPicker.js"></script>
<script type="text/javascript" src="${global_js_url}scm/deptPicker.js"></script>
</body>
</html>
