<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}thickbox/thickbox.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script src="${global_js_url}TabbedPanels.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}thickbox/thickbox-compressed.js"></script>
   
</head>

<body>
<div id="TabbedPanels1" class="TabbedPanels" style="overflow:auto;overflow: auto">
			<ul class="TabbedPanelsTabGroup">
				<li class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="0"
					id="TabbedPanelsTab1">
					Grants
				</li>
				<li class="TabbedPanelsTab" tabindex="1" id="TabbedPanelsTab2">
					Publications
				</li>
			</ul>
			<div class="TabbedPanelsContentGroup">
				<div style="display: block; overflow:hidden; margin-top: 10px;" class="TabbedPanelsContent"
					id="TabbedPanelsContent1">        
                     <iframe id="grantFrame" src="cust_pub_grant!listGrant.action?custNo=${custNo}&sessCustNo=${sessCustNo}" width="100%" height="400px" frameborder="0"></iframe>
				</div>
				<div style="display: block; overflow:hidden; margin-top: 10px;" class="TabbedPanelsContent"
					id="TabbedPanelsContent2">   
                     <iframe id="publicationFrame" src="cust_pub_grant!listPub.action?custNo=${custNo}&sessCustNo=${sessCustNo}" width="100%" height="400px" frameborder="0"></iframe>
				</div>
            </div>
      </div>
      
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>

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

function delGrantTR()
{
	if(!confirm("Are you sure to delete checked grants?"))
	{
		return;
	}
	var checkName = arguments[0];
	var grantList = "";
	$(":checkbox[name='"+checkName+"']").each( function(){
		//$("#tr"+this.value).remove();
		if(this.checked) grantList += this.value+",";
	});
	grantList = grantList.replace(new RegExp(",$", 'g'), '');
	
	$.ajax({
		type: "POST",
		url: "cust_pub_grant!delGrant.action",
		data: "custNo=${custNo}&grantIds="+grantList,
		success: function(msg) {
//			alert(msg);
			$(":checkbox[name='"+checkName+"']").each( function(){
				if(this.checked) $("#tr"+this.value).remove();
			});
			// parent.parent.GB_hide();
		},
		error: function(msg) {
			alert("Failed to delete the Grant. Please contact system administrator for help.");
		}
	});
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

function initGrantEditForm()
{
	var grantId = arguments[0];
	tb_show("Edit Grant","cust_pub_grant!showGrantEditForm.action?grantIdStr="+grantId+"&custNo=${custNo}"+"&sessCustNo=${sessCustNo}"+"&TB_iframe=true&height=280&width=650","thickbox");
}

function initPubEditForm()
{
	var pubId = arguments[0];
	tb_show("Edit Publication","cust_pub_grant!showPubEditForm.action?pubIdStr="+pubId+"&custNo=${custNo}"+"&sessCustNo=${sessCustNo}"+"&TB_iframe=true&height=250&width=600","thickbox");
}

</script>
</body>
</html>
