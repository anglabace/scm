<!-- {get_spec_selects value="ORIGINAL_SOURCE,TIME_ZONE"} -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}select.js"></script>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/contact_contact.js?v=6"></script>
<script type="text/javascript">
$().ready(function() {
    $('.ui-datepicker').each(function(){
		$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
				});
	});
	$('#contactDate').datepicker();  
	$('#scheduleDate').datepicker(); 
});
function aCheck1(){
	document.getElementById("Email1").style.display="block";
	document.getElementById("Fax").style.display="none";
	document.getElementById("Mail").style.display="none";
	document.getElementById("Phone").style.display="none";
	document.getElementById("Meeting").style.display="none";
	document.getElementById("schedule_div").style.display="block";
	document.getElementById("status_div").style.display="block";
}

function aCheck2(){
	document.getElementById("Email1").style.display="none";
	document.getElementById("Fax").style.display="block";
	document.getElementById("Mail").style.display="none";
	document.getElementById("Phone").style.display="none";
	document.getElementById("Meeting").style.display="none";
	document.getElementById("schedule_div").style.display="block";
	document.getElementById("status_div").style.display="block";
}

function aCheck3(){
	document.getElementById("Email1").style.display="none";
	document.getElementById("Fax").style.display="none";
	document.getElementById("Mail").style.display="block";
	document.getElementById("Phone").style.display="none";
	document.getElementById("Meeting").style.display="none";
	document.getElementById("schedule_div").style.display="block";
	document.getElementById("status_div").style.display="block";
}

function aCheck4(){
	document.getElementById("Email1").style.display="none";
	document.getElementById("Fax").style.display="none";
	document.getElementById("Mail").style.display="none";
	document.getElementById("Phone").style.display="block";
	document.getElementById("Meeting").style.display="none";
	document.getElementById("schedule_div").style.display="block";
	document.getElementById("status_div").style.display="block";
}

function aCheck5(){
	document.getElementById("Email1").style.display="none";
	document.getElementById("Fax").style.display="none";
	document.getElementById("Mail").style.display="none";
	document.getElementById("Phone").style.display="none";
	document.getElementById("Meeting").style.display="block";
	document.getElementById("schedule_div").style.display="none";
	document.getElementById("status_div").style.display="none";
}

function change_method(type)
{
	if(type == 'Email')
	{
		aCheck1();
		change_status('SENT');
	}
	else if(type == 'Fax')
	{
		aCheck2();
		change_status('SENT');
	}
	else if(type == 'Mail')
	{
		aCheck3();
		change_status('SENT');
	}
	else if(type == 'Phone')
	{
		aCheck4();
		change_status('CALLED');
	}
	else if(type == 'Meeting')
	{
		aCheck5();
	}
}

function change_status(name)
{
	$("#status_span").text(name);
	$("#status_name").val(name);
}

function a(obj){
	var _selectedvalue = obj.value;
	if(_selectedvalue==''){
		_selectedvalue = '-1';
	}
	for(i=1;i<=8;i++){
		var divobj = document.getElementById('tb'+i);
		if(!divobj)
		{
			continue;
		}
		if(parseInt(_selectedvalue)==i){
			divobj.style.display = 'block';
		}else{
			divobj.style.display = 'none';
		}
	}
}

function changeContent()
{
	var contactMethod = get_checked_str('contactMethod');
	var contentTypeId = contactMethod+'ContentType';

	var tplId = $('#'+contentTypeId).val();
	
	var content = $('#'+contentTypeId+' option:selected').attr('title');
	$("#"+contactMethod+"Content").text(content);
	
	if(tplId == 2)
	{
		$('#'+contactMethod+'ReferName2').show();
		$('#'+contactMethod+'Content').append('\n'+$('#'+contactMethod+'ReferName2').val());
		$('#'+contactMethod+'ReferName3').hide();
	}
	else if(tplId == 3)
	{
		$('#'+contactMethod+'ReferName3').show();
		$('#'+contactMethod+'Content').append('\n'+$('#'+contactMethod+'ReferName3').val());
		$('#'+contactMethod+'ReferName2').hide();
	}
	else
	{
		$('#EmailReferName2').hide();
		$('#EmailReferName3').hide();
	}
}

