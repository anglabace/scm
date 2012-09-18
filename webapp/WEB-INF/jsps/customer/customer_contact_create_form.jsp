<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}select.js"></script>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/customer_contact.js?v=6"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>

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
	$("#statusFlag").attr("checked", true);
});


function aCheck1(){
	document.getElementById("Email1").style.display="block";
	document.getElementById("Fax").style.display="none";
	document.getElementById("Mail").style.display="none";
	document.getElementById("Phone").style.display="none";
	document.getElementById("Meeting").style.display="none";
	document.getElementById("schedule_div").style.display="block";
	document.getElementById("status_div").style.display="block";
	change_status('SENT');
}

function aCheck2(){
	document.getElementById("Email1").style.display="none";
	document.getElementById("Fax").style.display="block";
	document.getElementById("Mail").style.display="none";
	document.getElementById("Phone").style.display="none";
	document.getElementById("Meeting").style.display="none";
	document.getElementById("schedule_div").style.display="block";
	document.getElementById("status_div").style.display="block";
	change_status('SENT');
}

function aCheck3(){
	document.getElementById("Email1").style.display="none";
	document.getElementById("Fax").style.display="none";
	document.getElementById("Mail").style.display="block";
	document.getElementById("Phone").style.display="none";
	document.getElementById("Meeting").style.display="none";
	document.getElementById("schedule_div").style.display="block";
	document.getElementById("status_div").style.display="block";
	change_status('SENT');
}

function aCheck4(){
	document.getElementById("Email1").style.display="none";
	document.getElementById("Fax").style.display="none";
	document.getElementById("Mail").style.display="none";
	document.getElementById("Phone").style.display="block";
	document.getElementById("Meeting").style.display="none";
	document.getElementById("schedule_div").style.display="block";
	document.getElementById("status_div").style.display="block";
	change_status('CALLED');
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
	document.getElementById("billing1").style.display="none";
	document.getElementById("shipping1").style.display="none";
	document.getElementById("sold1").style.display="none";
	document.getElementById("contact1").style.display="none";
	if(a_obj.length > 1)
	document.getElementById(id).style.display="block";
	$("#tmp_check").val(val);
}

function add_contact(bt_obj, form_name, action_name){
	if(!validate_form())
	{
		return false;
	}
	var sourceName = "";
	sourceName = $( '#source' ).find( "option:selected" ).text() ;
	action_name += "&sourceName="+sourceName ;

	var bt_val = bt_obj.value;
	bt_obj.value = bt_val+'...';
	bt_obj.disabled = true;
	var form = $("#"+form_name);
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
			parent.$('#addCustomerCntctDialog').dialog('close');
		},
		error: function(){
			alert('error...');
			bt_obj.value = bt_val;
			bt_obj.disabled = true;
		},
		resetForm:true,
		url:action_name,
		type:"POST"
	};
	form.ajaxForm(options);
	form.submit();
}

