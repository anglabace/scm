<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>formula detail</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${global_url}" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2'); 
    
});
function ShowElement(element,str)
{
var oldhtml = element.innerHTML;
 
if (oldhtml.indexOf('&lt;')!=-1||oldhtml.indexOf('&gt;')!=-1)
{
   if((oldhtml.indexOf('&lt;')!=-1)&&(oldhtml.indexOf('&gt;')!=-1))
   {
	  if (oldhtml.indexOf('&lt;&gt;')!=-1)
      {
          oldhtml = element.innerHTML.replace(/&lt;&gt;/g,"<>");
      }
	  else
	  {
		  oldhtml="input error!";  
	  }
   }
 
   else if (oldhtml.indexOf('&lt;')!=-1)
   {
     oldhtml = element.innerHTML.replace(/&lt;/g,"<");
   }
   else if(oldhtml.indexOf('&gt;')!=-1)
   {
	 oldhtml = element.innerHTML.replace(/&gt;/g,">");
   }
 
}
 
 
 
var newobj = document.createElement('input');
newobj.type = 'text';
if (str=='value')
{
  newobj.style.width='150px';	
}
else
{
  newobj.style.width='250px';
}
newobj.style.border='1px solid #ccc'
newobj.value=oldhtml;
newobj.onblur = function(){
	
 
//var str1=this.value.replace(/&lt;/g,"<");
//str1=this.value.replace(/\>/g,"&gt;");
 
	
element.innerHTML = this.value ? this.value : oldhtml; 
}
element.innerHTML = '';
element.appendChild(newobj);
newobj.focus();
}
</script>