function check_recipient(id, obj)
{
	var val = obj.value;
	var a_obj = document.getElementById(val);
	if(a_obj.length == 0)
	{
		alert("Please enter the address on the address tab to continue your operation. ");
		obj.checked = false;
		var tmp_check = $("#tmp_check").val();
		$('input[@name=recipient][value='+tmp_check+']').attr('checked', 'checked');
		$('#'+tmp_check).attr('checked', 'checked');
		return false;
	}
	document.getElementById("bill1").style.display="none";
 	document.getElementById("shipping1").style.display="none";
 	document.getElementById("sold1").style.display="none";
 	document.getElementById("contact1").style.display="none";
 	if(a_obj.length > 1)
	document.getElementById(id).style.display="block";
	$("#tmp_check").val(val);
}

function check_recipient_old(id)
{
	document.getElementById("bill1").style.display="none";
	$("#bill1").hide();
	$("#shipping1").hide();
	$("#sold1").hide();
	$("#contact1").hide();
	$("#"+id).show();
	var match_id = '';
	if(id == 'bill1')
	match_id = 'bill_address_c';
	else if(id == 'shipping1')
	match_id = 'shipping_address_c';
	else if(id == 'sold1')
	match_id = 'sold_address_c';
	else if(id == 'contact1')
	match_id = 'contact_address_c';
	$("#"+match_id).click();
}

function edit_contact(bt_obj, form_name, action_name){
	if(!validate_form())
	{
		return false;
	}
	var bt_val = bt_obj.value;
	bt_obj.value = bt_val+'...';
	bt_obj.disabled = true;
	var form = $("#"+form_name);
	var source_name=$('#source1 option:selected').text();
	//ajax form post
	var options = {
		success:function(data)
		{
			if(data)
			{
				alert(data);
			}
			bt_obj.value = bt_val+":success";
			parent.document.getElementById('contactIframe').src = parent.document.getElementById('contactIframe').src;
			parent.$('#editContactCntctDialog').dialog('close');
		},
		error: function(){
			alert('error...');
			bt_obj.value = bt_val;
			bt_obj.disabled = true;
		},
		resetForm:true,
		url:action_name+source_name,
		type:"POST"
	};
	form.ajaxForm(options);
	form.submit();
}

