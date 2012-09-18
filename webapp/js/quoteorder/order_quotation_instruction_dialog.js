$(document).ready(function(){
    $('.ui-datepicker').each(function(){
		$(this).datepicker(
			{
				dateFormat: 'yy-mm-dd',
				changeMonth: true,
				changeYear: true
			});
	}) 
}); 

function check_schedule_add(setDate){
	today =  formatDate(new Date());
	if (setDate.value != emptyDate){
		document.getElementById('schedule_date').value = ""; 
		if (setDate.value > today){
			alert ("Contact date shouldn't be in the future!"); 
			$("#contact_date").focus(); 
			$("#contact_date").val(""); 
		}
	}
}

function check_contact_add(setDate){
	today =  formatDate(new Date());
	if (setDate.value >= today){
		var rdo_complete = document.getElementsByName("complete");
		rdo_complete[0].checked = false;
		rdo_complete[1].checked = true; 
	}else{
		alert ("Schedule date shouldn't be the past!"); 
		$("#schedule_date").focus(); 
		$("#schedule_date").val(""); 
	}

	if (setDate.value != emptyDate){
		document.getElementById('contact_date').value = ""; 
	} 
}

function refresh_view(type_slt){
	var only_email = parent.$('#only_email').val();
	if (only_email == "Y"){
		return; 
	}else{
		//alert (type_slt.value); 
		if (type_slt.value == "SALES_NOTES" || type_slt.value == "SHIPMENT" || type_slt.value == "PRODUCTION" || type_slt.value == "ACCOUNTING" || type_slt.value == "CROSS_SELLING"){
			$('#send_chk_div').hide();
			$('#schedule_div').hide();
			$('#send_div').hide();
			$('#status_div').hide();
			$('#mail_div').hide();
			$('#content_div').hide();
			$('#desc_div').show();
			$('#followup_div').hide();
			$('#file_div').show();
		}else if (type_slt.value == "CUST_CONFIRM_EMAIL" || type_slt.value == "ORDER_CHANGE_NOTIFICATION" || type_slt.value == "VENDOR_CONFIRM_EMAIL" || type_slt.value == "FOLLOWUP_EMAIL"){
			$('#send_chk_div').show();
			$('#schedule_div').show();
			$('#send_div').show();
			$('#status_div').show();
			$('#mail_div').show();
			$('#content_div').show();
			$('#desc_div').hide();
			$('#followup_div').hide();
			$('#file_div').show();
			getEmailTypes();
		}else if (type_slt.value == "FOLLOWUP_DATE"){
			$('#send_chk_div').hide();
			$('#schedule_div').hide();
			$('#send_div').hide();
			$('#status_div').hide();
			$('#mail_div').hide();
			$('#content_div').hide();
			$('#desc_div').show();
			$('#followup_div').show();
			$('#file_div').hide();
		}
	}

}

function showhid(chk){
	if (chk.checked){ 
		$('#schedule_div').show();
		$('#status_div').show();
	}else{
		$('#schedule_div').hide();
		$('#status_div').hide();
	}
/* 
	var con_date = document.getElementById("contact_date");
	var sch_date = document.getElementById("schedule_date");
	var rdo_complete = document.getElementsByName("complete");
	if (chk.checked){
		con_date.disabled = false;
		sch_date.disabled = false;
		rdo_complete[0].disabled = false;
		rdo_complete[1].disabled = false; 
	}else{
		con_date.disabled = true;
		sch_date.disabled = true;
		rdo_complete[0].disabled = true;
		rdo_complete[1].disabled = true; 
	}
*/
}

function refresh_to_email(obj){
	if (obj.value == "Sales"){
		$('#emailto_list').val("zhangyang@genscriptcorp.com,gulifeng@genscriptcorp.com"); 
	}else if(obj.value == "Marketing"){
		$('#emailto_list').val("yinkun@genscriptcorp.com, viki@genscriptcorp.com"); 
	}else{
		$('#emailto_list').val(""); 
	}
}

function refresh_sub_slt(obj){
	$('#content').val(obj.value);
}

function add_file(){ 
	var uploaded_count = $('#uploaded_count').val();
	if (! uploaded_count){
		uploaded_count = 0;
	}
	var td = document.getElementById("uploaded_files");    
	var input = document.createElement("input");
	var button = document.createElement("input");
	var br = document.createElement("br");
	
	input.type = "file";
	input.name = "file_" + uploaded_count;
	input.size = "55";
	
	button.type = "button";
	button.value = "  Delete  ";
	
	button.onclick = function() {
		td.removeChild(br);
		td.removeChild(input);
		td.removeChild(button);
	}
	td.appendChild(input);
	td.appendChild(button); 
	td.appendChild(br);

	uploaded_count++;
	$('#uploaded_count').val(uploaded_count); 
}

function delete_file(file_name){
//	alert (file_name);
	var listDiv = document.getElementById("doc_list"); 
	var delDiv = document.getElementById(file_name);
	listDiv.removeChild(delDiv); 
	var files_to_del = document.getElementById("files_to_del"); 
	files_to_del.value += file_name + "##";
}

