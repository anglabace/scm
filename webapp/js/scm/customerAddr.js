//it's used to check all checkboxes.
function choose_all(e, tag_name)
{
	var a = document.getElementsByName(tag_name);
	for (var i=0; i<a.length; i++){
		a[i].checked = e.checked;
	}
}

function check_any_selected(name)
{
	var a = document.getElementsByName(name);
	var flag = false;
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			flag = true;
			break;
		}
	}
	return flag;
}

function set_selected(id, val)
{
	var obj = document.getElementById(id);
	for(var i=0;i<obj.options.length;i++)
	if(obj.options[i].value == val)
	obj.options[i].selected = true;
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

function show_edit_addrform(id, custNo)
{
	var sessCustNo= document.getElementById("sessCustNo").value;
    var allData = document.getElementById(id+'_allData').value;
	var href = 'cust_address!edit.action?addrId='+id+'&custNo='+custNo+'&operation_method=edit';
	href += '&allData='+allData;
	href += '&sessCustNo='+sessCustNo;
	parent.$('#editCustomerAddrDialogTrigger').val(href);
	parent.$('#editCustomerAddrDialogTrigger').click();
}

function del_address(addrId, form_id, action_name, custNo)
{
	if(!check_any_selected(addrId))
	{
		alert('Select at least one, please.');
		return;
	}
	else if(!confirm("Are you sure to continue?"))
	{
		return;
	}
	
	var form = $("#"+form_id);
	var sessCustNo= document.getElementById("sessCustNo").value;
	//ajax form post
	var options = {
		success:function(data)
		{	
			window.location.href = 'cust_address!list.action?custNo='+custNo+"&sessCustNo="+sessCustNo;
		},
		error: function(){
			alert('error');
		},
		resetForm:true,
		url:action_name+"?custNo="+custNo,
		type:"POST"
	};
	form.ajaxForm(options);
	form.submit();
}

function select_address(addrId, custNo)
{
	//var defaultFlags = document.getElementById("defaultFlags").value;
	var sessCustNo = document.getElementById("sessCustNo").value;
	var href = 'cust_address!custAddrPickerAct.action?addrId='+addrId+'&custNo='+custNo+'&sessCustNo='+sessCustNo;
	parent.$('#selectAddrDialogTrigger').val(href);
	parent.$('#selectAddrDialogTrigger').click();
}

function change_type()
{
	$("#defaultFlag").attr('checked', true);
}