function validate_form()
{
	if($('#contactName').val() == ''){
		alert('Contact name is required');
		$('#contactName').focus();
		return false;
	}
	var contactMethod = get_checked_str('contactMethod');
	if($("#"+contactMethod+'Subject').val() == ''){
		alert('Subject is required');
		$("#"+contactMethod+'Subject').focus();
		return false;
	}
	if(contactMethod == 'Email')
	{
		if(!$("#email").val()){
			alert('Email is required');
			return false;
		}
	}else if(contactMethod == 'Fax'){
		if(!$("#faxv").val()){
			alert('Fax is required');
			return false;
		}
	}else if(contactMethod == 'Mail'){
		var recipient = get_checked_str("recipient");
		if(recipient == '' || !$("#"+recipient).val()){
			alert('Address is required');
			return false;
		}
	}else if(contactMethod == 'Phone'){
		if(!$("#phoneNumber").val()){
			alert('Phone is required');
			return false;
		}
	}else if(contactMethod == 'Meeting'){
		if(!$("#meeting_name").val()){
			alert('Meeting name is required');
			return false;
		}
	}
	return true;
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

function check_address(obj)
{
	if(obj.length == 0)
	{
		alert("Please enter the address on the address tab to continue your operation.");
		return;
	}
}

function set_checked(name, val)
{
	var objs = document.getElementsByName(name);
	for(var i=0; i<objs.length; i++)
	{
		if(objs[i].value == val)
		{
			objs[i].checked = true;
		}
	}
}

function set_selected(id, val)
{
	var obj = document.getElementById(id);
	for(var i=0;i<obj.options.length;i++)
	if(obj.options[i].value == val){ 
		obj.options[i].selected = true;
	}
}

function set_selected_label(id, text)
{
	var obj = document.getElementById(id);
	for(var i=0;i<obj.options.length;i++){
		if(obj.options[i].innerHTML == text)
		obj.options[i].selected = true;
	}
}

function check_only(id)
{
	if(id == 'contactDate')
	{
		$('#scheduleDate').attr('value','');
	}
	else
	{
		$('#contactDate').attr('value','');
		$("#status_flag").attr("checked", false);
	}
}
function checkCntctSchdlDate(){
	if($("#statusFlag").attr('checked') == true){
		$("#scheduleDate").attr("disabled", true);
		if($("#contactDate").val() == '')
		{
			$("#contactDate").val(getTodayDate());
		}
	}else{
		$("#scheduleDate").attr("disabled", false);
	}
}
</script>

<style type="text/css">
<!--
body {
	width:690px;
	margin:0px 0px 10px 10px;
	
}
-->
</style>
</head>

<body>
<form method="post" action="contact_contact!showContactCntctEditForm.action" id="edit_contact_form">
<input type="hidden" name="contactType" value="" />
<input type="hidden" name="contactsKey" value="${contactsKey}" />
<table width="660" border="0"  cellpadding="0" cellspacing="0" >
  <tr>
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <td width="101" align="right">Contact Source </td>
        <td>
        	<select name="source" id="source1">
        	<s:iterator value="specDropDownist" >
        	<s:if test="name=='ORIGINAL_SOURCE'">
        	<s:iterator value="dropDownDTOs">
        		<option value="${id}" label="${name }" <c:if test="${allData.source == id}">selected="${id}"</c:if> >${name }</option>
        	</s:iterator>
        	</s:if>
        	</s:iterator>
        	</select>
        
        </td>
        <td width="100" align="right"><span class="important">*</span>Contact Name </td>
        <td colspan="2">
        	<input type="text" id="contactName" class="NFText" name="contactName" value="${allData.contactName}" />
        </td>
        </tr>

      <tr>
        <td valign="top" align="right">Options</td>
        <td width="" valign="top" colspan="4">
		<input name="contactMethod" type="radio" disabled=1  value="Email" checked="checked" onclick="change_method('Email');"/> 
			Email&nbsp;&nbsp;
          <input name="contactMethod" type="radio" disabled=1  value="Fax" onclick="change_method('Fax');"/>
          Fax&nbsp;&nbsp;
          <input name="contactMethod" type="radio" disabled=1  value="Mail" onclick="change_method('Mail');"/> 
          Mail&nbsp;&nbsp;
        	<input type="radio" disabled=1 name="contactMethod" value="Phone" onclick="change_method('Phone');"/>
          Phone&nbsp;&nbsp;
			<input type="radio" name="contactMethod" value="Meeting" />
          Meeting&nbsp;&nbsp;
      </tr>
	</table>

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
	  <tr>

		<td>
		<table><tr>
	  	<td width="95" align="right">Contact Date</td>
	  	<td width="200px">
	  		<input name="contactDate" type="text" class="NFText ui-datepicker" value="<c:if test="${! empty allData.contactDate}"><s:date name="allData.contactDate" format="yyyy-MM-dd" /></c:if>" size="20"  id="contactDate" style="width: 121px;" onchange="check_only(this.id);"/>
	  	</td></tr>
		</table> 
		</td>

		<td>
		<div id="schedule_div">
		<table><tr>
        <td align="right">Scheduled on</td>
        <td colspan="" width="200px" colspan="2">
        	<input name="scheduleDate" type="text" class="NFText ui-datepicker" 
			value="<c:if test="${! empty allData.scheduleDate}"><s:date name="allData.scheduleDate" format="yyyy-MM-dd" /></c:if>" size="20"  id="scheduleDate" style="width: 121px;" onchange="check_only(this.id);"/>
         </td></tr>
		</table> 
		</div>
		</td>

      </tr>
	</table>

      
	<div id="status_div">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
		 <td width="100">&nbsp;</td>
		 <td>
         <input type="checkbox" name="statusFlag" value="1" onclick="checkCntctSchdlDate();" id="statusFlag" />
         <span id="status_span">SENT</span>
         <input type="hidden" name="status_name" value="SENT" id="status_name" />
         </td>
      </tr> 
    </table>
	<div>


    </td>
  </tr>
  <tr>
    <td style="background: #EEF4FD;border:1px solid #ccc;height:180px; vertical-align:top;">
   <div id="Email1" style="display:block;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
        <tr>
          <td width="101" align="right">E-mail Address </td>
          <td colspan="2">
            <select name="email" id="email" style="width:185px;">
	            <s:iterator value="contactAddrs">
            	<option value="${busEmail }"<c:if test="${allData.email == busEmail}">selected="${busEmail}"</c:if>>${busEmail }</option>
            </s:iterator>
            </select>
          </td>
        </tr>
        <tr>
          <td align="right"><span class="important">*</span>Subject</td>
          <td colspan="2"><input id="EmailSubject" name="EmailSubject" type="text" class="NFText" size="60" value="<c:if test="${allData.contactMethod == 'Email'}">${allData.subject }</c:if>" /></td>
        </tr>
        <tr>
          <td valign="top" align="right">Content to Send </td>
          <td width="175px">
		     <select id='EmailContentType' onchange="changeContent();" name="EmailContentType">
  				<s:iterator value="contentTmplList">
  				<option value="${templateId}" title="${content}" >${name}</option>
  				</s:iterator>
   			</select>
   			</td>
   			<td>
				<select id='EmailReferName2' style="width:155px;display:none;" onchange="changeContent();" name="EmailReferName2">
					<option>GS56789 - $20 Off</option>
			  		<option>10% Off</option>
				</select>
				<select id='EmailReferName3'  style="width:155px;display:none;" onchange="changeContent();" name="EmailReferName3">
					<s:iterator value="catalogDTOList">
					<option>${catalogName}</option>
			  	</s:iterator>
				</select>
   			</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="2">
          <div id="tb1" style='display:block'>
            <textarea id="EmailContent" name="EmailContent" class="content_textarea"><c:if test="${allData.contactMethod == 'Email'}">${allData.content }</c:if></textarea>
          </div>
   		</td>
        </tr>
        
      </table>
   </div>
   
	<div id="Fax" style="display:none;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <td  width="60" align="right">Fax</td>
         <td>
           <div align="left">
             <input name="fax" type="text" class="NFText" value="${allData.fax}" size="12" id="faxv" />
             Ext
            <input name="faxExt" type="text" value="${allData.faxExt}" size="6" id="faxExt"  class="NFText"/>
          </div>
       </td>
  </tr>
  <tr>
          <td width="60" align="right"><span class="important">*</span>Subject</td>
          <td>
          <div align="left">
          	<input id="FaxSubject" name="FaxSubject" value="<c:if test="${allData.contactMethod == 'Fax'}">${allData.subject }</c:if>" type="text" class="NFText" size="60" />
          </div>
          </td>
     </tr>
</table>

	</div>

	<div id="Mail" style="display:none;">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" id="myLayer">
        <tr>
          <td width="101" valign="top" align="right">Recipient</td>
   		   <td>
   		   <input type="hidden" id="tmp_check" value="bill_address" />
          <input type="radio" name="recipient"  onclick="check_recipient('bill1', this);" value="bill_address" checked id="bill_address_c" />
          Send to Billing Address <br />
          <input name="recipient" type="radio" onclick="check_recipient('shipping1', this);" value="shipping_address" id="shipping_address_c" />
          Send to Shipping Address <br />
		 <input name="recipient" type="radio"   onclick="check_recipient('sold1', this);" value="sold_address" id="sold_address_c"/>
      		Send to Sold -To Address <br/>
      		<input name="recipient" type="radio"   onclick="check_recipient('contact1', this);" value="contact_address" id="contact_address_c" />
      		Send to Contact Address <br/> 	   
      		</td>
   			 <td  colspan="1" valign="top">
      			<div id="bill1"  style="padding-top:5px;">
      			<select name="bill_address" style="width:240PX;" onclick="check_address(this);" id="bill_address">
        			 <s:iterator value="billAddrs">
		            <option value="${fullAddrLine }">${fullAddrLine }</option>
		            </s:iterator>
      			</select>
      	</div>
      	<div id="shipping1" style="display:none;padding-top:20px;">
	  	<select name="shipping_address" style="width:240PX;" onclick="check_address(this);" id="shipping_address">
        	 <s:iterator value="shipAddrs">
		            <option value="${fullAddrLine }">${fullAddrLine }</option>
		            </s:iterator>
      	</select>
      	</div>   
	    <div id="sold1" style="display:none;padding-top:38px;">
	    <select name="sold_address" style="width:240PX;" onclick="check_address(this);" id="sold_address">
        	<s:iterator value="soldAddrs">
		            <option value="${fullAddrLine }">${fullAddrLine }</option>
		            </s:iterator>
      	</select>
      	</div>
      	
       <div id="contact1" style="display:none;padding-top:60px;">
	    <select name="contact_address" style="width:240PX;" onclick="check_address(this);" id="contact_address">
        	 <s:iterator value="contactAddrs">
		            <option value="${fullAddrLine }">${fullAddrLine }</option>
		            </s:iterator>
      	</select>
      	</div>
      	</td>
        </tr>
		<tr>
          <td align="right"><span class="important">*</span>Subject</td>
          <td colspan="2">
          	<input id="MailSubject" name="MailSubject" type="text" class="NFText" size="60" value="<c:if test="${allData.contactMethod == 'Mail'}">${allData.subject }</c:if>" />
          </td>
        </tr>
               <tr>
          <td valign="top" align="right">Content to Send </td>
          <td width="175px">
		     <select id='MailContentType' onchange="changeContent();" name="MailContentType">
  				<s:iterator value="contentTmplList">
  				<option value="${templateId}" title="${content}">${name}</option>
  				</s:iterator>
   			</select>
   			</td>
   			<td>
				<select id="MailReferName2" style="width:155px;display:none;" onchange="changeContent();" name="MailReferName2">
					<option>GS56789 - $20 Off</option>
			  		<option>10% Off</option>
				</select>
				<select id="MailReferName3"  style="width:155px;display:none;" onchange="changeContent();" name="MailReferName3">
					<s:iterator value="catalogDTOList">
					<option>${catalogName}</option>
			  	</s:iterator>
				</select>
   			</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="2">
          <div id="tb1" style='display:block'>
            <textarea id="MailContent" name="MailContent" class="content_textarea"><c:if test="${allData.contactMethod == 'Mail'}">${allData.content }</c:if></textarea>
          </div>
   		</td>
        </tr>
		</table>
		</div>
	
	<div id="Phone" style="display:none;">
		   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" >
           <tr>
              <td height="35" valign="top" align="right">Best Time to Call </td>
              <td valign="top" class="General_table">
              	  <input name="best_time_from" type="text" class="NFText" value="<c:if test="${ empty callTimeFrom}">:</c:if>${callTimeFrom}" size="4" readonly />
                  <select name="am_pm_from" style="width:40px;" disabled>
                    <!-- {html_options options=$miscconfig.amPm selected=$timeFrom[1]} -->
                    <option><c:if test="${ empty callTimeFromAPM}">AM</c:if>${callTimeFromAPM}</option>
                  </select>
                <img src="${global_image_url}range.jpg" />
                  <input name="best_time_to" type="text" class="NFText" value="<c:if test="${ empty callTimeTo}">:</c:if>${callTimeTo}" size="4" readonly />
                  <select name="am_pm_to" style="width:40px;" disabled>
                    <!-- {html_options options=$miscconfig.amPm selected=$timeTo[1]} -->
                    <option><c:if test="${ empty callTimeToAPM}">AM</c:if>${callTimeToAPM}</option>
                  </select>
                  <input name="textfield" type="text" class="NFText" value="${bstCallTmzn}" size="10" />
              </td>
      		<tr> 
		   
		   <tr>
              <td align="right">Phone Number </td>
              <td><select name="phoneNumber" id="phoneNumber">
                 <s:iterator value="contactAddrs">
            	<option value="${busPhone}">${busPhone }</option>
            	</s:iterator>
                </select>
              </td>
            </tr>
        <tr>
        <td align="right">Call Time</td>
        <td>
        
          <s:select name="callTime" list="callTimeList" listKey="value" listValue="value" cssStyle="width:60px;" headerKey=" " headerValue="" value="callTimeFrom"></s:select>
        <s:select name="callTimeAmPm" cssStyle="width:59px;" list="amPmList" headerKey=" " headerValue="" value="callTimeFromAPM" />
          <select name="callTmzn" style="width:350px;">
            <!-- {html_options options=$smarty.session.DROPDOWLIST.TIME_ZONE selected=$result.info.callTmzn} -->
            <s:iterator value="specDropDownist" >
        	<s:if test="name=='TIME_ZONE'">
        	<s:iterator value="dropDownDTOs">
        		<option value="${id}" label="${name }">${name }</option>
        	</s:iterator>
        	</s:if>
        	</s:iterator>
          </select>
       </td>
      </tr>
      <tr>
        <td align="right"><span class="important">*</span>Subject</td>
        <td>
          <input id="PhoneSubject" name="PhoneSubject" type="text" class="NFText" size="40" value="<c:if test="${allData.contactMethod == 'Phone'}">${allData.subject }</c:if>" />
        </td>
      </tr>
      <tr>
        <td valign="top" align="right">Call Log </td>
        <td>
          <textarea name="PhoneContent" class="content_textarea2" style="width:300px;"><c:if test="${allData.contactMethod == 'Phone'}">${allData.content }</c:if></textarea>
        </td>
      </tr>
      <tr>
        <td align="right">Interest Score </td>
        <td>
          <select name="interestScore" style="width:60px;" id="interestScore">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
          </select>
        </td>
      </tr>
    </table>
	</div>

	<div id="Meeting" style="display:none;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" > 
		<tr>
			<td align="right"><span class="important">*</span>Meeting Name</td>
			<td><input type="text" size="76" class="NFText" id="meeting_name" name="meeting_name" value="${allData.subject}"></td>
		</tr>
		<tr>
			<td valign="top" align="right">Meeting Content</td>
			<td>
			<textarea class="content_textarea2" id="meeting_content" name="meeting_content">${allData.content}</textarea>        
			</td>
		</tr>
		</table> 
	</div>

	</td>
  </tr>

  <tr>
    <td>
        <div align="center" style="margin-top:15px;">
        <input type="hidden" value = '${contactNo}' name="contactNo" />
        <input type="hidden" name="saveType" value="update" />
        <c:choose>
        <c:when test="${statusFlag == 1 }">
		<input type="button" value="  Save  " disabled />
        </c:when>
        <c:otherwise>
        <s:iterator>
        	
        </s:iterator>
        <input type="button" value="  Save  " onclick="edit_contact(this, 'edit_contact_form', 'contact_contact!save.action?contactMethod=${allData.contactMethod}&contactIdStr=${allData.contactIdStr}&sessContactNo=${sessContactNo}&sourceName=');" />
        </c:otherwise>
        </c:choose>
          <input type="hidden" value="${allData.contactId}" name="allData.contactId" />
          <input type="button" value="Cancel" onclick="parent.$('#editContactCntctDialog').dialog('close');"  />
      	</div>
      </td>
  </tr>
</table>
</form>
<script type="text/javascript">
function set_selected_letter(tmp_id, val)
{
	var id = tmp_id+'_address';
	var obj = document.getElementById(id);
	for(var i=0;i<obj.options.length;i++)
	if(obj.options[i].value == val){
		obj.options[i].selected = true;
		$("#"+id+"_c").attr('checked', 'checked');
		check_recipient_old(tmp_id+'1');
		return true;
	}
	return false;
}

var contactMethodTmp = '${allData.contactMethod}';
set_checked('contactMethod', '${allData.contactMethod}');
set_checked('statusFlag', '${statusFlag}');
if(contactMethodTmp == 'Mail')
{
	set_selected_letter('bill', '${allData.address}');
	set_selected_letter('shipping', '${allData.address}');
	set_selected_letter('contact', '${allData.address}');
	set_selected_letter('sold', '${allData.address}');
	set_selected_label('MailContentType', '${allData.contentType}');
	changeContent();
}
if(contactMethodTmp == 'Mail' || contactMethodTmp == 'Email')
{
	set_selected_label(contactMethodTmp+'ContentType', '${allData.contentType}');
	if($("#"+contactMethodTmp+'ContentType').val() == 2 ||$("#"+contactMethodTmp+'ContentType').val() == 3)
	{
		set_selected(contactMethodTmp+'ReferName'+$("#"+contactMethodTmp+'ContentType').val(), '${referName}');
	}
	changeContent();
}

set_selected('email', '${allData.email}');
set_selected('phoneNumber', '${allData.phone}');
change_method('${allData.contactMethod}');
set_selected('interestScore', '${allData.interestScore}');
var statusFlag = '${statusFlag}';
if(statusFlag == '1')
{
	$.each( $(":radio, textarea, img,image, :text, select, :checkbox"), function(i, n)
		{ $(n).attr('disabled', 'true'); });	
}
</script>
</body>
</html>