//update source in session
function update_email(op_type){
/*
	var chk_send_email = document.getElementById("chk_send_email"); 
	if (chk_send_email.checked == true){
		if (! $('#contact_date').val()){
			alert("Contact date should not be empty!"); 
			$("#contact_date").focus(); 
			return;
		}
		if (! $('#schedule_date').val()) {
			alert("Schedule date should not be empty!"); 
			$("#schedule_date").focus(); 
			return;
		}
	} 
*/

	var type_slt = document.getElementById('email_type');
	if (type_slt.value == "CUST_CONFIRM_EMAIL" || type_slt.value == "ORDER_CHANGE_NOTIFICATION" || type_slt.value == "VENDOR_CONFIRM_EMAIL" || type_slt.value == "FOLLOWUP_EMAIL"){
		if (! $('#email_subject').val()){
			alert("Please enter the Subject."); 
			$("#email_subject").focus(); 
			return;
		}
		if (! $('#content').val()){
			alert("Please enter the Content."); 
			$("#content").focus(); 
			return;
		}
		var rdo_complete = document.getElementsByName("complete");
		if (rdo_complete[0].checked == true && ! $('#contact_date').val()){
			alert("Please enter the Contact Date."); 
			$("#contact_date").focus(); 
			return; 
		}
		if (rdo_complete[1].checked == true && ! $('#schedule_date').val()){
			alert("Please enter the Schedule Date."); 
			$("#schedule_date").focus(); 
			return; 
		}
	}else if (type_slt.value == "SALES_NOTES" || type_slt.value == "SHIPMENT" || type_slt.value == "ACCOUNTING" || type_slt.value == "PRODUCTION" || type_slt.value == "FOLLOWUP_DATE" || type_slt.value == "CROSS_SELLING"){
		if (type_slt.value == "FOLLOWUP_DATE"){
			if ($('#followup_date').val() == emptyDate){
				alert("Please enter the Followup Date."); 
				$("#followup_date").focus(); 
				return;
			}
		}
		if (! $('#description').val()){
			alert("Please enter the Description."); 
			$("#description").focus(); 
			return;
		}
	}
	var action = globalUrl+'quorder/quorderInstruction/saveInstructionAct';
	var form = $("#instruction_form"); 
	var options = {
		success:function(data) { 
			if(data == "SUCCESS"){
				// refresh the source list
				var url = globalUrl+"quorder/quorderInstruction/searchInstructionAct?search_type=ALL&custNo=" + $('#custNo').val() + "&quorderNo="+ $('#quorderNo').val()+"&codeType="+$('#codeType').val();
				parent.document.getElementById('instructionIframe').src = url;
				if (op_type == 'add'){
					parent.$('#instruction_dlg').dialog('close'); 
				}else{
					parent.$('#instruction_update_dlg').dialog('close'); 
				}
			}else{
				alert(data); 
			}
		}, 
		error: function(){
			alert('error...'); 
		}, 
		resetForm:false, 
		url:action,
		type:"POST" 
	}; 
	form.ajaxForm(options);
	form.submit();
}

function getEmailTypes(){ 
	var reqUrl = globalUrl + "quorder/quorderInstruction/getEmailTplAct?op=level_one&codeType=" +$('#codeType').val()+ "&quorderNo="+$('#quorderNo').val();
	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
     		var msg = eval('(' + data + ')');
			var type_slt = document.getElementById('content_slt');
			type_slt.options.length = 0; 
			var i = -1;
			for ( i in msg ) {
				type_slt.options.add(new Option(msg[i], msg[i]));
			} 
		},
		error: function(xhr, textStatus){
			alert("Failed to access the web server . Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
				//alert("Timeout!");
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
			}
		}
	});
}

function refresh_content(obj){ 
	var reqUrl = globalUrl + "quorder/quorderInstruction/getEmailTplAct?op=level_two&codeType=" +$('#codeType').val()+ "&quorderNo="+$('#quorderNo').val() + "&typeName=" + obj.value;
	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
     		var msg = eval('(' + data + ')');
			var sub_slt = document.getElementById('sub_slt');
			var content = document.getElementById('content');
			sub_slt.options.length = 0; 
			var tt = msg[0].split("#####");
			if (tt[0]){
				$('#sub_slt_div').show();
				for ( i in msg ) {
					var part = msg[i].split("#####");
					var mName = part[0];
					var mContent = part[1];
					sub_slt.options.add(new Option(mName, mContent)); 
				} 
				content.value = tt[1];
			}else{
				$('#sub_slt_div').hide();
				content.value = tt[1];
			}

		},
		error: function(xhr, textStatus){
			alert("Failed to access the web server . Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
				//alert("Timeout!");
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
			}
		}
	});
}

function refresh_content_00(obj){
	if (obj.value == "ftc"){
		$('#sub_slt_div').hide();
		$('#content').val("FT-FTC Notice"); 
	}else if(obj.value == "promotion"){
		$('#sub_slt_div').show();
		var slt = document.getElementById('sub_slt');
		slt.options.length = 0;
		slt.options.add(new Option("",""));
		slt.options.add(new Option("GS56789 - $20 Off","GS56789 - $20 Off"));
		slt.options.add(new Option("10% Off","10% Off")); 
	}else if(obj.value == "catalog"){
		$('#sub_slt_div').show();
		var slt = document.getElementById('sub_slt');
		slt.options.length = 0;
		slt.options.add(new Option("",""));
		slt.options.add(new Option("07cc  -2007 SUMMER SPECTACULAR","07cc  -2007 SUMMER SPECTACULAR"));
		slt.options.add(new Option("06SS  -2006 SUMMER SPECTACULAR","06SS  -2006 SUMMER SPECTACULAR")); 
	}else{
		$('#sub_slt_div').hide();
	}
}
