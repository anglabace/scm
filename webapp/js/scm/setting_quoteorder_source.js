$(function(){
	$("input[name$='Cost']").keypress(function (e){ 
        var $obj=$(this);
	//alert (e.which || e.keyCode); 
		var keynum = e.which || e.keyCode;
		if ((keynum >= 48 && keynum <= 57) || keynum == 46 || keynum < 10){
			return true;
		}else{
            alert("Please enter the number.");
            $obj.focus();
			return false;
		}
	});

	$("input[name$='Cost']").keyup(function (e){ 
		var total = $('#listCost').val()?parseFloat($('#listCost').val()):0.00;
		total += $('#postageCost').val()?parseFloat($('#postageCost').val()):0.00;
		total += $('#printingCost').val()?parseFloat($('#printingCost').val()):0.00;
		total += $('#mailingCost').val()?parseFloat($('#mailingCost').val()):0.00;
		/*
		total += parseInt($('#printingCost').val());
		alert (total);
		total += parseInt($('#mailingCost').val());
		*/
		var total = formatFloat(total, 2);
		$('#total_cost').val(total);
	});
});

function showSearchBox(){
	$('#searchPS').dialog('open'); 
} 

//update source in session
function update_source(id){
	if($("#source_form").valid() == false) {
		return false;
	} 
	var opType = $("#opType").val(); 
	var action = 'quote_order_source!save.action?';
	var form = $("#source_form"); 
	var sourceId = $('#sourceId').val();
	var page_no = 1;
	var options = {
		success:function(data) { 
			var s1 = data.split(',')[0];
			var s2 = data.split(',')[1];
			if(s1 == "SUCCESS"){
				if (opType == 'add'){
					var content = '<tr id="source_row_'+s2+'"><td width="46" ><input type="checkbox" name="chkSource" value="'+s2+'" /></td> ';
					content += '<td width="150"><span id="code_'+s2+'"><a href="javascript:void(0)" onclick="javascript:show_edit_source(\''+s2+'\')">'+ $('#code').val()+'</span></a></td>';
					content += '<td width="150">&nbsp;<span id="name_'+s2+'">'+$('#name').val()+'</span></td>';
					content += '<td>&nbsp;<span id="desc_'+s2+'">'+$('#description').val()+'</span></td></tr>'; 
					parent.$('#sourceSearchResult').append(content);

					parent.$('#source_add_dialog').dialog('close'); 
				}else{
					var content = '<a href="javascript:void(0)" onclick="javascript:show_edit_source(\''+s2+'\')">'+ $('#code').val()+'</a>';
					parent.$('#code_'+s2).html(content);
					parent.$('#name_'+s2).html($('#name').val());
					parent.$('#desc_'+s2).html($('#description').val());
					parent.$('#source_edit_dialog').dialog('close'); 
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

function check_number_source(obj){
	if (!obj.value){
		return;
	}
	if (isNaN(obj.value)){
		alert ("Should be number!");
		obj.value = "";
		obj.focus();
		return;
	}
}
