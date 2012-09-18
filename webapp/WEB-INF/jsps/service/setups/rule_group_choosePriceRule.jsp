<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Choose price rule</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
 <script language="JavaScript" type="text/javascript">
 $(document).ready(function(){  
	    $('tr:even >td').addClass('list_td2'); 
	});
 function   cc(e)  
 {  
     var   a   =   document.getElementsByName("ids");  
     for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
 } 
 function clserule_group_choosePriceRule(){
	    parent.$('#choosePriceRuleDialog').dialog('close');
		parent.$('#choosePriceRuleDialog').dialog('destory');
 }
 function saveRuleToGroup(){
		var objSeq=$('[name="ids"]:checkbox:checked');
		var lenSeq = objSeq.length;
		if(!lenSeq){
			alert("Please select one item to continue your operation.");return;
		}
        var priceRuleIds = get_checked_str("ids");
        var sessionRuleGroupId='${sessionRuleGroupId}';
        $.ajax({
			url:"serv/price_rule_group!savePriceRuleToRuleGroupSession.action",
			type:"get",
			data:"priceRuleIds="+priceRuleIds+"&sessionRuleGroupId="+sessionRuleGroupId,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#deleteCategoryTrigger').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						 var ids = priceRuleIds.split(",");
						 var serviceType = parent.$('#clsId option:selected').text();
						 var html = "";
						 for(var i=0;i<ids.length;i++){
						 		var ruleName = $('#ruleName_'+ids[i]).val();
						 		var ruleType = $('#ruleType_'+ids[i]).val();
						 		var description = $('#description_'+ids[i]).val();
						 		html+='<tr id="'+ids[i]+'">';
				                html+='<td  width="46"><input type="checkbox" value="'+ids[i]+'" id="ids" name="ids"/></td>';
				                html+='<td  width="50" align="center">'+ids[i]+'</td>';
				                html+='<td width="193">'+ruleName+'</td>';
				                html+= '<td width="184">';
				                html+=serviceType+'&nbsp;';
				                html+='</td>';
				                html+='<td width="145">'+ruleType+'</td>';
				                html+='<td >&nbsp;'+description+'</td>';
				                html+='</tr>';
						 }
						 parent.$('#tableId').append(html);
						 parent.$('#choosePriceRuleDialog').dialog('close');
						 parent.$('#choosePriceRuleDialog').dialog('destory');
					}else{
						alert("System error! Please contact system administrator for help.");
					}
				}
			},
			error:function(data){
				alert("System error! Please contact system administrator for help.");
			},
			async:false
		});
		
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

<body>
	<b>Choose Service Rules: Service Rules List:</b><br/>
	<form id="mainForm" action="" method="get">
    <table width="300" border="0" cellspacing="0" cellpadding="0" class="list_table2">
   <tr>
    <th width="30"><input type="checkbox" name="packing2" value="checkbox"  onclick="cc(this)"/></th>
            <th width="50">Rule ID</th>
            <th>Rule Name</th>
    </tr>
    <s:iterator value="page.result">
    <tr>
    <td width="30" align="center"><input type="checkbox" name="ids" id="ids" value="${id }" /></td>
    
    
    <td width="50">
        ${id}&nbsp;
    </td>   
    <td>
        ${name}&nbsp;
    </td>  
    <input type="hidden" name="ruleName_${id }" id="ruleName_${id }" value="${name}"/> 
    <input type="hidden" name="ruleType_${id }" id="ruleType_${id }" value="${ruleType}"/> 
    <input type="hidden" name="description_${id }" id="description_${id }" value="${description}"/> 
    </tr>
   </s:iterator>
    </table>
    </form>
    <div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/serv/price_rule!priceRuleOfpriceRuleGroup.action" name="moduleURL"/>
		</jsp:include>
	</div>
	 <table border="0" cellspacing="0" cellpadding="0" align="center">
	  <tr>
    <td height="60"  align="center">
      <input type="submit" name="Submit62"class="style_botton"  value="Save" onclick="saveRuleToGroup()"/>
      <input type="submit" name="Submit622" value="Cancel"  class="style_botton" onclick="clserule_group_choosePriceRule()"/>
      </td>
  </tr>
	</table>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</body>
</html>