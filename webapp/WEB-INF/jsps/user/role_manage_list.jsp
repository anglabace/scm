<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User list</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">
    var GB_ROOT_DIR = "./greybox/";
</script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script  language="JavaScript" type="text/javascript">
function cc(e, name){
	var  a =   document.getElementsByName(name);
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;
} 

function get_checked_str(name){
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++){
		if(a[i].checked){
			str += a[i].value+',';
		}
	}
	return str.substring(0,str.length-1);
}

$(document).ready(function(){   
	init();
	//复选框绑定: 全选|全不选
	    $("#check_all").click( function() {
	    	var allChoiceVal = "";
	       $(":checkbox").each(function() {
	          this.checked = $("#check_all").get(0).checked;
	          if(this.checked&&this.name=="delRouteId") {
	              if(allChoiceVal=="") {
	            	  allChoiceVal=this.value;
	              } else {
	            	  allChoiceVal = allChoiceVal+","+this.value;
	              }
	          }
	       }); 
	       parent.document.getElementById("choiceOption").value=allChoiceVal;  
	    });
	$('#delRoleTrigger').click(function(){
		var del_role_nos = get_checked_str("roleId");
		if(del_role_nos == '')
	{
		alert('Please select one at least!');
		return;
	}
	if(!confirm('Are you sure to delete?'))
	{
		return;
	}
	
	$.ajax({
		type: "POST",
		url: "user!delete.action",
		data: 'roleIds='+del_role_nos,
		success: function(msg){
			if(msg == 'success'){
			window.location.reload();
			}
		},
		error: function(msg){
			alert("Error: Delete role failed");
		}
	});
	return false;
		
	});
	});
//复选框选择事件
function choice(obj) {
	if(parent.document.getElementById("choiceRoleVal")!=null) {
		var allChoiceVal = parent.document.getElementById("choiceRoleVal").value;
		var id = obj.value;
		if(obj.checked) {
			if(allChoiceVal=="") {
				allChoiceVal=id;
			} else {
				allChoiceVal=allChoiceVal+","+id;
			}
			
		} else {
			var allChoiceValArr = allChoiceVal.split(",");
			allChoiceVal ="";
			if(allChoiceValArr==null||allChoiceValArr==""||allChoiceValArr.length==0) {
				allChoiceVal=allChoiceVal+id;
			} else {
				$.each(allChoiceValArr,function(k,v) {
					if(v!=id) {
						if(allChoiceVal=="") {
							allChoiceVal=allChoiceVal+v;
						} else {
							allChoiceVal=allChoiceVal+","+v;
						}
						
					}
				});
			}
		}
		parent.document.getElementById("choiceRoleVal").value=allChoiceVal;
	}
	
}

//复选框初始化
function init() {
	if(parent.document.getElementById("choiceRoleVal")!=null) {
		var allChoiceVal = parent.document.getElementById("choiceRoleVal").value;
		if(allChoiceVal!="") {
			var allChoiceValArr = allChoiceVal.split(",");
			$.each(allChoiceValArr,function(k,v) {
				var id = "#"+v;
				if($(id)!=null) {
					$(id).attr("checked",true);
				}
			});
		}
	}
	
}

</script>
</head>
<body class="content">
	<script type="text/javascript">
		<c:if test="${request.sign == 1}">
			alert(" You search's role not exist !");
		</c:if>
	</script>
<body class="content">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td style="padding-right: 17px;"></td>
	</tr>
	<tr>
		<td>
		<div style="margin-right: 17px;">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="list_table">
			<tr>
				<th width="46">
				<div align="left">
				<input name="checkbox" type="checkbox" id="check_all"/>
				<img id="delRoleTrigger" src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" />
				</div>
				</th>
				<th width="50">Role ID</th>
				<th width="150">Role Name</th>
				<th>Description</th>
			</tr>
		</table>
		</div>
		<div class="list_box" style="height: 340px;">
		<div id="userOfRoleDiv" name="userOfRoleDiv">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="list_table">
			<c:set var="rowcount" value="1"></c:set>
			<s:iterator value="pageRoleDTO.result">
			<c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
				<tr>
					<td width="46" ${tdclass}><input type="checkbox"
						name="roleDto.roleId_${roleId}" value="${roleId}" id="${roleId}" onclick="choice(this);"/></td>
					<td width="50" ${tdclass}>
					<div align="center" ><a
						href="user!getRolePSInf.action?roleId=${roleId}"
						target="mainFrame">${roleId}</a></div>
					</td>
					<td width="150" align="center" name="roleDto.roleName" ${tdclass}>&nbsp;${roleName}</td>
					<td align="center" name="roleDto.description" ${tdclass}>&nbsp;${description}</td>
				</tr>
				<c:set var="rowcount" value="${rowcount+1}"></c:set>
			</s:iterator>
		</table>
		</div>
		</div>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
				<jsp:param value="${ctx}/user!RoleList.action" name="moduleURL" />
			</jsp:include></div>
		</td>
	</tr>
</table>
</body></html>