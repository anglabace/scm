<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" /> 
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<link href="${global_js_url}thickbox/thickbox.css" rel="stylesheet"
	type="text/css" />
<link type="text/css"
	href="https://dev.genscriptcorp.com/scm/js/jquery/themes/base/ui.all.css"
	rel="stylesheet" />
<link href="${global_css_url}stylesheet/scm.css" rel="stylesheet"
	type="text/css" />
<link type="text/css" href="${global_css_url}themes/base/ui.all.css"
	rel="stylesheet" /> 

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/customerPubGrant.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}thickbox/thickbox-compressed.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
</head>

<body>

	<div
		style="width: 700px; height: 250px; overflow-y: scroll; margin-top: 10px;">
		<table border="0" cellpadding="0" cellspacing="0" class="list_table"
			style="width: 100%;">
			<tr>
				<th width="46">
					<div align="left">
						<input type="checkbox" name="checkbox4" value="grantcheck"
							onclick="toggleCheck(this, 'grantcheck')" /> <img
							src="images/file_delete.gif" alt="s" width="16" height="16"
							border="0" onclick="delGrantTR('grantcheck');" />
					</div></th>
				<th>Project No</th>
				<th>Project Name</th>
				<th>Funding IC</th>
				<th>Org</th>
				<th>Amount</th>
				<th>Issue Date</th>
				<th>Expiration Date</th>
			</tr>
			<s:iterator value="pageGrants.result">
				<tr
					id="tr<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>">
					<td style="display: none"><input type="hidden"
						id="grant_id_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${grantId}" /> <input type="hidden"
						id="grant_category_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${category}" /> <input type="hidden"
						id="grant_fundingIC_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${fundingIc}" /> <input type="hidden"
						id="grant_projNum_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${projectNo}" /> <input type="hidden"
						id="grant_subProj_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${subProjectNo}" /> <input type="hidden"
						id="grant_projTitle_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${projectName}" /> <input type="hidden"
						id="grant_projAbst_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${abst}" /> <input type="hidden"
						id="grant_piName_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${pi}" /> <input type="hidden"
						id="grant_contactEmail_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${email}" /> <input type="hidden"
						id="grant_orgName_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${organization}" /> <input type="hidden"
						id="grant_state_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${state}" /> <input type="hidden"
						id="grant_country_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${country}" /> <input type="hidden"
						id="grant_amount_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="${amount}" /> <input type="hidden"
						id="grant_exprDate_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="<s:date name="exprDate"
                                                                                     format="yyyy-MM-dd"/>" />
						<input type="hidden"
						id="grant_issueDate_<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when> <c:otherwise>${grantId}</c:otherwise></c:choose>_sfx"
						value="<s:date name="issueDate"
                                                                                     format="yyyy-MM-dd"/>" />
					</td>
					<td><input type="checkbox" name="grantcheck"
						value="<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>" />
					</td>
					<td>&nbsp;<a href="javascript:void(0);"
						onclick="editGrant('<c:choose><c:when test="${empty grantId}">${grantIdStr}</c:when><c:otherwise>${grantId}</c:otherwise></c:choose>');">${projectNo}</a>
					</td>
					<td title="${projectName}">&nbsp;<s:if
							test="%{projectName.length()>15}">
							<s:property value="projectName.substring(0,15)" />
						</s:if>
						<s:else>${projectName}</s:else>
					</td>
					<td>&nbsp;${fundingIc}</td>
					<td title="${organization}">&nbsp;<s:if
							test="%{organization.length()>15}">
							<s:property value="organization.substring(0,15)" />
						</s:if>
						<s:else>${organization}</s:else>
					</td>
					<td>&nbsp;${amount}</td>
					<td>&nbsp;<s:date name="issueDate" format="yyyy-MM-dd" />
					</td>
					<td>&nbsp;<s:date name="exprDate" format="yyyy-MM-dd" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/cust_pub_grant!listGrant.action"
				name="moduleURL" />
		</jsp:include>
	</div>
	<br />
	<br />
	<div align="left">
		<form method="get" id="UploadForm" enctype="multipart/form-data">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>Grants Files Upload: <img src="images/excel.jpg"
						width="16" height="16" align="absmiddle" />
					</th>
					<td><span class="TabbedPanelsContent" style="display: block;">
							<input name="xls" type="file" id="fileField"
							class="type-file-file" /> </span></td>
					<td><span class="TabbedPanelsContent" style="display: block;">
							<input name="Submit" type="submit" class="style_botton"
							value="Upload" /> </span>
					</td>
				</tr>
			</table>
			<div id="upMessage" style="displan: hidden"></div>
		</form>
	</div>
	<br />

	<div id="newGrant" title="Add New Grant" style="visible: hidden"></div>
	<div id="editGrant" title="Edit Grant" style="visible: hidden"></div>
	<div align="center">
		<input type="button" name="Submit3" class="style_botton" value="Save"
			id="Save" onclick="DoSaveAll()" /> <input type="button" name="close"
			value="Cancel" class="style_botton"
			onclick="javascript:parent.parent.$('#grantPublicationDialog').dialog('close');" />
		<input type="button" value="New" class="style_botton"
			onclick="newGrant()" />

	</div>
	<input type="hidden" id="custNo" value="${custNo}" />
	<input type="hidden" id="sessCustNo" value="${sessCustNo }" />
	<script type="text/javascript">
    fileinput("fileField", "textfield");
     var custNo = $("#custNo").val();
    var sessCustNo = $("#sessCustNo").val();
      $(document).ready(function() {
        $('#newGrant').dialog({
            autoOpen: false,
            height: 310,
            width: 670,
            modal: true,
            bgiframe: true,
            open: function() {
                var htmlStr = '<iframe src="cust_pub_grant!showGrantCreateForm.action?custNo=' + custNo + '&type=add&sessCustNo=' + sessCustNo + '" height="250" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
                $('#newGrant').html(htmlStr);
            }
        });
                    var validateForm = function() {
                             var fileFieldpathw = $('#fileField');
                             var pathsw = getPath(fileFieldpathw);
                             var fileName = $('#fileField').val();
                             if(pathsw==""){
                                 alert("Please select a excel2003 file !");
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

                           var url2="cust_pub_grant!upLoadExcel_grant.action?custNo=" + custNo + "&sessCustNo=" + sessCustNo;
                             var options={
                              beforeSubmit: validateForm,
                              target : '#upMessage',
                              url:url2,
                              success : showResponse,
                              resetForm:true
                          };

          $('#UploadForm').ajaxForm(options);

    });


    function DoSaveAll() {
        $.ajax({
            type: "POST",
            url: "cust_pub_grant!saveALlGrants.action?custNo=" + custNo + "&sessCustNo=" + sessCustNo,
            success: function(msg) {
                alert("Success to save all new grant.");
                window.location.reload();
            },
            error: function(msg) {
                alert("Failed to save new grant. please contact system administrator for help.");
            }
        });
    }

    function getPath(obj) {
        if (obj) {
            if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
                obj.select();
                return document.selection.createRange().text;
            }
            else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
                if (obj.files) {
                    return obj.files.item(0).getAsDataURL();
                }
                return obj.value;
            }
            return obj.value;
        }
    }

    function newGrant() {
        $('#newGrant').dialog('open');
    }

    function editGrant() {
        var grantId = arguments[0];
        $('#editGrant').dialog({
            autoOpen: false,
            height: 310,
            width: 670,
            modal: true,
            bgiframe: true,
            open: function() {
                var htmlStr = '<iframe src="cust_pub_grant!showGrantEditForm.action?grantIdStr=' + grantId + '&custNo=' + custNo + '&sessCustNo=' + sessCustNo + '" height="250" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
                $('#editGrant').html(htmlStr);
            }
        });
        $('#editGrant').dialog('open');
    }


    function toggleCheck() {
        var checkObj = arguments[0];
        var checkName = arguments[1];
        if (checkObj.checked) {
            $("input[name='" + checkName + "']").each(function(index) {
                this.checked = "checked";
            });
        } else {
            $("input[name='" + checkName + "']").each(function(index) {
                this.checked = "";
            });
        }
    }

    function delGrantTR() {
        if (!confirm("Are you sure to delete checked grants?")) {
            return;
        }
        var checkName = arguments[0];
        var grantList = "";
        $(":checkbox[name='" + checkName + "']").each(function() {
            //$("#tr"+this.value).remove();
            if (this.checked) grantList += this.value + ",";
        });
        grantList = grantList.replace(new RegExp(",$", 'g'), '');

        $.ajax({
            type: "POST",
            url: "cust_pub_grant!delGrant.action",
            data: "custNo=${custNo}&grantIds=" + grantList,
            success: function(msg) {
//			alert(msg);
                $(":checkbox[name='" + checkName + "']").each(function() {
                    if (this.checked) $("#tr" + this.value).remove();
                });
                // parent.parent.GB_hide();
            },
            error: function(msg) {
                alert("Failed to delete the Grant. Please contact system administrator for help.");
            }
        });
    }

    function initGrantEditForm() {
        var grantId = arguments[0];
        tb_show("Edit Grant", "cust_pub_grant!showGrantEditForm.action?grantIdStr=" + grantId + "&custNo=${custNo}" + "&sessCustNo=${sessCustNo}" + "&TB_iframe=true&height=280&width=650", "thickbox");
    }
</script>
	<script src="${global_js_url}jquery/jquery.dialog.all.js"
		type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.datepicker.js"
		type="text/javascript"></script>
	<script src="${global_js_url}jquery/jquery.validate.js"
		type="text/javascript"></script>

	<script type="text/javascript"
		src="${global_js_url}customer/customer_validate.js"></script>
	<script type="text/javascript"
		src="${global_js_url}customer/customer_trigger.js"></script>
	<script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>
	<script type="text/javascript" src="${global_js_url}scm/divPicker.js"></script>
	<script type="text/javascript" src="${global_js_url}scm/deptPicker.js"></script>
</body>
</html>