<script language="javascript" type="text/javascript"> 
function   cc(e)  
{
	var   a   =   document.getElementsByName('ids');
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}
function save(){
	var name = $("#name").val();
	if(!name){
		alert("Please enter the formula.");return;
	}
	var sessionPriceRuleId = $("#sessionPriceRuleId").val();
	var clsId = $("#clsId").val();
	var id = $("#id").val();
	var url = "serv/price_rule!saveFormulaToSession.action";
	$.ajax({
		type:"POST",
		url:url,
		data:$("#formulaForm").serialize(),
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				if(isNaN(sessionPriceRuleId)||sessionPriceRuleId==null||sessionPriceRuleId==""){
					window.location.href="serv/price_rule!input.action?sessionPriceRuleId="+sessionPriceRuleId;
				}else{
					window.location.href="serv/price_rule!input.action?id="+sessionPriceRuleId+"&sessionPriceRuleId="+sessionPriceRuleId;
				}
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
		}
	});
	
}
function changeAttribute(){
	var sessionPriceRuleId = $("#sessionPriceRuleId").val();
	var ruleId = sessionPriceRuleId;
	var attributeId = $("#attributeId option:selected").val();
	$.ajax({
		type:"POST",
		url:"serv/price_rule!searchMappingValueOfCriteria.action?sessionPriceRuleId="+sessionPriceRuleId+"&ruleId="+ruleId+"&attributesId="+attributeId,
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				if(msg.attriMappingList!=""||msg.attriMappingList!=null){
					$("#attriMappDiv").empty(); 
					var html ="";
					if(msg.attriMappingList.length>0){
						html+='<select id="attributeValue" name="attributeValue">';
						for(var i=0;i<msg.attriMappingList.length;i++){
							var att =msg.attriMappingList[i];
							if(att.value==null||att.value=="null"){
								att.attributeName = ""
							}
							html+='<option value="'+att.value+'">'+att.value+'</option>';
						}
						html+='</select>';
					}else{
						html +='<input name="attributeValue" id="attributeValue" type="text" class="NFText" size="20" />';
					}
					$("#attriMappDiv").append(html);
				}
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
function delCr(name){
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
	var sessionFormulaItemId = $("#sessionFormulaItemId").val();
	$.ajax({
		type:"POST",
		url:"serv/price_rule!delCriteriaSession.action?delStr="+del_nos+"&sessionFormulaItemId="+sessionFormulaItemId,
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				var del = del_nos.split(",");
				for(var i=0;i<del.length;i++){
					$("#"+del[i]).remove();
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
function add(){
	var attributeId = $("#attributeId option:selected").val();
	if(!attributeId){
		alert("Please enter the attribute.");return;
	}
	var value = $("#attributeValue").val();
	if(!value){
		alert("Please enter the value.");return;
	}
	var attributeText = $("#attributeId option:selected").text();
	var sessionFormulaItemId = $("#sessionFormulaItemId").val();
	var url = "serv/price_rule!addCriteriaSession.action?sessionFormulaItemId="+sessionFormulaItemId+"&priceFormulaCriterias.attributeId="+attributeId+"&priceFormulaCriterias.value="+value;
	$.ajax({
		type:"POST",
		url:url,
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				var html='<tr id="'+msg.id+'">';
				html+='<td  width="46"><input type="checkbox" value="'+msg.id+'" name="ids"/></td>'
				html+='<td  width="155">'+attributeText+'</td>';
				html+='<td width="193"><div align="center" ondblclick="ShowElement(this,"value")">'+value+'</div></td>';
				html+='<td width="284"><div align="center" ondblclick="ShowElement(this,"desc")"></div></td>';
				html+='<td width="145"></td>';
				html+='<td ></td>';
				html+='</tr>';
                $("#addTable").append(html);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
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
</script>
</head>
 
<body class="content" style="background-image:none;">
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
<div class="scm">
<div class="title_content">
  <div class="title"><s:if test="id==null">New</s:if><s:else>Edit</s:else> Formula</div>
</div><div class="input_box">
<form id="formulaForm">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="113">Formula ID</th>
      <td width="426"><input name="priceFoemulasId" type="text" class="NFText" size="20" value="${priceFormulasDTO.priceFormulas.id }" disabled="disabled"/>
      	<input type ="hidden" name = "priceFormulasDTO.priceFormulas.id" value="${priceFormulasDTO.priceFormulas.id }"/>
      	<input type ="hidden" name = "priceFormulasDTO.priceFormulas.ruleId" id="id" value="${priceFormulasDTO.priceFormulas.ruleId }"/>
     	<input type ="hidden" name = "sessionPriceRuleId" id="sessionPriceRuleId" value="${sessionPriceRuleId }"/>
     	<input type ="hidden" name = "sessionFormulaItemId" id="sessionFormulaItemId" value="${sessionFormulaItemId }"/>
     	<input type ="hidden" name = "formulasKey" id="formulasKey" value="${priceFormulasDTO.sessionId }"/>
     	<input type ="hidden" name = "priceFormulasDTO.sessionId " id="priceFormulasDTO.sessionId " value="${priceFormulasDTO.sessionId }"/>
     	<input type ="hidden" name = "clsId" id="clsId" value="${clsId }"/>
      </td>
      <th width="100">Formula</th>
      <td width="244"><input name="priceFormulasDTO.priceFormulas.formulaBrief" id="name" type="text" class="NFText" size="20" value="${priceFormulasDTO.priceFormulas.formulaBrief }"/>
        <input name="Submit" type="button" class="style_botton" value="Define" onclick="window.openiframe('serv/price_rule!formulaItemIput.action?clsId=${clsId}&sessionPriceRuleId=${sessionPriceRuleId}&id=${priceFormulasDTO.priceFormulas.id }&sessionFormulaItemId=${sessionFormulaItemId}',620,460)"/>
      </td>
	  </tr>
    
	<tr>
      <th valign="top">Description</th>
      <td><textarea name="priceFormulasDTO.priceFormulas.desccription" class="content_textarea2" style="width:368px;">${priceFormulasDTO.priceFormulas.desccription}</textarea></td>
      <td>&nbsp;</td>
      <td valign="top">&nbsp;</td>
	</tr>
       <tr>
      <th valign="top">Modified Date</th>
      <td><input name="priceFormulasDTO.priceFormulas.modifyDate" type="text" class="NFText" value="<s:date name="priceFormulasDTO.priceFormulas.modifyDate" format="yyyy-MM-dd" />" size="20" disabled="disabled"/></td>
      <th>Modified By</th>
      <td><input name="priceFormulasDTO.modifiedByText" type="text" class="NFText" value="${priceFormulasDTO.modifiedByText}" size="20" readonly="readonly"/>
      	  <input name="priceFormulasDTO.priceFormulas.modifiedBy" type="hidden" value="${priceFormulasDTO.priceFormulas.modifiedBy}"/>
      </td>		
	  </tr>
         <tr>
      <th valign="top">Created Date</th>
      <td><input name="priceFormulasDTO.priceFormulas.creationDate" type="text" class="NFText" value="<s:date name="priceFormulasDTO.priceFormulas.creationDate" format="yyyy-MM-dd" />" size="20" disabled="disabled"/></td>
      <th>Created By</th>
      <td><input name="priceFormulasDTO.createdByText" type="text" class="NFText" value="${priceFormulasDTO.createdByText}" size="20" readonly="readonly"/>
      	  <input name = "priceFormulasDTO.priceFormulas.createdBy" type="hidden" value="${priceFormulasDTO.priceFormulas.createdBy}"/>
      </td>		
	  </tr>
  </table>
  </form>
</div>
  <div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
      <table width="967" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td><table border="0" cellspacing="0" cellpadding="0" class="General_table">
          <tr>
            <th>Attribute</th>
            <td>
            	<s:select list="attributesList" name="attributeId" id="attributeId" listValue="attributeName" listKey="id" cssStyle="width:112px;" onchange="changeAttribute()"></s:select>
            </td>
            <th>Value</th>
            <td>
            <div id="attriMappDiv">
            <s:if test="attriMappingList==null">
            		<input name="attributeValue" id="attributeValue" type="text" class="NFText" size="20" />
            	</s:if>
            	<s:else>
            		<s:select list="attriMappingList" name="attributeValue" id="attributeValue" listValue="value" listKey="value" cssStyle="width:120px;"></s:select>
            	</s:else>
            </div>
            </td>
            <td><input name="Submit2" type="button" class="style_botton" value="Add" onclick="add()"/></td>
          </tr>
        </table></td>
        </tr>
        <tr>
          <td><div style="margin-right:17px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
              <tr>
                <th width="46"><div class="quote_dele">
                <input name="checkbox2"   type="checkbox"   onclick="cc(this)" />
                <img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" onclick="delCr('ids')"/> </div>
             </th>
                <th width="155">Attribute</th>
                <th width="193">Value</th>
                <th width="284">Description</th>
                <th width="145">Created Date</th>
                <th>Created By</th>
              </tr>
            </table>
          </div></td>
        </tr>
        <tr>
          <td><div class="list_box" style="height:250px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table" id="addTable">
            <s:iterator value="priceFormulasDTO.priceFormlaCriteriasDTO">
              <tr id="${priceFormulasCriterias.id}">
                <td  width="46"><input type="checkbox" value="${priceFormulasCriterias.id }" name="ids"/></td>
                <td  width="155">${attributerName}</td>
                <td width="193"><div align="center" ondblclick="ShowElement(this,'value')">${priceFormulasCriterias.value}</div></td>
                <td width="284"><div align="center" ondblclick="ShowElement(this,'desc')">${priceFormulasCriterias.description}</div></td>
                <td width="145"><s:date name="priceFormulasCriterias.creationDate" format="yyyy-MM-dd" /></td>
                <td >${createdByText}</td>
              </tr>
            </s:iterator>
            </table>
          </div></td>
        </tr>
       
      </table>
      <div align="center"></div>
    </div>
  </div>
</div>
<div class="button_box">
      <input type="button" name="Submit123"  value="Save" class="search_input" onclick="save()"/>
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.location.href='serv/price_rule!input.action?id=${priceFormulasDTO.priceFormulas.ruleId }&sessionPriceRuleId=${sessionPriceRuleId }'"/>
</div>

</body>
</html>
<script type="text/javascript"> 
initTabs('dhtmlgoodies_tabView1',Array('Criteria'),0,998,320);
</script>