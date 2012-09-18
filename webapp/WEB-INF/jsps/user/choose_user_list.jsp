<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Choose User</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
	function chooseUser(userId){

		var groupId = parent.$("#groupId").val();
	
		if(groupId!=null&&groupId!=""&&groupId!="0"){
			$.ajax({
				type:"POST",
				url:"system/mail_group!checkUser.action?groupId="+groupId+"&userId="+userId,
				dataType:"json",
				data:$("#formCatPdtCategory").serialize(),
				success: function(msg){
					
					if(msg.message=="error"){
						alert("The data already exists, select the other data.");
						return;
					}else{
						var obj_subCategory = parent.$("#mail_address_iframe").contents();
						
						obj_subCategory.find("#userName").val($("#loginName"+userId).val());
						
						obj_subCategory.find("#emailAddress").val($("#email"+userId).val());	
						obj_subCategory.find("#departMent").val($("#deptId"+userId).val());	
						obj_subCategory.find("#status").val($("#status"+userId).val());	
						obj_subCategory.find("#userId").val(userId);		
						parent.$('#chooseUserDialog').dialog('close');
						parent.$('#chooseUserDialog').dialog('destory');
					}
				},
				error: function(msg){
					alert("System error! Please contact system administrator for help.");
					return;
				}
			});
		}else{
			var obj_subCategory = parent.$("#mail_address_iframe").contents();
			
			obj_subCategory.find("#userName").val($("#loginName"+userId).val());
			
			obj_subCategory.find("#emailAddress").val($("#email"+userId).val());	
			obj_subCategory.find("#departMent").val($("#deptId"+userId).val());	
			obj_subCategory.find("#status").val($("#status"+userId).val());	
			obj_subCategory.find("#userId").val(userId);		
			parent.$('#chooseUserDialog').dialog('close');
			parent.$('#chooseUserDialog').dialog('destory');
		}
		
	}
	$(document).ready(function(){  
	    $('tr:even >td').addClass('list_td2');
		
	});
</script>
</head>

<body>
	<b>Choose User:</b><br />
	<form id="mainForm" action="" method="get">
	<table width="300" border="0" cellpadding="0" cellspacing="0" class="list_table">
          <tr>
            <th width="150">user Login Name</th>
            <th>user email</th>
            </tr>
        </table>
	
    <table width="300" border="0" cellspacing="0" cellpadding="0" class="list_table2">
    <s:iterator value="pageuser.result">
    <tr>
    <td width="150">
    	<input type="hidden" id="loginName${userId}" value="${loginName}" />
    	<input type="hidden" id="email${userId}" value="${email}" />
    	<input type="hidden" id="deptId${userId}" value="${deptId}" />
    	<input type="hidden" id="status${userId}" value="${status}" />
        <a href="javascript:chooseUser('${userId}')">${loginName}&nbsp;</a>
    </td>  
    <td>
    	${email}&nbsp;
    </td> 
    </tr>
   </s:iterator>
    </table>
    </form>
    <div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx}/privileg/user!searchUserList.action" name="moduleURL"/>
			</jsp:include>
		</div>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</body>
</html>