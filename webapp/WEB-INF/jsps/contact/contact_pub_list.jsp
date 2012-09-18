<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/common/taglib.jsp"%>
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>scm</title>
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
        <script type="text/javascript"
			src="${global_js_url}thickbox/thickbox-compressed.js"></script>
<script type="text/javascript">  
$(function(){
	/** publications **/
	$("#newPublicationDialogTrigger").click(function (){
		parent.$('#newPublicationDialog').dialog('open');
	});
	
});

function show_edit(pubKey) {
	var href = 'contact/contact_pub!edit.action?pubKey='+pubKey +'&sessContactNo=${sessContactNo}';
	parent.$('#editPublicationDialogTrigger').val(href);
	parent.$('#editPublicationDialogTrigger').click();
}

</script>

	</head>
	<body>
		<div style="width: 940px; height: 350px; ">
				<table border="0" cellpadding="0" cellspacing="0" class="list_table"
					style="width:923px;">
					<tr>
						<th width="46">
							<div align="center">
								<input type="checkbox" value="pubcheck"
									onclick="toggleCheck(this, 'pubcheck')" />
								<img style="cursor: pointer" src="${global_image_url}file_delete.gif" alt="s" width="16"
									height="16" border="0" onclick="delPubTR('pubcheck');" />
							</div>
						</th>
						<th width="45%">
							Title
						</th>
						<th>
							Publication Name
						</th>
						<!--  <th width="10%">Issue Date</th> -->
						<th width="5%">
							URL
						</th>

					</tr>
				</table>
			<div style="width: 940px; height:220px; overflow:scroll">
				<table border="0" cellpadding="0" cellspacing="0" class="list_table"
					style="width:923px;">
					<s:iterator value="pubsMap">
						<tr	id="trpub${key}">
							<td align="center" width="46">
								<input type="checkbox" name="pubcheck"
									value="${key}" />
							</td>
							<td title="${value.title}" width="45%">
								&nbsp;
								<a href="javascript:show_edit('${key}')"><s:if
										test="%{value.title.length()>60}">
										<s:property value="value.title.substring(0,60)" />
									</s:if> <s:else>${value.title}</s:else> </a>
							</td>
							<td title="${value.publicationName}">
								&nbsp;
								<font size="1">
								<s:if test="%{value.publicationName.length()>45}">
									<s:property value="value.publicationName.substring(0,45)" />
								</s:if>
								<s:else>${value.publicationName}</s:else></font>
							</td>
							<td width="5%">
								&nbsp;
								<a href="<s:property value="value.url"/>" target="_blank"><img
										src="${global_image_url}link.gif" border="0" /> </a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
				<jsp:param value="${ctx}/contact_pub!list.action" name="moduleURL"/>
				</jsp:include>	
			</div>
			<div align="center">
			  <input type="submit" value="New"  class="style_botton" id="newPublicationDialogTrigger" />			  
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

function delPubTR(grantcheck)
{
	var checkName = grantcheck;
	if(!check_any_selected(checkName))
	{
		alert('Please choose at least one !');
		return;
	}
	if(!confirm("Are you sure to delete the checked pub(s)?"))
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
		url: "contact_pub!delete.action",
		data: "sessContactNo=${sessContactNo}&pub_id_str="+grantList,
		success: function(msg) {		    
			window.location.reload();
		},
		error: function(msg) {
			alert("Failed to remove the Pubs. Please contact system administrator for help.");
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
</script>
	</body>
</html>