function validate_form()
{
	var flag = true;

	var tmp_str1 = $('#contactDate').val().replace(/ /g,"");
	var tmp_str2 = $('#scheduleDate').val().replace(/ /g,"");
	if(tmp_str1 == '' && tmp_str2 == '')
	{
		alert("Please enter the contact or the schedule date.");
		return false;
	}
	var todayDate = getTodayDate();
	if(tmp_str1 == '' && tmp_str2){
		var dc = checkAskDate('scheduleDate');
		if(!dc){
			return false;
		}
		var compareFlag = compareDate($('#scheduleDate').val(), todayDate);
		if(compareFlag != 1){
			alert("Please enter the correct Scheduled Date.");
			$('#scheduleDate').focus();
			return false;
		}
	}else{
		var dc = checkAskDate('contactDate');
		if(!dc){
			return false;
		}
		var compareFlag = compareDate($('#contactDate').val(), todayDate);
		if(compareFlag == 1){
			alert("Contact Date is required to less than or equal to today date");
			$('#contactDate').focus();
			return false;
		}
	}
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
		if(!$("#emailv").val()){
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
		if(!$("#phonev").val()){
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
	if($('#contactDate').val() != '')
	{
		$("#statusFlag").attr("checked", true);	
	}else{
		$("#statusFlag").attr("checked", false);	
	}
}

function check_address(obj)
{
	if(obj.length == 0)
	{
		alert("Please enter the address on the address tab to continue your operation.");
		return;
	}
}

function checkScdlDate(){
	if($('#scheduleDate').val() != '')
	{
		$("#statusFlag").attr("checked", false);	
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
<form method="post" action="cust_contact/save.action" id="save_contact_form">
<table width="660" border="0"  cellpadding="0" cellspacing="0" >
  <tr>
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <td width="101" align="right">Contact Source </td>
        <td colspan="1">
        	<select name="source" id="source">
        	<s:iterator value="specDropDownist" >
        	<s:if test="name=='ORIGINAL_SOURCE'">
        	<s:iterator value="dropDownDTOs">
        		<option value="${id}" label="${name }">${name }</option>
        	</s:iterator>
        	</s:if>
        	</s:iterator>
        	</select>
        </td>
        <td width="100" align="right"><span class="important">*</span>Contact Name </td>
        <td colspan="2">
        	<input type="text" name="contactName" value="" class="NFText" id="contactName" />
        </td>
        </tr> 
      <tr>
        <td valign="top" align="right">Options</td>
        <td width="" valign="top" colspan="4">
		  <input name="contactMethod" type="radio" value="Email" checked="checked"  onclick="aCheck1()"/>
		  Email&nbsp;&nbsp;
          <input name="contactMethod" type="radio" value="Fax"   onclick="aCheck2()"/>
          Fax&nbsp;&nbsp;
          <input name="contactMethod" type="radio" value="Mail"  onclick="aCheck3()"/> 
          Mail&nbsp;&nbsp;
          <input type="radio" name="contactMethod" value="Phone" onclick="aCheck4()"/>
          Phone&nbsp;&nbsp;
          <input type="radio" name="contactMethod" value="Meeting" onclick="aCheck5()"/>
          Meeting&nbsp;&nbsp;
        </td>
      </tr>
	</table>

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
	  <tr>

		<td>
		<table><tr>
	  	<td width="95" align="right">Contact Date</td>
	  	<td width="200px">
	  		<input name="contactDate" type="text" class="NFText ui-datepicker" value="<s:date name="contactDateDefault" format="yyyy-MM-dd" />" size="20"  id="contactDate" onchange="check_only(this.id);" style="width: 121px;"/>
	  	</td></tr>
		</table> 
		</td>

		<td>
		<div id="schedule_div">
		<table><tr>
        <td align="right">Scheduled on</td>
        <td colspan="" width="200px" colspan="2">
        	<input name="scheduleDate" type="text" class="NFText ui-datepicker" value="" size="20"  id="scheduleDate" onchange="check_only(this.id);" style="width: 121px;" />
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
         <input type="checkbox"  name="statusFlag" value="1"onclick="return checkScdlDate();" id="statusFlag" />
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
            <select name="email" onclick="check_address(this);" style="width:185px;" id="emailv">
            <s:iterator value="custAddrs">
            	<option value="${busEmail }">${busEmail }</option>
            </s:iterator>
            </select>
          </td>
        </tr>
        <tr>
          <td align="right"><span class="important">*</span>Subject</td>
          <td colspan="2"><input id="EmailSubject" name="EmailSubject" type="text" class="NFText" size="60" /></td>
        </tr>
        <tr>
          <td valign="top" align="right">Content to Send </td>
          <td width="175px">
		     <select id='EmailContentType' onchange="changeContent();" name="EmailContentType">
  				<s:iterator value="contentTmplList">
  				<option value="${templateId}" title="${content}">${name}</option>
  				</s:iterator>
   			</select>
   			</td>
   			<td>
				<select id='EmailReferName2' style="width:155px;display:none;" onchange="changeContent();" name="EmailReferName2" >
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
            <textarea id="EmailContent" name="EmailContent" class="content_textarea"></textarea>
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
                 <input name="fax" type="text" class="NFText" value="${fax}" size="12" id="faxv" />
              		Ext
                 <input name="faxExt" type="text" value="${faxExt}" size="6" id="faxExt"  class="NFText"/>
           	</div>
        </td>
  </tr>
  <tr>
      <td width="60" align="right"><span class="important">*</span>Subject</td>
      <td>
       <div align="left">
       		<input id="FaxSubject" name="FaxSubject" type="text" class="NFText" size="60" />
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
          <input type="radio" name="recipient" id="bill_c" onclick="check_recipient('billing1', this);" value="bill_address" />
          Send to Billing Address <br />
          <input name="recipient" type="radio" id="ship_c" onclick="check_recipient('shipping1', this);" value="shipping_address"/>
          Send to Shipping Address <br />
		 <input name="recipient" type="radio" id="sold_c"   onclick="check_recipient('sold1', this);" value="sold_address"/>
      		Send to Sold -To Address <br />
      		<input name="recipient" type="radio" id="contact_c"   onclick="check_recipient('contact1', this);" value="contact_address"/>
      		Send to Contact Address   
      		</td>
   			 <td  colspan="1" valign="top">
      			<div id="billing1"  style="display:none;padding-top:5px;">
      			<select id="bill_address" name="bill_address" style="width:240PX;" onclick="check_address(this);">
        			 <s:iterator value="billAddrs">
		            <option value="${fullAddrLine }">${fullAddrLine }</option>
		            </s:iterator>
      			</select>
      			</div>
      	<div id="shipping1" style="display:none;padding-top:20px;">
	  	<select id="shipping_address" name="shipping_address" style="width:240PX;" onclick="check_address(this);">
             <s:iterator value="shipAddrs">
		            <option value="${fullAddrLine }">${fullAddrLine }</option>
		            </s:iterator>
      	</select>
      	</div>
      	<div id="sold1" style="display:none;padding-top:38px;">
	    <select id="sold_address" name="sold_address" style="width:240PX;" onclick="check_address(this);">
        	 <s:iterator value="soldAddrs">
		            <option value="${fullAddrLine }">${fullAddrLine }</option>
		            </s:iterator>
      	</select>
      	</div>
      	   
	    <div id="contact1" style="display:none;padding-top:60px;">
	    <select id="contact_address" name="contact_address" style="width:240PX;" onclick="check_address(this);">
        	 <s:iterator value="custAddrs">
		            <option value="${fullAddrLine }">${fullAddrLine }</option>
		            </s:iterator>
      	</select>
      	</div>
      	</td>
        </tr>
		<tr>
          <td align="right"><span class="important">*</span>Subject</td>
          <td colspan="2">
          	<input id="MailSubject" name="MailSubject" type="text" class="NFText" size="60" />
          </td>
        </tr>
        <tr>
          <td valign="top" align="right">Content to Send </td>
          <td width="175px">
		     <select id='MailContentType' onchange="changeContent();" name='MailContentType'>
  				<s:iterator value="contentTmplList">
  				<option value="${templateId}" title="${content}">${name}</option>
  				</s:iterator>
   			</select>
   			</td>
   			<td>
				<select id='MailReferName2' style="width:155px;display:none;" onchange="changeContent();" name='MailReferName2'>
					<option>GS56789 - $20 Off</option>
			  		<option>10% Off</option>
				</select>
				<select id='MailReferName3'  style="width:155px;display:none;" onchange="changeContent();" name='MailReferName3'>
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
            <textarea id="MailContent" name="MailContent" class="content_textarea"></textarea>
          </div>
   		</td>
        </tr>
		</table>
		</div>
	
	<div id="Phone" style="display:none;">
		   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" >
		               <tr>
              <td valign="top" height="35" align="right">Best Time to Call </td>
              <td class="General_table" valign="top">
              	  <input name="best_time_from" type="text" class="NFText" value="<c:if test="${ empty callTimeFrom}">:</c:if>${callTimeFrom}" size="4" readonly />
                  <select name="am_pm_from" style="width:40px;" disabled>
                    <!-- {html_options options=$miscconfig.amPm selected=$timeFrom[1]} -->
                    <option><c:if test="${ empty callTimeFromAPM}">AM</c:if>${callTimeFromAPM}</option>
                  </select>
                <img src="${global_image_url}range.jpg" />
                  <input name="best_time_to" type="text" class="NFText" value="<c:if test="${ empty callTimeTo}">:</c:if>${callTimeTo}" size="4" readonly />
                  <select name="am_pm_to" style="width:40px;" disabled>
                     <option><c:if test="${ empty callTimeToAPM}">AM</c:if>${callTimeToAPM}</option>
                  </select>
                  <input name="textfield" type="text" class="NFText" value="${bstCallTmzn}" size="10" readonly />
              </td>
      <tr>
		   
            <tr>
              <td align="right">Phone Number </td>
              <td><select name="phoneNumber" id="phonev">
                 <s:iterator value="custAddrs">
            	<option value="${busPhone}">${busPhone }</option>
            	</s:iterator>
                </select>
              </td>
            </tr>
        <td align="right">Call Time</td>
        <td>
        
        <s:select name="callTimeFrom" list="callTimeList" listKey="value" listValue="value" cssStyle="width:60px;" headerKey=" " headerValue="" value="callTimeFrom"></s:select>
        <s:select name="callTimeFromPm" cssStyle="width:59px;" list="amPmList" headerKey=" " headerValue="" value="callTimeFromAPM" />
          
         <select name="callTmzn" style="width:350px;">
         <s:iterator value="specDropDownist" >
        	<s:if test="name=='TIME_ZONE'">
        	<s:iterator value="dropDownDTOs">
        		<option value="${id}" label="${name }">${name }</option>
        	</s:iterator>
        	</s:if>
        	</s:iterator>
         </select>
          </td>
    
      <tr>
        <td align="right"><span class="important">*</span>Subject</td>
        <td>
          <input id="PhoneSubject" name="PhoneSubject" type="text" class="NFText" size="40" />
        </td>
      </tr>
      <tr>
        <td valign="top" align="right">Call Log </td>
        <td>
          <textarea name="PhoneContent" class="content_textarea2" style="width:300px;"></textarea>
        </td>
      </tr>
      <tr>
        <td align="right">Interest Score </td>
        <td>
          <select name="interestScore" style="width:60px;">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option selected="selected">5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
          </select>
        </td>
      </tr>
    </table>
    </div>

	<div id="Meeting" style="display:none;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" > 
		<tr>
			<td align="right"><span class="important">*</span>Meeting Name</td>
			<td><input type="text" size="76" class="NFText" id="meeting_name" name="meeting_name"></td>
		</tr>
		<tr>
			<td valign="top" align="right">Meeting Content</td>
			<td>
			<textarea class="content_textarea2" id="meeting_content" name="meeting_content">FT-FTC Notice</textarea>        
			</td>
		</tr>
		</table> 
	</div> 

	</td>
  </tr>

  <tr>
    <td>
        <div align="center" style="margin-top:15px;">
          <input type="hidden" value = '${custNo}' name="custNo" />
          <input type="hidden" value = 'add'  name='saveType' />
          <input type="button" value="  Add  " onclick="add_contact(this, 'save_contact_form', 'cust_contact!save.action?sessCustNo=${sessCustNo}');" />
          <input type="button" value="Cancel" onclick="parent.$('#addCustomerCntctDialog').dialog('close');"  />
      	</div>     
      </td>
  </tr>
</table>
</form>

<script>
changeContent();
function init_check(custAddrsCount, shipAddrsCount, billAddrsCount, soldAddrsCount)
{
	if(billAddrsCount > 0)
	{
		$("#bill_c").attr("checked", "checked");
		check_recipient('billing1', document.getElementById('bill_c'));
		return;
	}
	else if(shipAddrsCount >0)
	{
		$("#ship_c").attr("checked", "checked");
		check_recipient('shipping1', document.getElementById('ship_c'));
		return;
	}
	else if(soldAddrsCount >0 )
	{
		$("#sold_c").attr("checked", "checked");
		check_recipient('sold1', document.getElementById('sold_c'));
		return;
	}
	else if(custAddrsCount > 0)
	{
		$("#contact_c").attr("checked", "checked");
		check_recipient('contact1', document.getElementById('contact_c'));
		return;
	}
}
var custAddrsCount = "${custAddrsCount}";
var shipAddrsCount = "${shipAddrsCount}";
var billAddrsCount = "${billAddrsCount}";
var soldAddrsCount =  "${soldAddrsCount}";
init_check(custAddrsCount, shipAddrsCount, billAddrsCount, soldAddrsCount);
</script>
</body>
</html>
