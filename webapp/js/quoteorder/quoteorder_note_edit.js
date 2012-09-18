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

//contact_date发生变化时触发时执行的方法
function change_contact_date(dateObj) {
	today =  formatDate(new Date());
	if (dateObj.value < today){
		var rdo_complete = document.getElementsByName("status");
		rdo_complete[0].checked = true;
		rdo_complete[1].checked = false; 
       $('#schedule_date').val(""); 
	}else{
		alert ("Contact date shouldn't be in the future!"); 
		$("#contact_date").focus(); 
		$("#contact_date").val(""); 
	}
}

//send date发生变化时触发时执行的方法
function change_send_date(dateObj) {
	today =  formatDate(new Date());
	if (dateObj.value < today){
		alert ("Send date should be in the future!"); 
		$("#contact_date").focus(); 
		$("#contact_date").val(""); 
	}
}

//schedule_date发生变化时触发时执行的方法
function change_schedule_date(dateObj) {
	today =  formatDate(new Date());
	if (dateObj.value >= today){
		var rdo_complete = document.getElementsByName("status");
		rdo_complete[0].checked = false;
		rdo_complete[1].checked = true; 
        $('#contact_date').val(""); 
	}else{
		alert ("Schedule date shouldn't be the past!"); 
		$("#schedule_date").focus(); 
		$("#schedule_date").val(""); 
	}
}
//note_type发生变化时触发时执行的方法
function change_note_type(type_slt){
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


//从下拉框中更换mail To
function change_mailto(){
	var email_to = $('#email_to').val();
	$('#receipt').val(email_to);
}

function refresh_sub_slt(obj){
	$('#receipt').val(obj.value);
}

//update source in session
function saveNote(){
    $('#content').attr("value",$("div.nicEdit-main").html());
	var type_slt = document.getElementById('notetype_sel');
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
		var rdo_complete = document.getElementsByName("status");
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
		$('#description').val("");
	}else if (type_slt.value == "SALES_NOTES" || type_slt.value == "SHIPMENT" || type_slt.value == "ACCOUNTING" || type_slt.value == "PRODUCTION" || type_slt.value == "FOLLOWUP_DATE" || type_slt.value == "CROSS_SELLING"){
		if (type_slt.value == "FOLLOWUP_DATE"){
			if ($('#followup_date').val() == undefined || $('#followup_date').val() == ""){
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
		$('#content').val("");//清空
	}
	var action = orderquoteObj.url("saveNote");
	var form = $("#instruction_form");
	var options = {
		success:function(data) {
		
			parent.$('#instruction_dlg').dialog('close');
			parent.$('#instruction_update_dlg').dialog('close');
			if(parent.document.getElementById('instructionIframe')!=null){
				parent.document.getElementById('instructionIframe').src = parent.frames["instructionIframe"].document.URL; 
			}else{
				parent.$('#orderNoteDiv').append(data);
			}
		    
		}, 
		error: function(){
			parent.$('#instruction_dlg').dialog('close');
			parent.$('#instruction_update_dlg').dialog('close');
			if(parent.document.getElementById('instructionIframe')!=null){
				parent.document.getElementById('instructionIframe').src = parent.frames["instructionIframe"].document.URL; 
			}else{
				parent.$('#orderNoteDiv').append(data);
			}
		    

		}, 
		resetForm:false, 
		url:action,
		type:"POST",
		async:false
	};
	form.ajaxSubmit(options);
//	form.submit();



}

//update source in session
function saveMailLog(){
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
	if (! $('#receipt').val()){
		alert("Please enter the Recei."); 
		$("#receipt").focus(); 
		return;
	}
	var action = orderquoteObj.url("saveNote")+"&orderNoStrs="+parent.$("#choiceOption").val();
	if(parent.$("#choiceOption").val()==null||parent.$("#choiceOption").val()=="") {
		action = orderquoteObj.url("saveNote");
	}
	var form = $("#instruction_form");
	var options = {
		success:function(data) {
			alert("Success.");
			parent.$('#instruction_dlg').dialog('close');
			parent.$('#instruction_update_dlg').dialog('close');
			parent.window.location.reload();
		}, 
		error: function(){
			parent.$('#instruction_dlg').dialog('close');
			parent.$('#instruction_update_dlg').dialog('close');
		}, 
		resetForm:false, 
		url:action,
		type:"POST",
		async:false
	};
	form.ajaxSubmit(options);
//	form.submit();



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
	input.name = "upload";
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
	var height = parent.$('#instruction_dlg').dialog('option','height');
	parent.$('#instruction_dlg').dialog('option','height',height+10);
	
	var height2 = parent.$('#instruction_update_dlg').dialog('option','height');
	parent.$('#instruction_update_dlg').dialog('option','height',height2+10);
}

function delete_file(index){
	var listDiv = document.getElementById("doc_list"); 
	var delDiv = document.getElementById(index+"_docDiv");
	listDiv.removeChild(delDiv); 
	var docDelIndexs = document.getElementById("docDelIndexs");
	if(docDelIndexs.value == ""){
		docDelIndexs.value = index;
	}else{
		docDelIndexs.value += ","+index;
	}
	var height = parent.$('#instruction_dlg').dialog('option','height');
	parent.$('#instruction_dlg').dialog('option','height',height-10);
	
	var height2 = parent.$('#instruction_update_dlg').dialog('option','height');
	parent.$('#instruction_update_dlg').dialog('option','height',height2-10);
}

function downloadFile (filePath, fileName) {
	$("#downloadForm").attr("action","download.action");
	$("#myFileName").val(fileName);
	$("#myFilePath").val(filePath);
	$("#downloadForm").submit();
}
