<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${global_url}" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"><!-- 
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2'); 
    
});

function   cc(e,ids)  
{
	var   a   =   document.getElementsByName(ids);
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
} 
function clsIdChange(){
	var sessionPriceRuleId = $("#sessionPriceRuleId").val();
	var clsId = $("#clsId option:selected").val();
	$.ajax({
		type:"POST",
		url:"serv/price_rule!clsIdChange.action?clsId="+clsId+"&sessionPriceRuleId="+sessionPriceRuleId,
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				
				if(msg.attributesList!=""||msg.attributesList!=null){
					$("#attributeId").empty(); 
					var html ="";
					for(var i=0;i<msg.attributesList.length;i++){
						var att = msg.attributesList[i];
						if(att.attributeName==null||att.attributeName=="null"){
							att.attributeName = ""
						}
						html+='<option value='+att.id+'>'+att.attributeName+'</option>';
					}
					$("#attributeId").append(html);
				}
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

function priceFormulasInput(id){
	var url="${ctx }/price_rule!formulaInput.action";
	if(id==-1){
		 
	}else{
		 $("#formulasId").val(id);
	}
	$("#priceRuleForm").attr("action",url);
	$("#priceRuleForm").submit();
}

function addMapping(){
	var attribute = $("#attributeId option:selected").val();
	if(attribute==""||attribute==null){
		alert("Please enter the correct 'Attribute'.");
		return;
	}
	var attributerName = $("#attributeId option:selected").text();
	$.ajax({
		type:"POST",
		url:"serv/price_rule!addPriceRuleAttrValueMappingSession.action?attributerName="+attributerName,
		data:$("#mappingForm").serialize(),
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				//window.location.reload();
				htmlStr='<tr id="mappDel_'+msg.id+'">';
				htmlStr+='<td width="46"><input type="checkbox" value="'+msg.id+'" name="mappIds"/></td>';
				htmlStr+='<td width="155" align="center">'+$("#attributeId option:selected").text()+'&nbsp;</td>';
				htmlStr+='<td width="193">'+$("#operater option:selected").text()+'&nbsp;</td>';
				htmlStr+='<td width="200">'+$("#valueA").val()+'&nbsp;</td>';
				htmlStr+='<td width="182">'+$("#valueB").val()+'&nbsp;</td>';
				htmlStr+='<td>'+$("#value").val()+'&nbsp;</td>';
				htmlStr+='</tr>';
			    $("#mappingTable").prepend(htmlStr);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
		}
	});
}

function delMapping(name,sessionPriceRuleId){
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
	$.ajax({
		type:"POST",
		url:"serv/price_rule!delPriceRuleAttrValueMappingSession.action?delStr="+del_nos+"&sessionPriceRuleId="+sessionPriceRuleId,
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				var del = del_nos.split(",");
				for(var i=0;i<del.length;i++){
					$("#mappDel_"+del[i]).remove();
				}
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		error: function(msg){
			alert("Failed to remove the item.Please contact system administrator for help.");
		}
	});
}

function delFormula(name,sessionPriceRuleId){
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
	$.ajax({
		type:"POST",
		url:"serv/price_rule!delFormulaSession.action?delStr="+del_nos+"&sessionPriceRuleId="+sessionPriceRuleId,
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				var del = del_nos.split(",");
				for(var i=0;i<del.length;i++){
					$("#formDel_"+del[i]).remove();
				}
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		error: function(msg){
			alert("Failed to remove the item.Please contact system administrator for help.");
		}
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


function save(){
	var name = $("#name").val();
	if(!name){
		alert("Please enter the Name.");return;
	}
	var id = $("#id").val();
	var url = "serv/price_rule!save.action";
	$.ajax({
		type:"POST",
		url:url,
		data:$("#priceRuleForm").serialize(),
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				location.href = "serv/price_rule!input.action?id="+msg.id;
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

--></script>
</head>
 
<body class="content" style="background-image:none;">
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
<div class="scm">
<div class="title_content">
  <div class="title">
  <s:if test="entity.priceRules.id!=null">Edit</s:if><s:else>New</s:else> Rule</div>
</div><div class="input_box">
<form id="priceRuleForm">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="113">Rule ID</th>
      <td width="426"><input id="id" type="text" class="NFText" size="20" value="${entity.priceRules.id }" disabled="disabled"/></td>
      <th width="100">Rule Type</th>
      <td width="144">
      <s:if test="entity.priceRules.ruleType==\"Price\"">
      	  <select name="priceRules.ruleType" style="width:132px;">
	        <option value="Price" selected="selected">Price</option>
	        <option value="Cost">Cost</option>
	      </select>
      </s:if>
      <s:else>
        	<select name="priceRules.ruleType" style="width:132px;">
		        <option value="Price">Price</option>
		        <option value="Cost" selected="selected">Cost</option>
		    </select>
      </s:else>
      </td>
	  </tr>
       <tr>
      <th valign="top">Rule Name</th>
      <td><input name="priceRules.name" id="name" type="text" value="${entity.priceRules.name}" class="NFText" size="20" /></td>
      <th>Service Type</th>
      <td>
      <s:if test="priceRules.id!=null">
       <select style="width:130px;" name="priceRules.clsId" id="clsId">
        <s:iterator value="dropDownDTO">
        	<s:if test="priceRules.clsId == id">
        		<option value="${id}" selected="selected">${name}</option>
        	</s:if>
       	</s:iterator>
       </select>
      </s:if>
      <s:else>
     	<s:select id="clsId" name="priceRules.clsId" list="dropDownDTO" listKey="id" listValue="name" cssStyle="width:130px;" value="priceRules.clsId" onchange="clsIdChange()"></s:select>	
      </s:else>
      	<input type="hidden" id="oldClsId" value="1"/>
      	</td>		
	  </tr>
	<tr>
      <th valign="top">Description</th>
      <td><textarea name="priceRules.description" class="content_textarea2" style="width:368px;">${entity.priceRules.description }</textarea></td>
      <td>&nbsp;</td>
      <td valign="top">&nbsp;</td>
	</tr>
  </table>
   <input name="priceRules.creationDate" type="hidden" value="${entity.priceRules.creationDate }" />
   <input name="priceRules.groupId" type="hidden" value="${entity.priceRules.groupId }" />
   <input name="priceRules.createdBy" type="hidden" value="${entity.priceRules.createdBy}" />
   <input id="sessionPriceRuleId" name = "sessionPriceRuleId" type="hidden" value="${sessionPriceRuleId}"/>
   <input id="formulasId" name = "formulasId" type="hidden"/>
   <input id="id" name = "priceRules.id" type="hidden" value="${entity.priceRules.id }"/>
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
                  <input name="checkbox"   type="checkbox"   onclick="cc(this,'formIds')" />
                   <img src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" onclick='delFormula("formIds",${sessionPriceRuleId});' /></div></th>
                <th width="155">Formula ID</th>
                <th width="193">Formula</th>
                <th width="284">Description</th>
                <th width="145">Created Date</th>
                <th>Created By</th>
              </tr>
            </table>
          </div></td>
        </tr>
        <tr>
          <td><div class="list_box" style="height:250px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
            <s:iterator value="entity.priceFormulasDTOList">
	                	<s:if test="priceFormulas.id==null">
	                	<tr id="formDel_${sessionId}">
	                		<td  width="46"><input type="checkbox" value="${sessionId}" name="formIds"/></td>
	               			<td  width="155" align="center">
	                		<a href="javaScript:void(0)" onclick="priceFormulasInput('${sessionId}')">new</a>
	                		</td>
	                		 <td width="193">${priceFormulas.formulaBrief }&nbsp;</td>
	                		<td width="284">${priceFormulas.desccription }&nbsp;</td>
	                		<td width="145"><s:date name="priceFormulas.creationDate" format="yyyy-MM-dd" /></td>
	                		<td >${createdByText}&nbsp;</td>
              			</tr>
	                	</s:if>
	                	<s:else>
	                	<tr id="formDel_${priceFormulas.id }">
	                		<td  width="46"><input type="checkbox" value="${priceFormulas.id}" name="formIds"/></td>
	                		<td  width="155" align="center">
	                		<a href="javaScript:void(0)" onclick="priceFormulasInput('${priceFormulas.id}')">${priceFormulas.id}</a>
	                		 	</td>
	                		<td width="193">${priceFormulas.formulaBrief }&nbsp;</td>
	                		<td width="284">${priceFormulas.desccription }&nbsp;</td>
	                		<td width="145"><s:date name="priceFormulas.creationDate" format="yyyy-MM-dd" /></td>
	                		<td >${createdByText}&nbsp;</td>
               			</tr>
	                	</s:else>
            </s:iterator>
            </table>
          </div></td>
        </tr>
       
      </table>
      <div align="center" style="padding:10px;">
        <input name="Submit32" type="button" class="style_botton" value="New" onclick="priceFormulasInput(-1)"/></div>
      <div align="center"></div>
    </div>
    
    
  <div class="dhtmlgoodies_aTab">
      <table width="972" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td>
      <form id="mappingForm">
      <table border="0" cellspacing="0" cellpadding="0" class="General_table">
        <tr>
          <td>Attribute</td>
          <td>
           <s:select list="attributesList" name="attriMapping.attributeId" id="attributeId" listValue="attributeName" listKey="id" cssStyle="width:112px;"></s:select>
          </td>
          <td>Operator</td>
          <td>
          	<s:select list="optionItemList" name="attriMapping.operator" id="operater" listValue="value" listKey="value" cssStyle="width:112px;"></s:select>
          </td>
          <td>Value A</td>
          <td><input name="attriMapping.valueFrom" id="valueA" type="text" class="NFText" size="15" /></td>
          <td>Value B</td>
          <td><input name="attriMapping.valueTo" id="valueB" type="text" class="NFText" size="15" /></td>
          <td>Mapping Value</td>
          <td><input name="attriMapping.value" id="value" type="text" class="NFText" size="15" /></td>
          <td><input name="addAttributeMapping" type="button" class="style_botton" value="Add" onclick="addMapping()"/></td>
        	  <input id="sessionPriceRuleId" name = "sessionPriceRuleId" type="hidden" value="${sessionPriceRuleId}"/>
        </tr>
      </table>
      </form>
      </td>
      </tr>
        <tr>
          <td><div style="margin-right:17px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
              <tr>
                <th width="46"><div align="left">
                  <input name="checkbox"   type="checkbox"   onclick="cc(this,'mappIds')" />
                   <img src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" onclick='delMapping("mappIds",${sessionPriceRuleId});' /></div></th>
                <th width="155">Attribute Name</th>
                <th width="193">Operator</th>
                <th width="200">Value A</th>
                <th width="182">Value B</th>
                <th >Mapping Value</th>
              </tr>
            </table>
          </div></td>
        </tr>
        <tr>
          <td><div class="list_box" style="height:230px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table" id="mappingTable">
            <s:iterator value="priceAttrValMapDTOList">
              <tr id="mappDel_${priceRuleValue.id}">
                <td width="46"><input type="checkbox" value="${priceRuleValue.id}" name="mappIds"/></td>
                <td width="155" align="center">${attributerName}&nbsp;</td>
                <td width="193">${priceRuleValue.operator }&nbsp;</td>
                <td width="200">${priceRuleValue.valueFrom }&nbsp;</td>
                <td width="182">${priceRuleValue.valueTo }&nbsp;</td>
                <td >${priceRuleValue.value }&nbsp;</td>
              </tr>
            </s:iterator>
            </table>
          </div></td>
        </tr>
       
      </table>
 
    
    
  </div>
</div>
<div class="button_box">
	<saveButton:saveBtn parameter="${operation_method}"
		disabledBtn='<input type="button" name="save" value="Save" class="search_input" disabled="disabled"/>'
		saveBtn='<input type="button" name="save" id="saveAllTrigger" value="Save" class="search_input" onclick="save()"/>'
	/> 
     <input type="button" name="cancel" id="cancelAllTrigger" value="Cancel" class="search_input" onclick="cance()"/>
</div>

 <script type="text/javascript"> 
initTabs('dhtmlgoodies_tabView1',Array('Formulas','Service Attribute Mapping'),0,998,320);
</script>
</div>
</body>
</html>