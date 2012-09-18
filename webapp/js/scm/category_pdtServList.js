function   cc(e)  
{  
	var a = document.getElementsByName("ids");
	for(var i=0; i<a.length; i++){a[i].checked = e.checked;}
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
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2');  
    
    parent.$('#catPdtServAddDialog').dialog({
    	autoOpen: false,
    	height: 360,
    	width: 720,
    	modal: true,
    	bgiframe: true,
    	buttons: {}
    });
    parent.$('#catPdtServAddDialog').dialog("option", "open",function(){
    	var catId = parent.$("#ctgId").val();
    	var type = parent.$("#categoryType").val();
    	var htmlStr = '<iframe src="pdtServ/pdtServ/catPdtServAddAct?catId='+catId+'&type='+type+'&catalogId='+parent.$("#cataId").val()+'" height="300" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
    	
    	parent.$('#catPdtServAddDialog').html(htmlStr);
    });
    $("#catPdtServAddDialogTrigger").click(function(){
    	parent.$('#catPdtServAddDialog').dialog('open');
    });
    
    
    $("#pdtServDelete").click(function(){
    	delNos = get_checked_str("ids");
    	ctgId = parent.$("#ctgId").val();
    	if(!delNos) {
    		alert("Please select one item to continue your operation.");
    		return;
    	}
    	if(!confirm('Are you sure to delete?')) {
    		return;
    	}
    	
    	$.ajax({
    		type: "POST",
    		url: "category/category/delCatPdtServAct",
    		data: "ids=" + delNos + "&id=" + ctgId + "&type=" + $("#categoryType").val(),
    		success: function(msg) {
	    		trObj = $("#catPdtServList").find("tr");
				trObj.each(function(){
					if($(this).find(":checkbox").attr("checked") == true){
						$(this).remove();
					}
				});
    		},
    		error: function(msg) {
    			alert("Error: Delete failed");
    		}
    	});
    });
});