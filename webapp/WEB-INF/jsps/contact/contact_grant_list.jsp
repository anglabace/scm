<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/common/taglib.jsp"%>
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Contact Grant List</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
		<link href="${global_js_url}thickbox/thickbox.css" rel="stylesheet" type="text/css" />
        <link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet"  type="text/css" />
		<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
        <script type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
        <script type="text/javascript" src="${global_js_url}thickbox/thickbox-compressed.js"></script>
<script type="text/javascript">
$(function(){
	$("#newGrantDialogTrigger").click(function (){
		parent.$('#newGrantDialog').dialog('open');
	});
});
function show_edit(grantKey) {
	var href = 'contact/contact_grant!edit.action?grantKey='+grantKey +'&sessContactNo=${sessContactNo}';
	parent.$('#editGrantDialogTrigger').val(href);
	parent.$('#editGrantDialogTrigger').click();
}
</script>
	</head>
	<body>
		<div style="width: 940px; height: 350px;">
			<table border="0" cellpadding="0" cellspacing="0" class="list_table"
				style="width: 923px;">
				<tr>
					<th width="46">
						<div align="center">
							<input type="checkbox" name="checkbox4" value="grantcheck"
								onclick="toggleCheck(this, 'grantcheck')" />
							<img style="cursor: pointer"
								src="${global_image_url}file_delete.gif" alt="delete" width="16"
								height="16" border="0" onclick="delGrantTR('grantcheck');" />
						</div>
					</th>
					<th width="150">
						Project No
					</th>
					<th width="220">
						Project Name
					</th>
					<th width="75">
						Funding IC
					</th>
					<th width="180">
						Org
					</th>
					<th width="55">
						Amount
					</th>
					<th width="70">
						Issue Date
					</th>
					<th>
						Expiration Date
					</th>
				</tr>
			</table>
			<div style="width: 940px; height: 220px; overflow: scroll">
				<table border="0" cellpadding="0" cellspacing="0" class="list_table"
					style="width: 923px;">
					<s:iterator value="grantsMap">
						<tr id="tr">
							<td align="center" width="46">
								<input type="checkbox" name="grantcheck" value="${key}" />
							</td>
							<td width="150">
								&nbsp;
								<font size="1">
								<a
									href="javascript:show_edit('${key}');">${value.projectNo}</a></font>
							</td>
							<td title="${value.projectName}" width="220">
								&nbsp;<font size="1">
								<s:if test="%{value.projectName.length()>15}">
									<s:property value="value.projectName.substring(0,15)" />
								</s:if>
								<s:else>${value.projectName}</s:else></font>
							</td>
							<td width="75">
								&nbsp;<font size="1">${value.fundingIc}</font>
							</td>
							<td title="${value.organization}" width="180">
								&nbsp;<font size="1">
								<s:if test="%{value.organization.length()>15}">
									<s:property value="value.organization.substring(0,15)" />
								</s:if>
								<s:else>${value.organization}</s:else></font>
							</td>
							<td width="55">
								&nbsp;<font size="1">${value.amount}
							</td>
							<td align="center" width="70">
								&nbsp;
								<font size="1"><s:date name="value.issueDate" format="yyyy-MM-dd" /></font>
							</td>
							<td align="center">
								&nbsp;
								<font size="1"><s:date name="value.exprDate" format="yyyy-MM-dd" /></font>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/contact_grant!list.action"
						name="moduleURL" />
				</jsp:include>
			</div>
			<div align="center">
			  <input type="submit" value="New"  class="style_botton" id="newGrantDialogTrigger" />	
			</div>
		</div>
		<script type="text/javascript">
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

function delGrantTR(grantcheck)
{
	var checkName = grantcheck;
	if(!check_any_selected(checkName))
	{
		alert('Please choose at least one !');
		return;
	}
	if(!confirm("Are you sure to delete the checked grant(s)?"))
	{
		return;
	}
	var grantList = "";
	$(":checkbox[name='"+checkName+"']").each( function(){
		//$("#tr"+this.value).remove();
		if(this.checked) grantList += this.value+",";
	});
	$.ajax({
		type: "POST",
		url: "contact_grant!delete.action",
		data: "sessContactNo=${sessContactNo}&grant_id_str="+grantList,
		success: function(msg) {		    
			window.location.reload();
		},
		error: function(msg) {
			alert("Failed to delete the Grant. Please contact system administrator for help.");
		}
	});
}
function check_any_selected(name)
{
	var a = document.getElementsByName(name);
	var flag = false;
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			flag = true;
			break;
		}
	}
	return flag;
}
function get_checked_str(name)
{
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			str += a[i].value+',';
		}
	}
	return str.substring(0,str.length-1);
}
function initGrantEditForm()
{
	var grantId = arguments[0];
	tb_show("Edit Grant","cust_pub_grant!showGrantEditForm.action?grantIdStr="+grantId+"&custNo=${custNo}"+"&sessCustNo=${sessCustNo}"+"&TB_iframe=true&height=280&width=650","thickbox");
}
</script>
	</body>
</html>
