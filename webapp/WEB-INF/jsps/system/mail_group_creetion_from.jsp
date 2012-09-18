<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Mail Group</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>

 <script   language="JavaScript" type="text/javascript">  
 $(document).ready(function(){  
	    $('tr:even >td').addClass('list_td2'); 
	});


 
  function   cc(e)  
  {  
      var   a   =   document.getElementsByName("ids");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  }  
  
  function save(){
	  	var code = $("#groupCode").val();
	  	code=$.trim(code);
		if(code==null||code==''||!code){
			alert("Please enter the Email Group Code.");return;
		}
		var name = $("#groupName").val();
		name=$.trim(name);
		if(name==null||name==''||!name){
			alert("Please enter the Name.");return;
		}
		$("#groupName").val($.trim($("#groupName").val()));
		var id = $("#id").val();
		var url = "system/mail_group!save.action";
		$.ajax({
			type:"POST",
			url:url,
			data:$("#groupMailForm").serialize(),
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					location.href = "system/mail_group!input.action?id="+msg.id;
				}else{
					alert("System error! Please contact system administrator for help.");
				}
			},
			error: function(msg){
				alert("System error! Please contact system administrator for help.");
			}
		});
		
	}
	function cance(){
		if(confirm("Are you sure to continue?")){
			window.location.reload();
		}
	}

 </script>
</head>
 
<body class="content">

<div class="scm">
<div class="title_content">
  <div class="title">Email Group <span id="groupid"></span></div>
</div>
<div class="input_box">
		  <div class="content_box">
 
		    <form class="niceform" id="groupMailForm">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th width="160">Email Group Code</th>
                  <td><input name="mailGroup.groupCode" type="text" class="NFText" size="20" id="groupCode" value="${mailGroup.groupCode }"/></td>
                  <th width="150"> Name</th>
                  <td><input name="mailGroup.groupName" type="text" class="NFText" size="20" id="groupName" value="${mailGroup.groupName }"/></td>
                </tr>
                <tr>
                  <th rowspan="2">Description</th>
                  <td rowspan="2"><textarea name="mailGroup.description" class="content_textarea2">${mailGroup.description}</textarea></td>
                  <th>Status</th>
                  <td><select name="mailGroup.status">
                  <s:if test="mailGroup.status==\"ACTIVE\"">
                  	<option selected="selected">ACTIVE</option>
                    <option>INACTIVE</option>
                  </s:if>
                  <s:else>
                  	<option>ACTIVE</option>
                    <option selected="selected">INACTIVE</option>
                  </s:else>
                  </select></td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                  <tr>
      <th>Modified Date</th>
      <td><input name="modifyDate" type="text" class="NFText" value="<s:date name="mailGroup.modifyDate" format="yyyy-MM-dd" />" size="20" readonly="readonly" /></td>
      <th>Modified By </th>
      <td><input name="modifiedBy" type="text" class="NFText" value="${modifiedByText}" size="20" readonly="readonly"/></td>
    </tr>
              </table>
            </form>
		</div>
  </div>	
 
<div id="dhtmlgoodies_tabView1">

	    <div class="dhtmlgoodies_aTab">
	    	<iframe id="mailAddressList_iframe" name="mailAddressList_iframe" src="${ctx}/system/mail_group!findAddressList.action?groupId=${mailGroup.groupId}" width="100%" height="320" frameborder="0" scrolling="no"></iframe>
	    </div>
   
  </div>
<script type="text/javascript"> 
initTabs('dhtmlgoodies_tabView1',Array('Emails'),0,998,320);
 
</script>
<div class="button_box">
      <input type="submit" name="Save"  value="Save" class="search_input" onclick="save()"/>
      <input type="button" name="Cancel" value="Cancel" class="search_input" onclick="cance()"/>
</div>
</div>	
<div id="newMailAddressDialog" title="Add New Email"></div>
<div id="chooseUserDialog" title="Add New Email"></div>
</body>
</html>
