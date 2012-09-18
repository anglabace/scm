<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Rule Group</title>
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

  function delPriceRule(name){
		var del_nos = get_checked_str(name);
		if(del_nos == '')
		{
			alert('Please select one item to continue your operation.');
			return;
		}
		if(!confirm('Are you sure to delete?'))
		{
			return;
		}
		var sessionRuleGroupId = $("#sessionRuleGroupId").val();
		$.ajax({
			type:"POST",
			url:"serv/price_rule_group!delPriceRuleToruleGroupSession.action?delStr="+del_nos+"&sessionRuleGroupId="+sessionRuleGroupId,
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					var dels = del_nos.split(",");
					for(var i=0;i<dels.length;i++){
						$("#"+dels[i]).remove();
					}
				}else{
					alert("Failed to remove the item.Please contact system administrator for help.");	
				}
			},
			error: function(msg){
				alert("Failed to remove the item.Please contact system administrator for help.");
			}
		});
	}
  
  function clsIdChange(){
		var sessionRuleGroupId = $("#sessionRuleGroupId").val();
		var clsId = $("#clsId option:selected").val();
		$.ajax({
			type:"POST",
			url:"serv/price_rule_group!clsIdChange.action?clsId="+clsId+"&sessionRuleGroupId="+sessionRuleGroupId,
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					$("#oldClsId").val(clsId);
				}else{
						
					$("#clsId").attr('value',$("#oldClsId").val()); 
				}
			},
			error: function(msg){
				
				$("#clsId").attr('value',$("#oldClsId").val()); 
			}
		});
	}
	
  function save(){
		var name = $("#groupName").val();
		name=$.trim(name);
		if(name==null||name==''||!name){
			alert("Please enter the groupName.");return;
		}
		$("#groupName").val($.trim($("#groupName").val()));
		var id = $("#id").val();
		var url = "serv/price_rule_group!save.action";
		$.ajax({
			type:"POST",
			url:url,
			data:$("#ruleGroupForm").serialize(),
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					location.href = "serv/price_rule_group!input.action?groupId="+msg.id;
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

	function addTrigger(){
		$('#choosePriceRuleDialog').dialog({
			autoOpen: false,
			height: 480,
			width: 360,
			modal: true,
			bgiframe: true,
			buttons: {}
		}); 
		var clsId=$("#clsId option:selected").val();
		var sessionRuleGroupId = $("#sessionRuleGroupId").val();
		$('#choosePriceRuleDialog').dialog("option", "open",function(){
			var groupId = $("#groupId").val();
			if(groupId==''||groupId==null||groupId=="null"){
				var htmlStr = '<iframe id="choosePriceRuleDialog" src="${ctx}/serv/price_rule!priceRuleOfpriceRuleGroup.action?callBackName=choosePriceRuleOfRuleGroup&clsId='+clsId+'&sessionRuleGroupId='+sessionRuleGroupId+'" height="400" width="320" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			}else{
				var htmlStr = '<iframe id="choosePriceRuleDialog" src="${ctx}/serv/price_rule!priceRuleOfpriceRuleGroup.action?callBackName=choosePriceRuleOfRuleGroup&clsId='+clsId+'&sessionRuleGroupId='+sessionRuleGroupId+'&groupId='+groupId+'" height="400" width="320" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			}
			
			$('#choosePriceRuleDialog').html(htmlStr);
		});	
		$('#choosePriceRuleDialog').dialog('open');	
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
</head>
 
<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title"><s:if test="groupId==null">New Service Rules Group</s:if><s:else>Edit Service Rules Group ${priceRuleGropus.groupId}</s:else></div>
</div><div class="input_box">
<form id="ruleGroupForm">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="113">Group ID</th>
      <td width="426"><input name="entity.priceRuleGropus.groupId" id="groupId" type="text" class="NFText" size="20" value="${priceRuleGropus.groupId}" readonly="readonly"/></td>
      <th width="100">Group Name</th>
      <td width="144"><input name="entity.priceRuleGropus.groupName" id="groupName" type="text" class="NFText" size="20" value="${priceRuleGropus.groupName }"/></td>
	  </tr>
	<tr>
	  <th valign="top">Description</th>
	  <td><textarea name="entity.priceRuleGropus.description" class="content_textarea2" style="width:368px;">${priceRuleGropus.description }</textarea></td>
	  <th width="100">Service Type</th>
	  <td valign="top">
	  <s:if test="priceRuleGropus.groupId!=null">
       <select style="width:130px;" name="entity.priceRuleGropus.clsId" id="clsId">
        <s:iterator value="dropDownDTO">
        	<s:if test="entity.priceRuleGropus.clsId == id">
        		<option value="${id}" selected="selected">${name}</option>
        	</s:if>
       	</s:iterator>
       </select>
      </s:if>
      <s:else>
     	<s:select id="clsId" name="entity.priceRuleGropus.clsId" list="dropDownDTO" listKey="id" listValue="name" cssStyle="width:130px;" value="priceRuleGropus.clsId" onchange="clsIdChange()"></s:select>	
      </s:else>
      	<input type="hidden" id="oldClsId" value="1"/>
	  </td>
	  </tr>
       <tr>
      <th> Modified Date</th>
      <td><input name="modifyDate" type="text" class="NFText" value="<s:date name="priceRuleGropus.modifyDate" format="yyyy-MM-dd" />" size="20" readonly="readonly" /></td>
      <th>Modified By </th>
      <td colspan="2"><input name="modifiedBy" type="text" class="NFText" value="${modifiedByText}" size="20" readonly="readonly"/></td>
    </tr>
    <tr>
      <th height="22">Date Created </th>
      <td><input name="entity.priceRuleGropus.creationDate" type="text" class="NFText" value="<s:date name="priceRuleGropus.creationDate" format="yyyy-MM-dd" />" size="20" readonly="yes" /></td>
      <th>Created By </th>
      <td colspan="2"><input name="createdByText" type="text" class="NFText" value="${createdByText}" size="20" readonly="readonly"/>
      		<input name="entity.priceRuleGropus.createdBy" type="hidden" class="NFText" value="${priceRuleGropus.createdBy }" size="20" readonly="readonly"/>
      		<input id="sessionRuleGroupId" name = "sessionRuleGroupId" type="hidden" value="${sessionRuleGroupId}"/>
      	</td>
    </tr>
  </table>
  </form>
</div>
  <div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
      <table width="972" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div style="margin-right:17px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
              <tr>
                <th width="46"><div align="left">
                  <input name="checkbox"   type="checkbox"   onclick="cc(this)" />
                  <img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" onclick="delPriceRule('ids')"/></div></th>
                <th width="50">Rule ID</th>
                <th width="193">Rule Name</th>
                <th width="184">Service Type</th>
                <th width="145">Rule Type</th>
                <th>Description</th>
              </tr>
            </table>
          </div></td>
        </tr>
        <tr>
          <td><div class="list_box" style="height:250px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table" id="tableId">
            <s:iterator value="priceRuleList">
              <tr id="${id }">
                <td  width="46"><input type="checkbox" value="${id }" name="ids" id="ids"/></td>
                <td  width="50" align="center"><a href="serv/price_rule!input.action?id=${id}&operation_method=edit">${id}</a></td>
                <td width="193">${name}</td>
                <td width="184">
					    <s:iterator value="dropDownDTO">
				        <s:if test="id==clsId">
				         ${name}&nbsp;
				        </s:if>
				        </s:iterator>
				</td>
                <td width="145">${ruleType}</td>
                <td >&nbsp;${description }</td>
              </tr>
            </s:iterator>
            </table>
          </div></td>
        </tr>
       
      </table>
      <div align="center" style="padding:10px;">
        <input name="addPriceRule" id="addPriceRule" type="button" class="style_botton" value="New" onclick="addTrigger()"/></div>
      <div align="center"></div>
    </div>
</div>
<div class="button_box">
      <input type="submit" name="Save"  value="Save" class="search_input" onclick="save()"/>
      <input type="button" name="Cancel" value="Cancel" class="search_input" onclick="cance()"/>
</div>
<div id="choosePriceRuleDialog" title="Please Choose Service Rules"></div>

</div>
<script type="text/javascript"> 
initTabs('dhtmlgoodies_tabView1',Array('Service Rules'),0,998,320);
</script>
</body>
</html>
