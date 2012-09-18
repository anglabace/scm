<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${global_url}" />
<link href="${global_css_url }scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }table.css" rel="stylesheet" type="text/css" />

<link href="${global_css_url }SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url }jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url }ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url }tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url }TabbedPanels.js"></script>

<link type="text/css" href="${global_js_url }jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url }jquery/ui/ui.datepicker.js" type="text/javascript"></script>
 <script   language="JavaScript" type="text/javascript">  
 $(document).ready(function(){  
	    $('tr:even >td').addClass('list_td2'); 
	});


 
  function   cc(e)  
  {  
      var   a   =   document.getElementsByName("mailId");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  }  
function addNewMailAddress(mailId){
		parent.$('#newMailAddressDialog').dialog({
			autoOpen: false,
			height: 320,
			width: 720,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	
		parent.$('#newMailAddressDialog').dialog("option", "open",function(){
			var htmlStr = '<iframe id="mail_address_iframe" src="${ctx}/system/mail_group!mailAddressAdd.action?id=${param['filter_EQI_groupId']}&mailId='+mailId+'" height="260" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#newMailAddressDialog').html(htmlStr);
		});								  
		parent.$('#newMailAddressDialog').dialog('open');
		
	};
function delMailAddress(){
	var del_nos = get_checked_str("mailId");
	if(del_nos == '')
	{
		alert('Please select one item to continue your operation.');
		return;
	}
	if(!confirm('Are you sure to delete?'))
	{
		return;
	}
	var dels = del_nos.split(",");
	for(var i=0;i<dels.length;i++){
		var del_no=dels[i];
		if(getCheckedAdd(del_no)){
			
			if(!isNaN(del_no)){
				parent.$('#delMailAddress').val(parent.$('#delMailAddress').val()+","+del_no);
			}
		}
		$("#"+del_no).remove();
	}
	
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
function getCheckedAdd(id){
	var isFlase=true;
	var add_nos = parent.$('#addMailAddress').val();
	if(add_nos!=null&&add_nos!=""){
		var adds = add_nos.split("<;>");
		var newAdds ="";
		for(var i=0;i<adds.length;i++){
			if(adds[i]!=null&&adds[i]!=""){
				if(("userId_"+adds[i])==id){
					isFlase=false;
				}else{
					newAdds+=newAdds+"<;>"+adds[i];
				}
			}
		}
		parent.$('#addMailAddress').val(newAdds);
	}
	return isFlase;
}

 </script>
</head>
<body>
<form method="post" id="listForm" action="">

 <table width="955" border="0" cellspacing="0" cellpadding="0" class="list_table" >
        <tr>
          <th width="46"> <div align="left">
            <input name="checkbox" type="checkbox"  onclick="cc(this)" />
            <a href="javascript:void(0);" onclick="delMailAddress()"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
          <th width="202">Email Address</th>
          <th width="273">User Name</th>
          <th width="278">Department</th>
          <th>Status</th>
        </tr>
      </table>
      <div class="frame_box" style="height:210px; ">
        <table width="955" border="0" cellspacing="0" cellpadding="0" class="list_table2" id="addressTable">
        <s:iterator value="mailAddressList">
          <tr id="${mailId }">
            <td width="46"><input type="checkbox" value="${mailId }" name="mailId" id="mailId"/></td>
            <td width="202" align="center">
            ${user.email}&nbsp;</td>
            <td width="273">${user.loginName }&nbsp;</td>
            <td width="278">
            <s:iterator value="department">
            	<s:if test="user.deptId==deptId">
            		${name}
            	</s:if>
            </s:iterator>
            &nbsp;</td>
            <td align="center">${user.status }&nbsp;</td>
          </tr>
          </s:iterator>
        </table>
      </div>
      <!--<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx}/system/mail_group!findAddressList.action" name="moduleURL"/>
			</jsp:include>
		</div>
	 --></form>
<div align="center">
	<input name="newMailAddressDialogTrigger" id="newMailAddressDialogTrigger" type="button" class="style_botton" value="New" onclick="addNewMailAddress('')"/>
</div>
</body>
</html>
