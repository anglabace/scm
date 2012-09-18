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
	for(var i=0;i<obj.options.length;i++){
	if(obj.options[i].value == val){
		obj.options[i].selected = "selected";
		break;
		}
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

function show_edit_addrform(addressKey, sessContactNo)
{
	var href = 'contact/contact_address!edit.action?addressKey='+addressKey +'&sessContactNo='+sessContactNo+'&operation_method=edit';
	parent.$('#editContactAddrDialogTrigger').val(href);
	parent.$('#editContactAddrDialogTrigger').click();
}

function del_address(addrId, sessContactNo)
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
	var id_str = get_checked_str(addrId);
	$.ajax({
		url: 'contact_address!delete.action?sessContactNo='+sessContactNo,
		type: 'GET',
		data: 'addr_id_str='+id_str,
		dataType: 'text',
		error: function(){
		},
		success: function(data){
			if(data == 'success')
			{
				window.location.reload();
				clearContactCheck() ;
			}else{
				alert(data);
			}
		}
	});
}

function select_address(addressKey, sessContactNo)
{
	//var defaultFlags = document.getElementById("defaultFlags").value;
	var href = 'contact_address!select.action?addressKey='+addressKey+'&sessContactNo='+sessContactNo;
	parent.$('#selectAddrDialogTrigger').val(href);
	parent.$('#selectAddrDialogTrigger').click();

}

function change_type()
{
	$("#defaultFlag").attr('checked', false);
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

