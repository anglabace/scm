<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
</head>

<body>   
<form id="customerInterestForm">
    <div><strong>Deciplines &amp; Research </strong></div>

		<div id="Disciplines" style="display:block;">
		<table width="470" border="0" cellpadding="0" cellspacing="0" class="General_table">
		      <tr>
		        <td colspan="2">
		          <select id="disciplineInterest" style="width:250px;">
		          		<option value="">Please select</option>
        				<s:iterator value="specDropDownist" >
        				<s:if test="name=='DECIPLINE_INTEREST'">
        				<s:iterator value="dropDownDTOs">
        				<option value="${id}" label="${name }">${name }</option>
        				</s:iterator>
        				</s:if>
        			</s:iterator>
        		</select>
		            <input type="button" id="disciplineInterestAddTrigger" class="style_botton"  value="Add" />
		            <input type="button" id="disciplineInterestDelTrigger" class="style_botton" value="Remove" />        </td>
		      </tr>
		
		      
		      </table> 
		      <div class="interest2">  
		            <ul id="disciplineInterestContainer">
					     <s:iterator value="myDRList">
					   <li><input value="${areaId}:${name}" name="decInterestList" type="checkbox">${name}</li>
					  </s:iterator>
				  </ul>
			  </div>
		</div>	  
	  <div><strong>Applications &amp; Techniques</strong></div>
		<div id="Applications" style="display:block;">
			  <table width="470" border="0" cellpadding="0" cellspacing="0" class="General_table">
	<tr>
		<td colspan="2">
		<select id="appInterest" style="width: 250px;">
			<option value="">Please select</option>
			<s:iterator value="specDropDownist">
				<s:if test="name=='APPLICATION_INTEREST'">
					<s:iterator value="dropDownDTOs">
						<option value="${id}" label="${name }">${name }</option>
					</s:iterator>
				</s:if>
			</s:iterator>
		</select> <input type="button" id="appInterestAddTrigger" class="style_botton"
			value=" Add" /> <input type="button" id="appInterestDelTrigger"
			class="style_botton" value="Remove" /></td>
	</tr>
</table>
			  <div class="interest2">  
			    <ul id="appInterestContainer">
				      <s:iterator value="myATList">
					   <li><input value="${areaId}:${name}" name="appInterestList" type="checkbox">${name}</li>
					  </s:iterator>
			    </ul>
		      </div> 
	   </div>
	  
   <div align="center">
    <input type="button" name="Submit4"  class="style_botton" value="Save" onclick="saveMoreInfoIntrst();" />
    <input  type="button" name="close" value="Cancel" class="style_botton" onclick="javascript:parent.$('#areaInterestDialog').dialog('close');"  /> 
  </div>
</form>

<script language="javascript" type="text/javascript">
function saveMoreInfoIntrst()
{
	var formStr = "custNo=${custNo}&sessCustNo=${sessCustNo}";
	// serialize interest form
	$('#disciplineInterestContainer :input').each(function(){
		formStr += '&' + $(this).attr('name') + '=' + $(this).attr('value');
	});
	$('#appInterestContainer :input').each(function(){
		formStr += '&' + $(this).attr('name') + '=' + $(this).attr('value');
	});
	$.ajax({
		type: "POST",
		url: "cust_area_interest!save.action",
		data: formStr,
		success: function(msg) {
			parent.$('#areaInterestDialog').dialog('close');
		},
		error: function(msg) {
		}
	});
}
$(function(){
/********************** set area of interest add/remove event *****************************/
	$('#disciplineInterestAddTrigger')
		.click(function(){
			var interestSel = $('#disciplineInterest').get(0);
			var interestOpt = interestSel.options[interestSel.selectedIndex];
			var newInterest = '<li><input  type="checkbox" value="'+ interestOpt.value + ':' + interestOpt.text + '" name="decInterestList"/>'+ interestOpt.text +'</li>';
			if(interestOpt.value == '')	return;
			
			var existFlag = false;
			$('#disciplineInterestContainer :input').each(function(){
				tmpArr = this.value.split(':', 2);
				areaId = tmpArr[0];
				if(areaId == interestOpt.value)
				{
					existFlag = true;
				}
			});
			if(existFlag === false)
				$('#disciplineInterestContainer').append(newInterest);
			else
				alert("'"+interestOpt.text+"' has been already added.");
		});
	
	$('#disciplineInterestDelTrigger')
		.click(function(){
			$('#disciplineInterestContainer :input').each(function(){
				if(this.checked)
				{
					$(this).parent().remove();
				}
			});
		});
		
	$('#appInterestAddTrigger')
		.click(function(){
			var interestSel = $('#appInterest').get(0);
			var interestOpt = interestSel.options[interestSel.selectedIndex];
			var newInterest = '<li><input  type="checkbox" value="'+ interestOpt.value + ':' + interestOpt.text +'" name="appInterestList"/>'+ interestOpt.text +'</li>';
			if(interestOpt.value == '')	return;
			
			var existFlag = false;
			$('#appInterestContainer :input').each(function(){
				tmpArr = this.value.split(':', 2);
				areaId = tmpArr[0];
				if(areaId == interestOpt.value)
				{
					existFlag = true;
				}
			});
			if(existFlag === false)
				$('#appInterestContainer').append(newInterest);
			else
				alert("'"+interestOpt.text+"' has been already added.");
		});
		
	$('#appInterestDelTrigger')
		.click(function(){
			$('#appInterestContainer :input').each(function(){
				if(this.checked)
				{
					$(this).parent().remove();
				}
			});
		});
});
</script>
</body>
</html>
