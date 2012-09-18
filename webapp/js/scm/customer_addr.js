//it's used to check all checkboxes.
function choose_all(e, tag_name)
{
	var a = document.getElementsByName(tag_name);
	for (var i=0; i<a.length; i++){
		a[i].checked = e.checked;
	}
}

function del_address(addrId, form_id, action_name, cust_no)
{
	if(!check_any_selected(addrId))
	{
		alert('select at least one, please.');
		return;
	}
	else if(!confirm("Are you sure to continue?"))
	{
		return;
	}
	
	var form = $("#"+form_id);
	//ajax form post
	var options = {
		success:function(data)
		{	
			window.location.href = 'customer/custAddr/listCustAddrAct?cust_no=' + cust_no;
		},
		error: function(){
			alert('error');
		},
		resetForm:true,
		url:action_name+"?custNo="+cust_no,
		type:"POST"
	};
	form.ajaxForm(options);
	form.submit();
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

function add_address(bt_obj, form_name, action_name){
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
			parent.parent.location.reload();
			parent.parent.GB_hide();
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

function edit_address(bt_obj, form_name, action_name){
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
			parent.parent.location.reload();
			parent.parent.GB_hide();
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