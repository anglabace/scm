function cc(e, name) {
	var a = document.getElementsByName(name);
	for ( var i = 0; i < a.length; i++)
		a[i].checked = e.checked;
}

function remove(form_id) {
	var check_name = "delPriceIdList";
	if (!check_any_selected(check_name)) {
		alert('please choose at least one!');
		return;
	}
	$.ajax({
		url:"cust_spcl_prc!delete.action",
		data:$("#"+form_id).serialize(),
		dataType:"text",
		async:false,
		success:function(data){
			if(data == "SUCCESS"){
				$(":checkbox").attr("checked", false);
				window.location.reload();
			}else{
				if(data){
					alert(data);
				}else{
					alert("System error! Please contact system administrator for help.");
				}
			}
		},
		error:function(){
			alert("Failed to delete the item. Please contact system administrator for help.");
		}
	});
}

function check_any_selected(name) {
	var a = document.getElementsByName(name);
	var flag = false;
	for ( var i = 0; i < a.length; i++) {
		if (a[i].checked) {
			flag = true;
			break;
		}
	}
	return flag;
}

$(document).ready(function(){
	//document.ready  begin

//document.ready  end
});